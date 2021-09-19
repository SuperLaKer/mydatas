-- 17、21、

-- ===============查询“生物”课程比“物理”课程成绩高的所有学生的学号===================
-- score关联course
-- 一个临时表关联生物、一个临时表关联物理，临时表关联

select bo.student_id
from (select s.student_id as student_id, s.num as num
      from score s
               join course c on c.cid = s.course_id
      where c.cname = '生物') bo
         join
     (select s.student_id as student_id, s.num as num
      from score s
               join course c on c.cid = s.course_id
      where c.cname = '物理') ph
     on bo.num > ph.num
group by bo.student_id;

-- ===============查询平均成绩大于60分的同学的学号和平均成绩===================
select student_id, avg(num) as 'avg_num'
from score
group by sid
having avg(num) > 60;


-- ===============查询所有同学的学号、姓名、选课数、总成绩===================
-- 选课数count(sid)、总成绩(total(num))
select s.sid, s.sname, count(s.sid), sum(sc.num)
from score sc
         join student s on sc.student_id = s.sid
group by s.sid;
-- ===============查询姓“李”的老师的个数===================
-- "_"一个字符，"%"0个或多个字符，"[  ]"枚举，"[^]"不在次枚举中的
select *
from teacher
where tname like '李%';


-- ===============查询没学过“李平”老师课的同学的学号、姓名；===================
-- not in 李平老师的课程:
select cid
from course
         join teacher t on t.tid = course.teacher_id
where tname = '李平老师';

select *
from score
where student_id not in
      (select student_id
       from score
       where course_id in
             (select cid
              from course
                       join teacher t on t.tid = course.teacher_id
              where tname = '李平老师')
       group by student_id);


-- ===============查询学过“001”并且也学过编号“002”课程的同学的学号、姓名===================
--
select student_id
from score
where course_id in (1, 2)
group by student_id
having count(student_id) >= 2;

-- ===============查询学过“叶平”老师所教的所有课的同学的学号、姓名===================
-- in (叶平，count(xx))
select student_id
from score
where course_id in (1, 2)
group by student_id
having count(student_id) >= 2;

-- ===============9、查询课程编号“002”的成绩比课程编号“001”课程低的所有同学的学号、姓名===================
-- 第一题

-- ===============10、查询所有课程都成绩小于80分的同学的学号、姓名；===================
select student_id
from score
where student_id not in
      (select student_id from score where num > 80);

-- ===============11、查询没有学全所有课的同学的学号、姓名；===================

select student_id
from score
group by student_id
having count(student_id) < (select count(1) from course);

-- ===============12、查询至少有一门课与学号为“001”的同学所学相同的同学的学号和姓名；===================
-- in 001()


-- ===============13、查询至少学过学号为“001”同学所有课的其他同学学号和姓名；===================

select student_id
from score
where course_id in (select course_id from score where student_id = 1)
  and student_id != 1
group by student_id
having count(course_id) >= (select count(1) from score where student_id = 1);


-- ===============14、查询和“002”号的同学学习的课程完全相同的其他同学学号和姓名；===================
-- 13 =

-- ===============15、删除学习“叶平”老师课的score表记录；===================
-- delete from score where course_id in

-- ===============16、向temp表中插入一些记录，这些记录要求符合以下条件：
-- ①没有上过编号“002”课程的同学学号；②插入“002”号课程的平均成绩；===================
-- 学号和平均成绩
-- insert into temp (sid, sname, c2_avg) value
select sid, sname, (select avg(num) from score where course_id = 2) as c2_avg
from student
where sid not in (select student_id from score where course_id = 2);


-- ===============17、按平均成绩从低到高 显示所有学生的“语文”、“数学”、“英语”三门的课程成绩，
-- 按如下形式显示： 学生ID,语文,数学,英语,有效课程数,有效平均分；======================

select student_id,
       max(IF(course_id = 1, num, 0)) as '课程1', -- group by 之后求max
       max(IF(course_id = 2, num, 0)) as '课程2',
       max(IF(course_id = 3, num, 0)) as '课程3',
       max(IF(course_id = 4, num, 0)) as '课程4',
       count(student_id)              as "选课数",
       avg(num)                       as "平均分"
from score
group by student_id
order by avg(num);


-- ===============18、查询各科成绩最高和最低的分：以如下形式显示：课程ID，最高分，最低分；

select course_id, max(num) as '最高分', min(num) as '最低分'
from score
group by course_id;


-- =====================19、按各科平均成绩从低到高和及格率的百分数从高到低顺序；==============================

-- 各科平均成绩、及格率
select course_id, avg(num) as avgnum, sum(IF(score.num > 60, 1, 0)) / count(1) * 100 as percent
from score
group by course_id
order by avgnum, percent desc;

-- =====================20、课程平均分从高到低显示（现实任课老师）；

select temp.avg_num, course_id, t.tname
from (select avg(num) as avg_num, course_id
      from score
      group by course_id) temp
         join course on temp.course_id = course.cid
         join teacher t on t.tid = course.teacher_id
order by temp.avg_num desc;


-- =====================21、查询各科成绩前三名的记录:(不考虑成绩并列情况)
select score.sid, score.course_id, score.num, T.first_num, T.second_num
from score
         left join
     (
         select sid,
                (select num
                 from score as s2
                 where s2.course_id = s1.course_id
                 order by num desc
                 limit 0,1)                                                                                 as first_num,
                (select num
                 from score as s2
                 where s2.course_id = s1.course_id
                 order by num desc
                 limit 3,1)                                                                                 as second_num
         from score as s1
     ) as T
     on score.sid = T.sid
where score.num <= T.first_num
  and score.num >= T.second_num;




-- =====================22、查询每门课程被选修的学生数=============
-- 课程1 课程2 课程3 课程4
select course_id, count(course_id)
from score
group by course_id;

-- ==============23、查询出只选修了一门课程的全部学生的学号和姓名；

select student_id
from score
group by student_id
having count(student_id) = 1;

-- ==============24、查询男生、女生的人数；===================

select gender, count(gender)
from student
group by student.gender;

-- ==============27、查询每门课程的平均成绩，
-- 结果按平均成绩升序排列，平均成绩相同时，按课程号降序排列；===================
select avg(num), course_id
from score
group by course_id;

-- ===========28、查询平均成绩大于85的所有学生的学号、姓名和平均成绩；=========

select student_id, avg(num)
from score group by student_id
having avg(num) > 85;

-- ===========29、查询课程名称为“数学”，且分数低于60的学生姓名和分数===========

