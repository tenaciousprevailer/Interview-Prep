package _1_Sorting;

import java.util.Arrays;
import java.util.Comparator;

public class MergeSort implements Sorter<Integer>{

	private Comparator<Integer> comparator;
	
	public MergeSort(Comparator<Integer> comparator) {
		this.comparator = comparator;
	}
	
	@Override
	public void sort(Integer[] arr) {
		if (arr.length > 1) {
			sort(arr, 0, arr.length - 1);
		}
	}

	private void sort(Integer[] arr, int l, int r) {
		if(l<r) {
			int m = (l+r)/2;
			sort(arr, l, m);
			sort(arr, m+1, r);
			merge(arr, l, m, m+1, r);
		}
	}

	// 0 3 7 -> left 0 1 2 3 , right 4 5 6 7
	private void merge(Integer[] arr, int leftL, int leftR, int rightL, int rightR) {

		Integer[] leftArr = Arrays.copyOfRange(arr, leftL, leftR + 1);
		Integer[] rightArr = Arrays.copyOfRange(arr, rightL, rightR + 1);

		int leftArrIdx = 0, rightArrIdx = 0, i = leftL;
		while(leftArrIdx < leftArr.length && rightArrIdx < rightArr.length) {
			if(leftArr[leftArrIdx] < rightArr[rightArrIdx]) {
				arr[i++] = leftArr[leftArrIdx++];
			} else {
				arr[i++] = rightArr[rightArrIdx++];
			}
		}

		while(leftArrIdx < leftArr.length) {
			arr[i++] = leftArr[leftArrIdx++];
		}

		while(rightArrIdx < rightArr.length) {
			arr[i++] = rightArr[rightArrIdx++];
		}
	}

}
