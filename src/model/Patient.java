package model;

import java.sql.Date;
import java.util.List;

public class Patient {
	private int patientId;
	private String fullName;
	private String gender;
	private Date dateOfBirth;
	private String email;
	private String phoneNumber;
	private String address;
	private int accountId;
	private List<Integer> appointmentIds;
	private List<Integer> prescriptionIds;
	
	public Patient() {
		super();
	}

	public Patient(int patientId, String fullName, String gender, Date dateOfBirth, String email, String phoneNumber,
			String address, int accountId, List<Integer> appointmentIds,
			List<Integer> prescriptionIds) {
		super();
		this.patientId = patientId;
		this.fullName = fullName;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.accountId = accountId;
		this.appointmentIds = appointmentIds;
		this.prescriptionIds = prescriptionIds;
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public List<Integer> getAppointmentIds() {
		return appointmentIds;
	}

	public void setAppointmentIds(List<Integer> appointmentIds) {
		this.appointmentIds = appointmentIds;
	}

	public List<Integer> getPrescriptionIds() {
		return prescriptionIds;
	}

	public void setPrescriptionIds(List<Integer> prescriptionIds) {
		this.prescriptionIds = prescriptionIds;
	}

	@Override
	public String toString() {
		return "Dentist [patientId=" + patientId + ", fullName=" + fullName + ", gender=" + gender + ", dateOfBirth="
				+ dateOfBirth + ", email=" + email + ", phoneNumber=" + phoneNumber + ", address=" + address
				+ ", workingStatus=" + ", accountId=" + accountId + ", appointmentIds=" + appointmentIds
				+ ", prescriptionIds=" + prescriptionIds + "]";
	}
	
	
	
}
