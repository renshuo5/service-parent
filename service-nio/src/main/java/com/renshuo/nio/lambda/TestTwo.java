package com.renshuo.nio.lambda;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Stream;

/**
 * Created by Lenovo on 2023/8/31.
 */
public class TestTwo {

    public static void main(String[] args) {

        List<String> list= new ArrayList<>();

        list.stream();
        Integer[] nums = new Integer[]{1,2,2,3,3};
        Arrays.stream(nums).distinct().forEach(n-> System.out.println(n));

        System.out.println("---");
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 4, 5);
        integerStream.forEach(n -> System.out.println(n));

        System.out.println("--------");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Callable<Date> task = new Callable<Date>() {
            @Override
            public Date call() throws Exception {
                return sdf.parse("20161121");
            }
        };
        ExecutorService pool = Executors.newFixedThreadPool(10);
        List<Future<Date>> results = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            results.add(pool.submit(task));
        }

        try {
            for(Future<Date> ft:results){
                System.out.println(ft.get());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        pool.shutdown();


        Callable<Date> task1 = new Callable<Date>() {
            @Override
            public Date call() throws Exception {
                return null;
                //return DateFormatThreadLocal.parse("20151125","yyyyMMdd");
            }
        };
        ExecutorService pool1 = Executors.newFixedThreadPool(10);
        List<Future<Date>> results1 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            results1.add(pool1.submit(task1));
        }

        try {
            for(Future<Date> ft:results1){
                System.out.println(ft.get());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        pool.shutdown();
    }
}
