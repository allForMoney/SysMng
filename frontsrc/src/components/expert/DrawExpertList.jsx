/**
 * 所有项目列表,主要用在教育部 用户下展示列表
 * 可以用在需要展示项目列表的地方
 */
import React from 'react';
import { Form, Input, Button, Icon, Card, Table } from 'antd';
import LinkBtn from '../common/LinkBtn';


class DrawExpertList extends React.Component {
  componentDidMount() {
    this.props.dispatch({
      type: 'expertModel/setState',
      payload: {
        drawExpertList: [],
      },
    });
  }

  doSearch = (e) => {
    e.preventDefault();
    const values = this.props.form.getFieldsValue();
    console.log(values);
    // this.props.dispatch({
    //   type: 'expertModel/drawProjectList',
    // });
  }

  addFormRdf =(form) => {
    this.addForm = form;
  }

  render() {
    const {
      drawExpertList,
      loading,
    } = this.props;
    const { getFieldDecorator } = this.props.form;

    const columns =
      [{
        title: '职称',
        dataIndex: 'professionalTitle',
      }, {
        title: '学历',
        dataIndex: 'eduLevel',
      }, {
        title: '回避单位',
        dataIndex: 'avoidUnit',
      }, {
        title: '研究领域',
        dataIndex: 'researchField',
      }];

    return (
      <Card>
        <Form onSubmit={this.onSearchSubmit} layout="inline">
          <Form.Item label="抽取数量 ：">
            { getFieldDecorator('drawNum', {
            })(
              <Input size="small" width="100" />
            )}
          </Form.Item>
          <Form.Item label="职称 ：">
            { getFieldDecorator('professionalTitle', {
            })(
              <Input size="small" />
            )}
          </Form.Item>
          <Form.Item label="回避单位 ：">
            { getFieldDecorator('avoidUnit', {
            })(
              <Input size="small" />
            )}
          </Form.Item>
          <Form.Item label="研究领域 ：">
            { getFieldDecorator('researchField', {
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
          dataSource={drawExpertList}
          bordered
          loading={loading}
          rowKey={record => record.name}
        />
      </Card>
    );
  }
}

export default Form.create({})(DrawExpertList);
