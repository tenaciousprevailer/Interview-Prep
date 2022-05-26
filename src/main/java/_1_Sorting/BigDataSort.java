package _1_Sorting;

import util.AlgoUtil;

import java.util.Comparator;
import java.util.concurrent.*;

public class BigDataSort  implements Sorter<Integer>{

    private Comparator<Integer> comparator;

    public BigDataSort(Comparator<Integer> comparator) {
        this.comparator = comparator;
    }

    @Override
    public void sort(Integer[] array) {
        sort(array, 0, array.length - 1);
    }

    final int SELECTION_SORT_THRESHOLD = 16;
    final int HEAP_SORT_THRESHOLD = 4096;
    final int HYBRID_SORT_THRESHOLD = Integer.MAX_VALUE;
    final int MULTI_THREAD_SORT_THRESHOLD = HYBRID_SORT_THRESHOLD; // disabled

    public void sort(Integer[] array, int l, int r) {
        int size = r - l + 1;
        if( size <= SELECTION_SORT_THRESHOLD ) {
            selectionSort(array, l, r);
        }
        else if (size <= HEAP_SORT_THRESHOLD) {
            heapSort(array, l, r);
        }
        else if(size <= HYBRID_SORT_THRESHOLD) {
            hybridSort(array, l, r);
        } else {
            multipleThreadSort(array, l, r);
        }
    }

    public void multipleThreadSort(Integer[] array, int l, int r) {
        int size = r - l + 1;
        int multiThreadSortThreshold = (int) MULTI_THREAD_SORT_THRESHOLD;
        int batchCount = (size / multiThreadSortThreshold);
        if(size % multiThreadSortThreshold > 0) {
            batchCount++;
        }
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        BlockingQueue<String> blockingQueue = new LinkedTransferQueue<>();

        CountDownLatch countDownLatch = new CountDownLatch(batchCount + 1); // +1 for merge worker

        int startIdx = 0;
        int endIdx = Math.min(r, startIdx + multiThreadSortThreshold-1);
        for(int c = 0; c < batchCount; c++) {
            Runnable sortingTask = new SortingWorker(
                    array,
                    startIdx,
                    endIdx,
                    countDownLatch,
                    blockingQueue
            );

            executor.submit(sortingTask);

            startIdx += multiThreadSortThreshold;
            endIdx = Math.min(r, startIdx + multiThreadSortThreshold-1);
        }

        executor.submit(new SortingMergeWorker(array, countDownLatch, blockingQueue));

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }

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

    private void quickSort(Integer[] array, int l, int r) {
        if(l<r) {
            int pivotIndex = putPivotOnCorrectPos(array, l, r);
            quickSort(array, l, pivotIndex-1);
            quickSort(array, pivotIndex+1, r);
        }
    }

    private void hybridSort(Integer[] array, int l, int r) {
        if(l<r) {
            int pivotIndex = putPivotOnCorrectPos(array, l, r);
            sort(array, l, pivotIndex-1);
            sort(array, pivotIndex+1, r);
        }
    }

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



    class SortingWorker implements Runnable {

    private final Integer[] arr;
    private final int l;
    private final int r;
    private final CountDownLatch countDownLatch;
    private final BlockingQueue<String> blockingQueue;

    public SortingWorker(Integer[] arr, int l, int r, CountDownLatch countDownLatch, BlockingQueue<String> blockingQueue) {
        this.arr = arr;
        this.l = l;
        this.r = r;
        this.countDownLatch = countDownLatch;
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        sort(arr, l, r);
        this.blockingQueue.add(l+":"+r);
        countDownLatch.countDown();
    }
}

class SortingMergeWorker implements Runnable{
    private final Integer[] arr;
    private final CountDownLatch countDownLatch;
    private final BlockingQueue<String> blockingQueue;

    public SortingMergeWorker(Integer[] arr, CountDownLatch countDownLatch, BlockingQueue<String> blockingQueue) {
        this.arr = arr;
        this.countDownLatch = countDownLatch;
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        Integer[] merged = null;
        while(countDownLatch.getCount() > 1 || this.blockingQueue.size() > 0) {
            try {
                String range = this.blockingQueue.poll(100, TimeUnit.MILLISECONDS);
                if(range != null) {
                    merged = handleMergeOperation(merged, range);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("InterruptedException:" + e.getMessage());
            }
        }

        System.arraycopy(merged, 0, arr, 0, merged.length);
        countDownLatch.countDown();
    }

    private Integer[] handleMergeOperation(Integer[] merged, String range) {
        String[] rangeArr = range.split(":");
        int rangeStart = Integer.parseInt(rangeArr[0]), rangeEnd = Integer.parseInt(rangeArr[1]);

        if(merged == null) {
            merged = new Integer[rangeEnd - rangeStart + 1];
            System.arraycopy(arr, rangeStart, merged, 0, merged.length);
        } else {
            merged = merge(
                    merged, 0, merged.length-1,
                    arr, Integer.parseInt(rangeArr[0]), Integer.parseInt(rangeArr[1]) );

        }
        return merged;
    }

    public Integer[] merge(Integer[] arr1, int l1, int r1, Integer[] arr2, int l2, int r2) {
        Integer[] merged = new Integer[r1-l1+1   + r2-l2+1];
        int counter1 = l1;
        int counter2 = l2;
        int mergedCounter = 0;

        while(counter1 <= r1 && counter2 <= r2) {
            if(arr1[counter1] < arr2[counter2]) {
                merged[mergedCounter++] = arr1[counter1++];
            } else {
                merged[mergedCounter++] = arr2[counter2++];
            }
        }

        while(counter1 <= r1) {
            merged[mergedCounter++] = arr1[counter1++];
        }

        while(counter2 <= r2) {
            merged[mergedCounter++] = arr2[counter2++];
        }

        return merged;

    }
}


}
