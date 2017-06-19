import React from 'react';
import {
  Table,
  Card,
   Modal, Form, Input,
} from 'antd';

const FormItem = Form.Item;

class BudgetSeasonOutcome extends React.Component {
  state={
    modalVisible: false,
    currentEditIndex: 0,
  }

  onCancel= () => {
    this.props.form.resetFields();
    this.setState({ modalVisible: false });
  }

  saveBudgetInCome= () => {
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
      source: '123',
      source2: 444,
      pname: 456,
      money: 3333,
    }, {
      source2: 44554,
      source: '1323',
      pname: 4536,
      money: 33333,
    }, {
      source2: 544554,
      source: '51323',
      pname: 45536,
      money: 353333,
    }, {
      source2: 445954,
      source: '13923',
      pname: 45306,
      money: 333633,
    }];

    const columns = [{
      title: '资金来源',
      dataIndex: 'source',
      colSpan: 2,
      key: 'source',
    }, {
      title: '项目',
      dataIndex: 'source2',
      colSpan: 0,
      key: 'source2',
    }, {
      title: '总额',
      dataIndex: 'pname',
      key: 'pname',
    }, {
      title: '素材制作',
      dataIndex: 'money',
      key: 'money',
    }, {
      title: '企业案例收集制作',
      dataIndex: 'money3',
      key: 'money3',
    }, {
      title: '课程开发',
      dataIndex: 'mo3ney',
      key: 'monmo3neyey',
    }, {
      title: '特殊工具软件制作',
      dataIndex: 'mon3ey',
      key: 'mon3ey',
    }, {
      title: '应用推广',
      dataIndex: 'm4oney',
      key: 'm4oney',
    }, {
      title: '调研论证',
      dataIndex: 'mo5ney',
      key: 'mo5ney',
    }, {
      title: '专家咨询',
      dataIndex: 'm6oney',
      key: 'm6oney',
    }, {
      title: '其他',
      dataIndex: 'mo7ney',
      key: 'mo7ney',
    }];

    const { modalVisible } = this.state;
    const formItemLayout = {
      labelCol: { span: 6 },
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
          title="收入预算执行情况"
          okText="保存"
          onCancel={this.onCancel}
          onOk={this.saveBudgetInCome}
        >
          <Form layout="vertical">
            <FormItem label="资金来源" {...formItemLayout} >
              {getFieldDecorator('pname', {
              })(<Input />)}
            </FormItem>
            <FormItem label="金额" {...formItemLayout}>
              {getFieldDecorator('money', {
                rules: [filterRules],
              })(<Input />)}
            </FormItem>
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

export default Form.create({})(BudgetSeasonOutcome);
