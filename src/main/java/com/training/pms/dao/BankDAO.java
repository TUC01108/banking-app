package com.training.pms.dao;

import com.training.pms.model.Bank;

public interface BankDAO {
	public void addAccount(Bank account);
	public void updateAccount(Bank account);
	public void deleteAccount(int userId);
	public void searchByUserId(int userId);
	public void searchByUsername(String username);
	public void printAllAccounts();
	public void searchUsersByBalance(int lowerAmount, int upperAmount);
	
}
