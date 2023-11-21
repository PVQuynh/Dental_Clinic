package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.DentistManagementView;

public class DentistController implements ActionListener {
	private DentistManagementView dentistManagementView;
	
	public DentistController(DentistManagementView dentistManagementView) {
		super();
		this.dentistManagementView = dentistManagementView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String button_e = e.getActionCommand();
		
		if (button_e.equals("SAVE")) {
			this.dentistManagementView.saveDentist();
		} else if (button_e.equals("EDIT")) {
			this.dentistManagementView.editDentist();
		} else if (button_e.equals("DELETE")) {
			this.dentistManagementView.deleteDentist();
		} else if (button_e.equals("SHOW")) {
			this.dentistManagementView.showDentist();
		} else if (button_e.equals("RESET")) {
			this.dentistManagementView.resetDentist();
		} 
	}

}
