import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Update {
	/*
	 * Update class constructor
	 */
	public Update() {
		
	}
	
	  /**
     * SQL Query to update a specified product id
     * 
     * @param c  Database connection to be used
     * @param id Id to look up
     */
	public void updateInventoryItem(String productID, int quantity, float wholesale, float salesPrice, String supplierID) {
		
		Connection c = null;
		c = CrudOperator.connect();
	   
	        try {
	            Statement stmt = null;

	            c.setAutoCommit(false);
	            stmt = c.createStatement();

	            String out = "UPDATE products SET quantity =" + quantity + ", wholesale_cost = " + wholesale
	                    + ", sale_price = " + salesPrice + ", supplier_id = '" + supplierID + "' WHERE product_id = '" + productID
	                    + "';";

	            stmt.executeUpdate(out);
	            stmt.close();
	            c.commit();

	            System.out.println(productID + " was successfully updated!");

	        } catch (Exception e) {
	            e.printStackTrace();
	            System.exit(1);
	        }
	    }
	

	public void updateCustomerOrderItem(String id, String date, String custEmail, int custLocation, int quantity) {
		
		Connection c = null;
		c = CrudOperator.connect();
		
		Read reader = new Read();
		int initialQuantity = reader.readForUpdateProducts(id);
		
		try {
			
			Statement stmt = null;
			c.setAutoCommit(false);
			stmt = c.createStatement();
			
			String out = "UPDATE cust_orders SET product_quantity = " + quantity + ", cust_email = '" + custEmail
                    + "', cust_location = " + custLocation + ", date = '" + date + "' WHERE product_id = '" + id
                    + "';";

            stmt.executeUpdate(out);
            stmt.close();
            c.commit();

            System.out.println(id + " was successfully updated!");
            if (initialQuantity != 0) {
            	updateProductsUpdatedCustOrder(initialQuantity, id, quantity);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
	}
	
	/**
     * Will attempt to update the prod_quantity of a product with a matching product_id
     * based on how much of a product is being purchased in an incoming order
     * 
     * Is called in the customerOrder method
     * 
     * @param c
     * @param amountBought
     * @param productId
     */
    public void updateProductsNewCustOrder( int amountBought, String productId) {

    	Connection c = null;
		c = CrudOperator.connect();
    	
        try {

            //SQL query to try to get the current product quantity and setting it to previousAmount
            Statement stmt = null;
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT quantity FROM Products where product_id = "+productId+";" );
            int previousAmount = rs.getInt("quantity");
            rs.close();
            stmt.close();
            c.close();

            //subtracting current product quantity by order quantity
            int newAmount = previousAmount - amountBought;

            //SQL query to attempt to update the product quantity in the products table 
            stmt = c.createStatement();
            c.setAutoCommit(false);

            String out = ("UPDATE Products set quantity ="+newAmount+" where product_id = "+productId+";");
            stmt.executeUpdate(out);
            stmt.close();
            c.commit();

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

    }
    
    public void updateProductsUpdatedCustOrder( int initialQuantity, String productId, int quantity) {

    	Connection c = null;
		c = CrudOperator.connect();
		
        try {

        	
            //SQL query to try to get the current product quantity and setting it to previousAmount
            Statement stmt = null;
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT quantity FROM Products where product_id = "+productId+";" );
            int previousAmount = rs.getInt("quantity");
            int newAmount = previousAmount;
            rs.close();
            stmt.close();
            c.close();

            int newQuantity = quantity - initialQuantity;
            
            if (newQuantity > 0) {
            	newAmount = previousAmount - newQuantity;
            }
            else if (newQuantity < 0) {
            	newAmount = previousAmount + newQuantity;
            }

            //SQL query to attempt to update the product quantity in the products table 
            stmt = c.createStatement();
            c.setAutoCommit(false);

            String out = ("UPDATE Products set quantity ="+newAmount+" where product_id = "+productId+";");
            stmt.executeUpdate(out);
            stmt.close();
            c.commit();

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

    }
}