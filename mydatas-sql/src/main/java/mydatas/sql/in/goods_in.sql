use sfnotes;
set global query_cache_size = 0;
set global query_cache_type = 0;
set optimizer_trace = "enabled=on";
select *
from goods
where id in (13563, 4864, 25425, 1587852, 2452, 1645374, 788793, 1345468, 1897637, 1120453);
select trace
from (SELECT * FROM information_schema.OPTIMIZER_TRACE) op_t;



set optimizer_trace = "enabled=off";


set optimizer_trace = "enabled=on";
set optimizer_trace = "enabled=off";
set optimizer_trace = "enabled=on";
select id
from goods
where id = 13563
union ALL
select id
from goods
where id = 4864
union ALL
select id
from goods
where id = 25425
union ALL
select id
from goods
where id = 1587432852
union ALL
select id
from goods
where id = 2452
union ALL
select id
from goods
where id = 1645374
union ALL
select id
from goods
where id = 788793
union ALL
select id
from goods
where id = 13454684798496
union ALL
select id
from goods
where id = 1897637
union ALL
select id
from goods
where id = 1120453;
select trace
from (SELECT * FROM information_schema.OPTIMIZER_TRACE) op_t;