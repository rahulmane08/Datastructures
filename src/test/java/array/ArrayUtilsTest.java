package array;

import array.ArrayUtils.FindIndexEqualsElementInSortedArrayUtil;
import org.junit.Assert;
import org.junit.Test;

public class ArrayUtilsTest {

    @Test
    public void test_FindIndexEqualsElementInSortedArrayUtil() {
        FindIndexEqualsElementInSortedArrayUtil util = new FindIndexEqualsElementInSortedArrayUtil();
        Assert.assertEquals(4, util.find(new int[]{2, 2, 3, 3, 4}));
        Assert.assertEquals(3, util.find(new int[]{2, 2, 3, 3, 3}));
        Assert.assertEquals(2, util.find(new int[]{2, 2, 2, 2, 2}));
    }

    @Test
    public void test_printSpiralOrder() {
        int[][] arr = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        ArrayUtils.printSpiralOrder(arr);
    }

    @Test
    public void test_printCombinations() {
        ArrayUtils.printCombinations(new int[]{1, 2, 3, 4, 5}, 3);
    }
}
