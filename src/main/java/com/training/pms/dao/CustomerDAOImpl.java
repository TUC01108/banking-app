package com.training.pms.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.training.pms.model.Customer;
import com.training.pms.utility.DBConnection;

public class CustomerDAOImpl implements CustomerDAO {

	Connection con = DBConnection.getConnection();

	public boolean addUser(Customer user) {
		System.out.println("Adding account : " + user);
		PreparedStatement statement = null;

		try {
			statement = con.prepareStatement("insert into users values(?,?,?,?,?)");

			statement.setInt(1, user.getUserId());
			statement.setString(2, user.getFirstname());
			statement.setString(3, user.getUsername());
			statement.setString(4, user.getPassword());
			statement.setString(5, user.getAccounttype());

			int rows = statement.executeUpdate();
			System.out.println(rows + " added successfully");
			
			statement.close();
			con.close();

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
	/*
	@Override
	public boolean withdrawalFromAccount(Login login, int amount) {
		CallableStatement stat;
		try {
			stat = con.prepareCall("call withdrawal(?,?)");
			System.out.println(login.getUsername());
			stat.setString(1, login.getUsername());
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
	*/

	@Override
	public Customer getValues(String username, String password) {
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
	public boolean transferFromAccount(String sender, String receiver, long amount) {
		CallableStatement stat;
		try {
			stat = con.prepareCall("call transfer(?,?,?)");
			System.out.println("sending->"+sender+" : receiving->"+receiver);
			stat.setString(1, sender);
			stat.setString(2, receiver);
			stat.setLong(3, amount);

			stat.execute();
			
			stat.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		System.out.println("Transfer done/completed");
		
		return true;
	}
	
	@Override
	public boolean addTransaction(Customer customer, long amount) {
		PreparedStatement statement = null;
		int rows = 0;

		try {
			statement = con.prepareStatement("insert into transactions values(?,?,?,default)");
			statement.setString(1, customer.getUsername());
			statement.setString(2, "null");
			statement.setLong(3, amount);
			rows = statement.executeUpdate();
			System.out.println(rows + " transaction added to database");
			
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
		PreparedStatement statement = null;
		int rows = 0;

		try {
			statement = con.prepareStatement("insert into transactions values(?,?,?,default)");
			statement.setString(1, customer.getUsername());
			statement.setString(2, receiver);
			statement.setLong(3, amount);
			rows = statement.executeUpdate();
			System.out.println(rows + " transaction added to database");
			
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

}
