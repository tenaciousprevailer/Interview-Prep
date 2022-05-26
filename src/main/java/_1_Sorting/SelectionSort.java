package _1_Sorting;

import util.AlgoUtil;

import java.util.Comparator;

public class SelectionSort implements Sorter<Integer>{

	private final Comparator<Integer> comparator;
	
	public SelectionSort(Comparator<Integer> comparator) {
		this.comparator = comparator;
	}
	
	@Override
	public void sort(Integer[] array) {
		for (int i = 0; i < array.length - 1; i++) {
			int minIdx = i;
			for (int j = i + 1; j < array.length; j++) {
				if (array[minIdx] > array[j] ) {
					minIdx = j;
				}
			}
			AlgoUtil.swapArray(array, minIdx, i);
		}
	}

}
