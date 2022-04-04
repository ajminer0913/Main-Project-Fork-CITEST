package main;
import crudOperations.*;

import java.awt.EventQueue;

import javax.swing.JFrame;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Insets;
import java.awt.Color;

import javax.swing.JTable;
import java.awt.Component;
import javax.swing.JScrollPane;



public class GUI {

	private JFrame frame;
	private JTable table;
	private JTable table_1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
		
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{88, 0, 0, 55, 63, 65, 61, 0};
		gridBagLayout.rowHeights = new int[]{21, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
	
		
		/**
		 * button for read; no functionality 
		 */
		JButton btnNewButton = new JButton("Read");
		btnNewButton.setAlignmentY(Component.TOP_ALIGNMENT);
		btnNewButton.addActionListener(new ActionListener() {

			//Read Button
			public void actionPerformed(ActionEvent e) {
				Read reader = new Read();
				String input = JOptionPane.showInputDialog(null, "Input Product ID");
				// calling read method and storing it into a variable
				DataTransfer readResults = reader.read(input);

				//Storing data into variables
				String id = readResults.getInventoryID();
				int quantity = readResults.getInventoryQuantity();
				float cost = readResults.getInventoryCost();
				float sale = readResults.getInventorySale();
				String supID = readResults.getInventorySupID();
				//outputs 
				JOptionPane.showMessageDialog(null, "Product ID: " + id + "\n" + "Quantity: " + quantity + "\n" + "Cost: "+ cost + "\n" + "Sale Price: " + sale + "\n"+ "Supplier ID: " + supID);


			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton.gridx = 3;
		gbc_btnNewButton.gridy = 0;
		frame.getContentPane().add(btnNewButton, gbc_btnNewButton);
		
		
		/**
		 * button for create; no functionality
		 * 
		 */
		JButton btnNewButton_1 = new JButton("Create");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Create creator = new Create();
				
				String productID = JOptionPane.showInputDialog(null, "Input Product ID");
				String quantity = JOptionPane.showInputDialog(null, "Input Quantity");
				String wholeSale = JOptionPane.showInputDialog(null, "Input Wholesale Price ID");
				String sale = JOptionPane.showInputDialog(null, "Input Sales Price ID");
				String supplierID = JOptionPane.showInputDialog(null, "Input Supplier ID");
				
				int selectedOption = JOptionPane.showConfirmDialog(null, 
                        "Do you wanna create a new item in the inventory?", 
                        "Choose", 
                        JOptionPane.YES_NO_OPTION); 
				if (selectedOption == JOptionPane.YES_OPTION) {
					creator.createInventoryItem(productID, Integer.parseInt(quantity), Float.parseFloat(wholeSale), Float.parseFloat(sale), supplierID);
				}
				
			}
		});
		btnNewButton_1.setBackground(Color.WHITE);
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 4;
		gbc_btnNewButton_1.gridy = 0;
		frame.getContentPane().add(btnNewButton_1, gbc_btnNewButton_1);
		
		
		/**
		 * button for update; no functionality
		 * 
		 */
		JButton btnNewButton_2 = new JButton("Update");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Update updater = new Update();
				
				String productID = JOptionPane.showInputDialog(null, "Input Product ID");
				String quantity = JOptionPane.showInputDialog(null, "Input New Quantity");
				String wholeSale = JOptionPane.showInputDialog(null, "Input New Wholesale Price ID");
				String sale = JOptionPane.showInputDialog(null, "Input New Sales Price ID");
				String supplierID = JOptionPane.showInputDialog(null, "Input New Supplier ID");
				
				int selectedOption = JOptionPane.showConfirmDialog(null, 
                        "Do you wanna update this item in the inventory?", 
                        "Choose", 
                        JOptionPane.YES_NO_OPTION); 
				if (selectedOption == JOptionPane.YES_OPTION) {
					updater.updateInventoryItem(productID, Integer.parseInt(quantity), Float.parseFloat(wholeSale), Float.parseFloat(sale), supplierID);
				}
			}
		});
		btnNewButton_2.setBackground(Color.WHITE);
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_2.gridx = 5;
		gbc_btnNewButton_2.gridy = 0;
		frame.getContentPane().add(btnNewButton_2, gbc_btnNewButton_2);
		
		
		/**
		 * button for delete; no functionality
		 * 
		 */
		JButton btnNewButton_3 = new JButton("Delete");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Delete deleter = new Delete();
				
				String productID = JOptionPane.showInputDialog(null, "Input Product ID");
				
				int selectedOption = JOptionPane.showConfirmDialog(null, 
                        "Do you wanna delete this item in the inventory?", 
                        "Choose", 
                        JOptionPane.YES_NO_OPTION); 
				if (selectedOption == JOptionPane.YES_OPTION) {
					deleter.delete(productID);
				}
			}
		});
		btnNewButton_3.setForeground(Color.RED);
		btnNewButton_3.setBackground(Color.WHITE);
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_3.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnNewButton_3.gridx = 6;
		gbc_btnNewButton_3.gridy = 0;
		frame.getContentPane().add(btnNewButton_3, gbc_btnNewButton_3);
		
		//creates read object to fill entries of data
		Read reader = new Read();
		Object[][] data = reader.readAll();
		
		String column[]={"Product ID","Quantity","Wholesale Price", "Sale Price", "Supplier ID"};
		
		JTable table_1 = new JTable( data, column);
		
		Object[] row = new Object [5];
		
		GridBagConstraints gbc_table_1 = new GridBagConstraints();
		gbc_table_1.anchor = GridBagConstraints.NORTH;
		gbc_table_1.insets = new Insets(0, 0, 0, 5);
		gbc_table_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_table_1.gridx = 0;
		gbc_table_1.gridy = 4;
		frame.getContentPane().add(table_1, gbc_table_1);
		
		//This allows the table to scroll
		JScrollPane scrollPane_1 = new JScrollPane(table_1);
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.insets = new Insets(0, 0, 0, 5);
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 0;
		gbc_scrollPane_1.gridy = 4;
		frame.getContentPane().add(scrollPane_1, gbc_scrollPane_1);
		
	}
	
	

}

