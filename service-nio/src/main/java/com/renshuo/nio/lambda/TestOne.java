package com.renshuo.nio.lambda;

import com.renshuo.nio.lambda.bean.Apple;
import com.renshuo.nio.lambda.bean.Employee;
import org.springframework.util.StringUtils;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.*;
import java.util.stream.Collectors;

/**
 * Created by Lenovo on 2023/8/31.
 */
public class TestOne {


    public static void main(String[] args) {
        List names = new ArrayList();
        names.add("A");
        names.add("B");
        names.add("C");


        names.forEach(name -> System.out.println(name));


        PrintStream ps = System.out;
        Consumer<String> con = (str) -> ps.println(str);
        con.accept("Hello World！");

        Consumer<String> con2 = ps::println;
        con2.accept("Hello World！");

        Consumer<String> con3 = System.out::println;
        con3.accept("你好");

        //3、
        Comparator<Integer> cons = (a, b) -> Integer.compare(a, b);
        int compare = cons.compare(2, 2);
        Comparator<Integer> consr = cons.reversed();

        Comparator<Integer> consl = Integer::compare;

        Consumer<Integer> co = System.out::println;
        co.accept(compare);

        System.out.println("-------------------");
//        Supplier<Employee> esup =  Employee::new;
//        esup.get().setAge(10);
//        Supplier<Employee> esup2 =  Employee::new;
//        esup2.get().setAge(20);
        Employee ee1 = new Employee();
        ee1.setAge(30);
        Employee ee2 = new Employee();
        ee2.setAge(20);

        Comparator<Employee> es = (e1, e2) -> {
            return e1.getAge().compareTo(e2.getAge());
        };
        int compare1 = es.compare(ee1, ee2);
        System.out.println(compare1);
        System.out.println("-------------------");

        //4.
        BiPredicate bi = (x, y) -> x.equals(y);
        System.out.println(bi.test("11", "1"));
        BiPredicate<String, String> bb = String::equals;
        System.out.println(bb.test("12", "12"));

        Function<Employee, String> fun = e -> e.show();
        System.out.println(fun.apply(new Employee()));
        Function<Employee, String> f = Employee::show;
        con3.accept(f.apply(new Employee()));


        Supplier<Employee> sup = Employee::new;
        System.out.println(sup.get().show());

        Function<Integer, String[]> ifun = String[]::new;
        String[] apply = ifun.apply(10);

        System.out.println(apply.length);
        Arrays.stream(apply).forEach(str -> System.out.println(str));

        Function<Integer, String[]> iifun = integer -> new String[integer];
        String[] apply1 = iifun.apply(5);
        System.out.println(apply1.length);

        System.out.println("-------------------");

        //5.
        List<Apple> apples = new ArrayList<>();
        apples.add(new Apple("red", 99));
        apples.add(new Apple("red", 200));
        apples.add(new Apple("green", 130));
        apples.add(new Apple("green", 80));
        apples.add(new Apple("green", 230));
        apples.add(new Apple("", 230));


        /**
         *对排序lanmbda进行复复合-比较器链
         *1、默认逆序方法：reversed()
         *2、多级比较：thenComparing()
         *example:对apples按照颜色排序后，进行逆序，如果颜色一样再按照重量递增
         */

        Comparator<Apple> com=Comparator.comparing(Apple::getColor).reversed().thenComparing(Apple::getWeight);
        apples.sort(com);
        apples.forEach(a-> System.out.println(a));

        Predicate<Apple> predicate = (a) -> a.getColor().equals("red");
        Predicate<Apple> negate = predicate.negate();


        apples.stream().filter(negate.and(a -> a.getWeight() > 100)).forEach(a -> System.out.println(a));

//        apples.stream().filter(((a) -> a.getColor().equals("red") && a.getWeight()>100)).forEach(a-> System.out.println(a));


//        List<Apple> aps = apples.stream().filter((a) -> {
//            return a.getColor().equals("red");
//        }).collect(Collectors.toList());

        /**
         *函数复合
         *1、andThen将前一lambda执行结果，作为后一表达式的参数
         *2、compose将后一表达式的结果作为前一表达式的参数
         *example；complexReult=g(f(x))例如g(f(1))step1:1+1=2step2:(1+1)*2+""
         */
        Function<Integer, Integer> fi = x -> x + 1;
        Function<Integer, String> gi = x -> x * 2 + "--";
        Function<Integer, String> fr = fi.andThen(gi);
        String apply2 = fr.apply(1);
        System.out.println(apply2);

        Function<Integer, String> fc = gi.compose(fi);
        String apply3 = fc.apply(1);
        System.out.println(apply3);

        Supplier<Apple> sa = Apple::new;
        Supplier<Apple> sa1 =()->new Apple();
        System.out.println(sa1.get());

        //6.传入方法
        foreach(apples,apple -> {if(StringUtils.isEmpty(apple.getColor())) apple.setColor("bule");});

        apples.forEach(a-> System.out.println(a));
    }

    public static <T> void foreach(List<T> list,Consumer<T> consumer){

        for (T t : list) {
            consumer.accept(t);
        }

    }
}
