package com.small.rpc.client;

import com.small.rpc.common.bean.RpcRequest;
import com.small.rpc.common.bean.RpcResponse;
import com.small.rpc.common.util.StringUtil;
import com.small.rpc.registry.ServiceDiscovery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.UUID;

/**
 * RPC proxy
 * <p>
 * for create rpc proxy
 *
 * @author daidai21
 */
public class RpcProxy {

    private static final Logger LOGGER = LoggerFactory.getLogger(RpcProxy.class);

    private String serviceAddress;

    private ServiceDiscovery serviceDiscovery;

    public RpcProxy(String serviceAddress) {
        this.serviceAddress = serviceAddress;
    }

    public RpcProxy(ServiceDiscovery serviceDiscovery) {
        this.serviceDiscovery = serviceDiscovery;
    }

    @SuppressWarnings("unchecked")
    public <T> T create(final Class<?> interfaceClass) {
        return create(interfaceClass, "");
    }

    @SuppressWarnings("unchecked")
    public <T> T create(final Class<?> interfaceClass, final String serviceVersion) {
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class<?>[]{interfaceClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                RpcRequest request = new RpcRequest();
                request.setRequestId(UUID.randomUUID().toString());
                request.setInterfaceName(method.getDeclaringClass().getName());
                request.setServiceVersion(serviceVersion);
                request.setMethodName(method.getName());
                request.setParameterTypes(method.getParameterTypes());
                request.setParameters(args);
                if (serviceDiscovery != null) {
                    String serviceName = interfaceClass.getName();
                    if (StringUtil.isNotEmpty(serviceVersion)) {
                        serviceName += "-" + serviceVersion;
                    }
                    serviceAddress = serviceDiscovery.discover(serviceName);
                    LOGGER.debug("discover service: {} => {}", serviceName, serviceAddress);
                }
                if (StringUtil.isEmpty(serviceAddress)) {
                    throw new RuntimeException("server address is empty");
                }
                String[] array = StringUtil.split(serviceAddress, ":");
                String host = array[0];
                int port = Integer.parseInt(array[1]);
                RpcClient client = new RpcClient(host, port);
                long time = System.currentTimeMillis();
                RpcResponse response = client.send(request);
                LOGGER.debug("time: {}ms", System.currentTimeMillis() - time);
                if (response == null) {
                    throw new RuntimeException("response is null");
                }
                if (response.hasException()) {
                    throw response.getException();
                } else {
                    return response.getResult();
                }
            }
        });
    }
}
