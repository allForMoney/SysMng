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

  const {
     targetImplement,
     targetFirstYear,
     targetSecondYear,
     editable,
  } = props;
  return (
    <Row>
      <Col>
        <Form layout="horizontal">
          <FormItem label="实施期目标" {...formItemLayout} >
            {getFieldDecorator('targetImplement', {
              rules: [filterRules],
              initialValue: targetImplement
            })(<Input type="textarea" rows={4} disabled={!editable} />)}
          </FormItem>
          <FormItem label="年度目标(第一年)" {...formItemLayout}>
            {getFieldDecorator('targetFirstYear', {
              rules: [filterRules],
              initialValue: targetFirstYear
            })(<Input type="textarea" rows={4} disabled={!editable} />)}
          </FormItem>
          <FormItem label="年度目标(第二年)" {...formItemLayout}>
            {getFieldDecorator('targetSecondYear', {
              rules: [filterRules],
              initialValue: targetSecondYear
            })(<Input type="textarea" rows={4} disabled={!editable} />)}
          </FormItem>
        </Form>
      </Col>
    </Row>
  );
}

export default Form.create({})(AchiveTarget);
