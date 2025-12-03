package org.interview.linked;

import java.util.List;

/**
 * 相交链表： 给定两个单链表的头节点headA和headB，判断这两个链表是否相交。如果相交，返回相交的节点；否则返回null。
 */
public class IntersectingLinkedLists {

    public static void main(String[] args) {

        ListNode listNode = new ListNode(1);
        listNode.next = new ListNode(2);
        listNode.next.next = new ListNode(3);
        listNode.next.next.next = new ListNode(4);
        listNode.next.next.next.next = new ListNode(5);
        listNode.next.next.next.next.next = new ListNode(6);

        ListNode listNode2 = new ListNode(9);
        listNode2.next = new ListNode(8);
//        listNode2.next.next = listNode.next.next.next.next;

        ListNode intersectingNode = intersectingLinkedListsTraverse(listNode,listNode2);
        if (intersectingNode != null) {
            System.out.println("相交节点的值为：" + intersectingNode.val);
        } else {
            System.out.println("两个链表不相交");
        }
    }

    /**
     * 相交链表： 给定两个单链表的头节点headA和headB，判断这两个链表是否相交。如果相交，返回相交的节点；否则返回null。
     * 时间复杂度O(m+n)，空间复杂度O(1)
     * 双指针法
     *
     * @param headA
     * @param headB
     * @return
     */
    public static ListNode intersectingLinkedListsPoints(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode pA = headA, pB = headB;
        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }

    /**
     * 相交链表： 给定两个单链表的头节点headA和headB，判断这两个链表是否相交。如果相交，返回相交的节点；否则返回null。
     * 时间复杂度O(m+n)，空间复杂度O(1)
     * 遍历法
     *
     * @param headA
     * @param headB
     * @return
     */
    public static ListNode intersectingLinkedListsTraverse(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode pA = headA, pB = headB;
        int lengthA = 0;
        int lengthB = 0;
        while (pA != null || pB != null) {
            if (pA != null) {
                lengthA++;
                pA = pA.next;
            }
            if (pB != null) {
                lengthB++;
                pB = pB.next;
            }
        }

        int diff = lengthA - lengthB;

        pA = headA;
        pB = headB;
        while (pA != pB) {
            if (diff > 0) {
                pA = pA.next;
                diff--;
            } else if (diff < 0) {
                pB = pB.next;
                diff++;
            }
            if (diff == 0) {
                pA = pA.next;
                pB = pB.next;
            }
        }

        return pA;
    }

}
