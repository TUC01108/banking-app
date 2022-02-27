package com.training.pms.model;

import java.util.Objects;

//MODEL or POJO ( plain old java object )
public class Employee {
	private int userId;
	private String firstname;
	private String username;
	private String password;
	private String accounttype;
	
	public Employee() {
		// TODO Auto-generated constructor stub
	}
	
	public Employee(int userId, String firstname, String username, String password, String accounttype) {
		this.userId = userId;
		this.firstname = firstname;
		this.username = username;
		this.password = password;
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

	
	

	public String getAccounttype() {
		return accounttype;
	}

	public void setAccounttype(String accounttype) {
		this.accounttype = accounttype;
	}

	@Override
	public int hashCode() {
		return Objects.hash(accounttype, firstname, password, userId, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return Objects.equals(accounttype, other.accounttype) && Objects.equals(firstname, other.firstname)
				&& Objects.equals(password, other.password) && userId == other.userId
				&& Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "Employee [userId=" + userId + ", firstname=" + firstname + ", username=" + username + ", password="
				+ password + ", accounttype=" + accounttype + "]";
	}

	


}
