package _1_Sorting;

import java.util.Comparator;

public class MergeSort implements Sorter<Integer>{

	private Comparator<Integer> comparator;
	
	public MergeSort(Comparator<Integer> comparator) {
		this.comparator = comparator;
	}
	
	@Override
	public void sort(Integer[] arr) {
		
		sort(arr, 0, arr.length-1);
		
	}
	
	private void sort(Integer[] arr, int l, int r) {
		
		if(l<r) {
			int m = (l+r)/2;
			sort(arr, l, m);
			sort(arr, m+1, r);
			merge(arr, l, m , r);
		}
		
	}

	private void merge(Integer[] arr, int l, int m, int r) {
		
		// create two arrays
		int[] arr1 = new int[m-l+1];
		int[] arr2 = new int[r-m];
		
		//copy elements into the two
		int tmpCounter = l;
		for(int i=0;i<arr1.length;i++) {
			arr1[i] = arr[tmpCounter++];
		}
		
		tmpCounter = m+1; // not rquird
		for(int i=0;i<arr2.length;i++) {
			arr2[i] = arr[tmpCounter++];
		}
		
		// for both arrays
		int arr1C = 0, arr2C = 0, arrC = l;
		while(arr1C < arr1.length && arr2C < arr2.length) {
			if(arr1[arr1C] < arr2[arr2C]) {
				arr[arrC++] = arr1[arr1C++];
			} else {
				arr[arrC++] = arr2[arr2C++];
			}
		}
		
		
		// for arr1
		while(arr1C < arr1.length) {
			arr[arrC++] = arr1[arr1C++];
		}
		
		// for arr2
		while(arr2C < arr2.length) {
			arr[arrC++] = arr2[arr2C++];
		}
	}

}
