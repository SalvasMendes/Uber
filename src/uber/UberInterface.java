package uber;

import java.util.InputMismatchException;

import eds.*;
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

/**
 * 
 * @author Salvador Mendes (50503) sr.mendes@campus.fct.unl.pt
 * @author Miguel Candeias (50647) mb.candeias@campus.fct.unl.pt
 *
 */
public interface UberInterface {

	/**
	 * This method creates a new user of the app, throws exception in case the
	 * user is already registed
	 * 
	 * @param userId
	 * @param email
	 * @param phone
	 * @param name
	 * @param address
	 * @param nationality
	 * @throws UserExistException
	 * @throws InvalidPositionException
	 */
	void createUser(String userId, String email, String phone, String name, String address, String nationality)
			throws UserExistException, InvalidPositionException;

	/**
	 * This method creates a new home in the app, throws exception in case there
	 * is no user registed in the app or in the property already exists.
	 * 
	 * @param homeId
	 * @param userId
	 * @param price
	 * @param cap
	 * @param local
	 * @param description
	 * @param address
	 * @throws UserInexistantException
	 * @throws PropertyExistsException
	 * @throws InvalidPositionException
	 */
	void createHome(String homeId, String userId, int price, int cap, String local, String description, String address)
			throws UserInexistantException, PropertyExistsException, InvalidPositionException;

	/**
	 * This method allows a user to change some of his information, throws
	 * exception in case he is not registed
	 * 
	 * @param userId
	 * @param email
	 * @param phone
	 * @param address
	 * @throws UserInexistantException
	 * @throws InvalidPositionException
	 */
	void alterUser(String userId, String email, String phone, String address)
			throws UserInexistantException, InvalidPositionException;

	/**
	 * This method removes a user from the app, throws exception in case he is
	 * not registed or has properties registed in the app
	 * 
	 * @param userId
	 * @throws UserInexistantException
	 * @throws UserHasPlaceException
	 * @throws EmptyListException
	 * @throws InvalidPositionException
	 */
	void removeUser(String userId)
			throws UserInexistantException, UserHasPlaceException, EmptyListException, InvalidPositionException;

	/**
	 * This method returns a user object, with it is possible to check some of
	 * his info
	 * 
	 * @param userId
	 * @return User object
	 * @throws UserInexistantException
	 * @throws InvalidPositionException
	 */
	UserInterface userInfo(String userId) throws UserInexistantException, InvalidPositionException;

	/**
	 * This method removes a house from the app, throws exception in case the
	 * house has been visited or the property is not registed
	 * 
	 * @param homeId
	 * @throws PropertyInexistantException
	 * @throws PropertyVisitedException
	 * @throws EmptyListException
	 * @throws InvalidPositionException
	 */
	void removeHome(String homeId)
			throws PropertyInexistantException, PropertyVisitedException, EmptyListException, InvalidPositionException;

	/**
	 * This method return a house object, with it is possible to check some
	 * information of the house
	 * 
	 * @param homeId
	 * @return
	 * @throws PropertyInexistantException
	 * @throws InvalidPositionException
	 */
	Home homeInfo(String homeId) throws PropertyInexistantException, InvalidPositionException;

	/**
	 * This method rates the house, someone not the host is able to rate the
	 * house
	 * 
	 * @param userId
	 * @param homeId
	 * @param points
	 * @throws DadosInvalidosException
	 * @throws UserInexistantException
	 * @throws PropertyInexistantException
	 * @throws TravellerIsHostException
	 * @throws InvalidPositionException
	 */
	void addStay(String userId, String homeId, int points) throws DadosInvalidosException, UserInexistantException,
			PropertyInexistantException, TravellerIsHostException, InvalidPositionException;

	/**
	 * This method allows a host to "sleep" in his own house
	 * 
	 * @param userId
	 * @param homeId
	 * @throws UserInexistantException
	 * @throws PropertyInexistantException
	 * @throws TravellerIsNotHostException
	 * @throws InvalidPositionException
	 */
	void addStayNoPoints(String userId, String homeId) throws UserInexistantException, PropertyInexistantException,
			TravellerIsNotHostException, InvalidPositionException;

	/**
	 * Saves the information of the program
	 */
	void saveStatus();

	/**
	 * Returns a iterator of houses in a certain place, and capacity
	 * 
	 * @param people
	 * @param local
	 * @return
	 * @throws InputMismatchException
	 * @throws InvalidPositionException
	 * @throws EmptyListException
	 * @throws EmptyStackException 
	 */
	TreeIterator<String, Home> platformHousesIterator(int people, String local)
			throws InputMismatchException, InvalidPositionException, EmptyListException, EmptyStackException;

	/**
	 * Returns a iterator of all the houses hosted by a user
	 * 
	 * @param userId
	 * @return
	 * @throws UserInexistantException
	 * @throws UserHasNoHomeException
	 * @throws InvalidPositionException
	 * @throws EmptyListException
	 * @throws EmptyStackException 
	 */
	TreeIterator<String, Home> hostedHomesIterator(String userId)
			throws UserInexistantException, UserHasNoHomeException, InvalidPositionException, EmptyListException, EmptyStackException;

	/**
	 * Returns a iterator of all houses travelled by a user
	 * 
	 * @param userId
	 * @return
	 * @throws UserInexistantException
	 * @throws UserNotTravalledException
	 * @throws InvalidPositionException
	 * @throws EmptyListException
	 */
	TwoWayIterator<Home> travalledHomesIterator(String userId)
			throws UserInexistantException, UserNotTravalledException, InvalidPositionException, EmptyListException;

	/**
	 * Sorts the houses, and return an iterator of best properties in a place
	 * 
	 * @param local
	 * @return
	 * @throws InvalidPositionException
	 * @throws EmptyListException
	 * @throws EmptyStackException 
	 */
	TreeIterator<Integer, LBList<String, Home>> bestHomesIterator(String local) throws InvalidPositionException, EmptyListException, EmptyStackException;
	
	

}