use sfnotes;

explain
replace into goods (id, status)
values (1, 0),
       (2, 1),
       (15615346, 0);


explain
insert into goods (id, goods_name)
values (1, 'updated_name1'),
       (2, 'updated_name2'),
       (15164566, 'updated_name3')
on duplicate key update status = values(status);