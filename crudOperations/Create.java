package crudOperations;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;




public class Create extends CrudOperator {

	/*
	 * Create Class Constructor
	 */
	public Create() {
		
	}

/*
* Creates inventory Item through inputs
*
*/

	public void createInventoryItem(String productID, int quantity, float wholeSale, float sale, String supplierID) {
		
		Connection c = null;
		c = CrudOperator.connect();
	        

	      //Old user input code
	        /*
	        Scanner sc = new Scanner(System.in);
	        System.out.println("Input Product ID: ");
	        String id = sc.next();
	        System.out.println("Input Quantity: ");
	        while (true) {
	            try {
	                quantityNum = sc.nextInt();
	                break;
	            } catch (Exception e) {
	                System.out.print("Invalid input. Please reenter: ");
	                sc.nextLine();
	            }
	        }
	        System.out.println("Input Wholesale Price: ");
	        while (true) {
	            try {
	                wholesale = sc.nextDouble();
	                break;
	            } catch (Exception e) {
	                System.out.print("Invalid input. Please reenter: ");
	                sc.nextLine();
	            }
	        }
	        System.out.println("Input Sales Price: ");
	        while (true) {
	            try {
	                salesPrice = sc.nextDouble();
	                break;
	            } catch (Exception e) {
	                System.out.print("Invalid input. Please reenter: ");
	                sc.nextLine();
	            }
	        }
	        
	        System.out.println("Input Supplier ID: ");
	        String supplierId = sc.next();
			*/
	        try {
	            Statement stmt = null;
	            c.setAutoCommit(false);
	            stmt = c.createStatement();
	            String out = "INSERT INTO Products (product_id,quantity,wholesale_cost, sale_price, supplier_id)"
	                    + "VALUES('" + productID + "',"
	                    + quantity + ","
	                    + wholeSale + "," + sale + ",'" + supplierID + "' );";
	            stmt.executeUpdate(out);

	            stmt.close();
	            c.commit();

	        } catch (Exception e) {
	            e.printStackTrace();
	            System.exit(1);
	        }

	        System.out.println("Records successfully created");
	    }
	
/*
* Creates Customer order through inputs
*/
	
	public void createCustOrder(/*String date, String custEmail, int custLocation, String productId, int productQuantity*/) {
		 
		    Update updater = new Update();
			Connection c = null;
			c = CrudOperator.connect();
		        // User Input for Date, Email, Location, ProdId, Amount purchased, and Date
		        Scanner sc = new Scanner(System.in);
		        System.out.println("Input Date (as mm/dd/yyyy): ");
		        String date = sc.next();

		        System.out.println("Input Customer Email: ");
		        String email = sc.next();

		        System.out.println("Input Customer location: ");
		        String location = sc.next();

		        System.out.println("Input Product Id: ");
		        String productId = sc.next();

		        System.out.println("Input Amount Being Purchased: ");
		        String amount = sc.next();

		        System.out.println("Input Time of Purchase (as hh:mm): ");
		        String time = sc.next();
		        sc.close();

		        // SQL query that will attempt to add the user input into the Customer Orders table
		        try {
		            Statement stmt = null;

		            c.setAutoCommit(false);
		            stmt = c.createStatement();

		            String out = "INSERT INTO cust_orders (date, cust_email, cust_location, product_id, product_quantity)"
		                    + "VALUES('" + date + "','" + email +"'," + Integer.parseInt(location) + ",'" + productId +"'," + Integer.parseInt(amount) + ");";

		            stmt.executeUpdate(out);
		            stmt.close();
		            c.commit();

		            // updateProduct method 
		            updater.updateProductsNewCustOrder(Integer.parseInt(amount), productId);

		        } catch (Exception e) {
		            e.printStackTrace();
		            System.exit(1);
		        }

		        System.out.println("Records successfully created");
		        //System.out.println("Products List Updated");

		    }
	
	/**
	 * Method to create a year to date finance report. Can be used by GUI if we create a window for
	 * the finance table. Uses readFinanceSummary to output total orders and profit per month and
	 * for all the data in the table. 
	 * @return
	 */
	public String createFinanceReport () {
		
		//initializing variables 
		Read reader = new Read() ;
		String startDate = "2022-01-01";
		String endDate = "2022-01-31";
		String january;
		String february;
		String march;
		String april;
		String may;
		String june;
		String yearToDate;
		
		//finds total orders and profit for every month and for the year
		january = reader.readFinanceSummary( startDate, endDate);
		
		startDate = "2022-02-01";
		endDate = "2022-02-28";
		february = reader.readFinanceSummary( startDate, endDate);
		
		startDate = "2022-03-01";
		endDate = "2022-03-31";
		march = reader.readFinanceSummary( startDate, endDate);
		
		startDate = "2022-04-01";
		endDate = "2022-04-30";
		april = reader.readFinanceSummary( startDate, endDate);
		
		startDate = "2022-05-01";
		endDate = "2022-05-31";
		may = reader.readFinanceSummary( startDate, endDate);
		
		startDate = "2022-06-01";
		endDate = "2022-06-30";
		june = reader.readFinanceSummary( startDate, endDate);
		
		startDate = "2022-01-01";
		endDate = "2022-06-30";
		yearToDate = reader.readFinanceSummary( startDate, endDate);
		
		String report = "January : \n\t" + january + "\nFebruary : \n\t" + february +
				"\nMarch : \n\t" + march + "\nApril : \n\t" + april +"\nMay : \n\t" +
				may + "\nJune : \n\t" + june + "\nTotal : \n\t" + yearToDate;
		
		return report;
	}
	
	/**
	 * Method to find total orders and profit for a user specified length of time.
	 * Can be called by GUI if we create a window for the finance table.
	 * @param startDate
	 * @param endDate
	 * @return report
	 */
	public String createUniqueFinanceReport (String startDate, String endDate) {
		
		Read reader = new Read();
		
		String report = reader.readFinanceSummary( startDate, endDate);
		
		return report;
	}
	

}