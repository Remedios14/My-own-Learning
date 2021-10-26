# IO 编程

指 Input/Output，即输入和输出；包括同步 IO 和异步 IO

在磁盘上读写文件的功能都是由操作系统提供的，现代操作系统不允许普通的程序直接操作磁盘，所以，读写文件就是请求操作系统打开一个文件对象（通常称为文件描述符），然后，通过操作系统提供的接口从这个文件对象中读取数据（读文件），或者把数据写入这个文件对象（写文件）

## 文件读写

```python
f = open('/User/michael/test.txt', 'r')
print(f.read())
f.close() # 使用完一定记得关闭，故常 try...except...finally，或者
with open('/User/michael/test.txt', 'r') as f:
    print(f.read())
```

- `read()` 一次性读取所有内容
- `read(size)` 每次最多读取 size 个字节的内容
- `readline()` 每次读取一行内容
- `readlines()` 一次读取所有内容并按行返回 `list`

### 其他 tips

- 读取二进制文件用 `'rb'` 模式打开
- 读取非 UTF-8 文件需要给 `open()` 传入 `encoding='...'` 参数；还可传入 `errors` 参数设置处理编码错误的方法
- 写入模式 `'w'` 或 `'wb'` ，追加模式 `'a'` 
- [官方文档](https://docs.python.org/3/library/functions.html#open)

## StringIO 和 BytesIO

数据读写可以在文件( file )中，也可在内存中（即此处两种）

### StringIO 

只能操作 str

创建一个对象，用 `write()` 方法写入，用 `getvalue()` 方法获取

```python
>>> from io import StringIO
>>> f = StringIO()
>>> f.write('hello')
5
>>> f.write(' ')
1
>>> f.write('world!')
6
>>> print(f.getvalue())
hello world!
```

也可以直接初始化，其读取行为同文件

```python
>>> from io import StringIO
>>> f = StringIO('Hello!\nHi!\nGoodbye!')
>>> while True:
...     s = f.readline()
...     if s == '':
...         break
...     print(s.strip())
...
Hello!
Hi!
Goodbye!
```

### BytesIO

可以操作二进制数据

```python
>>> from io import BytesIO
>>> f = BytesIO()
>>> f.write('中文'.encode('utf-8'))
6
>>> print(f.getvalue())
b'\xe4\xb8\xad\xe6\x96\x87'
# 初始化
>>> from io import BytesIO
>>> f = BytesIO(b'\xe4\xb8\xad\xe6\x96\x87')
>>> f.read()
b'\xe4\xb8\xad\xe6\x96\x87'
```

## 操作文件和目录

由 `os` 模块调用操作系统提供的接口函数

`os.name` 存储操作系统类型：`os.uname()` 返回详细信息（在 `Windows` 上没有）

- `posix` for `Linux`、`Unix` 或 `Mac OS X`
- `nt` for `Windows`

### 环境变量

全部存在 `os.environ` 变量中

用 `os.environ.get('key')` 获取指定环境变量如 `'PATH'`

### 操作文件和目录

查看、创建和删除目录

```python
# 查看当前目录的绝对路径:
>>> os.path.abspath('.')
'/Users/michael'
# 在某个目录下创建一个新目录，首先把新目录的完整路径表示出来:
>>> os.path.join('/Users/michael', 'testdir')
'/Users/michael/testdir'
# 然后创建一个目录:
>>> os.mkdir('/Users/michael/testdir')
# 删掉一个目录:
>>> os.rmdir('/Users/michael/testdir')
```

合并字符串表示的路径时要使用 `os.path.join()` 函数，会自动添加相应操作系统的**分隔符**

同理，拆分的时候使用 `os.path.split()` ，最后一级为目录或文件名

`os.path.splitext()` 可以直接得到文件拓展名

```python
>>> os.path.splitext('/path/to/file.txt')
('/path/to/file', '.txt')
```

列出所有的 `.py` 文件

```python
>>> [x for x in os.listdir('.') if os.path.isfile(x) and os.path.splitext(x)[1]=='.py']
['apis.py', 'config.py', 'models.py', 'pymonitor.py', 'test_db.py', 'urls.py', 'wsgiapp.py']
```

## 序列化

把变量从内存中编程可存储或传输的过程 —— pickling

```python
import pickle
d = dict(name = 'Bob', age = 20, score = 88)
pic = pickle.dumps(d) # 得到序列化对象，也可直接写入
with open('dump.txt', 'wb') as f:
    pickle.dump(d, f)
with open('dump.txt', 'rb') as r:
    dic = pickle.load(r)
print(dic)
```

### JSON

| JSON类型   | Python类型 |
| :--------- | :--------- |
| {}         | dict       |
| []         | list       |
| "string"   | str        |
| 1234.56    | int或float |
| true/false | True/False |
| null       | None       |

```python
import json
d = dict(name = 'Bob', age = 20, score = 88)
json.dumps(d) # 返回一个 str ，其格式为  JSON 格式
with open('dumps.txt', 'wb') as f:
    json.dumps(d, f)
with open('dumps.txt', 'rb') as r:
    json.loads(r.readline())
```

读取 `loads()` 方法把 JSON 字符串反序列化；`load()` 从 `file-like Object` 反序列化
