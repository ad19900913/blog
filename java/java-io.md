## 管道

​	Java IO中的管道为运行在同一个JVM中的两个线程提供了通信的能力。可以通过Java IO中的PipedOutputStream和PipedInputStream创建管道。

## 替换系统流

​	尽管System.in, System.out, System.err这3个流是java.lang.System类中的静态成员，并且已经预先在JVM启动的时候初始化完成，你依然可以更改它们。只需要把一个新的InputStream设置给System.in或者一个新的OutputStream设置给System.out或者System.err，之后的数据都将会在新的流中进行读取、写入。可以使用System.setIn(), System.setOut(), System.setErr()方法设置新的系统流。

