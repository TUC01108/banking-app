package com.training.pms.dao;

import com.training.pms.model.Customer;
import com.training.pms.model.Employee;

public interface EmployeeDAO {
	public boolean addUser(Employee employee);
	public boolean updateUser(Employee employee);
	public boolean deleteUser(int userId);
	public Employee searchByUserId(int userId);
	public void searchByUsername(String username);
	public void printAllUsers();
	public void searchUsersByBalance(int lowerAmount, int upperAmount);
	public boolean isUserExists(int userId);
	
	public Employee getValues(String username, String password);
	
}
