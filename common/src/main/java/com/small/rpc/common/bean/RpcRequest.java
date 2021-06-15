package com.small.rpc.common.bean;

import lombok.Getter;
import lombok.Setter;

/**
 * Wrapper RPC Request
 *
 * @author daidai21
 */
@Setter
@Getter
public class RpcRequest {

    private String requestId;

    private String interfaceName;

    private String methodName;

    private Class<?>[] parameterTypes;

    private Object[] parameters;

    /**
     * FIXME: Request version id, temporarily useless
     */
    private String serviceVersion;
}
