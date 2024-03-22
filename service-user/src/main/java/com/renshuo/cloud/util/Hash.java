package com.renshuo.cloud.util;

/**
 * Created by Lenovo on 2023/11/20.
 */
public class Hash {

    final int modulus = 1000000007;
    public int add(String key) {
        int hash = 0;
        for (char c : key.toCharArray()) {
            hash = (hash + (int) c) % modulus;
        }

        return hash;
    }

    public int mulHash(String key) {
        int hash = 0;
        for (char c : key.toCharArray()) {
            hash= (31*hash+(int)c)%modulus;
        }
        return hash;
    }

    /* 异或哈希 */
    public int xorHash(String key) {
        int hash=0;
        for (char c : key.toCharArray()) {
            hash ^= (int)c;
        }
        return hash;
    }

    /* 旋转哈希 */
    int rotHash(String key) {
        int hash = 0;

        for (char c : key.toCharArray()) {
            hash =((hash<<4)^ (hash>>28) ^ (int)c)%modulus;
        }
        return hash;
    }
}
