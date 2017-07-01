import React from 'react';
import {
  Table,
  Card,
   Modal, Form, Input,InputNumber
} from 'antd';

const FormItem = Form.Item;

class BudgetSeasonIncome extends React.Component {
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
    if (![2, 6, 8, 10].includes(index)) {
      return;
    }
    console.log(record);
    const { setFieldsValue } = this.props.form;
    setFieldsValue(record);
    this.setState({ modalVisible: true, currentEditIndex: index });
  }

  saveBudgetInCome= () => {
    const { currentEditIndex } = this.state;
    const { dataSource } = this.props;
    this.props.form.validateFields((err,values) => {
      if (err) {
        return;
      }
      const oldData = dataSource[currentEditIndex];

      dataSource.splice(currentEditIndex, 1, Object.assign(oldData, values));

      this.props.dispatch({
        type: 'budgetModel/setState',
        payload: {
          buggetInComeList: dataSource
        }
      });
      this.onCancel();
    });
  }
  
  render() {
    const { getFieldDecorator } = this.props.form;
    // const dataSource = [{
    //   source2: '101',
    //   source: 102,
    //   pname: 103,
    //   money: 104,
    // }, {
    //   source2: 201,
    //   source: '202',
    //   pname: 203,
    //   money: 204,
    // }, {
    //   source2: 301,
    //   source: '302',
    //   pname: 303,
    //   money: 304,
    // }, {
    //   source2: 401,
    //   source: '402',
    //   pname: 403,
    //   money: 404,
    // }, {
    //   source2: 501,
    //   source: '502',
    //   pname: 503,
    //   money: 504,
    // }, {
    //   source2: 601,
    //   source: '602',
    //   pname: 603,
    //   money: 604,
    // }, {
    //   source2: 701,
    //   source: '702',
    //   pname: 703,
    //   money: 704,
    // }, {
    //   source2: 801,
    //   source: '802',
    //   pname: 803,
    //   money: 804,
    // }, {
    //   source2: 901,
    //   source: '902',
    //   pname: 903,
    //   money: 904,
    // }, {
    //   source2: 1001,
    //   source: '1002',
    //   pname: 1003,
    //   money: 1004,
    // }, {
    //   source2: 1001,
    //   source: '1002',
    //   pname: 1003,
    //   money: 1004,
    // }, {
    //   source2: 1001,
    //   source: '1002',
    //   pname: 1003,
    //   money: 1004,
    // }];

    const columns = [{
      title: '资金来源',
      dataIndex: 'source2',
      colSpan: 2,
      key: 'source2',
      render: (value, row, index) => {
        const obj = {
          children: value,
          props: {},
        };
        if (index < 4) {
          if (index % 2 === 0) {
            obj.props.rowSpan = 2;
            obj.props.colSpan = 2;
          } else {
            obj.props.rowSpan = 0;
          }
        }

        if (index === 4) {
          obj.props.rowSpan = 8;
        }
        if (index > 4) {
          obj.props.rowSpan = 0;
        }
        return obj;
      },
    }, {
      title: '项目',
      dataIndex: 'source',
      colSpan: 0,
      key: 'source',
      render: (value, row, index) => {
        const obj = {
          children: value,
          props: {},
        };
        if (index < 4) {
          obj.props.rowSpan = 0;
        }
        if (index >= 4) {
          if (index % 2 === 0) {
            obj.props.rowSpan = 2;
          } else {
            obj.props.rowSpan = 0;
          }
        }
        return obj;
      },
      
    }, {
      title: '项目',
      dataIndex: 'pname',
      key: 'pname',
    }, {
      title: '总额/到位率',
      dataIndex: 'money',
      key: 'money',
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

    const { dataSource } = this.props;

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
              })(<Input disabled />)}
            </FormItem>
            <FormItem label="金额" {...formItemLayout}>
              {getFieldDecorator('money', {
                rules: [filterRules],
              })(<InputNumber />)}
            </FormItem>
          </Form>
        </Modal>
        <Table
          title={() => '留言处理情况'}
          columns={columns}
          size="small"
          dataSource={dataSource}
          rowKey={record => record.id}
          onRowClick={this.onRowClicked}
          pagination={false}
        />
      </Card>
    );
  }
}

export default Form.create({})(BudgetSeasonIncome);
