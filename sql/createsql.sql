/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/7/9 10:12:20                            */
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
   ADJUST_ID            VARCHAR(50) NOT NULL COMMENT 'Ԥ�����ID',
   STATUS               VARCHAR(5) NOT NULL COMMENT '״̬',
   REPORT_TIME          DATETIME DEFAULT NULL COMMENT 'ʱ��',
   FINANCE_AUDIT_TIME   DATETIME DEFAULT NULL COMMENT '�������ʱ��',
   SCHOOL_AUDIT_TIME    DATETIME DEFAULT NULL COMMENT 'ѧУ���ʱ�䣨��Ŀ�����ˣ�',
   CONUTRY_AUDIT_TIME   DATETIME DEFAULT NULL COMMENT '�������ʱ�䣨��������',
   FINANCE_AUDIT_STATE  VARCHAR(5) NOT NULL COMMENT '�������״̬',
   SCHOOL_AUDIT_STATE   VARCHAR(5) NOT NULL COMMENT 'ѧУ���״̬',
   CONUTRY_AUDIT_STATE  VARCHAR(5) NOT NULL COMMENT '�������״̬',
   AUDIT_OPINION        VARCHAR(500) DEFAULT NULL COMMENT '������',
   PRIMARY KEY (ID)
)
ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='Ԥ�������˼�¼';

/*==============================================================*/
/* Table: BUDGET_IMPORT_DETAIL_NEW                              */
/*==============================================================*/
CREATE TABLE BUDGET_IMPORT_DETAIL_NEW
(
   ID                   VARCHAR(50) NOT NULL COMMENT 'Id',
   SEQUENCE_NO          VARCHAR(10) DEFAULT NULL COMMENT 'һ�����',
   USE_FOR              VARCHAR(50) DEFAULT NULL COMMENT '�ʽ���;',
   CONSULT              VARCHAR(50) DEFAULT NULL COMMENT '��ѯ��-֧��',
   PRINT                VARCHAR(50) DEFAULT NULL COMMENT 'ӡˢ��-֧��',
   TRAVEL               VARCHAR(50) DEFAULT NULL COMMENT '���÷�-֧��',
   METTING              VARCHAR(50) DEFAULT NULL COMMENT '�����-֧��',
   TRAINNING            VARCHAR(50) DEFAULT NULL COMMENT '��ѵ��-֧��',
   SPECIAL_MATERIAL     VARCHAR(50) DEFAULT NULL COMMENT 'ר�ò��Ϸ�',
   DELEGATION           VARCHAR(50) DEFAULT NULL COMMENT 'ί��ҵ���-֧��',
   OTHER_EXPENSE        VARCHAR(50) DEFAULT NULL COMMENT '����-֧��',
   SPECIAL_DEVICE       VARCHAR(50) DEFAULT NULL COMMENT 'ר���豸���÷�-֧��',
   INFOMATION_TECHNOLOGY VARCHAR(50) DEFAULT NULL COMMENT '��Ϣ���缰������ø���',
   TOTAL_MONEY          VARCHAR(50) DEFAULT NULL COMMENT '��ĿԤ���ܶ�',
   COUNTRY_TOTAL        VARCHAR(50) DEFAULT NULL COMMENT '���벿��ר��ϼ�',
   COUNTRY_PERCENT      VARCHAR(50) DEFAULT NULL COMMENT '���벿��ר��ٷֱ�',
   PROJECT_TOTAL        VARCHAR(50) DEFAULT NULL COMMENT '��Ŀ����ʽ�ϼ�',
   PROJECT_PERCENT      VARCHAR(50) DEFAULT NULL COMMENT '��Ŀ����ʽ�ռ��ĿԤ���ܶ��(%)',
   LOCAL                VARCHAR(50) DEFAULT NULL COMMENT '�ٰ췽��ط�����Ͷ���ʽ�',
   ENTERPRISE           VARCHAR(50) DEFAULT NULL COMMENT '��ҵ��ҵ֧���ʽ�',
   UNIVERSITY           VARCHAR(50) DEFAULT NULL COMMENT '���ԺУ�Գ��ʽ�',
   BUDGET_YEAR          VARCHAR(10) NOT NULL COMMENT 'Ԥ�����',
   FILE_IMPORT_ID       VARCHAR(50) DEFAULT NULL COMMENT '����ID',
   ORIGINALID_ID        VARCHAR(50) DEFAULT NULL COMMENT 'ԭ��������ID',
   PRIMARY KEY (ID)
)
ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='��ĿԤ����ϸ2016-ԭBudgetImportDetail2016';

