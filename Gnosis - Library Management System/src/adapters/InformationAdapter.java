package adapters;

import model.books.Book;
import model.books.BorrowedBook;
import model.users.User;

public class InformationAdapter {

	public static void displayUser(int no, User user) {
		System.out.println("User " + no);
		System.out.println("================");
		user.userInformation();
		System.out.println();
	}
	
	public static void displayBook(int no, Book book) {
		System.out.println("Book " + no);
		System.out.println("================");
		book.bookInformation();
		System.out.println();
	}

	public static void displayBorrowedBook(int no, BorrowedBook borrowedBook) {
		System.out.println("Borrowed Book " + no);
		System.out.println("================");
		borrowedBook.borrowedBookInformation();
		System.out.println();
	}
}
