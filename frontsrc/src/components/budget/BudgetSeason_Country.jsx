/**
 * 教育部看的预算执行季报
 * 先看所有项目,然后单个项目BudgetSeason_Country
 */
import React from 'react';
import { connect } from 'dva';
import { Table, Card } from 'antd';
import { routerRedux } from 'dva/router';
import FrameContent from '../common/FrameContent';
import ProjectList from '../project/ProjectList';

class BudgetSeason_Country extends React.Component {
  state= {
  }

  showSeasonDetail= (rec) => {
    const { id } = rec;
    console.log(id);
    this.props.dispatch({
      type: 'baseModel/setState',
      payload: {
        projectId: id,
        projectInfo: rec,
      }
    });
    this.props.dispatch(routerRedux.push(
     { pathname: '/budget/budgetSeasonList' }
    ));
  }

  render() {
    const {
      loading,
      projectList,
      projectListNum,
      projectListPage,
      dispatch,
    } = this.props;
    return (
      <FrameContent>
        <Card title="预算执行情况季报">
          <ProjectList
            loading={loading}
            projectList={projectList}
            projectListNum={projectListNum}
            projectListPage={projectListPage}
            actionFunc={this.showSeasonDetail}
            dispatch={dispatch}
          />
        </Card>
      </FrameContent>
    );
  }


}

function mapStateToProps(state) {
  const {
      userType,
      projectId,
      projectName,
      projectInfo,
  } = state.baseModel;
  const {
    projectList,
    projectListNum,
    projectListPage,
  } = state.ProjectModel;

  const {
    budgetRecordList,
    budgetRecordPage,
    budgetRecordNum,
  } = state.budgetModel;

  return {
    userType,
    projectInfo,
    projectList,
    projectListNum,
    projectListPage,
    projectId,
    projectName,
    budgetRecordList,
    budgetRecordPage,
    budgetRecordNum,
  };
}

export default connect(mapStateToProps)(BudgetSeason_Country);
