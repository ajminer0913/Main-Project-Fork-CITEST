package crudOperations;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


/*
 * Delete class to delete an item from the database
*/
public class Delete extends CrudOperator{
		
				

        /**
     * Query to delete selected product
     * 
     * @param id Id to delete
     */
    public void delete(String id) {
        try {

            Connection c = null;
            //Calls the abstract class for SQL connection
            c = CrudOperator.connect();

            Statement stmt = null;

            c.setAutoCommit(false);
            stmt = c.createStatement();

            String out = "DELETE FROM Products WHERE product_id ='" + id + "';";
            stmt.executeUpdate(out);
            stmt.close();
            c.commit();

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

    }
    
    
    /**
     * Query to delete a customer order
     * 
     * @param id
     * @param email
     */
    public void deleteOrder(String id, String email) {
        try {

            Connection c = null;
            //Calls the abstract class for SQL connection
            c = CrudOperator.connect();

            Statement stmt = null;

            c.setAutoCommit(false);
            stmt = c.createStatement();

            String out = "DELETE FROM cust_orders WHERE product_id ='" + id + "' AND cust_email = '" + email + "';";
            stmt.executeUpdate(out);
            stmt.close();
            c.commit();

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}


