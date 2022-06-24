package _5_Questions;

import org.junit.jupiter.api.Test;

public class MaxChairs {

    @Test
    public void test1() {
        MaxChairs maxChairs0 = new MaxChairs();
        int maxChairs = maxChairs0.findMaxChairs(new int[]{500,600,700}, new int[]{630,700,800});
        System.out.println(maxChairs);
    }

    public int findMaxChairs(int[] arrivalTimeArr, int[] departureTimeArr) {
        if(arrivalTimeArr == null || departureTimeArr == null) return -1;
        if(arrivalTimeArr.length != departureTimeArr.length) return -1;
        if(arrivalTimeArr.length == 0) return 0;

        for(int i=0; i<arrivalTimeArr.length;i++) {
            if(arrivalTimeArr[i] > departureTimeArr[i]) {
                return -1;
            }
        }

        int curMax = 0;
        int maxChairs = 0;

        int arrivalTimeIdx = 0;
        int departureTimeIdx = 0 ;
        while(arrivalTimeIdx < arrivalTimeArr.length && departureTimeIdx < departureTimeArr.length) {
            if(arrivalTimeArr[arrivalTimeIdx] < departureTimeArr[departureTimeIdx]) {
                arrivalTimeIdx++;
                curMax++;
            } else if (arrivalTimeArr[arrivalTimeIdx] > departureTimeArr[departureTimeIdx]) {
                departureTimeIdx++;
                curMax--;
            } else {
                // when both are equal, one person is entering & another is leaving,
// just increment counters
                arrivalTimeIdx++;
                departureTimeIdx++;
            }
            if(curMax < 0) curMax = 0;
            maxChairs = Math.max(curMax, maxChairs);
        }

        return maxChairs;
    }

}
