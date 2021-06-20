package com.small.rpc.registry;

/**
 * ZooKeeper 常量
 *
 * @author daidai21
 */
public interface ZooKeeperConstant {

    int ZK_SESSION_TIMEOUT = 5000;
    int ZK_CONNECTION_TIMEOUT = 1000;

    String ZK_REGISTRY_PATH = "/registry";
}
