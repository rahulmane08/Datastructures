package algorithms.backtracking;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestSubsetSum {

    @Test
    public void test() {
        assertTrue(SubsetSum.solve(new int[]{1, 2, 5, 6}, 3));
    }
}
