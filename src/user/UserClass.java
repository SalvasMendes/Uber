package user;

import eds.*;
import home.*;

public class UserClass implements UserInterface, java.io.Serializable {

	private static final long serialVersionUID = -94741892628226633L;
	private DLList<Home> hostedHomes;
	private DLList<Home> travelledHomes;
	private String userId, email, phone, name, address, nationality;

	public UserClass(String userId, String email, String phone, String name, String address, String nationality) {
		hostedHomes = new LinkedList<Home>();
		travelledHomes = new LinkedList<Home>();
		this.userId = userId;
		this.email = email;
		this.phone = phone;
		this.name = name;
		this.address = address;
		this.nationality = nationality;
	}

	public String getUserId() {
		return userId;
	}

	public String getEmail() {
		return email;
	}

	public String getPhone() {
		return phone;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public String getNationality() {
		return nationality;
	}

	public void createHome(Home home) {
		hostedHomes.addLast(home);
	}

	public void removeHome(String homeID) throws InvalidPositionException, EmptyListException {
		hostedHomes.remove(searchHome(homeID));
		// TODO refazer --> percorrer a lista 2 vezes

	}

	public boolean hasHome(String homeId) throws InvalidPositionException {
		return (searchHome(homeId) >= 0);

	}

	public TwoWayIterator<Home> hostedHomesIterator() throws InvalidPositionException, EmptyListException {
		return new TwoWayIteratorClass<Home>(hostedHomes.getFirst(), hostedHomes.getLast());

	}

	private int searchHome(String homeID) throws InvalidPositionException {
		int result = -1;
		boolean found = false;
		for (int i = 0; i < hostedHomes.getSize() && !found; i++) {
			if (hostedHomes.get(i).getHomeId().equals(homeID)) {
				result = i;
				found = true;
			}
		}
		return result;

	}

	public void addStay(Home home) {
		travelledHomes.addLast(home);
		home.visitederino();

	}

	public void alterUser(String email, String phone, String address) {
		this.email = email;
		this.phone = phone;
		this.address = address;
	}

	public boolean hasHomes() {
		return (hostedHomes.getSize() > 0);
	}

	public boolean hasTravelled() {
		return (travelledHomes.getSize() > 0);
	}

	public TwoWayIterator<Home> travalledHomesIterator() throws InvalidPositionException, EmptyListException {
		return new TwoWayIteratorClass<>(hostedHomes.getFirst(), travelledHomes.getLast());
	}

}
