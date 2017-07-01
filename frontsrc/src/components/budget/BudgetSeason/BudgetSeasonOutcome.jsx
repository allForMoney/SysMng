import React from 'react';
import {
  Table,
  Card,
   Modal, Form, Input,
   InputNumber,
} from 'antd';

const FormItem = Form.Item;

class BudgetSeasonOutcome extends React.Component {
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

  onCancel= () => {
    this.props.form.resetFields();
    this.setState({ modalVisible: false });
  }

  saveBudgetOutCome= () => {
    const { sourceData } = this.state;
    const { buggetOutComeList } = this.props;
    this.props.form.validateFields((err, values) => {
      if (err) {
        return;
      }
      let array = sourceData;
      if (buggetOutComeList.length > 0) {
        array = buggetOutComeList;
      }

      array.map((item) => {
        if (item.pid === values.pid) {
          Object.assign(item, values);
        }
      });
      this.props.dispatch({
        type: 'budgetModel/setState',
        payload: {
          buggetOutComeList: [...array]
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
      ...item,
    }, {
      column1: '项目筹措资金',
      moneySource: item.moneySource,
      project: '到位率',
      money: 0,
    }];
  }

  generateDataSource= (sourceData) => {
    const [ item1, item2, item3, item4 ] = sourceData;
    const sumMoney = item1.money + item2.money + item3.money + item4.money;
    const sumMoney2 = item2.money + item3.money + item4.money;
    let dataSource = [
      {
        column1: '合计',
        moneySource: '合计',
        project: '金额(元)',
        money: sumMoney,
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
        ...item1,
      }, {
        column1: item1.moneySource,
        moneySource: item1.moneySource,
        project: '到位率',
        money: 0,
      }, {
        column1: '项目筹措资金',
        moneySource: '小计',
        project: '金额(元)',
        money: sumMoney2,
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
    const { buggetOutComeList } = this.props;
    let sourceData = this.state.sourceData;
    if (buggetOutComeList.length > 0) {
      sourceData = buggetOutComeList;
    }

    const dataSource = this.generateDataSource(sourceData);

    const columns = [
      {
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
        title: '总额',
        dataIndex: 'pname',
        key: 'pname',
      }, {
        title: '素材制作',
        dataIndex: 'materialMake',
        key: 'materialMake',
      }, {
        title: '企业案例收集制作',
        dataIndex: 'companyCase',
        key: 'companyCase',
      }, {
        title: '课程开发',
        dataIndex: 'courseDevelopment',
        key: 'courseDevelopment',
      }, {
        title: '特殊工具软件制作',
        dataIndex: 'specialTool',
        key: 'specialTool',
      }, {
        title: '应用推广',
        dataIndex: 'applicationPromete',
        key: 'applicationPromete',
      }, {
        title: '调研论证',
        dataIndex: 'researchProve',
        key: 'researchProve',
      }, {
        title: '专家咨询',
        dataIndex: 'expertConsult',
        key: 'expertConsult',
      }, {
        title: '其他',
        dataIndex: 'otherFee',
        key: 'otherFee',
      }
    ];

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
          title="修改支出预算信息"
          okText="保存"
          onCancel={this.onCancel}
          onOk={this.saveBudgetOutCome}
        >
          <Form layout="vertical" size="small">
            <FormItem label="资金来源" {...formItemLayout} >
              {getFieldDecorator('moneySource', {
              })(<Input disabled />)}
            </FormItem>
            <FormItem label="素材制作" {...formItemLayout}>
              {getFieldDecorator('materialMake', {
                rules: [filterRules],
              })(<InputNumber />)}
            </FormItem>
            <FormItem label="企业案例收集制作" {...formItemLayout}>
              {getFieldDecorator('companyCase', {
                rules: [filterRules],
              })(<InputNumber />)}
            </FormItem>
            <FormItem label="课程开发" {...formItemLayout}>
              {getFieldDecorator('courseDevelopment', {
                rules: [filterRules],
              })(<InputNumber />)}
            </FormItem>
            <FormItem label="特殊工具软件制作" {...formItemLayout}>
              {getFieldDecorator('specialTool', {
                rules: [filterRules],
              })(<InputNumber />)}
            </FormItem>
            <FormItem label="应用推广" {...formItemLayout}>
              {getFieldDecorator('applicationPromete', {
                rules: [filterRules],
              })(<InputNumber />)}
            </FormItem>
            <FormItem label="调研论证" {...formItemLayout}>
              {getFieldDecorator('researchProve', {
                rules: [filterRules],
              })(<InputNumber />)}
            </FormItem>
            <FormItem label="专家咨询" {...formItemLayout}>
              {getFieldDecorator('expertConsult', {
                rules: [filterRules],
              })(<InputNumber />)}
            </FormItem>
            <FormItem label="其他" {...formItemLayout}>
              {getFieldDecorator('otherFee', {
                rules: [filterRules],
              })(<InputNumber />)}
            </FormItem>
            <FormItem label="其他" {...formItemLayout} style={{display:'none'}}>
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

export default Form.create({})(BudgetSeasonOutcome);
