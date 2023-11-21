package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.AppointmentManagementView;
import view.DentistManagementView;
import view.HomeView;
import view.MedicineManagementView;
import view.PatientManagementView;
import view.PrescriptionManagementView;
import view.ServiceManagementView;
import view.StaffManagementView;
import view.StatisticalManagementView;

public class NavbarController implements ActionListener {

	private HomeView HomeView;
	private AppointmentManagementView AppointmentManagementView;
	private DentistManagementView DentistManagementView;
	private MedicineManagementView MedicineManagementView;
	private PatientManagementView PatientManagementView;
	private PrescriptionManagementView PrescriptionManagementView;
	private ServiceManagementView ServiceManagementView;
	private StaffManagementView StaffManagementView;
	private StatisticalManagementView StatisticalManagementView;

	public NavbarController() {
		super();
	}
	
	public HomeView getHomeView() {
		return HomeView;
	}

	public void setHomeView(HomeView homeView) {
		HomeView = homeView;
	}

	public AppointmentManagementView getAppointmentManagementView() {
		return AppointmentManagementView;
	}

	public void setAppointmentManagementView(AppointmentManagementView appointmentManagementView) {
		AppointmentManagementView = appointmentManagementView;
	}

	public DentistManagementView getDentistManagementView() {
		return DentistManagementView;
	}

	public void setDentistManagementView(DentistManagementView dentistManagementView) {
		DentistManagementView = dentistManagementView;
	}

	public MedicineManagementView getMedicineManagementView() {
		return MedicineManagementView;
	}

	public void setMedicineManagementView(MedicineManagementView medicineManagementView) {
		MedicineManagementView = medicineManagementView;
	}

	public PatientManagementView getPatientManagementView() {
		return PatientManagementView;
	}

	public void setPatientManagementView(PatientManagementView patientManagementView) {
		PatientManagementView = patientManagementView;
	}

	public PrescriptionManagementView getPrescriptionManagementView() {
		return PrescriptionManagementView;
	}

	public void setPrescriptionManagementView(PrescriptionManagementView prescriptionManagementView) {
		PrescriptionManagementView = prescriptionManagementView;
	}

	public ServiceManagementView getServiceManagementView() {
		return ServiceManagementView;
	}

	public void setServiceManagementView(ServiceManagementView serviceManagementView) {
		ServiceManagementView = serviceManagementView;
	}

	public StaffManagementView getStaffManagementView() {
		return StaffManagementView;
	}

	public void setStaffManagementView(StaffManagementView staffManagementView) {
		StaffManagementView = staffManagementView;
	}

	public StatisticalManagementView getStatisticalManagementView() {
		return StatisticalManagementView;
	}

	public void setStatisticalManagementView(StatisticalManagementView statisticalManagementView) {
		StatisticalManagementView = statisticalManagementView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String button_e = e.getActionCommand();

		if (button_e.equals("Staff")) {
			new StaffManagementView();
		} else if (button_e.equals("Patient")) {
			new PatientManagementView();
		} else if (button_e.equals("Dentist")) {
			new DentistManagementView();
		} else if (button_e.equals("Service")) {
			new ServiceManagementView();
		} else if (button_e.equals("Appointment")) {
			new AppointmentManagementView();
		} else if (button_e.equals("Medicine")) {
			new MedicineManagementView();
		} else if (button_e.equals("Service")) {
			new ServiceManagementView();
		} else if (button_e.equals("Prescription")) {
			new PrescriptionManagementView();
		}  else if (button_e.equals("Statistical")) {
			new StatisticalManagementView();
		} else if (button_e.equals("Home")) {
			new HomeView();
		}


	}

}
