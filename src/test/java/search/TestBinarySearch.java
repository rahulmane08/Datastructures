package search;

import org.junit.Assert;
import org.junit.Test;

public class TestBinarySearch {

    @Test
    public void test() {
        int [] arr = {1,2,3,4,5};
        Assert.assertTrue(BinarySearch.searchIteratively(arr, 5));
        Assert.assertFalse(BinarySearch.searchIteratively(arr, 0));
    }
}
