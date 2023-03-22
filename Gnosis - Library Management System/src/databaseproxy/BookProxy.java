package databaseproxy;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import builder.BookBuilder;
import model.books.Book;
import singleton.Library;

public class BookProxy extends DatabaseProxy {

	public BookProxy() {}

	public ResultSet searchBook(String title) {
		String query = "SELECT * FROM books WHERE title = ?";
		PreparedStatement ps = con.prepareStatement(query);
		ResultSet rs = null;
		try {
			ps.setString(1, title);
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public void insertBook(Book book) {
		String query = "INSERT INTO books (title, author, publisher, synopsis, quantity, available_qty, id) VALUES (?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement ps = con.prepareStatement(query);

		try {
			ps.setString(1, book.getTitle());
			ps.setString(2, book.getAuthor());
			ps.setString(3, book.getPublisher());
			ps.setString(4, book.getSynopsis());
			ps.setInt(5, book.getQty());
			ps.setInt(6, book.getQty());
			ps.setString(7, book.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteBook(String id) {
		String query = "DELETE FROM books WHERE id = ?";
		PreparedStatement ps = con.prepareStatement(query);
		
		try {
			ps.setString(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void initBookList(BookBuilder bb){
		String query = "SELECT * FROM books";
		ResultSet rs = con.executeQuery(query);
		
		ArrayList<Book> books = Library.getLibrary().getBooks();
		try {
			while(rs.next()) {
				books.add(bb.buildBook(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateBook(String id, int qty) {
		String query = "UPDATE books SET quantity = ?, available_qty = ? WHERE id = ?";
		PreparedStatement ps = con.prepareStatement(query);
		
		try {
			ps.setInt(1, qty);
			ps.setInt(2, qty);
			ps.setString(3, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void decrementQty(Book book) {
		String query = "UPDATE books SET available_qty = ? WHERE id = ?";
		PreparedStatement ps = con.prepareStatement(query);
		
		try {
			ps.setInt(1, book.getAvailableQty());
			ps.setString(2, book.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void incrementQty(Book book) {
		String query = "UPDATE books SET available_qty = ? WHERE id = ?";
		PreparedStatement ps = con.prepareStatement(query);
		try {
			ps.setInt(1, book.getAvailableQty());
			ps.setString(2, book.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
