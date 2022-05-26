package _1_Sorting;

import util.AlgoUtil;

import java.util.Comparator;

public class InsertionSort implements Sorter<Integer>{

	private Comparator<Integer> comparator;
	
	public InsertionSort(Comparator<Integer> comparator) {
		this.comparator = comparator;
	}

	@Override
	public void sort(Integer[] arr) {
		for (int i = 1; i < arr.length; i++) {
			int j = i;
			while (j > 0 && arr[j] < arr[j-1]) {
				AlgoUtil.swapArray(arr, j, j-1);
				j--;
			}
		}
	}


}
