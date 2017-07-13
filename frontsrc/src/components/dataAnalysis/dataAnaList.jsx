/**
 * 项目列表页面,用来为管理员和教育厅提供 查看所有项目功能
 */
import { connect } from 'dva';
import React from 'react';
import { Form, Input, Button, Select, Card, Table, Upload, message, Modal, Popconfirm } from 'antd';
import FrameContent from '../common/FrameContent';

const Option = Select.Option;

class dataAnaList extends React.Component {
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
      type: 'dataAnalysisModel/getDataAnaList',
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
      type: 'dataAnalysisModel/getDataAnaList',
    });
  }

  handleChange = (value) => {
    this.props.dispatch({
      type: 'dataAnalysisModel/setState',
      payload: {
        analySisPage: 1,
        year: value,
      },
    });
    this.props.dispatch({
      type: 'dataAnalysisModel/getDataAnaList',
    });
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
          dataIndex: 'schoolName',
        }, {
          title: '部本专项资金',
          dataIndex: 'schoolName',
        }, {
          title: '举办方或地方财政投入资金 ',
          dataIndex: 'schoolName',
        }, {
          title: '行业企业支持资金 ',
          dataIndex: 'schoolName',
        }, {
          title: '相关院校自筹资金',
          dataIndex: 'schoolName',
        }]
      }, {
        title: '项目预算执行情况',
        children: [{
          title: '实际投入（元',
          children: [{
            title: '总额',
            dataIndex: 'schoolName'
          }, {
            title: '到位率',
            dataIndex: 'schoolName',
          }, {
            title: '部本专项资金',
            children: [{
              title: '总额',
              dataIndex: 'schoolName'
            }, {
              title: '到位率',
              dataIndex: 'schoolName',
            }],
          }, {
            title: '举办方或地方财政投入资金',
            children: [{
              title: '总额',
              dataIndex: 'schoolName'
            }, {
              title: '到位率',
              dataIndex: 'schoolName',
            }],
          }, {
            title: '行业企业支持资金',
            children: [{
              title: '总额',
              dataIndex: 'schoolName'
            }, {
              title: '到位率',
              dataIndex: 'schoolName',
            }],
          }, {
            title: '相关院校自筹资金',
            children: [{
              title: '总额',
              dataIndex: 'schoolName'
            }, {
              title: '到位率',
              dataIndex: 'schoolName',
            }],
          }],
        }, {
          title: '实际支出（元）',
          children: [{
            title: '总额',
            dataIndex: 'schoolName'
          }, {
            title: '到位率',
            dataIndex: 'schoolName',
          }, {
            title: '部本专项资金',
            children: [{
              title: '总额',
              dataIndex: 'schoolName'
            }, {
              title: '到位率',
              dataIndex: 'schoolName',
            }],
          }, {
            title: '举办方或地方财政投入资金',
            children: [{
              title: '总额',
              dataIndex: 'schoolName'
            }, {
              title: '到位率',
              dataIndex: 'schoolName',
            }],
          }, {
            title: '行业企业支持资金',
            children: [{
              title: '总额',
              dataIndex: 'schoolName'
            }, {
              title: '到位率',
              dataIndex: 'schoolName',
            }],
          }, {
            title: '相关院校自筹资金',
            children: [{
              title: '总额',
              dataIndex: 'schoolName'
            }, {
              title: '到位率',
              dataIndex: 'schoolName',
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
      <FrameContent>
        <Card title="预算执行情况一览表">
          <Select defaultValue="2017" style={{ width: 120 }} onChange={this.handleChange}>
            <Option value="2017">2017</Option>
            <Option value="2016">2016</Option>
            <Option value="2015" >2015</Option>
            <Option value="2014">2014</Option>
          </Select>
          <Table
            columns={columns}
            dataSource={analySisList}
            loading={loading}
            rowKey={record => record.name}
            pagination={pageConfig}
          />
        </Card>
      </FrameContent>
    );
  }
}

function mapStateToProps(state) {
  const {
    analySisList,
    analySisPage,
    analySisNum,
    loading,
  } = state.dataAnalysisModel;
  return {
    analySisList,
    analySisPage,
    analySisNum,
    loading,
  };
}

export default connect(mapStateToProps)(dataAnaList);
