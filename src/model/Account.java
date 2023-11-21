package model;

import java.sql.Timestamp;

public class Account {
	private int accountId;
	private String email;
	private String password;
	private int roleId;
	private Timestamp accountCreationTime;
	
	public Account() {
		super();
	}

	public Account(int accountId, String email, String password, int roleId, Timestamp accountCreationTime) {
		super();
		this.accountId = accountId;
		this.email = email;
		this.password = password;
		this.roleId = roleId;
		this.accountCreationTime = accountCreationTime;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Timestamp getAccountCreationTime() {
		return accountCreationTime;
	}

	public void setAccountCreationTime(Timestamp accountCreationTime) {
		this.accountCreationTime = accountCreationTime;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", email=" + email + ", password=" + password
				+ ", accountCreationTime=" + accountCreationTime + ", roleId=" + roleId + "]";
	}
	
}
