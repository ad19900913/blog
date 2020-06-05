# git学习笔记

## 搭建git服务

https://www.liaoxuefeng.com/wiki/896043488029600/899998870925664

实现了自动化部署,这才是生产力工具.

git中提供了钩子,可以在提交代码后触发,用来自动部署网站内容.

<!-- more -->

如果发现hooks脚本没执行,可以在git bash中执行git push,查看远程服务器的执行日志,用IDE提交看不到日志.

几种hooks的触发场景与应用

git-hooks按部署位置分为客户端、服务端

post-commit:在客户端提交后，触发，可用于本地自动部署应用

pro-git这本书绝对值得细读一遍。测试一下git的合并分支、解决冲突功能.

利用`git remote set-url --add [--push] <name> <newurl>`命令可以实现一次push两个远程分支。

对于同时维护部署在私服和公共服务器上的仓库，十分方便。

`git diff`命令看到如下结果，是因为改了文件权限。

```shell script
[root@VM_0_3_centos next]# git diff source/js/bookmark.js
diff --git a/source/js/bookmark.js b/source/js/bookmark.js
old mode 100644
new mode 100755
```
