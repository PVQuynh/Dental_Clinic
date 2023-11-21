package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.print.PrinterException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
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

import controller.NavbarController;
import controller.PrescriptionController;
import dao.PrescriptionDao;
import dao.ServiceDao;
import dao.AppointmentDao;
import dao.DentistDao;
import dao.MedicineDao;
import dao.PatientDao;
import model.Appointment;
import model.Dentist;
import model.Medicine;
import model.Patient;
import model.Prescription;
import model.Prescription_Medicine;
import model.Service;

public class PrescriptionManagementView extends JFrame {

	private JTextField textField_PrescriptionId;
	private JTextField textField_PrescriptionName;
	private JTextField textField_PrescriptionPrice;
	private JTextField textField_MedicineId;
	private JTextField textField_PatientId;
	private Prescription prescription;
	private Object[][] medicineDataTable;
	private DefaultTableModel medicineTableModel;
	private Object[] medicineColumnName;
	private JTextArea textArea_Print;
	private JTextField textField_AppointmentId;
	private JTextField textField_ServiceIds;
	private JTextField textField_totalServicesPrice;
	private double totalServicesPrice;
	private JButton button_Delete;
	private JLabel label_Empty10;

	public PrescriptionManagementView() {
		this.prescription = new Prescription();
		this.setVisible(true);
		this.init();
	}

