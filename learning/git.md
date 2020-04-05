# git学习笔记

## 搭建git服务

https://www.liaoxuefeng.com/wiki/896043488029600/899998870925664

实现了自动化部署,这才是生产力工具.

git中提供了钩子,可以在提交代码后触发,用来自动部署网站内容.

如果发现hooks脚本没执行,可以在git bash中执行git push,查看远程服务器的执行日志,用IDE提交看不到日志.

几种hooks的触发场景与应用

gitlab的CI脚本
