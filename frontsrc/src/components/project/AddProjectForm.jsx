import React from 'react';
import { Modal, Form, Icon, Input, Button } from 'antd';

import FormItemInfo from '../import/FormItemInfo';

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

    return (
      <Form layout="inline">
        <FormItemInfo
          getFieldDecorator={getFieldDecorator}
          label="第一主持单位"
          initValue={schoolName}
          attr={'schoolName'}
        />
        <FormItemInfo
          getFieldDecorator={getFieldDecorator}
          label="法定代表人"
          initValue={schoolHead}
          attr={'schoolHead'}
        />
        <FormItemInfo
          getFieldDecorator={getFieldDecorator}
          label="财务部门负责人"
          initValue={finaceHeader}
          attr={'finaceHeader'}
        />
        <FormItemInfo
          getFieldDecorator={getFieldDecorator}
          label="联系电话"
          initValue={finaceHeaderTel}
          attr={'finaceHeaderTel'}
        />
        <FormItemInfo
          getFieldDecorator={getFieldDecorator}
          label="QQ号码"
          initValue={finaceHeaderQq}
          attr={'finaceHeaderQq'}
        />
        <FormItemInfo
          getFieldDecorator={getFieldDecorator}
          label="项目负责人"
          initValue={projectHeader}
          attr={'projectHeader'}
        />
        <FormItemInfo
          getFieldDecorator={getFieldDecorator}
          label="联系电话"
          initValue={projectHeaderTel}
          attr={'projectHeaderTel'}
        />
        <FormItemInfo
          getFieldDecorator={getFieldDecorator}
          label="填表人"
          initValue={reporter}
          attr={'reporter'}
        />
        <FormItemInfo
          getFieldDecorator={getFieldDecorator}
          label="联系电话"
          initValue={reporterTel}
          attr={'reporterTel'}
        />
        <FormItemInfo
          getFieldDecorator={getFieldDecorator}
          label="QQ号码"
          initValue={reporterQq}
          attr={'reporterQq'}
        />
        <FormItemInfo
          getFieldDecorator={getFieldDecorator}
          label="联合主持单位"
          initValue={unionSchool}
          attr={'unionSchool'}
        />
        <FormItemInfo
          getFieldDecorator={getFieldDecorator}
          label="参与建设单位"
          initValue={partnerSchool}
          attr={'partnerSchool'}
        />
      </Form>
    );
  }
}

export default Form.create()(AddProjectModal);
