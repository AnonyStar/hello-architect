package online.icode.leetcode.leet125;

import online.icode.leetcode.Test;

/**
 * @author: zhoucx
 * @time: 2021/1/18 16:35
 */
public class isPalindrome {

      /*
    给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。

    说明：本题中，我们将空字符串定义为有效的回文串。

    示例 1:

    输入: "A man, a plan, a canal: Panama"
    输出: true
     */

    public static void main(String[] args) {
        String s = "A man, a plan, a canal: Panama";
        Test test = new Test();
        final boolean palindrome = test.isPalindrome(s);
        System.out.println(palindrome);
        // s.equalsIgnoreCase()
    }

    public boolean isPalindrome(String s) {
        // 1.去除除字母的字符
        String filerS = filterNonNumOrChar(s);
        //2.进行字符串反转
        String result = filterReverseStr(filerS);
        //3. 比较与原始字符是否相同
        return filerS.equalsIgnoreCase(result);

    }

    private String filterNonNumOrChar(String s) {
        return s.replaceAll("[^a-zA-Z0-9]", "");
    }

    private String filterReverseStr(String s) {
        return new StringBuilder(s).reverse().toString();
    }
}