/*==============================================================*/
/* Table: BUDGET_IMPORT_DETAIL_OLD                              */
/*==============================================================*/
CREATE TABLE BUDGET_IMPORT_DETAIL_OLD
(
   ID                   VARCHAR(50) NOT NULL COMMENT 'Id',
   SEQUENCE_NO          VARCHAR(10) DEFAULT NULL COMMENT '��ţ�һ��Ԥ���¼�Ĵ�����š�',
   USE_FOR              VARCHAR(50) DEFAULT NULL COMMENT 'Ԥ����;',
   TOTAL_MONEY          VARCHAR(50) DEFAULT NULL COMMENT '���',
   COUNTRY_TOTAL        VARCHAR(50) DEFAULT NULL COMMENT '����ר��ϼ�',
   COUNTRY_PERCENT      VARCHAR(50) DEFAULT NULL COMMENT '����ר��ռ�ٷֱ�',
   COUNTRY_FIRST_YEAR   VARCHAR(50) DEFAULT NULL COMMENT '2015���',
   COUNTRY_SECOND_YEAR  VARCHAR(50) DEFAULT NULL COMMENT '2016���',
   PROJECT_TOTAL        VARCHAR(50) DEFAULT NULL COMMENT '��Ŀ����ʽ�ϼ�',
   PROJECT_PERCENT      VARCHAR(50) DEFAULT NULL COMMENT '��Ŀ����ʽ�ٷֱ�',
   LOCAL_FIRST_YEAR     VARCHAR(50) DEFAULT NULL COMMENT '�ٰ췽��ط�����Ͷ���ʽ� 2015���Ԥ��',
   LOCAL_SECOND_YEAR    VARCHAR(50) DEFAULT NULL COMMENT '�ٰ췽��ط�����Ͷ���ʽ�2016Ԥ��',
   LOCAL_THREE_YEAR     VARCHAR(50) DEFAULT NULL COMMENT '�ٰ췽��ط�����Ͷ���ʽ� 2017Ԥ��',
   ENTERPRISE_FIRST_YEAR VARCHAR(50) DEFAULT NULL COMMENT '��ҵ��ҵ֧���ʽ�2015Ԥ��',
   ENTERPRISE_SECOND_YEAR VARCHAR(50) DEFAULT NULL COMMENT '��ҵ��ҵ֧���ʽ�2016Ԥ��',
   ENTERPRISE_THREE_YEAR VARCHAR(50) DEFAULT NULL COMMENT '��ҵ��ҵ֧���ʽ�2017Ԥ��',
   UNIVERSITY_FIRST_YEAR VARCHAR(50) DEFAULT NULL COMMENT '���ԺУ�Գ��ʽ� 2015Ԥ��',
   UNIVERSITY_SECOND_YEAR VARCHAR(50) DEFAULT NULL COMMENT '���ԺУ�Գ��ʽ�2016Ԥ��',
   UNIVERSITY_THREE_YEAR VARCHAR(50) DEFAULT NULL COMMENT '���ԺУ�Գ��ʽ�2017Ԥ��',
   FILE_IMPORT_ID       VARCHAR(50) DEFAULT NULL COMMENT '����ID',
   PRIMARY KEY (ID)
)
ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='2015Ԥ�㵼��-ԭBudgetImportDetail';

/*==============================================================*/
/* Table: COMMONCODE                                            */
/*==============================================================*/
CREATE TABLE COMMONCODE
(
   NAME                 VARCHAR(50) NOT NULL COMMENT '����',
   REMARK               VARCHAR(50) DEFAULT NULL COMMENT '��ע',
   CODE                 VARCHAR(10) NOT NULL COMMENT '����',
   CLASS_NAME           VARCHAR(10) DEFAULT NULL COMMENT '����������',
   ID                   VARCHAR(50) NOT NULL,
   PRIMARY KEY (ID)
)
ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='ͨ�ô�����';

