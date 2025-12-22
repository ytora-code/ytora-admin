-- ----------------------------
-- Table structure for sys_depart
-- ----------------------------
DROP TABLE IF EXISTS "ytora"."sys_depart";
CREATE TABLE "ytora"."sys_depart"
(
    "id"                int8                                        NOT NULL,
    "create_by"         varchar(16) COLLATE "pg_catalog"."default",
    "create_time"       timestamp(6),
    "update_by"         varchar(16) COLLATE "pg_catalog"."default",
    "update_time"       timestamp(6),
    "depart_code"       varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
    "remark"            varchar(255) COLLATE "pg_catalog"."default",
    "status"            varchar(255) COLLATE "pg_catalog"."default",
    "pid"               varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
    "depart_name"       varchar(255) COLLATE "pg_catalog"."default",
    "type"              varchar(255) COLLATE "pg_catalog"."default",
    "contact_user_name" varchar(255) COLLATE "pg_catalog"."default"
)
;
COMMENT
ON COLUMN "ytora"."sys_depart"."id" IS '主键ID';
COMMENT
ON COLUMN "ytora"."sys_depart"."create_by" IS '创建人';
COMMENT
ON COLUMN "ytora"."sys_depart"."create_time" IS '创建时间';
COMMENT
ON COLUMN "ytora"."sys_depart"."update_by" IS '更新人';
COMMENT
ON COLUMN "ytora"."sys_depart"."update_time" IS '更新时间';
COMMENT
ON COLUMN "ytora"."sys_depart"."depart_code" IS '部门编码';
COMMENT
ON COLUMN "ytora"."sys_depart"."remark" IS '数据备注';
COMMENT
ON COLUMN "ytora"."sys_depart"."status" IS '数据状态';
COMMENT
ON COLUMN "ytora"."sys_depart"."pid" IS '上级部门id';
COMMENT
ON COLUMN "ytora"."sys_depart"."depart_name" IS '部门名称';
COMMENT
ON COLUMN "ytora"."sys_depart"."type" IS '部门类型';
COMMENT
ON COLUMN "ytora"."sys_depart"."contact_user_name" IS '部门联系人username';
COMMENT
ON TABLE "ytora"."sys_depart" IS '部门表';

-- ----------------------------
-- Records of sys_depart
-- ----------------------------
INSERT INTO "ytora"."sys_depart"
VALUES (1, NULL, '2025-01-01 09:00:00', NULL, NULL, 'YT-001', '集团最高管理机构', '1', '0', '宇拓集团总部', '1', NULL);
INSERT INTO "ytora"."sys_depart"
VALUES (2, NULL, '2025-01-01 09:00:00', NULL, NULL, 'YT-RD-01', '负责技术战略与产品研发', '1', '1', '研发管理中心', '2',
        NULL);
INSERT INTO "ytora"."sys_depart"
VALUES (3, NULL, '2025-01-01 09:00:00', NULL, NULL, 'YT-FI-01', '负责集团财务与审计', '1', '1', '财务管理中心', '2',
        NULL);
INSERT INTO "ytora"."sys_depart"
VALUES (4, NULL, '2025-01-01 09:00:00', NULL, NULL, 'YT-HR-01', '负责人才发展与行政保障', '1', '1', '人力行政中心', '2',
        NULL);
INSERT INTO "ytora"."sys_depart"
VALUES (5, NULL, '2025-01-01 09:00:00', NULL, NULL, 'YT-SA-01', '负责全国业务拓展', '1', '1', '市场营销中心', '2',
        NULL);
INSERT INTO "ytora"."sys_depart"
VALUES (6, NULL, '2025-01-01 09:00:00', NULL, NULL, 'YT-RD-SOFT', '后端、前端及移动端开发', '1', '2', '软件开发部', '3',
        NULL);
INSERT INTO "ytora"."sys_depart"
VALUES (7, NULL, '2025-01-01 09:00:00', NULL, NULL, 'YT-RD-TEST', '自动化测试与性能压测', '1', '2', '质量测试部', '3',
        NULL);
INSERT INTO "ytora"."sys_depart"
VALUES (8, NULL, '2025-01-01 09:00:00', NULL, NULL, 'YT-FI-ACC', '日常账务处理', '1', '3', '会计核算部', '3', NULL);
INSERT INTO "ytora"."sys_depart"
VALUES (9, NULL, '2025-01-01 09:00:00', NULL, NULL, 'YT-HR-RECR', '高端人才猎聘', '1', '4', '招聘调配部', '3', NULL);
INSERT INTO "ytora"."sys_depart"
VALUES (10, NULL, '2025-01-01 09:00:00', NULL, NULL, 'YT-SA-EAST', '覆盖沪浙苏地区业务', '1', '5', '华东业务部', '3',
        NULL);
