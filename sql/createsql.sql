/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/7/9 10:46:45                            */
/*==============================================================*/


DROP TABLE IF EXISTS BUDGET_AUDIT_LOG;

DROP TABLE IF EXISTS BUDGET_IMPORT_DETAIL_NEW;

DROP TABLE IF EXISTS BUDGET_IMPORT_DETAIL_OLD;

DROP TABLE IF EXISTS COMMONCODE;

DROP TABLE IF EXISTS EXPERTS;

DROP TABLE IF EXISTS FILE_IMPORT_LOG;

DROP TABLE IF EXISTS FUNDS_BUDGET;

DROP TABLE IF EXISTS FUNDS_IN;

DROP TABLE IF EXISTS FUNDS_OUT;

DROP TABLE IF EXISTS INDICATOR_BASE;

DROP TABLE IF EXISTS INDICATOR_DETAIL;

DROP TABLE IF EXISTS LEAVE_MESSAGE;

DROP TABLE IF EXISTS PROJECT;

DROP TABLE IF EXISTS REPORT_AUDIT_LOG;

DROP TABLE IF EXISTS REPORT_DEADLINE_SETTING;

DROP TABLE IF EXISTS SITELOG;

DROP TABLE IF EXISTS TUSER;

/*==============================================================*/
/* Table: BUDGET_AUDIT_LOG                                      */
/*==============================================================*/
CREATE TABLE BUDGET_AUDIT_LOG
(
   ID                   VARCHAR(50) NOT NULL COMMENT 'Id',
   ADJUST_ID            VARCHAR(50) NOT NULL COMMENT '预算调整ID',
   STATUS               VARCHAR(5) NOT NULL COMMENT '状态',
   REPORT_TIME          DATETIME DEFAULT NULL COMMENT '时间',
   FINANCE_AUDIT_TIME   DATETIME DEFAULT NULL COMMENT '财政审核时间',
   SCHOOL_AUDIT_TIME    DATETIME DEFAULT NULL COMMENT '学校审核时间（项目负责人）',
   CONUTRY_AUDIT_TIME   DATETIME DEFAULT NULL COMMENT '国家审核时间（教育部）',
   FINANCE_AUDIT_STATE  VARCHAR(5) NOT NULL COMMENT '财政审核状态',
   SCHOOL_AUDIT_STATE   VARCHAR(5) NOT NULL COMMENT '学校审核状态',
   CONUTRY_AUDIT_STATE  VARCHAR(5) NOT NULL COMMENT '国家审核状态',
   AUDIT_OPINION        VARCHAR(500) DEFAULT NULL COMMENT '审核意见',
   PRIMARY KEY (ID)
)
ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='预算调整审核记录';

/*==============================================================*/
/* Table: BUDGET_IMPORT_DETAIL_NEW                              */
/*==============================================================*/
CREATE TABLE BUDGET_IMPORT_DETAIL_NEW
(
   ID                   VARCHAR(50) NOT NULL COMMENT 'Id',
   SEQUENCE_NO          VARCHAR(10) DEFAULT NULL COMMENT '一级序号',
   USE_FOR              VARCHAR(50) DEFAULT NULL COMMENT '资金用途',
   CONSULT              VARCHAR(50) DEFAULT NULL COMMENT '咨询费-支出',
   PRINT                VARCHAR(50) DEFAULT NULL COMMENT '印刷费-支出',
   TRAVEL               VARCHAR(50) DEFAULT NULL COMMENT '差旅费-支出',
   METTING              VARCHAR(50) DEFAULT NULL COMMENT '会议费-支出',
   TRAINNING            VARCHAR(50) DEFAULT NULL COMMENT '培训费-支出',
   SPECIAL_MATERIAL     VARCHAR(50) DEFAULT NULL COMMENT '专用材料费',
   DELEGATION           VARCHAR(50) DEFAULT NULL COMMENT '委托业务费-支出',
   OTHER_EXPENSE        VARCHAR(50) DEFAULT NULL COMMENT '其他-支出',
   SPECIAL_DEVICE       VARCHAR(50) DEFAULT NULL COMMENT '专用设备购置费-支出',
   INFOMATION_TECHNOLOGY VARCHAR(50) DEFAULT NULL COMMENT '信息网络及软件购置更新',
   TOTAL_MONEY          VARCHAR(50) DEFAULT NULL COMMENT '项目预算总额',
   COUNTRY_TOTAL        VARCHAR(50) DEFAULT NULL COMMENT '申请部本专项合计',
   COUNTRY_PERCENT      VARCHAR(50) DEFAULT NULL COMMENT '申请部本专项百分比',
   PROJECT_TOTAL        VARCHAR(50) DEFAULT NULL COMMENT '项目筹措资金合计',
   PROJECT_PERCENT      VARCHAR(50) DEFAULT NULL COMMENT '项目筹措资金占项目预算总额比(%)',
   LOCAL                VARCHAR(50) DEFAULT NULL COMMENT '举办方或地方财政投入资金',
   ENTERPRISE           VARCHAR(50) DEFAULT NULL COMMENT '行业企业支持资金',
   UNIVERSITY           VARCHAR(50) DEFAULT NULL COMMENT '相关院校自筹资金',
   BUDGET_YEAR          VARCHAR(10) NOT NULL COMMENT '预算年份',
   FILE_IMPORT_ID       VARCHAR(50) DEFAULT NULL COMMENT '导入ID',
   ORIGINALID_ID        VARCHAR(50) DEFAULT NULL COMMENT '原来的数据ID',
   PRIMARY KEY (ID)
)
ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='项目预算明细2016-原BudgetImportDetail2016';

