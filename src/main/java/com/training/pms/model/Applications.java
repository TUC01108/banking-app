package com.training.pms.model;

import java.util.Objects;

public class Applications {
	
	private int userId;
	private String firstname;
	private String username;
	private String password;
	private long balance;
	private String accounttype;
	private String accountName;
	
	public Applications() {
		// TODO Auto-generated constructor stub
	}

	public Applications(int userId, String firstname, String username, String password, long balance,
			String accounttype, String accountName) {
		this.userId = userId;
		this.firstname = firstname;
		this.username = username;
		this.password = password;
		this.balance = balance;
		this.accounttype = accounttype;
		this.accountName = accountName;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getBalance() {
		return balance;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}

	public String getAccounttype() {
		return accounttype;
	}

	public void setAccounttype(String accounttype) {
		this.accounttype = accounttype;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	@Override
	public int hashCode() {
		return Objects.hash(accountName, accounttype, balance, firstname, password, userId, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Applications other = (Applications) obj;
		return Objects.equals(accountName, other.accountName) && Objects.equals(accounttype, other.accounttype)
				&& balance == other.balance && Objects.equals(firstname, other.firstname)
				&& Objects.equals(password, other.password) && userId == other.userId
				&& Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "Applications [userId=" + userId + ", firstname=" + firstname + ", username=" + username + ", password="
				+ password + ", balance=" + balance + ", accounttype=" + accounttype + ", accountName=" + accountName
				+ "]";
	}
	
	

}
