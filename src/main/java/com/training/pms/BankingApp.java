package com.training.pms;

import java.util.Scanner;

import com.training.pms.dao.ProductDAO;
import com.training.pms.dao.ProductDAOImpl;
import com.training.pms.model.Product;

public class BankingApp {
	Scanner scanner = new Scanner(System.in);
	int choice = 0;
	ProductDAO productDAO = new ProductDAOImpl();
	Product product = new Product();

	public void startProductApp() {

		// declaring local variables for input
		int productId = 0;
		String productName = null;
		int quantityOnHand = 0;
		int price = 0;

		while (true) {

			System.out.println("B A N K I N G      -    APP    MENU");
			System.out.println("1. Add Product ");
			System.out.println("2. Delete Product ");
			System.out.println("3. Update Product ");
			System.out.println("4. Search Product By Id ");
			System.out.println("5. Search Product By Name ");
			System.out.println("6. Print All Products ");
			System.out.println("9. E X I T ");

			System.out.println("Please enter your choice : ");
			choice = scanner.nextInt();

			switch (choice) {
			case 1:
				// add product section
				System.out.println("WELCOME TO ADD PRODUCT SECTION ");

				// take input from user to add a product
				System.out.println("Please enter product id :");
				productId = scanner.nextInt();

				System.out.println("Please enter product name :");
				productName = scanner.next();

				System.out.println("Please enter product qoh :");
				quantityOnHand = scanner.nextInt();

				System.out.println("Please enter product price :");
				price = scanner.nextInt();

				product = new Product(productId, productName, quantityOnHand, price);
				
				// call dao layer to save product
				productDAO.addProduct(product);
				System.out.println("\nCongratulations, your product : " + productName + " saved successfully\n");
				break;
				
			case 3:

				// add product section
				System.out.println("WELCOME TO UPDATE PRODUCT SECTION ");

				// take input from user to update a product
				System.out.println("Please enter a product id to update :");
				productId = scanner.nextInt();

				System.out.println("Please enter a new product name :");
				productName = scanner.next();

				System.out.println("Please enter a new product qoh :");
				quantityOnHand = scanner.nextInt();

				System.out.println("Please enter a new product price :");
				price = scanner.nextInt();

				product = new Product(productId, productName, quantityOnHand, price);
				// call dao layer to save product
				productDAO.updateProduct(product);
				System.out.println("\nCongratulations, your product : " + productName + " updated successfully\n");
				break;

			case 9:
				System.out.println("Thanks for using my product app!");
				System.exit(0);

			default:
				System.out.println("Invalid choice , Please enter (1-6) or 9 to EXIT");
				break;
			}
		}
	}
}
