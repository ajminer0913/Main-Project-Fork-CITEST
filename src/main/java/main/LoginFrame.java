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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import crudOperations.LoginManager;
import javax.swing.JPasswordField;
import java.awt.Font;
//import gui.LoginFrame;

public class LoginFrame extends JFrame {

	private JPanel contentPane;
	private JTextField usernameInput;
	private JPasswordField passwordInput;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
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
	public LoginFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		//username label
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setBounds(184, 70, 66, 14);
		contentPane.add(lblNewLabel);
		
		//password label
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(184, 126, 67, 14);
		contentPane.add(lblNewLabel_1);
		
		//Login label
		JLabel lblNewLabel_2 = new JLabel("Login");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setBounds(191, 11, 95, 14);
		contentPane.add(lblNewLabel_2);
		
		//username text field
		usernameInput = new JTextField();
		usernameInput.setBounds(164, 95, 86, 20);
		contentPane.add(usernameInput);
		usernameInput.setColumns(10);
		
		//password text field
		passwordInput = new JPasswordField();
		passwordInput.setBounds(164, 151, 89, 20);
		contentPane.add(passwordInput);
		
		//login button
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			//login action
			public void actionPerformed(ActionEvent e) {
				LoginManager manager = new LoginManager();
				//getting inputs
				String uInput = usernameInput.getText();
				String pInput = passwordInput.getText();
				
				//getting username and password from database
				String storedUsername = manager.getUsername(uInput);
				String storedPassword = manager.getPassword(pInput);
				
				//checking if the inputed username and password matches the ones in the database
				if((uInput.equals(storedUsername) && pInput.equals(storedPassword)) && (storedUsername != "" && storedPassword != "")) {
					dispose();
					MainMenuFrame menu = new MainMenuFrame();
					menu.setVisible(true);
				}
				
				//display incorrect username or password and clear entries
				else {
					dispose();
					LoginFrame login = new LoginFrame();
					login.setVisible(true);
					JOptionPane.showMessageDialog(null, "Incorrect Username or Password");
				}
			}
		});
		btnNewButton.setBounds(277, 199, 89, 23);
		contentPane.add(btnNewButton);
		
		//Sign up button
		JButton btnNewButton_1 = new JButton("Sign Up");
		btnNewButton_1.addActionListener(new ActionListener() {
			//Sign Up action
			public void actionPerformed(ActionEvent e) {
				dispose();
				SignUpFrame signIn = new SignUpFrame();
				signIn.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(64, 199, 89, 23);
		contentPane.add(btnNewButton_1);
		
		
		
		
	}
}
