package user;

import eds.Stack;

public interface UserInterface {

	Stack<String> getHomes();

	String getUserId();

	String getEmail();

	String getPhone();

	String getName();

	String getAddress();

	String getNationality();

	void createHome(String homeId);

	void removeHome(String homeId);

	void alterUser(String email, String phone, String name, String address, String nationality);

}