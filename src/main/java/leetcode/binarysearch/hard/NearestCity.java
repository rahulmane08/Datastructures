package leetcode.binarysearch.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * A number of cities are arranged on a graph that has been divided up like an ordinary Cartesian plane.
 * Each city is located at an integral (x, y) coordinate intersection.
 * City names and locations are given in the form of three arrays: c, x, and y,
 * which are aligned by the index to provide the city name (c[i]), and its coordinates, (x[i], y[i]).
 * Determine the name of the nearest city that shares either an x or a y coordinate with the queried city.
 * If no other cities share an x or y coordinate, return 'NONE'. If two cities have the same distance to the queried city, q[i],
 * consider the one with an alphabetically shorter name (i.e. 'ab' < 'aba' < 'abb') as the closest choice.
 * The distance is the Manhattan distance, the absolute difference in x plus the absolute difference in y.
 * <p>
 * Constraints
 * <p>
 * 1 ≤ n, m ≤ 105
 * 1 ≤ x[i], y[i] ≤ 109
 * 1 ≤ length of q[i] and c[i]  ≤ 10
 * Each character of all c[i] and q[i] is in the range ascii[a-z, 0-9, -]
 * All city name values, c[i], are unique
 * All cities have unique coordinates
 * <p>
 * <p>
 * Function Description
 * Complete the function closestStraightCity in the editor below.
 * closestStraightCity has the following parameter(s):
 * string c[n]: an array of strings that represent the names of each city[i]
 * int x[n]: the x coordinates of each city[i]
 * int y[n]: the y coordinates of each city[i]
 * string q[m]: the names of each city to query
 * Returns:
 * string[m]: an array of m strings where the index of i element denotes the return value of the index of i query
 */
public class NearestCity {

  @Data
  @AllArgsConstructor
  class City {
    String city;
    int rowMajor;
  }

  public List<String> closestStraightCity1(String[] cities, int[] x, int[] y, String[] queries) {
    int n = x.length;
    City[] allCities = new City[n];
    int index = 0;
    Map<String, City> cityMap = new HashMap<>();
    for (int i = 0; i < n; i++) {
      City city = new City(cities[i], x[i] * n + y[i]);
      allCities[index++] = city;
      cityMap.put(cities[i], city);
    }
    Arrays.sort(allCities, Comparator.comparingInt(City::getRowMajor));
    List<String> result = new ArrayList<>();
    for (String query : queries) {
      result.add(getNearestCity(allCities, cityMap.get(query), n));
    }
    return result;
  }

  private String getNearestCity(City[] allCities, City inputCity, int n) {
    int index = search(allCities, inputCity, 0, n - 1);
    City leftNearest = null, rightNearest = null;
    if (index == 0) {
      if (isValid(inputCity, allCities[index + 1], n)) {
        rightNearest = allCities[index + 1];
      }
    } else if (index == n - 1) {
      if (isValid(inputCity, allCities[index - 1], n)) {
        leftNearest = allCities[index - 1];
      }
    } else {
      if (isValid(inputCity, allCities[index + 1], n)) {
        rightNearest = allCities[index + 1];
      }
      if (isValid(inputCity, allCities[index - 1], n)) {
        leftNearest = allCities[index - 1];
      }
    }

    if (leftNearest != null && rightNearest != null) {
      int leftDist = inputCity.rowMajor - leftNearest.rowMajor;
      int rightDist = rightNearest.rowMajor - inputCity.rowMajor;
      if (leftDist < rightDist) {
        return leftNearest.city;
      } else if (leftDist > rightDist) {
        return rightNearest.city;
      } else {
        if (leftNearest.city.compareTo(rightNearest.city) < 1) {
          return leftNearest.city;
        } else {
          return rightNearest.city;
        }
      }
    } else if (leftNearest != null) {
      return leftNearest.city;
    } else if (rightNearest != null) {
      return rightNearest.city;
    }

    return "NONE";
  }

  private boolean isValid(City city1, City city2, int n) {
    int x1 = city1.rowMajor / n;
    int y1 = city1.rowMajor % n;
    int x2 = city2.rowMajor / n;
    int y2 = city2.rowMajor % n;
    return x1 == x2 || y1 == y2;
  }

  private int search(City[] allCities, City inputCity, int start, int end) {
    if (start > end) {
      return -1;
    }
    int mid = (start + end) >>> 1;
    City midCity = allCities[mid];
    if (midCity.rowMajor == inputCity.rowMajor) {
      return mid;
    } else if (inputCity.rowMajor < midCity.rowMajor) {
      return search(allCities, inputCity, start, mid - 1);
    } else {
      return search(allCities, inputCity, mid + 1, end);
    }
  }

  public List<String> closestStraightCity(String[] cities, int[] x, int[] y, String[] queries) {
    int n = x.length;
    Map<Integer, List<int[]>> horrizontalCities = new HashMap<>();
    Map<Integer, List<int[]>> verticalCities = new HashMap<>();
    for (int i = 0; i < n; i++) {
      List<int[]> yList = horrizontalCities.compute(x[i], (yCord, list) -> list != null ? list : new ArrayList<>());
      List<int[]> xList = verticalCities.compute(y[i], (xCord, list) -> list != null ? list : new ArrayList<>());
      yList.add(new int[] {y[i], i});
      xList.add(new int[] {x[i], i});
    }

    horrizontalCities.values().forEach(list -> list.sort(Comparator.comparingInt(a -> a[0])));
    verticalCities.values().forEach(list -> list.sort(Comparator.comparingInt(a -> a[0])));

    List<String> results = new ArrayList<>();
    for (String city : queries) {
      int index = Arrays.binarySearch(cities, city);
      List<int[]> xList = horrizontalCities.get(x[index]);
      List<int[]> yList = verticalCities.get(y[index]);
      if (xList.isEmpty() && yList.isEmpty()) {
        results.add("NONE");
        continue;
      }
      index = search(xList, 0, n - 1, x[index]);
    }
    return results;
  }

  private int search(List<int[]> list, int start, int end, int target) {
    if (start > end) {
      return -1;
    }
    int mid = (start + end) >>> 1;
    int coordinate = list.get(mid)[0];
    if (coordinate == target) {
      return mid;
    } else if (target < coordinate) {
      return search(list, start, mid - 1, target);
    } else {
      return search(list, start, mid - 1, target);
    }
  }
}
