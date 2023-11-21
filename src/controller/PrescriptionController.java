package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.PrescriptionManagementView;

public class PrescriptionController implements ActionListener {
	private PrescriptionManagementView prescriptionManagementView;
	
	public PrescriptionController(PrescriptionManagementView prescriptionManagementView) {
		super();
		this.prescriptionManagementView = prescriptionManagementView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String button_e = e.getActionCommand();
		
		if (button_e.equals("SAVE PRESCIPRION*")) {
			this.prescriptionManagementView.savePrescription();
		}else if (button_e.equals("SAVE PRE=>MED")) {
			this.prescriptionManagementView.savePrescriptionMedicine();
		}else if (button_e.equals("EDIT")) {
			this.prescriptionManagementView.editPrescription();
		} else if (button_e.equals("DELETE")) {
			this.prescriptionManagementView.deletePrescription();
		} else if (button_e.equals("SHOW PRE")) {
			this.prescriptionManagementView.showPrescription();
		} else if (button_e.equals("SHOW SER")) {
			this.prescriptionManagementView.showService();
		} else if (button_e.equals("RESET")) {
			this.prescriptionManagementView.resetPrescription();
		} else if (button_e.equals("PRINT")) {
			this.prescriptionManagementView.printBill();
		} 
	}

}
