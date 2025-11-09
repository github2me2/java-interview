package org.interview;

/**
 * 移动零： 给定一个数组，将所有的0移动到数组的末尾，同时保持非零元素的相对顺序。考点：双指针的运用
 */
public class MoveZero {

    public static void main(String[] args) {
        int[] nums = {0, 1, 0, 3, 12};
        moveZero(nums);
        for (int num : nums) {
            System.out.print(num + " ");
        }

        System.out.println();

        int[] nums2 = {1, 0, 3, 12, 0, 0, 15, 34};
        moveZero(nums2);
        for (int num : nums2) {
            System.out.print(num + " ");
        }
    }

    /**
     * 双指针解法：使用两个指针，一个指向当前遍历到的元素，一个指向下一个非零元素的位置
     * 时间复杂度O(n)，空间复杂度O(1)
     *
     * @param nums
     */
    public static void moveZero(int[] nums) {
        int n = nums.length;
        int left = 0, right = 0;
        while (right < n) {
            if (nums[right] != 0) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++;
            }
            right++;
        }
    }
}
