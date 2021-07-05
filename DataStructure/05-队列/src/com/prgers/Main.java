package com.prgers;

/**
 * @Author prgers
 * @Date 2021/7/5 9:48 下午
 */
public class Main {

    public static void main(String[] args) {
//        test1();
        test2();
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
            System.out.println(deque.deQueueFront());
        }
    }
}
