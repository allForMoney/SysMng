import React from 'react';
import {
  Row,
  Col,
  Input,
  Form,
 } from 'antd';

const FormItem = Form.Item;

function AchiveTarget(props) {
  const { getFieldDecorator } = props.form;
  const filterRules = {
    required: true,
    message: '不可为空'
  };
  const formItemLayout = {
    labelCol: { span: 6 },
    wrapperCol: { span: 14 },
  };
  return (
    <Row>
      <Col>
        <Form layout="horizontal">
          <FormItem label="实施期目标" {...formItemLayout} >
            {getFieldDecorator('target1', {
              rules: [filterRules],
              initialValue: props.target1
            })(<Input type="textarea" rows={4} />)}
          </FormItem>
          <FormItem label="年度目标(第一年)" {...formItemLayout}>
            {getFieldDecorator('target2', {
              rules: [filterRules],
              initialValue: props.target2
            })(<Input type="textarea" rows={4} />)}
          </FormItem>
          <FormItem label="年度目标(第二年)" {...formItemLayout}>
            {getFieldDecorator('target3', {
              rules: [filterRules],
              initialValue: props.target3
            })(<Input type="textarea" rows={4} />)}
          </FormItem>
        </Form>
      </Col>
    </Row>
  );
}

export default Form.create({})(AchiveTarget);
