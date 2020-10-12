package sort;

import java.util.Arrays;

import org.junit.Test;

public class HeapSortTest {

    @Test
    public void test() {
        Integer[] arr = {5, 4, 3, 2, 1};
        HeapSort.sort(arr, false);
        System.out.println(Arrays.toString(arr));
    }
}
