package com.training.pms.dao;

import java.util.List;

import com.training.pms.model.Customer;
import com.training.pms.model.Employee;
import com.training.pms.model.Transactions;

public interface EmployeeDAO {
	public boolean addUser(Employee employee);
	public boolean updateUser(Employee employee);
	
	public boolean deleteUser(String username);
			
	//public Employee searchByUserId(int userId);
	public List<Customer> searchByUsername(String username);
	public void printAllUsers();
	public void searchUsersByBalance(int lowerAmount, int upperAmount);
	public boolean isUserExists(int userId);
	
	public Employee getValues(String username, String password);
	public List<Transactions> getTransactions();
	
}