/*==============================================================*/
/* Table: BUDGET_IMPORT_DETAIL_OLD                              */
/*==============================================================*/
CREATE TABLE BUDGET_IMPORT_DETAIL_OLD
(
   ID                   VARCHAR(50) NOT NULL COMMENT 'Id',
   SEQUENCE_NO          VARCHAR(10) DEFAULT NULL COMMENT '序号，一条预算记录的大分类编号。',
   USE_FOR              VARCHAR(50) DEFAULT NULL COMMENT '预算用途',
   TOTAL_MONEY          VARCHAR(50) DEFAULT NULL COMMENT '金额',
   COUNTRY_TOTAL        VARCHAR(50) DEFAULT NULL COMMENT '中央专项合计',
   COUNTRY_PERCENT      VARCHAR(50) DEFAULT NULL COMMENT '中央专项占百分比',
   COUNTRY_FIRST_YEAR   VARCHAR(50) DEFAULT NULL COMMENT '2015年度',
   COUNTRY_SECOND_YEAR  VARCHAR(50) DEFAULT NULL COMMENT '2016年度',
   PROJECT_TOTAL        VARCHAR(50) DEFAULT NULL COMMENT '项目筹措资金合计',
   PROJECT_PERCENT      VARCHAR(50) DEFAULT NULL COMMENT '项目筹措资金百分比',
   LOCAL_FIRST_YEAR     VARCHAR(50) DEFAULT NULL COMMENT '举办方或地方财政投入资金 2015年度预算',
   LOCAL_SECOND_YEAR    VARCHAR(50) DEFAULT NULL COMMENT '举办方或地方财政投入资金2016预算',
   LOCAL_THREE_YEAR     VARCHAR(50) DEFAULT NULL COMMENT '举办方或地方财政投入资金 2017预算',
   ENTERPRISE_FIRST_YEAR VARCHAR(50) DEFAULT NULL COMMENT '行业企业支持资金2015预算',
   ENTERPRISE_SECOND_YEAR VARCHAR(50) DEFAULT NULL COMMENT '行业企业支持资金2016预算',
   ENTERPRISE_THREE_YEAR VARCHAR(50) DEFAULT NULL COMMENT '行业企业支持资金2017预算',
   UNIVERSITY_FIRST_YEAR VARCHAR(50) DEFAULT NULL COMMENT '相关院校自筹资金 2015预算',
   UNIVERSITY_SECOND_YEAR VARCHAR(50) DEFAULT NULL COMMENT '相关院校自筹资金2016预算',
   UNIVERSITY_THREE_YEAR VARCHAR(50) DEFAULT NULL COMMENT '相关院校自筹资金2017预算',
   FILE_IMPORT_ID       VARCHAR(50) DEFAULT NULL COMMENT '导入ID',
  ORIGINALID_ID        VARCHAR(50) DEFAULT NULL COMMENT '原来的数据ID',
   PRIMARY KEY (ID)
)
ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='2015预算导入-原BudgetImportDetail';

