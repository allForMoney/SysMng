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

class BudgetJustifyRec extends React.Component {
  componentDidMount() {
    this.props.dispatch({
      type: 'BudgetJustifyModel/setState',
      payload: { auditStatus: null }
    });

    this.props.dispatch({
      type: 'BudgetJustifyModel/getBudgetJustifyList',
    });
  }
  componentWillUnmount() {
    this.props.dispatch({
      type: 'BudgetJustifyModel/setState',
      payload: {
        showJustifyDetail: false,
      }
    });
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

  onJustifyListDetailChange= (page) => {
    this.props.dispatch({
      type: 'BudgetJustifyModel/setState',
      payload: {
        budgetJustifyComparePage: page,
      }
    });
    this.props.dispatch({
      type: 'BudgetJustifyModel/getBudgetJustifyCompareList',
    });
  }

  showJustifyCompare= (rec) => {
    this.props.dispatch({
      type: 'BudgetJustifyModel/setState',
      payload: {
        compareId: rec.adjustId,
        budgetJustifyComparePage: 1,
      }
    });

    this.props.dispatch({
      type: 'BudgetJustifyModel/getBudgetJustifyCompareList',
      payload: {
        budgetJustifyComparePage: 1,
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
      render: rec => <LinkBtn onClick={this.showJustifyCompare.bind(this, rec)}>查看</LinkBtn>
    }];

    const recPageConfig = {
      className: 'ant-table-pagination',
      total: budgetJustifyNum,
      current: budgetJustifyPage,
      pageSize: 20,
      onChange: this.onJustifyListPageChange,
    };

    return (
      <FrameContent>
        <Card title="预算修改申请情况一览表">
          {showJustifyDetail &&
            <ProjectBudgetTable
              tableTitle={'预算变化内容'}
              totalNum={budgetJustifyCompareyNum}
              onPageChange={this.onJustifyListDetailChange}
              currentPage={budgetJustifyComparePage}
              dataList={budgetJustifyCompareList}
              loading={loading}
              compare
            />
          }
          {
            !showJustifyDetail &&
            <Table
              title={() => '预算修改申请情况一览表'}
              size="small"
              columns={columns}
              dataSource={budgetJustifyList}
              loading={loading}
              rowKey={record => record.adjustId}
              pagination={recPageConfig}
            />
          }
        </Card>
      </FrameContent>
    );
  }
}

function mapStateToProps(state) {
  const {
    projectInfo,
    projectNo,
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

export default connect(mapStateToProps)(BudgetJustifyRec);
