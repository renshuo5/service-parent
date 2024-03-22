package com.renshuo.cloud.load;

import sun.misc.Launcher;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Lenovo on 2021/3/27.
 */
public class Test {

    public static void main(String [] args ){

//        test2();

        isPalindrome(121);
    }


    private static void test2() {


        //1.
        //,-3,4,-1,2,1,-5,4
//        int [] nums ={-2,1,-3,4,-1,2,1,-5,4};


//        int res = nums[0];
//        int sum = 0;
//        for (int num : nums) {
//            if (sum > 0)
//                sum += num;
//            else
//                sum = num;
//            res = Math.max(res, sum);
//        }


        //2.

//        int [] digits={1,9};
//
//
//        for (int i = digits.length-1; i >=0 ; i--) {
//
//            if(digits[i]!=9){
//                digits[i]++;
//                return digits;
//            }else{
//                digits[i]=0;
//            }
//        }
//        int [] tmp =new int [digits.length+1];
//
//        tmp[0]=1;
//
//        return tmp;


        /**
         *3 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。

            每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢

         输入：n = 2
         输出：2
         解释：有两种方法可以爬到楼顶。
         1. 1 阶 + 1 阶
         2. 2 阶
         输入：n = 3
         输出：3
         解释：有三种方法可以爬到楼顶。
         1. 1 阶 + 1 阶 + 1 阶
         2. 1 阶 + 2 阶
         3. 2 阶 + 1 阶

         n=4
         输出：
         1、1+1+1+1
         2、1+2+1
         3、2+2
         4、2+1+1
         5、1+1+2
         */


        /**
         * 问题4
         * [1,2,3,0,0,0]
         3
         [2,5,6]
         3
         *
         */

//        int m=3,n=3;
//        int [] a ={1,2,3,0,0,0};
//        int [] b ={2,5,6};
//
//        for (int j = 0; j < b.length; j++) {
//            for (int i = 0; i < a.length; i++) {
//
//                if(b[j]<a[i]){
//                    a[i] = b[j];
//                }else{
//                    if(i<a.length-1) {
//                        a[i + 1] = b[j];
//                    }
//                }
//
//            }
//        }


        /**
         * 4答案
         */
//        int [] nums = {2,7,11,15};
//        int target = 9;
//
//        int [] tars = new int[2];
//        for (int i = 0; i < nums.length; i++) {
//            for (int j = i+1; j < nums.length; j++) {
//                int  tar = nums[i]+nums[j];
//
//                if(tar==target){
//
//                    tars[0]=i;
//                    tars[1]=j;
//                    System.out.println(Arrays.toString(tars));
//
//                    return ;
//                }
//            }
//        }



    }

    //5.回文数
    public static boolean isPalindrome(int x) {

        StringBuffer sb = new StringBuffer();
        sb.append(x);
        StringBuffer reverse = sb.reverse();
        try {
            Integer integer = Integer.valueOf(reverse.toString());
            if(integer==x){
                return true;
            }
        }catch (Exception e ){
            return false;
        }

        return false;
    }


    /**
     * 罗马数字转换
     * @param s
     * @return
     */
    public int romanToInt(String s) {



        return 0;

    }

    private static void test1() {
        double a= 25/2;
        System.out.println(a);

        float bb = 25;
        double c = 2;
        double d = bb/c ;
        System.out.println(d);


        int zz = 25;
        double xx = 2;
        System.out.println(zz/xx);

        System.out.println(String.class.getClassLoader());
        System.out.println(String.class.getClassLoader());
        System.out.println(com.sun.crypto.provider.DESKeyFactory.class.getClassLoader().getClass().getName());

        ClassLoader app =ClassLoader.getSystemClassLoader();
        ClassLoader ext = app.getParent();
        ClassLoader bootstrap = ext.getParent();

        System.out.println(app);
        System.out.println(ext);
        System.out.println(bootstrap);

        System.out.println("bootstrapLoader");

        URL[] urls = Launcher.getBootstrapClassPath().getURLs();

        for (int i = 0; i < urls.length; i++) {

            System.out.println(urls[i]);

        }

        System.out.println("extClassLoader加载一下文件");
        System.out.println(System.getProperty("java.class.path"));

        String s ="1231231asdf";

        StringBuffer b = new StringBuffer(s);
        String reverse = b.reverse().toString();
        System.out.println(reverse);
    }
}
