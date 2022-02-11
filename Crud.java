
/**
 * Program to perform CRUD (Create, Read, Update, Destroy) operations on excel/csv files
 *
 * @authors Sparkling Barracudas
 */

import java.io.*;
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
    * Method to create new entry on CSV file.
    * Gets user input and adds data to new line.
    * @ThrowsIOException
    *@Param fileName
    */
    public static void create(String fileName) throws IOException{
                 
        Scanner sc = new Scanner(System.in);
	System.out.println("Input Product ID: ");
        String id = sc.nextLine();
        System.out.println("Input Quantity: ");
        int quantity = sc.nextLine();
        System.out.println("Input Wholesale Price: ");
        double wholesale = sc.nextDouble();
        System.out.println("Input Sales Price: ");
        double salesPrice = sc.nextDouble();
        System.out.println("Input Supplier ID: ");
        String supplierId = sc.nextLine();
        
        try{
        
          FileWriter writer = new FileWriter(fileName, true);

            writer.append(id);
            writer.append(",");
            writer.append(quantity);
            writer.append(",");
            writer.append(wholesale);
            writer.append(",");
            writer.append(salesPrice);
            writer.append(",");
            writer.append(supplierId);
            writer.append('\n');
            writer.close();
        
        }
        
        catch(IOException writer){
        
           System.out.println("IOExcpetion, data maybe incorrect");
        
        }
        
        sc.close();
        
   }


    /**
    *Read method takes product Id as input, uses reader objects to 
    *find product Id and print out row
    *@throws FileNotFoundException and IOException
    *@Param: fileName
    */
    public static void read(String fileName) throws FileNotFoundException, IOException {

        Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        int dataFound = 0;
        System.out.println("Input Product ID: ");
        String productId = sc.nextLine();
        String[] row = new String[5];
	String[] row2 = {"Product Id:", "Quantity:", "Wholesale Cost:", "Sale Price:", "Supplier Id:"};
        String line;
    
        while ((line = br.readLine()) != null){
         
            row = line.split(",");
        
            if (row[0].equals(productId)){

                for (int x = 0; x < 5; x += 1){
            
                    System.out.println(row2[x] + " " + row[x] + " ");

                }
            
                dataFound = 1;

            }
        
        }
    
        if (dataFound == 0){
    
            System.out.println("Data Not Found");
    
        }
    
        br.close();

    }

    /**
     * An Update method that updates the quantity of a product given
     * its product ID
     * @param id product ID
     * @param fileName Location of file
     * @throws Exception
     */
	public void update(String id, String fileName)throws Exception {
        Scanner sc = new Scanner(new FileInputStream(fileName));
        String returnLine = null;
        int dataLocation = 0;
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String data[] = line.split(",");
            String current = data[0];
            if (current.equals(id)) {
                String quantity = data[1];
                String wholesale = data[2];
                String salePrice = data[3];
                String supId = data[4];
                
                
                Scanner myObj = new Scanner(System.in);
                System.out.println("Enter New Amount");
                
                String newData = myObj.nextLine();
                quantity = newData;
                returnLine = current + "," + quantity + "," + wholesale + ","+ salePrice + ","+ supId;
                break;
            }
            dataLocation += 1;
        }
        sc.close();
        delete(id, fileName);
        FileWriter csvFile = new FileWriter(fileName, true);
        csvFile.append(returnLine);
        
        
        csvFile.close();
        System.out.println("Product ID: "+ id + " Is Updated");
        	
        }
    /**
     * A method that takes in the filename and product ID and deletes
     * the data at the ID's location
     * @param id of the the product
     * @param fileName location of the file
     * @throws Exception
     */
    public void delete(String id, String fileName)throws Exception {
    	Scanner sc = new Scanner(new FileInputStream(fileName));
		int dataLocation = 0;
		while (sc.hasNextLine()) {
			String line = sc.nextLine();
			String data[] = line.split(",");
			String current = data[0];
			
			if (current.equals(id)) {
				
				Scanner myObj = new Scanner(System.in);
				System.out.println("Press Enter to Comfirm");
				
				String newData = myObj.nextLine();
				System.out.println("Product ID: " + id + " Was Deleted");
				
				break;
			}
			dataLocation += 1;
		}
		sc.close();
		int toEdit = dataLocation;
		File tmp = File.createTempFile("tmp", "");
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		BufferedWriter bw = new BufferedWriter( new FileWriter(tmp));
		
		for(int i = 0; i < toEdit; i++) 
			bw.write(String.format("%s%n" , br.readLine()));
		
		br.readLine();
		
		String l;
		while(null != (l = br.readLine()))
			bw.write(String.format("%s%n", l));
		br.close();
		bw.close();
		
		File oldFile = new File(fileName);
		if(oldFile.delete())
			tmp.renameTo(oldFile);
		

    }

}
