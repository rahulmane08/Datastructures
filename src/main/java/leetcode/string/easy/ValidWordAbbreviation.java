package leetcode.string.easy;

public class ValidWordAbbreviation {
  public static void main(String[] args) {
    ValidWordAbbreviation util = new ValidWordAbbreviation();
    System.out.println(util.validWordAbbreviation("rahul", "r4l"));
    System.out.println(util.validWordAbbreviation("rahul", "r3l"));
    System.out.println(util.validWordAbbreviation("rahul", "r2l"));
    System.out.println(util.validWordAbbreviation("rahul", "r03l"));
    System.out.println(util.validWordAbbreviation("rahul", "r0ahul"));
  }

  public boolean validWordAbbreviation(String word, String abbr) {
    int w = 0;
    int a = 0;
    while (a < abbr.length() && w < word.length()) {
      String numeric = "";
      while (a < abbr.length() && Character.isDigit(abbr.charAt(a))) {
        numeric += abbr.charAt(a++);
      }
      if (!numeric.isEmpty()) {
        if (numeric.charAt(0) == '0') {
          return false;
        }
        w += Integer.parseInt(numeric);
      } else {
        if (word.charAt(w++) != abbr.charAt(a++)) {
          return false;
        }
      }
    }
    return w == word.length() && a == abbr.length();
  }
}
