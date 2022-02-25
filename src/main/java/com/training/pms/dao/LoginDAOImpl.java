package com.training.pms.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.training.pms.model.Customer;
import com.training.pms.model.Employee;
import com.training.pms.model.Login;
import com.training.pms.utility.DBConnection;

public class LoginDAOImpl implements LoginDAO {
	
	Connection con = DBConnection.getConnection();

	@Override
	public boolean register(Login login, Customer customer, Employee employee, String accounttype, int balance) {
		System.out.println("Adding user : " + login);
		PreparedStatement statement = null;
		int rows = 0;

		try {
			if(accounttype.equalsIgnoreCase("C")) {
			statement = con.prepareStatement("insert into customers values(default,?,?,?,?,default)");
			statement.setString(1, "bobby");
			statement.setString(2, login.getUsername());
			statement.setString(3, login.getPassword());
			statement.setInt(4, balance);
			rows = statement.executeUpdate();
			System.out.println(rows + " customer added to database");
			
			statement = null;
			rows = 0;
			statement = con.prepareStatement("insert into login values(default,?,?)");
			statement.setString(1, login.getUsername());
			statement.setString(2, login.getPassword());
			rows = statement.executeUpdate();
			System.out.println(rows + " user registered successfully");
			} else {
				statement = con.prepareStatement("insert into employees values(default,?,?,?,?,default)");
				statement.setString(1, "bobby");
				statement.setString(2, login.getUsername());
				statement.setString(3, login.getPassword());
				statement.setInt(4, balance);
				rows = statement.executeUpdate();
				System.out.println(rows + " employee added to database");
				
				statement = null;
				rows = 0;
				statement = con.prepareStatement("insert into login values(default,?,?)");
				statement.setString(1, login.getUsername());
				statement.setString(2, login.getPassword());
				rows = statement.executeUpdate();
				System.out.println(rows + " user registered successfully");
			}

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
		PreparedStatement stat;

		try {
			stat = con.prepareStatement("select * from login where username = ? and password = ? ");
			stat.setString(1, username);
			stat.setString(2, password);

			ResultSet res = stat.executeQuery();

			while (res.next()) {
				Login login = new Login();
				login.setUsername(res.getString(1));
				login.setPassword(res.getString(2));
				logins.add(login);
				
				System.out.println("USER ID : "+login.getUserId());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//return products;
		
		if(logins.size() == 0) {
			return false;
		}
		return true;
	}
	
	public boolean isLoginExists(String username) {
		boolean loginExists = false;
		PreparedStatement stat;

		try {
			stat = con.prepareStatement("select * from login where username = ? ");
			stat.setString(1, username);

			ResultSet res = stat.executeQuery();
			loginExists = res.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return loginExists;
	}
	
	@Override
	public boolean withdrawalFromAccount(String username, int amount) {
		CallableStatement stat;
		try {
			stat = con.prepareCall("call withdrawal(?,?)");
			System.out.println(username);
			stat.setString(1, username);
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
	
	@Override
	public boolean transferFromAccount(String username, String receiver, int amount) {
		CallableStatement stat;
		try {
			stat = con.prepareCall("call transfer(?,?,?)");
			System.out.println("sending->"+username+" : receiving->"+receiver);
			stat.setString(1, username);
			stat.setString(2, receiver);
			stat.setInt(3, amount);

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
