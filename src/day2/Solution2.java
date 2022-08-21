package day2;

public class Solution2 {
    // - 状态 dp[i][j] = (s[i] == s[j]) and dp[i+1][j-1]
    // 边界条件：j-1-(i+1)+1<2, 整理得 j-i<3 <==> j-i+1<4
    // s[i..j] 长度为2或者3时，不用检查子串是否回文
    // 初始化 dp[i][j] = true
    public String longestPalidrome(String s) {
        int len = s.length();
        if (len < 2) {
            return s;
        }
        int maxLen = 1;
        int begin = 0;
        // d[i][j]表示s[i...j]是否回文
        boolean[][] dp = new boolean[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }
        char[] charArray = s.toCharArray();
        for (int j = 0; j < len; j++) {
            for (int i = 0; i < j; i++) {
                if (charArray[i] != charArray[j]) {
                    dp[i][j] = false;
                } else {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }
                // 只要dp[i][j] == true 成立， 就表示子串s[i...j]是回文，此时记录回文长度和起始位置
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }


}