INSERT INTO "ytora"."sys_depart"
VALUES (11, NULL, '2025-01-01 09:00:00', NULL, NULL, 'YT-IT-OPS', '云原生架构与系统运维', '1', '2', '运维保障部', '3',
        NULL);

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS "ytora"."sys_log";
CREATE TABLE "ytora"."sys_log"
(
    "id"            int8 NOT NULL,
    "create_by"     varchar(16) COLLATE "pg_catalog"."default",
    "create_time"   timestamp(6),
    "update_by"     varchar(16) COLLATE "pg_catalog"."default",
    "update_time"   timestamp(6),
    "depart_code"   varchar(255) COLLATE "pg_catalog"."default",
    "remark"        varchar(255) COLLATE "pg_catalog"."default",
    "status"        varchar(255) COLLATE "pg_catalog"."default",
    "type"          int4,
    "ip"            varchar(255) COLLATE "pg_catalog"."default",
    "trace_id"      varchar(255) COLLATE "pg_catalog"."default",
    "thread"        varchar(255) COLLATE "pg_catalog"."default",
    "request_url"   varchar(255) COLLATE "pg_catalog"."default",
    "class_name"    varchar(255) COLLATE "pg_catalog"."default",
    "method_name"   varchar(255) COLLATE "pg_catalog"."default",
    "params"        varchar(255) COLLATE "pg_catalog"."default",
    "result_length" int4,
    "cost"          int8,
    "content"       varchar(255) COLLATE "pg_catalog"."default",
    "error_msg"     varchar(255) COLLATE "pg_catalog"."default",
    "error_stack"   varchar(255) COLLATE "pg_catalog"."default"
)
;
COMMENT
ON COLUMN "ytora"."sys_log"."id" IS '主键ID';
COMMENT
ON COLUMN "ytora"."sys_log"."create_by" IS '创建人';
COMMENT
ON COLUMN "ytora"."sys_log"."create_time" IS '创建时间';
COMMENT
ON COLUMN "ytora"."sys_log"."update_by" IS '更新人';
COMMENT
ON COLUMN "ytora"."sys_log"."update_time" IS '更新时间';
COMMENT
ON COLUMN "ytora"."sys_log"."depart_code" IS '创建者所属部门';
COMMENT
ON COLUMN "ytora"."sys_log"."remark" IS '数据备注';
COMMENT
ON COLUMN "ytora"."sys_log"."status" IS '数据状态';
COMMENT
ON COLUMN "ytora"."sys_log"."type" IS '日志类型';
COMMENT
ON COLUMN "ytora"."sys_log"."ip" IS '操作人ip';
COMMENT
ON COLUMN "ytora"."sys_log"."trace_id" IS '链路跟踪 ID，用于聚合同一次调用的所有日志';
COMMENT
ON COLUMN "ytora"."sys_log"."thread" IS '所在线程信息';
COMMENT
ON COLUMN "ytora"."sys_log"."request_url" IS 'HTTP 请求路径';
COMMENT
ON COLUMN "ytora"."sys_log"."class_name" IS 'HTTP 请求路径';
COMMENT
ON COLUMN "ytora"."sys_log"."method_name" IS '日志发生的方法名';
COMMENT
ON COLUMN "ytora"."sys_log"."params" IS '参数';
COMMENT
ON COLUMN "ytora"."sys_log"."result_length" IS '返回值大小';
COMMENT
ON COLUMN "ytora"."sys_log"."cost" IS '请求耗时';
COMMENT
ON COLUMN "ytora"."sys_log"."content" IS '日志主体内容';
COMMENT
ON COLUMN "ytora"."sys_log"."error_msg" IS '错误信息';
COMMENT
ON COLUMN "ytora"."sys_log"."error_stack" IS '错误堆栈信息';
COMMENT
ON TABLE "ytora"."sys_log" IS '日志表';

