package com.prger;

import com.prger.set.ListSet;
import com.prger.set.Set;
import com.prger.set.Set.Visitor;
import com.prger.set.TreeSet;

/**
 * @Author prgers
 * @Date 2021/7/20 3:44 下午
 */
public class Main {
    public static void main(String[] args) {

        treeSet();

    }

    public static void treeSet() {
        TreeSet<Integer> treeSet = new TreeSet<>();
        treeSet.add(10);
        treeSet.add(11);
        treeSet.add(11);
        treeSet.add(12);
        treeSet.add(10);

        treeSet.traversal(new Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                System.out.println(element);

                return false;
            }
        });
    }

    public static void listSet() {
        Set<Integer> listSet = new ListSet<>();
        listSet.add(10);
        listSet.add(11);
        listSet.add(11);
        listSet.add(12);
        listSet.add(10);

        listSet.traversal(new Visitor<Integer>() {
            @Override
            public boolean visit(Integer element) {
                System.out.println(element);

                return false;
            }
        });
    }
}
