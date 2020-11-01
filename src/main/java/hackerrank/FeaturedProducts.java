package hackerrank;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.function.ToIntFunction;

public class FeaturedProducts {

    public static String featuredProduct(List<String> products) {
        if (products == null || products.isEmpty()) {
            return null;
        }
        Map<String, Integer> frequency = new HashMap<>();
        products.forEach(p -> frequency.compute(p, (k, v) -> (v == null) ? 1 : v + 1));
        Comparator<Map.Entry<String, Integer>> comparator = Comparator
                .comparing((Map.Entry<String, Integer> entry) -> entry.getValue())
                .thenComparing((Map.Entry<String, Integer> entry) -> entry.getKey())
                .reversed();
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(frequency.size(), comparator);
        frequency.entrySet().forEach(pq::add);
        return pq.poll().getKey();
    }

    public static void main(String[] args) {
        List<String> products = Arrays.asList("greenShirt", "bluePants", "redShirt", "blackShoes", "redPants", "redPants", "whiteShirt", "redShirt");
        System.out.println(featuredProduct(products));
    }
}
