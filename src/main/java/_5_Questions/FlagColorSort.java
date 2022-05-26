package _5_Questions;

import util.AlgoUtil;

import java.util.Arrays;

public class FlagColorSort {

    public static void main(String[] args) {
        ISol sol = new Sol1();

        int[] input = {1,1,1,0,0,1,1,2,0,0,0,0,1,2};
        input = sol.solve(input);
        Arrays.stream(input).forEach(System.out::print);
        System.out.println();
    }

    private interface ISol {
        int[] solve(int[] arr);
    }

    private static class Sol1 implements ISol {
        @Override
        public int[] solve(int[] arr) {
            int l = 0, r = arr.length - 1, i = 0;
            while (l < r && i < arr.length) {
                if (arr[i] == 0) AlgoUtil.swap(arr, i, l++);
                else if (arr[l] == 2) AlgoUtil.swap(arr, l, r--);
            }
            return arr;
        }
    }

}
