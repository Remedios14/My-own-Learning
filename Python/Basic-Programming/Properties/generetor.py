# 类似列表生成式，但是一个独立对象，其功能为在调取值时才计算，节省空间
# 生成方法：
g = (x * x for x in range(10))
print(g)
# 使用 next() 函数获取 生成器 的当前指向并向下指
print(next(g))

# 可以使用 for 循环调取
for n in g:
    print(n)

# 另一种定义：在函数定义中包含 yield 关键字，得到 generator 函数，会返回一个 generator
def fib(max):
    n, a, b = 0, 0, 1
    while n < max:
        yield b
        a, b = b, a + b
        n += 1
    return 'done'

fib_g = fib(5)
for y in fib_g:
    print(y)

# 调用 generator 函数会创建一个 generator 对象，多次调用 generator 函数会创建多个互相独立的 generator

# 生成杨辉三角
def triangles():
    prev = [1]
    yield prev
    while True:
        curr = [0] * (len(prev) + 1)
        curr[0] = prev[0]
        curr[-1] = prev[-1]
        for i in range(0, len(prev) - 1):
            curr[i + 1] = prev[i] + prev[i+1]
        yield curr
        prev = curr