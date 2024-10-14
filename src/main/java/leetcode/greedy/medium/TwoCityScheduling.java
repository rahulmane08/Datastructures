package leetcode.greedy.medium;

import java.util.Arrays;
import java.util.Comparator;

/**
 * (10, 20), (10, 30) (10, 40) (10, 50)
 * <p>
 * cityA : {(10, 20), (10, 30) (10, 40) (10, 50)}
 * cityB : {}
 * cityA : {(10, 40) (10, 50)} = 20
 * cityB : {(10, 20), (10, 30)} = 50
 * <p>
 * <p>
 * (90, 20), (100, 30) (10, 50) (30, 50)
 * <p>
 * cityA : {(10, 50) (30, 50)} = 40
 * cityB : {(90, 20), (100, 30)} = 50
 * <p>
 * [[10,20],[30,200],[400,50],[30,20]]
 * cityA : [10,20],[30,200] = 40
 * cityB : [400,50],[30,20] = 70
 * <p>
 * [[259,770],[448,54],[926,667],[184,139],[840,118],[577,469]]
 * cityA : [259,770]
 * cityB: [184,139] [448,54] [577,469] [840,118] [926,667]
 * <p>
 * cityA : [259,770] [184,139] [448,54]
 * cityB:  [577,469] [840,118] [926,667]
 * <p>
 * <p>
 * [[515,563],[451,713],[537,709],[343,819],[855,779],[457,60],[650,359],[631,42]]
 * cityA:[515,563] [537,709] [451,713] [343,819]
 * cityB: [457,60] [855,779]
 */
public class TwoCityScheduling {
  public static void main(String[] args) {
    int[][] costs = {{259, 770}, {448, 54}, {926, 667}, {926, 139}, {926, 118}, {577, 469}};
    TwoCityScheduling util = new TwoCityScheduling();
    System.out.println(util.twoCitySchedCost(costs));
  }

  public int twoCitySchedCost(int[][] costs) {
    Arrays.sort(costs, Comparator.comparingInt(a -> a[0] - a[1]));
    int i = 0;
    int minCost = 0;
    while (i < costs.length / 2) {
      minCost += costs[i++][0];
    }
    while (i < costs.length) {
      minCost += costs[i++][1];
    }
    return minCost;
  }
}
