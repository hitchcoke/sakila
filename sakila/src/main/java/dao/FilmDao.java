package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import util.DB;
import vo.Film;

public class FilmDao {
	public ArrayList<Film> selectFilmListBySearch(String word, String type, int beginPage, int rowPerPage){ 
		ArrayList<Film> list = new ArrayList<Film>();
		DB db =null;
		PreparedStatement stmt = null;
		Connection conn= null;
		ResultSet rs = null;
		try {
			db= new DB();
			conn = db.getConnection();
			
			String sql= null;
			
			if(word==null||word.equals("")) {
				sql="SELECT * FROM film ORDER BY film_id LIMIT ?,?";
				stmt = conn.prepareStatement(sql);
				stmt.setInt(1, beginPage);
				stmt.setInt(2, rowPerPage);
				rs = stmt.executeQuery();
			}else {
				sql= "SELECT * FROM film "
						+ "WHERE "+type+" LIKE ?"
						+ "order by film_id limit ?,?";
				
				stmt = conn.prepareStatement(sql);
				stmt.setString(1, "%"+word+"%");
				stmt.setInt(2, beginPage);
				stmt.setInt(3, rowPerPage);
				rs = stmt.executeQuery();
			}
			
			
			System.out.println(word+type);
			
			
			while(rs.next()) {
				Film f = new Film();
				f.setFilmId(rs.getInt("film_id"));
				f.setDescription(rs.getString("description"));
				f.setLanguageId(rs.getInt("language_id"));
				f.setLastUpdate(rs.getString("last_update"));
				f.setLength(rs.getInt("length"));
				f.setOriginalLanguageId(rs.getInt("original_language_id"));
				f.setRating(rs.getString("rating"));
				f.setReleaseYear(rs.getString("release_year"));
				f.setRentalDuration(rs.getInt("rental_duration"));
				f.setRentalRate(rs.getDouble("rental_rate"));
				f.setReplacementCost(rs.getDouble("replacement_cost"));
				f.setSpecialFeatures(rs.getString("special_features"));
				f.setTitle(rs.getString("title"));
				list.add(f);
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				db.close(rs, stmt, conn);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	public int filmCount(String type, String word, int rowPerPage) {
		int lastPage=0;
		int count = 0;
		String sql=null;
		DB db =null;
		PreparedStatement stmt = null;
		Connection conn= null;
		ResultSet rs = null;
		
		try {
			db= new DB();
			conn = db.getConnection();
			if(word==null||word.equals("")) {
				sql="SELECT COUNT(*) FROM film";
				stmt=conn.prepareStatement(sql);
				rs=stmt.executeQuery();
			}else {
				sql="SELECT COUNT(*) FROM film WHERE "+type+" LIKE ?";
				stmt= conn.prepareStatement(sql);
				stmt.setString(1, "%"+word+"%");
				rs=stmt.executeQuery();
			}
			
			if(rs.next()){
				count= rs.getInt("COUNT(*)");
			}
			
			lastPage= count/rowPerPage;
			if(count % rowPerPage !=0){
				lastPage++;
			} 
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				db.close(rs, stmt, conn);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		return lastPage;
	}
}
