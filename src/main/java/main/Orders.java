package main;

import crudOperations.*;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.border.MatteBorder;

public class Orders extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Orders frame = new Orders();
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
	public Orders() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 622, 402);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setBackground(Color.GRAY);
		btnNewButton_1.setBounds(440, 5, 73, 23);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				MainMenuFrame mainMenu = new MainMenuFrame();
				mainMenu.setVisible(true);
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton = new JButton("Read");
		btnNewButton.setBackground(Color.GRAY);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBounds(523, 5, 73, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// calling read method and storing it into a variable
				OrderSearch results = new OrderSearch();

				results.setVisible(true);
			}
		});
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 33, 591, 319);
		contentPane.add(scrollPane);
		
		//Code for JTable
		Read reader = new Read();
		Object[][] data = reader.readAllOrders();
		
		String column[]={"Date", "Email", "Location", "Product ID", "Quantity"};
		
		table = new JTable(data, column);
		table.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.GRAY));
		scrollPane.setViewportView(table);
		
		Object[] row = new Object [5];
	}

}
