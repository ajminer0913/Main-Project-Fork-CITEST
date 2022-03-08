import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

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
	public void read(String id) {
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
                
                System.out.println("");
                System.out.println("ID: " + idRet);
                System.out.println("QUANTITY: " + quantRet);
                System.out.println("WHOLESALE COST " + costRet);
                System.out.println("SALE PRICE: " + saleRet);
                System.out.println("SUPPLIER ID: " + supRet);
                System.out.println("");
			}
			rs.close();
			stmt.close();
		}catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
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
}
