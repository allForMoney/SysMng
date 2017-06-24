import React from 'react';
import { Card, Form, Icon, Input, Button } from 'antd';

const FormItem = Form.Item;

function FormItemInfo(props) {
  const { getFieldDecorator, label, initValue, attr } = props;
  return (
    <FormItem label={label}>
      {getFieldDecorator(attr, {
        initialValue: initValue
      })(
        <Input
          key={attr}
        />
      )}
    </FormItem>
  );
}

export default FormItemInfo;
