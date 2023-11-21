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

import controller.MedicineController;
import controller.NavbarController;
import dao.MedicineDao;
import model.Medicine;

public class MedicineManagementView extends JFrame {

	private JTextField textField_MedicineId;
	private JTextField textField_MedicineName;
	private JTextArea textArea_MedicineDetial;
	private JTextField textField_MedicinePrice;
	private Medicine medicine;
	private JTextField textField_PrescriptionIds;
	private JButton button_Save;
	private JButton button_Edit;
	private JButton button_Delete;
	private JLabel label_Empty10;

	public MedicineManagementView() {
		this.medicine = new Medicine();
		this.init();
		this.setVisible(true);
	}

	private void init() {
		// Frame
		this.setTitle("Medicine management Page");
		this.setSize(1200, 600);
		this.setLocationRelativeTo(null);
//		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Action
		MedicineController medicineController = new MedicineController(this);
		NavbarController navbarController = new NavbarController();
		navbarController.setMedicineManagementView(this);

		// Font
		Font font30 = new Font("Arial", Font.BOLD, 30);

		// Panel title
		JPanel panel_Title = new JPanel();
		panel_Title.setLayout(new GridLayout(1, 3));
		JPanel panel_RoleInfo = new JPanel();
		panel_RoleInfo.setLayout(new GridLayout(1, 4));
		panel_RoleInfo.setBackground(Color.yellow);

		JLabel label_Title = new JLabel("MEDICINE INFORMATION");// label_Title
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
		JLabel label_MedicineId = new JLabel("Medicine ID*: ");
		textField_MedicineId = new JTextField();
		JLabel label_MedicineName = new JLabel("Medicine Name: ");
		textField_MedicineName = new JTextField();
		JLabel label_MedicineDetial = new JLabel("Medicine Detail: ");
		textArea_MedicineDetial = new JTextArea();
		JScrollPane scrollPane_MedicineDetail = new JScrollPane(textArea_MedicineDetial,
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		JLabel label_MedicinePrice = new JLabel("Medicine Price: ");
		textField_MedicinePrice = new JTextField();
		JLabel label_PrescriptionIds = new JLabel("Prescription IDs: ");
		textField_PrescriptionIds = new JTextField();
		JLabel label_Empty8 = new JLabel(); // panel_Info_Button
		JLabel label_Empty9 = new JLabel();
		button_Save = new JButton("SAVE");
		button_Save.addActionListener(medicineController);
		button_Edit = new JButton("EDIT");
		button_Edit.addActionListener(medicineController);
		button_Delete = new JButton("DELETE");
		button_Delete.addActionListener(medicineController);
		JButton button_Show = new JButton("SHOW");
		button_Show.addActionListener(medicineController);
		JButton button_Reset = new JButton("RESET");
		button_Reset.addActionListener(medicineController);

		// Check login
		if (LoginView.account.getRoleId() == 2 || LoginView.account.getRoleId() == 3
				|| LoginView.account.getRoleId() == 4) {
			this.button_Delete.setEnabled(false);
		}

		// Check login of Patient
		if (LoginView.account.getRoleId() == 4) {
			this.button_Save.setEnabled(false);
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
		panel_Info_Form.add(label_MedicineId);
		panel_Info_Form.add(textField_MedicineId);
		panel_Info_Form.add(label_MedicineName);
		panel_Info_Form.add(textField_MedicineName);
		panel_Info_Form.add(label_MedicineDetial);
		panel_Info_Form.add(scrollPane_MedicineDetail);
		panel_Info_Form.add(label_MedicinePrice);
		panel_Info_Form.add(textField_MedicinePrice);
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
		ArrayList<Medicine> medicineList = MedicineDao.getInstance().selectAll();

		Object[][] medicineDataTable = new Object[medicineList.size()][];
		for (int i = 0; i < medicineList.size(); i++) {
			Medicine medicine = medicineList.get(i);
			Object[] MedicineArray = { medicine.getMedicineId(), medicine.getMedicineName(),
					medicine.getMedicineDetail(), medicine.getMedicinePrice(), medicine.getPrescriptions() };
			medicineDataTable[i] = MedicineArray;
		}

		String[] columnName = { "Medicine ID", "Medicine Name", "Medicine Detail", "Medicine Price",
				"Prescription IDs" };

		DefaultTableModel tableModel = new DefaultTableModel(medicineDataTable, columnName);
		JTable MedicineTable = new JTable(tableModel);

		JScrollPane scrollPane_MedicineTable = new JScrollPane(MedicineTable, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
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
//		panel_Navbar.add(button_MedicineItem);
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
		panel_container.add(scrollPane_MedicineTable, constraints_View);
		
		// View
		this.setLayout(new BorderLayout());
		this.add(panel_Navbar, BorderLayout.WEST);
		this.add(panel_container, BorderLayout.CENTER);
	}

	public void saveMedicine() {
		String medicineName = this.textField_MedicineName.getText();
		String medicineDetail = this.textArea_MedicineDetial.getText();
		double medicinePrice = Double.valueOf(this.textField_MedicinePrice.getText());

		Medicine medicine = new Medicine();
		medicine.setMedicineName(medicineName);
		medicine.setMedicineDetail(medicineDetail);
		medicine.setMedicinePrice(medicinePrice);

		MedicineDao.getInstance().insert(medicine);

		// Success notification
		this.label_Empty10.setText("Save medicine successfully!");
	}

	public void editMedicine() {
		int medicineId = Integer.valueOf(this.textField_MedicineId.getText());
		String medicineName = this.textField_MedicineName.getText();
		String medicineDetail = this.textArea_MedicineDetial.getText();
		double medicinePrice = Double.valueOf(this.textField_MedicinePrice.getText());

		Medicine medicine = new Medicine();
		medicine.setMedicineId(medicineId);
		medicine.setMedicineName(medicineName);
		medicine.setMedicineDetail(medicineDetail);
		medicine.setMedicinePrice(medicinePrice);

		MedicineDao.getInstance().update(medicine);
		
		// Success notification
		this.label_Empty10.setText("Edit medicine successfully!");
	}

	public void deleteMedicine() {
		int medicineId = Integer.valueOf(this.textField_MedicineId.getText());

		Medicine medicineIdObject = new Medicine();
		medicineIdObject.setMedicineId(medicineId);
		Medicine searchMedicine = MedicineDao.getInstance().selectById(medicineIdObject);

		MedicineDao.getInstance().delete(MedicineDao.getInstance().selectById(searchMedicine));

		// Success notification
		this.label_Empty10.setText("Delete medicine successfully!");
	}

	public void showMedicine() {
		int medicineId = Integer.valueOf(this.textField_MedicineId.getText());

		Medicine medicineIdObject = new Medicine();
		medicineIdObject.setMedicineId(medicineId);
		medicine = MedicineDao.getInstance().selectById(medicineIdObject);

		this.textField_MedicineName.setText(medicine.getMedicineName());
		this.textArea_MedicineDetial.setText(medicine.getMedicineDetail());
		this.textField_MedicinePrice.setText(String.valueOf(medicine.getMedicinePrice()));

		List<Integer> prescriptionsShow = medicine.getPrescriptions();
		String prescriptionsString = arrayListToString(prescriptionsShow);

		this.textField_PrescriptionIds.setText(prescriptionsString);
	}

	public void resetMedicine() {
		this.textField_MedicineId.setText("");
		this.textField_MedicineName.setText("");
		this.textArea_MedicineDetial.setText("");
		this.textField_MedicinePrice.setText("");
		this.textField_PrescriptionIds.setText("");
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
//		new MedicineManagementView();
//	}
}
