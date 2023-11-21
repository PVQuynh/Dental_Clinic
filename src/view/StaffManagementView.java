package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import controller.NavbarController;
import controller.StaffController;
import dao.AccountDao;
import dao.StaffDao;
import dao.StaffDao;
import model.Account;
import model.Staff;
import model.Staff;

public class StaffManagementView extends JFrame {
	private JTextField textField_StaffId;
	private JTextField textField_FullName;
	private ButtonGroup buttonGroup_Gender;
	private JTextField textField_DateOfBirth;
	private JTextField textField_Email;
	private JTextField textField_PhoneNumber;
	private JTextField textField_Address;
	private JComboBox<String> comboBox_Status;
	private Staff staff;
	private JButton button_Delete;
	private JLabel label_Empty10;
	private JButton button_StaffItem;

	public StaffManagementView() {
		this.setVisible(true);
		this.init();
		this.staff = new Staff();
	}

	private void init() {
		// Frame
		this.setTitle("Staff management Page");
		this.setSize(1200, 600);
		this.setLocationRelativeTo(null);
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Action
		StaffController staffController = new StaffController(this);
		NavbarController navbarController = new NavbarController();
		navbarController.setStaffManagementView(this);

		// Font
		Font font30 = new Font("Arial", Font.BOLD, 30);

		// Panel Image
		JPanel panel_Image = new JPanel();
		panel_Image.setLayout(new BorderLayout());
		JPanel panel_Image_RoleInfo = new JPanel();
		panel_Image_RoleInfo.setLayout(new GridLayout(1, 4));
		panel_Image_RoleInfo.setBackground(Color.yellow);
		panel_Image_RoleInfo.setOpaque(true);
		JPanel panel_Image_Select = new JPanel();
		panel_Image_Select.setLayout(new GridLayout(2, 3));

		JLabel label_Image_EmailTitle = new JLabel("Email: ");
		label_Image_EmailTitle.setHorizontalAlignment(SwingConstants.RIGHT);
		JLabel label_Image_FullNameContent = new JLabel();
		label_Image_FullNameContent.setText(LoginView.account.getEmail());
		label_Image_FullNameContent.setHorizontalAlignment(SwingConstants.LEFT);
		JLabel label_Image_RoleTitle = new JLabel("Role: ");
		label_Image_RoleTitle.setHorizontalAlignment(SwingConstants.RIGHT);
		JLabel label_Image_RoleContent = new JLabel();
		label_Image_RoleContent.setText(LoginView.role.getRole());
		label_Image_RoleContent.setHorizontalAlignment(SwingConstants.LEFT);
		JLabel label_Image = new JLabel();
		String imagePath = "D:/Documents/OOAD/Nhóm_01/src/resource/img/staff.jpg";
		label_Image.setIcon(new ImageIcon(imagePath));
		label_Image.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel label_Empty1 = new JLabel();
		JLabel label_Empty16 = new JLabel();
		JLabel label_Empty2 = new JLabel();
		JLabel label_Empty3 = new JLabel();
		JLabel label_Empty4 = new JLabel();
		JLabel label_Empty5 = new JLabel();

		panel_Image_RoleInfo.add(label_Image_EmailTitle);
		panel_Image_RoleInfo.add(label_Image_FullNameContent);
		panel_Image_RoleInfo.add(label_Image_RoleTitle);
		panel_Image_RoleInfo.add(label_Image_RoleContent);
		panel_Image_Select.add(label_Empty1);
		panel_Image_Select.add(label_Empty16);
		panel_Image_Select.add(label_Empty2);
		panel_Image_Select.add(label_Empty3);
		panel_Image_Select.add(label_Empty4);
		panel_Image_Select.add(label_Empty5);

		panel_Image.add(panel_Image_RoleInfo, BorderLayout.NORTH);
		panel_Image.add(label_Image, BorderLayout.CENTER);
		panel_Image.add(panel_Image_Select, BorderLayout.SOUTH);

		// Panel Info
		JPanel panel_Info = new JPanel();
		panel_Info.setLayout(new BorderLayout());
		JPanel panel_Info_Form = new JPanel();
		panel_Info_Form.setLayout(new GridLayout(10, 2));
		JPanel panel_Info_Button = new JPanel();
		panel_Info_Button.setLayout(new GridLayout(2, 5));

		JLabel label_Title = new JLabel("STAFF INFORMATION");// label_Title
		label_Title.setFont(font30);
		JLabel label_Empty6 = new JLabel();// panel_Info_Form
		JLabel label_Empty7 = new JLabel();
		JLabel label_StaffId = new JLabel("Staff ID*: ");
		textField_StaffId = new JTextField();
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
		JLabel label_PhoneNumber = new JLabel("Phone number: ");
		textField_PhoneNumber = new JTextField();
		JLabel label_Address = new JLabel("Address: ");
		textField_Address = new JTextField();
		JLabel label_Status = new JLabel("Status: ");
		String[] statuses = { "Working", "Resigned" };
		comboBox_Status = new JComboBox<String>(statuses);
		JLabel label_Empty8 = new JLabel(); // panel_Info_Button
		JLabel label_Empty9 = new JLabel();
		JButton button_Save = new JButton("SAVE");
		button_Save.addActionListener(staffController);
		JButton button_Edit = new JButton("EDIT");
		button_Edit.addActionListener(staffController);
		button_Delete = new JButton("DELETE");
		button_Delete.addActionListener(staffController);
		JButton button_Show = new JButton("SHOW");
		button_Show.addActionListener(staffController);
		JButton button_Reset = new JButton("RESET");
		button_Reset.addActionListener(staffController);

		// Check login
		if (LoginView.account.getRoleId() == 2 || LoginView.account.getRoleId() == 3
				|| LoginView.account.getRoleId() == 4) {
			this.button_Delete.setEnabled(false);
		}

		label_Empty10 = new JLabel();
		JLabel label_Empty11 = new JLabel();
		JLabel label_Empty12 = new JLabel();
		JLabel label_Empty13 = new JLabel();
		JLabel label_Empty14 = new JLabel();

		panel_Info_Form.add(label_Empty6);
		panel_Info_Form.add(label_Empty7);
		panel_Info_Form.add(label_StaffId);
		panel_Info_Form.add(textField_StaffId);
		panel_Info_Form.add(label_FullName);
		panel_Info_Form.add(textField_FullName);
		panel_Info_Form.add(label_Gender);
		panel_Info_Form.add(panel_Gender);
		panel_Info_Form.add(label_DateOfBirth);
		panel_Info_Form.add(textField_DateOfBirth);
		panel_Info_Form.add(label_Email);
		panel_Info_Form.add(textField_Email);
		panel_Info_Form.add(label_PhoneNumber);
		panel_Info_Form.add(textField_PhoneNumber);
		panel_Info_Form.add(label_Address);
		panel_Info_Form.add(textField_Address);
		panel_Info_Form.add(label_Status);
		panel_Info_Form.add(comboBox_Status);
		panel_Info_Form.add(label_Empty8);
		panel_Info_Form.add(label_Empty9);

		panel_Info_Button.add(button_Save);
		panel_Info_Button.add(button_Edit);
		panel_Info_Button.add(button_Delete);
		panel_Info_Button.add(button_Show);
		panel_Info_Button.add(button_Reset);
		panel_Info_Button.add(label_Empty10);
		panel_Info_Button.add(label_Empty11);
		panel_Info_Button.add(label_Empty12);
		panel_Info_Button.add(label_Empty13);
		panel_Info_Button.add(label_Empty14);

		panel_Info.add(label_Title, BorderLayout.NORTH);
		panel_Info.add(panel_Info_Form, BorderLayout.CENTER);
		panel_Info.add(panel_Info_Button, BorderLayout.SOUTH);

		// Panel Table
		ArrayList<Staff> staffList = StaffDao.getIntance().selectAll();
		Object[][] staffDataTable = new Object[staffList.size()][];
		for (int i = 0; i < staffList.size(); i++) {
			Staff staff = staffList.get(i);
			Object[] staffArray = { staff.getStaffId(), staff.getFullName(), staff.getGender(), staff.getDateOfBirth(),
					staff.getEmail(), staff.getPhoneNumber(), staff.getAddress(), staff.getWorkingStatus(),
					staff.getAccountId() };
			staffDataTable[i] = staffArray;
		}

		String[] columnName = { "Staff ID", "Full Name", "Gender", "Date of birth", "Email", "Phone Number", "Address",
				"Working Status", "Account ID" };

		DefaultTableModel tableModel = new DefaultTableModel(staffDataTable, columnName);
		JTable staffTable = new JTable(tableModel);

		JScrollPane scrollPane_StaffTable = new JScrollPane(staffTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		// Panel navbar
		JPanel panel_Navbar = new JPanel();
		panel_Navbar.setLayout(new GridLayout(8, 1));
		panel_Navbar.setPreferredSize(new Dimension(150, this.getHeight()));

		button_StaffItem = new JButton("Staff");
		button_StaffItem.addActionListener(navbarController);
		JButton button_DentistItem = new JButton("Dentist");
		button_DentistItem.addActionListener(navbarController);
		JButton button_PatientItem = new JButton("Patient");
		button_PatientItem.addActionListener(navbarController);
		JButton button_MedicineItem = new JButton("Medicine");
		button_MedicineItem.addActionListener(navbarController);
		JButton button_ServiceItem = new JButton("Service");
		button_ServiceItem.addActionListener(navbarController);
		JButton button_AppointmentItem = new JButton("Appointment");
		button_AppointmentItem.addActionListener(navbarController);
		JButton button_PrescriptionItem = new JButton("Prescription");
		button_PrescriptionItem.addActionListener(navbarController);
		JButton button_StatisticalItem = new JButton("Statistical");
		button_StatisticalItem.addActionListener(navbarController);
		JButton button_HomeItem = new JButton("Home");
		button_HomeItem.addActionListener(navbarController);

//				panel_Navbar.add(button_StaffItem);
		panel_Navbar.add(button_DentistItem);
		panel_Navbar.add(button_PatientItem);
		panel_Navbar.add(button_MedicineItem);
		panel_Navbar.add(button_ServiceItem);
		panel_Navbar.add(button_AppointmentItem);
		panel_Navbar.add(button_PrescriptionItem);
		panel_Navbar.add(button_StatisticalItem);
		panel_Navbar.add(button_HomeItem);

		// Panel container
		JPanel panel_container = new JPanel();

		panel_container.setLayout(new GridBagLayout());
		GridBagConstraints constraints_View = new GridBagConstraints();

		constraints_View.gridx = 0;
		constraints_View.gridy = 0; // Row 1
		constraints_View.gridwidth = 1;
		constraints_View.weightx = 1.0; // Divide the space horizontally
		constraints_View.weighty = 1.0; // Divide the space vertically
		constraints_View.fill = GridBagConstraints.BOTH; // make sure to fill the box
		panel_container.add(panel_Image, constraints_View);
		constraints_View.gridx = 1;
		constraints_View.gridy = 0; // Row 1
		constraints_View.gridwidth = 3;
		constraints_View.weightx = 1.0; // Divide the space horizontally
		constraints_View.weighty = 1.0; // Divide the space vertically
		constraints_View.fill = GridBagConstraints.BOTH; // make sure to fill the box
		panel_container.add(panel_Info, constraints_View);
		constraints_View.gridx = 0;
		constraints_View.gridy = 1; // Row 2
		constraints_View.gridwidth = 4;
		constraints_View.weightx = 1.0; // Divide the space horizontally
		constraints_View.weighty = 1.0; // Divide the space vertically
		constraints_View.fill = GridBagConstraints.BOTH; // make sure to fill the box
		panel_container.add(scrollPane_StaffTable, constraints_View);

		// View
		this.setLayout(new BorderLayout());
		this.add(panel_Navbar, BorderLayout.WEST);
		this.add(panel_container, BorderLayout.CENTER);
	}

	public void saveStaff() {
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
		String phoneNumber = this.textField_PhoneNumber.getText();
		String address = this.textField_Address.getText();
		String workingStatus = this.comboBox_Status.getSelectedItem().toString();

		Staff staff = new Staff();
		staff.setFullName(fullName);
		staff.setGender(gender);
		staff.setDateOfBirth(dateOfBirth);
		staff.setEmail(email);
		staff.setPhoneNumber(phoneNumber);
		staff.setAddress(address);
		staff.setWorkingStatus(workingStatus);

		StaffDao.getIntance().insert(staff);

		// Success notification
		this.label_Empty10.setText("Staff was saved successfully!");
	}

	public void editStaff() {
		int staffId = Integer.valueOf(this.textField_StaffId.getText());
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
		String phoneNumber = this.textField_PhoneNumber.getText();
		String address = this.textField_Address.getText();
		String email = this.textField_Email.getText();
		String workingStatus = this.comboBox_Status.getSelectedItem().toString();

		Staff staff = new Staff();
		staff.setStaffId(staffId);
		staff.setFullName(fullName);
		staff.setGender(gender);
		staff.setDateOfBirth(dateOfBirth);
		staff.setEmail(email);
		staff.setPhoneNumber(phoneNumber);
		staff.setAddress(address);
		staff.setWorkingStatus(workingStatus);

		StaffDao.getIntance().update(staff);

		// Success notification
		this.label_Empty10.setText("Staff was edited successfully!");
	}

	public void deleteStaff() {
		int staffId = Integer.valueOf(this.textField_StaffId.getText());

		// Delete dentist
		Staff staffIdObject = new Staff();
		staffIdObject.setStaffId(staffId);
		Staff searchStaff = StaffDao.getIntance().selectById(staffIdObject);

		StaffDao.getIntance().delete(StaffDao.getIntance().selectById(staffIdObject));

		// Delete account
		Account deleteAccount = new Account();
		deleteAccount.setAccountId(searchStaff.getAccountId());

		AccountDao.getInstance().delete(deleteAccount);

		// Success notification
		this.label_Empty10.setText("Staff was deleted successfully!");
	}

	public void showStaff() {
		int staffId = Integer.valueOf(this.textField_StaffId.getText());

		Staff staffIdObject = new Staff();
		staffIdObject.setStaffId(staffId);
		staff = StaffDao.getIntance().selectById(staffIdObject);

		this.textField_FullName.setText(staff.getFullName());

		String gender = staff.getGender();
		Enumeration<AbstractButton> button_Gender = this.buttonGroup_Gender.getElements();
		while (button_Gender.hasMoreElements()) {
			AbstractButton b = button_Gender.nextElement();
			if (b.getActionCommand().equals(gender)) {
				b.setSelected(true);
			}
		}

		this.textField_DateOfBirth.setText(staff.getDateOfBirth().toString());
		this.textField_PhoneNumber.setText(staff.getPhoneNumber());
		this.textField_Address.setText(staff.getAddress());
		this.textField_Email.setText(staff.getEmail());
		this.comboBox_Status.setSelectedItem(staff.getWorkingStatus());
	}

	public void resetStaff() {
		this.textField_StaffId.setText("");
		this.textField_FullName.setText("");
		this.buttonGroup_Gender.clearSelection();
		this.textField_DateOfBirth.setText("");
		this.textField_PhoneNumber.setText("");
		this.textField_Address.setText("");
		this.textField_Email.setText("");
		this.comboBox_Status.setSelectedItem("");
	}

	public static void main(String[] args) {
		new StaffManagementView();
	}
}
