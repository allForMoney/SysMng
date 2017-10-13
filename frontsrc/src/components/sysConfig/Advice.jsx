import React from 'react';
import {
  Form,
  Input,
  Button,
  Row,
} from 'antd';
import { connect } from 'dva';

import FrameContent from '../common/FrameContent';

const FormItem = Form.Item;

class Advice extends React.Component {
  handleSubmit = () => {
    const { validateFields } = this.props.form;
    validateFields((error, values) => {
      if (error) {
        return;
      }
      this.props.dispatch({
        type: 'baseModel/updateAdvice',
        payload: values
      });
    });
  }

  render() {
    const { getFieldDecorator } = this.props.form;
    const formItemLayout = {
      labelCol: {
        xs: { span: 24 },
        sm: { span: 6 },
      },
      wrapperCol: {
        xs: { span: 24 },
        sm: { span: 14 },
      },
    };
    const tailFormItemLayout = {
      wrapperCol: {
        xs: {
          span: 24,
          offset: 0,
        },
        sm: {
          span: 14,
          offset: 6,
        },
      },
    };

    return (

      <Form onSubmit={this.handleSubmit}>
          <Row>
           请在此输入对系统的建议
          </Row>
          <Row>
            <FormItem {...formItemLayout}>
              {getFieldDecorator('userName', {
                rules: [{ required: true, message: '请在此输入对系统的建议' }],
              })(
                <Input type="textarea" rows={8} placeholder="你的建议" />
            )}
            </FormItem>
          </Row>
          <FormItem {...tailFormItemLayout}>
            <Button type="primary" htmlType="submit" size="large">保存</Button>
          </FormItem>
        </Form>

    );
  }
}

export default Form.create({})(Advice);
