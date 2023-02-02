package leetcode.medium;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.HashMap;
import java.util.Map;
/**
public class FindCoupons {

    @Data
    @EqualsAndHashCode(onlyExplicitlyIncluded = true)
    class Category {
        @EqualsAndHashCode.Include
        String name;
        String parentCategory;
    }

    @Data
    class Coupon {
        String name;
    }

    private final Map<String, Category> categoryMap;
    private final Map<Category, Coupon> categoryToCouponMap;

    public FindCoupons(Map<String, String> categoryToParentMap, Map<String, String> categoryToCouponMap) {
        this.categoryMap = new HashMap<>();
        this.categoryToCouponMap = new HashMap<>();
        initialize(categoryToParentMap, categoryToCouponMap);
    }

    private void initialize(Map<String, String> categoryToParentMap,
                            Map<String, String> categoryToCouponMap) {



        categoryToCouponMap.entrySet().forEach(e -> {
            Category category = new Category();
            category.setName(e.getKey());
            Coupon coupon = new Coupon();
            coupon.setName(e.getValue());
            this.categoryToCouponMap.put(category, coupon);
        });



        for (String category : categoryToParentMap.keySet()) {
            if (categoryToCouponMap.containsKey(category)) {
                continue;
            }
            String coupon = null;
            String currentCategory = category;
            while (true) {
                String parent = categoryToParentMap.get(currentCategory);
                if (parent == null) {
                    break;
                }
                coupon = categoryToCouponMap.get(parent);
                if (coupon == null) {
                    break;
                }
                currentCategory = parent;
            }
            categoryToCouponMap.put(category, coupon);
        }
    }


    public String getCoupon(String categoryName) {
        return this.categoryToCouponMap.getOrDefault(categoryName, null);
    }

    public static void main(String[] args) {
        Map<String, String> categoryToCouponMap = new HashMap<>();
        categoryToCouponMap.put("Comforter Sets", "Comforters Sale");
        categoryToCouponMap.put("Bedding", "Savings on Bedding");
        categoryToCouponMap.put("Bed & Bath", "Low price for Bed & Bath");
    }

    FindCoupons findCoupons = new FindCoupons();

}
}
**/