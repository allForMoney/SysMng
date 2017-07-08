import { Modal, Form, Input } from 'antd';
import React, { PropTypes } from 'react';
import { connect } from 'dva';

const FormItem = Form.Item;

class ModifyPass extends React.Component {
  render() {
    const { visible, onCancel, onCreate, form } = this.props;
    const { getFieldDecorator } = form;
    const formItemLayout = {
      labelCol: { span: 6 },
      wrapperCol: { span: 14 },
    };

    const filterRules = {
      required: true,
      message: '不可为空'
    };

    return (
      <Modal
        visible={visible}
        title="修改密码"
        okText="保存"
        onCancel={onCancel}
        onOk={onCreate}
      >
        <Form layout="vertical">
          <FormItem label="用户名" {...formItemLayout} >
            {getFieldDecorator('userName', {
              initialValue: this.props.userName,
            })(<Input disabled />)}
          </FormItem>
          <FormItem label="原密码" {...formItemLayout}>
            {getFieldDecorator('oldPassword', {
              rules: [filterRules],
            })(<Input />)}
          </FormItem>
          <FormItem label="新密码" {...formItemLayout}>
            {getFieldDecorator('newPassword', {
              rules: [filterRules],
            })(<Input />)}
          </FormItem>
          <FormItem label="重复一次" {...formItemLayout}>
            {getFieldDecorator('repeatPass', {
              rules: [filterRules],
            })(<Input />)}
          </FormItem>
        </Form>
      </Modal>
    );
  }
}


ModifyPass.propTypes = {
  form: PropTypes.object,
};

export default Form.create({})(ModifyPass);

