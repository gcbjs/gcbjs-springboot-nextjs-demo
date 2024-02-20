package com.gcbjs.loadbalance;

import java.util.List;

/**
 * @ClassName ConsistentHash
 * @Description TODO
 * @Author yuzhangbin
 * @Date 2024/2/18 13:45
 * @Version 1.0
 **/
public class ConsistentHash<T> {

    private List<T> nodes;

    private HashFunction hashFunction;

    public ConsistentHash(List<T> nodes, HashFunction hashFunction) {
        this.nodes = nodes;
        this.hashFunction = hashFunction;
    }

    public T get(String key) {
        int hashValue = hashFunction.hash(key);
        int nodeIndex = findNodeIndex(hashValue);
        return nodes.get(nodeIndex);
    }

    private int findNodeIndex(int hashValue) {
        int low =0;
        int high = nodes.size()-1;
        while (low <= high) {
             int mid = (low + high) / 2;
             int midValue = hashFunction.hash(nodes.get(mid).toString());
             if (midValue < hashValue) {
                 high = mid - 1;
             }else {
                 low = mid + 1;
             }
        }
        return low % nodes.size();
    }
}
