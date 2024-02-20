package com.gcbjs.loadbalance;

import lombok.Data;

/**
 * @ClassName ServerV2
 * @Description TODO
 * @Author yuzhangbin
 * @Date 2024/2/18 13:53
 * @Version 1.0
 **/
@Data
public class ServerV2 {

    private String host;
    private int port;

    public ServerV2(String host, int port) {
        this.host = host;
        this.port = port;
    }
}
