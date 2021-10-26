# 可用 for 循环遍历的对象都属于 Iterable 对象
# 而可用 next() 函数调用返回下一个值的是 迭代器 Iterator 对象

print("Iterator 对象的计算是惰性的，可以保存一个无限大的数据流而仅占有限的空间")

print("for 循环本质上就是通过不断调用 next() 函数实现的")

print("对 Iterable 对象可以使用 iter() 函数获得相应的 Iterator 对象")