	private void init() {
		// Frame
		this.setTitle("Bill management Page");
		this.setSize(1200, 600);
		this.setLocationRelativeTo(null);
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Action
		PrescriptionController prescriptionController = new PrescriptionController(this);
		NavbarController navbarController = new NavbarController();
		navbarController.setPrescriptionManagementView(this);

		// Font
		Font font30 = new Font("Arial", Font.BOLD, 30);
		Font font20 = new Font("Arial", Font.BOLD, 20);

		// Panel title
		JPanel panel_Title = new JPanel();
		panel_Title.setLayout(new GridLayout(1, 2));
		JPanel panel_RoleInfo = new JPanel();
		panel_RoleInfo.setLayout(new GridLayout(1, 4));
		panel_RoleInfo.setBackground(Color.yellow);

		JLabel label_Title = new JLabel("BILL INFORMATION");// label_Title
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
		panel_Info_Form.setLayout(new GridBagLayout());
		GridBagConstraints constraints_View = new GridBagConstraints();
		JPanel panel_Info_Button = new JPanel();
		panel_Info_Button.setLayout(new GridLayout(2, 8));

		JLabel label_Prescription = new JLabel("PRESCRIPTION");// panel_Info_Form
		label_Prescription.setHorizontalAlignment(SwingConstants.CENTER);
		label_Prescription.setFont(font20);
		JLabel label_Services = new JLabel("SERVICES");
		label_Services.setHorizontalAlignment(SwingConstants.CENTER);
		label_Services.setFont(font20);

		JLabel label_PatientId = new JLabel("Patient ID: ");
		textField_PatientId = new JTextField();
		JLabel label_AppointmentId = new JLabel("Appointment ID*: ");
		textField_AppointmentId = new JTextField();
		JLabel label_PrescriptionId = new JLabel("Prescription ID*: ");
		textField_PrescriptionId = new JTextField();
		JLabel label_MedicineId = new JLabel("Medicine ID: ");
		textField_MedicineId = new JTextField();
		JLabel label_PrescriptionName = new JLabel("Prescription Name: ");
		textField_PrescriptionName = new JTextField();
		JLabel label_PrescriptionPrice = new JLabel("Prescription Price: ");
		textField_PrescriptionPrice = new JTextField();
		JLabel label_ServiceIds = new JLabel("Service IDs: ");
		textField_ServiceIds = new JTextField();
		JLabel label_totalServicesPrice = new JLabel("Total Services Price: ");
		textField_totalServicesPrice = new JTextField();

		medicineDataTable = null;
		medicineColumnName = new Object[] { "Medicine ID", "Medicine Name", "Medicine Price" };
		medicineTableModel = new DefaultTableModel(medicineDataTable, medicineColumnName);
		JTable MedicineTable = new JTable(medicineTableModel);
		JScrollPane scrollPane_MedicineTable = new JScrollPane(MedicineTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		JLabel label_Empty7 = new JLabel(); // panel_Info_Button
		JButton button_SavePrescription = new JButton("SAVE PRESCIPRION*");
		button_SavePrescription.addActionListener(prescriptionController);
		JButton button_SavePrescriptionMedicine = new JButton("SAVE PRE=>MED");
		button_SavePrescriptionMedicine.addActionListener(prescriptionController);
		JButton button_Edit = new JButton("EDIT");
		button_Edit.addActionListener(prescriptionController);
		button_Delete = new JButton("DELETE");
		button_Delete.addActionListener(prescriptionController);
		JButton button_ShowPre = new JButton("SHOW PRE");
		button_ShowPre.addActionListener(prescriptionController);
		JButton button_ShowSer = new JButton("SHOW SER");
		button_ShowSer.addActionListener(prescriptionController);
		JButton button_Reset = new JButton("RESET");
		button_Reset.addActionListener(prescriptionController);
		JButton button_Print = new JButton("PRINT");
		button_Print.addActionListener(prescriptionController);
		textArea_Print = new JTextArea();

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
		JLabel label_Empty15 = new JLabel();
		JLabel label_Empty16 = new JLabel();
		JLabel label_Empty17 = new JLabel();

		constraints_View.gridx = 0;
		constraints_View.gridy = 0; // Row 1
		constraints_View.gridwidth = 2;
		constraints_View.gridheight = 1;
		constraints_View.weightx = 1.0; // Divide the space horizontally
		constraints_View.weighty = 1.0; // Divide the space vertically
		constraints_View.fill = GridBagConstraints.BOTH; // make sure to fill the box
		panel_Info_Form.add(label_Prescription, constraints_View);

		constraints_View.gridx = 2;
		constraints_View.gridy = 0; // Row 1
		constraints_View.gridwidth = 2;
		constraints_View.gridheight = 1;
		constraints_View.weightx = 1.0; // Divide the space horizontally
		constraints_View.weighty = 1.0; // Divide the space vertically
		constraints_View.fill = GridBagConstraints.BOTH; // make sure to fill the box
		panel_Info_Form.add(label_Services, constraints_View);

		constraints_View.gridx = 0;
		constraints_View.gridy = 1; // Row 2
		constraints_View.gridwidth = 1;
		constraints_View.gridheight = 1;
		constraints_View.weightx = 1.0; // Divide the space horizontally
		constraints_View.weighty = 1.0; // Divide the space vertically
		constraints_View.fill = GridBagConstraints.BOTH; // make sure to fill the box
		panel_Info_Form.add(label_PatientId, constraints_View);

		constraints_View.gridx = 1;
		constraints_View.gridy = 1; // Row 2
		constraints_View.gridwidth = 1;
		constraints_View.gridheight = 1;
		constraints_View.weightx = 1.0; // Divide the space horizontally
		constraints_View.weighty = 1.0; // Divide the space vertically
		constraints_View.fill = GridBagConstraints.BOTH; // make sure to fill the box
		panel_Info_Form.add(textField_PatientId, constraints_View);

		constraints_View.gridx = 2;
		constraints_View.gridy = 1; // Row 2
		constraints_View.gridwidth = 1;
		constraints_View.gridheight = 1;
		constraints_View.weightx = 1.0; // Divide the space horizontally
		constraints_View.weighty = 1.0; // Divide the space vertically
		constraints_View.fill = GridBagConstraints.BOTH; // make sure to fill the box
		panel_Info_Form.add(label_AppointmentId, constraints_View);

		constraints_View.gridx = 3;
		constraints_View.gridy = 1; // Row 2
		constraints_View.gridwidth = 1;
		constraints_View.gridheight = 1;
		constraints_View.weightx = 1.0; // Divide the space horizontally
		constraints_View.weighty = 1.0; // Divide the space vertically
		constraints_View.fill = GridBagConstraints.BOTH; // make sure to fill the box
		panel_Info_Form.add(textField_AppointmentId, constraints_View);

		constraints_View.gridx = 0;
		constraints_View.gridy = 2; // Row 3
		constraints_View.gridwidth = 1;
		constraints_View.gridheight = 1;
		constraints_View.weightx = 1.0; // Divide the space horizontally
		constraints_View.weighty = 1.0; // Divide the space vertically
		constraints_View.fill = GridBagConstraints.BOTH; // make sure to fill the box
		panel_Info_Form.add(label_PrescriptionId, constraints_View);

		constraints_View.gridx = 1;
		constraints_View.gridy = 2; // Row 3
		constraints_View.gridwidth = 1;
		constraints_View.gridheight = 1;
		constraints_View.weightx = 1.0; // Divide the space horizontally
		constraints_View.weighty = 1.0; // Divide the space vertically
		constraints_View.fill = GridBagConstraints.BOTH; // make sure to fill the box
		panel_Info_Form.add(textField_PrescriptionId, constraints_View);

		constraints_View.gridx = 2;
		constraints_View.gridy = 2; // Row 3
		constraints_View.gridwidth = 1;
		constraints_View.gridheight = 1;
		constraints_View.weightx = 1.0; // Divide the space horizontally
		constraints_View.weighty = 1.0; // Divide the space vertically
		constraints_View.fill = GridBagConstraints.BOTH; // make sure to fill the box
		panel_Info_Form.add(label_ServiceIds, constraints_View);

		constraints_View.gridx = 3;
		constraints_View.gridy = 2; // Row 3
		constraints_View.gridwidth = 1;
		constraints_View.gridheight = 1;
		constraints_View.weightx = 1.0; // Divide the space horizontally
		constraints_View.weighty = 1.0; // Divide the space vertically
		constraints_View.fill = GridBagConstraints.BOTH; // make sure to fill the box
		panel_Info_Form.add(textField_ServiceIds, constraints_View);

		constraints_View.gridx = 0;
		constraints_View.gridy = 3; // Row 4
		constraints_View.gridwidth = 1;
		constraints_View.gridheight = 1;
		constraints_View.weightx = 1.0; // Divide the space horizontally
		constraints_View.weighty = 1.0; // Divide the space vertically
		constraints_View.fill = GridBagConstraints.BOTH; // make sure to fill the box
		panel_Info_Form.add(label_MedicineId, constraints_View);

		constraints_View.gridx = 1;
		constraints_View.gridy = 3; // Row 4
		constraints_View.gridwidth = 1;
		constraints_View.gridheight = 1;
		constraints_View.weightx = 1.0; // Divide the space horizontally
		constraints_View.weighty = 1.0; // Divide the space vertically
		constraints_View.fill = GridBagConstraints.BOTH; // make sure to fill the box
		panel_Info_Form.add(textField_MedicineId, constraints_View);

		constraints_View.gridx = 2;
		constraints_View.gridy = 3; // Row 4
		constraints_View.gridwidth = 1;
		constraints_View.gridheight = 1;
		constraints_View.weightx = 1.0; // Divide the space horizontally
		constraints_View.weighty = 1.0; // Divide the space vertically
		constraints_View.fill = GridBagConstraints.BOTH; // make sure to fill the box
		panel_Info_Form.add(label_totalServicesPrice, constraints_View);

		constraints_View.gridx = 3;
		constraints_View.gridy = 3; // Row 4
		constraints_View.gridwidth = 1;
		constraints_View.gridheight = 1;
		constraints_View.weightx = 1.0; // Divide the space horizontally
		constraints_View.weighty = 1.0; // Divide the space vertically
		constraints_View.fill = GridBagConstraints.BOTH; // make sure to fill the box
		panel_Info_Form.add(textField_totalServicesPrice, constraints_View);

		constraints_View.gridx = 0;
		constraints_View.gridy = 4; // Row 5
		constraints_View.gridwidth = 4;
		constraints_View.gridheight = 2;
		constraints_View.weightx = 1.0; // Divide the space horizontally
		constraints_View.weighty = 1.0; // Divide the space vertically
		constraints_View.fill = GridBagConstraints.BOTH; // make sure to fill the box
		panel_Info_Form.add(scrollPane_MedicineTable, constraints_View);

		constraints_View.gridx = 0;
		constraints_View.gridy = 6; // Row 7
		constraints_View.gridwidth = 1;
		constraints_View.gridheight = 1;
		constraints_View.weightx = 1.0; // Divide the space horizontally
		constraints_View.weighty = 1.0; // Divide the space vertically
		constraints_View.fill = GridBagConstraints.BOTH; // make sure to fill the box
		panel_Info_Form.add(label_PrescriptionName, constraints_View);

		constraints_View.gridx = 1;
		constraints_View.gridy = 6; // Row 7
		constraints_View.gridwidth = 1;
		constraints_View.gridheight = 1;
		constraints_View.weightx = 1.0; // Divide the space horizontally
		constraints_View.weighty = 1.0; // Divide the space vertically
		constraints_View.fill = GridBagConstraints.BOTH; // make sure to fill the box
		panel_Info_Form.add(textField_PrescriptionName, constraints_View);

		constraints_View.gridx = 2;
		constraints_View.gridy = 6; // Row 7
		constraints_View.gridwidth = 1;
		constraints_View.gridheight = 1;
		constraints_View.weightx = 1.0; // Divide the space horizontally
		constraints_View.weighty = 1.0; // Divide the space vertically
		constraints_View.fill = GridBagConstraints.BOTH; // make sure to fill the box
		panel_Info_Form.add(label_PrescriptionPrice, constraints_View);

		constraints_View.gridx = 3;
		constraints_View.gridy = 6; // Row 7
		constraints_View.gridwidth = 1;
		constraints_View.gridheight = 1;
		constraints_View.weightx = 1.0; // Divide the space horizontally
		constraints_View.weighty = 1.0; // Divide the space vertically
		constraints_View.fill = GridBagConstraints.BOTH; // make sure to fill the box
		panel_Info_Form.add(textField_PrescriptionPrice, constraints_View);

		panel_Info_Button.add(button_SavePrescription);
		panel_Info_Button.add(button_SavePrescriptionMedicine);
		panel_Info_Button.add(button_Edit);
		panel_Info_Button.add(button_Delete);
		panel_Info_Button.add(button_ShowPre);
		panel_Info_Button.add(button_ShowSer);
		panel_Info_Button.add(button_Reset);
		panel_Info_Button.add(button_Print);
		panel_Info_Button.add(label_Empty10);
		panel_Info_Button.add(label_Empty11);
		panel_Info_Button.add(label_Empty12);
		panel_Info_Button.add(label_Empty13);
		panel_Info_Button.add(label_Empty14);
		panel_Info_Button.add(label_Empty15);
		panel_Info_Button.add(label_Empty16);
		panel_Info_Button.add(label_Empty17);

		panel_Info.add(panel_Title, BorderLayout.NORTH);
		panel_Info.add(panel_Info_Form, BorderLayout.CENTER);
		panel_Info.add(panel_Info_Button, BorderLayout.SOUTH);

		// Panel Table
		ArrayList<Prescription> prescriptionList = PrescriptionDao.getInstance().selectAll();

		Object[][] prescriptionDataTable = new Object[prescriptionList.size()][];
		for (int i = 0; i < prescriptionList.size(); i++) {
			Prescription prescription = prescriptionList.get(i);
			Object[] PrescriptionArray = { prescription.getPrescriptionId(), prescription.getPrescriptionName(),
					prescription.getPrescriptionPrice(), prescription.getDentistId(), prescription.getPatientId(),
					prescription.getMedicines(), prescription.getPrescriptionCreationTime() };
			prescriptionDataTable[i] = PrescriptionArray;
		}

		String[] columnName = { "Prescription ID", "Prescription Name", "Prescription Price", "Dentist ID",
				"Patient ID", "Medicine IDs", "Prescription Creation Time" };

		DefaultTableModel tableModel = new DefaultTableModel(prescriptionDataTable, columnName);
		JTable PrescriptionTable = new JTable(tableModel);

		JScrollPane scrollPane_PrescriptionTable = new JScrollPane(PrescriptionTable,
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
//				panel_Navbar.add(button_PrescriptionItem);
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
		panel_container.add(scrollPane_PrescriptionTable, main_Constraints_View);

		// View
		this.setLayout(new BorderLayout());
		this.add(panel_Navbar, BorderLayout.WEST);
		this.add(panel_container, BorderLayout.CENTER);
	}

	public void savePrescription() {
		this.label_Empty10.setText("Save prescription successfully!");
		int patientId = Integer.parseInt(this.textField_PatientId.getText());
		String prescriptionName = this.textField_PrescriptionName.getText();

		Prescription prescription = new Prescription();
		prescription.setPrescriptionName(prescriptionName);

		// Get dentist Name from Login Account and set
		int accountId = LoginView.account.getAccountId();

		if (LoginView.account.getRoleId() == 3) {
			Dentist dentist_AccountId = new Dentist();
			dentist_AccountId.setAccountId(accountId);

			Dentist dentist = new Dentist();
			dentist = DentistDao.getIntance().selectByAccountId(dentist_AccountId);

			prescription.setDentistId(dentist.getDentistId());
		} else {
			// Account is not Dentist Account
			prescription.setDentistId(0);
		}

		// Set Patient
		prescription.setPatientId(patientId);

		PrescriptionDao.getInstance().insert(prescription);

		// Success notification
		this.label_Empty10.setText("Save pre successfully!");

	}

	public void savePrescriptionMedicine() {
		int prescriptionId = Integer.parseInt(this.textField_PrescriptionId.getText());
		String medicineString = this.textField_MedicineId.getText();

		// Get medicines
		List<Integer> medicines = new ArrayList<>();
		String[] medicineArray = medicineString.split(",");
		for (String medicine : medicineArray) {
			int medicineId = Integer.parseInt(medicine.trim());

			medicines.add(medicineId);
		}

		// Create object Prescription and insert
		Prescription prescription = new Prescription();
		prescription.setPrescriptionId(prescriptionId);
		prescription.setMedicines(medicines);

		PrescriptionDao.getInstance().insertPrescriptionMedicine(prescription);

		// Presciption
		// Get Object Prescription
		Prescription prescriptionIdObject = new Prescription();
		prescriptionIdObject.setPrescriptionId(prescriptionId);
		prescription = PrescriptionDao.getInstance().selectById(prescriptionIdObject);

		// Calculate Price
		double price = 0;

		for (Integer medicineId : medicines) {
			Medicine searchMedicine = new Medicine();
			searchMedicine.setMedicineId(medicineId);

			Medicine medicine = MedicineDao.getInstance().selectById(searchMedicine);

			price += medicine.getMedicinePrice();
		}

		prescription.setPrescriptionPrice(price);

		PrescriptionDao.getInstance().updatePrice(prescription);

		// Success notification
		this.label_Empty10.setText("Save pre-med successfully!");
	}

	public void editPrescription() {
		int prescriptionId = Integer.parseInt(this.textField_PrescriptionId.getText());
		String medicineString = this.textField_MedicineId.getText();

		// Get medicines
		List<Integer> medicines = new ArrayList<>();
		String[] medicineArray = medicineString.split(",");
		for (String medicine : medicineArray) {
			int medicineId = Integer.parseInt(medicine.trim());

			medicines.add(medicineId);
		}

		int patientId = Integer.parseInt(this.textField_PatientId.getText());
		String prescriptionName = this.textField_PrescriptionName.getText();

		// Create object Prescription and insert
		Prescription prescription = new Prescription();
		prescription.setPrescriptionId(prescriptionId);
		prescription.setPrescriptionName(prescriptionName);
		prescription.setPatientId(patientId);
		prescription.setMedicines(medicines);

		PrescriptionDao.getInstance().update(prescription);

		// Presciption
		// Get Object Prescription
		Prescription prescriptionIdObject = new Prescription();
		prescriptionIdObject.setPrescriptionId(prescriptionId);
		prescription = PrescriptionDao.getInstance().selectById(prescriptionIdObject);

		// Calculate Price
		double price = 0;

		for (Integer medicineId : medicines) {
			Medicine searchMedicine = new Medicine();
			searchMedicine.setMedicineId(medicineId);

			Medicine medicine = MedicineDao.getInstance().selectById(searchMedicine);

			price += medicine.getMedicinePrice();
		}

		prescription.setPrescriptionPrice(price);

		PrescriptionDao.getInstance().updatePrice(prescription);

		// Success notification
		this.label_Empty10.setText("Edit prescription successfully!");
	}

	public void deletePrescription() {
		int prescriptionId = Integer.valueOf(this.textField_PrescriptionId.getText());

		Prescription prescriptionIdObject = new Prescription();
		prescriptionIdObject.setPrescriptionId(prescriptionId);

		prescription = PrescriptionDao.getInstance().selectById(prescriptionIdObject);
		PrescriptionDao.getInstance().delete(prescription);

		// Success notification
		this.label_Empty10.setText("Delete prescription successfully!");
	}

	public void showPrescription() {
		int prescriptionId = Integer.valueOf(this.textField_PrescriptionId.getText());

		Prescription prescriptionIdObject = new Prescription();
		prescriptionIdObject.setPrescriptionId(prescriptionId);
		prescription = PrescriptionDao.getInstance().selectById(prescriptionIdObject);

		// Table medicine
		List<Integer> medicines = prescription.getMedicines();
		int rowCount = medicines.size();

		// Data of Table row
		medicineDataTable = new Object[rowCount][3];

		int index = 0;
		for (Integer medicineId : medicines) {
			Medicine medicine = new Medicine();
			medicine.setMedicineId(medicineId);

			Object[] medicineObject = MedicineDao.getInstance().selectNameAndPrice(medicine);

			medicineDataTable[index][0] = medicineObject[0];
			medicineDataTable[index][1] = medicineObject[1];
			medicineDataTable[index][2] = medicineObject[2];

			index++;
		}

		// Set data for table
		medicineTableModel.setDataVector(medicineDataTable, medicineColumnName);

		// Show
		this.textField_PatientId.setText(prescription.getPatientId() + "");
		this.textField_PrescriptionName.setText(prescription.getPrescriptionName());
		this.textField_PrescriptionId.setText(prescription.getPrescriptionId() + "");

		List<Integer> medicinesShow = prescription.getMedicines();
		String medicinesString = arrayListToString(medicinesShow);
		this.textField_MedicineId.setText(medicinesString);

		this.textField_PrescriptionPrice.setText(prescription.getPrescriptionPrice() + "");

		// Update dentist
		// Get dentist Name from Login Account and set
		int accountId = LoginView.account.getAccountId();

		if (LoginView.account.getRoleId() == 3) {
			Dentist dentist_AccountId = new Dentist();
			dentist_AccountId.setAccountId(accountId);

			Dentist dentist = new Dentist();
			dentist = DentistDao.getIntance().selectByAccountId(dentist_AccountId);

			prescription.setDentistId(dentist.getDentistId());
		} else {
			// Account is not Dentist Account
			prescription.setDentistId(0);
		}

		// Set Patient
		PrescriptionDao.getInstance().updateDentistId(prescription);
	}

	public void showService() {
		int appointmentId = Integer.valueOf(this.textField_AppointmentId.getText());

		Appointment appointmentIdObject = new Appointment();
		appointmentIdObject.setAppointmentId(appointmentId);
		Appointment appointment = AppointmentDao.getInstance().selectById(appointmentIdObject);

		// show
		totalServicesPrice = 0;
		List<Integer> servicesId = appointment.getServices();
		for (int i = 0; i < servicesId.size(); i++) {
			Service servicePrice = new Service();
			servicePrice.setServiceId(servicesId.get(i));

			Service searchService = ServiceDao.getInstance().selectById(servicePrice);

			totalServicesPrice += searchService.getServicePrice();
		}

		String servicesString = arrayListToString(servicesId);
		this.textField_ServiceIds.setText(servicesString);
		this.textField_totalServicesPrice.setText(totalServicesPrice + "");
	}

	public void resetPrescription() {
		this.textField_PatientId.setText("");
		this.textField_PrescriptionId.setText("");
		this.textField_PrescriptionName.setText("");
		this.textField_PrescriptionId.setText("");
		this.textField_MedicineId.setText("");
		this.textField_PrescriptionPrice.setText("");
		this.textField_AppointmentId.setText("");
		this.textField_ServiceIds.setText("");
		this.textField_totalServicesPrice.setText("");

		medicineTableModel.setDataVector(null, medicineColumnName);

	}

//	public void chargePrescription() {
//		// Presciption
//		int prescriptionId = Integer.valueOf(this.textField_PrescriptionId.getText());
//
//		// Get Object Prescription
//		Prescription prescriptionIdObject = new Prescription();
//		prescriptionIdObject.setPrescriptionId(prescriptionId);
//		prescription = PrescriptionDao.getInstance().selectById(prescriptionIdObject);
//
//		// Calculate Price
//		double price = 0;
//		List<Integer> medicines = prescription.getMedicines();
//
//		for (Integer medicineId : medicines) {
//			Medicine searchMedicine = new Medicine();
//			searchMedicine.setMedicineId(medicineId);
//
//			Medicine medicine = MedicineDao.getInstance().selectById(searchMedicine);
//
//			price += medicine.getMedicinePrice();
//		}
//
//		this.textField_PrescriptionPrice.setText(price + "");
//
//		prescription.setPrescriptionPrice(price);
//
//		PrescriptionDao.getInstance().updatePrice(prescription);
//
//		// Success notification
//		this.label_Empty10.setText("Charge prescription successfully!");
//	}

	public String arrayListToString(List<Integer> medicinesShow) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < medicinesShow.size(); i++) {
			sb.append(medicinesShow.get(i));
			if (i < medicinesShow.size() - 1) {
				sb.append(", ");
			}
		}
		return sb.toString();
	}

