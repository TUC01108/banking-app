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
	private String status;
	
	public Customer() {
		// TODO Auto-generated constructor stub
	}
	

	public Customer(int userId, String firstname, String username, String password, long balance, String accounttype,
			String status) {
		this.userId = userId;
		this.firstname = firstname;
		this.username = username;
		this.password = password;
		this.balance = balance;
		this.accounttype = accounttype;
		this.status = status;
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
	

	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	@Override
	public int hashCode() {
		return Objects.hash(accounttype, balance, firstname, password, status, userId, username);
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
		return Objects.equals(accounttype, other.accounttype) && balance == other.balance
				&& Objects.equals(firstname, other.firstname) && Objects.equals(password, other.password)
				&& Objects.equals(status, other.status) && userId == other.userId
				&& Objects.equals(username, other.username);
	}


	@Override
	public String toString() {
		return "Customer [userId=" + userId + ", firstname=" + firstname + ", username=" + username + ", password="
				+ password + ", balance=" + balance + ", accounttype=" + accounttype + ", status=" + status + "]";
	}


	

}
