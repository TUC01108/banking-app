package com.training.pms.dao;

import com.training.pms.model.Customer;
import com.training.pms.model.Employee;
import com.training.pms.model.Login;

public interface CustomerDAO {
	public boolean addUser(Customer customer);
	public boolean updateUser(Customer customer);
	public boolean deleteUser(int userId);
	public Customer searchByUserId(int userId);
	public void searchByUsername(String username);
	public void printAllUsers();
	public void searchUsersByBalance(int lowerAmount, int upperAmount);
	public boolean isUserExists(int userId);
	//public boolean withdrawalFromAccount(Customer customer, int amount);
	
	public Customer getValues(String username, String password);
	public boolean withdrawFromAccount(String username, long amount);
	public boolean depositIntoAccount(String username, long amount);
	public boolean transferFromAccount(String sender, String receiver, long amount);
	public boolean addTransaction(Customer customer, String receiver, long amount);
	
}
