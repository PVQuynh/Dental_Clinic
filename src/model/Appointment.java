package model;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class Appointment {
	private int appointmentId;
	private String appointmentNote;
	private String appointmentStatus; // Not examined or Examined
	private Date startTime;
	private Date endTime;
	private int staffId;
	private int dentistId;
	private int patientId;
	private List<Integer> services;
	private Timestamp appointmentCreationTime;
	
	public Appointment() {
		super();
	}

	public Appointment(int appointmentId, String appointmentNote, String appointmentStatus, Date startTime,
			Date endTime, int staffId, int dentistId, int patientId, List<Integer> services, Timestamp appointmentCreationTime) {
		super();
		this.appointmentId = appointmentId;
		this.appointmentNote = appointmentNote;
		this.appointmentStatus = appointmentStatus;
		this.startTime = startTime;
		this.endTime = endTime;
		this.staffId = staffId;
		this.dentistId = dentistId;
		this.patientId = patientId;
		this.services = services;
		this.appointmentCreationTime = appointmentCreationTime;
	}

	public int getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}

	public String getAppointmentNote() {
		return appointmentNote;
	}

	public void setAppointmentNote(String appointmentNote) {
		this.appointmentNote = appointmentNote;
	}

	public String getAppointmentStatus() {
		return appointmentStatus;
	}

	public void setAppointmentStatus(String appointmentStatus) {
		this.appointmentStatus = appointmentStatus;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public int getStaffId() {
		return staffId;
	}

	public void setStaffId(int staffId) {
		this.staffId = staffId;
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

	public List<Integer> getServices() {
		return services;
	}

	public void setServices(List<Integer> services) {
		this.services = services;
	}

	public Timestamp getAppointmentCreationTime() {
		return appointmentCreationTime;
	}

	public void setAppointmentCreationTime(Timestamp appointmentCreationTime) {
		this.appointmentCreationTime = appointmentCreationTime;
	}

	@Override
	public String toString() {
		return "Appointment [appointmentId=" + appointmentId + ", appointmentNote=" + appointmentNote
				+ ", appointmentStatus=" + appointmentStatus + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", staffId=" + staffId + ", patientId=" + patientId + ", services=" + services
				+ ", appointmentCreationTime=" + appointmentCreationTime + "]";
	}

}
