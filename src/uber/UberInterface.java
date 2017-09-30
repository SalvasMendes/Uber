package uber;

import eds.EmptyListException;
import eds.InvalidPositionException;
import exceptions.PropertyExistsException;
import exceptions.PropertyInexistantException;
import exceptions.PropertyVisitedException;
import exceptions.TravellerIsHostException;
import exceptions.TravellerIsNotHostException;
import exceptions.UserExistException;
import exceptions.UserHasPlaceException;
import exceptions.UserInexistantException;
import home.Home;
import user.UserInterface;

public interface UberInterface {

	void createUser(String userId, String email, String phone, String name, String address, String nationality) throws UserExistException, InvalidPositionException;

	void createHome(String homeId, String userId, int price, int cap, String local, String description, String address)
			throws InvalidPositionException, PropertyExistsException, UserInexistantException;

	void alterUser(String userId, String email, String phone, String name, String address, String nationality)
			throws InvalidPositionException, UserInexistantException;

	void removeUser(String userId) throws EmptyListException, InvalidPositionException, UserInexistantException, UserHasPlaceException;

	UserInterface userInfo(String userId) throws InvalidPositionException, UserInexistantException;

	void removeHome(String homeId) throws EmptyListException, InvalidPositionException, PropertyInexistantException, PropertyVisitedException;

	Home homeInfo(String homeId) throws InvalidPositionException, PropertyInexistantException;

	void addStay(String userId, String homeId, int points) throws InvalidPositionException, UserInexistantException, PropertyInexistantException, TravellerIsHostException;

	void addStayNoPoints(String userId, String homeId, int points) throws InvalidPositionException, UserInexistantException, PropertyInexistantException, TravellerIsNotHostException;
	
	void saveStatus();

}