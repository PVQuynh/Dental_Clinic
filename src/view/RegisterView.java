package view;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.sql.Date;
import java.util.Enumeration;
import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.SwingConstants;

import com.mysql.cj.x.protobuf.MysqlxNotice.Frame;

import controller.RegisterController;
import dao.AccountDao;
import dao.PatientDao;
import dao.RoleDao;
import model.Account;
import model.Patient;
import model.Role;

public class RegisterView extends JFrame {
	private JTextField textField_Email;
	private JTextField textField_FullName;
	private ButtonGroup buttonGroup_Gender;
	private JTextField textField_DateOfBirth;
	private JTextField textField_PhoneNumber;
	private JTextField textField_Address;
	private JTextField textField_Password;
	private Patient patient;
	private Account account;
	private JLabel label_Empty3;

	public RegisterView() {
		this.setVisible(true);
		this.init();
	}

	private void init() {
		// Frame
		this.setTitle("Register Page");
		this.setSize(500, 700);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Action
		RegisterController accountController = new RegisterController(this);

		// Font
		Font font = new Font("Arial", Font.BOLD, 30);
		Font font20 = new Font("Arial", Font.BOLD, 20);

		// Panel register page
		JPanel panel_Register = new JPanel();
		panel_Register.setLayout(new BorderLayout());
		panel_Register.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		JPanel panel_Register_Center = new JPanel();
		panel_Register_Center.setLayout(new GridLayout(9, 2));
		JPanel panel_Register_Button = new JPanel();
		panel_Register_Button.setLayout(new GridLayout(1, 3));

		JLabel label_Register = new JLabel("REGISTER (PATIENT)");
		label_Register.setFont(font);
		label_Register.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel label_Empty1 = new JLabel();
		JLabel label_Empty2 = new JLabel();
		JLabel label_FullName = new JLabel("Full name: ");
		textField_FullName = new JTextField();
		JLabel label_Gender = new JLabel("Gender: ");
		JPanel panel_Gender = new JPanel();
		panel_Gender.setLayout(new GridLayout(1, 2));
		JRadioButton radioButton_Male = new JRadioButton("Male");
		JRadioButton radioButton_FeMale = new JRadioButton("Female");
		buttonGroup_Gender = new ButtonGroup();
		buttonGroup_Gender.add(radioButton_Male);
		buttonGroup_Gender.add(radioButton_FeMale);
		panel_Gender.add(radioButton_Male);
		panel_Gender.add(radioButton_FeMale);
		JLabel label_DateOfBirth = new JLabel("Date of birth (yyyy-mm-dd): ");
		textField_DateOfBirth = new JTextField();
		JLabel label_Email = new JLabel("Email*: ");
		textField_Email = new JTextField();
		JLabel label_Password = new JLabel("Password*: ");
		textField_Password = new JTextField();
		JLabel label_PhoneNumber = new JLabel("Phone number: ");
		textField_PhoneNumber = new JTextField();
		JLabel label_Address = new JLabel("Address: ");
		textField_Address = new JTextField();
		label_Empty3 = new JLabel();
		JLabel label_Empty4 = new JLabel();

		JLabel label_Empty5 = new JLabel();
		JButton button_Register = new JButton("CREATE NEW ACCOUNT");
		button_Register.addActionListener(accountController);
		button_Register.setFont(font20);
		JLabel label_Empty6 = new JLabel();

		panel_Register_Center.add(label_Empty1);
		panel_Register_Center.add(label_Empty2);
		panel_Register_Center.add(label_FullName);
		panel_Register_Center.add(textField_FullName);
		panel_Register_Center.add(label_Gender);
		panel_Register_Center.add(panel_Gender);
		panel_Register_Center.add(label_DateOfBirth);
		panel_Register_Center.add(textField_DateOfBirth);
		panel_Register_Center.add(label_Email);
		panel_Register_Center.add(textField_Email);
		panel_Register_Center.add(label_Password);
		panel_Register_Center.add(textField_Password);
		panel_Register_Center.add(label_PhoneNumber);
		panel_Register_Center.add(textField_PhoneNumber);
		panel_Register_Center.add(label_Address);
		panel_Register_Center.add(textField_Address);
		panel_Register_Center.add(label_Empty3);
		panel_Register_Center.add(label_Empty4);

		panel_Register_Button.add(label_Empty5);
		panel_Register_Button.add(button_Register);
		panel_Register_Button.add(label_Empty6);

		panel_Register.add(label_Register, BorderLayout.NORTH);
		panel_Register.add(panel_Register_Center, BorderLayout.CENTER);
		panel_Register.add(panel_Register_Button, BorderLayout.SOUTH);

		// View
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(panel_Register, BorderLayout.CENTER);
	}

	public void register() {
		String fullName = this.textField_FullName.getText();
		String gender = "";

		Enumeration<AbstractButton> button_Gender = this.buttonGroup_Gender.getElements();
		while (button_Gender.hasMoreElements()) {
			AbstractButton b = button_Gender.nextElement();
			if (b.isSelected()) {
				gender = b.getText();
			}
		}

		Date dateOfBirth = Date.valueOf(this.textField_DateOfBirth.getText());
		String email = this.textField_Email.getText();
		String password = this.textField_Password.getText();
		String phoneNumber = this.textField_PhoneNumber.getText();
		String address = this.textField_Address.getText();

		// Creat Account
		Account searchAccount = new Account();
		searchAccount.setEmail(email);
		searchAccount.setPassword(password);
		searchAccount.setRoleId(3);
		AccountDao.getInstance().insert(searchAccount);

		account = AccountDao.getInstance().selectByEmailAndPassword(searchAccount);

		// Create Patient
		Patient searchPatient = new Patient();
		searchPatient.setFullName(fullName);
		searchPatient.setGender(gender);
		searchPatient.setDateOfBirth(dateOfBirth);
		searchPatient.setEmail(email);
		searchPatient.setPhoneNumber(phoneNumber);
		searchPatient.setAddress(address);
		searchPatient.setAccountId(account.getAccountId());

		PatientDao.getIntance().insert(searchPatient);

		// Success notification
		this.label_Empty3.setText("Successful account registration!");
	}

//	public static void main(String[] args) {
//		new RegisterView();
//	}
}
