package com.training.pms.dao;

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
	public boolean register(Login login, Customer customer, Employee employee, String accounttype, long balance, String firstname) {
		System.out.println("Adding user : " + firstname);
		PreparedStatement statement = null;
		int rows = 0;

		try {
			if(accounttype.equalsIgnoreCase("C")) {
			statement = con.prepareStatement("insert into customers values(default,?,?,?,?,default)");
			statement.setString(1, firstname);
			statement.setString(2, login.getUsername());
			statement.setString(3, login.getPassword());
			statement.setLong(4, balance);
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
				statement = con.prepareStatement("insert into employees values(default,?,?,?,default)");
				statement.setString(1, firstname);
				statement.setString(2, login.getUsername());
				statement.setString(3, login.getPassword());
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
	public boolean validate(String username, String password) {
		
		System.out.println("Searching for user with username : " + username);
		List<Login> logins = new ArrayList<Login>();
		Login login = new Login();
		PreparedStatement stat;

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
				
				//System.out.println("USER ID for login object : "+login.getUserId());
			}
			
			res.close();
			stat.close();
			con.close();
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

	

}