/*==============================================================*/
/* Table: COMMONCODE                                            */
/*==============================================================*/
CREATE TABLE COMMONCODE
(
   NAME                 VARCHAR(50) NOT NULL COMMENT '名称',
   REMARK               VARCHAR(50) DEFAULT NULL COMMENT '备注',
   CODE                 VARCHAR(10) NOT NULL COMMENT '代码',
   CLASS_NAME           VARCHAR(10) DEFAULT NULL COMMENT '代码分类对象',
   ID                   VARCHAR(50) NOT NULL,
   PRIMARY KEY (ID)
)
ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='通用代码类';

/*==============================================================*/
/* Table: EXPERTS                                               */
/*==============================================================*/
CREATE TABLE EXPERTS
(
   ID                   VARCHAR(50) NOT NULL COMMENT 'Id',
   CODE                 VARCHAR(20) DEFAULT NULL COMMENT '代码',
   NAME                 VARCHAR(20) DEFAULT NULL COMMENT '名字',
   GENDER               VARCHAR(10) DEFAULT NULL COMMENT '性别',
   CID                  VARCHAR(50) DEFAULT NULL COMMENT '身份证号码',
   TELEPHONE_NUMBER     VARCHAR(50) DEFAULT NULL COMMENT '电话号码',
   PROFESSIONAL_TITLE   VARCHAR(50) DEFAULT NULL COMMENT '职称',
   EDU_LEVEL            VARCHAR(20) DEFAULT NULL COMMENT '学历',
   AVOID_UNIT           VARCHAR(200) DEFAULT NULL COMMENT '回避单位',
   RESEARCH_FIELD       VARCHAR(200) DEFAULT NULL COMMENT '研究领域',
   PRIMARY KEY (ID)
)
ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='专家-原Experts';

/*==============================================================*/
/* Table: FILE_IMPORT_LOG                                       */
/*==============================================================*/
CREATE TABLE FILE_IMPORT_LOG
(
   ID                   VARCHAR(50) NOT NULL COMMENT 'Id',
   IMPORT_DATE          DATETIME NOT NULL COMMENT '导入日期',
   FILE_NAME            VARCHAR(500) NOT NULL COMMENT '文件名',
   IMPORT_USER_ID       VARCHAR(50) DEFAULT NULL COMMENT '导入用户',
   PROJECT_ID           VARCHAR(50) DEFAULT NULL COMMENT '项目ID',
   IMPORT_TYPE          VARCHAR(10) DEFAULT NULL COMMENT '导入类型',
   REQUEST_FILE         VARCHAR(500) DEFAULT NULL COMMENT '调整请示附件',
   DES_FILE             VARCHAR(500) DEFAULT NULL COMMENT '调整说明附件',
   PRIMARY KEY (ID)
)
ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='文件导入记录-原BudgetImport1';

/*==============================================================*/
/* Table: FUNDS_BUDGET                                          */
/*==============================================================*/
CREATE TABLE FUNDS_BUDGET
(
   ID                   VARCHAR(50) NOT NULL DEFAULT '',
   PROJECTID            VARCHAR(50) NOT NULL COMMENT '用户',
   MATERIALMAKE         DECIMAL(18,2) NOT NULL COMMENT '素材制作',
   COMPANYCASE          DECIMAL(18,2) NOT NULL COMMENT '企业案例收集',
   COURSEDEVELOPMENT    DECIMAL(18,2) NOT NULL COMMENT '课程开发',
   TOOLSOFTWARE         DECIMAL(18,2) NOT NULL COMMENT '特殊工具软件制作',
   APPLICATIONPROMOTE   DECIMAL(18,2) NOT NULL COMMENT '应用推广',
   RESEARCHPROVE        DECIMAL(18,2) NOT NULL COMMENT '调研论证',
   EXPERTCONSULT        DECIMAL(18,2) NOT NULL COMMENT '专家咨询',
   OTHERFEE             DECIMAL(18,2) NOT NULL COMMENT '其他',
   BUDGETYEAR           VARCHAR(10) DEFAULT NULL COMMENT '预算年份',
   SUBMITTIME           DATETIME DEFAULT NULL COMMENT '提交时间',
   ISDELETE             VARCHAR(5) NOT NULL COMMENT '是否删除',
   NOTE                 VARCHAR(50) DEFAULT NULL COMMENT '备注',
   PID                  VARCHAR(5) DEFAULT NULL COMMENT '资金来源',
   PRIMARY KEY (ID)
)
ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='预算（导入时计算入库）-原FundsBudget';

