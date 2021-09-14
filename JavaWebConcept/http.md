在客户端(浏览器)方面
http://localhost:8080/book/index.html
其中
http: 表示协议
localhost 表示 ip 地址，可以替换成网络 ip 或本机 ip
:8080 是端口号
/book 是工程目录，可以继续深入
/index.html 是文件名

向服务器发送请求

服务器(Tomcat)
/book 工程
    a.html
    index.html
服务器接受到请求之后，读取要访问的资源文件，然后回传给客户端需要的页面内容

-------------------------

没有工程名的时候默认访问 ROOT 工程
有工程名没有资源名则默认访问 index.html 页面
（以上内容都是通过 config 设置的）