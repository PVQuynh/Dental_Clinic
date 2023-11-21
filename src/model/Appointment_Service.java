package model;

public class Appointment_Service {
	private int appointmentId;
	private int serviceId;
	
	public Appointment_Service() {
		super();
	}

	public Appointment_Service(int appointmentId, int serviceId) {
		super();
		this.appointmentId = appointmentId;
		this.serviceId = serviceId;
	}

	public int getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}

	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	@Override
	public String toString() {
		return "Appointment_Service [appointmentId=" + appointmentId + ", serviceId=" + serviceId + "]";
	}
	
	
}
