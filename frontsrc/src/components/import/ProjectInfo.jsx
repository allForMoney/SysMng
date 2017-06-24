import React from 'react';
import { Card, Form, Icon, Input, Button } from 'antd';

import FormItemInfo from './FormItemInfo';

const FormItem = Form.Item;

function ProjectInfo(props) {
  const { getFieldDecorator } = props.form;
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
  } = props;
  
  return (
    <Form layout="inline">
      <FormItemInfo
        getFieldDecorator={getFieldDecorator}
        label="第一主持单位"
        initValue={schoolName}
      />
      <FormItemInfo
        getFieldDecorator={getFieldDecorator}
        label="法定代表人"
        initValue={schoolHead}
      />
      <FormItemInfo
        getFieldDecorator={getFieldDecorator}
        label="财务部门负责人"
        initValue={finaceHeader}
      />
      <FormItemInfo
        getFieldDecorator={getFieldDecorator}
        label="联系电话"
        initValue={finaceHeaderTel}
      />
      <FormItemInfo
        getFieldDecorator={getFieldDecorator}
        label="QQ号码"
        initValue={finaceHeaderQq}
      />
      <FormItemInfo
        getFieldDecorator={getFieldDecorator}
        label="项目负责人"
        initValue={projectHeader}
      />
      <FormItemInfo
        getFieldDecorator={getFieldDecorator}
        label="联系电话"
        initValue={projectHeaderTel}
      />
      <FormItemInfo
        getFieldDecorator={getFieldDecorator}
        label="填表人"
        initValue={reporter}
      />
      <FormItemInfo
        getFieldDecorator={getFieldDecorator}
        label="联系电话"
        initValue={reporterTel}
      />
      <FormItemInfo
        getFieldDecorator={getFieldDecorator}
        label="QQ号码"
        initValue={reporterQq}
      />
      <FormItemInfo
        getFieldDecorator={getFieldDecorator}
        label="联合主持单位"
        initValue={unionSchool}
      />
      <FormItemInfo
        getFieldDecorator={getFieldDecorator}
        label="参与建设单位"
        initValue={partnerSchool}
      />
    </Form>
  );
}

export default Form.create()(ProjectInfo);
