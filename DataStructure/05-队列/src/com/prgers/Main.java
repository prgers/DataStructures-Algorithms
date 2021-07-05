package com.prgers;

/**
 * @Author prgers
 * @Date 2021/7/5 9:48 下午
 */
public class Main {

    public static void main(String[] args) {
        Queue<Integer> queue = new Queue<>();
        queue.enQueue(10);
        queue.enQueue(20);
        queue.enQueue(30);
        queue.enQueue(40);

        while (!queue.isEmpty()) {
            System.out.println(queue.deQueue());
        }
    }
}
