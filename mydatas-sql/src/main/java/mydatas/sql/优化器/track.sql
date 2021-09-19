set session optimizer_trace="enabled=on",end_markers_in_json=on; -- 开启trace

select id from the_order limit 5000,10;
select id from the_order where id = 1439276150587428865;
SELECT trace FROM information_schema.OPTIMIZER_TRACE;


explain select id from the_order limit 1;
explain select id from the_order where id = 1439276150587428865;
select id from the_order limit 5000,10;
select id from the_order where id = 1439276150587428865;