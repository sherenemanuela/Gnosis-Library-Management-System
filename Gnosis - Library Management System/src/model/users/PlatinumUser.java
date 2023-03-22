package model.users;

public class PlatinumUser extends User {

	public PlatinumUser() {}

	@Override
	public int getMaxQty() {
		return 10;
	}

}
