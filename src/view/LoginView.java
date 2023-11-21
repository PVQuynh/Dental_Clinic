package view;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.SwingConstants;

import controller.LoginController;
import dao.AccountDao;
import dao.RoleDao;
import model.Account;
import model.Role;

public class LoginView extends JFrame {
	private JPasswordField textField_PassWord;
	private JLabel label_Empty5;
	private JLabel label_Empty6;
	private JTextField textField_Email;
	public static Account account;
	public static Role role;

	public LoginView() {
		this.init();
		this.setVisible(true);
	}

	private void init() {
		// Frame
		this.setTitle("Login Page");
		this.setSize(500, 400);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Action
		LoginController loginController = new LoginController(this);

		// Font 
		Font font = new Font("Arial", Font.BOLD, 30);
		Font font20 = new Font("Arial", Font.BOLD, 20);
		
		// Panel login page
		JPanel panel_Login = new JPanel();
		panel_Login.setLayout(new BorderLayout());
		panel_Login.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		JPanel panel_Login_Center = new JPanel();
		panel_Login_Center.setLayout(new GridLayout(5, 2));
		JPanel panel_Login_Button = new JPanel();
		panel_Login_Button.setLayout(new GridLayout(1, 3));

		JLabel label_Login = new JLabel("LOGIN");
		label_Login.setFont(font);
		label_Login.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel label_Empty1 = new JLabel();
		JLabel label_Empty2 = new JLabel();
		JLabel label_Email= new JLabel("Email: ");
		label_Email.setHorizontalAlignment(SwingConstants.CENTER);
		textField_Email = new JTextField();
		JLabel label_Empty3 = new JLabel();
		JLabel label_Empty4 = new JLabel();
		JLabel label_Password = new JLabel("Password: ");
		label_Password.setHorizontalAlignment(SwingConstants.CENTER);
		textField_PassWord = new JPasswordField();
		label_Empty5 = new JLabel();
		label_Empty6 = new JLabel();
		JButton button_Login = new JButton("LOGIN");
		button_Login.addActionListener(loginController);
		button_Login.setFont(font20); 
		JLabel label_Empty7 = new JLabel();
		JButton button_Register = new JButton("REGISTER");
		button_Register.addActionListener(loginController);
		button_Register.setFont(font20); 

		panel_Login_Center.add(label_Empty1);
		panel_Login_Center.add(label_Empty2);
		panel_Login_Center.add(label_Email);
		panel_Login_Center.add(textField_Email);
		panel_Login_Center.add(label_Empty3);
		panel_Login_Center.add(label_Empty4);
		panel_Login_Center.add(label_Password);
		panel_Login_Center.add(textField_PassWord);
		panel_Login_Center.add(label_Empty5);
		panel_Login_Center.add(label_Empty6);
		
		panel_Login_Button.add(button_Login);
		panel_Login_Button.add(label_Empty7);
		panel_Login_Button.add(button_Register);

		panel_Login.add(label_Login, BorderLayout.NORTH);
		panel_Login.add(panel_Login_Center, BorderLayout.CENTER);
		panel_Login.add(panel_Login_Button, BorderLayout.SOUTH);

		// View
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(panel_Login, BorderLayout.CENTER);
	}
	

	public void login() {
		String email = this.textField_Email.getText();
		String password = this.textField_PassWord.getText();
		
		// Account
		Account searchAccount = new Account();
		searchAccount.setEmail(email);
		searchAccount.setPassword(password);
		
		account = AccountDao.getInstance().selectByEmailAndPassword(searchAccount);
		
		// Check role
		if (account != null) {
			// Role
			int roleId = account.getRoleId();
			Role searchRole = new Role();
			searchRole.setRoleId(roleId);
			role = RoleDao.getInstance().selectById(searchRole);
			
			if (account.getRoleId() == 1) { // Manager
				new HomeView();
			} else if (account.getRoleId() == 2) { // Staff
				new HomeView();
			} else if (account.getRoleId() == 3) { // Dentist
				new HomeView();
			} else if (account.getRoleId() == 4) { // Patient
				new HomeView();
			}
			
			this.dispose();
		} else {
			label_Empty5.setText("Email or password is incorrect,");
			label_Empty6.setText("Please re-enter!");
		}
	}
	
//	public static void main(String[] args) {
//		new LoginView();
//	}
}
