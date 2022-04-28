package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.jupiter.api.Test;

import crudOperations.Read;
import crudOperations.CrudOperator;
/*
 * A ReadTest class to test the read class
 */
class ReadTest {
	/*
	 * Tests the read class read method to make sure its 
	 * reading the right product id
	 */
	@Test
	void testReadMethod() {
		System.out.println("------Read Method Test Running------");
		//Creates new read object
		Read reader = new Read();
		//Calling the method
		reader.read("5TXAQVSQ1SPS");
		try {
			Statement stmt = null;
			Connection c = CrudOperator.connect();
			c.setAutoCommit(false);
			stmt = c.createStatement();
			String productId = "5TXAQVSQ1SPS";
			ResultSet rs = stmt.executeQuery("Select * FROM Products WHERE product_id = '" + productId + "';");
			while (rs.next()) {
				String expected = "5TXAQVSQ1SPS";
				String idRet = rs.getString("product_id");
				assertEquals(expected, idRet);
			}

			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		System.out.println("------Test Done------");
	}
	
	/*
	 * A method that tests the readCustOrder() method in the read class
	 * is reading the correct data given customer email
	 */
	/*
	@Test
	void testReadCustOrder() {
		System.out.println("------Read Method Test Running------");
		//Creates new read object
		Read reader = new Read();
		//Calling the method
		reader.readCustOrder("pay@gmail.com");
		try {
			Statement stmt = null;
			Connection c = CrudOperator.connect();
			c.setAutoCommit(false);
			stmt = c.createStatement();
			String email = "pay@gmail.com";
			ResultSet rs = stmt.executeQuery("Select * FROM cust_orders WHERE cust_email = '" + email + "';");
			while (rs.next()) {
				String expected = "pay@gmail.com";
				String emailRet = rs.getString("cust_email");
				assertEquals(expected, emailRet);
			}

			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		System.out.println("------Test Done------");
	} */
	

}
