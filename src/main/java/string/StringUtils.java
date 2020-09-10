package string;

public class StringUtils {
    public static void buildLowestNumberRec(StringBuilder res, String str, int n) {
        // If there are 0 characters to remove from str,
        // append everything to result
        if (n == 0) {
            res.append(str);
            return;
        }

        int len = str.length();

        // If there are more characters to
        // remove than string length,
        // then append nothing to result
        if (len <= n) {
            return;
        }

        // Find the smallest character among
        // first (n+1) characters of str.
        int minIndex = 0;
        for (int i = 1; i <= n; i++)
            if (str.charAt(i) < str.charAt(minIndex))
                minIndex = i;

        // Append the smallest character to result
        res.append(str.charAt(minIndex));

        // substring starting from
        // minIndex+1 to str.length() - 1.
        String new_str = str.substring(minIndex + 1);

        // Recur for the above substring
        // and n equals to n-minIndex
        buildLowestNumberRec(res, new_str, n - minIndex);
    }

    public static boolean isPalindrome(String str) {
        if (str == null || str.isEmpty())
            return false;
        char[] chars = str.toCharArray();
        int i = 0, j = str.length() - 1;
        for (; i<j; i++, j--) {
            if (chars[i] != chars[j])
                return false;
        }
        return true;
    }
}
