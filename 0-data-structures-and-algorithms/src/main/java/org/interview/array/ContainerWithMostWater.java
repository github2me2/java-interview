package org.interview.array;

/**
 * 盛最多水的容器： 给定一个数组，每个元素代表一个容器的高度，计算容器最多可以盛多少水。考点：双指针的运用
 */
public class ContainerWithMostWater {

    public static void main(String[] args) {
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        int maxArea = maxArea(height);
        System.out.println(maxArea);

        int[] height2 = {0,2,0,1,1,0,1,1,1,1,2,1};
        int totalRainWater = totalRainWater(height2);
        System.out.println(totalRainWater);
    }

    /**
     * 双指针解法：使用两个指针，一个指向数组的头，一个指向数组的尾，每次移动较短的指针，计算当前容器的面积，更新最大面积
     * 时间复杂度O(n)，空间复杂度O(1)
     *
     * @param height
     * @return
     */
    public static int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;
        while (left < right) {
            int area = Math.min(height[left], height[right]) * (right - left);
            maxArea = Math.max(maxArea, area);
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxArea;
    }

    /**
     * 接雨水问题：给定一个数组，每个元素代表一个容器的高度，计算容器最多可以接多少雨水。考点：双指针的运用
     * 时间复杂度O(n)，空间复杂度O(1)
     *
     * @param height
     * @return
     */
    public static int totalRainWater(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int leftMax = 0;
        int rightMax = 0;
        int totalWater = 0;
        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] >= leftMax) {
                    leftMax = height[left];
                } else {
                    totalWater += leftMax - height[left];
                }
                left++;
            } else {
                if (height[right] > rightMax) {
                    rightMax = height[right];
                } else {
                    totalWater += rightMax - height[right];
                }
                right--;
            }
        }
        return totalWater;
    }
}
