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
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import controller.DentistController;
import controller.NavbarController;
import dao.AccountDao;
import dao.AppointmentDao;
import dao.DentistDao;
import dao.PrescriptionDao;
import model.Account;
import model.Appointment;
import model.Dentist;
import model.Prescription;

public class DentistManagementView extends JFrame {

	private JTextField textField_DentistId;
	private JTextField textField_FullName;
	private ButtonGroup buttonGroup_Gender;
	private JTextField textField_DateOfBirth;
	private JTextField textField_Email;
	private JTextField textField_PhoneNumber;
	private JTextField textField_Address;
	private JComboBox<String> comboBox_Status;
	private JTextArea textArea_AppointmentIds;
	private JTextArea textArea_PrescriptionIds;
	private JButton button_Delete;
	private JLabel label_Empty10;
	private JButton button_StaffItem;

	public DentistManagementView() {
		this.init();
		this.setVisible(true);
	}

	private void init() {
		// Frame
		this.setTitle("Dentist management Page");
		this.setSize(1200, 600);
		this.setLocationRelativeTo(null);
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Action
		DentistController DentistController = new DentistController(this);
		NavbarController navbarController = new NavbarController();
		navbarController.setDentistManagementView(this);

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
		String imagePath = "D:/Documents/OOAD/Nhóm_01/src/resource/img/dentist.jpg";
		label_Image.setIcon(new ImageIcon(imagePath));
		label_Image.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel label_Empty1 = new JLabel();
		JLabel label_Empty2 = new JLabel();
		JLabel label_Empty3 = new JLabel();
		JLabel label_Empty4 = new JLabel();
		JLabel label_Empty5 = new JLabel();
		JLabel label_Empty16 = new JLabel();

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
		panel_Info_Form.setLayout(new GridLayout(12, 2));
		JPanel panel_Info_Button = new JPanel();
		panel_Info_Button.setLayout(new GridLayout(2, 5));

		JLabel label_Title = new JLabel("DENTIST INFORMATION");// label_Title
		label_Title.setFont(font30);
		JLabel label_Empty6 = new JLabel();// panel_Info_Form
		JLabel label_Empty7 = new JLabel();
		JLabel label_DentistId = new JLabel("Dentist ID*: ");
		textField_DentistId = new JTextField();
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
		JButton button_Save = new JButton("SAVE");
		button_Save.addActionListener(DentistController);
		JButton button_Edit = new JButton("EDIT");
		button_Edit.addActionListener(DentistController);
		button_Delete = new JButton("DELETE");
		button_Delete.addActionListener(DentistController);
		JButton button_ShowAll = new JButton("SHOW");
		button_ShowAll.addActionListener(DentistController);
		JButton button_ShowConditon = new JButton("RESET");
		button_ShowConditon.addActionListener(DentistController);

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
		panel_Info_Form.add(label_DentistId);
		panel_Info_Form.add(textField_DentistId);
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
		panel_Info_Form.add(label_AppointmentIds);
		panel_Info_Form.add(scrollPane_AppointmentIds);
		panel_Info_Form.add(label_PrescriptionIds);
		panel_Info_Form.add(scrollPane_PrescriptionIds);
		panel_Info_Form.add(label_Empty8);
		panel_Info_Form.add(label_Empty9);

		panel_Info_Button.add(button_Save);
		panel_Info_Button.add(button_Edit);
		panel_Info_Button.add(button_Delete);
		panel_Info_Button.add(button_ShowAll);
		panel_Info_Button.add(button_ShowConditon);
		panel_Info_Button.add(label_Empty10);
		panel_Info_Button.add(label_Empty11);
		panel_Info_Button.add(label_Empty12);
		panel_Info_Button.add(label_Empty13);
		panel_Info_Button.add(label_Empty14);

		panel_Info.add(label_Title, BorderLayout.NORTH);
		panel_Info.add(panel_Info_Form, BorderLayout.CENTER);
		panel_Info.add(panel_Info_Button, BorderLayout.SOUTH);

		// Panel Table
		ArrayList<Dentist> DentistList = DentistDao.getIntance().selectAll();
		Object[][] DentistDataTable = new Object[DentistList.size()][];
		for (int i = 0; i < DentistList.size(); i++) {
			Dentist Dentist = DentistList.get(i);
			Object[] DentistArray = { Dentist.getDentistId(), Dentist.getFullName(), Dentist.getGender(),
					Dentist.getDateOfBirth(), Dentist.getEmail(), Dentist.getPhoneNumber(), Dentist.getAddress(),
					Dentist.getWorkingStatus(), Dentist.getAccountId(), Dentist.getAppointmentIds(),
					Dentist.getPrescriptionIds() };
			DentistDataTable[i] = DentistArray;
		}

		String[] columnName = { "Dentist ID", "Full Name", "Gender", "Date of birth", "Email", "Phone Number",
				"Address", "Working Status", "Account ID", "Appointment IDs", "Prescription IDs" };

		DefaultTableModel tableModel = new DefaultTableModel(DentistDataTable, columnName);
		JTable DentistTable = new JTable(tableModel);

		JScrollPane scrollPane_DentistTable = new JScrollPane(DentistTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

		// Panel navbar
		JPanel panel_Navbar = new JPanel();
		panel_Navbar.setLayout(new GridLayout(8, 1));
		panel_Navbar.setPreferredSize(new Dimension(150, this.getHeight()));

		button_StaffItem = new JButton("Staff");
		button_StaffItem.addActionListener(navbarController);
//				JButton button_DentistItem = new JButton("Dentist");
//				button_DentistItem.addActionListener(navbarController);
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
//				panel_Navbar.add(button_DentistItem);
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
		panel_container.add(scrollPane_DentistTable, constraints_View);

		// View
		this.setLayout(new BorderLayout());
		this.add(panel_Navbar, BorderLayout.WEST);
		this.add(panel_container, BorderLayout.CENTER);
		
	}

	public void saveDentist() {
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

		Dentist dentist = new Dentist();
		dentist.setFullName(fullName);
		dentist.setGender(gender);
		dentist.setDateOfBirth(dateOfBirth);
		dentist.setEmail(email);
		dentist.setPhoneNumber(phoneNumber);
		dentist.setAddress(address);
		dentist.setWorkingStatus(workingStatus);

		DentistDao.getIntance().insert(dentist);

		// Success notification
		this.label_Empty10.setText("Dentist was saved successfully!");
	}

	public void editDentist() {
		int dentistId = Integer.valueOf(this.textField_DentistId.getText());
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

		Dentist dentist = new Dentist();
		dentist.setDentistId(dentistId);
		dentist.setFullName(fullName);
		dentist.setGender(gender);
		dentist.setDateOfBirth(dateOfBirth);
		dentist.setEmail(email);
		dentist.setPhoneNumber(phoneNumber);
		dentist.setAddress(address);
		dentist.setWorkingStatus(workingStatus);

		DentistDao.getIntance().update(dentist);

		// Success notification
		this.label_Empty10.setText("Dentist was edited successfully!");
	}

	public void deleteDentist() {
		int dentistId = Integer.valueOf(this.textField_DentistId.getText());

		// Delete dentist
		Dentist dentistIdObject = new Dentist();
		dentistIdObject.setDentistId(dentistId);
		Dentist searchDentist = DentistDao.getIntance().selectById(dentistIdObject);

		DentistDao.getIntance().delete(searchDentist);

		// Delete account
		Account deleteAccount = new Account();
		deleteAccount.setAccountId(searchDentist.getAccountId());

		AccountDao.getInstance().delete(deleteAccount);

		// Success notification
		this.label_Empty10.setText("Dentist was deleted successfully!");
	}

	public void showDentist() {
		int dentistId = Integer.valueOf(this.textField_DentistId.getText());

		Dentist dentistIdObject = new Dentist();
		dentistIdObject.setDentistId(dentistId);
		Dentist searchDentistId = DentistDao.getIntance().selectById(dentistIdObject);

		this.textField_FullName.setText(searchDentistId.getFullName());

		String gender = searchDentistId.getGender();
		Enumeration<AbstractButton> button_Gender = this.buttonGroup_Gender.getElements();
		while (button_Gender.hasMoreElements()) {
			AbstractButton b = button_Gender.nextElement();
			if (b.getActionCommand().equals(gender)) {
				b.setSelected(true);
			}
		}

		this.textField_DateOfBirth.setText(searchDentistId.getDateOfBirth().toString());
		this.textField_Email.setText(searchDentistId.getEmail());
		this.textField_PhoneNumber.setText(searchDentistId.getPhoneNumber());
		this.textField_Address.setText(searchDentistId.getAddress());
		this.comboBox_Status.setSelectedItem(searchDentistId.getWorkingStatus());

		// select appointmentIds
		Appointment appointment_DentistId = new Appointment();
		appointment_DentistId.setDentistId(dentistId);

		List<Integer> appointmentsShow = AppointmentDao.getInstance()
				.selectAppointmentsIdByDentistId(appointment_DentistId);
		String appointmentsString = arrayListToString(appointmentsShow);
		this.textArea_AppointmentIds.setText(appointmentsString);

		// select prescriptionIds
		Prescription prescription_DentistId = new Prescription();
		prescription_DentistId.setDentistId(dentistId);

		List<Integer> prescriptionsShow = PrescriptionDao.getInstance()
				.selectPrescriptionsIdByDentistId(prescription_DentistId);
		String prescriptionsString = arrayListToString(prescriptionsShow);

		this.textArea_PrescriptionIds.setText(prescriptionsString);

	}

	public void resetDentist() {
		this.textField_DentistId.setText("");
		this.textField_FullName.setText("");
		this.buttonGroup_Gender.clearSelection();
		this.textField_DateOfBirth.setText("");
		this.textField_Email.setText("");
		this.textField_PhoneNumber.setText("");
		this.textField_Address.setText("");
		this.comboBox_Status.setSelectedItem("");
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

	public static void main(String[] args) {
		new DentistManagementView();
	}

}
