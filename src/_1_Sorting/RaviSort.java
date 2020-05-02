package _1_Sorting;


import util.AlgoUtil;
import util.IntegerComparator;

import java.util.Comparator;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RaviSort implements Sorter<Integer>  {

    private Comparator<Integer> comparator;

    public RaviSort(Comparator<Integer> comparator) {
        this.comparator = comparator;
    }

    @Override
    public void sort(Integer[] array) {
        sort(array, 0, array.length - 1);
    }

    final int SELECTION_SORT_THRESHOLD = 16;
    final int HEAP_SORT_THRESHOLD = 1024;
    final int QUICK_SORT_THRESHOLD = 2048;
    final int MULTI_THREAD_SORT_THRESHOLD = QUICK_SORT_THRESHOLD;

    // 2 4 8 16 32 64 128 256 512 1024 2048 4096 8192 16384 32768 65536 131072 262144 524288 1048576 2097152
    public void sort(Integer[] array, int l, int r) {
        int size = r - l + 1;
        if( size <= SELECTION_SORT_THRESHOLD ) {
            selectionSort(array, l, r);
        } else
        if (size <= HEAP_SORT_THRESHOLD) {
            heapSort(array, l, r);
        }
//        else if(size <= QUICK_SORT_THRESHOLD){
//            quickSort(array, l, r);
//        }
        else {
            quickSort(array, l, r);
//            multipleThreadSort(array, l, r);
        }
    }

//    public void multipleThreadSort(Integer[] array, int l, int r) {
//        int size = r - l + 1;
//        int batchCount = size / (MULTI_THREAD_SORT_THRESHOLD-1);
//        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
//        CountDownLatch countDownLatch = new CountDownLatch(batchCount);
//        int curStarting = 0;
//        for(int c = 0; c < batchCount; c++) {
//            Runnable sortingTask = new MultiThreadSorting(array,
//                    curStarting,
//                    Math.min(r, curStarting + (MULTI_THREAD_SORT_THRESHOLD-1)),
//                    countDownLatch
//            );
//            curStarting += MULTI_THREAD_SORT_THRESHOLD;
//            executor.submit(sortingTask);
//        }
//        try {
//            countDownLatch.await();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } finally {
//            executor.shutdown();
//        }
//    }

    public void selectionSort(Integer[] array, int l, int r) {
        int ithMinIndex;
        for(int i=l; i<r;i++) {
            ithMinIndex = findMinValueIndex(array, i, r);
            if(i != ithMinIndex) {
                swapArray(array, i, ithMinIndex);
            }
        }
    }

    private int findMinValueIndex(Integer[] arr, int start, int end) {
        int minValIndex = start;
        for(int i = start+1 ;i <= end; i++) {
            if(arr[i] - arr[minValIndex] < 0) {
                minValIndex = i;
            }
        }
        return minValIndex;
    }

   public void heapSort(Integer[] arr, int l, int lastElemIdx) {

        // convert to max heap
        int heapifyToStartFromIdx = (lastElemIdx + l - 1)  / 2;
        for(int rootIdx=heapifyToStartFromIdx; rootIdx >= l; rootIdx--) {
            heapify(arr, rootIdx, lastElemIdx, l);
        }

        for(int i=l; i<lastElemIdx; i++) {
            swapArray(arr, l, lastElemIdx-i + l);
            heapify(arr,l, lastElemIdx-i-1 + l, l);
        }
   }

    private void heapify(Integer[] arr, int rootNodeIdx, int lastElemIdx, int l) {

        int leftChildIdx = (rootNodeIdx * 2) +1 - l;

        if(leftChildIdx <= lastElemIdx) {
            int maxElemIdx = leftChildIdx;
            int rightChildIdx = leftChildIdx + 1;

            if(rightChildIdx <= lastElemIdx) {
                maxElemIdx = Math.max(arr[leftChildIdx], arr[rightChildIdx]) == arr[leftChildIdx]
                        ? leftChildIdx : rightChildIdx;
            }

            if(arr[maxElemIdx] > arr[rootNodeIdx]) {
                swapArray(arr, maxElemIdx, rootNodeIdx);
                heapify(arr,maxElemIdx, lastElemIdx, l);
            }
        }
    }

//    public static void main(String[] args) {
//        Integer[] arr = AlgoUtil.getUnsortedIntegerArray(20);
//        Integer[] newArr = Arrays.copyOf(arr, arr.length);
//        AlgoUtil.printArr(arr);
//        new RaviSort(null).heapSort(arr, 4, 14);
//        if(AlgoUtil.isIntegerArraySorted(arr, 4,14,new IntegerComparator())) {
//            System.out.println("Sorted");
//        } else {
//            System.out.println("Unsorted");
//            AlgoUtil.printArr(arr);
//        }
//    }



    private void quickSort(Integer[] array, int l, int r) {
        if(l<r) {
            int pivotIndex = putPivotOnCorrectPos(array, l, r);
            sort(array, l, pivotIndex-1);
            sort(array, pivotIndex+1, r);
        }
    }

    private int putPivotOnCorrectPos(Integer[] array, int l, int r) {
        int pivot = array[r];

        int leftPointer = l;
        for(int i=l; i<r;i++) {
            if(pivot - array[i] >= 0) {
                AlgoUtil.swapArray(array, leftPointer, i);
                leftPointer++;
            }
        }
        swapArray(array, leftPointer, r);
        return leftPointer;
    }

    public void swapArray(Integer[] arr, int pos1,int  pos2) {
        Integer temp = arr[pos1];
        arr[pos1] = arr[pos2];
        arr[pos2] = temp;
    }

    class MultiThreadSorting implements Runnable {

        private final Integer[] arr;
        private final int l;
        private final int r;
        private final CountDownLatch countDownLatch;

        public MultiThreadSorting(Integer[] arr, int l, int r, CountDownLatch countDownLatch) {
            this.arr = arr;
            this.l = l;
            this.r = r;
            System.out.println("Size:" + (r-l+1) + ", l=" + l + ", r=" + r);
            this.countDownLatch = countDownLatch;
        }

        @Override
        public void run() {
            sort(arr, l, r);
            System.out.println(Thread.currentThread().getName() +" " + AlgoUtil.isIntegerArraySorted(arr,l,r, new IntegerComparator()) );
            countDownLatch.countDown();
        }
    }

}


