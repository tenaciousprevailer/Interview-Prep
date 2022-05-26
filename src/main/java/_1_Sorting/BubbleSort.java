package _1_Sorting;

import java.util.Comparator;

import util.AlgoUtil;

public class BubbleSort implements Sorter<Integer> {

	private Comparator<Integer> comparator;

	public BubbleSort(Comparator<Integer> comparator) {
		this.comparator = comparator;
	}

	@Override
	public void sort(Integer[] arr) {
		for(int i=0;i<arr.length-1;i++) {
			for(int j=0;j<arr.length-i-1;j++) {
				if(arr[j] > arr[j+1]) {
					AlgoUtil.swapArray(arr, j, j + 1);
				}
			}
		}
	}
}
