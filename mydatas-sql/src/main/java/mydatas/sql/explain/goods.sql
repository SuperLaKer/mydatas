CREATE TABLE `goods`
(
    `id`          bigint(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
    `status`      int(1)                       DEFAULT 1,
    `goods_num`   int(5)                       DEFAULT '1000' COMMENT '库存',
    `create_time` datetime            NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '上架时间',

    `goods_name`  varchar(200)                 DEFAULT NULL COMMENT '商品名称',
    `goods_desc`  varchar(300)                 DEFAULT NULL COMMENT '商品描述',
    `price`       varchar(15)                  DEFAULT '39.99' COMMENT '价格',
    `version`     int(5) unsigned              DEFAULT '0' COMMENT '版本号（玩具一样的版本控制）',
    PRIMARY KEY (`id`),
    KEY `idx_status_num` (`status`, `goods_num`, `create_time`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 0
  DEFAULT CHARSET = utf8;

insert into goods (status, goods_num, create_time, goods_name, goods_desc, price, version)
    value (1, 1000, '2019-08-12 12:30:21', '图书1', '图书描述', '36.99', 100);