-- ----------------------------
-- Records of sys_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS "ytora"."sys_permission";
CREATE TABLE "ytora"."sys_permission"
(
    "id"              int8                                        NOT NULL,
    "create_by"       varchar(16) COLLATE "pg_catalog"."default",
    "create_time"     timestamp(6),
    "update_by"       varchar(16) COLLATE "pg_catalog"."default",
    "update_time"     timestamp(6),
    "depart_code"     varchar(255) COLLATE "pg_catalog"."default",
    "remark"          varchar(255) COLLATE "pg_catalog"."default",
    "status"          varchar(255) COLLATE "pg_catalog"."default",
    "pid"             varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
    "permission_name" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
    "permission_code" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
    "permission_type" varchar(255) COLLATE "pg_catalog"."default",
    "icon"            varchar(255) COLLATE "pg_catalog"."default",
    "visible"         bool,
    "index"           int4
)
;
COMMENT
ON COLUMN "ytora"."sys_permission"."id" IS '主键ID';
COMMENT
ON COLUMN "ytora"."sys_permission"."create_by" IS '创建人';
COMMENT
ON COLUMN "ytora"."sys_permission"."create_time" IS '创建时间';
COMMENT
ON COLUMN "ytora"."sys_permission"."update_by" IS '更新人';
COMMENT
ON COLUMN "ytora"."sys_permission"."update_time" IS '更新时间';
COMMENT
ON COLUMN "ytora"."sys_permission"."depart_code" IS '创建者所属部门';
COMMENT
ON COLUMN "ytora"."sys_permission"."remark" IS '数据备注';
COMMENT
ON COLUMN "ytora"."sys_permission"."status" IS '数据状态';
COMMENT
ON COLUMN "ytora"."sys_permission"."pid" IS '父资源id';
COMMENT
ON COLUMN "ytora"."sys_permission"."permission_name" IS '资源名称';
COMMENT
ON COLUMN "ytora"."sys_permission"."permission_code" IS '资源编码，例如：接口地址、页面的路由地址、页面元素(按钮)的唯一标识';
COMMENT
ON COLUMN "ytora"."sys_permission"."permission_type" IS '资源类型，1-接口、2-页面、3-页面元素';
COMMENT
ON COLUMN "ytora"."sys_permission"."icon" IS '图标';
COMMENT
ON COLUMN "ytora"."sys_permission"."visible" IS '是否可见';
COMMENT
ON COLUMN "ytora"."sys_permission"."index" IS '排序';
COMMENT
ON TABLE "ytora"."sys_permission" IS '资源表';

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO "ytora"."sys_permission"
VALUES (1, NULL, NULL, NULL, NULL, NULL, NULL, '1', '0', '权限管理', '/rbac', '2', 'FingerPrintOutline', 't', 0);
INSERT INTO "ytora"."sys_permission"
VALUES (2, NULL, NULL, NULL, NULL, NULL, NULL, '1', '1', '用户管理', '/rbac/user', '2', 'PersonOutline', 't', 1);
INSERT INTO "ytora"."sys_permission"
VALUES (3, NULL, NULL, NULL, NULL, NULL, NULL, '1', '1', '角色管理', '/rbac/role', '2', 'AccessibilityOutline', 't', 2);
INSERT INTO "ytora"."sys_permission"
VALUES (5, NULL, NULL, NULL, NULL, NULL, NULL, '1', '1', '部门管理', '/rbac/depart', '2', 'TriangleOutline', 't', 4);
INSERT INTO "ytora"."sys_permission"
VALUES (6, NULL, NULL, NULL, NULL, NULL, NULL, '1', '0', '系统管理', '/sys', '2', 'SettingsOutline', 't', 1);
INSERT INTO "ytora"."sys_permission"
VALUES (7, NULL, NULL, NULL, NULL, NULL, NULL, '1', '6', '数据字典', '/sys/dict', '2', 'BookOutline', 't', 1);
INSERT INTO "ytora"."sys_permission"
VALUES (4, NULL, NULL, NULL, NULL, NULL, NULL, '1', '1', '资源管理', '/rbac/permission', '2', 'GridOutline', 't', 3);
INSERT INTO "ytora"."sys_permission"
VALUES (8, NULL, NULL, NULL, NULL, NULL, NULL, '1', '6', '缓存管理', '/sys/cache', '2', 'LayersOutline', 't', 2);
INSERT INTO "ytora"."sys_permission"
VALUES (9, NULL, NULL, NULL, NULL, NULL, NULL, '1', '6', '数据库管理', '/sys/db', '2', 'ServerOutline', 't', 4);
INSERT INTO "ytora"."sys_permission"
VALUES (11, NULL, NULL, NULL, NULL, NULL, NULL, '1', '6', '文件管理', '/sys/file', '2', 'FileTrayFullOutline', 't', 5);
INSERT INTO "ytora"."sys_permission"
VALUES (10, NULL, NULL, NULL, NULL, NULL, NULL, '1', '6', '日志管理', '/sys/log', '2', 'TerminalOutline', 't', 3);
INSERT INTO "ytora"."sys_permission"
VALUES (12, NULL, NULL, NULL, NULL, NULL, NULL, '1', '6', '定时任务', '/sys/cron', '2', 'AlarmOutline', 't', 5);
INSERT INTO "ytora"."sys_permission"
VALUES (13, NULL, NULL, NULL, NULL, NULL, NULL, '1', '6', '系统监控', '/sys/monitor', '2', 'SpeedometerOutline', 't',
        6);
INSERT INTO "ytora"."sys_permission"
VALUES (14, NULL, NULL, NULL, NULL, NULL, NULL, '1', '6', 'API接口', '/sys/api', '2', 'CodeSlashOutline', 't', 7);
INSERT INTO "ytora"."sys_permission"
VALUES (15, NULL, NULL, NULL, NULL, NULL, NULL, '1', '0', '公共模块', '/common', '2', 'LockOpenOutline', 't', 2);
INSERT INTO "ytora"."sys_permission"
VALUES (16, NULL, NULL, NULL, NULL, NULL, NULL, '1', '15', '图标库', '/common/icon', '2', 'StarOutline', 't', 1);
INSERT INTO "ytora"."sys_permission"
VALUES (17, NULL, NULL, NULL, NULL, NULL, NULL, '1', '15', '系统说明', '/common/remark', '2', 'DocumentOutline', 't',
        2);

-- ----------------------------
-- Table structure for sys_recycle_bin
-- ----------------------------
DROP TABLE IF EXISTS "ytora"."sys_recycle_bin";
CREATE TABLE "ytora"."sys_recycle_bin"
(
    "id"             serial,
    "create_by"      varchar(16) COLLATE "pg_catalog"."default",
    "create_time"    timestamp(6),
    "update_by"      varchar(16) COLLATE "pg_catalog"."default",
    "update_time"    timestamp(6),
    "depart_code"    varchar(255) COLLATE "pg_catalog"."default",
    "remark"         varchar(255) COLLATE "pg_catalog"."default",
    "status"         varchar(255) COLLATE "pg_catalog"."default",
    "deleted_by"     varchar(255) COLLATE "pg_catalog"."default",
    "deleted_time"   timestamp(6),
    "delete_reason"  varchar(255) COLLATE "pg_catalog"."default",
    "original_table" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
    "original_id"    varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
    "original_data"  jsonb                                       NOT NULL
)
;
COMMENT
ON COLUMN "ytora"."sys_recycle_bin"."id" IS '主键ID';
COMMENT
ON COLUMN "ytora"."sys_recycle_bin"."create_by" IS '创建人';
COMMENT
ON COLUMN "ytora"."sys_recycle_bin"."create_time" IS '创建时间';
COMMENT
ON COLUMN "ytora"."sys_recycle_bin"."update_by" IS '更新人';
COMMENT
ON COLUMN "ytora"."sys_recycle_bin"."update_time" IS '更新时间';
COMMENT
ON COLUMN "ytora"."sys_recycle_bin"."depart_code" IS '创建者所属部门';
COMMENT
ON COLUMN "ytora"."sys_recycle_bin"."remark" IS '数据备注';
COMMENT
ON COLUMN "ytora"."sys_recycle_bin"."status" IS '数据状态';
COMMENT
ON COLUMN "ytora"."sys_recycle_bin"."deleted_by" IS '删除人';
COMMENT
ON COLUMN "ytora"."sys_recycle_bin"."deleted_time" IS '删除时间';
COMMENT
ON COLUMN "ytora"."sys_recycle_bin"."delete_reason" IS '删除原因';
COMMENT
ON COLUMN "ytora"."sys_recycle_bin"."original_table" IS '原始表';
COMMENT
ON COLUMN "ytora"."sys_recycle_bin"."original_id" IS '原始数据id';
COMMENT
ON COLUMN "ytora"."sys_recycle_bin"."original_data" IS '原始数据，JSON';
COMMENT
ON TABLE "ytora"."sys_recycle_bin" IS '回收站';

