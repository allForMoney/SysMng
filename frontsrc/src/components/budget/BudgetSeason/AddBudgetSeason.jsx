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
    year: 2017,
    season: 1,
    steps: 0,
    editable: this.props.userType === 'inputer', // 是否可编辑
    showCheckBtn: this.props.userType === 'finan' || this.props.userType === 'manager', // 是否展示审批菜单

  }

  onYearChange= (value) => {
    this.setState({ year: value });
  }
  
  onSeaSonChange= (value) => {
    const { year } = this.state;
    // this.props.dispatch   请求获取数据
  }

  doCheck= (flag) => {// flag=true,通过审核
    
  }

  goNext = () => {
    this.setState({ steps: 1 });
  }

  submit = () => {
    // 提交修改
  }

  render() {
    const {
      projectName,
      projectNo,
    } = this.props;
    const YearSelection = [];
    for (let i = 2015; i < 2018; i++) {
      YearSelection.push(<Option key={i}>{i}</Option>);
    }
    const SeasonSelection = [];
    for (let i = 1; i < 5; i++) {
      SeasonSelection.push(<Option key={i}>{i}</Option>);
    }

    const { steps, editable, showCheckBtn } = this.state;

    return (
      <FrameContent>
        <Card title={`收入预算执行情况[编号: ${projectNo},名称:${projectName}]`}>
          { steps === 0 &&
            <Row className="">
              <Select
                placeholder="Please select"
                size={'default'}
                defaultValue={['2017']}
                onChange={this.onYearChange}
                style={{ width: '20%', margin: 20 }}
              >
                {YearSelection}
              </Select>
              <Select
                placeholder="Please select"
                defaultValue={['1']}
                onChange={this.onSeaSonChange}
                style={{ width: '10%' }}
              >
                {SeasonSelection}
              </Select>
              <BudgetSeasonIncome
                editable={editable}
              />
              <Col className={styles.btnContainer}>
                <Button className={styles.btnClass} type="primary" onClick={this.goNext}>下一步</Button>
              </Col>
            </Row>
          }
          { steps === 1 &&
            <div className="">
                {showCheckBtn &&
                <Col className={styles.btnContainer}>
                  <Button className={styles.btnClass} type="primary" onClick={this.doCheck.bind(this, false)}>返回上一级</Button>
                  <Button className={styles.btnClass} type="primary" onClick={this.doCheck.bind(this, true)}>通过审核</Button>
                </Col>
                }
              <BudgetSeasonOutcome
                editable={editable}
              />
              <Col className={styles.btnContainer}>
                <Button className={styles.btnClass} type="primary" onClick={() => this.setState({ steps: 0 })}>返回</Button>
                {!showCheckBtn &&
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
    projectId } = state.baseModel;
  return {
    budgetMsgList,
    userType,
    loading,
    budgetMsgNum,
    budgetMsgage,
    projectId };
}

export default connect(mapStateToProps)(BudgetSeason);