/*==============================================================*/
/* Table: EXPERTS                                               */
/*==============================================================*/
CREATE TABLE EXPERTS
(
   ID                   VARCHAR(50) NOT NULL COMMENT 'Id',
   CODE                 VARCHAR(20) DEFAULT NULL COMMENT '����',
   NAME                 VARCHAR(20) DEFAULT NULL COMMENT '����',
   GENDER               VARCHAR(10) DEFAULT NULL COMMENT '�Ա�',
   CID                  VARCHAR(50) DEFAULT NULL COMMENT '���֤����',
   TELEPHONE_NUMBER     VARCHAR(50) DEFAULT NULL COMMENT '�绰����',
   PROFESSIONAL_TITLE   VARCHAR(50) DEFAULT NULL COMMENT 'ְ��',
   EDU_LEVEL            VARCHAR(20) DEFAULT NULL COMMENT 'ѧ��',
   AVOID_UNIT           VARCHAR(200) DEFAULT NULL COMMENT '�رܵ�λ',
   RESEARCH_FIELD       VARCHAR(200) DEFAULT NULL COMMENT '�о�����',
   PRIMARY KEY (ID)
)
ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='ר��-ԭExperts';

/*==============================================================*/
/* Table: FILE_IMPORT_LOG                                       */
/*==============================================================*/
CREATE TABLE FILE_IMPORT_LOG
(
   ID                   VARCHAR(50) NOT NULL COMMENT 'Id',
   IMPORT_DATE          DATETIME NOT NULL COMMENT '��������',
   FILE_NAME            VARCHAR(500) NOT NULL COMMENT '�ļ���',
   IMPORT_USER_ID       VARCHAR(50) DEFAULT NULL COMMENT '�����û�',
   PROJECT_ID           VARCHAR(50) DEFAULT NULL COMMENT '��ĿID',
   IMPORT_TYPE          VARCHAR(10) DEFAULT NULL COMMENT '��������',
   REQUEST_FILE         VARCHAR(500) DEFAULT NULL COMMENT '������ʾ����',
   DES_FILE             VARCHAR(500) DEFAULT NULL COMMENT '����˵������',
   PRIMARY KEY (ID)
)
ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='�ļ������¼-ԭBudgetImport1';

/*==============================================================*/
/* Table: FUNDS_BUDGET                                          */
/*==============================================================*/
CREATE TABLE FUNDS_BUDGET
(
   ID                   VARCHAR(50) NOT NULL DEFAULT '',
   PROJECTID            VARCHAR(50) NOT NULL COMMENT '�û�',
   MATERIALMAKE         DECIMAL(18,2) NOT NULL COMMENT '�ز�����',
   COMPANYCASE          DECIMAL(18,2) NOT NULL COMMENT '��ҵ�����ռ�',
   COURSEDEVELOPMENT    DECIMAL(18,2) NOT NULL COMMENT '�γ̿���',
   TOOLSOFTWARE         DECIMAL(18,2) NOT NULL COMMENT '���⹤���������',
   APPLICATIONPROMOTE   DECIMAL(18,2) NOT NULL COMMENT 'Ӧ���ƹ�',
   RESEARCHPROVE        DECIMAL(18,2) NOT NULL COMMENT '������֤',
   EXPERTCONSULT        DECIMAL(18,2) NOT NULL COMMENT 'ר����ѯ',
   OTHERFEE             DECIMAL(18,2) NOT NULL COMMENT '����',
   BUDGETYEAR           VARCHAR(10) DEFAULT NULL COMMENT 'Ԥ�����',
   SUBMITTIME           DATETIME DEFAULT NULL COMMENT '�ύʱ��',
   ISDELETE             VARCHAR(5) NOT NULL COMMENT '�Ƿ�ɾ��',
   NOTE                 VARCHAR(50) DEFAULT NULL COMMENT '��ע',
   PID                  VARCHAR(5) DEFAULT NULL COMMENT '�ʽ���Դ',
   PRIMARY KEY (ID)
)
ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='Ԥ�㣨����ʱ������⣩-ԭFundsBudget';

