/**
 * 预算导入记录查询
 */
import { connect } from 'dva';
import React from 'react';
import ImportRecord from '../components/import/ImportRecord';

function AchivemportRecord(props) {
  return (
    <ImportRecord
      dataSouce={props.achiveRecList}
      pageNum={props.achiveRecPageNum}
      pageTotal={props.achiveRecPageTotal}
      tableTitel={"业绩导入记录"}
      loading={props.dataSource}
    />
  );
}

function mapStateToProps(state) {
  const {
    achiveRecList,
    achiveRecPageNum,
    achiveRecPageTotal,
    achiveRecPageSize,
    loading,
  } = state.ImportData;
  return {
    achiveRecList,
    achiveRecPageNum,
    achiveRecPageTotal,
    loading,
    achiveRecPageSize,
  };
}

export default connect(mapStateToProps)(AchivemportRecord);
