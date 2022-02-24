package com.training.pms;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.training.pms.dao.LoginDAO;
import com.training.pms.dao.LoginDAOImpl;
import com.training.pms.dao.UserDAO;
import com.training.pms.dao.UserDAOImpl;
import com.training.pms.model.Login;

import com.training.pms.model.User;

public class BankApp {
	Scanner scanner = new Scanner(System.in);
	int choice = 0;
	UserDAO userDAO = new UserDAOImpl();
	User user = new User();
	LoginDAO loginDAO = new LoginDAOImpl();
	Login login = new Login();

	public void startBankApp() {

		// declaring local variables for input
		int userId = 0;
		String accountName = null;
		String username = null;
		String password = null;
		int balance = 0;
		String accounttype = null;
		List<Login> logins = new ArrayList<Login>();

		while (true) {
			System.out.println("=========================================");
			System.out.println("B A N K I N G      -    APP    MENU");
			System.out.println("1. Login to Account ");
			System.out.println("2. Create Account ");
			System.out.println("3. Know More About Banking App ");
			System.out.println("4. Know About Developer - Thomas");
			System.out.println("9. E X I T ");
			System.out.println("=========================================");
			System.out.println("Please enter your choice : ");
			choice = scanner.nextInt();

			/*
			 * 
			 * System.out.println("=========================================");
			 * System.out.println("B A N K I N G      -    APP    MENU");
			 * System.out.println("1. Login to Account ");
			 * System.out.println("2. Create Account ");
			 * System.out.println("3. Make Withdrawal from Account ");
			 * System.out.println("4. Make Deposit to Account ");
			 * System.out.println("5. Search Account By Name ");
			 * System.out.println("6. Print All Accounts ");
			 * System.out.println("7. Search Accounts by Account Balance(lower/upper");
			 * System.out.println("9. E X I T ");
			 * System.out.println("=========================================");
			 * System.out.println("Please enter your choice : "); choice =
			 * scanner.nextInt();
			 */

			switch (choice) {
			case 1:
				// login to account section
				System.out.println("WELCOME TO ACCOUNT LOGIN SECTION");
				// take input from user to create an account
				boolean notValid = true;
				String type;
				/*
				 * do { System.out.
				 * println("Please enter type of login (C - Customer /E - Employee) :"); type =
				 * scanner.next();
				 * 
				 * if(type.equalsIgnoreCase("C") || type.equalsIgnoreCase("E") ) { notValid =
				 * false; } } while(notValid);
				 * System.out.println("Selected valid account type");
				 * 
				 * System.out.println("Please enter your customer id : ");
				 */
				System.out.println("Please enter username: ");
				username = scanner.next();
				System.out.println("Please enter password: ");
				password = scanner.next();
				boolean isValidLogin = loginDAO.validate(username, password);

				if (!isValidLogin) {
					System.out.println("Incorrect username or password. Try again");
					continue;
				}

				System.out.println("Welcome, " + username);

				break;

			case 2:
				// create account section
				System.out.println("WELCOME TO CREATE ACCOUNT SECTION ");
				// take input from user to create an account
				System.out.println("Please enter username :");
				username = scanner.next();
				
				if(loginDAO.isLoginExists(username)) {
					System.out.println("User with username : - "+username+" - already exists, please try with another username");
					continue;
				}
				
				System.out.println("Please enter password :");
				password = scanner.next();
				
				login = new Login(userId, username, password);
				
				boolean isValidRegister = loginDAO.register(login);
				
				if (!isValidRegister) {
					System.out.println("Username already exists, please try another.");
					continue;
				}
				
				System.out.println("Congrats, - " + username+" - you are registered");
				
				/*
				System.out.println("Please enter user id :");
				userId = scanner.nextInt();

				if (userDAO.isUserExists(userId)) {
					System.out.println("Product with product id : " + userId
							+ " already exists, please try with another product id");
					continue;
				}

				System.out.println("Please enter account name :");
				accountName = scanner.next();

				System.out.println("Please enter username :");
				username = scanner.next();

				System.out.println("Please enter account balance :");
				balance = scanner.nextInt();

				System.out.println("Please enter account password :");
				password = scanner.next();

				boolean isTrue = true;
				while (isTrue) {
					System.out.println("Please enter account type (C - Customer /E - Employee) :");
					accounttype = scanner.next();
					accounttype = accounttype.toUpperCase();
					if (accounttype.equals("E") || accounttype.equals("C"))
						isTrue = false;
				}

				user = new User(userId, accountName, username, password, balance, accounttype);

				// call dao layer to save product
				userDAO.addUser(user);
				System.out.println("\nCongratulations, " + accountName + " your account :  saved successfully\n");
				*/
				break;
			/*
			 * case 3:
			 * 
			 * // make withdrawal section
			 * System.out.println("WELCOME TO MAKE WITHDRAWAL SECTION ");
			 * 
			 * 
			 * break;
			 * 
			 * case 4:
			 * 
			 * // make deposit section
			 * System.out.println("WELCOME TO MAKE DEPOSIT SECTION ");
			 * 
			 * case 5: System.out.println("Please enter Account name to search : ");
			 * accountName = scanner.next(); bankDAO.searchByAccountName(accountName);
			 * break;
			 * 
			 * 
			 * 
			 * case 7: System.out.println("Please enter account balance (lower) :"); int
			 * lowerAmount = scanner.nextInt();
			 * System.out.println("Please enter account balance (upper) :"); int upperAmount
			 * = scanner.nextInt();
			 * bankDAO.searchAccountsByBalance(lowerAmount,upperAmount); break;
			 */
			case 6:
				userDAO.printAllUsers();
				break;

			case 9:
				System.out.println("Thanks for using my bank app!");
				System.exit(0);

			default:
				System.out.println("Invalid choice , Please enter (1-4) or 9 to EXIT");
				break;
			}
		}
	}
}
