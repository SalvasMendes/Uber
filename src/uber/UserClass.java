package uber;

import eds.*;


/**
 * 
 * @author Salvador Mendes (50503) sr.mendes@campus.fct.unl.pt
 * @author Miguel Candeias (50647) mb.candeias@campus.fct.unl.pt
 *
 */
public class UserClass implements UserInterface, java.io.Serializable {

	private static final long serialVersionUID = 657L;
	private Map<String, Home> hostedHomes;
	private DLList<Home> travelledHomes;
	private String userId, email, phone, name, address, nationality;

	public UserClass(String userId, String email, String phone, String name, String address, String nationality) {
		hostedHomes = new BinarySearchTree<String, Home>();
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

	public void createHome(Home home) throws InvalidPositionException {
		hostedHomes.add(home.getHomeId().toLowerCase(), home);
	}

	public void removeHome(String homeID) throws InvalidPositionException, EmptyListException {
		hostedHomes.remove(homeID);
	}

	public boolean hasHome(String homeId) throws InvalidPositionException {
		return (hostedHomes.find(homeId) != null);
	}

	public TreeIterator2<String, Home> hostedHomesIterator() throws InvalidPositionException, EmptyListException, EmptyStackException {
		
		return hostedHomes.iterator2();
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
		return new TwoWayIteratorClass<>(travelledHomes.getFirst(), travelledHomes.getLast());
	}

}
