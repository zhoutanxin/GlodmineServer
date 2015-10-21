/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50022
Source Host           : localhost:3306
Source Database       : glodmine

Target Server Type    : MYSQL
Target Server Version : 50022
File Encoding         : 65001

Date: 2015-10-21 11:35:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `income`
-- ----------------------------
DROP TABLE IF EXISTS `income`;
CREATE TABLE `income` (
  `id` int(11) NOT NULL auto_increment,
  `idate` datetime default NULL,
  `isource` int(11) default NULL,
  `imoney` float default NULL,
  `imemo` varchar(50) default NULL,
  `member_id` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of income
-- ----------------------------
INSERT INTO `income` VALUES ('1', '2015-10-19 09:21:19', '3', '3500', '工资发放', '5');
INSERT INTO `income` VALUES ('2', '2015-10-20 09:21:42', '1', '10000', '还款', '5');
INSERT INTO `income` VALUES ('3', '2015-10-19 09:34:11', '1', '500', '生活费', '5');
INSERT INTO `income` VALUES ('4', '2015-10-19 09:34:25', '3', '1000', '利息', '5');
INSERT INTO `income` VALUES ('5', '2015-10-19 09:35:17', '1', '100', '捡到的', '5');
INSERT INTO `income` VALUES ('6', '2015-10-19 09:36:29', '1', '100', '红包', '5');
INSERT INTO `income` VALUES ('7', '2015-10-20 09:32:01', '1', '121', 'dsda', null);
INSERT INTO `income` VALUES ('8', '2015-10-20 09:32:07', '1', '121', '1213', null);
INSERT INTO `income` VALUES ('9', '2015-10-20 09:32:11', '1', '32132', '321', null);
INSERT INTO `income` VALUES ('10', '2015-10-21 10:32:20', '1', '23', '1000', '5');

-- ----------------------------
-- Table structure for `income_type`
-- ----------------------------
DROP TABLE IF EXISTS `income_type`;
CREATE TABLE `income_type` (
  `id` int(11) NOT NULL auto_increment,
  `icategory` varchar(8) default NULL,
  `says` varchar(50) default NULL,
  `member_id` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of income_type
-- ----------------------------
INSERT INTO `income_type` VALUES ('1', '工资', '每月工资情况', '5');
INSERT INTO `income_type` VALUES ('2', '兼职', '其他收入来源', '5');
INSERT INTO `income_type` VALUES ('3', '股票基金', '投资收益', '5');

-- ----------------------------
-- Table structure for `member`
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member` (
  `id` int(11) NOT NULL auto_increment,
  `nice_name` varchar(10) default NULL,
  `gender` int(11) default NULL,
  `real_name` varchar(10) default NULL,
  `brithday` datetime default NULL,
  `mobile_phone` varchar(20) default NULL,
  `imail` varchar(50) default NULL,
  `password` varchar(20) default NULL,
  `status` int(11) default NULL,
  `update_time` datetime default NULL,
  `register_time` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of member
-- ----------------------------
INSERT INTO `member` VALUES ('5', '新新2', '2', '周新新', '2015-09-30 00:00:00', '13683027377', 'zhoutanxin@hotmail.com', 'A45115517', '1', '2015-10-20 14:45:36', '2015-10-13 10:01:04');

-- ----------------------------
-- Table structure for `speed`
-- ----------------------------
DROP TABLE IF EXISTS `speed`;
CREATE TABLE `speed` (
  `id` int(11) NOT NULL auto_increment,
  `idate` datetime default NULL,
  `isource` int(11) default NULL,
  `imoney` float default NULL,
  `imemo` varchar(50) default NULL,
  `member_id` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of speed
-- ----------------------------

-- ----------------------------
-- Table structure for `speed_type`
-- ----------------------------
DROP TABLE IF EXISTS `speed_type`;
CREATE TABLE `speed_type` (
  `id` int(11) NOT NULL auto_increment,
  `icategory` varchar(8) default NULL,
  `says` varchar(50) default NULL,
  `member_id` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of speed_type
-- ----------------------------
