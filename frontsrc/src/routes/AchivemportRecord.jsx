/**
 * 预算导入记录查询
 */
import { connect } from 'dva';
import React from 'react';
import ImportRecord from '../components/import/ImportRecord';

class AchivemportRecord extends React.Component {
  componentDidMount = () => {
    this.props.dispatch({
      type: 'ImportData/getAllAchiveRec'
    });
  }
  render() {
    const {
        achiveRecList,
        achiveRecPageNum,
        achiveRecPageTotal,
        loading,
        achiveRecPageSize,
    } = this.props;
    return (
      <ImportRecord
        dataSouce={achiveRecList}
        pageNum={achiveRecPageNum}
        pageTotal={achiveRecPageTotal}
        tableTitel={"业绩导入记录"}
        loading={loading}
      />
    );
  }
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
