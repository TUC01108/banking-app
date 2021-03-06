package com.training.pms.dao;

import com.training.pms.model.Customer;

public interface CustomerDAO {
	
	public Customer getValues(String username, String password);
	//public Customer getValuesById()
	public boolean withdrawFromAccount(String username, long amount);
	public boolean depositIntoAccount(String username, long amount);
	public boolean transferFromAccount(String sender, String receiver, long amount, long senderBalance, long receiverBalance);
	public boolean addTransaction(Customer customer, long amount);
	public boolean addTransaction(Customer customer, String receiver, long amount);
	public boolean isUserExists(String username);
	
	//public boolean addUser(Customer customer);
	//public boolean updateUser(Customer customer);
	//public void searchByUsername(String username);
	//public void printAllUsers();
	//public void searchUsersByBalance(int lowerAmount, int upperAmount);
	//public boolean isUserExists(int userId);
	//public boolean withdrawalFromAccount(Customer customer, int amount);
	
}
