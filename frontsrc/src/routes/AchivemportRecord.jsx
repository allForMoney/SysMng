/**
 * 预算导入记录查询
 */
import { connect } from 'dva';
import React from 'react';
import ImportRecord from '../components/import/ImportRecord';
import { routerRedux } from 'dva/router';

class AchivemportRecord extends React.Component {
  componentDidMount = () => {
    this.props.dispatch({
      type: 'ImportData/setState',
      payload: {
        importFileType: 'jixiao'
      }
    });
  }

  showDetail=(record) => {
    this.props.dispatch({
      type: 'baseModel/setState',
      payload: {
        projectNo: record.projectNo,
        projectInfo: {
          id: record.projectId,
          projectNo: record.projectNo,
          majorName: record.majorName,
        },
        projectName: record.majorName,
      }
    });
    this.props.dispatch(routerRedux.push({
      pathname: '/achive/add',
    }));
  }

  render() {
    const {
      allImportData,
    allImportPage,
    allImportNum,
    dispatch,
    loading
    } = this.props;
    return (
      <ImportRecord
        dataSouce={allImportData}
        pageNum={allImportPage}
        pageTotal={allImportNum}
        tableTitel={'业绩导入记录'}
        actionFunc={this.showDetail}
        loading={loading}
        dispatch={dispatch}
      />
    );
  }
}

function mapStateToProps(state) {
  const {
    allImportData,
    allImportPage,
    allImportNum,
    loading,
  } = state.ImportData;
  return {
    allImportData,
    allImportPage,
    allImportNum,
    loading
  };
}

export default connect(mapStateToProps)(AchivemportRecord);
