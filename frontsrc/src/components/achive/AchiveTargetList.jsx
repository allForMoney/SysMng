import React from 'react';
import {
  Table,
  Card,
   Modal, Form, Input, Row, Col,
} from 'antd';
import AchiveTarget from './AchiveTarget';

const FormItem = Form.Item;

class AchiveTargetList extends React.Component {
  state={
    modalVisible: false,
    currentEditIndex: 0,
  }

  onCancel= () => {
    this.props.form.resetFields();
    this.setState({ modalVisible: false });
  }

  onRowClicked= (record, index) => {
    if (!this.props.editable) {
      return;
    }
    const { setFieldsValue } = this.props.form;
    setFieldsValue(record);
    this.setState({ modalVisible: true, currentEditIndex: index });
  }

  saveAchiveTarget = () => {
    const { currentEditIndex } = this.state;
    const { dataSource } = this.props;
    this.props.form.validateFields((err, values) => {
      if (err) {
        return;
      }
      const oldData = dataSource[currentEditIndex];
      dataSource.splice(currentEditIndex, 1, Object.assign(oldData, values));
      this.props.dispatch({
        type: 'achiveModel/setState',
        payload: {
          indicatorDetails: dataSource
        }
      });
      this.onCancel();
    });
  }

  render() {
    const { getFieldDecorator } = this.props.form;

    const columns = [{
      title: '一级指标',
      dataIndex: 'indicatorOneLevel',
      key: 'indicatorOneLevel',
      width: 100

    }, {
      title: '二级指标',
      dataIndex: 'indicatorTowLevel',
      width: 100,
      key: 'indicatorTowLevel',
    }, {
      title: '三级指标',
      dataIndex: 'indicatorThreeLevel',
      width: 100,
      key: 'indicatorThreeLevel',
    }, {
      title: '计划指标值',
      children: [{
        title: '总体值',
        dataIndex: 'planTotal',
        key: 'planTotal',
      }, {
        title: '第一年',
        dataIndex: 'planFirstYear',
        key: 'planFirstYear',
      }, {
        title: '第二年',
        dataIndex: 'planSecondYear',
        key: 'planSecondYear',
      }]
    }];

    const { modalVisible } = this.state;
    const formItemLayout = {
      labelCol: { span: 6 },
      wrapperCol: { span: 14 },
    };
    const formItemLayout2 = {
      labelCol: { span: 4 },
      wrapperCol: { span: 14 },
    };

    const filterRules = {
      required: true,
      message: '不可为空'
    };

    const { dataSource } = this.props;

    return (
      <Card>
        <Modal
          visible={modalVisible}
          title="填写具体目标"
          okText="保存"
          onCancel={this.onCancel}
          onOk={this.saveAchiveTarget}
        >
          <Form layout="vertical">
            <FormItem label="二级指标" {...formItemLayout} >
              {getFieldDecorator('indicatorTowLevel', {
              })(<Input />)}
            </FormItem>
            <FormItem label="三级指标" {...formItemLayout}>
              {getFieldDecorator('indicatorThreeLevel', {
                rules: [filterRules],
              })(<Input />)}
            </FormItem>
            <Row>
              <Col span={3} style={{ lineHeight: 13 }}>计划指标值</Col>
              <Col span={20}>
                <Row>
                  <FormItem label="总体值" {...formItemLayout2}>
                    {getFieldDecorator('planTotal', {
                      rules: [filterRules],
                    })(<Input />)}
                  </FormItem>
                </Row>
                <Row>
                  <FormItem label="第一年" {...formItemLayout2}>
                    {getFieldDecorator('planFirstYear', {
                      rules: [filterRules],
                    })(<Input />)}
                  </FormItem>
                </Row>
                <Row>
                  <FormItem label="第二年" {...formItemLayout2}>
                    {getFieldDecorator('planSecondYear', {
                      rules: [filterRules],
                    })(<Input />)}
                  </FormItem>
                </Row>
              </Col>
            </Row>
          </Form>
        </Modal>
        <Table
          title={() => '绩效指标'}
          bordered
          columns={columns}
          dataSource={dataSource}
          rowKey={record => record.id}
          onRowClick={this.onRowClicked}
          pagination={false}
          scroll={{ x: '140', y: 300 }}
        />
      </Card>
    );
  }
}

export default Form.create({})(AchiveTargetList);
