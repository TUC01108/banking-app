package com.training.pms.dao;

import com.training.pms.model.Bank;

public interface BankDAO {
	public void addBankAccount(Bank product);
	public void updateProduct(Bank product);
	public void deleteProduct(int productId);
	public void searchByProductId(int productId);
	public void searchByProductName(String productName);
	public void printAllProducts();
	
}
