package array;

import array.ArrayUtils.FindIndexEqualsElementInSortedArrayUtil;
import org.junit.Assert;
import org.junit.Test;

public class ArrayUtilsTest {

    @Test
    public void test_FindIndexEqualsElementInSortedArrayUtil() {
        FindIndexEqualsElementInSortedArrayUtil util = new FindIndexEqualsElementInSortedArrayUtil();
        Assert.assertEquals(4, util.find(new int[]{2,2,3,3,4}));
        Assert.assertEquals(3, util.find(new int[]{2, 2, 3, 3, 3}));
        Assert.assertEquals(2, util.find(new int[]{2, 2, 2, 2, 2}));
    }

}
