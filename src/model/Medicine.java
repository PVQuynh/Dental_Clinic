package model;

import java.util.List;

public class Medicine {
	private int medicineId;
	private String medicineName;
	private String medicineDetail;
	private double medicinePrice;
	private List<Integer> prescriptions;
	
	public Medicine() {
		super();
	}

	public Medicine(String medicineName, double medicinePrice) {
		super();
		this.medicineName = medicineName;
		this.medicinePrice = medicinePrice;
	}

	public Medicine(int medicineId, String medicineName, String medicineDetail, double medicinePrice,
			List<Integer> prescriptions) {
		super();
		this.medicineId = medicineId;
		this.medicineName = medicineName;
		this.medicineDetail = medicineDetail;
		this.medicinePrice = medicinePrice;
		this.prescriptions = prescriptions;
	}

	public int getMedicineId() {
		return medicineId;
	}

	public void setMedicineId(int medicineId) {
		this.medicineId = medicineId;
	}

	public String getMedicineName() {
		return medicineName;
	}

	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}

	public String getMedicineDetail() {
		return medicineDetail;
	}

	public void setMedicineDetail(String medicineDetail) {
		this.medicineDetail = medicineDetail;
	}

	public double getMedicinePrice() {
		return medicinePrice;
	}

	public void setMedicinePrice(double medicinePrice) {
		this.medicinePrice = medicinePrice;
	}

	public List<Integer> getPrescriptions() {
		return prescriptions;
	}

	public void setPrescriptions(List<Integer> prescriptions) {
		this.prescriptions = prescriptions;
	}

	@Override
	public String toString() {
		return "Medicine [medicineId=" + medicineId + ", medicineName=" + medicineName + ", medicineDetail="
				+ medicineDetail + ", medicinePrice=" + medicinePrice + ", prescriptions=" + prescriptions + "]";
	}

}
