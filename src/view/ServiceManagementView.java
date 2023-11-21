package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.util.ArrayList;
import java.util.List;

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

import controller.NavbarController;
import controller.ServiceController;
import dao.AppointmentDao;
import dao.ServiceDao;
import model.Appointment;
import model.Service;

public class ServiceManagementView extends JFrame {

	private JTextField textField_ServiceId;
	private JTextField textField_ServiceName;
	private JTextArea textArea_ServiceDetial;
	private JTextField textField_ServicePrice;
	private Service service;
	private JTextField textField_AppointmentIds;
	private JButton button_Save;
	private JButton button_Edit;
	private JButton button_Delete;
	private JLabel label_Empty10;

	public ServiceManagementView() {
		this.service = new Service();
		this.setVisible(true);
		this.init();
	}

	private void init() {
		// Frame
		this.setTitle("Service management Page");
		this.setSize(1200, 600);
		this.setLocationRelativeTo(null);
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Action
		ServiceController serviceController = new ServiceController(this);
		NavbarController navbarController = new NavbarController();
		navbarController.setServiceManagementView(this);

		// Font
		Font font30 = new Font("Arial", Font.BOLD, 30);

		// Panel title
		JPanel panel_Title = new JPanel();
		panel_Title.setLayout(new GridLayout(1, 3));
		JPanel panel_RoleInfo = new JPanel();
		panel_RoleInfo.setLayout(new GridLayout(1, 4));
		panel_RoleInfo.setBackground(Color.yellow);

		JLabel label_Title = new JLabel("SERVICE INFORMATION");// label_Title
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
		panel_Title.add(label_Empty1);

		// Panel Info
		JPanel panel_Info = new JPanel();
		panel_Info.setLayout(new BorderLayout());
		JPanel panel_Info_Form = new JPanel();
		panel_Info_Form.setLayout(new GridLayout(7, 2));
		JPanel panel_Info_Button = new JPanel();
		panel_Info_Button.setLayout(new GridLayout(2, 5));

		JLabel label_Empty6 = new JLabel();// panel_Info_Form
		JLabel label_Empty7 = new JLabel();
		JLabel label_ServiceId = new JLabel("Service ID*: ");
		textField_ServiceId = new JTextField();
		JLabel label_ServiceName = new JLabel("Service Name: ");
		textField_ServiceName = new JTextField();
		JLabel label_ServiceDetial = new JLabel("Service Detail: ");
		textArea_ServiceDetial = new JTextArea();
		JScrollPane scrollPane_ServiceDetail = new JScrollPane(textArea_ServiceDetial,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		JLabel label_ServicePrice = new JLabel("Service Price: ");
		textField_ServicePrice = new JTextField();
		JLabel label_AppointmentIds = new JLabel("Appointment IDs: ");
		textField_AppointmentIds = new JTextField();
		JLabel label_Empty8 = new JLabel(); // panel_Info_Button
		JLabel label_Empty9 = new JLabel();
		button_Save = new JButton("SAVE");
		button_Save.addActionListener(serviceController);
		button_Edit = new JButton("EDIT");
		button_Edit.addActionListener(serviceController);
		button_Delete = new JButton("DELETE");
		button_Delete.addActionListener(serviceController);
		JButton button_Show = new JButton("SHOW");
		button_Show.addActionListener(serviceController);
		JButton button_Reset = new JButton("RESET");
		button_Reset.addActionListener(serviceController);

		// Check login
		if (LoginView.account.getRoleId() == 2 || LoginView.account.getRoleId() == 3
				|| LoginView.account.getRoleId() == 4) {
			this.button_Delete.setEnabled(false);
		}

		// Check login of dentist
		if (LoginView.account.getRoleId() == 3) {
			this.button_Save.setEnabled(false);
			this.button_Edit.setEnabled(false);
			this.button_Delete.setEnabled(false);
		}

		// Check login of patient
		if (LoginView.account.getRoleId() == 4) {
			this.button_Save.setEnabled(false);
			this.button_Edit.setEnabled(false);
			this.button_Delete.setEnabled(false);
		}

		// Label empty
		label_Empty10 = new JLabel();
		JLabel label_Empty11 = new JLabel();
		JLabel label_Empty12 = new JLabel();
		JLabel label_Empty13 = new JLabel();
		JLabel label_Empty14 = new JLabel();

		panel_Info_Form.add(label_Empty6);
		panel_Info_Form.add(label_Empty7);
		panel_Info_Form.add(label_ServiceId);
		panel_Info_Form.add(textField_ServiceId);
		panel_Info_Form.add(label_ServiceName);
		panel_Info_Form.add(textField_ServiceName);
		panel_Info_Form.add(label_ServiceDetial);
		panel_Info_Form.add(scrollPane_ServiceDetail);
		panel_Info_Form.add(label_ServicePrice);
		panel_Info_Form.add(textField_ServicePrice);
		panel_Info_Form.add(label_AppointmentIds);
		panel_Info_Form.add(textField_AppointmentIds);
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

		panel_Info.add(panel_Title, BorderLayout.NORTH);
		panel_Info.add(panel_Info_Form, BorderLayout.CENTER);
		panel_Info.add(panel_Info_Button, BorderLayout.SOUTH);

		// Panel Table
		ArrayList<Service> serviceList = ServiceDao.getInstance().selectAll();

		Object[][] serviceDataTable = new Object[serviceList.size()][];
		for (int i = 0; i < serviceList.size(); i++) {
			Service service = serviceList.get(i);
			Object[] ServiceArray = { service.getServiceId(), service.getServiceName(), service.getServiceDetail(),
					service.getServicePrice(), service.getAppointments() };
			serviceDataTable[i] = ServiceArray;
		}

		String[] columnName = { "Service ID", "Service Name", "Service Detail", "Service Price", "Appointment IDs" };

		DefaultTableModel tableModel = new DefaultTableModel(serviceDataTable, columnName);
		JTable ServiceTable = new JTable(tableModel);

		JScrollPane scrollPane_ServiceTable = new JScrollPane(ServiceTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
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
		panel_Navbar.add(button_PatientItem);
		panel_Navbar.add(button_MedicineItem);
//				panel_Navbar.add(button_ServiceItem);
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
		constraints_View.gridwidth = 6;
		constraints_View.weightx = 1.0; // Divide the space horizontally
		constraints_View.weighty = 1.0; // Divide the space vertically
		constraints_View.fill = GridBagConstraints.BOTH; // make sure to fill the box
		panel_container.add(panel_Info, constraints_View);
		constraints_View.gridx = 0;
		constraints_View.gridy = 1; // Row 2
		constraints_View.gridwidth = 3;
		constraints_View.weightx = 1.0; // Divide the space horizontally
		constraints_View.weighty = 1.0; // Divide the space vertically
		constraints_View.fill = GridBagConstraints.BOTH; // make sure to fill the box
		panel_container.add(scrollPane_ServiceTable, constraints_View);

		// View
		this.setLayout(new BorderLayout());
		this.add(panel_Navbar, BorderLayout.WEST);
		this.add(panel_container, BorderLayout.CENTER);
	}

	public void saveService() {
		String serviceName = this.textField_ServiceName.getText();
		String serviceDetail = this.textArea_ServiceDetial.getText();
		double servicePrice = Double.valueOf(this.textField_ServicePrice.getText());

		Service service = new Service();
		service.setServiceName(serviceName);
		service.setServiceDetail(serviceDetail);
		service.setServicePrice(servicePrice);

		ServiceDao.getInstance().insert(service);

		// Success notification
		this.label_Empty10.setText("Save service successfully!");
	}

	public void editService() {
		int serviceId = Integer.valueOf(this.textField_ServiceId.getText());
		String serviceName = this.textField_ServiceName.getText();
		String serviceDetail = this.textArea_ServiceDetial.getText();
		double servicePrice = Double.valueOf(this.textField_ServicePrice.getText());

		Service service = new Service();
		service.setServiceId(serviceId);
		service.setServiceName(serviceName);
		service.setServiceDetail(serviceDetail);
		service.setServicePrice(servicePrice);

		ServiceDao.getInstance().update(service);

		// Success notification
		this.label_Empty10.setText("Edit service successfully!");
	}

	public void deleteService() {
		int serviceId = Integer.valueOf(this.textField_ServiceId.getText());

		Service serviceIdObject = new Service();
		serviceIdObject.setServiceId(serviceId);
		Service searchService = ServiceDao.getInstance().selectById(serviceIdObject);

		ServiceDao.getInstance().delete(searchService);

		// Success notification
		this.label_Empty10.setText("Delete service successfully!");
	}

	public void showService() {
		int serviceId = Integer.valueOf(this.textField_ServiceId.getText());

		Service serviceIdObject = new Service();
		serviceIdObject.setServiceId(serviceId);
		service = ServiceDao.getInstance().selectById(serviceIdObject);

		this.textField_ServiceName.setText(service.getServiceName());
		this.textArea_ServiceDetial.setText(service.getServiceDetail());
		this.textField_ServicePrice.setText(String.valueOf(service.getServicePrice()));

		List<Integer> appointmentsShow = service.getAppointments();
		String appointmentsString = arrayListToString(appointmentsShow);

		this.textField_AppointmentIds.setText(appointmentsString);
	}

	public void resetService() {
		this.textField_ServiceId.setText("");
		this.textField_ServiceName.setText("");
		this.textArea_ServiceDetial.setText("");
		this.textField_ServicePrice.setText("");
		this.textField_AppointmentIds.setText("");
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
//		new ServiceManagementView();
//	}
}
