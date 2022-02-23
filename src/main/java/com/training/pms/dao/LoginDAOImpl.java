package com.training.pms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.training.pms.model.Login;
import com.training.pms.utility.DBConnection;

public class LoginDAOImpl implements LoginDAO {
	
	Connection con = DBConnection.getConnection();

	@Override
	public boolean register(Login login) {
		System.out.println("Adding user : " + login);
		PreparedStatement statement = null;
		int rows = 0;

		try {
			statement = con.prepareStatement("insert into login values(default,?,?)");
			
			statement.setString(1, login.getUsername());
			statement.setString(2, login.getPassword());
			

			rows = statement.executeUpdate();
			System.out.println(rows + " created successfully");

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

}
