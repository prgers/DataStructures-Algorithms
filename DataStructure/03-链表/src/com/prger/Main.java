package com.prger;

public class Main {

    public static void main(String[] args) {
        List<Integer> list = new LinkedList<>();
        list.add(20);
        list.add(0, 10);
        list.add(30);

        list.add(list.size(), 50);
        list.set(list.size() - 1, 40);

        System.out.println(list);
    }

}
