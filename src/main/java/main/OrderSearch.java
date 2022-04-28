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
import javax.swing.JTable;
import java.awt.Insets;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.border.MatteBorder;

public class OrderSearch extends JFrame {

	private JPanel contentPane;
	private JTable table;
	
	
	String custEmail = JOptionPane.showInputDialog(null, "Input Email;");
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderSearch frame = new OrderSearch();
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
	public OrderSearch() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 555, 332);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnNewButton = new JButton("Close");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(Color.GRAY);
		btnNewButton.setBounds(461, 5, 73, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 33, 529, 255);
		contentPane.add(scrollPane);
		
		Read reader = new Read();
		Object[][] data = reader.readSpecOrders(custEmail);
		
		String column[]={"Date", "Email", "Location", "Product ID", "Quantity"};
		
		table = new JTable(data, column);
		table.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.GRAY));
		scrollPane.setViewportView(table);
		
		Object[] row = new Object [5];
	}

}
