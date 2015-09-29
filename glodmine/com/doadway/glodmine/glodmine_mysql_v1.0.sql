/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50022
Source Host           : localhost:3306
Source Database       : glodmine

Target Server Type    : MYSQL
Target Server Version : 50022
File Encoding         : 65001

Date: 2015-09-29 23:31:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `income`
-- ----------------------------
DROP TABLE IF EXISTS `income`;
CREATE TABLE `income` (
  `id` int(11) NOT NULL,
  `idate` datetime default NULL,
  `isource` int(11) default NULL,
  `imoney` float default NULL,
  `imomo` varchar(50) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of income
-- ----------------------------

-- ----------------------------
-- Table structure for `income_type`
-- ----------------------------
DROP TABLE IF EXISTS `income_type`;
CREATE TABLE `income_type` (
  `id` int(11) NOT NULL,
  `icategory` varchar(8) default NULL,
  `says` varchar(50) default NULL,
  `member_id` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of income_type
-- ----------------------------

-- ----------------------------
-- Table structure for `member`
-- ----------------------------
DROP TABLE IF EXISTS `member`;
CREATE TABLE `member` (
  `id` int(11) NOT NULL,
  `nice_name` varchar(10) default NULL,
  `gender` int(11) default NULL,
  `real_name` varchar(0) default NULL,
  `brithday` date default NULL,
  `mobile_phone` varchar(20) default NULL,
  `email` varchar(50) default NULL,
  `password` varchar(20) default NULL,
  `status` int(11) default NULL,
  `update_time` datetime default NULL,
  `register_time` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of member
-- ----------------------------

-- ----------------------------
-- Table structure for `speed`
-- ----------------------------
DROP TABLE IF EXISTS `speed`;
CREATE TABLE `speed` (
  `id` int(11) NOT NULL,
  `idate` datetime default NULL,
  `isource` int(11) default NULL,
  `imoney` float default NULL,
  `imomo` varchar(50) default NULL,
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
  `id` int(11) NOT NULL,
  `icategory` varchar(8) default NULL,
  `says` varchar(50) default NULL,
  `member_id` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of speed_type
-- ----------------------------
