package builder;

import java.sql.ResultSet;
import java.sql.SQLException;

import databaseproxy.BookProxy;
import model.books.Book;
import model.books.BorrowedBook;

public class BorrowedBookBuilder {

	private BorrowedBook borrowedBook;
	private BookBuilder bb;
	private BookProxy bp;
	
	public BorrowedBookBuilder() {
		this.bb = new BookBuilder();
		this.bp = new BookProxy();
	}
	
	public BorrowedBook buildBorrowedBook(ResultSet rs) {
		this.borrowedBook = new BorrowedBook();
		try {
			borrowedBook.setId(rs.getString(1));
			ResultSet bookRs = bp.searchBook(rs.getString(2));
			bookRs.next();
			borrowedBook.setBook(bb.buildBook(bookRs));
			borrowedBook.setCheckin(rs.getString(3));
			borrowedBook.setCheckout(rs.getString(4));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return this.borrowedBook;
	}
	
	public BorrowedBook buildBorrowedBook(Book book, BorrowedBook borrowedBook) {
		this.borrowedBook = borrowedBook;
		borrowedBook.setBook(book);
		borrowedBook.setCheckin(java.sql.Date.valueOf(java.time.LocalDate.now()).toString());
		borrowedBook.setCheckout(null);
		borrowedBook.setId();
		return borrowedBook;
	}

}
