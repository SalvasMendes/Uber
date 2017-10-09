package uber;

import java.util.InputMismatchException;

import eds.EmptyListException;
import eds.InvalidPositionException;
import eds.TwoWayIterator;
import exceptions.DadosInvalidosException;
import exceptions.PropertyExistsException;
import exceptions.PropertyInexistantException;
import exceptions.PropertyVisitedException;
import exceptions.TravellerIsHostException;
import exceptions.TravellerIsNotHostException;
import exceptions.UserExistException;
import exceptions.UserHasNoHomeException;
import exceptions.UserHasPlaceException;
import exceptions.UserInexistantException;
import exceptions.UserNotTravalledException;
import home.Home;
import user.UserInterface;

public interface UberInterface {

	void createUser(String userId, String email, String phone, String name, String address, String nationality)
			throws UserExistException, InvalidPositionException;

	void createHome(String homeId, String userId, int price, int cap, String local, String description, String address)
			throws UserInexistantException, PropertyExistsException, InvalidPositionException;

	void alterUser(String userId, String email, String phone, String address)
			throws UserInexistantException, InvalidPositionException;

	void removeUser(String userId)
			throws UserInexistantException, UserHasPlaceException, EmptyListException, InvalidPositionException;

	UserInterface userInfo(String userId) throws UserInexistantException, InvalidPositionException;

	void removeHome(String homeId)
			throws PropertyInexistantException, PropertyVisitedException, EmptyListException, InvalidPositionException;

	Home homeInfo(String homeId) throws PropertyInexistantException, InvalidPositionException;

	void addStay(String userId, String homeId, int points) throws DadosInvalidosException, UserInexistantException, PropertyInexistantException,
			TravellerIsHostException, InvalidPositionException;

	void addStayNoPoints(String userId, String homeId) throws UserInexistantException, PropertyInexistantException,
			TravellerIsNotHostException, InvalidPositionException;

	void saveStatus();

	TwoWayIterator<Home> platformHousesIterator(int people, String local)
			throws InputMismatchException, InvalidPositionException, EmptyListException;

	TwoWayIterator<Home> hostedHomesIterator(String userId)
			throws UserInexistantException, UserHasNoHomeException, InvalidPositionException, EmptyListException;

	TwoWayIterator<Home> travalledHomesIterator(String userId)
			throws UserInexistantException, UserNotTravalledException, InvalidPositionException, EmptyListException;

	TwoWayIterator<Home> bestHomesIterator(String local) throws InvalidPositionException, EmptyListException;

}