import React from 'react';
import { Col, Form, Icon, Input, Button } from 'antd';

const FormItem = Form.Item;

function FormItemInfo(props) {
  const { getFieldDecorator, label, initValue, attr } = props;
  const formItemLayout = {
    labelCol: {
      sm: { span: 8 },
    },
    wrapperCol: {
      sm: { span: 16 },
    },
  };
  return (
    <Col span={8}>
      <FormItem label={label} {...formItemLayout}>
        {getFieldDecorator(attr, {
          initialValue: initValue
        })(
          <Input
            key={attr}
            value={initValue}
            size="small"
          />
        )}
      </FormItem>
    </Col>  
  );
}

export default FormItemInfo;
