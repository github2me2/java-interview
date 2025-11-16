package org.interview.linked;

import java.util.List;

/**
 * 合并两个有序链表
 */
public class MergeTwoLinkedLists {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        ListNode head2 = new ListNode(1);
        head2.next = new ListNode(3);
        head2.next.next = new ListNode(4);
        ListNode merged = mergeTwoLists(head, head2);
        merged.print();

        System.out.println();

        head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head2 = new ListNode(1);
        head2.next = new ListNode(3);
        head2.next.next = new ListNode(4);
        merged = mergeTwoListsRecursion(head, head2);
        merged.print();

        System.out.println();

        head = new ListNode(1);
        head.next = new ListNode(5);
        head.next.next = new ListNode(7);
        head.next.next.next = new ListNode(9);
        head2 = new ListNode(1);
        head2.next = new ListNode(3);
        head2.next.next = new ListNode(4);
        ListNode head3 = new ListNode(1);
        head3.next = new ListNode(2);
        head3.next.next = new ListNode(6);
        head3.next.next.next = new ListNode(8);
        ListNode[] heads = new ListNode[]{head, head2, head3};
        merged = mergeKListRecursion(heads);
        merged.print();

        System.out.println();

        head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head2 = new ListNode(1);
        head2.next = new ListNode(3);
        head2.next.next = new ListNode(4);
        merged = mergeKListInPlace(head, head2);
        merged.print();

        System.out.println();

        head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head2 = new ListNode(1);
        head2.next = new ListNode(3);
        head2.next.next = new ListNode(4);
        merged = mergeTwoListsDescend(head, head2);
        merged.print();
    }

    /**
     * 合并两个有序链表，迭代法
     * 时间复杂度O(n)，空间复杂度O(1)
     * 合并两个有序链表的基本思想是，从两个链表的头节点开始，依次比较节点的值，
     * 将较小的节点插入到新链表中，直到两个链表都为空。
     *
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }
        curr.next = l1 == null ? l2 : l1;
        return dummy.next;
    }

    /**
     * 合并两个有序链表，递归法
     * 时间复杂度O(n)，空间复杂度O(n)
     * 递归地比较两个链表的头节点，选择较小的节点作为当前节点，然后递归合并剩余部分。
     *
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode mergeTwoListsRecursion(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        if (l1.val < l2.val) {
            l1.next = mergeTwoListsRecursion(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoListsRecursion(l1, l2.next);
            return l2;
        }
    }

    /**
     * 变种1：合并k个有序链表
     * 时间复杂度O(nklogk)，空间复杂度O(logk)
     * 变种1的基本思想是使用分治法，先将k个链表分成两组，每组分别合并，
     * 然后递归地合并这两组链表，直到只剩下一个链表。
     *
     * @param lists
     * @return
     */
    public static ListNode mergeKListRecursion(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        return merge(lists, 0, lists.length - 1);
    }

    public static ListNode merge(ListNode[] lists, int l, int r) {
        if (l == r) {
            return lists[l];
        }
        int m = (l + r) / 2;
        ListNode l1 = merge(lists, l, m);
        ListNode l2 = merge(lists, m + 1, r);
        return mergeTwoLists(l1, l2);
    }

    /**
     * 变种2：原地合并，不创建新节点
     * 时间复杂度O(nk)，空间复杂度O(1)
     * 变种2的基本思想是，从第一个链表开始，依次合并后面的链表，
     * 每次合并时，将较小的节点插入到第一个链表中，直到所有链表都合并完成。
     *
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode mergeKListInPlace(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        if (l1.val > l2.val) {
            ListNode temp = l1;
            l1 = l2;
            l2 = temp;
        }

        ListNode prev = l1;
        ListNode curr1 = l1.next;
        ListNode curr2 = l2;

        while (curr1 != null && curr2 != null) {
            if (curr1.val <= curr2.val) {
                prev.next = curr1;
                curr1 = curr1.next;
            } else {
                prev.next = curr2;
                curr2 = curr2.next;
            }
            prev = prev.next;
        }
        prev.next = curr1 == null ? curr2 : curr1;
        return l1;
    }

    /**
     * 变种3：降序合并
     * 时间复杂度O(nk)，空间复杂度O(1)
     * 变种3的基本思想是，从第一个链表开始，依次合并后面的链表，
     * 每次合并时，将较大的节点插入到第一个链表中，直到所有链表都合并完成。
     */
    public static ListNode mergeTwoListsDescend(ListNode l1, ListNode l2) {
        ListNode merged = mergeTwoLists(l1, l2);
        return reverseRecursion(merged);
    }
    public static ListNode reverseRecursion(ListNode head) {
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
}
