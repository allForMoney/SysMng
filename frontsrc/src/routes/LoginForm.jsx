import { Form, Icon, Input, Button, Select } from 'antd';
import React from 'react';
import { connect } from 'dva';
import styles from './LoginForm.less';

const Option = Select.Option;
const FormItem = Form.Item;

class LoginForm extends React.Component {
  state={
    selectedYear: '',
  }
  onYearChanged = (value) => {
    this.setState({ selectedYear: value });
  }

  getYears = () => {
    const years = [];
    for (let i = 2010; i < 2026; i++) {
      years.push(i);
    }
    return years;
  }

  handleSubmit = (e) => {
    e.preventDefault();
    this.props.form.validateFields((err, values) => {
      if (!err) {
        console.log('Received values of form: ', values);
      }
      console.log(values);
      this.props.dispatch({
        type: 'baseModel/login',
        payload: values
      });
    });
  }

  render() {
    const { getFieldDecorator } = this.props.form;
    const years = this.getYears();
    const options = years.map(year => <Option value={year}>{year}</Option>);

    const { selectedYear } = this.state;

    const formWraper = {
      labelCol: {
        sm: { span: 6 },
      },
      wrapperCol: {
        sm: { span: 14 },
      },
    };

    return (
      <Form onSubmit={this.handleSubmit} className={styles.loginForm}>
        <FormItem label="年度" {...formWraper}>
          {getFieldDecorator('year', {
            rules: [{ required: true, message: '请选择年度' }],
          })(
            <Select
              showSearch
              placeholder="Select a person"
              optionFilterProp="children"
              onChange={this.onYearChanged}
              value={selectedYear}
            >
              {options}
            </Select>
          )}
        </FormItem>
        <FormItem label="账号" {...formWraper}>
          {getFieldDecorator('username', {
            rules: [{ required: true, message: '请输入您的账号' }],
          })(
            <Input prefix={<Icon type="user" style={{ fontSize: 13 }} />} placeholder="Username" />
          )}
        </FormItem>
        <FormItem label="密码" {...formWraper}>
          {getFieldDecorator('password', {
            rules: [{ required: true, message: '请输入您的密码' }],
          })(
            <Input prefix={<Icon type="lock" style={{ fontSize: 13 }} />} type="password" placeholder="Password" />
          )}
        </FormItem>
        <FormItem wrapperCol={{ span: 12, offset: 6 }}>
          <Button type="primary" htmlType="submit" className="login-form-button" style={{ width: 200 }}>
            登录
          </Button>
        </FormItem>
      </Form>
    );
  }
}

function mapStateToProps(state) {
  const { userType, userName } = state.baseModel;
  return { userType, userName };
}

export default connect(mapStateToProps)(Form.create()(LoginForm));
