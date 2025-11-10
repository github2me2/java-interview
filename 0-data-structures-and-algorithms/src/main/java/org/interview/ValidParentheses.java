package org.interview;

import java.util.*;

/**
 * 有效的括号：给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * 有效字符串需满足：
 * 1. 左括号必须用相同类型的右括号闭合。
 * 2. 左括号必须以正确的顺序闭合。
 */
public class ValidParentheses {

    public static void main(String[] args) {
        boolean valid = isValid("()[]");
        System.out.println(valid);
        valid = isValidCounter("()[]");
        System.out.println(valid);
        valid = isValidVariant("(啊发射点发)啊手动阀[啊手动阀]");
        System.out.println(valid);
        int max = longestValidParenthesesStack("((()))(()");
        System.out.println(max);
    }

    /**
     * 栈解法：遍历字符串，遇到左括号入栈，遇到右括号出栈，判断是否匹配。
     * 时间复杂度O(n)，空间复杂度O(n)
     *
     * @param s
     * @return
     */
    public static boolean isValid(String s) {
        if (s == null || s.isEmpty()) {
            return false;
        }
        // 奇数个字符，一定不匹配
        if (s.length() % 2 != 0) {
            return false;
        }
        // 线程安全的栈实现，但是已经不推荐
//        Stack<Character> stack = new Stack<>();
        // 线程非安全的栈实现，使用双端队列实现栈
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // 左括号入栈
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else if (c == ')' || c == '}' || c == ']') {
                // 右括号出栈，判断是否匹配
                if (stack.isEmpty()) {
                    return false;
                }
                char top = stack.pop();
                if (top == '(' && c != ')') {
                    return false;
                }
                if (top == '{' && c != '}') {
                    return false;
                }
                if (top == '[' && c != ']') {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    /**
     * 变种1，只有一种括号 ()
     * 使用计数器代替栈实现，遍历字符串，遇到左括号计数器加1，遇到右括号计数器减1，
     * 最后判断计数器是否为0。
     * 时间复杂度O(n)，空间复杂度O(1)
     */
    public static boolean isValidCounter(String s) {
        if (s == null || s.isEmpty()) {
            return false;
        }
        if (s.length() % 2 != 0) {
            return false;
        }
        int counter = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                counter++;
            } else if (c == ')') {
                counter--;
            }
        }
        return counter == 0;
    }

    /**
     * 变种2，包含其他字符
     * 依然使用栈实现，维护合法的括号对。遇到非法的字符直接跳过。
     * 时间复杂度O(n)，空间复杂度O(n)
     */
    public static boolean isValidVariant(String s) {
        if (s == null || s.isEmpty()) {
            return false;
        }
        // 用map维护合法的括号对
        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put('}', '{');
        map.put(']', '[');
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            // 当存在于value中时，入栈
            if (map.containsValue(c)) {
                stack.push(c);
            } else if (map.containsKey(c)) {
                // 当存在于key中时，出栈判断是否匹配
                if (stack.isEmpty()) {
                    return false;
                }
                char top = stack.pop();
                if (top != map.get(c)) {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    /**
     * 变种3，最长有效括号子串：给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
     * 可以用栈，动态规划，双指针来解决。
     * 栈解法，维护一个栈和最大长度，初始化栈内第一个元素为-1，栈中维护字符的索引，遍历字符串，遇到左括号入栈，遇到右括号出栈，
     * 每次出栈时，判断当前栈是否为空，当栈为空时，将当前索引入栈，作为有效括号子串的开始位置。
     * 当栈不为空时，获取栈顶元素，更新最大长度为当前位置减去栈顶元素的索引。
     * 时间复杂度O(n)，空间复杂度O(n)
     */
    public static int longestValidParenthesesStack(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        int max = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(i);
            } else if (c == ')') {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    max = Math.max(max, i - stack.peek());
                }
            }
        }
        return max;
    }

    /**
     * 变种3的动态规划解法：
     * 动态规划解法的核心是维护一个和字符串等长的数组，dp[i]表示以第i个字符结尾的最长有效括号子串的长度。
     * 初始化dp[0] = 0，遍历字符串，当遇到右括号时，判断前一个字符是否为左括号，
     * 当前一个字符为左括号时，由于前面可能还有有效括号子串，所以dp[i] = dp[i-2] + 2，
     * 当前一个字符为右括号时，判断前一个字符的有效长度之前的前一个字符是否为左括号，
     * 如果是左括号，则匹配成功，由于前面可能还有有效括号子串，所以dp[i] = dp[i-1] + 2 + dp[i-dp[i-1]-2]，
     * 最后返回dp数组中的最大值。
     * 时间复杂度O(n)，空间复杂度O(n)
     */
    public static int longestValidParenthesesDP(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        int max = 0;
        int[] dp = new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + 2 + (i - dp[i - 1] >= 2 ? dp[i - dp[i - 1] - 2] : 0);
                }
                max = Math.max(max, dp[i]);
            }
        }
        return max;
    }

    /**
     * 变种3的双指针解法：
     * 双指针解法的核心是从左到右和从右到左各扫描一次，用两个计数器记录左右的括号的数量
     * 从左向右遍历字符串，当遇到左括号时，左记录加1，当遇到右括号时，右记录加1，
     * 如果左记录等于右记录，则更新最大长度为2倍的右记录，如果右记录大于左记录，则将左右的记录都重置为0
     * 从右向左遍历字符串，当遇到右括号时，右记录加1，当遇到左括号时，左记录加1，
     * 如果左记录等于右记录，则更新最大长度为2倍的左记录，如果左记录大于右记录，则将左右的记录都重置为0
     */
    public static int longestValidParenthesesTwoPointers(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        int max = 0;
        int left = 0, right = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                left++;
            } else if (c == ')') {
                right++;
            }
            if (left == right) {
                max = Math.max(max, 2 * right);
            } else if (right > left) {
                left = right = 0;
            }
        }
        left = right = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            if (c == '(') {
                left++;
            } else if (c == ')') {
                right++;
            }
            if (left == right) {
                max = Math.max(max, 2 * left);
            } else if (left > right) {
                left = right = 0;
            }
        }
        return max;
    }

    /**
     * 变种4，返回最长有效括号子串本身，而不是长度
     * 使用栈解法，与变种3的栈解法类似，不同的是需要维护一个起始索引，用于记录有效括号子串的开始位置。
     * 时间复杂度O(n)，空间复杂度O(n)
     */
     public static String valueValidParenthesesStack(String s) {
        if (s == null || s.isEmpty()) {
            return "";
        }
        int max = 0;
        int start = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(i);
            } else if (c == ')') {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    int currentLen = i - stack.peek();
                    if(max < currentLen) {
                        max = currentLen;
                        start = stack.peek() + 1;
                    }
                }
            }
        }
        return s.substring(start, start + max);
    }

    /**
     * 变种5，计算所有有效括号子串的数量
     * 使用栈解法，与变种4的栈解法类似，不同的是需要维护一个计数器，用于记录有效括号子串的数量。
     * 时间复杂度O(n)，空间复杂度O(n)
     */
    public static int numberValidParenthesesStack(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        int count = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(i);
            } else if (c == ')') {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    count++;
                }
            }
        }
        return count;
    }
}
