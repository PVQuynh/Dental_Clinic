package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.AccountEditingView;

public class AccountEditingController implements ActionListener {
	private AccountEditingView accountEditingView;
	
	public AccountEditingController(AccountEditingView accountEditingView) {
		super();
		this.accountEditingView = accountEditingView;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String button_e = e.getActionCommand();
		
		if (button_e.equals("UPDATE")) {
			this.accountEditingView.accountEditing();
//			this.accountEditingView.dispose();
		}
		
	}

}
