import { Form, InputNumber, Button } from 'antd';
import React from 'react';
import { connect } from 'dva';
import FrameContent from '../common/FrameContent';

const FormItem = Form.Item;

function reportTime(props) {
  const formItemLayout = {
    labelCol: {
      sm: { span: 4 },
    },
    wrapperCol: {
      sm: { span: 6 },
    },
  };
  const tailFormItemLayout = {
    wrapperCol: {
      xs: {
        span: 4,
        offset: 0,
      },
      sm: {
        span: 4,
        offset: 6,
      },
    },
  };
  const { getFieldDecorator, validateFields } = props.form;
  const handleSubmit = () => {
    console.log('tij');
    validateFields((error, values) => {
      if (error) {
        return;
      }
      console.log(values);
      props.dispatch({
        type: 'OtherModel/saveReportTime',
        payload: values
      });
    });
  };
  return (
    <FrameContent>
      <Form>
        <FormItem
          {...formItemLayout}
          label="第一季度上报截止日期"
          hasFeedback
        >
          {getFieldDecorator('quarterOneSetting', {
          })(
            <InputNumber min={1} max={31} />
          )}
        </FormItem>
        <FormItem
          {...formItemLayout}
          label="第二季度上报截止日期"
          hasFeedback
        >
          {getFieldDecorator('quarterTwoSetting', {
          })(
            <InputNumber min={1} max={31} />
          )}
        </FormItem>
        <FormItem
          {...formItemLayout}
          label="第三季度上报截止日期"
          hasFeedback
        >
          {getFieldDecorator('quarterThreeSetting', {
          })(
            <InputNumber min={1} max={31} />
          )}
        </FormItem>
        <FormItem
          {...formItemLayout}
          label="第四季度上报截止日期"
          hasFeedback
        >
          {getFieldDecorator('quarterFourSetting', {
          })(
            <InputNumber min={1} max={31} />
          )}
        </FormItem>
        <FormItem
          {...tailFormItemLayout}
        >
          <Button type="submit" onClick={handleSubmit}>保存</Button>
        </FormItem>
      </Form>
    </FrameContent>
  );
}

function mapStateToProps(state) {
  const { userType, userName } = state.baseModel;
  return { userType, userName };
}

export default connect(mapStateToProps)(Form.create()(reportTime));
