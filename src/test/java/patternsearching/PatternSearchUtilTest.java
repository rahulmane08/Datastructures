package patternsearching;

import org.junit.Test;

public class PatternSearchUtilTest {

    @Test
    public void test_searchNaive() {
        System.out.println(PatternSearchUtil.searchNaive("TEST", "THIS IS A TEST TEXT"));
        System.out.println(PatternSearchUtil.searchNaive("AABA", "AABAACAADAABAABA"));
        System.out.println(PatternSearchUtil.searchNaive("AABD", "AABAACAADAABAABA"));
    }
}
