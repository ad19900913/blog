# nginx学习记录

## 编译安装nginx

在离线环境下安装，系统为RHEL4.8.5

用系统yum源安装gcc、gcc-c++、zlib、zlib-devel

按照顺序编译安装pcre、libtool、nginx

安装完成后建立软连接 ln -s /usr/local/nginx/sbin/nginx  /usr/local/sbin/nginx 

## 编译安装正向代理模块

注意选择正确的patch版本

参考网站https://github.com/chobits/ngx_http_proxy_connect_module

```shell
cd /root
wget http://nginx.org/download/nginx-1.16.1.tar.gz
git clone https://github.com/chobits/ngx_http_proxy_connect_module
yum -y install pcre-devel zlib-devel gcc gcc-c++ make openssl-devel pcre-devel  zlib-devel patch   
tar xf nginx-1.6.0.tar.gz
unzip /root/ngx_http_proxy_connect_module-master.zip
cd  /root/nginx-1.6.0/
patch  -p1 < /root/ngx_http_proxy_connect_module-master/proxy_connect.patch  
./configure --add-module=/root/ngx_http_proxy_connect_module-master/ngx_http_proxy_connect_module
make &&  make install
ln -s /opt/demo/nginx/sbin/nginx /usr/bin/nginx
nginx start
curl http://localhost/index.html
```

正向代理配置参考官方文档例子

```
 server {
     listen                         3128;

     # dns resolver used by forward proxying
     resolver                       8.8.8.8;

     # forward proxy for CONNECT request
     proxy_connect;
     proxy_connect_allow            443 563;
     proxy_connect_connect_timeout  10s;
     proxy_connect_read_timeout     10s;
     proxy_connect_send_timeout     10s;

     # forward proxy for non-CONNECT request
     location / {
         proxy_pass http://$host;
         proxy_set_header Host $host;
     }
 }
```

## nginx搭建文件服务器

修改nginx.conf配置文件

```
server {
	listen 8000; # 端口
	server_name localhost; # 服务名
	charset utf-8; # 避免中文乱码
	root E:\Download\java; # 显示的根索引目录，注意这里要改成你自己的，目录要存在
	location / {
		autoindex on; # 开启索引功能
		autoindex_exact_size off; # 关闭计算文件确切大小（单位bytes），只显示大概大小（单位kb、mb、gb）
		autoindex_localtime on; # 显示本机时间而非 GMT 时间
	}
}
```

