package home;

import user.*;

/**
 * 
 * @author 50503_50647
 *
 */
public interface Home {

	/**
	 * This method rates a house
	 * 
	 * @param score
	 */
	void addScore(int score);

	/**
	 * 
	 * @return home id
	 */
	String getHomeId();

	/**
	 * 
	 * @return user id
	 */
	String getUserId();

	/**
	 * 
	 * @return House local
	 */
	String getLocal();

	/**
	 * 
	 * @return A description of the house
	 */
	String getDescription();

	/**
	 * 
	 * @return Adress of the house
	 */
	String getAddress();

	/**
	 * 
	 * @return price of the house
	 */
	int getPrice();

	/**
	 * 
	 * @return house capacity
	 */
	int getCap();

	/**
	 * 
	 * @return House score
	 */
	int getScore();

	/**
	 * Turns a house that was not visited into a visited one
	 */
	void visitederino();

	/**
	 * 
	 * @return true in case house is visited
	 */
	boolean isVisited();

	/**
	 * 
	 * @return Owner of the property
	 */
	UserInterface getOwner();

}