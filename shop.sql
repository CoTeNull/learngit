/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1_3306
Source Server Version : 50714
Source Host           : 127.0.0.1:3306
Source Database       : shop

Target Server Type    : MYSQL
Target Server Version : 50714
File Encoding         : 65001

Date: 2018-06-08 12:01:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for commodity
-- ----------------------------
DROP TABLE IF EXISTS `commodity`;
CREATE TABLE `commodity` (
  `commodity_id` int(11) NOT NULL AUTO_INCREMENT,
  `commodity_img` varchar(255) DEFAULT NULL,
  `commodity_name` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `commodity_prives` double DEFAULT NULL,
  `commodity_message` text CHARACTER SET utf8,
  `commodity_master_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`commodity_id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of commodity
-- ----------------------------
INSERT INTO `commodity` VALUES ('1', 'img/commoditys/zxyw1.jpg', '限定款连衣裙', '210.5', '走过路过千万不要错过，限定款连衣裙，穿出你的自信。我们的格言是——跳蚤市场，你本来就是跳蚤！', '1');
INSERT INTO `commodity` VALUES ('2', 'img/commoditys/zxyw1.jpg', '限定款连衣裙', '210.5', '走过路过千万不要错过，限定款连衣裙，穿出你的自信。我们的格言是——跳蚤市场，你本来就是跳蚤！', '1');
INSERT INTO `commodity` VALUES ('3', 'img/commoditys/zxyw1.jpg', '限定款连衣裙', '210.5', '走过路过千万不要错过，限定款连衣裙，穿出你的自信。我们的格言是——跳蚤市场，你本来就是跳蚤！', '1');
INSERT INTO `commodity` VALUES ('4', 'img/commoditys/zxyw1.jpg', '限定款连衣裙', '210.5', '走过路过千万不要错过，限定款连衣裙，穿出你的自信。我们的格言是——跳蚤市场，你本来就是跳蚤！', '1');
INSERT INTO `commodity` VALUES ('5', 'img/commoditys/zxyw1.jpg', '限定款连衣裙', '210.5', '走过路过千万不要错过，限定款连衣裙，穿出你的自信。我们的格言是——跳蚤市场，你本来就是跳蚤！', '1');
INSERT INTO `commodity` VALUES ('6', 'img/commoditys/zxyw1.jpg', '限定款连衣裙', '210.5', '走过路过千万不要错过，限定款连衣裙，穿出你的自信。我们的格言是——跳蚤市场，你本来就是跳蚤！', '1');
INSERT INTO `commodity` VALUES ('7', 'img/commoditys/cool4.jpg', '出现了测试衣物', '999.99', '跳蚤君专用衣服强的一批', '1');

-- ----------------------------
-- Table structure for deal
-- ----------------------------
DROP TABLE IF EXISTS `deal`;
CREATE TABLE `deal` (
  `deal_id` int(11) NOT NULL AUTO_INCREMENT,
  `deal_user_id` int(11) DEFAULT NULL,
  `deal_shop_id` int(11) DEFAULT NULL,
  `deal_time` datetime DEFAULT NULL,
  `deal_code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`deal_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of deal
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) DEFAULT NULL,
  `user_password` varchar(255) DEFAULT NULL,
  `user_nc` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `user_img` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=27 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', '123456', 'CoTe_Test', '/img/userImg/af1ee9c9-3336-473c-838d-39a542c27d98.jpg');
