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
	  * Updates product inventory item based on given product ID.
	  * 
	  * @param productID
	  * @param quantity
	  * @param wholesale
	  * @param salesPrice
	  * @param supplierID
	  */
	public void updateInventoryItem(String productID, int quantity, float wholesale, float salesPrice, String supplierID) {
		
		Connection c = null;
		c = CrudOperator.connect();
	   
	        try {
	            Statement stmt = null;

	            c.setAutoCommit(false);
	            stmt = c.createStatement();

	            //updates products table based on user info
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
	
	/**
	 * Updates item in customer order database based on given product ID.
	 * Reads current entry for given product ID and retrieves quantity before updating.
	 * The original quantity, the new quantity, and the product ID are passed to 
	 * updateProductsUpdatedCustOrder method to update product inventory based on updated customer order.
	 * 
	 * @param id
	 * @param date
	 * @param custEmail
	 * @param custLocation
	 * @param quantity
	 */
	public void updateCustomerOrderItem(String id, String date, String custEmail, int custLocation, int quantity) {
		
		Connection c = null;
		c = CrudOperator.connect();
		int quantityRet = 0;
		
		try {
			
			Statement stmt = null;
			c.setAutoCommit(false);
			stmt = c.createStatement();
			
			//retrieves original order quantity before it gets updated
			ResultSet res = stmt.executeQuery("Select * FROM cust_orders WHERE product_id = '" + id + "';");
			if (res.next()) {
				quantityRet = res.getInt("product_quantity");
			}
		
            res.close();
			
            //updates cust_orders table using user info
			String out = "UPDATE cust_orders SET product_quantity = " + quantity + ", cust_email = '" + custEmail
                    + "', cust_location = " + custLocation + ", date = '" + date + "' WHERE product_id = '" + id
                    + "';";

            stmt.executeUpdate(out);
            stmt.close();
            c.commit();

            System.out.println(id + " was successfully updated!");
            
            //calls method to update product inventory based on changes to order quantity
            updateProductsUpdatedCustOrder(quantityRet, id, quantity);
            
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
	}
	
	/**
	 * This method is called by the create class by the method that creates customer orders.
	 * Takes customer order quantity (amountBought) and uses that to update the product inventory.
	 * 
	 * @param amountBought
	 * @param productId
	 */
    public void updateProductsNewCustOrder( int amountBought, String productId) {

    	Connection c = null;
		c = CrudOperator.connect();
		int previousAmount = 0;
    	
        try {

            //SQL query to try to get the current product quantity and setting it to previousAmount
            Statement stmt = null;
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT quantity FROM Products where product_id = '"+productId+"';" );
            
            if (rs.next()) {
            	previousAmount = rs.getInt("quantity");
            }
            rs.close();
            stmt.close();
            //c.close();

            //subtracting current product quantity by order quantity
            int newAmount = previousAmount - amountBought;

            //SQL query to attempt to update the product quantity in the products table 
            stmt = c.createStatement();
            c.setAutoCommit(false);

            String out = ("UPDATE Products set quantity ="+newAmount+" where product_id = '"+productId+"';");
            stmt.executeUpdate(out);
            stmt.close();
            c.commit();
            c.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

    }
    /**
     * Called by updateCustomerOrderItem to update products inventory based on update to customer order.
     * Finds difference between old costumer order quantity and new customer order quantity.
     *  
     * @param initialQuantity
     * @param productId
     * @param quantity
     */
    public void updateProductsUpdatedCustOrder( int initialQuantity, String productId, int quantity) {

    	Connection c = null;
		c = CrudOperator.connect();
		int previousAmount = 0;
		
        try {

        	
            //SQL query to try to get the current product quantity and setting it to previousAmount
            Statement stmt = null;
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT quantity FROM Products where product_id = '"+productId+"';" );
            if (rs.next()) {
            	previousAmount = rs.getInt("quantity");
            }
            int newAmount = previousAmount;
			
            rs.close();
            stmt.close();

            //finds difference between old quantity and new quantity
            int newQuantity = quantity - initialQuantity;
            
            newAmount = previousAmount - newQuantity;

            //SQL query to attempt to update the product quantity in the products table 
            stmt = c.createStatement();
            c.setAutoCommit(false);

            String out = ("UPDATE Products set quantity ="+newAmount+" where product_id = '"+productId+"';");
            stmt.executeUpdate(out);
            stmt.close();
            c.commit();
            c.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

    }
}