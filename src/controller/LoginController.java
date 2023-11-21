package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.LoginView;
import view.RegisterView;

public class LoginController implements ActionListener {
	private LoginView loginView;
	
	public LoginController(LoginView loginView) {
		super();
		this.loginView = loginView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String button_e = e.getActionCommand();
		
		if (button_e.equals("LOGIN")) {
			this.loginView.login();
			
		} else if (button_e.equals("REGISTER")) {
			new RegisterView();
		}
		
		
		
	}

}
