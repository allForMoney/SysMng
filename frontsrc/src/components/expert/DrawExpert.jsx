/**
 * 教育部看的预算执行季报 结果
 * 先看所有项目,然后单个项目BudgetSeason_Country
 */
import React from 'react';
import { connect } from 'dva';
import { Table, Card } from 'antd';
import { routerRedux } from 'dva/router';
import FrameContent from '../common/FrameContent';
import ProjectList from '../project/ProjectList';

class DrawExpert extends React.Component {
  state= {
    editRec: {}
  }

  drawExpert= (rec) => {
    const { id } = rec;
    this.setState({
      editRec: rec
    });

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

      <Card title="预算执行情况季报">
        <ProjectList
          loading={loading}
          projectList={projectList}
          projectListNum={projectListNum}
          projectListPage={projectListPage}
          actionFunc={this.drawExpert}
          dispatch={dispatch}
          actionName={'抽取专家'}
        />
      </Card>

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

export default connect(mapStateToProps)(DrawExpert);
