package org.interview.linked;

/**
 * 链表节点
 */
public class ListNode {
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

    @Override
    public String toString() {
        return val + "";
    }
}