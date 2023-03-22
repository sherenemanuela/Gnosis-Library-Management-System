package databaseproxy;

import singleton.Database;

public abstract class DatabaseProxy {

	protected static Database con = Database.getConnection();
	
	public DatabaseProxy() {}
}
