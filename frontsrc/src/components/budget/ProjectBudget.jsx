import React from 'react';
import { connect } from 'dva';
import {
  Card,
  Button,
 } from 'antd';
import { routerRedux } from 'dva/router';
import FrameContent from '../common/FrameContent';
import ProjectBudgetTable from './ProjectBudgetTable';

function ProjectBudget(props) {
  function pageChangeHandler() {

  }
  const { projectList, projectTotal, projectPage, projectName, loading } = props;
  
  const title = `项目预算表${projectName}`;
  return (
    <FrameContent>
      <Card title={title}>
        <p>
           该信息为只读信息，如有疑义，请
          <Button
            onClick={
            () => props.dispatch({
              type: 'baseModel/setState',
              payload: { showMsgModal: true }
            })
            }
          >留言</Button>
          。 预算一经确定，原则上不予调整。确须调整，请点击
          <Button
            onClick={() => {
              props.dispatch(routerRedux.push({
                pathname: '/budget/justify',
              }));
            }
            }
          >预算调整</Button>办理手续。
        </p>
        <ProjectBudgetTable
          tableTitle={'项目预算表'}
          totalNum={projectTotal}
          onPageChange={pageChangeHandler}
          currentPage={projectPage}
          dataList={projectList}
          loading={loading}
        />
      </Card>
    </FrameContent>
  );
}
function mapStateToProps(state) {
  const { projectList, projectTotal, projectPage, projectName } = state.baseModel;
  return {
    loading: state.loading.models.baseModel,
    projectList,
    projectTotal,
    projectPage,
    projectName,
  };
}

export default connect(mapStateToProps)(ProjectBudget);
