package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.StaffManagementView;

public class StaffController implements ActionListener {
	private StaffManagementView staffManagementView;
	
	public StaffController(StaffManagementView staffManagementView) {
		super();
		this.staffManagementView = staffManagementView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String button_e = e.getActionCommand();
		
		if (button_e.equals("SAVE")) {
			this.staffManagementView.saveStaff();
		} else if (button_e.equals("EDIT")) {
			this.staffManagementView.editStaff();
		} else if (button_e.equals("DELETE")) {
			this.staffManagementView.deleteStaff();
		} else if (button_e.equals("SHOW")) {
			this.staffManagementView.showStaff();
		} else if (button_e.equals("RESET")) {
			this.staffManagementView.resetStaff();
		} 
	}

}
