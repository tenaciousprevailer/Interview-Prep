package _1_Sorting;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

import util.AlgoUtil;
import util.IntegerComparator;

public class SortingApplicationRunner {

	private static List<Class<? extends Sorter<Integer>>> sorter = Collections.unmodifiableList(
			Arrays.asList(
//					BubbleSort.class,
//					SelectionSort.class,
//					InsertionSort.class,
//					MergeSort.class,
//					HeapSort.class,
//					QuickSort.class,
					RaviSort.class
					)
			);
	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {

		PriorityQueue<String> pq = new PriorityQueue<String>((a,b) ->
				(int) (Long.parseLong(a.split("-")[1]) == Long.parseLong(b.split("-")[1]) ?
										Long.parseLong(a.split(":")[1]) - Long.parseLong(b.split(":")[1]) :
										Long.parseLong(a.split("-")[1]) - Long.parseLong(b.split("-")[1]))
		);

		long startTime, endTime = 0;

		for(int i=0;i<testCases;i++) {
			boolean testCasesPassed = true;
			Integer[] arr = AlgoUtil.getUnsortedIntegerArray(arrSize);
			IntegerComparator ic = new IntegerComparator();
			for(Class<? extends Sorter<Integer>> c : sorter) {
				Integer[] newArr = Arrays.copyOf(arr, arr.length);
				Sorter<Integer> sorterIntstance = c.getDeclaredConstructor(Comparator.class).newInstance(ic);
				startTime = System.currentTimeMillis();
				sorterIntstance.sort(newArr);
				endTime = System.currentTimeMillis();
				testCasesPassed = AlgoUtil.isIntegerArraySorted(newArr, ic);
				if(!testCasesPassed) {
					System.out.println("Test Cases:" + (i+1) + " failed for:" + c.getName());
					System.out.println("Input");
//					AlgoUtil.printArr(arr);
					System.out.println("Output");
//					AlgoUtil.printArr(newArr);
					break;
				} else {
					pq.offer( "test-" + i + "-" + c.getSimpleName() + ":" + (endTime-startTime));
//					System.out.println("i'm done:" + pq.peek());
				}
				//AlgoUtil.printArr(newArr);
			}
			
			if(!testCasesPassed) {
				break;
			}

			// java
			Integer[] newArr = Arrays.copyOf(arr, arr.length);
			startTime = System.currentTimeMillis();
			Arrays.sort(newArr);
			endTime = System.currentTimeMillis();
			pq.offer( "test-" + i + "-" + "Timsort" + ":" + (endTime-startTime));

			System.out.println("Test Cases:" + (i+1) + " Passed for all");
		}

		System.out.println("Results: "+ pq.size());
		int top = sorter.size() * testCases;
		HashMap<String, Integer> map = new HashMap<>();
		while(!pq.isEmpty() ) {

			if(pq.size() % (sorter.size() + 1) == 0) {
				String y = pq.poll();
				System.out.println("======>:" + y);
				String key = y.split("-")[2].split(":")[0];
				map.put(key, map.getOrDefault(key, 0) + 1 );
			} else {
				String y = pq.poll();
				System.out.println("===X:" + y);
			}
		}

		PriorityQueue<Map.Entry<String, Integer>> algoPq = new PriorityQueue<Map.Entry<String, Integer>>((a,b) -> b.getValue() - a.getValue());
		algoPq.addAll(map.entrySet());

		while(!algoPq.isEmpty()) {
			System.out.println(algoPq.poll());
		}
	}

	/**
	 * analysis:
	 *
	 insertion is fast when arr size is:
	 10:
	 HeapSort=450
	 InsertionSort=203
	 QuickSort=176
	 SelectionSort=146
	 MergeSort=23
	 BubbleSort=2

	 SelectionSort=387
	 HeapSort=255
	 InsertionSort=161
	 QuickSort=133
	 BubbleSort=45
	 MergeSort=19

	 SelectionSort=357
	 HeapSort=281
	 QuickSort=173
	 InsertionSort=166
	 MergeSort=21
	 BubbleSort=2

	 HeapSort=467
	 QuickSort=187
	 SelectionSort=180
	 InsertionSort=140
	 MergeSort=25
	 BubbleSort=1

	 HeapSort=434
	 SelectionSort=239
	 InsertionSort=181
	 QuickSort=105
	 BubbleSort=27
	 MergeSort=14

	 SelectionSort=431
	 HeapSort=277
	 InsertionSort=145
	 QuickSort=102
	 BubbleSort=23
	 MergeSort=22

	 HeapSort=396
	 InsertionSort=245
	 QuickSort=173
	 SelectionSort=154
	 MergeSort=25
	 BubbleSort=7

	 32:
	 HeapSort=634
	 QuickSort=337
	 InsertionSort=23
	 MergeSort=4
	 BubbleSort=2

	 HeapSort=541
	 QuickSort=385
	 InsertionSort=70
	 MergeSort=2
	 SelectionSort=1
	 BubbleSort=1

	 QuickSort=513
	 HeapSort=417
	 InsertionSort=62
	 BubbleSort=5
	 MergeSort=2
	 SelectionSort=1

	 HeapSort=600
	 QuickSort=344
	 InsertionSort=54
	 MergeSort=1
	 BubbleSort=1

	 64:
	 HeapSort=844
	 QuickSort=154
	 BubbleSort=2

	 HeapSort=838
	 QuickSort=156
	 BubbleSort=3
	 InsertionSort=2
	 Timsort=1

	 HeapSort=780
	 QuickSort=217
	 InsertionSort=2
	 BubbleSort=1

	 100:
	 HeapSort=884
	 QuickSort=115
	 Timsort=1

	 HeapSort=888
	 QuickSort=108
	 MergeSort=2
	 InsertionSort=1
	 Timsort=1

	 HeapSort=901
	 QuickSort=97
	 MergeSort=1
	 BubbleSort=1

	 1000:
	 HeapSort=967
	 QuickSort=32
	 Timsort=1

	 HeapSort=968
	 QuickSort=31
	 Timsort=1

	 10000:
	 HeapSort=953
	 QuickSort=45
	 MergeSort=1
	 Timsort=1

	 HeapSort=756
	 QuickSort=228
	 Timsort=16

	 HeapSort=794
	 QuickSort=192
	 Timsort=14

	 100000:
	 QuickSort=97
	 MergeSort=3

	 QuickSort=98
	 HeapSort=1
	 Timsort=1

	 QuickSort=967
	 Timsort=32
	 HeapSort=1
	 *
	 */


	static int testCases = 10;
	static int arrSize = 9999999;
}

