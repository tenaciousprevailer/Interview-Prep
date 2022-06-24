package _5_Questions;


public class KeyboardMaxAProblem {

    public static void main(String[] args) {

    }

    int calculateMaxAs(int N) {
        Integer[] dp = new Integer[N+1];
        return calculateMaxAs(N, dp);
    }

    // 7, 8 , 9, 11
    int calculateMaxAs(int N, Integer[] dp) {
        if (N <= 6)
            return N;

        if (dp[N] != null) return dp[N];

        //N => 13, 10
        int max = 0;
        for (int i = N-3; i > 0; i--) {  // i=10, 7, 6
            int curMax = (N - i - 1) * calculateMaxAs(i, dp); // 2 * 9, 2 * 9, 3 * 6, 4 *
            if (curMax > max) { // 18
                max = curMax;
            }
        }
        dp[N] = max;
        return max;
    }
    // 7 => 9

}

// _ _ _ _ _ _ _ _ _ _ _ _ _ ,  prev * 2



