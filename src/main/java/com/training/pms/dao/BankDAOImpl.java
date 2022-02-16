package com.training.pms.dao;

import com.training.pms.model.Bank;

public class BankDAOImpl implements BankDAO {

	public void addBankAccount(Bank product) {
		System.out.println("Adding product : "+product);

	}

	public void updateProduct(Bank product) {
		System.out.println("Updating product : "+product);

	}

	public void deleteProduct(int productId) {
		System.out.println("Deleting product with productId : "+productId);

	}

	public void searchByProductId(int productId) {
		System.out.println("Searching for product with productId : "+productId);

	}

	public void searchByProductName(String productName) {
		System.out.println("Searching for product with product name : "+productName);

	}

	public void printAllProducts() {
		System.out.println("Printing all products ");

	}

}
