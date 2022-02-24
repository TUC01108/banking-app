package com.training.pms.dao;

import com.training.pms.model.Customer;

public interface CustomerDAO {
	public boolean addUser(Customer customer);
	public boolean updateUser(Customer customer);
	public boolean deleteUser(int userId);
	public Customer searchByUserId(int userId);
	public void searchByUsername(String username);
	public void printAllUsers();
	public void searchUsersByBalance(int lowerAmount, int upperAmount);
	public boolean isUserExists(int userId);
	
}
