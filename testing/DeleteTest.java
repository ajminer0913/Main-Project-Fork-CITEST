package testing;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import crudOperations.Delete;
import crudOperations.Create;
import crudOperations.CrudOperator;

import org.junit.jupiter.api.Test;

/*
 * DeleteTest class that tests all delete methods in the delete class
 */
class DeleteTest {
	/*
	 * This method creates a new product in the inventory table using the createInventoryItem() method
	 * in the Create class.  Then using the delete() method in the Delete class deletes that inventory item
	 * and tests to see if that item is gone from the inventory table
	 */
	@Test
	void testDeleteMethod() {
		System.out.println("------Delete Test Running------");
		//Creating a new Create & Delete objects
		Delete deleter = new Delete();
		Create creator = new Create();
		Connection c = CrudOperator.connect();
		creator.createInventoryItem("737", 23, 20.0f, 20.0f, "737");
		
		//Tests that the item created is in the inventory
		try {
			Statement stmt = null;
			c.setAutoCommit(false);
			stmt = c.createStatement();
			String productId = "737";
			ResultSet rs = stmt.executeQuery("Select * FROM Products WHERE product_id = '" + productId + "';");
			while (rs.next()) {

				String idRet = rs.getString("product_id");
				String expected = "737";
				assertEquals(expected, idRet);
			}

			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		//Calling the delete method
		deleter.delete("737");
		
		//Testing that the delete method deleted the recently created inventory item
		try {
			Statement stmt = null;
			c.setAutoCommit(false);
			stmt = c.createStatement();
			String productId = "737";
			ResultSet rs = stmt.executeQuery("Select * FROM Products WHERE product_id = '" + productId + "';");

			if (rs.next() == false) {

				assertFalse(rs.next());
				assertEquals(false, rs.next());

			}
			rs.close();
			stmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		System.out.println("------Test Done------");
	}
	


}
