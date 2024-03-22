package com.renshuo.cloud.demo.zhipin;

import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Created by Lenovo on 2021/3/31.
 */
public class LRUCache {

    private List<Total> list = null;
//    private List<Map<Integer,Total>> list = null;

    private int capacity;

    public LRUCache(int capacity) {
        list = new ArrayList(capacity);
        this.capacity= capacity;
    }

    public int get(int key) {

        int res = -1;



        int reduce = list.size()==0?0:list.stream().mapToInt(total -> total.getNum()).max().getAsInt();


        for (Total total : list) {
            if(total.getKey()==key){
                res = total.getValue();
                if(reduce!=0) {
                    total.setNum(reduce + 1);
                }
            }
        }

        return res;
    }


    public void put(int key, int value) {

        List<Total> collect = list.stream().filter(t -> t.getKey() == key).collect(Collectors.toList());


        int reduce = list.size()==0?0:list.stream().mapToInt(total -> total.getNum()).max().getAsInt();
        //key集合中有
        if(collect==null || collect.size()==0){
            if(list.size()==capacity) {
                int min = list.stream().mapToInt(Total::getNum).min().getAsInt();
                int keys = list.stream().filter(t -> t.getNum() == min).mapToInt(Total::getKey).findFirst().getAsInt();
                boolean b = list.removeIf(total -> total.getNum() == min);
                if (b) {
                    System.out.println("删除成功"+keys);
                }
            }
            Total total = new Total();
            total.setKey(key);
            total.setValue(value);
            total.setNum(reduce+1);
            list.add(total);
        }else{

            Total total1 = collect.get(0);
            total1.setValue(value);
            total1.setNum(reduce+1);

        }
    }

    public static void main(String [] args ){
        LRUCache cache = new LRUCache(2);

//        cache.get(1);
//
//        cache.put(1,1);
//        cache.put(2,2);
//        System.out.println("返回1----");
//        int i = cache.get(1);
//        System.out.println(i);
//        cache.put(3,3);
//        System.out.println("该操作会使2作废----");
//
//        int ii = cache.get(2);
//        System.out.println("返回null----");
//
//        System.out.println(ii);
//        cache.put(4,4);
//        System.out.println("该操作会使1作废----");
//        int n = cache.get(1);
//        System.out.println("返回1===="+n);
//
//        int m = cache.get(3);
//        System.out.println("返回3===="+m);
//
//        int k = cache.get(4);
//        System.out.println("返回4===="+4);

//      ["LRUCache","put","put","get","put","get","put","get","get","get"]
//      [[2],[1,1],[2,2],[1],[3,3],[2],[4,4],[1],[3],[4]]

//        cache.put(1,1);
//        System.out.println("null");
//        cache.put(2,2);
//        System.out.println("null");
//
//        System.out.println(cache.get(1));
//        cache.put(3,3);
//        System.out.println("null");
//
//        System.out.println(cache.get(2));
//        cache.put(4,4);
//        System.out.println("null");
//
//        System.out.println(cache.get(1));
//        System.out.println(cache.get(3));
//        System.out.println(cache.get(4));

//        ["LRUCache","get","put","get","put","put","get","get"]
//        [[2],[2],[2,6],[1],[1,5],[1,2],[1],[2]]

        System.out.println(cache.get(2));
        cache.put(2,6);
        System.out.println("null");
        System.out.println(cache.get(1));
        cache.put(1,5);
        System.out.println("null");
        cache.put(1,2);
        System.out.println("null");
        System.out.println(cache.get(1));
        System.out.println(cache.get(2));





    }



    class Total{
        private int key ;
        private int value;
        private int num;

        public int getKey() {
            return key;
        }

        public void setKey(int key) {
            this.key = key;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }
    }
}

