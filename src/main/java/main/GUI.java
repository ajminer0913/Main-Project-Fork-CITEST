package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Insets;
import java.awt.Color;
import crudOperations.*;


public class GUI {

	private JFrame frame;

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
	/**
	 * Start of Grid layout code
	 */
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
	
	/**
	 * button for read; no functionality 
	 */
		JButton btnNewButton = new JButton("Read");
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
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 0;
		gbc_btnNewButton.gridy = 0;
		frame.getContentPane().add(btnNewButton, gbc_btnNewButton);
	
	/**
	 * button for create; no functionality
	 */
		JButton btnNewButton_1 = new JButton("Create");
		btnNewButton_1.setBackground(Color.WHITE);
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_1.gridx = 1;
		gbc_btnNewButton_1.gridy = 0;
		frame.getContentPane().add(btnNewButton_1, gbc_btnNewButton_1);
		
	/**
	 * button for Update; no functionality 
	 */
		JButton btnNewButton_2 = new JButton("Update");
		btnNewButton_2.setBackground(Color.WHITE);
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_2.gridx = 2;
		gbc_btnNewButton_2.gridy = 0;
		frame.getContentPane().add(btnNewButton_2, gbc_btnNewButton_2);
	
	/**
	 * Button for Delete; no functionality
	 */
		
		JButton btnNewButton_3 = new JButton("Delete");
		btnNewButton_3.setForeground(Color.RED);
		btnNewButton_3.setBackground(Color.WHITE);
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_3.gridx = 3;
		gbc_btnNewButton_3.gridy = 0;
		frame.getContentPane().add(btnNewButton_3, gbc_btnNewButton_3);
	}

}

