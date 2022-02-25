package com.training.pms.model;

import java.util.Objects;

public class Login {
	private int userId;
	private String username;
	private String password;
	
	public Login() {
		// TODO Auto-generated constructor stub
	}

	

	public Login(int userId, String username, String password) {
		this.userId = userId;
		this.username = username;
		this.password = password;
	}



	public int getUserId() {
		return userId;
	}



	public void setUserId(int userId) {
		this.userId = userId;
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



	@Override
	public int hashCode() {
		return Objects.hash(password, userId, username);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Login other = (Login) obj;
		return Objects.equals(password, other.password) && userId == other.userId
				&& Objects.equals(username, other.username);
	}



	@Override
	public String toString() {
		return "Login [userId=" + userId + ", username=" + username + ", password=" + password + "]";
	}



	
	
	

}
