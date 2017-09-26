package home;

public class HomeClass implements HomeInterface{

	private String homeId, userId, local,  description, address;
	private int price, cap, score;
	
	public HomeClass(String homeId, String userId, String local, String description, String address, int price,
			int cap) {
		this.homeId = homeId;
		this.userId = userId;
		this.local = local;
		this.description = description;
		this.address = address;
		this.price = price;
		this.cap = cap;
		score = 0;
	}

	@Override
	public void addScore(int score) {
		this.score += score;
		
	}
	
	
	
}
