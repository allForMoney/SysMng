/**
 * 项目预算表table,在 ProjectBudget 中引入
 */
import React from 'react';
import { Table } from 'antd';

function ProjectBudgetTable(props) {
  const {
    tableTitle,
    totalNum,
    onPageChange,
    currentPage,
    dataList,
    loading,
  } = props;
  const columns =
    [{
      title: '序号',
      dataIndex: 'sequenceNo',
      key: 'sequenceNo',
      width: 10,
    }, {
      title: '资金用途',
      dataIndex: 'useFor',
      key: 'useFor',
      width: 100,
    }, {
      title: '项目建设资金总数',
      dataIndex: 'totalMoney',
      key: 'totalMoney',
      width: 60,
    }, {
      title: '资金来源',
      children: [{
        title: '部本专项资金',
        children: [{
          title: '合计',
          dataIndex: 'countryTotal',
          width: 40,
          key: 'countryTotal',
        }, {
          title: '占项目建设资金的比例(%)',
          dataIndex: 'countryPercent',
          key: 'countryPercent',
        }, {
          title: '其中',
          children: [{
            title: '第一年',
            dataIndex: 'countryFirstYear',
            key: 'countryFirstYear',
          }, {
            title: '第二年',
            dataIndex: 'countrySecondYear',
            key: 'countrySecondYear',
          }]
        }],
      }, {
        title: '项目筹措资金',
        children: [{
          width: 40,
          title: '合计',
          dataIndex: 'projectTotal',
          key: 'projectTotal',
        }, {
          title: '占项目建设资金的比例(%)',
          dataIndex: 'projectPercent',
          key: 'projectPercent',
        }, {
          title: '其中',
          children: [{
            title: '举办方或地方财政投入资金',
            children: [{
              title: '第一年',
              dataIndex: 'localFirstYear',
              key: 'localFirstYear',
            }, {
              title: '第二年',
              dataIndex: 'localSecondYear',
              key: 'localSecondYear',
            }, {
              title: '第三年',
              dataIndex: 'localThreeYear',
              key: 'localThreeYear',
            }]
          }, {
            title: '行业企业支持资金',
            children: [{
              title: '第一年',
              dataIndex: 'enterpriseFirstYear',
              key: 'enterpriseFirstYear',
            }, {
              title: '第二年',
              dataIndex: 'enterpriseSecondYear',
              key: 'enterpriseSecondYear',
            }, {
              title: '第三年',
              dataIndex: 'enterpriseThreeYear',
              key: 'enterpriseThreeYear',
            }]
          }, {
            title: '相关院校自筹资金',
            children: [{
              title: '第一年',
              dataIndex: 'universityFirstYear',
              key: 'universityFirstYear',
            }, {
              title: '第二年',
              dataIndex: 'universitySecondYear',
              key: 'universitySecondYear',
            }, {
              title: '第三年',
              dataIndex: 'universityThreeYear',
              key: 'universityThreeYear',
            }]
          }]
        }],
      }],
    }];

  
  const title = `${tableTitle} (单位:万元)`;

  const pageConfig = {
    className: 'ant-table-pagination',
    total: totalNum,
    current: currentPage,
    // pageSize: 20,
    onChange: onPageChange,
  };
  return (
    <Table
      title={() => title}
      size="small"
      scroll={{ x: true, y: 300 }}
      columns={columns}
      dataSource={dataList}
      loading={loading}
      rowKey={record => record.id}
      pagination={false}
    />
  );
}

export default ProjectBudgetTable;
