package _5_Questions;

public class FadiraQueenMonsterGoldCoin {

    public static void main(String[] args) {
        FadiraQueenMonsterGoldCoin fadiraQueenMonsterGoldCoin = new FadiraQueenMonsterGoldCoin();
        int i = fadiraQueenMonsterGoldCoin.collectMaxGold(new int[]{1, 2, 3, 4, 5});
        System.out.println(i);
    }

    public int collectMaxGold(int[] goldCoinArr) {
        Integer[] cache = new Integer[goldCoinArr.length + 1];
        return collectMaxGold(goldCoinArr, goldCoinArr.length-1, cache);
    }

    public int collectMaxGold(int[] goldCoinArr, int index, Integer[] cache) {
        if(index < 0) return 0;
        if(index == 0) return goldCoinArr[index];
        if(cache[index] != null) return cache[index];
        int max = Math.max(
                goldCoinArr[index] + collectMaxGold(goldCoinArr, index-2, cache),
                collectMaxGold(goldCoinArr, index-1, cache)
        );
        cache[index] = max;
        return max;
    }

}
