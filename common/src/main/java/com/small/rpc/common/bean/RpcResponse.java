package com.small.rpc.common.bean;

import lombok.Getter;
import lombok.Setter;

/**
 * Wrapper RPC Response
 *
 * @author daidai21
 */
@Getter
@Setter
public class RpcResponse {

    private String requestId;

    private Exception exception;

    private Object result;

    public boolean hasException() {
        return this.exception != null;
    }
}
