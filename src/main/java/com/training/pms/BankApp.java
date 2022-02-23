package com.training.pms;

import java.util.Scanner;

import com.training.pms.dao.BankDAO;
import com.training.pms.dao.BankDAOImpl;
import com.training.pms.model.Bank;

public class BankApp {
	Scanner scanner = new Scanner(System.in);
	int choice = 0;
	BankDAO bankDAO = new BankDAOImpl();
	Bank bank = new Bank();

	public void startBankApp() {

		// declaring local variables for input
		int userId = 0;
		String accountName = null;
		String username = null;
		String password = null;
		int balance = 0;
		String accounttype = null;
		
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
		 
		 System.out.println("=========================================");
			System.out.println("B A N K I N G      -    APP    MENU");
			System.out.println("1. Login to Account ");
			System.out.println("2. Create Account ");
			System.out.println("3. Make Withdrawal from Account ");
			System.out.println("4. Make Deposit to Account ");
			System.out.println("5. Search Account By Name ");
			System.out.println("6. Print All Accounts ");
			System.out.println("7. Search Accounts by Account Balance(lower/upper");
			System.out.println("9. E X I T ");
			System.out.println("=========================================");
			System.out.println("Please enter your choice : ");
			choice = scanner.nextInt();
		 */
			
			switch (choice) {
			case 1:
				// login to account section
				System.out.println("WELCOME TO ACCOUNT LOGIN SECTION");
				// take input from user to create an account
				boolean notValid = true;
				String type;
				
				do {
					System.out.println("Please enter type of login (C - Customer /E - Employee) :");
					type = scanner.next();
					
					if(type.equalsIgnoreCase("C") || type.equalsIgnoreCase("E") ) {
						notValid = false;
					}
					} while(notValid);
				System.out.println("Selected valid account type");
				System.out.println("Please enter your customer id : ");
				//int customerId = scanner.nextInt();
				
				//System.out.println("Please enter product name to search : ");
				userId = scanner.nextInt();
				bankDAO.searchByUserId(userId);
				break;
				
				
			case 2:
				// create account section
				System.out.println("WELCOME TO CREATE ACCOUNT SECTION ");

				// take input from user to create an account
				System.out.println("Please enter user id :");
				userId = scanner.nextInt();

				System.out.println("Please enter account name :");
				accountName = scanner.next();
				
				System.out.println("Please enter username :");
				username = scanner.next();

				System.out.println("Please enter account balance :");
				balance = scanner.nextInt();

				System.out.println("Please enter account password :");
				password = scanner.next();
				
				
				boolean isTrue = true;
				while(isTrue) {
					System.out.println("Please enter account type (C - Customer /E - Employee) :");
					accounttype = scanner.next();
					accounttype = accounttype.toUpperCase();
				if(accounttype.equals("E") || accounttype.equals("C"))
					isTrue = false;
				}

				bank = new Bank(userId, accountName, username, password, balance, accounttype);
				
				// call dao layer to save product
				bankDAO.addAccount(bank);
				System.out.println("\nCongratulations, " + accountName +" your account :  saved successfully\n");
				break;
			/*	
			case 3:

				// make withdrawal section
				System.out.println("WELCOME TO MAKE WITHDRAWAL SECTION ");

				
				break;
			
			case 4:

				// make deposit section
				System.out.println("WELCOME TO MAKE DEPOSIT SECTION ");
				
			case 5:
				System.out.println("Please enter Account name to search : ");
				accountName = scanner.next();
				bankDAO.searchByAccountName(accountName);
				break;
				
			
				
			case 7:
				System.out.println("Please enter account balance (lower) :");
				int lowerAmount = scanner.nextInt();
				System.out.println("Please enter account balance (upper) :");
				int upperAmount = scanner.nextInt();
				bankDAO.searchAccountsByBalance(lowerAmount,upperAmount);
				break;
			*/
			case 6:
				bankDAO.printAllAccounts();
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
