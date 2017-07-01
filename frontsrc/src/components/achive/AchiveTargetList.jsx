import React from 'react';
import {
  Table,
  Card,
   Modal, Form, Input,Row,Col,
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
          buggetInComeList: dataSource
        }
      });
      this.onCancel();
    });
  }
  
  render() {
    const { getFieldDecorator } = this.props.form;
    const dataSource = [{
      source2: '101',
      source: 102,
      pname: 103,
      money: 104,
    }, {
      source2: 201,
      source: '202',
      pname: 203,
      money: 204,
    }, {
      source2: 301,
      source: '302',
      pname: 303,
      money: 304,
    }, {
      source2: 401,
      source: '402',
      pname: 403,
      money: 404,
    }, {
      source2: 501,
      source: '502',
      pname: 503,
      money: 504,
    }, {
      source2: 601,
      source: '602',
      pname: 603,
      money: 604,
    }, {
      source2: 701,
      source: '702',
      pname: 703,
      money: 704,
    }, {
      source2: 801,
      source: '802',
      pname: 803,
      money: 804,
    }, {
      source2: 901,
      source: '902',
      pname: 903,
      money: 904,
    }, {
      source2: 1001,
      source: '1002',
      pname: 1003,
      money: 1004,
    }, {
      source2: 1001,
      source: '1002',
      pname: 1003,
      money: 1004,
    }, {
      source2: 1001,
      source: '1002',
      pname: 1003,
      money: 1004,
    }];

    const columns = [{
      title: '一级指标',
      dataIndex: 'indicatorOneLevel',
      colSpan: 2,
      key: 'indicatorOneLevel',
    }, {
      title: '二级指标',
      dataIndex: 'indicatorTowLevel',
      colSpan: 0,
      key: 'indicatorTowLevel',
    }, {
      title: '三级指标',
      dataIndex: 'indicatorThreeLevel',
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
              <Col span={3} style={{lineHeight: 13 }}>计划指标值</Col>
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
          columns={columns}
          dataSource={dataSource}
          rowKey={record => record.id}
          onRowClick={this.onRowClicked}
          pagination={false}
        />
      </Card>
    );
  }
}

export default Form.create({})(AchiveTargetList);
