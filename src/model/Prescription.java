package model;

import java.sql.Timestamp;
import java.util.List;

public class Prescription {
	private int prescriptionId;
	private String prescriptionName;
	private double prescriptionPrice;
	private int dentistId;
	private int patientId;
	private List<Integer> medicines;
    private Timestamp prescriptionCreationTime;
	
    public Prescription() {
		super();
	}

	public Prescription(int prescriptionId, String prescriptionName, double prescriptionPrice, int dentistId,
			int patientId, List<Integer> medicines, Timestamp prescriptionCreationTime) {
		super();
		this.prescriptionId = prescriptionId;
		this.prescriptionName = prescriptionName;
		this.prescriptionPrice = prescriptionPrice;
		this.dentistId = dentistId;
		this.patientId = patientId;
		this.medicines = medicines;
		this.prescriptionCreationTime = prescriptionCreationTime;
	}

	public int getPrescriptionId() {
		return prescriptionId;
	}

	public void setPrescriptionId(int prescriptionId) {
		this.prescriptionId = prescriptionId;
	}

	public String getPrescriptionName() {
		return prescriptionName;
	}

	public void setPrescriptionName(String prescriptionName) {
		this.prescriptionName = prescriptionName;
	}

	public double getPrescriptionPrice() {
		return prescriptionPrice;
	}

	public void setPrescriptionPrice(double prescriptionPrice) {
		this.prescriptionPrice = prescriptionPrice;
	}

	public int getDentistId() {
		return dentistId;
	}

	public void setDentistId(int dentistId) {
		this.dentistId = dentistId;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public List<Integer> getMedicines() {
		return medicines;
	}

	public void setMedicines(List<Integer> medicines) {
		this.medicines = medicines;
	}

	public Timestamp getPrescriptionCreationTime() {
		return prescriptionCreationTime;
	}

	public void setPrescriptionCreationTime(Timestamp prescriptionCreationTime) {
		this.prescriptionCreationTime = prescriptionCreationTime;
	}

	@Override
	public String toString() {
		return "Prescription [prescriptionId=" + prescriptionId + ", prescriptionName=" + prescriptionName
				+ ", prescriptionPrice=" + prescriptionPrice + ", dentistId=" + dentistId + ", patientId=" + patientId
				+ ", medicines=" + medicines + ", prescriptionCreationTime=" + prescriptionCreationTime + "]";
	}
	
}
