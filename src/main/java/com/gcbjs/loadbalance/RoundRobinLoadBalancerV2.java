package com.gcbjs.loadbalance;

import java.util.Arrays;
import java.util.List;

/**
 * @ClassName RoundRobinLoadBalancerV2
 * @Description 一致性hash算法
 * @Author yuzhangbin
 * @Date 2024/2/18 13:44
 * @Version 1.0
 **/
public class RoundRobinLoadBalancerV2 {

    private List<ServerV2> servers;
    private ConsistentHash<ServerV2> consistentHash;

    public RoundRobinLoadBalancerV2(List<ServerV2> servers) {
        this.servers = servers;
        this.consistentHash = new ConsistentHash<>(servers, new MurmurHash3());
    }

    public ServerV2 nextServer(String key) {
        return consistentHash.get(key);
    }

    public static void main(String[] args) {
        List<ServerV2> servers = Arrays.asList(
                new ServerV2("192.168.1.100", 8080),
                new ServerV2("192.168.1.101", 8080),
                new ServerV2("192.168.1.102", 8080)
        );

        RoundRobinLoadBalancerV2 loadBalancer = new RoundRobinLoadBalancerV2(servers);

        for (int i = 0; i < 10; i++) {
            String key = "key" + i;
            ServerV2 server = loadBalancer.nextServer(key);
            System.out.println("Select server: " + server.getHost() + ":" + server.getPort());
        }
    }
}