-- ----------------------------
-- Records of sys_recycle_bin
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS "ytora"."sys_role";
CREATE TABLE "ytora"."sys_role"
(
    "id"          int8                                        NOT NULL,
    "create_by"   varchar(16) COLLATE "pg_catalog"."default",
    "create_time" timestamp(6),
    "update_by"   varchar(16) COLLATE "pg_catalog"."default",
    "update_time" timestamp(6),
    "depart_code" varchar(255) COLLATE "pg_catalog"."default",
    "remark"      varchar(255) COLLATE "pg_catalog"."default",
    "status"      varchar(255) COLLATE "pg_catalog"."default",
    "role_name"   varchar(255) COLLATE "pg_catalog"."default",
    "role_code"   varchar(255) COLLATE "pg_catalog"."default" NOT NULL
)
;
COMMENT
ON COLUMN "ytora"."sys_role"."id" IS '主键ID';
COMMENT
ON COLUMN "ytora"."sys_role"."create_by" IS '创建人';
COMMENT
ON COLUMN "ytora"."sys_role"."create_time" IS '创建时间';
COMMENT
ON COLUMN "ytora"."sys_role"."update_by" IS '更新人';
COMMENT
ON COLUMN "ytora"."sys_role"."update_time" IS '更新时间';
COMMENT
ON COLUMN "ytora"."sys_role"."depart_code" IS '创建者所属部门';
COMMENT
ON COLUMN "ytora"."sys_role"."remark" IS '数据备注';
COMMENT
ON COLUMN "ytora"."sys_role"."status" IS '数据状态';
COMMENT
ON COLUMN "ytora"."sys_role"."role_name" IS '角色名称';
COMMENT
ON COLUMN "ytora"."sys_role"."role_code" IS '角色编码';
COMMENT
ON TABLE "ytora"."sys_role" IS '角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO "ytora"."sys_role"
VALUES (1, NULL, '2025-01-01 09:00:00', NULL, NULL, NULL, '系统全权限', '1', '超级管理员', 'ROLE_SUPER_ADMIN');
INSERT INTO "ytora"."sys_role"
VALUES (2, NULL, '2025-01-01 09:00:00', NULL, NULL, NULL, '部门数据查看与审批权限', '1', '部门负责人',
        'ROLE_DEPT_LEADER');
INSERT INTO "ytora"."sys_role"
VALUES (3, NULL, '2025-01-01 09:00:00', NULL, NULL, NULL, '员工入离职管理权限', '1', '人事专员', 'ROLE_HR_SPECIALIST');
INSERT INTO "ytora"."sys_role"
VALUES (4, NULL, '2025-01-01 09:00:00', NULL, NULL, NULL, '财务报表查看与审计权', '1', '财务审计',
        'ROLE_FINANCE_AUDIT');
INSERT INTO "ytora"."sys_role"
VALUES (5, NULL, '2025-01-01 09:00:00', NULL, NULL, NULL, '研发系统操作权限', '1', '开发工程师', 'ROLE_DEV_ENGINEER');
INSERT INTO "ytora"."sys_role"
VALUES (6, NULL, '2025-01-01 09:00:00', NULL, NULL, NULL, '缺陷管理系统权限', '1', '测试工程师', 'ROLE_QA_ENGINEER');
INSERT INTO "ytora"."sys_role"
VALUES (7, NULL, '2025-01-01 09:00:00', NULL, NULL, NULL, '服务器监控与部署权限', '1', '运维工程师',
        'ROLE_OPS_ENGINEER');
