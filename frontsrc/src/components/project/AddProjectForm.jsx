import React from 'react';
import { Modal, Form, Icon, Input, Button } from 'antd';

import FormItemInfo from './FormItemInfo';

class AddProjectModal extends React.Component  {
  render() {
    const { getFieldDecorator } = this.props.form;
    const {
      schoolName,
      schoolHead,
      finaceHeader,
      finaceHeaderTel,
      finaceHeaderQq,
      projectHeader,
      projectHeaderTel,
      reporter,
      reporterTel,
      reporterQq,
      unionSchool,
      partnerSchool,
    } = this.props;

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

    return (
      <Form layout="horizontal">
        <FormItemInfo
          getFieldDecorator={getFieldDecorator}
          label="项目编号"
          initValue={projectNo}
          attr={'projectNo'}
          {...formItemLayout}
        />
        <FormItemInfo
          getFieldDecorator={getFieldDecorator}
          label="专业名称"
          initValue={schoolName}
          attr={'schoolName'}
          {...formItemLayout}
        />
        <FormItemInfo
          getFieldDecorator={getFieldDecorator}
          label="立项年度"
          initValue={schoolName}
          attr={'schoolName'}
          {...formItemLayout}
        />
        <FormItemInfo
          getFieldDecorator={getFieldDecorator}
          label="第一主持单位"
          initValue={schoolName}
          attr={'schoolName'}
          {...formItemLayout}
        />
        <FormItemInfo
          getFieldDecorator={getFieldDecorator}
          label="法定代表人"
          initValue={schoolHead}
          {...formItemLayout}
          attr={'schoolHead'}
        />
        <FormItemInfo
          getFieldDecorator={getFieldDecorator}
          label="财务部门负责人"
          {...formItemLayout}
          initValue={finaceHeader}
          attr={'finaceHeader'}
        />
        <FormItemInfo
          getFieldDecorator={getFieldDecorator}
          label="联系电话"
          {...formItemLayout}
          initValue={finaceHeaderTel}
          attr={'finaceHeaderTel'}
        />
        <FormItemInfo
          getFieldDecorator={getFieldDecorator}
          {...formItemLayout}
          label="QQ号码"
          initValue={finaceHeaderQq}
          attr={'finaceHeaderQq'}
        />
        <FormItemInfo
          getFieldDecorator={getFieldDecorator}
          label="项目负责人"
          {...formItemLayout}
          initValue={projectHeader}
          attr={'projectHeader'}
        />
        <FormItemInfo
          getFieldDecorator={getFieldDecorator}
          label="联系电话"
          {...formItemLayout}
          initValue={projectHeaderTel}
          attr={'projectHeaderTel'}
        />
        <FormItemInfo
          {...formItemLayout}
          getFieldDecorator={getFieldDecorator}
          label="填表人"
          initValue={reporter}
          attr={'reporter'}
        />
        <FormItemInfo
          getFieldDecorator={getFieldDecorator}
          label="联系电话"
          {...formItemLayout}
          initValue={reporterTel}
          attr={'reporterTel'}
        />
        <FormItemInfo
          {...formItemLayout}
          getFieldDecorator={getFieldDecorator}
          label="QQ号码"
          initValue={reporterQq}
          attr={'reporterQq'}
        />
        <FormItemInfo
          getFieldDecorator={getFieldDecorator}
          {...formItemLayout}
          label="联合主持单位"
          initValue={unionSchool}
          attr={'unionSchool'}
        />
        <FormItemInfo
          getFieldDecorator={getFieldDecorator}
          label="参与建设单位"
          {...formItemLayout}
          initValue={partnerSchool}
          attr={'partnerSchool'}
        />
      </Form>
    );
  }
}

export default Form.create()(AddProjectModal);
