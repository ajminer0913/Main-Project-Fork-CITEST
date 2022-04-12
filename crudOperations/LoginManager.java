package crudOperations;
import crudOperations.CrudOperator;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginManager {
	
	public LoginManager() {
		
	}
	
	public void setUsernamePassword(String username, String password) {
		Connection c = null;
		c = CrudOperator.connect();
		try {
			Statement stmt = null;
			c.setAutoCommit(false);
			stmt = c.createStatement();
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
	
	public String getUsername(String username) {
		String result = "";
		try {
			
			Connection c = null;
			c = CrudOperator.connect();
			Statement stmt = null;
			c.setAutoCommit(false);
			stmt = c.createStatement();
			
			
			
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
	
	public String getPassword(String password) {
		String result = "";
		try {
			
			Connection c = null;
			c = CrudOperator.connect();
			Statement stmt = null;
			c.setAutoCommit(false);
			stmt = c.createStatement();
			
			
			
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
