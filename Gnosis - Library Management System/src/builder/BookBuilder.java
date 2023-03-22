package builder;

import java.sql.ResultSet;
import java.sql.SQLException;

import databaseproxy.BookProxy;
import model.books.Book;

public class BookBuilder {

	private Book book;
	
	public BookBuilder() {}
	
	public void buildBook(Book book) {
		this.book = new Book();
		book.setId();
		book.setTitle();
		book.setAuthor();
		book.setPublisher();
		book.setSynopsis();
		book.setQty();
		book.setAvailableQty(book.getQty());
	}
	
	public Book buildBook(ResultSet rs) {
		this.book = new Book();
		try {
			book.setId(rs.getString(1));
			book.setTitle(rs.getString(2));
			book.setAuthor(rs.getString(3));
			book.setPublisher(rs.getString(4));
			book.setSynopsis(rs.getString(5));
			book.setQty(rs.getInt(6));
			book.setAvailableQty(rs.getInt(7));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return this.book;
	}
}
