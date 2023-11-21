package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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

import controller.AppointmentController;
import controller.NavbarController;
import dao.AppointmentDao;
import dao.DentistDao;
import dao.AppointmentDao;
import dao.ServiceDao;
import dao.StaffDao;
import model.Service;
import model.Staff;
import model.Appointment;
import model.Dentist;
import model.Appointment;

public class AppointmentManagementView extends JFrame {
	
	private JTextField textField_AppointmentId;
	private JTextField textField_AppointmentNote;
	private JTextField textField_AppointmentStatus;
	private Appointment appointment;
	private JTextField textField_ServiceId;
	private JTextField textField_StartTime;
	private JTextField textField_EndTime;
	private JTextField textField_PatientId;
	private JButton button_Delete;
	private JLabel label_Empty10;
	private JButton button_SaveAppointment;
	private JButton button_SaveAppSer;
	private JButton button_Edit;

	public AppointmentManagementView() {
		this.appointment = new Appointment();
		this.init();
		this.setVisible(true);
	}

	private void init() {
		// Frame
		this.setTitle("Appointment management Page");
		this.setSize(1200, 600);
		this.setLocationRelativeTo(null);
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Action
		AppointmentController appointmentController = new AppointmentController(this);
		
		NavbarController navbarController = new NavbarController();
		navbarController.setAppointmentManagementView(this);

		// Font
		Font font30 = new Font("Arial", Font.BOLD, 30);

		// Panel title
		JPanel panel_Title = new JPanel();
		panel_Title.setLayout(new GridLayout(1, 2));
		JPanel panel_RoleInfo = new JPanel();
		panel_RoleInfo.setLayout(new GridLayout(1, 4));
		panel_RoleInfo.setBackground(Color.yellow);

		JLabel label_Title = new JLabel("APPOINTMENT INFORMATION");// label_Title
		label_Title.setHorizontalAlignment(SwingConstants.CENTER);
		label_Title.setFont(font30);

		JLabel label_EmailTitle = new JLabel("Email: ");
		label_EmailTitle.setHorizontalAlignment(SwingConstants.RIGHT);
		JLabel label_FullNameContent = new JLabel();
		label_FullNameContent.setText(LoginView.account.getEmail());
		label_FullNameContent.setHorizontalAlignment(SwingConstants.LEFT);
		JLabel label_RoleTitle = new JLabel("Role: ");
		label_RoleTitle.setHorizontalAlignment(SwingConstants.RIGHT);
		JLabel label_RoleContent = new JLabel();
		label_RoleContent.setText(LoginView.role.getRole());
		label_RoleContent.setHorizontalAlignment(SwingConstants.LEFT);

		JLabel label_Empty1 = new JLabel();

		panel_RoleInfo.add(label_EmailTitle);
		panel_RoleInfo.add(label_FullNameContent);
		panel_RoleInfo.add(label_RoleTitle);
		panel_RoleInfo.add(label_RoleContent);

		panel_Title.add(panel_RoleInfo);
		panel_Title.add(label_Title);

		// Panel Info
		JPanel panel_Info = new JPanel();
		panel_Info.setLayout(new BorderLayout());
		JPanel panel_Info_Form = new JPanel();
		panel_Info_Form.setLayout(new GridLayout(9, 2));
		JPanel panel_Info_Button = new JPanel();
		panel_Info_Button.setLayout(new GridLayout(2, 6));

		JLabel label_Empty6 = new JLabel();// panel_Info_Form
		JLabel label_Empty7 = new JLabel();
		JLabel label_AppointmentId = new JLabel("Appointment ID*: ");
		textField_AppointmentId = new JTextField();
		JLabel label_PatientId = new JLabel("Patient ID: ");
		textField_PatientId = new JTextField();
		JLabel label_ServiceId = new JLabel("Service ID: ");
		textField_ServiceId = new JTextField();
		JLabel label_AppointmentNote = new JLabel("Appointment Note: ");
		textField_AppointmentNote = new JTextField();
		JLabel label_AppointmentStatus = new JLabel("Appointment Status: ");
		textField_AppointmentStatus = new JTextField();
		JLabel label_StartTime = new JLabel("Start Time (yyyy-mm-dd): ");
		textField_StartTime = new JTextField();
		JLabel label_EndTime = new JLabel("End Time (yyyy-mm-dd): ");
		textField_EndTime = new JTextField();
		JLabel label_Empty8 = new JLabel(); // panel_Info_Button
		JLabel label_Empty9 = new JLabel();
		button_SaveAppointment = new JButton("SAVE APPOINTMENT");
		button_SaveAppointment.addActionListener(appointmentController);
		button_SaveAppSer = new JButton("ADD APP-SER");
		button_SaveAppSer.addActionListener(appointmentController);
		button_Edit = new JButton("EDIT");
		button_Edit.addActionListener(appointmentController);
		button_Delete = new JButton("DELETE");
		button_Delete.addActionListener(appointmentController);
		JButton button_Show = new JButton("SHOW");
		button_Show.addActionListener(appointmentController);
		JButton button_Reset = new JButton("RESET");
		button_Reset.addActionListener(appointmentController);

		// Check login
		if (LoginView.account.getRoleId() == 2 || LoginView.account.getRoleId() == 3
				|| LoginView.account.getRoleId() == 4) {
			this.button_Delete.setEnabled(false);
		}
		
		if (LoginView.account.getRoleId() == 3) {
			this.button_SaveAppointment.setEnabled(false);
			this.button_SaveAppSer.setEnabled(false);
			this.button_Edit.setEnabled(false);
			this.button_Delete.setEnabled(false);
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
		panel_Info_Form.add(label_AppointmentId);
		panel_Info_Form.add(label_ServiceId);
		panel_Info_Form.add(textField_AppointmentId);
		panel_Info_Form.add(textField_ServiceId);
		panel_Info_Form.add(label_AppointmentNote);
		panel_Info_Form.add(textField_AppointmentNote);
		panel_Info_Form.add(label_AppointmentStatus);
		panel_Info_Form.add(textField_AppointmentStatus);
		panel_Info_Form.add(label_StartTime);
		panel_Info_Form.add(textField_StartTime);
		panel_Info_Form.add(label_EndTime);
		panel_Info_Form.add(textField_EndTime);
		panel_Info_Form.add(label_Empty8);
		panel_Info_Form.add(label_Empty9);

		panel_Info_Button.add(button_SaveAppointment);
		panel_Info_Button.add(button_SaveAppSer);
		panel_Info_Button.add(button_Edit);
		panel_Info_Button.add(button_Delete);
		panel_Info_Button.add(button_Show);
		panel_Info_Button.add(button_Reset);
		panel_Info_Button.add(label_Empty10);
		panel_Info_Button.add(label_Empty11);
		panel_Info_Button.add(label_Empty12);
		panel_Info_Button.add(label_Empty13);
		panel_Info_Button.add(label_Empty14);

		panel_Info.add(panel_Title, BorderLayout.NORTH);
		panel_Info.add(panel_Info_Form, BorderLayout.CENTER);
		panel_Info.add(panel_Info_Button, BorderLayout.SOUTH);

		// Panel Table
		ArrayList<Appointment> appointmentList = AppointmentDao.getInstance().selectAll();

		Object[][] appointmentDataTable = new Object[appointmentList.size()][];
		for (int i = 0; i < appointmentList.size(); i++) {
			Appointment appointment = appointmentList.get(i);
			Object[] AppointmentArray = { appointment.getAppointmentId(), appointment.getAppointmentNote(),
					appointment.getAppointmentStatus(), appointment.getStartTime(), appointment.getEndTime(),
					appointment.getStaffId(), appointment.getDentistId(), appointment.getPatientId(),
					appointment.getServices(), appointment.getAppointmentCreationTime() };
			appointmentDataTable[i] = AppointmentArray;
		}

		String[] columnName = { "Appointment ID", "Appointment Note", "Appointment Status", "Start Time", "End Time",
				"Staff ID", "Dentist ID", "Patient ID", "Service ID", "Appointment Creation Time" };

		DefaultTableModel tableModel = new DefaultTableModel(appointmentDataTable, columnName);
		JTable AppointmentTable = new JTable(tableModel);

		JScrollPane scrollPane_AppointmentTable = new JScrollPane(AppointmentTable,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		
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
//		JButton button_AppointmentItem = new JButton("Appointment");
//		button_AppointmentItem.addActionListener(navbarController);
		JButton button_PrescriptionItem = new JButton("Prescription");
		button_PrescriptionItem.addActionListener(navbarController);
		JButton button_StatisticalItem = new JButton("Statistical");
		button_StatisticalItem.addActionListener(navbarController);
		JButton button_HomeItem = new JButton("Home");
		button_HomeItem.addActionListener(navbarController);
		
		panel_Navbar.add(button_StaffItem);
		panel_Navbar.add(button_DentistItem);
		panel_Navbar.add(button_PatientItem);
		panel_Navbar.add(button_MedicineItem);
		panel_Navbar.add(button_ServiceItem);
//		panel_Navbar.add(button_AppointmentItem);
		panel_Navbar.add(button_PrescriptionItem);
		panel_Navbar.add(button_StatisticalItem);
		panel_Navbar.add(button_HomeItem);
		
		
		// Panel container
		JPanel panel_container = new JPanel();
		panel_container.setLayout(new GridBagLayout());
		GridBagConstraints main_Constraints_View = new GridBagConstraints();

		main_Constraints_View.gridx = 0;
		main_Constraints_View.gridy = 0; // Row 1
		main_Constraints_View.gridwidth = 3;
		main_Constraints_View.gridheight = 2;
		main_Constraints_View.weightx = 1.0; // Divide the space horizontally
		main_Constraints_View.weighty = 1.0; // Divide the space vertically
		main_Constraints_View.fill = GridBagConstraints.BOTH; // make sure to fill the box
		panel_container.add(panel_Info, main_Constraints_View);
		main_Constraints_View.gridx = 0;
		main_Constraints_View.gridy = 2; // Row 3
		main_Constraints_View.gridwidth = 3;
		main_Constraints_View.gridheight = 1;
		main_Constraints_View.weightx = 1.0; // Divide the space horizontally
		main_Constraints_View.weighty = 1.0; // Divide the space vertically
		main_Constraints_View.fill = GridBagConstraints.BOTH; // make sure to fill the box
		panel_container.add(scrollPane_AppointmentTable, main_Constraints_View);
		
		
		// View
		this.setLayout(new BorderLayout());
		this.add(panel_Navbar, BorderLayout.WEST);
		this.add(panel_container, BorderLayout.CENTER);
	}

	public void saveAppointment() {
		int patientId = Integer.parseInt(this.textField_PatientId.getText());
		String appointmentNote = this.textField_AppointmentNote.getText();
		String appointmentStatus = this.textField_AppointmentStatus.getText();
		Date startTime = Date.valueOf(textField_StartTime.getText());
		String textFieldEndTime = textField_EndTime.getText();
		Date endTime = null;

		if (!textFieldEndTime.isEmpty()) {
			try {
				endTime = Date.valueOf(textFieldEndTime);
				// Thực hiện xử lý khác nếu cần
			} catch (IllegalArgumentException e) {
				// Xử lý lỗi khi định dạng ngày không hợp lệ
				endTime = null;
			}
		} else {
			// Xử lý khi textField_EndTime không có giá trị
		}

		int staffId = 0;
		int dentistId = 0;

		// From account find out objects
		int accountId = LoginView.account.getAccountId();
		int roleId = LoginView.account.getRoleId();

		if (roleId == 2) {
			Staff staff_AccountIdObject = new Staff();
			staff_AccountIdObject.setAccountId(accountId);
			Staff searchStaff_AccountId = StaffDao.getIntance().selectByAccountId(staff_AccountIdObject);

			staffId = searchStaff_AccountId.getStaffId();
		} else if (roleId == 3) {
			Dentist dentist_AccountIdObject = new Dentist();
			dentist_AccountIdObject.setAccountId(accountId);
			Dentist searchDentist_AccountId = DentistDao.getIntance().selectByAccountId(dentist_AccountIdObject);

			dentistId = searchDentist_AccountId.getDentistId();
		}

		Appointment appointmentSave = new Appointment();
		appointmentSave.setPatientId(patientId);
		appointmentSave.setAppointmentNote(appointmentNote);
		appointmentSave.setAppointmentStatus(appointmentStatus);
		appointmentSave.setStartTime(startTime);
		appointmentSave.setEndTime(endTime);
		appointmentSave.setStaffId(staffId);
		appointmentSave.setDentistId(dentistId);

		AppointmentDao.getInstance().insert(appointmentSave);

		// Success notification
		this.label_Empty10.setText("Appointment was saved successfully!");
	}

	public void saveAppSer() {
		int appointmentId = Integer.parseInt(this.textField_AppointmentId.getText());
		String serviceString = this.textField_ServiceId.getText();

		// Get services
		List<Integer> services = new ArrayList<>();
		String[] serviceArray = serviceString.split(",");
		for (String service : serviceArray) {
			int serviceId = Integer.parseInt(service.trim());

			services.add(serviceId);
		}

		// Create object Appointment and insert
		Appointment appointment = new Appointment();
		appointment.setAppointmentId(appointmentId);
		appointment.setServices(services);

		AppointmentDao.getInstance().insertAppSer(appointment);

		// Success notification
		this.label_Empty10.setText("App-Ser was saved successfully!");
	}

	public void editAppointment() {
		int appointmentId = Integer.parseInt(this.textField_AppointmentId.getText());
		String appointmentNote = this.textField_AppointmentNote.getText();
		String appointmentStatus = this.textField_AppointmentStatus.getText();
		Date startTime = Date.valueOf(textField_StartTime.getText());
		Date endTime = Date.valueOf(textField_EndTime.getText());
		
		int staffId = 0;
		int dentistId = 0;
		String serviceString = this.textField_ServiceId.getText();

		// From account find out objects
		int accountId = LoginView.account.getAccountId();
		int roleId = LoginView.account.getRoleId();

		if (roleId == 2) {
			Staff staff_AccountIdObject = new Staff();
			staff_AccountIdObject.setAccountId(accountId);
			Staff searchStaff_AccountId = StaffDao.getIntance().selectByAccountId(staff_AccountIdObject);

			staffId = searchStaff_AccountId.getStaffId();
		} else if (roleId == 3) {
			Dentist dentist_AccountIdObject = new Dentist();
			dentist_AccountIdObject.setAccountId(accountId);
			Dentist searchDentist_AccountId = DentistDao.getIntance().selectByAccountId(dentist_AccountIdObject);

			dentistId = searchDentist_AccountId.getDentistId();
		}

		// Get services
		List<Integer> services = new ArrayList<>();
		String[] serviceArray = serviceString.split(",");
		for (String service : serviceArray) {
			int serviceId = Integer.parseInt(service.trim());

			services.add(serviceId);
		}

		// update
		Appointment appointmentUpdate = new Appointment();
		appointmentUpdate.setAppointmentId(appointmentId);
		appointmentUpdate.setAppointmentNote(appointmentNote);
		appointmentUpdate.setAppointmentStatus(appointmentStatus);
		appointmentUpdate.setStartTime(startTime);
		appointmentUpdate.setEndTime(endTime);
		appointmentUpdate.setStaffId(staffId);
		appointmentUpdate.setDentistId(dentistId);
		appointmentUpdate.setServices(services);

		AppointmentDao.getInstance().update(appointmentUpdate);

		// Success notification
		this.label_Empty10.setText("Appointment was edited successfully!");
	}

	public void deleteAppointment() {
		int appointmentId = Integer.valueOf(this.textField_AppointmentId.getText());

		Appointment appointmentIdObject = new Appointment();
		appointmentIdObject.setAppointmentId(appointmentId);
		AppointmentDao.getInstance().delete(AppointmentDao.getInstance().selectById(appointmentIdObject));

		// Success notification
		this.label_Empty10.setText("Appointment was deleted successfully!");
	}

	public void showAppointment() {
		int appointmentId = Integer.valueOf(this.textField_AppointmentId.getText());

		Appointment appointmentIdObject = new Appointment();
		appointmentIdObject.setAppointmentId(appointmentId);
		appointment = AppointmentDao.getInstance().selectById(appointmentIdObject);

		// show
		this.textField_PatientId.setText(appointment.getPatientId() + "");
		this.textField_AppointmentId.setText(appointment.getAppointmentId() + "");

		List<Integer> servicesShow = appointment.getServices();
		String servicesString = arrayListToString(servicesShow);
		this.textField_ServiceId.setText(servicesString);

		this.textField_PatientId.setText(appointment.getPatientId()+"");
		this.textField_AppointmentNote.setText(appointment.getAppointmentNote());
		this.textField_AppointmentStatus.setText(appointment.getAppointmentStatus());
		this.textField_StartTime.setText(appointment.getStartTime() + "");
		this.textField_EndTime.setText(appointment.getEndTime() + "");
		
		// Add dentist
		int dentistId = 0;

			// From account find out objects
		int accountId = LoginView.account.getAccountId();
		int roleId = LoginView.account.getRoleId();
		
		if (roleId == 3) {
			Dentist dentist_AccountIdObject = new Dentist();
			dentist_AccountIdObject.setAccountId(accountId);
			Dentist searchDentist_AccountId = DentistDao.getIntance().selectByAccountId(dentist_AccountIdObject);

			dentistId = searchDentist_AccountId.getDentistId();
			
			// Insert or update dentist
			Appointment appointmentUpdate = new Appointment();
			appointmentUpdate.setAppointmentId(appointmentId);
			appointmentUpdate.setDentistId(dentistId);
			
			AppointmentDao.getInstance().updateDentist(appointmentUpdate);
		}
		
	}

	public void resetAppointment() {
		this.textField_PatientId.setText("");
		this.textField_AppointmentId.setText("");
		this.textField_ServiceId.setText("");
		this.textField_AppointmentNote.setText("");
		this.textField_AppointmentStatus.setText("");
		this.textField_StartTime.setText("");
		this.textField_EndTime.setText("");
	}

	public String arrayListToString(List<Integer> servicesShow) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < servicesShow.size(); i++) {
			sb.append(servicesShow.get(i));
			if (i < servicesShow.size() - 1) {
				sb.append(", ");
			}
		}
		return sb.toString();
	}

//	public static void main(String[] args) {
//		new AppointmentManagementView();
//	}

}
