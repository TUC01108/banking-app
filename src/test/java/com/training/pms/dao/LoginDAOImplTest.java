package com.training.pms.dao;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import com.training.pms.model.Login;

@TestMethodOrder(value = MethodOrderer.OrderAnnotation.class)
class LoginDAOImplTest {
	
	LoginDAO loginDAO;
	Login login;
	
	EmployeeDAO employeeDAO;

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

	}

	@AfterEach
	void tearDown() throws Exception {
		//employeeDAO.deleteUser(username);
		
	}

	@Test
	@Order(value = 1)
	void testRegister1() {
		//login = new Login(userId, "customer10", "password");
		
		//assertTrue(loginDAO.register(username, password, accounttype, balance, firstname));
	}
	
	
	/*
	@Test
	@Order(value = 2)
	void testDeleteUser() {
		assertTrue(employeeDAO.deleteUser(username));

	}
	*/

	@Test
	void testValidate() {
		//fail("Not yet implemented");
	}

	@Test
	void testIsLoginExists() {
		//fail("Not yet implemented");
	}

	@Test
	void testApply() {
		//fail("Not yet implemented");
	}

}
