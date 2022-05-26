package _5_Questions;

public class MaxJump {

    public static void main(String[] args) {
        int[] arr = {3,2,1,0,4};
        Sol1 sol = new Sol1();
        boolean canJump = sol.canJump(arr);
        System.out.println(canJump);
    }

    interface ISol {
        boolean canJump(int[] arr);
    }

    static class Sol1 implements ISol {

        @Override
        public boolean canJump(int[] arr) {
            int maxIdx = 0;
            for (int i = 0; i < arr.length; i++) {
                if (i <= maxIdx) {
                    maxIdx = Math.max(maxIdx, i + arr[i]);
                } else {
                    break;
                }
            }
            return maxIdx >= arr.length - 1;
        }

    }

}
