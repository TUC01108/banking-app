package com.training.pms.dao;

import com.training.pms.model.Customer;

public interface LoginDAO {
	
	public boolean register(String username, String password, String accounttype, long balance, String firstname);
	public boolean validate(String username, String password);
	public boolean isLoginExists(String username);
	public boolean apply(Customer customer, long balance, String accountName);
	

}
