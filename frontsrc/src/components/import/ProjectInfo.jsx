import React from 'react';
import { Card, Form, Icon, Input, Button } from 'antd';

const FormItem = Form.Item;

function ProjectInfo(props) {
  const { getFieldDecorator } = props.form;
  const handleSubmit = () => {
    console.log('submit!')
  }; 
  return (
    <Form layout="inline" onSubmit={handleSubmit}>
      <FormItem label="123">
        {getFieldDecorator('userName', {
        })(
          <Input placeholder="Username" />
        )}
      </FormItem>
      <FormItem label="123">
        {getFieldDecorator('userName', {
        })(
          <Input placeholder="Username" />
        )}
      </FormItem>
      <FormItem label="123">
        {getFieldDecorator('userName', {
        })(
          <Input placeholder="Username" />
        )}
      </FormItem>
      <FormItem label="123">
        {getFieldDecorator('userName', {
        })(
          <Input placeholder="Username" />
        )}
      </FormItem>
      <FormItem label="123">
        {getFieldDecorator('userName', {
        })(
          <Input placeholder="Username" />
        )}
      </FormItem>
      <FormItem label="123">
        {getFieldDecorator('userName', {
        })(
          <Input placeholder="Username" />
        )}
      </FormItem>
      <FormItem label="123">
        {getFieldDecorator('userName', {
        })(
          <Input placeholder="Username" />
        )}
      </FormItem>
      <FormItem label="123">
        {getFieldDecorator('userName', {
        })(
          <Input placeholder="Username" />
        )}
      </FormItem>
    </Form>
  );
}

export default Form.create()(ProjectInfo);
