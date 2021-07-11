# small-rpc

### Introduction

Dependence: SpringBoot, Netty, ZooKeeper, protostuff; (lombok, slf4j, objenesis, ...)

Support:

* [-] Simple code and framework
* [ ] Service registry/discovery support by ZooKeeper
* [ ] High availability, load balance and failover
* [ ] Support different load balance strategy
* [ ] Support asynchronous/synchronous call
* [ ] Support different versions of service
* [ ] Support different serializer/deserializer
* [ ] Dead TCP connection detecting with heartbeat

### Catalog

* `client`
* `server`
* `common`
    * annotation
    * bean
    * codec
    * util
* `registry`

### Example

Run example:

1. start registry
   1. `docker pull zookeeper`
   2. `docker run --name rpc-zookeeper --restart always -d zookeeper`
   3. `docker ps | grep rpc-zookeeper` check ZK already on running.
2. start server
   1. ...
3. start client
   1. ...

rpc.service_address：发布 RPC 服务的地址    rpc.service_address=127.0.0.1:8000
rpc.registry_address：ZooKeeper 服务器的地址    127.0.0.1:2181

TODO: 端口

### Reference

* https://github.com/luxiaoxun/NettyRpc
* https://gitee.com/huangyong/rpc
