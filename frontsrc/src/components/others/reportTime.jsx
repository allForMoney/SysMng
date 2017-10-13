import { Form, InputNumber, Card, Button } from 'antd';
import React from 'react';
import { connect } from 'dva';
import FrameContent from '../common/FrameContent';

const FormItem = Form.Item;

class reportTime extends React.Component {
  componentDidMount=() => {
    this.props.dispatch({
      type: 'OtherModel/getReportTime',
    });
  }
  handleSubmit = () => {
    console.log('tij');
    this.props.form.validateFields((error, values) => {
      if (error) {
        return;
      }
      console.log(values);
      this.props.dispatch({
        type: 'OtherModel/saveReportTime',
        payload: values
      });
    });
  };

  render() {
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
    const { getFieldDecorator } = this.props.form;

    const {
      quarterOneSetting,
      quarterTwoSetting,
      quarterThreeSetting,
  quarterFourSetting,
    } = this.props.reportTimeObj;
    return (

      <Card title="上报时间设置">
        <Form>
          <FormItem
            {...formItemLayout}
            label="第一季度上报截止日期"
            hasFeedback
          >
            {getFieldDecorator('quarterOneSetting', {
              initialValue: quarterOneSetting
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
              initialValue: quarterTwoSetting
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
              initialValue: quarterThreeSetting
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
              initialValue: quarterFourSetting
            })(
              <InputNumber min={1} max={31} />
              )}
          </FormItem>
          <FormItem
            {...tailFormItemLayout}
          >
            <Button type="submit" onClick={this.handleSubmit}>保存</Button>
          </FormItem>
        </Form>
      </Card>

    );
  }
}

function mapStateToProps(state) {
  const { userType, userName } = state.baseModel;
  const { reportTimeObj } = state.OtherModel;
  return { userType, userName, reportTimeObj };
}

export default connect(mapStateToProps)(Form.create()(reportTime));
