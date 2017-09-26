package user;

import eds.*;

public class UserClass implements UserInterface {

	private Stack<String> homes;
	private String userId, email, phone, name, address, nationality;

	public UserClass(String userId, String email, String phone, String name, String address, String nationality) {
		homes = new Stack<String>();
		this.userId = userId;
		this.email = email;
		this.phone = phone;
		this.name = name;
		this.address = address;
		this.nationality = nationality;
	}

	@Override
	public Stack<String> getHomes() {
		return homes;
	}

	@Override
	public String getUserId() {
		return userId;
	}

	@Override
	public String getEmail() {
		return email;
	}

	@Override
	public String getPhone() {
		return phone;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getAddress() {
		return address;
	}

	@Override
	public String getNationality() {
		return nationality;
	}

	@Override
	public void createHome(String homeId) {
		homes.push(homeId);
	}

	@Override
	public void removeHome(String homeId) {
		homes.pop();

	}

	@Override
	public void alterUser(String email, String phone, String name, String address, String nationality) {
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.nationality = nationality;
	}
}
