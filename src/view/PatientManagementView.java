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
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import controller.NavbarController;
import controller.PatientController;
import dao.AccountDao;
import dao.AppointmentDao;
import dao.PatientDao;
import dao.PrescriptionDao;
import dao.PatientDao;
import dao.PatientDao;
import dao.ServiceDao;
import dao.PatientDao;
import model.Account;
import model.Appointment;
import model.Patient;
import model.Prescription;
import model.Patient;
import model.Patient;
import model.Patient;

public class PatientManagementView extends JFrame {
	private JTextField textField_FullName;
	private ButtonGroup buttonGroup_Gender;
	private JTextField textField_DateOfBirth;
	private JTextField textField_PhoneNumber;
	private JTextField textField_PatientId;
	private Patient patient;
	private JTextField textField_Email;
	private JTextField textField_Address;
	private JTextArea textArea_AppointmentIds;
	private JTextArea textArea_PrescriptionIds;
	private JButton button_Delete;
	private JLabel label_Empty10;
	private JButton button_Save;
	private JButton button_Edit;

	public PatientManagementView() {
		this.patient = new Patient();
		this.setVisible(true);
		this.init();
	}

	private void init() {
		// Frame
		this.setTitle("Patient management Page");
		this.setSize(1200, 600);
		this.setLocationRelativeTo(null);
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Action
		PatientController patientController = new PatientController(this);
		NavbarController navbarController = new NavbarController();
		navbarController.setPatientManagementView(this);

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
		String imagePath = "D:/Documents/OOAD/Nhóm_01/src/resource/img/patient.jpg";
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
		panel_Info_Form.setLayout(new GridLayout(11, 2));
		JPanel panel_Info_Button = new JPanel();
		panel_Info_Button.setLayout(new GridLayout(2, 5));

		JLabel label_Title = new JLabel("PATIENT INFORMATION");// label_Title
		label_Title.setFont(font30);
		JLabel label_Empty6 = new JLabel();// panel_Info_Form
		JLabel label_Empty7 = new JLabel();
		JLabel label_PatientId = new JLabel("Patient ID*: ");
		textField_PatientId = new JTextField();
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
		JLabel label_AppointmentIds = new JLabel("Appointments ID: ");
		textArea_AppointmentIds = new JTextArea();
		JScrollPane scrollPane_AppointmentIds = new JScrollPane(textArea_AppointmentIds,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		JLabel label_PrescriptionIds = new JLabel("Prescriptions ID: ");
		textArea_PrescriptionIds = new JTextArea();
		JScrollPane scrollPane_PrescriptionIds = new JScrollPane(textArea_PrescriptionIds,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		JLabel label_Empty8 = new JLabel(); // panel_Info_Button
		JLabel label_Empty9 = new JLabel();
		button_Save = new JButton("SAVE");
		button_Save.addActionListener(patientController);
		button_Edit = new JButton("EDIT");
		button_Edit.addActionListener(patientController);
		button_Delete = new JButton("DELETE");
		button_Delete.addActionListener(patientController);
		JButton button_Show = new JButton("SHOW");
		button_Show.addActionListener(patientController);
		JButton button_Reset = new JButton("RESET");
		button_Reset.addActionListener(patientController);

		// Check login
		if (LoginView.account.getRoleId() == 2 || LoginView.account.getRoleId() == 3
				|| LoginView.account.getRoleId() == 4) {
			this.button_Delete.setEnabled(false);
			
		}
		
		// Check dentist
		if (LoginView.account.getRoleId() == 3) {
			this.button_Save.setEnabled(false);
			this.button_Edit.setEnabled(false);
		}
		
		label_Empty10 = new JLabel();
		JLabel label_Empty11 = new JLabel();
		JLabel label_Empty12 = new JLabel();
		JLabel label_Empty13 = new JLabel();
		JLabel label_Empty14 = new JLabel();

		panel_Info_Form.add(label_Empty6);
		panel_Info_Form.add(label_Empty7);
		panel_Info_Form.add(label_PatientId);
		panel_Info_Form.add(textField_PatientId);
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
		panel_Info_Form.add(label_AppointmentIds);
		panel_Info_Form.add(scrollPane_AppointmentIds);
		panel_Info_Form.add(label_PrescriptionIds);
		panel_Info_Form.add(scrollPane_PrescriptionIds);

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
		ArrayList<Patient> patientList = PatientDao.getIntance().selectAll();

		Object[][] patientDataTable = new Object[patientList.size()][];

		for (int i = 0; i < patientList.size(); i++) {
			Patient patient = patientList.get(i);
			Object[] patientArray = { patient.getPatientId(), patient.getFullName(), patient.getGender(),
					patient.getDateOfBirth(), patient.getEmail(), patient.getPhoneNumber(), patient.getAddress(),
					patient.getAccountId(), patient.getAppointmentIds(), patient.getPrescriptionIds() };
			patientDataTable[i] = patientArray;
		}

		String[] columnName = { "patient ID", "Full Name", "Gender", "Date of birth", "Email", "Phone Number",
				"Address", "accountId", "Appointment IDs", "Prescription IDs" };

		DefaultTableModel tableModel = new DefaultTableModel(patientDataTable, columnName);
		JTable patientTable = new JTable(tableModel);

		JScrollPane scrollPane_PatientTable = new JScrollPane(patientTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		// Panel navbar
		JPanel panel_Navbar = new JPanel();
		panel_Navbar.setLayout(new GridLayout(8, 1));
		panel_Navbar.setPreferredSize(new Dimension(150, this.getHeight()));

		JButton button_StaffItem = new JButton("Staff");
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

		panel_Navbar.add(button_StaffItem);
		panel_Navbar.add(button_DentistItem);
//		panel_Navbar.add(button_PatientItem);
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
		panel_container.add(scrollPane_PatientTable, constraints_View);

		// View
		this.setLayout(new BorderLayout());
		this.add(panel_Navbar, BorderLayout.WEST);
		this.add(panel_container, BorderLayout.CENTER);

	}

	public void savePatient() {
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

		Patient patient = new Patient();
		patient.setFullName(fullName);
		patient.setGender(gender);
		patient.setDateOfBirth(dateOfBirth);
		patient.setEmail(email);
		patient.setPhoneNumber(phoneNumber);
		patient.setAddress(address);

		PatientDao.getIntance().insert(patient);

		// Success notification
		this.label_Empty10.setText("Patient was saved successfully!");

	}

	public void editPatient() {
		int patientId = Integer.valueOf(this.textField_PatientId.getText());
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

		Patient patient = new Patient();
		patient.setPatientId(patientId);
		patient.setFullName(fullName);
		patient.setGender(gender);
		patient.setDateOfBirth(dateOfBirth);
		patient.setEmail(email);
		patient.setPhoneNumber(phoneNumber);
		patient.setAddress(address);

		PatientDao.getIntance().update(patient);

		// Success notification
		this.label_Empty10.setText("Patient was edited successfully!");
	}

	public void deletePatient() {
		int patientId = Integer.valueOf(this.textField_PatientId.getText());

		// Delete patient
		Patient patientIdObject = new Patient();
		patientIdObject.setPatientId(patientId);
		Patient searchPatient = PatientDao.getIntance().selectById(patientIdObject);

		PatientDao.getIntance().delete(searchPatient);

		// Delete account
		Account deleteAccount = new Account();
		deleteAccount.setAccountId(searchPatient.getAccountId());

		AccountDao.getInstance().delete(deleteAccount);

		// Success notification
		this.label_Empty10.setText("Patient was deleted successfully!");
	}

	public void showPatient() {
		int patientId = Integer.valueOf(this.textField_PatientId.getText());

		Patient patientIdObject = new Patient();
		patientIdObject.setPatientId(patientId);
		Patient searchPatientId = PatientDao.getIntance().selectById(patientIdObject);

		this.textField_FullName.setText(searchPatientId.getFullName());

		String gender = patient.getGender();
		Enumeration<AbstractButton> button_Gender = this.buttonGroup_Gender.getElements();
		while (button_Gender.hasMoreElements()) {
			AbstractButton b = button_Gender.nextElement();
			if (b.getActionCommand().equals(gender)) {
				b.setSelected(true);
			}
		}

		this.textField_DateOfBirth.setText(searchPatientId.getDateOfBirth().toString());
		this.textField_Email.setText(searchPatientId.getEmail());
		this.textField_PhoneNumber.setText(searchPatientId.getPhoneNumber());
		this.textField_Address.setText(searchPatientId.getAddress());

		// select appointmentIds
		Appointment appointment_PatientId = new Appointment();
		appointment_PatientId.setPatientId(patientId);

		List<Integer> appointmentsShow = AppointmentDao.getInstance()
				.selectAppointmentsIdByPatientId(appointment_PatientId);
		String appointmentsString = arrayListToString(appointmentsShow);
		this.textArea_AppointmentIds.setText(appointmentsString);

		// select prescriptionIds
		Prescription prescription_PatientId = new Prescription();
		prescription_PatientId.setPatientId(patientId);

		List<Integer> prescriptionsShow = PrescriptionDao.getInstance()
				.selectPrescriptionsIdByPatientId(prescription_PatientId);
		String prescriptionsString = arrayListToString(prescriptionsShow);

		this.textArea_PrescriptionIds.setText(prescriptionsString);

	}

	public void resetPatient() {
		this.textField_PatientId.setText("");
		this.textField_FullName.setText("");
		this.buttonGroup_Gender.clearSelection();
		this.textField_Email.setText("");
		this.textField_DateOfBirth.setText("");
		this.textField_PhoneNumber.setText("");
		this.textField_Address.setText("");
		this.textArea_AppointmentIds.setText("");
		this.textArea_PrescriptionIds.setText("");
	}

	public String arrayListToString(List<Integer> show) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < show.size(); i++) {
			sb.append(show.get(i));
			if (i < show.size() - 1) {
				sb.append(", ");
			}
		}
		return sb.toString();
	}

//	public static void main(String[] args) {
//		new PatientManagementView();
//	}
}
