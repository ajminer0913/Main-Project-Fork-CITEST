package testing;

import static org.junit.jupiter.api.Assertions.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.jupiter.api.Test;
import crudOperations.*;

class CreateTest {
	/**
	 * A method that uses the createInventoryItem method to create a new product in the inventory table
	 * Then it checks that the product is in the table
	 * Then deletes the product from the table
	 */
	@Test
	void testCreateMethod() {
		System.out.println("------Create Test Running------");
		Create creator = new Create();
		Delete deletor = new Delete();
		Connection c = CrudOperator.connect();
		creator.createInventoryItem("737", 23, 20.0f, 20.0f, "737");
		System.out.println("Creating New Product: Product ID: 737");
		
		try {
			Statement stmt = null;
			c.setAutoCommit(false);
			stmt = c.createStatement();
			String productId = "737";
			ResultSet rs = stmt.executeQuery("Select * FROM Products WHERE product_id = '" + productId + "';");
			while (rs.next()) {
				System.out.println("Testing Create");
				String idRet = rs.getString("product_id");
				String expected = "737";
				assertEquals(expected, idRet);
				System.out.println("Test Successful");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		System.out.println("Deleting Created Product \n");
		deletor.delete("737");
		System.out.println("------Test Done------ \n");
	}

}
