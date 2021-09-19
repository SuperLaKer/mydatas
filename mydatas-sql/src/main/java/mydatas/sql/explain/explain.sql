-- type列 system > const > eq_ref > ref > range > index > ALL
-- key 列 使用哪个索引

explain
select *
from actor;
explain
select *
from film
where name = 'film1';


-- limit
explain
select *
from goods
limit 6000, 10; -- 不走索引
explain
select *
from goods
where id > 6000
limit 10;



-- =============== union all

-- type == range,
set optimizer_trace = "enabled=on";
set optimizer_trace = "enabled=off";
select *
from goods
where id in (4536, 1685, 2, 9875, 15348);
select *
from information_schema.OPTIMIZER_TRACE;

