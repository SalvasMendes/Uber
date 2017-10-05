package sort;

import eds.DLList;
import eds.InvalidPositionException;
import home.Home;

public interface QuickSort {

	void sortScore(DLList<Home> homes) throws InvalidPositionException;

	void sortID(DLList<Home> homes) throws InvalidPositionException;

}