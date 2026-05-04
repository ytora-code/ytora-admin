# Ytora Admin

🍉Ytora Admin 是一个面向中后台场景的后端脚手架平台，基于 `JDK 25`、`Spring Boot 3` 构建，目标是把后台系统里高频、重复、通用的能力前置沉淀下来，让业务开发可以更专注于领域本身，而不是反复搭基础设施。

🍌当前仓库正在持续迭代中，目前涵盖认证鉴权、RBAC、字典、文件、日志、调度、SSE、监控和代码生成等能力。

------



## 1. 技术栈

- `Spring Boot 3.5.x`
- `JDK 25`
- `Maven 3.9+`
- `Sqlux` 作为 ORM / SQL 访问框架
- `Knife4j + SpringDoc OpenAPI 3`
- `MapStruct + Lombok`
- `Caffeine` 本地缓存
- `SSE` 服务端推送
- `OSHI + Actuator` 应用 / JVM / OS 监控

------



## 2. 模块结构

```text
ytora-admin
├── ytora-base     平台基础设施模块
├── ytora-core     平台内置业务模块
├── ytora-system   启动模块 + 用户业务模块
└── db             数据库相关资源
```

### 2.1 ytora-base

基础能力沉淀层，主要提供：

- 认证鉴权过滤链、公共资源放行、跨域处理
- 全局异常处理、统一返回体 `R`
- Web MVC 扩展、Excel 参数解析、下载映射
- MapStruct 全局配置
- 本地缓存抽象
- 动态数据源、Sqlux 集成、SQL 日志与慢 SQL 监控
- SSE 注册与消息推送基础设施
- 文件存储抽象与本地存储实现
- 应用级指标采集、错误缓冲、请求统计
- 时间轮调度器基础实现

### 2.2 ytora-core

平台内置业务模块，当前已实现或已具备雏形的能力包括：

- `sys/login`：登录、登出、基于 token 获取当前用户信息
- `rbac`、`role`、`permission`、`depart`、`datarule`：用户、角色、权限、部门、数据范围
- `sys/dict`：数据字典与字典项管理
- `sys/file`、`sys/folder`：文件与目录管理、上传下载
- `sys/log`：系统日志
- `sys/schedule`：调度任务管理、启动、停止、立即执行
- `sys/sse`：SSE 连接、消息推送、事件启停
- `monitor/app`、`jvm`、`os`、`db`：应用、JVM、操作系统、数据库监控
- `online/db`：在线数据库能力与按表生成代码

同时，很多模块已经配套了分页查询、单条查询、增改、删除、Excel 导入导出等标准后台接口模式。

### 2.3 ytora-system

最终启动模块，也是二次开发的主要落点。启动类为 `xyz.ytora.YtoraApp`，这里负责组合 `ytora-base` 与 `ytora-core` 的能力，并承载用户自己的业务代码与配置。

------



## 3. 已有设计特点

已有的一套后台开发范式：

- 统一分层：`api / logic / repo / model`
- 统一参数与返回包装
- 统一异常处理与日志记录
- 统一权限接入方式
- 统一字典翻译、Excel 导入导出能力
- 统一监控视图与系统自观测能力
- 统一多数据源与 SQL 访问接入方式

------



## 4. 快速启动

### 4.1 环境要求

- `JDK 25`
- `Maven 3.9+`
- 推荐准备 `PostgreSQL`

父工程已经通过 `maven-enforcer-plugin` 对 Java 与 Maven 版本做了约束校验。

### 4.2 默认配置

开发环境配置位于 `ytora-system/src/main/resources/application-dev.yml`：

- 端口：`9876`
- 上下文路径：`/ytora`
- 默认激活环境：`dev`
- 已启用 `Knife4j`
- 默认启用认证模块
- 默认本地文件存储目录：`file-storage/`
- 默认主数据源为 PostgreSQL

### 4.3 启动命令

```bash
mvn clean package
mvn -pl ytora-system spring-boot:run
```

启动后可访问：

- 接口文档：`http://localhost:9876/ytora/doc.html`
- OpenAPI：`http://localhost:9876/ytora/v3/api-docs`

------



## 5. 二次开发建议

- 基础通用能力尽量沉淀到 `ytora-base`
- 平台级业务能力沉淀到 `ytora-core`
- 项目私有业务优先放在 `ytora-system`
- 实体扫描路径、动态数据源、文件存储、认证开关等都已支持通过配置扩展

------



## 6. 未来计划

- [x] RBAC
- [x] 数据范围和权限
- [x] 数据字典
- [x] 文件管理
- [x] 定时任务
- [x] 日志管理
- [x] 系统配置
- [ ] 回收站
- [x] 在线用户、接口管理
- [x] 本应用、JVM、服务器监控
- [ ] 数据库并发情况、SQL执行情况监控
- [x] SSE连接情况、Redis、缓存监控
- [ ] 动态API接口
- [ ] web数据库
- [ ] 低代码功能
