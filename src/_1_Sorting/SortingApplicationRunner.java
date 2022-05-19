package _1_Sorting;

import util.AlgoUtil;
import util.IntegerComparator;

import java.util.*;

public class SortingApplicationRunner {

	private static Comparator<Integer> compator = new IntegerComparator();
	private static List<Sorter<Integer>> sorterList = Collections.unmodifiableList(
			Arrays.asList(
//					new BubbleSort(compator)
					new SelectionSort(compator)
//					new InsertionSort(compator),
//					new MergeSort(compator),
//					new HeapSort(compator),
//					new QuickSort(compator),
//					new RaviSort(compator)
//					new QuickSortIterative(Integer::compareTo)
//					new QuickSort(compator),
//					new RaviBinaryHeapSort<>()
				)
			);

	public static void main(String[] args) throws IllegalArgumentException, SecurityException {

		PriorityQueue<String> pq = new PriorityQueue<String>((a,b) -> {
			int aTestNo = Integer.parseInt(a.split("-")[1]);
			int bTestNo = Integer.parseInt(b.split("-")[1]);
			int sameTest = aTestNo - bTestNo;
			if(sameTest == 0) {
				long aTime = Long.parseLong(a.split(":")[1]);
				long bTime = Long.parseLong(b.split(":")[1]);
				if(aTime == bTime) return 0;
				if(aTime > bTime) return 1;
				return -1;
			}
			return sameTest;
		});

		long startTime, endTime = 0;

		for(int i=0;i<testCases;i++) {
			boolean testCasesPassed = true;
//			System.out.println("Going to generate unsorted array");
			Integer[] arr = AlgoUtil.getUnsortedIntegerArray(arrSize);
//			System.out.println("Generated unsorted array");
			IntegerComparator ic = new IntegerComparator();
			for(Sorter sorterIntstance : sorterList) {
				Integer[] newArr = Arrays.copyOf(arr, arr.length);
				startTime = getTime();
				sorterIntstance.sort(newArr);
				endTime = getTime();
				testCasesPassed = AlgoUtil.isIntegerArraySorted(newArr, ic);
				if(!testCasesPassed) {
					System.out.println("Test Cases:" + (i+1) + " failed for:" + sorterIntstance.getClass().getName() + " " + (endTime-startTime));
//					System.out.println("Input");
//					AlgoUtil.printArr(arr);
//					System.out.println("Output");
//					AlgoUtil.printArr(newArr);
//					break;
				} else {
					pq.offer( "test-" + i + "-" + sorterIntstance.getClass().getSimpleName() + ":" + (endTime-startTime));
				}
			}
			
			if(!testCasesPassed) {
				break;
			}

			// timsort
//			Integer[] newArr = Arrays.copyOf(arr, arr.length);
//			startTime = getTime();
//			Arrays.sort(newArr);
//			endTime = getTime();
//			pq.offer( "test-" + i + "-" + "Timsort" + ":" + (endTime-startTime));

			System.out.println("Test Cases:" + (i+1) + " Passed for all");
		}

//		System.out.println("Results: "+ pq.size());
//		int top = sorterList.size() * testCases;
//		HashMap<String, Integer> map = new HashMap<>();
//		while(!pq.isEmpty() ) {
//
//			if(pq.size() % (sorterList.size() + 1) == 0) {
//				String y = pq.poll();
//				System.out.println("======>:" + y);
//				String key = y.split("-")[2].split(":")[0];
//				map.put(key, map.getOrDefault(key, 0) + 1 );
//			} else {
//				String y = pq.poll();
//				System.out.println("===X:" + y);
//			}
//		}
//
//		PriorityQueue<Map.Entry<String, Integer>> algoPq = new PriorityQueue<Map.Entry<String, Integer>>((a,b) -> b.getValue() - a.getValue());
//		algoPq.addAll(map.entrySet());
//
//		while(!algoPq.isEmpty()) {
//			System.out.println(algoPq.poll());
//		}
	}

	private static long getTime() {
		return System.nanoTime();
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
	static int[] arrSizeArr = {0,
			9, 				// 219785
			99, 			// 506527
			999,			// 1419209
			9999,			// 9370325
			99999,			// 57996778
			999999,			// 420802033
			9999999,		// 3682662962
							// 12648872242
			99999999,		// 24414973311
			999999999
	};

	static int[] abc = {1048576, 2097152,4194304};
	static int arrSize = arrSizeArr[4];
}

