package factory;

import model.users.GoldUser;
import model.users.PlatinumUser;
import model.users.SilverUser;
import model.users.User;

public class UserFactory {

	public static User createUser(String type) {
		if(type.equals("Silver"))
			return new SilverUser();
		else if(type.equals("Gold"))
			return new GoldUser();
		return new PlatinumUser();
	}
}
