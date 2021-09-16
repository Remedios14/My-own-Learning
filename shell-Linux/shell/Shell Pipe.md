# Shell Pipe

# [命令大全](https://www.runoob.com/linux/linux-command-manual.html)

管道命令，用 `|` 来分隔构成，后一条接受前一条的输出进行操作，但不能处理前一条的错误

`$ ls -al /etc | less` 第一个命令可以只有 stdout ，但后续的管道命令必须有 stdin

- [ps](https://www.runoob.com/linux/linux-comm-ps.html) (process status) - 用于显示当前进程的状态，类似于 windows 的任务管理器
  - `ps [options] [--help]`
  - -A 列出所有的进程
  - -w 显示加宽，可以显示较多的资讯
  - -au 显示较详细的资讯
  - -aux 显示所有包含其他使用者的进程
  - -ef 显示所有命令，连带命令行
- [grep](https://www.runoob.com/linux/linux-comm-grep.html) - 用于查找文件(或 document)里符合条件的字符串，若不指定文件名称，或是给予的文件名为`-`则会从标准输入读取数据
- [kill](https://www.runoob.com/linux/linux-comm-kill.html) - 用于删除执行中的程序或工作
  - `kill [-s <信息名称或编号>][程序]` 或 `kill [-l < 信息编号>]`

