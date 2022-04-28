package testing;

import static org.junit.jupiter.api.Assertions.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import org.junit.jupiter.api.Test;
import crudOperations.*;

class CreateTest {

	@Test
	void testCreateMethod() {
		System.out.println("------Create Test Running------");
		Create creator = new Create();
		Delete deletor = new Delete();
		Connection c = CrudOperator.connect();
		creator.createInventoryItem("737", 23, 20.0f, 20.0f, "737");
		
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
			
			
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		deletor.delete("737");
	}

}
