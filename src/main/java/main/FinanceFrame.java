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

public class FinanceFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FinanceFrame frame = new FinanceFrame();
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
	public FinanceFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 590, 426);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		//Read Profits for a single day button
		JButton btnNewButton_3 = new JButton("Read");
		btnNewButton_3.setForeground(Color.WHITE);
		btnNewButton_3.setBackground(Color.GRAY);
		btnNewButton_3.setBounds(10, 5, 151, 23);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Read reader = new Read();
				String input = JOptionPane.showInputDialog(null, "Input Date (Format : Year-Month-Day) : ");
				// calling read method and storing it into a variable
				String message = reader.readFinance(input);

				//outputs 
				JOptionPane.showMessageDialog(null, message);
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnNewButton_3);
		
		//Create Finance Report Unique Period
		JButton btnNewButton_2 = new JButton("Unique Finance Report");
		btnNewButton_2.setForeground(Color.WHITE);
		btnNewButton_2.setBackground(Color.GRAY);
		btnNewButton_2.setBounds(171, 5, 150, 23);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Create creater = new Create();
				String startDate = JOptionPane.showInputDialog(null, "Input Start Date (Format : Year-Month-Day) : ");
				String endDate = JOptionPane.showInputDialog(null, "Input End Date (Format : Year-Month-Day) : ");
				// calling create method and storing it into a variable
				String message = creater.createUniqueFinanceReport(startDate, endDate);

				//outputs 
				JOptionPane.showMessageDialog(null, message);
			}
		});
		contentPane.add(btnNewButton_2);
		
		//Create Finance Report 
		JButton btnNewButton_1 = new JButton("Finance Reoprt");
		btnNewButton_1.setBackground(Color.GRAY);
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setBounds(331, 5, 150, 23);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Create creater = new Create();
				// calling create method and storing it into a variable
				String message = creater.createFinanceReport();

				//outputs 
				JOptionPane.showMessageDialog(null, message);
			}
		});
		contentPane.add(btnNewButton_1);
		//back button
		JButton btnNewButton = new JButton("Back");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(Color.GRAY);
		btnNewButton.setBounds(491, 5, 73, 23);
		btnNewButton.addActionListener(new ActionListener() {
			//back button action
			public void actionPerformed(ActionEvent e) {
				dispose();
				MainMenuFrame menu = new MainMenuFrame();
				menu.setVisible(true);
			}
		});
		contentPane.add(btnNewButton);
				
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(5, 33, 559, 343);
		contentPane.add(scrollPane);
		
		//JTable Code
		Read reader = new Read();
		Object[][] data = reader.readAllFinance();
		
		String column[]={"Date","Orders","Profit"};
		
		table = new JTable(data, column);
		table.setBorder(new MatteBorder(1, 1, 1, 1, (Color) Color.GRAY));
		scrollPane.setViewportView(table);
		
		Object[] row = new Object [5];
	}
}
