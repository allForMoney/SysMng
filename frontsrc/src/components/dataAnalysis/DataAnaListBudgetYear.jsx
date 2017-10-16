/**
 * 项目列表页面,用来为管理员和教育厅提供 查看所有项目功能
 */
import { connect } from 'dva';
import React from 'react';
import { Form, Input, Button, Select, Card, Table, Upload, message, Modal, Popconfirm } from 'antd';
import FrameContent from '../common/FrameContent';

const Option = Select.Option;

class DataAnaListBudgetYear extends React.Component {
  state = {

  }
  componentDidMount() {
    this.props.dispatch({
      type: 'dataAnalysisModel/setState',
      payload: {
        analySisPage: 1,
      },
    });
    this.props.dispatch({
      type: 'dataAnalysisModel/getDataAnaListBudgetYear',
    });
  }

  onPageChanged = (page) => {
    this.props.dispatch({
      type: 'dataAnalysisModel/setState',
      payload: {
        analySisPage: page,
      },
    });
    this.props.dispatch({
      type: 'dataAnalysisModel/getDataAnaListBudgetYear',
    });
  }

  handleChange = (value) => {
    this.props.dispatch({
      type: 'dataAnalysisModel/setState',
      payload: {
        analySisPage: 1,
        projectYear: value,
      },
    });
    this.props.dispatch({
      type: 'dataAnalysisModel/getDataAnaListBudgetYear',
    });
  }
  // 导出
  expertExcel=() => {
    const { projectYear } = this.props;
    const url = `/dataanalyse/byyear/download?projectYear=${projectYear}`;
    window.open(url);
  }

  render() {
    const {
    analySisList,
    analySisPage,
    analySisNum,
    loading,
    } = this.props;

    const columns =
      [{
        title: '项目编号',
        dataIndex: 'projectNo',
      }, {
        title: '项目名称',
        dataIndex: 'majorName',
      }, {
        title: '主持院校',
        dataIndex: 'schoolName',
      }, {
        title: '项目预算',
        children: [{
          title: '总额',
          dataIndex: 'budgetTotal',
        }, {
          title: '部本专项资金',
          dataIndex: 'budgetCountry',
        }, {
          title: '举办方或地方财政投入资金 ',
          dataIndex: 'budgetLocal',
        }, {
          title: '行业企业支持资金 ',
          dataIndex: 'budgetEnterprise',
        }, {
          title: '相关院校自筹资金',
          dataIndex: 'budgetUniversity',
        }]
      }, {
        title: '项目预算执行情况',
        children: [{
          title: '实际投入（元',
          children: [{
            title: '总额',
            dataIndex: 'fundsInTotal'
          }, {
            title: '到位率',
            dataIndex: 'totalInPercent',
          }, {
            title: '部本专项资金',
            children: [{
              title: '总额',
              dataIndex: 'fundsInCountry'
            }, {
              title: '到位率',
              dataIndex: 'countryInPercent',
            }],
          }, {
            title: '举办方或地方财政投入资金',
            children: [{
              title: '总额',
              dataIndex: 'fundsInLocal'
            }, {
              title: '到位率',
              dataIndex: 'localInPercent',
            }],
          }, {
            title: '行业企业支持资金',
            children: [{
              title: '总额',
              dataIndex: 'fundsInEnterprise'
            }, {
              title: '到位率',
              dataIndex: 'enterpriseInPercent',
            }],
          }, {
            title: '相关院校自筹资金',
            children: [{
              title: '总额',
              dataIndex: 'fundsInUniversity'
            }, {
              title: '到位率',
              dataIndex: 'universityInPercent',
            }],
          }],
        }, {
          title: '实际支出（元）',
          children: [{
            title: '总额',
            dataIndex: 'fundsOutTotal'
          }, {
            title: '到位率',
            dataIndex: 'totalOutPercent',
          }, {
            title: '部本专项资金',
            children: [{
              title: '总额',
              dataIndex: 'fundsOutCountry'
            }, {
              title: '到位率',
              dataIndex: 'countryOutPercent',
            }],
          }, {
            title: '举办方或地方财政投入资金',
            children: [{
              title: '总额',
              dataIndex: 'fundsOutLocal'
            }, {
              title: '到位率',
              dataIndex: 'localOutPercent',
            }],
          }, {
            title: '行业企业支持资金',
            children: [{
              title: '总额',
              dataIndex: 'fundsOutEnterprise'
            }, {
              title: '到位率',
              dataIndex: 'enterpriseOutPercent',
            }],
          }, {
            title: '相关院校自筹资金',
            children: [{
              title: '总额',
              dataIndex: 'fundsOutUniversity'
            }, {
              title: '到位率',
              dataIndex: 'universityOutPercent',
            }],
          }],
        }]
      }];


    const pageConfig = {
      className: 'ant-table-pagination',
      total: analySisNum,
      current: analySisPage,
      pageSize: 20,
      onChange: this.onPageChanged,
    };

    return (

      <Card title="预算执行情况一览表">
          立项年度
          <Select defaultValue="2017" style={{ width: 120 }} onChange={this.handleChange}>
            <Option value="2017">2017</Option>
            <Option value="2016">2016</Option>
            <Option value="2015" >2015</Option>
            <Option value="2014">2014</Option>
          </Select>
        <Button onClick={this.expertExcel}>导出Excel</Button>
        <Table
          columns={columns}
          dataSource={analySisList}
          bordered
          scroll={{ x: '140', y: 300 }}

          loading={loading}
          rowKey={record => record.name}
          pagination={pageConfig}
          style={{ marginTop: 15 }}
        />
      </Card>

    );
  }
}

function mapStateToProps(state) {
  const {
    analySisList,
    analySisPage,
    analySisNum,
    projectYear,
    loading,
  } = state.dataAnalysisModel;
  return {
    analySisList,
    analySisPage,
    projectYear,
    analySisNum,
    loading,
  };
}

export default connect(mapStateToProps)(DataAnaListBudgetYear);
