# 共享核心

此部分代码抽象了可被不同限界上下文使用的公共代码。

## JDK编译器API

代码中引用了`com.sun.tools.javac`包中的部分代码，要使其正常运行 需添加JVM启动参数：
![img.png](img.png)