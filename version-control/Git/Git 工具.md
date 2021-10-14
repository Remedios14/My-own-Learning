# Git 工具

## 选择修订版本

```shell
$ git log --abbrev-commit # 显示 SHA-1 的简写，在不产生歧义的情况下确定字符数
$ git show HEAD^ # 显示 HEAD 的父提交
$ git show HEAD~3 # 等价于 HEAD~~~ ，显示 HEAD 的父的父的父
```

## 合并提交

```shell
$ git rebase -i HEAD~3 # 其中 -i 表示交互式变基选项
```

交互变基中指定 edit 可以在目标提交上重新变基，从而实现拆分提交或修改以前的提交

## 将当前修改作用于以前的提交

```shell
git stash // 先贮藏工作空间的修改
git rebase <commit-id>^ -i // 变基操作，找到需要重新编辑的提交号用 ^ 指向其父提交，修改 pick 为 edit即可编辑
git stash pop // 已在目标提交上，pop 出贮藏的修改，还可继续进行其他修改
git rebase --continue // 完成变基
```
