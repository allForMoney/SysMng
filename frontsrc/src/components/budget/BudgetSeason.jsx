import React from 'react';
import { connect } from 'dva';
import {
  Table,
  Card
} from 'antd';
import LinkBtn from '../common/LinkBtn';
import FrameContent from '../common/FrameContent';

class BudgetSeason extends React.Component {
  state = {
    showDetail: false,    
  }

  onBudgetMsgChange= () => {
  }

  render() {
    const {
      budgetSeasonList,
      loading,
      budgetSeasonNum,
      budgetSeasonPage,
      projectName,
      projectNo,
    } = this.props;

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
      dataIndex: 'time',
      key: 'time',
    }, {
      title: '财务部门负责人审核时间',
      dataIndex: 'time2',
      key: 'time2',
    }, {
      title: '项目负责人审核时间',
      dataIndex: 'time3',
      key: 'time3',
    }, {
      title: '状态',
      dataIndex: 'time',
      key: 'time',
    }, {
      title: '操作',
      key: 'operat',
      render: rec => <LinkBtn onClick={this.showJustifyDetail.bind(this, rec)}>查看报表</LinkBtn>
    }];

    const pageConfig = {
      className: 'ant-table-pagination',
      total: budgetSeasonNum,
      current: budgetSeasonPage,
      pageSize: 20,
      onChange: this.onBudgetMsgChange,
    };

    return (
      <FrameContent>
        <Card title={`留言处理情况[编号: ${projectNo},名称:${projectName}]`}>
          <Table
            title={() => '留言处理情况'}
            columns={columns}
            dataSource={budgetSeasonList}
            loading={loading}
            rowKey={record => record.id}
            pagination={pageConfig}
          />
        </Card>
      </FrameContent>
    );
  }
}

function mapStateToProps(state) {
  const {
      budgetMsgList,
      loading,
      budgetMsgNum,
      budgetMsgage,
    projectId } = state.baseModel;
  return {
    budgetMsgList,
    loading,
    budgetMsgNum,
    budgetMsgage,
    projectId };
}

export default connect(mapStateToProps)(BudgetSeason);