INSERT INTO "ytora"."sys_role"
VALUES (8, NULL, '2025-01-01 09:00:00', NULL, NULL, NULL, 'CRM系统管理权限', '1', '销售主管', 'ROLE_SALES_MANAGER');
INSERT INTO "ytora"."sys_role"
VALUES (9, NULL, '2025-01-01 09:00:00', NULL, NULL, NULL, '基础办公系统权限', '1', '普通员工', 'ROLE_COMMON_USER');
INSERT INTO "ytora"."sys_role"
VALUES (10, NULL, '2025-01-01 09:00:00', NULL, NULL, NULL, '只读视图权限', '1', '外部顾问', 'ROLE_GUEST');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS "ytora"."sys_role_permission";
CREATE TABLE "ytora"."sys_role_permission"
(
    "id"            int8 NOT NULL,
    "create_by"     varchar(16) COLLATE "pg_catalog"."default",
    "create_time"   timestamp(6),
    "update_by"     varchar(16) COLLATE "pg_catalog"."default",
    "update_time"   timestamp(6),
    "depart_code"   varchar(255) COLLATE "pg_catalog"."default",
    "remark"        varchar(255) COLLATE "pg_catalog"."default",
    "status"        varchar(255) COLLATE "pg_catalog"."default",
    "role_id"       int8 NOT NULL,
    "permission_id" int8 NOT NULL
)
;
COMMENT
ON COLUMN "ytora"."sys_role_permission"."id" IS '主键ID';
COMMENT
ON COLUMN "ytora"."sys_role_permission"."create_by" IS '创建人';
COMMENT
ON COLUMN "ytora"."sys_role_permission"."create_time" IS '创建时间';
COMMENT
ON COLUMN "ytora"."sys_role_permission"."update_by" IS '更新人';
COMMENT
ON COLUMN "ytora"."sys_role_permission"."update_time" IS '更新时间';
COMMENT
ON COLUMN "ytora"."sys_role_permission"."depart_code" IS '创建者所属部门';
COMMENT
ON COLUMN "ytora"."sys_role_permission"."remark" IS '数据备注';
COMMENT
ON COLUMN "ytora"."sys_role_permission"."status" IS '数据状态';
COMMENT
ON COLUMN "ytora"."sys_role_permission"."role_id" IS '角色ID';
COMMENT
ON COLUMN "ytora"."sys_role_permission"."permission_id" IS '资源ID';
COMMENT
ON TABLE "ytora"."sys_role_permission" IS '角色-资源关系表';

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO "ytora"."sys_role_permission"
VALUES (1, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 1);
INSERT INTO "ytora"."sys_role_permission"
VALUES (2, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 2);
INSERT INTO "ytora"."sys_role_permission"
VALUES (3, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 3);
INSERT INTO "ytora"."sys_role_permission"
VALUES (4, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 4);
INSERT INTO "ytora"."sys_role_permission"
VALUES (5, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 5);
INSERT INTO "ytora"."sys_role_permission"
VALUES (6, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 6);
INSERT INTO "ytora"."sys_role_permission"
VALUES (7, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 7);
INSERT INTO "ytora"."sys_role_permission"
VALUES (8, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 8);
INSERT INTO "ytora"."sys_role_permission"
VALUES (9, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 9);
INSERT INTO "ytora"."sys_role_permission"
VALUES (10, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 10);
INSERT INTO "ytora"."sys_role_permission"
VALUES (11, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 11);
INSERT INTO "ytora"."sys_role_permission"
VALUES (12, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 12);
INSERT INTO "ytora"."sys_role_permission"
VALUES (13, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 13);
INSERT INTO "ytora"."sys_role_permission"
VALUES (14, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 14);
INSERT INTO "ytora"."sys_role_permission"
VALUES (15, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 15);
INSERT INTO "ytora"."sys_role_permission"
VALUES (16, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 16);
INSERT INTO "ytora"."sys_role_permission"
VALUES (17, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, 17);

-- ----------------------------
-- Table structure for sys_scheduler_task
-- ----------------------------
DROP TABLE IF EXISTS "ytora"."sys_scheduler_task";
CREATE TABLE "ytora"."sys_scheduler_task"
(
    "id"          int8 NOT NULL,
    "create_by"   varchar(16) COLLATE "pg_catalog"."default",
    "create_time" timestamp(6),
    "update_by"   varchar(16) COLLATE "pg_catalog"."default",
    "update_time" timestamp(6),
    "depart_code" varchar(255) COLLATE "pg_catalog"."default",
    "remark"      varchar(255) COLLATE "pg_catalog"."default",
    "status"      varchar(255) COLLATE "pg_catalog"."default",
    "task_name"   varchar(255) COLLATE "pg_catalog"."default",
    "cron"        varchar(255) COLLATE "pg_catalog"."default",
    "type"        int2,
    "bean_name"   varchar(255) COLLATE "pg_catalog"."default",
    "parameter"   varchar(255) COLLATE "pg_catalog"."default"
)
;
COMMENT
ON COLUMN "ytora"."sys_scheduler_task"."id" IS '主键ID';
COMMENT
ON COLUMN "ytora"."sys_scheduler_task"."create_by" IS '创建人';
COMMENT
ON COLUMN "ytora"."sys_scheduler_task"."create_time" IS '创建时间';
COMMENT
ON COLUMN "ytora"."sys_scheduler_task"."update_by" IS '更新人';
COMMENT
ON COLUMN "ytora"."sys_scheduler_task"."update_time" IS '更新时间';
COMMENT
ON COLUMN "ytora"."sys_scheduler_task"."depart_code" IS '创建者所属部门';
COMMENT
ON COLUMN "ytora"."sys_scheduler_task"."remark" IS '数据备注';
COMMENT
ON COLUMN "ytora"."sys_scheduler_task"."status" IS '数据状态';
COMMENT
ON COLUMN "ytora"."sys_scheduler_task"."task_name" IS '任务名称';
COMMENT
ON COLUMN "ytora"."sys_scheduler_task"."cron" IS '任务执行周期';
COMMENT
ON COLUMN "ytora"."sys_scheduler_task"."type" IS '任务类型';
COMMENT
ON COLUMN "ytora"."sys_scheduler_task"."bean_name" IS '任务Bean名称';
COMMENT
ON COLUMN "ytora"."sys_scheduler_task"."parameter" IS '任务参数';
COMMENT
ON TABLE "ytora"."sys_scheduler_task" IS '定时任务表';

