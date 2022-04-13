package main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import crudOperations.LoginManager;

public class SignUpFrame extends JFrame {

	private JPanel contentPane;
	private JTextField usernameInput;
	private JPasswordField firstPassword;
	private JPasswordField passwordInput;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUpFrame frame = new SignUpFrame();
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
	public SignUpFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//enter a username label
		JLabel lblNewLabel = new JLabel("Enter a Username");
		lblNewLabel.setBounds(168, 11, 159, 14);
		contentPane.add(lblNewLabel);
		
		//enter a password label
		JLabel lblNewLabel_1 = new JLabel("Enter a Password");
		lblNewLabel_1.setBounds(168, 77, 114, 14);
		contentPane.add(lblNewLabel_1);
		
		//Comfirm Password label
		JLabel lblNewLabel_2 = new JLabel("Comfirm Password");
		lblNewLabel_2.setBounds(168, 134, 137, 14);
		contentPane.add(lblNewLabel_2);
		
		//username text field
		usernameInput = new JTextField();
		usernameInput.setBounds(168, 36, 86, 20);
		contentPane.add(usernameInput);
		usernameInput.setColumns(10);
		
		//password text field
		firstPassword = new JPasswordField();
		firstPassword.setBounds(168, 102, 86, 20);
		contentPane.add(firstPassword);
		
		// confrim password text field
		passwordInput = new JPasswordField();
		passwordInput.setBounds(168, 159, 86, 20);
		contentPane.add(passwordInput);
		
		//Sign Up Button
		JButton btnNewButton = new JButton("Sign Up");
		btnNewButton.addActionListener(new ActionListener() {
			//Sign Up Button Action
			public void actionPerformed(ActionEvent e) {
				//creating new LoginManager object
				LoginManager manager = new LoginManager();
				//Storing inputs
				String uInput = usernameInput.getText();
				String pInput = passwordInput.getText();
				String pFInput = firstPassword.getText();
				System.out.println(uInput != "" && pInput != "");
				//checking if the passwords entered match
				if(pFInput.equals(pInput) && (!uInput.equals("") && !pInput.equals("") && !pFInput.equals(""))) {
					//stores username and password into database
					manager.setUsernamePassword(uInput, pInput);
					//switches back to login
					dispose();
					LoginFrame login = new LoginFrame();
					login.setVisible(true);
					JOptionPane.showMessageDialog(null, "Account Created");
				}
				
				//display message if passwords don't match each other
				else {
					JOptionPane.showMessageDialog(null, "Passwords Didn't Match Please Try Again");
				}
				
			}
		});
		btnNewButton.setBounds(262, 210, 89, 23);
		contentPane.add(btnNewButton);
		
		//Cancel Button
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			//Cancel button action
			public void actionPerformed(ActionEvent e) {
				dispose();
				LoginFrame login = new LoginFrame();
				login.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(68, 210, 89, 23);
		contentPane.add(btnNewButton_1);
		
		
		
		
		
		
		
		
		
		
	}
}
