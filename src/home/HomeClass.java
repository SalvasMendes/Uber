package home;

import user.*;

public class HomeClass implements Home {

	private String homeId, userId, local, description, address;
	private int price, cap, score;
	private boolean visited;
	private UserInterface owner;

	public HomeClass(String homeId, String userId, String local, String description, String address, int price,
			int cap, UserInterface owner) {

		this.homeId = homeId;
		this.userId = userId;
		this.local = local;
		this.description = description;
		this.address = address;
		this.price = price;
		this.cap = cap;
		this.owner = owner;
		score = 0;
		visited = false;
	}
	
	public UserInterface getOwner() {
		return owner;
	}

	public boolean isVisited(){
		return visited;
	}
	
	public void visitederino(){
		visited = true;
	}

	public void addScore(int score) {
		this.score += score;

	}

	public String getHomeId() {
		return homeId;
	}

	public String getUserId() {
		return userId;
	}

	public String getLocal() {
		return local;
	}

	public String getDescription() {
		return description;
	}

	public String getAddress() {
		return address;
	}

	public int getPrice() {
		return price;
	}

	public int getCap() {
		return cap;
	}

	public int getScore() {
		return score;
	}

}
