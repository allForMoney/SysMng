import React from 'react';
import {
  Table,
  Card,
   Modal,
   Form,
   Input,
   InputNumber
} from 'antd';

const FormItem = Form.Item;

class BudgetSeasonIncome extends React.Component {
  state={
    modalVisible: false,
    currentEditIndex: 0,
    sourceData: [
      {
        pid: 1,
        moneySource: '部本专项资金',
        money: 0,
      }, {
        pid: 2,
        moneySource: '院校举办方或地方财政投入资金',
        money: 0,
      }, {
        pid: 3,
        moneySource: '行业企业支持资金',
        money: 0,
      }, {
        pid: 4,
        moneySource: '相关院校自筹资金',
        money: 0,
      }
    ],
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
    const { sourceData } = this.state;
    const { buggetInComeList } = this.props;
    this.props.form.validateFields((err,values) => {
      if (err) {
        return;
      }
      let array = sourceData;
      if (buggetInComeList.length > 0) {
        array = buggetInComeList;
      }

      array.map((item) => {
        if (item.pid === values.pid) {
          Object.assign(item, values);
        }
      });

      this.props.dispatch({
        type: 'budgetModel/setState',
        payload: {
          buggetInComeList: [...array]
        }
      });
      this.onCancel();
    });
  }

  getDetailSource = (item) => {
    return [{
      column1: '项目筹措资金',
      moneySource: item.moneySource,
      project: '金额(元)',
      pid: item.pid,
      money: item.money,
    }, {
      column1: '项目筹措资金',
      moneySource: item.moneySource,
      project: '到位率',
      money: 0,
    }];
  }

  generateDataSource= (sourceData) => {
    const [ item1, item2, item3, item4 ] = sourceData;
    let dataSource = [
      {
        column1: '合计',
        moneySource: '合计',
        project: '金额(元)',
        money: 0,
      }, {
        column1: '合计',
        moneySource: '合计',
        project: '到位率',
        money: 0,
      }, {
        column1: item1.moneySource,
        moneySource: item1.moneySource,
        pid: item1.pid,
        project: '金额(元)',
        money: item1.money,
      }, {
        column1: item1.moneySource,
        moneySource: item1.moneySource,
        project: '到位率',
        money: 0,
      }, {
        column1: '项目筹措资金',
        moneySource: '小计',
        project: '金额(元)',
        money: 0,
      }, {
        column1: '项目筹措资金',
        moneySource: '小计',
        project: '到位率',
        money: 0,
      }
    ];
    dataSource = dataSource.concat(this.getDetailSource(item2));
    dataSource = dataSource.concat(this.getDetailSource(item3));
    dataSource = dataSource.concat(this.getDetailSource(item4));

    return dataSource;
  }

  render() {
    const { getFieldDecorator } = this.props.form;
    const { buggetInComeList } = this.props;
    let sourceData = this.state.sourceData;
    if (buggetInComeList.length > 0) {
      sourceData = buggetInComeList;
    }

    const dataSource = this.generateDataSource(sourceData);

    const columns = [{
      title: '资金来源',
      dataIndex: 'column1',
      colSpan: 2,
      key: 'column1',
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
      dataIndex: 'moneySource',
      colSpan: 0,
      key: 'moneySource',
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
      dataIndex: 'project',
      key: 'project',
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
              {getFieldDecorator('moneySource', {
              })(<Input disabled />)}
            </FormItem>
            <FormItem label="金额" {...formItemLayout}>
              {getFieldDecorator('money', {
                rules: [filterRules],
              })(<InputNumber />)}
            </FormItem>
            <FormItem label="金额" {...formItemLayout} style={{display:'none'}}>
              {getFieldDecorator('pid', {
                rules: [filterRules],
              })(<InputNumber />)}
            </FormItem>
          </Form>
        </Modal>
        <Table
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
