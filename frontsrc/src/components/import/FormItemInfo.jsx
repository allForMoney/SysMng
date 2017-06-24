import React from 'react';
import { Card, Form, Icon, Input, Button } from 'antd';

const FormItem = Form.Item;

function FormItemInfo(props) {
  const { getFieldDecorator, label, initValue } = props;
  return (
    <FormItem label={label}>
      {getFieldDecorator('schoolName', {
        initialValue: initValue
      })(
        <Input />
      )}
    </FormItem>
  );
}

export default FormItemInfo;
