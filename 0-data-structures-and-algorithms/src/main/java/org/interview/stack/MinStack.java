package org.interview.stack;

import java.util.ArrayList;
import java.util.List;

/**
 * 最小栈： 设计一个支持push、pop、top操作，并能在常数时间内检索到最小元素的栈。
 * 时间复杂度O(1)，空间复杂度O(n)
 */
public class MinStack {

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin()); // 返回 -3
        minStack.pop();
        System.out.println(minStack.top());    // 返回 0
        System.out.println(minStack.getMin()); // 返回 -2
    }

    List<Integer> stack = new ArrayList<>();
    List<Integer> minStack = new ArrayList<>();

    /**
     * 入栈操作：将元素压入栈中，并更新最小栈
     * 时间复杂度O(1)，空间复杂度O(1)
     *
     * @param val
     */
    public void push(int val) {
        stack.add(val);
        if (minStack.isEmpty() || val <= minStack.get(minStack.size() - 1)) {
            minStack.add(val);
        }
    }

    /**
     * 出栈操作：将栈顶元素弹出，并更新最小栈
     * 时间复杂度O(1)，空间复杂度O(1)
     */
    public void pop() {
        int val = stack.remove(stack.size() - 1);
        if (val == minStack.get(minStack.size() - 1)) {
            minStack.remove(minStack.size() - 1);
        }
    }

    /**
     * 获取栈顶元素：返回栈顶元素，但不弹出
     * 时间复杂度O(1)，空间复杂度O(1)
     *
     * @return 栈顶元素
     */
    public int top() {
        return stack.get(stack.size() - 1);
    }

    /**
     * 获取最小元素：返回栈中的最小元素，但不弹出
     * 时间复杂度O(1)，空间复杂度O(1)
     *
     * @return 最小元素
     */
    public int getMin() {
        return minStack.get(minStack.size() - 1);
    }
}
