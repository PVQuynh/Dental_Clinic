package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.AccountEditingView;
import view.AppointmentManagementView;
import view.DentistManagementView;
import view.HomeView;
import view.LoginView;
import view.MedicineManagementView;
import view.PatientManagementView;
import view.PrescriptionManagementView;
import view.ServiceManagementView;
import view.StaffManagementView;
import view.StatisticalManagementView;

public class HomeController implements ActionListener {

	private HomeView homeView;

	public HomeController(HomeView homeView) {
		super();
		this.homeView = homeView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String button_e = e.getActionCommand();

		if (button_e.equals("Staff management")) {
			new StaffManagementView();
		} else if (button_e.equals("Patient management")) {
			new PatientManagementView();
		} else if (button_e.equals("Dentist management")) {
			new DentistManagementView();
		} else if (button_e.equals("Service management")) {
			new ServiceManagementView();
		} else if (button_e.equals("Appointment management")) {
			new AppointmentManagementView();
		} else if (button_e.equals("Medicine management")) {
			new MedicineManagementView();
		} else if (button_e.equals("Service management")) {
			new ServiceManagementView();
		} else if (button_e.equals("Prescription management")) {
			new PrescriptionManagementView();
		}  else if (button_e.equals("Statistical management")) {
			new StatisticalManagementView();
		} else if (button_e.equals("Edit Account")) {
			new AccountEditingView();
		} else if (button_e.equals("Log Out")) {
			this.homeView.dispose();

			new LoginView();
		}

	}

}
