package org.interview.linked;

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
        result.print();

        System.out.println();
        result = reverseKGroup(result, 3);
        result.print();

        System.out.println();
        result = reverseAdjacent(result);
        result.print();
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

    /**
     * 变种2：k个一组的进行反转，每k个节点为一组，对每组节点进行反转，不满k个节点的组不进行反转。
     * 例如k等于2，链表为1->2->3->4->5->6->7->8->9，
     * 则返回2->1->4->3->6->5->8->7->9。
     * 最后返回新链表的头节点。
     */
    public static ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k <= 1) {
            return head;
        }
        // 先找到每一组的头节点和尾节点，然后对每组节点进行反转，
        // 最后将反转后的链表与其他节点连接起来。
        ListNode dummy = new ListNode(0);
        // 虚拟头节点，指向原链表的头节点
        dummy.next = head;
        // 上一组的尾节点，初始时指向虚拟头节点
        ListNode prev = dummy;
        while (head != null) {
            // 找到当前组的尾节点
            // 如果当前组的节点数不足k个，直接返回虚拟头节点的下一个节点
            // 说明当前组的节点数不足k个，不需要反转
            ListNode tail = prev;
            for (int i = 0; i < k; i++) {
                tail = tail.next;
                if (tail == null) {
                    return dummy.next;
                }
            }
            // 记录下一组的头节点
            ListNode nextGroup = tail.next;
            // 反转当前组的节点
            ListNode[] reversed = reverseSegment(head, tail);
            head = reversed[0];
            tail = reversed[1];
            // 将反转后的链表与其他节点连接起来
            prev.next = head;
            tail.next = nextGroup;

            // 更新上一组的尾节点为当前组的尾节点
            prev = tail;
            // 更新当前组的头节点为下一组的头节点
            head = nextGroup;
        }
        return dummy.next;
    }

    /**
     * 反转链表的一部分，只，其他节点保持不变。
     * 时间复杂度O(n)，空间复杂度O(1)
     *
     * @param head
     * @param tail
     * @return
     */
    private static ListNode[] reverseSegment(ListNode head, ListNode tail) {
        ListNode prev = tail.next;
        ListNode curr = head;
        while (prev != tail) {
            ListNode temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }
        return new ListNode[]{tail, head};
    }

    /**
     * 变种3：反转相邻节点，例如1->2->3->4->5->6，
     * 则返回2->1->4->3->6->5。
     * 时间复杂度O(n)，空间复杂度O(1)
     */
    public static ListNode reverseAdjacent(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = head.next;
        head.next = reverseAdjacent(newHead.next);
        newHead.next = head;
        return newHead;
    }
}
