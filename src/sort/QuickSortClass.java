package sort;

import eds.*;
import home.*;

/**
 * 
 * @author Salvador Mendes (50503) sr.mendes@campus.fct.unl.pt
 * @author Miguel Candeias (50647) mb.candeias@campus.fct.unl.pt
 *
 */
public class QuickSortClass implements QuickSort {

	private DLList<Home> tempList;
	private int size;

	public void sortScore(DLList<Home> homes) throws InvalidPositionException, EmptyListException {
		if (homes == null || homes.getSize() <= 1) {
			return;
		} else {
			tempList = homes;
			size = homes.getSize();
			scoreSort(0, size - 1);
		}
	}

	public void sortID(DLList<Home> homes) throws InvalidPositionException, EmptyListException {
		if (homes == null || homes.getSize() <= 1) {
			return;
		} else {
			tempList = homes;
			size = homes.getSize();
			idSort(0, size - 1);
		}
	}

	private void scoreSort(int start, int end) throws InvalidPositionException, EmptyListException {
		int l = start;
		int h = end;

		int pivot = tempList.get((l + (h - l)) / 2).getScore();

		while (l <= h) {
			while (tempList.get(l).getScore() < pivot) {
				l++;
			}
			while (tempList.get(h).getScore() > pivot) {
				h--;
			}
			if (l <= h) {
				tempList.swapNode(l, h);
				l++;
				h--;
			}
		}
		if (start < l)
			scoreSort(start, l);
		if (h < end)
			scoreSort(h, end);
	}

	private void idSort(int start, int end) throws InvalidPositionException, EmptyListException {
		int l = start;
		int h = end;

		String pivot = tempList.get((l + (h - l)) / 2).getHomeId();

		while (l <= h) {
			while (tempList.get(l).getHomeId().compareTo(pivot) < 0) {
				l++;
			}
			while (tempList.get(l).getHomeId().compareTo(pivot) > 0) {
				h--;
			}
			if (l <= h) {
				tempList.swapNode(l, h);
				l++;
				h--;
			}
		}
		if (start < l)
			idSort(start, l);
		if (h < end)
			idSort(h, end);
	}

}