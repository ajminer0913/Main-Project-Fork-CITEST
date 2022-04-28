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
import java.awt.Color;
import javax.swing.border.MatteBorder;

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
		setBounds(100, 100, 564, 420);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		//Read button
		JButton btnNewButton_3 = new JButton("Read");
		btnNewButton_3.setForeground(Color.WHITE);
		btnNewButton_3.setBackground(Color.GRAY);
		btnNewButton_3.setBounds(118, 5, 76, 23);
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
		contentPane.setLayout(null);
		contentPane.add(btnNewButton_3);
		
		
		//Create button
		JButton btnNewButton_2 = new JButton("Create");
		btnNewButton_2.setForeground(Color.WHITE);
		btnNewButton_2.setBackground(Color.GRAY);
		btnNewButton_2.setBounds(204, 5, 76, 23);
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
		contentPane.add(btnNewButton_2);
		
		
		//Update Button
		JButton btnNewButton_1 = new JButton("Update");
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setBackground(Color.GRAY);
		btnNewButton_1.setBounds(290, 5, 76, 23);
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
		contentPane.add(btnNewButton_1);
		
		
		//Delete Button
		JButton btnNewButton = new JButton("Delete");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(Color.GRAY);
		btnNewButton.setBounds(376, 5, 76, 23);
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
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_4 = new JButton("Back");
		btnNewButton_4.setForeground(Color.WHITE);
		btnNewButton_4.setBackground(Color.GRAY);
		btnNewButton_4.setBounds(462, 5, 76, 23);
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				MainMenuFrame menu = new MainMenuFrame();
				menu.setVisible(true);
			}
		});
		contentPane.add(btnNewButton_4);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 33, 533, 337);
		contentPane.add(scrollPane);
		
		
		//JTable Code
		
		Read reader = new Read();
		Object[][] data = reader.readAll();
		
		String column[]={"Product ID","Quantity","Wholesale Price", "Sale Price", "Supplier ID"};
		
		table = new JTable(data, column);
		table.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.GRAY));
		scrollPane.setViewportView(table);
		
		Object[] row = new Object [5];
		
	}

}
