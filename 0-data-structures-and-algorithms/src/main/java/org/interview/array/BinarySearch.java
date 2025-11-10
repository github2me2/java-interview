package org.interview.array;

/**
 * 二分查找，在有序数组中查找目标值的索引。
 * 时间复杂度O(logn)，空间复杂度O(1)
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int target = 5;
        int index = binarySearch(nums, target);
        System.out.println(index);
    }

    /**
     * 二分查找，在有序数组中查找目标值的索引。
     * 关键点在于循环条件，left <= right，而不是left < right。
     * 时间复杂度O(logn)，空间复杂度O(1)
     *
     * @param nums
     * @param target
     * @return
     */
    public static int binarySearch(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}
