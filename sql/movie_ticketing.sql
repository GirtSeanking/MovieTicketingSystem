/*
 Navicat Premium Data Transfer

 Source Server         : GirtSeanking-MySQL
 Source Server Type    : MySQL
 Source Server Version : 80025
 Source Host           : localhost:3306
 Source Schema         : movie_ticketing

 Target Server Type    : MySQL
 Target Server Version : 80025
 File Encoding         : 65001

 Date: 29/06/2021 00:30:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for favorites
-- ----------------------------
DROP TABLE IF EXISTS `favorites`;
CREATE TABLE `favorites`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `login_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `movie_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of favorites
-- ----------------------------

-- ----------------------------
-- Table structure for hall
-- ----------------------------
DROP TABLE IF EXISTS `hall`;
CREATE TABLE `hall`  (
  `hall_id` int(0) NOT NULL AUTO_INCREMENT,
  `hall_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `capacity` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`hall_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of hall
-- ----------------------------
INSERT INTO `hall` VALUES (1, '1号观影厅', 60);
INSERT INTO `hall` VALUES (2, '2号观影厅', 60);
INSERT INTO `hall` VALUES (3, '3号观影厅', 55);

-- ----------------------------
-- Table structure for movie
-- ----------------------------
DROP TABLE IF EXISTS `movie`;
CREATE TABLE `movie`  (
  `movie_id` int(0) NOT NULL AUTO_INCREMENT,
  `movie_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `like_amount` bigint(0) NULL DEFAULT 0,
  PRIMARY KEY (`movie_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of movie
-- ----------------------------
INSERT INTO `movie` VALUES (1, '大话西游', 0);
INSERT INTO `movie` VALUES (2, '速度与激情7', 0);
INSERT INTO `movie` VALUES (3, '速度与激情8', 0);
INSERT INTO `movie` VALUES (4, '你的婚礼', 0);
INSERT INTO `movie` VALUES (5, '霸王别姬', 1);
INSERT INTO `movie` VALUES (6, '滚蛋吧！肿瘤君', 3);

-- ----------------------------
-- Table structure for movie_user
-- ----------------------------
DROP TABLE IF EXISTS `movie_user`;
CREATE TABLE `movie_user`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `movie_id` int(0) NULL DEFAULT NULL,
  `login_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of movie_user
-- ----------------------------
INSERT INTO `movie_user` VALUES (12, 6, '123');
INSERT INTO `movie_user` VALUES (13, 6, '111');
INSERT INTO `movie_user` VALUES (16, 6, '1233');
INSERT INTO `movie_user` VALUES (17, 5, '123');

-- ----------------------------
-- Table structure for orde
-- ----------------------------
DROP TABLE IF EXISTS `orde`;
CREATE TABLE `orde`  (
  `orde_id` int(0) NOT NULL AUTO_INCREMENT,
  `ticket_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `buy_time` datetime(0) NULL DEFAULT NULL,
  `hall_id` int(0) NULL DEFAULT NULL,
  `session_id` int(0) NULL DEFAULT NULL,
  `movie_id` int(0) NULL DEFAULT NULL,
  `login_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `seat_id` int(0) NULL DEFAULT NULL,
  `status` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '1',
  PRIMARY KEY (`orde_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orde
-- ----------------------------
INSERT INTO `orde` VALUES (1, 'G-1-1-12-1', '2021-06-28 17:41:55', 1, 1, 1, '123', 67, '1');
INSERT INTO `orde` VALUES (2, 'G-3-5-3-6', '2021-06-28 00:00:00', 3, 4, 6, '123', 47, '0');

-- ----------------------------
-- Table structure for seat
-- ----------------------------
DROP TABLE IF EXISTS `seat`;
CREATE TABLE `seat`  (
  `seat_id` int(0) NOT NULL AUTO_INCREMENT,
  `row_num` int(0) NULL DEFAULT NULL,
  `col_num` int(0) NULL DEFAULT NULL,
  `hall_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`seat_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of seat
-- ----------------------------
INSERT INTO `seat` VALUES (1, 1, 1, 3);
INSERT INTO `seat` VALUES (2, 1, 2, 3);
INSERT INTO `seat` VALUES (3, 1, 3, 3);
INSERT INTO `seat` VALUES (4, 1, 4, 3);
INSERT INTO `seat` VALUES (5, 1, 5, 3);
INSERT INTO `seat` VALUES (6, 1, 6, 3);
INSERT INTO `seat` VALUES (7, 1, 7, 3);
INSERT INTO `seat` VALUES (8, 1, 8, 3);
INSERT INTO `seat` VALUES (9, 1, 9, 3);
INSERT INTO `seat` VALUES (10, 1, 10, 3);
INSERT INTO `seat` VALUES (11, 1, 11, 3);
INSERT INTO `seat` VALUES (12, 2, 1, 3);
INSERT INTO `seat` VALUES (13, 2, 2, 3);
INSERT INTO `seat` VALUES (14, 2, 3, 3);
INSERT INTO `seat` VALUES (15, 2, 4, 3);
INSERT INTO `seat` VALUES (16, 2, 5, 3);
INSERT INTO `seat` VALUES (17, 2, 6, 3);
INSERT INTO `seat` VALUES (18, 2, 7, 3);
INSERT INTO `seat` VALUES (19, 2, 8, 3);
INSERT INTO `seat` VALUES (20, 2, 9, 3);
INSERT INTO `seat` VALUES (21, 2, 10, 3);
INSERT INTO `seat` VALUES (22, 2, 11, 3);
INSERT INTO `seat` VALUES (23, 3, 1, 3);
INSERT INTO `seat` VALUES (24, 3, 2, 3);
INSERT INTO `seat` VALUES (25, 3, 3, 3);
INSERT INTO `seat` VALUES (26, 3, 4, 3);
INSERT INTO `seat` VALUES (27, 3, 5, 3);
INSERT INTO `seat` VALUES (28, 3, 6, 3);
INSERT INTO `seat` VALUES (29, 3, 7, 3);
INSERT INTO `seat` VALUES (30, 3, 8, 3);
INSERT INTO `seat` VALUES (31, 3, 9, 3);
INSERT INTO `seat` VALUES (32, 3, 10, 3);
INSERT INTO `seat` VALUES (33, 3, 11, 3);
INSERT INTO `seat` VALUES (34, 4, 1, 3);
INSERT INTO `seat` VALUES (35, 4, 2, 3);
INSERT INTO `seat` VALUES (36, 4, 3, 3);
INSERT INTO `seat` VALUES (37, 4, 4, 3);
INSERT INTO `seat` VALUES (38, 4, 5, 3);
INSERT INTO `seat` VALUES (39, 4, 6, 3);
INSERT INTO `seat` VALUES (40, 4, 7, 3);
INSERT INTO `seat` VALUES (41, 4, 8, 3);
INSERT INTO `seat` VALUES (42, 4, 9, 3);
INSERT INTO `seat` VALUES (43, 4, 10, 3);
INSERT INTO `seat` VALUES (44, 4, 11, 3);
INSERT INTO `seat` VALUES (45, 5, 1, 3);
INSERT INTO `seat` VALUES (46, 5, 2, 3);
INSERT INTO `seat` VALUES (47, 5, 3, 3);
INSERT INTO `seat` VALUES (48, 5, 4, 3);
INSERT INTO `seat` VALUES (49, 5, 5, 3);
INSERT INTO `seat` VALUES (50, 5, 6, 3);
INSERT INTO `seat` VALUES (51, 5, 7, 3);
INSERT INTO `seat` VALUES (52, 5, 8, 3);
INSERT INTO `seat` VALUES (53, 5, 9, 3);
INSERT INTO `seat` VALUES (54, 5, 10, 3);
INSERT INTO `seat` VALUES (55, 5, 11, 3);
INSERT INTO `seat` VALUES (56, 1, 1, 1);
INSERT INTO `seat` VALUES (57, 1, 2, 1);
INSERT INTO `seat` VALUES (58, 1, 3, 1);
INSERT INTO `seat` VALUES (59, 1, 4, 1);
INSERT INTO `seat` VALUES (60, 1, 5, 1);
INSERT INTO `seat` VALUES (61, 1, 6, 1);
INSERT INTO `seat` VALUES (62, 1, 7, 1);
INSERT INTO `seat` VALUES (63, 1, 8, 1);
INSERT INTO `seat` VALUES (64, 1, 9, 1);
INSERT INTO `seat` VALUES (65, 1, 10, 1);
INSERT INTO `seat` VALUES (66, 1, 11, 1);
INSERT INTO `seat` VALUES (67, 1, 12, 1);
INSERT INTO `seat` VALUES (68, 2, 1, 1);
INSERT INTO `seat` VALUES (69, 2, 2, 1);
INSERT INTO `seat` VALUES (70, 2, 3, 1);
INSERT INTO `seat` VALUES (71, 2, 4, 1);
INSERT INTO `seat` VALUES (72, 2, 5, 1);
INSERT INTO `seat` VALUES (73, 2, 6, 1);
INSERT INTO `seat` VALUES (74, 2, 7, 1);
INSERT INTO `seat` VALUES (75, 2, 8, 1);
INSERT INTO `seat` VALUES (76, 2, 9, 1);
INSERT INTO `seat` VALUES (77, 2, 10, 1);
INSERT INTO `seat` VALUES (78, 2, 11, 1);
INSERT INTO `seat` VALUES (79, 2, 12, 1);
INSERT INTO `seat` VALUES (80, 3, 1, 1);
INSERT INTO `seat` VALUES (81, 3, 2, 1);
INSERT INTO `seat` VALUES (82, 3, 3, 1);
INSERT INTO `seat` VALUES (83, 3, 4, 1);
INSERT INTO `seat` VALUES (84, 3, 5, 1);
INSERT INTO `seat` VALUES (85, 3, 6, 1);
INSERT INTO `seat` VALUES (86, 3, 7, 1);
INSERT INTO `seat` VALUES (87, 3, 8, 1);
INSERT INTO `seat` VALUES (88, 3, 9, 1);
INSERT INTO `seat` VALUES (89, 3, 10, 1);
INSERT INTO `seat` VALUES (90, 3, 11, 1);
INSERT INTO `seat` VALUES (91, 3, 12, 1);
INSERT INTO `seat` VALUES (92, 4, 1, 1);
INSERT INTO `seat` VALUES (93, 4, 2, 1);
INSERT INTO `seat` VALUES (94, 4, 3, 1);
INSERT INTO `seat` VALUES (95, 4, 4, 1);
INSERT INTO `seat` VALUES (96, 4, 5, 1);
INSERT INTO `seat` VALUES (97, 4, 6, 1);
INSERT INTO `seat` VALUES (98, 4, 7, 1);
INSERT INTO `seat` VALUES (99, 4, 8, 1);
INSERT INTO `seat` VALUES (100, 4, 9, 1);
INSERT INTO `seat` VALUES (101, 4, 10, 1);
INSERT INTO `seat` VALUES (102, 4, 11, 1);
INSERT INTO `seat` VALUES (103, 4, 12, 1);
INSERT INTO `seat` VALUES (104, 5, 1, 1);
INSERT INTO `seat` VALUES (105, 5, 2, 1);
INSERT INTO `seat` VALUES (106, 5, 3, 1);
INSERT INTO `seat` VALUES (107, 5, 4, 1);
INSERT INTO `seat` VALUES (108, 5, 5, 1);
INSERT INTO `seat` VALUES (109, 5, 6, 1);
INSERT INTO `seat` VALUES (110, 5, 7, 1);
INSERT INTO `seat` VALUES (111, 5, 8, 1);
INSERT INTO `seat` VALUES (112, 5, 9, 1);
INSERT INTO `seat` VALUES (113, 5, 10, 1);
INSERT INTO `seat` VALUES (114, 5, 11, 1);
INSERT INTO `seat` VALUES (115, 5, 12, 1);
INSERT INTO `seat` VALUES (116, 1, 1, 2);
INSERT INTO `seat` VALUES (117, 1, 2, 2);
INSERT INTO `seat` VALUES (118, 1, 3, 2);
INSERT INTO `seat` VALUES (119, 1, 4, 2);
INSERT INTO `seat` VALUES (120, 1, 5, 2);
INSERT INTO `seat` VALUES (121, 1, 6, 2);
INSERT INTO `seat` VALUES (122, 1, 7, 2);
INSERT INTO `seat` VALUES (123, 1, 8, 2);
INSERT INTO `seat` VALUES (124, 1, 9, 2);
INSERT INTO `seat` VALUES (125, 1, 10, 2);
INSERT INTO `seat` VALUES (126, 1, 11, 2);
INSERT INTO `seat` VALUES (127, 1, 12, 2);
INSERT INTO `seat` VALUES (128, 2, 1, 2);
INSERT INTO `seat` VALUES (129, 2, 2, 2);
INSERT INTO `seat` VALUES (130, 2, 3, 2);
INSERT INTO `seat` VALUES (131, 2, 4, 2);
INSERT INTO `seat` VALUES (132, 2, 5, 2);
INSERT INTO `seat` VALUES (133, 2, 6, 2);
INSERT INTO `seat` VALUES (134, 2, 7, 2);
INSERT INTO `seat` VALUES (135, 2, 8, 2);
INSERT INTO `seat` VALUES (136, 2, 9, 2);
INSERT INTO `seat` VALUES (137, 2, 10, 2);
INSERT INTO `seat` VALUES (138, 2, 11, 2);
INSERT INTO `seat` VALUES (139, 2, 12, 2);
INSERT INTO `seat` VALUES (140, 3, 1, 2);
INSERT INTO `seat` VALUES (141, 3, 2, 2);
INSERT INTO `seat` VALUES (142, 3, 3, 2);
INSERT INTO `seat` VALUES (143, 3, 4, 2);
INSERT INTO `seat` VALUES (144, 3, 5, 2);
INSERT INTO `seat` VALUES (145, 3, 6, 2);
INSERT INTO `seat` VALUES (146, 3, 7, 2);
INSERT INTO `seat` VALUES (147, 3, 8, 2);
INSERT INTO `seat` VALUES (148, 3, 9, 2);
INSERT INTO `seat` VALUES (149, 3, 10, 2);
INSERT INTO `seat` VALUES (150, 3, 11, 2);
INSERT INTO `seat` VALUES (151, 3, 12, 2);
INSERT INTO `seat` VALUES (152, 4, 1, 2);
INSERT INTO `seat` VALUES (153, 4, 2, 2);
INSERT INTO `seat` VALUES (154, 4, 3, 2);
INSERT INTO `seat` VALUES (155, 4, 4, 2);
INSERT INTO `seat` VALUES (156, 4, 5, 2);
INSERT INTO `seat` VALUES (157, 4, 6, 2);
INSERT INTO `seat` VALUES (158, 4, 7, 2);
INSERT INTO `seat` VALUES (159, 4, 8, 2);
INSERT INTO `seat` VALUES (160, 4, 9, 2);
INSERT INTO `seat` VALUES (161, 4, 10, 2);
INSERT INTO `seat` VALUES (162, 4, 11, 2);
INSERT INTO `seat` VALUES (163, 4, 12, 2);
INSERT INTO `seat` VALUES (164, 5, 1, 2);
INSERT INTO `seat` VALUES (165, 5, 2, 2);
INSERT INTO `seat` VALUES (166, 5, 3, 2);
INSERT INTO `seat` VALUES (167, 5, 4, 2);
INSERT INTO `seat` VALUES (168, 5, 5, 2);
INSERT INTO `seat` VALUES (169, 5, 6, 2);
INSERT INTO `seat` VALUES (170, 5, 7, 2);
INSERT INTO `seat` VALUES (171, 5, 8, 2);
INSERT INTO `seat` VALUES (172, 5, 9, 2);
INSERT INTO `seat` VALUES (173, 5, 10, 2);
INSERT INTO `seat` VALUES (174, 5, 11, 2);
INSERT INTO `seat` VALUES (175, 5, 12, 2);

-- ----------------------------
-- Table structure for session
-- ----------------------------
DROP TABLE IF EXISTS `session`;
CREATE TABLE `session`  (
  `session_id` int(0) NOT NULL AUTO_INCREMENT,
  `start_time` datetime(0) NULL DEFAULT NULL,
  `end_time` datetime(0) NULL DEFAULT NULL,
  `price` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `movie_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`session_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of session
-- ----------------------------
INSERT INTO `session` VALUES (1, '2021-06-28 11:30:00', '2021-06-28 13:30:00', '35', 1);
INSERT INTO `session` VALUES (2, '2021-06-29 15:50:00', '2021-06-29 18:00:00', '35', 2);
INSERT INTO `session` VALUES (3, '2021-06-29 15:50:00', '2021-06-29 18:00:00', '35', 6);
INSERT INTO `session` VALUES (4, '2021-06-28 20:00:00', '2021-06-28 22:00:00', '35', 6);
INSERT INTO `session` VALUES (5, '2021-06-28 08:00:00', '2021-06-28 10:00:00', '35', 6);
INSERT INTO `session` VALUES (6, '2021-06-28 16:00:00', '2021-06-28 18:00:00', '35', 6);

-- ----------------------------
-- Table structure for session_hall
-- ----------------------------
DROP TABLE IF EXISTS `session_hall`;
CREATE TABLE `session_hall`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `session_id` int(0) NULL DEFAULT NULL,
  `hall_id` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of session_hall
-- ----------------------------
INSERT INTO `session_hall` VALUES (1, 1, 1);
INSERT INTO `session_hall` VALUES (2, 2, 1);
INSERT INTO `session_hall` VALUES (3, 3, 3);
INSERT INTO `session_hall` VALUES (4, 4, 3);
INSERT INTO `session_hall` VALUES (5, 5, 3);
INSERT INTO `session_hall` VALUES (6, 6, 3);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `login_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户账号',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `phone_number` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '手机号',
  `role` char(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '角色标识',
  PRIMARY KEY (`login_name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('111', '123', '12345678901', 'user');
INSERT INTO `user` VALUES ('123', '123', '13361335019', 'user');
INSERT INTO `user` VALUES ('1233', '1233', '13361335019', 'user');
INSERT INTO `user` VALUES ('admin', 'admin23', '12345678992', 'staff');

-- ----------------------------
-- Procedure structure for proc_initData
-- ----------------------------
DROP PROCEDURE IF EXISTS `proc_initData`;
delimiter ;;
CREATE PROCEDURE `proc_initData`()
BEGIN  
    DECLARE i INT DEFAULT 1;
		DECLARE j INT DEFAULT 1;
    WHILE i<=5 DO  
				SET j = 1;
         WHILE j<=12 DO
						INSERT INTO seat(row_num, col_num, hall_id) VALUES (i, j, 2);
						SET j = j + 1;
					END WHILE;
				SET i = i + 1;
    END WHILE;  
END
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
