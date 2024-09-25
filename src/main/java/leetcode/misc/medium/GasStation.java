package leetcode.misc.medium;

/**
 * https://leetcode.com/problems/gas-station/description/
 * Input: gas = [1,2,3,4,5], cost = [3,4,5,1,2]
 */
public class GasStation {
  public static void main(String[] args) {
    int[] gas = {1, 2, 3, 4, 5}, cost = {3, 4, 5, 1, 2};
    GasStation util = new GasStation();
    System.out.println(util.canCompleteCircuit(gas, cost));
  }

  /**
   * gas = {1, 2, 3, 4, 5}
   * cost= {3, 4, 5, 1, 2}
   *
   * @param gas
   * @param cost
   * @return
   */
  public int canCompleteCircuit(int[] gas, int[] cost) {
    int stations = gas.length;
    int start = 0;
    int end = 0;

    return -1;
  }
}
