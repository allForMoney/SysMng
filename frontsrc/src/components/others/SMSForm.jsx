import React from 'react';
import { Form, Input, Button, Card } from 'antd';

const FormItem = Form.Item;

class SMSForm extends React.Component {
  render() {
    const { getFieldDecorator } = this.props.form;
    const { onSendSMS } = this.props;
    return (
      <Form>
        <FormItem
          label="短信内容"
          labelCol={{ span: 4 }}
          wrapperCol={{ span: 8 }}
        >
          {getFieldDecorator('message', {
            rules: [{ required: true, message: '请输入短信内容' }],
          })(
            <Input type="textarea" rows={4} />
          )}
        </FormItem>
        <FormItem
          wrapperCol={{ span: 8, offset: 4 }}
        >
          <Button htmlType="submit" onClick={onSendSMS}>
            发送短信
          </Button>
        </FormItem>
        <FormItem
          wrapperCol={{ span: 8, offset: 4 }}
        >
         注：该短信内容将同时发送给项目第一主持单位的三个相关人员。
        </FormItem>
      </Form>
    );
  }
}

export default Form.create({})(SMSForm);
