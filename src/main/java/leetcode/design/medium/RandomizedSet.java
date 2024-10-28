package leetcode.design.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RandomizedSet {
  private final ArrayList<Integer> elements;
  private final Map<Integer, Integer> map;

  public RandomizedSet() {
    elements = new ArrayList<>();
    map = new HashMap<>();
  }

  public boolean search(int val) {
    return map.containsKey(val);
  }

  public boolean insert(int val) {
    if (search(val)) {
      return false;
    }

    elements.add(val);
    map.put(val, elements.size() - 1);
    return true;
  }

  public boolean remove(int val) {
    if (!search(val)) {
      return false;
    }

    int index = map.get(val);
    elements.set(index, elements.get(elements.size() - 1));
    map.put(elements.get(index), index);
    elements.remove(elements.size() - 1);
    map.remove(val);

    return true;
  }

  public int getRandom() {
    Random rand = new Random();
    return elements.get(rand.nextInt(elements.size()));
  }
}
