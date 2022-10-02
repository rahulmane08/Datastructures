package leetcode.medium;

public class HillsAndValleys {
    public int solution(int[] A) {
        if (A == null || A.length == 0) {
            return 0;
        }
        int n = A.length;
        int result = 0;
        for (int i = 0; i < n;) {
            int j = i;
            if (i + 1 < n && A[i] == A[i + 1]) {
                for (j = i + 1; j + 1 < n && A[j] == A[j + 1]; j++);
            }
            if (isHill(A, n, i , j) || isValley(A, n, i , j)) {
                result++;
            }
            i = j + 1;
        }
        return result;
    }

    private boolean isHill(int[] A, int n, int i, int j) {
        if (i == 0 && j == n-1) {
            return true;
        }
        else if ((i > 0 && A[i-1] < A[i]) && (j < n - 1 && A[j] > A[j+1])) {
            return true;
        }
        else if (i == 0 && A[j] > A[j+1]) {
            return true;
        }
        else if (j == n -1 && A[i-1] < A[i]) {
            return true;
        }
        else {
            return false;
        }
    }

    private boolean isValley(int[] A, int n, int i, int j) {
        if (i == 0 && j == n-1) {
            return true;
        }
        if ((i > 0 && A[i-1] > A[i]) && (j < n - 1 && A[j] < A[j+1])) {
            return true;
        }
        else if (i == 0 && A[j] < A[j+1]) {
            return true;
        }
        else if (j == n -1 && A[i-1] > A[i]) {
            return true;
        }
        else {
            return false;
        }
    }
}
