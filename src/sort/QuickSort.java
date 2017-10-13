package sort;

import eds.DLList;
import eds.InvalidPositionException;
import home.Home;

/**
 * 
 * @author Salvador Mendes (50503) sr.mendes@campus.fct.unl.pt
 * @author Miguel Candeias (50647) mb.candeias@campus.fct.unl.pt
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