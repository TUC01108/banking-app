package com.training.pms.dao;

import java.util.List;

import com.training.pms.model.Applications;
import com.training.pms.model.Customer;
import com.training.pms.model.Employee;
import com.training.pms.model.Transactions;

public interface EmployeeDAO {
	
	public boolean deleteUser(String username);
	public List<Customer> searchByUsername(String username);
	public void printAllUsers();
	public List<Customer> getAllApplications();
	public boolean isUserExists(int userId);
	public Employee getValues(String username, String password);
	public List<Transactions> getTransactions();
	public boolean isApplyExists(int userId);
	public boolean approveApply(int userId);
	public boolean rejectApply(int userId);
	
	//public boolean addUser(Employee employee);
	//public boolean updateUser(Employee employee);
	//public Employee searchByUserId(int userId);
	//public void searchUsersByBalance(int lowerAmount, int upperAmount);
	
}
