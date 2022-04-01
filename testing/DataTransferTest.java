package testing;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.jupiter.api.Test;
import crudOperations.DataTransfer;
import crudOperations.CrudOperator;

public class DataTransferTest {
	/**
	 * This method test that the data transfer object is setting and returning the correct data
	 * and data types
	 */
	@Test
	void testDataTransferObject() {
		System.out.println("-----Test Running-----");
		//Creating data transfer object (POGO)
		DataTransfer pogo = new DataTransfer();
		try {
			Connection c = CrudOperator.connect();
			Statement stmt = null;
			c.setAutoCommit(false);
			stmt = c.createStatement();
			String id = "OFKJP7RB8EW8";
			//SQL Query statement
			ResultSet rs = stmt.executeQuery("Select * FROM Products WHERE product_id = '" + id + "';");
			
			while(rs.next()) {
				//sets values for string test
				String idRet = rs.getString("product_id");
				pogo.setInventoryID(idRet);
				String expected = "OFKJP7RB8EW8";
				String result = pogo.getInventoryID();
				
				//sets values for int test
				int quantRet = rs.getInt("quantity");
				pogo.setInventoryQuantity(quantRet);
				int expected2 = 5087;
				int result2 = pogo.getInventoryQuantity();
				
				float costRet = rs.getFloat("wholesale_cost");
				pogo.setInventorySale(costRet);
				float expected3 = 429.2f;
				float result3 = pogo.getInventorySale();

				assertEquals(expected, result);
				assertEquals(expected2, result2);
				assertEquals(expected3, result3);
				
			}
		}catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		System.out.println("-----Test Done-----");
	}
}
