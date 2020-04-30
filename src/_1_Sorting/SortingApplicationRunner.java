package _1_Sorting;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import util.AlgoUtil;
import util.IntegerComparator;

public class SortingApplicationRunner {

	private static List<Class<? extends Sorter<Integer>>> sorter = Collections.unmodifiableList(
			Arrays.asList(
					BubbleSort.class,
					SelectionSort.class,
					InsertionSort.class,
					MergeSort.class,
					HeapSort.class,
					QuickSort.class
					)
			);
	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {

		int testCases = 200;
		for(int i=0;i<testCases;i++) {
			boolean testCasesPassed = false;
			Integer[] arr = AlgoUtil.getUnsortedIntegerArray(100);
			IntegerComparator ic = new IntegerComparator();
			for(Class<? extends Sorter<Integer>> c : sorter) {
				Integer[] newArr = Arrays.copyOf(arr, arr.length);
				c.getDeclaredConstructor(Comparator.class).newInstance(ic).sort(newArr);
				testCasesPassed = AlgoUtil.isIntegerArraySorted(newArr, ic);
				if(!testCasesPassed) {
					System.out.println("Test Cases:" + (i+1) + " failed for:" + c.getName());
					System.out.println("Input");
					AlgoUtil.printArr(arr);
					System.out.println("Output");
					AlgoUtil.printArr(newArr);
					break;
				}
				//AlgoUtil.printArr(newArr);
			}
			
			if(!testCasesPassed) {
				break;
			}
			System.out.println("Test Cases:" + (i+1) + " Passed for all");
		}
		
		
	}

}
