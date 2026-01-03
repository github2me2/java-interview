package org.interview.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 滑动窗口最大值： 给定一个数组nums和一个滑动窗口的大小k，找出所有滑动窗口里的最大值。
 */
public class MaxSlidingWindow {

    public static void main(String[] args) {
        int[] nums = {1,3,-1,-3,5,3,6,7};
        int k = 3;
        int[] maxSlidingWindow = maxSlidingWindow(nums, k);
        for (int num : maxSlidingWindow) {
            System.out.print(num + " ");
        }
        System.out.println();
        int[] minSlidingWindow = minSlidingWindow(nums, k);
        for (int num : minSlidingWindow) {
            System.out.print(num + " ");
        }
    }

    /**
     * 滑动窗口最大值： 给定一个数组nums和一个滑动窗口的大小k，找出所有滑动窗口里的最大值。
     * 时间复杂度O(n)，空间复杂度O(k)
     * 双端队列法
     *
     * @param nums
     * @param k
     * @return
     */
    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0 || k > nums.length) {
            return new int[0];
        }
        int n = nums.length;
        int[] res = new int[n - k + 1];
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            // 1. 移除超出窗口的元素（从队首）
            while (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                deque.pollFirst();
            }
            // 2. 维护单调递减性（从队尾）
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }

            // 3. 将当前元素索引加入队尾
            deque.offerLast(i);

            // 4. 当窗口形成时，记录最大值
            if (i >= k - 1) {
                res[i - k + 1] = nums[deque.peekFirst()];
            }
        }
        return res;
    }

    /**
     * 滑动窗口最小值： 给定一个数组nums和一个滑动窗口的大小k，找出所有滑动窗口里的最小值。
     * 时间复杂度O(n)，空间复杂度O(k)
     * 双端队列法, 与最大值的双端队列法类似, 只是维护的是单调递增性
     *
     * @param nums
     * @param k
     * @return
     */
    public static int[] minSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0 || k > nums.length) {
            return new int[0];
        }
        int n = nums.length;
        int[] res = new int[n - k + 1];
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            // 1. 移除超出窗口的元素（从队首）
            while (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                deque.pollFirst();
            }
            // 2. 维护单调递增性（从队尾）
            while (!deque.isEmpty() && nums[i] <= nums[deque.peekLast()]) {
                deque.pollLast();
            }

            // 3. 将当前元素索引加入队尾
            deque.offerLast(i);

            // 4. 当窗口形成时，记录最小值
            if (i >= k - 1) {
                res[i - k + 1] = nums[deque.peekFirst()];
            }
        }
        return res;
    }
}
