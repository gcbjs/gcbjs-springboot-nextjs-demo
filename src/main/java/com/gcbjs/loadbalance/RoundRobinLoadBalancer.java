package com.gcbjs.loadbalance;

import java.util.List;

/**
 * @ClassName RoundRobinLoadBalancer
 * @Description 轮询法负载均衡
 * @Author yuzhangbin
 * @Date 2024/2/18 11:31
 * @Version 1.0
 **/
public class RoundRobinLoadBalancer {

    private List<Server> servers;

    private int currentServerIndex = 0;

    public RoundRobinLoadBalancer(List<Server> servers) {
        this.servers = servers;
    }

    public Server nextServer() {
        synchronized (this) {
            int totalWeight = 0;
            for (Server server : servers) {
                totalWeight += server.getWeight();
            }
            int randomWeight = (int) (Math.random() * totalWeight);
            for (int i = 0; i < servers.size(); i++) {
                randomWeight -= servers.get(i).getWeight();
                if (randomWeight < 0) {
                    currentServerIndex = i;
                    return servers.get(i);
                }
            }
            currentServerIndex = 0;
            return servers.get(currentServerIndex);
        }
    }

    public static void main(String[] args) {
        List<Server> servers = List.of(new Server("127.0.0.1", 8080,1),
                new Server("127.0.0.2", 8080,2),
                new Server("127.0.0.3", 8080,3));
        RoundRobinLoadBalancer loadBalancer = new RoundRobinLoadBalancer(servers);
        for (int i = 0; i < 20; i++) {
            Server nextServer = loadBalancer.nextServer();
            System.out.println("next server host: " + nextServer.getHost() + ", port: " + nextServer.getPort());
        }
    }

}
