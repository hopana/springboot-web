/*
SQLyog Ultimate v12.5.0 (64 bit)
MySQL - 5.7.21 : Database - test
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`test` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `test`;

/*Table structure for table `goods` */

DROP TABLE IF EXISTS `goods`;

CREATE TABLE `goods` (
  `goods_code` char(32) NOT NULL COMMENT '商品编码 ',
  `goods_name` varchar(100) DEFAULT NULL COMMENT '商品名',
  PRIMARY KEY (`goods_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='商品';

/*Data for the table `goods` */

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `name` varchar(20) NOT NULL COMMENT '角色名称',
  `code` varchar(20) NOT NULL COMMENT '角色代码',
  `parent_code` varchar(20) NOT NULL COMMENT '父角色代码',
  `desc` varchar(50) DEFAULT NULL COMMENT '角色描述',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `role` */

/*Table structure for table `sms` */

DROP TABLE IF EXISTS `sms`;

CREATE TABLE `sms` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `mobile` char(11) NOT NULL COMMENT '发送手机号',
  `content` varchar(50) NOT NULL COMMENT '发送内容',
  `create_time` datetime NOT NULL COMMENT '发送时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

/*Data for the table `sms` */

/*Table structure for table `stock_io` */

DROP TABLE IF EXISTS `stock_io`;

CREATE TABLE `stock_io` (
  `io_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `location_code` char(32) NOT NULL COMMENT '库位编码',
  `goods_code` char(32) NOT NULL COMMENT '商品编码',
  `pack_bcode` char(32) NOT NULL COMMENT '包裹条形码',
  `inout_flg` tinyint(4) NOT NULL COMMENT '1:入库 -1:出库',
  `qty` decimal(18,4) DEFAULT '0.0000' COMMENT '数量',
  `qty_unit` char(32) DEFAULT NULL COMMENT '数量单位',
  `update_time` bigint(20) DEFAULT NULL COMMENT '更新时间(UTC毫秒)',
  PRIMARY KEY (`io_id`),
  KEY `IDX1` (`location_code`,`goods_code`,`pack_bcode`,`inout_flg`),
  KEY `IDX2` (`update_time`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1 COMMENT='出入库';

/*Data for the table `stock_io` */

insert  into `stock_io`(`io_id`,`location_code`,`goods_code`,`pack_bcode`,`inout_flg`,`qty`,`qty_unit`,`update_time`) values 
(1,'1001','g0011','p9001',1,20.0000,'?',20180205124108);

/*Table structure for table `stock_location` */

DROP TABLE IF EXISTS `stock_location`;

CREATE TABLE `stock_location` (
  `location_code` char(32) NOT NULL COMMENT '库位编号',
  `location_name` varchar(100) DEFAULT NULL COMMENT '库位名称',
  PRIMARY KEY (`location_code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='库位';

/*Data for the table `stock_location` */

/*Table structure for table `stock_status` */

DROP TABLE IF EXISTS `stock_status`;

CREATE TABLE `stock_status` (
  `location_code` char(32) NOT NULL COMMENT '库位编码',
  `goods_code` char(32) NOT NULL COMMENT '商品编码',
  `pack_bcode` char(32) NOT NULL COMMENT '包裹条形码',
  `qty` decimal(18,4) DEFAULT '0.0000' COMMENT '数量',
  `qty_unit` char(32) DEFAULT NULL COMMENT '数量单位',
  `update_time` bigint(20) DEFAULT NULL COMMENT '更新时间(UTC毫秒)',
  PRIMARY KEY (`location_code`,`goods_code`,`pack_bcode`),
  KEY `IDX1` (`update_time`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='库存';

/*Data for the table `stock_status` */

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `t_user` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `name` varchar(20) NOT NULL COMMENT '用户名',
  `age` int(3) NOT NULL COMMENT '年龄',
  `id_card` char(18) NOT NULL COMMENT '身份证号',
  `gender` tinyint(1) NOT NULL COMMENT '性别 1=男 2=女',
  `mobile` char(11) NOT NULL COMMENT '手机号',
  `address` varchar(50) NOT NULL COMMENT '地址',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