/*==============================================================*/
/* Table: FUNDS_IN                                              */
/*==============================================================*/
CREATE TABLE FUNDS_IN
(
   ID                   VARCHAR(50) NOT NULL COMMENT 'ID',
   AMOUNT_MONEY         DECIMAL(18,2) DEFAULT NULL COMMENT '合计',
   PROJECT_YEAR         VARCHAR(10) NOT NULL COMMENT '项目年份',
   QUARTER_NUM          VARCHAR(5) NOT NULL COMMENT '季节编号',
   SUBMIT_TIME          DATETIME DEFAULT NULL COMMENT '提交时间',
   IS_DELETE            VARCHAR(5) NOT NULL COMMENT '是否删除',
   NOTE                 VARCHAR(50) DEFAULT NULL COMMENT '备注',
   USER_ID              VARCHAR(50) DEFAULT NULL COMMENT 'UserId',
   PID                  VARCHAR(5) DEFAULT NULL COMMENT '资金来源',
   PRIMARY KEY (ID)
)
ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='收入预算执行季报-原FundsIn';

/*==============================================================*/
/* Table: FUNDS_OUT                                             */
/*==============================================================*/
CREATE TABLE FUNDS_OUT
(
   ID                   VARCHAR(50) NOT NULL COMMENT 'ID',
   MATERIAL_MAKE        DECIMAL(18,2) NOT NULL COMMENT '素材制作',
   COMPANY_CASE         DECIMAL(18,2) NOT NULL COMMENT '企业案例收集',
   COURSE_DEVELOPMENT   DECIMAL(18,2) NOT NULL COMMENT '课程开发',
   SPECIAL_TOOL         DECIMAL(18,2) NOT NULL COMMENT '特殊工具软件制作',
   APPLICATION_PROMETE  DECIMAL(18,2) NOT NULL COMMENT '应用推广',
   RESEARCH_PROVE       DECIMAL(18,2) NOT NULL COMMENT '调研论证',
   EXPERT_CONSULT       DECIMAL(18,2) NOT NULL COMMENT '专家咨询',
   OTHER_FEE            DECIMAL(18,2) NOT NULL COMMENT '其他',
   PROJECT_YEAR         VARCHAR(10) NOT NULL COMMENT '项目年份',
   QUARTER_NUM          VARCHAR(5) NOT NULL COMMENT '季节编号',
   SUBMIT_TIME          DATETIME DEFAULT NULL COMMENT '提交时间',
   IS_DELETE            VARCHAR(5) NOT NULL COMMENT '是否删除',
   NOTE                 VARCHAR(50) DEFAULT NULL COMMENT '备注',
   USER_ID              VARCHAR(50) DEFAULT NULL COMMENT 'UserId',
   PID                  VARCHAR(5) DEFAULT NULL COMMENT '资金来源',
   PRIMARY KEY (ID)
)
ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='支出预算执行季报-原FundsOut';

/*==============================================================*/
/* Table: INDICATOR_BASE                                        */
/*==============================================================*/
CREATE TABLE INDICATOR_BASE
(
   ID                   VARCHAR(50) NOT NULL COMMENT 'Id',
   FILE_IMPORT_ID       VARCHAR(50) NOT NULL COMMENT '导入编号',
   TARGET_IMPLEMENT     TEXT COMMENT '实施期目标',
   TARGET_FIRST_YEAR    TEXT COMMENT '第一年度目标',
   TARGET_SECOND_YEAR   TEXT COMMENT '第二年度目标',
   PRIMARY KEY (ID)
)
ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='项目绩效基本目标-原IndicatorImportDetail1';

