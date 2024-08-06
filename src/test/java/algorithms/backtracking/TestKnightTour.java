package algorithms.backtracking;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import algorithms.backtracking.hard.KnightTour;
import org.junit.Test;

public class TestKnightTour {

    @Test
    public void test() {
        assertTrue(KnightTour.solve(8, 8));
        assertFalse(KnightTour.solve(4, 4));
    }
}
