package org.interview;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 无重复字符的最长子串： 给定一个字符串，找出不含有重复字符的最长子串的长度。考点：滑动窗口
 */
public class LongestSubstringWithoutRepeatingCharacters {

    public static void main(String[] args) {
        int maxLength = lengthOfLongestSubstring("abcabcbb");
        System.out.println(maxLength);

        //输出：3
//        maxLength = lengthOfLongestSubstringKDistinct("eceba", 2);
        //输出：2
//        maxLength = lengthOfLongestSubstringKDistinct("aa", 1);
        //输出：0
//        maxLength = lengthOfLongestSubstringKDistinct("a", 0);
        //输出：2
        maxLength = lengthOfLongestSubstringKDistinct("abcabcabc", 2);
        System.out.println(maxLength);

        // 3
        maxLength = lengthOfLongestSubstringMinimumKDistinctPartition("aaabb", 3);
        // 5
        maxLength = lengthOfLongestSubstringMinimumKDistinctPartition("ababbc", 2);
        // 0
        maxLength = lengthOfLongestSubstringMinimumKDistinctPartition("abcde", 2);
        // 6
        maxLength = lengthOfLongestSubstringMinimumKDistinctPartition("ababbcdfdfdf", 2);
        System.out.println(maxLength);

        // 3
        maxLength = lengthOfLongestSubstringMinimumKDistinctSlidingWindow("aaabb", 3);
        // 5
        maxLength = lengthOfLongestSubstringMinimumKDistinctSlidingWindow("ababbc", 2);
        // 0
        maxLength = lengthOfLongestSubstringMinimumKDistinctSlidingWindow("abcde", 2);
        // 6
        maxLength = lengthOfLongestSubstringMinimumKDistinctSlidingWindow("ababbcdfdfdf", 2);
        System.out.println(maxLength);
    }

    /**
     * 滑动窗口解法：使用双指针表示当前窗口的左右边界，使用哈希表存储窗口中的字符和其索引，
     * 当遇到重复字符时，移动左指针到重复字符的下一个位置，更新最大长度。
     * 时间复杂度O(n)，空间复杂度O(n)
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        int left = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        int max = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                left = map.get(c) + 1;
            }
            map.put(c, i);
            max = Math.max(max, i - left + 1);
        }

        return max;
    }

    /**
     * 最多包含K个不同字符的最长子串： 给定一个字符串，找出最多包含K个不同字符的最长子串的长度。考点：滑动窗口
     *
     * @param s
     * @param k
     * @return
     */
    public static int lengthOfLongestSubstringKDistinct(String s, int k) {
        // map中存放字符和其索引，当map的大小大于k时，移动左指针到重复字符的下一个位置，更新最大长度
        HashMap<Character, Integer> map = new HashMap<>();
        int left = 0;
        int max = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            map.put(c, i);
            if (map.size() > k) {
                // 找到map中索引最小的字符，移动左指针到其下一个位置，更新最大长度
                char minIndex = map.keySet().stream().min(Comparator.comparingInt(map::get)).get();
                left = map.get(minIndex) + 1;
                map.remove(minIndex);
            }
            // 更新最大长度
            max = Math.max(max, i - left + 1);
        }

        return max;
    }

    /**
     * 至少包含K个重复字符的最长子串 分治法： 给定一个字符串S，找出其中最长的子串T，要求T中至少包含K个重复字符。
     * 时间复杂度O(nlogn)，空间复杂度O(n)
     */
    public static int lengthOfLongestSubstringMinimumKDistinctPartition(String s, int k) {
        if (s == null || s.isEmpty() || k <= 0 || k > s.length()) {
            return 0;
        }
        // 统计字符串中每个字符出现的次数
        Map<Character, Long> map = s.chars().mapToObj(c -> (char) c).collect(Collectors.groupingBy(c -> c, Collectors.counting()));

        for (Map.Entry<Character, Long> entry : map.entrySet()) {
            if (entry.getValue() < k) {
                List<Integer> indices = new ArrayList<>();
                for (String sub : s.split(entry.getKey().toString())) {
                    // 递归处理子串，将子串中出现次数小于k的字符作为分隔符
                    indices.add(lengthOfLongestSubstringMinimumKDistinctPartition(sub, k));
                }
                return Collections.max(indices);
            }
        }
        return s.length();
    }

    /**
     * 至少包含K个重复字符的最长子串 滑动窗口法： 给定一个字符串S，找出其中最长的子串T，要求T中至少包含K个重复字符。
     * 时间复杂度O(n)，空间复杂度O(n)
     */
    public static int lengthOfLongestSubstringMinimumKDistinctSlidingWindow(String s, int k) {
        // 统计字符串中每个字符出现的次数
        Map<Character, Long> map = s.chars().mapToObj(c -> (char) c).collect(Collectors.groupingBy(c -> c, Collectors.counting()));

        // 找到所有出现次数小于k的字符
        List<Character> collect = map.entrySet().stream().filter(e -> e.getValue() < k).map(Map.Entry::getKey).collect(Collectors.toList());

        // 如果所有字符出现次数都大于等于k，返回字符串长度
        if (collect.isEmpty()) {
            return s.length();
        }
        // 如果所有字符出现次数都小于k，返回0
        if (collect.size() == map.size()) {
            return 0;
        }

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (collect.contains(c)) {
                // 如果当前字符出现次数小于k，递归处理子串，将子串中出现次数小于k的字符作为分隔符
                int leftPart = lengthOfLongestSubstringMinimumKDistinctSlidingWindow(s.substring(0, i), k);
                int rightPart = lengthOfLongestSubstringMinimumKDistinctSlidingWindow(s.substring(i + 1), k);
                // 返回左右子串中较长的那个
                return Math.max(leftPart, rightPart);
            }
        }
        // 如果没有出现次数小于k的字符，返回字符串长度
        return s.length();
    }

}
