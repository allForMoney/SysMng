import React from 'react';
import { connect } from 'dva';
import {
  Card,
  Table,
  Modal,
  Input
} from 'antd';
import moment from 'moment';

import FrameContent from '../common/FrameContent';
import LinkBtn from '../common/LinkBtn';

class AchiveMsgList extends React.Component {
  state={
    showMsgModal: false, msgValue: '', editRec: {}
  }
  componentDidMount() {
    this.props.dispatch({
      type: 'msgModel/setState',
      payload: { budgetAchiveListPage: 1 }
    });

    this.props.dispatch({
      type: 'msgModel/getAchiveMsgList',
    });
  }

  onPageChange= (page) => {
    this.props.dispatch({
      type: 'msgModel/setState',
      payload: { budgetAchiveListPage: page }
    });

    this.props.dispatch({
      type: 'msgModel/getAchiveMsgList',
    });
  }

  replayMsg=(rec) => {
    this.setState({
      editRec: rec,
      showMsgModal: true,
    });
  }

  saveMsg= () => {
    const { msgValue, editRec } = this.state;
    this.props.dispatch({
      type: 'msgModel/saveBudgetReply',
      payload: { content: msgValue, id: editRec.id }
    });
    this.cancelMsg();
  }

  cancelMsg= () => {
    this.setState({
      showMsgModal: false, msgValue: ''
    });
  }

  render() {
    const {
    budgetAchiveList,
    budgetAchiveListPage,
    budgetAchiveListNum,
      loading,
    } = this.props;

    const columns = [
      {
        title: '项目编号',
        dataIndex: 'projectNo',
        key: 'projectNo',
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
        dataIndex: 'submitUserId',
        key: 'submitUserId',
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
      }, {
        title: '操作',
        key: 'operat',
        render: rec => <LinkBtn onClick={this.replayMsg.bind(this, rec)}>回复留言</LinkBtn>
      }
    ];

    const recPageConfig = {
      className: 'ant-table-pagination',
      total: budgetAchiveListNum,
      current: budgetAchiveListPage,
      pageSize: 20,
      onChange: this.onPageChange,
    };

    const { showMsgModal, msgValue } = this.state;

    return (
      <Card title="绩效留言处理">
        <Modal
          title="留言信息"
          visible={showMsgModal}
          onOk={this.saveMsg}
          onCancel={this.cancelMsg}
        >
          <Input
            type="textarea"
            rows={8}
            placeholder="你的留言"
            value={msgValue}
            onChange={e => this.setState({ msgValue: e.target.value })}
          />
        </Modal>
        <Table
          size="small"
          bordered
          columns={columns}
          dataSource={budgetAchiveList}
          loading={loading}
          pagination={recPageConfig}
        />
      </Card>

    );
  }
}

function mapStateToProps(state) {
  const {
    userId,
   } = state.baseModel;
  const {
      loading,
    budgetAchiveList,
    budgetAchiveListPage,
    budgetAchiveListNum,
     } = state.msgModel;
  return {
    loading,
    budgetAchiveList,
    budgetAchiveListPage,
    budgetAchiveListNum,
    userId,
  };
}

export default connect(mapStateToProps)(AchiveMsgList);
