package crudOperations;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/*
 * CrudOperator class contains generic code needed for all CRUD classes
 */
public abstract class CrudOperator {
	/*
	 * CrudOperator constructor
	 */
	public CrudOperator() {
		
	}
	/*
	 * Static connection method that connects to SQL data base and returns the connection
	 */
    public static Connection connect() {
        Connection conn = null;

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://52.91.137.51:5432/products", "postgres", "cS3250!");
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        if (conn != null) {
            System.out.println("Successfully Connected to database");
        } else {
            System.out.println("Connection Failed");
        }

        return conn;
    }
    
    /*
     * Additional methods needed for all CRUD classes
     */
}
