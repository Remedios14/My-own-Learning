import functools, time

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

print(now.__name__)

def log_1(text):
    def decorator(func):
        @functools.wraps(func)
        def wrapper(*args, **kw):
            print('%s %s():' % (text, func.__name__))
            return func(*args, **kw)
        return wrapper
    return decorator
# 自动实现签名传递
@log_1('run')
def curr():
    print(time.time())

print(curr.__name__)