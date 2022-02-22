package com.training.pms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import com.training.pms.model.Bank;
import com.training.pms.utility.DBConnection;

public class BankDAOImpl implements BankDAO {
	
	Connection con = DBConnection.getConnection();

	public void addBankAccount(Bank product) {
		System.out.println("Adding product : "+product);

	}

	@Override
	public void updateAccount(Bank account) {
		System.out.println("Updating account : "+account);
		
	}

	@Override
	public void deleteAccount(int accountId) {
		System.out.println("Deleting account with accountId : "+accountId);
		
	}

	@Override
	public void searchByAccountId(int accountId) {
		System.out.println("Searching for account with accountId : "+accountId);
		
	}

	@Override
	public void searchByAccountName(String accountName) {
		System.out.println("Searching for account with account name : "+accountName);
		
		try {
			//Statement stat = con.createStatement();
			PreparedStatement stat;
			stat = con.prepareStatement("select * from account where accountName like ? ");
			stat.setString(1, accountName);
			ResultSet res = stat.executeQuery();

			// Retrieve the column information
			ResultSetMetaData rsmd = res.getMetaData();
			int columnCount = rsmd.getColumnCount();

			// printing the column names
			for (int i = 1; i <= columnCount; i++) {
				System.out.print(rsmd.getColumnName(i) + "    ");
			}
			System.out.println();

			// takes the cursor to the next row
			// returns false if no record is there
			while (res.next()) {

				// printing all the values of the table
				for (int i = 1; i <= columnCount; i++) {
					System.out.print(res.getString(i) + "    ");
				}
				System.out.println();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void printAllAccounts() {
		System.out.println("Printing all accounts ");
		
		try {
			Statement stat = con.createStatement();
			// change to account table once created
			ResultSet res = stat.executeQuery("select * from product");

			// Retrieve the column information
			ResultSetMetaData rsmd = res.getMetaData();
			int columnCount = rsmd.getColumnCount();

			// printing the column names
			for (int i = 1; i <= columnCount; i++) {
				System.out.print(rsmd.getColumnName(i) + "    ");
			}
			System.out.println();

			// takes the cursor to the next row
			// returns false if no record is there
			while (res.next()) {

				// printing all the values of the table
				for (int i = 1; i <= columnCount; i++) {
					System.out.print(res.getString(i) + "    ");
				}
				System.out.println();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void searchAccountsByBalance(int lowerAmount, int upperAmount) {
		System.out.println("Searching accounts with account balances between :"+lowerAmount+" and "+upperAmount);
		PreparedStatement stat;
		try {
			//Statement stat = con.createStatement();
			
			stat = con.prepareStatement("select * from product where price between ? and ?");
			stat.setInt(1, lowerAmount);
			stat.setInt(2, upperAmount);
			ResultSet res = stat.executeQuery();

			// Retrieve the column information
			ResultSetMetaData rsmd = res.getMetaData();
			int columnCount = rsmd.getColumnCount();

			// printing the column names
			for (int i = 1; i <= columnCount; i++) {
				System.out.print(rsmd.getColumnName(i) + "    ");
			}
			System.out.println();

			// takes the cursor to the next row
			// returns false if no record is there
			while (res.next()) {

				// printing all the values of the table
				for (int i = 1; i <= columnCount; i++) {
					System.out.print(res.getString(i) + "    ");
				}
				System.out.println();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
