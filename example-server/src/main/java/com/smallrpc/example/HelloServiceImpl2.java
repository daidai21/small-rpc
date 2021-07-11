package com.small.rpc;

import com.small.rpc.common.annotation.RpcService;

@RpcService(value = HelloService.class, version = "v2")
public class HelloServiceImpl2 implements HelloService {

    @Override
    public String hello(String name) {
        return "你好! " + name;
    }

    @Override
    public String hello(Person person) {
        return "你好! " + person.getFirstName() + " " + person.getLastName();
    }
}
