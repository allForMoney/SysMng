import React from 'react';
import {
  Form,
  Input,
  Button,
  Row,
  Col,
} from 'antd';
import { connect } from 'dva';
import FrameContent from '../common/FrameContent';

const FormItem = Form.Item;

class ModifyConcat extends React.Component {

  handleSubmit = () => {
    const { validateFields } = this.props.form;
    validateFields((error, values) => {
      if (error) {
        return;
      }
      this.props.dispatch({
        type: 'baseModel/updateConcat',
        payload: values
      });
    });
  }

  render() {
    const { getFieldDecorator } = this.props.form;
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
    const tailFormItemLayout = {
      wrapperCol: {
        xs: {
          span: 24,
          offset: 0,
        },
        sm: {
          span: 14,
          offset: 6,
        },
      },
    };

    const { projectInfo } = this.props;
    const {
      majorName,
      schoolHead,
      finaceHeader,
      finaceHeaderTel,
      projectHeader,
      projectHeaderTel,
      reporter,
      reporterTel,
    } = projectInfo;
    const filterRules = {
      required: true,
      message: '不可为空'
    };
    return (

      <Form onSubmit={this.handleSubmit}>
        <Row>
          <Col span={10}>
            <FormItem
                {...formItemLayout}
                label="项目名称"
                hasFeedback
              >
                {getFieldDecorator('majorName', {
                  initialValue: majorName,
                })(<Input disabled type="text" />)}
              </FormItem>
          </Col>
          <Col span={10}>
            <FormItem
                {...formItemLayout}
                label="法定代表人"
                hasFeedback
              >
                {getFieldDecorator('schoolHead', {
                  initialValue: schoolHead,
                })(<Input disabled type="text" />)}
              </FormItem>
          </Col>
        </Row>
        <Row>
          <Col span={10}>
            <FormItem
                {...formItemLayout}
                label="项目负责人"
                hasFeedback
              >
                {getFieldDecorator('projectHeader', {
                  initialValue: projectHeader,
                })(<Input disabled type="text" />)}
              </FormItem>
          </Col>
          <Col span={10}>
            <FormItem
                {...formItemLayout}
                label="联系电话"
                hasFeedback
              >
                {getFieldDecorator('projectHeaderTel', {
                  rules: [filterRules],
                  initialValue: projectHeaderTel,
                })(<Input type="text" />)}
              </FormItem>
          </Col>
        </Row>
        <Row>
          <Col span={10}>
            <FormItem
                {...formItemLayout}
                label="财务部门负责人"
                hasFeedback
              >
                {getFieldDecorator('finaceHeader', {
                  initialValue: finaceHeader,
                })(<Input disabled type="text" />)}
              </FormItem>
          </Col>
          <Col span={10}>
            <FormItem
                {...formItemLayout}
                label="联系电话"
                hasFeedback
              >
                {getFieldDecorator('finaceHeaderTel', {
                  rules: [filterRules],
                  initialValue: finaceHeaderTel,
                })(<Input type="text" />)}
              </FormItem>
          </Col>
        </Row>
        <Row>
          <Col span={10}>
            <FormItem
                {...formItemLayout}
                label="填报人"
                hasFeedback
              >
                {getFieldDecorator('reporter', {
                  initialValue: reporter,
                })(<Input disabled type="text" />)}
              </FormItem>
          </Col>
          <Col span={10}>
            <FormItem
                {...formItemLayout}
                label="联系电话"
                hasFeedback
              >
                {getFieldDecorator('reporterTel', {
                  rules: [filterRules],
                  initialValue: reporterTel,
                })(<Input type="text" />)}
              </FormItem>
          </Col>
        </Row>
        <FormItem {...tailFormItemLayout}>
          <Button type="primary" htmlType="submit" size="large">保存</Button>
        </FormItem>
      </Form>

    );
  }
}
function mapStateToProps(state) {
  const { userType, userName, projectInfo } = state.baseModel;
  return { userType, userName, projectInfo };
}
export default connect(mapStateToProps)(Form.create({})(ModifyConcat));

