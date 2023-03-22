package main;

public class MenuPrinter {
	
	public static int mainMenu() {
		System.out.println();
		System.out.println("====================================");
		System.out.println("| Gnosis Library Management System |");
		System.out.println("====================================");
		System.out.println("| 1. Login                         |");
		System.out.println("| 2. Register                      |");
		System.out.println("| 3. Exit                          |");
		System.out.println("====================================");
		System.out.print(">> ");
		return 3;
	}
	
	public static int adminMenu() {
		System.out.println();
		System.out.println("====================================");
		System.out.println("| Gnosis Library Management System |");
		System.out.println("====================================");
		System.out.println("| 1. Add Book                      |");
		System.out.println("| 2. Delete Book                   |");
		System.out.println("| 3. Update Book                   |");
		System.out.println("| 4. View Book List                |");
		System.out.println("| 5. Search Books                  |");
		System.out.println("| 6. View Users                    |");
		System.out.println("| 7. Logout                        |");
		System.out.println("====================================");
		System.out.print(">> ");
		return 7;
	}
	
	public static int userMenu() {
		System.out.println();
		System.out.println("====================================");
		System.out.println("| Gnosis Library Management System |");
		System.out.println("====================================");
		System.out.println("| 1. Borrow Book                   |");
		System.out.println("| 2. Return Book                   |");
		System.out.println("| 3. View Book List                |");
		System.out.println("| 4. Search Book                   |");
		System.out.println("| 5. History                       |");
		System.out.println("| 6. Logout                        |");
		System.out.println("====================================");
		System.out.print(">> ");
		return 6;
	}
}
