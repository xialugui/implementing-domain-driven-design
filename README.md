# 领域驱动设计实践

此项目理论基础源于`Eric Evans`的《[领域驱动设计][ddd]》，`Vaughn Vernon`的《[实现领域驱动设计][ddd-implementation]》 （IMPLEMENTING DOMAIN-DRIVEN
DESIGN)
，[彭晨阳][peng-chen-yang]的《[复杂软件设计之道：领域驱动设计全面解析与实战][peng-chen-yang-book]》。10几年前`Eric Evans`给我们带来了领域驱动设计，其理论抽象
地描述了复杂软件解决之道，随后`Vaughn Vernon`将理论同实际相结合，让我们看到了领域驱动设计的落地方案。如今彭晨阳前辈将其十几年领域驱动设计经验凝结于书中，全面分析了领域驱动
设计的发展、技术实现和实战。此刻我们站在巨人的肩膀上，前行...

## 序

> 当人们学习设计技术时，各种可能性令他们兴奋不已，然而真实项目的错综复杂又会为他们泼一盆冷水
> --
> 引自[领域驱动设计][ddd]-前言

真实项目错综复杂，如何将领域驱动应用于实际？本项目或许能窥知一二。

## 概念完成度

1. 领域、子域和限界上下文✔
2. 上下文映射图✔
3. 架构✔
4. 实体✔
5. 值对象✔
6. 领域服务✔
7. 领域事件✔
8. 模块✔
9. 聚合✔
10. 工厂✔
11. 资源库✔
12. 集成限界上下文✔
13. 应用程序✔
14. 聚合与事件溯源（该部分会在Axon（DDD）框架分支详解 ）✔

## 前置基础

### 测试驱动开发

测试驱动开发是一种开发方式，要求先编写测试，然后实现。而不是先有实现，再有测试。真实环境下， 有时候先写测试是非常困难的，甚至是不可能的。我们可以选择前期先实现，中后期逐渐转换为测试驱动。
理想状态，我们所有的代码都是由测试驱动的，一些很薄的主要起着协调作用的层，如应用层，这些层的功能 主要是调用领域对象实现的功能。这时候对这些层做测试驱动显然费时费力且对质量保证作用甚微。因此，我们 主要对领域模型进行测试驱动。

测试驱动开发可参考：[java测试驱动开发][java-tdd]

### 测试驱动开发框架-Spock

`Spock`是基于`Groovy`语言的`Java`测试框架，是`Junit`的超集。简化了断言，添加了 函数名的`自然语言`支持，同是也是行为驱动开发（`BDD`）的工具。

详情可参考：[Spock官网][spock]

也可参考我的翻译：[点击直达][yuque-spock]

#### 测试文件位置

![img_2.png](image/img_2.png)

## 使用方式

### 推荐工具

#### 接口测试 postman

[软件下载][postman]
[postman接口测试示例][postman-test]

![img.png](image/img_21.png)

![img.png](image/img_16.png)使用客户端模式测试即可，接口测试说明：

1. 获取token
   ![img_1.png](image/img_17.png)
2. 设置token
   ![img_2.png](image/img_18.png)
   ![img_3.png](image/img_19.png)
3. 获取信息
   ![img_4.png](image/img_20.png)

### 包信息

查看每个模块包的`package-info.java`
![img_9.png](image/img_9.png)

每个包都对应DDD的概念，如

![img_10.png](image/img_10.png)

`valueobject`对应的是值对象，其`package-info`便是值对象的概念详解：
![img_11.png](image/img_11.png)
![img_12.png](image/img_12.png)

对概念的实例应用会被蓝字标注：
![img_13.png](image/img_13.png)
可跳转直达：
![img_15.png](image/img_15.png)
点击 ⏬
![img_14.png](image/img_14.png)
。

此举意在将理论落实到实际，讨论清楚为什么会采用代码中的方法，对实际编码的 好处，及其其它可行的方法，而不是为DDD 而DDD。

# 参考资料

1. [领域驱动设计][java-tdd]
1. [实现领域驱动设计][ddd-implementation]
1. [复杂软件设计之道：领域驱动设计全面解析与实战][peng-chen-yang-book]
1. [Java测试驱动开发][java-tdd]
1. [Java Testing with Spock][yuque-spock]
1. [Domain Driven Design with Springboot Enterprise App][ddd-springboot]
1. [Practical Domain-Driven Design in Enterprise Java - Using Jakarta EE, Eclipse MicroProfile, Spring Boot, and the Axon Framework][p-ddd]

[p-ddd]: https://www.amazon.com/Practical-Domain-Driven-Design-Enterprise-Java-ebook/dp/B07XHFZFQ7

[ddd-springboot]: https://www.amazon.com/Domain-Driven-Design-Spring-Boot/dp/1730819389

[peng-chen-yang]: https://www.jdon.com

[peng-chen-yang-book]: https://item.jd.com/12958558.html

[Spock]: https://spockframework.org/

[yuque-spock]: https://www.yuque.com/lugew/spock

[ddd-implementation]: https://kdocs.cn/l/sbM4tTbSgK6m

[ddd]: https://kdocs.cn/l/snqXPOgp8Uh5

[postman]: https://www.postman.com/

[java-tdd]: https://kdocs.cn/l/sv01QHmdhI8S

[postman-test]: https://www.postman.com/grey-crescent-914478/workspace/implimenting-domain-driven-desi~b2c62ddc-0a3c-4cf7-b850-15476a40b9ad/collection/13897285-338c4c7c-c3d5-4c2e-9503-4ebeaa74fe03