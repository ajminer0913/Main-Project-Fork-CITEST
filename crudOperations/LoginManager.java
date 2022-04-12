package crudOperations;
import crudOperations.CrudOperator;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 * 
 * LoginManager Class that stores usernames and passwords into a login data table
 * and retrieves usernames and passwords from login data table
 *
 */
public class LoginManager {
	/**
	 * Constructor
	 */
	public LoginManager() {
		
	}
	/**
	 * Takes in username and password and stores them into login data table
	 * @param username
	 * @param password
	 */
	public void setUsernamePassword(String username, String password) {
		Connection c = null;
		c = CrudOperator.connect();
		try {
			Statement stmt = null;
			c.setAutoCommit(false);
			stmt = c.createStatement();
			
			//SQL query statement to insert username and password into login data table
			String out = "INSERT INTO login (usernames, passwords)"
                    + "VALUES('" + username + "'"
                     + ",'" + password + "');";
			stmt.executeUpdate(out);
			
			stmt.close();
			c.commit();
			
		}catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}

		
	}
	/**
	 * Retrieves username from login data table
	 * @param username wanted
	 * @return username from data table
	 */
	public String getUsername(String username) {
		String result = "";
		try {
			
			Connection c = null;
			c = CrudOperator.connect();
			Statement stmt = null;
			c.setAutoCommit(false);
			stmt = c.createStatement();
			
			
			//SQL query statement to retrieve username from login datatable
			ResultSet rs = stmt.executeQuery("Select * FROM login WHERE usernames = '" + username+ "';");
			
			while (rs.next()) {
				result = rs.getString("usernames");
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		return result;
	}
	
	/**
	 * Returns a password from login data table
	 * @param password wanted
	 * @return password from data table
	 */
	public String getPassword(String password) {
		String result = "";
		try {
			
			Connection c = null;
			c = CrudOperator.connect();
			Statement stmt = null;
			c.setAutoCommit(false);
			stmt = c.createStatement();
			
			
			//SQL query statement to retrieve password from login data table
			ResultSet rs = stmt.executeQuery("Select * FROM login WHERE passwords = '" + password + "';");
			
			while (rs.next()) {
				result = rs.getString("passwords");
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		return result;
	}
}
