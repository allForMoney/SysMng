/**
 * 预算导入记录查询
 */
import { connect } from 'dva';
import React from 'react';
import ImportRecord from '../components/import/ImportRecord';

class BudgetImportRecord extends React.Component {
  componentDidMount = () => {
    this.props.dispatch({
      type: 'ImportData/getAllBudgetRec'
    });
  }

  render() {
    const {
      budgetRecList,
      budgetRecPageNum,
      budgetRecPageTotal,
      loading,
      budgetRecPageSize,
    } = this.props;
    return (
      <ImportRecord
        dataSouce={budgetRecList}
        pageNum={budgetRecPageNum}
        pageTotal={budgetRecPageTotal}
        tableTitel={"预算导入记录"}
        loading={loading}
      />
    );
  }
}

function mapStateToProps(state) {
  const {
    budgetRecList,
    budgetRecPageNum,
    budgetRecPageTotal,
    budgetRecPageSize,
    loading,
  } = state.ImportData;
  return {
    budgetRecList,
    budgetRecPageNum,
    budgetRecPageTotal,
    loading,
    budgetRecPageSize,
  };
}

export default connect(mapStateToProps)(BudgetImportRecord);
