#!/bin/bash
#获取字符串长度
name="SnailClimb"
# 第一种方式
echo ${#name} # 输出10
# 第二种方式
expr length "$name";
# 运算必须在符号两侧有空格
expr 4 + 6
expr 4+6
