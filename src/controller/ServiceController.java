package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.ServiceManagementView;

public class ServiceController implements ActionListener {
	private ServiceManagementView serviceManagementView;
	
	public ServiceController(ServiceManagementView serviceManagementView) {
		super();
		this.serviceManagementView = serviceManagementView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String button_e = e.getActionCommand();
		
		if (button_e.equals("SAVE")) {
			this.serviceManagementView.saveService();
		} else if (button_e.equals("EDIT")) {
			this.serviceManagementView.editService();
		} else if (button_e.equals("DELETE")) {
			this.serviceManagementView.deleteService();
		} else if (button_e.equals("SHOW")) {
			this.serviceManagementView.showService();
		} else if (button_e.equals("RESET")) {
			this.serviceManagementView.resetService();
		} 
	}

}
