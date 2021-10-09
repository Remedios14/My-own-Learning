# Git 分支

Git 的 “必杀技特性” —— 强无敌

是一系列的指针指向不同的快照流

## 分支新建与合并

```shell
$ git branch <new_br_name> # 从当前分支新建一个分支，HEAD指针不移动
$ git checkout -b # 从当前分支新建一个分支并检出
# 假定在新分支完成了一系列操作并提交
$ git checkout master # 回到源分支或之前检出的分支
$ git merge <new_br_name> # 将新分支的修改合并入源分支
```

## 分支管理

```shell
$ git branch [-v] [-vv] [--merged] [--no-merged] [-d] # 查看所有分支，显示最近提交，查看已合并入当前分支的分支（合并了的不带 * 符号）
```

### 远程分支

```shell
$ git ls-remote <remote> # 列出远程仓库的分支列表
$ git push <remote> local-br[:br-name] # 推动本地的分支到远程仓库，可以指定远程仓库的分支

$ git push <remote> --delete [br-name] # 删除远程仓库的指定分支，本地仍保留
$ git fetch <remote> -p # 直接删除本地存在而远程不存在的分支
```

### 跟踪分支

```shell
$ git checkout -b <branch> <remote>/<branch> # 新建并检出一个跟踪远程分支的本地分支
$ git checkout --track <remote>/<branch> # 同上只是同名

$ git branch -u <remote>/<branch> # 将本地已有分支设置或修改跟踪远程分支
$ git pull # 在有跟踪时可以直接完成拉取并合并
```

### 删除远程分支

```shell
$ git push <remote> --delete <branch> # 一般在垃圾回收之前都能恢复
```

## 变基

1. 找到两个分支的最近共同祖先
2. 对比当前分支相对于该祖先的历次提交，提取相应的修改并存为临时文件
3. 将当前分支指向目标基底
4. 将暂存的临时文件修改依次序应用

```shell
$ git checkout <work-br>
# 完成工作提交等
$ git rebase master # 变基，通常此时 master 已经有其他人的提交
$ git checkout master
$ git merge <work-br> # 进行 fast-forward 合并
```

### 酷功能

```shell
$ git rebase --onto <main> <mid> <work> 
# 取出 work 分支，找出从 mid 分支分歧之后的补丁，把这些补丁应用到 main 分支上，使得 work 分支看起来像直接基于 main 修改一样
$ git rebase <main> <work> # 无须检出直接 work 向 main 变基
$ git rebase <remote>/<branch> # 向指定分支变基，当整体被打乱时可以补救一下
$ git cherry-pick <commit-id> # 在当前分支上取出来自任意地方的一个提交并应用
```

### 代码回滚

```shell
$ git reset --hard <commit-id / HEAD*> # 回退到制定提交版本，可以用 ^ 或 ~n 符号结合 HEAD 指针
```
