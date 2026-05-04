-- ----------------------------
-- Table structure for base
-- ----------------------------
DROP TABLE IF EXISTS "ytora"."base";
CREATE TABLE "ytora"."base" (
  "id" int8 NOT NULL,
  "create_by" varchar(255) COLLATE "pg_catalog"."default",
  "create_time" date,
  "update_by" varchar(255) COLLATE "pg_catalog"."default",
  "update_time" date,
  "depart_code" varchar(255) COLLATE "pg_catalog"."default",
  "remark" varchar(255) COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "ytora"."base"."id" IS '主键';
COMMENT ON COLUMN "ytora"."base"."create_by" IS '数据创建人';
COMMENT ON COLUMN "ytora"."base"."create_time" IS '数据创建日期';
COMMENT ON COLUMN "ytora"."base"."update_by" IS '数据修改人';
COMMENT ON COLUMN "ytora"."base"."update_time" IS '数据修改日期';
COMMENT ON COLUMN "ytora"."base"."depart_code" IS '数据创建人所属部门';
COMMENT ON COLUMN "ytora"."base"."remark" IS '备注';

-- ----------------------------
-- Records of base
-- ----------------------------

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS "ytora"."sys_config";
CREATE TABLE "ytora"."sys_config" (
  "id" int8 NOT NULL,
  "create_by" varchar(255) COLLATE "pg_catalog"."default",
  "create_time" timestamp(6),
  "update_by" varchar(255) COLLATE "pg_catalog"."default",
  "update_time" timestamp(6),
  "depart_code" varchar(255) COLLATE "pg_catalog"."default",
  "remark" varchar(255) COLLATE "pg_catalog"."default",
  "name" varchar(255) COLLATE "pg_catalog"."default",
  "key" varchar(255) COLLATE "pg_catalog"."default",
  "value" varchar(255) COLLATE "pg_catalog"."default",
  "type" varchar(255) COLLATE "pg_catalog"."default",
  "status" bool
)
;
COMMENT ON COLUMN "ytora"."sys_config"."id" IS '数据主键ID';
COMMENT ON COLUMN "ytora"."sys_config"."create_by" IS '数据创建人';
COMMENT ON COLUMN "ytora"."sys_config"."create_time" IS '数据创建日期';
COMMENT ON COLUMN "ytora"."sys_config"."update_by" IS '数据修改人';
COMMENT ON COLUMN "ytora"."sys_config"."update_time" IS '数据修改日期';
COMMENT ON COLUMN "ytora"."sys_config"."depart_code" IS '数据创建人所属部门';
COMMENT ON COLUMN "ytora"."sys_config"."remark" IS '数据备注';
COMMENT ON COLUMN "ytora"."sys_config"."name" IS '配置名称';
COMMENT ON COLUMN "ytora"."sys_config"."key" IS '键';
COMMENT ON COLUMN "ytora"."sys_config"."value" IS '值';
COMMENT ON COLUMN "ytora"."sys_config"."type" IS '配置类型';
COMMENT ON COLUMN "ytora"."sys_config"."status" IS '是否启用';
COMMENT ON TABLE "ytora"."sys_config" IS '系统配置';

-- ----------------------------
-- Records of sys_config
-- ----------------------------

-- ----------------------------
-- Table structure for sys_data_scope
-- ----------------------------
DROP TABLE IF EXISTS "ytora"."sys_data_scope";
CREATE TABLE "ytora"."sys_data_scope" (
  "id" int8 NOT NULL,
  "create_by" varchar(255) COLLATE "pg_catalog"."default",
  "create_time" timestamp(6),
  "update_by" varchar(255) COLLATE "pg_catalog"."default",
  "update_time" timestamp(6),
  "depart_code" varchar(255) COLLATE "pg_catalog"."default",
  "remark" varchar(255) COLLATE "pg_catalog"."default",
  "group_id" int8 NOT NULL,
  "type" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "name" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "column" varchar(255) COLLATE "pg_catalog"."default",
  "value" varchar(255) COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "ytora"."sys_data_scope"."id" IS '数据主键ID';
COMMENT ON COLUMN "ytora"."sys_data_scope"."create_by" IS '数据创建人';
COMMENT ON COLUMN "ytora"."sys_data_scope"."create_time" IS '数据创建日期';
COMMENT ON COLUMN "ytora"."sys_data_scope"."update_by" IS '数据修改人';
COMMENT ON COLUMN "ytora"."sys_data_scope"."update_time" IS '数据修改日期';
COMMENT ON COLUMN "ytora"."sys_data_scope"."depart_code" IS '数据创建人所属部门';
COMMENT ON COLUMN "ytora"."sys_data_scope"."remark" IS '数据备注';
COMMENT ON COLUMN "ytora"."sys_data_scope"."group_id" IS '所属分组ID';
COMMENT ON COLUMN "ytora"."sys_data_scope"."type" IS '数据范围类型';
COMMENT ON COLUMN "ytora"."sys_data_scope"."name" IS '数据范围名称';
COMMENT ON COLUMN "ytora"."sys_data_scope"."column" IS '数据范围匹配的列';
COMMENT ON COLUMN "ytora"."sys_data_scope"."value" IS '数据范围的规则';
COMMENT ON TABLE "ytora"."sys_data_scope" IS '数据范围';

-- ----------------------------
-- Records of sys_data_scope
-- ----------------------------

-- ----------------------------
-- Table structure for sys_data_scope_group
-- ----------------------------
DROP TABLE IF EXISTS "ytora"."sys_data_scope_group";
CREATE TABLE "ytora"."sys_data_scope_group" (
  "id" int8 NOT NULL,
  "create_by" varchar(255) COLLATE "pg_catalog"."default",
  "create_time" timestamp(6),
  "update_by" varchar(255) COLLATE "pg_catalog"."default",
  "update_time" timestamp(6),
  "depart_code" varchar(255) COLLATE "pg_catalog"."default",
  "remark" varchar(255) COLLATE "pg_catalog"."default",
  "permission_id" int8 NOT NULL,
  "name" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "code" varchar(255) COLLATE "pg_catalog"."default" NOT NULL
)
;
COMMENT ON COLUMN "ytora"."sys_data_scope_group"."id" IS '数据主键ID';
COMMENT ON COLUMN "ytora"."sys_data_scope_group"."create_by" IS '数据创建人';
COMMENT ON COLUMN "ytora"."sys_data_scope_group"."create_time" IS '数据创建日期';
COMMENT ON COLUMN "ytora"."sys_data_scope_group"."update_by" IS '数据修改人';
COMMENT ON COLUMN "ytora"."sys_data_scope_group"."update_time" IS '数据修改日期';
COMMENT ON COLUMN "ytora"."sys_data_scope_group"."depart_code" IS '数据创建人所属部门';
COMMENT ON COLUMN "ytora"."sys_data_scope_group"."remark" IS '数据备注';
COMMENT ON COLUMN "ytora"."sys_data_scope_group"."permission_id" IS '所属的资源ID';
COMMENT ON COLUMN "ytora"."sys_data_scope_group"."name" IS '分组名称';
COMMENT ON COLUMN "ytora"."sys_data_scope_group"."code" IS '分组编码';
COMMENT ON TABLE "ytora"."sys_data_scope_group" IS '数据范围组';

-- ----------------------------
-- Records of sys_data_scope_group
-- ----------------------------

-- ----------------------------
-- Table structure for sys_depart
-- ----------------------------
DROP TABLE IF EXISTS "ytora"."sys_depart";
CREATE TABLE "ytora"."sys_depart" (
  "id" int8 NOT NULL,
  "create_by" varchar(255) COLLATE "pg_catalog"."default",
  "create_time" timestamp(6),
  "update_by" varchar(255) COLLATE "pg_catalog"."default",
  "update_time" timestamp(6),
  "depart_code" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "remark" varchar(255) COLLATE "pg_catalog"."default",
  "pid" int8 NOT NULL,
  "depart_name" varchar(255) COLLATE "pg_catalog"."default",
  "type" varchar(255) COLLATE "pg_catalog"."default",
  "contact_id" varchar(255) COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "ytora"."sys_depart"."id" IS '数据主键ID';
COMMENT ON COLUMN "ytora"."sys_depart"."create_by" IS '数据创建人';
COMMENT ON COLUMN "ytora"."sys_depart"."create_time" IS '数据创建日期';
COMMENT ON COLUMN "ytora"."sys_depart"."update_by" IS '数据修改人';
COMMENT ON COLUMN "ytora"."sys_depart"."update_time" IS '数据修改日期';
COMMENT ON COLUMN "ytora"."sys_depart"."depart_code" IS '部门编码';
COMMENT ON COLUMN "ytora"."sys_depart"."remark" IS '备注';
COMMENT ON COLUMN "ytora"."sys_depart"."pid" IS '上级部门id';
COMMENT ON COLUMN "ytora"."sys_depart"."depart_name" IS '部门名称';
COMMENT ON COLUMN "ytora"."sys_depart"."type" IS '部门类型';
COMMENT ON COLUMN "ytora"."sys_depart"."contact_id" IS '部门联系人ID';
COMMENT ON TABLE "ytora"."sys_depart" IS '部门表';

-- ----------------------------
-- Records of sys_depart
-- ----------------------------
INSERT INTO "ytora"."sys_depart" VALUES (724045561724928, 'ytora', '2026-05-04 20:54:18.500842', 'ytora', '2026-05-04 20:54:18.501834', 'A01', NULL, 0, '安布雷拉（保护伞）制药', 'ROOT', '2');
INSERT INTO "ytora"."sys_depart" VALUES (724053956427776, 'ytora', '2026-05-04 20:56:26.591354', 'ytora', '2026-05-04 20:56:26.591354', 'B01', NULL, 0, '星环集团', 'ROOT', '1');
INSERT INTO "ytora"."sys_depart" VALUES (724100000000001, 'ytora', '2026-05-04 21:00:53.854907', 'ytora', '2026-05-04 21:00:53.854907', 'A01-01', 'Umbrella Pharmaceuticals，对外制药业务主体', 724045561724928, 'Umbrella Pharmaceuticals', 'CENTER', NULL);
INSERT INTO "ytora"."sys_depart" VALUES (724100000000002, 'ytora', '2026-05-04 21:00:53.854907', 'ytora', '2026-05-04 21:00:53.854907', 'A01-01-01', '疫苗、药物与公开医学研究', 724100000000001, '制药研究部', 'DEPT', NULL);
INSERT INTO "ytora"."sys_depart" VALUES (724100000000003, 'ytora', '2026-05-04 21:00:53.854907', 'ytora', '2026-05-04 21:00:53.854907', 'A01-01-02', '药品生产与临床资料管理', 724100000000001, '药品生产部', 'DEPT', NULL);
INSERT INTO "ytora"."sys_depart" VALUES (724100000000010, 'ytora', '2026-05-04 21:00:53.854907', 'ytora', '2026-05-04 21:00:53.854907', 'A01-02', '阿克雷山区地下研究设施', 724045561724928, '阿克雷研究所', 'LAB', NULL);
INSERT INTO "ytora"."sys_depart" VALUES (724100000000011, 'ytora', '2026-05-04 21:00:53.854907', 'ytora', '2026-05-04 21:00:53.854907', 'A01-02-01', 'T病毒相关研究', 724100000000010, 'T病毒研究室', 'LAB', NULL);
INSERT INTO "ytora"."sys_depart" VALUES (724100000000012, 'ytora', '2026-05-04 21:00:53.854907', 'ytora', '2026-05-04 21:00:53.854907', 'A01-02-02', 'B.O.W.实验与观察', 724100000000010, 'B.O.W.实验室', 'LAB', NULL);
INSERT INTO "ytora"."sys_depart" VALUES (724100000000013, 'ytora', '2026-05-04 21:00:53.854907', 'ytora', '2026-05-04 21:00:53.854907', 'A01-02-03', '实验样本封存与流转', 724100000000010, '样本保管室', 'LAB', NULL);
INSERT INTO "ytora"."sys_depart" VALUES (724100000000020, 'ytora', '2026-05-04 21:00:53.854907', 'ytora', '2026-05-04 21:00:53.854907', 'A01-03', '浣熊市地下研究设施', 724045561724928, 'NEST 地下实验室', 'LAB', NULL);
INSERT INTO "ytora"."sys_depart" VALUES (724100000000021, 'ytora', '2026-05-04 21:00:53.854907', 'ytora', '2026-05-04 21:00:53.854907', 'A01-03-01', 'G病毒相关研究', 724100000000020, 'G病毒研究室', 'LAB', NULL);
INSERT INTO "ytora"."sys_depart" VALUES (724100000000022, 'ytora', '2026-05-04 21:00:53.854907', 'ytora', '2026-05-04 21:00:53.854907', 'A01-03-02', '生化兵器开发与改造', 724100000000020, 'B.O.W.开发区', 'LAB', NULL);
INSERT INTO "ytora"."sys_depart" VALUES (724100000000023, 'ytora', '2026-05-04 21:00:53.854907', 'ytora', '2026-05-04 21:00:53.854907', 'A01-03-03', '地下实验区安保与隔离', 724100000000020, '实验区管制室', 'UNIT', NULL);
INSERT INTO "ytora"."sys_depart" VALUES (724100000000030, 'ytora', '2026-05-04 21:00:53.854907', 'ytora', '2026-05-04 21:00:53.854907', 'A01-04', 'Umbrella Biohazard Countermeasure Service', 724045561724928, 'U.B.C.S.', 'UNIT', NULL);
INSERT INTO "ytora"."sys_depart" VALUES (724100000000031, 'ytora', '2026-05-04 21:00:53.854907', 'ytora', '2026-05-04 21:00:53.854907', 'A01-04-01', '城市生化事件应急投入部队', 724100000000030, 'Alpha Platoon', 'TEAM', NULL);
INSERT INTO "ytora"."sys_depart" VALUES (724100000000032, 'ytora', '2026-05-04 21:00:53.854907', 'ytora', '2026-05-04 21:00:53.854907', 'A01-04-02', '城市生化事件应急投入部队', 724100000000030, 'Bravo Platoon', 'TEAM', NULL);
INSERT INTO "ytora"."sys_depart" VALUES (724100000000033, 'ytora', '2026-05-04 21:00:53.854907', 'ytora', '2026-05-04 21:00:53.854907', 'A01-04-03', '城市生化事件应急投入部队', 724100000000030, 'Delta Platoon', 'TEAM', NULL);
INSERT INTO "ytora"."sys_depart" VALUES (724100000000040, 'ytora', '2026-05-04 21:00:53.854907', 'ytora', '2026-05-04 21:00:53.854907', 'A01-05', 'Umbrella Security Service', 724045561724928, 'U.S.S.', 'UNIT', NULL);
INSERT INTO "ytora"."sys_depart" VALUES (724100000000041, 'ytora', '2026-05-04 21:00:53.854907', 'ytora', '2026-05-04 21:00:53.854907', 'A01-05-01', 'U.S.S.特殊行动小队', 724100000000040, 'Alpha Team', 'TEAM', NULL);
INSERT INTO "ytora"."sys_depart" VALUES (724100000000042, 'ytora', '2026-05-04 21:00:53.854907', 'ytora', '2026-05-04 21:00:53.854907', 'A01-05-02', '情报回收与机密任务执行', 724100000000040, '情报回收小队', 'TEAM', NULL);
INSERT INTO "ytora"."sys_depart" VALUES (724100000000043, 'ytora', '2026-05-04 21:00:53.854907', 'ytora', '2026-05-04 21:00:53.854907', 'A01-05-03', '高危样本回收与处置', 724100000000040, '样本回收小队', 'TEAM', NULL);
INSERT INTO "ytora"."sys_depart" VALUES (724100000000050, 'ytora', '2026-05-04 21:00:53.854907', 'ytora', '2026-05-04 21:00:53.854907', 'A01-06', 'Umbrella Medical Equipment', 724045561724928, 'Umbrella Medical Equipment', 'CENTER', NULL);
INSERT INTO "ytora"."sys_depart" VALUES (724100000000051, 'ytora', '2026-05-04 21:00:53.854907', 'ytora', '2026-05-04 21:00:53.854907', 'A01-06-01', '医疗设备与实验设备制造', 724100000000050, '医疗设备制造部', 'DEPT', NULL);
INSERT INTO "ytora"."sys_depart" VALUES (724100000000052, 'ytora', '2026-05-04 21:00:53.854907', 'ytora', '2026-05-04 21:00:53.854907', 'A01-06-02', '实验设备维护与技术支持', 724100000000050, '设备维护部', 'DEPT', NULL);
INSERT INTO "ytora"."sys_depart" VALUES (724100000000060, 'ytora', '2026-05-04 21:00:53.854907', 'ytora', '2026-05-04 21:00:53.854907', 'A01-07', 'Umbrella Industries', 724045561724928, 'Umbrella Industries', 'CENTER', NULL);
INSERT INTO "ytora"."sys_depart" VALUES (724100000000061, 'ytora', '2026-05-04 21:00:53.854907', 'ytora', '2026-05-04 21:00:53.854907', 'A01-07-01', '工业化生产与特殊设备制造', 724100000000060, '工业制造部', 'DEPT', NULL);
INSERT INTO "ytora"."sys_depart" VALUES (724100000000062, 'ytora', '2026-05-04 21:00:53.854907', 'ytora', '2026-05-04 21:00:53.854907', 'A01-07-02', '特殊物资运输与仓储', 724100000000060, '特殊物流部', 'DEPT', NULL);
INSERT INTO "ytora"."sys_depart" VALUES (724200000000001, 'ytora', '2026-05-04 21:00:53.854907', 'ytora', '2026-05-04 21:00:53.854907', 'B01-01', '程心创办并掌管的公司主体', 724053956427776, '星环公司', 'CENTER', NULL);
INSERT INTO "ytora"."sys_depart" VALUES (724200000000002, 'ytora', '2026-05-04 21:00:53.854907', 'ytora', '2026-05-04 21:00:53.854907', 'B01-01-01', '程心、AA相关经营管理事务', 724200000000001, '总裁办公室', 'DEPT', NULL);
INSERT INTO "ytora"."sys_depart" VALUES (724200000000003, 'ytora', '2026-05-04 21:00:53.854907', 'ytora', '2026-05-04 21:00:53.854907', 'B01-01-02', '维德接管时期的项目推进机构', 724200000000001, '执行委员会', 'DEPT', NULL);
INSERT INTO "ytora"."sys_depart" VALUES (724200000000010, 'ytora', '2026-05-04 21:00:53.854907', 'ytora', '2026-05-04 21:00:53.854907', 'B01-02', '光速飞船研究主线项目', 724053956427776, '光速飞船研究中心', 'CENTER', NULL);
INSERT INTO "ytora"."sys_depart" VALUES (724200000000011, 'ytora', '2026-05-04 21:00:53.854907', 'ytora', '2026-05-04 21:00:53.854907', 'B01-02-01', '曲率驱动相关研究', 724200000000010, '曲率驱动实验室', 'LAB', NULL);
INSERT INTO "ytora"."sys_depart" VALUES (724200000000012, 'ytora', '2026-05-04 21:00:53.854907', 'ytora', '2026-05-04 21:00:53.854907', 'B01-02-02', '光速飞船总体方案设计', 724200000000010, '飞船总体设计室', 'LAB', NULL);
INSERT INTO "ytora"."sys_depart" VALUES (724200000000013, 'ytora', '2026-05-04 21:00:53.854907', 'ytora', '2026-05-04 21:00:53.854907', 'B01-02-03', '飞船航行、生命维持与长期生存系统', 724200000000010, '飞船生命系统室', 'LAB', NULL);
INSERT INTO "ytora"."sys_depart" VALUES (724200000000020, 'ytora', '2026-05-04 21:00:53.854907', 'ytora', '2026-05-04 21:00:53.854907', 'B01-03', '木星背后的星环城相关设施', 724053956427776, '星环城', 'BASE', NULL);
INSERT INTO "ytora"."sys_depart" VALUES (724200000000021, 'ytora', '2026-05-04 21:00:53.854907', 'ytora', '2026-05-04 21:00:53.854907', 'B01-03-01', '星环城工程建造与维护', 724200000000020, '星环城工程部', 'DEPT', NULL);
INSERT INTO "ytora"."sys_depart" VALUES (724200000000022, 'ytora', '2026-05-04 21:00:53.854907', 'ytora', '2026-05-04 21:00:53.854907', 'B01-03-02', '星环城内部运行保障', 724200000000020, '星环城运行部', 'DEPT', NULL);
INSERT INTO "ytora"."sys_depart" VALUES (724200000000023, 'ytora', '2026-05-04 21:00:53.854907', 'ytora', '2026-05-04 21:00:53.854907', 'B01-03-03', '星环城防御与对抗时期武装力量', 724200000000020, '星环城防卫部', 'UNIT', NULL);
INSERT INTO "ytora"."sys_depart" VALUES (724200000000030, 'ytora', '2026-05-04 21:00:53.854907', 'ytora', '2026-05-04 21:00:53.854907', 'B01-04', 'DX3906星系及恒星资产相关事务', 724053956427776, 'DX3906 资产管理处', 'DEPT', NULL);
INSERT INTO "ytora"."sys_depart" VALUES (724200000000031, 'ytora', '2026-05-04 21:00:53.854907', 'ytora', '2026-05-04 21:00:53.854907', 'B01-04-01', 'DX3906恒星权益管理', 724200000000030, '恒星资产管理室', 'DEPT', NULL);
INSERT INTO "ytora"."sys_depart" VALUES (724200000000032, 'ytora', '2026-05-04 21:00:53.854907', 'ytora', '2026-05-04 21:00:53.854907', 'B01-04-02', '行星所有权交割与资料归档', 724200000000030, '行星资产档案室', 'DEPT', NULL);
INSERT INTO "ytora"."sys_depart" VALUES (724200000000040, 'ytora', '2026-05-04 21:00:53.854907', 'ytora', '2026-05-04 21:00:53.854907', 'B01-05', '星环号飞船相关设施', 724053956427776, '星环号项目组', 'UNIT', NULL);
INSERT INTO "ytora"."sys_depart" VALUES (724200000000041, 'ytora', '2026-05-04 21:00:53.854907', 'ytora', '2026-05-04 21:00:53.854907', 'B01-05-01', '星环号飞船维护与调度', 724200000000040, '星环号维护组', 'TEAM', NULL);
INSERT INTO "ytora"."sys_depart" VALUES (724200000000042, 'ytora', '2026-05-04 21:00:53.854907', 'ytora', '2026-05-04 21:00:53.854907', 'B01-05-02', '星环号货运舱与文物转运相关事务', 724200000000040, '货运舱管理组', 'TEAM', NULL);
INSERT INTO "ytora"."sys_depart" VALUES (724200000000050, 'ytora', '2026-05-04 21:00:53.854907', 'ytora', '2026-05-04 21:00:53.854907', 'B01-06', '程心、AA后期基地维持相关事务', 724053956427776, '基地维持处', 'DEPT', NULL);
INSERT INTO "ytora"."sys_depart" VALUES (724200000000051, 'ytora', '2026-05-04 21:00:53.854907', 'ytora', '2026-05-04 21:00:53.854907', 'B01-06-01', '基地能源、环境与设备维护', 724200000000050, '基地运行组', 'TEAM', NULL);
INSERT INTO "ytora"."sys_depart" VALUES (724200000000052, 'ytora', '2026-05-04 21:00:53.854907', 'ytora', '2026-05-04 21:00:53.854907', 'B01-06-02', '资料、文物与历史记录保存', 724200000000050, '文明档案组', 'TEAM', NULL);

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS "ytora"."sys_dict";
CREATE TABLE "ytora"."sys_dict" (
  "id" int8 NOT NULL,
  "create_by" varchar(255) COLLATE "pg_catalog"."default",
  "create_time" timestamp(6),
  "update_by" varchar(255) COLLATE "pg_catalog"."default",
  "update_time" timestamp(6),
  "depart_code" varchar(255) COLLATE "pg_catalog"."default",
  "remark" varchar(255) COLLATE "pg_catalog"."default",
  "dict_name" varchar(255) COLLATE "pg_catalog"."default",
  "dict_code" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "index" int4
)
;
COMMENT ON COLUMN "ytora"."sys_dict"."id" IS '数据主键ID';
COMMENT ON COLUMN "ytora"."sys_dict"."create_by" IS '数据创建人';
COMMENT ON COLUMN "ytora"."sys_dict"."create_time" IS '数据创建日期';
COMMENT ON COLUMN "ytora"."sys_dict"."update_by" IS '数据修改人';
COMMENT ON COLUMN "ytora"."sys_dict"."update_time" IS '数据修改日期';
COMMENT ON COLUMN "ytora"."sys_dict"."depart_code" IS '数据创建人所属部门';
COMMENT ON COLUMN "ytora"."sys_dict"."dict_name" IS '字典名称';
COMMENT ON COLUMN "ytora"."sys_dict"."dict_code" IS '字典编码';
COMMENT ON COLUMN "ytora"."sys_dict"."index" IS '字典项排序';
COMMENT ON TABLE "ytora"."sys_dict" IS '字典表';

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO "ytora"."sys_dict" VALUES (1002, NULL, NULL, NULL, NULL, NULL, NULL, '证件类型', 'id_card_type', 2);
INSERT INTO "ytora"."sys_dict" VALUES (1003, NULL, NULL, NULL, NULL, NULL, NULL, '婚姻状态', 'marital_status', 3);
INSERT INTO "ytora"."sys_dict" VALUES (1004, NULL, NULL, NULL, NULL, NULL, NULL, '民族', 'nation_cn', 4);
INSERT INTO "ytora"."sys_dict" VALUES (1005, NULL, NULL, NULL, NULL, NULL, NULL, '国家地区', 'country_code', 5);
INSERT INTO "ytora"."sys_dict" VALUES (1006, NULL, NULL, NULL, NULL, NULL, NULL, '政治面貌', 'politics_status', 6);
INSERT INTO "ytora"."sys_dict" VALUES (1007, NULL, NULL, NULL, NULL, NULL, NULL, '学历', 'education_level', 7);
INSERT INTO "ytora"."sys_dict" VALUES (1008, NULL, NULL, NULL, NULL, NULL, NULL, '血型', 'blood_type', 8);
INSERT INTO "ytora"."sys_dict" VALUES (1009, NULL, NULL, NULL, NULL, NULL, NULL, '是否', 'yes_no', 9);
INSERT INTO "ytora"."sys_dict" VALUES (1010, NULL, NULL, NULL, NULL, NULL, NULL, '启用状态', 'enable_status', 10);
INSERT INTO "ytora"."sys_dict" VALUES (1011, NULL, NULL, NULL, NULL, NULL, NULL, '用户状态', 'user_status', 11);
INSERT INTO "ytora"."sys_dict" VALUES (1012, NULL, NULL, NULL, NULL, NULL, NULL, '通知级别', 'notice_level', 12);
INSERT INTO "ytora"."sys_dict" VALUES (1013, NULL, NULL, NULL, NULL, NULL, NULL, '文件类型', 'file_type', 13);
INSERT INTO "ytora"."sys_dict" VALUES (1014, NULL, NULL, NULL, NULL, NULL, NULL, '设备类型', 'device_type', 14);
INSERT INTO "ytora"."sys_dict" VALUES (1015, NULL, NULL, NULL, NULL, NULL, NULL, '日志级别', 'log_level', 15);
INSERT INTO "ytora"."sys_dict" VALUES (1001, NULL, NULL, NULL, '2026-05-02 00:34:15.158855', NULL, '123', '性别', 'gender', 1);
INSERT INTO "ytora"."sys_dict" VALUES (720076110495744, 'ytora', '2026-05-04 04:04:49.47967', 'ytora', '2026-05-04 04:04:49.47967', '707063588388864', NULL, '数据范围类型', 'DATA_SCOPE', NULL);

-- ----------------------------
-- Table structure for sys_dict_item
-- ----------------------------
DROP TABLE IF EXISTS "ytora"."sys_dict_item";
CREATE TABLE "ytora"."sys_dict_item" (
  "id" int8 NOT NULL,
  "create_by" varchar(255) COLLATE "pg_catalog"."default",
  "create_time" timestamp(6),
  "update_by" varchar(255) COLLATE "pg_catalog"."default",
  "update_time" timestamp(6),
  "depart_code" varchar(255) COLLATE "pg_catalog"."default",
  "remark" varchar(255) COLLATE "pg_catalog"."default",
  "dict_code" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "item_value" varchar(255) COLLATE "pg_catalog"."default",
  "item_text" varchar(255) COLLATE "pg_catalog"."default",
  "index" int4,
  "color" varchar(255) COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "ytora"."sys_dict_item"."id" IS '数据主键ID';
COMMENT ON COLUMN "ytora"."sys_dict_item"."create_by" IS '数据创建人';
COMMENT ON COLUMN "ytora"."sys_dict_item"."create_time" IS '数据创建日期';
COMMENT ON COLUMN "ytora"."sys_dict_item"."update_by" IS '数据修改人';
COMMENT ON COLUMN "ytora"."sys_dict_item"."update_time" IS '数据修改日期';
COMMENT ON COLUMN "ytora"."sys_dict_item"."depart_code" IS '数据创建人所属部门';
COMMENT ON COLUMN "ytora"."sys_dict_item"."dict_code" IS '字典编码';
COMMENT ON COLUMN "ytora"."sys_dict_item"."item_value" IS '字典项值';
COMMENT ON COLUMN "ytora"."sys_dict_item"."item_text" IS '字典项文本';
COMMENT ON COLUMN "ytora"."sys_dict_item"."index" IS '字典项排序';
COMMENT ON COLUMN "ytora"."sys_dict_item"."color" IS '字典项颜色';
COMMENT ON TABLE "ytora"."sys_dict_item" IS '字典项表';

-- ----------------------------
-- Records of sys_dict_item
-- ----------------------------
INSERT INTO "ytora"."sys_dict_item" VALUES (200001, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '1', '男', 1, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200002, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '2', '女', 2, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200003, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '3', '未知', 3, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200004, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '4', '不愿透露', 4, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200005, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '5', '非二元性别', 5, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200006, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '6', '跨性别男性', 6, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200007, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '7', '跨性别女性', 7, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200008, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '8', '双性别', 8, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200009, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '9', '无性别', 9, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200010, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '10', '性别流动', 10, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200011, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '11', '泛性别', 11, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200012, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '12', '半性别', 12, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200013, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '13', '半女性', 13, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200014, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '14', '半男性', 14, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200015, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '15', '中性', 15, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200016, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '16', '第三性别', 16, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200017, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '17', '双灵人', 17, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200018, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '18', '酷儿性别', 18, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200019, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '19', '疑性别', 19, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200020, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '20', '性别不符', 20, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200021, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '21', '男性化女性', 21, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200022, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '22', '女性化男性', 22, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200023, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '23', '变性男性', 23, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200024, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '24', '变性女性', 24, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200025, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '25', '跨性别者', 25, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200026, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '26', '间性人', 26, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200027, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '27', '雄雌同体', 27, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200028, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '28', '中间性别', 28, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200029, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '29', '多性别', 29, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200030, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '30', '灰性别', 30, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200031, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '31', '大性别', 31, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200032, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '32', '小性别', 32, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200033, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '33', '性别空白', 33, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200034, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '34', '性别中立', 34, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200035, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '35', '性别模糊', 35, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200036, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '36', '性别独立', 36, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200037, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '37', '性别扩展', 37, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200038, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '38', '性别多元', 38, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200039, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '39', '性别开放', 39, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200040, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '40', '性别质疑', 40, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200041, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '41', '性别创造者', 41, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200042, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '42', '性别混合', 42, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200043, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '43', '性别变体', 43, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200044, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '44', '性别非从众', 44, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200045, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '45', '性别非规范', 45, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200046, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '46', '性别游离', 46, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200047, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '47', '性别无关', 47, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200048, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '48', '性别不可知', 48, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200049, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '49', '性别中性者', 49, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200050, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '50', '性别二元外', 50, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200051, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '51', '女性非二元', 51, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200052, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '52', '男性非二元', 52, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200053, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '53', '女性倾向非二元', 53, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200054, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '54', '男性倾向非二元', 54, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200055, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '55', '无性别女性', 55, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200056, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '56', '无性别男性', 56, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200057, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '57', '流动女性', 57, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200058, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '58', '流动男性', 58, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200059, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '59', '泛性别女性', 59, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200060, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '60', '泛性别男性', 60, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200061, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '61', '双性别女性', 61, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200062, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '62', '双性别男性', 62, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200063, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '63', '多元女性', 63, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200064, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '64', '多元男性', 64, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200065, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '65', '中性女性', 65, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200066, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '66', '中性男性', 66, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200067, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '67', '跨女性非二元', 67, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200068, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '68', '跨男性非二元', 68, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200069, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '69', '跨非二元者', 69, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200070, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '70', '跨性别非二元', 70, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200071, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '71', '间性非二元', 71, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200072, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '72', '间性女性', 72, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200073, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '73', '间性男性', 73, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200074, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '74', '社会性别不明', 74, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200075, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '75', '生理性别不明', 75, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200076, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '76', '出生指定男性', 76, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200077, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '77', '出生指定女性', 77, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200078, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '78', '出生指定间性', 78, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200079, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '79', '法定性别男', 79, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200080, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '80', '法定性别女', 80, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200081, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '81', '法定性别其他', 81, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200082, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '82', '自我认同为男性', 82, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200083, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '83', '自我认同为女性', 83, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200084, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '84', '自我认同为非二元', 84, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200085, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '85', '自我认同为跨性别', 85, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200086, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '86', '自我认同为间性', 86, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200087, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '87', '自我认同为无性别', 87, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200088, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '88', '自我认同为流动性别', 88, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200089, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '89', '自我认同为酷儿性别', 89, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200090, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '90', '自定义性别', 90, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200091, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '91', '其他性别', 91, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200092, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '92', '未指定', 92, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200093, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '93', '不可用', 93, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200094, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '94', '数据缺失', 94, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200095, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '95', '保密', 95, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200096, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '96', '待确认', 96, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (200097, NULL, NULL, NULL, NULL, NULL, NULL, 'gender', '97', '不适用', 97, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (210002, NULL, NULL, NULL, NULL, NULL, NULL, 'id_card_type', 'PASSPORT', '护照', 2, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (210003, NULL, NULL, NULL, NULL, NULL, NULL, 'id_card_type', 'HK_MACAO_PASS', '港澳居民来往内地通行证', 3, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (210004, NULL, NULL, NULL, NULL, NULL, NULL, 'id_card_type', 'TAIWAN_PASS', '台湾居民来往大陆通行证', 4, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (210005, NULL, NULL, NULL, NULL, NULL, NULL, 'id_card_type', 'FOREIGN_PERMANENT_RESIDENCE', '外国人永久居留身份证', 5, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (210006, NULL, NULL, NULL, NULL, NULL, NULL, 'id_card_type', 'DRIVER_LICENSE', '驾驶证', 6, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (210007, NULL, NULL, NULL, NULL, NULL, NULL, 'id_card_type', 'OFFICER_CARD', '军官证', 7, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (210008, NULL, NULL, NULL, NULL, NULL, NULL, 'id_card_type', 'SOLDIER_CARD', '士兵证', 8, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (210009, NULL, NULL, NULL, NULL, NULL, NULL, 'id_card_type', 'POLICE_CARD', '警官证', 9, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (210010, NULL, NULL, NULL, NULL, NULL, NULL, 'id_card_type', 'HOUSEHOLD_REGISTER', '户口簿', 10, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (210011, NULL, NULL, NULL, NULL, NULL, NULL, 'id_card_type', 'BIRTH_CERTIFICATE', '出生证明', 11, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (210012, NULL, NULL, NULL, NULL, NULL, NULL, 'id_card_type', 'OTHER', '其他', 12, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (220001, NULL, NULL, NULL, NULL, NULL, NULL, 'marital_status', 'UNMARRIED', '未婚', 1, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (220002, NULL, NULL, NULL, NULL, NULL, NULL, 'marital_status', 'MARRIED', '已婚', 2, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (220003, NULL, NULL, NULL, NULL, NULL, NULL, 'marital_status', 'DIVORCED', '离异', 3, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (220004, NULL, NULL, NULL, NULL, NULL, NULL, 'marital_status', 'WIDOWED', '丧偶', 4, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (220005, NULL, NULL, NULL, NULL, NULL, NULL, 'marital_status', 'REMARRIED', '再婚', 5, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (220006, NULL, NULL, NULL, NULL, NULL, NULL, 'marital_status', 'SEPARATED', '分居', 6, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (220007, NULL, NULL, NULL, NULL, NULL, NULL, 'marital_status', 'SECRET', '保密', 7, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (230001, NULL, NULL, NULL, NULL, NULL, NULL, 'nation_cn', '1', '汉族', 1, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (230002, NULL, NULL, NULL, NULL, NULL, NULL, 'nation_cn', '2', '蒙古族', 2, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (230003, NULL, NULL, NULL, NULL, NULL, NULL, 'nation_cn', '3', '回族', 3, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (230004, NULL, NULL, NULL, NULL, NULL, NULL, 'nation_cn', '4', '藏族', 4, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (230005, NULL, NULL, NULL, NULL, NULL, NULL, 'nation_cn', '5', '维吾尔族', 5, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (230006, NULL, NULL, NULL, NULL, NULL, NULL, 'nation_cn', '6', '苗族', 6, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (230007, NULL, NULL, NULL, NULL, NULL, NULL, 'nation_cn', '7', '彝族', 7, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (230008, NULL, NULL, NULL, NULL, NULL, NULL, 'nation_cn', '8', '壮族', 8, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (230009, NULL, NULL, NULL, NULL, NULL, NULL, 'nation_cn', '9', '布依族', 9, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (230010, NULL, NULL, NULL, NULL, NULL, NULL, 'nation_cn', '10', '朝鲜族', 10, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (230011, NULL, NULL, NULL, NULL, NULL, NULL, 'nation_cn', '11', '满族', 11, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (230012, NULL, NULL, NULL, NULL, NULL, NULL, 'nation_cn', '12', '侗族', 12, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (230013, NULL, NULL, NULL, NULL, NULL, NULL, 'nation_cn', '13', '瑶族', 13, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (230014, NULL, NULL, NULL, NULL, NULL, NULL, 'nation_cn', '14', '白族', 14, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (230015, NULL, NULL, NULL, NULL, NULL, NULL, 'nation_cn', '15', '土家族', 15, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (230016, NULL, NULL, NULL, NULL, NULL, NULL, 'nation_cn', '16', '哈尼族', 16, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (230017, NULL, NULL, NULL, NULL, NULL, NULL, 'nation_cn', '17', '哈萨克族', 17, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (230018, NULL, NULL, NULL, NULL, NULL, NULL, 'nation_cn', '18', '傣族', 18, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (230019, NULL, NULL, NULL, NULL, NULL, NULL, 'nation_cn', '19', '黎族', 19, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (230020, NULL, NULL, NULL, NULL, NULL, NULL, 'nation_cn', '20', '傈僳族', 20, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (230021, NULL, NULL, NULL, NULL, NULL, NULL, 'nation_cn', '21', '佤族', 21, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (230022, NULL, NULL, NULL, NULL, NULL, NULL, 'nation_cn', '22', '畲族', 22, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (230023, NULL, NULL, NULL, NULL, NULL, NULL, 'nation_cn', '23', '高山族', 23, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (230024, NULL, NULL, NULL, NULL, NULL, NULL, 'nation_cn', '24', '拉祜族', 24, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (230025, NULL, NULL, NULL, NULL, NULL, NULL, 'nation_cn', '25', '水族', 25, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (230026, NULL, NULL, NULL, NULL, NULL, NULL, 'nation_cn', '26', '东乡族', 26, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (230027, NULL, NULL, NULL, NULL, NULL, NULL, 'nation_cn', '27', '纳西族', 27, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (230028, NULL, NULL, NULL, NULL, NULL, NULL, 'nation_cn', '28', '景颇族', 28, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (230029, NULL, NULL, NULL, NULL, NULL, NULL, 'nation_cn', '29', '柯尔克孜族', 29, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (230030, NULL, NULL, NULL, NULL, NULL, NULL, 'nation_cn', '30', '土族', 30, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (230031, NULL, NULL, NULL, NULL, NULL, NULL, 'nation_cn', '31', '达斡尔族', 31, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (230032, NULL, NULL, NULL, NULL, NULL, NULL, 'nation_cn', '32', '仫佬族', 32, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (230033, NULL, NULL, NULL, NULL, NULL, NULL, 'nation_cn', '33', '羌族', 33, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (230034, NULL, NULL, NULL, NULL, NULL, NULL, 'nation_cn', '34', '布朗族', 34, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (230035, NULL, NULL, NULL, NULL, NULL, NULL, 'nation_cn', '35', '撒拉族', 35, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (230036, NULL, NULL, NULL, NULL, NULL, NULL, 'nation_cn', '36', '毛南族', 36, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (230037, NULL, NULL, NULL, NULL, NULL, NULL, 'nation_cn', '37', '仡佬族', 37, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (230038, NULL, NULL, NULL, NULL, NULL, NULL, 'nation_cn', '38', '锡伯族', 38, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (230039, NULL, NULL, NULL, NULL, NULL, NULL, 'nation_cn', '39', '阿昌族', 39, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (230040, NULL, NULL, NULL, NULL, NULL, NULL, 'nation_cn', '40', '普米族', 40, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (230041, NULL, NULL, NULL, NULL, NULL, NULL, 'nation_cn', '41', '塔吉克族', 41, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (230042, NULL, NULL, NULL, NULL, NULL, NULL, 'nation_cn', '42', '怒族', 42, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (230043, NULL, NULL, NULL, NULL, NULL, NULL, 'nation_cn', '43', '乌孜别克族', 43, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (230044, NULL, NULL, NULL, NULL, NULL, NULL, 'nation_cn', '44', '俄罗斯族', 44, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (230045, NULL, NULL, NULL, NULL, NULL, NULL, 'nation_cn', '45', '鄂温克族', 45, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (230046, NULL, NULL, NULL, NULL, NULL, NULL, 'nation_cn', '46', '德昂族', 46, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (230047, NULL, NULL, NULL, NULL, NULL, NULL, 'nation_cn', '47', '保安族', 47, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (230048, NULL, NULL, NULL, NULL, NULL, NULL, 'nation_cn', '48', '裕固族', 48, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (230049, NULL, NULL, NULL, NULL, NULL, NULL, 'nation_cn', '49', '京族', 49, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (230050, NULL, NULL, NULL, NULL, NULL, NULL, 'nation_cn', '50', '塔塔尔族', 50, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (230051, NULL, NULL, NULL, NULL, NULL, NULL, 'nation_cn', '51', '独龙族', 51, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (230052, NULL, NULL, NULL, NULL, NULL, NULL, 'nation_cn', '52', '鄂伦春族', 52, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (230053, NULL, NULL, NULL, NULL, NULL, NULL, 'nation_cn', '53', '赫哲族', 53, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (230054, NULL, NULL, NULL, NULL, NULL, NULL, 'nation_cn', '54', '门巴族', 54, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (230055, NULL, NULL, NULL, NULL, NULL, NULL, 'nation_cn', '55', '珞巴族', 55, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (230056, NULL, NULL, NULL, NULL, NULL, NULL, 'nation_cn', '56', '基诺族', 56, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (240001, NULL, NULL, NULL, NULL, NULL, NULL, 'country_code', 'CN', '中国', 1, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (240002, NULL, NULL, NULL, NULL, NULL, NULL, 'country_code', 'US', '美国', 2, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (240003, NULL, NULL, NULL, NULL, NULL, NULL, 'country_code', 'GB', '英国', 3, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (240004, NULL, NULL, NULL, NULL, NULL, NULL, 'country_code', 'FR', '法国', 4, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (240005, NULL, NULL, NULL, NULL, NULL, NULL, 'country_code', 'DE', '德国', 5, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (240006, NULL, NULL, NULL, NULL, NULL, NULL, 'country_code', 'IT', '意大利', 6, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (240007, NULL, NULL, NULL, NULL, NULL, NULL, 'country_code', 'ES', '西班牙', 7, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (240008, NULL, NULL, NULL, NULL, NULL, NULL, 'country_code', 'PT', '葡萄牙', 8, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (240009, NULL, NULL, NULL, NULL, NULL, NULL, 'country_code', 'NL', '荷兰', 9, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (240010, NULL, NULL, NULL, NULL, NULL, NULL, 'country_code', 'BE', '比利时', 10, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (240011, NULL, NULL, NULL, NULL, NULL, NULL, 'country_code', 'CH', '瑞士', 11, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (240012, NULL, NULL, NULL, NULL, NULL, NULL, 'country_code', 'SE', '瑞典', 12, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (240013, NULL, NULL, NULL, NULL, NULL, NULL, 'country_code', 'NO', '挪威', 13, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (240014, NULL, NULL, NULL, NULL, NULL, NULL, 'country_code', 'DK', '丹麦', 14, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (240015, NULL, NULL, NULL, NULL, NULL, NULL, 'country_code', 'FI', '芬兰', 15, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (240016, NULL, NULL, NULL, NULL, NULL, NULL, 'country_code', 'IE', '爱尔兰', 16, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (240017, NULL, NULL, NULL, NULL, NULL, NULL, 'country_code', 'AT', '奥地利', 17, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (240018, NULL, NULL, NULL, NULL, NULL, NULL, 'country_code', 'GR', '希腊', 18, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (240019, NULL, NULL, NULL, NULL, NULL, NULL, 'country_code', 'RU', '俄罗斯', 19, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (240020, NULL, NULL, NULL, NULL, NULL, NULL, 'country_code', 'UA', '乌克兰', 20, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (240021, NULL, NULL, NULL, NULL, NULL, NULL, 'country_code', 'CA', '加拿大', 21, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (240022, NULL, NULL, NULL, NULL, NULL, NULL, 'country_code', 'MX', '墨西哥', 22, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (240023, NULL, NULL, NULL, NULL, NULL, NULL, 'country_code', 'BR', '巴西', 23, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (240024, NULL, NULL, NULL, NULL, NULL, NULL, 'country_code', 'AR', '阿根廷', 24, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (240025, NULL, NULL, NULL, NULL, NULL, NULL, 'country_code', 'CL', '智利', 25, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (240026, NULL, NULL, NULL, NULL, NULL, NULL, 'country_code', 'AU', '澳大利亚', 26, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (240027, NULL, NULL, NULL, NULL, NULL, NULL, 'country_code', 'NZ', '新西兰', 27, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (240028, NULL, NULL, NULL, NULL, NULL, NULL, 'country_code', 'JP', '日本', 28, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (240029, NULL, NULL, NULL, NULL, NULL, NULL, 'country_code', 'KR', '韩国', 29, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (240030, NULL, NULL, NULL, NULL, NULL, NULL, 'country_code', 'SG', '新加坡', 30, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (240031, NULL, NULL, NULL, NULL, NULL, NULL, 'country_code', 'MY', '马来西亚', 31, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (240032, NULL, NULL, NULL, NULL, NULL, NULL, 'country_code', 'TH', '泰国', 32, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (240033, NULL, NULL, NULL, NULL, NULL, NULL, 'country_code', 'VN', '越南', 33, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (240034, NULL, NULL, NULL, NULL, NULL, NULL, 'country_code', 'PH', '菲律宾', 34, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (240035, NULL, NULL, NULL, NULL, NULL, NULL, 'country_code', 'ID', '印度尼西亚', 35, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (240036, NULL, NULL, NULL, NULL, NULL, NULL, 'country_code', 'IN', '印度', 36, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (240037, NULL, NULL, NULL, NULL, NULL, NULL, 'country_code', 'PK', '巴基斯坦', 37, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (240038, NULL, NULL, NULL, NULL, NULL, NULL, 'country_code', 'BD', '孟加拉国', 38, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (240039, NULL, NULL, NULL, NULL, NULL, NULL, 'country_code', 'LK', '斯里兰卡', 39, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (240040, NULL, NULL, NULL, NULL, NULL, NULL, 'country_code', 'NP', '尼泊尔', 40, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (240041, NULL, NULL, NULL, NULL, NULL, NULL, 'country_code', 'AE', '阿联酋', 41, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (240042, NULL, NULL, NULL, NULL, NULL, NULL, 'country_code', 'SA', '沙特阿拉伯', 42, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (240043, NULL, NULL, NULL, NULL, NULL, NULL, 'country_code', 'TR', '土耳其', 43, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (240044, NULL, NULL, NULL, NULL, NULL, NULL, 'country_code', 'IL', '以色列', 44, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (240045, NULL, NULL, NULL, NULL, NULL, NULL, 'country_code', 'EG', '埃及', 45, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (240046, NULL, NULL, NULL, NULL, NULL, NULL, 'country_code', 'ZA', '南非', 46, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (240047, NULL, NULL, NULL, NULL, NULL, NULL, 'country_code', 'NG', '尼日利亚', 47, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (240048, NULL, NULL, NULL, NULL, NULL, NULL, 'country_code', 'KE', '肯尼亚', 48, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (240049, NULL, NULL, NULL, NULL, NULL, NULL, 'country_code', 'MA', '摩洛哥', 49, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (240050, NULL, NULL, NULL, NULL, NULL, NULL, 'country_code', 'HK', '中国香港', 50, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (240051, NULL, NULL, NULL, NULL, NULL, NULL, 'country_code', 'MO', '中国澳门', 51, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (240052, NULL, NULL, NULL, NULL, NULL, NULL, 'country_code', 'TW', '中国台湾', 52, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (250001, NULL, NULL, NULL, NULL, NULL, NULL, 'politics_status', 'CPC_MEMBER', '中共党员', 1, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (250002, NULL, NULL, NULL, NULL, NULL, NULL, 'politics_status', 'CPC_PROBATIONARY_MEMBER', '中共预备党员', 2, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (250003, NULL, NULL, NULL, NULL, NULL, NULL, 'politics_status', 'CYL_MEMBER', '共青团员', 3, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (250004, NULL, NULL, NULL, NULL, NULL, NULL, 'politics_status', 'RCCK_MEMBER', '民革党员', 4, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (250005, NULL, NULL, NULL, NULL, NULL, NULL, 'politics_status', 'CDL_MEMBER', '民盟盟员', 5, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (250006, NULL, NULL, NULL, NULL, NULL, NULL, 'politics_status', 'CNDCA_MEMBER', '民建会员', 6, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (250007, NULL, NULL, NULL, NULL, NULL, NULL, 'politics_status', 'CAPD_MEMBER', '民进会员', 7, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (250008, NULL, NULL, NULL, NULL, NULL, NULL, 'politics_status', 'CPWDP_MEMBER', '农工党党员', 8, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (250009, NULL, NULL, NULL, NULL, NULL, NULL, 'politics_status', 'CZGP_MEMBER', '致公党党员', 9, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (250010, NULL, NULL, NULL, NULL, NULL, NULL, 'politics_status', 'JSD_MEMBER', '九三学社社员', 10, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (250011, NULL, NULL, NULL, NULL, NULL, NULL, 'politics_status', 'TSL_MEMBER', '台盟盟员', 11, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (250012, NULL, NULL, NULL, NULL, NULL, NULL, 'politics_status', 'NON_PARTISAN', '无党派人士', 12, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (250013, NULL, NULL, NULL, NULL, NULL, NULL, 'politics_status', 'MASSES', '群众', 13, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (260001, NULL, NULL, NULL, NULL, NULL, NULL, 'education_level', 'PRIMARY', '小学', 1, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (260002, NULL, NULL, NULL, NULL, NULL, NULL, 'education_level', 'JUNIOR_HIGH', '初中', 2, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (260003, NULL, NULL, NULL, NULL, NULL, NULL, 'education_level', 'SENIOR_HIGH', '高中', 3, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (260004, NULL, NULL, NULL, NULL, NULL, NULL, 'education_level', 'TECHNICAL_SECONDARY', '中专', 4, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (260005, NULL, NULL, NULL, NULL, NULL, NULL, 'education_level', 'VOCATIONAL_HIGH', '职高', 5, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (260006, NULL, NULL, NULL, NULL, NULL, NULL, 'education_level', 'JUNIOR_COLLEGE', '大专', 6, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (260007, NULL, NULL, NULL, NULL, NULL, NULL, 'education_level', 'BACHELOR', '本科', 7, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (260008, NULL, NULL, NULL, NULL, NULL, NULL, 'education_level', 'MASTER', '硕士研究生', 8, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (260009, NULL, NULL, NULL, NULL, NULL, NULL, 'education_level', 'DOCTOR', '博士研究生', 9, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (260010, NULL, NULL, NULL, NULL, NULL, NULL, 'education_level', 'POST_DOCTOR', '博士后', 10, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (260011, NULL, NULL, NULL, NULL, NULL, NULL, 'education_level', 'OTHER', '其他', 11, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (270001, NULL, NULL, NULL, NULL, NULL, NULL, 'blood_type', 'A', 'A型', 1, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (270002, NULL, NULL, NULL, NULL, NULL, NULL, 'blood_type', 'B', 'B型', 2, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (270003, NULL, NULL, NULL, NULL, NULL, NULL, 'blood_type', 'AB', 'AB型', 3, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (270004, NULL, NULL, NULL, NULL, NULL, NULL, 'blood_type', 'O', 'O型', 4, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (270005, NULL, NULL, NULL, NULL, NULL, NULL, 'blood_type', 'RH_POSITIVE', 'Rh阳性', 5, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (270006, NULL, NULL, NULL, NULL, NULL, NULL, 'blood_type', 'RH_NEGATIVE', 'Rh阴性', 6, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (270007, NULL, NULL, NULL, NULL, NULL, NULL, 'blood_type', 'UNKNOWN', '未知', 7, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (280001, NULL, NULL, NULL, NULL, NULL, NULL, 'yes_no', 'Y', '是', 1, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (280002, NULL, NULL, NULL, NULL, NULL, NULL, 'yes_no', 'N', '否', 2, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (290001, NULL, NULL, NULL, NULL, NULL, NULL, 'enable_status', '1', '启用', 1, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (290002, NULL, NULL, NULL, NULL, NULL, NULL, 'enable_status', '0', '停用', 2, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (310001, NULL, NULL, NULL, NULL, NULL, NULL, 'notice_level', 'INFO', '普通', 1, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (310002, NULL, NULL, NULL, NULL, NULL, NULL, 'notice_level', 'SUCCESS', '成功', 2, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (310003, NULL, NULL, NULL, NULL, NULL, NULL, 'notice_level', 'WARNING', '警告', 3, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (310004, NULL, NULL, NULL, NULL, NULL, NULL, 'notice_level', 'ERROR', '错误', 4, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (310005, NULL, NULL, NULL, NULL, NULL, NULL, 'notice_level', 'URGENT', '紧急', 5, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (320001, NULL, NULL, NULL, NULL, NULL, NULL, 'file_type', 'IMAGE', '图片', 1, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (320002, NULL, NULL, NULL, NULL, NULL, NULL, 'file_type', 'VIDEO', '视频', 2, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (320003, NULL, NULL, NULL, NULL, NULL, NULL, 'file_type', 'AUDIO', '音频', 3, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (320004, NULL, NULL, NULL, NULL, NULL, NULL, 'file_type', 'DOCUMENT', '文档', 4, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (320005, NULL, NULL, NULL, NULL, NULL, NULL, 'file_type', 'SPREADSHEET', '表格', 5, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (320006, NULL, NULL, NULL, NULL, NULL, NULL, 'file_type', 'PRESENTATION', '演示文稿', 6, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (320007, NULL, NULL, NULL, NULL, NULL, NULL, 'file_type', 'PDF', 'PDF', 7, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (320008, NULL, NULL, NULL, NULL, NULL, NULL, 'file_type', 'ARCHIVE', '压缩包', 8, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (320009, NULL, NULL, NULL, NULL, NULL, NULL, 'file_type', 'TEXT', '文本', 9, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (320010, NULL, NULL, NULL, NULL, NULL, NULL, 'file_type', 'OTHER', '其他', 10, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (330001, NULL, NULL, NULL, NULL, NULL, NULL, 'device_type', 'PC', '电脑端', 1, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (330002, NULL, NULL, NULL, NULL, NULL, NULL, 'device_type', 'WEB', '网页端', 2, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (330003, NULL, NULL, NULL, NULL, NULL, NULL, 'device_type', 'H5', '移动网页', 3, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (330004, NULL, NULL, NULL, NULL, NULL, NULL, 'device_type', 'ANDROID', 'Android', 4, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (330005, NULL, NULL, NULL, NULL, NULL, NULL, 'device_type', 'IOS', 'iOS', 5, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (330006, NULL, NULL, NULL, NULL, NULL, NULL, 'device_type', 'MINI_PROGRAM', '小程序', 6, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (330007, NULL, NULL, NULL, NULL, NULL, NULL, 'device_type', 'TABLET', '平板', 7, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (330008, NULL, NULL, NULL, NULL, NULL, NULL, 'device_type', 'OTHER', '其他', 8, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (340001, NULL, NULL, NULL, NULL, NULL, NULL, 'log_level', 'TRACE', 'TRACE', 1, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (340002, NULL, NULL, NULL, NULL, NULL, NULL, 'log_level', 'DEBUG', 'DEBUG', 2, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (340003, NULL, NULL, NULL, NULL, NULL, NULL, 'log_level', 'INFO', 'INFO', 3, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (340004, NULL, NULL, NULL, NULL, NULL, NULL, 'log_level', 'WARN', 'WARN', 4, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (340005, NULL, NULL, NULL, NULL, NULL, NULL, 'log_level', 'ERROR', 'ERROR', 5, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (340006, NULL, NULL, NULL, NULL, NULL, NULL, 'log_level', 'FATAL', 'FATAL', 6, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (210001, NULL, NULL, NULL, '2026-05-02 01:15:00.163094', NULL, '1', 'id_card_type', 'ID_CARD', '居民身份证', 1, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (300001, NULL, NULL, 'ytora', '2026-05-03 18:05:44.084042', NULL, NULL, 'user_status', '1', '正常', 1, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (300002, NULL, NULL, 'ytora', '2026-05-03 18:05:48.883035', NULL, NULL, 'user_status', '2', '锁定', 2, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (300003, NULL, NULL, 'ytora', '2026-05-03 18:05:54.265442', NULL, NULL, 'user_status', '3', '禁用', 3, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (300004, NULL, NULL, 'ytora', '2026-05-03 18:05:59.255646', NULL, NULL, 'user_status', '4', '冻结', 4, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (300005, NULL, NULL, 'ytora', '2026-05-03 18:06:04.30006', NULL, NULL, 'user_status', '5', '注销', 5, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (300006, NULL, NULL, 'ytora', '2026-05-03 18:06:09.54136', NULL, NULL, 'user_status', '6', '待激活', 6, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (720082994790400, 'ytora', '2026-05-04 04:06:34.525366', 'ytora', '2026-05-04 04:06:34.525366', '707063588388864', NULL, 'DATA_SCOPE', 'ROOT_DEPT', '查看当前主体（组织根）数据', 2, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (720081290461184, 'ytora', '2026-05-04 04:06:08.519557', 'ytora', '2026-05-04 04:06:43.749789', '707063588388864', NULL, 'DATA_SCOPE', 'ALL', '查看全部数据', 1, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (720085034205184, 'ytora', '2026-05-04 04:07:05.644855', 'ytora', '2026-05-04 04:07:11.907956', '707063588388864', NULL, 'DATA_SCOPE', 'SELF_CREATED', '查看本人创建的数据', 4, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (720086311043072, 'ytora', '2026-05-04 04:07:25.127768', 'ytora', '2026-05-04 04:07:25.127768', '707063588388864', NULL, 'DATA_SCOPE', 'ASSIGNED_DEPART', '查看指定部门的数据', 5, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (720087255613440, 'ytora', '2026-05-04 04:07:39.540968', 'ytora', '2026-05-04 04:07:39.540968', '707063588388864', NULL, 'DATA_SCOPE', 'ASSIGNED_USER', '查看指定人员创建的数据', 6, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (720084440186880, 'ytora', '2026-05-04 04:06:56.580425', 'ytora', '2026-05-04 04:07:51.973634', '707063588388864', NULL, 'DATA_SCOPE', 'DEPT_AND_CHILD', '查看当前部门及子部门数据', 3, NULL);
INSERT INTO "ytora"."sys_dict_item" VALUES (720121645760512, 'ytora', '2026-05-04 04:16:24.292161', 'ytora', '2026-05-04 04:16:24.292161', '707063588388864', NULL, 'DATA_SCOPE', 'CUSTOM', '自定义', 7, NULL);

-- ----------------------------
-- Table structure for sys_file
-- ----------------------------
DROP TABLE IF EXISTS "ytora"."sys_file";
CREATE TABLE "ytora"."sys_file" (
  "id" int8 NOT NULL,
  "create_by" varchar(255) COLLATE "pg_catalog"."default",
  "create_time" timestamp(6),
  "update_by" varchar(255) COLLATE "pg_catalog"."default",
  "update_time" timestamp(6),
  "depart_code" varchar(255) COLLATE "pg_catalog"."default",
  "remark" varchar(255) COLLATE "pg_catalog"."default",
  "file_id" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "folder_id" varchar(255) COLLATE "pg_catalog"."default",
  "file_name" varchar(255) COLLATE "pg_catalog"."default",
  "file_size" int8,
  "file_size_text" varchar(255) COLLATE "pg_catalog"."default",
  "file_type" varchar(255) COLLATE "pg_catalog"."default",
  "download_count" int4
)
;
COMMENT ON COLUMN "ytora"."sys_file"."id" IS '数据主键ID';
COMMENT ON COLUMN "ytora"."sys_file"."create_by" IS '数据创建人';
COMMENT ON COLUMN "ytora"."sys_file"."create_time" IS '数据创建日期';
COMMENT ON COLUMN "ytora"."sys_file"."update_by" IS '数据修改人';
COMMENT ON COLUMN "ytora"."sys_file"."update_time" IS '数据修改日期';
COMMENT ON COLUMN "ytora"."sys_file"."depart_code" IS '数据创建人所属部门';
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

-- ----------------------------
-- Table structure for sys_folder
-- ----------------------------
DROP TABLE IF EXISTS "ytora"."sys_folder";
CREATE TABLE "ytora"."sys_folder" (
  "id" int8 NOT NULL,
  "create_by" varchar(255) COLLATE "pg_catalog"."default",
  "create_time" timestamp(6),
  "update_by" varchar(255) COLLATE "pg_catalog"."default",
  "update_time" timestamp(6),
  "depart_code" varchar(255) COLLATE "pg_catalog"."default",
  "remark" varchar(255) COLLATE "pg_catalog"."default",
  "pid" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "path" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "depth" int4
)
;
COMMENT ON COLUMN "ytora"."sys_folder"."id" IS '数据主键ID';
COMMENT ON COLUMN "ytora"."sys_folder"."create_by" IS '数据创建人';
COMMENT ON COLUMN "ytora"."sys_folder"."create_time" IS '数据创建日期';
COMMENT ON COLUMN "ytora"."sys_folder"."update_by" IS '数据修改人';
COMMENT ON COLUMN "ytora"."sys_folder"."update_time" IS '数据修改日期';
COMMENT ON COLUMN "ytora"."sys_folder"."depart_code" IS '数据创建人所属部门';
COMMENT ON COLUMN "ytora"."sys_folder"."pid" IS '父文件夹ID';
COMMENT ON COLUMN "ytora"."sys_folder"."path" IS '文件夹路径';
COMMENT ON COLUMN "ytora"."sys_folder"."depth" IS '文件夹深度';
COMMENT ON TABLE "ytora"."sys_folder" IS '系统文件夹';

-- ----------------------------
-- Records of sys_folder
-- ----------------------------

-- ----------------------------
-- Table structure for sys_icon
-- ----------------------------
DROP TABLE IF EXISTS "ytora"."sys_icon";
CREATE TABLE "ytora"."sys_icon" (
  "id" int4 NOT NULL,
  "create_by" varchar(255) COLLATE "pg_catalog"."default",
  "create_time" date,
  "update_by" varchar(255) COLLATE "pg_catalog"."default",
  "update_time" date,
  "depart_code" varchar(255) COLLATE "pg_catalog"."default",
  "code" varchar(255) COLLATE "pg_catalog"."default",
  "name" varchar(255) COLLATE "pg_catalog"."default",
  "type" varchar(255) COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "ytora"."sys_icon"."id" IS '主键';
COMMENT ON COLUMN "ytora"."sys_icon"."create_by" IS '数据创建人';
COMMENT ON COLUMN "ytora"."sys_icon"."create_time" IS '数据创建日期';
COMMENT ON COLUMN "ytora"."sys_icon"."update_by" IS '数据修改人';
COMMENT ON COLUMN "ytora"."sys_icon"."update_time" IS '数据修改日期';
COMMENT ON COLUMN "ytora"."sys_icon"."depart_code" IS '数据创建人所属部门';
COMMENT ON COLUMN "ytora"."sys_icon"."code" IS '图标code';
COMMENT ON COLUMN "ytora"."sys_icon"."name" IS '图标名称';
COMMENT ON COLUMN "ytora"."sys_icon"."type" IS '图标库类型';
COMMENT ON TABLE "ytora"."sys_icon" IS '系统图标库';

-- ----------------------------
-- Records of sys_icon
-- ----------------------------
INSERT INTO "ytora"."sys_icon" VALUES (5, NULL, NULL, NULL, NULL, NULL, 'i-lucide-activity', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (7, NULL, NULL, NULL, NULL, NULL, 'i-lucide-airplay', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (9, NULL, NULL, NULL, NULL, NULL, 'i-lucide-alarm-clock-check', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (10, NULL, NULL, NULL, NULL, NULL, 'i-lucide-alarm-clock-minus', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (11, NULL, NULL, NULL, NULL, NULL, 'i-lucide-alarm-clock-off', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (12, NULL, NULL, NULL, NULL, NULL, 'i-lucide-alarm-clock-plus', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (13, NULL, NULL, NULL, NULL, NULL, 'i-lucide-alarm-smoke', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (14, NULL, NULL, NULL, NULL, NULL, 'i-lucide-album', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (16, NULL, NULL, NULL, NULL, NULL, 'i-lucide-align-center-horizontal', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (17, NULL, NULL, NULL, NULL, NULL, 'i-lucide-align-center-vertical', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (18, NULL, NULL, NULL, NULL, NULL, 'i-lucide-align-end-horizontal', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (19, NULL, NULL, NULL, NULL, NULL, 'i-lucide-align-end-vertical', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (20, NULL, NULL, NULL, NULL, NULL, 'i-lucide-align-horizontal-distribute-center', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (21, NULL, NULL, NULL, NULL, NULL, 'i-lucide-align-horizontal-distribute-end', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (23, NULL, NULL, NULL, NULL, NULL, 'i-lucide-align-horizontal-justify-center', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (24, NULL, NULL, NULL, NULL, NULL, 'i-lucide-align-horizontal-justify-end', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (25, NULL, NULL, NULL, NULL, NULL, 'i-lucide-align-horizontal-justify-start', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (26, NULL, NULL, NULL, NULL, NULL, 'i-lucide-align-horizontal-space-around', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (27, NULL, NULL, NULL, NULL, NULL, 'i-lucide-align-horizontal-space-between', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (28, NULL, NULL, NULL, NULL, NULL, 'i-lucide-align-justify', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (29, NULL, NULL, NULL, NULL, NULL, 'i-lucide-align-left', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (30, NULL, NULL, NULL, NULL, NULL, 'i-lucide-align-right', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (31, NULL, NULL, NULL, NULL, NULL, 'i-lucide-align-start-horizontal', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (32, NULL, NULL, NULL, NULL, NULL, 'i-lucide-align-start-vertical', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (33, NULL, NULL, NULL, NULL, NULL, 'i-lucide-align-vertical-distribute-center', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (34, NULL, NULL, NULL, NULL, NULL, 'i-lucide-align-vertical-distribute-end', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (35, NULL, NULL, NULL, NULL, NULL, 'i-lucide-align-vertical-distribute-start', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (36, NULL, NULL, NULL, NULL, NULL, 'i-lucide-align-vertical-justify-center', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (37, NULL, NULL, NULL, NULL, NULL, 'i-lucide-align-vertical-justify-end', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (38, NULL, NULL, NULL, NULL, NULL, 'i-lucide-align-vertical-justify-start', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (40, NULL, NULL, NULL, NULL, NULL, 'i-lucide-align-vertical-space-between', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (41, NULL, NULL, NULL, NULL, NULL, 'i-lucide-ambulance', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (42, NULL, NULL, NULL, NULL, NULL, 'i-lucide-ampersand', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (43, NULL, NULL, NULL, NULL, NULL, 'i-lucide-ampersands', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (44, NULL, NULL, NULL, NULL, NULL, 'i-lucide-amphora', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (45, NULL, NULL, NULL, NULL, NULL, 'i-lucide-anchor', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (48, NULL, NULL, NULL, NULL, NULL, 'i-lucide-antenna', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (49, NULL, NULL, NULL, NULL, NULL, 'i-lucide-anvil', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (50, NULL, NULL, NULL, NULL, NULL, 'i-lucide-aperture', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (51, NULL, NULL, NULL, NULL, NULL, 'i-lucide-app-window', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (52, NULL, NULL, NULL, NULL, NULL, 'i-lucide-app-window-mac', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (53, NULL, NULL, NULL, NULL, NULL, 'i-lucide-apple', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (54, NULL, NULL, NULL, NULL, NULL, 'i-lucide-archive', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (56, NULL, NULL, NULL, NULL, NULL, 'i-lucide-archive-x', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (57, NULL, NULL, NULL, NULL, NULL, 'i-lucide-area-chart', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (58, NULL, NULL, NULL, NULL, NULL, 'i-lucide-armchair', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (59, NULL, NULL, NULL, NULL, NULL, 'i-lucide-arrow-big-down', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (60, NULL, NULL, NULL, NULL, NULL, 'i-lucide-arrow-big-down-dash', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (61, NULL, NULL, NULL, NULL, NULL, 'i-lucide-arrow-big-left', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (62, NULL, NULL, NULL, NULL, NULL, 'i-lucide-arrow-big-left-dash', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (64, NULL, NULL, NULL, NULL, NULL, 'i-lucide-arrow-big-right-dash', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (65, NULL, NULL, NULL, NULL, NULL, 'i-lucide-arrow-big-up', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (66, NULL, NULL, NULL, NULL, NULL, 'i-lucide-arrow-big-up-dash', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (67, NULL, NULL, NULL, NULL, NULL, 'i-lucide-arrow-down', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (68, NULL, NULL, NULL, NULL, NULL, 'i-lucide-arrow-down-0-1', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (69, NULL, NULL, NULL, NULL, NULL, 'i-lucide-arrow-down-1-0', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (70, NULL, NULL, NULL, NULL, NULL, 'i-lucide-arrow-down-a-z', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (71, NULL, NULL, NULL, NULL, NULL, 'i-lucide-arrow-down-from-line', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (72, NULL, NULL, NULL, NULL, NULL, 'i-lucide-arrow-down-left', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (73, NULL, NULL, NULL, NULL, NULL, 'i-lucide-arrow-down-narrow-wide', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (74, NULL, NULL, NULL, NULL, NULL, 'i-lucide-arrow-down-right', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (76, NULL, NULL, NULL, NULL, NULL, 'i-lucide-arrow-down-to-line', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (77, NULL, NULL, NULL, NULL, NULL, 'i-lucide-arrow-down-up', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (78, NULL, NULL, NULL, NULL, NULL, 'i-lucide-arrow-down-wide-narrow', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (79, NULL, NULL, NULL, NULL, NULL, 'i-lucide-arrow-down-z-a', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (80, NULL, NULL, NULL, NULL, NULL, 'i-lucide-arrow-left', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (81, NULL, NULL, NULL, NULL, NULL, 'i-lucide-arrow-left-from-line', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (82, NULL, NULL, NULL, NULL, NULL, 'i-lucide-arrow-left-right', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (83, NULL, NULL, NULL, NULL, NULL, 'i-lucide-arrow-left-to-line', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (84, NULL, NULL, NULL, NULL, NULL, 'i-lucide-arrow-right', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (86, NULL, NULL, NULL, NULL, NULL, 'i-lucide-arrow-right-left', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (87, NULL, NULL, NULL, NULL, NULL, 'i-lucide-arrow-right-to-line', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (88, NULL, NULL, NULL, NULL, NULL, 'i-lucide-arrow-up', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (89, NULL, NULL, NULL, NULL, NULL, 'i-lucide-arrow-up-0-1', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (90, NULL, NULL, NULL, NULL, NULL, 'i-lucide-arrow-up-1-0', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (91, NULL, NULL, NULL, NULL, NULL, 'i-lucide-arrow-up-a-z', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (92, NULL, NULL, NULL, NULL, NULL, 'i-lucide-arrow-up-down', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (93, NULL, NULL, NULL, NULL, NULL, 'i-lucide-arrow-up-from-dot', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (94, NULL, NULL, NULL, NULL, NULL, 'i-lucide-arrow-up-from-line', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (95, NULL, NULL, NULL, NULL, NULL, 'i-lucide-arrow-up-left', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (97, NULL, NULL, NULL, NULL, NULL, 'i-lucide-arrow-up-right', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (98, NULL, NULL, NULL, NULL, NULL, 'i-lucide-arrow-up-to-line', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (99, NULL, NULL, NULL, NULL, NULL, 'i-lucide-arrow-up-wide-narrow', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (100, NULL, NULL, NULL, NULL, NULL, 'i-lucide-arrow-up-z-a', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (101, NULL, NULL, NULL, NULL, NULL, 'i-lucide-arrows-up-from-line', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (102, NULL, NULL, NULL, NULL, NULL, 'i-lucide-asterisk', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (103, NULL, NULL, NULL, NULL, NULL, 'i-lucide-at-sign', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (104, NULL, NULL, NULL, NULL, NULL, 'i-lucide-atom', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (105, NULL, NULL, NULL, NULL, NULL, 'i-lucide-audio-lines', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (106, NULL, NULL, NULL, NULL, NULL, 'i-lucide-audio-waveform', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (108, NULL, NULL, NULL, NULL, NULL, 'i-lucide-axe', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (109, NULL, NULL, NULL, NULL, NULL, 'i-lucide-axis-3d', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (110, NULL, NULL, NULL, NULL, NULL, 'i-lucide-baby', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (111, NULL, NULL, NULL, NULL, NULL, 'i-lucide-backpack', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (112, NULL, NULL, NULL, NULL, NULL, 'i-lucide-badge', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (113, NULL, NULL, NULL, NULL, NULL, 'i-lucide-badge-alert', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (114, NULL, NULL, NULL, NULL, NULL, 'i-lucide-badge-cent', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (8, NULL, NULL, NULL, NULL, NULL, 'i-lucide-alarm-clock', '闹钟', 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (3, NULL, NULL, NULL, NULL, NULL, 'i-lucide-a-large-small', '大小A', 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (46, NULL, NULL, NULL, NULL, NULL, 'i-lucide-angry', '愤怒😡', 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (47, NULL, NULL, NULL, NULL, NULL, 'i-lucide-annoyed', '无语😐', 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (116, NULL, NULL, NULL, NULL, NULL, 'i-lucide-badge-dollar-sign', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (117, NULL, NULL, NULL, NULL, NULL, 'i-lucide-badge-euro', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (118, NULL, NULL, NULL, NULL, NULL, 'i-lucide-badge-indian-rupee', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (119, NULL, NULL, NULL, NULL, NULL, 'i-lucide-badge-info', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (121, NULL, NULL, NULL, NULL, NULL, 'i-lucide-badge-minus', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (122, NULL, NULL, NULL, NULL, NULL, 'i-lucide-badge-percent', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (123, NULL, NULL, NULL, NULL, NULL, 'i-lucide-badge-plus', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (124, NULL, NULL, NULL, NULL, NULL, 'i-lucide-badge-pound-sterling', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (125, NULL, NULL, NULL, NULL, NULL, 'i-lucide-badge-question-mark', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (126, NULL, NULL, NULL, NULL, NULL, 'i-lucide-badge-russian-ruble', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (127, NULL, NULL, NULL, NULL, NULL, 'i-lucide-badge-swiss-franc', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (128, NULL, NULL, NULL, NULL, NULL, 'i-lucide-badge-turkish-lira', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (129, NULL, NULL, NULL, NULL, NULL, 'i-lucide-badge-x', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (131, NULL, NULL, NULL, NULL, NULL, 'i-lucide-balloon', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (132, NULL, NULL, NULL, NULL, NULL, 'i-lucide-ban', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (133, NULL, NULL, NULL, NULL, NULL, 'i-lucide-banana', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (134, NULL, NULL, NULL, NULL, NULL, 'i-lucide-bandage', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (135, NULL, NULL, NULL, NULL, NULL, 'i-lucide-banknote', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (136, NULL, NULL, NULL, NULL, NULL, 'i-lucide-banknote-arrow-down', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (137, NULL, NULL, NULL, NULL, NULL, 'i-lucide-banknote-arrow-up', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (138, NULL, NULL, NULL, NULL, NULL, 'i-lucide-banknote-x', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (140, NULL, NULL, NULL, NULL, NULL, 'i-lucide-bar-chart-4', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (141, NULL, NULL, NULL, NULL, NULL, 'i-lucide-bar-chart-big', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (142, NULL, NULL, NULL, NULL, NULL, 'i-lucide-bar-chart-horizontal', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (143, NULL, NULL, NULL, NULL, NULL, 'i-lucide-bar-chart-horizontal-big', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (144, NULL, NULL, NULL, NULL, NULL, 'i-lucide-barcode', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (145, NULL, NULL, NULL, NULL, NULL, 'i-lucide-barrel', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (146, NULL, NULL, NULL, NULL, NULL, 'i-lucide-baseline', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (147, NULL, NULL, NULL, NULL, NULL, 'i-lucide-bath', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (148, NULL, NULL, NULL, NULL, NULL, 'i-lucide-battery', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (150, NULL, NULL, NULL, NULL, NULL, 'i-lucide-battery-full', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (151, NULL, NULL, NULL, NULL, NULL, 'i-lucide-battery-low', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (152, NULL, NULL, NULL, NULL, NULL, 'i-lucide-battery-medium', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (153, NULL, NULL, NULL, NULL, NULL, 'i-lucide-battery-plus', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (154, NULL, NULL, NULL, NULL, NULL, 'i-lucide-battery-warning', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (155, NULL, NULL, NULL, NULL, NULL, 'i-lucide-beaker', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (156, NULL, NULL, NULL, NULL, NULL, 'i-lucide-bean', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (157, NULL, NULL, NULL, NULL, NULL, 'i-lucide-bean-off', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (158, NULL, NULL, NULL, NULL, NULL, 'i-lucide-bed', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (159, NULL, NULL, NULL, NULL, NULL, 'i-lucide-bed-double', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (161, NULL, NULL, NULL, NULL, NULL, 'i-lucide-beef', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (162, NULL, NULL, NULL, NULL, NULL, 'i-lucide-beef-off', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (163, NULL, NULL, NULL, NULL, NULL, 'i-lucide-beer', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (164, NULL, NULL, NULL, NULL, NULL, 'i-lucide-beer-off', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (165, NULL, NULL, NULL, NULL, NULL, 'i-lucide-bell', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (166, NULL, NULL, NULL, NULL, NULL, 'i-lucide-bell-dot', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (167, NULL, NULL, NULL, NULL, NULL, 'i-lucide-bell-electric', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (168, NULL, NULL, NULL, NULL, NULL, 'i-lucide-bell-minus', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (170, NULL, NULL, NULL, NULL, NULL, 'i-lucide-bell-plus', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (171, NULL, NULL, NULL, NULL, NULL, 'i-lucide-bell-ring', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (172, NULL, NULL, NULL, NULL, NULL, 'i-lucide-between-horizontal-end', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (173, NULL, NULL, NULL, NULL, NULL, 'i-lucide-between-horizontal-start', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (174, NULL, NULL, NULL, NULL, NULL, 'i-lucide-between-vertical-end', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (175, NULL, NULL, NULL, NULL, NULL, 'i-lucide-between-vertical-start', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (176, NULL, NULL, NULL, NULL, NULL, 'i-lucide-biceps-flexed', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (177, NULL, NULL, NULL, NULL, NULL, 'i-lucide-bike', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (178, NULL, NULL, NULL, NULL, NULL, 'i-lucide-binary', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (179, NULL, NULL, NULL, NULL, NULL, 'i-lucide-binoculars', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (181, NULL, NULL, NULL, NULL, NULL, 'i-lucide-bird', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (182, NULL, NULL, NULL, NULL, NULL, 'i-lucide-birdhouse', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (183, NULL, NULL, NULL, NULL, NULL, 'i-lucide-bitcoin', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (184, NULL, NULL, NULL, NULL, NULL, 'i-lucide-blend', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (185, NULL, NULL, NULL, NULL, NULL, 'i-lucide-blinds', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (186, NULL, NULL, NULL, NULL, NULL, 'i-lucide-blocks', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (187, NULL, NULL, NULL, NULL, NULL, 'i-lucide-bluetooth', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (188, NULL, NULL, NULL, NULL, NULL, 'i-lucide-bluetooth-connected', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (190, NULL, NULL, NULL, NULL, NULL, 'i-lucide-bluetooth-searching', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (191, NULL, NULL, NULL, NULL, NULL, 'i-lucide-bold', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (192, NULL, NULL, NULL, NULL, NULL, 'i-lucide-bolt', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (193, NULL, NULL, NULL, NULL, NULL, 'i-lucide-bomb', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (194, NULL, NULL, NULL, NULL, NULL, 'i-lucide-bone', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (195, NULL, NULL, NULL, NULL, NULL, 'i-lucide-book', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (196, NULL, NULL, NULL, NULL, NULL, 'i-lucide-book-a', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (197, NULL, NULL, NULL, NULL, NULL, 'i-lucide-book-alert', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (198, NULL, NULL, NULL, NULL, NULL, 'i-lucide-book-audio', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (199, NULL, NULL, NULL, NULL, NULL, 'i-lucide-book-check', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (201, NULL, NULL, NULL, NULL, NULL, 'i-lucide-book-dashed', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (202, NULL, NULL, NULL, NULL, NULL, 'i-lucide-book-down', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (203, NULL, NULL, NULL, NULL, NULL, 'i-lucide-book-headphones', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (204, NULL, NULL, NULL, NULL, NULL, 'i-lucide-book-heart', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (205, NULL, NULL, NULL, NULL, NULL, 'i-lucide-book-image', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (206, NULL, NULL, NULL, NULL, NULL, 'i-lucide-book-key', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (208, NULL, NULL, NULL, NULL, NULL, 'i-lucide-book-marked', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (209, NULL, NULL, NULL, NULL, NULL, 'i-lucide-book-minus', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (210, NULL, NULL, NULL, NULL, NULL, 'i-lucide-book-open', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (211, NULL, NULL, NULL, NULL, NULL, 'i-lucide-book-open-check', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (212, NULL, NULL, NULL, NULL, NULL, 'i-lucide-book-open-text', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (213, NULL, NULL, NULL, NULL, NULL, 'i-lucide-book-plus', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (214, NULL, NULL, NULL, NULL, NULL, 'i-lucide-book-search', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (216, NULL, NULL, NULL, NULL, NULL, 'i-lucide-book-type', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (217, NULL, NULL, NULL, NULL, NULL, 'i-lucide-book-up', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (218, NULL, NULL, NULL, NULL, NULL, 'i-lucide-book-up-2', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (219, NULL, NULL, NULL, NULL, NULL, 'i-lucide-book-user', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (220, NULL, NULL, NULL, NULL, NULL, 'i-lucide-book-x', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (221, NULL, NULL, NULL, NULL, NULL, 'i-lucide-bookmark', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (223, NULL, NULL, NULL, NULL, NULL, 'i-lucide-bookmark-minus', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (224, NULL, NULL, NULL, NULL, NULL, 'i-lucide-bookmark-off', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (225, NULL, NULL, NULL, NULL, NULL, 'i-lucide-bookmark-plus', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (226, NULL, NULL, NULL, NULL, NULL, 'i-lucide-bookmark-x', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (227, NULL, NULL, NULL, NULL, NULL, 'i-lucide-boom-box', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (228, NULL, NULL, NULL, NULL, NULL, 'i-lucide-bot', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (229, NULL, NULL, NULL, NULL, NULL, 'i-lucide-bot-message-square', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (230, NULL, NULL, NULL, NULL, NULL, 'i-lucide-bot-off', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (232, NULL, NULL, NULL, NULL, NULL, 'i-lucide-bow-arrow', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (233, NULL, NULL, NULL, NULL, NULL, 'i-lucide-box', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (234, NULL, NULL, NULL, NULL, NULL, 'i-lucide-boxes', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (235, NULL, NULL, NULL, NULL, NULL, 'i-lucide-braces', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (236, NULL, NULL, NULL, NULL, NULL, 'i-lucide-brackets', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (237, NULL, NULL, NULL, NULL, NULL, 'i-lucide-brain', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (238, NULL, NULL, NULL, NULL, NULL, 'i-lucide-brain-circuit', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (239, NULL, NULL, NULL, NULL, NULL, 'i-lucide-brain-cog', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (241, NULL, NULL, NULL, NULL, NULL, 'i-lucide-brick-wall-fire', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (242, NULL, NULL, NULL, NULL, NULL, 'i-lucide-brick-wall-shield', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (243, NULL, NULL, NULL, NULL, NULL, 'i-lucide-briefcase', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (244, NULL, NULL, NULL, NULL, NULL, 'i-lucide-briefcase-business', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (245, NULL, NULL, NULL, NULL, NULL, 'i-lucide-briefcase-conveyor-belt', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (246, NULL, NULL, NULL, NULL, NULL, 'i-lucide-briefcase-medical', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (247, NULL, NULL, NULL, NULL, NULL, 'i-lucide-bring-to-front', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (248, NULL, NULL, NULL, NULL, NULL, 'i-lucide-brush', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (250, NULL, NULL, NULL, NULL, NULL, 'i-lucide-bubbles', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (251, NULL, NULL, NULL, NULL, NULL, 'i-lucide-bug', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (252, NULL, NULL, NULL, NULL, NULL, 'i-lucide-bug-off', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (253, NULL, NULL, NULL, NULL, NULL, 'i-lucide-bug-play', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (254, NULL, NULL, NULL, NULL, NULL, 'i-lucide-building', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (255, NULL, NULL, NULL, NULL, NULL, 'i-lucide-building-2', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (256, NULL, NULL, NULL, NULL, NULL, 'i-lucide-bus', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (257, NULL, NULL, NULL, NULL, NULL, 'i-lucide-bus-front', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (259, NULL, NULL, NULL, NULL, NULL, 'i-lucide-cable-car', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (260, NULL, NULL, NULL, NULL, NULL, 'i-lucide-cake', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (261, NULL, NULL, NULL, NULL, NULL, 'i-lucide-cake-slice', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (262, NULL, NULL, NULL, NULL, NULL, 'i-lucide-calculator', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (263, NULL, NULL, NULL, NULL, NULL, 'i-lucide-calendar', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (264, NULL, NULL, NULL, NULL, NULL, 'i-lucide-calendar-1', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (266, NULL, NULL, NULL, NULL, NULL, 'i-lucide-calendar-arrow-up', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (267, NULL, NULL, NULL, NULL, NULL, 'i-lucide-calendar-check', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (268, NULL, NULL, NULL, NULL, NULL, 'i-lucide-calendar-check-2', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (269, NULL, NULL, NULL, NULL, NULL, 'i-lucide-calendar-clock', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (270, NULL, NULL, NULL, NULL, NULL, 'i-lucide-calendar-cog', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (271, NULL, NULL, NULL, NULL, NULL, 'i-lucide-calendar-days', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (272, NULL, NULL, NULL, NULL, NULL, 'i-lucide-calendar-fold', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (273, NULL, NULL, NULL, NULL, NULL, 'i-lucide-calendar-heart', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (274, NULL, NULL, NULL, NULL, NULL, 'i-lucide-calendar-minus', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (275, NULL, NULL, NULL, NULL, NULL, 'i-lucide-calendar-minus-2', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (277, NULL, NULL, NULL, NULL, NULL, 'i-lucide-calendar-plus', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (278, NULL, NULL, NULL, NULL, NULL, 'i-lucide-calendar-plus-2', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (279, NULL, NULL, NULL, NULL, NULL, 'i-lucide-calendar-range', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (280, NULL, NULL, NULL, NULL, NULL, 'i-lucide-calendar-search', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (281, NULL, NULL, NULL, NULL, NULL, 'i-lucide-calendar-sync', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (282, NULL, NULL, NULL, NULL, NULL, 'i-lucide-calendar-x', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (283, NULL, NULL, NULL, NULL, NULL, 'i-lucide-calendar-x-2', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (284, NULL, NULL, NULL, NULL, NULL, 'i-lucide-calendars', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (285, NULL, NULL, NULL, NULL, NULL, 'i-lucide-camera', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (286, NULL, NULL, NULL, NULL, NULL, 'i-lucide-camera-off', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (288, NULL, NULL, NULL, NULL, NULL, 'i-lucide-candy', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (289, NULL, NULL, NULL, NULL, NULL, 'i-lucide-candy-cane', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (290, NULL, NULL, NULL, NULL, NULL, 'i-lucide-candy-off', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (291, NULL, NULL, NULL, NULL, NULL, 'i-lucide-cannabis', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (292, NULL, NULL, NULL, NULL, NULL, 'i-lucide-cannabis-off', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (293, NULL, NULL, NULL, NULL, NULL, 'i-lucide-captions', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (294, NULL, NULL, NULL, NULL, NULL, 'i-lucide-captions-off', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (295, NULL, NULL, NULL, NULL, NULL, 'i-lucide-car', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (296, NULL, NULL, NULL, NULL, NULL, 'i-lucide-car-front', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (298, NULL, NULL, NULL, NULL, NULL, 'i-lucide-caravan', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (299, NULL, NULL, NULL, NULL, NULL, 'i-lucide-card-sim', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (300, NULL, NULL, NULL, NULL, NULL, 'i-lucide-carrot', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (301, NULL, NULL, NULL, NULL, NULL, 'i-lucide-case-lower', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (302, NULL, NULL, NULL, NULL, NULL, 'i-lucide-case-sensitive', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (303, NULL, NULL, NULL, NULL, NULL, 'i-lucide-case-upper', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (304, NULL, NULL, NULL, NULL, NULL, 'i-lucide-cassette-tape', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (305, NULL, NULL, NULL, NULL, NULL, 'i-lucide-cast', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (306, NULL, NULL, NULL, NULL, NULL, 'i-lucide-castle', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (307, NULL, NULL, NULL, NULL, NULL, 'i-lucide-cat', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (308, NULL, NULL, NULL, NULL, NULL, 'i-lucide-cctv', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (310, NULL, NULL, NULL, NULL, NULL, 'i-lucide-chart-area', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (311, NULL, NULL, NULL, NULL, NULL, 'i-lucide-chart-bar', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (312, NULL, NULL, NULL, NULL, NULL, 'i-lucide-chart-bar-big', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (313, NULL, NULL, NULL, NULL, NULL, 'i-lucide-chart-bar-decreasing', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (314, NULL, NULL, NULL, NULL, NULL, 'i-lucide-chart-bar-increasing', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (315, NULL, NULL, NULL, NULL, NULL, 'i-lucide-chart-bar-stacked', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (317, NULL, NULL, NULL, NULL, NULL, 'i-lucide-chart-column', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (318, NULL, NULL, NULL, NULL, NULL, 'i-lucide-chart-column-big', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (319, NULL, NULL, NULL, NULL, NULL, 'i-lucide-chart-column-decreasing', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (320, NULL, NULL, NULL, NULL, NULL, 'i-lucide-chart-column-increasing', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (321, NULL, NULL, NULL, NULL, NULL, 'i-lucide-chart-column-stacked', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (322, NULL, NULL, NULL, NULL, NULL, 'i-lucide-chart-gantt', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (323, NULL, NULL, NULL, NULL, NULL, 'i-lucide-chart-line', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (324, NULL, NULL, NULL, NULL, NULL, 'i-lucide-chart-network', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (325, NULL, NULL, NULL, NULL, NULL, 'i-lucide-chart-no-axes-column', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (326, NULL, NULL, NULL, NULL, NULL, 'i-lucide-chart-no-axes-column-decreasing', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (328, NULL, NULL, NULL, NULL, NULL, 'i-lucide-chart-no-axes-combined', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (329, NULL, NULL, NULL, NULL, NULL, 'i-lucide-chart-no-axes-gantt', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (330, NULL, NULL, NULL, NULL, NULL, 'i-lucide-chart-pie', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (331, NULL, NULL, NULL, NULL, NULL, 'i-lucide-chart-scatter', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (332, NULL, NULL, NULL, NULL, NULL, 'i-lucide-chart-spline', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (333, NULL, NULL, NULL, NULL, NULL, 'i-lucide-check', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (336, NULL, NULL, NULL, NULL, NULL, 'i-lucide-chef-hat', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (337, NULL, NULL, NULL, NULL, NULL, 'i-lucide-cherry', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (338, NULL, NULL, NULL, NULL, NULL, 'i-lucide-chess-bishop', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (339, NULL, NULL, NULL, NULL, NULL, 'i-lucide-chess-king', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (340, NULL, NULL, NULL, NULL, NULL, 'i-lucide-chess-knight', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (341, NULL, NULL, NULL, NULL, NULL, 'i-lucide-chess-pawn', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (342, NULL, NULL, NULL, NULL, NULL, 'i-lucide-chess-queen', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (344, NULL, NULL, NULL, NULL, NULL, 'i-lucide-chevron-down', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (345, NULL, NULL, NULL, NULL, NULL, 'i-lucide-chevron-first', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (346, NULL, NULL, NULL, NULL, NULL, 'i-lucide-chevron-last', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (347, NULL, NULL, NULL, NULL, NULL, 'i-lucide-chevron-left', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (348, NULL, NULL, NULL, NULL, NULL, 'i-lucide-chevron-right', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (349, NULL, NULL, NULL, NULL, NULL, 'i-lucide-chevron-up', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (350, NULL, NULL, NULL, NULL, NULL, 'i-lucide-chevrons-down', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (351, NULL, NULL, NULL, NULL, NULL, 'i-lucide-chevrons-down-up', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (352, NULL, NULL, NULL, NULL, NULL, 'i-lucide-chevrons-left', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (353, NULL, NULL, NULL, NULL, NULL, 'i-lucide-chevrons-left-right', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (355, NULL, NULL, NULL, NULL, NULL, 'i-lucide-chevrons-right', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (356, NULL, NULL, NULL, NULL, NULL, 'i-lucide-chevrons-right-left', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (357, NULL, NULL, NULL, NULL, NULL, 'i-lucide-chevrons-up', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (846, NULL, NULL, NULL, NULL, NULL, 'i-lucide-hotel', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (358, NULL, NULL, NULL, NULL, NULL, 'i-lucide-chevrons-up-down', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (359, NULL, NULL, NULL, NULL, NULL, 'i-lucide-chrome', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (360, NULL, NULL, NULL, NULL, NULL, 'i-lucide-chromium', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (361, NULL, NULL, NULL, NULL, NULL, 'i-lucide-church', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (362, NULL, NULL, NULL, NULL, NULL, 'i-lucide-cigarette', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (363, NULL, NULL, NULL, NULL, NULL, 'i-lucide-cigarette-off', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (364, NULL, NULL, NULL, NULL, NULL, 'i-lucide-circle', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (365, NULL, NULL, NULL, NULL, NULL, 'i-lucide-circle-alert', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (367, NULL, NULL, NULL, NULL, NULL, 'i-lucide-circle-arrow-left', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (368, NULL, NULL, NULL, NULL, NULL, 'i-lucide-circle-arrow-out-down-left', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (369, NULL, NULL, NULL, NULL, NULL, 'i-lucide-circle-arrow-out-down-right', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (370, NULL, NULL, NULL, NULL, NULL, 'i-lucide-circle-arrow-out-up-left', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (371, NULL, NULL, NULL, NULL, NULL, 'i-lucide-circle-arrow-out-up-right', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (372, NULL, NULL, NULL, NULL, NULL, 'i-lucide-circle-arrow-right', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (373, NULL, NULL, NULL, NULL, NULL, 'i-lucide-circle-arrow-up', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (375, NULL, NULL, NULL, NULL, NULL, 'i-lucide-circle-check-big', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (376, NULL, NULL, NULL, NULL, NULL, 'i-lucide-circle-chevron-down', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (377, NULL, NULL, NULL, NULL, NULL, 'i-lucide-circle-chevron-left', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (378, NULL, NULL, NULL, NULL, NULL, 'i-lucide-circle-chevron-right', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (379, NULL, NULL, NULL, NULL, NULL, 'i-lucide-circle-chevron-up', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (380, NULL, NULL, NULL, NULL, NULL, 'i-lucide-circle-dashed', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (381, NULL, NULL, NULL, NULL, NULL, 'i-lucide-circle-divide', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (382, NULL, NULL, NULL, NULL, NULL, 'i-lucide-circle-dollar-sign', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (384, NULL, NULL, NULL, NULL, NULL, 'i-lucide-circle-dot-dashed', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (385, NULL, NULL, NULL, NULL, NULL, 'i-lucide-circle-ellipsis', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (386, NULL, NULL, NULL, NULL, NULL, 'i-lucide-circle-equal', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (387, NULL, NULL, NULL, NULL, NULL, 'i-lucide-circle-fading-arrow-up', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (388, NULL, NULL, NULL, NULL, NULL, 'i-lucide-circle-fading-plus', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (389, NULL, NULL, NULL, NULL, NULL, 'i-lucide-circle-gauge', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (390, NULL, NULL, NULL, NULL, NULL, 'i-lucide-circle-minus', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (391, NULL, NULL, NULL, NULL, NULL, 'i-lucide-circle-off', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (392, NULL, NULL, NULL, NULL, NULL, 'i-lucide-circle-parking', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (394, NULL, NULL, NULL, NULL, NULL, 'i-lucide-circle-pause', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (395, NULL, NULL, NULL, NULL, NULL, 'i-lucide-circle-percent', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (396, NULL, NULL, NULL, NULL, NULL, 'i-lucide-circle-pile', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (397, NULL, NULL, NULL, NULL, NULL, 'i-lucide-circle-play', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (398, NULL, NULL, NULL, NULL, NULL, 'i-lucide-circle-plus', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (399, NULL, NULL, NULL, NULL, NULL, 'i-lucide-circle-pound-sterling', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (400, NULL, NULL, NULL, NULL, NULL, 'i-lucide-circle-power', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (401, NULL, NULL, NULL, NULL, NULL, 'i-lucide-circle-question-mark', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (402, NULL, NULL, NULL, NULL, NULL, 'i-lucide-circle-slash', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (403, NULL, NULL, NULL, NULL, NULL, 'i-lucide-circle-slash-2', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (404, NULL, NULL, NULL, NULL, NULL, 'i-lucide-circle-small', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (405, NULL, NULL, NULL, NULL, NULL, 'i-lucide-circle-star', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (407, NULL, NULL, NULL, NULL, NULL, 'i-lucide-circle-user', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (408, NULL, NULL, NULL, NULL, NULL, 'i-lucide-circle-user-round', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (409, NULL, NULL, NULL, NULL, NULL, 'i-lucide-circle-x', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (410, NULL, NULL, NULL, NULL, NULL, 'i-lucide-circuit-board', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (411, NULL, NULL, NULL, NULL, NULL, 'i-lucide-citrus', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (412, NULL, NULL, NULL, NULL, NULL, 'i-lucide-clapperboard', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (413, NULL, NULL, NULL, NULL, NULL, 'i-lucide-clipboard', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (415, NULL, NULL, NULL, NULL, NULL, 'i-lucide-clipboard-clock', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (416, NULL, NULL, NULL, NULL, NULL, 'i-lucide-clipboard-copy', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (417, NULL, NULL, NULL, NULL, NULL, 'i-lucide-clipboard-list', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (418, NULL, NULL, NULL, NULL, NULL, 'i-lucide-clipboard-minus', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (419, NULL, NULL, NULL, NULL, NULL, 'i-lucide-clipboard-paste', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (420, NULL, NULL, NULL, NULL, NULL, 'i-lucide-clipboard-pen', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (421, NULL, NULL, NULL, NULL, NULL, 'i-lucide-clipboard-pen-line', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (422, NULL, NULL, NULL, NULL, NULL, 'i-lucide-clipboard-plus', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (423, NULL, NULL, NULL, NULL, NULL, 'i-lucide-clipboard-type', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (424, NULL, NULL, NULL, NULL, NULL, 'i-lucide-clipboard-x', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (425, NULL, NULL, NULL, NULL, NULL, 'i-lucide-clock', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (426, NULL, NULL, NULL, NULL, NULL, 'i-lucide-clock-1', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (427, NULL, NULL, NULL, NULL, NULL, 'i-lucide-clock-10', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (429, NULL, NULL, NULL, NULL, NULL, 'i-lucide-clock-12', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (430, NULL, NULL, NULL, NULL, NULL, 'i-lucide-clock-2', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (431, NULL, NULL, NULL, NULL, NULL, 'i-lucide-clock-3', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (432, NULL, NULL, NULL, NULL, NULL, 'i-lucide-clock-4', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (433, NULL, NULL, NULL, NULL, NULL, 'i-lucide-clock-5', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (434, NULL, NULL, NULL, NULL, NULL, 'i-lucide-clock-6', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (435, NULL, NULL, NULL, NULL, NULL, 'i-lucide-clock-7', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (436, NULL, NULL, NULL, NULL, NULL, 'i-lucide-clock-8', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (437, NULL, NULL, NULL, NULL, NULL, 'i-lucide-clock-9', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (438, NULL, NULL, NULL, NULL, NULL, 'i-lucide-clock-alert', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (440, NULL, NULL, NULL, NULL, NULL, 'i-lucide-clock-arrow-up', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (441, NULL, NULL, NULL, NULL, NULL, 'i-lucide-clock-check', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (442, NULL, NULL, NULL, NULL, NULL, 'i-lucide-clock-fading', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (443, NULL, NULL, NULL, NULL, NULL, 'i-lucide-clock-plus', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (560, NULL, NULL, NULL, NULL, NULL, 'i-lucide-drama', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (445, NULL, NULL, NULL, NULL, NULL, 'i-lucide-cloud', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (446, NULL, NULL, NULL, NULL, NULL, 'i-lucide-cloud-alert', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (447, NULL, NULL, NULL, NULL, NULL, 'i-lucide-cloud-backup', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (448, NULL, NULL, NULL, NULL, NULL, 'i-lucide-cloud-check', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (449, NULL, NULL, NULL, NULL, NULL, 'i-lucide-cloud-cog', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (450, NULL, NULL, NULL, NULL, NULL, 'i-lucide-cloud-download', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (451, NULL, NULL, NULL, NULL, NULL, 'i-lucide-cloud-drizzle', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (452, NULL, NULL, NULL, NULL, NULL, 'i-lucide-cloud-fog', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (454, NULL, NULL, NULL, NULL, NULL, 'i-lucide-cloud-lightning', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (455, NULL, NULL, NULL, NULL, NULL, 'i-lucide-cloud-moon', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (456, NULL, NULL, NULL, NULL, NULL, 'i-lucide-cloud-moon-rain', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (457, NULL, NULL, NULL, NULL, NULL, 'i-lucide-cloud-off', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (458, NULL, NULL, NULL, NULL, NULL, 'i-lucide-cloud-rain', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (459, NULL, NULL, NULL, NULL, NULL, 'i-lucide-cloud-rain-wind', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (460, NULL, NULL, NULL, NULL, NULL, 'i-lucide-cloud-snow', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (461, NULL, NULL, NULL, NULL, NULL, 'i-lucide-cloud-sun', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (463, NULL, NULL, NULL, NULL, NULL, 'i-lucide-cloud-sync', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (464, NULL, NULL, NULL, NULL, NULL, 'i-lucide-cloud-upload', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (465, NULL, NULL, NULL, NULL, NULL, 'i-lucide-cloudy', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (466, NULL, NULL, NULL, NULL, NULL, 'i-lucide-clover', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (467, NULL, NULL, NULL, NULL, NULL, 'i-lucide-club', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (468, NULL, NULL, NULL, NULL, NULL, 'i-lucide-code', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (469, NULL, NULL, NULL, NULL, NULL, 'i-lucide-code-xml', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (470, NULL, NULL, NULL, NULL, NULL, 'i-lucide-codepen', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (471, NULL, NULL, NULL, NULL, NULL, 'i-lucide-codesandbox', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (472, NULL, NULL, NULL, NULL, NULL, 'i-lucide-coffee', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (473, NULL, NULL, NULL, NULL, NULL, 'i-lucide-cog', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (475, NULL, NULL, NULL, NULL, NULL, 'i-lucide-columns-2', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (476, NULL, NULL, NULL, NULL, NULL, 'i-lucide-columns-3', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (477, NULL, NULL, NULL, NULL, NULL, 'i-lucide-columns-3-cog', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (478, NULL, NULL, NULL, NULL, NULL, 'i-lucide-columns-4', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (479, NULL, NULL, NULL, NULL, NULL, 'i-lucide-combine', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (480, NULL, NULL, NULL, NULL, NULL, 'i-lucide-command', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (481, NULL, NULL, NULL, NULL, NULL, 'i-lucide-compass', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (483, NULL, NULL, NULL, NULL, NULL, 'i-lucide-computer', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (484, NULL, NULL, NULL, NULL, NULL, 'i-lucide-concierge-bell', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (485, NULL, NULL, NULL, NULL, NULL, 'i-lucide-cone', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (486, NULL, NULL, NULL, NULL, NULL, 'i-lucide-construction', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (487, NULL, NULL, NULL, NULL, NULL, 'i-lucide-contact', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (488, NULL, NULL, NULL, NULL, NULL, 'i-lucide-contact-round', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (489, NULL, NULL, NULL, NULL, NULL, 'i-lucide-container', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (490, NULL, NULL, NULL, NULL, NULL, 'i-lucide-contrast', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (491, NULL, NULL, NULL, NULL, NULL, 'i-lucide-cookie', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (492, NULL, NULL, NULL, NULL, NULL, 'i-lucide-cooking-pot', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (494, NULL, NULL, NULL, NULL, NULL, 'i-lucide-copy-check', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (495, NULL, NULL, NULL, NULL, NULL, 'i-lucide-copy-minus', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (496, NULL, NULL, NULL, NULL, NULL, 'i-lucide-copy-plus', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (497, NULL, NULL, NULL, NULL, NULL, 'i-lucide-copy-slash', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (498, NULL, NULL, NULL, NULL, NULL, 'i-lucide-copy-x', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (499, NULL, NULL, NULL, NULL, NULL, 'i-lucide-copyleft', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (501, NULL, NULL, NULL, NULL, NULL, 'i-lucide-corner-down-left', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (502, NULL, NULL, NULL, NULL, NULL, 'i-lucide-corner-down-right', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (503, NULL, NULL, NULL, NULL, NULL, 'i-lucide-corner-left-down', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (504, NULL, NULL, NULL, NULL, NULL, 'i-lucide-corner-left-up', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (505, NULL, NULL, NULL, NULL, NULL, 'i-lucide-corner-right-down', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (506, NULL, NULL, NULL, NULL, NULL, 'i-lucide-corner-right-up', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (508, NULL, NULL, NULL, NULL, NULL, 'i-lucide-corner-up-right', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (509, NULL, NULL, NULL, NULL, NULL, 'i-lucide-cpu', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (510, NULL, NULL, NULL, NULL, NULL, 'i-lucide-creative-commons', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (511, NULL, NULL, NULL, NULL, NULL, 'i-lucide-credit-card', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (512, NULL, NULL, NULL, NULL, NULL, 'i-lucide-croissant', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (513, NULL, NULL, NULL, NULL, NULL, 'i-lucide-crop', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (514, NULL, NULL, NULL, NULL, NULL, 'i-lucide-cross', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (515, NULL, NULL, NULL, NULL, NULL, 'i-lucide-crosshair', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (516, NULL, NULL, NULL, NULL, NULL, 'i-lucide-crown', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (517, NULL, NULL, NULL, NULL, NULL, 'i-lucide-cuboid', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (519, NULL, NULL, NULL, NULL, NULL, 'i-lucide-currency', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (520, NULL, NULL, NULL, NULL, NULL, 'i-lucide-cylinder', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (521, NULL, NULL, NULL, NULL, NULL, 'i-lucide-dam', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (522, NULL, NULL, NULL, NULL, NULL, 'i-lucide-database', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (523, NULL, NULL, NULL, NULL, NULL, 'i-lucide-database-backup', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (524, NULL, NULL, NULL, NULL, NULL, 'i-lucide-database-search', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (526, NULL, NULL, NULL, NULL, NULL, 'i-lucide-decimals-arrow-left', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (527, NULL, NULL, NULL, NULL, NULL, 'i-lucide-decimals-arrow-right', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (528, NULL, NULL, NULL, NULL, NULL, 'i-lucide-delete', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (529, NULL, NULL, NULL, NULL, NULL, 'i-lucide-dessert', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (530, NULL, NULL, NULL, NULL, NULL, 'i-lucide-diameter', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (531, NULL, NULL, NULL, NULL, NULL, 'i-lucide-diamond', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (532, NULL, NULL, NULL, NULL, NULL, 'i-lucide-diamond-minus', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (533, NULL, NULL, NULL, NULL, NULL, 'i-lucide-diamond-percent', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (534, NULL, NULL, NULL, NULL, NULL, 'i-lucide-diamond-plus', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (535, NULL, NULL, NULL, NULL, NULL, 'i-lucide-dice-1', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (536, NULL, NULL, NULL, NULL, NULL, 'i-lucide-dice-2', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (537, NULL, NULL, NULL, NULL, NULL, 'i-lucide-dice-3', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (538, NULL, NULL, NULL, NULL, NULL, 'i-lucide-dice-4', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (539, NULL, NULL, NULL, NULL, NULL, 'i-lucide-dice-5', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (540, NULL, NULL, NULL, NULL, NULL, 'i-lucide-dice-6', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (541, NULL, NULL, NULL, NULL, NULL, 'i-lucide-dices', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (543, NULL, NULL, NULL, NULL, NULL, 'i-lucide-disc', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (544, NULL, NULL, NULL, NULL, NULL, 'i-lucide-disc-2', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (545, NULL, NULL, NULL, NULL, NULL, 'i-lucide-disc-3', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (546, NULL, NULL, NULL, NULL, NULL, 'i-lucide-disc-album', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (547, NULL, NULL, NULL, NULL, NULL, 'i-lucide-divide', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (548, NULL, NULL, NULL, NULL, NULL, 'i-lucide-dna', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (549, NULL, NULL, NULL, NULL, NULL, 'i-lucide-dna-off', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (550, NULL, NULL, NULL, NULL, NULL, 'i-lucide-dock', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (551, NULL, NULL, NULL, NULL, NULL, 'i-lucide-dog', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (553, NULL, NULL, NULL, NULL, NULL, 'i-lucide-donut', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (554, NULL, NULL, NULL, NULL, NULL, 'i-lucide-door-closed', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (555, NULL, NULL, NULL, NULL, NULL, 'i-lucide-door-closed-locked', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (556, NULL, NULL, NULL, NULL, NULL, 'i-lucide-door-open', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (557, NULL, NULL, NULL, NULL, NULL, 'i-lucide-dot', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (558, NULL, NULL, NULL, NULL, NULL, 'i-lucide-download', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (562, NULL, NULL, NULL, NULL, NULL, 'i-lucide-drill', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (563, NULL, NULL, NULL, NULL, NULL, 'i-lucide-drone', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (564, NULL, NULL, NULL, NULL, NULL, 'i-lucide-droplet', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (565, NULL, NULL, NULL, NULL, NULL, 'i-lucide-droplet-off', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (566, NULL, NULL, NULL, NULL, NULL, 'i-lucide-droplets', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (567, NULL, NULL, NULL, NULL, NULL, 'i-lucide-drum', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (568, NULL, NULL, NULL, NULL, NULL, 'i-lucide-drumstick', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (570, NULL, NULL, NULL, NULL, NULL, 'i-lucide-ear', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (571, NULL, NULL, NULL, NULL, NULL, 'i-lucide-ear-off', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (572, NULL, NULL, NULL, NULL, NULL, 'i-lucide-earth', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (573, NULL, NULL, NULL, NULL, NULL, 'i-lucide-earth-lock', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (574, NULL, NULL, NULL, NULL, NULL, 'i-lucide-eclipse', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (575, NULL, NULL, NULL, NULL, NULL, 'i-lucide-egg', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (576, NULL, NULL, NULL, NULL, NULL, 'i-lucide-egg-fried', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (577, NULL, NULL, NULL, NULL, NULL, 'i-lucide-egg-off', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (578, NULL, NULL, NULL, NULL, NULL, 'i-lucide-ellipse', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (580, NULL, NULL, NULL, NULL, NULL, 'i-lucide-ellipsis-vertical', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (581, NULL, NULL, NULL, NULL, NULL, 'i-lucide-equal', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (582, NULL, NULL, NULL, NULL, NULL, 'i-lucide-equal-approximately', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (583, NULL, NULL, NULL, NULL, NULL, 'i-lucide-equal-not', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (584, NULL, NULL, NULL, NULL, NULL, 'i-lucide-eraser', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (585, NULL, NULL, NULL, NULL, NULL, 'i-lucide-ethernet-port', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (586, NULL, NULL, NULL, NULL, NULL, 'i-lucide-euro', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (587, NULL, NULL, NULL, NULL, NULL, 'i-lucide-ev-charger', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (588, NULL, NULL, NULL, NULL, NULL, 'i-lucide-expand', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (590, NULL, NULL, NULL, NULL, NULL, 'i-lucide-eye', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (591, NULL, NULL, NULL, NULL, NULL, 'i-lucide-eye-closed', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (592, NULL, NULL, NULL, NULL, NULL, 'i-lucide-eye-off', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (593, NULL, NULL, NULL, NULL, NULL, 'i-lucide-facebook', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (594, NULL, NULL, NULL, NULL, NULL, 'i-lucide-factory', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (595, NULL, NULL, NULL, NULL, NULL, 'i-lucide-fan', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (596, NULL, NULL, NULL, NULL, NULL, 'i-lucide-fast-forward', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (597, NULL, NULL, NULL, NULL, NULL, 'i-lucide-feather', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (598, NULL, NULL, NULL, NULL, NULL, 'i-lucide-fence', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (600, NULL, NULL, NULL, NULL, NULL, 'i-lucide-figma', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (601, NULL, NULL, NULL, NULL, NULL, 'i-lucide-file', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (602, NULL, NULL, NULL, NULL, NULL, 'i-lucide-file-archive', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (603, NULL, NULL, NULL, NULL, NULL, 'i-lucide-file-audio', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (604, NULL, NULL, NULL, NULL, NULL, 'i-lucide-file-audio-2', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (605, NULL, NULL, NULL, NULL, NULL, 'i-lucide-file-axis-3d', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (606, NULL, NULL, NULL, NULL, NULL, 'i-lucide-file-badge', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (607, NULL, NULL, NULL, NULL, NULL, 'i-lucide-file-badge-2', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (608, NULL, NULL, NULL, NULL, NULL, 'i-lucide-file-box', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (609, NULL, NULL, NULL, NULL, NULL, 'i-lucide-file-braces', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (611, NULL, NULL, NULL, NULL, NULL, 'i-lucide-file-chart-column', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (612, NULL, NULL, NULL, NULL, NULL, 'i-lucide-file-chart-column-increasing', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (613, NULL, NULL, NULL, NULL, NULL, 'i-lucide-file-chart-line', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (614, NULL, NULL, NULL, NULL, NULL, 'i-lucide-file-chart-pie', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (615, NULL, NULL, NULL, NULL, NULL, 'i-lucide-file-check', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (616, NULL, NULL, NULL, NULL, NULL, 'i-lucide-file-check-2', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (617, NULL, NULL, NULL, NULL, NULL, 'i-lucide-file-check-corner', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (618, NULL, NULL, NULL, NULL, NULL, 'i-lucide-file-clock', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (619, NULL, NULL, NULL, NULL, NULL, 'i-lucide-file-code', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (621, NULL, NULL, NULL, NULL, NULL, 'i-lucide-file-code-corner', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (622, NULL, NULL, NULL, NULL, NULL, 'i-lucide-file-cog', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (623, NULL, NULL, NULL, NULL, NULL, 'i-lucide-file-diff', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (624, NULL, NULL, NULL, NULL, NULL, 'i-lucide-file-digit', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (625, NULL, NULL, NULL, NULL, NULL, 'i-lucide-file-down', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (627, NULL, NULL, NULL, NULL, NULL, 'i-lucide-file-headphone', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (628, NULL, NULL, NULL, NULL, NULL, 'i-lucide-file-heart', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (629, NULL, NULL, NULL, NULL, NULL, 'i-lucide-file-image', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (630, NULL, NULL, NULL, NULL, NULL, 'i-lucide-file-input', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (631, NULL, NULL, NULL, NULL, NULL, 'i-lucide-file-json', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (632, NULL, NULL, NULL, NULL, NULL, 'i-lucide-file-json-2', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (633, NULL, NULL, NULL, NULL, NULL, 'i-lucide-file-key', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (635, NULL, NULL, NULL, NULL, NULL, 'i-lucide-file-lock', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (636, NULL, NULL, NULL, NULL, NULL, 'i-lucide-file-lock-2', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (637, NULL, NULL, NULL, NULL, NULL, 'i-lucide-file-minus', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (638, NULL, NULL, NULL, NULL, NULL, 'i-lucide-file-minus-2', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (639, NULL, NULL, NULL, NULL, NULL, 'i-lucide-file-minus-corner', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (640, NULL, NULL, NULL, NULL, NULL, 'i-lucide-file-music', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (642, NULL, NULL, NULL, NULL, NULL, 'i-lucide-file-pen', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (643, NULL, NULL, NULL, NULL, NULL, 'i-lucide-file-pen-line', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (644, NULL, NULL, NULL, NULL, NULL, 'i-lucide-file-pie-chart', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (645, NULL, NULL, NULL, NULL, NULL, 'i-lucide-file-play', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (646, NULL, NULL, NULL, NULL, NULL, 'i-lucide-file-plus', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (647, NULL, NULL, NULL, NULL, NULL, 'i-lucide-file-plus-2', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (649, NULL, NULL, NULL, NULL, NULL, 'i-lucide-file-question-mark', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (650, NULL, NULL, NULL, NULL, NULL, 'i-lucide-file-scan', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (651, NULL, NULL, NULL, NULL, NULL, 'i-lucide-file-search', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (652, NULL, NULL, NULL, NULL, NULL, 'i-lucide-file-search-2', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (653, NULL, NULL, NULL, NULL, NULL, 'i-lucide-file-search-corner', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (654, NULL, NULL, NULL, NULL, NULL, 'i-lucide-file-signal', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (655, NULL, NULL, NULL, NULL, NULL, 'i-lucide-file-sliders', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (656, NULL, NULL, NULL, NULL, NULL, 'i-lucide-file-spreadsheet', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (658, NULL, NULL, NULL, NULL, NULL, 'i-lucide-file-symlink', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (659, NULL, NULL, NULL, NULL, NULL, 'i-lucide-file-terminal', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (660, NULL, NULL, NULL, NULL, NULL, 'i-lucide-file-text', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (661, NULL, NULL, NULL, NULL, NULL, 'i-lucide-file-type', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (662, NULL, NULL, NULL, NULL, NULL, 'i-lucide-file-type-2', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (663, NULL, NULL, NULL, NULL, NULL, 'i-lucide-file-type-corner', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (664, NULL, NULL, NULL, NULL, NULL, 'i-lucide-file-up', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (665, NULL, NULL, NULL, NULL, NULL, 'i-lucide-file-user', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (667, NULL, NULL, NULL, NULL, NULL, 'i-lucide-file-volume', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (668, NULL, NULL, NULL, NULL, NULL, 'i-lucide-file-volume-2', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (669, NULL, NULL, NULL, NULL, NULL, 'i-lucide-file-warning', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (670, NULL, NULL, NULL, NULL, NULL, 'i-lucide-file-x', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (671, NULL, NULL, NULL, NULL, NULL, 'i-lucide-file-x-2', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (672, NULL, NULL, NULL, NULL, NULL, 'i-lucide-file-x-corner', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (673, NULL, NULL, NULL, NULL, NULL, 'i-lucide-files', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (674, NULL, NULL, NULL, NULL, NULL, 'i-lucide-film', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (675, NULL, NULL, NULL, NULL, NULL, 'i-lucide-filter', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (677, NULL, NULL, NULL, NULL, NULL, 'i-lucide-fingerprint-pattern', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (678, NULL, NULL, NULL, NULL, NULL, 'i-lucide-fire-extinguisher', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (679, NULL, NULL, NULL, NULL, NULL, 'i-lucide-fish', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (680, NULL, NULL, NULL, NULL, NULL, 'i-lucide-fish-off', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (681, NULL, NULL, NULL, NULL, NULL, 'i-lucide-fish-symbol', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (683, NULL, NULL, NULL, NULL, NULL, 'i-lucide-fishing-rod', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (684, NULL, NULL, NULL, NULL, NULL, 'i-lucide-flag', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (685, NULL, NULL, NULL, NULL, NULL, 'i-lucide-flag-off', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (686, NULL, NULL, NULL, NULL, NULL, 'i-lucide-flag-triangle-left', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (687, NULL, NULL, NULL, NULL, NULL, 'i-lucide-flag-triangle-right', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (688, NULL, NULL, NULL, NULL, NULL, 'i-lucide-flame', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (689, NULL, NULL, NULL, NULL, NULL, 'i-lucide-flame-kindling', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (690, NULL, NULL, NULL, NULL, NULL, 'i-lucide-flashlight', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (692, NULL, NULL, NULL, NULL, NULL, 'i-lucide-flask-conical', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (693, NULL, NULL, NULL, NULL, NULL, 'i-lucide-flask-conical-off', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (694, NULL, NULL, NULL, NULL, NULL, 'i-lucide-flask-round', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (695, NULL, NULL, NULL, NULL, NULL, 'i-lucide-flip-horizontal-2', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (696, NULL, NULL, NULL, NULL, NULL, 'i-lucide-flip-vertical-2', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (697, NULL, NULL, NULL, NULL, NULL, 'i-lucide-flower', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (698, NULL, NULL, NULL, NULL, NULL, 'i-lucide-flower-2', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (699, NULL, NULL, NULL, NULL, NULL, 'i-lucide-focus', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (701, NULL, NULL, NULL, NULL, NULL, 'i-lucide-fold-vertical', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (702, NULL, NULL, NULL, NULL, NULL, 'i-lucide-folder', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (703, NULL, NULL, NULL, NULL, NULL, 'i-lucide-folder-archive', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (704, NULL, NULL, NULL, NULL, NULL, 'i-lucide-folder-check', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (705, NULL, NULL, NULL, NULL, NULL, 'i-lucide-folder-clock', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (706, NULL, NULL, NULL, NULL, NULL, 'i-lucide-folder-closed', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (707, NULL, NULL, NULL, NULL, NULL, 'i-lucide-folder-code', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (708, NULL, NULL, NULL, NULL, NULL, 'i-lucide-folder-cog', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (709, NULL, NULL, NULL, NULL, NULL, 'i-lucide-folder-dot', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (710, NULL, NULL, NULL, NULL, NULL, 'i-lucide-folder-down', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (712, NULL, NULL, NULL, NULL, NULL, 'i-lucide-folder-git-2', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (713, NULL, NULL, NULL, NULL, NULL, 'i-lucide-folder-heart', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (714, NULL, NULL, NULL, NULL, NULL, 'i-lucide-folder-input', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (715, NULL, NULL, NULL, NULL, NULL, 'i-lucide-folder-kanban', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (716, NULL, NULL, NULL, NULL, NULL, 'i-lucide-folder-key', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (717, NULL, NULL, NULL, NULL, NULL, 'i-lucide-folder-lock', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (718, NULL, NULL, NULL, NULL, NULL, 'i-lucide-folder-minus', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (719, NULL, NULL, NULL, NULL, NULL, 'i-lucide-folder-open', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (720, NULL, NULL, NULL, NULL, NULL, 'i-lucide-folder-open-dot', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (721, NULL, NULL, NULL, NULL, NULL, 'i-lucide-folder-output', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1083, NULL, NULL, NULL, NULL, NULL, 'i-lucide-move', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (723, NULL, NULL, NULL, NULL, NULL, 'i-lucide-folder-plus', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (724, NULL, NULL, NULL, NULL, NULL, 'i-lucide-folder-root', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (725, NULL, NULL, NULL, NULL, NULL, 'i-lucide-folder-search', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (726, NULL, NULL, NULL, NULL, NULL, 'i-lucide-folder-search-2', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (727, NULL, NULL, NULL, NULL, NULL, 'i-lucide-folder-symlink', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (728, NULL, NULL, NULL, NULL, NULL, 'i-lucide-folder-sync', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (729, NULL, NULL, NULL, NULL, NULL, 'i-lucide-folder-tree', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (730, NULL, NULL, NULL, NULL, NULL, 'i-lucide-folder-up', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (732, NULL, NULL, NULL, NULL, NULL, 'i-lucide-folders', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (733, NULL, NULL, NULL, NULL, NULL, 'i-lucide-footprints', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (734, NULL, NULL, NULL, NULL, NULL, 'i-lucide-forklift', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (735, NULL, NULL, NULL, NULL, NULL, 'i-lucide-form', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (736, NULL, NULL, NULL, NULL, NULL, 'i-lucide-forward', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (737, NULL, NULL, NULL, NULL, NULL, 'i-lucide-frame', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (738, NULL, NULL, NULL, NULL, NULL, 'i-lucide-framer', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (739, NULL, NULL, NULL, NULL, NULL, 'i-lucide-frown', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (740, NULL, NULL, NULL, NULL, NULL, 'i-lucide-fuel', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (741, NULL, NULL, NULL, NULL, NULL, 'i-lucide-fullscreen', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (743, NULL, NULL, NULL, NULL, NULL, 'i-lucide-funnel-plus', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (744, NULL, NULL, NULL, NULL, NULL, 'i-lucide-funnel-x', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (745, NULL, NULL, NULL, NULL, NULL, 'i-lucide-gallery-horizontal', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (746, NULL, NULL, NULL, NULL, NULL, 'i-lucide-gallery-horizontal-end', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (747, NULL, NULL, NULL, NULL, NULL, 'i-lucide-gallery-thumbnails', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (749, NULL, NULL, NULL, NULL, NULL, 'i-lucide-gallery-vertical-end', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (750, NULL, NULL, NULL, NULL, NULL, 'i-lucide-gamepad', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (751, NULL, NULL, NULL, NULL, NULL, 'i-lucide-gamepad-2', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (752, NULL, NULL, NULL, NULL, NULL, 'i-lucide-gamepad-directional', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (753, NULL, NULL, NULL, NULL, NULL, 'i-lucide-gauge', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (754, NULL, NULL, NULL, NULL, NULL, 'i-lucide-gavel', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (755, NULL, NULL, NULL, NULL, NULL, 'i-lucide-gem', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (756, NULL, NULL, NULL, NULL, NULL, 'i-lucide-georgian-lari', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (757, NULL, NULL, NULL, NULL, NULL, 'i-lucide-ghost', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (758, NULL, NULL, NULL, NULL, NULL, 'i-lucide-gift', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (759, NULL, NULL, NULL, NULL, NULL, 'i-lucide-git-branch', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (761, NULL, NULL, NULL, NULL, NULL, 'i-lucide-git-branch-plus', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (762, NULL, NULL, NULL, NULL, NULL, 'i-lucide-git-commit-horizontal', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (763, NULL, NULL, NULL, NULL, NULL, 'i-lucide-git-commit-vertical', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (764, NULL, NULL, NULL, NULL, NULL, 'i-lucide-git-compare', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (765, NULL, NULL, NULL, NULL, NULL, 'i-lucide-git-compare-arrows', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (766, NULL, NULL, NULL, NULL, NULL, 'i-lucide-git-fork', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (767, NULL, NULL, NULL, NULL, NULL, 'i-lucide-git-graph', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (768, NULL, NULL, NULL, NULL, NULL, 'i-lucide-git-merge', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (770, NULL, NULL, NULL, NULL, NULL, 'i-lucide-git-pull-request', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (771, NULL, NULL, NULL, NULL, NULL, 'i-lucide-git-pull-request-arrow', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (772, NULL, NULL, NULL, NULL, NULL, 'i-lucide-git-pull-request-closed', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (773, NULL, NULL, NULL, NULL, NULL, 'i-lucide-git-pull-request-create', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (774, NULL, NULL, NULL, NULL, NULL, 'i-lucide-git-pull-request-create-arrow', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (775, NULL, NULL, NULL, NULL, NULL, 'i-lucide-git-pull-request-draft', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (776, NULL, NULL, NULL, NULL, NULL, 'i-lucide-github', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (777, NULL, NULL, NULL, NULL, NULL, 'i-lucide-gitlab', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (778, NULL, NULL, NULL, NULL, NULL, 'i-lucide-glass-water', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (779, NULL, NULL, NULL, NULL, NULL, 'i-lucide-glasses', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (780, NULL, NULL, NULL, NULL, NULL, 'i-lucide-globe', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (781, NULL, NULL, NULL, NULL, NULL, 'i-lucide-globe-lock', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (783, NULL, NULL, NULL, NULL, NULL, 'i-lucide-globe-x', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (784, NULL, NULL, NULL, NULL, NULL, 'i-lucide-goal', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (785, NULL, NULL, NULL, NULL, NULL, 'i-lucide-gpu', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (786, NULL, NULL, NULL, NULL, NULL, 'i-lucide-graduation-cap', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (787, NULL, NULL, NULL, NULL, NULL, 'i-lucide-grape', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (788, NULL, NULL, NULL, NULL, NULL, 'i-lucide-grid-2x2', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (790, NULL, NULL, NULL, NULL, NULL, 'i-lucide-grid-2x2-plus', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (792, NULL, NULL, NULL, NULL, NULL, 'i-lucide-grid-3x2', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (793, NULL, NULL, NULL, NULL, NULL, 'i-lucide-grid-3x3', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (794, NULL, NULL, NULL, NULL, NULL, 'i-lucide-grip', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (795, NULL, NULL, NULL, NULL, NULL, 'i-lucide-grip-horizontal', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (796, NULL, NULL, NULL, NULL, NULL, 'i-lucide-grip-vertical', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (797, NULL, NULL, NULL, NULL, NULL, 'i-lucide-group', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (798, NULL, NULL, NULL, NULL, NULL, 'i-lucide-guitar', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (799, NULL, NULL, NULL, NULL, NULL, 'i-lucide-ham', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (800, NULL, NULL, NULL, NULL, NULL, 'i-lucide-hamburger', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (801, NULL, NULL, NULL, NULL, NULL, 'i-lucide-hammer', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (803, NULL, NULL, NULL, NULL, NULL, 'i-lucide-hand-coins', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (804, NULL, NULL, NULL, NULL, NULL, 'i-lucide-hand-fist', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (805, NULL, NULL, NULL, NULL, NULL, 'i-lucide-hand-grab', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (806, NULL, NULL, NULL, NULL, NULL, 'i-lucide-hand-heart', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (807, NULL, NULL, NULL, NULL, NULL, 'i-lucide-hand-helping', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (808, NULL, NULL, NULL, NULL, NULL, 'i-lucide-hand-metal', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (810, NULL, NULL, NULL, NULL, NULL, 'i-lucide-handbag', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (811, NULL, NULL, NULL, NULL, NULL, 'i-lucide-handshake', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (812, NULL, NULL, NULL, NULL, NULL, 'i-lucide-hard-drive', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (813, NULL, NULL, NULL, NULL, NULL, 'i-lucide-hard-drive-download', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (814, NULL, NULL, NULL, NULL, NULL, 'i-lucide-hard-drive-upload', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (815, NULL, NULL, NULL, NULL, NULL, 'i-lucide-hard-hat', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (816, NULL, NULL, NULL, NULL, NULL, 'i-lucide-hash', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (818, NULL, NULL, NULL, NULL, NULL, 'i-lucide-haze', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (819, NULL, NULL, NULL, NULL, NULL, 'i-lucide-hd', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (820, NULL, NULL, NULL, NULL, NULL, 'i-lucide-hdmi-port', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (821, NULL, NULL, NULL, NULL, NULL, 'i-lucide-heading', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (822, NULL, NULL, NULL, NULL, NULL, 'i-lucide-heading-1', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (823, NULL, NULL, NULL, NULL, NULL, 'i-lucide-heading-2', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (824, NULL, NULL, NULL, NULL, NULL, 'i-lucide-heading-3', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (826, NULL, NULL, NULL, NULL, NULL, 'i-lucide-heading-5', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (827, NULL, NULL, NULL, NULL, NULL, 'i-lucide-heading-6', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (828, NULL, NULL, NULL, NULL, NULL, 'i-lucide-headphone-off', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (829, NULL, NULL, NULL, NULL, NULL, 'i-lucide-headphones', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (830, NULL, NULL, NULL, NULL, NULL, 'i-lucide-headset', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (831, NULL, NULL, NULL, NULL, NULL, 'i-lucide-heart', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (832, NULL, NULL, NULL, NULL, NULL, 'i-lucide-heart-crack', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (834, NULL, NULL, NULL, NULL, NULL, 'i-lucide-heart-minus', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (835, NULL, NULL, NULL, NULL, NULL, 'i-lucide-heart-off', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (836, NULL, NULL, NULL, NULL, NULL, 'i-lucide-heart-plus', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (837, NULL, NULL, NULL, NULL, NULL, 'i-lucide-heart-pulse', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (838, NULL, NULL, NULL, NULL, NULL, 'i-lucide-heater', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (839, NULL, NULL, NULL, NULL, NULL, 'i-lucide-helicopter', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (840, NULL, NULL, NULL, NULL, NULL, 'i-lucide-hexagon', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (842, NULL, NULL, NULL, NULL, NULL, 'i-lucide-history', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (843, NULL, NULL, NULL, NULL, NULL, 'i-lucide-hop', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (844, NULL, NULL, NULL, NULL, NULL, 'i-lucide-hop-off', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (845, NULL, NULL, NULL, NULL, NULL, 'i-lucide-hospital', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (847, NULL, NULL, NULL, NULL, NULL, 'i-lucide-hourglass', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (848, NULL, NULL, NULL, NULL, NULL, 'i-lucide-house', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (849, NULL, NULL, NULL, NULL, NULL, 'i-lucide-house-heart', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (851, NULL, NULL, NULL, NULL, NULL, 'i-lucide-house-plus', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (852, NULL, NULL, NULL, NULL, NULL, 'i-lucide-house-wifi', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (853, NULL, NULL, NULL, NULL, NULL, 'i-lucide-ice-cream-bowl', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (854, NULL, NULL, NULL, NULL, NULL, 'i-lucide-ice-cream-cone', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (855, NULL, NULL, NULL, NULL, NULL, 'i-lucide-id-card', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (856, NULL, NULL, NULL, NULL, NULL, 'i-lucide-id-card-lanyard', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (857, NULL, NULL, NULL, NULL, NULL, 'i-lucide-image', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (858, NULL, NULL, NULL, NULL, NULL, 'i-lucide-image-down', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (859, NULL, NULL, NULL, NULL, NULL, 'i-lucide-image-minus', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (861, NULL, NULL, NULL, NULL, NULL, 'i-lucide-image-play', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (862, NULL, NULL, NULL, NULL, NULL, 'i-lucide-image-plus', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (863, NULL, NULL, NULL, NULL, NULL, 'i-lucide-image-up', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (864, NULL, NULL, NULL, NULL, NULL, 'i-lucide-image-upscale', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (865, NULL, NULL, NULL, NULL, NULL, 'i-lucide-images', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (866, NULL, NULL, NULL, NULL, NULL, 'i-lucide-import', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (867, NULL, NULL, NULL, NULL, NULL, 'i-lucide-inbox', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (868, NULL, NULL, NULL, NULL, NULL, 'i-lucide-indent-decrease', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (869, NULL, NULL, NULL, NULL, NULL, 'i-lucide-indent-increase', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (871, NULL, NULL, NULL, NULL, NULL, 'i-lucide-infinity', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (872, NULL, NULL, NULL, NULL, NULL, 'i-lucide-info', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (873, NULL, NULL, NULL, NULL, NULL, 'i-lucide-inspection-panel', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (874, NULL, NULL, NULL, NULL, NULL, 'i-lucide-instagram', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (875, NULL, NULL, NULL, NULL, NULL, 'i-lucide-italic', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (876, NULL, NULL, NULL, NULL, NULL, 'i-lucide-iteration-ccw', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (877, NULL, NULL, NULL, NULL, NULL, 'i-lucide-iteration-cw', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (878, NULL, NULL, NULL, NULL, NULL, 'i-lucide-japanese-yen', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (879, NULL, NULL, NULL, NULL, NULL, 'i-lucide-joystick', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (880, NULL, NULL, NULL, NULL, NULL, 'i-lucide-kanban', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (881, NULL, NULL, NULL, NULL, NULL, 'i-lucide-kayak', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (882, NULL, NULL, NULL, NULL, NULL, 'i-lucide-key', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (884, NULL, NULL, NULL, NULL, NULL, 'i-lucide-key-square', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (885, NULL, NULL, NULL, NULL, NULL, 'i-lucide-keyboard', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (886, NULL, NULL, NULL, NULL, NULL, 'i-lucide-keyboard-music', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (887, NULL, NULL, NULL, NULL, NULL, 'i-lucide-keyboard-off', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (888, NULL, NULL, NULL, NULL, NULL, 'i-lucide-lamp', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (889, NULL, NULL, NULL, NULL, NULL, 'i-lucide-lamp-ceiling', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (890, NULL, NULL, NULL, NULL, NULL, 'i-lucide-lamp-desk', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (892, NULL, NULL, NULL, NULL, NULL, 'i-lucide-lamp-wall-down', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (893, NULL, NULL, NULL, NULL, NULL, 'i-lucide-lamp-wall-up', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (894, NULL, NULL, NULL, NULL, NULL, 'i-lucide-land-plot', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (895, NULL, NULL, NULL, NULL, NULL, 'i-lucide-landmark', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (896, NULL, NULL, NULL, NULL, NULL, 'i-lucide-languages', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (897, NULL, NULL, NULL, NULL, NULL, 'i-lucide-laptop', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (898, NULL, NULL, NULL, NULL, NULL, 'i-lucide-laptop-minimal', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (900, NULL, NULL, NULL, NULL, NULL, 'i-lucide-lasso', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (901, NULL, NULL, NULL, NULL, NULL, 'i-lucide-lasso-select', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (902, NULL, NULL, NULL, NULL, NULL, 'i-lucide-laugh', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (903, NULL, NULL, NULL, NULL, NULL, 'i-lucide-layers', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (904, NULL, NULL, NULL, NULL, NULL, 'i-lucide-layers-2', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (905, NULL, NULL, NULL, NULL, NULL, 'i-lucide-layers-3', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1001, NULL, NULL, NULL, NULL, NULL, 'i-lucide-maximize', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1002, NULL, NULL, NULL, NULL, NULL, 'i-lucide-maximize-2', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1003, NULL, NULL, NULL, NULL, NULL, 'i-lucide-medal', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1004, NULL, NULL, NULL, NULL, NULL, 'i-lucide-megaphone', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1005, NULL, NULL, NULL, NULL, NULL, 'i-lucide-megaphone-off', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1006, NULL, NULL, NULL, NULL, NULL, 'i-lucide-meh', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1007, NULL, NULL, NULL, NULL, NULL, 'i-lucide-memory-stick', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1008, NULL, NULL, NULL, NULL, NULL, 'i-lucide-menu', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1009, NULL, NULL, NULL, NULL, NULL, 'i-lucide-merge', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1010, NULL, NULL, NULL, NULL, NULL, 'i-lucide-message-circle', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1011, NULL, NULL, NULL, NULL, NULL, 'i-lucide-message-circle-check', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1012, NULL, NULL, NULL, NULL, NULL, 'i-lucide-message-circle-code', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1013, NULL, NULL, NULL, NULL, NULL, 'i-lucide-message-circle-dashed', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1014, NULL, NULL, NULL, NULL, NULL, 'i-lucide-message-circle-heart', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1015, NULL, NULL, NULL, NULL, NULL, 'i-lucide-message-circle-more', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1016, NULL, NULL, NULL, NULL, NULL, 'i-lucide-message-circle-off', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1017, NULL, NULL, NULL, NULL, NULL, 'i-lucide-message-circle-plus', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1018, NULL, NULL, NULL, NULL, NULL, 'i-lucide-message-circle-question-mark', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (907, NULL, NULL, NULL, NULL, NULL, 'i-lucide-layout-dashboard', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (908, NULL, NULL, NULL, NULL, NULL, 'i-lucide-layout-grid', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (909, NULL, NULL, NULL, NULL, NULL, 'i-lucide-layout-list', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (910, NULL, NULL, NULL, NULL, NULL, 'i-lucide-layout-panel-left', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (912, NULL, NULL, NULL, NULL, NULL, 'i-lucide-layout-template', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (913, NULL, NULL, NULL, NULL, NULL, 'i-lucide-leaf', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (914, NULL, NULL, NULL, NULL, NULL, 'i-lucide-leafy-green', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (915, NULL, NULL, NULL, NULL, NULL, 'i-lucide-lectern', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (916, NULL, NULL, NULL, NULL, NULL, 'i-lucide-lens-concave', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (917, NULL, NULL, NULL, NULL, NULL, 'i-lucide-lens-convex', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (918, NULL, NULL, NULL, NULL, NULL, 'i-lucide-letter-text', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (919, NULL, NULL, NULL, NULL, NULL, 'i-lucide-library', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (920, NULL, NULL, NULL, NULL, NULL, 'i-lucide-library-big', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (921, NULL, NULL, NULL, NULL, NULL, 'i-lucide-life-buoy', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (923, NULL, NULL, NULL, NULL, NULL, 'i-lucide-lightbulb', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (924, NULL, NULL, NULL, NULL, NULL, 'i-lucide-lightbulb-off', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (925, NULL, NULL, NULL, NULL, NULL, 'i-lucide-line-chart', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (926, NULL, NULL, NULL, NULL, NULL, 'i-lucide-line-dot-right-horizontal', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (927, NULL, NULL, NULL, NULL, NULL, 'i-lucide-line-squiggle', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (928, NULL, NULL, NULL, NULL, NULL, 'i-lucide-line-style', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (929, NULL, NULL, NULL, NULL, NULL, 'i-lucide-link', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (930, NULL, NULL, NULL, NULL, NULL, 'i-lucide-link-2', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (932, NULL, NULL, NULL, NULL, NULL, 'i-lucide-linkedin', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (933, NULL, NULL, NULL, NULL, NULL, 'i-lucide-list', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (934, NULL, NULL, NULL, NULL, NULL, 'i-lucide-list-check', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (935, NULL, NULL, NULL, NULL, NULL, 'i-lucide-list-checks', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (936, NULL, NULL, NULL, NULL, NULL, 'i-lucide-list-chevrons-down-up', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (937, NULL, NULL, NULL, NULL, NULL, 'i-lucide-list-chevrons-up-down', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (938, NULL, NULL, NULL, NULL, NULL, 'i-lucide-list-collapse', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (939, NULL, NULL, NULL, NULL, NULL, 'i-lucide-list-end', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (941, NULL, NULL, NULL, NULL, NULL, 'i-lucide-list-filter-plus', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (942, NULL, NULL, NULL, NULL, NULL, 'i-lucide-list-indent-decrease', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (943, NULL, NULL, NULL, NULL, NULL, 'i-lucide-list-indent-increase', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (944, NULL, NULL, NULL, NULL, NULL, 'i-lucide-list-minus', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (945, NULL, NULL, NULL, NULL, NULL, 'i-lucide-list-music', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (946, NULL, NULL, NULL, NULL, NULL, 'i-lucide-list-ordered', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (947, NULL, NULL, NULL, NULL, NULL, 'i-lucide-list-plus', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (949, NULL, NULL, NULL, NULL, NULL, 'i-lucide-list-start', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (950, NULL, NULL, NULL, NULL, NULL, 'i-lucide-list-todo', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (951, NULL, NULL, NULL, NULL, NULL, 'i-lucide-list-tree', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (952, NULL, NULL, NULL, NULL, NULL, 'i-lucide-list-video', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (953, NULL, NULL, NULL, NULL, NULL, 'i-lucide-list-x', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (954, NULL, NULL, NULL, NULL, NULL, 'i-lucide-loader', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (955, NULL, NULL, NULL, NULL, NULL, 'i-lucide-loader-circle', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (956, NULL, NULL, NULL, NULL, NULL, 'i-lucide-loader-pinwheel', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (957, NULL, NULL, NULL, NULL, NULL, 'i-lucide-locate', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (958, NULL, NULL, NULL, NULL, NULL, 'i-lucide-locate-fixed', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (960, NULL, NULL, NULL, NULL, NULL, 'i-lucide-lock', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (961, NULL, NULL, NULL, NULL, NULL, 'i-lucide-lock-keyhole', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (962, NULL, NULL, NULL, NULL, NULL, 'i-lucide-lock-keyhole-open', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (963, NULL, NULL, NULL, NULL, NULL, 'i-lucide-lock-open', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (964, NULL, NULL, NULL, NULL, NULL, 'i-lucide-log-in', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (965, NULL, NULL, NULL, NULL, NULL, 'i-lucide-log-out', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (966, NULL, NULL, NULL, NULL, NULL, 'i-lucide-logs', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (967, NULL, NULL, NULL, NULL, NULL, 'i-lucide-lollipop', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (968, NULL, NULL, NULL, NULL, NULL, 'i-lucide-luggage', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (969, NULL, NULL, NULL, NULL, NULL, 'i-lucide-magnet', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (970, NULL, NULL, NULL, NULL, NULL, 'i-lucide-mail', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (972, NULL, NULL, NULL, NULL, NULL, 'i-lucide-mail-minus', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (973, NULL, NULL, NULL, NULL, NULL, 'i-lucide-mail-open', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (974, NULL, NULL, NULL, NULL, NULL, 'i-lucide-mail-plus', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (975, NULL, NULL, NULL, NULL, NULL, 'i-lucide-mail-question-mark', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (976, NULL, NULL, NULL, NULL, NULL, 'i-lucide-mail-search', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (978, NULL, NULL, NULL, NULL, NULL, 'i-lucide-mail-x', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (979, NULL, NULL, NULL, NULL, NULL, 'i-lucide-mailbox', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (980, NULL, NULL, NULL, NULL, NULL, 'i-lucide-mails', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (981, NULL, NULL, NULL, NULL, NULL, 'i-lucide-map', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (982, NULL, NULL, NULL, NULL, NULL, 'i-lucide-map-minus', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (983, NULL, NULL, NULL, NULL, NULL, 'i-lucide-map-pin', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (984, NULL, NULL, NULL, NULL, NULL, 'i-lucide-map-pin-check', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (985, NULL, NULL, NULL, NULL, NULL, 'i-lucide-map-pin-check-inside', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (986, NULL, NULL, NULL, NULL, NULL, 'i-lucide-map-pin-house', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (987, NULL, NULL, NULL, NULL, NULL, 'i-lucide-map-pin-minus', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (988, NULL, NULL, NULL, NULL, NULL, 'i-lucide-map-pin-minus-inside', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (989, NULL, NULL, NULL, NULL, NULL, 'i-lucide-map-pin-off', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (991, NULL, NULL, NULL, NULL, NULL, 'i-lucide-map-pin-plus', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (992, NULL, NULL, NULL, NULL, NULL, 'i-lucide-map-pin-plus-inside', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (993, NULL, NULL, NULL, NULL, NULL, 'i-lucide-map-pin-search', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (994, NULL, NULL, NULL, NULL, NULL, 'i-lucide-map-pin-x', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (995, NULL, NULL, NULL, NULL, NULL, 'i-lucide-map-pin-x-inside', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (996, NULL, NULL, NULL, NULL, NULL, 'i-lucide-map-pinned', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (997, NULL, NULL, NULL, NULL, NULL, 'i-lucide-map-plus', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (998, NULL, NULL, NULL, NULL, NULL, 'i-lucide-mars', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1000, NULL, NULL, NULL, NULL, NULL, 'i-lucide-martini', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1019, NULL, NULL, NULL, NULL, NULL, 'i-lucide-message-circle-reply', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1020, NULL, NULL, NULL, NULL, NULL, 'i-lucide-message-circle-warning', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1021, NULL, NULL, NULL, NULL, NULL, 'i-lucide-message-circle-x', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1022, NULL, NULL, NULL, NULL, NULL, 'i-lucide-message-square', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1023, NULL, NULL, NULL, NULL, NULL, 'i-lucide-message-square-check', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1024, NULL, NULL, NULL, NULL, NULL, 'i-lucide-message-square-code', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1025, NULL, NULL, NULL, NULL, NULL, 'i-lucide-message-square-dashed', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1026, NULL, NULL, NULL, NULL, NULL, 'i-lucide-message-square-diff', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1027, NULL, NULL, NULL, NULL, NULL, 'i-lucide-message-square-dot', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1028, NULL, NULL, NULL, NULL, NULL, 'i-lucide-message-square-heart', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1029, NULL, NULL, NULL, NULL, NULL, 'i-lucide-message-square-lock', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1030, NULL, NULL, NULL, NULL, NULL, 'i-lucide-message-square-more', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1031, NULL, NULL, NULL, NULL, NULL, 'i-lucide-message-square-off', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1032, NULL, NULL, NULL, NULL, NULL, 'i-lucide-message-square-plus', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1033, NULL, NULL, NULL, NULL, NULL, 'i-lucide-message-square-quote', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1034, NULL, NULL, NULL, NULL, NULL, 'i-lucide-message-square-reply', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1035, NULL, NULL, NULL, NULL, NULL, 'i-lucide-message-square-share', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1036, NULL, NULL, NULL, NULL, NULL, 'i-lucide-message-square-text', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1037, NULL, NULL, NULL, NULL, NULL, 'i-lucide-message-square-warning', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1038, NULL, NULL, NULL, NULL, NULL, 'i-lucide-message-square-x', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1039, NULL, NULL, NULL, NULL, NULL, 'i-lucide-messages-square', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1040, NULL, NULL, NULL, NULL, NULL, 'i-lucide-metronome', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1041, NULL, NULL, NULL, NULL, NULL, 'i-lucide-mic', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1042, NULL, NULL, NULL, NULL, NULL, 'i-lucide-mic-off', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1043, NULL, NULL, NULL, NULL, NULL, 'i-lucide-mic-vocal', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1044, NULL, NULL, NULL, NULL, NULL, 'i-lucide-microchip', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1045, NULL, NULL, NULL, NULL, NULL, 'i-lucide-microscope', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1046, NULL, NULL, NULL, NULL, NULL, 'i-lucide-microwave', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1047, NULL, NULL, NULL, NULL, NULL, 'i-lucide-milestone', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1048, NULL, NULL, NULL, NULL, NULL, 'i-lucide-milk', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1049, NULL, NULL, NULL, NULL, NULL, 'i-lucide-milk-off', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1050, NULL, NULL, NULL, NULL, NULL, 'i-lucide-minimize', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1051, NULL, NULL, NULL, NULL, NULL, 'i-lucide-minimize-2', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1052, NULL, NULL, NULL, NULL, NULL, 'i-lucide-minus', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1053, NULL, NULL, NULL, NULL, NULL, 'i-lucide-mirror-rectangular', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1054, NULL, NULL, NULL, NULL, NULL, 'i-lucide-mirror-round', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1055, NULL, NULL, NULL, NULL, NULL, 'i-lucide-monitor', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1056, NULL, NULL, NULL, NULL, NULL, 'i-lucide-monitor-check', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1057, NULL, NULL, NULL, NULL, NULL, 'i-lucide-monitor-cloud', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1058, NULL, NULL, NULL, NULL, NULL, 'i-lucide-monitor-cog', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1059, NULL, NULL, NULL, NULL, NULL, 'i-lucide-monitor-dot', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1060, NULL, NULL, NULL, NULL, NULL, 'i-lucide-monitor-down', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1061, NULL, NULL, NULL, NULL, NULL, 'i-lucide-monitor-off', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1062, NULL, NULL, NULL, NULL, NULL, 'i-lucide-monitor-pause', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1063, NULL, NULL, NULL, NULL, NULL, 'i-lucide-monitor-play', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1064, NULL, NULL, NULL, NULL, NULL, 'i-lucide-monitor-smartphone', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1065, NULL, NULL, NULL, NULL, NULL, 'i-lucide-monitor-speaker', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1066, NULL, NULL, NULL, NULL, NULL, 'i-lucide-monitor-stop', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1067, NULL, NULL, NULL, NULL, NULL, 'i-lucide-monitor-up', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1068, NULL, NULL, NULL, NULL, NULL, 'i-lucide-monitor-x', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1069, NULL, NULL, NULL, NULL, NULL, 'i-lucide-moon', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1070, NULL, NULL, NULL, NULL, NULL, 'i-lucide-moon-star', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1071, NULL, NULL, NULL, NULL, NULL, 'i-lucide-motorbike', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1072, NULL, NULL, NULL, NULL, NULL, 'i-lucide-mountain', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1073, NULL, NULL, NULL, NULL, NULL, 'i-lucide-mountain-snow', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1074, NULL, NULL, NULL, NULL, NULL, 'i-lucide-mouse', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1075, NULL, NULL, NULL, NULL, NULL, 'i-lucide-mouse-left', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1076, NULL, NULL, NULL, NULL, NULL, 'i-lucide-mouse-off', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1077, NULL, NULL, NULL, NULL, NULL, 'i-lucide-mouse-pointer', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1078, NULL, NULL, NULL, NULL, NULL, 'i-lucide-mouse-pointer-2', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1079, NULL, NULL, NULL, NULL, NULL, 'i-lucide-mouse-pointer-2-off', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1080, NULL, NULL, NULL, NULL, NULL, 'i-lucide-mouse-pointer-ban', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1081, NULL, NULL, NULL, NULL, NULL, 'i-lucide-mouse-pointer-click', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1082, NULL, NULL, NULL, NULL, NULL, 'i-lucide-mouse-right', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1084, NULL, NULL, NULL, NULL, NULL, 'i-lucide-move-3d', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1085, NULL, NULL, NULL, NULL, NULL, 'i-lucide-move-diagonal', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1086, NULL, NULL, NULL, NULL, NULL, 'i-lucide-move-diagonal-2', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1087, NULL, NULL, NULL, NULL, NULL, 'i-lucide-move-down', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1088, NULL, NULL, NULL, NULL, NULL, 'i-lucide-move-down-left', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1089, NULL, NULL, NULL, NULL, NULL, 'i-lucide-move-down-right', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1090, NULL, NULL, NULL, NULL, NULL, 'i-lucide-move-horizontal', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1091, NULL, NULL, NULL, NULL, NULL, 'i-lucide-move-left', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1092, NULL, NULL, NULL, NULL, NULL, 'i-lucide-move-right', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1093, NULL, NULL, NULL, NULL, NULL, 'i-lucide-move-up', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1094, NULL, NULL, NULL, NULL, NULL, 'i-lucide-move-up-left', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1095, NULL, NULL, NULL, NULL, NULL, 'i-lucide-move-up-right', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1096, NULL, NULL, NULL, NULL, NULL, 'i-lucide-move-vertical', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1097, NULL, NULL, NULL, NULL, NULL, 'i-lucide-music', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1098, NULL, NULL, NULL, NULL, NULL, 'i-lucide-music-2', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1099, NULL, NULL, NULL, NULL, NULL, 'i-lucide-music-3', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1100, NULL, NULL, NULL, NULL, NULL, 'i-lucide-music-4', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1101, NULL, NULL, NULL, NULL, NULL, 'i-lucide-navigation', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1102, NULL, NULL, NULL, NULL, NULL, 'i-lucide-navigation-2', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1103, NULL, NULL, NULL, NULL, NULL, 'i-lucide-navigation-2-off', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1104, NULL, NULL, NULL, NULL, NULL, 'i-lucide-navigation-off', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1105, NULL, NULL, NULL, NULL, NULL, 'i-lucide-network', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1106, NULL, NULL, NULL, NULL, NULL, 'i-lucide-newspaper', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1107, NULL, NULL, NULL, NULL, NULL, 'i-lucide-nfc', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1108, NULL, NULL, NULL, NULL, NULL, 'i-lucide-non-binary', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1109, NULL, NULL, NULL, NULL, NULL, 'i-lucide-notebook', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1110, NULL, NULL, NULL, NULL, NULL, 'i-lucide-notebook-pen', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1112, NULL, NULL, NULL, NULL, NULL, 'i-lucide-notebook-text', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1113, NULL, NULL, NULL, NULL, NULL, 'i-lucide-notepad-text', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1114, NULL, NULL, NULL, NULL, NULL, 'i-lucide-notepad-text-dashed', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1115, NULL, NULL, NULL, NULL, NULL, 'i-lucide-nut', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1116, NULL, NULL, NULL, NULL, NULL, 'i-lucide-nut-off', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1117, NULL, NULL, NULL, NULL, NULL, 'i-lucide-octagon', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1119, NULL, NULL, NULL, NULL, NULL, 'i-lucide-octagon-minus', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1120, NULL, NULL, NULL, NULL, NULL, 'i-lucide-octagon-pause', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1121, NULL, NULL, NULL, NULL, NULL, 'i-lucide-octagon-x', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1122, NULL, NULL, NULL, NULL, NULL, 'i-lucide-omega', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1123, NULL, NULL, NULL, NULL, NULL, 'i-lucide-option', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1124, NULL, NULL, NULL, NULL, NULL, 'i-lucide-orbit', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1125, NULL, NULL, NULL, NULL, NULL, 'i-lucide-origami', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1126, NULL, NULL, NULL, NULL, NULL, 'i-lucide-package', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1127, NULL, NULL, NULL, NULL, NULL, 'i-lucide-package-2', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1128, NULL, NULL, NULL, NULL, NULL, 'i-lucide-package-check', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1129, NULL, NULL, NULL, NULL, NULL, 'i-lucide-package-minus', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1130, NULL, NULL, NULL, NULL, NULL, 'i-lucide-package-open', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1132, NULL, NULL, NULL, NULL, NULL, 'i-lucide-package-search', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1134, NULL, NULL, NULL, NULL, NULL, 'i-lucide-paint-bucket', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1135, NULL, NULL, NULL, NULL, NULL, 'i-lucide-paint-roller', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1136, NULL, NULL, NULL, NULL, NULL, 'i-lucide-paintbrush', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1137, NULL, NULL, NULL, NULL, NULL, 'i-lucide-paintbrush-vertical', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1138, NULL, NULL, NULL, NULL, NULL, 'i-lucide-palette', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1139, NULL, NULL, NULL, NULL, NULL, 'i-lucide-panda', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1140, NULL, NULL, NULL, NULL, NULL, 'i-lucide-panel-bottom', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1141, NULL, NULL, NULL, NULL, NULL, 'i-lucide-panel-bottom-close', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1143, NULL, NULL, NULL, NULL, NULL, 'i-lucide-panel-bottom-open', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1144, NULL, NULL, NULL, NULL, NULL, 'i-lucide-panel-left', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1145, NULL, NULL, NULL, NULL, NULL, 'i-lucide-panel-left-close', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1146, NULL, NULL, NULL, NULL, NULL, 'i-lucide-panel-left-dashed', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1147, NULL, NULL, NULL, NULL, NULL, 'i-lucide-panel-left-open', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1148, NULL, NULL, NULL, NULL, NULL, 'i-lucide-panel-left-right-dashed', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1149, NULL, NULL, NULL, NULL, NULL, 'i-lucide-panel-right', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1150, NULL, NULL, NULL, NULL, NULL, 'i-lucide-panel-right-close', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1152, NULL, NULL, NULL, NULL, NULL, 'i-lucide-panel-right-open', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1153, NULL, NULL, NULL, NULL, NULL, 'i-lucide-panel-top', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1154, NULL, NULL, NULL, NULL, NULL, 'i-lucide-panel-top-bottom-dashed', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1155, NULL, NULL, NULL, NULL, NULL, 'i-lucide-panel-top-close', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1156, NULL, NULL, NULL, NULL, NULL, 'i-lucide-panel-top-dashed', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1157, NULL, NULL, NULL, NULL, NULL, 'i-lucide-panel-top-open', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1158, NULL, NULL, NULL, NULL, NULL, 'i-lucide-panels-left-bottom', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1159, NULL, NULL, NULL, NULL, NULL, 'i-lucide-panels-right-bottom', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1161, NULL, NULL, NULL, NULL, NULL, 'i-lucide-paperclip', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1162, NULL, NULL, NULL, NULL, NULL, 'i-lucide-parentheses', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1163, NULL, NULL, NULL, NULL, NULL, 'i-lucide-parking-meter', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1164, NULL, NULL, NULL, NULL, NULL, 'i-lucide-party-popper', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1165, NULL, NULL, NULL, NULL, NULL, 'i-lucide-pause', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1166, NULL, NULL, NULL, NULL, NULL, 'i-lucide-paw-print', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1167, NULL, NULL, NULL, NULL, NULL, 'i-lucide-pc-case', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1168, NULL, NULL, NULL, NULL, NULL, 'i-lucide-pen', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1169, NULL, NULL, NULL, NULL, NULL, 'i-lucide-pen-line', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1170, NULL, NULL, NULL, NULL, NULL, 'i-lucide-pen-off', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1172, NULL, NULL, NULL, NULL, NULL, 'i-lucide-pencil', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1173, NULL, NULL, NULL, NULL, NULL, 'i-lucide-pencil-line', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1174, NULL, NULL, NULL, NULL, NULL, 'i-lucide-pencil-off', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1175, NULL, NULL, NULL, NULL, NULL, 'i-lucide-pencil-ruler', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1176, NULL, NULL, NULL, NULL, NULL, 'i-lucide-pentagon', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1177, NULL, NULL, NULL, NULL, NULL, 'i-lucide-percent', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1178, NULL, NULL, NULL, NULL, NULL, 'i-lucide-person-standing', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1179, NULL, NULL, NULL, NULL, NULL, 'i-lucide-philippine-peso', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1180, NULL, NULL, NULL, NULL, NULL, 'i-lucide-phone', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1182, NULL, NULL, NULL, NULL, NULL, 'i-lucide-phone-forwarded', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1183, NULL, NULL, NULL, NULL, NULL, 'i-lucide-phone-incoming', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1184, NULL, NULL, NULL, NULL, NULL, 'i-lucide-phone-missed', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1185, NULL, NULL, NULL, NULL, NULL, 'i-lucide-phone-off', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1186, NULL, NULL, NULL, NULL, NULL, 'i-lucide-phone-outgoing', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1187, NULL, NULL, NULL, NULL, NULL, 'i-lucide-pi', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1188, NULL, NULL, NULL, NULL, NULL, 'i-lucide-piano', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1189, NULL, NULL, NULL, NULL, NULL, 'i-lucide-pickaxe', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1190, NULL, NULL, NULL, NULL, NULL, 'i-lucide-picture-in-picture', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1192, NULL, NULL, NULL, NULL, NULL, 'i-lucide-pie-chart', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1193, NULL, NULL, NULL, NULL, NULL, 'i-lucide-piggy-bank', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1194, NULL, NULL, NULL, NULL, NULL, 'i-lucide-pilcrow', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1195, NULL, NULL, NULL, NULL, NULL, 'i-lucide-pilcrow-left', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1196, NULL, NULL, NULL, NULL, NULL, 'i-lucide-pilcrow-right', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1197, NULL, NULL, NULL, NULL, NULL, 'i-lucide-pill', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1198, NULL, NULL, NULL, NULL, NULL, 'i-lucide-pill-bottle', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1199, NULL, NULL, NULL, NULL, NULL, 'i-lucide-pin', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1200, NULL, NULL, NULL, NULL, NULL, 'i-lucide-pin-off', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1201, NULL, NULL, NULL, NULL, NULL, 'i-lucide-pipette', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1202, NULL, NULL, NULL, NULL, NULL, 'i-lucide-pizza', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1203, NULL, NULL, NULL, NULL, NULL, 'i-lucide-plane', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1205, NULL, NULL, NULL, NULL, NULL, 'i-lucide-plane-takeoff', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1206, NULL, NULL, NULL, NULL, NULL, 'i-lucide-play', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1207, NULL, NULL, NULL, NULL, NULL, 'i-lucide-plug', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1208, NULL, NULL, NULL, NULL, NULL, 'i-lucide-plug-2', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1209, NULL, NULL, NULL, NULL, NULL, 'i-lucide-plug-zap', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1210, NULL, NULL, NULL, NULL, NULL, 'i-lucide-plus', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1211, NULL, NULL, NULL, NULL, NULL, 'i-lucide-pocket', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1212, NULL, NULL, NULL, NULL, NULL, 'i-lucide-pocket-knife', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1213, NULL, NULL, NULL, NULL, NULL, 'i-lucide-podcast', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1214, NULL, NULL, NULL, NULL, NULL, 'i-lucide-pointer', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1215, NULL, NULL, NULL, NULL, NULL, 'i-lucide-pointer-off', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1216, NULL, NULL, NULL, NULL, NULL, 'i-lucide-popcorn', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1217, NULL, NULL, NULL, NULL, NULL, 'i-lucide-popsicle', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1219, NULL, NULL, NULL, NULL, NULL, 'i-lucide-power', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1220, NULL, NULL, NULL, NULL, NULL, 'i-lucide-power-off', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1221, NULL, NULL, NULL, NULL, NULL, 'i-lucide-presentation', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1222, NULL, NULL, NULL, NULL, NULL, 'i-lucide-printer', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1223, NULL, NULL, NULL, NULL, NULL, 'i-lucide-printer-check', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1224, NULL, NULL, NULL, NULL, NULL, 'i-lucide-printer-x', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1225, NULL, NULL, NULL, NULL, NULL, 'i-lucide-projector', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1226, NULL, NULL, NULL, NULL, NULL, 'i-lucide-proportions', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1227, NULL, NULL, NULL, NULL, NULL, 'i-lucide-puzzle', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1228, NULL, NULL, NULL, NULL, NULL, 'i-lucide-pyramid', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1229, NULL, NULL, NULL, NULL, NULL, 'i-lucide-qr-code', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1231, NULL, NULL, NULL, NULL, NULL, 'i-lucide-rabbit', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1232, NULL, NULL, NULL, NULL, NULL, 'i-lucide-radar', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1233, NULL, NULL, NULL, NULL, NULL, 'i-lucide-radiation', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1234, NULL, NULL, NULL, NULL, NULL, 'i-lucide-radical', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1235, NULL, NULL, NULL, NULL, NULL, 'i-lucide-radio', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1236, NULL, NULL, NULL, NULL, NULL, 'i-lucide-radio-off', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1237, NULL, NULL, NULL, NULL, NULL, 'i-lucide-radio-receiver', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1238, NULL, NULL, NULL, NULL, NULL, 'i-lucide-radio-tower', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1239, NULL, NULL, NULL, NULL, NULL, 'i-lucide-radius', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1241, NULL, NULL, NULL, NULL, NULL, 'i-lucide-rainbow', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1242, NULL, NULL, NULL, NULL, NULL, 'i-lucide-rat', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1243, NULL, NULL, NULL, NULL, NULL, 'i-lucide-ratio', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1244, NULL, NULL, NULL, NULL, NULL, 'i-lucide-receipt', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1245, NULL, NULL, NULL, NULL, NULL, 'i-lucide-receipt-cent', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1248, NULL, NULL, NULL, NULL, NULL, 'i-lucide-receipt-japanese-yen', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1249, NULL, NULL, NULL, NULL, NULL, 'i-lucide-receipt-pound-sterling', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1250, NULL, NULL, NULL, NULL, NULL, 'i-lucide-receipt-russian-ruble', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1251, NULL, NULL, NULL, NULL, NULL, 'i-lucide-receipt-swiss-franc', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1252, NULL, NULL, NULL, NULL, NULL, 'i-lucide-receipt-text', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1253, NULL, NULL, NULL, NULL, NULL, 'i-lucide-receipt-turkish-lira', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1254, NULL, NULL, NULL, NULL, NULL, 'i-lucide-rectangle-circle', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1255, NULL, NULL, NULL, NULL, NULL, 'i-lucide-rectangle-ellipsis', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1256, NULL, NULL, NULL, NULL, NULL, 'i-lucide-rectangle-goggles', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1257, NULL, NULL, NULL, NULL, NULL, 'i-lucide-rectangle-horizontal', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1258, NULL, NULL, NULL, NULL, NULL, 'i-lucide-rectangle-vertical', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1259, NULL, NULL, NULL, NULL, NULL, 'i-lucide-recycle', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1260, NULL, NULL, NULL, NULL, NULL, 'i-lucide-redo', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1261, NULL, NULL, NULL, NULL, NULL, 'i-lucide-redo-2', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1263, NULL, NULL, NULL, NULL, NULL, 'i-lucide-refresh-ccw', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1264, NULL, NULL, NULL, NULL, NULL, 'i-lucide-refresh-ccw-dot', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1265, NULL, NULL, NULL, NULL, NULL, 'i-lucide-refresh-cw', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1266, NULL, NULL, NULL, NULL, NULL, 'i-lucide-refresh-cw-off', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1267, NULL, NULL, NULL, NULL, NULL, 'i-lucide-refrigerator', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1268, NULL, NULL, NULL, NULL, NULL, 'i-lucide-regex', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1269, NULL, NULL, NULL, NULL, NULL, 'i-lucide-remove-formatting', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1270, NULL, NULL, NULL, NULL, NULL, 'i-lucide-repeat', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1272, NULL, NULL, NULL, NULL, NULL, 'i-lucide-repeat-2', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1273, NULL, NULL, NULL, NULL, NULL, 'i-lucide-replace', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1274, NULL, NULL, NULL, NULL, NULL, 'i-lucide-replace-all', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1275, NULL, NULL, NULL, NULL, NULL, 'i-lucide-reply', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1276, NULL, NULL, NULL, NULL, NULL, 'i-lucide-reply-all', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1277, NULL, NULL, NULL, NULL, NULL, 'i-lucide-rewind', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1278, NULL, NULL, NULL, NULL, NULL, 'i-lucide-ribbon', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1279, NULL, NULL, NULL, NULL, NULL, 'i-lucide-road', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1280, NULL, NULL, NULL, NULL, NULL, 'i-lucide-rocket', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1281, NULL, NULL, NULL, NULL, NULL, 'i-lucide-rocking-chair', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1283, NULL, NULL, NULL, NULL, NULL, 'i-lucide-rose', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1284, NULL, NULL, NULL, NULL, NULL, 'i-lucide-rotate-3d', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1285, NULL, NULL, NULL, NULL, NULL, 'i-lucide-rotate-ccw', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1286, NULL, NULL, NULL, NULL, NULL, 'i-lucide-rotate-ccw-key', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1287, NULL, NULL, NULL, NULL, NULL, 'i-lucide-rotate-ccw-square', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1288, NULL, NULL, NULL, NULL, NULL, 'i-lucide-rotate-cw', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1289, NULL, NULL, NULL, NULL, NULL, 'i-lucide-rotate-cw-square', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1290, NULL, NULL, NULL, NULL, NULL, 'i-lucide-route', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1292, NULL, NULL, NULL, NULL, NULL, 'i-lucide-router', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1293, NULL, NULL, NULL, NULL, NULL, 'i-lucide-rows-2', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1294, NULL, NULL, NULL, NULL, NULL, 'i-lucide-rows-3', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1295, NULL, NULL, NULL, NULL, NULL, 'i-lucide-rows-4', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1296, NULL, NULL, NULL, NULL, NULL, 'i-lucide-rss', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1297, NULL, NULL, NULL, NULL, NULL, 'i-lucide-ruler', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1298, NULL, NULL, NULL, NULL, NULL, 'i-lucide-ruler-dimension-line', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1299, NULL, NULL, NULL, NULL, NULL, 'i-lucide-russian-ruble', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1300, NULL, NULL, NULL, NULL, NULL, 'i-lucide-sailboat', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1301, NULL, NULL, NULL, NULL, NULL, 'i-lucide-salad', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1302, NULL, NULL, NULL, NULL, NULL, 'i-lucide-sandwich', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1304, NULL, NULL, NULL, NULL, NULL, 'i-lucide-satellite-dish', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1305, NULL, NULL, NULL, NULL, NULL, 'i-lucide-saudi-riyal', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1306, NULL, NULL, NULL, NULL, NULL, 'i-lucide-save', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1307, NULL, NULL, NULL, NULL, NULL, 'i-lucide-save-all', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1308, NULL, NULL, NULL, NULL, NULL, 'i-lucide-save-off', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1309, NULL, NULL, NULL, NULL, NULL, 'i-lucide-scale', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1310, NULL, NULL, NULL, NULL, NULL, 'i-lucide-scale-3d', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1311, NULL, NULL, NULL, NULL, NULL, 'i-lucide-scaling', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1312, NULL, NULL, NULL, NULL, NULL, 'i-lucide-scan', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1314, NULL, NULL, NULL, NULL, NULL, 'i-lucide-scan-eye', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1315, NULL, NULL, NULL, NULL, NULL, 'i-lucide-scan-face', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1316, NULL, NULL, NULL, NULL, NULL, 'i-lucide-scan-heart', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1317, NULL, NULL, NULL, NULL, NULL, 'i-lucide-scan-line', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1318, NULL, NULL, NULL, NULL, NULL, 'i-lucide-scan-qr-code', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1319, NULL, NULL, NULL, NULL, NULL, 'i-lucide-scan-search', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1321, NULL, NULL, NULL, NULL, NULL, 'i-lucide-scatter-chart', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1322, NULL, NULL, NULL, NULL, NULL, 'i-lucide-school', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1323, NULL, NULL, NULL, NULL, NULL, 'i-lucide-scissors', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1324, NULL, NULL, NULL, NULL, NULL, 'i-lucide-scissors-line-dashed', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1325, NULL, NULL, NULL, NULL, NULL, 'i-lucide-scooter', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1326, NULL, NULL, NULL, NULL, NULL, 'i-lucide-screen-share', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1691, NULL, NULL, NULL, NULL, NULL, 'i-lucide-volume', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1327, NULL, NULL, NULL, NULL, NULL, 'i-lucide-screen-share-off', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1328, NULL, NULL, NULL, NULL, NULL, 'i-lucide-scroll', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1329, NULL, NULL, NULL, NULL, NULL, 'i-lucide-scroll-text', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1330, NULL, NULL, NULL, NULL, NULL, 'i-lucide-search', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1332, NULL, NULL, NULL, NULL, NULL, 'i-lucide-search-check', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1333, NULL, NULL, NULL, NULL, NULL, 'i-lucide-search-code', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1334, NULL, NULL, NULL, NULL, NULL, 'i-lucide-search-large', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1335, NULL, NULL, NULL, NULL, NULL, 'i-lucide-search-slash', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1336, NULL, NULL, NULL, NULL, NULL, 'i-lucide-search-x', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1337, NULL, NULL, NULL, NULL, NULL, 'i-lucide-section', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1338, NULL, NULL, NULL, NULL, NULL, 'i-lucide-send', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1339, NULL, NULL, NULL, NULL, NULL, 'i-lucide-send-horizontal', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1340, NULL, NULL, NULL, NULL, NULL, 'i-lucide-send-to-back', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1341, NULL, NULL, NULL, NULL, NULL, 'i-lucide-separator-horizontal', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1342, NULL, NULL, NULL, NULL, NULL, 'i-lucide-separator-vertical', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1343, NULL, NULL, NULL, NULL, NULL, 'i-lucide-server', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1345, NULL, NULL, NULL, NULL, NULL, 'i-lucide-server-crash', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1346, NULL, NULL, NULL, NULL, NULL, 'i-lucide-server-off', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1347, NULL, NULL, NULL, NULL, NULL, 'i-lucide-settings', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1348, NULL, NULL, NULL, NULL, NULL, 'i-lucide-settings-2', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1349, NULL, NULL, NULL, NULL, NULL, 'i-lucide-shapes', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1350, NULL, NULL, NULL, NULL, NULL, 'i-lucide-share', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1351, NULL, NULL, NULL, NULL, NULL, 'i-lucide-share-2', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1352, NULL, NULL, NULL, NULL, NULL, 'i-lucide-sheet', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1353, NULL, NULL, NULL, NULL, NULL, 'i-lucide-shell', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1354, NULL, NULL, NULL, NULL, NULL, 'i-lucide-shelving-unit', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1355, NULL, NULL, NULL, NULL, NULL, 'i-lucide-shield', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1357, NULL, NULL, NULL, NULL, NULL, 'i-lucide-shield-ban', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1358, NULL, NULL, NULL, NULL, NULL, 'i-lucide-shield-check', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1359, NULL, NULL, NULL, NULL, NULL, 'i-lucide-shield-cog', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1361, NULL, NULL, NULL, NULL, NULL, 'i-lucide-shield-ellipsis', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1362, NULL, NULL, NULL, NULL, NULL, 'i-lucide-shield-half', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1363, NULL, NULL, NULL, NULL, NULL, 'i-lucide-shield-minus', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1364, NULL, NULL, NULL, NULL, NULL, 'i-lucide-shield-off', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1365, NULL, NULL, NULL, NULL, NULL, 'i-lucide-shield-plus', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1366, NULL, NULL, NULL, NULL, NULL, 'i-lucide-shield-question-mark', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1367, NULL, NULL, NULL, NULL, NULL, 'i-lucide-shield-user', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1369, NULL, NULL, NULL, NULL, NULL, 'i-lucide-ship', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1370, NULL, NULL, NULL, NULL, NULL, 'i-lucide-ship-wheel', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1371, NULL, NULL, NULL, NULL, NULL, 'i-lucide-shirt', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1372, NULL, NULL, NULL, NULL, NULL, 'i-lucide-shopping-bag', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1373, NULL, NULL, NULL, NULL, NULL, 'i-lucide-shopping-basket', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1374, NULL, NULL, NULL, NULL, NULL, 'i-lucide-shopping-cart', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1375, NULL, NULL, NULL, NULL, NULL, 'i-lucide-shovel', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1376, NULL, NULL, NULL, NULL, NULL, 'i-lucide-shower-head', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1377, NULL, NULL, NULL, NULL, NULL, 'i-lucide-shredder', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1378, NULL, NULL, NULL, NULL, NULL, 'i-lucide-shrimp', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1379, NULL, NULL, NULL, NULL, NULL, 'i-lucide-shrink', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1381, NULL, NULL, NULL, NULL, NULL, 'i-lucide-shuffle', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1382, NULL, NULL, NULL, NULL, NULL, 'i-lucide-sigma', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1383, NULL, NULL, NULL, NULL, NULL, 'i-lucide-signal', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1384, NULL, NULL, NULL, NULL, NULL, 'i-lucide-signal-high', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1385, NULL, NULL, NULL, NULL, NULL, 'i-lucide-signal-low', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1386, NULL, NULL, NULL, NULL, NULL, 'i-lucide-signal-medium', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1387, NULL, NULL, NULL, NULL, NULL, 'i-lucide-signal-zero', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1388, NULL, NULL, NULL, NULL, NULL, 'i-lucide-signature', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1390, NULL, NULL, NULL, NULL, NULL, 'i-lucide-signpost-big', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1391, NULL, NULL, NULL, NULL, NULL, 'i-lucide-siren', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1392, NULL, NULL, NULL, NULL, NULL, 'i-lucide-skip-back', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1393, NULL, NULL, NULL, NULL, NULL, 'i-lucide-skip-forward', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1394, NULL, NULL, NULL, NULL, NULL, 'i-lucide-skull', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1395, NULL, NULL, NULL, NULL, NULL, 'i-lucide-slack', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1396, NULL, NULL, NULL, NULL, NULL, 'i-lucide-slash', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1397, NULL, NULL, NULL, NULL, NULL, 'i-lucide-slice', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1398, NULL, NULL, NULL, NULL, NULL, 'i-lucide-sliders-horizontal', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1400, NULL, NULL, NULL, NULL, NULL, 'i-lucide-smartphone', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1401, NULL, NULL, NULL, NULL, NULL, 'i-lucide-smartphone-charging', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1402, NULL, NULL, NULL, NULL, NULL, 'i-lucide-smartphone-nfc', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1403, NULL, NULL, NULL, NULL, NULL, 'i-lucide-smile', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1404, NULL, NULL, NULL, NULL, NULL, 'i-lucide-smile-plus', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1405, NULL, NULL, NULL, NULL, NULL, 'i-lucide-snail', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1406, NULL, NULL, NULL, NULL, NULL, 'i-lucide-snowflake', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1407, NULL, NULL, NULL, NULL, NULL, 'i-lucide-soap-dispenser-droplet', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1408, NULL, NULL, NULL, NULL, NULL, 'i-lucide-sofa', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1409, NULL, NULL, NULL, NULL, NULL, 'i-lucide-solar-panel', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1410, NULL, NULL, NULL, NULL, NULL, 'i-lucide-soup', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1412, NULL, NULL, NULL, NULL, NULL, 'i-lucide-spade', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1413, NULL, NULL, NULL, NULL, NULL, 'i-lucide-sparkle', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1414, NULL, NULL, NULL, NULL, NULL, 'i-lucide-sparkles', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1415, NULL, NULL, NULL, NULL, NULL, 'i-lucide-speaker', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1416, NULL, NULL, NULL, NULL, NULL, 'i-lucide-speech', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1417, NULL, NULL, NULL, NULL, NULL, 'i-lucide-spell-check', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1418, NULL, NULL, NULL, NULL, NULL, 'i-lucide-spell-check-2', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1419, NULL, NULL, NULL, NULL, NULL, 'i-lucide-spline', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1420, NULL, NULL, NULL, NULL, NULL, 'i-lucide-spline-pointer', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1421, NULL, NULL, NULL, NULL, NULL, 'i-lucide-split', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1422, NULL, NULL, NULL, NULL, NULL, 'i-lucide-spool', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1424, NULL, NULL, NULL, NULL, NULL, 'i-lucide-spotlight', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1425, NULL, NULL, NULL, NULL, NULL, 'i-lucide-spray-can', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1426, NULL, NULL, NULL, NULL, NULL, 'i-lucide-sprout', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1427, NULL, NULL, NULL, NULL, NULL, 'i-lucide-square', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1428, NULL, NULL, NULL, NULL, NULL, 'i-lucide-square-activity', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1429, NULL, NULL, NULL, NULL, NULL, 'i-lucide-square-arrow-down', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1430, NULL, NULL, NULL, NULL, NULL, 'i-lucide-square-arrow-down-left', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1432, NULL, NULL, NULL, NULL, NULL, 'i-lucide-square-arrow-left', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1433, NULL, NULL, NULL, NULL, NULL, 'i-lucide-square-arrow-out-down-left', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1434, NULL, NULL, NULL, NULL, NULL, 'i-lucide-square-arrow-out-down-right', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1435, NULL, NULL, NULL, NULL, NULL, 'i-lucide-square-arrow-out-up-left', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1436, NULL, NULL, NULL, NULL, NULL, 'i-lucide-square-arrow-out-up-right', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1437, NULL, NULL, NULL, NULL, NULL, 'i-lucide-square-arrow-right', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1439, NULL, NULL, NULL, NULL, NULL, 'i-lucide-square-arrow-right-exit', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1440, NULL, NULL, NULL, NULL, NULL, 'i-lucide-square-arrow-up', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1441, NULL, NULL, NULL, NULL, NULL, 'i-lucide-square-arrow-up-left', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1442, NULL, NULL, NULL, NULL, NULL, 'i-lucide-square-arrow-up-right', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1443, NULL, NULL, NULL, NULL, NULL, 'i-lucide-square-asterisk', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1444, NULL, NULL, NULL, NULL, NULL, 'i-lucide-square-bottom-dashed-scissors', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1445, NULL, NULL, NULL, NULL, NULL, 'i-lucide-square-centerline-dashed-horizontal', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1446, NULL, NULL, NULL, NULL, NULL, 'i-lucide-square-centerline-dashed-vertical', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1447, NULL, NULL, NULL, NULL, NULL, 'i-lucide-square-chart-gantt', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1448, NULL, NULL, NULL, NULL, NULL, 'i-lucide-square-check', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1449, NULL, NULL, NULL, NULL, NULL, 'i-lucide-square-check-big', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1450, NULL, NULL, NULL, NULL, NULL, 'i-lucide-square-chevron-down', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1452, NULL, NULL, NULL, NULL, NULL, 'i-lucide-square-chevron-right', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1453, NULL, NULL, NULL, NULL, NULL, 'i-lucide-square-chevron-up', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1454, NULL, NULL, NULL, NULL, NULL, 'i-lucide-square-code', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1455, NULL, NULL, NULL, NULL, NULL, 'i-lucide-square-dashed', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1456, NULL, NULL, NULL, NULL, NULL, 'i-lucide-square-dashed-bottom', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1457, NULL, NULL, NULL, NULL, NULL, 'i-lucide-square-dashed-bottom-code', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1458, NULL, NULL, NULL, NULL, NULL, 'i-lucide-square-dashed-kanban', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1459, NULL, NULL, NULL, NULL, NULL, 'i-lucide-square-dashed-mouse-pointer', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1460, NULL, NULL, NULL, NULL, NULL, 'i-lucide-square-dashed-text', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1462, NULL, NULL, NULL, NULL, NULL, 'i-lucide-square-divide', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1463, NULL, NULL, NULL, NULL, NULL, 'i-lucide-square-dot', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1464, NULL, NULL, NULL, NULL, NULL, 'i-lucide-square-equal', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1465, NULL, NULL, NULL, NULL, NULL, 'i-lucide-square-function', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1466, NULL, NULL, NULL, NULL, NULL, 'i-lucide-square-kanban', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1467, NULL, NULL, NULL, NULL, NULL, 'i-lucide-square-library', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1468, NULL, NULL, NULL, NULL, NULL, 'i-lucide-square-m', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1469, NULL, NULL, NULL, NULL, NULL, 'i-lucide-square-menu', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1472, NULL, NULL, NULL, NULL, NULL, 'i-lucide-square-parking', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1473, NULL, NULL, NULL, NULL, NULL, 'i-lucide-square-parking-off', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1474, NULL, NULL, NULL, NULL, NULL, 'i-lucide-square-pause', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1475, NULL, NULL, NULL, NULL, NULL, 'i-lucide-square-pen', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1476, NULL, NULL, NULL, NULL, NULL, 'i-lucide-square-percent', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1477, NULL, NULL, NULL, NULL, NULL, 'i-lucide-square-pi', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1478, NULL, NULL, NULL, NULL, NULL, 'i-lucide-square-pilcrow', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1479, NULL, NULL, NULL, NULL, NULL, 'i-lucide-square-play', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1480, NULL, NULL, NULL, NULL, NULL, 'i-lucide-square-plus', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1481, NULL, NULL, NULL, NULL, NULL, 'i-lucide-square-power', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1482, NULL, NULL, NULL, NULL, NULL, 'i-lucide-square-radical', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1484, NULL, NULL, NULL, NULL, NULL, 'i-lucide-square-scissors', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1485, NULL, NULL, NULL, NULL, NULL, 'i-lucide-square-sigma', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1486, NULL, NULL, NULL, NULL, NULL, 'i-lucide-square-slash', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1487, NULL, NULL, NULL, NULL, NULL, 'i-lucide-square-split-horizontal', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1488, NULL, NULL, NULL, NULL, NULL, 'i-lucide-square-split-vertical', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1489, NULL, NULL, NULL, NULL, NULL, 'i-lucide-square-square', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1490, NULL, NULL, NULL, NULL, NULL, 'i-lucide-square-stack', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1491, NULL, NULL, NULL, NULL, NULL, 'i-lucide-square-star', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1492, NULL, NULL, NULL, NULL, NULL, 'i-lucide-square-stop', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1493, NULL, NULL, NULL, NULL, NULL, 'i-lucide-square-terminal', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1494, NULL, NULL, NULL, NULL, NULL, 'i-lucide-square-user', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1496, NULL, NULL, NULL, NULL, NULL, 'i-lucide-square-x', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1497, NULL, NULL, NULL, NULL, NULL, 'i-lucide-squares-exclude', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1498, NULL, NULL, NULL, NULL, NULL, 'i-lucide-squares-intersect', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1499, NULL, NULL, NULL, NULL, NULL, 'i-lucide-squares-subtract', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1500, NULL, NULL, NULL, NULL, NULL, 'i-lucide-squares-unite', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1501, NULL, NULL, NULL, NULL, NULL, 'i-lucide-squircle', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1502, NULL, NULL, NULL, NULL, NULL, 'i-lucide-squircle-dashed', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1503, NULL, NULL, NULL, NULL, NULL, 'i-lucide-squirrel', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1504, NULL, NULL, NULL, NULL, NULL, 'i-lucide-stamp', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1505, NULL, NULL, NULL, NULL, NULL, 'i-lucide-star', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1507, NULL, NULL, NULL, NULL, NULL, 'i-lucide-star-off', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1508, NULL, NULL, NULL, NULL, NULL, 'i-lucide-step-back', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1509, NULL, NULL, NULL, NULL, NULL, 'i-lucide-step-forward', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1510, NULL, NULL, NULL, NULL, NULL, 'i-lucide-stethoscope', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1511, NULL, NULL, NULL, NULL, NULL, 'i-lucide-sticker', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1512, NULL, NULL, NULL, NULL, NULL, 'i-lucide-sticky-note', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1513, NULL, NULL, NULL, NULL, NULL, 'i-lucide-stone', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1514, NULL, NULL, NULL, NULL, NULL, 'i-lucide-store', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1516, NULL, NULL, NULL, NULL, NULL, 'i-lucide-stretch-vertical', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1517, NULL, NULL, NULL, NULL, NULL, 'i-lucide-strikethrough', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1518, NULL, NULL, NULL, NULL, NULL, 'i-lucide-subscript', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1519, NULL, NULL, NULL, NULL, NULL, 'i-lucide-sun', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1520, NULL, NULL, NULL, NULL, NULL, 'i-lucide-sun-dim', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1521, NULL, NULL, NULL, NULL, NULL, 'i-lucide-sun-medium', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1522, NULL, NULL, NULL, NULL, NULL, 'i-lucide-sun-moon', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1523, NULL, NULL, NULL, NULL, NULL, 'i-lucide-sun-snow', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1525, NULL, NULL, NULL, NULL, NULL, 'i-lucide-sunset', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1526, NULL, NULL, NULL, NULL, NULL, 'i-lucide-superscript', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1527, NULL, NULL, NULL, NULL, NULL, 'i-lucide-swatch-book', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1528, NULL, NULL, NULL, NULL, NULL, 'i-lucide-swiss-franc', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1529, NULL, NULL, NULL, NULL, NULL, 'i-lucide-switch-camera', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1530, NULL, NULL, NULL, NULL, NULL, 'i-lucide-sword', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1531, NULL, NULL, NULL, NULL, NULL, 'i-lucide-swords', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1532, NULL, NULL, NULL, NULL, NULL, 'i-lucide-syringe', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1533, NULL, NULL, NULL, NULL, NULL, 'i-lucide-table', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1534, NULL, NULL, NULL, NULL, NULL, 'i-lucide-table-2', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1536, NULL, NULL, NULL, NULL, NULL, 'i-lucide-table-cells-split', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1537, NULL, NULL, NULL, NULL, NULL, 'i-lucide-table-columns-split', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1538, NULL, NULL, NULL, NULL, NULL, 'i-lucide-table-of-contents', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1539, NULL, NULL, NULL, NULL, NULL, 'i-lucide-table-properties', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1540, NULL, NULL, NULL, NULL, NULL, 'i-lucide-table-rows-split', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1541, NULL, NULL, NULL, NULL, NULL, 'i-lucide-tablet', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1543, NULL, NULL, NULL, NULL, NULL, 'i-lucide-tablets', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1544, NULL, NULL, NULL, NULL, NULL, 'i-lucide-tag', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1545, NULL, NULL, NULL, NULL, NULL, 'i-lucide-tags', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1546, NULL, NULL, NULL, NULL, NULL, 'i-lucide-tally-1', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1547, NULL, NULL, NULL, NULL, NULL, 'i-lucide-tally-2', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1548, NULL, NULL, NULL, NULL, NULL, 'i-lucide-tally-3', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1549, NULL, NULL, NULL, NULL, NULL, 'i-lucide-tally-4', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1550, NULL, NULL, NULL, NULL, NULL, 'i-lucide-tally-5', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1551, NULL, NULL, NULL, NULL, NULL, 'i-lucide-tangent', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1552, NULL, NULL, NULL, NULL, NULL, 'i-lucide-target', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1553, NULL, NULL, NULL, NULL, NULL, 'i-lucide-telescope', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1554, NULL, NULL, NULL, NULL, NULL, 'i-lucide-tent', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1555, NULL, NULL, NULL, NULL, NULL, 'i-lucide-tent-tree', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1557, NULL, NULL, NULL, NULL, NULL, 'i-lucide-test-tube', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1558, NULL, NULL, NULL, NULL, NULL, 'i-lucide-test-tube-diagonal', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1559, NULL, NULL, NULL, NULL, NULL, 'i-lucide-test-tubes', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1560, NULL, NULL, NULL, NULL, NULL, 'i-lucide-text', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1561, NULL, NULL, NULL, NULL, NULL, 'i-lucide-text-align-center', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1562, NULL, NULL, NULL, NULL, NULL, 'i-lucide-text-align-end', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1564, NULL, NULL, NULL, NULL, NULL, 'i-lucide-text-align-start', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1565, NULL, NULL, NULL, NULL, NULL, 'i-lucide-text-cursor', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1566, NULL, NULL, NULL, NULL, NULL, 'i-lucide-text-cursor-input', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1567, NULL, NULL, NULL, NULL, NULL, 'i-lucide-text-initial', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1568, NULL, NULL, NULL, NULL, NULL, 'i-lucide-text-quote', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1569, NULL, NULL, NULL, NULL, NULL, 'i-lucide-text-search', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1570, NULL, NULL, NULL, NULL, NULL, 'i-lucide-text-wrap', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1571, NULL, NULL, NULL, NULL, NULL, 'i-lucide-theater', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1573, NULL, NULL, NULL, NULL, NULL, 'i-lucide-thermometer-snowflake', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1574, NULL, NULL, NULL, NULL, NULL, 'i-lucide-thermometer-sun', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1575, NULL, NULL, NULL, NULL, NULL, 'i-lucide-thumbs-down', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1576, NULL, NULL, NULL, NULL, NULL, 'i-lucide-thumbs-up', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1577, NULL, NULL, NULL, NULL, NULL, 'i-lucide-ticket', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1578, NULL, NULL, NULL, NULL, NULL, 'i-lucide-ticket-check', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1579, NULL, NULL, NULL, NULL, NULL, 'i-lucide-ticket-minus', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1580, NULL, NULL, NULL, NULL, NULL, 'i-lucide-ticket-percent', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1581, NULL, NULL, NULL, NULL, NULL, 'i-lucide-ticket-plus', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1583, NULL, NULL, NULL, NULL, NULL, 'i-lucide-ticket-x', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1585, NULL, NULL, NULL, NULL, NULL, 'i-lucide-tickets-plane', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1586, NULL, NULL, NULL, NULL, NULL, 'i-lucide-timer', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1587, NULL, NULL, NULL, NULL, NULL, 'i-lucide-timer-off', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1588, NULL, NULL, NULL, NULL, NULL, 'i-lucide-timer-reset', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1590, NULL, NULL, NULL, NULL, NULL, 'i-lucide-toggle-right', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1591, NULL, NULL, NULL, NULL, NULL, 'i-lucide-toilet', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1592, NULL, NULL, NULL, NULL, NULL, 'i-lucide-tool-case', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1593, NULL, NULL, NULL, NULL, NULL, 'i-lucide-toolbox', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1594, NULL, NULL, NULL, NULL, NULL, 'i-lucide-tornado', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1595, NULL, NULL, NULL, NULL, NULL, 'i-lucide-torus', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1596, NULL, NULL, NULL, NULL, NULL, 'i-lucide-touchpad', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1597, NULL, NULL, NULL, NULL, NULL, 'i-lucide-touchpad-off', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1598, NULL, NULL, NULL, NULL, NULL, 'i-lucide-towel-rack', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1600, NULL, NULL, NULL, NULL, NULL, 'i-lucide-toy-brick', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1601, NULL, NULL, NULL, NULL, NULL, 'i-lucide-tractor', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1602, NULL, NULL, NULL, NULL, NULL, 'i-lucide-traffic-cone', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1603, NULL, NULL, NULL, NULL, NULL, 'i-lucide-train-front', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1604, NULL, NULL, NULL, NULL, NULL, 'i-lucide-train-front-tunnel', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1605, NULL, NULL, NULL, NULL, NULL, 'i-lucide-train-track', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1606, NULL, NULL, NULL, NULL, NULL, 'i-lucide-tram-front', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1608, NULL, NULL, NULL, NULL, NULL, 'i-lucide-trash', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1609, NULL, NULL, NULL, NULL, NULL, 'i-lucide-trash-2', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1610, NULL, NULL, NULL, NULL, NULL, 'i-lucide-tree-deciduous', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1611, NULL, NULL, NULL, NULL, NULL, 'i-lucide-tree-palm', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1612, NULL, NULL, NULL, NULL, NULL, 'i-lucide-tree-pine', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1613, NULL, NULL, NULL, NULL, NULL, 'i-lucide-trees', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1614, NULL, NULL, NULL, NULL, NULL, 'i-lucide-trello', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1615, NULL, NULL, NULL, NULL, NULL, 'i-lucide-trending-down', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1616, NULL, NULL, NULL, NULL, NULL, 'i-lucide-trending-up', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1618, NULL, NULL, NULL, NULL, NULL, 'i-lucide-triangle', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1619, NULL, NULL, NULL, NULL, NULL, 'i-lucide-triangle-alert', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1620, NULL, NULL, NULL, NULL, NULL, 'i-lucide-triangle-dashed', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1621, NULL, NULL, NULL, NULL, NULL, 'i-lucide-triangle-right', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1622, NULL, NULL, NULL, NULL, NULL, 'i-lucide-trophy', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1623, NULL, NULL, NULL, NULL, NULL, 'i-lucide-truck', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1624, NULL, NULL, NULL, NULL, NULL, 'i-lucide-truck-electric', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1625, NULL, NULL, NULL, NULL, NULL, 'i-lucide-turkish-lira', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1626, NULL, NULL, NULL, NULL, NULL, 'i-lucide-turntable', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1627, NULL, NULL, NULL, NULL, NULL, 'i-lucide-turtle', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1628, NULL, NULL, NULL, NULL, NULL, 'i-lucide-tv', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1629, NULL, NULL, NULL, NULL, NULL, 'i-lucide-tv-minimal', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1631, NULL, NULL, NULL, NULL, NULL, 'i-lucide-twitch', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1632, NULL, NULL, NULL, NULL, NULL, 'i-lucide-twitter', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1633, NULL, NULL, NULL, NULL, NULL, 'i-lucide-type', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1634, NULL, NULL, NULL, NULL, NULL, 'i-lucide-type-outline', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1635, NULL, NULL, NULL, NULL, NULL, 'i-lucide-umbrella', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1636, NULL, NULL, NULL, NULL, NULL, 'i-lucide-umbrella-off', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1637, NULL, NULL, NULL, NULL, NULL, 'i-lucide-underline', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1638, NULL, NULL, NULL, NULL, NULL, 'i-lucide-undo', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1639, NULL, NULL, NULL, NULL, NULL, 'i-lucide-undo-2', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1640, NULL, NULL, NULL, NULL, NULL, 'i-lucide-undo-dot', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1642, NULL, NULL, NULL, NULL, NULL, 'i-lucide-unfold-vertical', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1643, NULL, NULL, NULL, NULL, NULL, 'i-lucide-ungroup', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1644, NULL, NULL, NULL, NULL, NULL, 'i-lucide-university', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1645, NULL, NULL, NULL, NULL, NULL, 'i-lucide-unlink', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1646, NULL, NULL, NULL, NULL, NULL, 'i-lucide-unlink-2', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1647, NULL, NULL, NULL, NULL, NULL, 'i-lucide-unplug', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1648, NULL, NULL, NULL, NULL, NULL, 'i-lucide-upload', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1649, NULL, NULL, NULL, NULL, NULL, 'i-lucide-usb', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1650, NULL, NULL, NULL, NULL, NULL, 'i-lucide-user', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1651, NULL, NULL, NULL, NULL, NULL, 'i-lucide-user-check', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1652, NULL, NULL, NULL, NULL, NULL, 'i-lucide-user-cog', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1654, NULL, NULL, NULL, NULL, NULL, 'i-lucide-user-lock', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1655, NULL, NULL, NULL, NULL, NULL, 'i-lucide-user-minus', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1656, NULL, NULL, NULL, NULL, NULL, 'i-lucide-user-pen', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1657, NULL, NULL, NULL, NULL, NULL, 'i-lucide-user-plus', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1658, NULL, NULL, NULL, NULL, NULL, 'i-lucide-user-round', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1660, NULL, NULL, NULL, NULL, NULL, 'i-lucide-user-round-cog', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1661, NULL, NULL, NULL, NULL, NULL, 'i-lucide-user-round-key', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1662, NULL, NULL, NULL, NULL, NULL, 'i-lucide-user-round-minus', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1663, NULL, NULL, NULL, NULL, NULL, 'i-lucide-user-round-pen', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1664, NULL, NULL, NULL, NULL, NULL, 'i-lucide-user-round-plus', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1665, NULL, NULL, NULL, NULL, NULL, 'i-lucide-user-round-search', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1666, NULL, NULL, NULL, NULL, NULL, 'i-lucide-user-round-x', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1667, NULL, NULL, NULL, NULL, NULL, 'i-lucide-user-search', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1668, NULL, NULL, NULL, NULL, NULL, 'i-lucide-user-star', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1669, NULL, NULL, NULL, NULL, NULL, 'i-lucide-user-x', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1670, NULL, NULL, NULL, NULL, NULL, 'i-lucide-users', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1672, NULL, NULL, NULL, NULL, NULL, 'i-lucide-utensils', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1673, NULL, NULL, NULL, NULL, NULL, 'i-lucide-utensils-crossed', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1674, NULL, NULL, NULL, NULL, NULL, 'i-lucide-utility-pole', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1675, NULL, NULL, NULL, NULL, NULL, 'i-lucide-van', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1676, NULL, NULL, NULL, NULL, NULL, 'i-lucide-variable', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1677, NULL, NULL, NULL, NULL, NULL, 'i-lucide-vault', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1679, NULL, NULL, NULL, NULL, NULL, 'i-lucide-vegan', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1680, NULL, NULL, NULL, NULL, NULL, 'i-lucide-venetian-mask', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1681, NULL, NULL, NULL, NULL, NULL, 'i-lucide-venus', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1682, NULL, NULL, NULL, NULL, NULL, 'i-lucide-venus-and-mars', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1683, NULL, NULL, NULL, NULL, NULL, 'i-lucide-vibrate', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1684, NULL, NULL, NULL, NULL, NULL, 'i-lucide-vibrate-off', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1685, NULL, NULL, NULL, NULL, NULL, 'i-lucide-video', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1686, NULL, NULL, NULL, NULL, NULL, 'i-lucide-video-off', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1687, NULL, NULL, NULL, NULL, NULL, 'i-lucide-videotape', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1688, NULL, NULL, NULL, NULL, NULL, 'i-lucide-view', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1689, NULL, NULL, NULL, NULL, NULL, 'i-lucide-voicemail', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1692, NULL, NULL, NULL, NULL, NULL, 'i-lucide-volume-1', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1693, NULL, NULL, NULL, NULL, NULL, 'i-lucide-volume-2', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1694, NULL, NULL, NULL, NULL, NULL, 'i-lucide-volume-off', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1695, NULL, NULL, NULL, NULL, NULL, 'i-lucide-volume-x', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1696, NULL, NULL, NULL, NULL, NULL, 'i-lucide-vote', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1697, NULL, NULL, NULL, NULL, NULL, 'i-lucide-wallet', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1699, NULL, NULL, NULL, NULL, NULL, 'i-lucide-wallet-minimal', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1701, NULL, NULL, NULL, NULL, NULL, 'i-lucide-wand', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1702, NULL, NULL, NULL, NULL, NULL, 'i-lucide-wand-sparkles', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1703, NULL, NULL, NULL, NULL, NULL, 'i-lucide-warehouse', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1704, NULL, NULL, NULL, NULL, NULL, 'i-lucide-washing-machine', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1705, NULL, NULL, NULL, NULL, NULL, 'i-lucide-watch', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1706, NULL, NULL, NULL, NULL, NULL, 'i-lucide-waves', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1707, NULL, NULL, NULL, NULL, NULL, 'i-lucide-waves-arrow-down', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1708, NULL, NULL, NULL, NULL, NULL, 'i-lucide-waves-arrow-up', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1709, NULL, NULL, NULL, NULL, NULL, 'i-lucide-waves-ladder', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1711, NULL, NULL, NULL, NULL, NULL, 'i-lucide-webcam', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1712, NULL, NULL, NULL, NULL, NULL, 'i-lucide-webhook', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1713, NULL, NULL, NULL, NULL, NULL, 'i-lucide-webhook-off', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1714, NULL, NULL, NULL, NULL, NULL, 'i-lucide-weight', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1715, NULL, NULL, NULL, NULL, NULL, 'i-lucide-weight-tilde', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1716, NULL, NULL, NULL, NULL, NULL, 'i-lucide-wheat', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1717, NULL, NULL, NULL, NULL, NULL, 'i-lucide-wheat-off', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1718, NULL, NULL, NULL, NULL, NULL, 'i-lucide-whole-word', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1719, NULL, NULL, NULL, NULL, NULL, 'i-lucide-wifi', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1720, NULL, NULL, NULL, NULL, NULL, 'i-lucide-wifi-cog', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1722, NULL, NULL, NULL, NULL, NULL, 'i-lucide-wifi-low', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1723, NULL, NULL, NULL, NULL, NULL, 'i-lucide-wifi-off', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1724, NULL, NULL, NULL, NULL, NULL, 'i-lucide-wifi-pen', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1725, NULL, NULL, NULL, NULL, NULL, 'i-lucide-wifi-sync', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1726, NULL, NULL, NULL, NULL, NULL, 'i-lucide-wifi-zero', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1727, NULL, NULL, NULL, NULL, NULL, 'i-lucide-wind', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1729, NULL, NULL, NULL, NULL, NULL, 'i-lucide-wine', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1730, NULL, NULL, NULL, NULL, NULL, 'i-lucide-wine-off', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1731, NULL, NULL, NULL, NULL, NULL, 'i-lucide-workflow', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1732, NULL, NULL, NULL, NULL, NULL, 'i-lucide-worm', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1733, NULL, NULL, NULL, NULL, NULL, 'i-lucide-wrap-text', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1734, NULL, NULL, NULL, NULL, NULL, 'i-lucide-wrench', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1735, NULL, NULL, NULL, NULL, NULL, 'i-lucide-x', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1736, NULL, NULL, NULL, NULL, NULL, 'i-lucide-x-line-top', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1738, NULL, NULL, NULL, NULL, NULL, 'i-lucide-zap', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1739, NULL, NULL, NULL, NULL, NULL, 'i-lucide-zap-off', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1740, NULL, NULL, NULL, NULL, NULL, 'i-lucide-zodiac-aquarius', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1741, NULL, NULL, NULL, NULL, NULL, 'i-lucide-zodiac-aries', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1742, NULL, NULL, NULL, NULL, NULL, 'i-lucide-zodiac-cancer', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1743, NULL, NULL, NULL, NULL, NULL, 'i-lucide-zodiac-capricorn', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1744, NULL, NULL, NULL, NULL, NULL, 'i-lucide-zodiac-gemini', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1745, NULL, NULL, NULL, NULL, NULL, 'i-lucide-zodiac-leo', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1747, NULL, NULL, NULL, NULL, NULL, 'i-lucide-zodiac-ophiuchus', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1748, NULL, NULL, NULL, NULL, NULL, 'i-lucide-zodiac-pisces', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1749, NULL, NULL, NULL, NULL, NULL, 'i-lucide-zodiac-sagittarius', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1750, NULL, NULL, NULL, NULL, NULL, 'i-lucide-zodiac-scorpio', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1751, NULL, NULL, NULL, NULL, NULL, 'i-lucide-zodiac-taurus', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1752, NULL, NULL, NULL, NULL, NULL, 'i-lucide-zodiac-virgo', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1753, NULL, NULL, NULL, NULL, NULL, 'i-lucide-zoom-in', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1754, NULL, NULL, NULL, NULL, NULL, 'i-lucide-zoom-out', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (6, NULL, NULL, NULL, NULL, NULL, 'i-lucide-air-vent', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (15, NULL, NULL, NULL, NULL, NULL, 'i-lucide-align-center', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (39, NULL, NULL, NULL, NULL, NULL, 'i-lucide-align-vertical-space-around', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (55, NULL, NULL, NULL, NULL, NULL, 'i-lucide-archive-restore', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (63, NULL, NULL, NULL, NULL, NULL, 'i-lucide-arrow-big-right', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (75, NULL, NULL, NULL, NULL, NULL, 'i-lucide-arrow-down-to-dot', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (85, NULL, NULL, NULL, NULL, NULL, 'i-lucide-arrow-right-from-line', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (96, NULL, NULL, NULL, NULL, NULL, 'i-lucide-arrow-up-narrow-wide', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (107, NULL, NULL, NULL, NULL, NULL, 'i-lucide-award', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (115, NULL, NULL, NULL, NULL, NULL, 'i-lucide-badge-check', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (120, NULL, NULL, NULL, NULL, NULL, 'i-lucide-badge-japanese-yen', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (130, NULL, NULL, NULL, NULL, NULL, 'i-lucide-baggage-claim', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (139, NULL, NULL, NULL, NULL, NULL, 'i-lucide-bar-chart-3', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (149, NULL, NULL, NULL, NULL, NULL, 'i-lucide-battery-charging', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (160, NULL, NULL, NULL, NULL, NULL, 'i-lucide-bed-single', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (169, NULL, NULL, NULL, NULL, NULL, 'i-lucide-bell-off', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (180, NULL, NULL, NULL, NULL, NULL, 'i-lucide-biohazard', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (189, NULL, NULL, NULL, NULL, NULL, 'i-lucide-bluetooth-off', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (200, NULL, NULL, NULL, NULL, NULL, 'i-lucide-book-copy', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (207, NULL, NULL, NULL, NULL, NULL, 'i-lucide-book-lock', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (215, NULL, NULL, NULL, NULL, NULL, 'i-lucide-book-text', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (222, NULL, NULL, NULL, NULL, NULL, 'i-lucide-bookmark-check', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (231, NULL, NULL, NULL, NULL, NULL, 'i-lucide-bottle-wine', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (240, NULL, NULL, NULL, NULL, NULL, 'i-lucide-brick-wall', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (249, NULL, NULL, NULL, NULL, NULL, 'i-lucide-brush-cleaning', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (258, NULL, NULL, NULL, NULL, NULL, 'i-lucide-cable', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (276, NULL, NULL, NULL, NULL, NULL, 'i-lucide-calendar-off', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (287, NULL, NULL, NULL, NULL, NULL, 'i-lucide-candlestick-chart', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (297, NULL, NULL, NULL, NULL, NULL, 'i-lucide-car-taxi-front', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (309, NULL, NULL, NULL, NULL, NULL, 'i-lucide-cctv-off', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (316, NULL, NULL, NULL, NULL, NULL, 'i-lucide-chart-candlestick', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (327, NULL, NULL, NULL, NULL, NULL, 'i-lucide-chart-no-axes-column-increasing', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (334, NULL, NULL, NULL, NULL, NULL, 'i-lucide-check-check', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (335, NULL, NULL, NULL, NULL, NULL, 'i-lucide-check-line', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (343, NULL, NULL, NULL, NULL, NULL, 'i-lucide-chess-rook', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (354, NULL, NULL, NULL, NULL, NULL, 'i-lucide-chevrons-left-right-ellipsis', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (366, NULL, NULL, NULL, NULL, NULL, 'i-lucide-circle-arrow-down', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (374, NULL, NULL, NULL, NULL, NULL, 'i-lucide-circle-check', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (383, NULL, NULL, NULL, NULL, NULL, 'i-lucide-circle-dot', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (393, NULL, NULL, NULL, NULL, NULL, 'i-lucide-circle-parking-off', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (406, NULL, NULL, NULL, NULL, NULL, 'i-lucide-circle-stop', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (414, NULL, NULL, NULL, NULL, NULL, 'i-lucide-clipboard-check', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (428, NULL, NULL, NULL, NULL, NULL, 'i-lucide-clock-11', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (439, NULL, NULL, NULL, NULL, NULL, 'i-lucide-clock-arrow-down', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (444, NULL, NULL, NULL, NULL, NULL, 'i-lucide-closed-caption', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (453, NULL, NULL, NULL, NULL, NULL, 'i-lucide-cloud-hail', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (462, NULL, NULL, NULL, NULL, NULL, 'i-lucide-cloud-sun-rain', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (474, NULL, NULL, NULL, NULL, NULL, 'i-lucide-coins', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (493, NULL, NULL, NULL, NULL, NULL, 'i-lucide-copy', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (500, NULL, NULL, NULL, NULL, NULL, 'i-lucide-copyright', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1111, NULL, NULL, NULL, NULL, NULL, 'i-lucide-notebook-tabs', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1118, NULL, NULL, NULL, NULL, NULL, 'i-lucide-octagon-alert', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1131, NULL, NULL, NULL, NULL, NULL, 'i-lucide-package-plus', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1133, NULL, NULL, NULL, NULL, NULL, 'i-lucide-package-x', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1142, NULL, NULL, NULL, NULL, NULL, 'i-lucide-panel-bottom-dashed', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1151, NULL, NULL, NULL, NULL, NULL, 'i-lucide-panel-right-dashed', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1160, NULL, NULL, NULL, NULL, NULL, 'i-lucide-panels-top-left', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1171, NULL, NULL, NULL, NULL, NULL, 'i-lucide-pen-tool', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1181, NULL, NULL, NULL, NULL, NULL, 'i-lucide-phone-call', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1191, NULL, NULL, NULL, NULL, NULL, 'i-lucide-picture-in-picture-2', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1204, NULL, NULL, NULL, NULL, NULL, 'i-lucide-plane-landing', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1218, NULL, NULL, NULL, NULL, NULL, 'i-lucide-pound-sterling', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1230, NULL, NULL, NULL, NULL, NULL, 'i-lucide-quote', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1240, NULL, NULL, NULL, NULL, NULL, 'i-lucide-rail-symbol', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1246, NULL, NULL, NULL, NULL, NULL, 'i-lucide-receipt-euro', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1247, NULL, NULL, NULL, NULL, NULL, 'i-lucide-receipt-indian-rupee', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1262, NULL, NULL, NULL, NULL, NULL, 'i-lucide-redo-dot', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1271, NULL, NULL, NULL, NULL, NULL, 'i-lucide-repeat-1', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1282, NULL, NULL, NULL, NULL, NULL, 'i-lucide-roller-coaster', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1291, NULL, NULL, NULL, NULL, NULL, 'i-lucide-route-off', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1303, NULL, NULL, NULL, NULL, NULL, 'i-lucide-satellite', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1313, NULL, NULL, NULL, NULL, NULL, 'i-lucide-scan-barcode', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1320, NULL, NULL, NULL, NULL, NULL, 'i-lucide-scan-text', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1331, NULL, NULL, NULL, NULL, NULL, 'i-lucide-search-alert', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1344, NULL, NULL, NULL, NULL, NULL, 'i-lucide-server-cog', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1356, NULL, NULL, NULL, NULL, NULL, 'i-lucide-shield-alert', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1360, NULL, NULL, NULL, NULL, NULL, 'i-lucide-shield-cog-corner', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1368, NULL, NULL, NULL, NULL, NULL, 'i-lucide-shield-x', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1380, NULL, NULL, NULL, NULL, NULL, 'i-lucide-shrub', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1389, NULL, NULL, NULL, NULL, NULL, 'i-lucide-signpost', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1399, NULL, NULL, NULL, NULL, NULL, 'i-lucide-sliders-vertical', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1411, NULL, NULL, NULL, NULL, NULL, 'i-lucide-space', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1423, NULL, NULL, NULL, NULL, NULL, 'i-lucide-sport-shoe', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1431, NULL, NULL, NULL, NULL, NULL, 'i-lucide-square-arrow-down-right', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1438, NULL, NULL, NULL, NULL, NULL, 'i-lucide-square-arrow-right-enter', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1451, NULL, NULL, NULL, NULL, NULL, 'i-lucide-square-chevron-left', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1461, NULL, NULL, NULL, NULL, NULL, 'i-lucide-square-dashed-top-solid', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1470, NULL, NULL, NULL, NULL, NULL, 'i-lucide-square-minus', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1471, NULL, NULL, NULL, NULL, NULL, 'i-lucide-square-mouse-pointer', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1483, NULL, NULL, NULL, NULL, NULL, 'i-lucide-square-round-corner', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1495, NULL, NULL, NULL, NULL, NULL, 'i-lucide-square-user-round', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1506, NULL, NULL, NULL, NULL, NULL, 'i-lucide-star-half', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1515, NULL, NULL, NULL, NULL, NULL, 'i-lucide-stretch-horizontal', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1524, NULL, NULL, NULL, NULL, NULL, 'i-lucide-sunrise', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1535, NULL, NULL, NULL, NULL, NULL, 'i-lucide-table-cells-merge', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1542, NULL, NULL, NULL, NULL, NULL, 'i-lucide-tablet-smartphone', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1556, NULL, NULL, NULL, NULL, NULL, 'i-lucide-terminal', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1563, NULL, NULL, NULL, NULL, NULL, 'i-lucide-text-align-justify', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1572, NULL, NULL, NULL, NULL, NULL, 'i-lucide-thermometer', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (518, NULL, NULL, NULL, NULL, NULL, 'i-lucide-cup-soda', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (525, NULL, NULL, NULL, NULL, NULL, 'i-lucide-database-zap', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (542, NULL, NULL, NULL, NULL, NULL, 'i-lucide-diff', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (552, NULL, NULL, NULL, NULL, NULL, 'i-lucide-dollar-sign', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (561, NULL, NULL, NULL, NULL, NULL, 'i-lucide-dribbble', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (569, NULL, NULL, NULL, NULL, NULL, 'i-lucide-dumbbell', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (579, NULL, NULL, NULL, NULL, NULL, 'i-lucide-ellipsis', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (589, NULL, NULL, NULL, NULL, NULL, 'i-lucide-external-link', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (599, NULL, NULL, NULL, NULL, NULL, 'i-lucide-ferris-wheel', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (610, NULL, NULL, NULL, NULL, NULL, 'i-lucide-file-braces-corner', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (620, NULL, NULL, NULL, NULL, NULL, 'i-lucide-file-code-2', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (626, NULL, NULL, NULL, NULL, NULL, 'i-lucide-file-exclamation-point', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (634, NULL, NULL, NULL, NULL, NULL, 'i-lucide-file-key-2', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (641, NULL, NULL, NULL, NULL, NULL, 'i-lucide-file-output', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (648, NULL, NULL, NULL, NULL, NULL, 'i-lucide-file-plus-corner', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (657, NULL, NULL, NULL, NULL, NULL, 'i-lucide-file-stack', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (666, NULL, NULL, NULL, NULL, NULL, 'i-lucide-file-video-camera', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (676, NULL, NULL, NULL, NULL, NULL, 'i-lucide-filter-x', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (682, NULL, NULL, NULL, NULL, NULL, 'i-lucide-fishing-hook', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (691, NULL, NULL, NULL, NULL, NULL, 'i-lucide-flashlight-off', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (700, NULL, NULL, NULL, NULL, NULL, 'i-lucide-fold-horizontal', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (711, NULL, NULL, NULL, NULL, NULL, 'i-lucide-folder-git', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (722, NULL, NULL, NULL, NULL, NULL, 'i-lucide-folder-pen', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (731, NULL, NULL, NULL, NULL, NULL, 'i-lucide-folder-x', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (742, NULL, NULL, NULL, NULL, NULL, 'i-lucide-funnel', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (760, NULL, NULL, NULL, NULL, NULL, 'i-lucide-git-branch-minus', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (769, NULL, NULL, NULL, NULL, NULL, 'i-lucide-git-merge-conflict', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (782, NULL, NULL, NULL, NULL, NULL, 'i-lucide-globe-off', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (789, NULL, NULL, NULL, NULL, NULL, 'i-lucide-grid-2x2-check', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (791, NULL, NULL, NULL, NULL, NULL, 'i-lucide-grid-2x2-x', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (802, NULL, NULL, NULL, NULL, NULL, 'i-lucide-hand', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (809, NULL, NULL, NULL, NULL, NULL, 'i-lucide-hand-platter', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (817, NULL, NULL, NULL, NULL, NULL, 'i-lucide-hat-glasses', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (825, NULL, NULL, NULL, NULL, NULL, 'i-lucide-heading-4', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (833, NULL, NULL, NULL, NULL, NULL, 'i-lucide-heart-handshake', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (841, NULL, NULL, NULL, NULL, NULL, 'i-lucide-highlighter', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (850, NULL, NULL, NULL, NULL, NULL, 'i-lucide-house-plug', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (860, NULL, NULL, NULL, NULL, NULL, 'i-lucide-image-off', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (870, NULL, NULL, NULL, NULL, NULL, 'i-lucide-indian-rupee', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (883, NULL, NULL, NULL, NULL, NULL, 'i-lucide-key-round', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (891, NULL, NULL, NULL, NULL, NULL, 'i-lucide-lamp-floor', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (899, NULL, NULL, NULL, NULL, NULL, 'i-lucide-laptop-minimal-check', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (906, NULL, NULL, NULL, NULL, NULL, 'i-lucide-layers-plus', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (922, NULL, NULL, NULL, NULL, NULL, 'i-lucide-ligature', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (931, NULL, NULL, NULL, NULL, NULL, 'i-lucide-link-2-off', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (940, NULL, NULL, NULL, NULL, NULL, 'i-lucide-list-filter', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (948, NULL, NULL, NULL, NULL, NULL, 'i-lucide-list-restart', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (959, NULL, NULL, NULL, NULL, NULL, 'i-lucide-locate-off', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (971, NULL, NULL, NULL, NULL, NULL, 'i-lucide-mail-check', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (977, NULL, NULL, NULL, NULL, NULL, 'i-lucide-mail-warning', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (990, NULL, NULL, NULL, NULL, NULL, 'i-lucide-map-pin-pen', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (999, NULL, NULL, NULL, NULL, NULL, 'i-lucide-mars-stroke', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1582, NULL, NULL, NULL, NULL, NULL, 'i-lucide-ticket-slash', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1584, NULL, NULL, NULL, NULL, NULL, 'i-lucide-tickets', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1589, NULL, NULL, NULL, NULL, NULL, 'i-lucide-toggle-left', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1599, NULL, NULL, NULL, NULL, NULL, 'i-lucide-tower-control', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1607, NULL, NULL, NULL, NULL, NULL, 'i-lucide-transgender', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1617, NULL, NULL, NULL, NULL, NULL, 'i-lucide-trending-up-down', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1630, NULL, NULL, NULL, NULL, NULL, 'i-lucide-tv-minimal-play', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1641, NULL, NULL, NULL, NULL, NULL, 'i-lucide-unfold-horizontal', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1653, NULL, NULL, NULL, NULL, NULL, 'i-lucide-user-key', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1659, NULL, NULL, NULL, NULL, NULL, 'i-lucide-user-round-check', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1671, NULL, NULL, NULL, NULL, NULL, 'i-lucide-users-round', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1678, NULL, NULL, NULL, NULL, NULL, 'i-lucide-vector-square', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1690, NULL, NULL, NULL, NULL, NULL, 'i-lucide-volleyball', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1698, NULL, NULL, NULL, NULL, NULL, 'i-lucide-wallet-cards', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1700, NULL, NULL, NULL, NULL, NULL, 'i-lucide-wallpaper', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1710, NULL, NULL, NULL, NULL, NULL, 'i-lucide-waypoints', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1721, NULL, NULL, NULL, NULL, NULL, 'i-lucide-wifi-high', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1728, NULL, NULL, NULL, NULL, NULL, 'i-lucide-wind-arrow-down', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1737, NULL, NULL, NULL, NULL, NULL, 'i-lucide-youtube', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1746, NULL, NULL, NULL, NULL, NULL, 'i-lucide-zodiac-libra', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (22, NULL, NULL, NULL, NULL, NULL, 'i-lucide-align-horizontal-distribute-start', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (265, NULL, NULL, NULL, NULL, NULL, 'i-lucide-calendar-arrow-down', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (482, NULL, NULL, NULL, NULL, NULL, 'i-lucide-component', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (507, NULL, NULL, NULL, NULL, NULL, 'i-lucide-corner-up-left', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (559, NULL, NULL, NULL, NULL, NULL, 'i-lucide-drafting-compass', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (748, NULL, NULL, NULL, NULL, NULL, 'i-lucide-gallery-vertical', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (911, NULL, NULL, NULL, NULL, NULL, 'i-lucide-layout-panel-top', NULL, 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (4, NULL, NULL, NULL, NULL, NULL, 'i-lucide-accessibility', '残疾人', 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (1, NULL, NULL, NULL, NULL, NULL, 'i-lucide-a-arrow-down', 'A下', 'lucide');
INSERT INTO "ytora"."sys_icon" VALUES (2, NULL, NULL, NULL, NULL, NULL, 'i-lucide-a-arrow-up', 'A上', 'lucide');

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS "ytora"."sys_log";
CREATE TABLE "ytora"."sys_log" (
  "id" int8 NOT NULL,
  "create_by" varchar(255) COLLATE "pg_catalog"."default",
  "create_time" timestamp(6),
  "end_time" timestamp(6),
  "type" varchar(255) COLLATE "pg_catalog"."default",
  "trace_id" varchar(255) COLLATE "pg_catalog"."default",
  "thread" varchar(255) COLLATE "pg_catalog"."default",
  "happen_place" varchar(255) COLLATE "pg_catalog"."default",
  "content" text COLLATE "pg_catalog"."default",
  "param_length" int4,
  "params" text COLLATE "pg_catalog"."default",
  "result_length" int4,
  "result" text COLLATE "pg_catalog"."default",
  "cost" int8,
  "ip" varchar(255) COLLATE "pg_catalog"."default",
  "request_url" varchar(255) COLLATE "pg_catalog"."default",
  "error_stack" varchar(255) COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "ytora"."sys_log"."id" IS '数据主键ID';
COMMENT ON COLUMN "ytora"."sys_log"."create_by" IS '日志创建人';
COMMENT ON COLUMN "ytora"."sys_log"."create_time" IS '日志创建日期';
COMMENT ON COLUMN "ytora"."sys_log"."end_time" IS '日志结束日期';
COMMENT ON COLUMN "ytora"."sys_log"."type" IS '日志类型';
COMMENT ON COLUMN "ytora"."sys_log"."trace_id" IS '链路跟踪 ID，用于聚合同一次调用的所有日志';
COMMENT ON COLUMN "ytora"."sys_log"."thread" IS '所在线程信息';
COMMENT ON COLUMN "ytora"."sys_log"."happen_place" IS '日志发生的位置';
COMMENT ON COLUMN "ytora"."sys_log"."content" IS '日志主体内容';
COMMENT ON COLUMN "ytora"."sys_log"."param_length" IS '参数大小';
COMMENT ON COLUMN "ytora"."sys_log"."params" IS '参数';
COMMENT ON COLUMN "ytora"."sys_log"."result_length" IS '返回值大小';
COMMENT ON COLUMN "ytora"."sys_log"."result" IS '返回值';
COMMENT ON COLUMN "ytora"."sys_log"."cost" IS '方法耗时';
COMMENT ON COLUMN "ytora"."sys_log"."ip" IS '操作人ip';
COMMENT ON COLUMN "ytora"."sys_log"."request_url" IS 'HTTP 请求路径';
COMMENT ON COLUMN "ytora"."sys_log"."error_stack" IS '错误堆栈信息';
COMMENT ON TABLE "ytora"."sys_log" IS '日志表';

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO "ytora"."sys_log" VALUES (723889010114560, NULL, '2026-05-04 20:14:29.705669', NULL, 'LOGIN_LOG', NULL, NULL, NULL, '退出登录', NULL, NULL, NULL, NULL, NULL, '127.0.0.1', NULL, NULL);
INSERT INTO "ytora"."sys_log" VALUES (723889103634432, 'ytora', '2026-05-04 20:14:31.137829', NULL, 'LOGIN_LOG', NULL, NULL, NULL, '登录系统', NULL, NULL, NULL, NULL, NULL, '127.0.0.1', NULL, NULL);
INSERT INTO "ytora"."sys_log" VALUES (723894467952640, 'ytora', '2026-05-04 20:15:52.987372', NULL, 'LOGIN_LOG', NULL, NULL, NULL, '登录系统', NULL, NULL, NULL, NULL, NULL, '127.0.0.1', NULL, NULL);
INSERT INTO "ytora"."sys_log" VALUES (723898216284160, 'ytora', '2026-05-04 20:16:50.179666', NULL, 'LOGIN_LOG', NULL, NULL, NULL, '登录系统', NULL, NULL, NULL, NULL, NULL, '127.0.0.1', NULL, NULL);
INSERT INTO "ytora"."sys_log" VALUES (723901418045440, 'ytora', '2026-05-04 20:17:39.03492', NULL, 'LOGIN_LOG', NULL, NULL, NULL, '登录系统', NULL, NULL, NULL, NULL, NULL, '127.0.0.1', NULL, NULL);
INSERT INTO "ytora"."sys_log" VALUES (723909031624704, 'ytora', '2026-05-04 20:19:35.209197', NULL, 'LOGIN_LOG', NULL, NULL, NULL, '登录系统', NULL, NULL, NULL, NULL, NULL, '127.0.0.1', NULL, NULL);
INSERT INTO "ytora"."sys_log" VALUES (724020482473984, NULL, '2026-05-04 20:47:55.80807', NULL, 'LOGIN_LOG', NULL, NULL, NULL, '退出登录', NULL, NULL, NULL, NULL, NULL, '127.0.0.1', NULL, NULL);
INSERT INTO "ytora"."sys_log" VALUES (724020639301632, 'ytora', '2026-05-04 20:47:58.212553', NULL, 'LOGIN_LOG', NULL, NULL, NULL, '登录系统', NULL, NULL, NULL, NULL, NULL, '127.0.0.1', NULL, NULL);
INSERT INTO "ytora"."sys_log" VALUES (724093967204352, 'ytora', '2026-05-04 21:06:37.102501', NULL, 'LOGIN_LOG', NULL, NULL, NULL, '登录系统', NULL, NULL, NULL, NULL, NULL, '127.0.0.1', NULL, NULL);
INSERT INTO "ytora"."sys_log" VALUES (724112198664192, 'ytora', '2026-05-04 21:11:15.289081', NULL, 'LOGIN_LOG', NULL, NULL, NULL, '登录系统', NULL, NULL, NULL, NULL, NULL, '127.0.0.1', NULL, NULL);

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS "ytora"."sys_permission";
CREATE TABLE "ytora"."sys_permission" (
  "id" int8 NOT NULL,
  "create_by" varchar(255) COLLATE "pg_catalog"."default",
  "create_time" timestamp(6),
  "update_by" varchar(255) COLLATE "pg_catalog"."default",
  "update_time" timestamp(6),
  "depart_code" varchar(255) COLLATE "pg_catalog"."default",
  "remark" varchar(255) COLLATE "pg_catalog"."default",
  "pid" int8 NOT NULL,
  "permission_name" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "permission_code" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "permission_type" int4,
  "icon" varchar(255) COLLATE "pg_catalog"."default",
  "meta" varchar(255) COLLATE "pg_catalog"."default",
  "visible" bool,
  "index" int4
)
;
COMMENT ON COLUMN "ytora"."sys_permission"."id" IS '数据主键ID';
COMMENT ON COLUMN "ytora"."sys_permission"."create_by" IS '数据创建人';
COMMENT ON COLUMN "ytora"."sys_permission"."create_time" IS '数据创建日期';
COMMENT ON COLUMN "ytora"."sys_permission"."update_by" IS '数据修改人';
COMMENT ON COLUMN "ytora"."sys_permission"."update_time" IS '数据修改日期';
COMMENT ON COLUMN "ytora"."sys_permission"."depart_code" IS '数据创建人所属部门';
COMMENT ON COLUMN "ytora"."sys_permission"."remark" IS '数据备注';
COMMENT ON COLUMN "ytora"."sys_permission"."pid" IS '父资源id';
COMMENT ON COLUMN "ytora"."sys_permission"."permission_name" IS '资源名称';
COMMENT ON COLUMN "ytora"."sys_permission"."permission_code" IS '资源编码，例如：接口地址、页面的路由地址、页面元素(按钮)的唯一标识';
COMMENT ON COLUMN "ytora"."sys_permission"."permission_type" IS '资源类型，1-接口、2-页面、3-页面元素';
COMMENT ON COLUMN "ytora"."sys_permission"."icon" IS '图标';
COMMENT ON COLUMN "ytora"."sys_permission"."meta" IS '资源的元数据';
COMMENT ON COLUMN "ytora"."sys_permission"."visible" IS '是否可见';
COMMENT ON COLUMN "ytora"."sys_permission"."index" IS '排序';
COMMENT ON TABLE "ytora"."sys_permission" IS '用户表';

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO "ytora"."sys_permission" VALUES (2, NULL, NULL, NULL, NULL, NULL, NULL, 0, '系统管理', '/sys', 2, 'i-lucide-settings', NULL, 't', 2);
INSERT INTO "ytora"."sys_permission" VALUES (3, NULL, NULL, NULL, NULL, NULL, NULL, 0, '系统监控', '/monitor', 2, 'i-lucide-activity', NULL, 't', 3);
INSERT INTO "ytora"."sys_permission" VALUES (4, NULL, NULL, NULL, NULL, NULL, NULL, 0, '在线开发', '/online', 2, 'i-lucide-code-xml', NULL, 't', 4);
INSERT INTO "ytora"."sys_permission" VALUES (5, NULL, NULL, NULL, NULL, NULL, NULL, 1, '用户管理', '/rbac/user', 2, 'i-lucide-users', NULL, 't', 1);
INSERT INTO "ytora"."sys_permission" VALUES (6, NULL, NULL, NULL, NULL, NULL, NULL, 1, '角色管理', '/rbac/role', 2, 'i-lucide-user-cog', NULL, 't', 2);
INSERT INTO "ytora"."sys_permission" VALUES (7, NULL, NULL, NULL, NULL, NULL, NULL, 1, '资源管理', '/rbac/permission', 2, 'i-lucide-key-round', NULL, 't', 3);
INSERT INTO "ytora"."sys_permission" VALUES (8, NULL, NULL, NULL, NULL, NULL, NULL, 1, '部门管理', '/rbac/depart', 2, 'i-lucide-building-2', NULL, 't', 4);
INSERT INTO "ytora"."sys_permission" VALUES (9, NULL, NULL, NULL, NULL, NULL, NULL, 2, '数据字典', '/sys/dict', 2, 'i-lucide-book-open', NULL, 't', 1);
INSERT INTO "ytora"."sys_permission" VALUES (10, NULL, NULL, NULL, NULL, NULL, NULL, 2, '文件管理', '/sys/file', 2, 'i-lucide-folder-open', NULL, 't', 2);
INSERT INTO "ytora"."sys_permission" VALUES (11, NULL, NULL, NULL, NULL, NULL, NULL, 2, '定时任务', '/sys/schedule', 2, 'i-lucide-clock-3', NULL, 't', 3);
INSERT INTO "ytora"."sys_permission" VALUES (12, NULL, NULL, NULL, NULL, NULL, NULL, 2, '日志管理', '/sys/log', 2, 'i-lucide-scroll-text', NULL, 't', 4);
INSERT INTO "ytora"."sys_permission" VALUES (13, NULL, NULL, NULL, NULL, NULL, NULL, 2, '配置管理', '/sys/config', 2, 'i-lucide-sliders-horizontal', NULL, 't', 5);
INSERT INTO "ytora"."sys_permission" VALUES (14, NULL, NULL, NULL, NULL, NULL, NULL, 2, '回收站', '/sys/recyclebin', 2, 'i-lucide-trash-2', NULL, 't', 6);
INSERT INTO "ytora"."sys_permission" VALUES (15, NULL, NULL, NULL, NULL, NULL, NULL, 2, '系统图标', '/sys/icon', 2, 'i-lucide-image', NULL, 't', 7);
INSERT INTO "ytora"."sys_permission" VALUES (17, NULL, NULL, NULL, NULL, NULL, NULL, 3, '在线用户', '/monitor/online-user', 2, 'i-lucide-user-round-check', NULL, 't', 1);
INSERT INTO "ytora"."sys_permission" VALUES (18, NULL, NULL, NULL, NULL, NULL, NULL, 3, 'JVM监控', '/monitor/jvm', 2, 'i-lucide-cpu', NULL, 't', 2);
INSERT INTO "ytora"."sys_permission" VALUES (19, NULL, NULL, NULL, NULL, NULL, NULL, 3, '服务器监控', '/monitor/os', 2, 'i-lucide-server', NULL, 't', 3);
INSERT INTO "ytora"."sys_permission" VALUES (20, NULL, NULL, NULL, NULL, NULL, NULL, 3, '应用监控', '/monitor/app', 2, 'i-lucide-app-window', NULL, 't', 4);
INSERT INTO "ytora"."sys_permission" VALUES (21, NULL, NULL, NULL, NULL, NULL, NULL, 3, '数据库监控', '/monitor/db', 2, 'i-lucide-database', NULL, 't', 5);
INSERT INTO "ytora"."sys_permission" VALUES (22, NULL, NULL, NULL, NULL, NULL, NULL, 3, 'Redis监控', '/monitor/redis', 2, 'i-lucide-hard-drive', NULL, 't', 6);
INSERT INTO "ytora"."sys_permission" VALUES (23, NULL, NULL, NULL, NULL, NULL, NULL, 3, '缓存监控', '/monitor/cache', 2, 'i-lucide-database-zap', NULL, 't', 7);
INSERT INTO "ytora"."sys_permission" VALUES (24, NULL, NULL, NULL, NULL, NULL, NULL, 3, '时间轮监控', '/monitor/timewheel', 2, 'i-lucide-alarm-clock', NULL, 't', 8);
INSERT INTO "ytora"."sys_permission" VALUES (25, NULL, NULL, NULL, NULL, NULL, NULL, 3, 'SSE连接情况', '/monitor/sse', 2, 'i-lucide-radio-tower', NULL, 't', 9);
INSERT INTO "ytora"."sys_permission" VALUES (26, NULL, NULL, NULL, '2026-05-01 23:23:44.814563', NULL, '123', 4, 'API接口', '/online/api', 2, 'i-lucide-braces', NULL, 't', 1);
INSERT INTO "ytora"."sys_permission" VALUES (27, NULL, NULL, NULL, NULL, NULL, NULL, 4, 'online数据库', '/online/db', 2, 'i-lucide-database', NULL, 't', 2);
INSERT INTO "ytora"."sys_permission" VALUES (28, NULL, NULL, NULL, NULL, NULL, NULL, 4, '表单设计', '/online/form', 2, 'i-lucide-file-pen-line', NULL, 't', 3);
INSERT INTO "ytora"."sys_permission" VALUES (29, NULL, NULL, NULL, NULL, NULL, NULL, 4, '表格设计', '/online/table', 2, 'i-lucide-table-2', NULL, 't', 4);
INSERT INTO "ytora"."sys_permission" VALUES (30, NULL, NULL, NULL, NULL, NULL, NULL, 4, '接口文档', '/online/swagger', 2, 'i-lucide-book-text', NULL, 't', 5);
INSERT INTO "ytora"."sys_permission" VALUES (16, NULL, NULL, NULL, NULL, NULL, NULL, 2, '接口管理', '/sys/static-api', 2, 'i-lucide-route', NULL, 't', 8);
INSERT INTO "ytora"."sys_permission" VALUES (1, NULL, NULL, 'ytora', '2026-05-03 16:00:38.186736', NULL, '1234', 0, 'RBAC', '/rbac', 2, 'i-lucide-shield-check', NULL, 't', 1);

-- ----------------------------
-- Table structure for sys_recycle_bin
-- ----------------------------
DROP TABLE IF EXISTS "ytora"."sys_recycle_bin";
CREATE TABLE "ytora"."sys_recycle_bin" (
  "id" int8 NOT NULL,
  "create_by" varchar(255) COLLATE "pg_catalog"."default",
  "create_time" timestamp(6),
  "update_by" varchar(255) COLLATE "pg_catalog"."default",
  "update_time" timestamp(6),
  "depart_code" varchar(255) COLLATE "pg_catalog"."default",
  "remark" varchar(255) COLLATE "pg_catalog"."default",
  "deleted_by" varchar(255) COLLATE "pg_catalog"."default",
  "deleted_time" timestamp(6),
  "delete_reason" varchar(255) COLLATE "pg_catalog"."default",
  "original_table" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "original_id" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "original_data" jsonb NOT NULL,
  "restore_sql" varchar(255) COLLATE "pg_catalog"."default"
)
;
COMMENT ON COLUMN "ytora"."sys_recycle_bin"."id" IS '数据主键ID';
COMMENT ON COLUMN "ytora"."sys_recycle_bin"."create_by" IS '数据创建人';
COMMENT ON COLUMN "ytora"."sys_recycle_bin"."create_time" IS '数据创建日期';
COMMENT ON COLUMN "ytora"."sys_recycle_bin"."update_by" IS '数据修改人';
COMMENT ON COLUMN "ytora"."sys_recycle_bin"."update_time" IS '数据修改日期';
COMMENT ON COLUMN "ytora"."sys_recycle_bin"."depart_code" IS '数据创建人所属部门';
COMMENT ON COLUMN "ytora"."sys_recycle_bin"."remark" IS '数据备注';
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

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS "ytora"."sys_role";
CREATE TABLE "ytora"."sys_role" (
  "id" int8 NOT NULL,
  "create_by" varchar(255) COLLATE "pg_catalog"."default",
  "create_time" timestamp(6),
  "update_by" varchar(255) COLLATE "pg_catalog"."default",
  "update_time" timestamp(6),
  "depart_code" varchar(255) COLLATE "pg_catalog"."default",
  "remark" varchar(255) COLLATE "pg_catalog"."default",
  "role_name" varchar(255) COLLATE "pg_catalog"."default",
  "role_code" varchar(255) COLLATE "pg_catalog"."default" NOT NULL
)
;
COMMENT ON COLUMN "ytora"."sys_role"."id" IS '数据主键ID';
COMMENT ON COLUMN "ytora"."sys_role"."create_by" IS '数据创建人';
COMMENT ON COLUMN "ytora"."sys_role"."create_time" IS '数据创建日期';
COMMENT ON COLUMN "ytora"."sys_role"."update_by" IS '数据修改人';
COMMENT ON COLUMN "ytora"."sys_role"."update_time" IS '数据修改日期';
COMMENT ON COLUMN "ytora"."sys_role"."depart_code" IS '数据创建人所属部门';
COMMENT ON COLUMN "ytora"."sys_role"."role_name" IS '角色名称';
COMMENT ON COLUMN "ytora"."sys_role"."role_code" IS '角色编码';
COMMENT ON TABLE "ytora"."sys_role" IS '角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO "ytora"."sys_role" VALUES (1, NULL, NULL, NULL, '2026-05-01 18:27:41.20729', NULL, '平台最高权限角色，拥有系统全部管理权限', '管理员', 'ADMIN');
INSERT INTO "ytora"."sys_role" VALUES (724120000000001, 'ytora', '2026-05-04 21:14:12.305555', 'ytora', '2026-05-04 21:14:12.305555', NULL, '负责平台配置、参数维护、菜单维护、系统监控等运维工作', '系统运维', 'OPS');
INSERT INTO "ytora"."sys_role" VALUES (724120000000002, 'ytora', '2026-05-04 21:14:12.305555', 'ytora', '2026-05-04 21:14:12.305555', NULL, '负责用户、角色、权限、部门等基础权限数据管理', '权限管理员', 'AUTH_ADMIN');
INSERT INTO "ytora"."sys_role" VALUES (724120000000003, 'ytora', '2026-05-04 21:14:12.305555', 'ytora', '2026-05-04 21:14:12.305555', NULL, '负责查看操作日志、登录日志、审计记录，不参与业务修改', '审计员', 'AUDITOR');
INSERT INTO "ytora"."sys_role" VALUES (724120000000004, 'ytora', '2026-05-04 21:14:12.305555', 'ytora', '2026-05-04 21:14:12.305555', 'A01', '安布雷拉公司主体管理员，管理本公司用户、部门、角色和业务数据', '安布雷拉管理员', 'A01_ADMIN');
INSERT INTO "ytora"."sys_role" VALUES (724120000000005, 'ytora', '2026-05-04 21:14:12.305555', 'ytora', '2026-05-04 21:14:12.305555', 'B01', '星环集团主体管理员，管理本公司用户、部门、角色和业务数据', '星环管理员', 'B01_ADMIN');
INSERT INTO "ytora"."sys_role" VALUES (724120000000006, 'ytora', '2026-05-04 21:14:12.305555', 'ytora', '2026-05-04 21:14:12.305555', 'A01', '普通员工角色，只能访问个人工作台和授权范围内的数据', '安布雷拉员工', 'A01_EMPLOYEE');
INSERT INTO "ytora"."sys_role" VALUES (724120000000007, 'ytora', '2026-05-04 21:14:12.305555', 'ytora', '2026-05-04 21:14:12.305555', 'B01', '普通员工角色，只能访问个人工作台和授权范围内的数据', '星环员工', 'B01_EMPLOYEE');
INSERT INTO "ytora"."sys_role" VALUES (724120000000008, 'ytora', '2026-05-04 21:14:12.305555', 'ytora', '2026-05-04 21:14:12.305555', NULL, '只读角色，仅允许查看基础数据和被授权的业务数据', '只读用户', 'READONLY');
INSERT INTO "ytora"."sys_role" VALUES (724118196518912, 'ytora', '2026-05-04 21:12:46.819496', 'ytora', '2026-05-04 21:14:28.418829', 'A01-02-01', '测试系统的角色', '测试人员', 'TEST');

-- ----------------------------
-- Table structure for sys_role_data_scope
-- ----------------------------
DROP TABLE IF EXISTS "ytora"."sys_role_data_scope";
CREATE TABLE "ytora"."sys_role_data_scope" (
  "id" int8 NOT NULL,
  "create_by" varchar(255) COLLATE "pg_catalog"."default",
  "create_time" timestamp(6),
  "update_by" varchar(255) COLLATE "pg_catalog"."default",
  "update_time" timestamp(6),
  "depart_code" varchar(255) COLLATE "pg_catalog"."default",
  "remark" varchar(255) COLLATE "pg_catalog"."default",
  "role_id" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "group_id" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "scope_id" varchar(255) COLLATE "pg_catalog"."default" NOT NULL
)
;
COMMENT ON COLUMN "ytora"."sys_role_data_scope"."id" IS '数据主键ID';
COMMENT ON COLUMN "ytora"."sys_role_data_scope"."create_by" IS '数据创建人';
COMMENT ON COLUMN "ytora"."sys_role_data_scope"."create_time" IS '数据创建日期';
COMMENT ON COLUMN "ytora"."sys_role_data_scope"."update_by" IS '数据修改人';
COMMENT ON COLUMN "ytora"."sys_role_data_scope"."update_time" IS '数据修改日期';
COMMENT ON COLUMN "ytora"."sys_role_data_scope"."depart_code" IS '数据创建人所属部门';
COMMENT ON COLUMN "ytora"."sys_role_data_scope"."remark" IS '数据备注';
COMMENT ON COLUMN "ytora"."sys_role_data_scope"."role_id" IS '角色ID';
COMMENT ON COLUMN "ytora"."sys_role_data_scope"."group_id" IS '数据分组ID';
COMMENT ON COLUMN "ytora"."sys_role_data_scope"."scope_id" IS '数据范围ID';
COMMENT ON TABLE "ytora"."sys_role_data_scope" IS '角色-数据范围关系表';

-- ----------------------------
-- Records of sys_role_data_scope
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role_data_scope_group
-- ----------------------------
DROP TABLE IF EXISTS "ytora"."sys_role_data_scope_group";
CREATE TABLE "ytora"."sys_role_data_scope_group" (
  "id" int8 NOT NULL,
  "create_by" varchar(255) COLLATE "pg_catalog"."default",
  "create_time" timestamp(6),
  "update_by" varchar(255) COLLATE "pg_catalog"."default",
  "update_time" timestamp(6),
  "depart_code" varchar(255) COLLATE "pg_catalog"."default",
  "remark" varchar(255) COLLATE "pg_catalog"."default",
  "role_id" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "permission_id" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "group_id" varchar(255) COLLATE "pg_catalog"."default" NOT NULL
)
;
COMMENT ON COLUMN "ytora"."sys_role_data_scope_group"."id" IS '数据主键ID';
COMMENT ON COLUMN "ytora"."sys_role_data_scope_group"."create_by" IS '数据创建人';
COMMENT ON COLUMN "ytora"."sys_role_data_scope_group"."create_time" IS '数据创建日期';
COMMENT ON COLUMN "ytora"."sys_role_data_scope_group"."update_by" IS '数据修改人';
COMMENT ON COLUMN "ytora"."sys_role_data_scope_group"."update_time" IS '数据修改日期';
COMMENT ON COLUMN "ytora"."sys_role_data_scope_group"."depart_code" IS '数据创建人所属部门';
COMMENT ON COLUMN "ytora"."sys_role_data_scope_group"."remark" IS '数据备注';
COMMENT ON COLUMN "ytora"."sys_role_data_scope_group"."role_id" IS '角色ID';
COMMENT ON COLUMN "ytora"."sys_role_data_scope_group"."permission_id" IS '所属的资源ID';
COMMENT ON COLUMN "ytora"."sys_role_data_scope_group"."group_id" IS '数据范围分组ID';
COMMENT ON TABLE "ytora"."sys_role_data_scope_group" IS '角色-数据范围分组关系表';

-- ----------------------------
-- Records of sys_role_data_scope_group
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS "ytora"."sys_role_permission";
CREATE TABLE "ytora"."sys_role_permission" (
  "id" int8 NOT NULL,
  "create_by" varchar(255) COLLATE "pg_catalog"."default",
  "create_time" timestamp(6),
  "update_by" varchar(255) COLLATE "pg_catalog"."default",
  "update_time" timestamp(6),
  "depart_code" varchar(255) COLLATE "pg_catalog"."default",
  "remark" varchar(255) COLLATE "pg_catalog"."default",
  "role_id" int8 NOT NULL,
  "permission_id" int8 NOT NULL
)
;
COMMENT ON COLUMN "ytora"."sys_role_permission"."id" IS '数据主键ID';
COMMENT ON COLUMN "ytora"."sys_role_permission"."create_by" IS '数据创建人';
COMMENT ON COLUMN "ytora"."sys_role_permission"."create_time" IS '数据创建日期';
COMMENT ON COLUMN "ytora"."sys_role_permission"."update_by" IS '数据修改人';
COMMENT ON COLUMN "ytora"."sys_role_permission"."update_time" IS '数据修改日期';
COMMENT ON COLUMN "ytora"."sys_role_permission"."depart_code" IS '数据创建人所属部门';
COMMENT ON COLUMN "ytora"."sys_role_permission"."role_id" IS '角色ID';
COMMENT ON COLUMN "ytora"."sys_role_permission"."permission_id" IS '资源ID';
COMMENT ON TABLE "ytora"."sys_role_permission" IS '角色-资源关系表';

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO "ytora"."sys_role_permission" VALUES (717053143482368, 'ytora', '2026-05-03 15:16:02.663717', 'ytora', '2026-05-03 15:16:02.663717', '707063588388864', NULL, 1, 5);
INSERT INTO "ytora"."sys_role_permission" VALUES (717054079401984, 'ytora', '2026-05-03 15:16:16.944349', 'ytora', '2026-05-03 15:16:16.944349', '707063588388864', NULL, 1, 22);
INSERT INTO "ytora"."sys_role_permission" VALUES (717054079401985, 'ytora', '2026-05-03 15:16:16.944349', 'ytora', '2026-05-03 15:16:16.944349', '707063588388864', NULL, 1, 23);
INSERT INTO "ytora"."sys_role_permission" VALUES (717054079401986, 'ytora', '2026-05-03 15:16:16.944349', 'ytora', '2026-05-03 15:16:16.944349', '707063588388864', NULL, 1, 24);
INSERT INTO "ytora"."sys_role_permission" VALUES (717054079401987, 'ytora', '2026-05-03 15:16:16.944349', 'ytora', '2026-05-03 15:16:16.944349', '707063588388864', NULL, 1, 25);
INSERT INTO "ytora"."sys_role_permission" VALUES (717054079401988, 'ytora', '2026-05-03 15:16:16.944349', 'ytora', '2026-05-03 15:16:16.944349', '707063588388864', NULL, 1, 26);
INSERT INTO "ytora"."sys_role_permission" VALUES (717054079401989, 'ytora', '2026-05-03 15:16:16.944925', 'ytora', '2026-05-03 15:16:16.944937', '707063588388864', NULL, 1, 27);
INSERT INTO "ytora"."sys_role_permission" VALUES (717054079401990, 'ytora', '2026-05-03 15:16:16.944937', 'ytora', '2026-05-03 15:16:16.944937', '707063588388864', NULL, 1, 28);
INSERT INTO "ytora"."sys_role_permission" VALUES (717054079401991, 'ytora', '2026-05-03 15:16:16.944937', 'ytora', '2026-05-03 15:16:16.944937', '707063588388864', NULL, 1, 29);
INSERT INTO "ytora"."sys_role_permission" VALUES (717054079401992, 'ytora', '2026-05-03 15:16:16.944937', 'ytora', '2026-05-03 15:16:16.944937', '707063588388864', NULL, 1, 30);
INSERT INTO "ytora"."sys_role_permission" VALUES (717054079467520, 'ytora', '2026-05-03 15:16:16.945037', 'ytora', '2026-05-03 15:16:16.945037', '707063588388864', NULL, 1, 10);
INSERT INTO "ytora"."sys_role_permission" VALUES (717054079467521, 'ytora', '2026-05-03 15:16:16.945037', 'ytora', '2026-05-03 15:16:16.945037', '707063588388864', NULL, 1, 11);
INSERT INTO "ytora"."sys_role_permission" VALUES (717054079467522, 'ytora', '2026-05-03 15:16:16.945037', 'ytora', '2026-05-03 15:16:16.945037', '707063588388864', NULL, 1, 12);
INSERT INTO "ytora"."sys_role_permission" VALUES (717054079467523, 'ytora', '2026-05-03 15:16:16.945037', 'ytora', '2026-05-03 15:16:16.945131', '707063588388864', NULL, 1, 13);
INSERT INTO "ytora"."sys_role_permission" VALUES (717054079467524, 'ytora', '2026-05-03 15:16:16.945131', 'ytora', '2026-05-03 15:16:16.945131', '707063588388864', NULL, 1, 14);
INSERT INTO "ytora"."sys_role_permission" VALUES (717054079467525, 'ytora', '2026-05-03 15:16:16.945131', 'ytora', '2026-05-03 15:16:16.945131', '707063588388864', NULL, 1, 15);
INSERT INTO "ytora"."sys_role_permission" VALUES (717054079467526, 'ytora', '2026-05-03 15:16:16.945131', 'ytora', '2026-05-03 15:16:16.945131', '707063588388864', NULL, 1, 16);
INSERT INTO "ytora"."sys_role_permission" VALUES (717054079467527, 'ytora', '2026-05-03 15:16:16.945226', 'ytora', '2026-05-03 15:16:16.945226', '707063588388864', NULL, 1, 17);
INSERT INTO "ytora"."sys_role_permission" VALUES (717054079467528, 'ytora', '2026-05-03 15:16:16.945226', 'ytora', '2026-05-03 15:16:16.945226', '707063588388864', NULL, 1, 18);
INSERT INTO "ytora"."sys_role_permission" VALUES (717054079467529, 'ytora', '2026-05-03 15:16:16.945226', 'ytora', '2026-05-03 15:16:16.945226', '707063588388864', NULL, 1, 19);
INSERT INTO "ytora"."sys_role_permission" VALUES (717054079467530, 'ytora', '2026-05-03 15:16:16.945226', 'ytora', '2026-05-03 15:16:16.945319', '707063588388864', NULL, 1, 1);
INSERT INTO "ytora"."sys_role_permission" VALUES (717054079467531, 'ytora', '2026-05-03 15:16:16.945319', 'ytora', '2026-05-03 15:16:16.945319', '707063588388864', NULL, 1, 2);
INSERT INTO "ytora"."sys_role_permission" VALUES (717054079467532, 'ytora', '2026-05-03 15:16:16.945319', 'ytora', '2026-05-03 15:16:16.945319', '707063588388864', NULL, 1, 3);
INSERT INTO "ytora"."sys_role_permission" VALUES (717054079467533, 'ytora', '2026-05-03 15:16:16.945394', 'ytora', '2026-05-03 15:16:16.945394', '707063588388864', NULL, 1, 4);
INSERT INTO "ytora"."sys_role_permission" VALUES (717054079467534, 'ytora', '2026-05-03 15:16:16.945394', 'ytora', '2026-05-03 15:16:16.945394', '707063588388864', NULL, 1, 7);
INSERT INTO "ytora"."sys_role_permission" VALUES (717054079467535, 'ytora', '2026-05-03 15:16:16.945394', 'ytora', '2026-05-03 15:16:16.945394', '707063588388864', NULL, 1, 8);
INSERT INTO "ytora"."sys_role_permission" VALUES (717054079467536, 'ytora', '2026-05-03 15:16:16.945394', 'ytora', '2026-05-03 15:16:16.945394', '707063588388864', NULL, 1, 9);
INSERT INTO "ytora"."sys_role_permission" VALUES (717054079467537, 'ytora', '2026-05-03 15:16:16.945394', 'ytora', '2026-05-03 15:16:16.945394', '707063588388864', NULL, 1, 20);
INSERT INTO "ytora"."sys_role_permission" VALUES (717054079467538, 'ytora', '2026-05-03 15:16:16.945394', 'ytora', '2026-05-03 15:16:16.945394', '707063588388864', NULL, 1, 21);
INSERT INTO "ytora"."sys_role_permission" VALUES (717050531479560, 'ytora', '2026-05-03 15:15:22.807886', 'ytora', '2026-05-03 15:15:22.807886', '707063588388864', NULL, 1, 6);

-- ----------------------------
-- Table structure for sys_scheduler_task
-- ----------------------------
DROP TABLE IF EXISTS "ytora"."sys_scheduler_task";
CREATE TABLE "ytora"."sys_scheduler_task" (
  "id" int8 NOT NULL,
  "create_by" varchar(255) COLLATE "pg_catalog"."default",
  "create_time" timestamp(6),
  "update_by" varchar(255) COLLATE "pg_catalog"."default",
  "update_time" timestamp(6),
  "depart_code" varchar(255) COLLATE "pg_catalog"."default",
  "remark" varchar(255) COLLATE "pg_catalog"."default",
  "time_wheel_task_id" varchar(255) COLLATE "pg_catalog"."default",
  "task_name" varchar(255) COLLATE "pg_catalog"."default",
  "task_code" varchar(255) COLLATE "pg_catalog"."default",
  "cron" varchar(255) COLLATE "pg_catalog"."default",
  "type" int2,
  "params" varchar(255) COLLATE "pg_catalog"."default",
  "status" int2
)
;
COMMENT ON COLUMN "ytora"."sys_scheduler_task"."id" IS '数据主键ID';
COMMENT ON COLUMN "ytora"."sys_scheduler_task"."create_by" IS '数据创建人';
COMMENT ON COLUMN "ytora"."sys_scheduler_task"."create_time" IS '数据创建日期';
COMMENT ON COLUMN "ytora"."sys_scheduler_task"."update_by" IS '数据修改人';
COMMENT ON COLUMN "ytora"."sys_scheduler_task"."update_time" IS '数据修改日期';
COMMENT ON COLUMN "ytora"."sys_scheduler_task"."depart_code" IS '数据创建人所属部门';
COMMENT ON COLUMN "ytora"."sys_scheduler_task"."time_wheel_task_id" IS '时间轮任务ID';
COMMENT ON COLUMN "ytora"."sys_scheduler_task"."task_name" IS '任务名称';
COMMENT ON COLUMN "ytora"."sys_scheduler_task"."task_code" IS '任务code';
COMMENT ON COLUMN "ytora"."sys_scheduler_task"."cron" IS '任务执行CRON';
COMMENT ON COLUMN "ytora"."sys_scheduler_task"."type" IS '任务类型';
COMMENT ON COLUMN "ytora"."sys_scheduler_task"."params" IS '任务参数';
COMMENT ON COLUMN "ytora"."sys_scheduler_task"."status" IS '任务状态，1-未启动/2-运行中';
COMMENT ON TABLE "ytora"."sys_scheduler_task" IS '调度任务表';

-- ----------------------------
-- Records of sys_scheduler_task
-- ----------------------------
INSERT INTO "ytora"."sys_scheduler_task" VALUES (718086710165504, 'ytora', '2026-05-03 19:38:53.640977', 'ytora', '2026-05-03 19:38:53.640977', '707063588388864', NULL, NULL, '测试任务', 'test', '0/5 * * * * ?', NULL, NULL, 2);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS "ytora"."sys_user";
CREATE TABLE "ytora"."sys_user" (
  "id" int8 NOT NULL,
  "create_by" varchar(255) COLLATE "pg_catalog"."default",
  "create_time" timestamp(6),
  "update_by" varchar(255) COLLATE "pg_catalog"."default",
  "update_time" timestamp(6),
  "depart_code" varchar(255) COLLATE "pg_catalog"."default",
  "remark" varchar(255) COLLATE "pg_catalog"."default",
  "user_name" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "real_name" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "password" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  "avatar" varchar(255) COLLATE "pg_catalog"."default",
  "phone" varchar(16) COLLATE "pg_catalog"."default",
  "email" varchar(255) COLLATE "pg_catalog"."default",
  "birthday" date,
  "id_card" varchar(255) COLLATE "pg_catalog"."default",
  "status" int4
)
;
COMMENT ON COLUMN "ytora"."sys_user"."id" IS '数据主键ID';
COMMENT ON COLUMN "ytora"."sys_user"."create_by" IS '数据创建人';
COMMENT ON COLUMN "ytora"."sys_user"."create_time" IS '数据创建日期';
COMMENT ON COLUMN "ytora"."sys_user"."update_by" IS '数据修改人';
COMMENT ON COLUMN "ytora"."sys_user"."update_time" IS '数据修改日期';
COMMENT ON COLUMN "ytora"."sys_user"."depart_code" IS '数据创建人所属部门';
COMMENT ON COLUMN "ytora"."sys_user"."user_name" IS '用户名';
COMMENT ON COLUMN "ytora"."sys_user"."real_name" IS '真实姓名';
COMMENT ON COLUMN "ytora"."sys_user"."password" IS '密码';
COMMENT ON COLUMN "ytora"."sys_user"."avatar" IS '头像';
COMMENT ON COLUMN "ytora"."sys_user"."phone" IS '手机号码';
COMMENT ON COLUMN "ytora"."sys_user"."email" IS '邮箱';
COMMENT ON COLUMN "ytora"."sys_user"."birthday" IS '生日';
COMMENT ON COLUMN "ytora"."sys_user"."id_card" IS '身份证';
COMMENT ON COLUMN "ytora"."sys_user"."status" IS '状态,1-正常/2-冻结';
COMMENT ON TABLE "ytora"."sys_user" IS '用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO "ytora"."sys_user" VALUES (2, 'admin', '2026-05-03 18:46:08.770908', 'ytora', '2026-05-04 21:07:04.599631', 'A01-02-01', '1234', 'ytora', '杨桐', '220600', '/avatar.jpg', '123456', '123@456.com', '2000-01-01', '123456X', 1);
INSERT INTO "ytora"."sys_user" VALUES (1, 'admin', '2026-05-03 18:46:08.770908', 'ytora', '2026-05-04 21:09:02.466201', 'B01', '333', 'admin', '管理员', '220600', '/avatar.jpg', '123456', '123@456.com', '2000-01-01', '123456X', 1);

-- ----------------------------
-- Table structure for sys_user_depart
-- ----------------------------
DROP TABLE IF EXISTS "ytora"."sys_user_depart";
CREATE TABLE "ytora"."sys_user_depart" (
  "id" int8 NOT NULL,
  "create_by" varchar(255) COLLATE "pg_catalog"."default",
  "create_time" timestamp(6),
  "update_by" varchar(255) COLLATE "pg_catalog"."default",
  "update_time" timestamp(6),
  "depart_code" varchar(255) COLLATE "pg_catalog"."default",
  "remark" varchar(255) COLLATE "pg_catalog"."default",
  "user_id" int8 NOT NULL,
  "depart_id" int8 NOT NULL
)
;
COMMENT ON COLUMN "ytora"."sys_user_depart"."id" IS '数据主键ID';
COMMENT ON COLUMN "ytora"."sys_user_depart"."create_by" IS '数据创建人';
COMMENT ON COLUMN "ytora"."sys_user_depart"."create_time" IS '数据创建日期';
COMMENT ON COLUMN "ytora"."sys_user_depart"."update_by" IS '数据修改人';
COMMENT ON COLUMN "ytora"."sys_user_depart"."update_time" IS '数据修改日期';
COMMENT ON COLUMN "ytora"."sys_user_depart"."depart_code" IS '数据创建人所属部门';
COMMENT ON COLUMN "ytora"."sys_user_depart"."user_id" IS '用户ID';
COMMENT ON COLUMN "ytora"."sys_user_depart"."depart_id" IS '部门ID';
COMMENT ON TABLE "ytora"."sys_user_depart" IS '用户-部门关系表';

-- ----------------------------
-- Records of sys_user_depart
-- ----------------------------
INSERT INTO "ytora"."sys_user_depart" VALUES (724076292931584, 'ytora', '2026-05-04 21:02:07.419457', 'ytora', '2026-05-04 21:02:07.420456', 'A01', NULL, 2, 724100000000011);
INSERT INTO "ytora"."sys_user_depart" VALUES (724076698796032, 'ytora', '2026-05-04 21:02:13.613566', 'ytora', '2026-05-04 21:02:13.613566', 'A01', NULL, 2, 724100000000012);
INSERT INTO "ytora"."sys_user_depart" VALUES (724077282459648, 'ytora', '2026-05-04 21:02:22.518613', 'ytora', '2026-05-04 21:02:22.518613', 'A01', NULL, 2, 724100000000021);
INSERT INTO "ytora"."sys_user_depart" VALUES (724103089815552, 'ytora', '2026-05-04 21:08:56.307629', 'ytora', '2026-05-04 21:08:56.307629', 'A01', NULL, 1, 724053956427776);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS "ytora"."sys_user_role";
CREATE TABLE "ytora"."sys_user_role" (
  "id" int8 NOT NULL,
  "create_by" varchar(255) COLLATE "pg_catalog"."default",
  "create_time" timestamp(6),
  "update_by" varchar(255) COLLATE "pg_catalog"."default",
  "update_time" timestamp(6),
  "depart_code" varchar(255) COLLATE "pg_catalog"."default",
  "remark" varchar(255) COLLATE "pg_catalog"."default",
  "user_id" int8 NOT NULL,
  "role_id" int8 NOT NULL
)
;
COMMENT ON COLUMN "ytora"."sys_user_role"."id" IS '数据主键ID';
COMMENT ON COLUMN "ytora"."sys_user_role"."create_by" IS '数据创建人';
COMMENT ON COLUMN "ytora"."sys_user_role"."create_time" IS '数据创建日期';
COMMENT ON COLUMN "ytora"."sys_user_role"."update_by" IS '数据修改人';
COMMENT ON COLUMN "ytora"."sys_user_role"."update_time" IS '数据修改日期';
COMMENT ON COLUMN "ytora"."sys_user_role"."depart_code" IS '数据创建人所属部门';
COMMENT ON COLUMN "ytora"."sys_user_role"."user_id" IS '用户ID';
COMMENT ON COLUMN "ytora"."sys_user_role"."role_id" IS '角色ID';
COMMENT ON TABLE "ytora"."sys_user_role" IS '用户-角色关系表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO "ytora"."sys_user_role" VALUES (1, NULL, NULL, NULL, NULL, NULL, NULL, 1, 1);
INSERT INTO "ytora"."sys_user_role" VALUES (2, NULL, NULL, NULL, NULL, NULL, NULL, 2, 1);

-- ----------------------------
-- Primary Key structure for table base
-- ----------------------------
ALTER TABLE "ytora"."base" ADD CONSTRAINT "base_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table sys_config
-- ----------------------------
ALTER TABLE "ytora"."sys_config" ADD CONSTRAINT "sys_config_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table sys_data_scope
-- ----------------------------
ALTER TABLE "ytora"."sys_data_scope" ADD CONSTRAINT "sys_data_scope_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table sys_data_scope_group
-- ----------------------------
ALTER TABLE "ytora"."sys_data_scope_group" ADD CONSTRAINT "sys_data_scope_group_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table sys_depart
-- ----------------------------
ALTER TABLE "ytora"."sys_depart" ADD CONSTRAINT "sys_depart_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table sys_dict
-- ----------------------------
ALTER TABLE "ytora"."sys_dict" ADD CONSTRAINT "sys_dict_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table sys_dict_item
-- ----------------------------
ALTER TABLE "ytora"."sys_dict_item" ADD CONSTRAINT "sys_dict_item_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table sys_file
-- ----------------------------
ALTER TABLE "ytora"."sys_file" ADD CONSTRAINT "sys_file_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table sys_folder
-- ----------------------------
ALTER TABLE "ytora"."sys_folder" ADD CONSTRAINT "sys_folder_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table sys_icon
-- ----------------------------
ALTER TABLE "ytora"."sys_icon" ADD CONSTRAINT "sys_icon_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table sys_log
-- ----------------------------
ALTER TABLE "ytora"."sys_log" ADD CONSTRAINT "sys_log_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table sys_permission
-- ----------------------------
ALTER TABLE "ytora"."sys_permission" ADD CONSTRAINT "sys_permission_copy1_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table sys_recycle_bin
-- ----------------------------
ALTER TABLE "ytora"."sys_recycle_bin" ADD CONSTRAINT "sys_recycle_bin_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table sys_role
-- ----------------------------
ALTER TABLE "ytora"."sys_role" ADD CONSTRAINT "sys_role_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table sys_role_data_scope
-- ----------------------------
ALTER TABLE "ytora"."sys_role_data_scope" ADD CONSTRAINT "sys_role_data_scope_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table sys_role_data_scope_group
-- ----------------------------
ALTER TABLE "ytora"."sys_role_data_scope_group" ADD CONSTRAINT "sys_role_data_scope_group_pkey" PRIMARY KEY ("id");

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
