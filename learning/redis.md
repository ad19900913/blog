# 使用docker搭建redis集群

运行环境为windows10

## 安装docker

下载链接：https://download.docker.com/win/stable/Docker%20Desktop%20Installer.exe

## 拉取redis镜像

docker pull redis

<!-- more -->

## 运行redis镜像

```shell
docker network create redis-net
mkdir -p /home/redis-cluster
cd /home/redis-cluster
vim redis-cluster.tmpl

port ${PORT}
cluster-enabled yes
cluster-config-file nodes.conf
cluster-node-timeout 5000
cluster-announce-ip 192.168.168.131
cluster-announce-port ${PORT}
cluster-announce-bus-port 1${PORT}
appendonly yes


for port in $(seq 8010 8015); \
do \
  mkdir -p ./${port}/conf  \
  && PORT=${port} envsubst < ./redis-cluster.tmpl > ./${port}/conf/redis.conf \
  && mkdir -p ./${port}/data; \
done

for port in $(seq 8010 8015); \
do \
   docker run -it -d -p ${port}:${port} -p 1${port}:1${port} \
  --privileged=true -v /home/redis-cluster/${port}/conf/redis.conf:/usr/local/etc/redis/redis.conf \
  --privileged=true -v /home/redis-cluster/${port}/data:/data \
  --restart always --name redis-${port} --net redis-net \
  --sysctl net.core.somaxconn=1024 redis redis-server /usr/local/etc/redis/redis.conf; \
done

docker exec -it redis-8010 bash
cd /usr/local/bin/

redis-cli --cluster create 192.168.168.131:8010 192.168.168.131:8011 192.168.168.131:8012 192.168.168.131:8013 192.168.168.131:8014 192.168.168.131:8015

redis-cli -c -h 192.168.168.131 -p 8010

docker ps -a | grep -i "redis-801*" | awk '{print $1}' | xargs docker stop
docker ps -a | grep -i "redis-801*" | awk '{print $1}' | xargs docker rm -f
rm -rf 801{0..5}/conf/redis.conf
```



# redis集群说明文档

https://redis.io/topics/cluster-tutorial