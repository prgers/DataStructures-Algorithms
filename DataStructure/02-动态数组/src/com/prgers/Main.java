package com.prgers;

public class Main {
    
    public static void main(String[] args) {

        ArrayList list = new ArrayList();

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(1, 4);
        list.remove(1);
        list.set(0, 4);

        System.out.println("list = " + list);
    }
}
