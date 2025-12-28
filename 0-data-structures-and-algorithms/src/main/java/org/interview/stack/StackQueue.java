package org.interview.stack;

import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * 栈实现队列
 */
public class StackQueue {

     private final Stack<Integer> inStack = new Stack<>();
     private final Stack<Integer> outStack = new Stack<>();

    public static void main(String[] args) {
        StackQueue stackQueue = new StackQueue();
        stackQueue.push(1);
        stackQueue.push(2);
        stackQueue.push(3);
        stackQueue.push(4);
        System.out.println(stackQueue.peek());
        System.out.println(stackQueue.pop());
        System.out.println(stackQueue.pop());
        stackQueue.push(5);
        stackQueue.push(6);
    }

     /**
      * 入队
      * 时间复杂度O(1)，空间复杂度O(1)
      *
      * @param x
      */
     public void push(int x) {
         inStack.push(x);
     }

     public int pop() {
         if (outStack.isEmpty()) {
             while (!inStack.isEmpty()) {
                 outStack.push(inStack.pop());
             }
         }
         return outStack.pop();
     }

     /**
      * 出队
      * 时间复杂度O(1)，空间复杂度O(1)
      *
      * @return
      */
     public int peek() {
         if (outStack.isEmpty()) {
             while (!inStack.isEmpty()) {
                 outStack.push(inStack.pop());
             }
         }
         return outStack.peek();
     }
}
