/*
Navicat MySQL Data Transfer

Source Server         : 阿里云-185自建mysql
Source Server Version : 50626
Source Host           : 47.107.150.185:3306
Source Database       : weiit_task

Target Server Type    : MYSQL
Target Server Version : 50626
File Encoding         : 65001

Date: 2020-12-25 17:55:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for xxl_job_qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_qrtz_blob_triggers`;
CREATE TABLE `xxl_job_qrtz_blob_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `xxl_job_qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `xxl_job_qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xxl_job_qrtz_blob_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for xxl_job_qrtz_calendars
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_qrtz_calendars`;
CREATE TABLE `xxl_job_qrtz_calendars` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `CALENDAR_NAME` varchar(200) NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xxl_job_qrtz_calendars
-- ----------------------------

-- ----------------------------
-- Table structure for xxl_job_qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_qrtz_cron_triggers`;
CREATE TABLE `xxl_job_qrtz_cron_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `CRON_EXPRESSION` varchar(200) NOT NULL,
  `TIME_ZONE_ID` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `xxl_job_qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `xxl_job_qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xxl_job_qrtz_cron_triggers
-- ----------------------------
INSERT INTO `xxl_job_qrtz_cron_triggers` VALUES ('quartzScheduler', '14', '5', '0 0/3 * * * ?', 'PRC');
INSERT INTO `xxl_job_qrtz_cron_triggers` VALUES ('quartzScheduler', '15', '5', '0 0/30 * * * ?', 'PRC');
INSERT INTO `xxl_job_qrtz_cron_triggers` VALUES ('quartzScheduler', '16', '5', '0 0/3 * * * ?', 'PRC');
INSERT INTO `xxl_job_qrtz_cron_triggers` VALUES ('quartzScheduler', '17', '5', '0 30 5 * * ? *', 'PRC');
INSERT INTO `xxl_job_qrtz_cron_triggers` VALUES ('quartzScheduler', '18', '5', '0 0/30 * * * ?', 'PRC');
INSERT INTO `xxl_job_qrtz_cron_triggers` VALUES ('quartzScheduler', '19', '5', '0 0/30 * * * ?', 'PRC');
INSERT INTO `xxl_job_qrtz_cron_triggers` VALUES ('quartzScheduler', '20', '5', '0 0/30 * * * ?', 'PRC');
INSERT INTO `xxl_job_qrtz_cron_triggers` VALUES ('quartzScheduler', '21', '5', '0 0/30 * * * ?', 'PRC');
INSERT INTO `xxl_job_qrtz_cron_triggers` VALUES ('quartzScheduler', '22', '5', '0 30 5 * * ? *', 'PRC');
INSERT INTO `xxl_job_qrtz_cron_triggers` VALUES ('quartzScheduler', '23', '5', '0 10 4 * * ? *', 'PRC');
INSERT INTO `xxl_job_qrtz_cron_triggers` VALUES ('quartzScheduler', '24', '5', '0 0/3 * * * ?', 'PRC');
INSERT INTO `xxl_job_qrtz_cron_triggers` VALUES ('quartzScheduler', '25', '5', '0 0/3 * * * ?', 'PRC');

-- ----------------------------
-- Table structure for xxl_job_qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_qrtz_fired_triggers`;
CREATE TABLE `xxl_job_qrtz_fired_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `ENTRY_ID` varchar(95) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) NOT NULL,
  `JOB_NAME` varchar(200) DEFAULT NULL,
  `JOB_GROUP` varchar(200) DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xxl_job_qrtz_fired_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for xxl_job_qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_qrtz_job_details`;
CREATE TABLE `xxl_job_qrtz_job_details` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) NOT NULL,
  `IS_DURABLE` varchar(1) NOT NULL,
  `IS_NONCONCURRENT` varchar(1) NOT NULL,
  `IS_UPDATE_DATA` varchar(1) NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) NOT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xxl_job_qrtz_job_details
-- ----------------------------
INSERT INTO `xxl_job_qrtz_job_details` VALUES ('quartzScheduler', '14', '5', null, 'com.xxl.job.admin.core.jobbean.RemoteHttpJobBean', '0', '0', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787000737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F40000000000010770800000010000000007800);
INSERT INTO `xxl_job_qrtz_job_details` VALUES ('quartzScheduler', '15', '5', null, 'com.xxl.job.admin.core.jobbean.RemoteHttpJobBean', '0', '0', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787000737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F40000000000010770800000010000000007800);
INSERT INTO `xxl_job_qrtz_job_details` VALUES ('quartzScheduler', '16', '5', null, 'com.xxl.job.admin.core.jobbean.RemoteHttpJobBean', '0', '0', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787000737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F40000000000010770800000010000000007800);
INSERT INTO `xxl_job_qrtz_job_details` VALUES ('quartzScheduler', '17', '5', null, 'com.xxl.job.admin.core.jobbean.RemoteHttpJobBean', '0', '0', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787000737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F40000000000010770800000010000000007800);
INSERT INTO `xxl_job_qrtz_job_details` VALUES ('quartzScheduler', '18', '5', null, 'com.xxl.job.admin.core.jobbean.RemoteHttpJobBean', '0', '0', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787000737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F40000000000010770800000010000000007800);
INSERT INTO `xxl_job_qrtz_job_details` VALUES ('quartzScheduler', '19', '5', null, 'com.xxl.job.admin.core.jobbean.RemoteHttpJobBean', '0', '0', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787000737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F40000000000010770800000010000000007800);
INSERT INTO `xxl_job_qrtz_job_details` VALUES ('quartzScheduler', '20', '5', null, 'com.xxl.job.admin.core.jobbean.RemoteHttpJobBean', '0', '0', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787000737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F40000000000010770800000010000000007800);
INSERT INTO `xxl_job_qrtz_job_details` VALUES ('quartzScheduler', '21', '5', null, 'com.xxl.job.admin.core.jobbean.RemoteHttpJobBean', '0', '0', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787000737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F40000000000010770800000010000000007800);
INSERT INTO `xxl_job_qrtz_job_details` VALUES ('quartzScheduler', '22', '5', null, 'com.xxl.job.admin.core.jobbean.RemoteHttpJobBean', '0', '0', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787000737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F40000000000010770800000010000000007800);
INSERT INTO `xxl_job_qrtz_job_details` VALUES ('quartzScheduler', '23', '5', null, 'com.xxl.job.admin.core.jobbean.RemoteHttpJobBean', '0', '0', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787000737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F40000000000010770800000010000000007800);
INSERT INTO `xxl_job_qrtz_job_details` VALUES ('quartzScheduler', '24', '5', null, 'com.xxl.job.admin.core.jobbean.RemoteHttpJobBean', '0', '0', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787000737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F40000000000010770800000010000000007800);
INSERT INTO `xxl_job_qrtz_job_details` VALUES ('quartzScheduler', '25', '5', null, 'com.xxl.job.admin.core.jobbean.RemoteHttpJobBean', '0', '0', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787000737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F40000000000010770800000010000000007800);

-- ----------------------------
-- Table structure for xxl_job_qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_qrtz_locks`;
CREATE TABLE `xxl_job_qrtz_locks` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `LOCK_NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xxl_job_qrtz_locks
-- ----------------------------
INSERT INTO `xxl_job_qrtz_locks` VALUES ('quartzScheduler', 'STATE_ACCESS');
INSERT INTO `xxl_job_qrtz_locks` VALUES ('quartzScheduler', 'TRIGGER_ACCESS');

-- ----------------------------
-- Table structure for xxl_job_qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_qrtz_paused_trigger_grps`;
CREATE TABLE `xxl_job_qrtz_paused_trigger_grps` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xxl_job_qrtz_paused_trigger_grps
-- ----------------------------

-- ----------------------------
-- Table structure for xxl_job_qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_qrtz_scheduler_state`;
CREATE TABLE `xxl_job_qrtz_scheduler_state` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xxl_job_qrtz_scheduler_state
-- ----------------------------
INSERT INTO `xxl_job_qrtz_scheduler_state` VALUES ('quartzScheduler', 'VM-0-8-centos1607528361961', '1608889892937', '5000');

-- ----------------------------
-- Table structure for xxl_job_qrtz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_qrtz_simple_triggers`;
CREATE TABLE `xxl_job_qrtz_simple_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `xxl_job_qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `xxl_job_qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xxl_job_qrtz_simple_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for xxl_job_qrtz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_qrtz_simprop_triggers`;
CREATE TABLE `xxl_job_qrtz_simprop_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `STR_PROP_1` varchar(512) DEFAULT NULL,
  `STR_PROP_2` varchar(512) DEFAULT NULL,
  `STR_PROP_3` varchar(512) DEFAULT NULL,
  `INT_PROP_1` int(11) DEFAULT NULL,
  `INT_PROP_2` int(11) DEFAULT NULL,
  `LONG_PROP_1` bigint(20) DEFAULT NULL,
  `LONG_PROP_2` bigint(20) DEFAULT NULL,
  `DEC_PROP_1` decimal(13,4) DEFAULT NULL,
  `DEC_PROP_2` decimal(13,4) DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `xxl_job_qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `xxl_job_qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xxl_job_qrtz_simprop_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for xxl_job_qrtz_trigger_group
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_qrtz_trigger_group`;
CREATE TABLE `xxl_job_qrtz_trigger_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `app_name` varchar(64) NOT NULL COMMENT '执行器AppName',
  `title` varchar(12) NOT NULL COMMENT '执行器名称',
  `order` tinyint(4) NOT NULL DEFAULT '0' COMMENT '排序',
  `address_type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '执行器地址类型：0=自动注册、1=手动录入',
  `address_list` varchar(512) DEFAULT NULL COMMENT '执行器地址列表，多地址逗号分隔',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xxl_job_qrtz_trigger_group
-- ----------------------------
INSERT INTO `xxl_job_qrtz_trigger_group` VALUES ('5', 'weiit-saas', 'SAAS相关定时任务', '1', '1', '47.107.125.138:9998');

-- ----------------------------
-- Table structure for xxl_job_qrtz_trigger_info
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_qrtz_trigger_info`;
CREATE TABLE `xxl_job_qrtz_trigger_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `job_group` int(11) NOT NULL COMMENT '执行器主键ID',
  `job_cron` varchar(128) NOT NULL COMMENT '任务执行CRON',
  `job_desc` varchar(255) NOT NULL,
  `add_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `author` varchar(64) DEFAULT NULL COMMENT '作者',
  `alarm_email` varchar(255) DEFAULT NULL COMMENT '报警邮件',
  `executor_route_strategy` varchar(50) DEFAULT NULL COMMENT '执行器路由策略',
  `executor_handler` varchar(255) DEFAULT NULL COMMENT '执行器任务handler',
  `executor_param` varchar(512) DEFAULT NULL COMMENT '执行器任务参数',
  `executor_block_strategy` varchar(50) DEFAULT NULL COMMENT '阻塞处理策略',
  `executor_fail_strategy` varchar(50) DEFAULT NULL COMMENT '失败处理策略',
  `glue_type` varchar(50) NOT NULL COMMENT 'GLUE类型',
  `glue_source` text COMMENT 'GLUE源代码',
  `glue_remark` varchar(128) DEFAULT NULL COMMENT 'GLUE备注',
  `glue_updatetime` datetime DEFAULT NULL COMMENT 'GLUE更新时间',
  `child_jobid` varchar(255) DEFAULT NULL COMMENT '子任务ID，多个逗号分隔',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xxl_job_qrtz_trigger_info
-- ----------------------------
INSERT INTO `xxl_job_qrtz_trigger_info` VALUES ('14', '5', '0 0/3 * * * ?', '砍价活动超时失效，3分钟检查一次', '2018-08-06 20:28:05', '2018-08-06 20:42:04', '小罗', '', 'FIRST', 'bargainEndTask', '', 'SERIAL_EXECUTION', 'FAIL_ALARM', 'BEAN', '', 'GLUE代码初始化', '2018-08-06 20:28:05', '');
INSERT INTO `xxl_job_qrtz_trigger_info` VALUES ('15', '5', '0 0/30 * * * ?', '用户发起的砍价到期未支付失效，30分钟检查一次', '2018-08-06 20:29:56', '2018-08-06 20:41:27', '小罗', '', 'FIRST', 'bargainOrderEndTask', '', 'SERIAL_EXECUTION', 'FAIL_ALARM', 'BEAN', '', 'GLUE代码初始化', '2018-08-06 20:29:56', '');
INSERT INTO `xxl_job_qrtz_trigger_info` VALUES ('16', '5', '0 0/3 * * * ?', '砍价活动自动生效，3分钟检查一次', '2018-08-06 20:31:07', '2018-08-06 20:41:01', '小罗', '', 'FIRST', 'bargainStartTask', '', 'SERIAL_EXECUTION', 'FAIL_ALARM', 'BEAN', '', 'GLUE代码初始化', '2018-08-06 20:31:07', '');
INSERT INTO `xxl_job_qrtz_trigger_info` VALUES ('17', '5', '0 30 5 * * ? *', '关闭已失效的优惠券，凌晨5点半检查一次', '2018-08-06 20:33:15', '2018-08-06 20:40:27', '小罗', '', 'FIRST', 'couponEndTask', '', 'SERIAL_EXECUTION', 'FAIL_ALARM', 'BEAN', '', 'GLUE代码初始化', '2018-08-06 20:33:15', '');
INSERT INTO `xxl_job_qrtz_trigger_info` VALUES ('18', '5', '0 0/30 * * * ?', '优惠券自动开启，30分钟检查一次', '2018-08-06 20:34:47', '2018-08-06 20:39:59', '小罗', '', 'FIRST', 'couponStartTask', '', 'SERIAL_EXECUTION', 'FAIL_ALARM', 'BEAN', '', 'GLUE代码初始化', '2018-08-06 20:34:47', '');
INSERT INTO `xxl_job_qrtz_trigger_info` VALUES ('19', '5', '0 0/30 * * * ?', '拼团活动结束关闭活动，30分钟检查一次', '2018-08-06 20:35:45', '2018-08-06 20:41:46', '小罗', '', 'FIRST', 'grouponEndTask', '', 'SERIAL_EXECUTION', 'FAIL_ALARM', 'BEAN', '', 'GLUE代码初始化', '2018-08-06 20:35:45', '');
INSERT INTO `xxl_job_qrtz_trigger_info` VALUES ('20', '5', '0 0/30 * * * ?', '拼团活动结束未完成订单关闭，30分钟检查一次', '2018-08-06 20:36:48', '2018-08-06 20:41:38', '小罗', '', 'FIRST', 'GrouponOrderEndTask', '', 'SERIAL_EXECUTION', 'FAIL_ALARM', 'BEAN', '', 'GLUE代码初始化', '2018-08-06 20:36:48', '');
INSERT INTO `xxl_job_qrtz_trigger_info` VALUES ('21', '5', '0 0/30 * * * ?', '拼团活动自动开启，30分钟检查一次', '2018-08-06 20:37:50', '2018-08-06 20:37:50', '小罗', '', 'FIRST', 'grouponStartTask', '', 'SERIAL_EXECUTION', 'FAIL_ALARM', 'BEAN', '', 'GLUE代码初始化', '2018-08-06 20:37:50', '');
INSERT INTO `xxl_job_qrtz_trigger_info` VALUES ('22', '5', '0 30 5 * * ? *', '商户自动到期关闭，每天凌晨5点半检查一次', '2018-08-06 20:43:22', '2018-08-06 20:43:22', '小罗', '', 'FIRST', 'merchantExpireTask', '', 'SERIAL_EXECUTION', 'FAIL_ALARM', 'BEAN', '', 'GLUE代码初始化', '2018-08-06 20:43:22', '');
INSERT INTO `xxl_job_qrtz_trigger_info` VALUES ('23', '5', '0 10 4 * * ? *', '15天未付款的订单自动关闭，每天凌晨4点10分检查一次', '2018-08-06 20:44:43', '2018-08-06 20:44:43', '小罗', '', 'FIRST', 'orderConfirmTask', '', 'SERIAL_EXECUTION', 'FAIL_ALARM', 'BEAN', '', 'GLUE代码初始化', '2018-08-06 20:44:43', '');
INSERT INTO `xxl_job_qrtz_trigger_info` VALUES ('24', '5', '0 0/3 * * * ?', '用户优惠券超时未使用，每天3点10分检查一次', '2018-08-06 20:45:49', '2018-08-14 15:36:13', '小罗', '', 'FIRST', 'userCouponEndTask', '', 'SERIAL_EXECUTION', 'FAIL_ALARM', 'BEAN', '', 'GLUE代码初始化', '2018-08-06 20:45:49', '');
INSERT INTO `xxl_job_qrtz_trigger_info` VALUES ('25', '5', '0 0/3 * * * ?', '用户领取优惠券自动生效，每3分钟检查一次', '2018-08-06 20:46:52', '2018-08-06 20:46:52', '小罗', '', 'FIRST', 'userCouponStartTask', '', 'SERIAL_EXECUTION', 'FAIL_ALARM', 'BEAN', '', 'GLUE代码初始化', '2018-08-06 20:46:52', '');

-- ----------------------------
-- Table structure for xxl_job_qrtz_trigger_log
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_qrtz_trigger_log`;
CREATE TABLE `xxl_job_qrtz_trigger_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `job_group` int(11) NOT NULL COMMENT '执行器主键ID',
  `job_id` int(11) NOT NULL COMMENT '任务，主键ID',
  `glue_type` varchar(50) DEFAULT NULL COMMENT 'GLUE类型',
  `executor_address` varchar(255) DEFAULT NULL COMMENT '执行器地址，本次执行的地址',
  `executor_handler` varchar(255) DEFAULT NULL COMMENT '执行器任务handler',
  `executor_param` varchar(512) DEFAULT NULL COMMENT '执行器任务参数',
  `trigger_time` datetime DEFAULT NULL COMMENT '调度-时间',
  `trigger_code` int(11) NOT NULL COMMENT '调度-结果',
  `trigger_msg` varchar(2048) DEFAULT NULL COMMENT '调度-日志',
  `handle_time` datetime DEFAULT NULL COMMENT '执行-时间',
  `handle_code` int(11) NOT NULL COMMENT '执行-状态',
  `handle_msg` varchar(2048) DEFAULT NULL COMMENT '执行-日志',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1126847 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xxl_job_qrtz_trigger_log
-- ----------------------------
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES ('291675', '5', '14', 'BEAN', '47.107.125.138:9998', 'bargainEndTask', '', '2018-12-21 08:36:00', '200', '调度机器：172.16.16.5<br>执行器-注册方式：手动录入<br>执行器-地址列表：[47.107.125.138:9998]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：47.107.125.138:9998<br>code：200<br>msg：null', '2018-12-21 08:36:00', '200', '');
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES ('291676', '5', '16', 'BEAN', '47.107.125.138:9998', 'bargainStartTask', '', '2018-12-21 08:36:00', '200', '调度机器：172.16.16.5<br>执行器-注册方式：手动录入<br>执行器-地址列表：[47.107.125.138:9998]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：47.107.125.138:9998<br>code：200<br>msg：null', '2018-12-21 08:36:00', '200', '');
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES ('291677', '5', '24', 'BEAN', '47.107.125.138:9998', 'userCouponEndTask', '', '2018-12-21 08:36:00', '200', '调度机器：172.16.16.5<br>执行器-注册方式：手动录入<br>执行器-地址列表：[47.107.125.138:9998]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：47.107.125.138:9998<br>code：200<br>msg：null', '2018-12-21 08:36:00', '200', '');
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES ('291678', '5', '25', 'BEAN', '47.107.125.138:9998', 'userCouponStartTask', '', '2018-12-21 08:36:00', '200', '调度机器：172.16.16.5<br>执行器-注册方式：手动录入<br>执行器-地址列表：[47.107.125.138:9998]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：47.107.125.138:9998<br>code：200<br>msg：null', '2018-12-21 08:36:00', '200', '');
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES ('291679', '5', '14', 'BEAN', '47.107.125.138:9998', 'bargainEndTask', '', '2018-12-21 08:39:00', '200', '调度机器：172.16.16.5<br>执行器-注册方式：手动录入<br>执行器-地址列表：[47.107.125.138:9998]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：47.107.125.138:9998<br>code：200<br>msg：null', '2018-12-21 08:39:00', '200', '');
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES ('291680', '5', '16', 'BEAN', '47.107.125.138:9998', 'bargainStartTask', '', '2018-12-21 08:39:00', '200', '调度机器：172.16.16.5<br>执行器-注册方式：手动录入<br>执行器-地址列表：[47.107.125.138:9998]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：47.107.125.138:9998<br>code：200<br>msg：null', '2018-12-21 08:39:00', '200', '');
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES ('291681', '5', '24', 'BEAN', '47.107.125.138:9998', 'userCouponEndTask', '', '2018-12-21 08:39:00', '200', '调度机器：172.16.16.5<br>执行器-注册方式：手动录入<br>执行器-地址列表：[47.107.125.138:9998]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：47.107.125.138:9998<br>code：200<br>msg：null', '2018-12-21 08:39:00', '200', '');
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES ('291682', '5', '25', 'BEAN', '47.107.125.138:9998', 'userCouponStartTask', '', '2018-12-21 08:39:00', '200', '调度机器：172.16.16.5<br>执行器-注册方式：手动录入<br>执行器-地址列表：[47.107.125.138:9998]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：47.107.125.138:9998<br>code：200<br>msg：null', '2018-12-21 08:39:00', '200', '');
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES ('291683', '5', '14', 'BEAN', '47.107.125.138:9998', 'bargainEndTask', '', '2018-12-21 08:42:00', '200', '调度机器：172.16.16.5<br>执行器-注册方式：手动录入<br>执行器-地址列表：[47.107.125.138:9998]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：47.107.125.138:9998<br>code：200<br>msg：null', '2018-12-21 08:42:00', '200', '');
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES ('291684', '5', '16', 'BEAN', '47.107.125.138:9998', 'bargainStartTask', '', '2018-12-21 08:42:00', '200', '调度机器：172.16.16.5<br>执行器-注册方式：手动录入<br>执行器-地址列表：[47.107.125.138:9998]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：47.107.125.138:9998<br>code：200<br>msg：null', '2018-12-21 08:42:00', '200', '');
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES ('291685', '5', '24', 'BEAN', '47.107.125.138:9998', 'userCouponEndTask', '', '2018-12-21 08:42:00', '200', '调度机器：172.16.16.5<br>执行器-注册方式：手动录入<br>执行器-地址列表：[47.107.125.138:9998]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：47.107.125.138:9998<br>code：200<br>msg：null', '2018-12-21 08:42:00', '200', '');
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES ('291686', '5', '25', 'BEAN', '47.107.125.138:9998', 'userCouponStartTask', '', '2018-12-21 08:42:00', '200', '调度机器：172.16.16.5<br>执行器-注册方式：手动录入<br>执行器-地址列表：[47.107.125.138:9998]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：47.107.125.138:9998<br>code：200<br>msg：null', '2018-12-21 08:42:00', '200', '');
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES ('291687', '5', '14', 'BEAN', '47.107.125.138:9998', 'bargainEndTask', '', '2018-12-21 08:45:00', '200', '调度机器：172.16.16.5<br>执行器-注册方式：手动录入<br>执行器-地址列表：[47.107.125.138:9998]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：47.107.125.138:9998<br>code：200<br>msg：null', '2018-12-21 08:45:00', '200', '');
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES ('291688', '5', '16', 'BEAN', '47.107.125.138:9998', 'bargainStartTask', '', '2018-12-21 08:45:00', '200', '调度机器：172.16.16.5<br>执行器-注册方式：手动录入<br>执行器-地址列表：[47.107.125.138:9998]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：47.107.125.138:9998<br>code：200<br>msg：null', '2018-12-21 08:45:00', '200', '');
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES ('291689', '5', '24', 'BEAN', '47.107.125.138:9998', 'userCouponEndTask', '', '2018-12-21 08:45:00', '200', '调度机器：172.16.16.5<br>执行器-注册方式：手动录入<br>执行器-地址列表：[47.107.125.138:9998]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：47.107.125.138:9998<br>code：200<br>msg：null', '2018-12-21 08:45:00', '200', '');
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES ('291690', '5', '25', 'BEAN', '47.107.125.138:9998', 'userCouponStartTask', '', '2018-12-21 08:45:00', '200', '调度机器：172.16.16.5<br>执行器-注册方式：手动录入<br>执行器-地址列表：[47.107.125.138:9998]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：47.107.125.138:9998<br>code：200<br>msg：null', '2018-12-21 08:45:00', '200', '');
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES ('291691', '5', '14', 'BEAN', '47.107.125.138:9998', 'bargainEndTask', '', '2018-12-21 08:48:00', '200', '调度机器：172.16.16.5<br>执行器-注册方式：手动录入<br>执行器-地址列表：[47.107.125.138:9998]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：47.107.125.138:9998<br>code：200<br>msg：null', '2018-12-21 08:48:00', '200', '');
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES ('291692', '5', '16', 'BEAN', '47.107.125.138:9998', 'bargainStartTask', '', '2018-12-21 08:48:00', '200', '调度机器：172.16.16.5<br>执行器-注册方式：手动录入<br>执行器-地址列表：[47.107.125.138:9998]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：47.107.125.138:9998<br>code：200<br>msg：null', '2018-12-21 08:48:00', '200', '');
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES ('291693', '5', '24', 'BEAN', '47.107.125.138:9998', 'userCouponEndTask', '', '2018-12-21 08:48:00', '200', '调度机器：172.16.16.5<br>执行器-注册方式：手动录入<br>执行器-地址列表：[47.107.125.138:9998]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：47.107.125.138:9998<br>code：200<br>msg：null', '2018-12-21 08:48:00', '200', '');
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES ('291694', '5', '25', 'BEAN', '47.107.125.138:9998', 'userCouponStartTask', '', '2018-12-21 08:48:00', '200', '调度机器：172.16.16.5<br>执行器-注册方式：手动录入<br>执行器-地址列表：[47.107.125.138:9998]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：47.107.125.138:9998<br>code：200<br>msg：null', '2018-12-21 08:48:00', '200', '');
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES ('291695', '5', '14', 'BEAN', '47.107.125.138:9998', 'bargainEndTask', '', '2018-12-21 08:51:00', '200', '调度机器：172.16.16.5<br>执行器-注册方式：手动录入<br>执行器-地址列表：[47.107.125.138:9998]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：47.107.125.138:9998<br>code：200<br>msg：null', '2018-12-21 08:51:00', '200', '');
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES ('291696', '5', '16', 'BEAN', '47.107.125.138:9998', 'bargainStartTask', '', '2018-12-21 08:51:00', '200', '调度机器：172.16.16.5<br>执行器-注册方式：手动录入<br>执行器-地址列表：[47.107.125.138:9998]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：47.107.125.138:9998<br>code：200<br>msg：null', '2018-12-21 08:51:00', '200', '');
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES ('291697', '5', '24', 'BEAN', '47.107.125.138:9998', 'userCouponEndTask', '', '2018-12-21 08:51:00', '200', '调度机器：172.16.16.5<br>执行器-注册方式：手动录入<br>执行器-地址列表：[47.107.125.138:9998]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：47.107.125.138:9998<br>code：200<br>msg：null', '2018-12-21 08:51:00', '200', '');
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES ('291698', '5', '25', 'BEAN', '47.107.125.138:9998', 'userCouponStartTask', '', '2018-12-21 08:51:00', '200', '调度机器：172.16.16.5<br>执行器-注册方式：手动录入<br>执行器-地址列表：[47.107.125.138:9998]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：47.107.125.138:9998<br>code：200<br>msg：null', '2018-12-21 08:51:00', '200', '');
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES ('291699', '5', '14', 'BEAN', '47.107.125.138:9998', 'bargainEndTask', '', '2018-12-21 08:54:00', '200', '调度机器：172.16.16.5<br>执行器-注册方式：手动录入<br>执行器-地址列表：[47.107.125.138:9998]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：47.107.125.138:9998<br>code：200<br>msg：null', '2018-12-21 08:54:00', '200', '');
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES ('291700', '5', '16', 'BEAN', '47.107.125.138:9998', 'bargainStartTask', '', '2018-12-21 08:54:00', '200', '调度机器：172.16.16.5<br>执行器-注册方式：手动录入<br>执行器-地址列表：[47.107.125.138:9998]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：47.107.125.138:9998<br>code：200<br>msg：null', '2018-12-21 08:54:00', '200', '');
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES ('291701', '5', '24', 'BEAN', '47.107.125.138:9998', 'userCouponEndTask', '', '2018-12-21 08:54:00', '200', '调度机器：172.16.16.5<br>执行器-注册方式：手动录入<br>执行器-地址列表：[47.107.125.138:9998]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：47.107.125.138:9998<br>code：200<br>msg：null', '2018-12-21 08:54:00', '200', '');
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES ('291702', '5', '25', 'BEAN', '47.107.125.138:9998', 'userCouponStartTask', '', '2018-12-21 08:54:00', '200', '调度机器：172.16.16.5<br>执行器-注册方式：手动录入<br>执行器-地址列表：[47.107.125.138:9998]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：47.107.125.138:9998<br>code：200<br>msg：null', '2018-12-21 08:54:00', '200', '');
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES ('291703', '5', '14', 'BEAN', '47.107.125.138:9998', 'bargainEndTask', '', '2018-12-21 08:57:00', '200', '调度机器：172.16.16.5<br>执行器-注册方式：手动录入<br>执行器-地址列表：[47.107.125.138:9998]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：47.107.125.138:9998<br>code：200<br>msg：null', '2018-12-21 08:57:00', '200', '');
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES ('291704', '5', '16', 'BEAN', '47.107.125.138:9998', 'bargainStartTask', '', '2018-12-21 08:57:00', '200', '调度机器：172.16.16.5<br>执行器-注册方式：手动录入<br>执行器-地址列表：[47.107.125.138:9998]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：47.107.125.138:9998<br>code：200<br>msg：null', '2018-12-21 08:57:00', '200', '');
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES ('291705', '5', '24', 'BEAN', '47.107.125.138:9998', 'userCouponEndTask', '', '2018-12-21 08:57:00', '200', '调度机器：172.16.16.5<br>执行器-注册方式：手动录入<br>执行器-地址列表：[47.107.125.138:9998]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：47.107.125.138:9998<br>code：200<br>msg：null', '2018-12-21 08:57:00', '200', '');
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES ('291706', '5', '25', 'BEAN', '47.107.125.138:9998', 'userCouponStartTask', '', '2018-12-21 08:57:00', '200', '调度机器：172.16.16.5<br>执行器-注册方式：手动录入<br>执行器-地址列表：[47.107.125.138:9998]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：47.107.125.138:9998<br>code：200<br>msg：null', '2018-12-21 08:57:00', '200', '');
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES ('291707', '5', '14', 'BEAN', '47.107.125.138:9998', 'bargainEndTask', '', '2018-12-21 09:00:00', '200', '调度机器：172.16.16.5<br>执行器-注册方式：手动录入<br>执行器-地址列表：[47.107.125.138:9998]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：47.107.125.138:9998<br>code：200<br>msg：null', '2018-12-21 09:00:00', '200', '');
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES ('291708', '5', '15', 'BEAN', '47.107.125.138:9998', 'bargainOrderEndTask', '', '2018-12-21 09:00:00', '200', '调度机器：172.16.16.5<br>执行器-注册方式：手动录入<br>执行器-地址列表：[47.107.125.138:9998]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：47.107.125.138:9998<br>code：200<br>msg：null', '2018-12-21 09:00:00', '200', '');
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES ('291709', '5', '16', 'BEAN', '47.107.125.138:9998', 'bargainStartTask', '', '2018-12-21 09:00:00', '200', '调度机器：172.16.16.5<br>执行器-注册方式：手动录入<br>执行器-地址列表：[47.107.125.138:9998]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：47.107.125.138:9998<br>code：200<br>msg：null', '2018-12-21 09:00:00', '200', '');
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES ('291710', '5', '18', 'BEAN', '47.107.125.138:9998', 'couponStartTask', '', '2018-12-21 09:00:00', '200', '调度机器：172.16.16.5<br>执行器-注册方式：手动录入<br>执行器-地址列表：[47.107.125.138:9998]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：47.107.125.138:9998<br>code：200<br>msg：null', '2018-12-21 09:00:00', '200', '');
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES ('291711', '5', '19', 'BEAN', '47.107.125.138:9998', 'grouponEndTask', '', '2018-12-21 09:00:00', '200', '调度机器：172.16.16.5<br>执行器-注册方式：手动录入<br>执行器-地址列表：[47.107.125.138:9998]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：47.107.125.138:9998<br>code：200<br>msg：null', '2018-12-21 09:00:00', '200', '');
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES ('291712', '5', '20', 'BEAN', '47.107.125.138:9998', 'GrouponOrderEndTask', '', '2018-12-21 09:00:00', '200', '调度机器：172.16.16.5<br>执行器-注册方式：手动录入<br>执行器-地址列表：[47.107.125.138:9998]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：47.107.125.138:9998<br>code：200<br>msg：null', '2018-12-21 09:00:00', '200', '');
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES ('291713', '5', '21', 'BEAN', '47.107.125.138:9998', 'grouponStartTask', '', '2018-12-21 09:00:00', '200', '调度机器：172.16.16.5<br>执行器-注册方式：手动录入<br>执行器-地址列表：[47.107.125.138:9998]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：47.107.125.138:9998<br>code：200<br>msg：null', '2018-12-21 09:00:00', '200', '');
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES ('291714', '5', '24', 'BEAN', '47.107.125.138:9998', 'userCouponEndTask', '', '2018-12-21 09:00:00', '200', '调度机器：172.16.16.5<br>执行器-注册方式：手动录入<br>执行器-地址列表：[47.107.125.138:9998]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：47.107.125.138:9998<br>code：200<br>msg：null', '2018-12-21 09:00:00', '200', '');
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES ('291715', '5', '25', 'BEAN', '47.107.125.138:9998', 'userCouponStartTask', '', '2018-12-21 09:00:00', '200', '调度机器：172.16.16.5<br>执行器-注册方式：手动录入<br>执行器-地址列表：[47.107.125.138:9998]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：47.107.125.138:9998<br>code：200<br>msg：null', '2018-12-21 09:00:00', '200', '');
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES ('291716', '5', '14', 'BEAN', '47.107.125.138:9998', 'bargainEndTask', '', '2018-12-21 09:03:00', '200', '调度机器：172.16.16.5<br>执行器-注册方式：手动录入<br>执行器-地址列表：[47.107.125.138:9998]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：47.107.125.138:9998<br>code：200<br>msg：null', '2018-12-21 09:03:00', '200', '');
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES ('291717', '5', '16', 'BEAN', '47.107.125.138:9998', 'bargainStartTask', '', '2018-12-21 09:03:00', '200', '调度机器：172.16.16.5<br>执行器-注册方式：手动录入<br>执行器-地址列表：[47.107.125.138:9998]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：47.107.125.138:9998<br>code：200<br>msg：null', '2018-12-21 09:03:00', '200', '');
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES ('291718', '5', '24', 'BEAN', '47.107.125.138:9998', 'userCouponEndTask', '', '2018-12-21 09:03:00', '200', '调度机器：172.16.16.5<br>执行器-注册方式：手动录入<br>执行器-地址列表：[47.107.125.138:9998]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：47.107.125.138:9998<br>code：200<br>msg：null', '2018-12-21 09:03:00', '200', '');
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES ('291719', '5', '25', 'BEAN', '47.107.125.138:9998', 'userCouponStartTask', '', '2018-12-21 09:03:00', '200', '调度机器：172.16.16.5<br>执行器-注册方式：手动录入<br>执行器-地址列表：[47.107.125.138:9998]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：47.107.125.138:9998<br>code：200<br>msg：null', '2018-12-21 09:03:00', '200', '');
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES ('291720', '5', '14', 'BEAN', '47.107.125.138:9998', 'bargainEndTask', '', '2018-12-21 09:06:00', '200', '调度机器：172.16.16.5<br>执行器-注册方式：手动录入<br>执行器-地址列表：[47.107.125.138:9998]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：47.107.125.138:9998<br>code：200<br>msg：null', '2018-12-21 09:06:00', '200', '');
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES ('291721', '5', '16', 'BEAN', '47.107.125.138:9998', 'bargainStartTask', '', '2018-12-21 09:06:00', '200', '调度机器：172.16.16.5<br>执行器-注册方式：手动录入<br>执行器-地址列表：[47.107.125.138:9998]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：47.107.125.138:9998<br>code：200<br>msg：null', '2018-12-21 09:06:00', '200', '');
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES ('291722', '5', '24', 'BEAN', '47.107.125.138:9998', 'userCouponEndTask', '', '2018-12-21 09:06:00', '200', '调度机器：172.16.16.5<br>执行器-注册方式：手动录入<br>执行器-地址列表：[47.107.125.138:9998]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：47.107.125.138:9998<br>code：200<br>msg：null', '2018-12-21 09:06:00', '200', '');
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES ('291723', '5', '25', 'BEAN', '47.107.125.138:9998', 'userCouponStartTask', '', '2018-12-21 09:06:00', '200', '调度机器：172.16.16.5<br>执行器-注册方式：手动录入<br>执行器-地址列表：[47.107.125.138:9998]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：47.107.125.138:9998<br>code：200<br>msg：null', '2018-12-21 09:06:00', '200', '');
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES ('291724', '5', '14', 'BEAN', '47.107.125.138:9998', 'bargainEndTask', '', '2018-12-21 09:09:00', '200', '调度机器：172.16.16.5<br>执行器-注册方式：手动录入<br>执行器-地址列表：[47.107.125.138:9998]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：47.107.125.138:9998<br>code：200<br>msg：null', '2018-12-21 09:09:00', '200', '');
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES ('291725', '5', '16', 'BEAN', '47.107.125.138:9998', 'bargainStartTask', '', '2018-12-21 09:09:00', '200', '调度机器：172.16.16.5<br>执行器-注册方式：手动录入<br>执行器-地址列表：[47.107.125.138:9998]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：47.107.125.138:9998<br>code：200<br>msg：null', '2018-12-21 09:09:00', '200', '');
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES ('291726', '5', '24', 'BEAN', '47.107.125.138:9998', 'userCouponEndTask', '', '2018-12-21 09:09:00', '200', '调度机器：172.16.16.5<br>执行器-注册方式：手动录入<br>执行器-地址列表：[47.107.125.138:9998]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：47.107.125.138:9998<br>code：200<br>msg：null', '2018-12-21 09:09:00', '200', '');
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES ('291727', '5', '25', 'BEAN', '47.107.125.138:9998', 'userCouponStartTask', '', '2018-12-21 09:09:00', '200', '调度机器：172.16.16.5<br>执行器-注册方式：手动录入<br>执行器-地址列表：[47.107.125.138:9998]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：47.107.125.138:9998<br>code：200<br>msg：null', '2018-12-21 09:09:00', '200', '');
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES ('291728', '5', '14', 'BEAN', '47.107.125.138:9998', 'bargainEndTask', '', '2018-12-21 09:12:00', '200', '调度机器：172.16.16.5<br>执行器-注册方式：手动录入<br>执行器-地址列表：[47.107.125.138:9998]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：47.107.125.138:9998<br>code：200<br>msg：null', '2018-12-21 09:12:00', '200', '');
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES ('291729', '5', '16', 'BEAN', '47.107.125.138:9998', 'bargainStartTask', '', '2018-12-21 09:12:00', '200', '调度机器：172.16.16.5<br>执行器-注册方式：手动录入<br>执行器-地址列表：[47.107.125.138:9998]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：47.107.125.138:9998<br>code：200<br>msg：null', '2018-12-21 09:12:00', '200', '');
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES ('291730', '5', '24', 'BEAN', '47.107.125.138:9998', 'userCouponEndTask', '', '2018-12-21 09:12:00', '200', '调度机器：172.16.16.5<br>执行器-注册方式：手动录入<br>执行器-地址列表：[47.107.125.138:9998]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：47.107.125.138:9998<br>code：200<br>msg：null', '2018-12-21 09:12:00', '200', '');
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES ('291731', '5', '25', 'BEAN', '47.107.125.138:9998', 'userCouponStartTask', '', '2018-12-21 09:12:00', '200', '调度机器：172.16.16.5<br>执行器-注册方式：手动录入<br>执行器-地址列表：[47.107.125.138:9998]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：47.107.125.138:9998<br>code：200<br>msg：null', '2018-12-21 09:12:00', '200', '');
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES ('291732', '5', '14', 'BEAN', '47.107.125.138:9998', 'bargainEndTask', '', '2018-12-21 09:15:00', '200', '调度机器：172.16.16.5<br>执行器-注册方式：手动录入<br>执行器-地址列表：[47.107.125.138:9998]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：47.107.125.138:9998<br>code：200<br>msg：null', '2018-12-21 09:15:00', '200', '');
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES ('291733', '5', '16', 'BEAN', '47.107.125.138:9998', 'bargainStartTask', '', '2018-12-21 09:15:00', '200', '调度机器：172.16.16.5<br>执行器-注册方式：手动录入<br>执行器-地址列表：[47.107.125.138:9998]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：47.107.125.138:9998<br>code：200<br>msg：null', '2018-12-21 09:15:00', '200', '');
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES ('291734', '5', '24', 'BEAN', '47.107.125.138:9998', 'userCouponEndTask', '', '2018-12-21 09:15:00', '200', '调度机器：172.16.16.5<br>执行器-注册方式：手动录入<br>执行器-地址列表：[47.107.125.138:9998]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：47.107.125.138:9998<br>code：200<br>msg：null', '2018-12-21 09:15:00', '200', '');
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES ('291735', '5', '25', 'BEAN', '47.107.125.138:9998', 'userCouponStartTask', '', '2018-12-21 09:15:00', '200', '调度机器：172.16.16.5<br>执行器-注册方式：手动录入<br>执行器-地址列表：[47.107.125.138:9998]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：47.107.125.138:9998<br>code：200<br>msg：null', '2018-12-21 09:15:00', '200', '');
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES ('291736', '5', '14', 'BEAN', '47.107.125.138:9998', 'bargainEndTask', '', '2018-12-21 09:18:00', '200', '调度机器：172.16.16.5<br>执行器-注册方式：手动录入<br>执行器-地址列表：[47.107.125.138:9998]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：47.107.125.138:9998<br>code：200<br>msg：null', '2018-12-21 09:18:00', '200', '');
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES ('291737', '5', '16', 'BEAN', '47.107.125.138:9998', 'bargainStartTask', '', '2018-12-21 09:18:00', '200', '调度机器：172.16.16.5<br>执行器-注册方式：手动录入<br>执行器-地址列表：[47.107.125.138:9998]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：47.107.125.138:9998<br>code：200<br>msg：null', '2018-12-21 09:18:00', '200', '');
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES ('291738', '5', '24', 'BEAN', '47.107.125.138:9998', 'userCouponEndTask', '', '2018-12-21 09:18:00', '200', '调度机器：172.16.16.5<br>执行器-注册方式：手动录入<br>执行器-地址列表：[47.107.125.138:9998]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：47.107.125.138:9998<br>code：200<br>msg：null', '2018-12-21 09:18:00', '200', '');
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES ('291739', '5', '25', 'BEAN', '47.107.125.138:9998', 'userCouponStartTask', '', '2018-12-21 09:18:00', '200', '调度机器：172.16.16.5<br>执行器-注册方式：手动录入<br>执行器-地址列表：[47.107.125.138:9998]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：47.107.125.138:9998<br>code：200<br>msg：null', '2018-12-21 09:18:00', '200', '');
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES ('291740', '5', '14', 'BEAN', '47.107.125.138:9998', 'bargainEndTask', '', '2018-12-21 09:21:00', '200', '调度机器：172.16.16.5<br>执行器-注册方式：手动录入<br>执行器-地址列表：[47.107.125.138:9998]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：47.107.125.138:9998<br>code：200<br>msg：null', '2018-12-21 09:21:00', '200', '');
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES ('291741', '5', '16', 'BEAN', '47.107.125.138:9998', 'bargainStartTask', '', '2018-12-21 09:21:00', '200', '调度机器：172.16.16.5<br>执行器-注册方式：手动录入<br>执行器-地址列表：[47.107.125.138:9998]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：47.107.125.138:9998<br>code：200<br>msg：null', '2018-12-21 09:21:00', '200', '');
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES ('291742', '5', '24', 'BEAN', '47.107.125.138:9998', 'userCouponEndTask', '', '2018-12-21 09:21:00', '200', '调度机器：172.16.16.5<br>执行器-注册方式：手动录入<br>执行器-地址列表：[47.107.125.138:9998]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：47.107.125.138:9998<br>code：200<br>msg：null', '2018-12-21 09:21:00', '200', '');
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES ('291743', '5', '25', 'BEAN', '47.107.125.138:9998', 'userCouponStartTask', '', '2018-12-21 09:21:00', '200', '调度机器：172.16.16.5<br>执行器-注册方式：手动录入<br>执行器-地址列表：[47.107.125.138:9998]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：47.107.125.138:9998<br>code：200<br>msg：null', '2018-12-21 09:21:00', '200', '');
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES ('291744', '5', '14', 'BEAN', '47.107.125.138:9998', 'bargainEndTask', '', '2018-12-21 09:24:00', '200', '调度机器：172.16.16.5<br>执行器-注册方式：手动录入<br>执行器-地址列表：[47.107.125.138:9998]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：47.107.125.138:9998<br>code：200<br>msg：null', '2018-12-21 09:24:00', '200', '');
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES ('291745', '5', '16', 'BEAN', '47.107.125.138:9998', 'bargainStartTask', '', '2018-12-21 09:24:00', '200', '调度机器：172.16.16.5<br>执行器-注册方式：手动录入<br>执行器-地址列表：[47.107.125.138:9998]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：47.107.125.138:9998<br>code：200<br>msg：null', '2018-12-21 09:24:00', '200', '');
INSERT INTO `xxl_job_qrtz_trigger_log` VALUES ('291746', '5', '24', 'BEAN', '47.107.125.138:9998', 'userCouponEndTask', '', '2018-12-21 09:24:00', '200', '调度机器：172.16.16.5<br>执行器-注册方式：手动录入<br>执行器-地址列表：[47.107.125.138:9998]<br>路由策略：第一个<br>阻塞处理策略：单机串行<br>失败处理策略：失败告警<br><br><span style=\"color:#00c0ef;\" > >>>>>>>>>>>触发调度<<<<<<<<<<< </span><br>触发调度：<br>address：47.107.125.138:9998<br>code：200<br>msg：null', '2018-12-21 09:24:00', '200', '');

-- ----------------------------
-- Table structure for xxl_job_qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_qrtz_triggers`;
CREATE TABLE `xxl_job_qrtz_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) NOT NULL,
  `TRIGGER_TYPE` varchar(8) NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) DEFAULT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `SCHED_NAME` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  CONSTRAINT `xxl_job_qrtz_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `xxl_job_qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xxl_job_qrtz_triggers
-- ----------------------------
INSERT INTO `xxl_job_qrtz_triggers` VALUES ('quartzScheduler', '7', '5', '7', '5', null, '1500457640000', '-1', '5', 'PAUSED', 'CRON', '1500457636000', '0', null, '2', '');


-- ----------------------------
-- Table structure for xxl_job_qrtz_trigger_registry
-- ----------------------------
DROP TABLE IF EXISTS `xxl_job_qrtz_trigger_registry`;
CREATE TABLE `xxl_job_qrtz_trigger_registry` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `registry_group` varchar(255) NOT NULL,
  `registry_key` varchar(255) NOT NULL,
  `registry_value` varchar(255) NOT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of xxl_job_qrtz_trigger_registry
-- ----------------------------
INSERT INTO `xxl_job_qrtz_trigger_registry` VALUES ('3', 'ADMIN', 'ADMIN', '192.168.1.102:8888', '2017-07-19 20:11:37');
INSERT INTO `xxl_job_qrtz_trigger_registry` VALUES ('4', 'EXECUTOR', 'xxl-job-executor-example', '127.0.0.1:9999', '2017-07-19 20:11:59');
