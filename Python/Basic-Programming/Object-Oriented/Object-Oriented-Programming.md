# 面向对象编程

最重要的概念就是类（Class）和实例（Instance）

继承写法；可以给实例对象赋值属性和方法，也可以给类赋值属性和方法（写在定义中比较正规，但这是专属动态语言的优势）

```python
class Animal:
    __init__(self, name):
        self.__name = name # 两个下划线前置的属性是私有属性，会自动解释为 _Animal__name，实际上还是能访问
    def run(self):
        print('%s is running' % self.__name);
class Dog(Animal):
	__init(self, name, type):
        self.__name = name
        self.__type = type

doggy = Dog("Doggy", "Husky")
def smile(self):
    print('%s is smiling' % self.__name)
doggy.smile = smile()
doggy.smile()
```

# Advanced

## 使用 \__slots__

在类定义中使用 \__slots__ 可以限制该 class 实例能添加的属性，但其子类不受限制

```python
class Student(object):
    __slots__ = ('name', 'age') # 用 tuple 定义允许绑定的属性名称
```

## 使用@property

```python
class Student(object):

    @property
    def score(self):
        return self._score # 注意实例变量名不要和方法名一致

    @score.setter
    def score(self, value):
        if not isinstance(value, int):
            raise ValueError('score must be an integer!')
        if value < 0 or value > 100:
            raise ValueError('score must between 0 ~ 100!')
        self._score = value
```

加上 `@property` 把一个 getter 方法变成属性，同时会创建一个 `@score.setter` 装饰器可以把一个 setter 方法变成属性赋值

## 多重继承

一个类继承自多个类即可同时获得多个类的功能 （MixIn）—— Python 允许而 Java 不允许

## 定制类

- `print(Instance)` 调用类对象是调用该对象的 `__str__()` 方法 （相当于 Java 的 `toString()`）

- 直接显示变量调用的是 `__repr__()` 方法，返回开发者看到的字符串，也可以修改（没必要）

- 实现 `__iter__()` 方法的类可以用 `for ... in` 循环，该方法返回一个迭代对象，然后 Python 的 for 循环就会不断调用该迭代对象的 `__next__()` 方法直到 `StopIteration` 错误

  ```python
  class Fib(object):
      def __init__(self):
          self.a, self.b = 0,1 # 初始化两个计数器
      def __iter__(self):
          return self # 实例本身就是迭代对象，故返回自己
      def __next__(self):
          self.a, self.b = self.b, self.a + self.b # 计算下一个值
          if self.a > 100000: # 退出循环
              raise StopIteration()
          return self.a # 返回下一个值
  ```

- 实现 `__getitem__()` 方法按照下标取出元素，下方实现了索引和切片，但还需要处理 step 和 负数等情况

  ```python
  class Fib(object):
      def __getitem__(self, n):
          if isinstance(n, int): # n是索引
              a, b = 1, 1
              for x in range(n):
                  a, b = b, a + b
              return a
          if isinstance(n, slice): # n是切片
              start = n.start
              stop = n.stop
              if start is None:
                  start = 0
              a, b = 1, 1
              L = []
              for x in range(stop):
                  if x >= start:
                      L.append(a)
                  a, b = b, a + b
              return L
  ```

  还有 `__setitem__()` 方法和 `__delitem__()` 方法用于增删

- 当调用一个类不存在的属性或方法时，会向 `__getattr__()` 方法找，其写法如下，默认会返回 None ，像这里写了 raise 则会抛出错误

  ```python
  class Student(object):
      def __init__(self):
          self.name = 'Michael'
      def __getattr__(self, attr):
          if attr == 'score':
              return 99 # 写 s.score
          elif attr == 'age':
              return lambda: 25 # 调函数时写 s.age()
          raise AttributeError('\'Student\' object has no attribute \'%s\'' % attr)
  ```

  这个动态调用可以实现一个牛的功能：

  ```python
  class Chain(object):
      
      def __init__(self, path=''):
          self._path = path
      
      def __getattr__(self, path):
          return Chain('%s/%s' % (self._path, path))
      
      def __str__(self):
          return self._path
      
      __repr__ = __str__
      
  >>> Chain().status.user.timeline.list
  '/status/user/timeline/list'
  ```

- 在实例本身上调用方法，只需要编写 `__call__()` 方法即可

  ```python
  class Student(object):
      def __init__(self, name):
          self.name = name
  
      def __call__(self):
          print('My name is %s.' % self.name)
      
  >>> s = Student('Michael')
  >>> s() # self 参数不需要传入
  My name is Michael.
  ```

## 使用枚举类

```python
from enum import Enum， unique

Month = Enum('Month', ('Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec'))
# 枚举成员，其 value 默认从 1 开始计数，可以手动指定
for name, member in Month.__members__.items():
    print(name, '=>', member, ',', member.value)
@unique
class Weekday(Enum):
    Sun = 0 # Sun的value被设定为0
    Mon = 1
    Tue = 2
    Wed = 3
    Thu = 4
    Fri = 5
    Sat = 6
# unique 装饰器可以检查保证没有重复值
```

得到 `Month` 类型的枚举类，可以用 `Month.Jan` 来引用一个常量

## 使用元类

`type()` 函数可以查看一个类型或变量的类型

```python
>>> from hello import Hello
>>> h = Hello()
>>> h.hello()
Hello, world.
>>> print(type(Hello))
<class 'type'>
>>> print(type(h))
<class 'hello.Hello'>
```

也可以通过 `type()` 函数创建一个类，结果和一般方法一样（都是调用这个 `type()` ）

```python
>>> def fn(self, name='world'): # 先定义函数
...     print('Hello, %s.' % name)
...
>>> Hello = type('Hello', (object,), dict(hello=fn)) # 创建Hello class
>>> h = Hello()
>>> h.hello()
Hello, world.
>>> print(type(Hello))
<class 'type'>
>>> print(type(h))
<class '__main__.Hello'>
```

要创建一个class对象，`type()`函数依次传入3个参数：

1. class的名称；
2. 继承的父类集合，注意Python支持多重继承，如果只有一个父类，别忘了tuple的单元素写法；
3. class的方法名称与函数绑定，这里我们把函数`fn`绑定到方法名`hello`上。

