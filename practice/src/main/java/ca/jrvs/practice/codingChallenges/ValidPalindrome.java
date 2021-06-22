package ca.jrvs.practice.codingChallenges;

/**
 * https://www.notion.so/jarvisdev/Valid-Palindrome-7d14afccdf054b5b9a3b09fcf8d69fc6
 * Determine if a string is a palindrome (the same if read from left and from right)
 * The solution is tested using https://leetcode.com/problems/valid-palindrome/
 */
public class ValidPalindrome {

  /**
   * Implements two pointers approach
   * Running time is O(n) where n is the length of the string
   * @param s string to consider
   * @return true if is palindrome
   */
  public static boolean isPalindromePointers(String s) {
    int i = 0;
    int j = s.length() - 1;
    while (i <= j) {
      char iChar = s.charAt(i);
      if (!Character.isLetterOrDigit(iChar)) {
        i++;
        continue;
      }
      char jChar = s.charAt(j);
      if (!Character.isLetterOrDigit(jChar)) {
        j--;
        continue;
      }
      if (Character.toLowerCase(iChar) != Character.toLowerCase(jChar)) {
        return false;
      }
      i++;
      j--;
    }
    return true;
  }


  /**
   * Implements recursive solution
   * Time complexity is O(n), because each pair of character is compared only once
   * Space complexity is O(n) because indices are passed in recursion, instead of strings
   * @param s string to consider
   * @return true if is palindrome
   */
  public static boolean isPalindromeRecursive(String s) {
    return isPalindromeHelper(s, 0, s.length() - 1);
  }

  private static boolean isPalindromeHelper(String s, int i, int j) {
    //base case
    if (i >= j) return true;
    // removing non-alphanumeric
    if (!Character.isLetterOrDigit(s.charAt(i))) {
      return isPalindromeHelper(s, i + 1, j);
    }
    if (!Character.isLetterOrDigit(s.charAt(j))) {
      return isPalindromeHelper(s, i, j - 1);
    }
    return Character.toLowerCase(s.charAt(i)) == Character.toLowerCase(s.charAt(j))
        && isPalindromeHelper(s, i + 1, j - 1);
  }

}
