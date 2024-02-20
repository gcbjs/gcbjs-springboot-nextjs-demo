package com.gcbjs.loadbalance;

import lombok.Data;

/**
 * @ClassName Server
 * @Description 服务器实体
 * @Author yuzhangbin
 * @Date 2024/2/18 11:31
 * @Version 1.0
 **/
@Data
public class Server {

    private String host;
    private int port;
    private int weight;

    public Server(String host, int port, int weight) {
        this.host = host;
        this.port = port;
        this.weight = weight;
    }
}
