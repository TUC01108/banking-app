package com.training.pms.dao;

import com.training.pms.model.Customer;
import com.training.pms.model.Employee;
import com.training.pms.model.Login;

public interface LoginDAO {
	
	public boolean register(Login login, Customer customer, Employee employee, String accounttype, int balance, String firstname);
	public boolean validate(String username, String password);
	
	
	public boolean isLoginExists(String username);
	public boolean withdrawFromAccount(String username, int amount);
	public boolean transferFromAccount(String sender, String receiver, int amount);

}
