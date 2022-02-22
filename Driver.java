import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Driver class for the program
 *
 */

public class Driver {

    public static void main(String[] args) {

        // Creates new instance on start up
        Crud crud = Crud.getInstance();

        Scanner sc = new Scanner(System.in);
        String fileName;
        String id;
        Connection conn = null;
        conn = crud.connect();

        String optionNum = "";

        /**
         * input loop for user to navigate through
         * menu and options and call each specific function
         */
        while (!optionNum.equals("5")) {
            menu();
            System.out.print("Enter option #: ");
            optionNum = sc.next();

            switch (optionNum) {
                case "1":
                    try {
                        crud.create(conn);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

                case "2":
                    System.out.println("Input Product ID: ");
                    id = sc.next();
                    try {
                        crud.read(conn, id);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    break;

                case "3":
                    System.out.print("Enter id to update: ");
                    id = sc.next();
                    try {
                        crud.update(conn, id);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

                case "4":
                    System.out.print("Enter id: ");
                    id = sc.next();
                    try {
                        crud.delete(conn, id);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

                case "5":
                    System.out.println("Goodbye");
                    break;

                default:
                    System.out.println("Invalid option");
                    break;

            }

        }
        try {
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Method to print out menu options for user
     */
    private static void menu() {
        System.out.println("1) Create");
        System.out.println("2) Read");
        System.out.println("3) Update");
        System.out.println("4) Delete");
        System.out.println("5) Quit");
    }
}
