package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.AppointmentManagementView;

public class AppointmentController implements ActionListener {
	private AppointmentManagementView appointmentManagementView;

	public AppointmentController(AppointmentManagementView appointmentManagementView) {
		super();
		this.appointmentManagementView = appointmentManagementView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String button_e = e.getActionCommand();

		if (button_e.equals("SAVE APPOINTMENT")) {
			this.appointmentManagementView.saveAppointment();
		} else if (button_e.equals("ADD APP-SER")) {
			this.appointmentManagementView.saveAppSer();
		} else if (button_e.equals("EDIT")) {
			this.appointmentManagementView.editAppointment();
		} else if (button_e.equals("DELETE")) {
			this.appointmentManagementView.deleteAppointment();
		} else if (button_e.equals("SHOW")) {
			this.appointmentManagementView.showAppointment();
		} else if (button_e.equals("RESET")) {
			this.appointmentManagementView.resetAppointment();
		}
	}

}
