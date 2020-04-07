create database sharding_rep;
create database sharding_rep_0;
create database sharding_rep_1;
create database sharding_rep_slave;

CREATE TABLE `sharding_rep`.`test_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) NOT NULL,
  `order_no` varchar(100) NOT NULL,
  `goods_id` bigint(20) NOT NULL,
  `goods_name` varchar(100) DEFAULT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10008 DEFAULT CHARSET=latin1 COMMENT='订单表';
CREATE TABLE `sharding_rep`.`worker` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `money` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

CREATE TABLE `sharding_rep_0`.`test_order_0` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) NOT NULL,
  `order_no` varchar(100) NOT NULL,
  `goods_id` bigint(20) NOT NULL,
  `goods_name` varchar(100) DEFAULT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10008 DEFAULT CHARSET=latin1 COMMENT='订单表';

CREATE TABLE `sharding_rep_1`.`test_order_1` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `order_id` bigint(20) NOT NULL,
  `order_no` varchar(100) NOT NULL,
  `goods_id` bigint(20) NOT NULL,
  `goods_name` varchar(100) DEFAULT NULL,
  `user_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10008 DEFAULT CHARSET=latin1 COMMENT='订单表';

CREATE TABLE `sharding_rep_slave`.`worker` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `money` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;