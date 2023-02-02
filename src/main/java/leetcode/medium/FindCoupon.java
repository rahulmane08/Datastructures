package leetcode.medium;

import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
public class FindCoupon {

    private Map<String, String> couponMap;
    private Map<String, String> categoryMap;

    public String getCoupon(String category) {
        if (category == null || category.isEmpty()) {
            return null;
        }

        String coupon = couponMap.get(category);
        String currentCategory = category;

        while (coupon == null && currentCategory != null) {
            if (couponMap.containsKey(currentCategory)) {
                coupon = couponMap.get(currentCategory);
            } else {
                currentCategory = categoryMap.get(currentCategory);
            }
        }

        return coupon;
    }

    public static void main(String[] args) {
        String[][] coupons = {{"Comforter Sets", "Comforters Sale"},
            {"Bedding", "Savings on Bedding"},
            {"Bed & Bath", "Low price for Bed & Bath"}};


        String[][] categories = {{"Comforter Sets", "Bedding"},
            {"Bedding", "Bed & Bath"},
            {"Bed & Bath", null},
            {"Soap Dispensers", "Bathroom Accessories"},
            {"Bathroom Accessories", "Bed & Bath"},
            {"Toy Organizers", "Baby And Kids"},
            {"Baby And Kids", null}};

        Map<String, String> couponMap =
                Arrays.stream(coupons).collect(Collectors.toMap(c -> c[0], c -> c[1]));
        Map<String, String> categoryMap =
                Arrays.stream(categories).filter(c -> c[1] != null).collect(Collectors.toMap(c -> c[0], c -> c[1]));

        FindCoupon util = new FindCoupon(couponMap, categoryMap);
        System.out.println(util.getCoupon("Comforter Sets"));
        System.out.println(util.getCoupon("Bedding"));
        System.out.println(util.getCoupon("Bathroom Accessories"));
        System.out.println(util.getCoupon("Soap Dispensers"));
        System.out.println(util.getCoupon("Toy Organizers"));
    }
}
