import { Form, Input, Button, Row, Col } from 'antd';
import React, { PropTypes } from 'react';
import LinkBtn from '../common/LinkBtn';
import styles from '../../less/sign.less';
import { SIGN_NAMESPACE, ASKCODEINTERVAL, ASKCODEREG, PHONENUMREG, CONCATNAMEREG } from '../../configs/signConfigs';
import { SEND_SMSCODE } from '../../actions/signActions';

const FormItem = Form.Item;

class BaseInfo extends React.Component {
  state={
    isSendSMS: true, //是否允许发送短信验证码
    smsCode: '',
  };

  onValidateInputs = (attr) => {
    this.props.form.validateFields([attr], () => {
    });
  }

  checkData = (attr) => {
    let flag = true;
    this.props.form.validateFields([attr], (err) => {
      if (err) {
        flag = false;
      }
    });
    return flag;
  }

  cancelEdit = (attr, editAttr, newValueAttr) => {
    const { setFieldsValue } = this.props.form;
    setFieldsValue({
      [attr]: '',
    });
    this.props.dispatch({
      type: `${SIGN_NAMESPACE}/setState`,
      payload: {
        [editAttr]: false,
        [newValueAttr]: '',
      }
    });
  }

  /** 发送验证码 */
  handleSendSMSCode = () => {
    const isSendSMS = false;
    if (!this.checkData('mobile')) {
      return;
    }
    this.setState({ isSendSMS });
    const mobile = this.props.form.getFieldValue('mobile');
    // 发出action发送短信验证码
    this.props.dispatch({
      type: `${SIGN_NAMESPACE}/${SEND_SMSCODE}`,
      payload: { mobile }
    });
    // 一段时间后可以重新发短信验证码
    setTimeout(() => this.setState({ isSendSMS: true }), ASKCODEINTERVAL);
  };

  handleEdit = (payload) => {
    this.props.dispatch({
      type: `${SIGN_NAMESPACE}/setState`,
      payload
    });
  };

  handleSMSCodeChanged = (e) => {
    const { value } = e.target;
    if ((!isNaN(value) && ASKCODEREG.test(value)) || value === '') {
      this.setState({ smsCode: value });
      this.props.form.setFieldsValue({ SMSCode: value });
    }
  }

  render() {
    const { form } = this.props;
    const { getFieldDecorator } = form;
    const formItemLayout = {
      labelCol: { span: 8 },
      wrapperCol: { span: 16 },
    };

    return (
      <Form className={styles.baseInfoForm}>
        <FormItem label="联系人姓名" {...formItemLayout} >
          <Row gutter={1}>
            <Col span={12}>
              {getFieldDecorator('name', {
                rules: [{ required: true, message: '联系人姓名不可为空' },
                        { max: 15, message: '请填写正确的联系人姓名。' },
                        { pattern: CONCATNAMEREG, message: '请填写正确的联系人姓名。' }],
                initialValue: this.props.newConcatName,
              })(<Input
                type={this.props.editName ? 'text' : 'hidden'}
              />)}
              { !this.props.editName
                &&
                this.props.name
              }
            </Col>
            { !this.props.editName
              &&
              <Col span={12} pull={8}>
                <LinkBtn onClick={this.handleEdit.bind(this, { editName: true })} >修改</LinkBtn>
              </Col>
            }
            { this.props.editName && (this.props.name)
              &&
              <Col span={12} push={2}>
                <LinkBtn onClick={this.cancelEdit.bind(this, 'name', 'editName', 'newConcatName')} >取消修改</LinkBtn>
              </Col>
            }
          </Row>
        </FormItem>
        <FormItem label="手机号码" {...formItemLayout} >
          <Row >
            <Col span={12}>
              {getFieldDecorator('mobile', {
                rules: [{ required: true, message: '手机号码不可为空' },
                { pattern: PHONENUMREG, message: '请输入正确的手机号码' }],
                initialValue: this.props.newPhoneNum,
              })(<Input
                type={this.props.editPhone ? 'text' : 'hidden'}
              />)}
              { !this.props.editPhone
                &&
                this.props.mobile
              }
            </Col>
            { this.props.editPhone
              &&
              <Col span={5} push={1}>
                <Button
                  onClick={this.handleSendSMSCode}
                  disabled={!this.state.isSendSMS}
                >
                  获取验证码
                </Button>
              </Col>
            }
            { !this.props.editPhone
              &&
              <Col span={6} pull={6}>
                <LinkBtn onClick={this.handleEdit.bind(this, { editPhone: true })} >修改</LinkBtn>
              </Col>
            }
            { this.props.editPhone && (this.props.mobile)
              &&
              <Col span={6} push={3}>
                <LinkBtn onClick={this.cancelEdit.bind(this, 'mobile', 'editPhone', 'newPhoneNum')} >取消修改</LinkBtn>
              </Col>
            }
          </Row>
        </FormItem>
        { this.props.editPhone
          &&
          <FormItem label="短信验证码" {...formItemLayout}>
            <Row>
              <Col span={12}>
                {getFieldDecorator('SMSCode', {
                })(<Input type={'hidden'} />)}
                <Input
                  type={'text'}
                  value={this.state.smsCode}
                  onChange={this.handleSMSCodeChanged}
                />
              </Col>
            </Row>
          </FormItem>
        }

        <FormItem label="电子邮箱" {...formItemLayout}>
          <Row gutter={1}>
            <Col span={12}>
              {getFieldDecorator('email', {
                rules: [{ required: true, message: '电子邮箱不可为空' },
                        { type: 'email', message: '请填写正确的电子邮箱。' }],
                initialValue: this.props.newEmail,
              })(<Input
                type={this.props.editEmail ? 'text' : 'hidden'}
              />)}
              {!this.props.editEmail
              &&
              this.props.email
              }
            </Col>
            {!this.props.editEmail
            &&
            <Col span={12} pull={4}>
              <LinkBtn onClick={this.handleEdit.bind(this, { editEmail: true })} >修改</LinkBtn>
            </Col>
            }
            { this.props.editEmail && (this.props.email)
              &&
              <Col span={12} push={2}>
                <LinkBtn onClick={this.cancelEdit.bind(this, 'email', 'editEmail', 'newEmail')} >取消修改</LinkBtn>
              </Col>
            }
          </Row>
        </FormItem>
      </Form>
    );
  }
}


BaseInfo.propTypes = {
  form: PropTypes.object,
  editName: PropTypes.bool,
  editPhone: PropTypes.bool,
  editEmail: PropTypes.bool,
  name: PropTypes.string,
  email: PropTypes.string,
  mobile: PropTypes.string,
  newConcatName: PropTypes.string,
  newEmail: PropTypes.string,
  newPhoneNum: PropTypes.string,
};

export default Form.create({})(BaseInfo);
