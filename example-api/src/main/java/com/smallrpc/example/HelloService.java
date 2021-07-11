package com.small.rpc;

public interface HelloService {

    String hello(String name);

    String hello(Person person);
}