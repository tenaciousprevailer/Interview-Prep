package _5_Questions;

import java.util.HashMap;
import java.util.Map;

public class DP_Fib {

    public static void main(String[] args) {
        NthFibonacciCalculator nthFibonacciCalculator = new CachedNthFibonacciCalculatorImpl();
        Long fib = nthFibonacciCalculator.calculateNthFibonacci(1000);
        System.out.println(fib);
    }

}

interface NthFibonacciCalculator {
    Long calculateNthFibonacci(int N);
}

class NthFibonacciCalculatorImpl implements NthFibonacciCalculator {

    // 1 1 2 3 5 8
    @Override
    public Long calculateNthFibonacci(int N) {
        if (N <= 0) return 0L;
        if (N < 3) return 1L;
        return calculateNthFibonacci(N - 1) + calculateNthFibonacci(N - 2);
    }
}

class CachedNthFibonacciCalculatorImpl extends NthFibonacciCalculatorImpl {

    private final Map<Integer, Long> cacheMap = new HashMap<>();

    @Override
    public Long calculateNthFibonacci(int N) {
        Long nThFib = cacheMap.get(N);
        if (nThFib == null) {
            nThFib = super.calculateNthFibonacci(N);
            cacheMap.put(N, nThFib);
        }
        return nThFib;
    }

}
