package _1_Sorting;

import java.util.Comparator;

public class RaviSort implements Sorter<Integer>  {

    private final Comparator<Integer> comparator;

    public RaviSort(Comparator<Integer> comparator) {
        this.comparator = comparator;
    }

    @Override
    public void sort(Integer[] array) {
        sort(array, 0, array.length - 1);
    }

    private final int SELECTION_SORT_THRESHOLD = 16;
    private final int INSERTION_SORT_THRESHOLD = 256;
    private final int HEAP_SORT_THRESHOLD = 4096;
    private final int HYBRID_QUICK_SORT_THRESHOLD =  1048576;
    private final int HYBRID_MERGE_SORT_BATCH_SIZE = HYBRID_QUICK_SORT_THRESHOLD;

    public void sort(Integer[] array, int l, int r) {
        int size = r - l + 1;
        if( size <= SELECTION_SORT_THRESHOLD ) {
            selectionSort(array, l, r);
        }
        else if(size <= INSERTION_SORT_THRESHOLD && isNearlySorted(array, l, r)) {
            insertionSort(array, l, r);
        }
        else if (size <= HEAP_SORT_THRESHOLD) {
            heapSort(array, l, r);
        }
        else if (size <= HYBRID_QUICK_SORT_THRESHOLD) {
            hybridQuickSort(array, l, r);
        }
        else {
            hybridMergeSort(array, l, r);
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

    private static boolean isNearlySorted(Integer[] array, int l, int r) {
        int m;
        while (l < r) {
            m = (l+r) / 2;
            if(array[l] > array[m] || array[m] > array[r]) {
                return false;
            }
            r = m - 1;
        }

        return true;
    }

    public void insertionSort(Integer[] arr, int l, int r) {
        for(int i=l+1; i<=r; i++) {
            int curVal = arr[i];
            int j = i-1;
            while(j>l-1 && curVal < arr[j]) {
                arr[j+1] = arr[j];
                arr[j] = curVal;
                j--;
            }
        }
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

    private void hybridQuickSort(Integer[] array, int l, int r) {
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
                swapArray(array, m , r);
            // else l r m
        } else if(array[l] < array[r]){ // m l r
            swapArray(array, l , r);
        }
        // else m r l

        int pivot = array[r];

        int leftPointer = l;
        for(int i=l; i<r;i++) {
            if(pivot - array[i] >= 0) {
                swapArray(array, leftPointer, i);
                leftPointer++;
            }
        }
        swapArray(array, leftPointer, r);
        return leftPointer;
    }

    class Range {
        final int start, end;
        Range(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "{" +
                    start +
                    "-" + end +
                    '}';
        }
    }



    private void hybridMergeSort(Integer[] array, int l, int r) {

        class FixedSizedStack<T> implements IStack<T> {

            private Object[] arr;
            private int length;

            public FixedSizedStack(int maxCapacity) {
                arr = new Object[maxCapacity];
            }

            @Override
            public void push(T elem) {
                if(length >= arr.length) throw new RuntimeException("No capacity to insert");
                arr[length++] = elem;
            }

            @Override
            public T pop() {
                if(length == 0) throw new RuntimeException("No Element to Pop");
                T elem = (T) arr[--length];
                arr[length] = null;
                return elem;
            }

            @Override
            public T peek() {
                if(length == 0) throw new RuntimeException("No Element to Peek");
                return (T) arr[length-1];
            }

            @Override
            public int size() {
                return length;
            }
        }

        int multiple = (r-l + 1) / HYBRID_MERGE_SORT_BATCH_SIZE;
        FixedSizedStack<Range> stack = new FixedSizedStack((multiple+1));

        int curL = 0, curR = HYBRID_MERGE_SORT_BATCH_SIZE-1;
        for (int i = 0; i < multiple; i++) {
            sort(array, curL, curR);
            stack.push(new Range(curL, curR));
            curL += HYBRID_MERGE_SORT_BATCH_SIZE;
            curR += HYBRID_MERGE_SORT_BATCH_SIZE;
        }

        if(curL < r) {
            curR = r;
            sort(array, curL, curR);
            stack.push(new Range(curL, curR));
        }

        FixedSizedStack<Range> stackOut = stack;
        FixedSizedStack<Range> stackIn = new FixedSizedStack(stackOut.size());
        FixedSizedStack<Range> stackTemp;

        boolean dscMode = true;
        while(stackIn.size() + stackOut.size() > 2) {
            while (stackOut.size() > 1) {
                handleMerge(array, stackOut, stackIn, dscMode);
            }
            stackTemp = stackOut;
            stackOut = stackIn;
            stackIn = stackTemp;
            dscMode = !dscMode;
        }

        if(stackOut.size() == 1) {
            if( stackOut.peek().start < stackIn.peek().start ) dscMode = true;
            else dscMode = false;
            stackOut.push(stackIn.pop());
        }
        handleMerge(array, stackOut, stackIn, dscMode);
    }

    private void handleMerge(Integer[] array, IStack<Range> stackOut, IStack<Range> stackIn, boolean dscMode) {
        Range r1;
        Range r2;
        int left;
        int mid;
        int mid2;
        int right;
        r1 = stackOut.pop();
        r2 = stackOut.pop();
        if(dscMode) {
            left = r2.start;
            mid = r2.end;
            mid2 = r1.start;
            right = r1.end;
        } else {
            left = r1.start;
            mid = r1.end;
            mid2 = r2.start;
            right = r2.end;
        }
        merge(array, left, mid, mid2, right);
        stackIn.push(new Range(left, right));
    }

    private void merge(Integer[] arr, int l, int m1, int m2, int r) {
        if(arr[m1] < arr[m1+1]) return;

        int[] leftArr = new int[m1-l+1];
        int[] rightArr = new int[r-m2+1];

        int tmpCounter = l;
        for(int i=0;i<leftArr.length;i++) {
            leftArr[i] = arr[tmpCounter++];
        }

        tmpCounter = m2;
        for(int i=0;i<rightArr.length;i++) {
            rightArr[i] = arr[tmpCounter++];
        }

        // for both arrays
        int arr1Counter = 0, arr2Counter = 0, mainArrCounter = l;
        while(arr1Counter < leftArr.length && arr2Counter < rightArr.length) {
            if(leftArr[arr1Counter] <= rightArr[arr2Counter]) {
                arr[mainArrCounter++] = leftArr[arr1Counter++];
            } else {
                arr[mainArrCounter++] = rightArr[arr2Counter++];
            }
        }

        // for arr1
        while(arr1Counter < leftArr.length) {
            arr[mainArrCounter++] = leftArr[arr1Counter++];
        }

        // for rightArr
        while(arr2Counter < rightArr.length) {
            arr[mainArrCounter++] = rightArr[arr2Counter++];
        }
    }

    public void swapArray(Integer[] arr, int pos1,int  pos2) {
        Integer temp = arr[pos1];
        arr[pos1] = arr[pos2];
        arr[pos2] = temp;
    }

}

interface IStack<T> {
    void push(T elem);
    T pop();
    T peek();
    int size();
}