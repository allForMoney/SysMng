/**
 * 项目预算表
 */
import React from 'react';
import { connect } from 'dva';
import {
  Card,
  Button,
 } from 'antd';
import { routerRedux } from 'dva/router';
import FrameContent from '../common/FrameContent';
import ProjectBudgetTable from './ProjectBudgetTable';

class ProjectBudget extends React.Component {
  componentDidMount = () => {
    this.props.dispatch({
      type: 'budgetModel/getBudgetProjectList',
    });
  }
  pageChangeHandler = () => {
  }
  render() {
    const { budgetProjectList, projectTotal, projectPage, projectName, loading } = this.props;

    const title = `项目预算表 [${projectName}]`;

    return (
      <FrameContent>
        <Card title={title}>
          <p>
            该信息为只读信息，如有疑义，请
            <Button
              onClick={
              () => this.props.dispatch({
                type: 'baseModel/setState',
                payload: {
                  showMsgModal: true,
                  msgType: 'budget'
                }
              })
              }
            >留言</Button>
            。 预算一经确定，原则上不予调整。确须调整，请点击
            <Button
              onClick={() => {
                this.props.dispatch(routerRedux.push({
                  pathname: '/budget/justify',
                }));
              }
              }
            >预算调整</Button>办理手续。
          </p>
          <ProjectBudgetTable
            tableTitle={'项目预算表'}
            totalNum={projectTotal}
            onPageChange={this.pageChangeHandler}
            currentPage={projectPage}
            dataList={budgetProjectList}
            loading={loading}
          />
        </Card>
      </FrameContent>
    );
  }
}

function mapStateToProps(state) {
  const { projectList, projectTotal, projectPage, projectName, projectNo, projectId } = state.baseModel;
  const { budgetProjectList } = state.budgetModel;
  return {
    loading: state.loading.models.baseModel,
    projectList,
    projectTotal,
    projectPage,
    projectName,
    projectId,
    budgetProjectList,
    projectNo,
  };
}

export default connect(mapStateToProps)(ProjectBudget);
