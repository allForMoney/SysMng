/**
 * 项目预算表table,在 ProjectBudget/BudgetJustifyRec 中引入
 */
import React from 'react';
import { Table } from 'antd';

function ProjectBudgetTable(props) {
  const {
    tableTitle,
    totalNum,
    onPageChange,
    currentPage,
    loading,
    compare,
  } = props;
  let { dataList } = props;
  const columns =
    [{
      title: '一级序号',
      dataIndex: 'sequenceNo',
      key: 'sequenceNo',
      width: 10,
    }, {
      title: '支出',
      children: [{
        title: '开支范围',
        dataIndex: 'useFor',
        key: 'useFor',
      }, {
        title: '经济业务分类',
        children: [{
          title: '咨询费',
          dataIndex: 'consult',
          key: 'consult',
        }, {
          title: '印刷费',
          dataIndex: 'print',
          key: 'print',
        }, {
          title: '差旅费',
          dataIndex: 'travel',
          key: 'travel',
        }, {
          title: '会议费',
          dataIndex: 'metting',
          key: 'metting',
        }, {
          title: '培训费',
          dataIndex: 'trainning',
          key: 'trainning',
        }, {
          title: '专用材料费',
          dataIndex: 'specialMaterial',
          key: 'specialMaterial',
        }, {
          title: '委托业务费',
          dataIndex: 'delegation',
          key: 'delegation',
        }, {
          title: '其它商品和服务支出',
          dataIndex: 'otherExpense',
          key: 'otherExpense',
        }, {
          title: '专用设备购置费',
          dataIndex: 'specialDevice',
          key: 'specialDevice',
        }, {
          title: '信息网络及软件购置更新',
          dataIndex: 'infomationTechnology',
          key: 'infomationTechnology',
        }]
      }]
    }, {
      title: '项目预算总额',
      dataIndex: 'totalMoney',
      key: 'totalMoney',
    }, {
      title: '支出',
      children: [{
        title: '申请部本专项',
        children: [{
          title: '合计',
          dataIndex: 'countryTotal',
          key: 'countryTotal',
        }, {
          title: '占项目预算总额比(%)',
          dataIndex: 'countryPercent',
          key: 'countryPercent',
        }]
      }, {
        title: '项目筹措资金',
        children: [{
          title: '合计',
          dataIndex: 'projectTotal',
          key: 'projectTotal',
        }, {
          title: '占项目预算总额比(%)',
          dataIndex: 'projectPercent',
          key: 'projectPercent',
        }, {
          title: '其中',
          children: [{
            title: '举办方或地方财政投入资金',
            dataIndex: 'local',
            key: 'local',
          }, {
            title: '行业企业支持资金',
            dataIndex: 'enterprise',
            key: 'enterprise',
          }, {
            title: '相关院校自筹资金',
            dataIndex: 'university',
            key: 'university',
          }]
        }]
      }]
    }];


  const title = `${tableTitle} (单位:万元)`;
  if (compare) {
    columns.unshift(
      {
        title: '对比',
        dataIndex: 'compare',
        key: 'compare',
        width: 30,
      });
    const temp = [];
    dataList.forEach((item) => {
      temp.push({ compare: '调整前', ...item.beforeAdjust });
      temp.push({ compare: '调整后', ...item.afterAdjust });
// afterAdjust/beforeAdjust
    });
    dataList = temp;
  }

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
      bordered
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
