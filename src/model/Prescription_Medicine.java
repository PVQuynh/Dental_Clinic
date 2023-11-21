package model;

public class Prescription_Medicine {
	private int medicineId;
	private int prescriptionId;
	
	public Prescription_Medicine() {
		super();
	}

	public Prescription_Medicine(int medicineId, int prescriptionId) {
		super();
		this.medicineId = medicineId;
		this.prescriptionId = prescriptionId;
	}

	public int getMedicineId() {
		return medicineId;
	}

	public void setMedicineId(int medicineId) {
		this.medicineId = medicineId;
	}

	public int getPrescriptionId() {
		return prescriptionId;
	}

	public void setPrescriptionId(int prescriptionId) {
		this.prescriptionId = prescriptionId;
	}

	@Override
	public String toString() {
		return "Prescription_Medicine [medicineId=" + medicineId + ", prescriptionId=" + prescriptionId + "]";
	}
	
	
}
