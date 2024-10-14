package leetcode.greedy.medium;

import java.util.Arrays;

/**
 * T(n) = O(n log(n))
 * S(n) = O(1)
 */

public class BoatsToSavePeople {
  /**
   * people = [1,2,2,3,5,6], limit = 5
   *
   * @param people
   * @param limit
   * @return
   */
  public int numRescueBoats(int[] people, int limit) {
    int numBoats = 0;
    Arrays.sort(people);
    int start = 0;
    int end = people.length - 1;
    for (; end > -1 && people[end] > limit; end--);
    while (start <= end) {
      if (people[start] + people[end] <= limit) {
        start++;
      }
      end--;
      numBoats++;
    }
    return numBoats;
  }

  public static void main(String[] args) {
    BoatsToSavePeople util = new BoatsToSavePeople();
    System.out.println(util.numRescueBoats(new int[] {6, 5, 3, 2, 2, 1}, 5));
    System.out.println(util.numRescueBoats(new int[] {3, 2, 2, 1}, 3));
  }
}