	public void printBill() {
		// Prescription
		int prescriptionId = Integer.parseInt(this.textField_PrescriptionId.getText());
		String medicineString = this.textField_MedicineId.getText();

		// Get medicines
		List<Integer> medicines = new ArrayList<>();
		String[] medicineArray = medicineString.split(",");
		for (String medicine : medicineArray) {
			int medicineId = Integer.parseInt(medicine.trim());

			medicines.add(medicineId);
		}

		int patientId = Integer.parseInt(this.textField_PatientId.getText());
		String prescriptionName = this.textField_PrescriptionName.getText();

		// Create object prescription
		Prescription prescriptionIdObject = new Prescription();
		prescriptionIdObject.setPrescriptionId(prescriptionId);

		prescription = PrescriptionDao.getInstance().selectById(prescriptionIdObject);

		// Get patient name
		Patient patientIdObject = new Patient();
		patientIdObject.setPatientId(patientId);
		Patient patient = PatientDao.getIntance().selectById(patientIdObject);

		// Tabel
		int rowCount = medicines.size();

		medicineDataTable = new Object[rowCount][3];

		int index = 0;
		for (Integer medicineId : medicines) {
			Medicine medicine = new Medicine();
			medicine.setMedicineId(medicineId);

			Object[] medicineObject = MedicineDao.getInstance().selectNameAndPrice(medicine);

			medicineDataTable[index][0] = medicineObject[0];
			medicineDataTable[index][1] = medicineObject[1];
			medicineDataTable[index][2] = medicineObject[2];

			index++;
		}

		// Services
		String servicesString = this.textField_ServiceIds.getText();

		List<Integer> servicesIds = stringToArrayList(servicesString);

		// Bill
		textArea_Print.setText("Dental Clinic (Group 1 - OOAD)\n\n\n");
		textArea_Print.setText(textArea_Print.getText()
				+ "-----------------------------------------------------  BILL  -----------------------------------------------------\n\n\n\n");
		textArea_Print.setText(textArea_Print.getText() + "Full Name: " + patient.getFullName() + "\t\t Gender: "
				+ patient.getGender() + "\n");
		textArea_Print.setText(textArea_Print.getText() + "Phone number: " + patient.getPhoneNumber() + "\n\n");
		textArea_Print.setText(textArea_Print.getText() + "Prescription Name: " + prescriptionName + "\n\n\n");

		// Medicine
		textArea_Print.setText(textArea_Print.getText()
				+ "------------------------------------------------------------------------------------------------------------------\n");
		textArea_Print.setText(
				textArea_Print.getText() + "Medicine ID\t|      " + "Medicine Price\t|         " + "Medicine Name\n");

		for (Object[] row : medicineDataTable) {
			int medicineId = (int) row[0];
			String medicineName = (String) row[1];
			double medicinePrice = (double) row[2];
			textArea_Print.setText(textArea_Print.getText() + medicineId + "\t|      " + medicinePrice
					+ "\t\t|         " + medicineName + "\n");
		}

		textArea_Print.setText(textArea_Print.getText() + "\n");
		textArea_Print.setText(textArea_Print.getText()
				+ "------------------------------------------------------------------------------------------------------------------\n");
		textArea_Print.setText(
				textArea_Print.getText() + "Prescription Price: " + prescription.getPrescriptionPrice() + "\n\n");

		// Service
		textArea_Print.setText(textArea_Print.getText()
				+ "------------------------------------------------------------------------------------------------------------------\n");
		textArea_Print.setText(
				textArea_Print.getText() + "Service ID\t|      " + "Service Price\t|         " + "Service Name\n");

		for (Integer serviceId : servicesIds) {
			Service serviceIdObject = new Service();
			serviceIdObject.setServiceId(serviceId);

			Service searchService = ServiceDao.getInstance().selectById(serviceIdObject);

			textArea_Print.setText(textArea_Print.getText() + searchService.getServiceId() + "\t|      "
					+ searchService.getServicePrice() + "\t\t|         " + searchService.getServiceName() + "\n");
		}
		textArea_Print.setText(textArea_Print.getText() + "\n");
		textArea_Print.setText(textArea_Print.getText()
				+ "------------------------------------------------------------------------------------------------------------------\n");
		textArea_Print.setText(textArea_Print.getText() + "Total Services Price: " + totalServicesPrice + "\n\n\n");

		// Total money
		double totalMoney = prescription.getPrescriptionPrice() + totalServicesPrice;
		textArea_Print.setText(textArea_Print.getText() + "Total amount to be paid: " + totalMoney + "\n\n\n");

		// Get creation time
		Timestamp timestamp = prescription.getPrescriptionCreationTime();
		LocalDateTime localDateTime = timestamp.toLocalDateTime();

		LocalDate date = localDateTime.toLocalDate();
//		LocalTime time = localDateTime.toLocalTime();

		textArea_Print.setText(textArea_Print.getText() + "\t\t\tPrescription Creation Date: " + date + "\n");
//		textArea_Print.setText(textArea_Print.getText() + "\t\t\tPrescription Creation Time: " + time + "\n");

		// Full Name of Dentist
		String fullNameDentist = "";
		
		if (prescription.getDentistId() != 0) {
			Dentist dentistId = new Dentist();
			dentistId.setDentistId(prescription.getDentistId());
			
			Dentist dentist = new Dentist();
			dentist = DentistDao.getIntance().selectById(dentistId);
			fullNameDentist = dentist.getFullName();
		}

		textArea_Print.setText(textArea_Print.getText() + "\t\t\t\t      Dentist\n");
		textArea_Print.setText(textArea_Print.getText() + "\t\t\t\t" + fullNameDentist + "\n");

		
		try {
			textArea_Print.print();
		} catch (PrinterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Success notification
		this.label_Empty10.setText("Print bill successfully!");

	}

	public List<Integer> stringToArrayList(String input) {
		// Loại bỏ khoảng trắng dư thừa và tách chuỗi thành mảng các số
		String[] numberStrings = input.replaceAll("\\s+", "").split(",");

		// Khởi tạo một ArrayList<Integer>
		ArrayList<Integer> arrayList = new ArrayList<>();

		// Chuyển đổi từng chuỗi số thành Integer và thêm vào ArrayList
		for (String numberString : numberStrings) {
			Integer number = Integer.parseInt(numberString);
			arrayList.add(number);
		}

		return arrayList;

	}

//	public static void main(String[] args) {
//		new PrescriptionManagementView();
//	}
}
