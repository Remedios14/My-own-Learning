# yosoro~

## Intro

虽然说是 intro ，但是准备把常用指令都存进来~~并且后续应该懒得补充深入的内容了~~

### 简单介绍

通常数据分析都谈到 numpy, pandas, matplotlib, 以及可能使用到的 seabon, sklearn；不过 pandas 基本集成了 numpy 的数值运算内容，超值

### 数据读取

pandas 可以读取的数据格式包括 execl, csv, table 等，读入后形成数据类型 DataFrame，常用读取函数为 `read_csv` 和 `read_excel`，传入参数主要为文件路径，类比 `open()`

```python
import pandas as pd
df = pd.read_csv('trial.txt')
print(df.head())
```

csv 是一种（通常）以英文半角逗号为列分割符，以换行符为行分隔符的纯文本数据格式

### 数据概览

B 话不多说直接上代码

```python
df.head() # 默认显示前 5 行，可传入 n
df.tail() # 默认显示后 5 行
df.shape # 属性，n * m 大小
df.columns # 所有列名的 list
```

### 数据定位

常用 `loc` 和 `iloc` ，有些微的区别：

- loc : works on the labels in the index —— 按照标签取数据
- iloc : works on the positions in the index (so it only takes integers)

```python
df.loc[1] # 传入一个值取行形成 Series，此处取第 2 行数据
df.loc[2:4] # 可传入切片或列表来取出多行
df.loc[[1,4]] # 传入列表和传入切片的形式有一点不同，不可以对换
rows = k / n:m / [k1,k2,...]
cols = col_name / [col1, col2, ...] # 字符串形式
df.loc[rows, cols] # 行索引后可以加上列名或 列名 list
```

### 数据类型

pandas 在读入数据存储为 DataFrame 或 Series 时会将值转为相应的类型，如下有对应 pandas 内和 python 原生：

- object : string
- int : integer
- float : float
- datetime : time
- bool : boolean

`var.dtype` 查看变量的 dtype

