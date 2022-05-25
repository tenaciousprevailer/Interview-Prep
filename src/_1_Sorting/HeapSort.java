package _1_Sorting;

import util.AlgoUtil;

import java.util.Comparator;

public class HeapSort implements Sorter<Integer> {

	private Comparator<Integer> comparator;

	public HeapSort(Comparator<Integer> comparator) {
		this.comparator = comparator;
	}

	@Override
	public void sort(Integer[] arr) {
		
		// convert to max heap
		int lastElemIdx = arr.length - 1;
		int heapifyToStartFromIdx = (lastElemIdx - 1)  / 2;
		
		for(int i=heapifyToStartFromIdx; i > -1; i--) {
			heapify(arr,i, lastElemIdx);
		}
		
		
		// for 0->n-1 elemts
			// swap max elem(0th index) with n-i elem
			// heapify the array 0 -> (n-i)
		
		for(int i=0;i<lastElemIdx;i++) {
			AlgoUtil.swapArray(arr, 0, lastElemIdx-i);
			heapify(arr,0, lastElemIdx-i-1);
		}
		
	}

	private void heapify(Integer[] arr, int rootNodeIdx, int lastElemIdx) {

		int leftChildIdx = (rootNodeIdx * 2) +1;

		if(leftChildIdx <= lastElemIdx) {
			int maxElemIdx = leftChildIdx;
			int rightChildIdx = leftChildIdx + 1;
			if(rightChildIdx <= lastElemIdx) {
				maxElemIdx = Math.max(arr[leftChildIdx], arr[rightChildIdx]) == arr[leftChildIdx]? leftChildIdx :rightChildIdx;
			} 
			
			if(arr[maxElemIdx] > arr[rootNodeIdx]) {
				AlgoUtil.swapArray(arr, maxElemIdx, rootNodeIdx);
				heapify(arr,maxElemIdx, lastElemIdx);
			}
		}
	}

}
