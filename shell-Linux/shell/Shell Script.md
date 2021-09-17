# Shell Script

## Shell 环境定义

### 临时环境变量

​		指用户在当前登陆环境生效的变量，用户登录系统后，直接在命令行上定义的环境变量便只能在当前的登陆环境中使用。

- 将环境变量定义写入到配置文件中，实现每次登陆时系统自动定义
- `/etc/profile` 针对系统所有用户生效
- `$HOME_name/.bash_profile` 针对特定用户生效，$HOME 为用户的宿主目录

当用户登陆后，先继承前者，再继承后者

### 系统预定义的环境变量

如 $PATH、$HOME、$SHELL、$PWD 等

## Shell 脚本编程

首行必须为 `!#/bin/bash` 其中为指向 shell 解释器的绝对路径

### 执行

1. 输入脚本的绝对路径或相对路径
2. `bash` 或 `sh` + 脚本名称
3. 在脚本的路径前再加 `.` 或 `source`

## 交互命令

### 读取用户输入

```shell
read -p (提示语句) -n (字符个数) -t (等待时间，s) -s (隐藏输入)
read –t 30 –p “please input your name: ” NAME
echo $NAME
read –s –p “please input your age : ” AGE
echo $AGE
read –n 1 –p “please input your sex  [M/F]: ” GENDER
echo $GENDER
```

## 脚本调试

`bash -x script` 执行脚本并显示所有变量的值

`bash -v script` 执行并显示脚本内容

`bash -n script` 不执行脚本仅检查语法，将返回所有语法错误

在脚本内添加 `set -x` 对部分脚本调试