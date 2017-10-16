/**
 * 所有项目列表,主要用在教育部 用户下展示列表
 * 可以用在需要展示项目列表的地方
 */
import React from 'react';
import { Form, Input, Button, Icon, Card, Table } from 'antd';
import LinkBtn from '../common/LinkBtn';


class ProjectList extends React.Component {
  componentDidMount() {
    this.props.dispatch({
      type: 'ProjectModel/setState',
      payload: {
        filterParam: {},
        projectListPage: 1,
      },
    });
    this.props.dispatch({
      type: 'ProjectModel/getProjectList',
    });
  }

  onPageChanged = (page) => {
    this.props.dispatch({
      type: 'ProjectModel/setState',
      payload: {
        projectListPage: page,
      },
    });
    this.props.dispatch({
      type: 'ProjectModel/getProjectList',
    });
  }

  doSearch = (e) => {
    e.preventDefault();
    const values = this.props.form.getFieldsValue();
    console.log(values);
    this.props.dispatch({
      type: 'ProjectModel/setState',
      payload: {
        filterParam: values,
        projectListPage: 1,
      },
    });
    this.props.dispatch({
      type: 'ProjectModel/getProjectList',
    });
  }

  addFormRdf =(form) => {
    this.addForm = form;
  }

  render() {
    const {
      loading,
      projectList,
      projectListNum,
      projectListPage,
      actionFunc,
      actionName,
    } = this.props;
    const { getFieldDecorator } = this.props.form;

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
        title: '操作',
        render: (text, rec) => (
          <span>
            <LinkBtn onClick={actionFunc.bind(this, rec, '1')}>{actionName || '查看详情'}</LinkBtn>
          </span>
        )
      }];

    const pageConfig = {
      className: 'ant-table-pagination',
      total: projectListNum,
      current: projectListPage,
      pageSize: 20,
      onChange: this.onPageChanged,
    };

    return (
      <Card>
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
            <Button size="small" type="primary" htmlType="submit" icon="search" onClick={this.doSearch}>查询</Button>
            <Button size="small" style={{ marginLeft: 5 }} type="primary" onClick={() => this.props.form.resetFields()}><Icon type="rollback" />重置</Button>
          </Form.Item>
        </Form>
        <Table
          columns={columns}
          dataSource={projectList}
          bordered
          loading={loading}
          scroll={{ x: '140', y: 300 }}
          rowKey={record => record.name}
          pagination={pageConfig}
        />
      </Card>
    );
  }
}

export default Form.create({})(ProjectList);
