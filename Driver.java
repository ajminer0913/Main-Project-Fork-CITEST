import java.util.*;

/**
 * Driver class for the program
 * 
 */

public class Driver {

    public static void main(String[] args) {

        // Creates new instance on start up
        Crud crud = Crud.getInstance();

        Scanner userIn = new Scanner(System.in);
        String fileName;

        int optionNum = 0;
        String id, supportId;
        int quantity;
        double wholesale, salePrice;

        /**
         * input loop for user to navigate through
         * menu and options
         */
        while (optionNum != 5) {
            menu();
            System.out.print("Enter option #: ");

            optionNum = userIn.nextInt();

            switch (optionNum) {
                case 1:
                    System.out.print("Enter id number: ");
                    id = userIn.next();
                    System.out.print("Enter Quantity: ");
                    quantity = userIn.nextInt();
                    System.out.print("Enter Wholesale Cost: ");
                    wholesale = userIn.nextDouble();
                    System.out.print("Enter Sales Price: ");
                    salePrice = userIn.nextDouble();
                    System.out.print("Enter Support ID: ");
                    supportId = userIn.next();
                    crud.create(); // will update later
                    break;

                case 2:
                    System.out.print("Enter id: ");
                    id = userIn.next();
                    crud.read(); // will update later
                    break;

                case 3:
                    System.out.print("Enter id: ");
                    id = userIn.next();
                    crud.update(); // will update later
                    break;

                case 4:
                    System.out.print("Enter id: ");
                    id = userIn.next();
                    crud.delete();
                    break;

                case 5:
                    System.out.println("Goodbye");
                    break;

                default:
                    System.out.print("Invalid option");
                    break;

            }

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
