package leetcode.greedy.hard;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * target = 15
 * startFuel = 2
 * stations: [[1,2],[2,8],[4,10],[6,7],[7,2],[8,1]]
 * <p>
 * [[1,2],[1,8],[2,10],[2,7],[1,2],[1,1]]
 * <p>
 * sf = 1, target = 4
 * [1,1][1,1][2,1]
 */
public class MinimumNumberOfRefuelingStops {
  /**
   * Intuition:
   * with the current fuel in car, we try to figure out how many stations we can cover witout refuelling and add the
   * fuel of each such station within range in a maxPQ. So that we can take the best station providing max fuel.
   * When we reach a station that we cant reach with the current fuel then we take the pump that provides the max fuel,
   * polling the max from PQ, and increment the count. We do this until our maxReach is less than next station distance.
   * While doing so if we already have filled enough to cross the target then we return the count without considering next station.
   * Else will all the fuel captured if we can still make it to the next station or target we return -1.
   *
   * @param args
   */
  public static void main(String[] args) {
    MinimumNumberOfRefuelingStops util = new MinimumNumberOfRefuelingStops();
    System.out.println(util.minRefuelStops(100, 25, new int[][] {{25, 25}, {50, 25}, {75, 25}}));

  }

  public int minRefuelStops(int target, int startFuel, int[][] stations) {
    // return greedy(target, startFuel, stations);
    return dp(target, startFuel, stations);
  }

  private int dp(int target, int startFuel, int[][] stations) {
    int minStops = topDown(target, startFuel, stations, -1, 0);
    return minStops == Integer.MAX_VALUE ? -1 : minStops;
  }

  private int topDown(int target, int startFuel, int[][] stations, int index, int count) {
    if (index == stations.length) {
      if (target <= 0) {
        return count;
      }
      return Integer.MAX_VALUE;
    }

    int minStops = Integer.MAX_VALUE;
    for (int i = index + 1; i + 1 < stations.length && startFuel >= stations[i + 1][0]; i++) {
      int current = topDown(target - stations[i][0], stations[i][1], stations, i + 1, count + 1);
      minStops = Math.min(minStops, current);
    }
    return minStops;
  }

  private int greedy(int target, int startFuel, int[][] stations) {
    PriorityQueue<Integer> maxPQ = new PriorityQueue<>(Comparator.reverseOrder());
    int currentMaxDistanceCarCanTravel = startFuel;
    int count = 0;
    for (int i = 0; i < stations.length; i++) {
      int nextStationDistFromStart = stations[i][0];
      int fuelAtNextStation = stations[i][1];

      // With the current fuel check if we can reach the next station?
      if (currentMaxDistanceCarCanTravel < nextStationDistFromStart) {
        // We can NOT reach the next station with current fuel, hence consider the options queued up.
        // Found a station that cant be reached with currentMaxDistanceCarCanTravel.
        // now amongst available options , keep picking the best stations providing max fuel.
        while (!maxPQ.isEmpty() && currentMaxDistanceCarCanTravel < nextStationDistFromStart) {
          currentMaxDistanceCarCanTravel += maxPQ.poll();
          count++; // took the station with max fuel provider, until we can reach next station.
        }

        // check if we can already reach the target
        // if yes then no need to check reachability to next stations :)
        if (currentMaxDistanceCarCanTravel >= target) {
          return count;
        }

        // if we cant reach target nor next station, journey ends!! :(
        if (currentMaxDistanceCarCanTravel < nextStationDistFromStart) {
          return -1;
        }
      }
      maxPQ.offer(fuelAtNextStation);
    }

    // If we covered all stations and havent reached the target yet, check the spare petrol and see if we can reach target.
    while (!maxPQ.isEmpty() && currentMaxDistanceCarCanTravel < target) {
      currentMaxDistanceCarCanTravel += maxPQ.poll();
      count++;
    }
    return currentMaxDistanceCarCanTravel >= target ? count : -1;
  }
}