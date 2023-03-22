package facade;

import java.util.Scanner;

import adapters.InformationAdapter;
import builder.BorrowedBookBuilder;
import databaseproxy.BookProxy;
import databaseproxy.BorrowedBookProxy;
import iterator.FIFOiterator;
import main.MenuPrinter;
import model.books.Book;
import model.books.BorrowedBook;
import model.users.User;
import singleton.Library;

public class UserMenuFacade {

	private Scanner sc = new Scanner(System.in);
	private User user;
	private Book book;
	private BorrowedBook borrowedBook;
	private BorrowedBookProxy borrowedBookProxy;
	private BorrowedBookBuilder borrowedBookBuilder;
	private FIFOiterator<Book> fifoLibraryBooks;
	private FIFOiterator<BorrowedBook> fifoBorrowedBooks;
	
	public UserMenuFacade(User user) {
		this.user = user;
		this.borrowedBook = new BorrowedBook();
		this.borrowedBookProxy = new BorrowedBookProxy();
		this.borrowedBookBuilder = new BorrowedBookBuilder();
		this.fifoLibraryBooks = new FIFOiterator<>(Library.getLibrary().getBooks());
		this.fifoBorrowedBooks = new FIFOiterator<>(user.getBorrowedBooks());
	}
	
	public void userMenu() {
		int menu = -1, menuCtr = -1;
		
		do {
			menuCtr = MenuPrinter.userMenu();
			menu = sc.nextInt();
			sc.nextLine();
			
			switch(menu) {
			case 1:
				borrowBook();
				break;
			case 2:
				returnBook();
				break;
			case 3:
				viewBookList();
				break;
			case 4:
				searchBook();
				break;
			case 5:
				history();
				break;
			}
		} while (menu != menuCtr);
	}

	private void history() {
		int ctr = 1;
		
		if(fifoBorrowedBooks.iteratable()) {
			while(fifoBorrowedBooks.hasNext()) {
				borrowedBook = fifoBorrowedBooks.getNext();
				InformationAdapter.displayBorrowedBook(ctr++, borrowedBook);
			}
			return;
		}
		System.out.println("No borrowed book yet!");
	}

	private void returnBook() {
		history();
		System.out.print("Insert borrow slip id: ");
		String id = sc.nextLine();
		
		if(borrowedBookFound(id)) {
			updateBorrowedBook(id);
		}
	}

	private void updateBorrowedBook(String id) {
		if(fifoLibraryBooks.iteratable()){ 
			while(fifoLibraryBooks.hasNext()) {
				book = fifoLibraryBooks.getNext();
				if(book.getId().equals(borrowedBook.getBook().getId())) {
					int temp = book.getAvailableQty() + 1;
					book.setAvailableQty(temp);
					borrowedBookProxy.updateBorrowedBook(id, book);
				}
			}
		}
	}

	private void borrowBook() {
		viewBookList();
		String id = null;
		System.out.print("Insert book id: ");
		id = sc.nextLine();
		
		if(userAllowed(id)) {
			if(bookAvailable(id)) {
				borrowedBook = borrowedBookBuilder.buildBorrowedBook(book, new BorrowedBook());
				user.getBorrowedBooks().add(borrowedBook);
				borrowedBookProxy.insertBorrowedBook(user, borrowedBook);
				System.out.println("Book borrowed successfully!");
				return;
			}
		}
	}

	private boolean userAllowed(String id) {
		int ctr = 1;
		if(fifoBorrowedBooks.iteratable()) {
			while(fifoBorrowedBooks.hasNext()) {
				BorrowedBook borrowedBook = fifoBorrowedBooks.getNext();
				if(borrowedBook.getBook().getId().equals(id) && borrowedBook.getCheckout() == null) {
					System.out.println("You cannot borrow the same book!");
					return false;
				}
				if(borrowedBook.getCheckout() == null) {
					ctr++;
				}
			}
		}
		if(ctr < user.getMaxQty())
			return true;
		System.out.println("You can only borrow " + user.getMaxQty() + " books at a time!");
		return false;
	}
	
	private boolean bookAvailable(String id) {
		if(fifoLibraryBooks.iteratable()) {
			while(fifoLibraryBooks.hasNext()) {
				book = fifoLibraryBooks.getNext();
				if(book.getId().equals(id) && book.getAvailableQty() > 0) {
					book.setAvailableQty(book.getAvailableQty() - 1);
					return true;
				}
			}
		}
		System.out.println("Book not available!");
		return false;
	}
	
	private boolean borrowedBookFound(String id) {
		if(fifoBorrowedBooks.iteratable()) { 
			while(fifoBorrowedBooks.hasNext()) {
				borrowedBook = fifoBorrowedBooks.getNext();
				if(borrowedBook.getId().equals(id) && borrowedBook.getCheckout() == null) {
					borrowedBook.setCheckout(java.sql.Date.valueOf(java.time.LocalDate.now()).toString());
					return true;
				}
			}
		}
		System.out.println("Borrowed book not found!");
		return false;
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
}
