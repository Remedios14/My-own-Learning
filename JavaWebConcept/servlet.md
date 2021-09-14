# Servlet

## 什么是 Servlet

1. Servlet 是 JavaEE 规范之一。规范就是接口
2. Servlet 是 JavaWeb 三大组件之一。三大组件分别是：Servlet 程序、Filter 过滤器、Listener 监听器
3. Servlet 是运行在服务器上的一个 java 程序，它可以**接受客户端发送过来的请求，并响应数据给客户端**

## 实现流程

1. 编写一个类去实现 Servlet 接口
2. 实现 service 方法，处理请求，并响应数据
3. 到 web.xml 中去配置 servlet 程序的访问地址

## 生命周期

1. 执行 Servlet 构造方法
2. 执行 init 初始化方法
第一、二步是在第一次访问的时候创建 servlet 程序会被调用
3. 执行 service 方法
第三步是在每次访问都会调用
4. 执行 destory 销毁方法
第四步是在 web 工程停止的时候会调用