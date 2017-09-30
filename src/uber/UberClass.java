package uber;

import java.io.*;
import java.util.Scanner;

import eds.*;
import exceptions.*;
import home.*;
import user.*;

public class UberClass implements UberInterface {

	private DLList<UserInterface> users;
	private DLList<Home> homes;

	@SuppressWarnings("unchecked")
	public UberClass() {

		try {
			FileInputStream fileIn = new FileInputStream("users.ser");
			ObjectInputStream objIn = new ObjectInputStream(fileIn);
			users = (DLList<UserInterface>) objIn.readObject();
			objIn.close();
			fileIn.close();
		} catch (FileNotFoundException fnf) {
			users = new LinkedList<UserInterface>();
			fnf.printStackTrace();
		} catch (IOException i) {
			i.printStackTrace();
			return;
		} catch (ClassNotFoundException c) {
			System.out.println("Class User not found.");
			c.printStackTrace();
			return;
		}

		try {
			FileInputStream fileIn = new FileInputStream("homes.ser");
			ObjectInputStream objIn = new ObjectInputStream(fileIn);
			users = (DLList<UserInterface>) objIn.readObject();
			objIn.close();
			fileIn.close();
		} catch (FileNotFoundException fnf) {
			homes = new LinkedList<Home>();
			fnf.printStackTrace();
		} catch (IOException i) {
			i.printStackTrace();
			return;
		} catch (ClassNotFoundException c) {
			System.out.println("Class Home not found.");
			c.printStackTrace();
			return;
		}

		// homes = new LinkedList<Home>();
		// users = new LinkedList<UserInterface>();
	}

	public void createUser(String userId, String email, String phone, String name, String address, String nationality)
			throws UserExistException, InvalidPositionException {

		if (searchUsers(userId) >= 0)
			throw new UserExistException();

		UserInterface user = new UserClass(userId, email, phone, name, address, nationality);
		users.addLast(user);

	}

	public void createHome(String homeId, String userId, int price, int cap, String local, String description,
			String address) throws UserInexistantException, PropertyExistsException, InvalidPositionException {

		// Exe�ao da puta de dados invalidos, falar com stora

		if (searchUsers(userId) == -1)
			throw new UserInexistantException();

		else if (searchHome(homeId) >= 0)
			throw new PropertyExistsException();

		else {

			UserInterface owner = users.get(searchUsers(userId));
			Home home = new HomeClass(homeId, userId, local, description, address, price, cap, owner);
			homes.addLast(home);
			users.get(searchUsers(userId)).createHome(home);
		}
	}

	public void alterUser(String userId, String email, String phone, String name, String address, String nationality)
			throws UserInexistantException, InvalidPositionException {

		if (searchUsers(userId) == -1)
			throw new UserInexistantException();
		users.get(searchUsers(userId)).alterUser(email, phone, name, address, nationality);

	}

	public void removeUser(String userId)
			throws UserInexistantException, UserHasPlaceException, EmptyListException, InvalidPositionException {

		int user = searchUsers(userId);

		if (user == -1)
			throw new UserInexistantException();

		else if (users.get(user).hasHomes())
			throw new UserHasPlaceException();

		else {
			users.remove(searchUsers(userId));
		}
	}

	public UserInterface userInfo(String userId) throws UserInexistantException, InvalidPositionException {

		if (searchUsers(userId) == -1)
			throw new UserInexistantException();

		return users.get(searchUsers(userId));

	}

	public void removeHome(String homeId)
			throws PropertyInexistantException, PropertyVisitedException, EmptyListException, InvalidPositionException {

		int pos = searchHome(homeId);

		if (pos == -1)
			throw new PropertyInexistantException();

		else if (homes.get(pos).isVisited())
			throw new PropertyVisitedException();

		else {

			homes.remove(pos);
			homes.get(pos).getOwner().removeHome(homeId);
		}
	}

	public Home homeInfo(String homeId) throws PropertyInexistantException, InvalidPositionException {
		if (searchHome(homeId) == -1)
			throw new PropertyInexistantException();

		return homes.get(searchHome(homeId));
	}

	public void addStay(String userId, String homeId, int points) throws UserInexistantException,
			PropertyInexistantException, TravellerIsHostException, InvalidPositionException {

		// Dados invalidos exe�ao, ask prof

		int user = searchUsers(userId);
		int pos = searchHome(homeId);

		if (user == -1)
			throw new UserInexistantException();

		else if (pos == -1)
			throw new PropertyInexistantException();

		else if (users.get(pos).hasHome(homeId))
			throw new TravellerIsHostException();

		else {
			homes.get(pos).addScore(points);
			users.get(user).addStay(homes.get(pos));
		}

	}

	public void addStayNoPoints(String userId, String homeId, int points) throws UserInexistantException,
			PropertyInexistantException, TravellerIsNotHostException, InvalidPositionException {

		int user = searchUsers(userId);
		int home = searchHome(homeId);

		if (user == -1)
			throw new UserInexistantException();

		else if (home == -1)
			throw new PropertyInexistantException();

		else if (!(users.get(user).hasHome(homeId)))
			throw new TravellerIsNotHostException();

		else {
			users.get(user).addStay(homes.get(home));
		}

	}
	
	public void saveStatus() {
		try {
			FileOutputStream fileOut = new FileOutputStream("users.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(users);
			out.close();
			fileOut.close();
		}
		catch(IOException i){
			i.printStackTrace();
		}
		
		try {
			FileOutputStream fileOut = new FileOutputStream("homes.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(homes);
			out.close();
			fileOut.close();
		}
		catch(IOException i){
			i.printStackTrace();
		}
	}

	private int searchHome(String homeID) throws InvalidPositionException {
		int result = -1;
		boolean found = false;
		for (int i = 0; i < homes.getSize() && !found; i++) {
			if (homes.get(i).getHomeId().equals(homeID)) {
				result = i;
				found = true;
			}
		}
		return result;

	}

	private int searchUsers(String userID) throws InvalidPositionException {
		int result = -1;
		boolean found = false;
		for (int i = 0; i < users.getSize() && !found; i++) {
			if (homes.get(i).getUserId().equals(userID)) {
				result = i;
				found = true;
			}
		}
		return result;

	}

	// TODO 4 iteradores. 1 metodo de sort por int. Serialize. Merda gay da
	// eficiencia das procuras.

}