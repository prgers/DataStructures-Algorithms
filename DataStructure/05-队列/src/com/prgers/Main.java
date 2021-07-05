package com.prgers;

import com.prgers.circle.CircleQueue;

/**
 * @Author prgers
 * @Date 2021/7/5 9:48 下午
 */
public class Main {

    public static void main(String[] args) {
//        test1();
//        test2();

        test3();
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
//        while (!queue.isEmpty()) {
//            System.out.println(queue.deQueue());
//        }
        System.out.println(queue);
    }
}
