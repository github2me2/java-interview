package org.interview.linked;

public class DeleteLastN {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next.next = new ListNode(7);

        head.print();

        System.out.println();
        head = deleteLastNTraverse(head, 22);
        head.print();


    }

    /**
     * 双指针法，需要注意的是要添加临时头节点，为了应对删除头节点的情况
     * 第一次循环需要循环到n的位置
     *
     * @param head
     * @param n
     * @return
     */
    public static ListNode deleteLastNPointer(ListNode head, int n) {
        if (head == null || n <= 0) {
            return head;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode fast = dummy;
        ListNode slow = dummy;

        // 第一次循环需要循环到n的位置，因为要删除的是倒数第n个节点
        for (int i = 0; i <= n; i++) {
            if (fast == null) {
                // n大于链表长度，直接返回头节点
                return dummy.next;
            }
            fast = fast.next;
        }

        // 第二次循环，fast到达链表末尾时，slow.next就是要删除的节点
        while (fast != null) {
            slow = slow.next;
            fast = fast.next;
        }

        // 删除slow.next节点
        slow.next = slow.next.next;
        return dummy.next;
    }

    /**
     * 两次遍历法， 第一次找到k的位置，第二次进行删除
     */
    public static ListNode deleteLastNTraverse(ListNode head, int n) {
        if (head == null || n <= 0) {
            return head;
        }

        int length = 0;
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode temp = dummy;

        while (temp.next != null){
            length++;
            temp = temp.next;
        }

        temp = dummy;

        if (length - n < 0) {
            return dummy.next;
        }

        for (int i = 0; i < length - n; i++) {
            temp = temp.next;
        }

        temp.next = temp.next.next;

        return dummy.next;
    }
}
