package com.renshuo.cloud.util;

/**
 * Created by Lenovo on 2023/11/16.
 */
public class LinkedListDeque {

    // 头节点 front ，尾节点 foot
    DListNode front, foot;
    private int queSize = 0; // 双向队列的长度

    public LinkedListDeque() {
        front = foot = null;
    }

    /* 获取双向队列的长度 */
    public int size() {
        return queSize;
    }

    /* 判断双向队列是否为空 */
    public boolean isEmpty() {
        return size() == 0;
    }

    //头部添加传入true，尾部添加传入false
    public void push(int num, boolean isFront) {
        DListNode node = new DListNode(num);

        if (isEmpty()) {
            front = foot = node;
        }else if (isFront) {
            DListNode frontTemp =new DListNode(front.val);
            frontTemp.prev = front.prev;
            frontTemp.next = front.next;
            front.prev = node;
            node.next = frontTemp;
            front = node;
        } else {
            foot.next = node;
            node.prev = foot;
            foot = node;
        }

        queSize++;

    }

    /* 队首入队 */
    public void pushFirst(int num) {
        push(num, true);
    }

    /* 队尾入队 */
    public void pushLast(int num) {
        push(num, false);
    }


    /* 出队操作 */
    private int pop(boolean isFront) {
        if (isEmpty())
            throw new IndexOutOfBoundsException();
        int val;
        // 队首出队操作
        if (isFront) {
            val = front.val; // 暂存头节点值
            // 删除头节点
            DListNode fNext = front.next;
            if (fNext != null) {
                fNext.prev = null;
                front.next = null;
            }
            front = fNext; // 更新头节点
            // 队尾出队操作
        } else {
            val = foot.val; // 暂存尾节点值
            // 删除尾节点
            DListNode rPrev = foot.prev;
            if (rPrev != null) {
                rPrev.next = null;
                foot.prev = null;
            }
            foot = rPrev; // 更新尾节点
        }
        queSize--; // 更新队列长度
        return val;
    }

    /* 队首出队 */
    public int popFirst() {
        return pop(true);
    }

    /* 队尾出队 */
    public int popLast() {
        return pop(false);
    }

    /* 访问队首元素 */
    public int peekFirst() {
        if (isEmpty())
            throw new IndexOutOfBoundsException();
        return front.val;
    }

    /* 访问队尾元素 */
    public int peekLast() {
        if (isEmpty())
            throw new IndexOutOfBoundsException();
        return foot.val;
    }


}
