package builder;

import java.sql.ResultSet;
import java.sql.SQLException;

import factory.UserFactory;
import model.books.Book;
import model.users.User;

public class UserBuilder {

	private User user;
	
	public UserBuilder() {}
	
	public void buildUser(User user, String username, String password) {
		user.setUsername(username);
		user.setPassword(password);
		user.initBorrowedBooks();
	}
	
	public User buildUser(ResultSet rs) {
		try {
			this.user = UserFactory.createUser(rs.getString(3));
			this.user.setUsername(rs.getString(1));
			this.user.setPassword(rs.getString(2));
			this.user.initBorrowedBooks();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return this.user;
	}

}
