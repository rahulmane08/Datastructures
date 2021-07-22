package algos.dynamicprogramming.boundedknapsack;

public class TargetSum {

    public static class TopDown {
        public int solve(int[] arr, int targetSum) {
            if (arr == null) {
                return -1;
            }

            int n = arr.length;
            int totalSum = 0;
            for (int i = 0; i < n; i++) {
                totalSum += i;
            }
            int actualSum = (totalSum + targetSum) / 2;

            return new CountSubsetsMatchingSum.TopDown().solve(arr, actualSum);
        }
    }

    public static class BottomUp {
        public int solve(int[] arr, int targetSum) {
            int n = arr.length;
            int totalSum = 0;
            for (int i = 0; i < n; i++) {
                totalSum += i;
            }
            int actualSum = (totalSum + targetSum) / 2;

            return new CountSubsetsMatchingSum.BottomUp().solve(arr, actualSum);
        }
    }
}
