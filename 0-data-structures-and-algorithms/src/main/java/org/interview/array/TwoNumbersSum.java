package org.interview.array;

import java.util.HashMap;
import java.util.Map;

/**
 * 两数之和： 给定一个数组和一个目标值，找出和为目标值的两个数。考点：哈希表的运用
 */
public class TwoNumbersSum {

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] result = simple(nums, target);
        System.out.println(result[0] + " " + result[1]);
        result = hashTable(nums, target);
        System.out.println(result[0] + " " + result[1]);
        result = orderedDoublePointer(nums, target);
        System.out.println(result[0] + " " + result[1]);
    }

    /**
     * 简单解法：使用两层循环，计算是否有符合num[i] + num[j] = target的值
     * 时间复杂度O(n^2)，空间复杂度O(1)
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] simple(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }

    /**
     * 哈希表解法：使用哈希表存储数组中的每个元素和其索引，在遍历数组时，判断哈希表中是否存在目标值与当前元素的差值
     * 时间复杂度O(n)，空间复杂度O(n)
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] hashTable(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return new int[0];
    }

    /**
     * 有序数组双指针解法：假设数组本身是有序的，使用双指针从数组的两端向中间移动，判断是否有符合条件的两个数
     * 时间复杂度O(nlogn)，空间复杂度O(1)
     * 注意：该方法要求数组是有序的
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] orderedDoublePointer(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum == target) {
                return new int[]{left, right};
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return new int[0];
    }
}
