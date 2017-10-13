import React from 'react';
import { connect } from 'dva';
import {
  Table,
} from 'antd';
import moment from 'moment';

import FrameContent from '../common/FrameContent';
import LinkBtn from '../common/LinkBtn';

class BudgetMsgTable extends React.Component {
  componentDidMount() {
    this.props.dispatch({
      type: 'msgModel/setState',
      payload: { budgetMsgListPage: 1 }
    });

    this.props.dispatch({
      type: 'msgModel/getBudgetMsgList',
    });
  }
  onPageChange= (page) => {
    this.props.dispatch({
      type: 'msgModel/setState',
      payload: { budgetMsgListPage: page }
    });

    this.props.dispatch({
      type: 'msgModel/getBudgetMsgList',
    });
  }

  render() {
    const {
      budgetMsgList,
      loading,
      budgetMsgNum,
      budgetMsgage,
    } = this.props;

    const columns = [
      {
        title: '项目编号',
        dataIndex: 'projectNos',
        key: 'projectNos',
      }, {
        title: '留言时间',
        dataIndex: 'submitDate',
        key: 'submitDate',
        render: (time) => {
          let str = '-';
          if (time) {
            str = moment(time).format('YYYY-MM-DD HH:mm;ss');
          }
          return str;
        }
      }, {
        title: '留言人',
        dataIndex: 'submitUserName',
        key: 'submitUserName',
      }, {
        title: '留言内容',
        dataIndex: 'contents',
        key: 'contents',
        width: 300
      }, {
        title: '相关文件',
        dataIndex: 'refFile',
        key: 'refFile',
      }, {
        title: '回复内容',
        dataIndex: 'replyContents',
        key: 'replyContents',
      }, {
        title: '回复时间',
        dataIndex: 'replyDate',
        key: 'replyDate',
        render: (time) => {
          let str = '-';
          if (time) {
            str = moment(time).format('YYYY-MM-DD HH:mm;ss');
          }
          return str;
        }
      }
    ];

    const pageConfig = {
      className: 'ant-table-pagination',
      total: budgetMsgNum,
      current: budgetMsgage,
      pageSize: 20,
      onChange: this.onBudgetMsgChange,
    };

    return (

      <Table
        title={() => '留言处理情况'}
        bordered
        columns={columns}
        dataSource={budgetMsgList}
        loading={loading}
        rowKey={record => record.id}
        pagination={pageConfig}
      />

    );
  }
}

function mapStateToProps(state) {
  const {
    userId,
   } = state.baseModel;
  const {
      loading,
      budgetMsgList,
      budgetMsgListPage,
      budgetMsgListNum,
     } = state.msgModel;
  return {
    loading,
    budgetMsgList,
    budgetMsgListPage,
    budgetMsgListNum,
    userId,
  };
}

export default connect(mapStateToProps)(BudgetMsgTable);
