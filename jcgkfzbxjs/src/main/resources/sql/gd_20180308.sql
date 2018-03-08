# Host: localhost  (Version 5.7.17-log)
# Date: 2018-03-08 10:01:22
# Generator: MySQL-Front 6.0  (Build 1.181)


#
# Structure for table "gd_parameter_smo"
#

DROP TABLE IF EXISTS `gd_parameter_smo`;
CREATE TABLE `gd_parameter_smo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_no` varchar(50) NOT NULL DEFAULT '' COMMENT '学号或工号',
  `mask_dimension` double(24,10) DEFAULT NULL,
  `pixel_size` double(24,10) DEFAULT NULL,
  `reflect` double(24,10) DEFAULT NULL,
  `absorb` double(24,10) DEFAULT NULL,
  `shadow_near` double(24,10) DEFAULT NULL,
  `shadow_far` double(24,10) DEFAULT NULL,
  `wavelength` double(24,10) DEFAULT NULL,
  `sigma_in` double(24,10) DEFAULT NULL,
  `sigma_out` double(24,10) DEFAULT NULL,
  `tis` double(24,10) DEFAULT NULL,
  `na` double(24,10) DEFAULT NULL,
  `ratio` double(24,10) DEFAULT NULL,
  `step_source` double(24,10) DEFAULT NULL,
  `omega_source_qua` double(24,10) DEFAULT NULL,
  `step_mask_main` double(24,10) DEFAULT NULL,
  `step_mask_sraf` double(24,10) DEFAULT NULL,
  `omega_mask_qua` double(24,10) DEFAULT NULL,
  `maxloop_smo` double(24,10) DEFAULT NULL,
  `threshold` double(24,10) DEFAULT NULL,
  `tr` double(24,10) DEFAULT NULL,
  `a_source` double(24,10) DEFAULT NULL,
  `core_num` double(24,10) DEFAULT NULL,
  `input_mask` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COMMENT='SMO模块的仿真参数';

#
# Data for table "gd_parameter_smo"
#

INSERT INTO `gd_parameter_smo` VALUES (10,'2120160998',201.0000000000,11.0000000000,0.8000000000,0.0707000000,1.4240000000,1.8350000000,13.5000000000,0.2400000000,0.8400000000,0.1690000000,0.2500000000,4.0000000000,0.0300000000,0.0010000000,0.1000000000,0.1000000000,0.0005000000,1.0000000000,100.0000000000,0.2500000000,25.0000000000,4.0000000000,'E:/ztest/target4','2018-03-07 17:15:27','2018-03-07 17:15:27'),(11,'2120160998',201.0000000000,11.0000000000,0.8000000000,0.0707100000,1.4240000000,1.8350000000,13.5000000000,0.2400000000,0.8400000000,0.1690000000,0.2500000000,4.0000000000,0.0300000000,0.0010000000,0.1000000000,0.1000000000,0.0005000000,1.0000000000,100.0000000000,0.2500000000,25.0000000000,4.0000000000,'E:/ztest/target4','2018-03-07 19:18:25','2018-03-07 19:18:25');

#
# Structure for table "gd_permission"
#

DROP TABLE IF EXISTS `gd_permission`;
CREATE TABLE `gd_permission` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `permission_name` varchar(50) NOT NULL DEFAULT '' COMMENT '权限名字',
  `admin_name` varchar(50) DEFAULT NULL COMMENT '创建者姓名',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "gd_permission"
#


#
# Structure for table "gd_r_role_permission"
#

