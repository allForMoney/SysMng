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
  state={
    showJustifyDetail: true,
  }

  onJustifyListPageChange= () => {
    this.cancelMsg();
  }

  onJustifyListDetailChange= () => {
    this.cancelMsg();
  }

  render() {
    const {
      justifyList,
      loading,
      justifyListNum,
      justifyListPage,
      justifyDetailList,
      justifyDetailNum,
      justifyDetailPage,
    } = this.props;
    const {
      showJustifyDetail,
     } = this.state;

    const columns = [{
      title: '项目编码',
      dataIndex: 'index',
      key: 'index',
    }, {
      title: '专业名称',
      dataIndex: 'name',
      key: 'name',
    }, {
      title: '状态',
      dataIndex: 'status',
      key: 'status',
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
      title: '教育部审核时间',
      dataIndex: 'time4',
      key: 'time4',
    }, {
      title: '审核意见',
      dataIndex: 'opi',
      key: 'opi',
    }, {
      title: '操作',
      key: 'operat',
      render: rec => <LinkBtn onClick={this.showJustifyDetail.bind(this, rec)}>查看</LinkBtn>
    }];
    const pageConfig = {
      className: 'ant-table-pagination',
      total: justifyListNum,
      current: justifyListPage,
      pageSize: 20,
      onChange: this.onJustifyListPageChange,
    };

    return (
      <FrameContent>
        <Card title="预算修改申请情况一览表">
          {showJustifyDetail &&
            <ProjectBudgetTable
              tableTitle={'预算变化内容'}
              totalNum={justifyDetailNum}
              onPageChange={this.onJustifyListDetailChange}
              currentPage={justifyDetailPage}
              dataList={justifyDetailList}
              loading={loading}
            />
          }
          {
            !showJustifyDetail &&
            <Table
              title={() => '预算修改申请情况一览表'}
              columns={columns}
              dataSource={justifyList}
              loading={loading}
              rowKey={record => record.id}
              pagination={pageConfig}
            />
          }
        </Card>
      </FrameContent>
    );
  }
}

function mapStateToProps(state) {
  const {
      justifyList,
      loading,
      justifyListNum,
      justifyListPage,
      justifyDetailList,
      justifyDetailNum,
      justifyDetailPage,
    projectId } = state.baseModel;
  return {
    justifyList,
    loading,
    justifyListNum,
    justifyListPage,
    justifyDetailList,
    justifyDetailNum,
    justifyDetailPage,
    projectId };
}

export default connect(mapStateToProps)(BudgetJustifyRec);
