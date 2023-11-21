package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.mysql.cj.x.protobuf.MysqlxNotice.Frame;

import controller.HomeController;
import model.Role;

public class HomeView extends JFrame {
	private JLabel label_Role_Tnfo;
	private JButton button_StaffManagement;
	private JButton button_PatientManagement;
	private JButton button_DentistManagement;
	private JButton button_ServiceManagement;
	private JButton button_PrescriptionManagement;
	private JButton button_StatisticalManagement;
	private JButton button_MedicineManagement;
	private JLabel label_Email_Info;
	private JButton button_AppointmentManagement;

	public HomeView() {
		this.init();
		this.setVisible(true);
		this.checkRole();
	}

	private void init() {
		// Frame
		this.setTitle("Home Page");
		this.setSize(900, 400);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Action
		HomeController homeController = new HomeController(this);

		// Font
		Font font = new Font("Arial", Font.BOLD, 30);

		// Panel header
		JPanel panel_Header = new JPanel();
		panel_Header.setLayout(new GridBagLayout());
		GridBagConstraints constraints_View = new GridBagConstraints();
		panel_Header.setBackground(Color.yellow);
		panel_Header.setOpaque(true);

		JLabel label_Email = new JLabel("Email: ");
		label_Email.setHorizontalAlignment(SwingConstants.RIGHT);
		label_Email_Info = new JLabel();
		label_Email_Info.setText(LoginView.account.getEmail());
		label_Email_Info.setHorizontalAlignment(SwingConstants.LEFT);
		JLabel label_Role = new JLabel("Role: ");
		label_Role.setHorizontalAlignment(SwingConstants.RIGHT);
		label_Role_Tnfo = new JLabel();
		label_Role_Tnfo.setText(LoginView.role.getRole());
		label_Role_Tnfo.setHorizontalAlignment(SwingConstants.LEFT);
		JButton button_EditAccount = new JButton("Edit Account");
		button_EditAccount.addActionListener(homeController);
		JButton button_LogOut = new JButton("Log Out");
		button_LogOut.addActionListener(homeController);
		
		constraints_View.gridx = 0;
		constraints_View.gridy = 0; // Row 1
		constraints_View.gridwidth = 1;
		constraints_View.gridheight = 1;
		constraints_View.weightx = 1.0; // Divide the space horizontally
		constraints_View.weighty = 1.0; // Divide the space vertically
		constraints_View.fill = GridBagConstraints.BOTH; // make sure to fill the box
		panel_Header.add(label_Email, constraints_View);
		constraints_View.gridx = 1;
		constraints_View.gridy = 0; // Row 1
		constraints_View.gridwidth = 1;
		constraints_View.gridheight = 1;
		constraints_View.weightx = 1.0; // Divide the space horizontally
		constraints_View.weighty = 1.0; // Divide the space vertically
		constraints_View.fill = GridBagConstraints.BOTH; // make sure to fill the box
		panel_Header.add(label_Email_Info, constraints_View);
		constraints_View.gridx = 2;
		constraints_View.gridy = 0; // Row 1
		constraints_View.gridwidth = 1;
		constraints_View.gridheight = 1;
		constraints_View.weightx = 1.0; // Divide the space horizontally
		constraints_View.weighty = 1.0; // Divide the space vertically
		constraints_View.fill = GridBagConstraints.BOTH; // make sure to fill the box
		panel_Header.add(label_Role, constraints_View);
		constraints_View.gridx = 3;
		constraints_View.gridy = 0; // Row 1
		constraints_View.gridwidth = 1;
		constraints_View.gridheight = 1;
		constraints_View.weightx = 1.0; // Divide the space horizontally
		constraints_View.weighty = 1.0; // Divide the space vertically
		constraints_View.fill = GridBagConstraints.BOTH; // make sure to fill the box
		panel_Header.add(label_Role_Tnfo, constraints_View);
		constraints_View.gridx = 4;
		constraints_View.gridy = 0; // Row 1
		constraints_View.gridwidth = 1;
		constraints_View.gridheight = 1;
		constraints_View.weightx = 1.0; // Divide the space horizontally
		constraints_View.weighty = 1.0; // Divide the space vertically
		constraints_View.fill = GridBagConstraints.BOTH; // make sure to fill the box
		panel_Header.add(button_EditAccount, constraints_View);
		constraints_View.gridx = 5;
		constraints_View.gridy = 0; // Row 1
		constraints_View.gridwidth = 1;
		constraints_View.gridheight = 1;
		constraints_View.weightx = 1.0; // Divide the space horizontally
		constraints_View.weighty = 1.0; // Divide the space vertically
		constraints_View.fill = GridBagConstraints.BOTH; // make sure to fill the box
		panel_Header.add(button_LogOut, constraints_View);

		// Panel Center
		JPanel panel_Center = new JPanel();
		panel_Center.setLayout(new GridBagLayout());
		GridBagConstraints constraints_Center = new GridBagConstraints();

		JLabel label_Image = new JLabel();
		String imagePath = "D:/Documents/OOAD/Nhóm_01/src/resource/img/logo.jpg";
		label_Image.setIcon(new ImageIcon(imagePath));
		label_Image.setHorizontalAlignment(SwingConstants.CENTER);
		button_StaffManagement = new JButton("Staff management");
		button_StaffManagement.addActionListener(homeController);
		button_DentistManagement = new JButton("Dentist management");
		button_DentistManagement.addActionListener(homeController);
		button_PatientManagement = new JButton("Patient management");
		button_PatientManagement.addActionListener(homeController);
		button_AppointmentManagement = new JButton("Appointment management");
		button_AppointmentManagement.addActionListener(homeController);
		button_MedicineManagement = new JButton("Medicine management");
		button_MedicineManagement.addActionListener(homeController);
		button_ServiceManagement = new JButton("Service management");
		button_ServiceManagement.addActionListener(homeController);
		button_PrescriptionManagement = new JButton("Prescription management");
		button_PrescriptionManagement.addActionListener(homeController);
		button_StatisticalManagement = new JButton("Statistical management"); 
		button_StatisticalManagement.addActionListener(homeController);
		
		constraints_Center.gridx = 0;
		constraints_Center.gridy = 0; // Row 1
		constraints_Center.gridwidth = 3;
		constraints_Center.weightx = 2.0; // Divide the space horizontally
		constraints_Center.weighty = 2.0; // Divide the space vertically
		constraints_Center.fill = GridBagConstraints.BOTH; // make sure to fill the box
		panel_Center.add(label_Image, constraints_Center);
		constraints_Center.gridx = 0;
		constraints_Center.gridy = 2; // Row 2
		constraints_Center.gridwidth = 1;
		constraints_Center.weightx = 1.0; // Divide the space horizontally
		constraints_Center.weighty = 1.0; // Divide the space vertically
		constraints_Center.fill = GridBagConstraints.BOTH; // make sure to fill the box
		panel_Center.add(button_StaffManagement, constraints_Center);
		constraints_Center.gridx = 1;
		constraints_Center.gridy = 2; // Row 2
		constraints_Center.gridwidth = 1;
		constraints_Center.weightx = 1.0; // Divide the space horizontally
		constraints_Center.weighty = 1.0; // Divide the space vertically
		constraints_Center.fill = GridBagConstraints.BOTH; // make sure to fill the box
		panel_Center.add(button_DentistManagement, constraints_Center);
		constraints_Center.gridx = 2;
		constraints_Center.gridy = 2; // Row 2
		constraints_Center.gridwidth = 1;
		constraints_Center.weightx = 1.0; // Divide the space horizontally
		constraints_Center.weighty = 1.0; // Divide the space vertically
		constraints_Center.fill = GridBagConstraints.BOTH; // make sure to fill the box
		panel_Center.add(button_PatientManagement, constraints_Center);
		constraints_Center.gridx = 0;
		constraints_Center.gridy = 3; // Row 3
		constraints_Center.gridwidth = 1;
		constraints_Center.weightx = 1.0; // Divide the space horizontally
		constraints_Center.weighty = 1.0; // Divide the space vertically
		constraints_Center.fill = GridBagConstraints.BOTH; // make sure to fill the box
		panel_Center.add(button_AppointmentManagement, constraints_Center);
		constraints_Center.gridx = 1;
		constraints_Center.gridy = 3; // Row 3
		constraints_Center.gridwidth = 1;
		constraints_Center.weightx = 1.0; // Divide the space horizontally
		constraints_Center.weighty = 1.0; // Divide the space vertically
		constraints_Center.fill = GridBagConstraints.BOTH; // make sure to fill the box
		panel_Center.add(button_MedicineManagement, constraints_Center);
		constraints_Center.gridx = 2;
		constraints_Center.gridy = 3; // Row 3
		constraints_Center.gridwidth = 1;
		constraints_Center.weightx = 1.0; // Divide the space horizontally
		constraints_Center.weighty = 1.0; // Divide the space vertically
		constraints_Center.fill = GridBagConstraints.BOTH; // make sure to fill the box
		panel_Center.add(button_ServiceManagement, constraints_Center);
		constraints_Center.gridx = 0;
		constraints_Center.gridy = 4; // Row 4
		constraints_Center.gridwidth = 1;
		constraints_Center.weightx = 1.0; // Divide the space horizontally
		constraints_Center.weighty = 1.0; // Divide the space vertically
		constraints_Center.fill = GridBagConstraints.BOTH; // make sure to fill the box
		panel_Center.add(button_PrescriptionManagement, constraints_Center);
		constraints_Center.gridx = 1;
		constraints_Center.gridy = 4; // Row 4
		constraints_Center.gridwidth = 1;
		constraints_Center.weightx = 1.0; // Divide the space horizontally
		constraints_Center.weighty = 1.0; // Divide the space vertically
		constraints_Center.fill = GridBagConstraints.BOTH; // make sure to fill the box
		panel_Center.add(button_StatisticalManagement, constraints_Center);

		// View
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(panel_Header, BorderLayout.NORTH);
		getContentPane().add(panel_Center, BorderLayout.CENTER);
	}
	
	private void checkRole() {
		if (LoginView.account.getRoleId() == 1) {
			
		} else if (LoginView.account.getRoleId() == 2) {
			button_StaffManagement.setEnabled(false);
			
		} else if (LoginView.account.getRoleId() == 3) {
			button_StaffManagement.setEnabled(false);
			button_DentistManagement.setEnabled(false);
//			button_AppointmentManagement.setEnabled(false);
//			button_ServiceManagement.setEnabled(false);
			button_StatisticalManagement.setEnabled(false);
			
		} else if (LoginView.account.getRoleId() == 4) {
			button_StaffManagement.setEnabled(false);
			button_DentistManagement.setEnabled(false);
			button_PatientManagement.setEnabled(false);
			button_AppointmentManagement.setEnabled(false);
//			button_MedicineManagement.setEnabled(false);
//			button_ServiceManagement.setEnabled(false);
			button_PrescriptionManagement.setEnabled(false);
			button_StatisticalManagement.setEnabled(false);
			
		}
	}
}
