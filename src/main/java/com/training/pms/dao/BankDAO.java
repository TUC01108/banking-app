package com.training.pms.dao;

import com.training.pms.model.Bank;

public interface BankDAO {
	public void addBankAccount(Bank account);
	public void updateAccount(Bank account);
	public void deleteAccount(int accountId);
	public void searchByAccountId(int accountId);
	public void searchByAccountName(String accountName);
	public void printAllAccounts();
	public void searchAccountsByBalance(int lowerAmount, int upperAmount);
	
}
