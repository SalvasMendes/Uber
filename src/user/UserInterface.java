package user;

import eds.EmptyListException;
import eds.InvalidPositionException;
import eds.TwoWayIterator;
import home.*;

/**
 * 
 * @author Salvador Mendes (50503) sr.mendes@campus.fct.unl.pt
 * @author Miguel Candeias (50647) mb.candeias@campus.fct.unl.pt
 *
 */
public interface UserInterface {

	/**
	 * 
	 * @return User id
	 */
	String getUserId();

	/**
	 * 
	 * @return User email
	 */
	String getEmail();

	/**
	 * 
	 * @return User cell number
	 */
	String getPhone();

	/**
	 * 
	 * @return User name
	 */
	String getName();

	/**
	 * 
	 * @return User adress
	 */
	String getAddress();

	/**
	 * 
	 * @return User nacionality
	 */
	String getNationality();

	/**
	 * This method allows a user to regist a property
	 * 
	 * @param home
	 */
	void createHome(Home home);

	/**
	 * This method removes one house of a user, given a homeID
	 * 
	 * @param homeId
	 * @throws InvalidPositionException
	 * @throws EmptyListException
	 */
	void removeHome(String homeId) throws InvalidPositionException, EmptyListException;

	/**
	 * This method changes the user info
	 * 
	 * @param email
	 * @param phone
	 * @param address
	 */
	void alterUser(String email, String phone, String address);

	/**
	 * Regist a house travelled
	 * 
	 * @param home
	 */
	void addStay(Home home);

	/**
	 * 
	 * @return True in case user still has properties registed in the app
	 */
	boolean hasHomes();

	/**
	 * 
	 * @param homeId
	 * @return True in case given a home id, a user has that property
	 * @throws InvalidPositionException
	 */
	boolean hasHome(String homeId) throws InvalidPositionException;

	/**
	 * 
	 * @return A iterator of hosted properties
	 * @throws InvalidPositionException
	 * @throws EmptyListException
	 */
	TwoWayIterator<Home> hostedHomesIterator() throws InvalidPositionException, EmptyListException;

	/**
	 * 
	 * @return A iterator of travelled propeties
	 * @throws InvalidPositionException
	 * @throws EmptyListException
	 */
	TwoWayIterator<Home> travalledHomesIterator() throws InvalidPositionException, EmptyListException;

	/**
	 * 
	 * @return True in case user has travelled
	 */
	boolean hasTravelled();
}