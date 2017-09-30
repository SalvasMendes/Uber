package user;

import eds.EmptyListException;
import eds.InvalidPositionException;
import home.*;

public interface UserInterface {

	

	String getUserId();

	String getEmail();

	String getPhone();

	String getName();

	String getAddress();

	String getNationality();

	void createHome(Home home);

	void removeHome(String homeId) throws InvalidPositionException, EmptyListException;

	void alterUser(String email, String phone, String name, String address, String nationality);
	
	void addStay(Home home);
	
	 boolean hasHomes();
	 
	 boolean hasHome(String homeId) throws InvalidPositionException;

}