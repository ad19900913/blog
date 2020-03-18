# SQL转换之db2>>>oracle

## 系统时间戳

* db2:CURRENT TIMESTAMP
* oracle:CURRENT_TIMESTAMP
## 限制查询条数
* db2:LIMIT N
* db2:fetch first N rows only
* oracle:rownum <= N
## 字符串拼接
* db2:CONCAT
* oracle:||
## 字符串截取
* db2:SUBSTRING(id_type,2)
* oracle:SUBSTR(id_type,2)
## 系统表
* db2:select 1 from sysibm.sysdummy1
* oracle:select 1 from dual
## 截位函数
* db2:truncate
* oracle:trunc
## 获取序列的值
* db2:NEXTVAL FOR SEQ
* oracle:SEQ.NEXTVAL
## 分析函数
* db2:rownumber() over ()
* oracle:row_number() over ()
## 转换函数
* db2:INTEGER(col)
* oracle:cast(col as int)
## 计算两个日期相差多少天
* db2:days(to_date(END_DATE,'YYYYMMDD'))-days(to_date(START_DATE,'YYYYMMDD'))
* oracle:to_date(END_DATE,'YYYYMMDD')-to_date(START_DATE,'YYYYMMDD')
