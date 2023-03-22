package model.books;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import databaseproxy.BookProxy;
import singleton.Library;

public class Book {

	private Scanner sc = new Scanner(System.in);
	private String title, author, synopsis, publisher, id;
	private int qty, availableQty;
	
	public Book() {}

	public int getAvailableQty() {
		return availableQty;
	}

	public void setAvailableQty(int availableQty) {
		this.availableQty = availableQty;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle() {
		do {
			System.out.print("Insert book title: ");
			this.title = sc.nextLine();
			emptyStringError(this.title, "Title");
		} while (this.title.length() == 0);
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	
	public String getAuthor() {
		return author;
	}

	public void setAuthor() {
		do {
			System.out.print("Insert book author: ");
			this.author = sc.nextLine();
			emptyStringError(this.author, "Author");
		} while (this.author.length() == 0);
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis() {
		do {
			System.out.print("Insert book synopsis: ");
			this.synopsis = sc.nextLine();
			emptyStringError(this.synopsis, "Synopsis");
		} while (this.synopsis.length() == 0);
	}
	
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher() {
		do {
			System.out.print("Insert book publisher: ");
			this.publisher = sc.nextLine();
			emptyStringError(this.publisher, "Publisher");
		} while (this.publisher.length() == 0);
	}
	
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public int getQty() {
		return qty;
	}

	public void setQty() {
		do {
			System.out.print("Insert book quantity: ");
			this.qty = sc.nextInt();
			sc.nextLine();
			
			if(this.qty <= 0)
				System.out.println("Quantity must be more than 0!");
		} while (this.qty <= 0);
	}
	
	public void setQty(int qty) {
		this.qty = qty;
	}

	private void emptyStringError(String string, String attribute) {
		if(string.length() == 0)
			System.out.println(attribute + " cannot be empty!");
	}
	
	public void bookInformation() {
		System.out.println("ID : " + this.getId());
		System.out.println("Title : " + this.getTitle());
		System.out.println("Author : " + this.getAuthor());
		System.out.println("Publisher : " + this.getPublisher());
		System.out.println("Synopsis : " + this.getSynopsis());
		System.out.println("Quantity : " + this.getQty());
		System.out.println("Available Quantity : " + this.getAvailableQty());
	}
	
	public void setId() {
		Random rand = new Random();
		this.id = "B" + rand.nextInt(10) + rand.nextInt(10) + rand.nextInt(10);
	}
}
