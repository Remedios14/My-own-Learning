# 函数式编程

## 高阶函数

以 Python 内置的函数 `abs()` 为例，其本身是一个变量
```Python
>>> abs
<built-in function abs>
```

可以指向其他对象，需要重启交互式进程才能恢复；实际上定义在 `impurt builtins` 模块中

接收另一个函数作为参数的函数称为**高阶函数**
```python
def add(x, y, f):
    return f(x) + f(y)
```

### Map/Reduce 模型

Python 内建了 `map()` 和 `reduce()` 函数

其中 `map()` 函数接收一个函数和一个 `Iterable` 实例，将传入的函数依次作用到每个元素并返回一个 `Iterable`

例如

```python
def f(x):
    return x * x
r = map(f, [1, 2, 3, 4, 5])
list(r)
```

`reduce()` 把一个函数 `f` 作用在一个序列 `[x1, x2, x3, ...]` 上，这个函数 `f` 必须接收两个参数，继而逐个将前两个的结果结合下一个元素进行计算

```python
# reduce(f, [x1, x2, x3]) = f(f(x1, x2), x3)
from functools import reduce
def fn(x,y):
    return x * 10 + y
reduce(fn , [1, 3, 5, 7, 9])
```

[Map-Reduce-Test.py](./Map-Reduce-Test.py)

### Filter 函数

类似 `map()` ，传入一个函数和一个序列，把传入的函数作用于每个元素，然后根据是否为 `True` 来保留或删除该元素

```Python
def not_empty(s):
    return s and s.strip()

list(filter(not_empty, ['A', '', 'B', None, 'C', '  ']))
# 结果: ['A', 'B', 'C']
```

`filter()` 返回的是一个 `Iterator` ，惰性对象

[Filter-Test.py](./Filter-Test.py)

### Sorted 函数

传入一个 `list` 和一个可选的 `key()` 函数，对 List 内元素经过 `key()` 映射后的列表递增排序返回，还可传入第三个参数 `reverse=True` 实现降序

```python
>>> sorted(['bob', 'about', 'Zoo', 'Credit'], key=str.lower)
['about', 'bob', 'Credit', 'Zoo']
```

## 返回函数

将函数作为结果返回值

```python
def lazy_sum(*args):
    def sum():
        ax = 0
        for n in args:
            ax = ax + n
        return ax
    return sum
# 函数对象
>>> f = lazy_sum(1, 3, 5, 7, 9)
>>> f
<function lazy_sum.<locals>.sum at 0x101c6ed90>
# 调用对象
>>> f()
25
```

内部函数`sum`可以引用外部函数`lazy_sum`的参数和局部变量，当`lazy_sum`返回函数`sum`时，相关参数和变量都保存在返回的函数中，这种称为“闭包（Closure）”的程序结构拥有极大的威力。

### 闭包

注意到返回的函数在其定义内部引用了局部变量`args`，所以，当一个函数返回了一个函数后，其内部的局部变量还被新函数引用

返回函数没有立刻执行，而是直到调用了 `f()` 才执行

返回闭包时牢记一点：返回函数不要引用任何循环变量，或者后续会发生变化的变量。

如果一定要引用循环变量，则应当再创建一个函数对象，如下：

```python
def count():
    def f(j):
        def g():
            return j*j
        return g
    fs = []
    for i in range(1, 4):
        fs.append(f(i)) # f(i)立刻被执行，因此i的当前值被传入f()
    return fs
```

### 实现计数器

```python
def createCounter():
    Li = [0] # 在调用 createCounter() 时得到一个与 counter 关联的 列表对象
    def counter():
        Li.append(Li[-1] + 1)
        return Li[-1]
    return counter
```

## 匿名函数

关键词 `lambda` 来表示，形如

```python
lambda x: x * x
```

冒号前的 `x` 表示函数参数，后续只能有一个表达式，其计算结果作为表达式的返回

## 装饰器

函数对象有一个 `__name__` 属性，可以溯源（找出函数变量对应的原函数）

“装饰器”(Decorater) 在代码运行期间动态增加功能

```python
def log(func):
    def wrapper(*args, **kw):
        print('call %s():' % func.__name__)
        return func(*args, **kw)
    return wrapper
import time
# 相当于 now = log(now)
@log
def now():
    print(time.time()) 
>>> now()
call now();
2021-11-3
```

由于`log()`是一个decorator，返回一个函数，所以，原来的`now()`函数仍然存在，只是现在同名的`now`变量指向了新的函数，于是调用`now()`将执行新函数，即在`log()`函数中返回的`wrapper()`函数

### 深层嵌套

```python
def log(text):
    def decorator(func):
        def wrapper(*args, **kw):
            print('%s %s():' % (text, func.__name__))
            return func(*args, **kw)
        return wrapper
    return decorator
# 相当于 now = log('execute')(now)
@log('execute')
def now():
    print('2015-3-25')

>>> now.__name__
'wrapper'
```

注意以上两种包装都是修改变量指向的函数，会导致函数签名 `__name__` 变化，故一般用内置的 `functools.wrpas` 来实现签名传递

```python
import functools

def log(func):
    @functools.wraps(func)
    def wrapper(*args, **kw):
        print('call %s():' % func.__name__)
        return func(*args, **kw)
    return wrapper

def log(text):
    def decorator(func):
        @functools.wraps(func)
        def wrapper(*args, **kw):
            print('%s %s():' % (text, func.__name__))
            return func(*args, **kw)
        return wrapper
    return decorator
```

## 偏函数

`functools.partial` 可以固定传入函数的某些参数为给定值或传入默认的参数并返回修改后的函数

```python
int2 = functools.partial(int, base=2)
int2('10010')
# 相当于
kw = { 'base': 2 }
int('10010', **kw)

max2 = functools.partial(max, 10)
max2(5,6,7)
# 相当于
args = (10, 5, 6, 7)
max(*args)
```

