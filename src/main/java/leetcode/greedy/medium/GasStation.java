package leetcode.greedy.medium;

/**
 * https://leetcode.com/problems/gas-station/description/
 * Input: gas = [1,2,3,4,5], cost = [3,4,5,1,2]
 * Approach
 * Total Gas and Cost:
 * First, we calculate the total amount of gas and cost across all stations.
 * If the total gas is less than the total cost, return -1 because completing the circuit is impossible.
 * Greedy Approach:
 * We use a greedy approach to find the first valid starting point:
 * We maintain a tank variable to track how much gas is left after each station.
 * If at any point the tank becomes negative, it means that starting from the current station isn't possible. We reset the tank to 0 and move the starting station to the next one.
 * Final check:
 * If the total gas is greater than or equal to the total cost, the last value of start will be the correct starting point.
 */
public class GasStation {

  /**
   * gas = {1, 2, 3, 4, 5}
   * cost= {3, 4, 5, 1, 2}
   *
   * @param gas
   * @param cost
   * @return
   */
  public int canCompleteCircuit(int[] gas, int[] cost) {
    int start = 0;
    int tank = 0;
    int totalTripCost = 0;
    for (int i = 0; i < gas.length; i++) {
      tank += (gas[i] - cost[i]);
      totalTripCost += (gas[i] - cost[i]);
      if (tank < 0) {
        // this is not a good station to start with, try next
        start = i + 1;
        tank = 0;
      }
    }
    if (totalTripCost < 0) {
      return -1;
    }
    return start;
  }

  public static void main(String[] args) {
    int[] gas = {1, 2, 3, 4, 5};
    int[] cost = {3, 4, 5, 1, 1};
    GasStation util = new GasStation();
    System.out.println(util.canCompleteCircuit(gas, cost));
  }
}
