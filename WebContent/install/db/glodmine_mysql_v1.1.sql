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
