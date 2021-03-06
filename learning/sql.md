# 数据库相关

## 不同数据库驱动及链接

### MySQL 5 ###

jdbc.driverClassName=com.mysql.jdbc.Driver
jdbc.url=jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=GBK
jdbc.username=root
jdbc.password=root

<!-- more -->

### PostgreSQL

#jdbc.driverClassName=org.postgresql.Driver
#jdbc.url=jdbc:postgresql://localhost/bookstore
#jdbc.username=
#jdbc.password=

### Oracle ###

#jdbc.driverClassName=oracle.jdbc.driver.OracleDriver
#jdbc.url=jdbc:oracle:thin:@192.168.1.250:1521:devdb
#jdbc.username=HFOSPSP
#jdbc.password=HFOSPSP

### ODBC

#jdbc.driverClassName=sun.jdbc.odbc.JdbcOdbcDriver
#jdbc.url=jdbc:odbc:bookstore
#jdbc.username=
#jdbc.password=

## oracle如何选择驱动版本

驱动包路径：oracle\product\11.2.0\dbhome_1\jdbc\lib

ojdbc5.jar
ojdbc5dms.jar
ojdbc5dms_g.jar
ojdbc5_g.jar
ojdbc6.jar
ojdbc6dms.jar
ojdbc6dms_g.jar
ojdbc6_g.jar
simplefan.jar

在使用Oracle JDBC驱动时，有些问题你是不是通过替换不同版本的Oracle JDBC驱动来解决的？最常使用的ojdbc14.jar有多个版本，classes12.jar有多个版本你了解吗？

连接类型：
1、JDBC OCI： oci是oracle call interface的缩写，此驱动类似于传统的ODBC 驱动。因为它需要Oracle Call Interface and Net8，所以它需要在运行使用此驱动的JAVA程序的机器上安装客户端软件，其实主要是用到orcale客户端里以dll方式提供的oci和服务器配 置。
2、JDBC Thin： thin是for thin client的意思，这种驱动一般用在运行在WEB浏览器中的JAVA程序。它不是通过OCI or Net8，而是通过Java sockets进行通信，是纯java实现的驱动，因此不需要在使用JDBC Thin的客户端机器上安装orcale客户端软件，所以有很好的移植性，通常用在web开发中。

\-------------------------------------------------------------------------------

随Oracle 8i发布的Oracle JDBC驱动8.1.7版本
classes111.zip 适用于JDK 1.1.x
classes12.zip 适用于JDK 1.2.x
只有zip文件，无jar文件。
\-------------------------------------------------------------------------------

随Oracle 9i发布的Oracle JDBC驱动9.2.0版本

classes111.jar 适用于JDK 1.1.x
classes12.jar 适用于JDK 1.2 and JDK 1.3 （我的项目环境JDK1.6，oracle 10g，windows，用了这个目前没发现问题）
ojdbc14.jar 适用于JDK 1.4
classes111.zip 适用于JDK 1.1.x
classes12.zip 适用于JDK 1.2.x

***_g.jar 只是用javac -g编译，生成所有调试信息，其它全一样

新特性：
1、Thin连接类型的驱动对BFILE，BLOB，CLOB 提供直接支持，以前通常是调用PL/SQL来实现。
2、支持JDBC 3.0 特性
3、ojdbc14.jar 支持JDK 1.4
4、ojdbc14.jar 支持保存点（Savepoint）
5、可以在不同的连接池中使用PreparedStatement，这是重要的性能提升

从此以后新的jar文件的命名采用 ojdbc<jdk ver>.jar 格式 ，以前的jar文件名称不变
\-------------------------------------------------------------------------------

随Oracle 10.2发布的Oracle JDBC驱动10.2版本
classes12.jar 适用于JDK 1.2 and JDK 1.3.

ojdbc14.jar 适用于 JDK 1.4 and 5.0
***_g.jar 只是用javac -g编译，生成所有调试信息，其它全一样


特点：
1、全面支持JDK 1.5
2、支持JDBC 3.0
\-------------------------------------------------------------------------------

随Oracle 11.1发布的Oracle JDBC驱动11.1版本

 

ojdbc5.jar: 适用于jdk5
ojdbc6.jar: 适用于jdk6 （如果你使用jdk1.5,就不能使用这个驱动）
***_g.jar 只是用javac -g编译，生成所有调试信息，其它全一样


新特性：
1、ojdbc6.jar：支持JDK6，支持JDBC 4.0，新的java.sql.SQLXML类型没有被支持。
ojdbc5.jar：全面支持使用JDK5 和 JDBC 3.0 。
2、建议使用oracle.jdbc.OracleDriver类，不建议使用oracle.jdbc.driver.OracleDriver。从9.0.1开始的每个release都推荐使用oracle.jdbc。

3、j2se 1.2,1.3,1.4不再支持。11R1不再包括这些版本的jar和zip，如果仍然使用这些版本，可以继续使用10gR2的jdbc。
4、11gR1 Thin driver支持AES加密算法，SHA1 hash算法，RADIUS, KERBEROS，SSL认证机制.
5、支持ANYDATE和ANYTYPE类型。这两种类型自9i引入，11R1前，程序员只能通过PL/SQL操作。
6、高级队列支持。11R1提供了访问AQ的高性能接口。
7、支持数据库变更通知。
8、Thin和OCI的数据库启动和关闭。11R1提供了这样的方法来启动和关闭数据库。
9、新的工厂方法。Oracle JDBC 11R1 oracle.jdbc.OracleConnection提供了创建Oracle对象的工厂方法。
包括ARRAY, BFILE, DATE, INTERVALDS, NUMBER, STRUCT, TIME,TIMESTAMP,TIMESTAMP等。

 

 

\---------------------------------------------------

总体讲新版本的JDBC驱动 性能强、很多bug被发现并已解决。

我遇到的，之前使用ojdbc14.jar（不记得哪个版本了）批量插入10万条，实际只插入了3万多条，其它的丢失了，换ojdbc6.jar后，一次commit批量插入100万条也OK了。


尽量使用和数据库版本一致的驱动,有bug时，换高版本的JDBC驱动试试 。

如果一个jdbc的jar包你不知道是那个版本的,可以解压这个jar包,再META-INF\MANIFEST.MF 文件中找"Oracle JDBC Driver version - 10.1.0.2.0"字样,就知道版本了.