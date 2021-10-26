#Environ_Test.py
import os

print(os.name)

print(os.uname())

print(os.environ)

print(os.environ.get('PATH'))

print(os.path.abspath('.'))

print(os.path.splitext('/path/to/file.txt'))

dirs = [x for x in os.listdir('.') if os.path.isdir(x)]

for d in dirs:
    print(d)
