package algorithms.backtracking;

import static algorithms.backtracking.hard.NQueens.solve;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestNQueens {

    @Test
    public void test() {
        assertTrue(solve(4));
        assertFalse(solve(2));
    }
}
