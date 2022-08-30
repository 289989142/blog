---
title: "凤凰架构-架构师的视角-透明多级分流系统"
date: 2022-08-29T14:38:38+08:00
lastmod: 2018-03-03T16:01:23+08:00
draft: false
tags: ["notes"]
categories: ["Notes"]
authors:
- Lhy

---


客户端缓存
1.强制缓存
1.1
HTTP/1.1 200 OK
Expires: Wed, 8 Apr 2020 07:28:00 GMT
返回头带上一个过期时间，保证过期之前查询结果不会改变，浏览器可以直接返回缓存。

1.2
HTTP/1.1 200 OK
Cache-Control: max-age=600
max-age
no-cache
等等 Cache-Control提供了多种参数


2.协商缓存
2.1
HTTP/1.1 304 Not Modified
Cache-Control: public, max-age=600
Last-Modified: Wed, 8 Apr 2020 15:31:30 GMT

Last-Modified 和 If-Modified-Since：Last-Modified 是服务器的响应 Header，用于告诉客户端这个资源的最后修改时间。对于带有这个 Header 的资源，当客户端需要再次请求时，会通过 If-Modified-Since 把之前收到的资源最后修改时间发送回服务端。

如果此时服务端发现资源在该时间后没有被修改过，就只要返回一个 304/Not Modified 的响应即可，无须附带消息体，达到节省流量的目的.

2.2
或者使用Etag 返回和请求时都带着资源的Etag服务器发现Etag没有变化的话也会返回304

Etag一致性好，Last-Modified性能好



## 域名解析


本地 DNS 收到查询请求后，会按照“是否有

```
www.icyfenix.com.cn
```

的权威服务器”→“是否有

```
icyfenix.com.cn
```

的权威服务器”→“是否有

```
com.cn
```

的权威服务器”→“是否有

```
cn
```

的权威服务器”的顺序，依次查询自己的地址记录，如果都没有查询到，就会一直找到最后点号代表的根域名服务器为止。这个步骤里涉及了两个重要名词：

- **权威域名服务器**（Authoritative DNS）：是指负责翻译特定域名的 DNS 服务器，“权威”意味着这个域名应该翻译出怎样的结果是由它来决定的。DNS 翻译域名时无需像查电话本一样刻板地一对一翻译，根据来访机器、网络链路、服务内容等各种信息，可以玩出很多花样，权威 DNS 的灵活应用，在后面的内容分发网络、服务发现等章节都还会有所涉及。
- **根域名服务器**（Root DNS）是指固定的、无需查询的[顶级域名](https://en.wikipedia.org/wiki/Top-level_domain)（Top-Level Domain）服务器，可以默认为它们已内置在操作系统代码之中。全世界一共有 13 组根域名服务器（注意并不是 13 台，每一组根域名都通过[任播](https://en.wikipedia.org/wiki/Anycast)的方式建立了一大群镜像，根据维基百科的数据，迄今已经超过 1000 台根域名服务器的镜像了）。13 这个数字是由于 DNS 主要采用 UDP 传输协议（在需要稳定性保证的时候也可以采用 TCP）来进行数据交换，未分片的 UDP 数据包在 IPv4 下最大有效值为 512 字节，最多可以存放 13 组地址记录，由此而来的限制。
