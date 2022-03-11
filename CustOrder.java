import java.sql.*;
import java.io.*;
import java.util.Scanner;


public class CustOrder {
    private static CustOrder firstInstance = null;

    // Constructor for CustOrder
    private CustOrder() {
    }   

    /**
     * Creates new instance of CustOrder and if there is already an instance
     * running, will not do anything
     *
     * @return returns the instance that's running or creates a new one
     */
    public static CustOrder getInstance() {
        if (firstInstance == null) {
            firstInstance = new CustOrder();
        }

        return firstInstance;
    }


    /**
     * will attempt to update the prod_quantity of a product with a matching product_id
     * based on how much of a product is being purchased in an incoming order
     * 
     * Is called in the customerOrder method
     * 
     * @param c
     * @param amountBought
     * @param productId
     */
    public void updateProducts(Connection c, int amountBought, String productId) {

        try {

            //SQL query to try to get the current product quantity and setting it to previousAmount
            Statement stmt = null;
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT quantity FROM Products where product_id = "+productId+";" );
            int previousAmount = rs.getInt("quantity");
            rs.close();
            stmt.close();
            c.close();

            //subtracting current product quantity by order quantity
            int newAmount = previousAmount - amountBought;

            //SQL query to attempt to update the product quantity in the products table 
            stmt = c.createStatement();
            c.setAutoCommit(false);

            String out = ("UPDATE Products set quantity ="+newAmount+" where product_id = "+productId+";");
            stmt.executeUpdate(out);
            stmt.close();
            c.commit();

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

    }


    /**
     * SQL Query that create a new order entry in the customer orders database
     * 
     * @param c Database connection to be used
     */
    public void createOrder(Connection c) {

        //User Input for Date, Email, Location, ProdId, Amount purchased, and Date
       Scanner sc = new Scanner(System.in);
        System.out.println("Input Date (as mm/dd/yyyy): ");
        String date = sc.next();

        System.out.println("Input Customer Email: ");
        String email = sc.next();

        System.out.println("Input Customer location: ");
        String location = sc.next();

        System.out.println("Input Product Id: ");
        String productId = sc.next();

        System.out.println("Input Amount Being Purchased: ");
        String amount = sc.next();

        

        // SQL query that will attempt to add the user input into the Customer Orders table
        try {
            Statement stmt = null;

            c.setAutoCommit(false);
            stmt = c.createStatement();

          String out = "INSERT INTO cust_orders (date, cust_email, cust_location, product_id, product_quantity)"
                    + "VALUES('" + date + "','" + email +"'," + Integer.parseInt(location) + ",'" + productId +"'," + Integer.parseInt(amount) + ");";

            stmt.executeUpdate(out);
            stmt.close();
            c.commit();

            // updateProduct method 
            updateProducts(c, Integer.parseInt(amount), productId);

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        System.out.println("Records successfully created");
        //System.out.println("Products List Updated");

        
    }
}