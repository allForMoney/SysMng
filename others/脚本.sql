/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2017/6/20 8:01:01                            */
/*==============================================================*/


drop table if exists BudgetAdjust;

drop table if exists BudgetAuditLog;

drop table if exists BudgetImportDetail2016;

drop table if exists BudgetImportDetailOld;

drop table if exists CommonCode;

drop table if exists Experts;

drop table if exists FileImportLog;

drop table if exists FundsBudget;

drop table if exists FundsIn;

drop table if exists FundsOut;

drop table if exists IndicatorBase;

drop table if exists IndicatorDetail;

drop table if exists LeaveMessage;

drop table if exists Project;

drop table if exists ReportAuditLog;

drop table if exists ReportDeadlineSetting;

drop table if exists SiteLog;

drop table if exists TUser;

/*==============================================================*/
/* Table: BudgetAdjust                                          */
/*==============================================================*/
create table BudgetAdjust
(
   Id                   varchar(20) not null,
   ImportDate           datetime not null,
   FileName             national varchar(50) not null,
   Attachment1          national varchar(500),
   Attachment2          national varchar(500),
   ImportUserId         varchar(20),
   ProjectId            varchar(20),
   primary key (Id)
);

/*==============================================================*/
/* Table: BudgetAuditLog                                        */
/*==============================================================*/
create table BudgetAuditLog
(
   Id                   varchar(20) not null,
   Year                 varchar(10) not null,
   AdjudstId            varchar(20) not null,
   Status               varchar(1) not null,
   ReportTime           datetime,
   FinanceAuditTime     datetime,
   SchoolAuditTime      datetime,
   CountryAuditTime     datetime,
   FinanceAuditState    varchar(1) not null,
   SchoolAuditState     varchar(1) not null,
   CountryAuditState    varchar(1) not null,
   AuditOpinion         varchar(500),
   primary key (Id)
);

/*==============================================================*/
/* Table: BudgetImportDetail2016                                */
/*==============================================================*/
create table BudgetImportDetail2016
(
   Id                   varchar(20) not null,
   SequenceNo           varchar(10),
   UsedFor              varchar(50),
   Consult              varchar(50),
   Print                varchar(50),
   Travel               varchar(50),
   Meeting              varchar(50),
   Training             varchar(50),
   SpecialMaterial      varchar(50),
   Delegation           varchar(50),
   OtherExpense         varchar(50),
   SpecialDevice        varchar(50),
   InformationTechnology varchar(50),
   TotalMoney           varchar(50),
   CountryTotal         varchar(50),
   CountryPercent       varchar(50),
   ProjectTotal         varchar(50),
   ProjectPercent       varchar(50),
   Local                varchar(50),
   Enterprise           varchar(50),
   University1          varchar(50),
   BudgetYear1          varchar(10) not null,
   BudgetImportId       varchar(20),
   primary key (Id)
);

/*==============================================================*/
/* Table: BudgetImportDetailOld                                 */
/*==============================================================*/
create table BudgetImportDetailOld
(
   Id                   varchar(20) not null,
   SequenceNo           varchar(10),
   UsedFor              varchar(50),
   TotalMoney           varchar(50),
   CountryTotal         varchar(50),
   CountryPercent       varchar(50),
   CountryYear1         varchar(50),
   CountryYear2         varchar(50),
   ProjectTotal         varchar(50),
   ProjectPercent       varchar(50),
   LocalYear1           varchar(50),
   LocalYear2           varchar(50),
   LocalYear3           varchar(50),
   EnterpriseYear1      varchar(50),
   EnterpriseYear2      varchar(50),
   EnterpriseYear3      varchar(50),
   UniversityYear1      varchar(50),
   UniversityYear2      varchar(50),
   UniversityYear3      varchar(50),
   BudgetImportId       varchar(20),
   primary key (Id)
);

/*==============================================================*/
/* Table: CommonCode                                            */
/*==============================================================*/
create table CommonCode
(
   Id                   varchar(20) not null,
   name                 varchar(50) not null,
   remark               varchar(50),
   code                 varchar(10) not null,
   className            varchar(10),
   primary key (Id)
);

/*==============================================================*/
/* Table: Experts                                               */
/*==============================================================*/
create table Experts
(
   Id                   varchar(20) not null,
   代码                   varchar(20),
   EName                varchar(20),
   Gender               varchar(10),
   Cid                  varchar(50),
   TelNumber            varchar(50),
   ZhiCheng             varchar(50),
   XueLi                varchar(20),
   UnUnit               varchar(200),
   LingYu               varchar(200),
   primary key (Id)
);

/*==============================================================*/
/* Table: FileImportLog                                         */
/*==============================================================*/
create table FileImportLog
(
   Id                   varchar(20) not null,
   ImportDate           datetime not null,
   FileName             national varchar(50) not null,
   ImportUserId         varchar(20),
   ProjectId            varchar(20),
   ImportType           varchar(10),
   primary key (Id)
);

/*==============================================================*/
/* Table: FundsBudget                                           */
/*==============================================================*/
create table FundsBudget
(
   UserID               varchar(20) not null,
   MaterialMake         decimal(18,2) not null,
   CompanyCase          decimal(18,2) not null,
   CourseDevelopment    decimal(18,2) not null,
   ToolSoftware         decimal(18,2) not null,
   ApplicationPromote   decimal(18,2) not null,
   ResearchProve        decimal(18,2) not null,
   ExpertConsult        decimal(18,2) not null,
   OtherFee             decimal(18,2) not null,
   BudgetYear           varchar(10) not null,
   SubmitTime           datetime,
   IsDelete             varchar(1) not null,
   Note                 varchar(50),
   PID                  varchar(1),
   primary key (UserID)
);

