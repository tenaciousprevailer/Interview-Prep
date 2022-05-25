package _5_Questions;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class MaximumSubArray {

    public static void main(String[] args) {
        IMaximumSubArray solution = new SolutionON();
        int[] input = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int sum = solution.calculateMaximumSubArraySum(input);
        System.out.println(sum);
    }

}

interface IMaximumSubArray {
    int calculateMaximumSubArraySum(int[] arr);
}

class SolutionON implements IMaximumSubArray {

    @Override
    public int calculateMaximumSubArraySum(int[] arr) {
        int max = 0, curMax = arr[0];
        for (int i = 0; i < arr.length; i++) {
            curMax = curMax + arr[i];
            if (curMax < 0) {
                curMax = 0;
            } else if (max < curMax){
                max = curMax;
            }
        }
        return max;
    }
}

class Solution implements IMaximumSubArray {

    @Override
    public int calculateMaximumSubArraySum(int[] arr) {
        int sum = Arrays.stream(arr).sum();
        return calculateMaximumSubArraySum(arr, 0, arr.length-1, sum);
    }

    protected int calculateMaximumSubArraySum(int[] arr, int l, int r, int sum) {
        if (l > arr.length - 1 || r < 0) return 0;
        if (l == r) return arr[l];
        int sumA = calculateMaximumSubArraySum(arr, l + 1, r, sum - arr[l]);
        int sumB = calculateMaximumSubArraySum(arr, l, r - 1, sum - arr[r]);
        return Math.max(sum, Math.max(sumA, sumB));
    }

}

class CachedSolution extends Solution {

    private final Map<String, Integer> sumMap = new HashMap<>();

    @Override
    protected int calculateMaximumSubArraySum(int[] arr, int l, int r, int sum) {
        String key = l + ":" + r;
        Integer sum0 = sumMap.get(key);
        if (sum0 == null) {
            sum0 = super.calculateMaximumSubArraySum(arr, l, r, sum);
            sumMap.put(key, sum0);
        }
        return sum0;
    }

}


