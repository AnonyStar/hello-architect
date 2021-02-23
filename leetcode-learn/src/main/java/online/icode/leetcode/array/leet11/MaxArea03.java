package online.icode.leetcode.array.leet11;

/**
 * @author: zhoucx
 * @time: 2021/1/31 11:09
 */
public class MaxArea03 {

    /*
    输入：[1,8,6,2,5,4,8,3,7]
    //输出：49
    //解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为
     */

    public int maxArea01(int[] height) {
        //暴力
        if (height == null || height.length < 2) return 0;
        int maxArea = 0;

        for (int i = 0; i < height.length - 1; i ++) {
            for (int j = i + 1; j < height.length; j ++) {
                int area = (j - i) * Math.min(height[i],height[j]);
                maxArea = Math.max(area,maxArea);
            }
        }
        return maxArea;
    }

    public int maxArea(int[] height) {
        //双指针
        if (height == null || height.length < 2) return 0;

        int maxArea = 0;
        //定义快慢指针
        for (int left = 0, right = height.length - 1; left < right ;) {
            int minHeight = height[left] > height[right] ? height[right --] : height[left ++];
            maxArea = Math.max(maxArea,(right - left + 1) * minHeight);
        }

        return maxArea;
    }
}
