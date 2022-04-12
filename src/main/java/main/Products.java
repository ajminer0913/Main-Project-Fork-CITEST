package main;

import crudOperations.*;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import crudOperations.Create;
import crudOperations.DataTransfer;
import crudOperations.Delete;
import crudOperations.Read;
import crudOperations.Update;

import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class Products extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Products frame = new Products();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Products() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 321, 374);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		//Read button
		JButton btnNewButton_3 = new JButton("Read");
		btnNewButton_3.addActionListener(new ActionListener() {
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
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_3.gridx = 1;
		gbc_btnNewButton_3.gridy = 0;
		contentPane.add(btnNewButton_3, gbc_btnNewButton_3);
		
		
		//Create button
		JButton btnNewButton_2 = new JButton("Create");
		btnNewButton_2.addActionListener(new ActionListener() {
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
					JOptionPane.showMessageDialog(null, "Item Has Been Created");
				}
			}
		});
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_2.gridx = 2;
		gbc_btnNewButton_2.gridy = 0;
		contentPane.add(btnNewButton_2, gbc_btnNewButton_2);
		
		
		//Update Button
		JButton btnNewButton_1 = new JButton("Update");
		btnNewButton_1.addActionListener(new ActionListener() {
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
					JOptionPane.showMessageDialog(null, "Item Has Been Updated");
				}
			}
		});
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 3;
		gbc_btnNewButton_1.gridy = 0;
		contentPane.add(btnNewButton_1, gbc_btnNewButton_1);
		
		
		//Delete Button
		JButton btnNewButton = new JButton("Delete");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Delete deleter = new Delete();
				
				String productID = JOptionPane.showInputDialog(null, "Input Product ID");
				
				int selectedOption = JOptionPane.showConfirmDialog(null, 
                        "Do you wanna delete this item in the inventory?", 
                        "Choose", 
                        JOptionPane.YES_NO_OPTION); 
				if (selectedOption == JOptionPane.YES_OPTION) {
					deleter.delete(productID);
					JOptionPane.showMessageDialog(null, "Item Has Been Deleted");

				}
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 4;
		gbc_btnNewButton.gridy = 0;
		contentPane.add(btnNewButton, gbc_btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridwidth = 5;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		contentPane.add(scrollPane, gbc_scrollPane);
		
		
		//JTable Code
		Read reader = new Read();
		Object[][] data = reader.readAll();
		
		String column[]={"Product ID","Quantity","Wholesale Price", "Sale Price", "Supplier ID"};
		
		table = new JTable(data, column);
		scrollPane.setViewportView(table);
		
		Object[] row = new Object [5];
	}

}
