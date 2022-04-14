package crudOperations;
/**
 * Data Transfer class that allows you to get the data from the database
 * using getters and setters
 */
public class DataTransfer {
	//Instance variables to contain the inventory data
	private String inventoryID;
	private int inventoryQuantity;
	private float inventoryCost;
	private float inventorySale;
	private String inventorySupID;
	
	private String custDate;
	private String custEmail;
	private int custLocation;
	private String custProductID;
	private int custQuantity;
	/**
	 * Data Transfer Constructor
	 */
	public DataTransfer() {
		inventoryID = "";
		inventoryQuantity = 0;
		inventoryCost = 0;
		inventorySale = 0;
		inventorySupID = "";
	}
	/**
	 * sets the inventory id
	 * @param put the inventory id you want
	 */
	public void setInventoryID(String newID) {
		inventoryID = newID;
	}
	/**
	 * Returns the inventoryID
	 */
	public String getInventoryID() {
		return inventoryID;
	}
	/**
	 * Sets quantity
	 * @param put the quantity you want
	 */
	public void setInventoryQuantity(int newQuantity) {
		inventoryQuantity = newQuantity;
	}
	/**
	 * Returns the quantity
	 */
	public int getInventoryQuantity() {
		return inventoryQuantity;
	}
	/**
	 * Sets cost
	 * @param put the wholesale cost you want
	 */
	public void setInventoryCost(float newCost) {
		inventoryCost = newCost;
	}
	/**
	 * returns the wholesale cost
	 */
	public float getInventoryCost() {
		return inventoryCost;
	}
	/**
	 * Sets the sale price
	 * @param put the sale price you want
	 */
	public void setInventorySale(float newSale) {
		inventorySale = newSale;
	}
	/**
	 * Returns the sale price
	 */
	public float getInventorySale() {
		return inventorySale;
	}
	/**
	 * Sets Supplier ID
	 * @param put the supplier ID you want
	 */
	public void setInventorySupID(String newSupID) {
		inventorySupID = newSupID;
	}
	/**
	 * Returns Supplier ID
	 */
	public String getInventorySupID() {
		return inventorySupID;
	}
	
	//-----------------------------------------------------------------------
	public void setCustomerDate(String newDate) {
		custDate = newDate;
	}
	
	public String getCustomerDate() {
		return custDate;
	}
	
	public void setCustomerEmail(String newEmail) {
		custEmail = newEmail;
	}
	
	public String getCustomerEmail() {
		return custEmail;
	}
	
	public void setCustomerLocation(int newLocation) {
		custLocation = newLocation;
	}
	
	public int getCustomerLocation() {
		return custLocation;
	}
	
	public void setCustomerProductID(String newID) {
		custProductID = newID;
	}
	
	public String getCustomerProductID() {
		return custProductID;
	}
	
	public void setCustomerQuantity(int newQuantity) {
		custQuantity = newQuantity;
	}
	
	public int getCustomerQuantity() {
		return custQuantity;
	}
}
