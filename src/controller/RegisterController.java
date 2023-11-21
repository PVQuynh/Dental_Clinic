package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.RegisterView;

public class RegisterController implements ActionListener {
	private RegisterView registerView;
	
	public RegisterController(RegisterView registerView) {
		super();
		this.registerView = registerView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String button_e = e.getActionCommand();
		
		if (button_e.equals("CREATE NEW ACCOUNT")) {
			this.registerView.register();
//			this.registerView.dispose();
		}
		
	}

}
