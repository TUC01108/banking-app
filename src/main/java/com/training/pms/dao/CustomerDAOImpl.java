package com.training.pms.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import com.training.pms.model.Customer;
import com.training.pms.utility.DBConnection;

public class CustomerDAOImpl implements CustomerDAO {

	Connection con = DBConnection.getConnection();

	@Override
	public Customer getValues(String username, String password) {
		con = DBConnection.getConnection();
		//System.out.println("Searching for customer with username : " + username);
		List<Customer> customers = new ArrayList<Customer>();
		Customer customer = new Customer();
		PreparedStatement stat;

		try {
			stat = con.prepareStatement("select * from customers where username = ? and password = ? ");
			stat.setString(1, username);
			stat.setString(2, password);

			ResultSet res = stat.executeQuery();

			while (res.next()) {
				customer = new Customer();
				customer.setUserId(res.getInt(1));
				customer.setFirstname(res.getString(2));
				customer.setUsername(res.getString(3));
				customer.setPassword(res.getString(4));
				customer.setBalance(res.getLong(5));
				customer.setAccounttype(res.getString(6));
				customer.setStatus(res.getString(7));
				customers.add(customer);
				
				//System.out.println("USER ID customer object : "+customer.getUserId());
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
		return customer;
	}

	@Override
	public boolean withdrawFromAccount(String username, long amount) {
		con = DBConnection.getConnection();
		CallableStatement stat;
		try {
			stat = con.prepareCall("call withdraw(?,?)");
			stat.setString(1, username);
			stat.setLong(2, amount);

			stat.execute();
			
			stat.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		System.out.println("Withdraw done/completed");
		
		return true;
	}
	
	@Override
	public boolean depositIntoAccount(String username, long amount) {
		con = DBConnection.getConnection();
		CallableStatement stat;
		try {
			stat = con.prepareCall("call deposit(?,?)");
			//System.out.println(username);
			stat.setString(1, username);
			stat.setLong(2, amount);

			stat.execute();
			
			stat.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		System.out.println("Deposit done/completed");
		
		return true;
	}

	@Override
	public boolean transferFromAccount(String sender, String receiver, long amount, long senderBalance, long receiverBalance) {
		con = DBConnection.getConnection();
		CallableStatement stat;
		//long debitorBalance=0;
		//receiverBalance=0;
		try {
			stat = con.prepareCall("call transferAmountAndGetbalance(?,?,?,?,?)");
			System.out.println("sending->"+sender+" : receiving->"+receiver);
			stat.setString(1, sender);
			stat.setString(2, receiver);
			stat.setLong(3, amount);
			
			stat.registerOutParameter(4, Types.BIGINT);
			stat.setLong(4, senderBalance);

			stat.registerOutParameter(5, Types.BIGINT);
			stat.setLong(5, receiverBalance);

			stat.execute();
			
			senderBalance = stat.getLong(4);
			receiverBalance = stat.getLong(5);
			
			stat.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		System.out.println("\nSender's new balance : "+senderBalance);
		System.out.println("Receiver's new balance : "+receiverBalance);
		System.out.println();

		//System.out.println("Transfer done/completed");
		
		return true;
	}
	
	@Override
	public boolean addTransaction(Customer customer, long amount) {
		con = DBConnection.getConnection();
		PreparedStatement statement = null;
		int rows = 0;

		try {
			statement = con.prepareStatement("insert into transactions values(?,?,?,default)");
			statement.setString(1, customer.getUsername());
			statement.setString(2, "null");
			statement.setLong(3, amount);
			rows = statement.executeUpdate();
			//System.out.println(rows + " transaction added to database");
			
			statement.close();
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
	public boolean addTransaction(Customer customer, String receiver, long amount) {
		con = DBConnection.getConnection();
		PreparedStatement statement = null;
		int rows = 0;

		try {
			statement = con.prepareStatement("insert into transactions values(?,?,?,default)");
			statement.setString(1, customer.getUsername());
			statement.setString(2, receiver);
			statement.setLong(3, amount);
			rows = statement.executeUpdate();
			//System.out.println(rows + " transaction added to database");
			
			statement.close();
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
	public boolean isUserExists(String username) {
		boolean loginExists = false;
		PreparedStatement stat;
		con = DBConnection.getConnection();

		try {
			stat = con.prepareStatement("select * from customers where username = ? ");
			stat.setString(1, username);

			ResultSet res = stat.executeQuery();
			loginExists = res.next();
			
			res.close();
			stat.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return loginExists;
	}

}
