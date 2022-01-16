# yosoro~

## 设计模式

设计模式，即Design Patterns，是指在软件设计中，被反复使用的一种代码设计经验。使用设计模式的目的是为了可重用代码，提高代码的可扩展性和可维护性。

设计模式这个术语是上个世纪90年代由Erich Gamma、Richard Helm、Raplh Johnson和Jonhn Vlissides四个人总结提炼出来的，并且写了一本[Design Patterns](https://en.wikipedia.org/wiki/Design_Patterns)的书。这四人也被称为四人帮（GoF）。

为什么要使用设计模式？根本原因还是**软件开发要实现可维护、可扩展，就必须尽量复用代码，并且降低代码的耦合度**。设计模式主要是基于OOP编程提炼的，它基于以下几个原则：

### 开闭原则

由Bertrand Meyer提出的开闭原则（Open Closed Principle）是指，软件应该**对扩展开放，而对修改关闭**。这里的意思是在增加新功能的时候，能不改代码就尽量不要改，如果只增加代码就完成了新功能，那是最好的。

### 里氏替换原则

里氏替换原则是Barbara Liskov提出的，这是一种面向对象的设计原则，即如果我们调用一个父类的方法可以成功，**那么替换成子类调用也应该完全可以运行**。