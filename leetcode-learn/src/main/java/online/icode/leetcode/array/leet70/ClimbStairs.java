package online.icode.leetcode.array.leet70;

/**
 * @author: zhoucx
 * @time: 2021/1/19 9:33
 */
public class ClimbStairs {

    //假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
//
// 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
//
// 注意：给定 n 是一个正整数。
//
// 示例 1：
//
// 输入： 2
//输出： 2
//解释： 有两种方法可以爬到楼顶。
//1.  1 阶 + 1 阶
//2.  2 阶
//
// 示例 2：
//
// 输入： 3
//输出： 3
//解释： 有三种方法可以爬到楼顶。
//1.  1 阶 + 1 阶 + 1 阶
//2.  1 阶 + 2 阶
//3.  2 阶 + 1 阶
//
// Related Topics 动态规划
// 👍 1421 👎 0


    /*
    1 : 1
    2 : 2
    3 : f(2) + f(1)
    4 : f(3) + f(2)
    n : f(n-1) + f(n-2)
    斐波那契数列
     */
    public int climbStairs(int n) {
        // 递归方式进行处理
        if (n <= 2) {
            return n;
        }
        return climbStairs(n - 1) + climbStairs(n - 2);
    }
    // 提交超过时间限制
}