/*==============================================================*/
/* Table: FUNDS_IN                                              */
/*==============================================================*/
CREATE TABLE FUNDS_IN
(
   ID                   VARCHAR(50) NOT NULL COMMENT 'ID',
   AMOUNT_MONEY         DECIMAL(18,2) DEFAULT NULL COMMENT '�ϼ�',
   PROJECT_YEAR         VARCHAR(10) NOT NULL COMMENT '��Ŀ���',
   QUARTER_NUM          VARCHAR(5) NOT NULL COMMENT '���ڱ��',
   SUBMIT_TIME          DATETIME DEFAULT NULL COMMENT '�ύʱ��',
   IS_DELETE            VARCHAR(5) NOT NULL COMMENT '�Ƿ�ɾ��',
   NOTE                 VARCHAR(50) DEFAULT NULL COMMENT '��ע',
   USER_ID              VARCHAR(50) DEFAULT NULL COMMENT 'UserId',
   PID                  VARCHAR(5) DEFAULT NULL COMMENT '�ʽ���Դ',
   PRIMARY KEY (ID)
)
ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='����Ԥ��ִ�м���-ԭFundsIn';

/*==============================================================*/
/* Table: FUNDS_OUT                                             */
/*==============================================================*/
CREATE TABLE FUNDS_OUT
(
   ID                   VARCHAR(50) NOT NULL COMMENT 'ID',
   MATERIAL_MAKE        DECIMAL(18,2) NOT NULL COMMENT '�ز�����',
   COMPANY_CASE         DECIMAL(18,2) NOT NULL COMMENT '��ҵ�����ռ�',
   COURSE_DEVELOPMENT   DECIMAL(18,2) NOT NULL COMMENT '�γ̿���',
   SPECIAL_TOOL         DECIMAL(18,2) NOT NULL COMMENT '���⹤���������',
   APPLICATION_PROMETE  DECIMAL(18,2) NOT NULL COMMENT 'Ӧ���ƹ�',
   RESEARCH_PROVE       DECIMAL(18,2) NOT NULL COMMENT '������֤',
   EXPERT_CONSULT       DECIMAL(18,2) NOT NULL COMMENT 'ר����ѯ',
   OTHER_FEE            DECIMAL(18,2) NOT NULL COMMENT '����',
   PROJECT_YEAR         VARCHAR(10) NOT NULL COMMENT '��Ŀ���',
   QUARTER_NUM          VARCHAR(5) NOT NULL COMMENT '���ڱ��',
   SUBMIT_TIME          DATETIME DEFAULT NULL COMMENT '�ύʱ��',
   IS_DELETE            VARCHAR(5) NOT NULL COMMENT '�Ƿ�ɾ��',
   NOTE                 VARCHAR(50) DEFAULT NULL COMMENT '��ע',
   USER_ID              VARCHAR(50) DEFAULT NULL COMMENT 'UserId',
   PID                  VARCHAR(5) DEFAULT NULL COMMENT '�ʽ���Դ',
   PRIMARY KEY (ID)
)
ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='֧��Ԥ��ִ�м���-ԭFundsOut';

/*==============================================================*/
/* Table: INDICATOR_BASE                                        */
/*==============================================================*/
CREATE TABLE INDICATOR_BASE
(
   ID                   VARCHAR(50) NOT NULL COMMENT 'Id',
   FILE_IMPORT_ID       VARCHAR(50) NOT NULL COMMENT '������',
   TARGET_IMPLEMENT     TEXT COMMENT 'ʵʩ��Ŀ��',
   TARGET_FIRST_YEAR    TEXT COMMENT '��һ���Ŀ��',
   TARGET_SECOND_YEAR   TEXT COMMENT '�ڶ����Ŀ��',
   PRIMARY KEY (ID)
)
ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='��Ŀ��Ч����Ŀ��-ԭIndicatorImportDetail1';

