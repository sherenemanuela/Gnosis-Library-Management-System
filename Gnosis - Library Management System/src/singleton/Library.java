package singleton;


import java.util.ArrayList;

import databaseproxy.BookProxy;
import databaseproxy.BorrowedBookProxy;
import model.books.Book;
import model.users.User;

public class Library {
	
	private ArrayList<User> users;
	private ArrayList<Book> books;
	private static Library library;
	
	private Library() {
		this.books = new ArrayList<>();
		this.users = new ArrayList<>();
	}
	
	public static synchronized Library getLibrary() {
		if(library == null) {
			library = new Library();
		}
		return library;
    }
	
	public ArrayList<Book> getBooks() {
		return this.books;
	}
	
	public ArrayList<User> getUsers() {
		return this.users;
	}
}
