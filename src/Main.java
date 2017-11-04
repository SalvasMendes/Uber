import java.util.InputMismatchException;
import java.util.Scanner;

import eds.*;
import exceptions.*;
import home.*;
import uber.*;
import user.*;
/**
 * 
 * @author Salvador Mendes (50503) sr.mendes@campus.fct.unl.pt
 * @author Miguel Candeias (50647) mb.candeias@campus.fct.unl.pt
 *
 */
public class Main {

	// Main commands
	private static final String QUIT = "XS";
	private static final String REGIST_USER = "IU";
	private static final String EDIT_USER = "UU";
	private static final String REMOVE_USER = "RU";
	private static final String CHECK_USER = "GU";
	private static final String ADD_HOUSE = "AH";
	private static final String REMOVE_HOUSE = "RH";
	private static final String CHECK_HOUSE = "GH";
	private static final String ADD_STAY = "AT";
	private static final String LIST_HOST = "LH";
	private static final String LIST_TRAVELLER = "LT";
	private static final String SEARCH_PROPERTY = "PH";
	private static final String LIST_BEST = "LB";

	// Strings
	private static final String EXIT = "Gravando e terminando...";
	private static final String USER_CR = "Insercao de utilizador com sucesso.";
	private static final String UPDATE = "Utilizador atualizado com sucesso.";
	private static final String REMOVE_USERR = "Utilizador removido com sucesso.";
	private static final String CHECK_USERR = "%s: %s, %s, %s, %s\n";
	private static final String REMOV_HOUSE = "Propriedade removida com sucesso.";
	private static final String CREAT_HOUSE = "Propriedade adicionada com sucesso.";
	private static final String CHEK_HOUSE = "%s: %s, %s, %d, %d, %d, %s\n";
	private static final String STAY = "Estadia adicionada com sucesso.";
	private static final String LIST = "%s %s %s %s %d %d %d\n";
	private static final String EXE = "Dados invalidos.";

	public static void main(String[] args) throws InvalidPositionException, UserInexistantException,
			PropertyInexistantException, TravellerIsHostException, TravellerIsNotHostException {
		Scanner in = new Scanner(System.in);
		UberInterface ub = new UberClass();
		String op = getCommand(in);

		while (!op.equals(QUIT)) {
			switch (op) {
			case REGIST_USER:
				registUser(in, ub);
				break;
			case EDIT_USER:
				editUser(in, ub);
				break;
			case REMOVE_USER:
				removeUser(in, ub);
				break;
			case CHECK_USER:
				checkUser(in, ub);
				break;
			case ADD_HOUSE:
				addHouse(in, ub);
				break;
			case REMOVE_HOUSE:
				removeHouse(in, ub);
				break;
			case CHECK_HOUSE:
				checkHouse(in, ub);
				break;
			case ADD_STAY:
				addStay(in, ub);
				break;
			case LIST_HOST:
				listHost(in, ub);
				break;
			case LIST_TRAVELLER:
				listTraveller(in, ub);
				break;
			case SEARCH_PROPERTY:
				searchProperty(in, ub);
				break;
			case LIST_BEST:
				listBest(in, ub);
				break;
			}
			System.out.println();
			op = getCommand(in);
		}
		System.out.println(EXIT);
		System.out.println();
		ub.saveStatus();
		in.close();

	}

	private static void listBest(Scanner in, UberInterface ub) {
		String local = in.nextLine().trim();

		try {
			HashTableIterator<String, Home> it = ub.bestHomesIterator(local);
			while (it.hasNext()) {
				Home home = it.next().getObject();

				System.out.printf(LIST, home.getHomeId(), home.getDescription(), home.getAddress(), home.getLocal(),
						home.getPrice(), home.getCap(), home.getScore());

			}

		} catch (EmptyListException e) {
			System.out.println(e.getMessage());

		} catch (InvalidPositionException e) {
			System.out.println(e.getMessage());
		}

	}

	private static void searchProperty(Scanner in, UberInterface ub) {

		try {
			int people = in.nextInt();
			String local = in.nextLine().trim();

			HashTableIterator<String, Home> it = ub.platformHousesIterator(people, local);
			while (it.hasNext()) {
				Home home = it.next().getObject();

				System.out.printf(LIST, home.getHomeId(), home.getDescription(), home.getAddress(), home.getLocal(),
						home.getPrice(), home.getCap(), home.getScore());
			}

		} catch (InputMismatchException e) {
			System.out.println(EXE);
			in.nextLine();

		} catch (InvalidPositionException e) {
			System.out.println(e.getMessage());

		} catch (EmptyListException e) {
			System.out.println(e.getMessage());
		}

	}

	private static void listTraveller(Scanner in, UberInterface ub) {
		String userId = in.next();
		in.nextLine();

		try {
			TwoWayIterator<Home> it = ub.travalledHomesIterator(userId);
			it.fullForward();
			while (it.hasPrevious()) {
				Home home = it.previous();
				System.out.printf(LIST, home.getHomeId(), home.getDescription(), home.getAddress(), home.getLocal(),
						home.getPrice(), home.getCap(), home.getScore());
			}
		} catch (UserInexistantException e) {
			System.out.println(e.getMessage());
		} catch (UserNotTravalledException e) {
			System.out.println(e.getMessage());
		} catch (InvalidPositionException e) {
			System.out.println(e.getMessage());
		} catch (EmptyListException e) {
			System.out.println(e.getMessage());

		}

	}

