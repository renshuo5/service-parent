package com.renshuo.cloud.thread;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lenovo on 2021/3/30.
 */
public class NameList {


    private List names = new ArrayList();
    public synchronized void add(String name)
    {
        names.add(name);
    }
    public synchronized void printAll()     {
        for (int i = 0; i < names.size(); i++)
        {
            System.out.print(names.get(i) + "");
        }
    }

    public static void main(String[]args)
    {
        final NameList sl = new NameList();
        for (int i = 0; i < 2; i++)
        {

            final int num =i;

            new Thread()
            {
                public void run()
                {
                    System.out.println(num);
                    sl.add("A");
                    sl.add("B");
                    sl.add("C");
                    sl.printAll();
                }
            } .start();
        }
    }
}
