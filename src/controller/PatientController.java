package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.PatientManagementView;

public class PatientController implements ActionListener {
	private PatientManagementView patientManagementView;
	
	public PatientController(PatientManagementView patientManagementView) {
		super();
		this.patientManagementView = patientManagementView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String button_e = e.getActionCommand();
		
		if (button_e.equals("SAVE")) {
			this.patientManagementView.savePatient();
		} else if (button_e.equals("EDIT")) {
			this.patientManagementView.editPatient();
		} else if (button_e.equals("DELETE")) {
			this.patientManagementView.deletePatient();
		} else if (button_e.equals("SHOW")) {
			this.patientManagementView.showPatient();
		} else if (button_e.equals("RESET")) {
			this.patientManagementView.resetPatient();
		} 
	}

}
