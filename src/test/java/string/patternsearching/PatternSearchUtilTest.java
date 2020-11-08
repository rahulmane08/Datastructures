package string.patternsearching;

import org.junit.Test;

public class PatternSearchUtilTest {

    @Test
    public void test_searchNaive() {
        System.out.println(NaiveAlgo.search("TEST", "THIS IS A TEST TEXT"));
        System.out.println(NaiveAlgo.search("AABA", "AABAACAADAABAABA"));
        System.out.println(NaiveAlgo.search("AABD", "AABAACAADAABAABA"));
    }
}
