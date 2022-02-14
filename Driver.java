import java.util.*;

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

        int optionNum = 0;

        System.out.print("Enter file name: ");
        fileName = sc.nextLine();
        /**
         * input loop for user to navigate through
         * menu and options and call each specific function
         */
        while (optionNum != 5) {
            menu();
            System.out.print("Enter option #: ");
            optionNum = sc.nextInt();

            switch (optionNum) {
                case 1:
                    try {
                        crud.create(fileName);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

                case 2:
                    try {
                        crud.read(fileName);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    break;

                case 3:
                    System.out.print("Enter id: ");
                    id = sc.next();
                    try {
                        crud.update(id, fileName);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

                case 4:
                    System.out.print("Enter id: ");
                    id = sc.next();
                    try {
                        crud.delete(id, fileName);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
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
