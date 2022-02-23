package com.training.pms.model;

import java.util.Objects;

//MODEL or POJO ( plain old java object )
public class Bank {
	private int userId;
	private String accountName;
	private String username;
	private String password;
	private int balance;
	private String accounttype;
	
	public Bank() {
		// TODO Auto-generated constructor stub
	}
	
	public Bank(int userId, String accountName, String username, String password, int balance, String accounttype) {
		this.userId = userId;
		this.accountName = accountName;
		this.username = username;
		this.password = password;
		this.balance = balance;
		this.accounttype = accounttype;
	}





	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
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

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}
	
	

	public String getAccounttype() {
		return accounttype;
	}

	public void setAccounttype(String accounttype) {
		this.accounttype = accounttype;
	}

	@Override
	public int hashCode() {
		return Objects.hash(accountName, accounttype, balance, password, userId, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Bank other = (Bank) obj;
		return Objects.equals(accountName, other.accountName) && Objects.equals(accounttype, other.accounttype)
				&& balance == other.balance && Objects.equals(password, other.password) && userId == other.userId
				&& Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "Bank [userId=" + userId + ", accountName=" + accountName + ", username=" + username + ", password="
				+ password + ", balance=" + balance + ", accounttype=" + accounttype + "]";
	}


}
