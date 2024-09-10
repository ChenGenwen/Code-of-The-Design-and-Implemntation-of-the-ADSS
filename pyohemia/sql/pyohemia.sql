/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MariaDB
 Source Server Version : 100121
 Source Host           : localhost:3306
 Source Schema         : pyohemia

 Target Server Type    : MariaDB
 Target Server Version : 100121
 File Encoding         : 65001

 Date: 29/04/2022 16:43:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for authentication
-- ----------------------------
DROP TABLE IF EXISTS `authentication`;
CREATE TABLE `authentication`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of authentication
-- ----------------------------
INSERT INTO `authentication` VALUES (1, '1', 'Admin');
INSERT INTO `authentication` VALUES (2, '2', 'DOCTOR');

-- ----------------------------
-- Table structure for cases
-- ----------------------------
DROP TABLE IF EXISTS `cases`;
CREATE TABLE `cases`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `case_number` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `patient_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `admission_time` date NULL DEFAULT NULL,
  `admission_symptom` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pasthistory` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `allergy` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for duty
-- ----------------------------
DROP TABLE IF EXISTS `duty`;
CREATE TABLE `duty`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `duty_time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `duty_doctor` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `sort` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 86 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of duty
-- ----------------------------
INSERT INTO `duty` VALUES (79, '周一上午', '张医生', '1', '无', '0');
INSERT INTO `duty` VALUES (80, '周一下午', '王医生', '2', NULL, '0');
INSERT INTO `duty` VALUES (81, '周二上午', '李医生', '3', NULL, '0');
INSERT INTO `duty` VALUES (82, '周二下午', '张医生', '4', '', '0');
INSERT INTO `duty` VALUES (83, '周三上午', '李医生', '5', '', '0');
INSERT INTO `duty` VALUES (84, '周三下午', '王医生', '6', '', '0');
INSERT INTO `duty` VALUES (85, '周四上午', '王医生', '7', '空闲', '1');

-- ----------------------------
-- Table structure for inspect
-- ----------------------------
DROP TABLE IF EXISTS `inspect`;
CREATE TABLE `inspect`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `patient_id` int(11) NULL DEFAULT NULL,
  `mean_arterial_pressure` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dopamine_dose` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `adrenaline_dose` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `norepinephrine_dose` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dobutamine` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `paoz` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `breathing` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `creatinine` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `urinevolume` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `bilirubin` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `blood_platelet` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gcs` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `overall_score` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `case_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_date` datetime(0) NULL DEFAULT NULL,
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of inspect
-- ----------------------------
INSERT INTO `inspect` VALUES (1, 79, '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '2022-04-27 00:00:00', '1', '0');
INSERT INTO `inspect` VALUES (2, 80, '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '2022-04-27 00:00:00', '1', '0');
INSERT INTO `inspect` VALUES (3, 81, '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '2022-04-27 00:00:00', '1', '0');
INSERT INTO `inspect` VALUES (4, 1, '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '2022-04-27 01:59:01', '1', '0');
INSERT INTO `inspect` VALUES (5, 1, '1', '1', '1', '1', '1', '1', '', '', '', '', '', NULL, NULL, NULL, '2022-04-27 19:09:45', NULL, '0');
INSERT INTO `inspect` VALUES (6, 1, '2', '2', '2', '2', NULL, '1', '2', '2', '2', '2', '2', NULL, NULL, NULL, '2022-04-27 19:10:00', NULL, '0');
INSERT INTO `inspect` VALUES (7, 1, '3', '3', '3', '3', '3', NULL, '3', '3', '3', '3', '3', NULL, NULL, NULL, '2022-04-27 19:13:27', NULL, '0');
INSERT INTO `inspect` VALUES (8, 1, '3', '2', '3', '2', '4', '3', '4', '5', '6', '2', '4', NULL, NULL, NULL, '2022-04-27 19:14:28', NULL, '0');

-- ----------------------------
-- Table structure for patient
-- ----------------------------
DROP TABLE IF EXISTS `patient`;
CREATE TABLE `patient`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '患者编号',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '姓名',
  `age` int(11) NULL DEFAULT NULL,
  `gender` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `occupation` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职业',
  `phone` bigint(11) NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `nativeplace` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '籍贯',
  `create_date` date NULL DEFAULT NULL,
  `create_by` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主治医师',
  `complaint` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '主诉',
  `admission_time` datetime(0) NOT NULL COMMENT '入院时间（时分秒）',
  `admission_symptom` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '入院症状',
  `pasthistory` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '既往史',
  `allergy` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '过敏史',
  `flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 82 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of patient
-- ----------------------------
INSERT INTO `patient` VALUES (1, '张怀强', 24, '男', '教师', 13900008888, '天津市东丽区', '天津市东丽区', '2022-04-26', '张医生', '头晕', '2022-04-27 00:00:00', '头晕', '头晕', '无', '0');
INSERT INTO `patient` VALUES (79, '陈娜', 30, '女', '教师', 13900008888, '天津市东丽区', '天津市东丽区', '2022-04-26', '王医生', '头晕', '2022-04-27 00:00:00', '头晕', '头晕', '无', '0');
INSERT INTO `patient` VALUES (80, 'jing ye', 12, '男', '学生', 15777773333, '北京市东城区', '北京市东城区', '2022-04-26', '王医生', '头晕', '2022-04-27 00:00:00', '头晕', '头晕', '无', '0');
INSERT INTO `patient` VALUES (81, '陈海拿', 12, '男', '学生', 15777773333, '北京市朝阳区', '北京市朝阳区', '2022-04-26', '王医生', '头晕', '2022-04-27 11:44:00', '头晕', '头晕', '无', '0');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `department` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age` int(11) NULL DEFAULT NULL,
  `gender` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `phone` bigint(11) NULL DEFAULT NULL,
  `create_date` date NULL DEFAULT NULL,
  `flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '1', 24, '男', '北京市', '主任医师', 'admin', '$2a$10$45kMr0J9m./Sq6zNUiFDEeVfm0vfHwwtnjmVJ.nv9OSp1JJRkViMO', 13800007786, '2022-04-21', '0');
INSERT INTO `user` VALUES (2, '张医生', '外科', 24, '男', '北京市', '主任医师', 'zhang', '$2a$10$45kMr0J9m./Sq6zNUiFDEeVfm0vfHwwtnjmVJ.nv9OSp1JJRkViMO', 13800007786, '2022-04-21', '0');
INSERT INTO `user` VALUES (3, '李娜', '外科', 27, '女', NULL, '主治医师', 'lina', '$2a$10$h.vNyReTlSH.wS1IEdcDUerlyN838hadscjuej3byjjyRnlLlK1C2', 15777773333, '2022-04-29', '0');

-- ----------------------------
-- Table structure for user_authorities
-- ----------------------------
DROP TABLE IF EXISTS `user_authorities`;
CREATE TABLE `user_authorities`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `authentication_id` int(11) NULL DEFAULT NULL,
  `user_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of user_authorities
-- ----------------------------
INSERT INTO `user_authorities` VALUES (1, 1, 1);
INSERT INTO `user_authorities` VALUES (2, 2, 2);
INSERT INTO `user_authorities` VALUES (3, 2, 3);

SET FOREIGN_KEY_CHECKS = 1;
