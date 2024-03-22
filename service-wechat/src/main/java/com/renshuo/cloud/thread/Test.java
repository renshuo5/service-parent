package com.renshuo.cloud.thread;

/**
 * Created by Lenovo on 2021/3/29.
 */
public class Test {

    public static int aMethod(int i) throws Exception {

        Boolean flag = false;
        System.out.println(17 ^ 5);
        if (flag = true) {

        } else {
        }


        try {
            return i / 10;
        } catch (Exception ex) {
            throw new Exception("exception in a Method");
        } finally {
            System.out.printf("finally");
        }
    }

    private int count;

    Test() {
    }

    Test(int a) {
        count = a;
    }

    String str = new String("good");

    public void change(String str) {
        str = "123";

    }
    static int arr[] = new int[5];

    void fermin(int i){
        i++;
    }

    public static void main(String[] args) {


        Test inc = new Test();
        int i = 0;
        inc.fermin(i);
        i= i ++;
        System.out.println("yige"+i);

        System.out.println(arr[0]);
        System.out.println(17 ^ 5);

        Test test = new Test(88);

        test.change(test.str);
        System.out.println(test.str);

        System.out.println(test.count);
//        try {
//            aMethod(0);
//        } catch (Exception ex) {
//            System.out.printf("exception in main");
//        }
//        System.out.printf("finished");

        func(1);
        func(2);
        System.out.println(sRet);

        test();
    }


    public static String sRet = "";

    public static void func(int i) {
        try {
            if (i % 2 == 0) {
                throw new Exception();
            }
        } catch (Exception e) {
            sRet += "0";
            return;
        } finally {
            sRet += "1";
        }
        sRet += "2";
    }


    public static void add(Byte b) {
        b = b++;
    }

    public static void test() {
        Byte a = 127;
        Byte b = 127;
        add(++a);
        System.out.print(a + " ");
        add(b);
        System.out.print(b + "");
    }


}
