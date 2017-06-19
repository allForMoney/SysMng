import React from 'react';
import {
  Table,
  Card,
   Modal, Form, Input,Row,Col,
} from 'antd';

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

  saveAchiveTarget= () => {
    const { currentEditIndex } = this.state;
    this.props.form.validateFields((err,values) => {
      if (err) {
        return;
      }
      // TODO genju currentEditIndex 更新list 
    });
    this.onCancel();
  }

  onRowClicked= (record, index) => {
    if (!this.props.editable) {
      return;
    }
    const { setFieldsValue } = this.props.form;
    setFieldsValue(record);
    this.setState({ modalVisible: true, currentEditIndex: index });
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
      dataIndex: 'source2',
      colSpan: 2,
      key: 'source2',
    }, {
      title: '二级指标',
      dataIndex: 'source',
      colSpan: 0,
      key: 'source',
    }, {
      title: '三级指标',
      dataIndex: 'pname',
      key: 'pname',
    }, {
      title: '计划指标值',
      children: [{
        title: '总体值',
        dataIndex: 'pna6me',
        key: 'pnam0e',
      }, {
        title: '第一年',
        dataIndex: 'pna1me',
        key: 'pna-me',
      }, {
        title: '第二年',
        dataIndex: 'pna1m4e',
        key: 'pn8ame',
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
              {getFieldDecorator('pname', {
              })(<Input />)}
            </FormItem>
            <FormItem label="三级指标" {...formItemLayout}>
              {getFieldDecorator('money', {
                rules: [filterRules],
              })(<Input />)}
            </FormItem>
            <Row>
              <Col span={3} style={{lineHeight: 13 }}>计划指标值</Col>
              <Col span={20}>
                <Row>
                  <FormItem label="总体值" {...formItemLayout2}>
                    {getFieldDecorator('money', {
                      rules: [filterRules],
                    })(<Input />)}
                  </FormItem>
                </Row>
                <Row>
                  <FormItem label="第一年" {...formItemLayout2}>
                    {getFieldDecorator('money', {
                      rules: [filterRules],
                    })(<Input />)}
                  </FormItem>
                </Row>
                <Row>
                  <FormItem label="第二年" {...formItemLayout2}>
                    {getFieldDecorator('money', {
                      rules: [filterRules],
                    })(<Input />)}
                  </FormItem>
                </Row>
              </Col>
            </Row>
          </Form>
        </Modal>
        <Table
          title={() => '留言处理情况'}
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
