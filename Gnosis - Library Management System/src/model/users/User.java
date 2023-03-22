package model.users;

import java.util.ArrayList;
import java.util.Scanner;

import builder.BorrowedBookBuilder;
import databaseproxy.BorrowedBookProxy;
import model.books.Book;
import model.books.BorrowedBook;

public abstract class User {

	protected Scanner sc = new Scanner(System.in);
	protected String username, password;
	protected ArrayList<BorrowedBook> borrowedBooks;
	
	public User() {
		this.borrowedBooks = new ArrayList<>();
	}
	
	public abstract int getMaxQty();
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername() {
		do {
			System.out.print("Insert username [Min 5 Characters]: ");
			this.username = sc.nextLine();
			if(this.username.length() < 5)
				System.out.println("Username must have at least 5 characters!");
		} while (this.username.length() < 5);
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword() {
		do {
			System.out.print("Insert password [Min 8 Characters]: ");
			this.password = sc.nextLine();
			if(this.password.length() < 8)
				System.out.println("Password must have at least 8 characters!");
		} while (this.password.length() == 0 || this.password.length() < 5);
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public ArrayList<BorrowedBook> getBorrowedBooks() {
		return borrowedBooks;
	}
	
	public void initBorrowedBooks() {
		new BorrowedBookProxy().getBorrowedBookList(this.username, this.borrowedBooks);
	}
	
	public void userInformation() {
		System.out.println("Username : " + this.getUsername());
	}
	
	public String promptUserType() {
		String type;
		do {
			System.out.print("Insert user type [Silver | Gold | Platinum]: ");
			type = sc.nextLine();
		} while (!type.equals("Silver") && !type.equals("Gold") && !type.equals("Platinum"));
		return type;
	}
}