/*==============================================================*/
/* Table: INDICATOR_DETAIL                                      */
/*==============================================================*/
CREATE TABLE INDICATOR_DETAIL
(
   ID                   VARCHAR(50) NOT NULL COMMENT 'Id',
   PROJECT_ID           VARCHAR(50) DEFAULT NULL,
   FILE_IMPORT_ID       VARCHAR(50) NOT NULL COMMENT '导入编号',
   INDICATOR_THREE_LEVEL VARCHAR(500) DEFAULT NULL COMMENT '三级指标名称',
   PLAN_TOTAL           VARCHAR(500) DEFAULT NULL COMMENT '指标总体值',
   PLAN_FIRST_YEAR      VARCHAR(500) DEFAULT NULL COMMENT '第一年度指标',
   PLAN_SECOND_YEAR     VARCHAR(500) DEFAULT NULL COMMENT '第二年度指标',
   INDICATOR_ONE_LEVEL  VARCHAR(500) DEFAULT NULL COMMENT '一级指标',
   INDICATOR_TOW_LEVEL  VARCHAR(500) DEFAULT NULL COMMENT '二级指标',
   PRIMARY KEY (ID)
)
ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='项目绩效指标-原IndicatorImportDetail2';

/*==============================================================*/
/* Table: LEAVE_MESSAGE                                         */
/*==============================================================*/
CREATE TABLE LEAVE_MESSAGE
(
   ID                   VARCHAR(50) NOT NULL COMMENT 'ID',
   MES_TYPE             VARCHAR(50) DEFAULT NULL COMMENT '留言类型',
   CONTENTS             VARCHAR(1000) DEFAULT NULL COMMENT '留言内容',
   PROJECT_ID           VARCHAR(50) DEFAULT NULL COMMENT '项目ID',
   SUBMIT_USER_ID       VARCHAR(50) DEFAULT NULL COMMENT '留言用户ID',
   SUBMIT_DATE          DATETIME DEFAULT NULL COMMENT '留言日期',
   REPLY_USER_ID        VARCHAR(50) DEFAULT NULL COMMENT '回复用户ID',
   REPLY_DATE           DATETIME DEFAULT NULL COMMENT '回复日期',
   REPLY_CONTENTS       VARCHAR(500) DEFAULT NULL COMMENT '回复内容',
   REF_FILE             VARCHAR(50) DEFAULT NULL COMMENT '关联文件',
   PRIMARY KEY (ID)
)
ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='留言-原TestMsg';

/*==============================================================*/
/* Table: PROJECT                                               */
/*==============================================================*/
CREATE TABLE PROJECT
(
   ID                   VARCHAR(50) NOT NULL COMMENT 'Id',
   SCHOOL_NAME          VARCHAR(200) NOT NULL COMMENT '学校名称',
   SCHOOL_HEAD          VARCHAR(50) NOT NULL COMMENT '校领导',
   FINACE_HEADER        VARCHAR(50) NOT NULL COMMENT '财务',
   FINACE_HEADER_TEL    VARCHAR(50) NOT NULL COMMENT '财务电话',
   FINACE_HEADER_QQ     VARCHAR(50) DEFAULT NULL COMMENT '财务QQ',
   PROJECT_HEADER       VARCHAR(50) NOT NULL COMMENT '项目负责人',
   PROJECT_HEADER_TEL   VARCHAR(50) NOT NULL COMMENT '项目负责人电话',
   REPORTER             VARCHAR(50) NOT NULL COMMENT '填报人',
   REPORTER_TEL         VARCHAR(50) NOT NULL COMMENT '填报人电话',
   REPORTER_QQ          VARCHAR(50) DEFAULT NULL COMMENT '填报人qq',
   NOTE                 VARCHAR(100) DEFAULT NULL COMMENT '备注',
   SUBMIT_TIME          DATETIME DEFAULT NULL COMMENT '提交时间',
   IS_DELETE            VARCHAR(5) NOT NULL COMMENT '是否删除',
   PROJECT_NO           VARCHAR(100) NOT NULL COMMENT '项目编号',
   MAJOR_NAME           VARCHAR(100) NOT NULL COMMENT '专业名称',
   CREATE_YEAR          VARCHAR(10) NOT NULL COMMENT '立项年度',
   UNION_SCHOOL         TEXT COMMENT '联合主持单位',
   PARTNER_SCHOOL       TEXT COMMENT '参与建设单位',
   PROVENCE_ID          VARCHAR(50) DEFAULT NULL,
   IMPORT_USER_ID       VARCHAR(50) DEFAULT NULL COMMENT '导入用户ID',
   PRIMARY KEY (ID)
)
ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='项目-原Project';

