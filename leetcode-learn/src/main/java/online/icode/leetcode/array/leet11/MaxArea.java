package online.icode.leetcode.array.leet11;

/**
 * @author: zhoucx
 * @time: 2021/1/19 9:51
 */
public class MaxArea {


    /**
     * 	解答成功:
     * 		执行耗时:838 ms,击败了17.72% 的Java用户
     * 		内存消耗:39.8 MB,击败了77.88% 的Java用户
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        // 暴力解法
        int maxArea = 0;
        for (int i = 0; i < height.length - 1; i ++) { //左边界
            for (int y = i + 1; y < height.length; y ++) { // 右边界
                // 计算面积
                int area = (y - i) * Math.min(height[i], height[y]);
                maxArea = Math.max(area, maxArea);
            }
        }
        return maxArea;
    }


}
