package stack;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestExpressionUtils {

    @Test
    public void testPrefixToInfix() {
        String prefix = "*+AB-CD";
        assertEquals(ExpressionUtils.prefixToInfix(prefix), "((A+B)*(C-D))");
        prefix = "*-A/BC-/AKL";
        assertEquals(ExpressionUtils.prefixToInfix(prefix), "((A-(B/C))*((A/K)-L))");
    }

    @Test
    public void testInfixToPostfix() {
        String infix = "((a+b)*c/d/e)";
        assertEquals("ab+c*d/e/", ExpressionUtils.infixToPostfix(infix));
    }

    @Test
    public void testPrefixToPostfix() {
        String prefix = "*-A/BC-/AKL";
        assertEquals(ExpressionUtils.prefixToPostfix(prefix), "ABC/-AK/L-*");
    }

    @Test
    public void testPostfixToInfix() {
        String postfix = "ab*c+";
        assertEquals(ExpressionUtils.postfixToInfix(postfix), "((a*b)+c)");
    }

    @Test
    public void testPostfixToPrefix() {
        String postfix = "AB+CD-*";
        assertEquals(ExpressionUtils.postfixToPrefix(postfix), "*+AB-CD");
    }
}
