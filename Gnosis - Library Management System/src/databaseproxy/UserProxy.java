package databaseproxy;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import builder.BookBuilder;
import builder.UserBuilder;
import model.books.Book;
import model.users.User;
import singleton.Library;

public class UserProxy extends DatabaseProxy {

	public UserProxy() {}
	
	public ResultSet searchUser(User user) {
		String query = "SELECT * FROM users WHERE username = ? AND password = ?";
		PreparedStatement ps = con.prepareStatement(query);
		ResultSet rs = null;
		try {
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			rs = ps.executeQuery();
			if(rs.next() == false) return null;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public void insertUser(User user, String type) {
		String query = "INSERT INTO users (username, password, type) VALUES (?, ?, ?)";
		PreparedStatement ps = con.prepareStatement(query);
		try {
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, type);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("User registered successfully!");
	}

	public void initUserList(UserBuilder ub){
		String query = "SELECT * FROM users";
		ResultSet rs = con.executeQuery(query);
		
		ArrayList<User> users = Library.getLibrary().getUsers();
		try {
			while(rs.next()) {
				users.add(ub.buildUser(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
