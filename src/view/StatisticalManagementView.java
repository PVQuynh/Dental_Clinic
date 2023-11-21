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
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.border.Border;

import controller.NavbarController;
import dao.AppointmentDao;
import dao.DentistDao;
import dao.MedicineDao;
import dao.PatientDao;
import dao.PrescriptionDao;
import dao.ServiceDao;
import dao.StaffDao;
import model.Account;
import model.Role;

public class StatisticalManagementView extends JFrame {
	public static Account account;
	public static Role role;
	private JLabel label_StaffStatiscal;
	private JLabel label_PatientStatiscal;
	private JLabel label_DentistStatiscal;
	private JLabel label_AppointmentStatiscal;
	private JLabel label_MedicineStatiscal;
	private JLabel label_ServiceStatiscal;
	private JLabel label_PrescriptionStatiscal;

	public StatisticalManagementView() {
		this.init();
		statistical();
		this.setVisible(true);
	}

	private void init() {
		// Frame
		this.setTitle("Statistical Page");
		this.setSize(800, 600);
		this.setLocationRelativeTo(null);

		// Action
		NavbarController navbarController = new NavbarController();
		navbarController.setStatisticalManagementView(this);

		// Font
		Font font = new Font("Arial", Font.BOLD, 30);
		Font font20 = new Font("Arial", Font.BOLD, 20);

		// Panel statistical page
		JPanel panel_Statistical = new JPanel();
		panel_Statistical.setLayout(new BorderLayout());
		panel_Statistical.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
		JPanel panel_StatisticalCenter = new JPanel();
		panel_StatisticalCenter.setLayout(new GridLayout(9, 2));

		JLabel label_Title = new JLabel("STATISTICAL INFORMATION");
		label_Title.setHorizontalAlignment(SwingConstants.CENTER);
		label_Title.setFont(font);

		JLabel label_Empty1 = new JLabel();
		JLabel label_Empty2 = new JLabel();
		JLabel label_Staff = new JLabel("Staff: ");
		label_StaffStatiscal = new JLabel("Staff Statiscal");
		JLabel label_Patient = new JLabel("Patient: ");
		label_PatientStatiscal = new JLabel("Patient Statiscal");
		JLabel label_Dentist = new JLabel("Dentist: ");
		label_DentistStatiscal = new JLabel("Dentist Statiscal");
		JLabel label_Appointment = new JLabel("Appointment: ");
		label_AppointmentStatiscal = new JLabel("Appointment Statiscal");
		JLabel label_Medicine = new JLabel("Medicine: ");
		label_MedicineStatiscal = new JLabel("Medicine Statiscal");
		JLabel label_Service = new JLabel("Service: ");
		label_ServiceStatiscal = new JLabel("Service Statiscal");
		JLabel label_Prescription = new JLabel("Prescription: ");
		label_PrescriptionStatiscal = new JLabel("Prescription Statiscal");

		panel_StatisticalCenter.add(label_Empty1);
		panel_StatisticalCenter.add(label_Empty2);
		panel_StatisticalCenter.add(label_Staff);
		panel_StatisticalCenter.add(label_StaffStatiscal);
		panel_StatisticalCenter.add(label_Patient);
		panel_StatisticalCenter.add(label_PatientStatiscal);
		panel_StatisticalCenter.add(label_Dentist);
		panel_StatisticalCenter.add(label_DentistStatiscal);
		panel_StatisticalCenter.add(label_Appointment);
		panel_StatisticalCenter.add(label_AppointmentStatiscal);
		panel_StatisticalCenter.add(label_Medicine);
		panel_StatisticalCenter.add(label_MedicineStatiscal);
		panel_StatisticalCenter.add(label_Service);
		panel_StatisticalCenter.add(label_ServiceStatiscal);
		panel_StatisticalCenter.add(label_Prescription);
		panel_StatisticalCenter.add(label_PrescriptionStatiscal);

		panel_Statistical.setLayout(new BorderLayout());
		panel_Statistical.add(label_Title, BorderLayout.NORTH);
		panel_Statistical.add(panel_StatisticalCenter, BorderLayout.CENTER);

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
		panel_Navbar.add(button_ServiceItem);
		panel_Navbar.add(button_AppointmentItem);
		panel_Navbar.add(button_PrescriptionItem);
//		panel_Navbar.add(button_StatisticalItem);
		panel_Navbar.add(button_HomeItem);

		// View
		this.setLayout(new BorderLayout());
		
		this.add(panel_Statistical, BorderLayout.CENTER);
		this.add(panel_Navbar, BorderLayout.WEST);

	}

	public void statistical() {
		this.label_StaffStatiscal.setText(StaffDao.getIntance().totalRows() + "");
		this.label_DentistStatiscal.setText(DentistDao.getIntance().totalRows() + "");
		this.label_PatientStatiscal.setText(PatientDao.getIntance().totalRows() + "");
		this.label_AppointmentStatiscal.setText(AppointmentDao.getInstance().totalRows() + "");
		this.label_MedicineStatiscal.setText(MedicineDao.getInstance().totalRows() + "");
		this.label_ServiceStatiscal.setText(ServiceDao.getInstance().totalRows() + "");
		this.label_PrescriptionStatiscal.setText(PrescriptionDao.getInstance().totalRows() + "");
	}

//	public static void main(String[] args) {
//		new StatisticalManagementView();
//	}
}
