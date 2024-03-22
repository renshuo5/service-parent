package com.renshuo.cloud.util;

import lombok.Data;

/**
 * Created by Lenovo on 2023/11/14.
 */
public class LinkedListStack<T> {

    private ListNode<T> stack;

    private int stackSize = 0;

    public int size(){
        return stackSize;
    }

    public void push(T item) {
        ListNode node =new ListNode<T>(item);
        node.setNext(stack);
        stack = node;
        stackSize++;
    }

    public T peek(){
        T item = stack.getVal();
        return item;
    }

    public T pop(){
        T item = stack.getVal();

        stack = stack.getNext();
        stackSize--;
        return item;
    }

}

@Data
class ListNode<T> {

    private ListNode next;
    private T val;// 节点值

    ListNode(T val) {
        this.val = val;
    }  // 构造函数


}
