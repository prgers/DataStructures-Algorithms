package com.prgers;

/**
 * @Author prgers
 * @Date 2021/7/3 10:38 下午
 */
public class Main {

    public static void main(String[] args) {

        Stack<Integer> stack = new Stack<>();
        stack.push(10);
        stack.push(20);
        stack.push(30);
        stack.push(40);

        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }
}
