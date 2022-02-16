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
		int accountId = 0;
		String accountName = null;
		int balance = 0;
		String password = null;

		while (true) {

			System.out.println("B A N K I N G      -    APP    MENU");
			System.out.println("1. Login to Account ");
			System.out.println("2. Create Account ");
			System.out.println("3. Make Withdrawal from Account ");
			System.out.println("4. Make Deposit to Account ");
			System.out.println("5. Search Product By Name ");
			System.out.println("6. Print All Products ");
			System.out.println("9. E X I T ");

			System.out.println("Please enter your choice : ");
			choice = scanner.nextInt();

			switch (choice) {
			case 1:
				// login to account section
				System.out.println("WELCOME TO ACCOUNT LOGIN SECTION");
				// take input from user to create an account
				System.out.println("Please enter account id :");
				accountId = scanner.nextInt();
				
				break; 
				
			case 2:
				// create account section
				System.out.println("WELCOME TO ADD ACCOUNT SECTION ");

				// take input from user to create an account
				System.out.println("Please enter account id :");
				accountId = scanner.nextInt();

				System.out.println("Please enter account name :");
				accountName = scanner.next();

				System.out.println("Please enter account balance :");
				balance = scanner.nextInt();

				System.out.println("Please enter account password :");
				password = scanner.next();

				bank = new Bank(accountId, accountName, balance, password);
				
				// call dao layer to save product
				bankDAO.addBankAccount(bank);
				System.out.println("\nCongratulations, your account : " + accountName + " saved successfully\n");
				break;
				
			case 3:

				// make withdrawal section
				System.out.println("WELCOME TO MAKE WITHDRAWAL SECTION ");

				
				break;
			
			case 4:

				// make deposit section
				System.out.println("WELCOME TO MAKE DEPOSIT SECTION ");

				
				break;

			case 9:
				System.out.println("Thanks for using my bank app!");
				System.exit(0);

			default:
				System.out.println("Invalid choice , Please enter (1-6) or 9 to EXIT");
				break;
			}
		}
	}
}
