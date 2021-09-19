-- ===================================


/*
    字段非null, 设置默认值。优化性能
    null值: 认为是最小的，建索引有影响
*/

-- ===================================


use sfnotes;

-- count(1) > count(*) == count(二级) > count(id);


-- id主键聚簇索引，name: 联合索引的第一列
-- count(非主键): 不会计算null值，把null值哪一行数据直接扔了

-- count: 扫描所在列的索引
-- count(id): 聚簇主键索引，包含所有的数据。占用空间大，扫描慢
-- count(name): 二级索引包含数据少，占用空间小，扫描快
-- count(1): 遍历比较小的索引树，不会取数据。比较快一点。count(name)会取出name数据


select id
from goods
where id > 1700000
limit 10;



-- ========================= count优化

-- myisam: 保存总记录数
-- myisam: 性能高 select count(*) from myisam_table;

-- myisam 不支持事务
-- innodb 支持事务


-- innodb count(*)优化
show table status like 'goods';

-- redis: incr decr, 通过redis维护数据表总记录数。如果redis挂了存在分布式事务问题
-- 增加计数表: MySQL可以设置钩子函数