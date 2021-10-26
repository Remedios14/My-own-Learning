from functools import reduce

def normalize(name):
    name = name.lower()
    name = name.capitalize()
    return name

L1 = ['adam', 'LISA', 'barT']
L2 = list(map(normalize, L1))
print(L2)

def prod(L):
    def mult(x, y):
        return x * y
    return reduce(mult, L)

print('3 * 5 * 7 * 9 =', prod([3, 5, 7, 9]))
if prod([3, 5, 7, 9]) == 945:
    print('测试成功!')
else:
    print('测试失败!')

def str2float(s):
    i = s.index('.')
    return reduce(lambda x, y: x * 10 + y, map(int, s[:i])) +\
                  reduce(lambda x, y: x / 10 + y, map(int, s[i+1:][::-1])) / 10

print('str2float(\'123.456\') =', str2float('123.456'))
if abs(str2float('123.456') - 123.456) < 0.00001:
    print('测试成功!')
else:
    print('测试失败!')