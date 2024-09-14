package leetcode.misc.medium;

/**
 * https://leetcode.com/problems/gas-station/description/
 * Input: gas = [1,2,3,4,5], cost = [3,4,5,1,2]
 */
public class GasStation {
  /**
   * gas = {1, 2, 3, 4, 5}
   * cost= {3, 4, 5, 1, 2}
   * @param gas
   * @param cost
   * @return
   */
  public int canCompleteCircuit(int[] gas, int[] cost) {
    int stations = gas.length;
    int[] mileages = new int[stations];
    int start = -1;
    for (int i = 0; i < stations; i++) {
      mileages[i] = gas[i] - cost[i];
      if (start == -1 && mileages[i] >= 0) {
        start = i;
      }
    }

    if (start == stations) {
      return -1;
    }

    int end = (start + 1) % stations;
    int currentGas = mileages[start];

    while (start != end) {
      if (start == stations) {
        return -1;
      }

    }
    return -1;
  }

  public static void main(String[] args) {
    int[] gas = {1, 2, 3, 4, 5}, cost = {3, 4, 5, 1, 2};
    GasStation util = new GasStation();
    System.out.println(util.canCompleteCircuit(gas, cost));
  }
}