/*==============================================================*/
/* Table: REPORT_AUDIT_LOG                                      */
/*==============================================================*/
CREATE TABLE REPORT_AUDIT_LOG
(
   ID                   VARCHAR(50) NOT NULL COMMENT 'Id',
   YEAR                 VARCHAR(10) NOT NULL COMMENT '年度',
   QUARTER              VARCHAR(5) NOT NULL COMMENT '季度',
   STATUS               VARCHAR(5) NOT NULL COMMENT '状态',
   REPORT_TIME          DATETIME DEFAULT NULL COMMENT '时间',
   FINANCE_AUDIT_TIME   DATETIME DEFAULT NULL COMMENT '财政审核时间',
   SCHOOL_AUDIT_TIME    DATETIME DEFAULT NULL COMMENT '学校审核时间（项目负责人）',
   CONUTRY_AUDIT_TIME   DATETIME DEFAULT NULL COMMENT '国家审核时间（教育部）',
   FINANCE_AUDIT_STATE  VARCHAR(5) NOT NULL COMMENT '财政审核状态',
   SCHOOL_AUDIT_STATE   VARCHAR(5) NOT NULL COMMENT '学校审核状态',
   CONUTRY_AUDIT_STATE  VARCHAR(5) NOT NULL COMMENT '国家审核状态',
   AUDIT_OPINION        VARCHAR(500) DEFAULT NULL COMMENT '审核意见',
   PROJECT_ID           VARCHAR(50) DEFAULT NULL COMMENT '项目ID',
   PRIMARY KEY (ID)
)
ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='季报审核-原Report';

/*==============================================================*/
/* Table: REPORT_DEADLINE_SETTING                               */
/*==============================================================*/
CREATE TABLE REPORT_DEADLINE_SETTING
(
   ID                   VARCHAR(50) NOT NULL COMMENT 'Id',
   VALUE                VARCHAR(50) NOT NULL COMMENT '截止日期',
   REMARK               VARCHAR(50) DEFAULT NULL COMMENT '备注',
   PRIMARY KEY (ID)
)
ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='季报提交截止日期设置-原MySetting';

/*==============================================================*/
/* Table: SITELOG                                               */
/*==============================================================*/
CREATE TABLE SITELOG
(
   ID                   VARCHAR(50) NOT NULL COMMENT 'Id',
   URL                  VARCHAR(500) NOT NULL COMMENT 'Url',
   ACTION               VARCHAR(50) DEFAULT NULL COMMENT '操作类别',
   CONTROLLER           VARCHAR(50) DEFAULT NULL COMMENT '关联控制器类',
   IP                   VARCHAR(50) DEFAULT NULL COMMENT 'IP地址',
   OPERATE_TIME         DATETIME NOT NULL COMMENT '操作时间',
   USER_ID              VARCHAR(50) DEFAULT NULL COMMENT '用户ID',
   PRIMARY KEY (ID)
)
ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='网络访问日志-原SiteLog';

/*==============================================================*/
/* Table: TUSER                                                 */
/*==============================================================*/
CREATE TABLE TUSER
(
   ID                   VARCHAR(50) NOT NULL COMMENT 'UserID',
   USER_NO              VARCHAR(100) NOT NULL COMMENT '用户编号，登录凭证',
   MAJOR_NAME           VARCHAR(200) NOT NULL COMMENT '专业名称',
   USER_PASSWORD        VARCHAR(200) NOT NULL COMMENT '密码',
   USER_ROLE            VARCHAR(5) NOT NULL COMMENT '用户角色',
   NOTE                 VARCHAR(100) DEFAULT NULL COMMENT '备注',
   IS_DELETE            VARCHAR(5) DEFAULT NULL COMMENT '是否删除',
   TELEPHONE_NUM        VARCHAR(20) DEFAULT NULL COMMENT '电话',
   USER_NAME            VARCHAR(20) DEFAULT NULL COMMENT '用户名字',
   PRIMARY KEY (ID)
)
ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='用户';

