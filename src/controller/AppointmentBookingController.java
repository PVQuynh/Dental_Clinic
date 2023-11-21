package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.AppointmentBookingView;

public class AppointmentBookingController implements ActionListener{
	private AppointmentBookingView appointmentBookingView;
	
	public AppointmentBookingController(AppointmentBookingView appointmentBookingView) {
		super();
		this.appointmentBookingView = appointmentBookingView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String button_e = e.getActionCommand();
		
		if (button_e.equals("BOOK")) {
			this.appointmentBookingView.appointmentBooking();
		}
	}

}
