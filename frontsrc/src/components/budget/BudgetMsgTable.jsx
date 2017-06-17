import React from 'react';
import { connect } from 'dva';
import {
  Table,
} from 'antd';

import FrameContent from '../common/FrameContent';

class BudgetMsgTable extends React.Component {
  onBudgetMsgChange= () => {
    
  }
  
  render() {
    const {
      budgetMsgList,
      loading,
      budgetMsgNum,
      budgetMsgage,
    } = this.props;

    const columns = [{
      dataIndex: 'index',
      key: 'index',
    }, {
      title: '留言时间',
      dataIndex: 'name',
      key: 'name',
    }, {
      title: '留言内容',
      dataIndex: 'status',
      key: 'status',
    }, {
      title: '相关文件',
      dataIndex: 'time',
      key: 'time',
    }, {
      title: '恢复内容',
      dataIndex: 'time2',
      key: 'time2',
    }, {
      title: '回复时间',
      dataIndex: 'time3',
      key: 'time3',
    }];

    const pageConfig = {
      className: 'ant-table-pagination',
      total: budgetMsgNum,
      current: budgetMsgage,
      pageSize: 20,
      onChange: this.onBudgetMsgChange,
    };

    return (
      <FrameContent>
        <Table
          title={() => '留言处理情况'}
          columns={columns}
          dataSource={budgetMsgList}
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

export default connect(mapStateToProps)(BudgetMsgTable);
