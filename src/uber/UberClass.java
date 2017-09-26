package uber;

import eds.*;
import home.*;
import user.*;

public class UberClass implements UberInterface {

	private Stack<UserInterface> users;
	private Stack<HomeInterface> homes;

	public UberClass() {
		Stack<HomeInterface> homes = new Stack<HomeInterface>();
		Stack<UserInterface> users = new Stack<UserInterface>();
	}

	@Override
	public void createUser(String userId, String email, String phone, String name, String address, String nationality) {
		UserInterface user = new UserClass(userId, email, phone, name, address, nationality);
		users.push(user);

	}

	@Override
	public void createHome(String homeId, String userId, int price, int cap, String local, String description,
			String address) {
		HomeInterface home = new HomeClass(homeId, userId, local, description, address, price, cap);
		homes.push(home);

	}

	@Override
	public void alterUser(String userId, String email, String phone, String name, String address, String nationality) {
		users.top().alterUser(email, phone, name, address, nationality);

	}

	@Override
	public void removeUser(String userId) {
		users.pop();

	}

	@Override
	public UserInterface userInfo(String userId) {
		return users.top();
		
	}

	@Override
	public void removeHome(String homeId) {
		homes.pop();

	}

	@Override
	public HomeInterface homeInfo(String homeId) {
		return homes.top();
	}

	@Override
	public void addScore(String homeId, String userId, int score) {
		//Excepcoes
		homes.top().addScore(score);
		
	}
}
