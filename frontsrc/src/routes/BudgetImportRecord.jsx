/**
 * 预算导入记录查询
 */
import { connect } from 'dva';
import React from 'react';
import ImportRecord from '../components/import/ImportRecord';

function BudgetImportRecord(props) {
  return (
    <ImportRecord
      dataSouce={props.budgetRecList}
      pageNum={props.budgetRecPageNum}
      pageTotal={props.budgetRecPageTotal}
      tableTitel={"预算导入记录"}
      loading={props.dataSource}
    />
  );
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
