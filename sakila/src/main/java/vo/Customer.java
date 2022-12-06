package vo;

public class Customer {
   private int customerId;
   private int storeId;
   private String firstName;
   private String lastName;
   private String email;
   private String addressId;
   private int active;
   private String createdate;
   private String lastupadte;
   public int getCustomerId() {
      return customerId;
   }
   public void setCustomerId(int customerId) {
      this.customerId = customerId;
   }
   public int getStoreId() {
      return storeId;
   }
   public void setStoreId(int storeId) {
      this.storeId = storeId;
   }
   public String getFirstName() {
      return firstName;
   }
   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }
   public String getLastName() {
      return lastName;
   }
   public void setLastName(String lastName) {
      this.lastName = lastName;
   }
   public String getEmail() {
      return email;
   }
   public void setEmail(String email) {
      this.email = email;
   }
   public String getAddressId() {
      return addressId;
   }
   public void setAddressId(String addressId) {
      this.addressId = addressId;
   }
   public int getActive() {
      return active;
   }
   public void setActive(int active) {
      this.active = active;
   }
   public String getCreatedate() {
      return createdate;
   }
   public void setCreatedate(String createdate) {
      this.createdate = createdate;
   }
   public String getLastupadte() {
      return lastupadte;
   }
   public void setLastupadte(String lastupadte) {
      this.lastupadte = lastupadte;
   }
   
}