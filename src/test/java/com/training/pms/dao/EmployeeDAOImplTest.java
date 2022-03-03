package com.training.pms.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.training.pms.model.Employee;
import com.training.pms.model.Login;

class EmployeeDAOImplTest {
	
	EmployeeDAO employeeDAO;
	Employee employee;
	LoginDAO loginDAO;
	Login login;
	
	int userId;
	String username;
	String password;
	String accounttype;
	long balance;
	String firstname;
	String status;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		employeeDAO = new EmployeeDAOImpl();
		userId = 9999;
		username = "customer9999";
		employee = new Employee();
		
		loginDAO = new LoginDAOImpl();
		firstname = "john";
		password = "password";
		accounttype = "C";
		balance = 10000000;
		status = "N";
		login = new Login();
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	@Test
	void testCreateUser() {
		assertTrue(() -> loginDAO.register(username, password, accounttype, balance, firstname));
	}
	

	@Test
	void testDeleteUser() {
		assertTrue(() -> employeeDAO.deleteUser(username));

	}
	
	@Test
	public void testUserExistsUniqueUserId() {
		assertFalse(() -> employeeDAO.isUserExists(9999));
	}

}