	private static void listHost(Scanner in, UberInterface ub) {
		String userId = in.next();
		in.nextLine();

		try {
			TwoWayIterator<Home> it = ub.hostedHomesIterator(userId);
			while (it.hasNext()) {
				Home home = it.next();
				System.out.printf(LIST, home.getHomeId(), home.getDescription(), home.getAddress(), home.getLocal(),
						home.getPrice(), home.getCap(), home.getScore());
			}
		} catch (UserInexistantException e) {
			System.out.println(e.getMessage());
		} catch (UserHasNoHomeException e) {
			System.out.println(e.getMessage());
		} catch (InvalidPositionException e) {
			System.out.println(e.getMessage());
		} catch (EmptyListException e) {
			System.out.println(e.getMessage());

		}

	}

	private static void addStay(Scanner in, UberInterface ub) throws InvalidPositionException, UserInexistantException,
			PropertyInexistantException, TravellerIsHostException, TravellerIsNotHostException {

		String userId = in.next();
		String homeId = in.next();
		String tempPoint = in.nextLine();
		int points;
		try {
			points = Integer.parseInt(tempPoint.trim());
			ub.addStay(userId, homeId, points);
			System.out.println(STAY);
		} catch (NumberFormatException nfe) {
			try {
				ub.addStayNoPoints(userId, homeId);
				System.out.println(STAY);
			} catch (InvalidPositionException e) {
				System.out.println(e.getMessage());
			} catch (PropertyInexistantException e) {
				System.out.println(e.getMessage());
			} catch (UserInexistantException e) {
				System.out.println(e.getMessage());
			} catch (TravellerIsNotHostException e) {
				System.out.println(e.getMessage());
			}
		} catch (InvalidPositionException e) {
			System.out.println(e.getMessage());
		} catch (PropertyInexistantException e) {
			System.out.println(e.getMessage());
		} catch (UserInexistantException e) {
			System.out.println(e.getMessage());
		} catch (TravellerIsHostException e) {
			System.out.println(e.getMessage());
		} catch (DadosInvalidosException e) {
			System.out.println(e.getMessage());
		}

	}

	private static void checkHouse(Scanner in, UberInterface ub) {
		String homeId = in.next();
		in.nextLine();
		Home home = null;
		try {
			home = ub.homeInfo(homeId);
			System.out.printf(CHEK_HOUSE, home.getDescription(), home.getAddress(), home.getLocal(), home.getPrice(),
					home.getCap(), home.getScore(), home.getOwner().getName());
		} catch (InvalidPositionException e) {
			System.out.println(e.getMessage());
		} catch (PropertyInexistantException e) {
			System.out.println(e.getMessage());

		}
	}

	private static void removeHouse(Scanner in, UberInterface ub) {
		String homeId = in.next();
		in.nextLine();
		try {
			ub.removeHome(homeId);
			System.out.println(REMOV_HOUSE);
		} catch (EmptyListException e) {
			System.out.println(e.getMessage());
		} catch (InvalidPositionException e) {
			System.out.println(e.getMessage());
		} catch (PropertyInexistantException e) {
			System.out.println(e.getMessage());
		} catch (PropertyVisitedException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void addHouse(Scanner in, UberInterface ub) {
		try {

			String homeId = in.next();
			String userId = in.next();
			int price = in.nextInt();
			int cap = in.nextInt();
			String local = in.nextLine().trim();
			String description = in.nextLine();
			String address = in.nextLine();

			ub.createHome(homeId, userId, price, cap, local, description, address);
			System.out.println(CREAT_HOUSE);
		} catch (InputMismatchException e) {
			System.out.println(EXE);
			in.nextLine();
		} catch (InvalidPositionException e) {
			System.out.println(e.getMessage());
		} catch (PropertyExistsException e) {
			System.out.println(e.getMessage());
		} catch (UserInexistantException e) {
			System.out.println(e.getMessage());
		}

	}

	private static void checkUser(Scanner in, UberInterface ub) {
		String userId = in.next();
		in.nextLine();
		UserInterface user = null;
		try {
			user = ub.userInfo(userId);
			System.out.printf(CHECK_USERR, user.getName(), user.getAddress(), user.getNationality(), user.getEmail(),
					user.getPhone());
		} catch (InvalidPositionException e) {
			System.out.println(e.getMessage());
		} catch (UserInexistantException e) {
			System.out.println(e.getMessage());
		}

	}

	private static void removeUser(Scanner in, UberInterface ub) {
		String userId = in.next();
		in.nextLine();
		try {
			ub.removeUser(userId);
			System.out.println(REMOVE_USERR);
		} catch (EmptyListException e) {
			System.out.println(e.getMessage());
		} catch (InvalidPositionException e) {
			System.out.println(e.getMessage());
		} catch (UserInexistantException e) {
			System.out.println(e.getMessage());
		} catch (UserHasPlaceException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void editUser(Scanner in, UberInterface ub) {
		String userId = in.next();
		String email = in.next();
		String phone = in.next();
		in.nextLine();
		String address = in.nextLine();
		try {
			ub.alterUser(userId, email, phone, address);
			System.out.println(UPDATE);
		} catch (InvalidPositionException e) {
			System.out.println(e.getMessage());
		} catch (UserInexistantException e) {
			System.out.println(e.getMessage());
		}
	}

	private static void registUser(Scanner in, UberInterface ub) {

		String userId = in.next();
		String email = in.next();
		String phone = in.next();
		String name = in.nextLine().trim();
		String nationality = in.nextLine();
		String address = in.nextLine();
		try {
			ub.createUser(userId, email, phone, name, address, nationality);
			System.out.println(USER_CR);
		} catch (UserExistException e) {
			System.out.println(e.getMessage());
		} catch (InvalidPositionException e) {
			System.out.println(e.getMessage());
		}
	}

	private static String getCommand(Scanner in) {
		String s;
		s = in.next().toUpperCase();
		return s;
	}

}