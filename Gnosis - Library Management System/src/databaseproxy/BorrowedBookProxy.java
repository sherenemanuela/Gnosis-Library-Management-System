package databaseproxy;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import builder.BorrowedBookBuilder;
import builder.UserBuilder;
import model.books.Book;
import model.books.BorrowedBook;
import model.users.User;
import singleton.Library;

public class BorrowedBookProxy extends DatabaseProxy {

	private BookProxy bp;
	
	public BorrowedBookProxy() {
		this.bp = new BookProxy();
	}
	
	public void insertBorrowedBook(User user, BorrowedBook borrowedBook) {
		String query = "INSERT INTO borrowed_books (book_id, username, checkin, checkout, id) VALUES (?, ?, ?, ?, ?)";
		PreparedStatement ps = con.prepareStatement(query);
		
		try {
			ps.setString(1, borrowedBook.getBook().getId());
			ps.setString(2, user.getUsername());
			ps.setDate(3, java.sql.Date.valueOf(java.time.LocalDate.now()));
			ps.setString(4, null);
			ps.setString(5, borrowedBook.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		bp.decrementQty(borrowedBook.getBook());
	}

	public void getBorrowedBookList(String username, ArrayList<BorrowedBook> borrowedBook){
		String query = "SELECT borrowed_books.id, title, checkin, checkout FROM borrowed_books JOIN books ON books.id = borrowed_books.book_id WHERE username LIKE '" + username + "'";
		PreparedStatement ps = con.prepareStatement(query);
		
		ArrayList<BorrowedBook> borrowedBooks = borrowedBook;
		BorrowedBookBuilder bbb = new BorrowedBookBuilder();
		try {
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				borrowedBooks.add(bbb.buildBorrowedBook(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateBorrowedBook(String id, Book book) {
		String query = "UPDATE borrowed_books SET checkout = ? WHERE id = ?";
		PreparedStatement ps = con.prepareStatement(query);
		
		try {
			ps.setString(1, java.sql.Date.valueOf(java.time.LocalDate.now()).toString());
			ps.setString(2, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		bp.incrementQty(book);
	}

}
