package facade;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import builder.BookBuilder;
import builder.BorrowedBookBuilder;
import builder.UserBuilder;
import databaseproxy.BookProxy;
import databaseproxy.BorrowedBookProxy;
import databaseproxy.UserProxy;
import factory.UserFactory;
import main.MenuPrinter;
import model.users.SilverUser;
import model.users.User;
import singleton.Library;

public class LMSFacade {

	private Scanner sc = new Scanner(System.in);
	private UserProxy userProxy;
	private BookProxy bookProxy;
	private BorrowedBookProxy borrowedBookProxy;
	private UserBuilder userBuilder;
	private BookBuilder bookBuilder;
	private User user;
	
	public LMSFacade(){
		this.bookProxy = new BookProxy();
		this.user = new SilverUser();
		this.userProxy = new UserProxy();
		this.userBuilder = new UserBuilder();
		this.bookBuilder = new BookBuilder();
		this.borrowedBookProxy = new BorrowedBookProxy();
	}
	
	public void startApplication() {
		initializeLibrary();
		mainMenu();
	}
	
	public void initializeLibrary() {
		userProxy.initUserList(userBuilder);
		bookProxy.initBookList(bookBuilder);
	}
	
	public void mainMenu() {
		int menu = -1, menuCount = -1;
		do {
			menuCount = MenuPrinter.mainMenu();
			menu = sc.nextInt();
			sc.nextLine();
			
			switch(menu) {
			case 1:
				login();
				break;
			case 2:
				register();
				break;
			}
		} while (menu != menuCount);
	}

	private void register() {
		promptLoginCredentials();
		String type = user.promptUserType();
		
		if(user.getUsername().equals("Admin")) {
			System.out.println("Username is not allowed, please choose another username!");
			return;
		}
		
		ResultSet rs = userProxy.searchUser(user);
		if(rs == null)
			userProxy.insertUser(user, type);
		else
			System.out.println("User exist, please login!");
	}

	private void login() {
		promptLoginCredentials();
		if(user.getUsername().equals("Admin") && user.getPassword().equals("AdminGnosis"))
			adminMenu(new AdminMenuFacade(bookProxy, bookBuilder));
		else
			userMenu();
	}

	private void adminMenu(AdminMenuFacade adminMenuFacade) {
		adminMenuFacade.adminMenu();
	}
	
	private void userMenu() {
		ResultSet rs = userProxy.searchUser(user);
		try {
			if(rs == null) {
				System.out.println("Wrong credentials!");
			}
			else {
				user = UserFactory.createUser(rs.getString(3));
				userBuilder.buildUser(user, rs.getString(1), rs.getString(2));
				UserMenuFacade userMenuFacade = new UserMenuFacade(user);
				userMenuFacade.userMenu();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void promptLoginCredentials() {
		user.setUsername();
		user.setPassword();
	}
}