-- ----------------------------
-- Records of sys_scheduler_task
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS "ytora"."sys_user";
CREATE TABLE "ytora"."sys_user"
(
    "id"          int8                                        NOT NULL,
    "create_by"   varchar(16) COLLATE "pg_catalog"."default",
    "create_time" timestamp(6),
    "update_by"   varchar(16) COLLATE "pg_catalog"."default",
    "update_time" timestamp(6),
    "depart_code" varchar(255) COLLATE "pg_catalog"."default",
    "remark"      varchar(255) COLLATE "pg_catalog"."default",
    "status"      varchar(255) COLLATE "pg_catalog"."default",
    "user_name"   varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
    "real_name"   varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
    "password"    varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
    "avatar"      varchar(255) COLLATE "pg_catalog"."default",
    "phone"       varchar(16) COLLATE "pg_catalog"."default",
    "email"       varchar(255) COLLATE "pg_catalog"."default",
    "birthday"    date,
    "id_card"     varchar(255) COLLATE "pg_catalog"."default"
)
;
COMMENT
ON COLUMN "ytora"."sys_user"."id" IS '主键ID';
COMMENT
ON COLUMN "ytora"."sys_user"."create_by" IS '创建人';
COMMENT
ON COLUMN "ytora"."sys_user"."create_time" IS '创建时间';
COMMENT
ON COLUMN "ytora"."sys_user"."update_by" IS '更新人';
COMMENT
ON COLUMN "ytora"."sys_user"."update_time" IS '更新时间';
COMMENT
ON COLUMN "ytora"."sys_user"."depart_code" IS '创建者所属部门';
COMMENT
ON COLUMN "ytora"."sys_user"."remark" IS '数据备注';
COMMENT
ON COLUMN "ytora"."sys_user"."status" IS '数据状态';
COMMENT
ON COLUMN "ytora"."sys_user"."user_name" IS '用户名';
COMMENT
ON COLUMN "ytora"."sys_user"."real_name" IS '真实姓名';
COMMENT
ON COLUMN "ytora"."sys_user"."password" IS '密码';
COMMENT
ON COLUMN "ytora"."sys_user"."avatar" IS '头像';
COMMENT
ON COLUMN "ytora"."sys_user"."phone" IS '手机号码';
COMMENT
ON COLUMN "ytora"."sys_user"."email" IS '邮箱';
COMMENT
ON COLUMN "ytora"."sys_user"."birthday" IS '生日';
COMMENT
ON COLUMN "ytora"."sys_user"."id_card" IS '身份证';
COMMENT
ON TABLE "ytora"."sys_user" IS '用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO "ytora"."sys_user"
VALUES (109, NULL, '2025-01-04 10:00:00', NULL, NULL, 'YT-HR-RECR', NULL, '1', 'sun.yao', '孙尧', '1', '/avatar.jpg',
        '13811110009', 'sun.yao@ytora.com', '2025-12-22', '114514');
INSERT INTO "ytora"."sys_user"
VALUES (110, NULL, '2025-01-05 09:00:00', NULL, NULL, 'YT-SA-EAST', NULL, '1', 'gao.yuan', '高原', '1', '/avatar.jpg',
        '13811110010', 'gao.yuan@ytora.com', '2025-12-22', '114514');
INSERT INTO "ytora"."sys_user"
VALUES (111, NULL, '2025-01-05 10:00:00', NULL, NULL, 'YT-IT-OPS', NULL, '1', 'he.yang', '何洋', '1', '/avatar.jpg',
        '13811110011', 'he.yang@ytora.com', '2025-12-22', '114514');
INSERT INTO "ytora"."sys_user"
VALUES (112, NULL, '2025-01-06 09:00:00', NULL, NULL, 'YT-001', NULL, '1', 'external.v', '顾文', '1', '/avatar.jpg',
        '13811110012', 'guest@ext.com', '2025-12-22', '114514');
INSERT INTO "ytora"."sys_user"
VALUES (101, NULL, '2025-01-01 10:00:00', NULL, NULL, 'YT-001', NULL, '1', 'admin', '杨桐', '1', '/avatar.jpg',
        '13811110001', 'admin@ytora.com', '2025-12-22', '114514');
INSERT INTO "ytora"."sys_user"
VALUES (102, NULL, '2025-01-02 10:00:00', NULL, NULL, 'YT-RD-01', NULL, '1', 'li.miao', '李苗', '1', '/avatar.jpg',
        '13811110002', 'li.miao@ytora.com', '2025-12-22', '114514');
INSERT INTO "ytora"."sys_user"
VALUES (103, NULL, '2025-01-02 11:00:00', NULL, NULL, 'YT-FI-01', NULL, '1', 'wang.qiang', '王强', '1', '/avatar.jpg',
        '13811110003', 'wang.qiang@ytora.com', '2025-12-22', '114514');
INSERT INTO "ytora"."sys_user"
VALUES (104, NULL, '2025-01-02 12:00:00', NULL, NULL, 'YT-HR-01', NULL, '1', 'chen.xi', '陈曦', '1', '/avatar.jpg',
        '13811110004', 'chen.xi@ytora.com', '2025-12-22', '114514');
