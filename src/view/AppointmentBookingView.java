package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.AppointmentBookingController;
import dao.AccountDao;
import dao.AppointmentDao;
import dao.PatientDao;
import dao.AppointmentDao;
import dao.ServiceDao;
import model.Account;
import model.Appointment;
import model.Appointment_Service;
import model.Patient;
import model.Appointment;
import model.Service;

public class AppointmentBookingView extends JFrame {
	private JTextField textField_FullName;
	private ButtonGroup buttonGroup_Gender;
	private JTextField textField_DateOfBirth;
	private JTextField textField_PhoneNumber;
	private JTextField textField_AppointmentTime;
	private JTextField textField_Email;
	private JTextField textField_Address;
	private JList<String> list_ServiceName;
	private JTextArea textArea_AppointmentNote;
	private Patient patient;
	private Account account;
	private JLabel label_Empty3;

	public AppointmentBookingView() {
		this.patient = new Patient();
		this.init();
		this.setVisible(true);
	}

	private void init() {
		// Frame
		this.setTitle("Appointment booking Page");
		this.setSize(600, 800);
		this.setLocationRelativeTo(null);

		// Action
		AppointmentBookingController appointmentBookingController = new AppointmentBookingController(this);

		// Font
		Font font = new Font("Arial", Font.BOLD, 30);
		Font font20 = new Font("Arial", Font.BOLD, 20);

		// Panel appointment booking page
		JPanel panel_Appointment = new JPanel();
		panel_Appointment.setLayout(new BorderLayout());
		panel_Appointment.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		JPanel panel_Appointment_Center = new JPanel();
		panel_Appointment_Center.setLayout(new GridLayout(10, 2));
		JPanel panel_Appointment_Button = new JPanel();
		panel_Appointment_Button.setLayout(new GridLayout(1, 3));

		JLabel label_Appointment = new JLabel("APPOINTMENT BOOKING");
		label_Appointment.setHorizontalAlignment(SwingConstants.CENTER);
		label_Appointment.setFont(font);
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
		JLabel label_Email = new JLabel("Email : ");
		textField_Email = new JTextField();
		JLabel label_PhoneNumber = new JLabel("Phone number: ");
		textField_PhoneNumber = new JTextField();
		JLabel label_Address = new JLabel("Adress: ");
		textField_Address = new JTextField();
		JLabel label_ServiceName = new JLabel("Service Name: ");

		//
		ArrayList<String> serviceNameList = ServiceDao.getInstance().selectServiceName();

		String[] servicesName = serviceNameList.toArray(new String[serviceNameList.size()]);

		list_ServiceName = new JList<String>(servicesName);

		JScrollPane scrollPane_ServiceDetail = new JScrollPane(list_ServiceName,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		JLabel label_AppointmentTime = new JLabel("Appointment time (yyyy-mm-dd): ");
		textField_AppointmentTime = new JTextField();
		JLabel label_AppointmentNote = new JLabel("Appointment Note: ");
		textArea_AppointmentNote = new JTextArea();
		label_Empty3 = new JLabel();
		
		JScrollPane scrollPane_AppointmentNote = new JScrollPane(textArea_AppointmentNote,
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		JLabel label_Empty1 = new JLabel();
		JButton button_Book = new JButton("BOOK");
		button_Book.addActionListener(appointmentBookingController);
		button_Book.setFont(font20);
		JLabel label_Empty2 = new JLabel();

		panel_Appointment_Center.add(label_FullName);
		panel_Appointment_Center.add(textField_FullName);
		panel_Appointment_Center.add(label_Gender);
		panel_Appointment_Center.add(panel_Gender);
		panel_Appointment_Center.add(label_DateOfBirth);
		panel_Appointment_Center.add(textField_DateOfBirth);
		panel_Appointment_Center.add(label_Email);
		panel_Appointment_Center.add(textField_Email);
		panel_Appointment_Center.add(label_PhoneNumber);
		panel_Appointment_Center.add(textField_PhoneNumber);
		panel_Appointment_Center.add(label_Address);
		panel_Appointment_Center.add(textField_Address);
		panel_Appointment_Center.add(label_ServiceName);
		panel_Appointment_Center.add(scrollPane_ServiceDetail);
		panel_Appointment_Center.add(label_AppointmentTime);
		panel_Appointment_Center.add(textField_AppointmentTime);
		panel_Appointment_Center.add(label_AppointmentNote);
		panel_Appointment_Center.add(scrollPane_AppointmentNote);
		panel_Appointment_Center.add(label_Empty3);

		panel_Appointment_Button.add(label_Empty1);
		panel_Appointment_Button.add(button_Book);
		panel_Appointment_Button.add(label_Empty2);

		panel_Appointment.add(label_Appointment, BorderLayout.NORTH);
		panel_Appointment.add(panel_Appointment_Center, BorderLayout.CENTER);
		panel_Appointment.add(panel_Appointment_Button, BorderLayout.SOUTH);

		// View
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(panel_Appointment, BorderLayout.CENTER);
	}

	public void appointmentBooking() {
		// get value from form
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
		Object[] servicesName = this.list_ServiceName.getSelectedValues();
		String appointmentNote = this.textArea_AppointmentNote.getText();
		Date startTime = Date.valueOf(this.textField_AppointmentTime.getText());

		// Creat Account
		Account searchAccount = new Account();
		searchAccount.setEmail(email);
		searchAccount.setPassword(email);
		searchAccount.setRoleId(4);
		AccountDao.getInstance().insert(searchAccount);

		account = AccountDao.getInstance().selectByEmailAndPassword(searchAccount);
		
		// Set information of patient 
		Patient searchPatient = new Patient();
		searchPatient.setGender(gender);
		searchPatient.setFullName(fullName);
		searchPatient.setDateOfBirth(dateOfBirth);
		searchPatient.setEmail(email);
		searchPatient.setPhoneNumber(phoneNumber);
		searchPatient.setAddress(address);
		searchPatient.setAccountId(account.getAccountId());	
		
		PatientDao.getIntance().insertNotAccount(searchPatient);

		// Set information of appointment
		Patient searchPatientEmail = new Patient();
		searchPatientEmail.setEmail(email);
		
		Patient patient = PatientDao.getIntance().selectByEmail(searchPatientEmail);
		
		Appointment searchAppointment = new Appointment();
		searchAppointment.setAppointmentNote(appointmentNote);
		searchAppointment.setAppointmentStatus("Not examined");
		searchAppointment.setStartTime(startTime);
		searchAppointment.setPatientId(patient.getPatientId());

		AppointmentDao.getInstance().insert(searchAppointment);

		// Set information of appointment_service table
		List<Integer> services = new ArrayList<>();
		
		for (Object serviceName : servicesName) {

			Service serviceNameObject = new Service();
			serviceNameObject.setServiceName(String.valueOf(serviceName));
			Service service = ServiceDao.getInstance().selectByName(serviceNameObject);
			
			services.add(service.getServiceId());
		}
		
		Appointment appointmentPatientId = new Appointment();
		appointmentPatientId.setPatientId(patient.getPatientId());
		
		Appointment searchAppointmentPatientID = AppointmentDao.getInstance().selectByPatientID(appointmentPatientId);
		
		Appointment appointment = new Appointment();
		appointment.setAppointmentId(searchAppointmentPatientID.getAppointmentId());
		appointment.setServices(services);
		
		AppointmentDao.getInstance().insertAppointmentService(appointment);
		
		// Success notification
		this.label_Empty3.setText("Successful appointment registration!");
	}

//	public static void main(String[] args) {
//		new AppointmentBookingView();
//	}
}
