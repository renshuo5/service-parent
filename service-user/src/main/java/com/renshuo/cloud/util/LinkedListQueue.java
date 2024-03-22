package com.renshuo.cloud.util;

import lombok.Data;

/**
 * Created by Lenovo on 2023/11/15.
 */
public class LinkedListQueue {
    private ListNodeQueue front, rear; // 头节点 front ，尾节点 rear
    private int queSize = 0;

    public LinkedListQueue() {
        front = null;
        rear = null;
    }

    /* 获取队列的长度 */
    public int size() {
        return queSize;
    }

    /* 判断队列是否为空 */
    public boolean isEmpty() {
        return size() == 0;
    }

    /* 入队 */
    public void push(int num) {
        // 尾节点后添加 num
        ListNodeQueue node = new ListNodeQueue(num);
        // 如果队列为空，则令头、尾节点都指向该节点
        if (front == null) {
            front = node;
            rear = node;
            // 如果队列不为空，则将该节点添加到尾节点后
        } else {
            rear.next = node;
            rear = node;
        }
        queSize++;
    }

    /* 出队 */
    public int pop() {
        int num = peek();
        // 删除头节点
        front = front.next;
        queSize--;
        return num;
    }

    /* 访问队首元素 */
    public int peek() {
        if (isEmpty())
            throw new IndexOutOfBoundsException();
        return front.val;
    }
}

@Data
class ListNodeQueue {

    public ListNodeQueue next;
    public int val;// 节点值

    ListNodeQueue(int val) {
        this.val = val;
    }  // 构造函数


}
