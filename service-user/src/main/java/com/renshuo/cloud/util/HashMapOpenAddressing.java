package com.renshuo.cloud.util;

/**
 * 开放寻址哈希表
 */
public class HashMapOpenAddressing {

    private int size; // 键值对数量
    private int capacity = 4; // 哈希表容量
    private final double loadThres = 2.0 / 3.0; // 触发扩容的负载因子阈值
    private final int extendRatio = 2; // 扩容倍数
    private Pair[] buckets; // 桶数组
    private final Pair TOMBSTONE = new Pair(-1, "-1"); // 删除标记

    /* 构造方法 */
    public HashMapOpenAddressing() {
        size = 0;
        buckets = new Pair[capacity];
    }

    /* 哈希函数 */
    private int hashFunc(int key) {
        return key % capacity;
    }

    private double loadFactor(){
        return (double)size/capacity;
    }

    /* 搜索 key 对应的桶索引 */
    private int findBucket(int key) {

        int firstTombstone = -1;

        int index = hashFunc(key);
        while(buckets[index]!=null){
            if(buckets[index].key==key){

                //说明之前查到了空桶
                if(firstTombstone!=-1){
                    buckets[firstTombstone] = buckets[index];
                    buckets[index] = TOMBSTONE;
                    return firstTombstone; // 返回移动后的桶索引
                }
                return index;
            }
            //记录遇到的首个删除标记
            if(firstTombstone==-1 && buckets[index]==TOMBSTONE){
                firstTombstone= index;
            }
            // 计算桶索引，越过尾部返回头部
            index = (index + 1) % capacity;
        }
        return firstTombstone == -1 ? index : firstTombstone;

    }

    /* 查询操作 */
    public String get(int key) {
        // 搜索 key 对应的桶索引
        int index = findBucket(key);
        // 若找到键值对，则返回对应 val
        if (buckets[index] != null && buckets[index] != TOMBSTONE) {
            return buckets[index].val;
        }
        // 若键值对不存在，则返回 null
        return null;
    }

    /* 添加操作 */
    public void put(int key, String val) {

        if(loadFactor()>loadThres){
            extend();
        }
        int index = findBucket(key);

        // 若找到键值对，则覆盖 val 并返回
        if(buckets[index]!=null && buckets[index]!=TOMBSTONE){
            buckets[index].val=val;
            return;
        }
        buckets[index] = new Pair(key,val);
        size++;

    }

    /* 删除操作 */
    public void remove(int key){
        int index = findBucket(key);
        //若找到键值对，则用删除标记覆盖它
        if(buckets[index]!=null&&buckets[index]!=TOMBSTONE){
            buckets[index] = TOMBSTONE;
        }
        size--;

    }

    private void extend() {

        Pair[] temp = buckets;
        // 初始化扩容后的新哈希表
        capacity *= extendRatio;
        buckets = new Pair[capacity];
        size = 0;

        for (Pair pair : temp) {
            if(pair!=null && pair!=TOMBSTONE){
                put(pair.key,pair.val);
            }
        }
    }


    /* 打印哈希表 */
    public void print() {
        for (Pair pair : buckets) {
            if (pair == null) {
                System.out.println("null");
            } else if (pair == TOMBSTONE) {
                System.out.println("TOMBSTONE");
            } else {
                System.out.println(pair.key + " -> " + pair.val);
            }
        }
    }


}
