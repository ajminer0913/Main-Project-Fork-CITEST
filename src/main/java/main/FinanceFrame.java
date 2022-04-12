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
		
		//Read Profits for a single day button
		JButton btnNewButton_3 = new JButton("Read");
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
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_3.gridx = 1;
		gbc_btnNewButton_3.gridy = 0;
		contentPane.add(btnNewButton_3, gbc_btnNewButton_3);
		
		//Create Finance Report Unique Period
		JButton btnNewButton_2 = new JButton("Unique Finance Report");
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
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_2.gridx = 2;
		gbc_btnNewButton_2.gridy = 0;
		contentPane.add(btnNewButton_2, gbc_btnNewButton_2);
		
		//Create Finance Report 
		JButton btnNewButton_1 = new JButton("Finance Reoprt");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Create creater = new Create();
				// calling create method and storing it into a variable
				String message = creater.createFinanceReport();

				//outputs 
				JOptionPane.showMessageDialog(null, message);
			}
		});
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_1.gridx = 3;
		gbc_btnNewButton_1.gridy = 0;
		contentPane.add(btnNewButton_1, gbc_btnNewButton_1);
				
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridwidth = 5;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 1;
		contentPane.add(scrollPane, gbc_scrollPane);
		
		//JTable Code
		Read reader = new Read();
		Object[][] data = reader.readAllFinance();
		
		String column[]={"Date","Orders","Profit"};
		
		table = new JTable(data, column);
		scrollPane.setViewportView(table);
		
		Object[] row = new Object [5];
	}
}
