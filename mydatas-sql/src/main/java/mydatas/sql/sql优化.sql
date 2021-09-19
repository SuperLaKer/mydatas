-- @formatter:off
use all_in_one;

-- =======================常见优化==========================
-- count(1)效率高


-- =======================in优化===========================
-- 使用索引和查询的数据量有关
explain select id from the_order where id in (1439276150587428899, 1439276150587428900, 1439279247705570321);
explain select id from the_order where id = 1439276150587428899 or id = 1439276150587428900 or id =1439279247705570321;


explain
select id from the_order where id = 1439279239381925917
    union select id from the_order where id = 1439279237179916312
    union select id from the_order where id = 1439279241705570336;





-- =======================in优化===========================
-- 优化
explain
select * from the_order as main_table
    inner join
(select id from the_order where id in (4, 5, 6, 7)) as less_table
    on less_table.id = main_table.id;

explain
select * from the_order where id in (4, 5, 6, 7);

-- @formatter:off

