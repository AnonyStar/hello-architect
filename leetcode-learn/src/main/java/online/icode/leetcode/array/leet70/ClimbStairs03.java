package online.icode.leetcode.array.leet70;

/**
 * @author: zhoucx
 * @time: 2021/1/31 15:20
 */
public class ClimbStairs03 {


    /*
     //假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
//
// 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
     */

    public int climbStairs(int n) {
        //递归实现
        if (n <= 3) return n;

        int f1 = 1, f2 = 2, f3 = 3;

        for (int i = 3; i <= n; i++) {
            f3 = f2 + f1;
            f1 = f2;
            f2 = f3;
        }
        return f3;


    }

}