/*==============================================================*/
/* Table: INDICATOR_DETAIL                                      */
/*==============================================================*/
CREATE TABLE INDICATOR_DETAIL
(
   ID                   VARCHAR(50) NOT NULL COMMENT 'Id',
   PROJECT_ID           VARCHAR(50) DEFAULT NULL,
   FILE_IMPORT_ID       VARCHAR(50) NOT NULL COMMENT '������',
   INDICATOR_THREE_LEVEL VARCHAR(500) DEFAULT NULL COMMENT '����ָ������',
   PLAN_TOTAL           VARCHAR(500) DEFAULT NULL COMMENT 'ָ������ֵ',
   PLAN_FIRST_YEAR      VARCHAR(500) DEFAULT NULL COMMENT '��һ���ָ��',
   PLAN_SECOND_YEAR     VARCHAR(500) DEFAULT NULL COMMENT '�ڶ����ָ��',
   INDICATOR_ONE_LEVEL  VARCHAR(500) DEFAULT NULL COMMENT 'һ��ָ��',
   INDICATOR_TOW_LEVEL  VARCHAR(500) DEFAULT NULL COMMENT '����ָ��',
   PRIMARY KEY (ID)
)
ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='��Ŀ��Чָ��-ԭIndicatorImportDetail2';

/*==============================================================*/
/* Table: LEAVE_MESSAGE                                         */
/*==============================================================*/
CREATE TABLE LEAVE_MESSAGE
(
   ID                   VARCHAR(50) NOT NULL COMMENT 'ID',
   MES_TYPE             VARCHAR(50) DEFAULT NULL COMMENT '��������',
   CONTENTS             VARCHAR(1000) DEFAULT NULL COMMENT '��������',
   PROJECT_ID           VARCHAR(50) DEFAULT NULL COMMENT '��ĿID',
   SUBMIT_USER_ID       VARCHAR(50) DEFAULT NULL COMMENT '�����û�ID',
   SUBMIT_DATE          DATETIME DEFAULT NULL COMMENT '��������',
   REPLY_USER_ID        VARCHAR(50) DEFAULT NULL COMMENT '�ظ��û�ID',
   REPLY_DATE           DATETIME DEFAULT NULL COMMENT '�ظ�����',
   REPLY_CONTENTS       VARCHAR(500) DEFAULT NULL COMMENT '�ظ�����',
   REF_FILE             VARCHAR(50) DEFAULT NULL COMMENT '�����ļ�',
   PRIMARY KEY (ID)
)
ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='����-ԭTestMsg';

/*==============================================================*/
/* Table: PROJECT                                               */
/*==============================================================*/
CREATE TABLE PROJECT
(
   ID                   VARCHAR(50) NOT NULL COMMENT 'Id',
   SCHOOL_NAME          VARCHAR(200) NOT NULL COMMENT 'ѧУ����',
   SCHOOL_HEAD          VARCHAR(50) NOT NULL COMMENT 'У�쵼',
   FINACE_HEADER        VARCHAR(50) NOT NULL COMMENT '����',
   FINACE_HEADER_TEL    VARCHAR(50) NOT NULL COMMENT '����绰',
   FINACE_HEADER_QQ     VARCHAR(50) DEFAULT NULL COMMENT '����QQ',
   PROJECT_HEADER       VARCHAR(50) NOT NULL COMMENT '��Ŀ������',
   PROJECT_HEADER_TEL   VARCHAR(50) NOT NULL COMMENT '��Ŀ�����˵绰',
   REPORTER             VARCHAR(50) NOT NULL COMMENT '���',
   REPORTER_TEL         VARCHAR(50) NOT NULL COMMENT '��˵绰',
   REPORTER_QQ          VARCHAR(50) DEFAULT NULL COMMENT '���qq',
   NOTE                 VARCHAR(100) DEFAULT NULL COMMENT '��ע',
   SUBMIT_TIME          DATETIME DEFAULT NULL COMMENT '�ύʱ��',
   IS_DELETE            VARCHAR(5) NOT NULL COMMENT '�Ƿ�ɾ��',
   PROJECT_NO           VARCHAR(100) NOT NULL COMMENT '��Ŀ���',
   MAJOR_NAME           VARCHAR(100) NOT NULL COMMENT 'רҵ����',
   CREATE_YEAR          VARCHAR(10) NOT NULL COMMENT '�������',
   UNION_SCHOOL         TEXT COMMENT '�������ֵ�λ',
   PARTNER_SCHOOL       TEXT COMMENT '���뽨�赥λ',
   PROVENCE_ID����ǰ��ֱ�������ƣ� VARCHAR(50) DEFAULT NULL,
   IMPORT_USER_ID       VARCHAR(50) DEFAULT NULL COMMENT '�����û�ID',
   PRIMARY KEY (ID)
)
ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='��Ŀ-ԭProject';

