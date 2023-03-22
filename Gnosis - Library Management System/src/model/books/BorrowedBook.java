package model.books;

import java.util.Random;

import databaseproxy.BookProxy;
import databaseproxy.BorrowedBookProxy;
import singleton.Library;

public class BorrowedBook {

	private Book book;
	private String checkin, checkout, id;
	
	public BorrowedBook() {}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public void setId() {
		Random rand = new Random();
		this.id = "BR" + rand.nextInt(10) + rand.nextInt(10);
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public String getCheckin() {
		return checkin;
	}

	public void setCheckin(String checkin) {
		this.checkin = checkin;
	}

	public String getCheckout() {
		return checkout;
	}

	public void setCheckout(String checkout) {
		this.checkout = checkout;
	}

	public void borrowedBookInformation() {
		System.out.println("Borrow Slip ID : " + this.getId());
		System.out.println("Book ID : " + this.getBook().getId());
		System.out.println("Book Title : " + this.getBook().getTitle());
		System.out.println("Checkin : " + this.getCheckin());
		System.out.println("Checkout : " + this.getCheckout());
	}
}
