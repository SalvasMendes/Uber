package sort;

import eds.*;
import home.*;

public class QuickSortClass implements QuickSort {

	private DLList<Home> tempList;
	private int size;

	public void sortScore(DLList<Home> homes) throws InvalidPositionException {
		if (homes == null || homes.getSize() == 0) {
			return;
		}
		tempList = homes;
		size = homes.getSize();
		sort(0, size - 1);
	}

	private void sort(int start, int end) throws InvalidPositionException {
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
			sort(start, l);
		if (h < end)
			sort(h, end);
	}
}
