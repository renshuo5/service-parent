package com.renshuo.cloud;

import com.renshuo.cloud.dao.OrderMapper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lenovo on 2021/3/10.
 */
public class ShardingTests extends DemoApplicationTests {

    @Autowired
    private OrderMapper orderMapper;

    @Test
    public void testInsertOrder(){
//        for(int i=1; i<51;i++){
//            orderMapper.insert("订单"+i,new BigDecimal((5*i)+""));
//        }


        Thread t = new Thread(()->

            System.out.println("123")
        );

        t.start();


        Map<String,Integer> conMap = new HashMap<>();
        if(conMap.containsKey("ss")){
            conMap.containsKey("ss");
        }
    }


}
