package online.icode.leetcode.array.leet11;

/**
 * @author: zhoucx
 * @time: 2021/1/19 10:24
 */
public class MaxArea02 {

    public static void main(String[] args) {
        int[] arrs = {0,3,0,4,4,20};
        System.out.println(new MaxArea02().maxArea(arrs));
    }


    public int maxArea(int[] height) {
        //双指针
        int maxArea = 0;
        for (int x = 0, y = height.length - 1; x != y;) {
            int minHeight = height[x] > height[y] ? height[y --] : height[x ++];
            int area = (y - x + 1) * minHeight;
            maxArea = Math.max(area, maxArea);
        }
        return maxArea;
    }
}
