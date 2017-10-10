import React from 'react';
import { connect } from 'dva';
import {
  Card,
  Select,
  Button,
  Row,
  Col,
} from 'antd';
import BudgetSeasonIncome from './BudgetSeasonIncome';
import BudgetSeasonOutcome from './BudgetSeasonOutcome';
import FrameContent from '../../common/FrameContent';
import styles from '../../../index.less';

const Option = Select.Option;

class BudgetSeason extends React.Component {
  state= {
    editable: true, // 是否可编辑
  }
  componentDidMount() {
    this.props.dispatch({
      type: 'budgetModel/getBudgetSeasonList',
    });
  }

  componentWillUnmount() {
    this.props.dispatch({
      type: 'budgetModel/SetState',
      payload: {
        projectYear: 0,
        quarterNum: 0,
        editBudgetSteps: 0,
        buggetInComeList: [],
        buggetOutComeList: [],
      }
    });
  }

  onYearChange= (value) => {
    this.props.dispatch({
      type: 'budgetModel/setState',
      payload: {
        projectYear: value,
      }
    });
  }

  onSeaSonChange= (value) => {
    this.props.dispatch({
      type: 'budgetModel/setState',
      payload: {
        quarterNum: value,
      }
    });
    this.props.dispatch({
      type: 'budgetModel/getBudgetSeasonList',
    });
  }

  doExport= () => {
    const {
      projectInfo,
      projectYear,
      quarterNum,
  } = this.props;
    const url = `./budget/report/quarterly/download?projectId=${projectInfo.id}&projectYear=${projectYear}&quarterNum=${quarterNum}`;
    window.open(url);
  }

  doCheck= (flag) => { // flag=true,通过审核
    const {
      projectInfo,
      projectYear,
      quarterNum,
      userType,
  } = this.props;
    this.props.dispatch({
      type: 'budgetModel/changeCheckStatus',
      payload: {
        projectId: projectInfo.id,
        projectYear,
        quarterNum,
        auditType: userType,
        auditContent: flag,
      }
    });
  }

  goNext = () => {
    this.props.dispatch({
      type: 'budgetModel/setState',
      payload: {
        editBudgetSteps: 1
      }
    });
  }

  submit = () => {
    this.props.dispatch({
      type: 'budgetModel/updateSeasonBudget',
    });
  }

  getCheckVisible = (userType, auditStatus) => {
    let cancelVisible = false;
    let passVisible = false;
    if (userType === 'finace') {
      if (auditStatus === '0') {
        passVisible = true;
      }
      if (auditStatus === '1') {
        cancelVisible = true;
      }
    }
    if (userType === 'school') {
      if (auditStatus === '1') {
        passVisible = true;
      }
      if (auditStatus === '3') {
        cancelVisible = true;
      }
    }
    if (userType === 'country') {
      if (auditStatus === '3') {
        passVisible = true;
      }
      if (auditStatus === '5') {
        cancelVisible = true;
      }
    }

    return {
      cancelVisible,
      passVisible
    };
  }

  render() {
    const {
      projectInfo,
      editBudgetSteps,
      buggetInComeList,
      buggetOutComeList,
      dispatch,
      projectYear,
      quarterNum,
      userType,
      auditStatus,
      showSeasonExport,
    } = this.props;
    const YearSelection = [];
    for (let i = 2015; i < 2019; i++) {
      YearSelection.push(<Option key={i}>{i}</Option>);
    }
    const SeasonSelection = [];
    for (let i = 1; i < 5; i++) {
      SeasonSelection.push(<Option key={i}>{`第${i}季度`}</Option>);
    }

    const editable = this.props.userType === 'inputer';
    const { cancelVisible, passVisible } = this.getCheckVisible(userType, auditStatus);

    return (
      <FrameContent>
        <Card title={`预算执行季报[编号: ${projectInfo.projectNo},名称:${projectInfo.majorName}]`}>
          { editBudgetSteps === 0 &&
            <Row className="">
              年度
              <Select
                placeholder="选择年度"
                size={'default'}
                onChange={this.onYearChange}
                style={{ width: '20%', margin: 20 }}
              >
                {YearSelection}
              </Select>
              季度
              <Select
                placeholder="选择季度"
                onChange={this.onSeaSonChange}
                style={{ width: '10%' }}
              >
                {SeasonSelection}
              </Select>
              {projectYear && quarterNum &&
                <div>
                  <BudgetSeasonIncome
                    editable={editable}
                    dispatch={dispatch}
                    buggetInComeList={buggetInComeList}
                  />
                  <Col className={styles.btnContainer}>
                    <Button className={styles.btnClass} type="primary" onClick={this.goNext}>下一步</Button>
                  </Col>
                </div>
              }
            </Row>
          }
          { editBudgetSteps === 1 &&
            <div className="">
                {cancelVisible &&
                  <Button className={styles.btnClass} type="primary" onClick={this.doCheck.bind(this, '0')}>返回上一级</Button>
                }
                {passVisible &&
                  <Button className={styles.btnClass} type="primary" onClick={this.doCheck.bind(this, '1')}>通过审核</Button>
                }
                {showSeasonExport &&
                  <Button className={styles.btnClass} type="primary" onClick={this.doExport}>导出Excel文件</Button>
                }

              <BudgetSeasonOutcome
                editable={editable}
                buggetOutComeList={buggetOutComeList}
                dispatch={dispatch}
              />
              <Col className={styles.btnContainer}>
                <Button
                  className={styles.btnClass}
                  type="primary"
                  onClick={() => {
                    this.props.dispatch({
                      type: 'budgetModel/setState',
                      payload: {
                        editBudgetSteps: 0
                      }
                    });
                  }
                  }
                >
                  返回
                </Button>
                {editable &&
                  <Button className={styles.btnClass} type="primary" onClick={this.submit}>保存</Button>
                }
              </Col>
            </div>
          }
        </Card>
      </FrameContent>
    );
  }
}

function mapStateToProps(state) {
  const {
      budgetMsgList,
      userType,
      loading,
      budgetMsgNum,
      budgetMsgage,
      projectId,
      projectInfo,
  } = state.baseModel;
  const {
    editBudgetSteps,
    buggetInComeList,
    buggetOutComeList,
    projectYear,
    quarterNum,
    auditStatus,
    showSeasonExport,
    } = state.budgetModel;
  return {
    budgetMsgList,
    userType,
    projectInfo,
    showSeasonExport,
    loading,
    budgetMsgNum,
    budgetMsgage,
    editBudgetSteps,
    buggetInComeList,
    projectYear,
    quarterNum,
    auditStatus,
    buggetOutComeList,
    projectId
  };
}

export default connect(mapStateToProps)(BudgetSeason);
