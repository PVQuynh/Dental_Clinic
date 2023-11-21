package view;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.SwingConstants;

import controller.AccountEditingController;
import dao.AccountDao;
import dao.RoleDao;
import model.Account;
import model.Role;

public class AccountEditingView extends JFrame {
	private JTextField textField_NewPassword;
	private JTextField textField_ReNewPassword;
	private JTextField textField_Password;
	private JLabel label_Empty3;
	public static Account account;
	public static Role role;

	public AccountEditingView() {
		this.init();
		this.setVisible(true);
	}

	private void init() {
		// Frame
		this.setTitle("Account Editing Page");
		this.setSize(500, 500);
		this.setLocationRelativeTo(null);

		// Action
		AccountEditingController accountEdittingController  = new AccountEditingController(this);

		// Font 
		Font font = new Font("Arial", Font.BOLD, 30);
		Font font20 = new Font("Arial", Font.BOLD, 20);
		
		// Panel accountEditing page
		JPanel panel_EditAccount = new JPanel();
		panel_EditAccount.setLayout(new BorderLayout());
		panel_EditAccount.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		JPanel panel_Title = new JPanel();
		panel_Title.setLayout(new GridBagLayout());
		GridBagConstraints constraints_View = new GridBagConstraints();
		JPanel panel_EditAccount_Center = new JPanel();
		panel_EditAccount_Center.setLayout(new GridLayout(5, 2));
		JPanel panel_EditAccount_Button = new JPanel();
		panel_EditAccount_Button.setLayout(new GridLayout(1, 3));

		JLabel label_EditAccount = new JLabel("ACCOUNT EDITING");
		label_EditAccount.setFont(font);
		label_EditAccount.setHorizontalAlignment(SwingConstants.CENTER);
		JLabel label_EmailTitle = new JLabel("Email: ");
		label_EmailTitle.setHorizontalAlignment(SwingConstants.RIGHT);
		label_EmailTitle.setBackground(Color.yellow);
		label_EmailTitle.setOpaque(true);
		JLabel label_EmailContent = new JLabel();
		label_EmailContent.setText(LoginView.account.getEmail());
		label_EmailContent.setHorizontalAlignment(SwingConstants.LEFT);
		label_EmailContent.setBackground(Color.yellow);
		label_EmailContent.setOpaque(true);
		JLabel label_RoleTitle = new JLabel("Role: ");
		label_RoleTitle.setHorizontalAlignment(SwingConstants.RIGHT);
		label_RoleTitle.setBackground(Color.yellow);
		label_RoleTitle.setOpaque(true);
		JLabel label_RoleContent = new JLabel();
		label_RoleContent.setText(LoginView.role.getRole());
		label_RoleContent.setHorizontalAlignment(SwingConstants.LEFT);
		label_RoleContent.setBackground(Color.yellow);
		label_RoleContent.setOpaque(true);
		
		JLabel label_Empty1 = new JLabel();
		JLabel label_Empty2 = new JLabel();
		JLabel label_Password = new JLabel("Current password: ");
		textField_Password = new JTextField();
		JLabel label_NewPassword = new JLabel("New password: ");
		textField_NewPassword = new JTextField();
		JLabel label_ReNewPassword = new JLabel("Re-enter your new password: ");
		textField_ReNewPassword = new JTextField();
		label_Empty3 = new JLabel();
		JLabel label_Empty4 = new JLabel();
		
		
		JLabel label_Empty5 = new JLabel();
		JButton button_EditAccount = new JButton("UPDATE");
		button_EditAccount.addActionListener(accountEdittingController);
		button_EditAccount.setFont(font20); 
		JLabel label_Empty6 = new JLabel();
		
		constraints_View.gridx = 0;
		constraints_View.gridy = 0; // Row 1
		constraints_View.gridwidth = 4;
		constraints_View.gridheight = 1;
		constraints_View.weightx = 1.0; // Divide the space horizontally
		constraints_View.weighty = 1.0; // Divide the space vertically
		constraints_View.fill = GridBagConstraints.BOTH; // make sure to fill the box
		panel_Title.add(label_EditAccount, constraints_View);
		
		constraints_View.gridx = 0;
		constraints_View.gridy = 1; // Row 2
		constraints_View.gridwidth = 1;
		constraints_View.gridheight = 1;
		constraints_View.weightx = 1.0; // Divide the space horizontally
		constraints_View.weighty = 1.0; // Divide the space vertically
		constraints_View.fill = GridBagConstraints.BOTH; // make sure to fill the box
		panel_Title.add(label_EmailTitle, constraints_View);
		
		constraints_View.gridx = 1;
		constraints_View.gridy = 1; // Row 2
		constraints_View.gridwidth = 1;
		constraints_View.gridheight = 1;
		constraints_View.weightx = 1.0; // Divide the space horizontally
		constraints_View.weighty = 1.0; // Divide the space vertically
		constraints_View.fill = GridBagConstraints.BOTH; // make sure to fill the box
		panel_Title.add(label_EmailContent, constraints_View);
		
		constraints_View.gridx = 2;
		constraints_View.gridy = 1; // Row 2
		constraints_View.gridwidth = 1;
		constraints_View.gridheight = 1;
		constraints_View.weightx = 1.0; // Divide the space horizontally
		constraints_View.weighty = 1.0; // Divide the space vertically
		constraints_View.fill = GridBagConstraints.BOTH; // make sure to fill the box
		panel_Title.add(label_RoleTitle, constraints_View);
		
		constraints_View.gridx = 3;
		constraints_View.gridy = 1; // Row 2
		constraints_View.gridwidth = 1;
		constraints_View.gridheight = 1;
		constraints_View.weightx = 1.0; // Divide the space horizontally
		constraints_View.weighty = 1.0; // Divide the space vertically
		constraints_View.fill = GridBagConstraints.BOTH; // make sure to fill the box
		panel_Title.add(label_RoleContent, constraints_View);
		
		panel_EditAccount_Center.add(label_Empty1);
		panel_EditAccount_Center.add(label_Empty2);
		panel_EditAccount_Center.add(label_Password);
		panel_EditAccount_Center.add(textField_Password);
		panel_EditAccount_Center.add(label_NewPassword);
		panel_EditAccount_Center.add(textField_NewPassword);
		panel_EditAccount_Center.add(label_ReNewPassword);
		panel_EditAccount_Center.add(textField_ReNewPassword);
		panel_EditAccount_Center.add(label_Empty3);
		panel_EditAccount_Center.add(label_Empty4);
		
		panel_EditAccount_Button.add(label_Empty5);
		panel_EditAccount_Button.add(button_EditAccount);
		panel_EditAccount_Button.add(label_Empty6);

		panel_EditAccount.add(panel_Title, BorderLayout.NORTH);
		panel_EditAccount.add(panel_EditAccount_Center, BorderLayout.CENTER);
		panel_EditAccount.add(panel_EditAccount_Button, BorderLayout.SOUTH);

		// View
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(panel_EditAccount, BorderLayout.CENTER);
	}
	
	public void accountEditing() {
		String password = this.textField_Password.getText();
		String newPassword = this.textField_NewPassword.getText();
		String reNewPassword = this.textField_ReNewPassword.getText();
		
		String currentPassword = LoginView.account.getPassword();
		
		if (password.equals(currentPassword)) {
			if (newPassword.equals(reNewPassword)) {
				LoginView.account.setPassword(newPassword);
				
				AccountDao.getInstance().update(LoginView.account);
				
				this.label_Empty3.setText("Change password successfully!");
			} else {
				this.label_Empty3.setText("Re-entered password does not match!");
			}
		} else {
			this.label_Empty3.setText("Current password is incorrect!");
		}
		
	}

//	public static void main(String[] args) {
//		new AccountEditingView();
//	}

}
