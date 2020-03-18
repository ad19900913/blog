# maven学习记录

## 配置相关

settings.xml的模板文件及字段说明-https://maven.apache.org/ref/3.6.3/maven-settings/settings.html

## 仓库相关

安装jar包到本地仓库

```shell
mvn install:install-file 
-Dfile=E:\download\kstrap-1.2.8.4.jar 
-DgroupId=crom.kayakwise.kstrap
-DartifactId=kstrap
-Dversion=1.2.8.4 
-Dpackaging=jar
```

