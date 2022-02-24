package com.training.pms.dao;

import com.training.pms.model.Customer;
import com.training.pms.model.Login;

public interface LoginDAO {
	
	public boolean register(Login login, Customer customer);
	public boolean validate(String username, String password);
	public boolean isLoginExists(String username);

}