INSERT INTO "ytora"."sys_user"
VALUES (105, NULL, '2025-01-03 09:00:00', NULL, NULL, 'YT-RD-SOFT', NULL, '1', 'zhang.wei', '张伟', '1', '/avatar.jpg',
        '13811110005', 'zhang.wei@ytora.com', '2025-12-22', '114514');
INSERT INTO "ytora"."sys_user"
VALUES (106, NULL, '2025-01-03 10:00:00', NULL, NULL, 'YT-RD-SOFT', NULL, '1', 'zhou.jie', '周杰', '1', '/avatar.jpg',
        '13811110006', 'zhou.jie@ytora.com', '2025-12-22', '114514');
INSERT INTO "ytora"."sys_user"
VALUES (107, NULL, '2025-01-03 11:00:00', NULL, NULL, 'YT-RD-TEST', NULL, '1', 'lin.yv', '林语', '1', '/avatar.jpg',
        '13811110007', 'lin.yv@ytora.com', '2025-12-22', '114514');
INSERT INTO "ytora"."sys_user"
VALUES (108, NULL, '2025-01-04 09:00:00', NULL, NULL, 'YT-FI-ACC', NULL, '1', 'huang.bo', '黄博', '1', '/avatar.jpg',
        '13811110008', 'huang.bo@ytora.com', '2025-12-22', '114514');

-- ----------------------------
-- Table structure for sys_user_depart
-- ----------------------------
DROP TABLE IF EXISTS "ytora"."sys_user_depart";
CREATE TABLE "ytora"."sys_user_depart"
(
    "id"          int8 NOT NULL,
    "create_by"   varchar(16) COLLATE "pg_catalog"."default",
    "create_time" timestamp(6),
    "update_by"   varchar(16) COLLATE "pg_catalog"."default",
    "update_time" timestamp(6),
    "depart_code" varchar(255) COLLATE "pg_catalog"."default",
    "remark"      varchar(255) COLLATE "pg_catalog"."default",
    "status"      varchar(255) COLLATE "pg_catalog"."default",
    "user_id"     int8 NOT NULL,
    "depart_id"   int8 NOT NULL
)
;
COMMENT
ON COLUMN "ytora"."sys_user_depart"."id" IS '主键ID';
COMMENT
ON COLUMN "ytora"."sys_user_depart"."create_by" IS '创建人';
COMMENT
ON COLUMN "ytora"."sys_user_depart"."create_time" IS '创建时间';
COMMENT
ON COLUMN "ytora"."sys_user_depart"."update_by" IS '更新人';
COMMENT
ON COLUMN "ytora"."sys_user_depart"."update_time" IS '更新时间';
COMMENT
ON COLUMN "ytora"."sys_user_depart"."depart_code" IS '创建者所属部门';
COMMENT
ON COLUMN "ytora"."sys_user_depart"."remark" IS '数据备注';
COMMENT
ON COLUMN "ytora"."sys_user_depart"."status" IS '数据状态';
COMMENT
ON COLUMN "ytora"."sys_user_depart"."user_id" IS '用户ID';
COMMENT
ON COLUMN "ytora"."sys_user_depart"."depart_id" IS '部门ID';
COMMENT
ON TABLE "ytora"."sys_user_depart" IS '用户-部门关系表';

