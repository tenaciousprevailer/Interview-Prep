package _1_Sorting;

import java.util.Comparator;

public class InsertionSort implements Sorter<Integer>{

	private Comparator<Integer> comparator;
	
	public InsertionSort(Comparator<Integer> comparator) {
		this.comparator = comparator;
	}

	@Override
	public void sort(Integer[] arr) {
		for(int i=1; i<arr.length; i++) {
			int curVal = arr[i];
			int j = i-1;
			while(j>-1 && curVal < arr[j]) {
				arr[j+1] = arr[j];
				arr[j] = curVal;
				
				j--;
			}
		}
		
	}


}
