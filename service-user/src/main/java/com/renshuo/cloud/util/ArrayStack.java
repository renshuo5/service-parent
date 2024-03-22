package com.renshuo.cloud.util;

import org.apache.poi.ss.formula.functions.T;

import java.util.ArrayList;

/**
 * Created by Lenovo on 2023/11/14.
 */
public class ArrayStack<T> {

    private ArrayList<T> stack;

    public ArrayStack() {
        // 初始化列表（动态数组）
        stack = new ArrayList<>();
    }

    public int size(){
        return stack.size();
    }

    public void push(T item) {
        stack.add(item);

    }

    public T pop() {
        T remove = stack.remove(size() - 1);
        return remove;
    }

    public T peek() {
        return stack.get(size()-1);
    }

}
