# Linux

[免费！开源！你还能要求什么呢！](https://www.runoob.com/linux/linux-command-manual.html)

## 系统目录结构

```shell
ls /
```

根目录下各目录：

- **/bin**: 是 Binaries (二进制文件) 的所写，这个目录存放着最经常使用的命令
- /boot: 存放启动 Linux 时使用的一些核心文件，包括一些连接文件以及镜像文件
- /dev: Device (设备) 的缩写，存放 Linux 的外部设备
- **/etc**: Etcetera (等等) 的缩写，存放所有的系统管理所需要的配置文件和子目录
- /home: 用户主目录，每个用户都在该目录下有一个自己的目录
- /lib: Library 的缩写，存放系统最基本的动态连接共享库，几乎所有应用程序都要
- /lost+found: 一般是空的，当系统非法关机之后会存入文件
- /media: 自动识别如 U 盘、光驱等设备并挂载到这里
- /mnt: 让用户临时挂载别的文件系统，如光驱
- /opt: optional 的缩写，给主机额外安装软件所存放的目录，默认为空。
- /proc: Processes (进程) 的缩写，是一种伪文件系统，存储当前内核运行状态的一系列特殊文件。这个目录是一个虚拟的目录，是系统内存的映射，可以通过直接访问这个目录来获取系统信息。
- /root: 系统管理员目录 (super user)
- **/sbin**: Superuser Binaries 的缩写，存放 su 的系统管理程序
- /selinux: 是 Redhat/CentOS 特有的
- /srv: 存放一些服务启动后需要提取的数据
- /sys: 安装 2.6 内核中新出现的文件系统 sysfs，集成了三种文件系统的信息：
  - 针对进程信息的 proc 文件系统
  - 针对设备的 devfs 文件系统
  - 针对伪终端的 devpts 文件系统
- /tmp: temporary 的缩写，用来存放一些临时文件
- /usr: unix shared resources (共享资源) 的缩写，非常重要，用户的很多应用程序和文件都放在这个目录下
- **/usr/bin**: 系统用户使用的应用程序
- **/usr/sbin**: 超级用户使用的比较高级的管理程序和系统守护程序
- /usr/src: 内核源代码默认的放置目录
- **/var**: variable 的缩写，存放着不断扩充的东西
- /run: 临时文件系统，存储系统启动以来的信息，重启时被删掉或清除



## 快捷键

- ctrl + c 停止当前进程
- ctrl + z 挂起当前进程，放后台
- ctrl + r 查看命令历史
- ctrl + l 清屏，同 `clear`
- ctrl + a 光标移动到行首
- ctrl + e 行尾
- ctrl + k 清除
- ctrl + w 清除单词

## 进程管理命令

程序和进程

1. 程序是静态概念，本身作为一种软件资源长期保存；而进程是程序的执行过程，它是动态概念，有一定的生命期，是动态产生和消亡的。
2. 程序和进程无一一对应关系。一个程序可以由多个进程共用；另一方面，一个进程在活动中有可顺序地执行若干个程序。

进程和线程

进程：就是正在执行的程序或命令，每一个进程都是一个运行的实体，都有自己的地址空间，并占用一定的系统资源。

线程： 轻量级的进程；进程有独立的地址空间，线程没有；线程不能独立存在，它由进程创建；相对讲，线程耗费的cpu和内存要小于进程。

### ps

查看系统中的进程信息

`ps -[auxle]`

- a 显示所有用户的进程
- u 显示用户名和启动时间
- x 显示没有控制终端的进程
- e 显示所有进程，包括没有控制终端的进程
- l 长格式显示

`ps -ef | grep init` 查看指定进程信息

### pstree

需要 psmisc 包，查看当前进程树

`pstree -[pu]`

- p 显示进程 PID
- u 显示进程的所属用户

### top

查看系统健康状态，显示当前系统中耗费资源最多的进程，以及系统的一些负载情况

`top -d` 指定秒数，几秒刷新一次，默认 3 秒

### kill

关闭进程

`kill -9 PID` 强行关闭（常用）

`kill -1 PID` 重启进程

## 文件基本属性

多用户系统下，不同用户处于不同的地位，拥有不同的权限。

```
chown (change ownerp): 修改所属用户与组
shmod (change mode) : 修改用户的权限
```

`ll` 或者 `ls -l` 来显示文件属性以及所属用户和组，个例如下：

`dr-xr-xr-x   2 root root 4096 Dec 14  2012 bin`

其中第一个字母表示文件属性：

| 字符 | 含义                                       |
| ---- | ------------------------------------------ |
| `d`  | 目录                                       |
| `-`  | 文件                                       |
| `l`  | 链接文档(link file)                        |
| `b`  | 装置文件里面的可供储存的接口设备           |
| `c`  | 装置文件里面的串行端口设备，例如键盘、鼠标 |

接下来字符每三个为一组，均为 `rwx` 的组合

`r` 表示可读(read)、`w` 表示可写(write)、 `x` 表示可执行(execute)，若相应无权限 `-`

可以看见共三组 `rwx` 组合，依次代表：

属主权限、属组权限、其他用户权限



随后的两个 root 分别代表所属用户、所属用户组

#### 修改文件属组

`chgrp [-R] 属组名 文件名`

-R ： 递归地更改文件属组

#### 修改文件属主，可同时更改属组

`chown [-R] 属主名 文件名`

`chown [-R] 属主名：属组名 文件名`

### 修改权限

#### 使用数字

`chmod [-R] xyz 文件或目录`

由于 r:4、w:2、x:1 ，故三个权限组合的数字唯一，可以由此来单个数字设定

#### 使用符号

| 命令  | 缩略符     | 给赋    | 权限    | 目标       |
| ----- | ---------- | ------- | ------- | ---------- |
| chmod | u、g、o、a | +、-、= | r、w、x | 文件或目录 |

其中 + 表示添加、 - 表示除去、 = 表示设定；

例子`chmod u=rwx,g=rx,o=r 文件名`



## 文件与目录管理

#### 处理目录

| 命令  | 全拼                 | 功能                   |
| ----- | -------------------- | ---------------------- |
| ls    | list files           | 列出目录及文件名       |
| cd    | change directory     | 切换目录               |
| pwd   | print work directory | 显示当前目录           |
| mkdir | make directory       | 创建一个新的目录       |
| rmdir | remove directory     | 删除一个**空的**目录   |
| cp    | copy file            | 复制文件或目录         |
| rm    | remove               | 删除文件或目录         |
| mv    | move file            | 移动文件或目录或重命名 |

#### 文件查看

| 命令 | 功能                            |
| ---- | ------------------------------- |
| cat  | 从第一行开始显示文件内容        |
| tac  | 从最后一行开始显示 # cat 的逆反 |
| nl   | 显示的时候顺便输出行号          |
| more | 胰一页一页地显示文件内容        |
| less | 类似，可以往前翻页              |
| head | 只看头几行                      |
| tail | 只看尾巴几行                    |

## 用户和用户组管理

主要及几个方面：

- 用户账号的添加、删除与修改
- 用户口令的管理
- 用户组的管理

###  用户账号的管理

`useradd 选项 用户名`

`userdel 选项 用户名`

`usermod 选项 用户名`

`passwd 选项 用户名`

### 用户组的管理

```
groupadd 选项 用户组
groupdel 选项 用户组
groupmod 选项 用户组
$ newgrp root // 切换用户组
```

### 与用户账号有关的系统文件

1. /etc/passwd 文件是用户管理工作涉及的最重要的一个文件
   1. `用户名:口令:用户标识号:组标识号:注释性描述:主目录:登陆 Shell`
2. /etc/shadow 由 pwconv 命令根据上面文件的内容对应生成
3. /etc/group 所有用户组信息



## 磁盘管理

三个常用命令 `df`、`du`和`fdisk`

- `df` - disk full ：列出文件系统的整体磁盘使用量
  - -a ：列出所有的文件系统，包括系统特有的 /proc 等文件系统
  - -k ：以 kBytes 的容量显示各文件系统
  - -m ：以 MBytes 的容量显示各文件系统
  - -h ：以人们较易阅读的 GBytes, MBytes, KBytes 等格式自行显示
  - -H ：以 M=1000K 取代 M=1024K 的进位方式
  - -T ：显示文件系统类型, 连同该 partition 的 filesystem 名称 (例如 ext3) 也列出
  - \- i ：不用硬盘容量，而以 inode 的数量来显示
- `du` - disk used ：检查磁盘空间使用量
  - -a ：列出所有的文件与目录容量，因为默认仅统计目录底下的文件量而已
  - -h ：以人们较易读的容量格式 (G/M) 显示；
  - -s ：列出总量而已，而不列出每个各别的目录占用容量；
  - -S ：不包括子目录下的总计，与 -s 有点差别。
  - -k ：以 KBytes 列出容量显示；
  - -m ：以 MBytes 列出容量显示；
- `fdisk` - 用于磁盘分区
  - -l ：输出后面接的装置所有的分区内容。若仅有 fdisk -l 时， 则系统将会把整个系统内能够搜寻到的装置的分区均列出来。

#### 磁盘格式化

make file system

`mkfs [-t 文件系统格式] 装置文件名`

#### 磁盘检验

file system check

`fsck [-t 文件系统] [-ACay] 装置名称`

#### 磁盘挂载与卸除

`mount` 和 `umount`

`mount [-t 文件系统] [L Label名] [-o 额外选项] [-n] 装置文件名 挂载点`

`umount [-fn] 装置文件名或挂载点`

