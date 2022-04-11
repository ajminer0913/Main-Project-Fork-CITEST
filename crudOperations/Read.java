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
	
	/**
	 * Method to read orders and profit in finance table for a specific day.
	 * 
	 * @param date
	 */
	public void readFinance(String date) {
		try {
					
					Connection c = null;
					//Calls the abstract class for SQL connection
					c = CrudOperator.connect();
					
					Statement stmt = null;
					c.setAutoCommit(false);
					stmt = c.createStatement();
					
					//query to return total orders and profit for a specific day
					ResultSet rs = stmt.executeQuery("Select * FROM finance WHERE date = '" + date + "';");
					
					//loop to set and print orders and profit from finance table
					while(rs.next()) {
						int orders = rs.getInt("orders");
		                float profit = rs.getFloat("profit");
		                
		                System.out.println("");
		                System.out.println("Number of Orders: " + orders);
		                System.out.println("Total Profit: " + profit);
		                System.out.println("");
					}
					rs.close();
					stmt.close();
				}catch (Exception e) {
					e.printStackTrace();
					System.exit(1);
				}
			}
	
	/**
	 * Method to read total orders and total profit over some period of time. Should be called by 
	 * a createFinanceReport method.
	 * 
	 * @param startDate
	 * @param endDate
	 * @return report
	 */
	public String readFinanceSummary(String startDate, String endDate) {
		
		String report = "";
		try{
			
			Connection c = null;
			c = CrudOperator.connect();
		
			Statement stmt = null;
			c.setAutoCommit(false);
			stmt = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, 
	                ResultSet.CONCUR_UPDATABLE);
			
			//query to find all rows in finance table that are within a certain range of date
			ResultSet rs = stmt.executeQuery("SELECT * FROM finance WHERE dates >= '" + startDate + "' "
					+ "AND dates <= '" + endDate + "';");

			int rowCount = getRowCount(rs); // Row Count
			int columnCount = getColumnCount(rs); // Column Count

			Object[][] data = null;
			data = new Object[rowCount][columnCount];

			// Starting from First Row for Iteration
			rs.beforeFirst();

			int i = 0;
			
			//loop to record and store daily orders and profit found in query
			while (rs.next()) {

				int j = 0;

				data[i][j++] = rs.getInt("orders");
				data[i][j++] = rs.getFloat("profit");

				i++;
			}
			 
			//initializing orders and profit
			float profit = 0;
			int orders = 0;
			
			//loop to calculate total orders and total profit over a period of time
			for (int p = 0; p < rowCount; p++) {
				
				int dailyOrders = (int)data[p][0];
				float dailyProfit = (float)data[p][1];
				profit = dailyProfit + profit;
				orders = orders + dailyOrders;
				
			}
			//this string is returned from method and just gives a basic financially summary
			report = "Total Orders From " + startDate + " To " + endDate + " : " + orders + ".\n Profit "
					+ "From " + startDate + " To " + endDate + " : " + profit + ".\n";
			
			rs.close();
			stmt.close();
			
		
	}catch (Exception e) {
		e.printStackTrace();
		System.exit(1);
	}
		return report;
	}
	
	/*
	 * Method used to read data into finance table. Takes the date and finds all orders placed on that date.
	 * Then stores quantity and product id of those orders. Queries the products table to get wholesale
	 * cost and sale price for the product id. Sale price, wholesale cost, and quantity are then used to 
	 * calculate the daily profit.
	 * 
	 * @param String date  
	 */
	public void readDailyOrdersIntoFinance (String date) {
		
		try{
			//connecting to database and creating statement
			Connection c = null;
			c = connect();
		
			Statement stmt = null;
			c.setAutoCommit(false);
			stmt = c.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, 
	                ResultSet.CONCUR_UPDATABLE);
			
			//query to find orders for specific day
			ResultSet rs = stmt.executeQuery("SELECT product_id, product_quantity FROM cust_orders"
					+ " WHERE date = '" + date +"';");

			int rowCount = getRowCount(rs); // Row Count
			int columnCount = getColumnCount(rs); // Column Count
			
			Object[][] data = null;
			data = new Object[rowCount][columnCount];

			// Starting from First Row for Iteration
			rs.beforeFirst();

			int i = 0;
			
			//loop to store product id and quantity into array of arrays for later use
			while (rs.next()) {

				int j = 0;

				data[i][j++] = rs.getString("product_id");
				data[i][j++] = rs.getInt("product_quantity");
				
				i++;
			}
			
			ResultSet invRS ;
			
			Object[][] dataInventory = new Object[rowCount][2] ;
			
			int n = 0;
			
			//loop to query products table for every product id to find wholesale cost and sale price
			//and store these values in array of arrays
			for (int k = 0; k  < rowCount; k++) {
				
				Object checkID = data[k][0];
				
				//query to check return wholesale cost and sale price
				invRS = stmt.executeQuery("SELECT wholesale_cost, sale_price FROM products WHERE "
						+ "product_id = '" + checkID + "';");
				
				invRS.beforeFirst();
				
				while (invRS.next()) {
				int j = 0;

				dataInventory[n][j++] = invRS.getFloat("wholesale_cost");
				dataInventory[n][j++] = invRS.getFloat("sale_price");
				
				n++;
				}
			}
			
			float profit = 0 ;
			
			//loop to calculate profit by finding profit of each order and adding that to total profit
			for (int p = 0; p < rowCount; p++) {
				
				int quantity = (int)data[p][1];
				float wholesale = (float)dataInventory[p][0];
				float sale = (float)dataInventory[p][1];
				profit = quantity*(sale - wholesale) + profit;
				
			}
			
			//formating profit to guarantee it has two decimal places
			profit = (float) (Math.round(profit * 100.0) / 100.0);
			
			//query statement to create new entry in finance table
			String out = "INSERT INTO finance (dates, orders, profit)VALUES('"+ date +"'," + rowCount + ","+ profit +");";
			
			stmt.executeUpdate(out);
			 
			c.commit();
			stmt.close();
			c.close();
			
	}catch (Exception e) {
		e.printStackTrace();
		System.exit(1);
	}
    }
}