-- ----------------------------
-- Records of sys_user_depart
-- ----------------------------
INSERT INTO "ytora"."sys_user_depart"
VALUES (1, NULL, '2025-01-01 10:00:00', NULL, NULL, NULL, NULL, '1', 101, 1);
INSERT INTO "ytora"."sys_user_depart"
VALUES (2, NULL, '2025-01-02 10:00:00', NULL, NULL, NULL, NULL, '1', 102, 2);
INSERT INTO "ytora"."sys_user_depart"
VALUES (3, NULL, '2025-01-02 11:00:00', NULL, NULL, NULL, NULL, '1', 103, 3);
INSERT INTO "ytora"."sys_user_depart"
VALUES (4, NULL, '2025-01-02 12:00:00', NULL, NULL, NULL, NULL, '1', 104, 4);
INSERT INTO "ytora"."sys_user_depart"
VALUES (5, NULL, '2025-01-03 09:00:00', NULL, NULL, NULL, NULL, '1', 105, 6);
INSERT INTO "ytora"."sys_user_depart"
VALUES (6, NULL, '2025-01-03 10:00:00', NULL, NULL, NULL, NULL, '1', 106, 6);
INSERT INTO "ytora"."sys_user_depart"
VALUES (7, NULL, '2025-01-03 11:00:00', NULL, NULL, NULL, NULL, '1', 107, 7);
INSERT INTO "ytora"."sys_user_depart"
VALUES (8, NULL, '2025-01-04 09:00:00', NULL, NULL, NULL, NULL, '1', 108, 8);
INSERT INTO "ytora"."sys_user_depart"
VALUES (9, NULL, '2025-01-04 10:00:00', NULL, NULL, NULL, NULL, '1', 109, 9);
INSERT INTO "ytora"."sys_user_depart"
VALUES (10, NULL, '2025-01-05 09:00:00', NULL, NULL, NULL, NULL, '1', 110, 10);
INSERT INTO "ytora"."sys_user_depart"
VALUES (11, NULL, '2025-01-05 10:00:00', NULL, NULL, NULL, NULL, '1', 111, 11);
INSERT INTO "ytora"."sys_user_depart"
VALUES (12, NULL, '2025-01-06 09:00:00', NULL, NULL, NULL, NULL, '1', 112, 1);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS "ytora"."sys_user_role";
CREATE TABLE "ytora"."sys_user_role"
(
    "id"          int8 NOT NULL,
    "create_by"   varchar(16) COLLATE "pg_catalog"."default",
    "create_time" timestamp(6),
    "update_by"   varchar(16) COLLATE "pg_catalog"."default",
    "update_time" timestamp(6),
    "depart_code" varchar(255) COLLATE "pg_catalog"."default",
    "remark"      varchar(255) COLLATE "pg_catalog"."default",
    "status"      varchar(255) COLLATE "pg_catalog"."default",
    "user_id"     int8 NOT NULL,
    "role_id"     int8 NOT NULL
)
;
COMMENT
ON COLUMN "ytora"."sys_user_role"."id" IS '主键ID';
COMMENT
ON COLUMN "ytora"."sys_user_role"."create_by" IS '创建人';
COMMENT
ON COLUMN "ytora"."sys_user_role"."create_time" IS '创建时间';
COMMENT
ON COLUMN "ytora"."sys_user_role"."update_by" IS '更新人';
COMMENT
ON COLUMN "ytora"."sys_user_role"."update_time" IS '更新时间';
COMMENT
ON COLUMN "ytora"."sys_user_role"."depart_code" IS '创建者所属部门';
COMMENT
ON COLUMN "ytora"."sys_user_role"."remark" IS '数据备注';
COMMENT
ON COLUMN "ytora"."sys_user_role"."status" IS '数据状态';
COMMENT
ON COLUMN "ytora"."sys_user_role"."user_id" IS '用户ID';
COMMENT
ON COLUMN "ytora"."sys_user_role"."role_id" IS '角色ID';
COMMENT
ON TABLE "ytora"."sys_user_role" IS '用户-角色关系表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO "ytora"."sys_user_role"
VALUES (1, NULL, '2025-01-01 10:00:00', NULL, NULL, NULL, NULL, '1', 101, 1);
INSERT INTO "ytora"."sys_user_role"
VALUES (2, NULL, '2025-01-02 10:00:00', NULL, NULL, NULL, NULL, '1', 102, 2);
INSERT INTO "ytora"."sys_user_role"
VALUES (3, NULL, '2025-01-02 11:00:00', NULL, NULL, NULL, NULL, '1', 103, 2);
INSERT INTO "ytora"."sys_user_role"
VALUES (4, NULL, '2025-01-02 12:00:00', NULL, NULL, NULL, NULL, '1', 104, 2);
INSERT INTO "ytora"."sys_user_role"
VALUES (5, NULL, '2025-01-03 09:00:00', NULL, NULL, NULL, NULL, '1', 105, 5);
INSERT INTO "ytora"."sys_user_role"
VALUES (6, NULL, '2025-01-03 10:00:00', NULL, NULL, NULL, NULL, '1', 106, 5);
INSERT INTO "ytora"."sys_user_role"
VALUES (7, NULL, '2025-01-03 11:00:00', NULL, NULL, NULL, NULL, '1', 107, 6);
INSERT INTO "ytora"."sys_user_role"
VALUES (8, NULL, '2025-01-04 09:00:00', NULL, NULL, NULL, NULL, '1', 108, 4);
INSERT INTO "ytora"."sys_user_role"
VALUES (9, NULL, '2025-01-04 10:00:00', NULL, NULL, NULL, NULL, '1', 109, 3);
INSERT INTO "ytora"."sys_user_role"
VALUES (10, NULL, '2025-01-05 09:00:00', NULL, NULL, NULL, NULL, '1', 110, 8);
INSERT INTO "ytora"."sys_user_role"
VALUES (11, NULL, '2025-01-05 10:00:00', NULL, NULL, NULL, NULL, '1', 111, 7);
INSERT INTO "ytora"."sys_user_role"
VALUES (12, NULL, '2025-01-06 09:00:00', NULL, NULL, NULL, NULL, '1', 112, 10);

-- ----------------------------
-- Primary Key structure for table sys_depart
-- ----------------------------
ALTER TABLE "ytora"."sys_depart"
    ADD CONSTRAINT "sys_depart_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table sys_log
-- ----------------------------
ALTER TABLE "ytora"."sys_log"
    ADD CONSTRAINT "sys_log_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table sys_permission
-- ----------------------------
ALTER TABLE "ytora"."sys_permission"
    ADD CONSTRAINT "sys_permission_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table sys_recycle_bin
-- ----------------------------
ALTER TABLE "ytora"."sys_recycle_bin"
    ADD CONSTRAINT "sys_recycle_bin_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table sys_role
-- ----------------------------
ALTER TABLE "ytora"."sys_role"
    ADD CONSTRAINT "sys_role_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table sys_role_permission
-- ----------------------------
ALTER TABLE "ytora"."sys_role_permission"
    ADD CONSTRAINT "sys_role_permission_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table sys_scheduler_task
-- ----------------------------
ALTER TABLE "ytora"."sys_scheduler_task"
    ADD CONSTRAINT "sys_scheduler_task_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table sys_user
-- ----------------------------
ALTER TABLE "ytora"."sys_user"
    ADD CONSTRAINT "sys_user_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table sys_user_depart
-- ----------------------------
ALTER TABLE "ytora"."sys_user_depart"
    ADD CONSTRAINT "sys_user_depart_pkey" PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table sys_user_role
-- ----------------------------
ALTER TABLE "ytora"."sys_user_role"
    ADD CONSTRAINT "sys_user_role_pkey" PRIMARY KEY ("id");
