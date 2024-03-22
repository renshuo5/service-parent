package com.renshuo.cloud.demo.zhipin;

import org.apache.commons.collections.map.HashedMap;
import org.apache.tomcat.util.descriptor.InputSourceUtil;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

/**
 * Created by Lenovo on 2021/4/1.
 */
public class Solution {


    public static List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> list = new ArrayList<>();

        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {

            char[] chars = s.toCharArray();

            Arrays.sort(chars);

            String ss = new String(chars);

            if (map == null || map.size() == 0) {
                List<String> inlist = new ArrayList<>();
                inlist.add(s);
                map.put(ss, inlist);
            } else {
                if (map.containsKey(ss)) {
                    List<String> ins = map.get(ss);
                    ins.add(s);
                } else {
                    List<String> inlist = new ArrayList<>();
                    inlist.add(s);
                    map.put(ss, inlist);
                }
            }
        }
        Set<Map.Entry<String, List<String>>> entries = map.entrySet();
        for (Map.Entry<String, List<String>> entry : entries) {
            list.add(entry.getValue());
        }
        return list;

    }

    public static int[] swapNumbers(int[] numbers) {
//        int total =0;
//        for (int number : numbers) {
//            total+=number;
//        }
//
//        for (int i=0;i<numbers.length;i++) {
//            numbers[i]=total-numbers[i];
//        }

        numbers[0] ^= numbers[1];
        numbers[1] ^= numbers[0];
        numbers[0] ^= numbers[1];

        return numbers;
    }

    String[] book;

    Map<String, Integer> map = new HashMap();

    public Solution(){

    }
    public Solution(String[] book) {
        this.book = book;

        for (String s : book) {
            if (map.containsKey(s)) {
                map.put(s, map.get(s) + 1);
            } else {
                map.put(s, 1);
            }
        }
    }

    public int get(String word) {
        Integer integer = map.get(word);
        return integer == null ? 0 : integer.intValue();
    }


    public static int smallestDifference(int[] a, int[] b) {

//        List<Integer> list = new ArrayList<>();
        int resInt = Integer.MAX_VALUE;
        for (int i : a) {
            for (int j : b) {

                int res = Math.abs(i - j);
                if (0 <= res && res <= 2147483647) {
                    resInt = Math.min(resInt, res);
                }
            }
        }

//        while (i < a.length && j < b.length) {
//            res = Math.min(res, Math.abs((long)a[i] - (long)b[j]));
//            if (a[i] < b[j]) {
//                i ++;
//            } else {
//                j ++;
//            }
//        }
//        System.out.println(list);
//        return list.stream().mapToInt(str->str).min().getAsInt();
        return resInt;
    }

    public static String numberToWords(int num) {
        int length = String.valueOf(num).length();

        String res = "";
        switch (length) {
            case 1:
                res="";
                break;
            case 2:
                res="";
                break;
            case 3:
                res="Hundred";
                break;
            case 4:
                res="Thousand ";
                break;
            case 5:
                res="";
                break;
            case 6:
                res="";
                break;
            case 7:
                res="";
                break;
            case 8:
                res="";
                break;
            case 9:
                res="Hundred";
                break;

            case 10:
                res = "Billion";
                break;

        }


        return "";
    }


    public  static int minus(int a, int b) {

        int c = b>0?-1:1;
        if(b>0) {
            for (int i = a; i <= b; i++) {
                a += c;
            }
        }else{
            for(int i=b;i<=a;i++){
                a+=c;
            }
        }



        return Integer.valueOf((BigInteger.valueOf(a).subtract(BigInteger.valueOf(b))).toString());

    }

    public static int multiply(int a, int b) {
        return Integer.valueOf((BigInteger.valueOf(a).multiply(BigInteger.valueOf(b))).toString());

    }

    public static int divide(int a, int b) {
        if(b==0){

            return 0;
        }
        return Integer.valueOf((BigInteger.valueOf(a).divide(BigInteger.valueOf(b))).toString());
    }

    private int a=10;
    int b=20;
    static int c=1;

    public static void main(String[] args) {

        StringBuffer sb =new StringBuffer("abc");
        String a="abc";
        String s = sb.toString();
        System.out.println(a==s.intern());
        System.out.println(s==s.intern());



//        String[] str = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};

//        System.out.println(groupAnagrams(str));

//        String[] book = {"i", "have", "an", "apple", "he", "have", "a", "pen"};
//        Solution s = new Solution(book);
//        System.out.println(s.get("have"));
//
//        int[] numbers = {1, 2};
//        int[] ints = swapNumbers(numbers);
//        for (int anInt : ints) {
//            System.out.println("===" + anInt);
//        }

//        [-2147483648,1]
//[2147483647,0]
//        int [] a = {0};
//        int [] b= {2147483647};
//        System.out.println(smallestDifference(a,b));
//        System.out.println(minus(0,-2147483647));


//        [2,5,5,11]
//        10
//        int [] num = {2,5,5,11};
//        int tar = 10;
//        int [] ss = twoSum(num,tar);
//        System.out.println(ss[0]+"===="+ss[1]);


//        System.out.println(new B().getValue());

//        Solution s = new Solution();

    }

    public static int[] twoSum(int[] nums, int target) {
        int [] ss = new int[2];
        con:for (int i = 0; i < nums.length; i++) {
            for (int j = 1; j <= nums.length-1; j++) {
                if(i==j){
                    continue;
                }
                int total = nums[i] + nums[j];
                if(target==total){
                    ss[0]= i;
                    ss[1]= j;
                    break con;
                }
            }
        }

        return ss;
    }



    static class A {
        protected int value;
        public A (int v) {
            setValue(v);
        }
        public void setValue(int value) {
            this.value= value;
        }
        public int getValue() {
            try {
                value ++;
                return value;
            } finally {
                this.setValue(value);
                System.out.println(value);
            }
        }
    }
    static class B extends A {
        public B () {
            super(5);
            setValue(getValue()- 3);
        }
        @Override
        public void setValue(int value) {
            super.setValue(2 * value);
        }
    }
}

