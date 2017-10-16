/**
 * 项目预算审核页面( 在非填报人点击 项目预算调整时展现)
 */
import React from 'react';
import { connect } from 'dva';
import {
  Card,
  Table,
} from 'antd';
import moment from 'moment';

import FrameContent from '../common/FrameContent';
import LinkBtn from '../common/LinkBtn';
import ProjectBudgetTable from './ProjectBudgetTable';

class BudgetJustifyCheck extends React.Component {
  componentDidMount() {
    const auditStatus = this.getStatusByUserType();
    this.props.dispatch({
      type: 'BudgetJustifyModel/setState',
      payload: { auditStatus }
    });
    this.props.dispatch({
      type: 'BudgetJustifyModel/getBudgetJustifyList',
    });
  }

  getStatusByUserType = () => {
    const { userType } = this.props;
    let status = '-1';
    switch (userType) {
      case 'finace':
        status = '0';
        break;
      case 'school':
        status = '1';
        break;
      case 'country':
        status = '3';
        break;
      default:
        break;
    }
    return status;
  }

  onJustifyListPageChange= (page) => {
    this.props.dispatch({
      type: 'BudgetJustifyModel/setState',
      payload: {
        budgetJustifyPage: page,
      }
    });

    this.props.dispatch({
      type: 'BudgetJustifyModel/getBudgetJustifyList',
    });
  }

  passCheck= (rec, flag) => { // flag=true,通过审核
    const {
      userType,
  } = this.props;
    this.props.dispatch({
      type: 'BudgetJustifyModel/changeCheckStatus',
      payload: {
        id: rec.adjustId,
        auditType: userType,
        auditContent: flag,
      }
    });
  }

  render() {
    const {
      budgetJustifyList,
      loading,
      showJustifyDetail,
      budgetJustifyPage,
      budgetJustifyNum,
      budgetJustifyCompareList,
      budgetJustifyComparePage,
      budgetJustifyCompareyNum,
    } = this.props;

    const columns = [{
      title: '项目编码',
      dataIndex: 'projectNo',
      key: 'projectNo',
    }, {
      title: '专业名称',
      dataIndex: 'majorName',
      key: 'majorName',
    }, {
      title: '状态',
      dataIndex: 'status',
      key: 'status',
      render: (status) => {
        let str = '';
        switch (status) {
          case '0':
            str = '填报人已提交';
            break;
          case '1':
            str = '财务已审核通过';
            break;
          case '3':
            str = '负责人已审核通过';
            break;
          case '5':
            str = '教育部已审核通过';
            break;
          default:
            break;
        }
        return str;
      }
    }, {
      title: '填报人提交时间',
      dataIndex: 'reportTime',
      key: 'reportTime',
      render: (time) => {
        let str = '-';
        if (time) {
          str = moment(time).format('YYYY-MM-DD HH:mm;ss');
        }
        return str;
      }
    }, {
      title: '财务部门负责人审核时间',
      dataIndex: 'financeAuditTime',
      key: 'financeAuditTime',
      render: (time) => {
        let str = '-';
        if (time) {
          str = moment(time).format('YYYY-MM-DD HH:mm;ss');
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
          str = moment(time).format('YYYY-MM-DD HH:mm;ss');
        }
        return str;
      }
    }, {
      title: '教育部审核时间',
      dataIndex: 'conutryAuditTime',
      key: 'conutryAuditTime',
      render: (time) => {
        let str = '-';
        if (time) {
          str = moment(time).format('YYYY-MM-DD HH:mm;ss');
        }
        return str;
      }
    }, {
      title: '审核意见',
      dataIndex: 'auditOpinion',
      key: 'auditOpinion',
    }, {
      title: '操作',
      key: 'operat',
      render: (text, rec) => (
        <span>
          <LinkBtn onClick={this.passCheck.bind(this, rec, '0')}>返回上一级</LinkBtn>
          <LinkBtn onClick={this.passCheck.bind(this, rec, '1')}>通过审核</LinkBtn>
        </span>
        )
    }];

    const recPageConfig = {
      className: 'ant-table-pagination',
      total: budgetJustifyNum,
      current: budgetJustifyPage,
      pageSize: 20,
      onChange: this.onJustifyListPageChange,
    };

    return (
      <Card title="预算修改申请情况一览表">
        <Table
          title={() => '预算修改申请情况一览表'}
          size="small"
          columns={columns}
          dataSource={budgetJustifyList}
          loading={loading}
          bordered
          scroll={{ x: '140', y: 300 }}
          rowKey={record => record.adjustId}
          pagination={recPageConfig}
        />
      </Card>
    );
  }
}

function mapStateToProps(state) {
  const {
    projectInfo,
    projectNo,
    userType,
   } = state.baseModel;
  const {
      loading,
      showJustifyDetail,
      budgetJustifyList,
      budgetJustifyPage,
      budgetJustifyNum,
      budgetJustifyCompareList,
      budgetJustifyComparePage,
      budgetJustifyCompareyNum,
     } = state.BudgetJustifyModel;
  return {
    loading,
    projectNo,
    userType,
    projectInfo,
    budgetJustifyList,
    showJustifyDetail,
    budgetJustifyPage,
    budgetJustifyNum,
    budgetJustifyCompareList,
    budgetJustifyComparePage,
    budgetJustifyCompareyNum,
  };
}

export default connect(mapStateToProps)(BudgetJustifyCheck);
