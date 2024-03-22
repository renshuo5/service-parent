package com.renshuo.cloud.util;

/**
 * Created by Lenovo on 2023/11/16.
 */
public class DListNode {

    public int val;

    //下一个节点信息
    public DListNode next;

    //上一个节点信息
    public DListNode prev;

    public DListNode(int val){
        this.val = val;
        prev = next = null;
    }


}
