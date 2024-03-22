package com.renshuo.cloud.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 链式地址哈希表
 */
public class HashMapChaining {

    int size;
    int capacity; // 哈希表容量
    double loadThres; //负载因子
    int extendRatio; // 扩容倍数
    List<List<Pair>> buckets; // 桶数组

    /* 构造方法 */
    public HashMapChaining() {
        size = 0;
        capacity = 4;
        loadThres = 2.0 / 3.0;
        extendRatio = 2;
        buckets = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            buckets.add(new ArrayList<>());
        }
    }

    int hashFunction(int key) {
        return key % capacity;
    }

    double loadFactor() {
        return (double) size / capacity;
    }

    String get(int key) {

        int index = hashFunction(key);
        List<Pair> bucket = buckets.get(index);
        for (Pair pair : bucket) {
            if (pair != null) {
                if (pair.key == key)
                    return pair.val;
            }
        }
        // 若未找到 key 则返回 null
        return null;

    }

    void put(int key,String val){

        if(loadFactor()>loadThres){
            //执行扩容
            extend();
        }
        int i = hashFunction(key);
        List<Pair> pairs = buckets.get(i);
        for (Pair pair : pairs) {
            if(pair.key==key){
                pair.val = val;
            }
        }
        Pair p = new Pair(key,val);
        pairs.add(p);
        size++;
    }

    void extend() {

        // 暂存原哈希表
        List<List<Pair>> bucketsTmp = buckets;

        capacity *= extendRatio;
        buckets = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            buckets.add(new ArrayList<>());
        }
        size = 0;
        for (List<Pair> bucket : bucketsTmp) {
            for (Pair pair : bucket) {
                put(pair.key,pair.val);
            }
        }
    }

    /* 删除操作 */
    void remove(int key) {
        int index = hashFunction(key);
        List<Pair> bucket = buckets.get(index);
        // 遍历桶，从中删除键值对
        for (Pair pair : bucket) {
            if (pair.key == key) {
                bucket.remove(pair);
                size--;
                break;
            }
        }
    }

    /* 打印哈希表 */
    void print() {
        for (List<Pair> bucket : buckets) {
            List<String> res = new ArrayList<>();
            for (Pair pair : bucket) {
                res.add(pair.key + " -> " + pair.val);
            }
            System.out.println(res);
        }
    }


}
