# yosoro~

## 什么是 PyTorch

PyTorch 是一个基于 Python 的科学计算包

在此先简单介绍一些八股操作

### Tensor 张量

#### 创建

类似于 numpy 的 ndarrays，同时 Tensor 可以使用 GPU 进行计算

```python
torch.empty(5,3) # 空矩阵，填充值不固定
torch.rand(5,3) # 随机初始化，[0,1] 取值
torch.zeros(5,3,dtype=torch.long) # 0 矩阵并指定数据类型
torch.tensor([5.5,3]) # 用具体的值初始化，可以传入 list 的 list
torch.randn_like(x,dtype=torch.float) # 基于现有的形状新建一个
torch.ones_like(x)
x.size() # 返回形如 torch.Size([5,3]) 的元组类型
```

--------------

#### 操作

注意要匹配两个矩阵的 Size，以加法为例

```python
x + y # 直接相加
torch.add(x, y) # 用内置函数
torch.add(x, y, out=result) # 可以指定输出变量到已有的变量（仿佛指针）
y.add_(x) # 直接对 y 变量修改相加后的结果，不推荐
```

其他类似的改变本身所指对象的方法都会 `'_'` 后缀

乘法包括矩阵乘法和点乘

```python
t1 @ t1.T # 矩阵乘法
t1.matmul(t1.T) # 同上， matrix multiplication 的缩写
torch.matmul(t1, t1.T, out=y1)

t2 * t2 # 点乘
t2.mul(t2)
torch.mul(t2, t2, out=y2)
```

```python
print(x[:,1]) # 切片也是可行的，有几维就传几个
x[:,1] = 0 # 对切片赋值相当于对切片上每个值赋值
x.view(m,n) # 返回重塑的 tensor，可以传单参数或传 -1 来自动展开
t1 = torch.cat([tensor, tensor, tensor], dim=1) # 拼接操作，dim 指定拼接维度即朝向
tensor.T # 返回转置对象
```

对单值 tensor 有 `.item()` 方法获取其内部值

### Datasets & DataLoaders

提供有 `torch.utils.data.DataLoader` 和 `torch.utils.data.Dataset` 来使用预存（如 [FashionMNIST](https://pytorch.org/tutorials/beginner/basics/data_tutorial.html)）的或自己的数据集

------

### 自动微分 torch.autograd

```python
x = torch.ones(5)  # input tensor
y = torch.zeros(3)  # expected output
w = torch.randn(5, 3, requires_grad=True) # 跟踪梯度计算
b = torch.randn(3, requires_grad=True)
z = torch.matmul(x, w)+b
loss = torch.nn.functional.binary_cross_entropy_with_logits(z, y)
```