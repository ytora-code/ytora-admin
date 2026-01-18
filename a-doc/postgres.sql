/*
 Navicat Premium Dump SQL

 Source Server         : pg_ytora
 Source Server Type    : PostgreSQL
 Source Server Version : 170006 (170006)
 Source Host           : localhost:5432
 Source Catalog        : ytora
 Source Schema         : ytora

 Target Server Type    : PostgreSQL
 Target Server Version : 170006 (170006)
 File Encoding         : 65001

 Date: 19/01/2026 01:07:11
*/


-- ----------------------------
-- Table structure for sys_data_rule
-- ----------------------------
DROP TABLE IF EXISTS "ytora"."sys_data_rule";
CREATE TABLE "ytora"."sys_data_rule" (
  "id" int8 NOT NULL,
  "create_by" varchar(16) COLLATE "pg_catalog"."default",
  "create_time" timestamp(6),
  "update_by" varchar(16) COLLATE "pg_catalog"."default",
  "update_time" timestamp(6),
  "depart_code" varchar(255) COLLATE "pg_catalog"."default",
  "remark" varchar(255) COLLATE "pg_catalog"."default",
  "status" varchar(255) COLLATE "pg_catalog"."default",
  "permission_id" int8 NOT NULL,
  "rule_name" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "rule_field" varchar(255) COLLATE "pg_catalog"."default",
  "rule_type" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "rule_value" varchar(255) COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "ytora"."sys_data_rule"."id" IS '主键ID';
COMMENT ON COLUMN "ytora"."sys_data_rule"."create_by" IS '创建人';
COMMENT ON COLUMN "ytora"."sys_data_rule"."create_time" IS '创建时间';
COMMENT ON COLUMN "ytora"."sys_data_rule"."update_by" IS '更新人';
COMMENT ON COLUMN "ytora"."sys_data_rule"."update_time" IS '更新时间';
COMMENT ON COLUMN "ytora"."sys_data_rule"."depart_code" IS '创建者所属部门';
COMMENT ON COLUMN "ytora"."sys_data_rule"."remark" IS '数据备注';
COMMENT ON COLUMN "ytora"."sys_data_rule"."status" IS '数据状态';
COMMENT ON COLUMN "ytora"."sys_data_rule"."permission_id" IS '资源ID';
COMMENT ON COLUMN "ytora"."sys_data_rule"."rule_name" IS '规则名称';
COMMENT ON COLUMN "ytora"."sys_data_rule"."rule_field" IS '规则字段';
COMMENT ON COLUMN "ytora"."sys_data_rule"."rule_type" IS '规则类型';
COMMENT ON COLUMN "ytora"."sys_data_rule"."rule_value" IS '规则值';
COMMENT ON TABLE "ytora"."sys_data_rule" IS '数据规则';

-- ----------------------------
-- Records of sys_data_rule
-- ----------------------------
INSERT INTO "ytora"."sys_data_rule" VALUES (83692641714176, 'ytora', '2026-01-11 18:44:08.366762', 'ytora', '2026-01-11 18:44:08.366762', 'YT-HR-RECR', NULL, NULL, 23, '查询本人创建的数据', 'create_by', '=', '${userName}');
INSERT INTO "ytora"."sys_data_rule" VALUES (83797329379328, 'ytora', '2026-01-11 19:10:45.772207', 'ytora', '2026-01-11 19:10:45.772207', 'YT-HR-RECR', NULL, NULL, 23, '查看本部门的数据', 'depart_code', '=', '${departCode}');
INSERT INTO "ytora"."sys_data_rule" VALUES (83800502239232, 'ytora', '2026-01-11 19:11:34.187712', 'ytora', '2026-01-11 19:11:34.187712', 'YT-HR-RECR', NULL, NULL, 23, '查看本部门及子部门的数据', 'depart_code', 'startWith', '${departCode}');
INSERT INTO "ytora"."sys_data_rule" VALUES (89821629317120, 'ytora', '2026-01-12 20:42:49.295007', 'ytora', '2026-01-12 20:42:49.295007', 'YT-HR-RECR', NULL, NULL, 89686066331648, '查看全部数据', NULL, '查看全部数据', NULL);
INSERT INTO "ytora"."sys_data_rule" VALUES (83802886635520, 'ytora', '2026-01-11 19:12:10.570451', 'ytora', '2026-01-13 21:29:00.636188', 'YT-HR-RECR', NULL, NULL, 23, '查看指定部门的数据', 'depart_code', 'SpecifyDepart', '-');
INSERT INTO "ytora"."sys_data_rule" VALUES (83805188390912, 'ytora', '2026-01-11 19:12:45.691594', 'ytora', '2026-01-13 21:29:08.713916', 'YT-HR-RECR', NULL, NULL, 23, '查看指定人员创建的数据', 'create_by', 'SpecifyUser', '-');
INSERT INTO "ytora"."sys_data_rule" VALUES (83810916302848, 'ytora', '2026-01-11 19:14:13.093215', 'ytora', '2026-01-13 21:29:17.020302', 'YT-HR-RECR', NULL, NULL, 23, '查看全部数据', '-', 'ALL', '-');

-- ----------------------------
-- Table structure for sys_depart
-- ----------------------------
DROP TABLE IF EXISTS "ytora"."sys_depart";
CREATE TABLE "ytora"."sys_depart" (
  "id" int8 NOT NULL,
  "create_by" varchar(16) COLLATE "pg_catalog"."default",
  "create_time" timestamp(6),
  "update_by" varchar(16) COLLATE "pg_catalog"."default",
  "update_time" timestamp(6),
  "depart_code" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "remark" varchar(255) COLLATE "pg_catalog"."default",
  "status" varchar(255) COLLATE "pg_catalog"."default",
  "pid" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "depart_name" varchar(255) COLLATE "pg_catalog"."default",
  "type" varchar(255) COLLATE "pg_catalog"."default",
  "contact_user_name" varchar(255) COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "ytora"."sys_depart"."id" IS '主键ID';
COMMENT ON COLUMN "ytora"."sys_depart"."create_by" IS '创建人';
COMMENT ON COLUMN "ytora"."sys_depart"."create_time" IS '创建时间';
COMMENT ON COLUMN "ytora"."sys_depart"."update_by" IS '更新人';
COMMENT ON COLUMN "ytora"."sys_depart"."update_time" IS '更新时间';
COMMENT ON COLUMN "ytora"."sys_depart"."depart_code" IS '部门编码';
COMMENT ON COLUMN "ytora"."sys_depart"."remark" IS '数据备注';
COMMENT ON COLUMN "ytora"."sys_depart"."status" IS '数据状态';
COMMENT ON COLUMN "ytora"."sys_depart"."pid" IS '上级部门id';
COMMENT ON COLUMN "ytora"."sys_depart"."depart_name" IS '部门名称';
COMMENT ON COLUMN "ytora"."sys_depart"."type" IS '部门类型';
COMMENT ON COLUMN "ytora"."sys_depart"."contact_user_name" IS '部门联系人username';
COMMENT ON TABLE "ytora"."sys_depart" IS '部门表';

-- ----------------------------
-- Records of sys_depart
-- ----------------------------
INSERT INTO "ytora"."sys_depart" VALUES (1, NULL, '2025-01-01 09:00:00', NULL, NULL, 'YT', '集团最高管理机构', '1', '0', '宇拓集团总部', '1', NULL);
INSERT INTO "ytora"."sys_depart" VALUES (49618700402688, 'ytora', '2026-01-05 18:18:41.279657', 'ytora', '2026-01-05 18:19:02.770837', 'CES', '范围分为', '2', '0', '测试公司', '1', 'admin');
INSERT INTO "ytora"."sys_depart" VALUES (49646947663872, 'ytora', '2026-01-05 18:25:52.302579', 'ytora', '2026-01-05 18:25:52.302579', 'YT-01', '研发中心', '1', '1', '研发部', '2', 'ytor');
INSERT INTO "ytora"."sys_depart" VALUES (49653032157184, 'ytora', '2026-01-05 18:27:25.143995', 'ytora', '2026-01-05 18:27:25.14499', 'CES-01', '3213213', '2', '49618700402688', '测试1', '1', '213123');
INSERT INTO "ytora"."sys_depart" VALUES (49648122265600, 'ytora', '2026-01-05 18:26:10.225248', 'ytora', '2026-01-05 18:27:51.221549', 'YT-02', NULL, '1', '1', '财务部', '2', NULL);
INSERT INTO "ytora"."sys_depart" VALUES (50713984172032, 'ytora', '2026-01-05 22:57:13.910566', 'ytora', '2026-01-05 22:57:13.910566', 'YT-01-01', NULL, '1', '49646947663872', '前端', '2', NULL);
INSERT INTO "ytora"."sys_depart" VALUES (50714597588992, 'ytora', '2026-01-05 22:57:23.347175', 'ytora', '2026-01-05 22:57:23.347175', 'YT-01-02', NULL, '2', '49646947663872', '后端', '2', NULL);
INSERT INTO "ytora"."sys_depart" VALUES (50715561951232, 'ytora', '2026-01-05 22:57:38.062664', 'ytora', '2026-01-05 22:57:38.062664', 'YT-01-02-01', NULL, '2', '50714597588992', '后端一组', '3', NULL);

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS "ytora"."sys_dict";
CREATE TABLE "ytora"."sys_dict" (
  "id" int8 NOT NULL,
  "create_by" varchar(16) COLLATE "pg_catalog"."default",
  "create_time" timestamp(6),
  "update_by" varchar(16) COLLATE "pg_catalog"."default",
  "update_time" timestamp(6),
  "depart_code" varchar(255) COLLATE "pg_catalog"."default",
  "remark" varchar(255) COLLATE "pg_catalog"."default",
  "status" varchar(255) COLLATE "pg_catalog"."default" DEFAULT '1'::character varying,
  "pid" varchar(255) COLLATE "pg_catalog"."default" NOT NULL DEFAULT '0'::character varying,
  "dict_name" varchar(255) COLLATE "pg_catalog"."default",
  "dict_code" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "dict_item_value" varchar(255) COLLATE "pg_catalog"."default",
  "dict_item_text" varchar(255) COLLATE "pg_catalog"."default",
  "index" int4,
  "type" int4 NOT NULL
)
;
COMMENT ON COLUMN "ytora"."sys_dict"."id" IS '主键ID';
COMMENT ON COLUMN "ytora"."sys_dict"."create_by" IS '创建人';
COMMENT ON COLUMN "ytora"."sys_dict"."create_time" IS '创建时间';
COMMENT ON COLUMN "ytora"."sys_dict"."update_by" IS '更新人';
COMMENT ON COLUMN "ytora"."sys_dict"."update_time" IS '更新时间';
COMMENT ON COLUMN "ytora"."sys_dict"."depart_code" IS '创建者所属部门';
COMMENT ON COLUMN "ytora"."sys_dict"."remark" IS '数据备注';
COMMENT ON COLUMN "ytora"."sys_dict"."status" IS '数据状态';
COMMENT ON COLUMN "ytora"."sys_dict"."pid" IS '父字典ID';
COMMENT ON COLUMN "ytora"."sys_dict"."dict_name" IS '字典名称';
COMMENT ON COLUMN "ytora"."sys_dict"."dict_code" IS '字典编码';
COMMENT ON COLUMN "ytora"."sys_dict"."dict_item_value" IS '字典项值';
COMMENT ON COLUMN "ytora"."sys_dict"."dict_item_text" IS '字典项值对应的显示文本';
COMMENT ON COLUMN "ytora"."sys_dict"."index" IS '字典排序';
COMMENT ON COLUMN "ytora"."sys_dict"."type" IS '类型：0-字典/1-字典项';
COMMENT ON TABLE "ytora"."sys_dict" IS '系统字典';

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO "ytora"."sys_dict" VALUES (95479551688704, 'ytora', '2026-01-13 20:41:42.337835', 'ytora', '2026-01-14 13:34:55.358763', 'YT-HR-RECR', '数据规则的类型', NULL, '0', '规则类型', 'rule_type', NULL, NULL, 2, 1);
INSERT INTO "ytora"."sys_dict" VALUES (89958370967552, 'ytora', '2026-01-12 21:17:35.807605', 'ytora', '2026-01-14 13:35:48.560481', 'YT-HR-RECR', '资源类型', NULL, '0', '资源类型', 'permission_type', NULL, NULL, 3, 1);
INSERT INTO "ytora"."sys_dict" VALUES (101041133453312, 'ytora', '2026-01-14 20:16:05.337858', 'ytora', '2026-01-14 20:16:05.337858', 'YT-HR-RECR', '如果默认的表单项类型无法满足需求，可以将类型定义为插槽，然后在程序里面将自定义组件传入插槽', NULL, '0', NULL, 'formItemType', 'form-item::slot', '插槽', 6, 2);
INSERT INTO "ytora"."sys_dict" VALUES (100311143677952, 'ytora', '2026-01-14 17:10:26.582974', 'ytora', '2026-01-14 21:50:44.850442', 'YT-HR-RECR', '表示两种状态', NULL, '0', NULL, 'formItemType', 'form-item::switch', '开关', 5, 2);
INSERT INTO "ytora"."sys_dict" VALUES (100448915619840, 'ytora', '2026-01-14 17:45:28.814316', 'ytora', '2026-01-14 20:17:01.327053', 'YT-HR-RECR', '数字输入框，只能输入数字', NULL, '0', NULL, 'formItemType', 'form-item::numInput', '数字框', 2, 2);
INSERT INTO "ytora"."sys_dict" VALUES (100523412619264, 'ytora', '2026-01-14 18:04:25.54971', 'ytora', '2026-01-14 18:04:36.962858', 'YT-HR-RECR', '日期、时间选择框，默认格式：yyyy-MM-dd，默认类型：date', NULL, '0', NULL, 'formItemType', 'form-item::date', '日期', 3, 2);
INSERT INTO "ytora"."sys_dict" VALUES (100494884339712, 'ytora', '2026-01-14 17:57:10.240734', 'ytora', '2026-01-14 17:57:10.241684', 'YT-HR-RECR', '字典下拉框', NULL, '0', NULL, 'formItemType', 'form-item::dict', '字典', 4, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90261443903488, 'ytora', '2026-01-12 22:34:40.333676', 'ytora', '2026-01-14 23:43:11.431702', 'YT-HR-RECR', 'boy', NULL, '0', NULL, 'sex', '♂', '男', 1, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90262131769344, 'ytora', '2026-01-12 22:34:50.829252', 'ytora', '2026-01-14 23:43:13.363234', 'YT-HR-RECR', 'girl', NULL, '0', NULL, 'sex', '♀', '女', 2, 2);
INSERT INTO "ytora"."sys_dict" VALUES (100306489638912, 'ytora', '2026-01-14 17:09:15.555292', 'ytora', '2026-01-14 21:45:47.725038', 'YT-HR-RECR', '输入框', NULL, '0', NULL, 'formItemType', 'form-item::input', '输入框', 1, 2);
INSERT INTO "ytora"."sys_dict" VALUES (95484189409280, 'ytora', '2026-01-13 20:42:53.104814', 'ytora', '2026-01-14 23:43:56.200405', 'YT-HR-RECR', '', NULL, '0', '规则类型', 'rule_type', '=', '等于', 1, 2);
INSERT INTO "ytora"."sys_dict" VALUES (95485175136256, 'ytora', '2026-01-13 20:43:08.146107', 'ytora', '2026-01-14 23:44:00.11817', 'YT-HR-RECR', '', NULL, '0', '规则类型', 'rule_type', '<>', '不等于', 2, 2);
INSERT INTO "ytora"."sys_dict" VALUES (95487493865472, 'ytora', '2026-01-13 20:43:43.527938', 'ytora', '2026-01-14 23:44:13.757118', 'YT-HR-RECR', '', NULL, '0', '规则类型', 'rule_type', 'like', '模糊', 7, 2);
INSERT INTO "ytora"."sys_dict" VALUES (95485670588416, 'ytora', '2026-01-13 20:43:15.706941', 'ytora', '2026-01-14 23:44:03.076265', 'YT-HR-RECR', '', NULL, '0', '规则类型', 'rule_type', '<', '小于', 3, 2);
INSERT INTO "ytora"."sys_dict" VALUES (95486094737408, 'ytora', '2026-01-13 20:43:22.177743', 'ytora', '2026-01-14 23:44:05.743228', 'YT-HR-RECR', '', NULL, '0', '规则类型', 'rule_type', '<=', '小于等于', 4, 2);
INSERT INTO "ytora"."sys_dict" VALUES (95486580621312, 'ytora', '2026-01-13 20:43:29.592841', 'ytora', '2026-01-14 23:44:08.180515', 'YT-HR-RECR', '', NULL, '0', '规则类型', 'rule_type', '>', '大于', 5, 2);
INSERT INTO "ytora"."sys_dict" VALUES (95487083741184, 'ytora', '2026-01-13 20:43:37.268784', 'ytora', '2026-01-14 23:44:10.628496', 'YT-HR-RECR', '', NULL, '0', '规则类型', 'rule_type', '>=', '大于等于', 6, 2);
INSERT INTO "ytora"."sys_dict" VALUES (95488077201408, 'ytora', '2026-01-13 20:43:52.428586', 'ytora', '2026-01-14 23:44:16.523071', 'YT-HR-RECR', '', NULL, '0', '规则类型', 'rule_type', 'startWith', '以...开始', 8, 2);
INSERT INTO "ytora"."sys_dict" VALUES (95488664797184, 'ytora', '2026-01-13 20:44:01.394262', 'ytora', '2026-01-14 23:44:19.475291', 'YT-HR-RECR', '', NULL, '0', '规则类型', 'rule_type', 'endWith', '以...结束', 9, 2);
INSERT INTO "ytora"."sys_dict" VALUES (95489185021952, 'ytora', '2026-01-13 20:44:09.332097', 'ytora', '2026-01-14 23:44:23.452438', 'YT-HR-RECR', '', NULL, '0', '规则类型', 'rule_type', 'SpecifyUser', '指定用户', 10, 2);
INSERT INTO "ytora"."sys_dict" VALUES (95489642201088, 'ytora', '2026-01-13 20:44:16.308', 'ytora', '2026-01-14 23:44:26.558433', 'YT-HR-RECR', '', NULL, '0', '规则类型', 'rule_type', 'SpecifyDepart', '指定部门', 11, 2);
INSERT INTO "ytora"."sys_dict" VALUES (95490824077312, 'ytora', '2026-01-13 20:44:34.342843', 'ytora', '2026-01-14 23:44:29.244866', 'YT-HR-RECR', '', NULL, '0', '规则类型', 'rule_type', 'Customize', '自定义', 12, 2);
INSERT INTO "ytora"."sys_dict" VALUES (95490287730688, 'ytora', '2026-01-13 20:44:26.158275', 'ytora', '2026-01-14 23:44:31.53179', 'YT-HR-RECR', '', NULL, '0', '规则类型', 'rule_type', 'ALL', '查看全部数据', 13, 2);
INSERT INTO "ytora"."sys_dict" VALUES (99544982290432, 'ytora', '2026-01-14 13:55:35.875469', 'ytora', '2026-01-14 23:45:03.069307', 'YT-HR-RECR', '动态表格', NULL, '0', '表格列类型', 'tableColType', 'ALL', '查看全部数据', 4, 1);
INSERT INTO "ytora"."sys_dict" VALUES (100241603035136, 'ytora', '2026-01-14 16:52:45.476139', 'ytora', '2026-01-14 23:46:58.388328', 'YT-HR-RECR', '动态表单', NULL, '0', '表单项类型', 'formItemType', NULL, NULL, 5, 1);
INSERT INTO "ytora"."sys_dict" VALUES (89943859789824, 'ytora', '2026-01-12 21:13:54.384545', 'ytora', '2026-01-14 23:47:56.183518', 'YT-HR-RECR', '性别字典', NULL, '0', '性别', 'sex', NULL, NULL, 1, 1);
INSERT INTO "ytora"."sys_dict" VALUES (120000000000001, 'ytora', '2026-01-14 23:51:14.508704', 'ytora', '2026-01-14 23:51:14.508704', 'YT-SYS', '中华人民共和国56个民族', '0', '0', '民族', 'nation', NULL, NULL, 1, 1);
INSERT INTO "ytora"."sys_dict" VALUES (120000000000002, 'ytora', '2026-01-14 23:52:07.268118', 'ytora', '2026-01-14 23:52:07.268118', 'YT-SYS', '汉族', NULL, '0', NULL, 'nation', '01', '汉族', 1, 2);
INSERT INTO "ytora"."sys_dict" VALUES (120000000000003, 'ytora', '2026-01-14 23:52:07.268118', 'ytora', '2026-01-14 23:52:07.268118', 'YT-SYS', '蒙古族', NULL, '0', NULL, 'nation', '02', '蒙古族', 2, 2);
INSERT INTO "ytora"."sys_dict" VALUES (120000000000004, 'ytora', '2026-01-14 23:52:07.268118', 'ytora', '2026-01-14 23:52:07.268118', 'YT-SYS', '回族', NULL, '0', NULL, 'nation', '03', '回族', 3, 2);
INSERT INTO "ytora"."sys_dict" VALUES (120000000000005, 'ytora', '2026-01-14 23:52:07.268118', 'ytora', '2026-01-14 23:52:07.268118', 'YT-SYS', '藏族', NULL, '0', NULL, 'nation', '04', '藏族', 4, 2);
INSERT INTO "ytora"."sys_dict" VALUES (120000000000006, 'ytora', '2026-01-14 23:52:07.268118', 'ytora', '2026-01-14 23:52:07.268118', 'YT-SYS', '维吾尔族', NULL, '0', NULL, 'nation', '05', '维吾尔族', 5, 2);
INSERT INTO "ytora"."sys_dict" VALUES (120000000000007, 'ytora', '2026-01-14 23:52:07.268118', 'ytora', '2026-01-14 23:52:07.268118', 'YT-SYS', '苗族', NULL, '0', NULL, 'nation', '06', '苗族', 6, 2);
INSERT INTO "ytora"."sys_dict" VALUES (120000000000008, 'ytora', '2026-01-14 23:52:07.268118', 'ytora', '2026-01-14 23:52:07.268118', 'YT-SYS', '彝族', NULL, '0', NULL, 'nation', '07', '彝族', 7, 2);
INSERT INTO "ytora"."sys_dict" VALUES (120000000000009, 'ytora', '2026-01-14 23:52:07.268118', 'ytora', '2026-01-14 23:52:07.268118', 'YT-SYS', '壮族', NULL, '0', NULL, 'nation', '08', '壮族', 8, 2);
INSERT INTO "ytora"."sys_dict" VALUES (120000000000010, 'ytora', '2026-01-14 23:52:07.268118', 'ytora', '2026-01-14 23:52:07.268118', 'YT-SYS', '布依族', NULL, '0', NULL, 'nation', '09', '布依族', 9, 2);
INSERT INTO "ytora"."sys_dict" VALUES (120000000000011, 'ytora', '2026-01-14 23:52:07.268118', 'ytora', '2026-01-14 23:52:07.268118', 'YT-SYS', '朝鲜族', NULL, '0', NULL, 'nation', '10', '朝鲜族', 10, 2);
INSERT INTO "ytora"."sys_dict" VALUES (99273504391168, 'ytora', '2026-01-14 12:46:33.463711', 'ytora', '2026-01-15 10:28:02.763536', 'YT-HR-RECR', NULL, NULL, '0', '资源类型', 'permission_type', '2', '页面', 2, 2);
INSERT INTO "ytora"."sys_dict" VALUES (99468203982848, 'ytora', '2026-01-14 13:36:04.33442', 'ytora', '2026-01-15 10:28:07.091554', 'YT-HR-RECR', NULL, NULL, '0', '资源类型', 'permission_type', '5', '表单', 5, 2);
INSERT INTO "ytora"."sys_dict" VALUES (99320569987072, 'ytora', '2026-01-14 12:58:31.627433', 'ytora', '2026-01-15 10:28:15.650584', 'YT-HR-RECR', NULL, NULL, '0', '资源类型', 'permission_type', '3', '页面元素', 3, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90262583771136, 'ytora', '2026-01-12 22:34:57.726199', 'ytora', '2026-01-15 11:36:50.784648', 'YT-HR-RECR', '武装直升机武装直升机武装直升机武装直升机', NULL, '0', NULL, 'sex', 'ZSJ', '武装直升机', 3, 2);
INSERT INTO "ytora"."sys_dict" VALUES (120000000000012, 'ytora', '2026-01-14 23:52:07.268118', 'ytora', '2026-01-14 23:52:07.268118', 'YT-SYS', '满族', NULL, '0', NULL, 'nation', '11', '满族', 11, 2);
INSERT INTO "ytora"."sys_dict" VALUES (120000000000013, 'ytora', '2026-01-14 23:52:07.268118', 'ytora', '2026-01-14 23:52:07.268118', 'YT-SYS', '侗族', NULL, '0', NULL, 'nation', '12', '侗族', 12, 2);
INSERT INTO "ytora"."sys_dict" VALUES (99551328600064, 'ytora', '2026-01-14 13:57:12.724379', 'ytora', '2026-01-16 00:21:14.555078', 'YT-HR-RECR', '最常见的表格列，渲染文字', NULL, '0', '表格列类型', 'tableColType', 'table-col::normal', '表格列-普通文本', 3, 2);
INSERT INTO "ytora"."sys_dict" VALUES (99567705456640, 'ytora', '2026-01-14 14:01:22.615714', 'ytora', '2026-01-16 00:21:32.887462', 'YT-HR-RECR', '这种组件列一般放在table-col::flex下面', NULL, '0', '表格列类型', 'tableColType', 'table-col::icon', '表格列-图标', 4, 2);
INSERT INTO "ytora"."sys_dict" VALUES (99572198277120, 'ytora', '2026-01-14 14:02:31.170969', 'ytora', '2026-01-16 00:21:37.900657', 'YT-HR-RECR', '如果提供的表格列类型不满足需求，可以在代码里面使用插槽自定义组件', NULL, '0', '表格列类型', 'tableColType', 'table-col::slot', '表格列-插槽', 5, 2);
INSERT INTO "ytora"."sys_dict" VALUES (99557705515008, 'ytora', '2026-01-14 13:58:50.028842', 'ytora', '2026-01-16 00:21:49.158995', 'YT-HR-RECR', '这种组件列一般放在table-col::flex下面', NULL, '0', '表格列类型', 'tableColType', 'table-col::button', '表格列-按钮', 7, 2);
INSERT INTO "ytora"."sys_dict" VALUES (99558947946496, 'ytora', '2026-01-14 13:59:08.986786', 'ytora', '2026-01-16 00:21:53.907102', 'YT-HR-RECR', '这种组件列一般放在table-col::flex下面', NULL, '0', '表格列类型', 'tableColType', 'table-col::popconfirm', '表格列-二次确认按钮', 8, 2);
INSERT INTO "ytora"."sys_dict" VALUES (99560043053056, 'ytora', '2026-01-14 13:59:25.696901', 'ytora', '2026-01-16 00:21:58.207243', 'YT-HR-RECR', '这种组件列一般放在table-col::flex下面', NULL, '0', '表格列类型', 'tableColType', 'table-col::tag', '表格列-标签', 9, 2);
INSERT INTO "ytora"."sys_dict" VALUES (99577950240768, 'ytora', '2026-01-14 14:03:58.938233', 'ytora', '2026-01-16 00:22:02.214567', 'YT-HR-RECR', '这种组件列一般放在table-col::flex下面', NULL, '0', '表格列类型', 'tableColType', 'table-col::switch', '表格列-开关', 10, 2);
INSERT INTO "ytora"."sys_dict" VALUES (120000000000014, 'ytora', '2026-01-14 23:52:07.268118', 'ytora', '2026-01-14 23:52:07.268118', 'YT-SYS', '瑶族', NULL, '0', NULL, 'nation', '13', '瑶族', 13, 2);
INSERT INTO "ytora"."sys_dict" VALUES (120000000000015, 'ytora', '2026-01-14 23:52:07.268118', 'ytora', '2026-01-14 23:52:07.268118', 'YT-SYS', '白族', NULL, '0', NULL, 'nation', '14', '白族', 14, 2);
INSERT INTO "ytora"."sys_dict" VALUES (120000000000016, 'ytora', '2026-01-14 23:52:07.268118', 'ytora', '2026-01-14 23:52:07.268118', 'YT-SYS', '土家族', NULL, '0', NULL, 'nation', '15', '土家族', 15, 2);
INSERT INTO "ytora"."sys_dict" VALUES (120000000000017, 'ytora', '2026-01-14 23:52:07.268118', 'ytora', '2026-01-14 23:52:07.268118', 'YT-SYS', '哈尼族', NULL, '0', NULL, 'nation', '16', '哈尼族', 16, 2);
INSERT INTO "ytora"."sys_dict" VALUES (120000000000018, 'ytora', '2026-01-14 23:52:07.268118', 'ytora', '2026-01-14 23:52:07.268118', 'YT-SYS', '哈萨克族', NULL, '0', NULL, 'nation', '17', '哈萨克族', 17, 2);
INSERT INTO "ytora"."sys_dict" VALUES (120000000000019, 'ytora', '2026-01-14 23:52:07.268118', 'ytora', '2026-01-14 23:52:07.268118', 'YT-SYS', '傣族', NULL, '0', NULL, 'nation', '18', '傣族', 18, 2);
INSERT INTO "ytora"."sys_dict" VALUES (120000000000020, 'ytora', '2026-01-14 23:52:07.268118', 'ytora', '2026-01-14 23:52:07.268118', 'YT-SYS', '黎族', NULL, '0', NULL, 'nation', '19', '黎族', 19, 2);
INSERT INTO "ytora"."sys_dict" VALUES (120000000000021, 'ytora', '2026-01-14 23:52:07.268118', 'ytora', '2026-01-14 23:52:07.268118', 'YT-SYS', '傈僳族', NULL, '0', NULL, 'nation', '20', '傈僳族', 20, 2);
INSERT INTO "ytora"."sys_dict" VALUES (120000000000022, 'ytora', '2026-01-14 23:52:07.268118', 'ytora', '2026-01-14 23:52:07.268118', 'YT-SYS', '佤族', NULL, '0', NULL, 'nation', '21', '佤族', 21, 2);
INSERT INTO "ytora"."sys_dict" VALUES (120000000000023, 'ytora', '2026-01-14 23:52:07.268118', 'ytora', '2026-01-14 23:52:07.268118', 'YT-SYS', '畲族', NULL, '0', NULL, 'nation', '22', '畲族', 22, 2);
INSERT INTO "ytora"."sys_dict" VALUES (120000000000024, 'ytora', '2026-01-14 23:52:07.268118', 'ytora', '2026-01-14 23:52:07.268118', 'YT-SYS', '高山族', NULL, '0', NULL, 'nation', '23', '高山族', 23, 2);
INSERT INTO "ytora"."sys_dict" VALUES (120000000000025, 'ytora', '2026-01-14 23:52:07.268118', 'ytora', '2026-01-14 23:52:07.268118', 'YT-SYS', '拉祜族', NULL, '0', NULL, 'nation', '24', '拉祜族', 24, 2);
INSERT INTO "ytora"."sys_dict" VALUES (120000000000026, 'ytora', '2026-01-14 23:52:07.268118', 'ytora', '2026-01-14 23:52:07.268118', 'YT-SYS', '水族', NULL, '0', NULL, 'nation', '25', '水族', 25, 2);
INSERT INTO "ytora"."sys_dict" VALUES (120000000000027, 'ytora', '2026-01-14 23:52:07.268118', 'ytora', '2026-01-14 23:52:07.268118', 'YT-SYS', '东乡族', NULL, '0', NULL, 'nation', '26', '东乡族', 26, 2);
INSERT INTO "ytora"."sys_dict" VALUES (120000000000028, 'ytora', '2026-01-14 23:52:07.268118', 'ytora', '2026-01-14 23:52:07.268118', 'YT-SYS', '纳西族', NULL, '0', NULL, 'nation', '27', '纳西族', 27, 2);
INSERT INTO "ytora"."sys_dict" VALUES (120000000000029, 'ytora', '2026-01-14 23:52:07.268118', 'ytora', '2026-01-14 23:52:07.268118', 'YT-SYS', '景颇族', NULL, '0', NULL, 'nation', '28', '景颇族', 28, 2);
INSERT INTO "ytora"."sys_dict" VALUES (120000000000030, 'ytora', '2026-01-14 23:52:07.268118', 'ytora', '2026-01-14 23:52:07.268118', 'YT-SYS', '柯尔克孜族', NULL, '0', NULL, 'nation', '29', '柯尔克孜族', 29, 2);
INSERT INTO "ytora"."sys_dict" VALUES (120000000000031, 'ytora', '2026-01-14 23:52:07.268118', 'ytora', '2026-01-14 23:52:07.268118', 'YT-SYS', '土族', NULL, '0', NULL, 'nation', '30', '土族', 30, 2);
INSERT INTO "ytora"."sys_dict" VALUES (120000000000032, 'ytora', '2026-01-14 23:52:07.268118', 'ytora', '2026-01-14 23:52:07.268118', 'YT-SYS', '达斡尔族', NULL, '0', NULL, 'nation', '31', '达斡尔族', 31, 2);
INSERT INTO "ytora"."sys_dict" VALUES (120000000000033, 'ytora', '2026-01-14 23:52:07.268118', 'ytora', '2026-01-14 23:52:07.268118', 'YT-SYS', '仫佬族', NULL, '0', NULL, 'nation', '32', '仫佬族', 32, 2);
INSERT INTO "ytora"."sys_dict" VALUES (120000000000034, 'ytora', '2026-01-14 23:52:07.268118', 'ytora', '2026-01-14 23:52:07.268118', 'YT-SYS', '羌族', NULL, '0', NULL, 'nation', '33', '羌族', 33, 2);
INSERT INTO "ytora"."sys_dict" VALUES (120000000000035, 'ytora', '2026-01-14 23:52:07.268118', 'ytora', '2026-01-14 23:52:07.268118', 'YT-SYS', '布朗族', NULL, '0', NULL, 'nation', '34', '布朗族', 34, 2);
INSERT INTO "ytora"."sys_dict" VALUES (120000000000036, 'ytora', '2026-01-14 23:52:07.268118', 'ytora', '2026-01-14 23:52:07.268118', 'YT-SYS', '撒拉族', NULL, '0', NULL, 'nation', '35', '撒拉族', 35, 2);
INSERT INTO "ytora"."sys_dict" VALUES (120000000000037, 'ytora', '2026-01-14 23:52:07.268118', 'ytora', '2026-01-14 23:52:07.268118', 'YT-SYS', '毛南族', NULL, '0', NULL, 'nation', '36', '毛南族', 36, 2);
INSERT INTO "ytora"."sys_dict" VALUES (120000000000038, 'ytora', '2026-01-14 23:52:07.268118', 'ytora', '2026-01-14 23:52:07.268118', 'YT-SYS', '仡佬族', NULL, '0', NULL, 'nation', '37', '仡佬族', 37, 2);
INSERT INTO "ytora"."sys_dict" VALUES (120000000000039, 'ytora', '2026-01-14 23:52:07.268118', 'ytora', '2026-01-14 23:52:07.268118', 'YT-SYS', '锡伯族', NULL, '0', NULL, 'nation', '38', '锡伯族', 38, 2);
INSERT INTO "ytora"."sys_dict" VALUES (120000000000040, 'ytora', '2026-01-14 23:52:07.268118', 'ytora', '2026-01-14 23:52:07.268118', 'YT-SYS', '阿昌族', NULL, '0', NULL, 'nation', '39', '阿昌族', 39, 2);
INSERT INTO "ytora"."sys_dict" VALUES (120000000000041, 'ytora', '2026-01-14 23:52:07.268118', 'ytora', '2026-01-14 23:52:07.268118', 'YT-SYS', '普米族', NULL, '0', NULL, 'nation', '40', '普米族', 40, 2);
INSERT INTO "ytora"."sys_dict" VALUES (120000000000042, 'ytora', '2026-01-14 23:52:07.268118', 'ytora', '2026-01-14 23:52:07.268118', 'YT-SYS', '塔吉克族', NULL, '0', NULL, 'nation', '41', '塔吉克族', 41, 2);
INSERT INTO "ytora"."sys_dict" VALUES (120000000000043, 'ytora', '2026-01-14 23:52:07.268118', 'ytora', '2026-01-14 23:52:07.268118', 'YT-SYS', '怒族', NULL, '0', NULL, 'nation', '42', '怒族', 42, 2);
INSERT INTO "ytora"."sys_dict" VALUES (120000000000044, 'ytora', '2026-01-14 23:52:07.268118', 'ytora', '2026-01-14 23:52:07.268118', 'YT-SYS', '乌孜别克族', NULL, '0', NULL, 'nation', '43', '乌孜别克族', 43, 2);
INSERT INTO "ytora"."sys_dict" VALUES (120000000000045, 'ytora', '2026-01-14 23:52:07.268118', 'ytora', '2026-01-14 23:52:07.268118', 'YT-SYS', '俄罗斯族', NULL, '0', NULL, 'nation', '44', '俄罗斯族', 44, 2);
INSERT INTO "ytora"."sys_dict" VALUES (120000000000046, 'ytora', '2026-01-14 23:52:07.268118', 'ytora', '2026-01-14 23:52:07.268118', 'YT-SYS', '鄂温克族', NULL, '0', NULL, 'nation', '45', '鄂温克族', 45, 2);
INSERT INTO "ytora"."sys_dict" VALUES (120000000000047, 'ytora', '2026-01-14 23:52:07.268118', 'ytora', '2026-01-14 23:52:07.268118', 'YT-SYS', '德昂族', NULL, '0', NULL, 'nation', '46', '德昂族', 46, 2);
INSERT INTO "ytora"."sys_dict" VALUES (120000000000048, 'ytora', '2026-01-14 23:52:07.268118', 'ytora', '2026-01-14 23:52:07.268118', 'YT-SYS', '保安族', NULL, '0', NULL, 'nation', '47', '保安族', 47, 2);
INSERT INTO "ytora"."sys_dict" VALUES (120000000000049, 'ytora', '2026-01-14 23:52:07.268118', 'ytora', '2026-01-14 23:52:07.268118', 'YT-SYS', '裕固族', NULL, '0', NULL, 'nation', '48', '裕固族', 48, 2);
INSERT INTO "ytora"."sys_dict" VALUES (120000000000050, 'ytora', '2026-01-14 23:52:07.268118', 'ytora', '2026-01-14 23:52:07.268118', 'YT-SYS', '京族', NULL, '0', NULL, 'nation', '49', '京族', 49, 2);
INSERT INTO "ytora"."sys_dict" VALUES (120000000000051, 'ytora', '2026-01-14 23:52:07.268118', 'ytora', '2026-01-14 23:52:07.268118', 'YT-SYS', '塔塔尔族', NULL, '0', NULL, 'nation', '50', '塔塔尔族', 50, 2);
INSERT INTO "ytora"."sys_dict" VALUES (120000000000052, 'ytora', '2026-01-14 23:52:07.268118', 'ytora', '2026-01-14 23:52:07.268118', 'YT-SYS', '独龙族', NULL, '0', NULL, 'nation', '51', '独龙族', 51, 2);
INSERT INTO "ytora"."sys_dict" VALUES (120000000000053, 'ytora', '2026-01-14 23:52:07.268118', 'ytora', '2026-01-14 23:52:07.268118', 'YT-SYS', '鄂伦春族', NULL, '0', NULL, 'nation', '52', '鄂伦春族', 52, 2);
INSERT INTO "ytora"."sys_dict" VALUES (120000000000054, 'ytora', '2026-01-14 23:52:07.268118', 'ytora', '2026-01-14 23:52:07.268118', 'YT-SYS', '赫哲族', NULL, '0', NULL, 'nation', '53', '赫哲族', 53, 2);
INSERT INTO "ytora"."sys_dict" VALUES (120000000000055, 'ytora', '2026-01-14 23:52:07.268118', 'ytora', '2026-01-14 23:52:07.268118', 'YT-SYS', '门巴族', NULL, '0', NULL, 'nation', '54', '门巴族', 54, 2);
INSERT INTO "ytora"."sys_dict" VALUES (120000000000056, 'ytora', '2026-01-14 23:52:07.268118', 'ytora', '2026-01-14 23:52:07.268118', 'YT-SYS', '珞巴族', NULL, '0', NULL, 'nation', '55', '珞巴族', 55, 2);
INSERT INTO "ytora"."sys_dict" VALUES (120000000000057, 'ytora', '2026-01-14 23:52:07.268118', 'ytora', '2026-01-14 23:52:07.268118', 'YT-SYS', '基诺族', NULL, '0', NULL, 'nation', '56', '基诺族', 56, 2);
INSERT INTO "ytora"."sys_dict" VALUES (130000000000001, 'ytora', '2026-01-14 23:52:14.252925', 'ytora', '2026-01-14 23:52:14.252925', 'YT-SYS', '国家和地区', '0', '0', '国家', 'country', NULL, NULL, 1, 1);
INSERT INTO "ytora"."sys_dict" VALUES (130000000000002, 'ytora', '2026-01-14 23:52:23.160793', 'ytora', '2026-01-14 23:52:23.160793', 'YT-SYS', '中华人民共和国', NULL, '0', NULL, 'country', 'CN', '中国', 1, 2);
INSERT INTO "ytora"."sys_dict" VALUES (130000000000003, 'ytora', '2026-01-14 23:52:23.160793', 'ytora', '2026-01-14 23:52:23.160793', 'YT-SYS', '美利坚合众国', NULL, '0', NULL, 'country', 'US', '美国', 2, 2);
INSERT INTO "ytora"."sys_dict" VALUES (130000000000004, 'ytora', '2026-01-14 23:52:23.160793', 'ytora', '2026-01-14 23:52:23.160793', 'YT-SYS', '日本国', NULL, '0', NULL, 'country', 'JP', '日本', 3, 2);
INSERT INTO "ytora"."sys_dict" VALUES (130000000000005, 'ytora', '2026-01-14 23:52:23.160793', 'ytora', '2026-01-14 23:52:23.160793', 'YT-SYS', '大韩民国', NULL, '0', NULL, 'country', 'KR', '韩国', 4, 2);
INSERT INTO "ytora"."sys_dict" VALUES (130000000000006, 'ytora', '2026-01-14 23:52:23.160793', 'ytora', '2026-01-14 23:52:23.160793', 'YT-SYS', '俄罗斯联邦', NULL, '0', NULL, 'country', 'RU', '俄罗斯', 5, 2);
INSERT INTO "ytora"."sys_dict" VALUES (130000000000007, 'ytora', '2026-01-14 23:52:23.160793', 'ytora', '2026-01-14 23:52:23.160793', 'YT-SYS', '德意志联邦共和国', NULL, '0', NULL, 'country', 'DE', '德国', 6, 2);
INSERT INTO "ytora"."sys_dict" VALUES (130000000000008, 'ytora', '2026-01-14 23:52:23.160793', 'ytora', '2026-01-14 23:52:23.160793', 'YT-SYS', '法兰西共和国', NULL, '0', NULL, 'country', 'FR', '法国', 7, 2);
INSERT INTO "ytora"."sys_dict" VALUES (130000000000009, 'ytora', '2026-01-14 23:52:23.160793', 'ytora', '2026-01-14 23:52:23.160793', 'YT-SYS', '大不列颠及北爱尔兰联合王国', NULL, '0', NULL, 'country', 'GB', '英国', 8, 2);
INSERT INTO "ytora"."sys_dict" VALUES (130000000000010, 'ytora', '2026-01-14 23:52:23.160793', 'ytora', '2026-01-14 23:52:23.160793', 'YT-SYS', '加拿大', NULL, '0', NULL, 'country', 'CA', '加拿大', 9, 2);
INSERT INTO "ytora"."sys_dict" VALUES (130000000000011, 'ytora', '2026-01-14 23:52:23.160793', 'ytora', '2026-01-14 23:52:23.160793', 'YT-SYS', '澳大利亚联邦', NULL, '0', NULL, 'country', 'AU', '澳大利亚', 10, 2);
INSERT INTO "ytora"."sys_dict" VALUES (130000000000012, 'ytora', '2026-01-14 23:52:23.160793', 'ytora', '2026-01-14 23:52:23.160793', 'YT-SYS', '印度共和国', NULL, '0', NULL, 'country', 'IN', '印度', 11, 2);
INSERT INTO "ytora"."sys_dict" VALUES (130000000000013, 'ytora', '2026-01-14 23:52:23.160793', 'ytora', '2026-01-14 23:52:23.160793', 'YT-SYS', '意大利共和国', NULL, '0', NULL, 'country', 'IT', '意大利', 12, 2);
INSERT INTO "ytora"."sys_dict" VALUES (130000000000014, 'ytora', '2026-01-14 23:52:23.160793', 'ytora', '2026-01-14 23:52:23.160793', 'YT-SYS', '西班牙王国', NULL, '0', NULL, 'country', 'ES', '西班牙', 13, 2);
INSERT INTO "ytora"."sys_dict" VALUES (130000000000015, 'ytora', '2026-01-14 23:52:23.160793', 'ytora', '2026-01-14 23:52:23.160793', 'YT-SYS', '新加坡共和国', NULL, '0', NULL, 'country', 'SG', '新加坡', 14, 2);
INSERT INTO "ytora"."sys_dict" VALUES (140000000000001, 'ytora', '2026-01-14 23:55:08.287891', 'ytora', '2026-01-14 23:55:08.287891', 'YT-SYS', '中国政治面貌', '0', '0', '政治面貌', 'politicalStatus', NULL, NULL, 1, 1);
INSERT INTO "ytora"."sys_dict" VALUES (140000000000002, 'ytora', '2026-01-14 23:55:12.546647', 'ytora', '2026-01-14 23:55:12.546647', 'YT-SYS', '中国共产党党员', NULL, '0', NULL, 'politicalStatus', 'party_member', '中共党员', 1, 2);
INSERT INTO "ytora"."sys_dict" VALUES (140000000000003, 'ytora', '2026-01-14 23:55:12.546647', 'ytora', '2026-01-14 23:55:12.546647', 'YT-SYS', '中国共产党预备党员', NULL, '0', NULL, 'politicalStatus', 'probationary_member', '预备党员', 2, 2);
INSERT INTO "ytora"."sys_dict" VALUES (140000000000004, 'ytora', '2026-01-14 23:55:12.546647', 'ytora', '2026-01-14 23:55:12.546647', 'YT-SYS', '中国共产主义青年团团员', NULL, '0', NULL, 'politicalStatus', 'league_member', '共青团员', 3, 2);
INSERT INTO "ytora"."sys_dict" VALUES (140000000000005, 'ytora', '2026-01-14 23:55:12.546647', 'ytora', '2026-01-14 23:55:12.546647', 'YT-SYS', '民主党派成员', NULL, '0', NULL, 'politicalStatus', 'democratic_party', '民主党派', 4, 2);
INSERT INTO "ytora"."sys_dict" VALUES (140000000000006, 'ytora', '2026-01-14 23:55:12.546647', 'ytora', '2026-01-14 23:55:12.546647', 'YT-SYS', '无党派人士/群众', NULL, '0', NULL, 'politicalStatus', 'masses', '群众', 5, 2);
INSERT INTO "ytora"."sys_dict" VALUES (140000000000010, 'ytora', '2026-01-14 23:55:16.820366', 'ytora', '2026-01-14 23:55:16.820366', 'YT-SYS', '证件类型', '0', '0', '证件类型', 'idCardType', NULL, NULL, 1, 1);
INSERT INTO "ytora"."sys_dict" VALUES (140000000000011, 'ytora', '2026-01-14 23:55:21.190008', 'ytora', '2026-01-14 23:55:21.190008', 'YT-SYS', '中华人民共和国居民身份证', NULL, '0', NULL, 'idCardType', 'id_card', '居民身份证', 1, 2);
INSERT INTO "ytora"."sys_dict" VALUES (140000000000012, 'ytora', '2026-01-14 23:55:21.190008', 'ytora', '2026-01-14 23:55:21.190008', 'YT-SYS', '护照', NULL, '0', NULL, 'idCardType', 'passport', '护照', 2, 2);
INSERT INTO "ytora"."sys_dict" VALUES (140000000000013, 'ytora', '2026-01-14 23:55:21.190008', 'ytora', '2026-01-14 23:55:21.190008', 'YT-SYS', '港澳居民来往内地通行证', NULL, '0', NULL, 'idCardType', 'hk_macao_pass', '港澳通行证', 3, 2);
INSERT INTO "ytora"."sys_dict" VALUES (140000000000014, 'ytora', '2026-01-14 23:55:21.190008', 'ytora', '2026-01-14 23:55:21.190008', 'YT-SYS', '台湾居民来往大陆通行证', NULL, '0', NULL, 'idCardType', 'taiwan_pass', '台湾通行证', 4, 2);
INSERT INTO "ytora"."sys_dict" VALUES (140000000000015, 'ytora', '2026-01-14 23:55:21.190008', 'ytora', '2026-01-14 23:55:21.190008', 'YT-SYS', '军官证', NULL, '0', NULL, 'idCardType', 'military_id', '军官证', 5, 2);
INSERT INTO "ytora"."sys_dict" VALUES (140000000000020, 'ytora', '2026-01-14 23:55:24.75095', 'ytora', '2026-01-14 23:55:24.75095', 'YT-SYS', '婚姻状况', '0', '0', '婚姻状况', 'maritalStatus', NULL, NULL, 1, 1);
INSERT INTO "ytora"."sys_dict" VALUES (140000000000021, 'ytora', '2026-01-14 23:55:28.099054', 'ytora', '2026-01-14 23:55:28.099054', 'YT-SYS', '未婚', NULL, '0', NULL, 'maritalStatus', 'unmarried', '未婚', 1, 2);
INSERT INTO "ytora"."sys_dict" VALUES (140000000000022, 'ytora', '2026-01-14 23:55:28.099054', 'ytora', '2026-01-14 23:55:28.099054', 'YT-SYS', '已婚', NULL, '0', NULL, 'maritalStatus', 'married', '已婚', 2, 2);
INSERT INTO "ytora"."sys_dict" VALUES (140000000000023, 'ytora', '2026-01-14 23:55:28.099054', 'ytora', '2026-01-14 23:55:28.099054', 'YT-SYS', '离异', NULL, '0', NULL, 'maritalStatus', 'divorced', '离异', 3, 2);
INSERT INTO "ytora"."sys_dict" VALUES (140000000000024, 'ytora', '2026-01-14 23:55:28.099054', 'ytora', '2026-01-14 23:55:28.099054', 'YT-SYS', '丧偶', NULL, '0', NULL, 'maritalStatus', 'widowed', '丧偶', 4, 2);
INSERT INTO "ytora"."sys_dict" VALUES (140000000000030, 'ytora', '2026-01-14 23:55:31.788657', 'ytora', '2026-01-14 23:55:31.788657', 'YT-SYS', '学历', '0', '0', '学历', 'educationLevel', NULL, NULL, 1, 1);
INSERT INTO "ytora"."sys_dict" VALUES (140000000000031, 'ytora', '2026-01-14 23:55:35.239496', 'ytora', '2026-01-14 23:55:35.239496', 'YT-SYS', '小学', NULL, '0', NULL, 'educationLevel', 'primary', '小学', 1, 2);
INSERT INTO "ytora"."sys_dict" VALUES (140000000000032, 'ytora', '2026-01-14 23:55:35.239496', 'ytora', '2026-01-14 23:55:35.239496', 'YT-SYS', '初中', NULL, '0', NULL, 'educationLevel', 'junior_middle', '初中', 2, 2);
INSERT INTO "ytora"."sys_dict" VALUES (140000000000033, 'ytora', '2026-01-14 23:55:35.239496', 'ytora', '2026-01-14 23:55:35.239496', 'YT-SYS', '高中/中专', NULL, '0', NULL, 'educationLevel', 'senior_middle', '高中/中专', 3, 2);
INSERT INTO "ytora"."sys_dict" VALUES (140000000000034, 'ytora', '2026-01-14 23:55:35.239496', 'ytora', '2026-01-14 23:55:35.239496', 'YT-SYS', '大专', NULL, '0', NULL, 'educationLevel', 'college', '大专', 4, 2);
INSERT INTO "ytora"."sys_dict" VALUES (140000000000035, 'ytora', '2026-01-14 23:55:35.239496', 'ytora', '2026-01-14 23:55:35.239496', 'YT-SYS', '本科', NULL, '0', NULL, 'educationLevel', 'bachelor', '本科', 5, 2);
INSERT INTO "ytora"."sys_dict" VALUES (140000000000036, 'ytora', '2026-01-14 23:55:35.239496', 'ytora', '2026-01-14 23:55:35.239496', 'YT-SYS', '硕士研究生', NULL, '0', NULL, 'educationLevel', 'master', '硕士', 6, 2);
INSERT INTO "ytora"."sys_dict" VALUES (140000000000037, 'ytora', '2026-01-14 23:55:35.239496', 'ytora', '2026-01-14 23:55:35.239496', 'YT-SYS', '博士研究生', NULL, '0', NULL, 'educationLevel', 'doctor', '博士', 7, 2);
INSERT INTO "ytora"."sys_dict" VALUES (99273169043456, 'ytora', '2026-01-14 12:46:28.336214', 'ytora', '2026-01-15 10:27:58.084412', 'YT-HR-RECR', NULL, NULL, '0', '资源类型', 'permission_type', '1', '接口', 1, 2);
INSERT INTO "ytora"."sys_dict" VALUES (99321308250112, 'ytora', '2026-01-14 12:58:42.892316', 'ytora', '2026-01-15 10:28:20.089333', 'YT-HR-RECR', NULL, NULL, '0', '资源类型', 'permission_type', '4', '表格', 4, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90270000000004, 'ytora', '2026-01-15 10:00:00', 'ytora', '2026-01-15 10:00:00', 'YT-HR-RECR', 'Walmart shopping bag', NULL, '0', NULL, 'sex', 'WB', '沃尔玛购物袋', 4, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000006, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', '硬件兼容模式开启，支持双系统启动', NULL, '0', NULL, 'sex', 'intersex', '双性人', 6, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000007, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', '出厂默认设置，未进行任何魔改', NULL, '0', NULL, 'sex', 'cisgender', '顺性别', 7, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000008, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', '系统OTA升级中，正在刷入新固件', NULL, '0', NULL, 'sex', 'transgender', '跨性别', 8, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000009, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', '早期版本补丁，硬件层面的重构', NULL, '0', NULL, 'sex', 'transsexual', '变性人', 9, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000010, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', '拒绝0和1的二进制暴政，我是量子态', NULL, '0', NULL, 'sex', 'non_binary', '非二元', 10, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000011, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', '性别界的赛博朋克 2077', NULL, '0', NULL, 'sex', 'genderqueer', '性别酷儿', 11, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000012, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', '今天出门急，把性别忘家里了', NULL, '0', NULL, 'sex', 'agender', '无性别', 12, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000013, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', '瑞士：永远保持中立', NULL, '0', NULL, 'sex', 'neutrois', '中性性别', 13, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000014, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', '由于太帅，导致分不清性别', NULL, '0', NULL, 'sex', 'androgynous', '雌雄同体', 14, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000015, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', '支持双卡双待，信号更强', NULL, '0', NULL, 'sex', 'bigender', '双性别', 15, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000016, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', '这就购买三相之力，伤害爆炸', NULL, '0', NULL, 'sex', 'trigender', '三性别', 16, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000017, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', '小孩子才做选择，我全都要', NULL, '0', NULL, 'sex', 'pangender', '泛性别', 17, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000018, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', 'CPU多线程处理中，请勿打扰', NULL, '0', NULL, 'sex', 'polygender', '多性别', 18, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000019, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', '已收集所有图鉴，达成白金成就', NULL, '0', NULL, 'sex', 'omnigender', '全性别', 19, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000020, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', '正在加载资源，卡在 50% 了', NULL, '0', NULL, 'sex', 'intergender', '介于性别之间', 20, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000021, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', '挂空挡滑行，省油', NULL, '0', NULL, 'sex', 'gender_neutral', '性别中立', 21, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000022, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', 'NullPointerException (空指针异常)', NULL, '0', NULL, 'sex', 'genderless', '无性别属性', 22, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000023, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', '独行侠，自带 BGM 的性别', NULL, '0', NULL, 'sex', 'maverique', '独立性别', 23, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000024, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', '像猫一样是液体，建议用杯子装着', NULL, '0', NULL, 'sex', 'genderfluid', '性别流动', 24, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000025, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', '信号不好，性别时有时无', NULL, '0', NULL, 'sex', 'genderflux', '性别强度变化', 25, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000026, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', '半糖去冰，谢谢', NULL, '0', NULL, 'sex', 'demigender', '半性别', 26, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000027, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', '半个男的，另一半在打游戏', NULL, '0', NULL, 'sex', 'demiboy', '半男性', 27, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000028, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', '半个女的，另一半在喝奶茶', NULL, '0', NULL, 'sex', 'demigirl', '半女性', 28, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000029, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', '天秤座？反正含量很低', NULL, '0', NULL, 'sex', 'libragender', '弱性别', 29, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000030, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', '虽然不中，亦不远矣', NULL, '0', NULL, 'sex', 'paragender', '近性别', 30, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000031, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', '多重影分身之术！', NULL, '0', NULL, 'sex', 'multigender', '多重性别', 31, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000032, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', '又变又流，建议去看医生', NULL, '0', NULL, 'sex', 'fluidflux', '双重流动', 32, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000033, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', '拿显微镜才能看到的性别', NULL, '0', NULL, 'sex', 'nanogender', '极弱性别', 33, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000034, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', '灰度测试中，尚未发布到生产环境', NULL, '0', NULL, 'sex', 'greygender', '灰性别', 34, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000035, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', '随便吧，累了，毁灭吧', NULL, '0', NULL, 'sex', 'cassgender', '性别冷漠', 35, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000036, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', '性别？关我屁事', NULL, '0', NULL, 'sex', 'apagender', '性别疏离', 36, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000037, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', '在性别的边缘疯狂试探', NULL, '0', NULL, 'sex', 'perigender', '边缘性别', 37, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000038, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', '像是女的，但带点赛博风味', NULL, '0', NULL, 'sex', 'juxera', '女性取向的非二元', 38, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000039, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', '我的脑电波决定了我今天是谁', NULL, '0', NULL, 'sex', 'neurogender', '神经性别', 39, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000040, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', '只有我自己懂的加密频道', NULL, '0', NULL, 'sex', 'autigender', '自闭性别', 40, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000041, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', '边界徘徊者，危险请勿靠近', NULL, '0', NULL, 'sex', 'bordergender', '边缘人格性别', 41, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000042, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', '感受到了你的性别，现在它是我的了', NULL, '0', NULL, 'sex', 'empathgender', '共情性别', 42, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000043, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', '我就是我，不一样的烟火', NULL, '0', NULL, 'sex', 'egogender', '自我中心性别', 43, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000044, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', '看心情，心情好我是彭于晏', NULL, '0', NULL, 'sex', 'affectgender', '情绪性别', 44, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000045, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', '梦里啥都有，别叫醒我', NULL, '0', NULL, 'sex', 'dreamgender', '梦境性别', 45, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000046, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', '潜意识里我是一架F-22猛禽战斗机', NULL, '0', NULL, 'sex', 'subgender', '潜意识性别', 46, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000047, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', '我思故我在，我想我是啥就是啥', NULL, '0', NULL, 'sex', 'cogigender', '认知性别', 47, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000048, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', '怒气值满变身绿巨人', NULL, '0', NULL, 'sex', 'moodgender', '情绪驱动性别', 48, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000049, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', '别逼我，压力一大我连物种都会变', NULL, '0', NULL, 'sex', 'stressgender', '压力相关性别', 49, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000050, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', '这是一个悲伤的故事', NULL, '0', NULL, 'sex', 'trauma_gender', '创伤性别', 50, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000051, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', '玩的就是心理战术', NULL, '0', NULL, 'sex', 'psygender', '心理性别', 51, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000052, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', '我们的征途是星辰大海', NULL, '0', NULL, 'sex', 'stargender', '星性别', 52, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000053, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', '代表月亮消灭你', NULL, '0', NULL, 'sex', 'moongender', '月性别', 53, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000054, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', '赞美太阳！ \[T]/', NULL, '0', NULL, 'sex', 'solgender', '太阳性别', 54, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000055, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', '数据已上传至云端，像云一样飘', NULL, '0', NULL, 'sex', 'cloudgender', '云性别', 55, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000056, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', '上善若水，或者就是纯净水', NULL, '0', NULL, 'sex', 'watergender', '水性别', 56, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000057, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', '燃烧吧，我的小宇宙！', NULL, '0', NULL, 'sex', 'firegender', '火性别', 57, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000058, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', '稳如老狗，不动如山', NULL, '0', NULL, 'sex', 'earthgender', '地性别', 58, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000059, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', '德鲁伊变身，回归自然', NULL, '0', NULL, 'sex', 'naturegender', '自然性别', 59, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000060, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', '我是一朵花，请勿践踏', NULL, '0', NULL, 'sex', 'floragender', '植物性别', 60, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000061, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', '春天到了，万物复苏...', NULL, '0', NULL, 'sex', 'faunagender', '动物性别', 61, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000062, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', '喵喵喵？铲屎官退下', NULL, '0', NULL, 'sex', 'catgender', '猫性别', 62, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000063, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', '北方孤狼，申请出战', NULL, '0', NULL, 'sex', 'wolfgender', '狼性别', 63, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000064, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', '恶龙咆哮，嗷呜~', NULL, '0', NULL, 'sex', 'dragongender', '龙性别', 64, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000065, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', '传说中的 SSR，爆率极低', NULL, '0', NULL, 'sex', 'mythgender', '神话性别', 65, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000066, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', '虚空恐惧科加斯，吞噬一切', NULL, '0', NULL, 'sex', 'voidgender', '虚无性别', 66, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000067, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', '以太网连接中...请稍候', NULL, '0', NULL, 'sex', 'aethergender', '以太性别', 67, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000068, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', '混沌初开，盘古都懵了', NULL, '0', NULL, 'sex', 'chaosgender', '混沌性别', 68, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000069, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', '强迫症福音，一切整整齐齐', NULL, '0', NULL, 'sex', 'ordergender', '秩序性别', 69, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000070, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', '你相信光吗？迪迦！', NULL, '0', NULL, 'sex', 'lightgender', '光性别', 70, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000071, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', '黑暗骑士崛起，I am Batman', NULL, '0', NULL, 'sex', 'darkgender', '暗性别', 71, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000072, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', '动次打次，苍茫的天涯是我的爱', NULL, '0', NULL, 'sex', 'soundgender', '声音性别', 72, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000073, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', '时间管理大师', NULL, '0', NULL, 'sex', 'timegender', '时间性别', 73, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000074, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', '萨满双修，灵魂绑定', NULL, '0', NULL, 'sex', 'two_spirit', '双灵', 74, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000075, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', '南亚特供版本，锁区', NULL, '0', NULL, 'sex', 'hijra', '南亚文化性别', 75, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000076, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', '道生一，一生二，二生三，三生万物', NULL, '0', NULL, 'sex', 'third_gender', '第三性别', 76, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000077, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', '异形：夺命舰', NULL, '0', NULL, 'sex', 'xenogender', '外来性别', 77, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000078, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', '火星人你好，地球很危险，快回火星去', NULL, '0', NULL, 'sex', 'aliengender', '异类性别', 78, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000079, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', '版本更新太快，描述文档还没写', NULL, '0', NULL, 'sex', 'novigender', '无法描述的性别', 79, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000080, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', '法外狂徒张三', NULL, '0', NULL, 'sex', 'gender_outlaw', '性别越界者', 80, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000081, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', '您有一份新的问卷调查待填写', NULL, '0', NULL, 'sex', 'questioning_gender', '性别探索中', 81, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000082, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', 'Uncaught ReferenceError: gender is not defined', NULL, '0', NULL, 'sex', 'undefined_gender', '未定义性别', 82, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000083, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', '那个...那个...就算其他吧', NULL, '0', NULL, 'sex', 'other_gender', '其他', 83, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000084, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', '变体洛基，时间管理局通缉对象', NULL, '0', NULL, 'sex', 'gender_variant', '性别变异', 84, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000085, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', '保护生物多样性，人人有责', NULL, '0', NULL, 'sex', 'gender_diverse', '性别多元', 85, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000086, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', '无可奉告', NULL, '0', NULL, 'sex', 'prefer_not_to_say', '不愿透露', 86, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000087, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', '居然没列出来？这系统做得不行', NULL, '0', NULL, 'sex', 'not_listed', '未列出', 87, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000088, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', '让我再想想...CPU过热降频中', NULL, '0', NULL, 'sex', 'unsure', '不确定', 88, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000089, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', '请听我狡辩', NULL, '0', NULL, 'sex', 'self_described', '自我描述', 89, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000090, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', '出厂设置：男', NULL, '0', NULL, 'sex', 'amab', '出生指派为男', 90, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000091, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', '出厂设置：女', NULL, '0', NULL, 'sex', 'afab', '出生指派为女', 91, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000092, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', '建号选了男角色，后来转职了', NULL, '0', NULL, 'sex', 'amab_non_binary', '出生男的非二元', 92, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000093, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', '建号选了女角色，后来转职了', NULL, '0', NULL, 'sex', 'afab_non_binary', '出生女的非二元', 93, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000094, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', '叛逆期到了，拒绝被定义', NULL, '0', NULL, 'sex', 'gender_non_conforming', '不符合性别规范', 94, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000095, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', '性别膨胀了，注意通货膨胀', NULL, '0', NULL, 'sex', 'gender_expansive', '性别扩展', 95, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000096, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', '十万个为什么之我是谁', NULL, '0', NULL, 'sex', 'gender_questioning', '性别疑问中', 96, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000097, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', '我造词能力满分，自己捏人', NULL, '0', NULL, 'sex', 'gender_creative', '性别创造性', 97, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000098, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', '无法归类，建议重修生物分类学', NULL, '0', NULL, 'sex', 'gender_non_classifiable', '不可归类', 98, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000099, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', '影分身 + 多重人格', NULL, '0', NULL, 'sex', 'multiple_genders', '多重性别', 99, 2);
INSERT INTO "ytora"."sys_dict" VALUES (90300000000100, 'ytora', '2026-01-15 12:00:00', 'ytora', '2026-01-15 12:00:00', 'YT-HR-RECR', '跳出三界外，不在五行中', NULL, '0', NULL, 'sex', 'no_gender_category', '无性别分类', 100, 2);
INSERT INTO "ytora"."sys_dict" VALUES (99550036164608, 'ytora', '2026-01-14 13:56:53.003875', 'ytora', '2026-01-16 00:21:08.230907', 'YT-HR-RECR', '数据的序号', NULL, '0', '表格列类型', 'tableColType', 'table-col::index', '表格列-索引', 2, 2);
INSERT INTO "ytora"."sys_dict" VALUES (99576446255104, 'ytora', '2026-01-14 14:03:35.989172', 'ytora', '2026-01-16 00:21:41.691025', 'YT-HR-RECR', '表格列里面如果需要存放非文本内容，比如按钮、switch、tag等，就需要包一层table-col::flex', NULL, '0', '表格列类型', 'tableColType', 'table-col::flex', '表格列-组件', 6, 2);
INSERT INTO "ytora"."sys_dict" VALUES (107728383639552, 'ytora', '2026-01-16 00:36:44.6764', 'ytora', '2026-01-16 00:36:44.678402', 'YT-HR-RECR', '', NULL, '0', '', 'formItemType', 'form', '表单项主体', 0, 2);
INSERT INTO "ytora"."sys_dict" VALUES (99548433678336, 'ytora', '2026-01-14 13:56:28.551034', 'ytora', '2026-01-16 00:46:14.833048', 'YT-HR-RECR', '表格主体', NULL, '0', '表格列类型', 'tableColType', 'table', '表格主体', 1, 2);

-- ----------------------------
-- Table structure for sys_file
-- ----------------------------
DROP TABLE IF EXISTS "ytora"."sys_file";
CREATE TABLE "ytora"."sys_file" (
  "id" int8 NOT NULL,
  "create_by" varchar(16) COLLATE "pg_catalog"."default",
  "create_time" timestamp(6),
  "update_by" varchar(16) COLLATE "pg_catalog"."default",
  "update_time" timestamp(6),
  "depart_code" varchar(255) COLLATE "pg_catalog"."default",
  "remark" varchar(255) COLLATE "pg_catalog"."default",
  "status" varchar(255) COLLATE "pg_catalog"."default" DEFAULT '1'::character varying,
  "file_id" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "folder_id" varchar(255) COLLATE "pg_catalog"."default",
  "file_name" varchar(255) COLLATE "pg_catalog"."default",
  "file_size" int8,
  "file_size_text" varchar(255) COLLATE "pg_catalog"."default",
  "file_type" varchar(255) COLLATE "pg_catalog"."default",
  "download_count" int4
)
;
COMMENT ON COLUMN "ytora"."sys_file"."id" IS '主键ID';
COMMENT ON COLUMN "ytora"."sys_file"."create_by" IS '创建人';
COMMENT ON COLUMN "ytora"."sys_file"."create_time" IS '创建时间';
COMMENT ON COLUMN "ytora"."sys_file"."update_by" IS '更新人';
COMMENT ON COLUMN "ytora"."sys_file"."update_time" IS '更新时间';
COMMENT ON COLUMN "ytora"."sys_file"."depart_code" IS '创建者所属部门';
COMMENT ON COLUMN "ytora"."sys_file"."remark" IS '数据备注';
COMMENT ON COLUMN "ytora"."sys_file"."status" IS '数据状态';
COMMENT ON COLUMN "ytora"."sys_file"."file_id" IS '文件id';
COMMENT ON COLUMN "ytora"."sys_file"."folder_id" IS '所在文件夹';
COMMENT ON COLUMN "ytora"."sys_file"."file_name" IS '原始文件名称';
COMMENT ON COLUMN "ytora"."sys_file"."file_size" IS '文件大小，单位字节';
COMMENT ON COLUMN "ytora"."sys_file"."file_size_text" IS '文件大小-文本';
COMMENT ON COLUMN "ytora"."sys_file"."file_type" IS '文件类型';
COMMENT ON COLUMN "ytora"."sys_file"."download_count" IS '下载次数';
COMMENT ON TABLE "ytora"."sys_file" IS '系统文件表';

-- ----------------------------
-- Records of sys_file
-- ----------------------------
INSERT INTO "ytora"."sys_file" VALUES (113613662781440, 'ytora', '2026-01-17 01:33:26.879255', 'ytora', '2026-01-17 01:55:41.676038', 'YT-HR-RECR', NULL, NULL, '47ESqcYHYuYaqBxdyLGFVs', '113493766242304', 'qwe.jpg1', 20059167, '19.13 MB', 'application/pdf', 3);
INSERT INTO "ytora"."sys_file" VALUES (113498354679808, 'ytora', '2026-01-17 01:04:07.453057', 'ytora', '2026-01-17 01:32:27.756631', 'YT-HR-RECR', NULL, NULL, '5tRKYeMMGilfO1GRwQ6hVA', '113493766242304', 'c51493f2e0b2fbb5fd5edd45ea628fef198832892.png', 1425747, '1.36 MB', 'image/png', 33);
INSERT INTO "ytora"."sys_file" VALUES (113540977524736, 'ytora', '2026-01-17 01:14:57.826274', 'ytora', '2026-01-17 01:40:50.052785', 'YT-HR-RECR', NULL, NULL, '5kvZoiY5EzRorRBBdT8tD3', '113493766242304', '报纸墙 粉色长发女孩 4K动漫桌面壁纸_彼岸图网.jpg', 3465342, '3.30 MB', 'image/jpeg', 21);
INSERT INTO "ytora"."sys_file" VALUES (113539894214656, 'ytora', '2026-01-17 01:14:41.226973', 'ytora', '2026-01-17 01:41:05.034747', 'YT-HR-RECR', NULL, NULL, '2Pek1xaBm7SeeeWJpqabBf', '113493766242304', 'Xshell-8.0.0087.exe', 46767464, '44.60 MB', 'application/x-msdownload', 1);
INSERT INTO "ytora"."sys_file" VALUES (113682208325632, 'ytora', '2026-01-17 01:50:52.821792', 'ytora', '2026-01-19 01:06:06.404902', 'YT-HR-RECR', NULL, NULL, '3X0r8KT0xY9ZIxHWxSKk6G', '113493766242304', '115568325_p0_master1200.jpg', 628394, '613.67 KB', 'image/jpeg', 23);
INSERT INTO "ytora"."sys_file" VALUES (113695704154112, 'ytora', '2026-01-17 01:54:18.743042', 'ytora', '2026-01-19 01:06:07.229015', 'YT-HR-RECR', NULL, NULL, '5XYIB9fiPg5v87lbTXEdcl', '113493766242304', 'qwe.jpg', 3465342, '3.30 MB', 'image/jpeg', 15);
INSERT INTO "ytora"."sys_file" VALUES (113705789423616, 'ytora', '2026-01-17 01:56:52.656142', 'ytora', '2026-01-19 01:06:08.220594', 'YT-HR-RECR', NULL, NULL, '4y6hAKPjFjcKJyBu6oySSF', '113493766242304', '子系统服务器.txt', 252, '252.00 B', 'text/plain', 8);

-- ----------------------------
-- Table structure for sys_folder
-- ----------------------------
DROP TABLE IF EXISTS "ytora"."sys_folder";
CREATE TABLE "ytora"."sys_folder" (
  "id" int8 NOT NULL,
  "create_by" varchar(16) COLLATE "pg_catalog"."default",
  "create_time" timestamp(6),
  "update_by" varchar(16) COLLATE "pg_catalog"."default",
  "update_time" timestamp(6),
  "depart_code" varchar(255) COLLATE "pg_catalog"."default",
  "remark" varchar(255) COLLATE "pg_catalog"."default",
  "status" varchar(255) COLLATE "pg_catalog"."default" DEFAULT '1'::character varying,
  "pid" varchar(255) COLLATE "pg_catalog"."default" NOT NULL DEFAULT '0'::character varying,
  "path" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "depth" int4
)
;
COMMENT ON COLUMN "ytora"."sys_folder"."id" IS '主键ID';
COMMENT ON COLUMN "ytora"."sys_folder"."create_by" IS '创建人';
COMMENT ON COLUMN "ytora"."sys_folder"."create_time" IS '创建时间';
COMMENT ON COLUMN "ytora"."sys_folder"."update_by" IS '更新人';
COMMENT ON COLUMN "ytora"."sys_folder"."update_time" IS '更新时间';
COMMENT ON COLUMN "ytora"."sys_folder"."depart_code" IS '创建者所属部门';
COMMENT ON COLUMN "ytora"."sys_folder"."remark" IS '数据备注';
COMMENT ON COLUMN "ytora"."sys_folder"."status" IS '数据状态';
COMMENT ON COLUMN "ytora"."sys_folder"."pid" IS '父文件夹ID';
COMMENT ON COLUMN "ytora"."sys_folder"."path" IS '文件夹路径';
COMMENT ON COLUMN "ytora"."sys_folder"."depth" IS '文件夹深度';
COMMENT ON TABLE "ytora"."sys_folder" IS '系统文件夹';

-- ----------------------------
-- Records of sys_folder
-- ----------------------------
INSERT INTO "ytora"."sys_folder" VALUES (113493430566912, 'ytora', '2026-01-17 01:02:52.313094', 'ytora', '2026-01-17 01:02:52.313094', 'YT-HR-RECR', NULL, NULL, '0', 'root', 0);
INSERT INTO "ytora"."sys_folder" VALUES (113493766242304, 'ytora', '2026-01-17 01:02:57.439401', 'ytora', '2026-01-17 01:02:57.439401', 'YT-HR-RECR', NULL, NULL, '113493430566912', 'test', 0);
INSERT INTO "ytora"."sys_folder" VALUES (118113090863104, 'ytora', '2026-01-17 20:37:42.68973', 'ytora', '2026-01-17 20:37:42.68973', 'YT-HR-RECR', NULL, NULL, '113493430566912', '1231212', 0);
INSERT INTO "ytora"."sys_folder" VALUES (118114378186752, 'ytora', '2026-01-17 20:38:02.382622', 'ytora', '2026-01-17 20:38:02.382622', 'YT-HR-RECR', NULL, NULL, '113493766242304', '21312312', 0);

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS "ytora"."sys_log";
CREATE TABLE "ytora"."sys_log" (
  "id" int8 NOT NULL,
  "create_by" varchar(16) COLLATE "pg_catalog"."default",
  "create_time" timestamp(6),
  "update_by" varchar(16) COLLATE "pg_catalog"."default",
  "update_time" timestamp(6),
  "depart_code" varchar(255) COLLATE "pg_catalog"."default",
  "remark" varchar(255) COLLATE "pg_catalog"."default",
  "status" varchar(255) COLLATE "pg_catalog"."default",
  "type" int4,
  "ip" varchar(255) COLLATE "pg_catalog"."default",
  "trace_id" varchar(255) COLLATE "pg_catalog"."default",
  "thread" varchar(255) COLLATE "pg_catalog"."default",
  "request_url" varchar(255) COLLATE "pg_catalog"."default",
  "class_name" varchar(255) COLLATE "pg_catalog"."default",
  "method_name" varchar(255) COLLATE "pg_catalog"."default",
  "params" varchar(255) COLLATE "pg_catalog"."default",
  "result_length" int4,
  "cost" int8,
  "content" varchar(255) COLLATE "pg_catalog"."default",
  "error_msg" varchar(255) COLLATE "pg_catalog"."default",
  "error_stack" varchar(255) COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "ytora"."sys_log"."id" IS '主键ID';
COMMENT ON COLUMN "ytora"."sys_log"."create_by" IS '创建人';
COMMENT ON COLUMN "ytora"."sys_log"."create_time" IS '创建时间';
COMMENT ON COLUMN "ytora"."sys_log"."update_by" IS '更新人';
COMMENT ON COLUMN "ytora"."sys_log"."update_time" IS '更新时间';
COMMENT ON COLUMN "ytora"."sys_log"."depart_code" IS '创建者所属部门';
COMMENT ON COLUMN "ytora"."sys_log"."remark" IS '数据备注';
COMMENT ON COLUMN "ytora"."sys_log"."status" IS '数据状态';
COMMENT ON COLUMN "ytora"."sys_log"."type" IS '日志类型';
COMMENT ON COLUMN "ytora"."sys_log"."ip" IS '操作人ip';
COMMENT ON COLUMN "ytora"."sys_log"."trace_id" IS '链路跟踪 ID，用于聚合同一次调用的所有日志';
COMMENT ON COLUMN "ytora"."sys_log"."thread" IS '所在线程信息';
COMMENT ON COLUMN "ytora"."sys_log"."request_url" IS 'HTTP 请求路径';
COMMENT ON COLUMN "ytora"."sys_log"."class_name" IS 'HTTP 请求路径';
COMMENT ON COLUMN "ytora"."sys_log"."method_name" IS '日志发生的方法名';
COMMENT ON COLUMN "ytora"."sys_log"."params" IS '参数';
COMMENT ON COLUMN "ytora"."sys_log"."result_length" IS '返回值大小';
COMMENT ON COLUMN "ytora"."sys_log"."cost" IS '请求耗时';
COMMENT ON COLUMN "ytora"."sys_log"."content" IS '日志主体内容';
COMMENT ON COLUMN "ytora"."sys_log"."error_msg" IS '错误信息';
COMMENT ON COLUMN "ytora"."sys_log"."error_stack" IS '错误堆栈信息';
COMMENT ON TABLE "ytora"."sys_log" IS '日志表';

-- ----------------------------
-- Records of sys_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS "ytora"."sys_permission";
CREATE TABLE "ytora"."sys_permission" (
  "id" int8 NOT NULL,
  "create_by" varchar(16) COLLATE "pg_catalog"."default",
  "create_time" timestamp(6),
  "update_by" varchar(16) COLLATE "pg_catalog"."default",
  "update_time" timestamp(6),
  "depart_code" varchar(255) COLLATE "pg_catalog"."default",
  "remark" varchar(255) COLLATE "pg_catalog"."default",
  "status" varchar(255) COLLATE "pg_catalog"."default",
  "pid" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "permission_name" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "permission_code" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "permission_type" int2,
  "icon" varchar(255) COLLATE "pg_catalog"."default",
  "meta" jsonb,
  "visible" bool,
  "index" int4
)
;
COMMENT ON COLUMN "ytora"."sys_permission"."id" IS '主键ID';
COMMENT ON COLUMN "ytora"."sys_permission"."create_by" IS '创建人';
COMMENT ON COLUMN "ytora"."sys_permission"."create_time" IS '创建时间';
COMMENT ON COLUMN "ytora"."sys_permission"."update_by" IS '更新人';
COMMENT ON COLUMN "ytora"."sys_permission"."update_time" IS '更新时间';
COMMENT ON COLUMN "ytora"."sys_permission"."depart_code" IS '创建者所属部门';
COMMENT ON COLUMN "ytora"."sys_permission"."remark" IS '数据备注';
COMMENT ON COLUMN "ytora"."sys_permission"."status" IS '数据状态';
COMMENT ON COLUMN "ytora"."sys_permission"."pid" IS '父资源id';
COMMENT ON COLUMN "ytora"."sys_permission"."permission_name" IS '资源名称';
COMMENT ON COLUMN "ytora"."sys_permission"."permission_code" IS '资源编码，例如：接口地址、页面的路由地址、页面元素(按钮)的唯一标识';
COMMENT ON COLUMN "ytora"."sys_permission"."permission_type" IS '资源类型，1-接口、2-页面、3-DOM、4-表格、5-表单';
COMMENT ON COLUMN "ytora"."sys_permission"."icon" IS '图标';
COMMENT ON COLUMN "ytora"."sys_permission"."meta" IS '元数据';
COMMENT ON COLUMN "ytora"."sys_permission"."visible" IS '是否可见';
COMMENT ON COLUMN "ytora"."sys_permission"."index" IS '排序';
COMMENT ON TABLE "ytora"."sys_permission" IS '资源表';

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO "ytora"."sys_permission" VALUES (4, NULL, NULL, NULL, NULL, NULL, NULL, '1', '1', '资源管理', '/rbac/permission', 2, 'GridOutline', NULL, 't', 3);
INSERT INTO "ytora"."sys_permission" VALUES (9, NULL, NULL, NULL, NULL, NULL, NULL, '1', '6', '数据库管理', '/sys/db', 2, 'ServerOutline', NULL, 't', 0);
INSERT INTO "ytora"."sys_permission" VALUES (20, NULL, NULL, 'ytora', '2026-01-10 02:44:34.377905', NULL, NULL, '1', '0', '公共模块', '/common', 2, 'LockOpenOutline', NULL, 't', 4);
INSERT INTO "ytora"."sys_permission" VALUES (5, NULL, NULL, NULL, NULL, NULL, NULL, '1', '1', '部门管理', '/rbac/depart', 2, 'TriangleOutline', NULL, 't', 4);
INSERT INTO "ytora"."sys_permission" VALUES (6, NULL, NULL, NULL, NULL, NULL, NULL, '1', '0', '系统管理', '/sys', 2, 'SettingsOutline', NULL, 't', 2);
INSERT INTO "ytora"."sys_permission" VALUES (7, NULL, NULL, NULL, NULL, NULL, NULL, '1', '6', '数据字典', '/sys/dict', 2, 'BookOutline', NULL, 't', 0);
INSERT INTO "ytora"."sys_permission" VALUES (8, NULL, NULL, NULL, NULL, NULL, NULL, '1', '6', '缓存管理', '/sys/cache', 2, 'LayersOutline', NULL, 't', 0);
INSERT INTO "ytora"."sys_permission" VALUES (89686066331648, 'ytora', '2026-01-12 20:08:20.723514', 'ytora', '2026-01-12 20:08:20.723514', 'YT-HR-RECR', NULL, NULL, '7', '字典表格', 'sys_dict', 4, NULL, '{"key": null, "attr": {}, "type": "table", "width": null, "height": null}', 't', 1);
INSERT INTO "ytora"."sys_permission" VALUES (74273410318336, 'ytora', '2026-01-10 02:48:42.258668', 'ytora', '2026-01-10 03:34:07.142537', 'YT-HR-RECR', NULL, NULL, '4', '数据权限表格', 'data-permission-table', 4, NULL, '{"key": null, "attr": {}, "type": "table", "width": 0, "height": 0}', 't', 2);
INSERT INTO "ytora"."sys_permission" VALUES (3, NULL, NULL, NULL, NULL, NULL, NULL, '1', '1', '角色管理', '/rbac/role', 2, 'AccessibilityOutline', NULL, 't', 2);
INSERT INTO "ytora"."sys_permission" VALUES (2, NULL, NULL, NULL, NULL, NULL, NULL, '1', '1', '用户管理', '/rbac/user', 2, 'PersonOutline', NULL, 't', 1);
INSERT INTO "ytora"."sys_permission" VALUES (1, NULL, NULL, NULL, NULL, NULL, NULL, '1', '0', '权限管理', '/rbac', 2, 'FingerPrintOutline', NULL, 't', 1);
INSERT INTO "ytora"."sys_permission" VALUES (10, NULL, NULL, NULL, NULL, NULL, NULL, '1', '6', '日志管理', '/sys/log', 2, 'TerminalOutline', NULL, 't', 0);
INSERT INTO "ytora"."sys_permission" VALUES (11, NULL, NULL, NULL, NULL, NULL, NULL, '1', '6', '文件管理', '/sys/file', 2, 'FileTrayFullOutline', NULL, 't', 0);
INSERT INTO "ytora"."sys_permission" VALUES (12, NULL, NULL, NULL, NULL, NULL, NULL, '1', '6', '定时任务', '/sys/cron', 2, 'AlarmOutline', NULL, 't', 0);
INSERT INTO "ytora"."sys_permission" VALUES (14, NULL, NULL, NULL, NULL, NULL, NULL, '1', '6', 'API接口', '/sys/api', 2, 'CodeSlashOutline', NULL, 't', 0);
INSERT INTO "ytora"."sys_permission" VALUES (15, NULL, NULL, NULL, NULL, NULL, NULL, '1', '0', '系统监控', '/monitor', 2, 'SpeedometerOutline', NULL, 't', 3);
INSERT INTO "ytora"."sys_permission" VALUES (21, NULL, NULL, NULL, NULL, NULL, NULL, '1', '20', '图标库', '/common/icon', 2, 'StarOutline', NULL, 't', 0);
INSERT INTO "ytora"."sys_permission" VALUES (22, NULL, NULL, NULL, NULL, NULL, NULL, '1', '20', '系统说明', '/common/remark', 2, 'DocumentOutline', NULL, 't', 0);
INSERT INTO "ytora"."sys_permission" VALUES (17, NULL, NULL, 'ytora', '2026-01-08 23:39:51.713134', NULL, NULL, '1', '15', 'SQL统计', '/monitor/sqlStat', 2, 'FlashOutline', NULL, 't', 3);
INSERT INTO "ytora"."sys_permission" VALUES (19, NULL, NULL, 'ytora', '2026-01-08 23:39:44.901388', NULL, NULL, '1', '15', '在线状态', '/monitor/onlineStat', 2, 'PeopleOutline', NULL, 't', 2);
INSERT INTO "ytora"."sys_permission" VALUES (18, NULL, NULL, 'ytora', '2026-01-08 23:39:58.603464', NULL, NULL, '1', '15', 'API调用统计', '/monitor/apiStat', 2, 'BarChartOutline', NULL, 't', 4);
INSERT INTO "ytora"."sys_permission" VALUES (16, NULL, NULL, 'ytora', '2026-01-08 23:38:24.56542', NULL, NULL, '1', '15', '健康状态', '/monitor/health', 2, 'PulseOutline', NULL, 't', 1);
INSERT INTO "ytora"."sys_permission" VALUES (62, NULL, NULL, NULL, NULL, NULL, '部门管理数据表格', '1', '5', '部门模块表格', 'depart-table', 4, NULL, '{"type": "table"}', 't', 0);
INSERT INTO "ytora"."sys_permission" VALUES (48, NULL, NULL, 'ytora', '2026-01-10 03:34:02.538678', NULL, '资源管理数据表格', '1', '4', '资源模块表格', 'permission-table', 4, NULL, '{"key": null, "attr": {}, "type": "table", "width": null, "height": null}', 't', 1);
INSERT INTO "ytora"."sys_permission" VALUES (89686066331649, 'ytora', '2026-01-12 20:08:20.723514', 'ytora', '2026-01-12 20:08:20.723514', 'YT-HR-RECR', NULL, NULL, '7', '字典项表格', 'sys_dict_item', 4, NULL, '{"key": null, "attr": {}, "type": "table", "width": null, "height": null}', 't', 1);
INSERT INTO "ytora"."sys_permission" VALUES (74312224735232, 'ytora', '2026-01-10 02:58:34.562521', 'ytora', '2026-01-13 21:04:33.633461', 'YT-HR-RECR', '匹配类型，等值，模糊，右模糊，左模糊，大于，小于，大于等于，小于等于，不等于，指定部门(这种情况下规则值只能是"A01,B03,C03"这样部门数组)，自定义', NULL, '74273410318336', '规则类型', 'data-permission-table::ruleType', 3, NULL, '{"key": "ruleType_dict", "attr": {"ellipsis": true}, "type": "table-col::normal", "width": 120, "height": 0}', 't', 3);
INSERT INTO "ytora"."sys_permission" VALUES (78495257067520, 'ytora', '2026-01-10 20:42:22.566866', 'ytora', '2026-01-10 22:39:49.648545', 'YT-HR-RECR', NULL, NULL, '74273410318336', '操作', 'data-permission-table::action', 3, NULL, '{"key": null, "attr": {"fixed": "right", "justify": "center"}, "type": "table-col::flex", "width": 140, "height": 0}', 't', 5);
INSERT INTO "ytora"."sys_permission" VALUES (78505298690048, 'ytora', '2026-01-10 20:44:55.79367', 'ytora', '2026-01-10 20:53:46.312444', 'YT-HR-RECR', NULL, NULL, '78495257067520', '编辑', 'data-permission-table::action::edit', 3, NULL, '{"key": null, "attr": {"size": "small", "type": "primary", "ghost": true}, "type": "table-col::button", "width": null, "height": 0}', 't', 1);
INSERT INTO "ytora"."sys_permission" VALUES (78509540638720, 'ytora', '2026-01-10 20:46:00.52065', 'ytora', '2026-01-10 20:53:51.454508', 'YT-HR-RECR', NULL, NULL, '78495257067520', '删除', 'data-permission-table::action::delete', 3, NULL, '{"key": null, "attr": {"size": "small", "type": "error", "ghost": true}, "type": "table-col::popconfirm", "width": null, "height": 0}', 't', 2);
INSERT INTO "ytora"."sys_permission" VALUES (82, NULL, NULL, NULL, NULL, NULL, NULL, '1', '45', '资源', 'role-table::action::permission', 3, NULL, '{"key": "action::delete", "attr": {"size": "small", "type": "primary", "ghost": true}, "type": "table-col::button"}', 't', 7);
INSERT INTO "ytora"."sys_permission" VALUES (24, NULL, NULL, NULL, NULL, NULL, '表格下面的列', '1', '23', '序号', 'user-table::index', 3, NULL, '{"key": "index", "attr": {"fixed": "left"}, "type": "table-col::index", "width": 70}', 't', 1);
INSERT INTO "ytora"."sys_permission" VALUES (74401624817664, 'ytora', '2026-01-10 03:21:18.697133', 'ytora', '2026-01-10 22:31:43.228441', 'YT-HR-RECR', NULL, NULL, '74273410318336', '规则字段', 'data-permission-table::ruleField', 3, NULL, '{"key": "ruleField", "attr": {"ellipsis": "true"}, "type": "table-col::normal", "width": 120, "height": 0}', 't', 2);
INSERT INTO "ytora"."sys_permission" VALUES (78931841581056, 'ytora', '2026-01-10 22:33:24.291143', 'ytora', '2026-01-10 22:34:02.276024', 'YT-HR-RECR', NULL, NULL, '74273410318336', '名称', 'data-permission-table::ruleName', 3, NULL, '{"key": "ruleName", "attr": {"ellipsis": "true"}, "type": "table-col::normal", "width": 120, "height": 0}', 't', 1);
INSERT INTO "ytora"."sys_permission" VALUES (89692227829760, 'ytora', '2026-01-12 20:09:54.785358', 'ytora', '2026-01-12 20:10:05.65216', 'YT-HR-RECR', NULL, NULL, '89686066331648', '字典名称', 'sys_dict::dictName', 3, NULL, '{"key": "dictName", "attr": {}, "type": "table-col::normal", "width": 140, "height": 0}', 't', 2);
INSERT INTO "ytora"."sys_permission" VALUES (67859872677888, 'ytora', '2026-01-08 23:37:39.464598', 'ytora', '2026-01-08 23:37:39.464598', 'YT-HR-RECR', NULL, NULL, '15', '本机状态', '/monitor/localhost', 2, 'BugOutline', NULL, 't', 0);
INSERT INTO "ytora"."sys_permission" VALUES (52, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '48', '资源类型', 'permission-table::permissionType', 3, NULL, '{"key": "permissionType", "attr": {"width": 120, "justify": "center"}, "type": "table-col::flex", "width": 120}', 't', 4);
INSERT INTO "ytora"."sys_permission" VALUES (63, NULL, NULL, 'ytora', '2026-01-05 22:34:22.806134', NULL, NULL, NULL, '62', '层级', 'depart-table::level', 3, NULL, '{"key": "levelKey", "attr": {"align": "left", "width": "70"}, "type": "table-col::normal", "width": 180, "height": 0}', 't', 1);
INSERT INTO "ytora"."sys_permission" VALUES (51, NULL, NULL, 'ytora', '2026-01-08 17:35:13.026353', NULL, NULL, NULL, '48', '权限编码', 'permission-table::permissionCode', 3, NULL, '{"key": "permissionCode", "attr": {"width": "180", "ellipsis": "true"}, "type": "table-col::normal", "width": 200, "height": null}', 't', 3);
INSERT INTO "ytora"."sys_permission" VALUES (89688258904065, 'ytora', '2026-01-12 20:08:54.224422', 'ytora', '2026-01-14 21:50:27.502431', 'YT-HR-RECR', NULL, NULL, '89686066331649', '排序', 'sys_dict_item::index', 3, NULL, '{"key": "index", "attr": {"ellipsis": true}, "type": "table-col::normal", "width": 70, "height": 0}', 't', 3);
INSERT INTO "ytora"."sys_permission" VALUES (89718307422209, 'ytora', '2026-01-12 20:16:32.721011', 'ytora', '2026-01-14 21:50:11.216293', 'YT-HR-RECR', NULL, NULL, '89686066331649', '字典值', 'sys_dict_item::dictItemValue', 3, NULL, '{"key": "dictItemValue", "attr": {"ellipsis": true}, "type": "table-col::normal", "width": 100, "height": 0}', 't', 2);
INSERT INTO "ytora"."sys_permission" VALUES (89718307422208, 'ytora', '2026-01-12 20:16:32.721011', 'ytora', '2026-01-12 20:16:32.721011', 'YT-HR-RECR', NULL, NULL, '89686066331648', '字典编码', 'sys_dict::dictCode', 3, NULL, '{"key": "dictCode", "attr": {}, "type": "table-col::normal", "width": 140, "height": null}', 't', 3);
INSERT INTO "ytora"."sys_permission" VALUES (23, NULL, NULL, 'ytora', '2026-01-15 10:27:14.785033', NULL, '用户页面的数据表格', '1', '2', 'user模块表格', 'user-table', 4, NULL, '{"key": null, "attr": {}, "type": "table", "width": null, "height": null}', 't', 2);
INSERT INTO "ytora"."sys_permission" VALUES (75, NULL, NULL, 'ytora', '2026-01-15 10:27:24.265042', NULL, NULL, NULL, '2', '用户与角色关联表格', 'user-role-table', 4, NULL, '{"key": null, "attr": {}, "type": "table", "width": null, "height": null}', 't', 3);
INSERT INTO "ytora"."sys_permission" VALUES (89734831210496, 'ytora', '2026-01-12 20:20:44.861688', 'ytora', '2026-01-12 20:21:11.27714', 'YT-HR-RECR', NULL, NULL, '89686066331648', '操作', 'sys_dict::action', 3, NULL, '{"key": "action", "attr": {"fixed": "right", "justify": "center"}, "type": "table-col::flex", "width": 180, "height": 0}', 't', 5);
INSERT INTO "ytora"."sys_permission" VALUES (89723277017088, 'ytora', '2026-01-12 20:17:48.55759', 'ytora', '2026-01-12 20:31:53.456229', 'YT-HR-RECR', NULL, NULL, '89686066331648', '备注', 'sys_dict::remark', 3, NULL, '{"key": "remark", "attr": {"ellipsis": true}, "type": "table-col::normal", "width": 200, "height": 0}', 't', 4);
INSERT INTO "ytora"."sys_permission" VALUES (30, NULL, NULL, NULL, NULL, NULL, 'status列下面的真正组件，是一个switch', '1', '23', '证件号', 'user-table::idCard', 3, NULL, '{"key": "idCard", "attr": {"width": 200}, "type": "table-col::normal", "width": 200}', 't', 7);
INSERT INTO "ytora"."sys_permission" VALUES (34, NULL, NULL, NULL, NULL, NULL, 'status列下面的真正组件，是一个switch', '1', '23', '操作', 'user-table::action', 3, NULL, '{"key": "action", "attr": {"fixed": "right", "width": 300, "justify": "center"}, "type": "table-col::flex", "width": 220}', 't', 11);
INSERT INTO "ytora"."sys_permission" VALUES (31, NULL, NULL, NULL, NULL, NULL, 'status列下面的真正组件，是一个switch', '1', '23', '状态', 'user-table::status', 3, NULL, '{"key": "status", "attr": {"width": 200, "justify": "center"}, "type": "table-col::flex", "width": 200}', 't', 8);
INSERT INTO "ytora"."sys_permission" VALUES (26, NULL, NULL, NULL, NULL, NULL, '表格下面的列', '1', '23', '真实姓名', 'user-table::realName', 3, NULL, '{"key": "realName", "attr": {"width": 200}, "type": "table-col::normal", "width": 200}', 't', 3);
INSERT INTO "ytora"."sys_permission" VALUES (32, NULL, NULL, NULL, NULL, NULL, 'status列下面的真正组件，是一个switch', '1', '31', '状态tag', 'user-table::status::tag', 3, NULL, '{"key": "status", "attr": {"type": "success", "mapper": {"1": "正常::success", "2": "禁用::error"}}, "type": "table-col::tag"}', 't', 9);
INSERT INTO "ytora"."sys_permission" VALUES (35, NULL, NULL, NULL, NULL, NULL, 'status列下面的真正组件，是一个switch', '1', '34', '角色', 'user-table::action::role', 3, NULL, '{"key": "action::role", "attr": {"size": "small", "type": "primary", "ghost": true}, "type": "table-col::button"}', 't', 1);
INSERT INTO "ytora"."sys_permission" VALUES (37, NULL, NULL, NULL, NULL, NULL, 'status列下面的真正组件，是一个switch', '1', '34', '删除', 'user-table::action::delete-popconfirm', 3, NULL, '{"key": "action::delete", "attr": {"size": "small", "type": "error", "ghost": true}, "type": "table-col::popconfirm"}', 't', 3);
INSERT INTO "ytora"."sys_permission" VALUES (27, NULL, NULL, NULL, NULL, NULL, '表格下面的列，该列比较特殊，是一个组件，下面还有子元素', '1', '23', '电话', 'user-table::phone', 3, NULL, '{"key": "phone", "attr": {"width": 200}, "type": "table-col::norma", "width": 200}', 't', 4);
INSERT INTO "ytora"."sys_permission" VALUES (33, NULL, NULL, NULL, NULL, NULL, 'status列下面的真正组件，是一个switch', '1', '23', '备注', 'user-table::remark', 3, NULL, '{"key": "remark", "attr": {"width": 300, "ellipsis": true}, "type": "table-col::normal", "width": 200}', 't', 10);
INSERT INTO "ytora"."sys_permission" VALUES (28, NULL, NULL, NULL, NULL, NULL, 'status列下面的真正组件，是一个switch', '1', '23', '邮箱', 'user-table::email', 3, NULL, '{"key": "email", "attr": {"width": 200}, "type": "table-col::normal", "width": 200}', 't', 5);
INSERT INTO "ytora"."sys_permission" VALUES (29, NULL, NULL, NULL, NULL, NULL, 'status列下面的真正组件，是一个switch', '1', '23', '生日', 'user-table::birthday', 3, NULL, '{"key": "birthday", "attr": {"width": 200}, "type": "table-col::normal", "width": 200}', 't', 6);
INSERT INTO "ytora"."sys_permission" VALUES (36, NULL, NULL, NULL, NULL, NULL, 'status列下面的真正组件，是一个switch', '1', '34', '编辑', 'user-table::action::edit', 3, NULL, '{"key": "action::edit", "attr": {"size": "small", "type": "success", "ghost": true}, "type": "table-col::button"}', 't', 2);
INSERT INTO "ytora"."sys_permission" VALUES (42, NULL, NULL, NULL, NULL, NULL, NULL, '1', '38', '状态', 'role-table::status', 3, NULL, '{"key": "status", "attr": {"width": 200, "justify": "center"}, "type": "table-col::flex", "width": 100}', 't', 4);
INSERT INTO "ytora"."sys_permission" VALUES (43, NULL, NULL, NULL, NULL, NULL, NULL, '1', '42', '状态tag', 'role-table::status::tag', 3, NULL, '{"key": "status", "attr": {"type": "success", "mapper": {"1": "正常::success", "2": "禁用::error"}}, "type": "table-col::tag"}', 't', 5);
INSERT INTO "ytora"."sys_permission" VALUES (61, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '54', '图标', 'permission-table::icon-item', 3, NULL, '{"key": "icon", "attr": {"align": "center", "width": 100}, "type": "table-col::icon"}', 't', 1);
INSERT INTO "ytora"."sys_permission" VALUES (45, NULL, NULL, NULL, NULL, NULL, NULL, '1', '38', '操作', 'role-table::action', 3, NULL, '{"key": "action", "attr": {"fixed": "right", "width": 300, "justify": "center"}, "type": "table-col::flex", "width": 220}', 't', 7);
INSERT INTO "ytora"."sys_permission" VALUES (40, NULL, NULL, NULL, NULL, NULL, NULL, '1', '38', '角色名称', 'role-table::roleName', 3, NULL, '{"key": "roleName", "type": "table-col::normal", "width": 180}', 't', 2);
INSERT INTO "ytora"."sys_permission" VALUES (41, NULL, NULL, NULL, NULL, NULL, NULL, '1', '38', '角色编码', 'role-table::roleCode', 3, NULL, '{"key": "roleCode", "type": "table-col::normal", "width": 180}', 't', 3);
INSERT INTO "ytora"."sys_permission" VALUES (44, NULL, NULL, NULL, NULL, NULL, NULL, '1', '38', '备注', 'role-table::remark', 3, NULL, '{"key": "remark", "attr": {"ellipsis": true}, "type": "table-col::normal", "width": 220}', 't', 6);
INSERT INTO "ytora"."sys_permission" VALUES (66, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '62', '部门类型', 'depart-table::type', 3, NULL, '{"key": "type", "attr": {"width": 120, "justify": "center"}, "type": "table-col::flex"}', 't', 4);
INSERT INTO "ytora"."sys_permission" VALUES (67, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '66', '类型标签', 'depart-table::type::tag', 3, NULL, '{"key": "type", "attr": {"mapper": {"1": "公司::info", "2": "部门::primary", "3": "小组::success"}}, "type": "table-col::tag"}', 't', 1);
INSERT INTO "ytora"."sys_permission" VALUES (39, NULL, NULL, NULL, NULL, NULL, NULL, '1', '38', '序号', 'role-table::index', 3, NULL, '{"key": "index", "attr": {"fixed": "left"}, "type": "table-col::index", "width": 70}', 't', 1);
INSERT INTO "ytora"."sys_permission" VALUES (89789647749121, 'ytora', '2026-01-12 20:34:41.2941', 'ytora', '2026-01-12 21:18:42.40932', 'YT-HR-RECR', '', '', '89734831210497', '编辑', 'sys_dict_item::action::edit', 3, NULL, '{"key": null, "attr": {"size": "small", "type": "success", "ghost": true}, "type": "table-col::button", "width": 0, "height": 0}', 't', 2);
INSERT INTO "ytora"."sys_permission" VALUES (89688258904064, 'ytora', '2026-01-12 20:08:54.224422', 'ytora', '2026-01-12 20:41:59.078804', 'YT-HR-RECR', NULL, NULL, '89686066331648', '序号', 'sys_dict::index', 3, NULL, '{"key": "index", "attr": {"fixed": "left"}, "type": "table-col::index", "width": 70, "height": 0}', 't', 0);
INSERT INTO "ytora"."sys_permission" VALUES (89967055077376, 'ytora', '2026-01-12 21:19:48.316629', 'ytora', '2026-01-12 21:19:48.316629', 'YT-HR-RECR', NULL, NULL, '89734831210496', '字典项', 'sys_dict::action::dictItem', 3, NULL, '{"key": null, "attr": {"size": "small", "type": "primary", "ghost": true}, "type": "table-col::button", "width": null, "height": null}', 't', 1);
INSERT INTO "ytora"."sys_permission" VALUES (47, NULL, NULL, NULL, NULL, NULL, 'status列下面的真正组件，是一个switch', '1', '45', '删除', 'role-table::action::delete-popconfirm', 3, NULL, '{"key": "action::delete", "attr": {"size": "small", "type": "error", "ghost": true}, "type": "table-col::popconfirm"}', 't', 9);
INSERT INTO "ytora"."sys_permission" VALUES (54, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '48', '图标', 'permission-table::icon', 3, NULL, '{"key": "icon", "attr": {"width": 100, "justify": "center"}, "type": "table-col::flex"}', 't', 5);
INSERT INTO "ytora"."sys_permission" VALUES (55, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '48', '可见', 'permission-table::visible', 3, NULL, '{"key": "visible", "attr": {"width": 100, "justify": "center"}, "type": "table-col::flex"}', 't', 6);
INSERT INTO "ytora"."sys_permission" VALUES (56, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '55', '可见标签', 'permission-table::visible::tag', 3, NULL, '{"key": "visible", "attr": {"mapper": {"true": "显示::success", "false": "隐藏::error"}}, "type": "table-col::tag"}', 't', 1);
INSERT INTO "ytora"."sys_permission" VALUES (68, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '62', '联系人', 'depart-table::contactUserName', 3, NULL, '{"key": "contactUserName", "attr": {"width": 150}, "type": "table-col::normal"}', 't', 5);
INSERT INTO "ytora"."sys_permission" VALUES (58, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '57', '添加下级', 'permission-table::action::add-sub', 3, NULL, '{"key": "action::add-sub", "attr": {"size": "small", "type": "primary", "ghost": true}, "type": "table-col::button"}', 't', 1);
INSERT INTO "ytora"."sys_permission" VALUES (59, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '57', '编辑', 'permission-table::action::edit', 3, NULL, '{"key": "action::edit", "attr": {"size": "small", "type": "success", "ghost": true}, "type": "table-col::button"}', 't', 2);
INSERT INTO "ytora"."sys_permission" VALUES (60, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '57', '删除', 'permission-table::action::delete-popconfirm', 3, NULL, '{"key": "action::delete", "attr": {"size": "small", "type": "error", "ghost": true}, "type": "table-col::popconfirm"}', 't', 3);
INSERT INTO "ytora"."sys_permission" VALUES (80, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '75', '拥有状态', 'user-role-table::owner', 3, NULL, '{"key": "owner", "attr": {"justify": "center"}, "type": "table-col::flex", "width": 80}', 't', 5);
INSERT INTO "ytora"."sys_permission" VALUES (69, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '62', '状态', 'depart-table::status', 3, NULL, '{"key": "status", "attr": {"width": 100, "justify": "center"}, "type": "table-col::flex"}', 't', 6);
INSERT INTO "ytora"."sys_permission" VALUES (70, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '69', '状态标签', 'depart-table::status::tag', 3, NULL, '{"key": "status", "attr": {"mapper": {"1": "正常::success", "2": "停用::error"}}, "type": "table-col::tag"}', 't', 1);
INSERT INTO "ytora"."sys_permission" VALUES (74, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '72', '删除', 'depart-table::action::delete-popconfirm', 3, NULL, '{"key": "action::delete", "attr": {"size": "small", "type": "error", "ghost": true}, "type": "table-col::popconfirm"}', 't', 2);
INSERT INTO "ytora"."sys_permission" VALUES (81, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '80', '拥有状态-switch', 'abc', 3, NULL, '{"key": "owner-switch", "type": "table-col::slot", "width": 80}', 't', 6);
INSERT INTO "ytora"."sys_permission" VALUES (79, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '75', '角色备注', 'user-role-table::remark', 3, NULL, '{"key": "remark", "attr": {"ellipsis": true}, "type": "table-col::normal", "width": 120}', 't', 4);
INSERT INTO "ytora"."sys_permission" VALUES (76, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '75', '序号', 'user-role-table::index', 3, NULL, '{"key": "index", "type": "table-col::index", "width": 40}', 't', 1);
INSERT INTO "ytora"."sys_permission" VALUES (77, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '75', '角色名称', 'user-role-table::roleName', 3, NULL, '{"key": "roleName", "attr": {"ellipsis": true}, "type": "table-col::normal", "width": 80}', 't', 2);
INSERT INTO "ytora"."sys_permission" VALUES (78, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '75', '角色编码', 'user-role-table::roleCode', 3, NULL, '{"key": "roleCode", "attr": {"ellipsis": true}, "type": "table-col::normal", "width": 120}', 't', 3);
INSERT INTO "ytora"."sys_permission" VALUES (83, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '72', '添加下级', 'depart-table::action::addSub', 3, NULL, '{"key": "action::edit", "attr": {"size": "small", "type": "primary", "ghost": true}, "type": "table-col::button"}', 't', 0);
INSERT INTO "ytora"."sys_permission" VALUES (73, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '72', '编辑', 'depart-table::action::edit', 3, NULL, '{"key": "action::edit", "attr": {"size": "small", "type": "success", "ghost": true}, "type": "table-col::button"}', 't', 1);
INSERT INTO "ytora"."sys_permission" VALUES (25, NULL, NULL, 'ytora', '2026-01-05 21:11:57.728554', NULL, '表格下面的列', '1', '23', '用户名', 'user-table::userName', 3, NULL, '{"key": "userName", "attr": {"333": "444", "test": "123"}, "type": "table-col::normal", "width": 180, "height": 0}', 't', 2);
INSERT INTO "ytora"."sys_permission" VALUES (72, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '62', '操作', 'depart-table::action', 3, NULL, '{"key": "action", "attr": {"fixed": "right", "justify": "center"}, "type": "table-col::flex", "width": 220}', 't', 8);
INSERT INTO "ytora"."sys_permission" VALUES (71, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '62', '备注', 'depart-table::remark', 3, NULL, '{"key": "remark", "attr": {"width": 250, "ellipsis": true}, "type": "table-col::normal", "width": 220}', 't', 7);
INSERT INTO "ytora"."sys_permission" VALUES (65, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '62', '部门编码', 'depart-table::departCode', 3, NULL, '{"key": "departCode", "attr": {"width": 150}, "type": "table-col::normal", "width": 160}', 't', 3);
INSERT INTO "ytora"."sys_permission" VALUES (64, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '62', '部门名称', 'depart-table::departName', 3, NULL, '{"key": "departName", "attr": {"width": 180}, "type": "table-col::normal", "width": 160}', 't', 2);
INSERT INTO "ytora"."sys_permission" VALUES (50, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '48', '权限名称', 'permission-table::permissionName', 3, NULL, '{"key": "permissionName", "attr": {"width": 150}, "type": "table-col::normal", "width": 180}', 't', 2);
INSERT INTO "ytora"."sys_permission" VALUES (49, NULL, NULL, 'ytora', '2026-01-08 17:54:26.408164', NULL, NULL, NULL, '48', '层级', 'permission-table::level', 3, NULL, '{"key": "levelKey", "attr": {"align": "left"}, "type": "table-col::normal", "width": 185, "height": 0}', 't', 1);
INSERT INTO "ytora"."sys_permission" VALUES (57, NULL, NULL, 'ytora', '2026-01-10 20:43:07.540849', NULL, NULL, NULL, '48', '操作', 'permission-table::action', 3, NULL, '{"key": "action", "attr": {"fixed": "right", "justify": "center"}, "type": "table-col::flex", "width": 220, "height": null}', 't', 7);
INSERT INTO "ytora"."sys_permission" VALUES (89795768156160, 'ytora', '2026-01-12 20:36:14.685328', 'ytora', '2026-01-12 21:18:38.581247', 'YT-HR-RECR', '', '', '89734831210496', '删除', 'sys_dict::action::delete-popconfirm', 3, NULL, '{"key": null, "attr": {"size": "small", "type": "error", "ghost": true}, "type": "table-col::popconfirm", "width": 0, "height": 0}', 't', 3);
INSERT INTO "ytora"."sys_permission" VALUES (89789647749120, 'ytora', '2026-01-12 20:34:41.2941', 'ytora', '2026-01-12 21:18:42.40932', 'YT-HR-RECR', '', '', '89734831210496', '编辑', 'sys_dict::action::edit', 3, NULL, '{"key": null, "attr": {"size": "small", "type": "success", "ghost": true}, "type": "table-col::button", "width": 0, "height": 0}', 't', 2);
INSERT INTO "ytora"."sys_permission" VALUES (89723277017089, 'ytora', '2026-01-12 20:17:48.55759', 'ytora', '2026-01-12 21:58:13.60633', 'YT-HR-RECR', NULL, NULL, '89686066331649', '备注', 'sys_dict_item::remark', 3, NULL, '{"key": "remark", "attr": {"ellipsis": true}, "type": "table-col::normal", "width": 150, "height": 0}', 't', 4);
INSERT INTO "ytora"."sys_permission" VALUES (74405249024000, 'ytora', '2026-01-10 03:22:14.000797', 'ytora', '2026-01-10 20:48:21.927221', 'YT-HR-RECR', '可以自定义，规则字段将使用指定规则类型去和规则值进行匹配，除此之外，还可以有以下特殊值
1.${userName}:当前用户
2.${departCode}:当前用户部门
3.${allDepartCodes}:用户所有部门的数组，格式："A1,B2..."
4.${currentDate}:当前日期
5.${currentTime}:当前时间', NULL, '74273410318336', '规则值', 'data-permission-table::ruleValue', 3, NULL, '{"key": "ruleValue", "attr": {"ellipsis": "true"}, "type": "table-col::normal", "width": 120, "height": 0}', 't', 4);
INSERT INTO "ytora"."sys_permission" VALUES (89734831210497, 'ytora', '2026-01-12 20:20:44.861688', 'ytora', '2026-01-12 21:58:19.857668', 'YT-HR-RECR', NULL, NULL, '89686066331649', '操作', 'sys_dict_item::action', 3, NULL, '{"key": "action", "attr": {"fixed": "right", "justify": "center"}, "type": "table-col::flex", "width": 150, "height": 0}', 't', 5);
INSERT INTO "ytora"."sys_permission" VALUES (89795768156161, 'ytora', '2026-01-12 20:36:14.685328', 'ytora', '2026-01-12 21:18:38.581247', 'YT-HR-RECR', '', '', '89734831210497', '删除', 'sys_dict_item::action::delete-popconfirm', 3, NULL, '{"key": null, "attr": {"size": "small", "type": "error", "ghost": true}, "type": "table-col::popconfirm", "width": 0, "height": 0}', 't', 3);
INSERT INTO "ytora"."sys_permission" VALUES (46, NULL, NULL, NULL, NULL, NULL, 'status列下面的真正组件，是一个switch', '1', '45', '编辑', 'role-table::action::edit', 3, NULL, '{"key": "action::edit", "attr": {"size": "small", "type": "success", "ghost": true}, "type": "table-col::button"}', 't', 8);
INSERT INTO "ytora"."sys_permission" VALUES (53, NULL, NULL, NULL, NULL, NULL, NULL, NULL, '52', '类型标签', 'permission-table::type::tag', 3, NULL, '{"key": "permissionType", "attr": {"mapper": {"1": "接口::default", "2": "页面::primary", "3": "元素::default", "4": "表格::success", "5": "表单::error"}}, "type": "table-col::tag"}', 't', 1);
INSERT INTO "ytora"."sys_permission" VALUES (100460061065216, 'ytora', '2026-01-14 17:48:18.881731', 'ytora', '2026-01-14 20:54:42.773449', 'YT-HR-RECR', NULL, NULL, '99995844542464', '电话', 'form-item::phone', 3, NULL, '{"key": "phone", "attr": {"placeholder": "电话"}, "type": "form-item::input", "width": null, "height": 0}', 't', 3);
INSERT INTO "ytora"."sys_permission" VALUES (100345884770304, 'ytora', '2026-01-14 17:19:16.68901', 'ytora', '2026-01-14 19:56:01.924571', 'YT-HR-RECR', NULL, NULL, '99995844542464', '用户名', 'form::userName', 3, NULL, '{"key": "userName", "attr": {"placeholder": "用户名"}, "type": "form-item::input", "width": 0, "height": 0}', 't', 1);
INSERT INTO "ytora"."sys_permission" VALUES (100347815264256, 'ytora', '2026-01-14 17:19:46.146065', 'ytora', '2026-01-14 19:56:11.711677', 'YT-HR-RECR', NULL, NULL, '99995844542464', '真实姓名', 'form::realName', 3, NULL, '{"key": "realName", "attr": {"placeholder": "真实姓名"}, "type": "form-item::input", "width": 0, "height": 0}', 't', 2);
INSERT INTO "ytora"."sys_permission" VALUES (100507141996544, 'ytora', '2026-01-14 18:00:17.279143', 'ytora', '2026-01-14 19:57:54.902457', 'YT-HR-RECR', NULL, NULL, '99995844542464', '状态', 'form-item::status', 3, NULL, '{"key": "status", "attr": {"placeholder": "状态"}, "type": "form-item::input", "width": 0, "height": 0}', 't', 4);
INSERT INTO "ytora"."sys_permission" VALUES (89692227829761, 'ytora', '2026-01-12 20:09:54.785358', 'ytora', '2026-01-14 21:48:27.393654', 'YT-HR-RECR', NULL, NULL, '89686066331649', '文本', 'sys_dict_item::dictItemText', 3, NULL, '{"key": "dictItemText", "attr": {"ellipsis": true}, "type": "table-col::normal", "width": 100, "height": 0}', 't', 1);
INSERT INTO "ytora"."sys_permission" VALUES (38, NULL, NULL, 'ytora', '2026-01-15 10:26:11.467386', NULL, '角色页面的数据表格', '1', '3', 'role模块表格', 'role-table', 4, NULL, '{"key": null, "attr": {}, "type": "table", "width": null, "height": null}', 't', 2);
INSERT INTO "ytora"."sys_permission" VALUES (99995844542464, 'ytora', '2026-01-14 15:50:15.49857', 'ytora', '2026-01-15 10:27:18.677314', 'YT-HR-RECR', NULL, NULL, '2', '查询条件', 'user-search', 5, NULL, NULL, 't', 1);
INSERT INTO "ytora"."sys_permission" VALUES (104386373746688, 'ytora', '2026-01-15 10:26:49.653107', 'ytora', '2026-01-15 10:27:38.077669', 'YT-HR-RECR', '', '', '3', '查询条件', 'role-search', 5, NULL, NULL, 't', 1);
INSERT INTO "ytora"."sys_permission" VALUES (104411370422272, 'ytora', '2026-01-15 10:33:11.075962', 'ytora', '2026-01-15 10:33:11.075962', 'YT-HR-RECR', '', '', '104386373746688', '角色名称', 'role-search::roleName', 3, NULL, '{"key": "roleName", "attr": {}, "type": "form-item::input", "width": null, "height": null}', 't', 1);
INSERT INTO "ytora"."sys_permission" VALUES (104413187342336, 'ytora', '2026-01-15 10:33:38.801132', 'ytora', '2026-01-15 10:33:38.801132', 'YT-HR-RECR', '', '', '104386373746688', '角色编码', 'role-search::roleCode', 3, NULL, '{"key": "roleCode", "attr": {}, "type": "form-item::input", "width": null, "height": null}', 't', 2);
INSERT INTO "ytora"."sys_permission" VALUES (107610315096064, 'ytora', '2026-01-16 00:06:43.092097', 'ytora', '2026-01-16 00:42:54.456397', 'YT-HR-RECR', '该页面实际没有表格，这里只是为了使用数据规则', '', '11', '目录树', 'folder-tree', 4, NULL, '{"key": "table", "attr": {}, "type": "table", "width": 0, "height": 0}', 't', 1);
INSERT INTO "ytora"."sys_permission" VALUES (124376883068928, 'ytora', '2026-01-18 23:10:40.558912', 'ytora', '2026-01-18 23:16:56.794654', 'YT-HR-RECR', '数据库管理新tab', NULL, '9', '数据库页面', '/src/views/sys/db/db.vue', 2, NULL, NULL, 'f', 1);

-- ----------------------------
-- Table structure for sys_recycle_bin
-- ----------------------------
DROP TABLE IF EXISTS "ytora"."sys_recycle_bin";
CREATE TABLE "ytora"."sys_recycle_bin" (
  "id" int8 NOT NULL,
  "deleted_by" varchar(255) COLLATE "pg_catalog"."default",
  "deleted_time" timestamp(6),
  "delete_reason" varchar(255) COLLATE "pg_catalog"."default",
  "original_table" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "original_id" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "original_data" jsonb NOT NULL,
  "restore_sql" text COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "ytora"."sys_recycle_bin"."id" IS '主键ID';
COMMENT ON COLUMN "ytora"."sys_recycle_bin"."deleted_by" IS '删除人';
COMMENT ON COLUMN "ytora"."sys_recycle_bin"."deleted_time" IS '删除时间';
COMMENT ON COLUMN "ytora"."sys_recycle_bin"."delete_reason" IS '删除原因';
COMMENT ON COLUMN "ytora"."sys_recycle_bin"."original_table" IS '原始表';
COMMENT ON COLUMN "ytora"."sys_recycle_bin"."original_id" IS '原始数据id';
COMMENT ON COLUMN "ytora"."sys_recycle_bin"."original_data" IS '原始数据，JSON';
COMMENT ON COLUMN "ytora"."sys_recycle_bin"."restore_sql" IS 'redo,还原SQL';
COMMENT ON TABLE "ytora"."sys_recycle_bin" IS '回收站';

-- ----------------------------
-- Records of sys_recycle_bin
-- ----------------------------
INSERT INTO "ytora"."sys_recycle_bin" VALUES (74396222750720, 'ytora', '2026-01-10 03:19:56.252355', '-', 'sys_permission', '74307169026048', '{"id": 74307169026048, "pid": "74273410318336", "icon": null, "meta": {"null": false, "type": "jsonb", "value": "{\"key\": \"ruleName\", \"attr\": {}, \"type\": \"table-col::normal\", \"width\": 160, \"height\": 0}"}, "index": 2, "remark": null, "status": null, "visible": true, "create_by": "ytora", "update_by": "ytora", "create_time": "2026-01-10", "depart_code": "YT-HR-RECR", "update_time": "2026-01-10", "permission_code": "data-permission-table::ruleField1", "permission_name": "规则字段", "permission_type": 5}', 'INSERT INTO sys_permission (id,create_by,create_time,update_by,update_time,depart_code,remark,status,pid,permission_name,permission_code,permission_type,icon,meta,visible,index) VALUES (74307169026048,''ytora'',''2026-01-10 02:57:17'',''ytora'',''2026-01-10 03:18:52'',''YT-HR-RECR'',NULL,NULL,''74273410318336'',''规则字段'',''data-permission-table::ruleField1'',5,NULL,''{"key": "ruleName", "attr": {}, "type": "table-col::normal", "width": 160, "height": 0}'',1,2);');
INSERT INTO "ytora"."sys_recycle_bin" VALUES (77586336251904, 'ytora', '2026-01-10 16:51:13.532219', '-', 'sys_role_permission', '32', '{"id": 32, "remark": null, "status": null, "role_id": 1, "create_by": null, "update_by": null, "create_time": null, "depart_code": null, "update_time": null, "permission_id": 32}', 'INSERT INTO sys_role_permission (id,create_by,create_time,update_by,update_time,depart_code,remark,status,role_id,permission_id) VALUES (32,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,32);');
INSERT INTO "ytora"."sys_recycle_bin" VALUES (77633497792512, 'ytora', '2026-01-10 17:03:13.155498', '-', 'sys_user_role', '44776643821568', '{"id": 44776643821568, "remark": null, "status": null, "role_id": 2, "user_id": 1, "create_by": "ytora", "update_by": "ytora", "create_time": "2026-01-04", "depart_code": "YT-HR-RECR", "update_time": "2026-01-04"}', 'INSERT INTO sys_user_role (id,create_by,create_time,update_by,update_time,depart_code,remark,status,user_id,role_id) VALUES (44776643821568,''ytora'',''2026-01-04 21:47:17'',''ytora'',''2026-01-04 21:47:17'',''YT-HR-RECR'',NULL,NULL,1,2);');
INSERT INTO "ytora"."sys_recycle_bin" VALUES (78637741703168, 'ytora', '2026-01-10 21:18:36.665075', '-', 'sys_permission', '74299826634752', '{"id": 74299826634752, "pid": "74273410318336", "icon": null, "meta": {"null": false, "type": "jsonb", "value": "{\"key\": \"index\", \"attr\": {\"fixed\": \"left\"}, \"type\": \"table-col::index\", \"width\": 60, \"height\": 0}"}, "index": 1, "remark": null, "status": null, "visible": true, "create_by": "ytora", "update_by": "ytora", "create_time": "2026-01-10", "depart_code": "YT-HR-RECR", "update_time": "2026-01-10", "permission_code": "data-permission-table::index", "permission_name": "序号", "permission_type": 5}', 'INSERT INTO sys_permission (id,create_by,create_time,update_by,update_time,depart_code,remark,status,pid,permission_name,permission_code,permission_type,icon,meta,visible,index) VALUES (74299826634752,''ytora'',''2026-01-10 02:55:25'',''ytora'',''2026-01-10 20:47:10'',''YT-HR-RECR'',NULL,NULL,''74273410318336'',''序号'',''data-permission-table::index'',5,NULL,''{"key": "index", "attr": {"fixed": "left"}, "type": "table-col::index", "width": 60, "height": 0}'',1,1);');
INSERT INTO "ytora"."sys_recycle_bin" VALUES (82628488790017, 'ytora', '2026-01-11 14:13:30.660022', '-', 'sys_role_data_rule', '1', '{"id": 1, "remark": null, "status": null, "role_id": 1, "rule_id": 1, "create_by": null, "update_by": null, "create_time": null, "depart_code": null, "update_time": null, "permission_id": 23}', 'INSERT INTO sys_role_data_rule (id,create_by,create_time,update_by,update_time,depart_code,remark,status,role_id,permission_id,rule_id) VALUES (1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,23,1);');
INSERT INTO "ytora"."sys_recycle_bin" VALUES (82628488790016, 'ytora', '2026-01-11 14:13:30.650026', '-', 'sys_role_data_rule', '1', '{"id": 1, "remark": null, "status": null, "role_id": 1, "rule_id": 1, "create_by": null, "update_by": null, "create_time": null, "depart_code": null, "update_time": null, "permission_id": 23}', 'INSERT INTO sys_role_data_rule (id,create_by,create_time,update_by,update_time,depart_code,remark,status,role_id,permission_id,rule_id) VALUES (1,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,23,1);');
INSERT INTO "ytora"."sys_recycle_bin" VALUES (82628491214848, 'ytora', '2026-01-11 14:13:30.660022', '-', 'sys_role_data_rule', '2', '{"id": 2, "remark": null, "status": null, "role_id": 1, "rule_id": 2, "create_by": null, "update_by": null, "create_time": null, "depart_code": null, "update_time": null, "permission_id": 23}', 'INSERT INTO sys_role_data_rule (id,create_by,create_time,update_by,update_time,depart_code,remark,status,role_id,permission_id,rule_id) VALUES (2,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,23,2);');
INSERT INTO "ytora"."sys_recycle_bin" VALUES (82655202443264, 'ytora', '2026-01-11 14:20:18.290536', '-', 'sys_role_data_rule', '82654582865920', '{"id": 82654582865920, "remark": null, "status": null, "role_id": 1, "rule_id": 1, "create_by": "ytora", "update_by": "ytora", "create_time": "2026-01-11", "depart_code": "YT-HR-RECR", "update_time": "2026-01-11", "permission_id": 23}', 'INSERT INTO sys_role_data_rule (id,create_by,create_time,update_by,update_time,depart_code,remark,status,role_id,permission_id,rule_id) VALUES (82654582865920,''ytora'',''2026-01-11 14:20:08'',''ytora'',''2026-01-11 14:20:08'',''YT-HR-RECR'',NULL,NULL,1,23,1);');
INSERT INTO "ytora"."sys_recycle_bin" VALUES (82655203229696, 'ytora', '2026-01-11 14:20:18.290536', '-', 'sys_role_data_rule', '82655008784384', '{"id": 82655008784384, "remark": null, "status": null, "role_id": 1, "rule_id": 2, "create_by": "ytora", "update_by": "ytora", "create_time": "2026-01-11", "depart_code": "YT-HR-RECR", "update_time": "2026-01-11", "permission_id": 23}', 'INSERT INTO sys_role_data_rule (id,create_by,create_time,update_by,update_time,depart_code,remark,status,role_id,permission_id,rule_id) VALUES (82655008784384,''ytora'',''2026-01-11 14:20:15'',''ytora'',''2026-01-11 14:20:15'',''YT-HR-RECR'',NULL,NULL,1,23,2);');
INSERT INTO "ytora"."sys_recycle_bin" VALUES (83838743937024, 'ytora', '2026-01-11 19:21:17.708957', '-', 'sys_role', '10', '{"id": 10, "remark": "只读视图权限", "status": "1", "create_by": null, "role_code": "ROLE_GUEST", "role_name": "外部顾问", "update_by": null, "create_time": "2025-01-01", "depart_code": null, "update_time": null}', 'INSERT INTO sys_role (id,create_by,create_time,update_by,update_time,depart_code,remark,status,role_name,role_code) VALUES (10,NULL,''2025-01-01 09:00:00'',NULL,NULL,NULL,''只读视图权限'',''1'',''外部顾问'',''ROLE_GUEST'');');
INSERT INTO "ytora"."sys_recycle_bin" VALUES (83838929534976, 'ytora', '2026-01-11 19:21:20.538178', '-', 'sys_role', '8', '{"id": 8, "remark": "CRM系统管理权限", "status": "1", "create_by": null, "role_code": "ROLE_SALES_MANAGER", "role_name": "销售主管", "update_by": null, "create_time": "2025-01-01", "depart_code": null, "update_time": null}', 'INSERT INTO sys_role (id,create_by,create_time,update_by,update_time,depart_code,remark,status,role_name,role_code) VALUES (8,NULL,''2025-01-01 09:00:00'',NULL,NULL,NULL,''CRM系统管理权限'',''1'',''销售主管'',''ROLE_SALES_MANAGER'');');
INSERT INTO "ytora"."sys_recycle_bin" VALUES (83839018926080, 'ytora', '2026-01-11 19:21:21.904308', '-', 'sys_role', '3', '{"id": 3, "remark": "员工入离职管理权限", "status": "1", "create_by": null, "role_code": "ROLE_HR_SPECIALIST", "role_name": "人事专员", "update_by": null, "create_time": "2025-01-01", "depart_code": null, "update_time": null}', 'INSERT INTO sys_role (id,create_by,create_time,update_by,update_time,depart_code,remark,status,role_name,role_code) VALUES (3,NULL,''2025-01-01 09:00:00'',NULL,NULL,NULL,''员工入离职管理权限'',''1'',''人事专员'',''ROLE_HR_SPECIALIST'');');
INSERT INTO "ytora"."sys_recycle_bin" VALUES (83839098421248, 'ytora', '2026-01-11 19:21:23.116578', '-', 'sys_role', '4', '{"id": 4, "remark": "财务报表查看与审计权", "status": "1", "create_by": null, "role_code": "ROLE_FINANCE_AUDIT", "role_name": "财务审计", "update_by": null, "create_time": "2025-01-01", "depart_code": null, "update_time": null}', 'INSERT INTO sys_role (id,create_by,create_time,update_by,update_time,depart_code,remark,status,role_name,role_code) VALUES (4,NULL,''2025-01-01 09:00:00'',NULL,NULL,NULL,''财务报表查看与审计权'',''1'',''财务审计'',''ROLE_FINANCE_AUDIT'');');
INSERT INTO "ytora"."sys_recycle_bin" VALUES (83839209570304, 'ytora', '2026-01-11 19:21:24.811779', '-', 'sys_role', '5', '{"id": 5, "remark": "研发系统操作权限", "status": "1", "create_by": null, "role_code": "ROLE_DEV_ENGINEER", "role_name": "开发工程师", "update_by": null, "create_time": "2025-01-01", "depart_code": null, "update_time": null}', 'INSERT INTO sys_role (id,create_by,create_time,update_by,update_time,depart_code,remark,status,role_name,role_code) VALUES (5,NULL,''2025-01-01 09:00:00'',NULL,NULL,NULL,''研发系统操作权限'',''1'',''开发工程师'',''ROLE_DEV_ENGINEER'');');
INSERT INTO "ytora"."sys_recycle_bin" VALUES (83839384354816, 'ytora', '2026-01-11 19:21:27.480212', '-', 'sys_role', '6', '{"id": 6, "remark": "缺陷管理系统权限", "status": "1", "create_by": null, "role_code": "ROLE_QA_ENGINEER", "role_name": "测试工程师", "update_by": null, "create_time": "2025-01-01", "depart_code": null, "update_time": null}', 'INSERT INTO sys_role (id,create_by,create_time,update_by,update_time,depart_code,remark,status,role_name,role_code) VALUES (6,NULL,''2025-01-01 09:00:00'',NULL,NULL,NULL,''缺陷管理系统权限'',''1'',''测试工程师'',''ROLE_QA_ENGINEER'');');
INSERT INTO "ytora"."sys_recycle_bin" VALUES (83839482200064, 'ytora', '2026-01-11 19:21:28.973056', '-', 'sys_role', '7', '{"id": 7, "remark": "服务器监控与部署权限", "status": "1", "create_by": null, "role_code": "ROLE_OPS_ENGINEER", "role_name": "运维工程师", "update_by": null, "create_time": "2025-01-01", "depart_code": null, "update_time": null}', 'INSERT INTO sys_role (id,create_by,create_time,update_by,update_time,depart_code,remark,status,role_name,role_code) VALUES (7,NULL,''2025-01-01 09:00:00'',NULL,NULL,NULL,''服务器监控与部署权限'',''1'',''运维工程师'',''ROLE_OPS_ENGINEER'');');
INSERT INTO "ytora"."sys_recycle_bin" VALUES (83839567855616, 'ytora', '2026-01-11 19:21:30.280522', '-', 'sys_role', '9', '{"id": 9, "remark": "基础办公系统权限", "status": "1", "create_by": null, "role_code": "ROLE_COMMON_USER", "role_name": "普通员工", "update_by": null, "create_time": "2025-01-01", "depart_code": null, "update_time": null}', 'INSERT INTO sys_role (id,create_by,create_time,update_by,update_time,depart_code,remark,status,role_name,role_code) VALUES (9,NULL,''2025-01-01 09:00:00'',NULL,NULL,NULL,''基础办公系统权限'',''1'',''普通员工'',''ROLE_COMMON_USER'');');
INSERT INTO "ytora"."sys_recycle_bin" VALUES (83843989569536, 'ytora', '2026-01-11 19:22:37.749846', '-', 'sys_role', '2', '{"id": 2, "remark": "部门数据查看与审批权限", "status": "2", "create_by": null, "role_code": "ROLE_DEPT_LEADER", "role_name": "部门负责人", "update_by": "ytora", "create_time": "2025-01-01", "depart_code": null, "update_time": "2025-12-30"}', 'INSERT INTO sys_role (id,create_by,create_time,update_by,update_time,depart_code,remark,status,role_name,role_code) VALUES (2,NULL,''2025-01-01 09:00:00'',''ytora'',''2025-12-30 22:05:48'',NULL,''部门数据查看与审批权限'',''2'',''部门负责人'',''ROLE_DEPT_LEADER'');');
INSERT INTO "ytora"."sys_recycle_bin" VALUES (83844389535744, 'ytora', '2026-01-11 19:22:43.853066', '-', 'sys_user', '4', '{"id": 4, "email": "he.yang@ytora.com", "phone": "13811110011", "avatar": "/avatar.jpg", "remark": "这是一个一个一个备注啊啊啊啊啊啊啊", "status": "2", "id_card": "114514", "birthday": "2025-12-22", "password": "1", "create_by": null, "real_name": "何洋", "update_by": "ytora", "user_name": "he.yang", "create_time": "2025-01-05", "depart_code": "YT-IT-OPS", "update_time": "2026-01-03"}', 'INSERT INTO sys_user (id,create_by,create_time,update_by,update_time,depart_code,remark,status,user_name,real_name,password,avatar,phone,email,birthday,id_card) VALUES (4,NULL,''2025-01-05 10:00:00'',''ytora'',''2026-01-03 01:54:20'',''YT-IT-OPS'',''这是一个一个一个备注啊啊啊啊啊啊啊'',''2'',''he.yang'',''何洋'',''1'',''/avatar.jpg'',''13811110011'',''he.yang@ytora.com'',''2025-12-22 00:00:00'',''114514'');');
INSERT INTO "ytora"."sys_recycle_bin" VALUES (83844514381824, 'ytora', '2026-01-11 19:22:45.758371', '-', 'sys_user', '2', '{"id": 2, "email": "admin@ytora.com", "phone": "13811110001", "avatar": "/avatar.jpg", "remark": "这是一个一个一个备注啊啊啊啊啊啊啊", "status": "1", "id_card": "114514", "birthday": "2025-12-02", "password": "1", "create_by": null, "real_name": "孙尧", "update_by": "ytora", "user_name": "sun.yao", "create_time": "2025-01-01", "depart_code": "YT-001", "update_time": "2026-01-03"}', 'INSERT INTO sys_user (id,create_by,create_time,update_by,update_time,depart_code,remark,status,user_name,real_name,password,avatar,phone,email,birthday,id_card) VALUES (2,NULL,''2025-01-01 10:00:00'',''ytora'',''2026-01-03 01:56:44'',''YT-001'',''这是一个一个一个备注啊啊啊啊啊啊啊'',''1'',''sun.yao'',''孙尧'',''1'',''/avatar.jpg'',''13811110001'',''admin@ytora.com'',''2025-12-02 00:00:00'',''114514'');');
INSERT INTO "ytora"."sys_recycle_bin" VALUES (83844713742336, 'ytora', '2026-01-11 19:22:48.799441', '-', 'sys_user', '6', '{"id": 6, "email": "li.miao@ytora.com", "phone": "13811110002", "avatar": "/avatar.jpg", "remark": "这是一个一个一个备注啊啊啊啊啊啊啊", "status": "1", "id_card": "114514", "birthday": "2025-12-22", "password": "1", "create_by": null, "real_name": "李苗", "update_by": null, "user_name": "li.miao", "create_time": "2025-01-02", "depart_code": "YT-RD-01", "update_time": null}', 'INSERT INTO sys_user (id,create_by,create_time,update_by,update_time,depart_code,remark,status,user_name,real_name,password,avatar,phone,email,birthday,id_card) VALUES (6,NULL,''2025-01-02 10:00:00'',NULL,NULL,''YT-RD-01'',''这是一个一个一个备注啊啊啊啊啊啊啊'',''1'',''li.miao'',''李苗'',''1'',''/avatar.jpg'',''13811110002'',''li.miao@ytora.com'',''2025-12-22 00:00:00'',''114514'');');
INSERT INTO "ytora"."sys_recycle_bin" VALUES (83844786749440, 'ytora', '2026-01-11 19:22:49.913566', '-', 'sys_user', '7', '{"id": 7, "email": "wang.qiang@ytora.com", "phone": "13811110003", "avatar": "/avatar.jpg", "remark": "这是一个一个一个备注啊啊啊啊啊啊啊", "status": "1", "id_card": "114514", "birthday": "2025-12-22", "password": "1", "create_by": null, "real_name": "王强", "update_by": null, "user_name": "wang.qiang", "create_time": "2025-01-02", "depart_code": "YT-FI-01", "update_time": null}', 'INSERT INTO sys_user (id,create_by,create_time,update_by,update_time,depart_code,remark,status,user_name,real_name,password,avatar,phone,email,birthday,id_card) VALUES (7,NULL,''2025-01-02 11:00:00'',NULL,NULL,''YT-FI-01'',''这是一个一个一个备注啊啊啊啊啊啊啊'',''1'',''wang.qiang'',''王强'',''1'',''/avatar.jpg'',''13811110003'',''wang.qiang@ytora.com'',''2025-12-22 00:00:00'',''114514'');');
INSERT INTO "ytora"."sys_recycle_bin" VALUES (83844861591552, 'ytora', '2026-01-11 19:22:51.056218', '-', 'sys_user', '8', '{"id": 8, "email": "chen.xi@ytora.com", "phone": "13811110004", "avatar": "/avatar.jpg", "remark": "这是一个一个一个备注啊啊啊啊啊啊啊", "status": "1", "id_card": "114514", "birthday": "2025-12-22", "password": "1", "create_by": null, "real_name": "陈曦", "update_by": null, "user_name": "chen.xi", "create_time": "2025-01-02", "depart_code": "YT-HR-01", "update_time": null}', 'INSERT INTO sys_user (id,create_by,create_time,update_by,update_time,depart_code,remark,status,user_name,real_name,password,avatar,phone,email,birthday,id_card) VALUES (8,NULL,''2025-01-02 12:00:00'',NULL,NULL,''YT-HR-01'',''这是一个一个一个备注啊啊啊啊啊啊啊'',''1'',''chen.xi'',''陈曦'',''1'',''/avatar.jpg'',''13811110004'',''chen.xi@ytora.com'',''2025-12-22 00:00:00'',''114514'');');
INSERT INTO "ytora"."sys_recycle_bin" VALUES (83845043257344, 'ytora', '2026-01-11 19:22:53.828097', '-', 'sys_user', '9', '{"id": 9, "email": "zhang.wei@ytora.com", "phone": "13811110005", "avatar": "/avatar.jpg", "remark": "这是一个一个一个备注啊啊啊啊啊啊啊", "status": "1", "id_card": "114514", "birthday": "2025-12-22", "password": "1", "create_by": null, "real_name": "张伟", "update_by": null, "user_name": "zhang.wei", "create_time": "2025-01-03", "depart_code": "YT-RD-SOFT", "update_time": null}', 'INSERT INTO sys_user (id,create_by,create_time,update_by,update_time,depart_code,remark,status,user_name,real_name,password,avatar,phone,email,birthday,id_card) VALUES (9,NULL,''2025-01-03 09:00:00'',NULL,NULL,''YT-RD-SOFT'',''这是一个一个一个备注啊啊啊啊啊啊啊'',''1'',''zhang.wei'',''张伟'',''1'',''/avatar.jpg'',''13811110005'',''zhang.wei@ytora.com'',''2025-12-22 00:00:00'',''114514'');');
INSERT INTO "ytora"."sys_recycle_bin" VALUES (83845167185920, 'ytora', '2026-01-11 19:22:55.71924', '-', 'sys_user', '10', '{"id": 10, "email": "zhou.jie@ytora.com", "phone": "13811110006", "avatar": "/avatar.jpg", "remark": "这是一个一个一个备注啊啊啊啊啊啊啊", "status": "1", "id_card": "114514", "birthday": "2025-12-22", "password": "1", "create_by": null, "real_name": "周杰", "update_by": null, "user_name": "zhou.jie", "create_time": "2025-01-03", "depart_code": "YT-RD-SOFT", "update_time": null}', 'INSERT INTO sys_user (id,create_by,create_time,update_by,update_time,depart_code,remark,status,user_name,real_name,password,avatar,phone,email,birthday,id_card) VALUES (10,NULL,''2025-01-03 10:00:00'',NULL,NULL,''YT-RD-SOFT'',''这是一个一个一个备注啊啊啊啊啊啊啊'',''1'',''zhou.jie'',''周杰'',''1'',''/avatar.jpg'',''13811110006'',''zhou.jie@ytora.com'',''2025-12-22 00:00:00'',''114514'');');
INSERT INTO "ytora"."sys_recycle_bin" VALUES (83845341511680, 'ytora', '2026-01-11 19:22:58.379523', '-', 'sys_user', '3', '{"id": 3, "email": "gao.yuan@ytora.com", "phone": "13811110010", "avatar": "/avatar.jpg", "remark": "这是一个一个一个备注啊啊啊啊啊啊啊", "status": "2", "id_card": "114514", "birthday": "2025-12-22", "password": "1", "create_by": null, "real_name": "高原", "update_by": null, "user_name": "gao.yuan", "create_time": "2025-01-05", "depart_code": "YT-SA-EAST", "update_time": null}', 'INSERT INTO sys_user (id,create_by,create_time,update_by,update_time,depart_code,remark,status,user_name,real_name,password,avatar,phone,email,birthday,id_card) VALUES (3,NULL,''2025-01-05 09:00:00'',NULL,NULL,''YT-SA-EAST'',''这是一个一个一个备注啊啊啊啊啊啊啊'',''2'',''gao.yuan'',''高原'',''1'',''/avatar.jpg'',''13811110010'',''gao.yuan@ytora.com'',''2025-12-22 00:00:00'',''114514'');');
INSERT INTO "ytora"."sys_recycle_bin" VALUES (83845451350016, 'ytora', '2026-01-11 19:23:00.055112', '-', 'sys_user', '5', '{"id": 5, "email": "guest@ext.com", "phone": "13811110012", "avatar": "/avatar.jpg", "remark": "这是一个一个一个备注啊啊啊啊啊啊啊", "status": "2", "id_card": "114514", "birthday": "2025-12-22", "password": "1", "create_by": null, "real_name": "顾文", "update_by": null, "user_name": "external.v", "create_time": "2025-01-06", "depart_code": "YT-001", "update_time": null}', 'INSERT INTO sys_user (id,create_by,create_time,update_by,update_time,depart_code,remark,status,user_name,real_name,password,avatar,phone,email,birthday,id_card) VALUES (5,NULL,''2025-01-06 09:00:00'',NULL,NULL,''YT-001'',''这是一个一个一个备注啊啊啊啊啊啊啊'',''2'',''external.v'',''顾文'',''1'',''/avatar.jpg'',''13811110012'',''guest@ext.com'',''2025-12-22 00:00:00'',''114514'');');
INSERT INTO "ytora"."sys_recycle_bin" VALUES (83878169739264, 'ytora', '2026-01-11 19:31:19.28562', '-', 'sys_user', '11', '{"id": 11, "email": "lin.yv@ytora.com", "phone": "13811110007", "avatar": "/avatar.jpg", "remark": "这是一个一个一个备注啊啊啊啊啊啊啊", "status": "2", "id_card": "114514", "birthday": "2025-12-22", "password": "1", "create_by": null, "real_name": "林语", "update_by": null, "user_name": "lin.yv", "create_time": "2025-01-03", "depart_code": "YT-RD-TEST", "update_time": null}', 'INSERT INTO sys_user (id,create_by,create_time,update_by,update_time,depart_code,remark,status,user_name,real_name,password,avatar,phone,email,birthday,id_card) VALUES (11,NULL,''2025-01-03 11:00:00'',NULL,NULL,''YT-RD-TEST'',''这是一个一个一个备注啊啊啊啊啊啊啊'',''2'',''lin.yv'',''林语'',''1'',''/avatar.jpg'',''13811110007'',''lin.yv@ytora.com'',''2025-12-22 00:00:00'',''114514'');');
INSERT INTO "ytora"."sys_recycle_bin" VALUES (90056023015424, 'ytora', '2026-01-12 21:42:25.610733', '-', 'sys_permission', '89967055077377', '{"id": 89967055077377, "pid": "89734831210497", "icon": null, "meta": {"null": false, "type": "jsonb", "value": "{\"key\": null, \"attr\": {\"size\": \"small\", \"type\": \"primary\", \"ghost\": true}, \"type\": \"table-col::button\", \"width\": null, \"height\": null}"}, "index": 1, "remark": null, "status": null, "visible": true, "create_by": "ytora", "update_by": "ytora", "create_time": "2026-01-12", "depart_code": "YT-HR-RECR", "update_time": "2026-01-12", "permission_code": "sys_dict_item::action::dictItem", "permission_name": "字典项", "permission_type": 5}', 'INSERT INTO sys_permission (id,create_by,create_time,update_by,update_time,depart_code,remark,status,pid,permission_name,permission_code,permission_type,icon,meta,visible,index) VALUES (89967055077377,''ytora'',''2026-01-12 21:19:48'',''ytora'',''2026-01-12 21:19:48'',''YT-HR-RECR'',NULL,NULL,''89734831210497'',''字典项'',''sys_dict_item::action::dictItem'',5,NULL,''{"key": null, "attr": {"size": "small", "type": "primary", "ghost": true}, "type": "table-col::button", "width": null, "height": null}'',1,1);');
INSERT INTO "ytora"."sys_recycle_bin" VALUES (95463520206848, 'ytora', '2026-01-13 20:37:37.704254', '-', 'sys_role_data_rule', '83836073934848', '{"id": 83836073934848, "remark": null, "status": null, "role_id": 1, "rule_id": 83797329379328, "create_by": "ytora", "update_by": "ytora", "create_time": "2026-01-11", "depart_code": "YT-HR-RECR", "update_time": "2026-01-11", "permission_id": 23}', 'INSERT INTO sys_role_data_rule (id,create_by,create_time,update_by,update_time,depart_code,remark,status,role_id,permission_id,rule_id) VALUES (83836073934848,''ytora'',''2026-01-11 19:20:36'',''ytora'',''2026-01-11 19:20:36'',''YT-HR-RECR'',NULL,NULL,1,23,83797329379328);');
INSERT INTO "ytora"."sys_recycle_bin" VALUES (95463521320960, 'ytora', '2026-01-13 20:37:37.704254', '-', 'sys_role_data_rule', '83836073934849', '{"id": 83836073934849, "remark": null, "status": null, "role_id": 1, "rule_id": 83800502239232, "create_by": "ytora", "update_by": "ytora", "create_time": "2026-01-11", "depart_code": "YT-HR-RECR", "update_time": "2026-01-11", "permission_id": 23}', 'INSERT INTO sys_role_data_rule (id,create_by,create_time,update_by,update_time,depart_code,remark,status,role_id,permission_id,rule_id) VALUES (83836073934849,''ytora'',''2026-01-11 19:20:36'',''ytora'',''2026-01-11 19:20:36'',''YT-HR-RECR'',NULL,NULL,1,23,83800502239232);');
INSERT INTO "ytora"."sys_recycle_bin" VALUES (95463521386496, 'ytora', '2026-01-13 20:37:37.704254', '-', 'sys_role_data_rule', '83836073934850', '{"id": 83836073934850, "remark": null, "status": null, "role_id": 1, "rule_id": 83810916302848, "create_by": "ytora", "update_by": "ytora", "create_time": "2026-01-11", "depart_code": "YT-HR-RECR", "update_time": "2026-01-11", "permission_id": 23}', 'INSERT INTO sys_role_data_rule (id,create_by,create_time,update_by,update_time,depart_code,remark,status,role_id,permission_id,rule_id) VALUES (83836073934850,''ytora'',''2026-01-11 19:20:36'',''ytora'',''2026-01-11 19:20:36'',''YT-HR-RECR'',NULL,NULL,1,23,83810916302848);');
INSERT INTO "ytora"."sys_recycle_bin" VALUES (95463521386497, 'ytora', '2026-01-13 20:37:37.704254', '-', 'sys_role_data_rule', '83836073934851', '{"id": 83836073934851, "remark": null, "status": null, "role_id": 1, "rule_id": 83805188390912, "create_by": "ytora", "update_by": "ytora", "create_time": "2026-01-11", "depart_code": "YT-HR-RECR", "update_time": "2026-01-11", "permission_id": 23}', 'INSERT INTO sys_role_data_rule (id,create_by,create_time,update_by,update_time,depart_code,remark,status,role_id,permission_id,rule_id) VALUES (83836073934851,''ytora'',''2026-01-11 19:20:36'',''ytora'',''2026-01-11 19:20:36'',''YT-HR-RECR'',NULL,NULL,1,23,83805188390912);');
INSERT INTO "ytora"."sys_recycle_bin" VALUES (95463521386498, 'ytora', '2026-01-13 20:37:37.704254', '-', 'sys_role_data_rule', '83836073934853', '{"id": 83836073934853, "remark": null, "status": null, "role_id": 1, "rule_id": 83802886635520, "create_by": "ytora", "update_by": "ytora", "create_time": "2026-01-11", "depart_code": "YT-HR-RECR", "update_time": "2026-01-11", "permission_id": 23}', 'INSERT INTO sys_role_data_rule (id,create_by,create_time,update_by,update_time,depart_code,remark,status,role_id,permission_id,rule_id) VALUES (83836073934853,''ytora'',''2026-01-11 19:20:36'',''ytora'',''2026-01-11 19:20:36'',''YT-HR-RECR'',NULL,NULL,1,23,83802886635520);');
INSERT INTO "ytora"."sys_recycle_bin" VALUES (100322351316992, 'ytora', '2026-01-14 17:13:17.591563', '-', 'sys_permission', '100070845120512', '{"id": 100070845120512, "pid": "99995844542464", "icon": null, "meta": null, "index": 1, "remark": null, "status": null, "visible": true, "create_by": "ytora", "update_by": "ytora", "create_time": "2026-01-14", "depart_code": "YT-HR-RECR", "update_time": "2026-01-14", "permission_code": "userName", "permission_name": "用户名", "permission_type": 3}', 'INSERT INTO sys_permission (id,create_by,create_time,update_by,update_time,depart_code,remark,status,pid,permission_name,permission_code,permission_type,icon,meta,visible,index) VALUES (100070845120512,''ytora'',''2026-01-14 16:09:19'',''ytora'',''2026-01-14 16:09:19'',''YT-HR-RECR'',NULL,NULL,''99995844542464'',''用户名'',''userName'',3,NULL,NULL,1,1);');
INSERT INTO "ytora"."sys_recycle_bin" VALUES (100322435727360, 'ytora', '2026-01-14 17:13:18.884719', '-', 'sys_permission', '100072333770752', '{"id": 100072333770752, "pid": "99995844542464", "icon": null, "meta": null, "index": 2, "remark": null, "status": null, "visible": true, "create_by": "ytora", "update_by": "ytora", "create_time": "2026-01-14", "depart_code": "YT-HR-RECR", "update_time": "2026-01-14", "permission_code": "realName", "permission_name": "真实姓名·", "permission_type": 3}', 'INSERT INTO sys_permission (id,create_by,create_time,update_by,update_time,depart_code,remark,status,pid,permission_name,permission_code,permission_type,icon,meta,visible,index) VALUES (100072333770752,''ytora'',''2026-01-14 16:09:42'',''ytora'',''2026-01-14 16:09:42'',''YT-HR-RECR'',NULL,NULL,''99995844542464'',''真实姓名·'',''realName'',3,NULL,NULL,1,2);');
INSERT INTO "ytora"."sys_recycle_bin" VALUES (100969782968320, 'ytora', '2026-01-14 19:57:56.558194', '-', 'sys_permission', '100529256923136', '{"id": 100529256923136, "pid": "99995844542464", "icon": null, "meta": {"null": false, "type": "jsonb", "value": "{\"key\": \"birthday\", \"attr\": {}, \"type\": \"form-item::date\", \"width\": null, \"height\": null}"}, "index": 5, "remark": null, "status": null, "visible": true, "create_by": "ytora", "update_by": "ytora", "create_time": "2026-01-14", "depart_code": "YT-HR-RECR", "update_time": "2026-01-14", "permission_code": "birthdaybirthday", "permission_name": "生日", "permission_type": 3}', 'INSERT INTO sys_permission (id,create_by,create_time,update_by,update_time,depart_code,remark,status,pid,permission_name,permission_code,permission_type,icon,meta,visible,index) VALUES (100529256923136,''ytora'',''2026-01-14 18:05:54'',''ytora'',''2026-01-14 18:05:54'',''YT-HR-RECR'',NULL,NULL,''99995844542464'',''生日'',''birthdaybirthday'',3,NULL,''{"key": "birthday", "attr": {}, "type": "form-item::date", "width": null, "height": null}'',1,5);');
INSERT INTO "ytora"."sys_recycle_bin" VALUES (101189727354880, 'ytora', '2026-01-14 20:53:52.662598', '-', 'sys_permission', '101102938357760', '{"id": 101102938357760, "pid": "99995844542464", "icon": null, "meta": {"null": false, "type": "jsonb", "value": "{\"key\": null, \"attr\": {}, \"type\": \"form-item::slot\", \"width\": 0, \"height\": 0}"}, "index": 5, "remark": null, "status": null, "visible": true, "create_by": "ytora", "update_by": "ytora", "create_time": "2026-01-14", "depart_code": "YT-HR-RECR", "update_time": "2026-01-14", "permission_code": "slotTest", "permission_name": "", "permission_type": 3}', 'INSERT INTO sys_permission (id,create_by,create_time,update_by,update_time,depart_code,remark,status,pid,permission_name,permission_code,permission_type,icon,meta,visible,index) VALUES (101102938357760,''ytora'',''2026-01-14 20:31:48'',''ytora'',''2026-01-14 20:42:45'',''YT-HR-RECR'',NULL,NULL,''99995844542464'','''',''slotTest'',3,NULL,''{"key": null, "attr": {}, "type": "form-item::slot", "width": 0, "height": 0}'',1,5);');
INSERT INTO "ytora"."sys_recycle_bin" VALUES (101298232557568, 'ytora', '2026-01-14 21:21:28.354903', '-', 'sys_user', '101293306413056', '{"id": 101293306413056, "email": "5", "phone": "4", "avatar": null, "remark": "321312", "status": null, "id_card": "123", "birthday": null, "password": "2", "create_by": "ytora", "real_name": "3", "update_by": "ytora", "user_name": "1", "create_time": "2026-01-14", "depart_code": "YT-HR-RECR", "update_time": "2026-01-14"}', 'INSERT INTO sys_user (id,create_by,create_time,update_by,update_time,depart_code,remark,status,user_name,real_name,password,avatar,phone,email,birthday,id_card) VALUES (101293306413056,''ytora'',''2026-01-14 21:20:13'',''ytora'',''2026-01-14 21:21:01'',''YT-HR-RECR'',''321312'',NULL,''1'',''3'',''2'',NULL,''4'',''5'',NULL,''123'');');
INSERT INTO "ytora"."sys_recycle_bin" VALUES (104419395567616, 'ytora', '2026-01-15 10:35:13.528349', '-', 'sys_role_permission', '104413990813696', '{"id": 104413990813696, "remark": null, "status": null, "role_id": 1, "create_by": "ytora", "update_by": "ytora", "create_time": "2026-01-15", "depart_code": "YT-HR-RECR", "update_time": "2026-01-15", "permission_id": 104411370422272}', 'INSERT INTO sys_role_permission (id,create_by,create_time,update_by,update_time,depart_code,remark,status,role_id,permission_id) VALUES (104413990813696,''ytora'',''2026-01-15 10:33:51'',''ytora'',''2026-01-15 10:33:51'',''YT-HR-RECR'',NULL,NULL,1,104411370422272);');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS "ytora"."sys_role";
CREATE TABLE "ytora"."sys_role" (
  "id" int8 NOT NULL,
  "create_by" varchar(16) COLLATE "pg_catalog"."default",
  "create_time" timestamp(6),
  "update_by" varchar(16) COLLATE "pg_catalog"."default",
  "update_time" timestamp(6),
  "depart_code" varchar(255) COLLATE "pg_catalog"."default",
  "remark" varchar(255) COLLATE "pg_catalog"."default",
  "status" varchar(255) COLLATE "pg_catalog"."default",
  "role_name" varchar(255) COLLATE "pg_catalog"."default",
  "role_code" varchar(255) COLLATE "pg_catalog"."default" NOT NULL
)
;
COMMENT ON COLUMN "ytora"."sys_role"."id" IS '主键ID';
COMMENT ON COLUMN "ytora"."sys_role"."create_by" IS '创建人';
COMMENT ON COLUMN "ytora"."sys_role"."create_time" IS '创建时间';
COMMENT ON COLUMN "ytora"."sys_role"."update_by" IS '更新人';
COMMENT ON COLUMN "ytora"."sys_role"."update_time" IS '更新时间';
COMMENT ON COLUMN "ytora"."sys_role"."depart_code" IS '创建者所属部门';
COMMENT ON COLUMN "ytora"."sys_role"."remark" IS '数据备注';
COMMENT ON COLUMN "ytora"."sys_role"."status" IS '数据状态';
COMMENT ON COLUMN "ytora"."sys_role"."role_name" IS '角色名称';
COMMENT ON COLUMN "ytora"."sys_role"."role_code" IS '角色编码';
COMMENT ON TABLE "ytora"."sys_role" IS '角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO "ytora"."sys_role" VALUES (1, NULL, '2025-01-01 09:00:00', 'ytora', '2026-01-11 19:22:07.259859', NULL, '管理员，拥有系统最高权限', '1', '管理员', 'admin');

-- ----------------------------
-- Table structure for sys_role_data_rule
-- ----------------------------
DROP TABLE IF EXISTS "ytora"."sys_role_data_rule";
CREATE TABLE "ytora"."sys_role_data_rule" (
  "id" int8 NOT NULL,
  "create_by" varchar(16) COLLATE "pg_catalog"."default",
  "create_time" timestamp(6),
  "update_by" varchar(16) COLLATE "pg_catalog"."default",
  "update_time" timestamp(6),
  "depart_code" varchar(255) COLLATE "pg_catalog"."default",
  "remark" varchar(255) COLLATE "pg_catalog"."default",
  "status" varchar(255) COLLATE "pg_catalog"."default",
  "role_id" int8 NOT NULL,
  "permission_id" int8 NOT NULL,
  "rule_id" int8 NOT NULL
)
;
COMMENT ON COLUMN "ytora"."sys_role_data_rule"."id" IS '主键ID';
COMMENT ON COLUMN "ytora"."sys_role_data_rule"."create_by" IS '创建人';
COMMENT ON COLUMN "ytora"."sys_role_data_rule"."create_time" IS '创建时间';
COMMENT ON COLUMN "ytora"."sys_role_data_rule"."update_by" IS '更新人';
COMMENT ON COLUMN "ytora"."sys_role_data_rule"."update_time" IS '更新时间';
COMMENT ON COLUMN "ytora"."sys_role_data_rule"."depart_code" IS '创建者所属部门';
COMMENT ON COLUMN "ytora"."sys_role_data_rule"."remark" IS '数据备注';
COMMENT ON COLUMN "ytora"."sys_role_data_rule"."status" IS '数据状态';
COMMENT ON COLUMN "ytora"."sys_role_data_rule"."role_id" IS '角色ID';
COMMENT ON COLUMN "ytora"."sys_role_data_rule"."permission_id" IS '资源ID';
COMMENT ON COLUMN "ytora"."sys_role_data_rule"."rule_id" IS '数据规则ID';
COMMENT ON TABLE "ytora"."sys_role_data_rule" IS '角色-数据规则关系表';

-- ----------------------------
-- Records of sys_role_data_rule
-- ----------------------------
INSERT INTO "ytora"."sys_role_data_rule" VALUES (83836073934852, 'ytora', '2026-01-11 19:20:36.968144', 'ytora', '2026-01-11 19:20:36.968144', 'YT-HR-RECR', NULL, NULL, 1, 23, 83692641714176);
INSERT INTO "ytora"."sys_role_data_rule" VALUES (89822462607360, 'ytora', '2026-01-12 20:43:02.010073', 'ytora', '2026-01-12 20:43:02.010073', 'YT-HR-RECR', NULL, NULL, 1, 89686066331648, 89821629317120);
INSERT INTO "ytora"."sys_role_data_rule" VALUES (95668655292416, 'ytora', '2026-01-13 21:29:47.824079', 'ytora', '2026-01-13 21:29:47.824079', 'YT-HR-RECR', NULL, NULL, 1, 23, 83797329379328);
INSERT INTO "ytora"."sys_role_data_rule" VALUES (95668655292417, 'ytora', '2026-01-13 21:29:47.824079', 'ytora', '2026-01-13 21:29:47.824079', 'YT-HR-RECR', NULL, NULL, 1, 23, 83800502239232);
INSERT INTO "ytora"."sys_role_data_rule" VALUES (95668952498176, 'ytora', '2026-01-13 21:29:52.366155', 'ytora', '2026-01-13 21:29:52.366155', 'YT-HR-RECR', NULL, NULL, 1, 23, 83805188390912);

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS "ytora"."sys_role_permission";
CREATE TABLE "ytora"."sys_role_permission" (
  "id" int8 NOT NULL,
  "create_by" varchar(16) COLLATE "pg_catalog"."default",
  "create_time" timestamp(6),
  "update_by" varchar(16) COLLATE "pg_catalog"."default",
  "update_time" timestamp(6),
  "depart_code" varchar(255) COLLATE "pg_catalog"."default",
  "remark" varchar(255) COLLATE "pg_catalog"."default",
  "status" varchar(255) COLLATE "pg_catalog"."default",
  "role_id" int8 NOT NULL,
  "permission_id" int8 NOT NULL
)
;
COMMENT ON COLUMN "ytora"."sys_role_permission"."id" IS '主键ID';
COMMENT ON COLUMN "ytora"."sys_role_permission"."create_by" IS '创建人';
COMMENT ON COLUMN "ytora"."sys_role_permission"."create_time" IS '创建时间';
COMMENT ON COLUMN "ytora"."sys_role_permission"."update_by" IS '更新人';
COMMENT ON COLUMN "ytora"."sys_role_permission"."update_time" IS '更新时间';
COMMENT ON COLUMN "ytora"."sys_role_permission"."depart_code" IS '创建者所属部门';
COMMENT ON COLUMN "ytora"."sys_role_permission"."remark" IS '数据备注';
COMMENT ON COLUMN "ytora"."sys_role_permission"."status" IS '数据状态';
COMMENT ON COLUMN "ytora"."sys_role_permission"."role_id" IS '角色ID';
COMMENT ON COLUMN "ytora"."sys_role_permission"."permission_id" IS '资源ID';
COMMENT ON TABLE "ytora"."sys_role_permission" IS '角色-资源关系表';

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO "ytora"."sys_role_permission" VALUES (41, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 41);
INSERT INTO "ytora"."sys_role_permission" VALUES (42, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 42);
INSERT INTO "ytora"."sys_role_permission" VALUES (43, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 43);
INSERT INTO "ytora"."sys_role_permission" VALUES (44, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 44);
INSERT INTO "ytora"."sys_role_permission" VALUES (45, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 45);
INSERT INTO "ytora"."sys_role_permission" VALUES (46, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 46);
INSERT INTO "ytora"."sys_role_permission" VALUES (47, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 47);
INSERT INTO "ytora"."sys_role_permission" VALUES (1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 1);
INSERT INTO "ytora"."sys_role_permission" VALUES (2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 2);
INSERT INTO "ytora"."sys_role_permission" VALUES (3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 3);
INSERT INTO "ytora"."sys_role_permission" VALUES (4, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 4);
INSERT INTO "ytora"."sys_role_permission" VALUES (5, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 5);
INSERT INTO "ytora"."sys_role_permission" VALUES (6, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 6);
INSERT INTO "ytora"."sys_role_permission" VALUES (7, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 7);
INSERT INTO "ytora"."sys_role_permission" VALUES (8, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 8);
INSERT INTO "ytora"."sys_role_permission" VALUES (9, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 9);
INSERT INTO "ytora"."sys_role_permission" VALUES (10, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 10);
INSERT INTO "ytora"."sys_role_permission" VALUES (11, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 11);
INSERT INTO "ytora"."sys_role_permission" VALUES (12, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 12);
INSERT INTO "ytora"."sys_role_permission" VALUES (13, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 13);
INSERT INTO "ytora"."sys_role_permission" VALUES (14, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 14);
INSERT INTO "ytora"."sys_role_permission" VALUES (15, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 15);
INSERT INTO "ytora"."sys_role_permission" VALUES (20, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 20);
INSERT INTO "ytora"."sys_role_permission" VALUES (23, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 23);
INSERT INTO "ytora"."sys_role_permission" VALUES (24, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 24);
INSERT INTO "ytora"."sys_role_permission" VALUES (25, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 25);
INSERT INTO "ytora"."sys_role_permission" VALUES (26, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 26);
INSERT INTO "ytora"."sys_role_permission" VALUES (27, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 27);
INSERT INTO "ytora"."sys_role_permission" VALUES (48, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 48);
INSERT INTO "ytora"."sys_role_permission" VALUES (49, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 49);
INSERT INTO "ytora"."sys_role_permission" VALUES (50, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 50);
INSERT INTO "ytora"."sys_role_permission" VALUES (51, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 51);
INSERT INTO "ytora"."sys_role_permission" VALUES (52, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 52);
INSERT INTO "ytora"."sys_role_permission" VALUES (53, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 53);
INSERT INTO "ytora"."sys_role_permission" VALUES (35, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 35);
INSERT INTO "ytora"."sys_role_permission" VALUES (36, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 36);
INSERT INTO "ytora"."sys_role_permission" VALUES (54, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 54);
INSERT INTO "ytora"."sys_role_permission" VALUES (55, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 55);
INSERT INTO "ytora"."sys_role_permission" VALUES (56, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 56);
INSERT INTO "ytora"."sys_role_permission" VALUES (57, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 57);
INSERT INTO "ytora"."sys_role_permission" VALUES (58, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 58);
INSERT INTO "ytora"."sys_role_permission" VALUES (59, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 59);
INSERT INTO "ytora"."sys_role_permission" VALUES (60, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 60);
INSERT INTO "ytora"."sys_role_permission" VALUES (61, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 61);
INSERT INTO "ytora"."sys_role_permission" VALUES (28, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 28);
INSERT INTO "ytora"."sys_role_permission" VALUES (29, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 29);
INSERT INTO "ytora"."sys_role_permission" VALUES (30, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 30);
INSERT INTO "ytora"."sys_role_permission" VALUES (31, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 31);
INSERT INTO "ytora"."sys_role_permission" VALUES (33, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 33);
INSERT INTO "ytora"."sys_role_permission" VALUES (34, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 34);
INSERT INTO "ytora"."sys_role_permission" VALUES (37, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 37);
INSERT INTO "ytora"."sys_role_permission" VALUES (38, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 38);
INSERT INTO "ytora"."sys_role_permission" VALUES (39, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 39);
INSERT INTO "ytora"."sys_role_permission" VALUES (40, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 40);
INSERT INTO "ytora"."sys_role_permission" VALUES (62, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 62);
INSERT INTO "ytora"."sys_role_permission" VALUES (63, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 63);
INSERT INTO "ytora"."sys_role_permission" VALUES (64, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 64);
INSERT INTO "ytora"."sys_role_permission" VALUES (65, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 65);
INSERT INTO "ytora"."sys_role_permission" VALUES (66, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 66);
INSERT INTO "ytora"."sys_role_permission" VALUES (67, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 67);
INSERT INTO "ytora"."sys_role_permission" VALUES (68, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 68);
INSERT INTO "ytora"."sys_role_permission" VALUES (69, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 69);
INSERT INTO "ytora"."sys_role_permission" VALUES (76, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 76);
INSERT INTO "ytora"."sys_role_permission" VALUES (77, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 77);
INSERT INTO "ytora"."sys_role_permission" VALUES (78, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 78);
INSERT INTO "ytora"."sys_role_permission" VALUES (79, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 79);
INSERT INTO "ytora"."sys_role_permission" VALUES (80, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 80);
INSERT INTO "ytora"."sys_role_permission" VALUES (70, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 70);
INSERT INTO "ytora"."sys_role_permission" VALUES (71, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 71);
INSERT INTO "ytora"."sys_role_permission" VALUES (72, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 72);
INSERT INTO "ytora"."sys_role_permission" VALUES (73, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 73);
INSERT INTO "ytora"."sys_role_permission" VALUES (74, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 74);
INSERT INTO "ytora"."sys_role_permission" VALUES (75, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 75);
INSERT INTO "ytora"."sys_role_permission" VALUES (81, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 81);
INSERT INTO "ytora"."sys_role_permission" VALUES (82, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 82);
INSERT INTO "ytora"."sys_role_permission" VALUES (45473197588480, 'ytora', '2026-01-05 00:44:25.93048', 'ytora', '2026-01-05 00:44:25.93048', 'YT-HR-RECR', NULL, NULL, 1, 16);
INSERT INTO "ytora"."sys_role_permission" VALUES (45473197588481, 'ytora', '2026-01-05 00:44:25.93048', 'ytora', '2026-01-05 00:44:25.93048', 'YT-HR-RECR', NULL, NULL, 1, 17);
INSERT INTO "ytora"."sys_role_permission" VALUES (45473197588482, 'ytora', '2026-01-05 00:44:25.93048', 'ytora', '2026-01-05 00:44:25.93048', 'YT-HR-RECR', NULL, NULL, 1, 18);
INSERT INTO "ytora"."sys_role_permission" VALUES (45473197588483, 'ytora', '2026-01-05 00:44:25.93048', 'ytora', '2026-01-05 00:44:25.93048', 'YT-HR-RECR', NULL, NULL, 1, 19);
INSERT INTO "ytora"."sys_role_permission" VALUES (45473197588484, 'ytora', '2026-01-05 00:44:25.93048', 'ytora', '2026-01-05 00:44:25.93048', 'YT-HR-RECR', NULL, NULL, 1, 21);
INSERT INTO "ytora"."sys_role_permission" VALUES (45473197588485, 'ytora', '2026-01-05 00:44:25.93048', 'ytora', '2026-01-05 00:44:25.93048', 'YT-HR-RECR', NULL, NULL, 1, 22);
INSERT INTO "ytora"."sys_role_permission" VALUES (83, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 83);
INSERT INTO "ytora"."sys_role_permission" VALUES (67861003698176, 'ytora', '2026-01-08 23:37:56.741994', 'ytora', '2026-01-08 23:37:56.741994', 'YT-HR-RECR', NULL, NULL, 1, 67859872677888);
INSERT INTO "ytora"."sys_role_permission" VALUES (74324877115392, 'ytora', '2026-01-10 03:01:47.621959', 'ytora', '2026-01-10 03:01:47.621959', 'YT-HR-RECR', NULL, NULL, 1, 74273410318336);
INSERT INTO "ytora"."sys_role_permission" VALUES (74324877115393, 'ytora', '2026-01-10 03:01:47.621959', 'ytora', '2026-01-10 03:01:47.621959', 'YT-HR-RECR', NULL, NULL, 1, 74299826634752);
INSERT INTO "ytora"."sys_role_permission" VALUES (74324877115394, 'ytora', '2026-01-10 03:01:47.622959', 'ytora', '2026-01-10 03:01:47.621959', 'YT-HR-RECR', NULL, NULL, 1, 74312224735232);
INSERT INTO "ytora"."sys_role_permission" VALUES (74324877115395, 'ytora', '2026-01-10 03:01:47.622959', 'ytora', '2026-01-10 03:01:47.622959', 'YT-HR-RECR', NULL, NULL, 1, 74307169026048);
INSERT INTO "ytora"."sys_role_permission" VALUES (74454739517440, 'ytora', '2026-01-10 03:34:49.164741', 'ytora', '2026-01-10 03:34:49.164741', 'YT-HR-RECR', NULL, NULL, 1, 74405249024000);
INSERT INTO "ytora"."sys_role_permission" VALUES (74454739517441, 'ytora', '2026-01-10 03:34:49.164741', 'ytora', '2026-01-10 03:34:49.164741', 'YT-HR-RECR', NULL, NULL, 1, 74401624817664);
INSERT INTO "ytora"."sys_role_permission" VALUES (77590400794624, 'ytora', '2026-01-10 16:52:15.558908', 'ytora', '2026-01-10 16:52:15.558908', 'YT-HR-RECR', NULL, NULL, 1, 32);
INSERT INTO "ytora"."sys_role_permission" VALUES (78510362001408, 'ytora', '2026-01-10 20:46:13.053436', 'ytora', '2026-01-10 20:46:13.053436', 'YT-HR-RECR', NULL, NULL, 1, 78505298690048);
INSERT INTO "ytora"."sys_role_permission" VALUES (78510362001409, 'ytora', '2026-01-10 20:46:13.053436', 'ytora', '2026-01-10 20:46:13.053436', 'YT-HR-RECR', NULL, NULL, 1, 78495257067520);
INSERT INTO "ytora"."sys_role_permission" VALUES (78510362001410, 'ytora', '2026-01-10 20:46:13.053436', 'ytora', '2026-01-10 20:46:13.053436', 'YT-HR-RECR', NULL, NULL, 1, 78509540638720);
INSERT INTO "ytora"."sys_role_permission" VALUES (78950302154752, 'ytora', '2026-01-10 22:38:06.003908', 'ytora', '2026-01-10 22:38:06.003908', 'YT-HR-RECR', NULL, NULL, 1, 78931841581056);
INSERT INTO "ytora"."sys_role_permission" VALUES (89820068839424, 'ytora', '2026-01-12 20:42:25.479745', 'ytora', '2026-01-12 20:42:25.479745', 'YT-HR-RECR', NULL, NULL, 1, 89692227829760);
INSERT INTO "ytora"."sys_role_permission" VALUES (89820068839425, 'ytora', '2026-01-12 20:42:25.479745', 'ytora', '2026-01-12 20:42:25.479745', 'YT-HR-RECR', NULL, NULL, 1, 89688258904064);
INSERT INTO "ytora"."sys_role_permission" VALUES (89820068839426, 'ytora', '2026-01-12 20:42:25.479745', 'ytora', '2026-01-12 20:42:25.479745', 'YT-HR-RECR', NULL, NULL, 1, 89789647749120);
INSERT INTO "ytora"."sys_role_permission" VALUES (89820068839427, 'ytora', '2026-01-12 20:42:25.479745', 'ytora', '2026-01-12 20:42:25.479745', 'YT-HR-RECR', NULL, NULL, 1, 89686066331648);
INSERT INTO "ytora"."sys_role_permission" VALUES (89820068839428, 'ytora', '2026-01-12 20:42:25.479745', 'ytora', '2026-01-12 20:42:25.479745', 'YT-HR-RECR', NULL, NULL, 1, 89795768156160);
INSERT INTO "ytora"."sys_role_permission" VALUES (89820068839429, 'ytora', '2026-01-12 20:42:25.479745', 'ytora', '2026-01-12 20:42:25.479745', 'YT-HR-RECR', NULL, NULL, 1, 89734831210496);
INSERT INTO "ytora"."sys_role_permission" VALUES (89820068839430, 'ytora', '2026-01-12 20:42:25.479745', 'ytora', '2026-01-12 20:42:25.479745', 'YT-HR-RECR', NULL, NULL, 1, 89723277017088);
INSERT INTO "ytora"."sys_role_permission" VALUES (89820068839431, 'ytora', '2026-01-12 20:42:25.479745', 'ytora', '2026-01-12 20:42:25.479745', 'YT-HR-RECR', NULL, NULL, 1, 89718307422208);
INSERT INTO "ytora"."sys_role_permission" VALUES (89968386179072, 'ytora', '2026-01-12 21:20:08.627285', 'ytora', '2026-01-12 21:20:08.627285', 'YT-HR-RECR', NULL, NULL, 1, 89967055077376);
INSERT INTO "ytora"."sys_role_permission" VALUES (90108526460928, 'ytora', '2026-01-12 21:55:46.997593', 'ytora', '2026-01-12 21:55:46.997593', 'YT-HR-RECR', NULL, NULL, 1, 89692227829761);
INSERT INTO "ytora"."sys_role_permission" VALUES (90108526460929, 'ytora', '2026-01-12 21:55:46.997593', 'ytora', '2026-01-12 21:55:46.997593', 'YT-HR-RECR', NULL, NULL, 1, 89688258904065);
INSERT INTO "ytora"."sys_role_permission" VALUES (90108526460930, 'ytora', '2026-01-12 21:55:46.997593', 'ytora', '2026-01-12 21:55:46.997593', 'YT-HR-RECR', NULL, NULL, 1, 89686066331649);
INSERT INTO "ytora"."sys_role_permission" VALUES (90108526460931, 'ytora', '2026-01-12 21:55:46.997593', 'ytora', '2026-01-12 21:55:46.997593', 'YT-HR-RECR', NULL, NULL, 1, 89789647749121);
INSERT INTO "ytora"."sys_role_permission" VALUES (90108526460932, 'ytora', '2026-01-12 21:55:46.997593', 'ytora', '2026-01-12 21:55:46.997593', 'YT-HR-RECR', NULL, NULL, 1, 89795768156161);
INSERT INTO "ytora"."sys_role_permission" VALUES (90108526460933, 'ytora', '2026-01-12 21:55:46.997593', 'ytora', '2026-01-12 21:55:46.997593', 'YT-HR-RECR', NULL, NULL, 1, 89723277017089);
INSERT INTO "ytora"."sys_role_permission" VALUES (90108526460934, 'ytora', '2026-01-12 21:55:46.998596', 'ytora', '2026-01-12 21:55:46.998596', 'YT-HR-RECR', NULL, NULL, 1, 89718307422209);
INSERT INTO "ytora"."sys_role_permission" VALUES (90108526460935, 'ytora', '2026-01-12 21:55:46.998596', 'ytora', '2026-01-12 21:55:46.998596', 'YT-HR-RECR', NULL, NULL, 1, 89734831210497);
INSERT INTO "ytora"."sys_role_permission" VALUES (100002689122304, 'ytora', '2026-01-14 15:51:59.939412', 'ytora', '2026-01-14 15:51:59.939412', 'YT-HR-RECR', NULL, NULL, 1, 99995844542464);
INSERT INTO "ytora"."sys_role_permission" VALUES (100074381246464, 'ytora', '2026-01-14 16:10:13.874108', 'ytora', '2026-01-14 16:10:13.874108', 'YT-HR-RECR', NULL, NULL, 1, 100070845120512);
INSERT INTO "ytora"."sys_role_permission" VALUES (100074381246465, 'ytora', '2026-01-14 16:10:13.874108', 'ytora', '2026-01-14 16:10:13.874108', 'YT-HR-RECR', NULL, NULL, 1, 100072333770752);
INSERT INTO "ytora"."sys_role_permission" VALUES (100348380905472, 'ytora', '2026-01-14 17:19:54.777026', 'ytora', '2026-01-14 17:19:54.777026', 'YT-HR-RECR', NULL, NULL, 1, 100345884770304);
INSERT INTO "ytora"."sys_role_permission" VALUES (100348380905473, 'ytora', '2026-01-14 17:19:54.777026', 'ytora', '2026-01-14 17:19:54.777026', 'YT-HR-RECR', NULL, NULL, 1, 100347815264256);
INSERT INTO "ytora"."sys_role_permission" VALUES (100462210449408, 'ytora', '2026-01-14 17:48:51.678691', 'ytora', '2026-01-14 17:48:51.678691', 'YT-HR-RECR', NULL, NULL, 1, 100460061065216);
INSERT INTO "ytora"."sys_role_permission" VALUES (100507483570176, 'ytora', '2026-01-14 18:00:22.491248', 'ytora', '2026-01-14 18:00:22.491248', 'YT-HR-RECR', NULL, NULL, 1, 100507141996544);
INSERT INTO "ytora"."sys_role_permission" VALUES (100529916346368, 'ytora', '2026-01-14 18:06:04.788663', 'ytora', '2026-01-14 18:06:04.788663', 'YT-HR-RECR', NULL, NULL, 1, 100529256923136);
INSERT INTO "ytora"."sys_role_permission" VALUES (101103413493760, 'ytora', '2026-01-14 20:31:55.660824', 'ytora', '2026-01-14 20:31:55.660824', 'YT-HR-RECR', NULL, NULL, 1, 101102938357760);
INSERT INTO "ytora"."sys_role_permission" VALUES (104413990813697, 'ytora', '2026-01-15 10:33:51.061016', 'ytora', '2026-01-15 10:33:51.061016', 'YT-HR-RECR', NULL, NULL, 1, 104413187342336);
INSERT INTO "ytora"."sys_role_permission" VALUES (104413990813698, 'ytora', '2026-01-15 10:33:51.061016', 'ytora', '2026-01-15 10:33:51.061016', 'YT-HR-RECR', NULL, NULL, 1, 104386373746688);
INSERT INTO "ytora"."sys_role_permission" VALUES (104420167712768, 'ytora', '2026-01-15 10:35:25.31353', 'ytora', '2026-01-15 10:35:25.31353', 'YT-HR-RECR', NULL, NULL, 1, 104411370422272);
INSERT INTO "ytora"."sys_role_permission" VALUES (124378295107584, 'ytora', '2026-01-18 23:11:02.16906', 'ytora', '2026-01-18 23:11:02.16906', 'YT-HR-RECR', NULL, NULL, 1, 124376883068928);

-- ----------------------------
-- Table structure for sys_scheduler_task
-- ----------------------------
DROP TABLE IF EXISTS "ytora"."sys_scheduler_task";
CREATE TABLE "ytora"."sys_scheduler_task" (
  "id" int8 NOT NULL,
  "create_by" varchar(16) COLLATE "pg_catalog"."default",
  "create_time" timestamp(6),
  "update_by" varchar(16) COLLATE "pg_catalog"."default",
  "update_time" timestamp(6),
  "depart_code" varchar(255) COLLATE "pg_catalog"."default",
  "remark" varchar(255) COLLATE "pg_catalog"."default",
  "status" varchar(255) COLLATE "pg_catalog"."default",
  "task_name" varchar(255) COLLATE "pg_catalog"."default",
  "cron" varchar(255) COLLATE "pg_catalog"."default",
  "type" int2,
  "bean_name" varchar(255) COLLATE "pg_catalog"."default",
  "parameter" varchar(255) COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "ytora"."sys_scheduler_task"."id" IS '主键ID';
COMMENT ON COLUMN "ytora"."sys_scheduler_task"."create_by" IS '创建人';
COMMENT ON COLUMN "ytora"."sys_scheduler_task"."create_time" IS '创建时间';
COMMENT ON COLUMN "ytora"."sys_scheduler_task"."update_by" IS '更新人';
COMMENT ON COLUMN "ytora"."sys_scheduler_task"."update_time" IS '更新时间';
COMMENT ON COLUMN "ytora"."sys_scheduler_task"."depart_code" IS '创建者所属部门';
COMMENT ON COLUMN "ytora"."sys_scheduler_task"."remark" IS '数据备注';
COMMENT ON COLUMN "ytora"."sys_scheduler_task"."status" IS '数据状态';
COMMENT ON COLUMN "ytora"."sys_scheduler_task"."task_name" IS '任务名称';
COMMENT ON COLUMN "ytora"."sys_scheduler_task"."cron" IS '任务执行周期';
COMMENT ON COLUMN "ytora"."sys_scheduler_task"."type" IS '任务类型';
COMMENT ON COLUMN "ytora"."sys_scheduler_task"."bean_name" IS '任务Bean名称';
COMMENT ON COLUMN "ytora"."sys_scheduler_task"."parameter" IS '任务参数';
COMMENT ON TABLE "ytora"."sys_scheduler_task" IS '定时任务表';

-- ----------------------------
-- Records of sys_scheduler_task
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS "ytora"."sys_user";
CREATE TABLE "ytora"."sys_user" (
  "id" int8 NOT NULL,
  "create_by" varchar(16) COLLATE "pg_catalog"."default",
  "create_time" timestamp(6),
  "update_by" varchar(16) COLLATE "pg_catalog"."default",
  "update_time" timestamp(6),
  "depart_code" varchar(255) COLLATE "pg_catalog"."default",
  "remark" varchar(255) COLLATE "pg_catalog"."default",
  "status" varchar(255) COLLATE "pg_catalog"."default" DEFAULT 1,
  "user_name" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "real_name" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "password" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "avatar" varchar(255) COLLATE "pg_catalog"."default",
  "phone" varchar(16) COLLATE "pg_catalog"."default",
  "email" varchar(255) COLLATE "pg_catalog"."default",
  "birthday" date,
  "id_card" varchar(255) COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "ytora"."sys_user"."id" IS '主键ID';
COMMENT ON COLUMN "ytora"."sys_user"."create_by" IS '创建人';
COMMENT ON COLUMN "ytora"."sys_user"."create_time" IS '创建时间';
COMMENT ON COLUMN "ytora"."sys_user"."update_by" IS '更新人';
COMMENT ON COLUMN "ytora"."sys_user"."update_time" IS '更新时间';
COMMENT ON COLUMN "ytora"."sys_user"."depart_code" IS '创建者所属部门';
COMMENT ON COLUMN "ytora"."sys_user"."remark" IS '数据备注';
COMMENT ON COLUMN "ytora"."sys_user"."status" IS '数据状态';
COMMENT ON COLUMN "ytora"."sys_user"."user_name" IS '用户名';
COMMENT ON COLUMN "ytora"."sys_user"."real_name" IS '真实姓名';
COMMENT ON COLUMN "ytora"."sys_user"."password" IS '密码';
COMMENT ON COLUMN "ytora"."sys_user"."avatar" IS '头像';
COMMENT ON COLUMN "ytora"."sys_user"."phone" IS '手机号码';
COMMENT ON COLUMN "ytora"."sys_user"."email" IS '邮箱';
COMMENT ON COLUMN "ytora"."sys_user"."birthday" IS '生日';
COMMENT ON COLUMN "ytora"."sys_user"."id_card" IS '身份证';
COMMENT ON TABLE "ytora"."sys_user" IS '用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO "ytora"."sys_user" VALUES (1, NULL, '2025-01-04 10:00:00', 'ytora', '2026-01-14 21:21:23.238532', 'YT-HR-RECR', '这是一个一个一个备注啊啊啊啊啊啊啊啊', '1', 'ytora', '杨桐', '1', '/avatar.jpg', '13811110009', 'sun.yao@ytora.com', '2025-12-22', '114514');

-- ----------------------------
-- Table structure for sys_user_depart
-- ----------------------------
DROP TABLE IF EXISTS "ytora"."sys_user_depart";
CREATE TABLE "ytora"."sys_user_depart" (
  "id" int8 NOT NULL,
  "create_by" varchar(16) COLLATE "pg_catalog"."default",
  "create_time" timestamp(6),
  "update_by" varchar(16) COLLATE "pg_catalog"."default",
  "update_time" timestamp(6),
  "depart_code" varchar(255) COLLATE "pg_catalog"."default",
  "remark" varchar(255) COLLATE "pg_catalog"."default",
  "status" varchar(255) COLLATE "pg_catalog"."default",
  "user_id" int8 NOT NULL,
  "depart_id" int8 NOT NULL
)
;
COMMENT ON COLUMN "ytora"."sys_user_depart"."id" IS '主键ID';
COMMENT ON COLUMN "ytora"."sys_user_depart"."create_by" IS '创建人';
COMMENT ON COLUMN "ytora"."sys_user_depart"."create_time" IS '创建时间';
COMMENT ON COLUMN "ytora"."sys_user_depart"."update_by" IS '更新人';
COMMENT ON COLUMN "ytora"."sys_user_depart"."update_time" IS '更新时间';
COMMENT ON COLUMN "ytora"."sys_user_depart"."depart_code" IS '创建者所属部门';
COMMENT ON COLUMN "ytora"."sys_user_depart"."remark" IS '数据备注';
COMMENT ON COLUMN "ytora"."sys_user_depart"."status" IS '数据状态';
COMMENT ON COLUMN "ytora"."sys_user_depart"."user_id" IS '用户ID';
COMMENT ON COLUMN "ytora"."sys_user_depart"."depart_id" IS '部门ID';
COMMENT ON TABLE "ytora"."sys_user_depart" IS '用户-部门关系表';

-- ----------------------------
-- Records of sys_user_depart
-- ----------------------------
INSERT INTO "ytora"."sys_user_depart" VALUES (1, NULL, '2025-01-01 10:00:00', NULL, NULL, NULL, NULL, '1', 101, 1);
INSERT INTO "ytora"."sys_user_depart" VALUES (2, NULL, '2025-01-02 10:00:00', NULL, NULL, NULL, NULL, '1', 102, 2);
INSERT INTO "ytora"."sys_user_depart" VALUES (3, NULL, '2025-01-02 11:00:00', NULL, NULL, NULL, NULL, '1', 103, 3);
INSERT INTO "ytora"."sys_user_depart" VALUES (4, NULL, '2025-01-02 12:00:00', NULL, NULL, NULL, NULL, '1', 104, 4);
INSERT INTO "ytora"."sys_user_depart" VALUES (5, NULL, '2025-01-03 09:00:00', NULL, NULL, NULL, NULL, '1', 105, 6);
INSERT INTO "ytora"."sys_user_depart" VALUES (6, NULL, '2025-01-03 10:00:00', NULL, NULL, NULL, NULL, '1', 106, 6);
INSERT INTO "ytora"."sys_user_depart" VALUES (7, NULL, '2025-01-03 11:00:00', NULL, NULL, NULL, NULL, '1', 107, 7);
INSERT INTO "ytora"."sys_user_depart" VALUES (8, NULL, '2025-01-04 09:00:00', NULL, NULL, NULL, NULL, '1', 108, 8);
INSERT INTO "ytora"."sys_user_depart" VALUES (9, NULL, '2025-01-04 10:00:00', NULL, NULL, NULL, NULL, '1', 109, 9);
INSERT INTO "ytora"."sys_user_depart" VALUES (10, NULL, '2025-01-05 09:00:00', NULL, NULL, NULL, NULL, '1', 110, 10);
INSERT INTO "ytora"."sys_user_depart" VALUES (11, NULL, '2025-01-05 10:00:00', NULL, NULL, NULL, NULL, '1', 111, 11);
INSERT INTO "ytora"."sys_user_depart" VALUES (12, NULL, '2025-01-06 09:00:00', NULL, NULL, NULL, NULL, '1', 112, 1);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS "ytora"."sys_user_role";
CREATE TABLE "ytora"."sys_user_role" (
  "id" int8 NOT NULL,
  "create_by" varchar(16) COLLATE "pg_catalog"."default",
  "create_time" timestamp(6),
  "update_by" varchar(16) COLLATE "pg_catalog"."default",
  "update_time" timestamp(6),
  "depart_code" varchar(255) COLLATE "pg_catalog"."default",
  "remark" varchar(255) COLLATE "pg_catalog"."default",
  "status" varchar(255) COLLATE "pg_catalog"."default",
  "user_id" int8 NOT NULL,
  "role_id" int8 NOT NULL
)
;
COMMENT ON COLUMN "ytora"."sys_user_role"."id" IS '主键ID';
COMMENT ON COLUMN "ytora"."sys_user_role"."create_by" IS '创建人';
COMMENT ON COLUMN "ytora"."sys_user_role"."create_time" IS '创建时间';
COMMENT ON COLUMN "ytora"."sys_user_role"."update_by" IS '更新人';
COMMENT ON COLUMN "ytora"."sys_user_role"."update_time" IS '更新时间';
COMMENT ON COLUMN "ytora"."sys_user_role"."depart_code" IS '创建者所属部门';
COMMENT ON COLUMN "ytora"."sys_user_role"."remark" IS '数据备注';
COMMENT ON COLUMN "ytora"."sys_user_role"."status" IS '数据状态';
COMMENT ON COLUMN "ytora"."sys_user_role"."user_id" IS '用户ID';
COMMENT ON COLUMN "ytora"."sys_user_role"."role_id" IS '角色ID';
COMMENT ON TABLE "ytora"."sys_user_role" IS '用户-角色关系表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO "ytora"."sys_user_role" VALUES (44777358688256, 'ytora', '2026-01-04 21:47:28.271858', 'ytora', '2026-01-04 21:47:28.271858', 'YT-HR-RECR', NULL, NULL, 1, 1);

-- ----------------------------
-- Primary Key structure for table sys_data_rule
-- ----------------------------
ALTER TABLE "ytora"."sys_data_rule" ADD CONSTRAINT "sys_data_rule_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Uniques structure for table sys_depart
-- ----------------------------
ALTER TABLE "ytora"."sys_depart" ADD CONSTRAINT "uk_depart_code" UNIQUE ("depart_code");

-- ----------------------------
-- Primary Key structure for table sys_depart
-- ----------------------------
ALTER TABLE "ytora"."sys_depart" ADD CONSTRAINT "sys_depart_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table sys_dict
-- ----------------------------
ALTER TABLE "ytora"."sys_dict" ADD CONSTRAINT "sys_dict_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table sys_file
-- ----------------------------
ALTER TABLE "ytora"."sys_file" ADD CONSTRAINT "sys_file_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table sys_folder
-- ----------------------------
ALTER TABLE "ytora"."sys_folder" ADD CONSTRAINT "sys_folder_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table sys_log
-- ----------------------------
ALTER TABLE "ytora"."sys_log" ADD CONSTRAINT "sys_log_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Uniques structure for table sys_permission
-- ----------------------------
ALTER TABLE "ytora"."sys_permission" ADD CONSTRAINT "uk_permission_code" UNIQUE ("permission_code");
COMMENT ON CONSTRAINT "uk_permission_code" ON "ytora"."sys_permission" IS '资源code应该唯一';

-- ----------------------------
-- Primary Key structure for table sys_permission
-- ----------------------------
ALTER TABLE "ytora"."sys_permission" ADD CONSTRAINT "sys_permission_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table sys_recycle_bin
-- ----------------------------
ALTER TABLE "ytora"."sys_recycle_bin" ADD CONSTRAINT "sys_recycle_bin_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table sys_role
-- ----------------------------
ALTER TABLE "ytora"."sys_role" ADD CONSTRAINT "sys_role_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table sys_role_data_rule
-- ----------------------------
ALTER TABLE "ytora"."sys_role_data_rule" ADD CONSTRAINT "sys_role_data_rule_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table sys_role_permission
-- ----------------------------
ALTER TABLE "ytora"."sys_role_permission" ADD CONSTRAINT "sys_role_permission_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table sys_scheduler_task
-- ----------------------------
ALTER TABLE "ytora"."sys_scheduler_task" ADD CONSTRAINT "sys_scheduler_task_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table sys_user
-- ----------------------------
ALTER TABLE "ytora"."sys_user" ADD CONSTRAINT "sys_user_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table sys_user_depart
-- ----------------------------
ALTER TABLE "ytora"."sys_user_depart" ADD CONSTRAINT "sys_user_depart_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table sys_user_role
-- ----------------------------
ALTER TABLE "ytora"."sys_user_role" ADD CONSTRAINT "sys_user_role_pkey" PRIMARY KEY ("id");
