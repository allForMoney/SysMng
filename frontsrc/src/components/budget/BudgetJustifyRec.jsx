import React from 'react';
import { connect } from 'dva';
import {
  Card,
  Table,
} from 'antd';

import FrameContent from '../common/FrameContent';
import LinkBtn from '../common/LinkBtn';
import ProjectBudgetTable from './ProjectBudgetTable';

class BudgetJustifyRec extends React.Component {
  componentDidMount() {
    this.props.dispatch({
      type: 'BudgetJustifyModel/getBudgetJustifyList',
      payload: {
        budgetJustifyPage: 0,
      }
    });
  }

  onJustifyListPageChange= (page) => {
    this.props.dispatch({
      type: 'BudgetJustifyModel/getBudgetJustifyList',
      payload: {
        budgetJustifyPage: page,
      }
    });
  }

  onJustifyListDetailChange= (page) => {
    this.props.dispatch({
      type: 'BudgetJustifyModel/getBudgetJustifyCompareList',
      payload: {
        budgetJustifyComparePage: page,
      }
    });
  }

  showJustifyCompare= (rec) => {
    this.props.dispatch({
      type: 'BudgetJustifyModel/setState',
      payload: {
        compareId: rec.id,
        budgetJustifyComparePage: 1,
      }
    });
    
    this.props.dispatch({
      type: 'BudgetJustifyModel/getBudgetJustifyCompareList',
      payload: {
        budgetJustifyComparePage: page,
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
    }, {
      title: '填报人提交时间',
      dataIndex: 'reportTime',
      key: 'reportTime',
    }, {
      title: '财务部门负责人审核时间',
      dataIndex: 'financeAuditTime',
      key: 'financeAuditTime',
    }, {
      title: '项目负责人审核时间',
      dataIndex: 'schoolAuditTime',
      key: 'schoolAuditTime',
    }, {
      title: '教育部审核时间',
      dataIndex: 'conutryAuditTime',
      key: 'conutryAuditTime',
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
            />
          }
          {
            !showJustifyDetail &&
            <Table
              title={() => '预算修改申请情况一览表'}
              columns={columns}
              dataSource={budgetJustifyList}
              loading={loading}
              rowKey={record => record.id}
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
