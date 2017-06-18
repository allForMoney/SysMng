import React from 'react';
import { connect } from 'dva';
import {
  Table,
  Card,
  Select,
} from 'antd';
import BudgetSeasonIncome from './BudgetSeasonIncome';
import BudgetSeasonOutcome from './BudgetSeasonOutcome';
import FrameContent from '../../common/FrameContent';

const Option = Select.Option;

class BudgetSeason extends React.Component {
  state = {
    year: 2017,
    season: 1,

  }

  onYearChange= () => {
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

    return (
      <FrameContent>
        <Card title={`收入预算执行情况[编号: ${projectNo},名称:${projectName}]`}>
          <Select
            placeholder="Please select"
            size={'default'}
            defaultValue={['2017']}
            onChange={this.onYearChange}
            style={{ width: '20%' }}
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
            editable
          />
          <BudgetSeasonOutcome
            editable
          />
        </Card>
      </FrameContent>
    );
  }
}

function mapStateToProps(state) {
  const {
      budgetMsgList,
      loading,
      budgetMsgNum,
      budgetMsgage,
    projectId } = state.baseModel;
  return {
    budgetMsgList,
    loading,
    budgetMsgNum,
    budgetMsgage,
    projectId };
}

export default connect(mapStateToProps)(BudgetSeason);
