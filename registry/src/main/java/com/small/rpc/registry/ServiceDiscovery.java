package com.small.rpc.registry;

/**
 * 服务发现
 *
 * @author daidai21
 */
public interface ServiceDiscovery {

    /**
     * 根据服务名称查找服务地址
     *
     * @param serviceName
     * @return
     */
    String discover(String serviceName);
}
