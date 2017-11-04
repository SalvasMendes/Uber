package uber;

import java.io.*;
import java.util.InputMismatchException;

import eds.*;
import exceptions.*;
import home.*;
import sort.*;
import user.*;

/**
 * 
 * @author Salvador Mendes (50503) sr.mendes@campus.fct.unl.pt
 * @author Miguel Candeias (50647) mb.candeias@campus.fct.unl.pt
 *
 */
public class UberClass implements UberInterface {

	private Map<String, UserInterface> users;
	private Map<String, Home> homes;

	@SuppressWarnings("unchecked")
	public UberClass() {
		// TODO : VERIFICAR SE TODAS AS CENAS SAO SERIALIZAVEIS
		try {
			FileInputStream fileIn = new FileInputStream("users.ser");
			ObjectInputStream objIn = new ObjectInputStream(fileIn);
			users = (Map<String, UserInterface>) (objIn.readObject());
			objIn.close();
			fileIn.close();
		} catch (FileNotFoundException fnf) {
			users = new MapClass<String, UserInterface>();
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
			homes = (Map<String, Home>) objIn.readObject();
			objIn.close();
			fileIn.close();
		} catch (FileNotFoundException fnf) {
			homes = new MapClass<String, Home>();
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

		if (users.exists(userId))
			throw new UserExistException();
		else {
			UserInterface user = new UserClass(userId, email, phone, name, address, nationality);
			users.add(userId, user);
		}
	}

	public void createHome(String homeId, String userId, int price, int cap, String local, String description,
			String address) throws UserInexistantException, PropertyExistsException, InvalidPositionException {

		if (cap > 20 || cap < 1 || price < 0) {
			throw new InputMismatchException();
		} else if (!users.exists(userId)) {
			throw new UserInexistantException();
		} else if (homes.exists(homeId)) {
			throw new PropertyExistsException();
		} else {

			UserInterface owner = users.find(userId);
			Home home = new HomeClass(homeId, userId, local, description, address, price, cap, owner);
			homes.add(homeId, home);
			users.find(userId).createHome(home);
		}
	}

	public void alterUser(String userId, String email, String phone, String address)
			throws UserInexistantException, InvalidPositionException {

		if (!users.exists(userId))
			throw new UserInexistantException();
		else {
			users.find(userId).alterUser(email, phone, address);
		}
	}

	public void removeUser(String userId)
			throws UserInexistantException, UserHasPlaceException, EmptyListException, InvalidPositionException {

		if (!users.exists(userId)) {
			throw new UserInexistantException();
		} else if (users.find(userId).hasHomes()) {
			throw new UserHasPlaceException();
		} else {
			users.remove(userId);
		}
	}

	public UserInterface userInfo(String userId) throws UserInexistantException, InvalidPositionException {
		if (!users.exists(userId))
			throw new UserInexistantException();
		else {
			return users.find(userId);
		}
	}

	public void removeHome(String homeId)
			throws PropertyInexistantException, PropertyVisitedException, EmptyListException, InvalidPositionException {

		if (!homes.exists(homeId))
			throw new PropertyInexistantException();

		else if (homes.find(homeId).isVisited())
			throw new PropertyVisitedException();

		else {
			users.find(homes.find(homeId).getUserId()).removeHome(homeId);
			homes.remove(homeId);
		}
	}

	public Home homeInfo(String homeId) throws PropertyInexistantException, InvalidPositionException {
		if (!homes.exists(homeId))
			throw new PropertyInexistantException();

		return homes.find(homeId);
	}

	public void addStay(String userId, String homeId, int points) throws DadosInvalidosException,
			UserInexistantException, PropertyInexistantException, TravellerIsHostException, InvalidPositionException {

		if (points < 0) {
			throw new DadosInvalidosException();

		} else {

			if (!users.exists(userId)) {
				throw new UserInexistantException();

			} else {

				if (!homes.exists(homeId))
					throw new PropertyInexistantException();

				else if (users.find(userId).hasHome(homeId))
					throw new TravellerIsHostException();

				else {
					Home home = homes.find(homeId);
					home.addScore(points);
					users.find(userId).addStay(home);
				}
			}
		}
	}

	public void addStayNoPoints(String userId, String homeId) throws UserInexistantException,
			PropertyInexistantException, TravellerIsNotHostException, InvalidPositionException {

		if (users.exists(userId)) {
			throw new UserInexistantException();

		} else {

			UserInterface user = users.find(userId);

			if (homes.exists(homeId))
				throw new PropertyInexistantException();

			else if (!(user.hasHome(homeId)))
				throw new TravellerIsNotHostException();

			else {
				user.addStay(homes.find(homeId));
			}
		}
	}

	public void saveStatus() {
		try {
			FileOutputStream fileOut = new FileOutputStream("users.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(users);
			out.close();
			fileOut.close();
		} catch (IOException i) {
			i.printStackTrace();
		}

		try {
			FileOutputStream fileOut = new FileOutputStream("homes.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(homes);
			out.close();
			fileOut.close();
		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	public HashTableIterator<String, Home> platformHousesIterator(int people, String local)
			throws InputMismatchException, InvalidPositionException, EmptyListException {

		Map<String, Home> tempMap = new MapClass<String, Home>(users.getSize());
		HashTableIterator<String, Home> it = homes.iterate();
		
		while (it.hasNext()) {
			Home home = it.next().getObject();
			if (home.getCap() >= people && home.getLocal().equalsIgnoreCase(local)) {
				tempMap.add(home.getHomeId(), home);
			}
		}

		if (people < 1 || people > 20) {
			throw new InputMismatchException();

		} else {
			
			return tempMap.iterate();
		}
	}

	public TwoWayIterator<Home> hostedHomesIterator(String userId)
			throws UserInexistantException, UserHasNoHomeException, InvalidPositionException, EmptyListException {

		if (!users.exists(userId))
			throw new UserInexistantException();

		else if (!users.find(userId).hasHomes())
			throw new UserHasNoHomeException();

		else {
			return users.find(userId).hostedHomesIterator();
		}
	}

	public TwoWayIterator<Home> travalledHomesIterator(String userId)
			throws UserInexistantException, UserNotTravalledException, InvalidPositionException, EmptyListException {


		if (!users.exists(userId))
			throw new UserInexistantException();

		else if (!users.find(userId).hasTravelled())
			throw new UserNotTravalledException();
		else {
			return users.find(userId).travalledHomesIterator();
		}
	}

	public HashTableIterator<String, Home> bestHomesIterator(String local) throws InvalidPositionException, EmptyListException {
		
		Map<String, Home> tempMap = new MapClass<String, Home>(users.getSize());
		HashTableIterator<String, Home> it = homes.iterate();
		
		while (it.hasNext()) {
			Home home = it.next().getObject();
			if (home.getLocal().equalsIgnoreCase(local)) {
				tempMap.add(home.getHomeId(), home);
			}
		}

		return tempMap.iterate();
	}
}
