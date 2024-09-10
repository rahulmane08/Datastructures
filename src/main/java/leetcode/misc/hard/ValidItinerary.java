package leetcode.misc.hard;

import static java.util.Arrays.asList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/explore/interview/card/uber/296/trees-and-graphs/1727/
 */
public class ValidItinerary {

  public static void main(String[] args) {
    List<List<String>> tickets =
        asList(asList("JFK", "SFO"), asList("JFK", "ATL"), asList("SFO", "ATL"), asList("ATL", "JFK"),
            asList("ATL", "SFO"));
    ValidItinerary util = new ValidItinerary();
    System.out.println(util.findItinerary(tickets));

    tickets = asList(asList("JFK", "KUL"), asList("JFK", "NRT"), asList("NRT", "JFK"));
    System.out.println(util.findItinerary(tickets));
  }

  public List<String> findItinerary(List<List<String>> tickets) {
    Map<String, ArrayList<String>> graph = new HashMap<>();
    tickets.forEach(itinerary -> graph.compute(itinerary.get(0),
        (start, destinations) -> destinations == null ?
            new ArrayList<>() : destinations).add(itinerary.get(1)));

    // Graph has source and sorted list of destinations in lexical order.
    graph.values().forEach(destinations -> destinations.sort(Comparator.naturalOrder()));

    // total flights, for an itinerary to complete the itinerary route = totalFlights, to cover all destinations
    // and back to source = JFK
    int totalFlights = tickets.size() + 1;

    // boolean visitMap for each source to destination
    Map<String, boolean[]> visitMap = new HashMap<>();
    graph.entrySet().forEach(e -> visitMap.put(e.getKey(), new boolean[e.getValue().size()]));

    Deque<String> route = new LinkedList<>();
    String start = "JFK";
    route.offer(start);

    if (dfs(graph, start, totalFlights, route, visitMap)) {
      return new ArrayList<>(route);
    }
    return Collections.emptyList();
  }

  private boolean dfs(Map<String, ArrayList<String>> graph,
                      String start,
                      int totalFlights,
                      Deque<String> route,
                      Map<String, boolean[]> visitMap) {
    if (totalFlights == route.size()) {
      return true;
    }

    if (!graph.containsKey(start)) {
      return false;
    }

    int i = 0;
    boolean[] visitedRoutes = visitMap.get(start);
    for (String dest : graph.get(start)) {
      if (!visitedRoutes[i]) {
        visitedRoutes[i] = true;
        route.add(dest);
        if (dfs(graph, dest, totalFlights, route, visitMap)) {
          return true;
        }
        //backtrack
        visitedRoutes[i] = false;
        route.pollLast();
      }
      i++;
    }
    return false;
  }
}
