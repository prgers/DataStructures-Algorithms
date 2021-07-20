package com.prger;

import com.prger.map.Map;
import com.prger.map.Map.Visitor;
import com.prger.map.TreeMap;

/**
 * @Author prgers
 * @Date 2021/7/20 9:31 下午
 */
public class Main {

    public static void main(String[] args) {
        Map<String, Integer> map = new TreeMap<>();
        map.put("c", 2);
        map.put("a", 5);
        map.put("b", 6);
        map.put("a", 8);

        map.traversal(new Visitor<String, Integer>() {
            public boolean visit(String key, Integer value) {
                System.out.println(key + "_" + value);
                return false;
            }
        });
    }
}
