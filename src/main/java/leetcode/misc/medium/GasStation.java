package leetcode.misc.medium;

/**
 * https://leetcode.com/problems/gas-station/description/
 * Input: gas = [1,2,3,4,5], cost = [3,4,5,1,2]
 */
public class GasStation {
  public int canCompleteCircuit(int[] gas, int[] cost) {
    return -1;
  }

  public static void main(String[] args) {
    int[] gas = {1, 2, 3, 4, 5}, cost = {3, 4, 5, 1, 2};
    GasStation util = new GasStation();
    System.out.println(util.canCompleteCircuit(gas, cost));
  }
}
