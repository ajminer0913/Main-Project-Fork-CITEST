package botCode;

import java.sql.*;
import java.io.*;
import java.util.Scanner;


public class Botoperations {
    private static Botoperations firstInstance = null;

    // Constuctor for CustOrder
    private Botoperations() {
    }

    /**
     * Creates new instance of CustOrder and if there is already an instance
     * running, will not do anything
     *
     * @return returns the instance that's running or creates a new one
     */
    public static Botoperations getInstance() {
        if (firstInstance == null) {
            firstInstance = new Botoperations();
        }

        return firstInstance;
    }


    /**
     * will atempt to update the prod_quantity of a product with a matching product_id
     * based on how much of a product is being purchased in an oncoming order
     *
     * Is called in the customerOrder method
     *
     * @param c
     * @param amountBought
     * @param productId
     */
    public void updateProducts(Connection c, int amountBought, String productId) {

        try {

            //sql querry to try to get the current product quantity and setting it to previousAmount
            Statement stmt = null;
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery( "SELECT quantity FROM Products where product_id = "+productId+";" );
            int previousAmount = rs.getInt("quantity");
            rs.close();
            stmt.close();
            c.close();

            //subtracting current product quantity by order quantity
            int newAmount = previousAmount - amountBought;

            //sql querry to attempt to update the product quantity in the products table
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
     * Method to connect to remote database server
     *
     * @return returns the connection if established
     */
    public Connection connect() {
        Connection conn = null;

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection("jdbc:postgresql://52.91.137.51:5432/products", "postgres", "cS3250!");
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (conn != null) {
            System.out.println("Successfully Connected to database");
        } else {
            System.out.println("Connection Failed");
        }

        return conn;
    }
}
