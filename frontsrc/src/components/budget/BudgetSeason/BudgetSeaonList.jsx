
import React from 'react';
import { connect } from 'dva';
import { Table } from 'antd';
import moment from 'moment';
import { routerRedux } from 'dva/router';
import FrameContent from '../../common/FrameContent';
import LinkBtn from '../../common/LinkBtn';

class BudgetSeaonList extends React.Component {
  componentDidMount() {
    this.props.dispatch({
      type: 'budgetModel/getBudgetRecList',
    });
  }

  onPageChanged = (page) => {
    this.props.dispatch({
      type: 'budgetModel/setState',
      payload: {
        budgetRecordPage: page,
      },
    });
    this.props.dispatch({
      type: 'budgetModel/getBudgetRecList',
    });
  };

  checkBudget = (rec) => {
    const { year, quarter } = rec;
    this.props.dispatch({
      type: 'budgetModel/setState',
      payload: {
        projectYear: year,
        quarterNum: quarter,
        showSeasonExport: true,
      }
    });
    this.props.dispatch(routerRedux.push({
      pathname: '/budget/addbudgetseason',
    }));
  };

  render() {
    const {
      loading,
      budgetRecordList,
      budgetRecordPage,
      budgetRecordNum,
      projectInfo,
    } = this.props;

    const pageConfig = {
      className: 'ant-table-pagination',
      total: budgetRecordNum,
      current: budgetRecordPage,
      pageSize: 20,
      onChange: this.onPageChanged,
    };

    const columns = [{
      title: '年度',
      dataIndex: 'year',
      key: 'year',
    }, {
      title: '季度',
      dataIndex: 'quarter',
      key: 'quarter',
    }, {
      title: '填报人提交时间',
      dataIndex: 'reportTime',
      key: 'reportTime',
      render: (time) => {
        let str = '-';
        if (time) {
          str = moment(time).format('YYYY-MM-DD: HH:mm:ss');
        }
        return str;
      }
    }, {
      title: '部门负责人审核时间',
      dataIndex: 'financeAuditTime',
      key: 'financeAuditTime',
      render: (time) => {
        let str = '-';
        if (time) {
          str = moment(time).format('YYYY-MM-DD: HH:mm:ss');
        }
        return str;
      }
    }, {
      title: '项目负责人审核时间',
      dataIndex: 'schoolAuditTime',
      key: 'schoolAuditTime',
      render: (time) => {
        let str = '-';
        if (time) {
          str = moment(time).format('YYYY-MM-DD: HH:mm:ss');
        }
        return str;
      }
    }, {
      title: '状态',
      dataIndex: 'status',
      key: 'status',
      render: (status) => {
        let str = '已经填报';
        switch (status) {
          case '0':
            str = '已经填报';
            break;
          case '1':
            str = '财务已经审核通过';
            break;
          case '3':
            str = '负责人已经审核通过';
            break;
          case '5':
            str = '教育部已经审核通过';
            break;
          default:
            break;
        }
        return str;
      }
    }, {
      title: '操作',
      dataIndex: 'tiyyme3',
      render: (text, rec) => <LinkBtn onClick={this.checkBudget.bind(this, rec)} >查看报表</LinkBtn>
    }];

    return (
      <FrameContent>
        <Table
          title={() => `预算执行季报查询【编号：${projectInfo.projectNo}，名称：${projectInfo.majorName}】`}
          columns={columns}
          bordered
          dataSource={budgetRecordList}
          loading={loading}
          rowKey={record => record.id}
          pagination={pageConfig}
        />
      </FrameContent>
    );
  }
}

function mapStateToProps(state) {
  const {
      userType,
      projectId,
      projectName,
      projectInfo,
  } = state.baseModel;

  const {
    budgetRecordList,
    budgetRecordPage,
    budgetRecordNum,
  } = state.budgetModel;

  return {
    userType,
    projectInfo,
    projectId,
    projectName,
    budgetRecordList,
    budgetRecordPage,
    budgetRecordNum,
  };
}

export default connect(mapStateToProps)(BudgetSeaonList);
