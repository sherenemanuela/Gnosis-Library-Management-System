package model.users;

public class GoldUser extends User {

	public GoldUser() {}

	@Override
	public int getMaxQty() {
		return 7;
	}
}
