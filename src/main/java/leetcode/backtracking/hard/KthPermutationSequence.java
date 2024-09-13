package leetcode.backtracking.hard;

/**
 *
 */
public class KthPermutationSequence {
  public String getPermutation(int n, int k) {
    return new KthPermutationUtil(n, k).getOutput();
  }

  static class KthPermutationUtil {
    int n;
    int k;
    char[] sequence;

    String output;

    public KthPermutationUtil(int n, int k) {
      this.n = n;
      this.k = k;
      sequence = new char[n];
      for (int i = 1; i <= n; i++) {
        sequence[i - 1] = (char) (i + '0');
      }
      compute(0, n - 1);
    }

    public String getOutput() {
      return output;
    }

    public void compute(int start, int end) {
      if (start == end && k != 0) {
        if (--k == 0) {
          output = new String(sequence);
        }
        return;
      }

      for (int i = start; i <= end; i++) {
        swap(i, start);
        compute(start + 1, end);
        swap(i, start);
      }
    }

    void swap(int i, int j) {
      if (i != j) {
        char temp = sequence[i];
        sequence[i] = sequence[j];
        sequence[j] = temp;
      }
    }
  }

  public static void main(String[] args) {
    KthPermutationSequence util = new KthPermutationSequence();
    System.out.println(util.getPermutation(3, 1));
  }
}
