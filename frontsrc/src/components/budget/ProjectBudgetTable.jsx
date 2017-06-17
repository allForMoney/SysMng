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
      dataIndex: 'index',
      key: 'index',
      width: 10,
    }, {
      title: '资金用途',
      dataIndex: 'index2',
      key: 'index2',
      width: 10,
    }, {
      title: '项目建设资金总数',
      dataIndex: 'index3',
      key: 'index3',
      width: 10,
    }, {
      title: '资金来源',
      children: [{
        title: '部本专项资金',
        children: [{
          title: '合计',
          dataIndex: 'total',
          key: 'street',
        }, {
          title: '占项目建设资金的比例(%)',
          dataIndex: 'street',
          key: 'street3',
        }, {
          title: '其中',
          children: [{
            title: '第一年',
            dataIndex: 'street',
            key: 'street33',
          }, {
            title: '第二年',
            dataIndex: 'street',
            key: 'street333',
          }]
        }],
      }, {
        title: '项目筹措资金',
        children: [{
          title: '合计',
          dataIndex: 'total',
          key: 'street1',
        }, {
          title: '占项目建设资金的比例(%)',
          dataIndex: 'street',
          key: 'street11',
        }, {
          title: '其中',
          children: [{
            title: '举办方或地方财政投入资金',
            children: [{
              title: '第一年',
              dataIndex: 'street',
              key: 'stre2et',
            }, {
              title: '第二年',
              dataIndex: 'street',
              key: 'str22eet',
            }, {
              title: '第三年',
              dataIndex: 'street',
              key: 'str2222eet',
            }]
          }, {
            title: '行业企业支持资金',
            children: [{
              title: '第一年',
              dataIndex: 'street',
              key: 'seetreet',
            }, {
              title: '第二年',
              dataIndex: 'street',
              key: 'streeet',
            }, {
              title: '第三年',
              dataIndex: 'street',
              key: 'streeeeeet',
            }]
          }, {
            title: '相关院校自筹资金',
            children: [{
              title: '第一年',
              dataIndex: 'street',
              key: 'streeft',
            }, {
              title: '第二年',
              dataIndex: 'street',
              key: 'streefft',
            }, {
              title: '第三年',
              dataIndex: 'street',
              key: 'strefffet',
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
    pageSize: 20,
    onChange: onPageChange,
  };
  return (
    <Table
      title={() => title}
      columns={columns}
      dataSource={dataList}
      loading={loading}
      rowKey={record => record.id}
      pagination={pageConfig}
    />
  );
}

export default ProjectBudgetTable;
