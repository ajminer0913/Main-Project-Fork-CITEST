package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import crudOperations.LoginManager;

public class MainMenuFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenuFrame frame = new MainMenuFrame();
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
	public MainMenuFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Welcome Label
		JLabel lblNewLabel = new JLabel("Welcome to Spectacular Barracudas");
		lblNewLabel.setBounds(128, 11, 271, 14);
		contentPane.add(lblNewLabel);
		
		//Inventory Button
		JButton btnNewButton = new JButton("Inventory");
		btnNewButton.addActionListener(new ActionListener() {
			//Inventory Button action
			public void actionPerformed(ActionEvent e) {
				dispose();
				Products inventory = new Products();
				inventory.setVisible(true);
			}
		});
		btnNewButton.setBounds(56, 106, 135, 23);
		contentPane.add(btnNewButton);
		
		//Customer Orders button
		JButton btnNewButton_1 = new JButton("Customer Orders");
		btnNewButton_1.setBounds(237, 106, 135, 23);
		contentPane.add(btnNewButton_1);
		
		//Sign Out Button
		JButton btnNewButton_2 = new JButton("Sign Out");
		btnNewButton_2.addActionListener(new ActionListener() {
			//Sign Out Button Action
			public void actionPerformed(ActionEvent e) {
				//confirms log out
				int selectedOption = JOptionPane.showConfirmDialog(null, 
                        "Are You Sure You Want to Log Out?", 
                        "Choose", 
                        JOptionPane.YES_NO_OPTION); 
				if (selectedOption == JOptionPane.YES_OPTION) {
					dispose();
					LoginFrame login = new LoginFrame();
					login.setVisible(true);
					JOptionPane.showMessageDialog(null, "You've Logged Out of Your Account");
				}
				
			}
		});
		btnNewButton_2.setBounds(10, 7, 89, 23);
		contentPane.add(btnNewButton_2);
		
	}
}