DROP TABLE IF EXISTS `gd_r_role_permission`;
CREATE TABLE `gd_r_role_permission` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL COMMENT '角色表id',
  `permission_id` int(11) NOT NULL COMMENT '权限表id',
  `admin_name` varchar(50) DEFAULT NULL COMMENT '创建者姓名',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` int(11) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

#
# Data for table "gd_r_role_permission"
#


#
# Structure for table "gd_r_user_role"
#

DROP TABLE IF EXISTS `gd_r_user_role`;
CREATE TABLE `gd_r_user_role` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户表id',
  `role_id` int(11) NOT NULL COMMENT '角色表id',
  `admin_name` varchar(50) DEFAULT NULL COMMENT '创建者姓名',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

#
# Data for table "gd_r_user_role"
#

INSERT INTO `gd_r_user_role` VALUES (1,1,1,'侯豪',NULL,NULL),(2,1,2,NULL,NULL,NULL),(3,1,3,NULL,NULL,NULL),(4,1,4,NULL,NULL,NULL),(5,19,1,NULL,NULL,NULL),(15,6,5,'张晔','2018-03-05 14:15:41','2018-03-05 14:15:41'),(16,7,3,'张晔','2018-03-05 14:42:10','2018-03-05 14:42:10'),(17,8,3,'张晔','2018-03-05 14:42:31','2018-03-05 14:42:31'),(20,23,3,'张晔','2018-03-05 14:48:45','2018-03-05 14:48:45'),(21,6,3,'张晔','2018-03-05 20:24:07','2018-03-05 20:24:07'),(22,29,3,'张晔','2018-03-05 20:47:14','2018-03-05 20:47:14'),(23,29,4,'张晔','2018-03-05 20:47:15','2018-03-05 20:47:15'),(24,25,3,'张晔','2018-03-05 20:59:45','2018-03-05 20:59:45'),(25,17,3,'张晔','2018-03-05 20:59:59','2018-03-05 20:59:59'),(26,21,3,'张晔','2018-03-06 10:24:54','2018-03-06 10:24:54'),(30,42,6,'侯豪','2018-03-06 12:38:24','2018-03-06 12:38:24'),(31,42,5,'侯豪','2018-03-06 12:39:09','2018-03-06 12:39:09');

#
# Structure for table "gd_role"
#

DROP TABLE IF EXISTS `gd_role`;
CREATE TABLE `gd_role` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `role_name` varchar(50) NOT NULL DEFAULT '' COMMENT '角色名字',
  `admin_name` varchar(50) DEFAULT NULL COMMENT '角色创建者姓名',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

#
# Data for table "gd_role"
#

INSERT INTO `gd_role` VALUES (1,'admin','侯豪',NULL,NULL),(2,'student','侯豪',NULL,NULL),(3,'smo',NULL,NULL,NULL),(4,'opc',NULL,NULL,NULL),(5,'smpwo',NULL,NULL,NULL),(6,'pdod',NULL,NULL,NULL),(7,'test','侯豪','2018-03-04 20:47:49','2018-03-04 20:47:49');

#
# Structure for table "gd_user"
#

DROP TABLE IF EXISTS `gd_user`;
CREATE TABLE `gd_user` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `user_no` varchar(50) NOT NULL DEFAULT '' COMMENT '用户学号或工号，用于登录',
  `password` varchar(50) NOT NULL DEFAULT '' COMMENT '用户密码，MD5加密',
  `name` varchar(50) DEFAULT NULL COMMENT '用户姓名',
  `phone` varchar(20) DEFAULT NULL COMMENT '用户手机号',
  `status` int(1) DEFAULT '0' COMMENT '0为可用，1为不可用',
  `admin_name` varchar(50) DEFAULT NULL COMMENT '添加该账户的管理员姓名',
  `crate_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;

#
# Data for table "gd_user"
#

