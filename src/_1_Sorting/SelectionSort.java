package _1_Sorting;

import java.util.Comparator;

import util.AlgoUtil;

public class SelectionSort implements Sorter<Integer>{

	private Comparator<Integer> comparator;
	
	public SelectionSort(Comparator<Integer> comparator) {
		this.comparator = comparator;
	}
	
	@Override
	public void sort(Integer[] array) {
		
		for(int i=0; i<array.length;i++) {
			int ithMinIndex = findMinValueIndex(array, i, array.length-1);
			if(i != ithMinIndex) {
				AlgoUtil.swapArray(array, i, ithMinIndex);
			}
		}
		
	}
	
	private int findMinValueIndex(Integer[] arr, int start, int end) {
		int minVal=arr[start],minValIndex=start;
		int compareVal;
		for(int i=start;i<=end;i++) {
			compareVal = comparator.compare(arr[i], minVal);
			if(compareVal < 0) {
				minVal = arr[i];
				minValIndex = i;
			}
		}
		return minValIndex;
	}
	
//	public void sort(Integer[] arr) {
//		
//		for(int i=0; i < arr.length - 1; i++) {
//			int indexOfMin = getIndexOfMin(arr,i);
//			swap(arr, i, indexOfMin);
//		}
//		
//	}
//
//	private void swap(Integer[] arr, int i, int indexOfMin) {
//		int tmp = arr[i];
//		arr[i] = arr[indexOfMin];
//		arr[indexOfMin] = tmp;
//	}
//	
//	private int getIndexOfMin(Integer[] arr, int startPos) {
//		int indexOfMin = startPos;
//		for(int i=startPos+1 ; i< arr.length; i++) {
//			if(arr[indexOfMin] > arr[i]) {
//				indexOfMin = i;
//			}
//		}
//		return indexOfMin;
//	}

}
