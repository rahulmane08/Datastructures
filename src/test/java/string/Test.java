package string;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;

public class Test {

    @org.junit.Test
    public void test_removeKdigits() {
        StringBuilder res = new StringBuilder();
        String num;
        int k;
        num = "1432219";
        k = 3;
        StringUtils.buildLowestNumberRec(res, num, k);
        assertEquals("1219", res.toString());

        res = new StringBuilder();
        num = "10200";
        k = 1;
        StringUtils.buildLowestNumberRec(res, num, k);
        System.out.println(res);


        res = new StringBuilder();
        num = "1431229";
        k = 4;
        StringUtils.buildLowestNumberRec(res, num, k);
        System.out.println(res);
    }

    @org.junit.Test
    public void test_palindrome() {
        String str = "abba";
        assertTrue(StringUtils.isPalindrome(str));
        str = "ababa";
        assertTrue(StringUtils.isPalindrome(str));
        str = "abbab";
        assertFalse(StringUtils.isPalindrome(str));
    }
}
