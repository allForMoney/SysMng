import { Form, Input, Button } from 'antd';
import React from 'react';
import { connect } from 'dva';
import FrameContent from '../common/FrameContent';

const FormItem = Form.Item;

function reportTime() {
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
  const { getFieldDecorator } = this.props.form;

  return (
    <FrameContent>
      <Form onSubmit={this.handleSubmit}>
        <FormItem
          {...formItemLayout}
          label="第一季度上报截止日期"
          hasFeedback
        >
          {getFieldDecorator('season1', {
          })(
            <Input />
          )}
        </FormItem>
        <FormItem
          {...tailFormItemLayout}
        >
        <Button></Button>
        </FormItem>
      </Form>
    </FrameContent>
  );
}

function mapStateToProps(state) {
  const { userType, userName } = state.OtherModel;
  return { userType, userName };
}

export default connect(mapStateToProps)(Form.create()(reportTime));
