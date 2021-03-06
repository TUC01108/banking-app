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
	
	
	@Override
	public boolean deleteUser(String username) {
		con = DBConnection.getConnection();
		PreparedStatement stat = null;
		System.out.println("Deleting account with username : "+username);
		
		int rows = 0;
		try {
			// delete from customers table
			stat = con.prepareStatement("delete from customers where username = ?");
			stat.setString(1, username);
			rows = stat.executeUpdate();
			System.out.println(rows + " deleted successfully");
			
			// delete from login table
			stat= con.prepareStatement("delete from login where username = ?");
			stat.setString(1, username);
			rows = stat.executeUpdate();
			
			
			stat.close();
			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (rows == 0)
			return false;
		else
			return true;
		
	}

	@Override
	public List<Customer> searchByUsername(String username) {
		con = DBConnection.getConnection();
		System.out.println("Searching for account with account name : "+username);
		List<Customer> customers = new ArrayList<Customer>();
		PreparedStatement stat = null;
		try {
			//Statement stat = con.createStatement();
			
			stat = con.prepareStatement("select * from customers where username like ? ");
			stat.setString(1, username);
			ResultSet res = stat.executeQuery();

			while (res.next()) {

				Customer customer = new Customer();
				customer.setUserId(1);
				customer.setFirstname(res.getString(2));
				customer.setUsername(res.getString(3));
				customer.setPassword(res.getString(4));
				customer.setBalance(res.getLong(5));
				customer.setAccounttype(res.getString(6));
				customer.setStatus(res.getString(7));
				customers.add(customer);
			}
			
			res.close();
			stat.close();
			con.close();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return customers;
	}

	@Override
	public void printAllUsers() {
		con = DBConnection.getConnection();
		System.out.println("Printing all accounts ");
		
		try {
			Statement stat = con.createStatement();
			
			
			// change to CORRECT table once created
			ResultSet res = stat.executeQuery("select * from customers");

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
		con = DBConnection.getConnection();
		boolean userExists = false;
		PreparedStatement stat = null;
		
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
		con = DBConnection.getConnection();
		PreparedStatement stat = null;
		List<Employee> employees = new ArrayList<Employee>();
		Employee employee = new Employee();
		

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
				
				//System.out.println("USER ID from employee object : "+employee.getUserId());
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
		con = DBConnection.getConnection();
		System.out.println("Getting log of all transactions");
		List<Transactions> transactions = new ArrayList<Transactions>();
		
		Statement stat = null;
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


	@Override
	public List<Customer> getAllApplications() {
		con = DBConnection.getConnection();
		Statement stat = null;
		//ResultSet res = null;
		System.out.println("Getting all applications ");
		List<Customer> customers = new ArrayList<Customer>();

		try {
			stat = con.createStatement();
			ResultSet res = stat.executeQuery("select * from customers where status = 'N' ");
			while (res.next()) {
				Customer customer = new Customer();
				customer.setUserId(res.getInt(1));
				customer.setFirstname(res.getString(2));
				customer.setUsername(res.getString(3));
				customer.setPassword(res.getString(4));
				customer.setBalance(res.getLong(5));
				customer.setAccounttype(res.getString(6));
				customer.setStatus(res.getString(7));
				customers.add(customer);
				
				
			}
			res.close();
			stat.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return customers;
		
	}


	@Override
	public boolean isApplyExists(int userId) {
		con = DBConnection.getConnection();
		boolean applyExists = false;
		PreparedStatement stat = null;
		//ResultSet res = null;
		
		try {
			stat = con.prepareStatement("select * from customers where userid = ? ");
			stat.setInt(1, userId);

			ResultSet res = stat.executeQuery();
			applyExists = res.next();
			
			res.close();
			stat.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return applyExists;
	}


	@Override
	public boolean approveApply(int userId) {
		con = DBConnection.getConnection();
		PreparedStatement stat = null;
		int rows = 0;
		
		try {
			
			stat = con.prepareStatement("update customers set status = 'Y' where userid = ? ");
			stat.setInt(1, userId);

			rows = stat.executeUpdate();
			
			stat.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (rows == 0)
			return false;
		else
			return true;
	}


	@Override
	public boolean rejectApply(int userId) {
		con = DBConnection.getConnection();
		PreparedStatement stat = null;
		int rows = 0;
		
		try {
			stat = con.prepareStatement("delete from customers where userid = ? ");
			stat.setInt(1, userId);

			rows = stat.executeUpdate();
			
			stat.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if (rows == 0)
			return false;
		else
			return true;
	}
	
}
