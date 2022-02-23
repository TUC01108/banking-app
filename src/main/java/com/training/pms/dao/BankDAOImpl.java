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

	public void addAccount(Bank bank) {
		System.out.println("Adding account : "+bank);
		
		PreparedStatement statement = null;

		try {
			statement = con.prepareStatement("insert into users values(?,?,?,?,?)");

			statement.setInt(1, bank.getUserId());
			statement.setString(2, bank.getAccountName());
			statement.setString(3, bank.getUsername());
			statement.setString(4, bank.getPassword());
			statement.setString(5, bank.getAccounttype());

			int rows = statement.executeUpdate();
			System.out.println(rows + " added successfully");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void updateAccount(Bank account) {
		System.out.println("Updating account : "+account);
		
	}

	@Override
	public void deleteAccount(int userId) {
		System.out.println("Deleting account with accountId : "+userId);
		
	}

	@Override
	public void searchByUserId(int userId) {
		System.out.println("Searching for account with accountId : "+userId);
		
	}

	@Override
	public void searchByUsername(String username) {
		System.out.println("Searching for account with account name : "+username);
		
		try {
			//Statement stat = con.createStatement();
			PreparedStatement stat;
			stat = con.prepareStatement("select * from account where accountName like ? ");
			stat.setString(1, username);
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
			
			// change to CORRECT table once created
			ResultSet res = stat.executeQuery("select * from users");

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
	public void searchUsersByBalance(int lowerAmount, int upperAmount) {
		System.out.println("Searching users with balances between :"+lowerAmount+" and "+upperAmount);
		PreparedStatement stat;
		try {
			//Statement stat = con.createStatement();
			
			stat = con.prepareStatement("select * from users where balance between ? and ?");
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
