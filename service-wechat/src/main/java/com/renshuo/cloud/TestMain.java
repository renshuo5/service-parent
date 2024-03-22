package com.renshuo.cloud;

import com.renshuo.cloud.bean.Trader;
import com.renshuo.cloud.bean.Transaction;
import com.renshuo.cloud.wechat.service.HelloWorldServiceImpl;
import com.renshuo.cloud.wechat.service.IHelloWorldService;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;

import java.util.*;
import java.util.stream.Stream;

/**
 * Created by Lenovo on 2021/2/20.
 */
public class TestMain {


    public static void main(String [] args) {
        System.out.println("web Service start");
        HelloWorldServiceImpl implementor = new HelloWorldServiceImpl();
        String address="http://localhost/helloWorld";
        JaxWsServerFactoryBean factoryBean = new JaxWsServerFactoryBean();
        factoryBean.setAddress(address); //设置暴露地址
        factoryBean.setServiceClass(IHelloWorldService.class); //接口类
        factoryBean.setServiceBean(implementor); //设置实现类
        factoryBean.create();
        System.out.println("web Service started");
    }

    /**
     *测试
     */
    public static void mainTo(String [] args) {
        Trader z = new Trader("张三", "北京");
        Trader l = new Trader("李四", "北京");
        Trader w = new Trader("王五", "上海");
        Trader ss = new Trader("硕硕", "唐山");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(z, 2011, 300),
                new Transaction(l, 2012, 1000),
                new Transaction(l, 2011, 400),
                new Transaction(w, 2012, 710),
                new Transaction(w, 2012, 700),
                new Transaction(ss, 2012, 950));

        //找出2011年发生的所有交易，并按交易额排序（从低到高）.reversed()
        transactions.stream().filter(t -> t.getYear() == 2011).sorted(Comparator.comparing(Transaction::getValue)).forEach(t-> System.out.println(t));

        //交易员都在哪些不同的城市工作过？
        transactions.stream().map(t->t.getTrader().getCity()).distinct().forEach(t-> System.out.println(t));

        //查找所有来自北京的交易员，并按姓名排序。
        transactions.stream().map(t->t.getTrader()).filter(t->t.getCity().equals("北京")).sorted(Comparator.comparing(Trader::getName).reversed()).distinct().forEach(t-> System.out.println(t));

        //返回所有交易员的姓名字符串，按字母顺序排序。
        transactions.stream().map(t->t.getTrader().getName().split("")).flatMap(Arrays::stream).distinct().forEach(t-> System.out.println(t));

        //有没有交易员是在上海工作的？
        boolean bool = transactions.stream().anyMatch(t -> t.getTrader().getCity().equals("上海"));
        System.out.println(bool);

        //打印生活在北京的交易员的所有交易额。
        transactions.stream().filter(t->t.getTrader().getCity().equals("北京")).map(t -> t.getValue()).forEach(t-> System.out.println(t));

        //所有交易中，最高的交易额是多少？
        Optional<Integer> reduce1 = transactions.stream().map(t -> t.getValue()).reduce(Integer::max);
        System.out.println(reduce1);


        //找到交易额最小的交易。
        Optional<Integer> reduce2 = transactions.stream().map(t -> t.getValue()).reduce(Integer::min);
        System.out.println(reduce2);
        int sum = transactions.stream().mapToInt(Transaction::getValue).sum();
        System.out.println(sum);
        OptionalDouble average = transactions.stream().mapToInt(t -> t.getValue()).average();
        System.out.println(average);

        Stream.iterate(0, n -> n + 2).limit(10).forEach(System.out::println);
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]}).limit(12).map(t -> t[0]).forEach(System.out::println);

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> arrList = new ArrayList(numbers);
        arrList.removeIf(n->n==1);
        arrList.stream().forEach(System.out::println);



        String str= "abscaercf1k3h6";
        char [] arr = str.toCharArray();

        Arrays.sort(arr);

        try {
            String sss = new String(arr);
            System.out.println(sss);
        }finally {

        }
    }
}