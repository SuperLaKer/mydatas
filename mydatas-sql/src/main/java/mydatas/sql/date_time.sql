use slkdatabase;
CREATE TABLE `time_date`
(
    `id`            bigint(11)     NOT NULL AUTO_INCREMENT COMMENT '主键',
    `the_date`      date           NULL COMMENT 'field_date',
    `the_datetime`  datetime       NULL COMMENT 'field_datetime',
    `the_time`      time           NULL COMMENT 'field_time',
    `the_timestamp` timestamp      NULL COMMENT 'field_timestamp',
    `temp_a_string` varchar(20)    NULL COMMENT '保留字段1',
    `temp_b_string` varchar(20)    NULL COMMENT '保留字段2',
    `status`        enum ('1','0') NULL COMMENT '是否启用',
    PRIMARY KEY (`id`)
);

