package com.training.pms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.training.pms.model.Customer;
import com.training.pms.model.Employee;
import com.training.pms.model.Transactions;
import com.training.pms.utility.DBConnection;

public class EmployeeDAOImpl implements EmployeeDAO {
	
	Connection con = DBConnection.getConnection();

	public boolean addUser(Employee employee) {
		System.out.println("Adding account : "+employee);
		PreparedStatement statement = null;

		try {
			statement = con.prepareStatement("insert into users values(?,?,?,?,?)");

			statement.setInt(1, employee.getUserId());
			statement.setString(2, employee.getFirstname());
			statement.setString(3, employee.getUsername());
			statement.setString(4, employee.getPassword());
			statement.setString(5, employee.getAccounttype());

			int rows = statement.executeUpdate();
			System.out.println(rows + " added successfully");
			
			statement.close();
			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Change code above
		return false;

	}

	@Override
	public boolean updateUser(Employee employee) {
		System.out.println("Updating account : "+employee);
		PreparedStatement statement = null;
		int rows = 0;
		/*
		try {
			statement = con.prepareStatement(
					"update user set user = ?, quantityonhand = ?, price = ? where productId = ?");

			statement.setString(1, product.getProductName());
			statement.setInt(2, product.getQuantityOnHand());
			statement.setInt(3, product.getPrice());
			statement.setInt(4, product.getProductId());

			rows = statement.executeUpdate();
			System.out.println(rows + " updated successfully");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		if (rows == 0)
			return false;
		else
			return true;
		
	}

	@Override
	public boolean deleteUser(int userId) {
		System.out.println("Deleting account with user_Id : "+userId);
		return false;
		
	}
	
	@Override
	public boolean deleteUser(String username) {
		System.out.println("Deleting account with user_Id : "+username);
		return false;
		
	}

	@Override
	public Employee searchByUserId(int userId) {
		System.out.println("Searching for account with user__Id : "+userId);
		return null;
		
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
			
			res.close();
			stat.close();
			con.close();

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
			
			res.close();
			stat.close();
			con.close();

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
			
			res.close();
			stat.close();
			con.close();

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
			
			res.close();
			stat.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userExists;
	}

	@Override
	public Employee getValues(String username, String password) {
		//System.out.println("Searching for employee with username : " + username);
		List<Employee> employees = new ArrayList<Employee>();
		Employee employee = new Employee();
		PreparedStatement stat;

		try {
			stat = con.prepareStatement("select * from employees where username = ? and password = ? ");
			stat.setString(1, username);
			stat.setString(2, password);

			ResultSet res = stat.executeQuery();

			while (res.next()) {
				employee = new Employee();
				employee.setUserId(res.getInt(1));
				employee.setFirstname(res.getString(2));
				employee.setUsername(res.getString(3));
				employee.setPassword(res.getString(4));
				employee.setAccounttype(res.getString(5));
				employees.add(employee);
				
				System.out.println("USER ID from employee object : "+employee.getUserId());
			}
			
			res.close();
			stat.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		if(logins.size() == 0) {
			return false;
		}
		*/
		return employee;
	}

	@Override
	public List<Transactions> getTransactions() {
		System.out.println("Getting log of all transactions");
		List<Transactions> transactions = new ArrayList<Transactions>();
		
		Statement stat;
		try {
			stat = con.createStatement();
			ResultSet res = stat.executeQuery("select * from transactions");
			while(res.next()) {
				Transactions transaction = new Transactions();
				transaction.setReceiver(res.getString(1));
				transaction.setSender(res.getString(2));
				transaction.setAmount(res.getLong(3));
				transaction.setTransactiontime(res.getTimestamp(4));
				transactions.add(transaction);
			}
			
			res.close();
			stat.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return transactions;
	}

}
