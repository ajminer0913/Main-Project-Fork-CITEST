package crudOperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import crudOperations.CrudOperator;

/*
 * Read class that reads data in the data base
 */
public class Read extends CrudOperator {
	/*
	 * Read Constructor
	 */
	public Read() {
		
				
	}

	/**
	 * 
	 * @param data base connection
	 * @param product ID
	 * This method reads the SQL data base and prints the value give a
	 * product ID
	 */
	public DataTransfer read(String id) {
		//creating a new transfer object
		DataTransfer pogo = new DataTransfer();
		try {
			
			Connection c = null;
			//Calls the abstract class for SQL connection
			c = CrudOperator.connect();
			
			Statement stmt = null;
			c.setAutoCommit(false);
			stmt = c.createStatement();
			
			ResultSet rs = stmt.executeQuery("Select * FROM Products WHERE product_id = '" + id + "';");
			
			while(rs.next()) {
				String idRet = rs.getString("product_id");
                int quantRet = rs.getInt("quantity");
                float costRet = rs.getFloat("wholesale_cost");
                float saleRet = rs.getFloat("sale_price");
                String supRet = rs.getString("supplier_id");
                
              //using transfer object to store data
                pogo.setInventoryID(idRet);
                pogo.setInventoryQuantity(quantRet);
                pogo.setInventoryCost(costRet);
                pogo.setInventorySale(saleRet);
                pogo.setInventorySupID(supRet);
			}
			rs.close();
			stmt.close();
		}catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		return pogo;
	}
	/**
	 * Method to get all data from the database into JTable for use in the GUI
	 * @return array of objects to be used for jTable
	 */
	public Object[][] readAll() {
		Object[][] data = null;
		try{
			
			Connection c = null;
			c = CrudOperator.connect();
		
			Statement stmt = null;
			c.setAutoCommit(false);
			stmt = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, 
	                ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = stmt.executeQuery("SELECT product_id, quantity, wholesale_cost, sale_price, supplier_id from products;");

			int rowCount = getRowCount(rs); // Row Count
			int columnCount = getColumnCount(rs); // Column Count

			data = new Object[rowCount][columnCount];

			// Starting from First Row for Iteration
			rs.beforeFirst();

			int i = 0;
			
			while (rs.next()) {

				int j = 0;

				data[i][j++] = rs.getString("product_id");
				data[i][j++] = rs.getInt("quantity");
				data[i][j++] = rs.getFloat("wholesale_cost");
				data[i][j++] = rs.getFloat("sale_price");
				data[i][j++] = rs.getString("supplier_id");

				i++;
			}
			 
			rs.close();
			stmt.close();
		
	}catch (Exception e) {
		e.printStackTrace();
		System.exit(1);
	}
		return data;
	}
	
	public void readCustOrder(String custEmail) {
try {
			
			Connection c = null;
			//Calls the abstract class for SQL connection
			c = CrudOperator.connect();
			
			Statement stmt = null;
			c.setAutoCommit(false);
			stmt = c.createStatement();
			
			ResultSet rs = stmt.executeQuery("Select * FROM cust_orders WHERE cust_email = '" + custEmail + "';");
			
			while(rs.next()) {
				String emailRet = rs.getString("cust_email");
                int locationRet = rs.getInt("cust_location");
                String productRet = rs.getString("product_id");
                int quantityRet = rs.getInt("product_quantity");
                
                System.out.println("");
                System.out.println("EMAIL: " + emailRet);
                System.out.println("LOCATION: " + locationRet);
                System.out.println("PRODUCT ID " + productRet);
                System.out.println("QUANTITY: " +quantityRet);
                System.out.println("");
			}
			rs.close();
			stmt.close();
		}catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	// Method to get row Count from ResultSet Object
	private int getRowCount(ResultSet rs) {

		try {
			
			if(rs != null) {
				
				rs.last();
				
				return rs.getRow(); 
			}
			
		} catch (SQLException e) {

			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		return 0;
	}

	// Method to get Column Count from ResultSet Object
	private int getColumnCount(ResultSet rs) {

		try {

			if(rs != null)
				return rs.getMetaData().getColumnCount();

		} catch (SQLException e) {

			System.out.println(e.getMessage());
			e.printStackTrace();
		}

		return 0;
	}
}
