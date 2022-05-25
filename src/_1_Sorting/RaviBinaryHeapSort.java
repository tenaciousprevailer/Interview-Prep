package _1_Sorting;


import util.AlgoUtil;

import java.util.Comparator;
import java.util.PriorityQueue;

public class RaviBinaryHeapSort<T extends Comparable<T>> implements Sorter<T> {

    private PriorityQueue<Object[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(o -> (int) o[0]));

    @Override
    public void sort(T[] array) {
        sort(array, 0, array.length-1);
    }

    public void sort(final T[] array, final int start, final int end) {
        if(start < end - 1) {
            int length = end - start + 1;
            int numOfBuckets = getNumOfBuckets(length);
            final int bucketSize = length / numOfBuckets;
            if(length > numOfBuckets*bucketSize) numOfBuckets++;

            // recursion
            int l = start;
            while (l <= end) {
                sort(array, l, Math.min(l + bucketSize - 1, end));
                l = l + bucketSize;
            }

            // create sorted sub arrays
            Object[][] subArrArr = new Object[numOfBuckets][0];
            int counter = 0;
            l = start;
            while (l <= end) {
                subArrArr[counter] = createArr(array, l, Math.min(l + bucketSize - 1, end));
                l = l + bucketSize;
                counter++;
            }

            // sort the sub sorted arrays and create one
            int[] subArrCounter = new int[numOfBuckets];
            for (int i = 0; i < subArrCounter.length; i++) {
                if(subArrCounter[i] < subArrArr[i].length) {
                    minHeap.add(new Object[]{ subArrArr[i][subArrCounter[i]], i});
                    subArrCounter[i]++;
                }
            }

            int completedArrays = 0;
            int l0 = start;
            while (completedArrays < subArrArr.length) {
                final Object[] polled = minHeap.poll();
                array[l0++] = (T) polled[0];
                int idx = (int) polled[1];
                if(subArrCounter[idx] < subArrArr[idx].length) {
                    minHeap.add( new Object[]{ subArrArr[idx][subArrCounter[idx]], idx});
                    subArrCounter[idx]++;
                } else {
                    completedArrays++;
                }
            }

            while (!minHeap.isEmpty()) {
                array[l0++] = (T) minHeap.poll()[0];
            }

        } else if(end -1 == start && array[start].compareTo(array[end]) > 0) {
            AlgoUtil.swapArray(array, start, end);
        }
    }

    private Object[] createArr(final T[] array, final int l, final int r) {
        Object[] subArr = new Object[r-l+1];
        for (int i = l; i <= r ; i++) {
            subArr[i-l] = array[i];
        }
        return subArr;
    }


    public int getNumOfBuckets(int length) {
        if(length <= 2.0)
            return 1;
        else if(length <= 4.0)
            return 2;
        else if(length <= 8.0)
            return 3;
        else if(length <= 16.0)
            return 4;
        else if(length <= 32.0)
            return 5;
        else if(length <= 64.0)
            return 6;
        else if(length <= 128.0)
            return 7;
        else if(length <= 256.0)
            return 8;
        else if(length <= 512.0)
            return 9;
        else if(length <= 1024.0)
            return 10;
        else if(length <= 2048.0)
            return 11;
        else if(length <= 4096.0)
            return 12;
        else if(length <= 8192.0)
            return 13;
        else if(length <= 16384.0)
            return 14;
        else if(length <= 32768.0)
            return 15;
        else if(length <= 65536.0)
            return 16;
        else if(length <= 131072.0)
            return 17;
        else if(length <= 262144.0)
            return 18;
        else if(length <= 524288.0)
            return 19;
        else if(length <= 1048576.0)
            return 20;
        else if(length <= 2097152.0)
            return 21;
        else if(length <= 4194304.0)
            return 22;
        else if(length <= 8388608.0)
            return 23;
        else if(length <= 1.6777216E7)
            return 24;
        else if(length <= 3.3554432E7)
            return 25;
        else if(length <= 6.7108864E7)
            return 26;
        else if(length <= 1.34217728E8)
            return 27;
        else if(length <= 2.68435456E8)
            return 28;
        else if(length <= 5.36870912E8)
            return 29;
        else if(length <= 1.073741824E9)
            return 30;
        else if(length <= 2.147483648E9)
            return 31;
        else return 32;
    }

    public static void main(String[] args) {
        Integer[] arr = AlgoUtil.getUnsortedIntegerArray(1000);
        new RaviBinaryHeapSort<Integer>().sort(arr);
        final boolean sorted = AlgoUtil.isIntegerArraySorted(arr, Integer::compare);
        System.out.println("Sorted:" + sorted);
        AlgoUtil.printArr(arr);
    }

}
