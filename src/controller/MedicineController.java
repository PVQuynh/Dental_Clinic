package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.MedicineManagementView;

public class MedicineController implements ActionListener {
	private MedicineManagementView medicineManagementView;
	
	public MedicineController(MedicineManagementView medicineManagementView) {
		super();
		this.medicineManagementView = medicineManagementView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String button_e = e.getActionCommand();
		
		if (button_e.equals("SAVE")) {
			this.medicineManagementView.saveMedicine();
		} else if (button_e.equals("EDIT")) {
			this.medicineManagementView.editMedicine();
		} else if (button_e.equals("DELETE")) {
			this.medicineManagementView.deleteMedicine();
		} else if (button_e.equals("SHOW")) {
			this.medicineManagementView.showMedicine();
		} else if (button_e.equals("RESET")) {
			this.medicineManagementView.resetMedicine();
		} 
	}

}
