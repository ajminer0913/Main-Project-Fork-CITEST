package testing;
import static org.junit.jupiter.api.Assertions.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.jupiter.api.Test;
import crudOperations.*;
public class UpdateTest {
	/**
	 * A method that creates a new inventory product and then updates it
	 * This method checks to see that the product is contains the updated information
	 * Then deletes the product from the inventory
	 */
	@Test
	public void testUpdateMethod() {
		System.out.println("------Update Test Running------");
		Create creator = new Create();
		Update updator = new Update();
		Delete deletor = new Delete();
		Connection c = CrudOperator.connect();
		System.out.println("Creating new Inventory: ID 737");
		creator.createInventoryItem("737", 23, 20.0f, 20.0f, "737");
		System.out.println("Updating Inventory ID 737");
		updator.updateInventoryItem("737", 27, 73.0f, 37.0f, "373");
		
		try {
			Statement stmt = null;
			stmt = c.createStatement();
			String productId = "3733";
			ResultSet rs = stmt.executeQuery("Select * FROM Products WHERE product_id = '" + productId + "';");
			while(rs.next()) {
				System.out.println("Testing Update");
				int quanRet = rs.getInt("quantity");
				float wholeRet = rs.getFloat("wholesale_cost");
				float saleRet = rs.getFloat("sale_price");
				String supRet = rs.getString("supplier_id");
				
				int expectedQuan = 27;
				float expectedWhole = 73.0f;
				float expectedSale = 37.0f;
				String expectedSup = "373";
				
				assertEquals(expectedQuan, quanRet);
				assertEquals(expectedWhole, wholeRet);
				assertEquals(expectedSale, saleRet);
				assertEquals(expectedSup,supRet);
				System.out.println("Test Successful");
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		System.out.println("Deleting Invnetory ID 737");
		deletor.delete("737");
		System.out.println("------Test Done------ \n");
	}
}
