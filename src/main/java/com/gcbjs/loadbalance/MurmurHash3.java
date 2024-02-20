package com.gcbjs.loadbalance;

/**
 * @ClassName MurmurHash3
 * @Description TODO
 * @Author yuzhangbin
 * @Date 2024/2/18 13:45
 * @Version 1.0
 **/
public class MurmurHash3 implements HashFunction {

    private static final int C1=0xcc9e2d51;
    private static final int C2=0x1b873593;

    @Override
    public int hash(String key) {
        int hash = 0;
        for (int i = 0; i < key.length(); i++) {
            int k = key.charAt(i);
            hash = (hash * C1) + k;
            hash = (hash << 15) | (hash >>> 17);
            hash = hash * C2;
        }
        return hash;
    }
}
