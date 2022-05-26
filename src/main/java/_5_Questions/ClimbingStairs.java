package _5_Questions;

import java.util.HashMap;
import java.util.Map;

public class ClimbingStairs {

    public static void main(String[] args) {
        ISol iSol = new Sol1();
        int ways = iSol.calculateTotalSteps(45);
        System.out.println(ways);
    }

    private interface ISol {
        int calculateTotalSteps(int n);
    }

    private static class Sol1 implements ISol {

        private final Map<Integer, Integer> waysMap = new HashMap<>();

        @Override
        public int calculateTotalSteps(int n) {
            Integer ways = waysMap.get(n);
            if (ways == null) {
                if (n == 1) return 1;
                if (n == 2) return 2;
                ways = calculateTotalSteps(n-1) + calculateTotalSteps(n-2);
                waysMap.put(n, ways);
            }
            return ways;
        }

    }

}
