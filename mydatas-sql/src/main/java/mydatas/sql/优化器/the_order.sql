-- @formatter:off

-- id是自增主键，
-- user_id和create_time是normal, user_id和product_id是normal
-- union_a和union_b是union_key

-- system > const(唯一索引一条结果) > eq_ref(const_join) > ref(命中索引，多条结果) > range(命中索引，范围查询) > index(全扫索引) > ALL(全表扫描)

insert into the_order (`id`, `is_deleted`, `user_id`) value   (1439107102869078018, 0, 634536);


-- =================== const  ===================
-- primary
explain select * from the_order where id = 1439279243970494487;
-- union
explain select id from the_order where union_a = 20356837 and union_b = 40713674;

-- =================== ref  ===================
-- user_id和product_id
explain select count(1) from the_order where user_id = 261324;

-- =================== range  ===================
explain select count(1) from the_order where id > 1439279243970494487 and id < 1439279243970494499;

-- =================== index  ===================
explain select count(user_id) from the_order;

-- =================== all  ===================
-- 慎用
explain select count(the_money) from the_order limit 10;
