package _1_Sorting;

import util.AlgoUtil;

import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;

public class QuickSortIterative implements Sorter<Integer>{

	private Comparator<Integer> comparator;

	public QuickSortIterative(Comparator<Integer> comparator) {
		this.comparator = comparator;
	}

	
	@Override
	public void sort(Integer[] array) {
		sort(array, 0, array.length-1);
	}

	private void sort(Integer[] array, int l, int r) {
		Deque<Integer> stack = new LinkedList<>();

		stack.push(l);
		stack.push(r);

		while(stack.size() > 0) {
			r = stack.pop();
			l = stack.pop();

			if(l<r) {
				int pivotIndex = putPivotOnCorrectPos(array, l, r);

				stack.push(l);
				stack.push(pivotIndex-1);

				stack.push(pivotIndex+1);
				stack.push(r);
			}
		}
	}


	// 2 10 4 6 8 5, pivot 5
	private int putPivotOnCorrectPos(Integer[] array, int l, int r) {

		int m = (l+r)/2;
		if(array[m] > array[l]) {
			if(array[m] < array[r]) // l m r
				AlgoUtil.swapArray(array, m , r);
			// else l r m
		} else if(array[l] < array[r]){ // m l r
			AlgoUtil.swapArray(array, l , r);
		}
		// else m r l

		int pivot = array[r];
		int leftPointer = l;
		for(int i=l; i<r;i++) {
			if(array[i] <= pivot) {
				AlgoUtil.swapArray(array, leftPointer, i);
				leftPointer++;
			}
		}
		AlgoUtil.swapArray(array, leftPointer, r);
		return leftPointer;
	}
	
}
