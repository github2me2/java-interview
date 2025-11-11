package org.interview.linked;

import java.util.List;

/**
 * 反转链表
 */
public class ReverseLinkedList {

    public static void main(String[] args) {
        // 1->2->3->4->5->null
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        head.print();

        System.out.println();
        ListNode result = reverseHeadInsertion(head);
        result.print();

        System.out.println();
        result = reversePart(result, 2, 4);
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        public void print() {
            ListNode tempNode = this;
            while (tempNode != null) {
                System.out.print(tempNode.val + " ");
                tempNode = tempNode.next;
            }
        }
    }

    /**
     * 迭代法反转链表
     * 时间复杂度O(n)，空间复杂度O(1)
     * 迭代法反转链表的基本思想是，从原链表的头节点开始，逐个反转节点的指针，
     * 直到最后一个节点，然后将最后一个节点作为新链表的头节点，返回新链表的头节点。
     *
     * @param head
     * @return
     */
    public static ListNode reverseIteration(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }
        return prev;
    }

    /**
     * 递归法反转链表
     * 时间复杂度O(n)，空间复杂度O(n)
     * 递归法反转链表的基本思想是，从原链表的头节点开始，递归地反转后面的节点，
     * 直到最后一个节点，然后将最后一个节点作为新链表的头节点，返回新链表的头节点。
     *
     * @param head
     * @return
     */
    public static ListNode reverseRecursion(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseRecursion(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    /**
     * 头插法反转链表
     * 时间复杂度O(n)，空间复杂度O(1)
     * 头插法反转链表的基本思想是，从原链表中依次取出节点，插入到新链表的头部，从而实现反转。
     *
     * @param head
     * @return
     */
    public static ListNode reverseHeadInsertion(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = newHead;
            newHead = head;
            head = next;
        }
        return newHead;
    }

    /**
     * 变种1：反转链表的一部分，只对指定范围内的链表进行反转，其他节点保持不变。
     * 时间复杂度O(n)，空间复杂度O(1)
     * 变种1的基本思想是，先找到指定范围内的链表，然后对该范围内的链表进行反转，
     * 最后将反转后的链表与其他节点连接起来。
     *
     * @param head
     * @param left
     * @param right
     * @return
     */
    public static ListNode reversePart(ListNode head, int left, int right) {
        if (head == null || head.next == null || left >= right) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        for (int i = 0; i < left - 1; i++) {
            prev = prev.next;
        }
        ListNode curr = prev.next;
        for (int i = 0; i < right - left; i++) {
            ListNode next = curr.next;
            curr.next = next.next;
            next.next = prev.next;
            prev.next = next;
        }
        return dummy.next;
    }
}
