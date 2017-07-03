
import React from 'react';
import { connect } from 'dva';
import { Table } from 'antd';
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

  checkBudget = () => {
    console.log(' checkBudget... ');
    this.props.dispatch(routerRedux.push({
      pathname: '/budget/addbudgetseason',
    }));
  };

  render() {
    const {
      projectId,
      loading,
      projectName,
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
      dataIndex: 'index',
      key: 'index',
    }, {
      title: '季度',
      dataIndex: 'name',
      key: 'name',
    }, {
      title: '填报人提交时间',
      dataIndex: 'status',
      key: 'status',
    }, {
      title: '部门负责人审核时间',
      dataIndex: 'time',
      key: 'time',
    }, {
      title: '项目负责人审核时间',
      dataIndex: 'time2',
      key: 'time2',
    }, {
      title: '状态',
      dataIndex: 'titme3',
      key: 'time3',
    }, {
      title: '操作',
      dataIndex: 'tiyyme3',
      render: () => <LinkBtn onClick={this.checkBudget} >查看报表</LinkBtn>
    }];

    return (
      <FrameContent>
        <Table
          title={() => `预算执行季报查询【编号：${projectInfo.id}，名称：${projectName}】`}
          columns={columns}
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
