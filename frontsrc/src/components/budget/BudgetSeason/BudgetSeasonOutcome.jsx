import React from 'react';
import {
  Table,
  Card,
   Modal, Form, Input,
   InputNumber,
} from 'antd';
import styles from './BudgetSeasonIncome.less';

const FormItem = Form.Item;

class BudgetSeasonOutcome extends React.Component {
  state={
    modalVisible: false,
    currentEditIndex: 0,
    sourceData: [
      {
        pid: 1,
        moneySource: '部本专项资金',
        materialMake: 0,
        companyCase: 0,
        courseDevelopment: 0,
        specialTool: 0,
        applicationPromete: 0,
        researchProve: 0,
        expertConsult: 0,
        otherFee: 0,
      }, {
        pid: 2,
        moneySource: '院校举办方或地方财政投入资金',
        materialMake: 0,
        companyCase: 0,
        courseDevelopment: 0,
        specialTool: 0,
        applicationPromete: 0,
        researchProve: 0,
        expertConsult: 0,
        otherFee: 0,
      }, {
        pid: 3,
        moneySource: '行业企业支持资金',
        materialMake: 0,
        companyCase: 0,
        courseDevelopment: 0,
        specialTool: 0,
        applicationPromete: 0,
        researchProve: 0,
        expertConsult: 0,
        otherFee: 0,
      }, {
        pid: 4,
        moneySource: '相关院校自筹资金',
        materialMake: 0,
        companyCase: 0,
        courseDevelopment: 0,
        specialTool: 0,
        applicationPromete: 0,
        researchProve: 0,
        expertConsult: 0,
        otherFee: 0,
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

  getItemTotalMoney = (item) => {
    const totalMoney = (item.materialMake + item.companyCase + item.courseDevelopment
    + item.specialTool + item.applicationPromete +
    item.researchProve + item.expertConsult + item.otherFee);
    return totalMoney;
  }

  getItemSingleTotalMoney = (attr, [item1, item2, item3, item4], flag) => {
    let totalMoney = (item1[attr] + item2[attr] + item3[attr] + item4[attr]);
    if (flag) {
      totalMoney -= item1[attr];
    }
    return totalMoney;
  }

  getDetailSource = (item) => {
    const totalMoney = this.getItemTotalMoney(item);
    return [{
      column1: '项目筹措资金',
      moneySource: item.moneySource,
      project: '金额(元)',
      pid: item.pid,
      totalMoney,
      ...item,
    }, {
      column1: '项目筹措资金',
      moneySource: item.moneySource,
      project: '到位率',
      totalMoney: 0,
    }];
  }

  generateDataSource= (sourceData) => {
    const [item1, item2, item3, item4] = sourceData;
    const item1Total = this.getItemTotalMoney(item1);
    const item2Total = this.getItemTotalMoney(item2);
    const item3Total = this.getItemTotalMoney(item3);
    const item4Total = this.getItemTotalMoney(item4);
    const sumMoney = item1Total + item2Total + item3Total + item4Total;

    const sumMoney2 = item2Total + item3Total + item4Total;

    let dataSource = [
      {
        column1: '合计',
        moneySource: '合计',
        project: '金额(元)',
        totalMoney: sumMoney,
        materialMake: this.getItemSingleTotalMoney('materialMake', sourceData),
        companyCase: this.getItemSingleTotalMoney('companyCase', sourceData),
        courseDevelopment: this.getItemSingleTotalMoney('courseDevelopment', sourceData),
        specialTool: this.getItemSingleTotalMoney('specialTool', sourceData),
        applicationPromete: this.getItemSingleTotalMoney('applicationPromete', sourceData),
        researchProve: this.getItemSingleTotalMoney('researchProve', sourceData),
        expertConsult: this.getItemSingleTotalMoney('expertConsult', sourceData),
        otherFee: this.getItemSingleTotalMoney('otherFee', sourceData),
      }, {
        column1: '合计',
        moneySource: '合计',
        project: '到位率',
        totalMoney: 0,
      }, {
        column1: item1.moneySource,
        moneySource: item1.moneySource,
        pid: item1.pid,
        project: '金额(元)',
        totalMoney: this.getItemTotalMoney(item1),
        ...item1,
      }, {
        column1: item1.moneySource,
        moneySource: item1.moneySource,
        project: '到位率',
        totalMoney: 0,
      }, {
        column1: '项目筹措资金',
        moneySource: '小计',
        project: '金额(元)',
        totalMoney: sumMoney2,
        materialMake: this.getItemSingleTotalMoney('materialMake', sourceData, true),
        companyCase: this.getItemSingleTotalMoney('companyCase', sourceData, true),
        courseDevelopment: this.getItemSingleTotalMoney('courseDevelopment', sourceData, true),
        specialTool: this.getItemSingleTotalMoney('specialTool', sourceData, true),
        applicationPromete: this.getItemSingleTotalMoney('applicationPromete', sourceData, true),
        researchProve: this.getItemSingleTotalMoney('researchProve', sourceData, true),
        expertConsult: this.getItemSingleTotalMoney('expertConsult', sourceData, true),
        otherFee: this.getItemSingleTotalMoney('otherFee', sourceData, true),
      }, {
        column1: '项目筹措资金',
        moneySource: '小计',
        project: '到位率',
        totalMoney: 0,
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
        width: 80
      }, {
        title: '总额',
        dataIndex: 'totalMoney',
        width: 80,
        key: 'totalMoney',
      }, {
        title: '素材制作',
        dataIndex: 'materialMake',
        key: 'materialMake',
        render: (value, row, index) => {
          if ([2, 6, 8, 10].includes(index)) {
            return (<p className={styles.editable}>{value}</p>);
          }
          return value;
        }
      }, {
        title: '企业案例收集制作',
        dataIndex: 'companyCase',
        key: 'companyCase',
        render: (value, row, index) => {
          if ([2, 6, 8, 10].includes(index)) {
            return (<p className={styles.editable}>{value}</p>);
          }
          return value;
        }
      }, {
        title: '课程开发',
        dataIndex: 'courseDevelopment',
        key: 'courseDevelopment',
        render: (value, row, index) => {
          if ([2, 6, 8, 10].includes(index)) {
            return (<p className={styles.editable}>{value}</p>);
          }
          return value;
        }
      }, {
        title: '特殊工具软件制作',
        dataIndex: 'specialTool',
        key: 'specialTool',
        render: (value, row, index) => {
          if ([2, 6, 8, 10].includes(index)) {
            return (<p className={styles.editable}>{value}</p>);
          }
          return value;
        }
      }, {
        title: '应用推广',
        dataIndex: 'applicationPromete',
        key: 'applicationPromete',
        render: (value, row, index) => {
          if ([2, 6, 8, 10].includes(index)) {
            return (<p className={styles.editable}>{value}</p>);
          }
          return value;
        }
      }, {
        title: '调研论证',
        dataIndex: 'researchProve',
        key: 'researchProve',
        render: (value, row, index) => {
          if ([2, 6, 8, 10].includes(index)) {
            return (<p className={styles.editable}>{value}</p>);
          }
          return value;
        }
      }, {
        title: '专家咨询',
        dataIndex: 'expertConsult',
        key: 'expertConsult',
        render: (value, row, index) => {
          if ([2, 6, 8, 10].includes(index)) {
            return (<p className={styles.editable}>{value}</p>);
          }
          return value;
        }
      }, {
        title: '其他',
        dataIndex: 'otherFee',
        key: 'otherFee',
        render: (value, row, index) => {
          if ([2, 6, 8, 10].includes(index)) {
            return (<p className={styles.editable}>{value}</p>);
          }
          return value;
        }
      }
    ];

    const { modalVisible } = this.state;
    const formItemLayout = {
      labelCol: { span: 10 },
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
            <FormItem label="其他" {...formItemLayout} style={{ display: 'none' }}>
              {getFieldDecorator('pid', {
                rules: [filterRules],
              })(<InputNumber />)}
            </FormItem>
          </Form>
        </Modal>
        <Table
          columns={columns}
          size="small"
          bordered
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
