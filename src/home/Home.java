package home;

import user.*;

public interface Home {

	void addScore(int score);

	String getHomeId();

	String getUserId();

	String getLocal();

	String getDescription();

	String getAddress();

	int getPrice();

	int getCap();

	int getScore();
	
	void visitederino();
	
	boolean isVisited();
	
	UserInterface getOwner();

}