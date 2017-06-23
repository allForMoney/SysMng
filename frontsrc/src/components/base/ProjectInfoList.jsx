import React from 'react';
import { Form, Input, Button, Icon, Card, Table } from 'antd';
import FrameContent from '../common/FrameContent';
import styles from '../../index.less';
import { connect } from 'dva';

class ProjectInfoList extends React.Component {
  state = {
    selectedRowKeys: [],
  }

  doSearch = (e) => {
    e.preventDefault();
    const values = this.props.form.getFieldsValue();
    console.log(values);
    // this.props.dispatch({
    //   type: `${CONTENT_NAMESPACE}/${FETCH_CONTENTS}`,
    //   payload: { pageNo: 1, title },
    // });
  }

  addNew =() => {

  }

  editProject =() => {

  }
  deletePro =() => {

  }

  importPro =() => {

  }

  render() {
    const {
      loading,
      budgetMsgNum,
      budgetMsgage,
    } = this.props;
    const { getFieldDecorator } = this.props.form;

    const columns = [{
      title: 'Name',
      dataIndex: 'name',
      render: text => <a href="#">{text}</a>,
    }, {
      title: 'Age',
      dataIndex: 'age',
    }, {
      title: 'Address',
      dataIndex: 'address',
    }];
    const data = [{
      key: '1',
      name: 'John Brown',
      age: 32,
      address: 'New York No. 1 Lake Park',
    }, {
      key: '2',
      name: 'Jim Green',
      age: 42,
      address: 'London No. 1 Lake Park',
    }, {
      key: '3',
      name: 'Joe Black',
      age: 32,
      address: 'Sidney No. 1 Lake Park',
    }, {
      key: '4',
      name: 'Disabled User',
      age: 99,
      address: 'Sidney No. 1 Lake Park',
    }];

    const pageConfig = {
      className: 'ant-table-pagination',
      total: budgetMsgNum,
      current: budgetMsgage,
      pageSize: 20,
      onChange: this.onBudgetMsgChange,
    };
    const { selectedRowKeys } = this.state;

    const rowSelection = {
      selectedRowKeys,
      onChange: (selectedRowKeys, selectedRows) => {
        console.log(`selectedRowKeys: ${selectedRowKeys}`, 'selectedRows: ', selectedRows);
        this.setState({ selectedRowKeys });
      },
    };
    return (
      <FrameContent>
        <Card title="项目基础数据管理">
          <Form onSubmit={this.onSearchSubmit} layout="inline">
            <Form.Item label="项目编号 ：">
              { getFieldDecorator('projectNo', {
              })(
                <Input />
              )}
            </Form.Item>
            <Form.Item label="专业名称 ：">
              { getFieldDecorator('name', {
              })(
                <Input />
              )}
            </Form.Item>
            <Form.Item label="主持单位 ：">
              { getFieldDecorator('unit', {
              })(
                <Input />
              )}
            </Form.Item>
            <Form.Item >
              <Button type="primary" htmlType="submit" icon="search" onClick={this.doSearch}>查询</Button>
              <Button style={{marginLeft:5}} type="primary" onClick={() => this.props.form.resetFields()}><Icon type="rollback" />重置</Button>
              <Button style={{marginLeft:5}} type="primary" onClick={this.addNew}><Icon type="plus" />添加</Button>
              <Button style={{marginLeft:5}} type="primary" onClick={this.editProject}><Icon type="edit" />编辑</Button>
              <Button style={{marginLeft:5}} type="primary" onClick={this.deletePro}><Icon type="close" />删除</Button>
              <Upload action={'//project/uploadProject'}>
                <Button>
                  <Icon type="upload" /> 上传文件
                </Button>
              </Upload>
            </Form.Item>
          </Form>
          <Table
            rowSelection={rowSelection}
            columns={columns}
            dataSource={data}
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
  const { userType, userName } = state.baseModel;
  return { userType, userName };
}

export default connect(mapStateToProps)(Form.create({})(ProjectInfoList));
