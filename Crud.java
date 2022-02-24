
/**
 * Program to perform CRUD (Create, Read, Update, Destroy) operations on excel/csv files
 *
 * @authors Spectacular Barracudas
 */

import java.sql.*;
import java.util.Scanner;

public class Crud {

    private static Crud firstInstance = null;

    // Constuctor for crud
    private Crud() {
    }

    /**
     * Creates new instance of Crud and if there is already an instance
     * running, will not do anything
     *
     * @return returns the instance that's running or creates a new one
     */
    public static Crud getInstance() {
        if (firstInstance == null) {
            firstInstance = new Crud();
        }

        return firstInstance;
    }

    /**
     * SQL Query that create a new entry
     * 
     * @param c Database connection to be used
     */
    public void create(Connection c) {

        int quantityNum;
        double wholesale;
        double salesPrice;

        Scanner sc = new Scanner(System.in);
        System.out.println("Input Product ID: ");
        String id = sc.next();
        System.out.println("Input Quantity: ");
        while (true) {
            try {
                quantityNum = sc.nextInt();
                break;
            } catch (Exception e) {
                System.out.print("Invalid input. Please reenter: ");
                sc.nextLine();
            }
        }
        System.out.println("Input Wholesale Price: ");
        while (true) {
            try {
                wholesale = sc.nextDouble();
                break;
            } catch (Exception e) {
                System.out.print("Invalid input. Please reenter: ");
                sc.nextLine();
            }
        }
        System.out.println("Input Sales Price: ");
        while (true) {
            try {
                salesPrice = sc.nextDouble();
                break;
            } catch (Exception e) {
                System.out.print("Invalid input. Please reenter: ");
                sc.nextLine();
            }
        }
        System.out.println("Input Supplier ID: ");
        String supplierId = sc.next();

        try {
            Statement stmt = null;
            c.setAutoCommit(false);
            stmt = c.createStatement();
            String out = "INSERT INTO Products (product_id,quantity,wholesale_cost, sale_price, supplier_id)"
                    + "VALUES('" + id + "',"
                    + quantityNum + ","
                    + wholesale + "," + salesPrice + ",'" + supplierId + "' );";
            stmt.executeUpdate(out);

            stmt.close();
            c.commit();

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        System.out.println("Records successfully created");
    }

    /**
     * SQL Query to read product id
     * 
     * @param c Database connection to be used
     */
    public void read(Connection c, String productId) {

        try {
            Statement stmt = null;
            c.setAutoCommit(false);
            stmt = c.createStatement();

            ResultSet rs = stmt.executeQuery("Select * FROM Products WHERE product_id = '" + productId + "';");

            while (rs.next()) {
                String idRet = rs.getString("product_id");
                int quantRet = rs.getInt("quantity");
                float costRet = rs.getFloat("wholesale_cost");
                float saleRet = rs.getFloat("sale_price");
                String supRet = rs.getString("supplier_id");

                System.out.println("");
                System.out.println("ID: " + idRet);
                System.out.println("QUANTITY: " + quantRet);
                System.out.println("WHOLESALE COST " + costRet);
                System.out.println("SALE PRICE: " + saleRet);
                System.out.println("SUPPLIER ID: " + supRet);
                System.out.println("");
            }

            rs.close();
            stmt.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * SQL Query to update a specified product id
     * 
     * @param c  Database connection to be used
     * @param id Id to look up
     */
    public void update(Connection c, String id) {

        int quantityNum;
        double wholesale;
        double salesPrice;

        Scanner sc = new Scanner(System.in);
        System.out.println("Input new quantity: ");
        while (true) {
            try {
                quantityNum = sc.nextInt();
                break;
            } catch (Exception e) {
                System.out.print("Invalid input. Please reenter: ");
                sc.nextLine();
            }
        }
        System.out.println("Input new wholesale price: ");
        while (true) {
            try {
                wholesale = sc.nextDouble();
                break;
            } catch (Exception e) {
                System.out.print("Invalid input. Please reenter: ");
                sc.nextLine();
            }
        }
        System.out.println("Input new sales price: ");
        while (true) {
            try {
                salesPrice = sc.nextDouble();
                break;
            } catch (Exception e) {
                System.out.print("Invalid input. Please reenter: ");
                sc.nextLine();
            }
        }
        System.out.println("Input new supplier ID: ");
        String supplierId = sc.next();

        try {
            Statement stmt = null;

            c.setAutoCommit(false);
            stmt = c.createStatement();

            String out = "UPDATE products SET quantity =" + quantityNum + ", wholesale_cost = " + wholesale
                    + ", sale_price = " + salesPrice + ", supplier_id = '" + supplierId + "' WHERE product_id = '" + id
                    + "';";

            stmt.executeUpdate(out);
            stmt.close();
            c.commit();

            System.out.println(id + "Successfully updated!");

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Query to delete selected product
     * 
     * @param c  Database connection to be used
     * @param id Id to delete
     */
    public void delete(Connection c, String id) {
        try {
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
            System.exit(1);
        }

        if (conn != null) {
            System.out.println("Successfully Connected to database");
        } else {
            System.out.println("Connection Failed");
        }

        return conn;
    }

}