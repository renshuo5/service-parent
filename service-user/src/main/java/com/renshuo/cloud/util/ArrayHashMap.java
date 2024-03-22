package com.renshuo.cloud.util;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 基于数组简易实现的哈希表
 */
public class ArrayHashMap {

    private List<Pair> buckets;

    //设置初始桶的大小为100
    public ArrayHashMap() {
        buckets = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            buckets.add(null);
        }
    }

    private int hashFunction(int key) {
        int index = key % 100;
        return index;
    }

    public void put(int key, String val) {
        Pair p = new Pair(key, val);
        int i = hashFunction(key);
        buckets.set(i, p);
    }

    public String get(int key) {

        int i = hashFunction(key);
        Pair pair = buckets.get(i);
        if (pair == null) {
            return null;
        }
        return pair.val;
    }

    public void remove(int key) {

        int i = hashFunction(key);
        buckets.set(i, null);
    }

    public Set<Pair> pairSet() {

        Set<Pair> pairSet = new HashSet<>();
        for (Pair pair : buckets) {
            if (pair != null)
                pairSet.add(pair);
        }
        return pairSet;
    }

    public List<Integer> keySet() {
        List<Integer> keys = new ArrayList<>();
        for (Pair pair : buckets) {
            if (pair != null)
                keys.add(pair.key);
        }
        return keys;
    }

    public List<String> valueSet() {
        List<String> values = new ArrayList<>();
        for (Pair pair : buckets) {
            if (pair != null)
                values.add(pair.val);
        }
        return values;
    }

    /* 打印哈希表 */
    public void print() {
        for (Pair kv : pairSet()) {
            System.out.println(kv.key + " -> " + kv.val);
        }
    }

}

@AllArgsConstructor
class Pair {
    public int key;
    public String val;

}
