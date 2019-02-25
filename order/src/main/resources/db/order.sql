-- ORDER_MASTER
create table  `ORDER_MASTER`
(
       `ORDER_ID`        INT auto_increment primary key not null comment '唯一标识',
       `CREATE_TIME`     DATETIME comment '创建时间',
       `UPDATE_TIME`     DATETIME comment '修改时间',
       `ORDER_STATUS`    TINYINT default 0 not null comment '订单状态,默认为新下单 支付状态,默认未支付',
       `PAY_STATUS`      TINYINT default 0 not null comment '支付状态,默认未支付',
       `ORDER_AMOUNT`    DECIMAL(8,2) comment '订单总金额',
       `BUYER_NAME`      VARCHAR(32) comment '买家名字',
       `BUYER_PHONE`     VARCHAR(32) comment '买家电话',
       `BUYER_ADDRESS`   VARCHAR(128) comment '买家地址',
       `BUYER_OPENID`    VARCHAR(64) comment '买家微信openid'
);
alter table `ORDER_MASTER` comment= '订单信息表';

-- ORDER_DETAIL
create table  `ORDER_DETAIL`
(
       `DETAIL_ID`       INT auto_increment primary key not null comment '唯一标识',
       `ORDER_ID`        INT comment '操作人ID',
       `PRODUCT_ID`      INT comment '创建人ID',
       `CREATE_TIME`     DATETIME comment '创建时间',
       `UPDATE_TIME`     DATETIME comment '失效时间',
       `PRODUCT_QUANTITY` INT comment '数量',
       `PRODUCT_PRICE`   DECIMAL(8,2) not null comment '当前价格',
       `PRODUCT_NAME`    VARCHAR(64) not null comment '商品名字',
       `PRODUCT_ICON`    VARCHAR(512) comment '小图'
);
alter table `ORDER_DETAIL` comment= '订单商品';


