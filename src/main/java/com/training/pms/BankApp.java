package com.training.pms;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.training.pms.dao.CustomerDAO;
import com.training.pms.dao.CustomerDAOImpl;
import com.training.pms.dao.EmployeeDAO;
import com.training.pms.dao.EmployeeDAOImpl;
import com.training.pms.dao.LoginDAO;
import com.training.pms.dao.LoginDAOImpl;
import com.training.pms.model.Customer;
import com.training.pms.model.Employee;
import com.training.pms.model.Login;
import com.training.pms.model.Transactions;

public class BankApp {
	Scanner scanner = new Scanner(System.in);
	int choice = 0;
	CustomerDAO customerDAO = new CustomerDAOImpl();
	Customer customer = new Customer();

	EmployeeDAO employeeDAO = new EmployeeDAOImpl();
	Employee employee = new Employee();

	LoginDAO loginDAO = new LoginDAOImpl();
	Login login = new Login();

	public void startBankApp() {

		// declaring local variables for input
		int userId = 0;
		String username = null;
		String password = null;
		String accounttype = null;
		String firstname = null;
		String receiver = null;
		long balance = 0;
		long amount = 0;
		boolean notValid = true;
		boolean isValidTransfer = true;
		boolean isValidAdd = true;
		List<Customer> customers = new ArrayList<Customer>();
		List<Transactions> transaction = new ArrayList<Transactions>();
		
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

			switch (choice) {
			case 1:
				// login to account section
				System.out.println("WELCOME TO ACCOUNT LOGIN SECTION");
				notValid = true;
				
				// Confirm account type is of type customer or employee
				do {
					System.out.println("Please enter type of login (C - Customer /E - Employee) :");
					accounttype = scanner.next();
					accounttype = accounttype.toUpperCase();
					if (accounttype.equals("C") || accounttype.equals("E")) {
						notValid = false;
					}
				} while (notValid);
				//System.out.println("Selected valid account type");
				System.out.println("=============================\n");
				
				// take input from user to create an account
				System.out.println("Please enter username: ");
				username = scanner.next();
				System.out.println("Please enter password: ");
				password = scanner.next();
				boolean isValidLogin = loginDAO.validate(username, password);
				customer = customerDAO.getValues(username, password);
				
				if (!isValidLogin) {
					if(customer.getStatus() != null && customer.getStatus().equals("N"))
						System.out.println("Account has not yet been approved. Administrator will update in a timely manner.");
					else
						System.out.println("Incorrect username or password. Try again");
					
					accounttype = null;
					continue;
				}

				
				employee = employeeDAO.getValues(username, password);

				System.out.println("Welcome, " + username);

				// USER IS LOGGED IN

				// Divided into CUSTOMER and EMPLOYEE FUNCTIONALITY
				
				// EMPLOYEE MENU
				if (accounttype.equalsIgnoreCase("E")) {

					while (true) {
						System.out.println("=========================================");
						System.out.println(
								employee.getFirstname().toUpperCase() + "\'S B A N K I N G      -    APP    MENU");
						System.out.println("1. Approve or Reject Accounts ");
						System.out.println("2. View Customers Bank Account By Name ");
						System.out.println("3. View Log of All Transactions ");
						System.out.println("4. Delete a User ");
						System.out.println("9. E X I T ");
						System.out.println("=========================================");
						System.out.println("Please enter your choice : ");
						choice = scanner.nextInt();

						
						switch (choice) {
						case 1:
							// Approve or reject accounts section
							System.out.println("APPROVE OR REJECT ACCOUNTS SECTION");
							//employeeDAO.printAllUsers();
							customers = employeeDAO.getAllApplications();
							userId = 0;
							
							if (customers.size()==0) {
								System.out.println("No applications to approve or reject");
								continue;
							} else {
								System.out.println("You have "+customers.size()+" account(s) to approve or reject.\n");							
								printApplicationDetails(customers);
								System.out.println();
								
								System.out.println("If you'd like to approve an application type 1 if you'd like to decline then type 2");
								choice = scanner.nextInt();
								if(choice == 1) {
									System.out.println("\nPlease enter userid to approve :");
									userId = scanner.nextInt();}
								else if (choice == 2) {
									System.out.println("\nPlease enter a userid to delete :");
									userId = scanner.nextInt();
								} else {
									System.out.println("You entered an incorrect option.");
								}
								
								if(employeeDAO.isApplyExists(userId) && choice == 1)
								{
									customerDAO.getValues(username, password);
									employeeDAO.approveApply(userId);
									System.out.println("Application with user id : "+userId+ " approved successfully");
								}
								else if(employeeDAO.isApplyExists(userId) && choice == 2)
								{
									employeeDAO.rejectApply(userId);
									System.out.println("Application with user id : "+userId+ " deleted successfully");
								}
								else
								{
									System.out.println("Application with user id : "+userId+ " does not exist, hence cannot be approved/deleted");

								}
							}
							
							
							break;
						case 2:
							// View customers accounts section
							System.out.println("VIEW CUSTOMERS ACCOUNTS SECTION");
							System.out.println("Please enter username to search : ");
							username = scanner.next();
							
							customers = employeeDAO.searchByUsername(username);
							if (customers.size()==0) {
								System.out.println("No username matching your criteria");
								continue;
							}
							printUsersDetails(customers);

							break;
						case 3:
							// View log of all transactions section
							System.out.println("VIEW LOG OF ALL TRANSACTIONS SECTION");
							transaction = employeeDAO.getTransactions();
							if (transaction.size()==0) {
								System.out.println("No transactions");
								continue;
							}
							
							printTransactionDetails(transaction);
							break;
						case 4:
							//Delete user section
							System.out.println("DELETE A USER SECTION");
							employeeDAO.printAllUsers();
							System.out.println("\nPlease enter username to delete :");
							username = scanner.next();
							
							if(loginDAO.isLoginExists(username))
							{
								employeeDAO.deleteUser(username);
								System.out.println("Product with product id : "+username+ " deleted successfully");
							}
							else
							{
								System.out.println("Product with product id : "+username+ " does not exists, hence cannot be deleted");

							}

						case 9:
							System.out.println("Thanks for using my bank app!");
							System.exit(0);
							break;
						default:
							System.out.println("Invalid choice , Please enter (1-3) or 9 to EXIT");
							break;
						}

					}
				}
				
				// CUSTOMER MENU
				else if (accounttype.equals("C") && customer.getFirstname() != null) {

					while (true) {
						System.out.println("=========================================");
						System.out.println(
								customer.getFirstname().toUpperCase() + "\'S B A N K I N G      -    APP    MENU");
						System.out.println("1. Make Withdraw from Personal Account ");
						System.out.println("2. Make Deposit to Personal Account ");
						System.out.println("3. Deposit to Another Account ");
						System.out.println("4. Apply for an Account");
						System.out.println("5. View Balance");
						// System.out.println("4. Search Account By Name ");
						// System.out.println("5. Search Accounts by Account Balance(lower/upper");
						System.out.println("9. E X I T ");
						System.out.println("=========================================");
						System.out.println("Please enter your choice : ");
						choice = scanner.nextInt();

						switch (choice) {
						case 1:
							// Withdraw from account section
							System.out.println("WELCOME TO WITHDRAW FROM ACCOUNT SECTION");
							notValid = true;
							while (notValid) {
								System.out.println("Enter the amount to be withdrawn :");
								amount = scanner.nextLong();
								// System.out.println("amount"+amount);

								if (amount <= customer.getBalance() && amount >= 0) {

									notValid = false;
								} else if(amount < 0)
									System.out.println("Amount cannot not be negative.");
								else
									System.out.println("Cannot withdraw more than current balance");
							}
							// System.out.println("Username is : " + username);
							// System.out.println("UserId is : " + userId);
							boolean isValidWithdraw = customerDAO.withdrawFromAccount(username, amount);
							
							isValidAdd = customerDAO.addTransaction(customer, -(amount));

							if (!isValidWithdraw) {
								System.out.println("Withdraw was unsuccessful. Try again");
								continue;
							}

							System.out.println("Account was credited : " + amount);
							customer = customerDAO.getValues(username, password);
							break;
							
						case 2:
							// DEPOSIT TO account section
							System.out.println("WELCOME TO DEPOSIT TO ACCOUNT SECTION");
							
							notValid = true;
							while (notValid) {
								System.out.println("Enter the amount to be deposited :");
								amount = scanner.nextLong();
								// System.out.println("amount"+amount);

								if (amount < 0)
									System.out.println("Amount to deposit cannot not be negative.");
								else 
									notValid = false;
							}
							
							boolean isValidDeposit = customerDAO.depositIntoAccount(username, amount);
							
							isValidAdd = customerDAO.addTransaction(customer, amount);

							if (!isValidDeposit) {
								System.out.println("Deposit was unsuccessful. Try again");
								continue;
							}

							System.out.println("Account was debited : " + amount);
							customer = customerDAO.getValues(username, password);
							break;

						case 3:
							// DEPOSIT TO ANOTHER account section
							System.out.println("WELCOME TO DEPOSIT TO ANOTHER ACCOUNT SECTION");
							if (customer.getBalance() == 0) {
								System.out.println("You don't have enough money to make a transfer.\n");
								continue;
							}
							amount = 0;

							notValid = true;
							while (notValid) {
								System.out.println("Current customer balance : " + customer.getBalance());
								System.out.println("Enter the amount to be transfered :");
								amount = scanner.nextLong();

								if (amount <= customer.getBalance() && amount > 0) {

									notValid = false;
								} else if (amount <= 0) {
									System.out.println("Cannot transfer amount less than 1");
								} else
									System.out.println("Cannot transfer amount that is more than current balance");
							}
							int count = 0;
							System.out.println("Sender is : " + username);
							do {
								if(count>0) {
									System.out.println("Username is invalid, please try another.");
									System.out.println("Type exit to quit.");
								}
							System.out.println("Enter username you would like to transfer to : ");
							receiver = scanner.next();
								if(receiver.equals("exit")) {
									System.out.println("Thanks for using my bank app!");
									System.exit(0);
								}
							count++;
							} while (!customerDAO.isUserExists(receiver));
							long receiverBalance = 0;
							isValidTransfer = customerDAO.transferFromAccount(username, receiver, amount, balance, receiverBalance);
							
							isValidAdd = customerDAO.addTransaction(customer, receiver, amount);

							if (!isValidTransfer || !isValidAdd) {
								System.out.println("Transfer was unsuccessful. Try again");
								continue;
							}

							System.out.println("Amount transfered : " + amount);
							System.out.println();
							
							customer = customerDAO.getValues(username, password);
							break;
							
						case 4:
							// Apply for account section
							System.out.println("WELCOME TO APPLY FOR ACCOUNT SECTION");
							boolean validChoice = false;
							String accountName = null;
							
							System.out.println("Please choose what type of account you'd like to apply for :");
							while (!validChoice) {
								System.out.println("1. Checking ");
								System.out.println("2. Savings ");
								System.out.println("3. Credit ");
								System.out.println("9. E X I T ");
								System.out.println("=========================================");
								System.out.println("Please enter your choice : ");
								choice = scanner.nextInt();
								
								
								switch (choice) {
								case 1:
									System.out.println("Let's get your checking account approved.");
									accountName = "Checking";
									validChoice = true;
									break;
								case 2:
									System.out.println("Let's get your savings account approved.");
									accountName = "Savings";
									validChoice = true;
									break;
								case 3:
									System.out.println("Let's get your credit account approved.");
									accountName = "Credit";
									validChoice = true;
									break;
								case 9:
									System.out.println("Thanks for using my Bank app!");
									System.exit(0);
									break;
								default:
									System.out.println("Please enter a valid choice");
									break;
								}
							}
							

							System.out.println("Please enter password to confirm choice :");
							password = scanner.next();

							
							System.out.println("Please enter starting balance :");
							balance = scanner.nextLong();
							

							//login = new Login(userId, username, password);

							boolean isValidApply = loginDAO.apply(customer, balance, accountName);

							if (!isValidApply) {
								System.out.println("The application process did not go through, please try again.");
								continue;
							}

							System.out.println("Congrats, - " + username + " - you have applied for an account");

							break;

						case 5:
							// View Balance Section
							System.out.println("WELCOME TO VIEW BALANCE SECTION");
							
							System.out.println("\nCurrent customer balance : " + customer.getBalance()+"\n");

							break;
							
						case 6:
							// SEARCH ACCOUNT BY NAME section
							System.out.println("WELCOME TO SEARCH ACCOUNT BY NAME SECTION");

							break;

						case 7:
							// SEARCH ACCOUNT BY ACCOUNT BALANCE section
							System.out.println("WELCOME TO SEARCH ACCOUNTS BY BALANCE SECTION");

							break;
						case 9:
							System.out.println("Thanks for using my bank app!");
							System.exit(0);
							break;
						default:
							System.out.println("Invalid choice , Please enter (1-4) or 9 to EXIT");
							break;
						}
					}
				} else {
					System.out.println("NOT A VALID ACCOUNT TYPE");
					System.out.println("Please try again.");
					break;
					//System.out.println("Thanks for using my bank app!");
					//System.exit(0);
				}
			case 2:
				// create account section
				System.out.println("WELCOME TO CREATE ACCOUNT SECTION ");

				// Confirm account type is of type customer or employee
				do {
					System.out.println("Please enter type of login (C - Customer /E - Employee) :");
					accounttype = scanner.next();
					accounttype = accounttype.toUpperCase();

					if (accounttype.equals("C") || accounttype.equals("E")) {
						notValid = false;
					}
				} while (notValid);

				// take input from user to create an account
				System.out.println("Please enter username :");
				username = scanner.next();

				if (loginDAO.isLoginExists(username)) {
					System.out.println("User with username : - " + username
							+ " - already exists, please try with another username");
					continue;
				}

				System.out.println("Please enter password :");
				password = scanner.next();

				System.out.println("Please enter first name :");
				firstname = scanner.next();

				if (accounttype.equalsIgnoreCase("C")) {
					System.out.println("Please enter starting balance :");
					balance = scanner.nextLong();
				}

				login = new Login(userId, username, password);
				
				// Can I create a Customer object without userId to pass object instead of variables?
				
				//customer = new Customer();
				boolean isValidRegister = loginDAO.register(username, password, accounttype, balance, firstname);

				if (!isValidRegister) {
					System.out.println("Username already exists, please try another.");
					continue;
				}
				if(accounttype.equals("C")) {
					System.out.println("Congrats, - " + username + " - your account got opened and is pending approval.");
				} else
					System.out.println("Congrats, - " + username + " - your account got opened.");

				break;
			
			 case 3:
			  
			 // make withdraw section
			 System.out.println("WELCOME TO KNOW MORE ABOUT BANKING APP SECTION ");
			 System.out.println("TO DO: Fill in with information about how it was built.");
			 System.out.println();
			 System.out.println();
			 System.out.println();
			 System.out.println();
			 System.out.println();
			 System.out.println();
			 break;
			  
			 case 4:
			  
			 // make deposit section
			 System.out.println("WELCOME TO Know About Developer SECTION ");
			 System.out.println("Go to GITHUB HERE -> https://github.com/TUC01108");
			 System.out.println();
			 System.out.println();
			 
			 break;
			 
			case 6:
				//customerDAO.printAllUsers();
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
	
	public void printTransactionDetails(List<Transactions> transaction) {
		Iterator<Transactions> iterator = transaction.iterator();
		while(iterator.hasNext()) {
			Transactions temp = iterator.next();
			System.out.println(temp);
		}
		
	}
	
	public void printUsersDetails(List<Customer> customers) {
		Iterator<Customer> iterator = customers.iterator();
		while(iterator.hasNext()) {
			Customer temp = iterator.next();
			System.out.println(temp);
		}
		
	}
	
	public void printApplicationDetails(List<Customer> customers) {
		Iterator<Customer> iterator = customers.iterator();
		while(iterator.hasNext()) {
			Customer temp = iterator.next();
			System.out.println(temp);
		}
	}
}
