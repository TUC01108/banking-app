package com.training.pms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.training.pms.model.Customer;
import com.training.pms.model.Login;
import com.training.pms.utility.DBConnection;

public class LoginDAOImpl implements LoginDAO {
	
	Connection con = DBConnection.getConnection();

	@Override
	public boolean register(String username, String password, String accounttype, long balance, String firstname) {
		System.out.println("Adding user : " + firstname);
		PreparedStatement stat = null;
		con = DBConnection.getConnection();
		int rows = 0;

		try {
			if(accounttype.equals("C")) {
			stat = con.prepareStatement("insert into customers values(default,?,?,?,?,default,default)");
			stat.setString(1, firstname);
			stat.setString(2, username);
			stat.setString(3, password);
			stat.setLong(4, balance);
			rows = stat.executeUpdate();
			System.out.println(rows + " customer added to database");
			
			stat = null;
			rows = 0;
			stat = con.prepareStatement("insert into login values(default,?,?)");
			stat.setString(1, username);
			stat.setString(2, password);
			rows = stat.executeUpdate();
			System.out.println(rows + " user registered successfully");
			} else {
				stat = con.prepareStatement("insert into employees values(default,?,?,?,default)");
				stat.setString(1, firstname);
				stat.setString(2, username);
				stat.setString(3, password);
				rows = stat.executeUpdate();
				System.out.println(rows + " employee added to database");
				
				stat = null;
				rows = 0;
				stat = con.prepareStatement("insert into login values(default,?,?)");
				stat.setString(1, username);
				stat.setString(2, password);
				rows = stat.executeUpdate();
				System.out.println(rows + " user registered successfully");
			}
			
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
	public boolean validate(String username, String password) {
		
		System.out.println("Searching for user with username : " + username);
		List<Login> logins = new ArrayList<Login>();
		Login login = new Login();
		Customer customer = new Customer();
		List<Customer> customers = new ArrayList<Customer>();
		PreparedStatement stat;
		con = DBConnection.getConnection();

		try {
			stat = con.prepareStatement("select * from login where username = ? and password = ? ");
			stat.setString(1, username);
			stat.setString(2, password);

			ResultSet res = stat.executeQuery();

			while (res.next()) {
				login = new Login();
				login.setUserId(res.getInt(1));
				login.setUsername(res.getString(2));
				login.setPassword(res.getString(3));
				logins.add(login);
				
			}
			
			stat = con.prepareStatement("select * from customers where username = ? and password = ?");
			stat.setString(1, username);
			stat.setString(2, password);
			//stat.setString(3, "Y");
			
			res = stat.executeQuery();
			
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
			}
			
			//System.out.println(customer.getStatus());
			res.close();
			stat.close();
			con.close();
			
			if(customer.getStatus() != null && customer.getStatus().equals("N")) {
				//System.out.println("Account has not been approved yet. Administrator will update in a timely manner.");
				return false;
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(logins.size() == 0) {
			return false;
		}
		
		return true;
	}
	
	public boolean isLoginExists(String username) {
		boolean loginExists = false;
		PreparedStatement stat;
		con = DBConnection.getConnection();

		try {
			stat = con.prepareStatement("select * from login where username = ? ");
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

	@Override
	public boolean apply(Customer customer, long balance, String accountName) {
		System.out.println("Applying for account by : " + customer.getFirstname());
		PreparedStatement stat = null;
		con = DBConnection.getConnection();
		int rows = 0;

		try {
			stat = con.prepareStatement("insert into apply values(default,?,?,?,?,?,?)");
			stat.setString(1, customer.getFirstname());
			stat.setString(2, customer.getUsername());
			stat.setString(3, customer.getPassword());
			stat.setLong(4, balance);
			stat.setString(5, customer.getAccounttype());
			stat.setString(6, accountName);
			rows = stat.executeUpdate();
			System.out.println(rows + " account application added to database");
			
			
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

	

}
