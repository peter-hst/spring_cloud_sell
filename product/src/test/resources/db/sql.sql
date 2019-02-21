-- PRODUCT_CATEGORY
create table  `PRODUCT_CATEGORY`
(
       `CATEGORY_ID`     INT auto_increment primary key not null comment '唯一标识',
       `CREATE_TIME`     DATETIME comment '创建时间',
       `UPDATE_TIME`     DATETIME comment '修改时间',
       `CATEGORY_TYPE`   INT comment '类目类型',
       `CATEGORY_NAME`   VARCHAR(64) comment '类目名称'
);
alter table `PRODUCT_CATEGORY` comment= '商品类目';

-- PRODUCT_INFO
create table  `PRODUCT_INFO`
(
       `PRODUCT_ID`      INT auto_increment primary key not null comment '唯一标识',
       `CREATE_TIME`     DATETIME comment '创建时间',
       `UPDATE_TIME`     DATETIME comment '创建时间',
       `PRODUCT_PRICE`   DOUBLE comment '商品价格',
       `CATEGORY_TYPE`   INT comment '商品类目类别',
       `PRODUCT_STOCK`   INT comment '库存数量',
       `PRODUCT_STATUS`  INT comment '状态',
       `PRODUCT_NAME`    VARCHAR(64) comment '餐厅名称',
       `PRODUCT_DESCRIPTION` VARCHAR(256) comment '描述',
       `PRODUCT_ICON`    VARCHAR(256) comment '图标'
);
alter table `PRODUCT_INFO` comment= '商品信息表';

