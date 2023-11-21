package model;

import java.util.List;

public class Service {
	private int serviceId;
	private String serviceName;
	private String serviceDetail;
	private double servicePrice;
	private List<Integer> appointments;
	
	public Service() {
		super();
	}

	public Service(int serviceId, String serviceName, String serviceDetail, double servicePrice,
			List<Integer> appointments) {
		super();
		this.serviceId = serviceId;
		this.serviceName = serviceName;
		this.serviceDetail = serviceDetail;
		this.servicePrice = servicePrice;
		this.appointments = appointments;
	}

	public int getServiceId() {
		return serviceId;
	}

	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getServiceDetail() {
		return serviceDetail;
	}

	public void setServiceDetail(String serviceDetail) {
		this.serviceDetail = serviceDetail;
	}

	public double getServicePrice() {
		return servicePrice;
	}

	public void setServicePrice(double servicePrice) {
		this.servicePrice = servicePrice;
	}

	public List<Integer> getAppointments() {
		return appointments;
	}

	public void setAppointments(List<Integer> appointments) {
		this.appointments = appointments;
	}

	@Override
	public String toString() {
		return "Service [serviceId=" + serviceId + ", serviceName=" + serviceName + ", serviceDetail=" + serviceDetail
				+ ", servicePrice=" + servicePrice + ", appointments=" + appointments + "]";
	}
	
}
