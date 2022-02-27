package com.training.pms.model;

import java.util.Objects;

//MODEL or POJO ( plain old java object )
public class Customer {
	private int userId;
	private String firstname;
	private String username;
	private String password;
	private long balance;
	private String accounttype;
	
	public Customer() {
		// TODO Auto-generated constructor stub
	}
	
	public Customer(int userId, String firstname, String username, String password, int balance, String accounttype) {
		this.userId = userId;
		this.firstname = firstname;
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

	public void setBalance(long l) {
		this.balance = l;
	}
	
	

	public String getAccounttype() {
		return accounttype;
	}

	public void setAccounttype(String accounttype) {
		this.accounttype = accounttype;
	}

	@Override
	public int hashCode() {
		return Objects.hash(firstname, accounttype, balance, password, userId, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		return Objects.equals(firstname, other.firstname) && Objects.equals(accounttype, other.accounttype)
				&& balance == other.balance && Objects.equals(password, other.password) && userId == other.userId
				&& Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "Bank [userId=" + userId + ", firstname=" + firstname + ", username=" + username + ", password="
				+ password + ", balance=" + balance + ", accounttype=" + accounttype + "]";
	}


}
