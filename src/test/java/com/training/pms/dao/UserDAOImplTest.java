package com.training.pms.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserDAOImplTest {
	
	CustomerDAO userDAO;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		userDAO = new CustomerDAOImpl();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@DisplayName("Testing user exists-1")
	void testIsUserExists() {
		int userId=1;
		boolean actual = userDAO.isUserExists(userId);
		assertTrue(actual);
	}
	@Test
	@DisplayName("Testing user exists-2")
	void testIsUserExists2() {
		int userId=2827;
		boolean actual = userDAO.isUserExists(userId);
		assertFalse(actual);
	}

	@Test
	@DisplayName("Testing user exists-3")
	void testIsUserExists4() {
		int userId=20827;
		boolean actual = userDAO.isUserExists(userId);
		assertFalse(actual);
	}

}
