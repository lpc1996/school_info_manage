/*
 Navicat Premium Data Transfer

 Source Server         : student_manage
 Source Server Type    : MySQL
 Source Server Version : 80013
 Source Host           : localhost:3306
 Source Schema         : xupt

 Target Server Type    : MySQL
 Target Server Version : 80013
 File Encoding         : 65001

 Date: 20/05/2019 17:24:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for base_info
-- ----------------------------
DROP TABLE IF EXISTS `base_info`;
CREATE TABLE `base_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自动序号',
  `user_id` char(8) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户名',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '姓名',
  `formar_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '曾用名',
  `sex` enum('男','女') CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '性别',
  `age` int(2) DEFAULT NULL COMMENT '年龄',
  `native_place` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '籍贯',
  `IDCARD_type` enum('居民身份证') CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '证件类型',
  `IDCARD_NUM` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '证件号码',
  `user_type` enum('student','teacher','admin') CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户类型',
  `tel` char(11) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '电话号码',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `user_id_2`(`user_id`) USING BTREE,
  UNIQUE INDEX `user_id_3`(`user_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 49 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '用户基本信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of base_info
-- ----------------------------
INSERT INTO `base_info` VALUES (2, '07980001', '范九伦', '无', '男', 54, '河南省温县', '居民身份证', '111111196411231111', 'admin', '1234567890');
INSERT INTO `base_info` VALUES (9, 'TR072002', '谢卫强', '无', '男', 43, '陕西省西安市', '居民身份证', '111111111111111111', 'teacher', '');
INSERT INTO `base_info` VALUES (12, 'TR072003', '刘冰', '无', '女', 34, '陕西省西安市', '居民身份证', '111111111111111111', 'teacher', '12345678901');
INSERT INTO `base_info` VALUES (14, 'TR072004', '冯锋', '无', '男', 37, '陕西省西安市', '居民身份证', '123456789012345678', 'teacher', '12345678901');
INSERT INTO `base_info` VALUES (15, 'TR072005', '盛为冲', '无', '男', 27, '陕西省西安市', '居民身份证', '123456789012345678', 'teacher', '12345678901');
INSERT INTO `base_info` VALUES (24, '07161075', '柳鹏程', '无', '男', 22, '甘肃省灵台县', '居民身份证', '622723199609231716', 'student', '18292517962');
INSERT INTO `base_info` VALUES (27, '07161065', '陈增增', '无', '男', 22, '陕西省定边市', '居民身份证', '123456789012345678', 'student', '01234567890');
INSERT INTO `base_info` VALUES (28, '07161079', '方学诚', '无', '男', 21, '陕西省西安市', '居民身份证', '123456789012345678', 'student', '');
INSERT INTO `base_info` VALUES (29, '07161045', '梁劲舟', '无', '男', 22, '广西省', '居民身份证', '123456789012345678', 'student', '');
INSERT INTO `base_info` VALUES (32, '07171001', '车泽航', '无', '男', 20, '陕西省西安市', '居民身份证', '123456789012345678', 'student', '');
INSERT INTO `base_info` VALUES (33, 'TR071001', '徐建刚', '无', '男', 45, '陕西省蓝田市', '居民身份证', '123456789012345678', 'teacher', '12345678901');
INSERT INTO `base_info` VALUES (34, 'TR020001', '杜慧敏', '无', '女', 0, '陕西省西安市', '居民身份证', '123456789012345678', 'teacher', '');
INSERT INTO `base_info` VALUES (35, 'TR030001', '王忠民', '无', '男', 0, '陕西西安', '居民身份证', '123456789012345678', 'teacher', '');
INSERT INTO `base_info` VALUES (36, 'TR040001', '王文庆', '无', '男', 0, '陕西省西安市', '居民身份证', '123456789012345678', 'teacher', '');
INSERT INTO `base_info` VALUES (37, 'TR051001', '张鸿', '无', '男', 0, '陕西省西安市', '居民身份证', '123456789012345678', 'teacher', '');
INSERT INTO `base_info` VALUES (38, 'TR061001', '薛蓉', '无', '女', 0, '陕西省西安市', '居民身份证', '123456789012345678', 'teacher', '');
INSERT INTO `base_info` VALUES (39, 'TR081001', '袁武振', '', '男', 0, '陕西省西安市', '居民身份证', '123456789012345678', 'teacher', '');
INSERT INTO `base_info` VALUES (40, 'TR091001', '王得忠', '', '男', 0, '陕西省西安市', '居民身份证', '123456789012345678', 'teacher', '');
INSERT INTO `base_info` VALUES (41, 'TR101001', '袁小陆', '', '男', 0, '陕西省西安市', '居民身份证', '123456789012345678', 'teacher', '');
INSERT INTO `base_info` VALUES (42, 'TR111001', '马力', '', '男', 0, '陕西省西安市', '居民身份证', '123456789012345678', 'teacher', '');
INSERT INTO `base_info` VALUES (43, 'TR121001', '张继荣', '', '男', 0, '陕西省西安市', '居民身份证', '123456789012345678', 'teacher', '');
INSERT INTO `base_info` VALUES (44, 'TR131001', '李静', '', '女', 0, '陕西省西安市', '居民身份证', '123456789012345678', 'teacher', '');
INSERT INTO `base_info` VALUES (45, 'TR141001', '朱志祥', '', '男', 0, '陕西省西安市', '居民身份证', '123456789012345678', 'teacher', '');
INSERT INTO `base_info` VALUES (46, 'TR151001', '齐劲进', '', '男', 0, '陕西省西安市', '居民身份证', '123456789012345678', 'teacher', '');
INSERT INTO `base_info` VALUES (47, 'TR072001', '马小珏', '', '女', 45, '陕西西安', '居民身份证', '123456789012345678', 'teacher', '');
INSERT INTO `base_info` VALUES (48, 'TR081002', '马智', '', '男', 0, '宁夏彭阳县', '居民身份证', '123456789012345678', 'teacher', '');
INSERT INTO `base_info` VALUES (49, 'TR072006', '杨爽', '', '女', 0, '江苏南京', '居民身份证', '123456789012345678', 'teacher', '');

-- ----------------------------
-- Table structure for college
-- ----------------------------
DROP TABLE IF EXISTS `college`;
CREATE TABLE `college`  (
  `id` char(8) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '学院编号',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '学院名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '学院信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of college
-- ----------------------------
INSERT INTO `college` VALUES ('xupt0001', '通信与信息工程学院');
INSERT INTO `college` VALUES ('xupt0002', '电子工程学院');
INSERT INTO `college` VALUES ('xupt0003', '计算机学院');
INSERT INTO `college` VALUES ('xupt0004', '自动化学院');
INSERT INTO `college` VALUES ('xupt0005', '经济与管理学院');
INSERT INTO `college` VALUES ('xupt0006', '现代邮政学院、邮政研究院');
INSERT INTO `college` VALUES ('xupt0007', '理学院');
INSERT INTO `college` VALUES ('xupt0008', '马克思主义学院');
INSERT INTO `college` VALUES ('xupt0009', '人文社科学院');
INSERT INTO `college` VALUES ('xupt0010', '外国语学院');
INSERT INTO `college` VALUES ('xupt0011', '数字艺术学院');
INSERT INTO `college` VALUES ('xupt0012', '继续教育学院、职业技术学院');
INSERT INTO `college` VALUES ('xupt0013', '体育部');
INSERT INTO `college` VALUES ('xupt0014', '物联网与两化融合研究院');
INSERT INTO `college` VALUES ('xupt0015', '国防教育学院');

-- ----------------------------
-- Table structure for course
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course`  (
  `id` char(8) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '课程编号',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '课程名称',
  `college_id` char(8) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '所属学院ID',
  `department_id` char(8) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '所属系/部ID',
  `type` enum('必修课','选修课') CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '课程类型',
  `credit` double(1, 0) NOT NULL COMMENT '学分',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '课程信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('03040001', '高级语言程序设计（C语言）', 'xupt0003', 'xupt0304', '必修课', 4);
INSERT INTO `course` VALUES ('07020001', '数学分析I', 'xupt0007', 'xupt0702', '必修课', 6);
INSERT INTO `course` VALUES ('07020002', '高等代数I', 'xupt0007', 'xupt0702', '必修课', 4);
INSERT INTO `course` VALUES ('07020003', '解析几何', 'xupt0007', 'xupt0702', '必修课', 3);
INSERT INTO `course` VALUES ('07020004', '高等代数II', 'xupt0007', 'xupt0702', '必修课', 4);

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department`  (
  `id` char(8) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '系/部代号',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '系/部名称',
  `college_id` char(8) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '所属学院代号',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `department-college`(`college_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '系/部信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('xupt0101', '通信工程系', 'xupt0001');
INSERT INTO `department` VALUES ('xupt0102', '信息安全系', 'xupt0001');
INSERT INTO `department` VALUES ('xupt0103', '电子信息科学与技术系', 'xupt0001');
INSERT INTO `department` VALUES ('xupt0104', '信息工程系', 'xupt0001');
INSERT INTO `department` VALUES ('xupt0105', '基础部', 'xupt0001');
INSERT INTO `department` VALUES ('xupt0106', '现代通信技术实验教学中心', 'xupt0001');
INSERT INTO `department` VALUES ('xupt0107', '信号处理实验教学中心', 'xupt0001');
INSERT INTO `department` VALUES ('xupt0108', '信息安全与信息对抗实验教学中心', 'xupt0001');
INSERT INTO `department` VALUES ('xupt0109', '通信基础实验教学中心', 'xupt0001');
INSERT INTO `department` VALUES ('xupt0110', '信息通信技术工程训练中心', 'xupt0001');
INSERT INTO `department` VALUES ('xupt0111', '无线网络安全技术国家工程实验室', 'xupt0001');
INSERT INTO `department` VALUES ('xupt0112', '电子信息现场勘验应用技术公安部重点实验室', 'xupt0001');
INSERT INTO `department` VALUES ('xupt0113', '陕西省信息通信网络及安全重点实验室', 'xupt0001');
INSERT INTO `department` VALUES ('xupt0114', '现代通信技术部级重点实验室', 'xupt0001');
INSERT INTO `department` VALUES ('xupt0115', '陕西省无线通信与信息处理技术国际联合研究中心', 'xupt0001');
INSERT INTO `department` VALUES ('xupt0116', '陕西省法庭科学电子信息试验研究中心', 'xupt0001');
INSERT INTO `department` VALUES ('xupt0117', '西安市智慧环保工程技术研究中心', 'xupt0001');
INSERT INTO `department` VALUES ('xupt0201', '光电子技术系', 'xupt0002');
INSERT INTO `department` VALUES ('xupt0202', '电子信息工程', 'xupt0002');
INSERT INTO `department` VALUES ('xupt0203', '微电子学系', 'xupt0002');
INSERT INTO `department` VALUES ('xupt0204', '电路与电子技术基础教学部', 'xupt0002');
INSERT INTO `department` VALUES ('xupt0205', '电工电子实验教学部', 'xupt0002');
INSERT INTO `department` VALUES ('xupt0301', '计算机科学与技术系', 'xupt0003');
INSERT INTO `department` VALUES ('xupt0302', '软件工程系', 'xupt0003');
INSERT INTO `department` VALUES ('xupt0303', '网络工程系', 'xupt0003');
INSERT INTO `department` VALUES ('xupt0304', '计算机基础教学部', 'xupt0003');
INSERT INTO `department` VALUES ('xupt0305', '计算机基础实验教学中心', 'xupt0003');
INSERT INTO `department` VALUES ('xupt0306', '计算机科学与技术实验教学中心', 'xupt0003');
INSERT INTO `department` VALUES ('xupt0307', 'IT应用型人才实训中心', 'xupt0003');
INSERT INTO `department` VALUES ('xupt0401', '自动控制系', 'xupt0004');
INSERT INTO `department` VALUES ('xupt0402', '测控技术与仪器系', 'xupt0004');
INSERT INTO `department` VALUES ('xupt0403', '智能科学与技术系', 'xupt0004');
INSERT INTO `department` VALUES ('xupt0404', '电气工程系', 'xupt0004');
INSERT INTO `department` VALUES ('xupt0405', '自动化测控实验室', 'xupt0004');
INSERT INTO `department` VALUES ('xupt0406', '智能实验室', 'xupt0004');
INSERT INTO `department` VALUES ('xupt0407', '自动化技术训练中心', 'xupt0004');
INSERT INTO `department` VALUES ('xupt0501', '经济学系', 'xupt0005');
INSERT INTO `department` VALUES ('xupt0502', '工商管理系', 'xupt0005');
INSERT INTO `department` VALUES ('xupt0503', '管理工程系', 'xupt0005');
INSERT INTO `department` VALUES ('xupt0504', '会计学系', 'xupt0005');
INSERT INTO `department` VALUES ('xupt0505', '物流与电商系', 'xupt0005');
INSERT INTO `department` VALUES ('xupt0506', '金融与信息系', 'xupt0005');
INSERT INTO `department` VALUES ('xupt0701', '应用物理系', 'xupt0007');
INSERT INTO `department` VALUES ('xupt0702', '应用数学系', 'xupt0007');
INSERT INTO `department` VALUES ('xupt0703', '大学数学教学部', 'xupt0007');
INSERT INTO `department` VALUES ('xupt0704', '大学物理教学部', 'xupt0007');
INSERT INTO `department` VALUES ('xupt0705', '物理实验教学中心', 'xupt0007');
INSERT INTO `department` VALUES ('xupt0706', '数学实验教学中心', 'xupt0007');
INSERT INTO `department` VALUES ('xupt0707', '信息与计算科学研究院', 'xupt0007');
INSERT INTO `department` VALUES ('xupt0708', '量子信息技术研究院', 'xupt0007');

-- ----------------------------
-- Table structure for login
-- ----------------------------
DROP TABLE IF EXISTS `login`;
CREATE TABLE `login`  (
  `id` char(8) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户名',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '用户昵称',
  `limit` enum('1','2','3','4','5','6','7','8','9') CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '1' COMMENT '权限等级',
  `pass` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '用户密码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '用户信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of login
-- ----------------------------
INSERT INTO `login` VALUES ('07161045', '无', '1', '甁甁甁甁甁甁甁甁');
INSERT INTO `login` VALUES ('07161065', '陈肥肥', '1', '畈畈甁甂甃甄甅甆');
INSERT INTO `login` VALUES ('07161075', '旁观者', '3', '畈畈甁甆甀甂甆甁甄畜畀畓');
INSERT INTO `login` VALUES ('07980001', '校长大人', '9', '畈畈甁甂甃甄甅甆');

-- ----------------------------
-- Table structure for major
-- ----------------------------
DROP TABLE IF EXISTS `major`;
CREATE TABLE `major`  (
  `id` char(8) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '专业编号',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '专业名称',
  `college_id` char(8) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '所属学院ID',
  `department_id` char(8) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '所属系/部ID',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `major_colleege`(`college_id`) USING BTREE,
  INDEX `major_department`(`department_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '专业信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of major
-- ----------------------------
INSERT INTO `major` VALUES ('ZY010101', '通信工程专业', 'xupt0001', 'xupt0101');
INSERT INTO `major` VALUES ('ZY010201', '信息安全专业', 'xupt0001', 'xupt0102');
INSERT INTO `major` VALUES ('ZY010202', '信息对抗专业', 'xupt0001', 'xupt0102');
INSERT INTO `major` VALUES ('ZY010301', '电子信息科学与技术专业', 'xupt0001', 'xupt0103');
INSERT INTO `major` VALUES ('ZY010302', '物联网工程专业', 'xupt0001', 'xupt0103');
INSERT INTO `major` VALUES ('ZY010401', '信息工程专业', 'xupt0001', 'xupt0104');
INSERT INTO `major` VALUES ('ZY010402', '广播电视工程专业', 'xupt0001', 'xupt0104');
INSERT INTO `major` VALUES ('ZY030101', '计算机科学与技术', 'xupt0003', 'xupt0301');
INSERT INTO `major` VALUES ('ZY030201', '软件工程', 'xupt0003', 'xupt0302');
INSERT INTO `major` VALUES ('ZY030301', '网络工程', 'xupt0003', 'xupt0303');
INSERT INTO `major` VALUES ('ZY070101', '应用物理', 'xupt0007', 'xupt0701');
INSERT INTO `major` VALUES ('ZY070201', '信息与计算科学', 'xupt0007', 'xupt0702');

-- ----------------------------
-- Table structure for offering_courses
-- ----------------------------
DROP TABLE IF EXISTS `offering_courses`;
CREATE TABLE `offering_courses`  (
  `id` char(10) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '开课班级序号',
  `course_id` char(8) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '课程编号',
  `teacher_id` char(8) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '授课教师',
  `begin` date NOT NULL COMMENT '开始时间',
  `school_year_id` char(8) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '开课学年',
  `school_trem_id` char(8) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '开课学期',
  `semester_id` char(8) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '授课年级',
  `max_num` int(3) NOT NULL COMMENT '班级人数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '开课信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of offering_courses
-- ----------------------------
INSERT INTO `offering_courses` VALUES ('0702000101', '07020003', 'TR072002', '2019-01-22', '20162017', 'ST161701', 'SEME2016', 75);
INSERT INTO `offering_courses` VALUES ('0702000102', '07020002', 'TR072002', '2018-09-03', '20162017', 'ST161701', 'SEME2016', 75);

-- ----------------------------
-- Table structure for result
-- ----------------------------
DROP TABLE IF EXISTS `result`;
CREATE TABLE `result`  (
  `id` int(10) NOT NULL COMMENT '自动序号',
  `student_id` char(8) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '学生学号',
  `course_id` char(8) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '所选课程班',
  `result` double(3, 2) NOT NULL COMMENT '成绩',
  `time` datetime(0) NOT NULL COMMENT '录入时间',
  `teacher_id` char(8) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '录入教师',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '成绩表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for school_trem
-- ----------------------------
DROP TABLE IF EXISTS `school_trem`;
CREATE TABLE `school_trem`  (
  `id` char(8) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '学期编号',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '学期名称',
  `school_year` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '所属学年',
  `begin` date NOT NULL COMMENT '开始时间',
  `end` date NOT NULL COMMENT '结束时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '学期' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of school_trem
-- ----------------------------
INSERT INTO `school_trem` VALUES ('ST161701', '2016到2017学年度第一学期', '20162017', '2016-09-04', '2017-01-20');
INSERT INTO `school_trem` VALUES ('ST171801', '2017到2018学年度第一学期', '20172018', '2017-09-03', '2018-01-15');
INSERT INTO `school_trem` VALUES ('ST171802', '2017到2018学年度第一学期', '20172018', '2018-03-04', '2019-07-15');
INSERT INTO `school_trem` VALUES ('ST181901', '2018到2019学年度第一学期', '20182019', '2018-09-03', '2019-01-15');
INSERT INTO `school_trem` VALUES ('ST181902', '2018到2019学年度第二学期', '20182019', '2019-03-03', '2019-07-17');
INSERT INTO `school_trem` VALUES ('ST192001', '2019到2020学年度第一学期', '20192020', '2019-09-01', '2019-07-16');
INSERT INTO `school_trem` VALUES ('ST192002', '2019到2020学年度第二学期', '20192020', '2019-03-03', '2020-01-15');

-- ----------------------------
-- Table structure for school_year
-- ----------------------------
DROP TABLE IF EXISTS `school_year`;
CREATE TABLE `school_year`  (
  `id` char(8) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '学年编号',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '学年名称',
  `begin` date NOT NULL COMMENT '开始时间',
  `end` date NOT NULL COMMENT '结束时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '学年' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of school_year
-- ----------------------------
INSERT INTO `school_year` VALUES ('20162017', '2016到2017学年', '2016-09-04', '2017-07-17');
INSERT INTO `school_year` VALUES ('20172018', '2017到2018学年', '2017-09-03', '2018-07-15');
INSERT INTO `school_year` VALUES ('20182019', '2018到2019学年', '2018-09-02', '2019-07-15');
INSERT INTO `school_year` VALUES ('20192020', '2019到2020学年', '2019-09-03', '2020-07-15');

-- ----------------------------
-- Table structure for semester
-- ----------------------------
DROP TABLE IF EXISTS `semester`;
CREATE TABLE `semester`  (
  `id` char(8) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '年级编号',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '年级名称',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '年级' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of semester
-- ----------------------------
INSERT INTO `semester` VALUES ('SEME2016', '2016级');
INSERT INTO `semester` VALUES ('SEME2017', '2017级');
INSERT INTO `semester` VALUES ('SEME2018', '2018级');
INSERT INTO `semester` VALUES ('SEME2019', '2019级');
INSERT INTO `semester` VALUES ('SEME2020', '2020级');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `id` char(8) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '学号',
  `year` date NOT NULL COMMENT '入学年份',
  `college` char(8) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '所属学院',
  `department` char(8) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '所属系/部',
  `major` char(8) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '所属专业',
  `grade` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '所属年级',
  `class` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '所属班级',
  `culture_level` enum('专科','本科','硕士','博士') CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '培养层次',
  `student_type` enum('普通专科生','普通本科生','硕士研究生','博士生') CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '学生类别',
  `Education` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '学历',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '学生学籍信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('07161045', '2018-11-06', 'xupt0007', 'xupt0702', '07020001', '2016级', '信息1602', '本科', '普通本科生', '');
INSERT INTO `student` VALUES ('07161065', '2018-11-06', 'xupt0001', 'xupt0101', 'ZY010101', 'SEME2016', '07021801', '本科', '普通本科生', '');
INSERT INTO `student` VALUES ('07161075', '2018-11-06', 'xupt0007', 'xupt0702', '07020001', '2018级', '信息1802', '本科', '普通本科生', '');
INSERT INTO `student` VALUES ('07161079', '2018-11-06', 'xupt0007', 'xupt0702', '07020001', '2016级 ', '信息1602', '本科', '普通本科生', '');
INSERT INTO `student` VALUES ('07171001', '2018-11-17', 'xupt0001', 'xupt0101', 'ZY010101', 'SEME2016', '07021801', '本科', '普通本科生', '');

-- ----------------------------
-- Table structure for student_course
-- ----------------------------
DROP TABLE IF EXISTS `student_course`;
CREATE TABLE `student_course`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自动序号',
  `student_id` char(8) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '学生学号',
  `course_id` char(10) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '授课班',
  `school_year_id` char(8) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '选课学年',
  `school_trem_id` char(8) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '选课学期',
  `time` date NOT NULL COMMENT '选课时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '学生选课信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student_course
-- ----------------------------
INSERT INTO `student_course` VALUES (2, '07161075', '0702000101', '20182019', 'ST181901', '2018-09-01');
INSERT INTO `student_course` VALUES (3, '07161075', '0702000102', '20182019', 'ST181901', '2019-02-06');

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher`  (
  `id` char(8) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '工号',
  `college` char(8) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '所属学院',
  `department` char(8) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '所属系/部',
  `level` enum('助教','讲师','副教授','教授') CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '职称',
  `Education` enum('专科','本科','硕士','博士') CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '学历',
  `year` date NOT NULL COMMENT '入职时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '教师信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('TR020001', 'xupt0001', 'xupt0201', '教授', '博士', '2018-11-21');
INSERT INTO `teacher` VALUES ('TR030001', 'xupt0003', 'xupt0301', '教授', '博士', '2018-11-22');
INSERT INTO `teacher` VALUES ('TR040001', 'xupt0004', 'xupt0401', '教授', '博士', '2018-11-22');
INSERT INTO `teacher` VALUES ('TR051001', 'xupt0005', 'xupt0501', '教授', '博士', '2018-11-23');
INSERT INTO `teacher` VALUES ('TR061001', 'xupt0006', 'xupt0101', '教授', '博士', '2018-11-23');
INSERT INTO `teacher` VALUES ('TR071001', 'xupt0007', 'xupt0701', '教授', '博士', '2018-11-23');
INSERT INTO `teacher` VALUES ('TR072001', 'xupt0007', 'xupt0702', '教授', '博士', '2019-01-22');
INSERT INTO `teacher` VALUES ('TR072002', 'xupt0007', 'xupt0702', '讲师', '博士', '2018-10-29');
INSERT INTO `teacher` VALUES ('TR072003', 'xupt0007', 'xupt0702', '助教', '博士', '2018-11-03');
INSERT INTO `teacher` VALUES ('TR072004', 'xupt0007', 'xupt0702', '教授', '博士', '2018-11-06');
INSERT INTO `teacher` VALUES ('TR072005', 'xupt0007', 'xupt0702', '助教', '硕士', '2018-11-06');
INSERT INTO `teacher` VALUES ('TR072006', 'xupt0007', 'xupt0702', '教授', '博士', '2019-01-24');
INSERT INTO `teacher` VALUES ('TR081001', 'xupt0002', 'xupt0101', '教授', '博士', '2018-11-23');
INSERT INTO `teacher` VALUES ('TR081002', 'xupt0008', 'xupt0101', '教授', '博士', '2019-01-24');
INSERT INTO `teacher` VALUES ('TR091001', 'xupt0009', 'xupt0101', '教授', '博士', '2018-11-23');
INSERT INTO `teacher` VALUES ('TR101001', 'xupt0010', 'xupt0101', '教授', '博士', '2018-11-23');
INSERT INTO `teacher` VALUES ('TR111001', 'xupt0011', 'xupt0101', '教授', '博士', '2018-11-23');
INSERT INTO `teacher` VALUES ('TR121001', 'xupt0012', 'xupt0101', '教授', '博士', '2018-11-23');
INSERT INTO `teacher` VALUES ('TR131001', 'xupt0013', 'xupt0101', '教授', '博士', '2018-11-23');
INSERT INTO `teacher` VALUES ('TR141001', 'xupt0014', 'xupt0101', '教授', '博士', '2018-11-23');
INSERT INTO `teacher` VALUES ('TR151001', 'xupt0015', 'xupt0101', '教授', '博士', '2018-11-23');

-- ----------------------------
-- Table structure for xclass
-- ----------------------------
DROP TABLE IF EXISTS `xclass`;
CREATE TABLE `xclass`  (
  `id` char(8) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '班级编号',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '班级名称',
  `number` int(2) NOT NULL COMMENT '班级人数',
  `college_id` char(8) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '所属学院',
  `department_id` char(8) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '所属系/部',
  `major_id` char(8) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '所属专业',
  `semester` char(8) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '所属年级',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '班级信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of xclass
-- ----------------------------
INSERT INTO `xclass` VALUES ('07011601', '物理1601', 35, 'xupt0007', 'xupt0701', 'ZY070101', 'SEME2016');
INSERT INTO `xclass` VALUES ('07011602', '物理1602', 33, 'xupt0007', 'xupt0701', 'ZY010101', 'SEME2016');
INSERT INTO `xclass` VALUES ('07011801', '物理1801', 38, 'xupt0007', 'xupt0701', 'ZY070101', 'SEME2018');
INSERT INTO `xclass` VALUES ('07011802', '物理1802', 34, 'xupt0007', 'xupt0701', 'ZY070101', 'SEME2018');
INSERT INTO `xclass` VALUES ('07021601', '信息1601', 36, 'xupt0007', 'xupt0702', 'ZY070201', 'SEME2016');
INSERT INTO `xclass` VALUES ('07021602', '信息1602', 35, 'xupt0007', 'xupt0702', 'ZY070201', 'SEME2016');
INSERT INTO `xclass` VALUES ('07021801', '信息1801', 38, 'xupt0007', 'xupt0702', '0702ZY01', 'SEME2018');
INSERT INTO `xclass` VALUES ('07021802', '信息1802', 38, 'xupt0007', 'xupt0702', '0702ZY01', 'SEME2018');

SET FOREIGN_KEY_CHECKS = 1;