/*==============================================================*/
/* Table: REPORT_AUDIT_LOG                                      */
/*==============================================================*/
CREATE TABLE REPORT_AUDIT_LOG
(
   ID                   VARCHAR(50) NOT NULL COMMENT 'Id',
   YEAR                 VARCHAR(10) NOT NULL COMMENT '���',
   QUARTER              VARCHAR(5) NOT NULL COMMENT '����',
   STATUS               VARCHAR(5) NOT NULL COMMENT '״̬',
   REPORT_TIME          DATETIME DEFAULT NULL COMMENT 'ʱ��',
   FINANCE_AUDIT_TIME   DATETIME DEFAULT NULL COMMENT '�������ʱ��',
   SCHOOL_AUDIT_TIME    DATETIME DEFAULT NULL COMMENT 'ѧУ���ʱ�䣨��Ŀ�����ˣ�',
   CONUTRY_AUDIT_TIME   DATETIME DEFAULT NULL COMMENT '�������ʱ�䣨��������',
   FINANCE_AUDIT_STATE  VARCHAR(5) NOT NULL COMMENT '�������״̬',
   SCHOOL_AUDIT_STATE   VARCHAR(5) NOT NULL COMMENT 'ѧУ���״̬',
   CONUTRY_AUDIT_STATE  VARCHAR(5) NOT NULL COMMENT '�������״̬',
   AUDIT_OPINION        VARCHAR(500) DEFAULT NULL COMMENT '������',
   PROJECT_ID           VARCHAR(50) DEFAULT NULL COMMENT '��ĿID',
   PRIMARY KEY (ID)
)
ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='�������-ԭReport';

/*==============================================================*/
/* Table: REPORT_DEADLINE_SETTING                               */
/*==============================================================*/
CREATE TABLE REPORT_DEADLINE_SETTING
(
   ID                   VARCHAR(50) NOT NULL COMMENT 'Id',
   VALUE                VARCHAR(50) NOT NULL COMMENT '��ֹ����',
   REMARK               VARCHAR(50) DEFAULT NULL COMMENT '��ע',
   PRIMARY KEY (ID)
)
ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='�����ύ��ֹ��������-ԭMySetting';

/*==============================================================*/
/* Table: SITELOG                                               */
/*==============================================================*/
CREATE TABLE SITELOG
(
   ID                   VARCHAR(50) NOT NULL COMMENT 'Id',
   URL                  VARCHAR(500) NOT NULL COMMENT 'Url',
   ACTION               VARCHAR(50) DEFAULT NULL COMMENT '�������',
   CONTROLLER           VARCHAR(50) DEFAULT NULL COMMENT '������������',
   IP                   VARCHAR(50) DEFAULT NULL COMMENT 'IP��ַ',
   OPERATE_TIME         DATETIME NOT NULL COMMENT '����ʱ��',
   USER_ID              VARCHAR(50) DEFAULT NULL COMMENT '�û�ID',
   PRIMARY KEY (ID)
)
ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='���������־-ԭSiteLog';

/*==============================================================*/
/* Table: TUSER                                                 */
/*==============================================================*/
CREATE TABLE TUSER
(
   ID                   VARCHAR(50) NOT NULL COMMENT 'UserID',
   USER_NO              VARCHAR(100) NOT NULL COMMENT '�û���ţ���¼ƾ֤',
   MAJOR_NAME           VARCHAR(200) NOT NULL COMMENT 'רҵ����',
   USER_PASSWORD        VARCHAR(200) NOT NULL COMMENT '����',
   USER_ROLE            VARCHAR(5) NOT NULL COMMENT '�û���ɫ',
   NOTE                 VARCHAR(100) DEFAULT NULL COMMENT '��ע',
   IS_DELETE            VARCHAR(5) DEFAULT NULL COMMENT '�Ƿ�ɾ��',
   TELEPHONE_NUM        VARCHAR(20) DEFAULT NULL COMMENT '�绰',
   USER_NAME            VARCHAR(20) DEFAULT NULL COMMENT '�û�����',
   PRIMARY KEY (ID)
)
ENGINE=INNODB DEFAULT CHARSET=UTF8 COMMENT='�û�';

