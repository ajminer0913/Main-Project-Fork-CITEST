import java.sql.Connection;
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

            Conection c = null;
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
    
}


