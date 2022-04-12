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
	private JTextField passwordInput;

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
		
		JLabel lblNewLabel = new JLabel("Enter a Username");
		lblNewLabel.setBounds(174, 11, 159, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Enter a Password");
		lblNewLabel_1.setBounds(168, 77, 114, 14);
		contentPane.add(lblNewLabel_1);
		
		usernameInput = new JTextField();
		usernameInput.setBounds(168, 36, 86, 20);
		contentPane.add(usernameInput);
		usernameInput.setColumns(10);
		
		passwordInput = new JTextField();
		passwordInput.setBounds(168, 124, 86, 20);
		contentPane.add(passwordInput);
		passwordInput.setColumns(10);
		
		JButton btnNewButton = new JButton("Sign Up");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginManager manager = new LoginManager();
				String uInput = usernameInput.getText();
				String pInput = passwordInput.getText();
				
				manager.setUsernamePassword(uInput, pInput);
				dispose();
				LoginFrame login = new LoginFrame();
				login.setVisible(true);
				JOptionPane.showMessageDialog(null, "Account Created");
			}
		});
		btnNewButton.setBounds(257, 170, 89, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				LoginFrame login = new LoginFrame();
				login.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(71, 170, 89, 23);
		contentPane.add(btnNewButton_1);
		
		
		
		
	}
}
