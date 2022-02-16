package com.training.pms.model;

//MODEL or POJO ( plain old java object )
public class Bank {
	private int accountId;
	private String accountName;
	private int balance;
	private String password;
	
	public Bank() {
		// TODO Auto-generated constructor stub
	}

	public Bank(int accountId, String accountName, int balance, String password) {
		this.accountId = accountId;
		this.accountName = accountName;
		this.balance = balance;
		this.password = password;
	}

	

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accountId;
		result = prime * result + ((accountName == null) ? 0 : accountName.hashCode());
		result = prime * result + balance;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		return result;
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
		if (accountId != other.accountId)
			return false;
		if (accountName == null) {
			if (other.accountName != null)
				return false;
		} else if (!accountName.equals(other.accountName))
			return false;
		if (balance != other.balance)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Bank [accountId=" + accountId + ", accountName=" + accountName + ", balance=" + balance + ", password="
				+ password + "]";
	}

	
	
	

}
