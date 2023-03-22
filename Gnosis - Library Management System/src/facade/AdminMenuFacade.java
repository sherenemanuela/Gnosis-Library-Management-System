package facade;

import java.util.Scanner;
import adapters.InformationAdapter;
import builder.BookBuilder;
import databaseproxy.BookProxy;
import iterator.FIFOiterator;
import main.MenuPrinter;
import model.books.Book;
import model.users.User;
import singleton.Library;

public class AdminMenuFacade {

	private Scanner sc = new Scanner(System.in);
	private Book book;
	private User user;
	private BookProxy bookProxy;
	private BookBuilder bookBuilder;
	private FIFOiterator<Book> fifoLibraryBooks;
	private FIFOiterator<User> fifoLibraryUsers;
	
	public AdminMenuFacade(BookProxy bookProxy, BookBuilder bookBuilder) {
		this.book = new Book();
		this.bookProxy = bookProxy;
		this.bookBuilder = bookBuilder;
		this.fifoLibraryBooks = new FIFOiterator<>(Library.getLibrary().getBooks());
		this.fifoLibraryUsers = new FIFOiterator<>(Library.getLibrary().getUsers());
	}
	
	public void adminMenu() {
		int menu = -1, menuCtr = -1;
		
		do {
			menuCtr = MenuPrinter.adminMenu();
			menu = sc.nextInt();
			sc.nextLine();
			
			switch(menu) {
			case 1:
				addBook();
				break;
			case 2:
				removeBook();
				break;
			case 3:
				updateBook();
				break;
			case 4:
				viewBookList();
				break;
			case 5:
				searchBook();
				break;
			case 6:
				viewUserList();
				break;
			}
		} while (menu != menuCtr);
	}

	private void viewUserList() {
		int ctr = 1;
		if(fifoLibraryUsers.iteratable()) {
			while(fifoLibraryUsers.hasNext()) {
				user = fifoLibraryUsers.getNext();
				InformationAdapter.displayUser(ctr++, user);
			}
		}
		System.out.println("No user yet!");
	}

	private void searchBook() {
		if(fifoLibraryBooks.iteratable()) {
			System.out.print("Insert book title: ");
			String title = sc.nextLine();
			
			while(fifoLibraryBooks.hasNext()) {
				book = fifoLibraryBooks.getNext();
				if(book.getTitle().equals(title)) {
					book.bookInformation();
					return;
				}
			}
			System.out.println("Book not found!");
		}
	}

	private void updateBook() {
		viewBookList();
		
		if(fifoLibraryBooks.iteratable()) {
			boolean found = false;
			System.out.print("Insert book id to be updated: ");
			String id = sc.nextLine();
			
			System.out.print("Insert book quantity to be added: ");
			int qty = sc.nextInt();
			sc.nextLine();
			
			int ctr = 0;
			while(fifoLibraryBooks.hasNext()) {
				book = fifoLibraryBooks.getNext();
				if(book.getId().equals(id)) {
					found = true;
					book = Library.getLibrary().getBooks().get(ctr); 
					book.setQty(book.getQty() + qty);
					book.setAvailableQty(book.getAvailableQty() + qty);
					break;
				}
				ctr++;
			}
			if(found) {
				bookProxy.updateBook(id, book.getQty());
				System.out.println("Book updated successfully!");
				return;
			}
		}
		System.out.println("Book not found!");
	}

	private void viewBookList() {
		int ctr = 1;
		if(fifoLibraryBooks.iteratable()) {
			while(fifoLibraryBooks.hasNext()) {
				book = fifoLibraryBooks.getNext();
				InformationAdapter.displayBook(ctr++, book);
			}
			return;
		}
		System.out.println("No book yet!");
	}

	private void removeBook() {
		viewBookList();
		
		if(fifoLibraryBooks.iteratable()) {
			boolean found = false;
			System.out.print("Insert book id to be removed: ");
			String id = sc.nextLine();
			
			int ctr = 0;
			while(fifoLibraryBooks.hasNext()) {
				book = fifoLibraryBooks.getNext();
				if(book.getId().equals(id)) {
					Library.getLibrary().getBooks().remove(ctr);
					found = true;
					break;
				}
				ctr++;
			}
			if(found) {
				bookProxy.deleteBook(id);
				System.out.println("Book deleted successfully!");
				return;
			}
		}
		System.out.println("No book found!");
	}

	private void addBook() {
		Book newBook = new Book();
		bookBuilder.buildBook(newBook);
		Library.getLibrary().getBooks().add(newBook);
		bookProxy.insertBook(newBook);
		System.out.println("Book added successfully!");
	}

}
