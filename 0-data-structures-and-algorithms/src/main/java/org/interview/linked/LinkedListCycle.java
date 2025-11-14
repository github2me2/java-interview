package org.interview.linked;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 判断链表中是否存在环
 */
public class LinkedListCycle {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
//        head.next.next.next.next = head.next;
// 1->2->3->4->2->...
//        head.print();
        boolean cycleHash = isCycleHash(head);
        System.out.println(cycleHash);

        head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
//        head.next.next.next.next = head.next;
        boolean cyclePointer = isCyclePointer(head);
        System.out.println(cyclePointer);


        head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = head.next.next;
        ListNode cycleEntrance = findCycleEntrance(head);
        System.out.println(cycleEntrance);
    }

    /**
     * 判断链表中是否存在环，使用哈希表记录每个节点是否被访问过。
     * 时间复杂度O(n)，空间复杂度O(n)
     *
     * @param head
     * @return
     */
    public static boolean isCycleHash(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        HashSet<ListNode> listNodes = new HashSet<>();
        while (head != null) {
            if (listNodes.contains(head)) {
                return true;
            }
            listNodes.add(head);
            head = head.next;
        }
        return false;
    }

    /**
     * 判断链表中是否存在环，使用快慢指针。
     * 时间复杂度O(n)，空间复杂度O(1)
     *
     * @param head
     * @return
     */
    public static boolean isCyclePointer(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return false;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        return true;
    }

    /**
     * 变种1：寻找环的入口
     * 依然使用快慢指针，先确定有环，然后将慢指针指向头节点，快指针保持在相遇点，
     * 然后快慢指针每次都移动一步，相遇点就是环的入口。
     * 时间复杂度O(n)，空间复杂度O(1)
     *
     * @param head
     * @return
     */
    public static ListNode findCycleEntrance(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }
        ListNode slow = head;
        ListNode fast = head.next;
        while (slow != fast) {
            if (fast == null || fast.next == null) {
                return null;
            }
            slow = slow.next;
            fast = fast.next.next;
        }
        slow = head;
        fast = fast.next;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return slow;
    }
}
