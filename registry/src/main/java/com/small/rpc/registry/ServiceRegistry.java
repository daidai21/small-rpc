package com.small.rpc.registry;

/**
 * 服务注册
 *
 * @author daidai21
 */
public interface ServiceRegistry {
    /**
     * 注册服务名称与服务地址
     *
     * @param serviceName
     * @param serviceAddress
     */
    void register(String serviceName, String serviceAddress);
}