/*==============================================================*/
/* Table: FundsIn                                               */
/*==============================================================*/
create table FundsIn
(
   ID                   varchar(20) not null,
   MoneyAmount          decimal(18,2) not null,
   ProjectYear          varchar(10) not null,
   QuarterNum           varchar(1) not null,
   SubmitTime           datetime,
   IsDelete             varchar(1) not null,
   Note                 varchar(50),
   用户ID                 varchar(20),
   PID                  varchar(1),
   primary key (ID)
);

/*==============================================================*/
/* Table: FundsOut                                              */
/*==============================================================*/
create table FundsOut
(
   ID                   varchar(20) not null,
   MaterialMake         decimal(18,2) not null,
   CompanyCase          decimal(18,2) not null,
   CourseDevelopment    decimal(18,2) not null,
   ToolSoftware         decimal(18,2) not null,
   ApplicationPromote   decimal(18,2) not null,
   ResearchProve        decimal(18,2) not null,
   ExpertConsult        decimal(18,2) not null,
   OtherFee             decimal(18,2) not null,
   ProjectYear          varchar(10) not null,
   QuarterNum           varchar(1) not null,
   SubmitTime           datetime,
   IsDelete             varchar(1) not null,
   Note                 varchar(50),
   用户ID                 varchar(20),
   PID                  varchar(1),
   primary key (ID)
);

/*==============================================================*/
/* Table: IndicatorBase                                         */
/*==============================================================*/
create table IndicatorBase
(
   Id                   varchar(20) not null,
   BudgetImportId       varchar(20) not null,
   Objective            text,
   ObjectiveYear1       text,
   ObjectiveYear2       text,
   primary key (Id)
);

/*==============================================================*/
/* Table: IndicatorDetail                                       */
/*==============================================================*/
create table IndicatorDetail
(
   Id                   varchar(20) not null,
   BudgetImportId       varchar(20) not null,
   Indicator3           varchar(500),
   PlanTotal            varchar(500),
   PlanYear1            varchar(500),
   PlanYear2            varchar(500),
   Indicator1           varchar(500),
   Indicator2           varchar(500),
   primary key (Id)
);

/*==============================================================*/
/* Table: LeaveMessage                                          */
/*==============================================================*/
create table LeaveMessage
(
   ID                   varchar(20) not null,
   留言类型                 varchar(50),
   Contents             varchar(1000),
   ProjectId            varchar(20),
   SubmitUserId         varchar(20),
   SubmitDate           datetime,
   ReplyUserId          varchar(20),
   ReplyDate            datetime,
   ReplyContents        varchar(500),
   ReferFileName        varchar(50),
   primary key (ID)
);

/*==============================================================*/
/* Table: Project                                               */
/*==============================================================*/
create table Project
(
   Id                   varchar(20) not null,
   SchoolName           varchar(200) not null,
   SchoolHead           varchar(50) not null,
   FinanceHead          varchar(50) not null,
   FHTel                varchar(50) not null,
   FHQQ                 varchar(50),
   ProjectHead          varchar(50) not null,
   PHTel                varchar(50) not null,
   ReportHead           varchar(50) not null,
   RHTel                varchar(50) not null,
   RHQQ                 varchar(50),
   Note                 varchar(100),
   SubmitTime           datetime,
   IsDelete             varchar(1) not null,
   ProjectNo            varchar(100) not null,
   MajorName            varchar(100) not null,
   CreateYear           varchar(10) not null,
   School2              text,
   School3              text,
   primary key (Id)
);

/*==============================================================*/
/* Table: ReportAuditLog                                        */
/*==============================================================*/
create table ReportAuditLog
(
   Id                   varchar(20) not null,
   Year                 varchar(10) not null,
   Quarter              varchar(1) not null,
   Status               varchar(1) not null,
   ReportTime           datetime,
   FinanceAuditTime     datetime,
   SchoolAuditTime      datetime,
   CountryAuditTime     datetime,
   FinanceAuditState    varchar(1) not null,
   SchoolAuditState     varchar(1) not null,
   CountryAuditState    varchar(1) not null,
   AuditOpinion         varchar(500),
   ProjectId            varchar(20),
   primary key (Id)
);

/*==============================================================*/
/* Table: ReportDeadlineSetting                                 */
/*==============================================================*/
create table ReportDeadlineSetting
(
   Id                   varchar(20) not null,
   Value                varchar(50) not null,
   Remark               varchar(50),
   primary key (Id)
);

/*==============================================================*/
/* Table: SiteLog                                               */
/*==============================================================*/
create table SiteLog
(
   Id                   varchar(20) not null,
   Url                  varchar(500) not null,
   Action               varchar(50),
   Controller           varchar(50),
   IPAddress            varchar(50),
   TheTime              datetime not null,
   UserId               varchar(20),
   primary key (Id)
);

/*==============================================================*/
/* Table: TUser                                                 */
/*==============================================================*/
create table TUser
(
   UserID               varchar(20) not null,
   UserNo               varchar(100) not null,
   MajorName            varchar(200) not null,
   UserPassword         varchar(200) not null,
   UserRole             varchar(1) not null,
   Note                 varchar(100),
   IsDelete             varchar(1),
   Telephone            varchar(20),
   UserName             varchar(20),
   primary key (UserID)
);

