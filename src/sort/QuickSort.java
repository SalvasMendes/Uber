package sort;

import eds.DLList;
import eds.InvalidPositionException;
import home.Home;

/**
 * 
 * @author 50503_50647
 *
 */
public interface QuickSort {
	
	/**	
	 * This method sorts the properties by the best score
	 * @param homes
	 * @throws InvalidPositionException
	 */
	void sortScore(DLList<Home> homes) throws InvalidPositionException;
	
	/**
	 * This method sorts the IDs
	 * @param homes
	 * @throws InvalidPositionException
	 */
	void sortID(DLList<Home> homes) throws InvalidPositionException;

}