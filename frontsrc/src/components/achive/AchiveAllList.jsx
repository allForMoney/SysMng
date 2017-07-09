
import { connect } from 'dva';
import React from 'react';
import { Form, Input, Button, Icon, Card, Table, Upload, message, Modal, Popconfirm } from 'antd';
import FrameContent from '../common/FrameContent';
import styles from '../../index.less';
import { routerRedux } from 'dva/router';

class AchiveAllList extends React.Component {
  componentDidMount= () => {
    this.props.dispatch({
      type: 'achiveModel/getAchiveAllList',
    });
  }

  showAchiveDetail=(rec) => {
    const { id } = rec;
    /** 获取当前项目 */
    this.props.dispatch({
      type: 'baseModel/getProjectInfoById',
      payload: {
        projectNo: id
      }
    });
    this.props.dispatch(routerRedux.push({
      pathname: '/achive/add',
    }));
  }
  doSearch = (e) => {
    e.preventDefault();
    const values = this.props.form.getFieldsValue();
    console.log(values);
    this.props.dispatch({
      type: 'achiveModel/setState',
      payload: {
        filterParam: values,
        achiveAllListPage: 1,
      },
    });
    this.props.dispatch({
      type: 'achiveModel/getAchiveAllList',
    });
  }


  render() {
    const {
      achiveAllList,
      achiveAllListNum,
      achiveAllListPage,
      loading,
    } = this.props;
    const { getFieldDecorator } = this.props.form;

    const pageConfig = {
      className: 'ant-table-pagination',
      total: achiveAllListNum,
      current: achiveAllListPage,
      pageSize: 20,
      onChange: this.onPageChanged,
    };

    const columns =
      [{
        title: '项目编号',
        dataIndex: 'projectNo',
      }, {
        title: '专业名称',
        dataIndex: 'majorName',
      }, {
        title: '立项年度',
        dataIndex: 'createYear',
      }, {
        title: '主持单位',
        dataIndex: 'schoolName',
      }, {
        title: '省份',
        dataIndex: 'provenceId',
      }, {
        title: '操作',
        render: (text, rec) => this.showAchiveDetail.bind(this, rec)
      }];

    return (
      <FrameContent>
        <Card title="绩效目标列表">
          <Form onSubmit={this.onSearchSubmit} layout="inline">
            <Form.Item label="项目编号 ：">
              { getFieldDecorator('projectNo', {
              })(
                <Input size="small" width="100" />
              )}
            </Form.Item>
            <Form.Item label="专业名称 ：">
              { getFieldDecorator('majorName', {
              })(
                <Input size="small" />
              )}
            </Form.Item>
            <Form.Item label="主持单位 ：">
              { getFieldDecorator('schoolName', {
              })(
                <Input size="small" />
              )}
            </Form.Item>
            <Form.Item >
              <Button size="small" type="primary" icon="search" onClick={this.doSearch}>查询</Button>
              <Button size="small" style={{ marginLeft: 5 }} type="primary" onClick={() => this.props.form.resetFields()}><Icon type="rollback" />重置</Button>
            </Form.Item>
          </Form>
          <Table
            columns={columns}
            dataSource={achiveAllList}
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
    achiveAllList,
    achiveAllListNum,
    achiveAllListPage,
    loading,
  } = state.achiveModel;
  return {
    achiveAllList,
    achiveAllListNum,
    achiveAllListPage,
    loading,
  };
}
export default connect(mapStateToProps)(Form.create({})(AchiveAllList));
