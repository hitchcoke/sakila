package dao;
import java.sql.*;
import java.util.*;
import vo.*;

public class CustomerDao {
	
	//총 갯수 구하기
	public int customerCount()
	{
		int count = 0;
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try
		{
			String sql = "SELECT COUNT(*) FROM customer";
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/sakila","root","java1234");
			stmt = conn.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next())
			{
				count = rs.getInt("COUNT(*)");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			try
			{
				rs.close();
				stmt.close();
				conn.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		return count;
	}
	
	
	//Map 사용
	public ArrayList<HashMap<String, Object>> selectCustomerMapList(int beginRow, int rowPerPage)
	{
		ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try
		{
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/sakila","root","java1234");
			String sql = "SELECT"
					+ " cu.first_name firstName, cu.last_name lastName, ad.address address, ad.district district, ci.city city, co.country country"
					+ " FROM customer cu INNER JOIN address ad on cu.address_id = ad.address_id"
					+ "		INNER JOIN city ci ON ad.city_id = ci.city_id"
					+ "		INNER JOIN country co ON ci.country_id = co.country_id"
					+ " ORDER BY cu.customer_id ASC"
					+ " LIMIT ?, ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, beginRow);
			stmt.setInt(2, rowPerPage);
			rs = stmt.executeQuery();
			while(rs.next())
			{
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("firstName", rs.getString("firstName"));
				map.put("lastName", rs.getString("lastName"));
				map.put("address", rs.getString("address"));
				map.put("district", rs.getString("district"));
				map.put("city", rs.getString("city"));
				map.put("country", rs.getString("country"));
				list.add(map);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			try
			{
				rs.close();
				stmt.close();
				conn.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return list;
	
	}
	
	
	//4개 테이블을 조인 Customer X Address X City X Country 조인 ResultSet 타입
	//자료구조 -> 배열 -> 기본 자료구조 API 제공 (Collections 프레임 웍) -> list/Set/Map
	public ArrayList<CustomerAddressCityCountry> selectCustomerList(int beginRow, int rowPerPage)
	{
		ArrayList<CustomerAddressCityCountry> list = new ArrayList<CustomerAddressCityCountry>();
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try
		{
			Class.forName("org.mariadb.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/sakila","root","java1234");
			String sql = "SELECT cu.*, ad.*, ci.*, co.*"
					+ " FROM customer cu INNER JOIN address ad on cu.address_id = ad.address_id"
					+ "		INNER JOIN city ci ON ad.city_id = ci.city_id"
					+ "		INNER JOIN country co ON ci.country_id = co.country_id"
					+ " ORDER BY cu.customer_id ASC"
					+ " LIMIT ?, ?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, beginRow);
			stmt.setInt(2, rowPerPage);
			rs = stmt.executeQuery();
			while(rs.next())
			{
				CustomerAddressCityCountry cacc = new CustomerAddressCityCountry();
				
				//Customer table 결과셋
				Customer cu = new Customer();
				cu.setCustomerId(rs.getInt("cu.customer_id"));
				cu.setStoreId(rs.getInt("cu.store_id"));
				cu.setFirstName(rs.getString("cu.first_name"));
				cu.setLastName(rs.getString("cu.last_name"));
				cu.setEmail(rs.getString("cu.email"));
				cu.setActive(rs.getInt("cu.active"));
				cacc.setCustomer(cu);
				
				//Address table 결과셋
				Address ad = new Address();
				ad.setAddress(rs.getString("ad.address"));
				ad.setAddress2(rs.getString("ad.address2"));
				ad.setDistrict(rs.getString("ad.district"));
				ad.setPhone(rs.getString("phone"));
				cacc.setAddress(ad);
				
				
				//City table 결과셋
				City ci = new City();
				ci.setCity(rs.getString("ci.city"));
				cacc.setCity(ci);
				
				//Country table 결과셋
				Country co = new Country();
				co.setCountry(rs.getString("country"));
				cacc.setCountry(co);
				
				list.add(cacc);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally {
			try
			{
				rs.close();
				stmt.close();
				conn.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return list;
	}
	
}
