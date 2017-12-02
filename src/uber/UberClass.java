package uber;

import java.io.*;
import java.util.InputMismatchException;

import eds.*;
import exceptions.*;
import home.*;
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

	}

	public void createUser(String userId, String email, String phone, String name, String address, String nationality)
			throws UserExistException, InvalidPositionException {

		if (users.exists(userId.toLowerCase()))
			throw new UserExistException();
		else {
			UserInterface user = new UserClass(userId, email, phone, name, address, nationality);
			users.add(userId.toLowerCase(), user);
		}
	}

	public void createHome(String homeId, String userId, int price, int cap, String local, String description,
			String address) throws UserInexistantException, PropertyExistsException, InvalidPositionException {

		if (cap > 20 || cap < 1 || price < 0) {
			throw new InputMismatchException();
		} else if (!users.exists(userId.toLowerCase())) {
			throw new UserInexistantException();
		} else if (homes.exists(homeId.toLowerCase())) {
			throw new PropertyExistsException();
		} else {

			UserInterface owner = users.find(userId.toLowerCase());
			Home home = new HomeClass(homeId, userId, local, description, address, price, cap, owner);
			homes.add(homeId.toLowerCase(), home);
			users.find(userId.toLowerCase()).createHome(home);

		}
	}

	public void alterUser(String userId, String email, String phone, String address)
			throws UserInexistantException, InvalidPositionException {

		if (!users.exists(userId.toLowerCase()))
			throw new UserInexistantException();
		else {
			users.find(userId.toLowerCase()).alterUser(email, phone, address);
		}
	}

	public void removeUser(String userId)
			throws UserInexistantException, UserHasPlaceException, EmptyListException, InvalidPositionException {

		if (!users.exists(userId.toLowerCase())) {
			throw new UserInexistantException();
		} else if (users.find(userId.toLowerCase()).hasHomes()) {
			throw new UserHasPlaceException();
		} else {
			users.remove(userId.toLowerCase());
		}
	}

	public UserInterface userInfo(String userId) throws UserInexistantException, InvalidPositionException {
		if (!users.exists(userId.toLowerCase()))
			throw new UserInexistantException();
		else {
			return users.find(userId.toLowerCase());
		}
	}

	public void removeHome(String homeId)
			throws PropertyInexistantException, PropertyVisitedException, EmptyListException, InvalidPositionException {

		if (!homes.exists(homeId.toLowerCase()))
			throw new PropertyInexistantException();

		else if (homes.find(homeId.toLowerCase()).isVisited())
			throw new PropertyVisitedException();

		else {
			users.find(homes.find(homeId.toLowerCase()).getUserId()).removeHome(homeId.toLowerCase());
			homes.remove(homeId.toLowerCase());

		}
	}

	public Home homeInfo(String homeId) throws PropertyInexistantException, InvalidPositionException {
		if (!homes.exists(homeId.toLowerCase()))
			throw new PropertyInexistantException();

		return homes.find(homeId.toLowerCase());
	}

	public void addStay(String userId, String homeId, int points) throws DadosInvalidosException,
			UserInexistantException, PropertyInexistantException, TravellerIsHostException, InvalidPositionException {

		if (points <= 0 || points > 20) {
			throw new DadosInvalidosException();

		} else {

			if (!users.exists(userId.toLowerCase())) {
				throw new UserInexistantException();

			} else {

				if (!homes.exists(homeId.toLowerCase()))
					throw new PropertyInexistantException();

				else if (users.find(userId.toLowerCase()).hasHome(homeId.toLowerCase()))
					throw new TravellerIsHostException();

				else {
					Home home = homes.find(homeId.toLowerCase());
					home.addScore(points);
					home.visitederino();
					users.find(userId.toLowerCase()).addStay(home);

				}
			}
		}
	}

	public void addStayNoPoints(String userId, String homeId) throws UserInexistantException,
			PropertyInexistantException, TravellerIsNotHostException, InvalidPositionException {

		if (!users.exists(userId.toLowerCase())) {
			throw new UserInexistantException();

		} else {

			UserInterface user = users.find(userId);

			if (!homes.exists(homeId.toLowerCase()))
				throw new PropertyInexistantException();

			else if (!(user.hasHome(homeId.toLowerCase())))
				throw new TravellerIsNotHostException();

			else {
				user.addStay(homes.find(homeId.toLowerCase()));
				Home home = homes.find(homeId.toLowerCase());
				home.visitederino();
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

	public TreeIterator2<Integer, LBList<String, Home>> platformHousesIterator(int people, String local)
			throws InputMismatchException, InvalidPositionException, EmptyListException, EmptyStackException {
		if (homes.nbr() == 0) {
			throw new EmptyListException();
		} else {

			Map<Integer, LBList<String, Home>> tempMap = new BinarySearchTree<Integer, LBList<String, Home>>();
			HashTableIterator<String, Home> it = homes.iterate();

			while (it.hasNext()) {
				Home home = it.next().getObject();
				if (home.getCap() >= people && home.getLocal().equalsIgnoreCase(local)) {
					int cap = home.getCap();
					if (tempMap.find(cap) != null) {
						tempMap.find(cap).orderedAdd(home.getHomeId(), home);

					} else {
						LBList<String, Home> list = new LinkedBucketList<String, Home>();
						list.orderedAdd(home.getHomeId(), home);
						tempMap.add(cap, list);
					}
				}
			}

			if (people < 1 || people > 20) {
				throw new InputMismatchException();

			} else if (tempMap.getSize() == 0) {
				throw new EmptyListException();
			} else {
				return tempMap.iterator2();
			}
		}
	}

	public TreeIterator2<String, Home> hostedHomesIterator(String userId) throws UserInexistantException,
			UserHasNoHomeException, InvalidPositionException, EmptyListException, EmptyStackException {

		if (!users.exists(userId.toLowerCase()))
			throw new UserInexistantException();

		else if (!users.find(userId.toLowerCase()).hasHomes())
			throw new UserHasNoHomeException();

		else {
			return users.find(userId.toLowerCase()).hostedHomesIterator();
		}
	}

	public TwoWayIterator<Home> travalledHomesIterator(String userId)
			throws UserInexistantException, UserNotTravalledException, InvalidPositionException, EmptyListException {

		if (!users.exists(userId.toLowerCase()))
			throw new UserInexistantException();

		else if (!users.find(userId.toLowerCase()).hasTravelled())
			throw new UserNotTravalledException();
		else {
			return users.find(userId.toLowerCase()).travalledHomesIterator();
		}
	}

	public TreeIterator<Integer, Map<String, Home>> bestHomesIterator(String local)
			throws InvalidPositionException, EmptyListException, EmptyStackException {

		if (homes.nbr() == 0) {
			throw new EmptyListException();
		} else {

			HashTableIterator<String, Home> it = homes.iterate();
			Map<Integer, Map<String, Home>> tempMap = new BinarySearchTree<Integer, Map<String, Home>>();

			while (it.hasNext()) {
				Home home = it.next().getObject();
				if (home.getLocal().equalsIgnoreCase(local.toLowerCase())) {
					int score = home.getScore();
					if (tempMap.find(score) != null) {
						tempMap.find(score).add(home.getHomeId().toLowerCase(), home);

					} else {
						Map<String, Home> map = new BinarySearchTree<String, Home>();
						map.add(home.getHomeId().toLowerCase(), home);
						tempMap.add(score, map);
					}
				}
			}
			if (tempMap.getSize() == 0) {
				throw new EmptyListException();
			} else {
				return tempMap.iterator();
			}
		}
	}
}
