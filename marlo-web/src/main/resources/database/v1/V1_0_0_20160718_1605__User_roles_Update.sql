/*
Navicat MySQL Data Transfer

Source Server         : LocalHost
Source Server Version : 50712
Source Host           : localhost:3306
Source Database       : ccafspr_marlo

Target Server Type    : MYSQL
Target Server Version : 50712
File Encoding         : 65001

Date: 2016-07-18 16:03:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user_roles
-- ----------------------------
DROP TABLE IF EXISTS `user_roles`;
CREATE TABLE `user_roles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`) USING BTREE,
  KEY `role_id` (`role_id`) USING BTREE,
  CONSTRAINT `user_roles_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_roles_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1198 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_roles
-- ----------------------------
INSERT INTO `user_roles` VALUES ('1', '1', '1');
INSERT INTO `user_roles` VALUES ('8', '5', '2');
INSERT INTO `user_roles` VALUES ('9', '7', '2');
INSERT INTO `user_roles` VALUES ('16', '16', '2');
INSERT INTO `user_roles` VALUES ('18', '17', '2');
INSERT INTO `user_roles` VALUES ('19', '18', '2');
INSERT INTO `user_roles` VALUES ('20', '19', '2');
INSERT INTO `user_roles` VALUES ('21', '20', '2');
INSERT INTO `user_roles` VALUES ('22', '21', '2');
INSERT INTO `user_roles` VALUES ('23', '22', '2');
INSERT INTO `user_roles` VALUES ('24', '22', '7');
INSERT INTO `user_roles` VALUES ('26', '24', '2');
INSERT INTO `user_roles` VALUES ('27', '25', '2');
INSERT INTO `user_roles` VALUES ('28', '26', '2');
INSERT INTO `user_roles` VALUES ('30', '28', '2');
INSERT INTO `user_roles` VALUES ('31', '29', '2');
INSERT INTO `user_roles` VALUES ('32', '29', '7');
INSERT INTO `user_roles` VALUES ('33', '30', '2');
INSERT INTO `user_roles` VALUES ('34', '31', '2');
INSERT INTO `user_roles` VALUES ('36', '32', '2');
INSERT INTO `user_roles` VALUES ('37', '50', '2');
INSERT INTO `user_roles` VALUES ('38', '51', '2');
INSERT INTO `user_roles` VALUES ('39', '52', '7');
INSERT INTO `user_roles` VALUES ('40', '53', '7');
INSERT INTO `user_roles` VALUES ('41', '54', '7');
INSERT INTO `user_roles` VALUES ('42', '55', '7');
INSERT INTO `user_roles` VALUES ('43', '56', '7');
INSERT INTO `user_roles` VALUES ('45', '58', '7');
INSERT INTO `user_roles` VALUES ('46', '59', '7');
INSERT INTO `user_roles` VALUES ('47', '60', '7');
INSERT INTO `user_roles` VALUES ('48', '61', '7');
INSERT INTO `user_roles` VALUES ('49', '62', '7');
INSERT INTO `user_roles` VALUES ('50', '63', '7');
INSERT INTO `user_roles` VALUES ('51', '64', '7');
INSERT INTO `user_roles` VALUES ('52', '65', '7');
INSERT INTO `user_roles` VALUES ('53', '66', '7');
INSERT INTO `user_roles` VALUES ('54', '67', '7');
INSERT INTO `user_roles` VALUES ('55', '68', '7');
INSERT INTO `user_roles` VALUES ('61', '73', '7');
INSERT INTO `user_roles` VALUES ('65', '77', '7');
INSERT INTO `user_roles` VALUES ('66', '78', '7');
INSERT INTO `user_roles` VALUES ('67', '79', '7');
INSERT INTO `user_roles` VALUES ('68', '80', '7');
INSERT INTO `user_roles` VALUES ('69', '81', '7');
INSERT INTO `user_roles` VALUES ('70', '82', '7');
INSERT INTO `user_roles` VALUES ('71', '83', '7');
INSERT INTO `user_roles` VALUES ('72', '84', '7');
INSERT INTO `user_roles` VALUES ('73', '85', '7');
INSERT INTO `user_roles` VALUES ('74', '86', '7');
INSERT INTO `user_roles` VALUES ('75', '87', '7');
INSERT INTO `user_roles` VALUES ('77', '89', '7');
INSERT INTO `user_roles` VALUES ('78', '90', '7');
INSERT INTO `user_roles` VALUES ('82', '94', '7');
INSERT INTO `user_roles` VALUES ('83', '95', '7');
INSERT INTO `user_roles` VALUES ('84', '96', '7');
INSERT INTO `user_roles` VALUES ('85', '97', '7');
INSERT INTO `user_roles` VALUES ('86', '98', '7');
INSERT INTO `user_roles` VALUES ('87', '99', '7');
INSERT INTO `user_roles` VALUES ('88', '100', '7');
INSERT INTO `user_roles` VALUES ('89', '101', '7');
INSERT INTO `user_roles` VALUES ('90', '102', '7');
INSERT INTO `user_roles` VALUES ('91', '103', '7');
INSERT INTO `user_roles` VALUES ('93', '105', '7');
INSERT INTO `user_roles` VALUES ('94', '106', '7');
INSERT INTO `user_roles` VALUES ('95', '107', '7');
INSERT INTO `user_roles` VALUES ('96', '108', '7');
INSERT INTO `user_roles` VALUES ('97', '109', '7');
INSERT INTO `user_roles` VALUES ('98', '110', '7');
INSERT INTO `user_roles` VALUES ('99', '111', '7');
INSERT INTO `user_roles` VALUES ('100', '112', '7');
INSERT INTO `user_roles` VALUES ('101', '113', '7');
INSERT INTO `user_roles` VALUES ('102', '114', '7');
INSERT INTO `user_roles` VALUES ('104', '116', '7');
INSERT INTO `user_roles` VALUES ('105', '117', '7');
INSERT INTO `user_roles` VALUES ('106', '118', '7');
INSERT INTO `user_roles` VALUES ('107', '119', '7');
INSERT INTO `user_roles` VALUES ('124', '132', '7');
INSERT INTO `user_roles` VALUES ('132', '141', '7');
INSERT INTO `user_roles` VALUES ('134', '143', '7');
INSERT INTO `user_roles` VALUES ('135', '144', '7');
INSERT INTO `user_roles` VALUES ('136', '145', '7');
INSERT INTO `user_roles` VALUES ('137', '150', '7');
INSERT INTO `user_roles` VALUES ('138', '151', '7');
INSERT INTO `user_roles` VALUES ('144', '158', '7');
INSERT INTO `user_roles` VALUES ('147', '162', '7');
INSERT INTO `user_roles` VALUES ('154', '172', '7');
INSERT INTO `user_roles` VALUES ('155', '173', '7');
INSERT INTO `user_roles` VALUES ('171', '189', '7');
INSERT INTO `user_roles` VALUES ('175', '193', '2');
INSERT INTO `user_roles` VALUES ('212', '241', '7');
INSERT INTO `user_roles` VALUES ('229', '259', '7');
INSERT INTO `user_roles` VALUES ('240', '270', '7');
INSERT INTO `user_roles` VALUES ('777', '3', '1');
INSERT INTO `user_roles` VALUES ('785', '66', '4');
INSERT INTO `user_roles` VALUES ('789', '844', '1');
INSERT INTO `user_roles` VALUES ('791', '843', '1');
INSERT INTO `user_roles` VALUES ('800', '13', '1');
INSERT INTO `user_roles` VALUES ('819', '52', '4');
INSERT INTO `user_roles` VALUES ('820', '61', '4');
INSERT INTO `user_roles` VALUES ('821', '67', '4');
INSERT INTO `user_roles` VALUES ('822', '69', '4');
INSERT INTO `user_roles` VALUES ('823', '82', '4');
INSERT INTO `user_roles` VALUES ('824', '83', '4');
INSERT INTO `user_roles` VALUES ('825', '108', '4');
INSERT INTO `user_roles` VALUES ('826', '119', '4');
INSERT INTO `user_roles` VALUES ('827', '162', '4');
INSERT INTO `user_roles` VALUES ('828', '243', '4');
INSERT INTO `user_roles` VALUES ('829', '247', '4');
INSERT INTO `user_roles` VALUES ('830', '271', '4');
INSERT INTO `user_roles` VALUES ('834', '88', '4');
INSERT INTO `user_roles` VALUES ('836', '847', '4');
INSERT INTO `user_roles` VALUES ('837', '848', '4');
INSERT INTO `user_roles` VALUES ('838', '275', '4');
INSERT INTO `user_roles` VALUES ('839', '450', '9');
INSERT INTO `user_roles` VALUES ('840', '294', '2');
INSERT INTO `user_roles` VALUES ('841', '145', '9');
INSERT INTO `user_roles` VALUES ('842', '158', '4');
INSERT INTO `user_roles` VALUES ('843', '142', '9');
INSERT INTO `user_roles` VALUES ('845', '852', '2');
INSERT INTO `user_roles` VALUES ('846', '182', '7');
INSERT INTO `user_roles` VALUES ('848', '855', '2');
INSERT INTO `user_roles` VALUES ('849', '833', '7');
INSERT INTO `user_roles` VALUES ('851', '288', '9');
INSERT INTO `user_roles` VALUES ('852', '856', '9');
INSERT INTO `user_roles` VALUES ('853', '14', '1');
INSERT INTO `user_roles` VALUES ('854', '478', '7');
INSERT INTO `user_roles` VALUES ('855', '857', '1');
INSERT INTO `user_roles` VALUES ('856', '858', '7');
INSERT INTO `user_roles` VALUES ('857', '361', '7');
INSERT INTO `user_roles` VALUES ('860', '206', '7');
INSERT INTO `user_roles` VALUES ('862', '302', '7');
INSERT INTO `user_roles` VALUES ('863', '863', '4');
INSERT INTO `user_roles` VALUES ('864', '864', '7');
INSERT INTO `user_roles` VALUES ('867', '199', '9');
INSERT INTO `user_roles` VALUES ('869', '182', '9');
INSERT INTO `user_roles` VALUES ('873', '871', '9');
INSERT INTO `user_roles` VALUES ('874', '250', '7');
INSERT INTO `user_roles` VALUES ('875', '869', '7');
INSERT INTO `user_roles` VALUES ('878', '271', '7');
INSERT INTO `user_roles` VALUES ('880', '862', '7');
INSERT INTO `user_roles` VALUES ('882', '872', '9');
INSERT INTO `user_roles` VALUES ('883', '880', '9');
INSERT INTO `user_roles` VALUES ('885', '881', '9');
INSERT INTO `user_roles` VALUES ('886', '872', '7');
INSERT INTO `user_roles` VALUES ('887', '61', '9');
INSERT INTO `user_roles` VALUES ('888', '115', '7');
INSERT INTO `user_roles` VALUES ('890', '7', '7');
INSERT INTO `user_roles` VALUES ('891', '5', '9');
INSERT INTO `user_roles` VALUES ('892', '158', '9');
INSERT INTO `user_roles` VALUES ('894', '179', '9');
INSERT INTO `user_roles` VALUES ('896', '285', '7');
INSERT INTO `user_roles` VALUES ('898', '153', '7');
INSERT INTO `user_roles` VALUES ('899', '73', '9');
INSERT INTO `user_roles` VALUES ('901', '54', '9');
INSERT INTO `user_roles` VALUES ('903', '905', '9');
INSERT INTO `user_roles` VALUES ('904', '174', '7');
INSERT INTO `user_roles` VALUES ('906', '910', '7');
INSERT INTO `user_roles` VALUES ('908', '5', '1');
INSERT INTO `user_roles` VALUES ('910', '915', '9');
INSERT INTO `user_roles` VALUES ('911', '178', '7');
INSERT INTO `user_roles` VALUES ('915', '88', '7');
INSERT INTO `user_roles` VALUES ('920', '842', '2');
INSERT INTO `user_roles` VALUES ('921', '294', '7');
INSERT INTO `user_roles` VALUES ('922', '842', '9');
INSERT INTO `user_roles` VALUES ('923', '924', '9');
INSERT INTO `user_roles` VALUES ('924', '924', '7');
INSERT INTO `user_roles` VALUES ('925', '925', '9');
INSERT INTO `user_roles` VALUES ('926', '925', '7');
INSERT INTO `user_roles` VALUES ('927', '557', '7');
INSERT INTO `user_roles` VALUES ('928', '56', '9');
INSERT INTO `user_roles` VALUES ('929', '19', '7');
INSERT INTO `user_roles` VALUES ('930', '170', '9');
INSERT INTO `user_roles` VALUES ('932', '926', '7');
INSERT INTO `user_roles` VALUES ('937', '132', '2');
INSERT INTO `user_roles` VALUES ('938', '31', '7');
INSERT INTO `user_roles` VALUES ('940', '271', '9');
INSERT INTO `user_roles` VALUES ('941', '948', '7');
INSERT INTO `user_roles` VALUES ('942', '63', '9');
INSERT INTO `user_roles` VALUES ('943', '952', '9');
INSERT INTO `user_roles` VALUES ('944', '928', '9');
INSERT INTO `user_roles` VALUES ('945', '72', '7');
INSERT INTO `user_roles` VALUES ('948', '878', '9');
INSERT INTO `user_roles` VALUES ('954', '91', '9');
INSERT INTO `user_roles` VALUES ('957', '470', '7');
INSERT INTO `user_roles` VALUES ('961', '955', '7');
INSERT INTO `user_roles` VALUES ('963', '957', '7');
INSERT INTO `user_roles` VALUES ('964', '176', '9');
INSERT INTO `user_roles` VALUES ('965', '69', '9');
INSERT INTO `user_roles` VALUES ('966', '963', '7');
INSERT INTO `user_roles` VALUES ('968', '965', '9');
INSERT INTO `user_roles` VALUES ('970', '119', '9');
INSERT INTO `user_roles` VALUES ('977', '367', '9');
INSERT INTO `user_roles` VALUES ('981', '288', '7');
INSERT INTO `user_roles` VALUES ('986', '987', '7');
INSERT INTO `user_roles` VALUES ('993', '994', '7');
INSERT INTO `user_roles` VALUES ('994', '1002', '4');
INSERT INTO `user_roles` VALUES ('995', '136', '9');
INSERT INTO `user_roles` VALUES ('997', '28', '12');
INSERT INTO `user_roles` VALUES ('999', '30', '12');
INSERT INTO `user_roles` VALUES ('1000', '852', '12');
INSERT INTO `user_roles` VALUES ('1001', '31', '12');
INSERT INTO `user_roles` VALUES ('1002', '32', '12');
INSERT INTO `user_roles` VALUES ('1003', '50', '12');
INSERT INTO `user_roles` VALUES ('1004', '5', '12');
INSERT INTO `user_roles` VALUES ('1005', '7', '12');
INSERT INTO `user_roles` VALUES ('1006', '16', '12');
INSERT INTO `user_roles` VALUES ('1007', '51', '12');
INSERT INTO `user_roles` VALUES ('1008', '24', '11');
INSERT INTO `user_roles` VALUES ('1009', '25', '11');
INSERT INTO `user_roles` VALUES ('1010', '18', '11');
INSERT INTO `user_roles` VALUES ('1011', '19', '11');
INSERT INTO `user_roles` VALUES ('1012', '20', '11');
INSERT INTO `user_roles` VALUES ('1013', '21', '11');
INSERT INTO `user_roles` VALUES ('1014', '26', '11');
INSERT INTO `user_roles` VALUES ('1015', '193', '11');
INSERT INTO `user_roles` VALUES ('1016', '22', '11');
INSERT INTO `user_roles` VALUES ('1017', '855', '11');
INSERT INTO `user_roles` VALUES ('1019', '286', '1');
INSERT INTO `user_roles` VALUES ('1020', '160', '9');
INSERT INTO `user_roles` VALUES ('1021', '133', '9');
INSERT INTO `user_roles` VALUES ('1022', '24', '9');
INSERT INTO `user_roles` VALUES ('1023', '60', '9');
INSERT INTO `user_roles` VALUES ('1024', '1007', '9');
INSERT INTO `user_roles` VALUES ('1025', '181', '7');
INSERT INTO `user_roles` VALUES ('1026', '996', '7');
INSERT INTO `user_roles` VALUES ('1027', '104', '7');
INSERT INTO `user_roles` VALUES ('1028', '157', '7');
INSERT INTO `user_roles` VALUES ('1029', '567', '7');
INSERT INTO `user_roles` VALUES ('1032', '174', '9');
INSERT INTO `user_roles` VALUES ('1033', '107', '9');
INSERT INTO `user_roles` VALUES ('1036', '1014', '9');
INSERT INTO `user_roles` VALUES ('1042', '1009', '9');
INSERT INTO `user_roles` VALUES ('1046', '194', '9');
INSERT INTO `user_roles` VALUES ('1047', '28', '7');
INSERT INTO `user_roles` VALUES ('1049', '57', '9');
INSERT INTO `user_roles` VALUES ('1053', '1051', '2');
INSERT INTO `user_roles` VALUES ('1054', '1052', '9');
INSERT INTO `user_roles` VALUES ('1055', '179', '11');
INSERT INTO `user_roles` VALUES ('1056', '186', '2');
INSERT INTO `user_roles` VALUES ('1057', '186', '11');
INSERT INTO `user_roles` VALUES ('1118', '316', '12');
INSERT INTO `user_roles` VALUES ('1123', '1', '12');
INSERT INTO `user_roles` VALUES ('1124', '844', '12');
INSERT INTO `user_roles` VALUES ('1125', '1', '18');
INSERT INTO `user_roles` VALUES ('1126', '1', '16');
INSERT INTO `user_roles` VALUES ('1127', '988', '19');
INSERT INTO `user_roles` VALUES ('1128', '1060', '19');
INSERT INTO `user_roles` VALUES ('1130', '1', '17');
INSERT INTO `user_roles` VALUES ('1131', '1061', '26');
INSERT INTO `user_roles` VALUES ('1132', '1062', '32');
INSERT INTO `user_roles` VALUES ('1133', '1064', '26');
INSERT INTO `user_roles` VALUES ('1134', '7', '1');
INSERT INTO `user_roles` VALUES ('1135', '955', '15');
INSERT INTO `user_roles` VALUES ('1138', '114', '15');
INSERT INTO `user_roles` VALUES ('1139', '1008', '15');
INSERT INTO `user_roles` VALUES ('1143', '13', '17');
INSERT INTO `user_roles` VALUES ('1144', '14', '17');
INSERT INTO `user_roles` VALUES ('1145', '18', '15');
INSERT INTO `user_roles` VALUES ('1146', '906', '18');
INSERT INTO `user_roles` VALUES ('1147', '844', '18');
INSERT INTO `user_roles` VALUES ('1150', '1008', '18');
INSERT INTO `user_roles` VALUES ('1151', '29', '12');
INSERT INTO `user_roles` VALUES ('1152', '19', '15');
INSERT INTO `user_roles` VALUES ('1153', '179', '15');
INSERT INTO `user_roles` VALUES ('1154', '179', '15');
INSERT INTO `user_roles` VALUES ('1155', '179', '15');
INSERT INTO `user_roles` VALUES ('1156', '179', '15');
INSERT INTO `user_roles` VALUES ('1157', '25', '15');
INSERT INTO `user_roles` VALUES ('1158', '25', '15');
INSERT INTO `user_roles` VALUES ('1159', '25', '15');
INSERT INTO `user_roles` VALUES ('1160', '25', '15');
INSERT INTO `user_roles` VALUES ('1161', '855', '15');
INSERT INTO `user_roles` VALUES ('1162', '855', '15');
INSERT INTO `user_roles` VALUES ('1163', '855', '15');
INSERT INTO `user_roles` VALUES ('1164', '855', '15');
INSERT INTO `user_roles` VALUES ('1165', '855', '15');
INSERT INTO `user_roles` VALUES ('1166', '186', '15');
INSERT INTO `user_roles` VALUES ('1167', '186', '15');
INSERT INTO `user_roles` VALUES ('1168', '186', '15');
INSERT INTO `user_roles` VALUES ('1169', '186', '15');
INSERT INTO `user_roles` VALUES ('1170', '186', '15');
INSERT INTO `user_roles` VALUES ('1171', '22', '15');
INSERT INTO `user_roles` VALUES ('1172', '22', '15');
INSERT INTO `user_roles` VALUES ('1173', '22', '15');
INSERT INTO `user_roles` VALUES ('1174', '22', '15');
INSERT INTO `user_roles` VALUES ('1175', '22', '15');
INSERT INTO `user_roles` VALUES ('1176', '26', '15');
INSERT INTO `user_roles` VALUES ('1177', '26', '15');
INSERT INTO `user_roles` VALUES ('1178', '26', '15');
INSERT INTO `user_roles` VALUES ('1179', '193', '15');
INSERT INTO `user_roles` VALUES ('1180', '193', '15');
INSERT INTO `user_roles` VALUES ('1181', '193', '15');
INSERT INTO `user_roles` VALUES ('1182', '20', '15');
INSERT INTO `user_roles` VALUES ('1183', '20', '15');
INSERT INTO `user_roles` VALUES ('1184', '20', '15');
INSERT INTO `user_roles` VALUES ('1185', '21', '15');
INSERT INTO `user_roles` VALUES ('1186', '21', '15');
INSERT INTO `user_roles` VALUES ('1187', '21', '15');
INSERT INTO `user_roles` VALUES ('1188', '1065', '27');
INSERT INTO `user_roles` VALUES ('1189', '1064', '29');
INSERT INTO `user_roles` VALUES ('1190', '79', '29');
INSERT INTO `user_roles` VALUES ('1191', '1060', '30');
INSERT INTO `user_roles` VALUES ('1192', '1060', '30');
INSERT INTO `user_roles` VALUES ('1193', '1060', '28');
INSERT INTO `user_roles` VALUES ('1195', '554', '15');
INSERT INTO `user_roles` VALUES ('1196', '582', '15');
INSERT INTO `user_roles` VALUES ('1197', '66', '14');
