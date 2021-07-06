package com.prgers;

import com.prgers.circle.CircleDeque;
import com.prgers.circle.CircleQueue;

/**
 * @Author prgers
 * @Date 2021/7/5 9:48 下午
 */
public class Main {

    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();
    }

    public static void test1() {
        Queue<Integer> queue = new Queue<>();
        queue.enQueue(10);
        queue.enQueue(20);
        queue.enQueue(30);
        queue.enQueue(40);

        while (!queue.isEmpty()) {
            System.out.println(queue.deQueue());
        }
    }

    public static void test2() {
        Deque<Integer> deque = new Deque<>();

        //尾 40 30 10 20 头
        deque.enQueueFront(10);
        deque.enQueueFront(20);
        deque.enQueueRear(30);
        deque.enQueueRear(40);


        while (!deque.isEmpty()) {
            System.out.println(deque.deQueueRear());
        }
    }

    public static void test3() {
        CircleQueue<Integer> queue = new CircleQueue<>();

        // 0 1 2 3 4 5 6 7 8 9
        for (int i = 0; i < 10; i++) {
            queue.enQueue(i);
        }
        // null null null null null 5 6 7 8 9
        for (int i = 0; i < 5; i++) {
            queue.deQueue();
        }
        // 15 16 17 18 19 5 6 7 8 9
        for (int i = 15; i < 20; i++) {
            queue.enQueue(i);
        }
        while (!queue.isEmpty()) {
            System.out.println(queue.deQueue());
        }
        System.out.println(queue);
    }

    public static void test4() {
        CircleDeque<Integer> deque = new CircleDeque<>();
        // 头5 4 3 2 1  100 101 102 103 104 105 106 8 7 6 尾

        // 头 8 7 6  5 4 3 2 1  100 101 102 103 104 105 106 107 108 109 null null 10 9 尾
        for (int i = 0; i < 10; i++) {
            deque.enQueueFront(i + 1);
            deque.enQueueRear(i + 100);
        }

        // 头 null 7 6  5 4 3 2 1  100 101 102 103 104 105 106 null null null null null null null 尾
        for (int i = 0; i < 3; i++) {
            deque.deQueueFront();
            deque.deQueueRear();
        }

        // 头 11 7 6  5 4 3 2 1  100 101 102 103 104 105 106 null null null null null null 12 尾
        deque.enQueueFront(11);
        deque.enQueueFront(12);
        System.out.println(deque);
        while (!deque.isEmpty()) {
            System.out.println(deque.deQueueFront());
        }
    }
}
