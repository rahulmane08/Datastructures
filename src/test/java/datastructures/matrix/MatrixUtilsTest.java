package datastructures.matrix;

import org.junit.Test;

public class MatrixUtilsTest {

    @Test
    public void test_printSpiralOrder() {
        int[][] arr = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        MatrixUtils.PrintUtils.printSpiralOrder(arr);
    }
}
