import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;

import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.EventListener;

import javax.annotation.Nonnull;

/**
 * Driver class for the program
 *
 */

public class Driver implements EventListener{



    public static void main(String[] args) throws InterruptedException{

        //Create a new bot
        new Bot();
        // Creates new instance on start up
        Crud crud = Crud.getInstance();
        CustOrder order = CustOrder.getInstance();
        
        //creates CRUD objects
        Create creator = new Create();
        Read reader = new Read();
        Delete deleter = new Delete();
        Update updater = new Update();
        
        //Initializing variables and objects
        Scanner sc = new Scanner(System.in);
        String id;
        String email;
        int quant;
        float wholesale;
        float salesPrice;
        String supplierID;
        String date;
        String custEmail;
        int custLocation;

        Connection connInventory = null;
        Connection connCustomer = null;
        connInventory = crud.connect();
        //connCustomer = order.connect();
        String optionNum = "";

        /**
         * input loop for user to navigate through
         * menu and options and call each specific function
         */

        while (!optionNum.equals("9")) {
            menu();
            System.out.print("Enter option #: ");
            optionNum = sc.next();

            switch (optionNum) {
                case "1":
                    try {
                        crud.create(connInventory);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

                case "2":
                    System.out.println("Input Product ID: ");
                    id = sc.next();
                    try {
                        reader.read(id);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    break;

                  //asks user for updated variables and passes those variables to update method
                    //checks for correct input and asks user to reenter if incorrect
                case "3":
                	System.out.println("Input product ID to change: ");
        	        while (true) {
        	            try {
        	                id = sc.next();
        	                break;
        	            } catch (Exception e) {
        	                System.out.print("Invalid input. Please reenter: ");
        	                sc.nextLine();
        	            }
        	        }
                	System.out.println("Input new quantity: ");
        	        while (true) {
        	            try {
        	                quant = sc.nextInt();
        	                break;
        	            } catch (Exception e) {
        	                System.out.print("Invalid input. Please reenter: ");
        	                sc.nextLine();
        	            }
        	        }
        	        System.out.println("Input new wholesale price: ");
        	        while (true) {
        	            try {
        	                wholesale = sc.nextFloat();
        	                break;
        	            } catch (Exception e) {
        	                System.out.print("Invalid input. Please reenter: ");
        	                sc.nextLine();
        	            }
        	        }
        	        System.out.println("Input new sales price: ");
        	        while (true) {
        	            try {
        	                salesPrice = sc.nextFloat();
        	                break;
        	            } catch (Exception e) {
        	                System.out.print("Invalid input. Please reenter: ");
        	                sc.nextLine();
        	            }
        	        }
        	        System.out.println("Input new supplier ID: ");
        	        while (true) {
        	            try {
        	                supplierID = sc.next();
        	                break;
        	            } catch (Exception e) {
        	                System.out.print("Invalid input. Please reenter: ");
        	                sc.nextLine();
        	            }
        	        }
        	        try {
                        updater.updateInventoryItem(id, quant, wholesale, salesPrice, supplierID);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

                case "4":
                    System.out.print("Enter id: ");
                    id = sc.next();
                    try {
                        deleter.delete(id);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;

                case "5" :
                    try{
                        creator.createCustOrder();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                    
                case "6" :
                	System.out.print("Enter Email: ");
                	id = sc.next();
                	try {
                		reader.readCustOrder(id);
                	} catch (Exception e) {
                		e.printStackTrace();
                	}
                	break;
                	

                	//asks user for updated variables and passes those variables to update method
                    //checks for correct input and asks user to reenter if incorrect
                case "7" :
                	System.out.println("Input product ID to change: ");
        	        while (true) {
        	            try {
        	                id = sc.next();
        	                break;
        	            } catch (Exception e) {
        	                System.out.print("Invalid input. Please reenter: ");
        	                sc.nextLine();
        	            }
        	        }
                	System.out.println("Input new date: ");
        	        while (true) {
        	            try {
        	                date = sc.next();
        	                break;
        	            } catch (Exception e) {
        	                System.out.print("Invalid input. Please reenter: ");
        	                sc.nextLine();
        	            }
        	        }
        	        System.out.println("Input new customer email: ");
        	        while (true) {
        	            try {
        	                custEmail = sc.next();
        	                break;
        	            } catch (Exception e) {
        	                System.out.print("Invalid input. Please reenter: ");
        	                sc.nextLine();
        	            }
        	        }
        	        System.out.println("Input new customer location: ");
        	        while (true) {
        	            try {
        	                custLocation = sc.nextInt();
        	                break;
        	            } catch (Exception e) {
        	                System.out.print("Invalid input. Please reenter: ");
        	                sc.nextLine();
        	            }
        	        }
        	        System.out.println("Input new quantity: ");
        	        while (true) {
        	            try {
        	                quant = sc.nextInt();
        	                break;
        	            } catch (Exception e) {
        	                System.out.print("Invalid input. Please reenter: ");
        	                sc.nextLine();
        	            }
        	        }
        	        try {
                        updater.updateCustomerOrderItem(id, date, custEmail, custLocation, quant);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                	
                case "8" :
                	System.out.print("Enter Id: ");
                	id = sc.next();
                	System.out.print("Enter Email: ");
                	custEmail = sc.next();
                	try {
                		deleter.deleteOrder(id, custEmail);


                	} catch (Exception e) {
                		e.printStackTrace();
                	}
                	break;
                                  
                case "9":
                    System.out.println("Goodbye");
                    break;

                default:
                    System.out.println("Invalid option");
                    break;
            }

        }
        try {
            connInventory.close();
            
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Method to print out menu options for user
     */
    private static void menu() {
        System.out.println("1) Create Inventory Item");
        System.out.println("2) Read Inventory Item");
        System.out.println("3) Update Inventory Item");
        System.out.println("4) Delete Inventory Item");
        System.out.println("5) Create Customer Order");
        System.out.println("6) Read Customer Order");
        System.out.println("7) Update Customer Order");
        System.out.println("8) Delete Customer Order");
        System.out.println("9) Quit");


    }

    public void onEvent(GenericEvent event) {
        if (event instanceof ReadyEvent){
            System.out.println("Bot has connected");
        }
    }
}