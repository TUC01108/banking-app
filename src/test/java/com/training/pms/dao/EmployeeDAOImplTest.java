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
	
	int userId;
	String username;
	String password;
	String accounttype;
	long balance;
	String firstname;

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
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testDeleteUser() {
		assertTrue(employeeDAO.deleteUser(username));

	}

	@Test
	void testSearchByUsername() {
		fail("Not yet implemented");
	}

	@Test
	void testPrintAllUsers() {
		fail("Not yet implemented");
	}

	@Test
	void testIsUserExists() {
		fail("Not yet implemented");
	}

	@Test
	void testGetValues() {
		fail("Not yet implemented");
	}

	@Test
	void testGetTransactions() {
		fail("Not yet implemented");
	}

	@Test
	void testGetAllApplications() {
		fail("Not yet implemented");
	}

	@Test
	void testApproveApply() {
		fail("Not yet implemented");
	}

	@Test
	void testRejectApply() {
		fail("Not yet implemented");
	}

}
