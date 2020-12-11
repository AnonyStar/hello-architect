package online.icode.leetcode.twoSum01;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author: zhoucx
 * @time: 2020/11/20 16:06
 */
public class Solution {

    /*
    给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的
    那两个整数，并返回他们的数组下标。
    你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
        示例:

        给定 nums = [2, 7, 11, 15], target = 9

        因为 nums[0] + nums[1] = 2 + 7 = 9
        所以返回 [0, 1]
     */

    public static void main(String[] args) {
        int [] nums = {3,2,4};

        int num = 6;
        //solutionTest1(nums, num);
        System.out.println(solutionTest2(nums, num));
    }

    private static int[] solutionTest2(int[] nums, int target){
        if (null == nums) return null;
        Map map = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])){
                System.out.println("["+i+","+map.get(target-nums[i])+"]");
                return new int[]{i, (int) map.get(target-nums[i])};
            }else if (!map.containsKey(nums[i])){
                map.put(nums[i],i);
            }

        }
        return null;
    }

    private static void solutionTest1(int[] nums, int num) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i+1; j < nums.length; j++) {
                if (nums[i]+ nums[j] == num){
                    System.out.println("找到坐标");
                    System.out.println("["+i+","+j+"]");
                }
            }
        }
    }
}