INSERT INTO `gd_user` VALUES (1,'2120160998','923195b5aa99a6cfd3e26d855ddf34c3','侯豪','15901038182',0,'侯豪',NULL,'2018-03-07 13:33:31'),(6,'2120160999','cd97118633e95914962d0f55ea4982e5','测试2','15901038183',0,'侯豪','2018-03-02 18:03:32','2018-03-05 20:57:17'),(7,'2120160997','ebd58bdd49d79819515cfac330e2ae07','测试','15901038182',0,'侯豪','2018-03-02 18:03:54','2018-03-07 19:16:21'),(8,'2120160996','d65dbf135a3845dd08307dff79b2dedf','名字已修改+3','09090',0,'侯豪','2018-03-04 13:24:00','2018-03-07 19:16:23'),(15,'21213','c31a9c4dd907cf13cb2ddeefcff8b118','测试','15901038182',0,'侯豪','2018-03-04 19:18:58','2018-03-05 13:20:13'),(19,'111111','8184bc398a289a2a32fdd33ed916d2f7','张晔','13345816016',0,'侯豪','2018-03-05 10:11:44','2018-03-05 22:46:32'),(21,'2222222','27fa36bc820b17f05e71d44fe9e8ed28','q','123',1,'张晔','2018-03-05 14:10:27','2018-03-05 15:49:11'),(23,'888888','3e31c63723d921771429eef05e37719d','冻结了？','13867827777',0,'张晔','2018-03-05 14:47:34','2018-03-06 10:25:04'),(24,'777777','78fcaaa9238afc27e9da99b942a0037a','','13377777777',0,'张晔','2018-03-05 15:11:26','2018-03-05 15:11:26'),(25,'777774','d1fe41bb33a29d5cfc727f72fd35ba42','','13377777777',0,'张晔','2018-03-05 15:11:38','2018-03-05 15:11:38'),(27,'121212','12faa47f58a2535bed26b3309cfdaf5b','21','13234567890',0,'张晔','2018-03-05 15:19:25','2018-03-06 10:22:19'),(28,'444444','b06ca61c9adf41571e501df8351822ee',NULL,'13345678908',0,'张晔','2018-03-05 15:21:30','2018-03-05 15:21:30'),(29,'2220160888','0113ae57d77d09e52e39abe0851749a2',NULL,NULL,0,'张晔','2018-03-05 20:47:08','2018-03-05 20:47:08'),(30,'999999','42277b147686fc5efde7c0dd5328c0ab','上帝说要有名字',NULL,0,'张晔','2018-03-06 10:25:49','2018-03-06 10:25:49'),(31,'101','3649efe52eb931a4f991a32285396f1e','哈哈哈',NULL,1,'侯豪','2018-03-06 10:32:22','2018-03-07 19:16:16'),(32,'102','ac582188016a6f204e2dbe15c47f5715','呵呵呵',NULL,0,'侯豪','2018-03-06 10:32:31','2018-03-06 10:32:31'),(33,'103','6fc69d6ada83f62fb4abccb2f7c4a482','嘻嘻嘻',NULL,0,'侯豪','2018-03-06 10:32:44','2018-03-06 10:32:44'),(34,'104','5734f7bcd2d3a80f1facf429064d914a','干活回个话',NULL,0,'侯豪','2018-03-06 10:33:40','2018-03-06 10:33:40'),(35,'105','0dde6228c9f8d6e7bed69ebd7b37589c','阿斯蒂芬',NULL,0,'侯豪','2018-03-06 10:38:28','2018-03-06 10:38:28'),(36,'106','064049a3e989c3c31f8e4aa89f1fec12','阿斯顿发生大法师打发点',NULL,0,'侯豪','2018-03-06 10:38:35','2018-03-06 10:38:35'),(37,'107','f16055caba76c72c6bc903795cd3558e','翻翻',NULL,0,'侯豪','2018-03-06 10:38:47','2018-03-06 10:38:47'),(38,'108','f1c1ac7614b061fc6e9dc4947e0182d7','框架',NULL,0,'侯豪','2018-03-06 10:38:54','2018-03-06 10:38:54'),(39,'109','f0fc8dde3058a078cf183e58baf3e62f','狂欢购',NULL,0,'侯豪','2018-03-06 10:39:10','2018-03-06 10:39:10'),(40,'110','2f698bf742f19d1c59e88765529a1f28','火锅',NULL,0,'侯豪','2018-03-06 10:39:33','2018-03-06 10:39:33'),(41,'111','674a856502389b44c68db8613bdbc21e','新时代',NULL,0,'侯豪','2018-03-06 12:27:58','2018-03-06 12:27:58'),(42,'112','90fd0d872137c70005abd11bda601722','加班','15901038182',0,'侯豪','2018-03-06 12:28:25','2018-03-06 12:58:47');
