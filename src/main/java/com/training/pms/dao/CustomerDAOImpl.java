package com.training.pms.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import com.training.pms.model.Customer;
import com.training.pms.model.Employee;
import com.training.pms.utility.DBConnection;

public class CustomerDAOImpl implements CustomerDAO {

	Connection con = DBConnection.getConnection();

	public boolean addUser(Customer user) {
		System.out.println("Adding account : " + user);
		PreparedStatement statement = null;

		try {
			statement = con.prepareStatement("insert into users values(?,?,?,?,?)");

			statement.setInt(1, user.getUserId());
			statement.setString(2, user.getAccountName());
			statement.setString(3, user.getUsername());
			statement.setString(4, user.getPassword());
			statement.setString(5, user.getAccounttype());

			int rows = statement.executeUpdate();
			System.out.println(rows + " added successfully");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Change code above
		return false;

	}

	@Override
	public boolean updateUser(Customer account) {
		System.out.println("Updating account : " + account);
		PreparedStatement statement = null;
		int rows = 0;
		/*
		 * try { statement = con.prepareStatement(
		 * "update user set user = ?, quantityonhand = ?, price = ? where productId = ?"
		 * );
		 * 
		 * statement.setString(1, product.getProductName()); statement.setInt(2,
		 * product.getQuantityOnHand()); statement.setInt(3, product.getPrice());
		 * statement.setInt(4, product.getProductId());
		 * 
		 * rows = statement.executeUpdate(); System.out.println(rows +
		 * " updated successfully");
		 * 
		 * } catch (SQLException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); }
		 */
		if (rows == 0)
			return false;
		else
			return true;

	}

	@Override
	public boolean deleteUser(int userId) {
		System.out.println("Deleting account with user_Id : " + userId);
		return false;

	}

	@Override
	public Customer searchByUserId(int userId) {
		System.out.println("Searching for account with user__Id : " + userId);
		return null;

	}

	@Override
	public void searchByUsername(String username) {
		System.out.println("Searching for account with account name : " + username);

		try {
			// Statement stat = con.createStatement();
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
	public void printAllUsers() {
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
		System.out.println("Searching users with balances between :" + lowerAmount + " and " + upperAmount);
		PreparedStatement stat;
		try {
			// Statement stat = con.createStatement();

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

	@Override
	public boolean isUserExists(int userId) {
		boolean userExists = false;
		PreparedStatement stat;

		try {
			stat = con.prepareStatement("select * from users where user_Id = ? ");
			stat.setInt(1, userId);

			ResultSet res = stat.executeQuery();
			userExists = res.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userExists;
	}

	@Override
	public boolean withdrawalFromAccount(Customer customer, int amount) {
		CallableStatement stat;
		try {
			stat = con.prepareCall("call withdrawal(?,?)");
			System.out.println(customer.getUserId());
			stat.setInt(1, customer.getUserId());
			stat.setInt(2, amount);

			stat.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		System.out.println("Transfer done/completed");
		
		return true;
	}

}
