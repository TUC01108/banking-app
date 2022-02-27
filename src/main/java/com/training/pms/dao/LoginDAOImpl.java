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
		PreparedStatement stat = null;
		con = DBConnection.getConnection();
		int rows = 0;

		try {
			if(accounttype.equalsIgnoreCase("C")) {
			stat = con.prepareStatement("insert into customers values(default,?,?,?,?,default)");
			stat.setString(1, firstname);
			stat.setString(2, login.getUsername());
			stat.setString(3, login.getPassword());
			stat.setLong(4, balance);
			rows = stat.executeUpdate();
			System.out.println(rows + " customer added to database");
			
			stat = null;
			rows = 0;
			stat = con.prepareStatement("insert into login values(default,?,?)");
			stat.setString(1, login.getUsername());
			stat.setString(2, login.getPassword());
			rows = stat.executeUpdate();
			System.out.println(rows + " user registered successfully");
			} else {
				stat = con.prepareStatement("insert into employees values(default,?,?,?,default)");
				stat.setString(1, firstname);
				stat.setString(2, login.getUsername());
				stat.setString(3, login.getPassword());
				rows = stat.executeUpdate();
				System.out.println(rows + " employee added to database");
				
				stat = null;
				rows = 0;
				stat = con.prepareStatement("insert into login values(default,?,?)");
				stat.setString(1, login.getUsername());
				stat.setString(2, login.getPassword());
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

	

}
