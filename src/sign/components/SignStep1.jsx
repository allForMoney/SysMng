import React, { PropTypes } from 'react';
import {
  Button,
  Upload,
  Checkbox,
  Row,
  Col,
  message,
  Form,
  Modal,
  Icon
} from 'antd';
import {
  SIGN_NAMESPACE,
  API_UPLOAD_FILES
} from '../configs/signConfigs';
import BaseInfo from './BaseInfo';
import { CHANGE_STEP, CHECK_SMSCODE } from '../actions/signActions';
import styles from '../sign.less';


class SignStep1 extends React.Component {
  state = {
    showAddLicenceBtn: !this.props.privateLicenseURL,
    previewVisible: false,
    previewImage: '',
  }
  // 勾选/反选 同意协议
  onAgreeChanged = (e) => {
    const checked = e.target.checked;
    this.props.dispatch({
      type: `${SIGN_NAMESPACE}/setState`,
      payload: { isAgreed: checked }
    });
  }

  getLicenceList = () => {
    let licenceList = [];
    const { privateLicenseId, privateLicenseURL, privateLicenseName } = this.props;
    if (privateLicenseId && privateLicenseURL) {
      licenceList = [{
        uid: privateLicenseId,
        name: privateLicenseName,
        status: 'done',
        url: privateLicenseURL,
      }];
    }
    return licenceList;
  }

  handleCancel = () => this.setState({ previewVisible: false });

  checkBaseInfo = () => {
    const { editPhone, editName, editEmail } = this.props;
    const { getFieldValue, validateFields } = this.props.form;
    let flag = true;
    // 如果编辑基本信息,校验之
    if (editPhone) {
      const smsCode = getFieldValue('SMSCode');
      const newPhoneNum = getFieldValue('mobile');
      if (!newPhoneNum) {
        validateFields(['mobile'], () => {});
        return false;
      }
      if (!smsCode) {
        message.info('请填写正确的短信验证码。');
        return false;
      }
      flag = false;
      this.props.dispatch({
        type: `${SIGN_NAMESPACE}/${CHECK_SMSCODE}`,
        payload: { mobile: newPhoneNum, askCode: smsCode }
      });
    } else if (!this.props.name) {
      message.info('请输入联系人名称');
      return false;
    }

    if (editName) {
      const name = getFieldValue('name');
      if (!name) {
        validateFields(['name'], () => {});
        return false;
      }
      this.props.dispatch({
        type: `${SIGN_NAMESPACE}/setState`,
        payload: { newConcatName: name }
      });
    }
    if (editEmail) {
      const newEmail = getFieldValue('email');
      if (!newEmail) {
        validateFields(['email'], () => {});
        return false;
      }
      this.props.dispatch({
        type: `${SIGN_NAMESPACE}/setState`,
        payload: { newEmail }
      });
    }
    return flag;
  }

  checkValue = () => {
    const { validateFields } = this.props.form;
    // 个人用户需要校验营业执照
    if (this.props.isPerson && !this.props.privateLicenseURL) {
      validateFields(['licencePic'], () => { });
      return;
    }
    // 勾选协议
    if (!this.props.isAgreed) {
      message.info('请勾选同意协议');
      return;
    }
    // 校验基本信息和短信验证码
    const validated = this.checkBaseInfo();
    validated && this.goNext();
  }

  goNext = () => {
    this.props.dispatch({
      type: `${SIGN_NAMESPACE}/${CHANGE_STEP}`,
      payload: { currentStep: 1 },
    });
  }

  handleLicenceImgChanged = ({ file, fileList }) => {
    let showAddLicenceBtn = false;
    if (fileList.length < 1) {
      showAddLicenceBtn = true;
    }
    this.setState({ showAddLicenceBtn });

    const { uid, name, response, status } = file;
    if (response && response.stat) {
      if (response.stat !== 'ok') { // 失败了
        fileList.pop();
        if (fileList.length < 1) {
          this.setState({ showAddLicenceBtn: true });
        }
        message.error(response.msg || `${name} 上传失败,请刷新页面或稍后重试`, 10);
      } else {
        switch (status) {
          case 'done':
            this.props.dispatch({
              type: `${SIGN_NAMESPACE}/setState`,
              payload: {
                privateLicenseId: uid,
                privateLicenseName: name,
                privateLicenseURL: response.result
              }
            });
            break;
          case 'removed':
            this.props.dispatch({
              type: `${SIGN_NAMESPACE}/setState`,
              payload: {
                privateLicenseId: '',
                privateLicenseName: '',
                privateLicenseURL: ''
              }
            });
            break;
          case 'error':
            message.warning(`${name} 上传失败,请刷新页面或稍后重试`, 10);
            break;
          default:
            break;
        }
      }
    }
  }

  handlePreview = (file) => {
    this.setState({
      previewImage: file.url || file.thumbUrl,
      previewVisible: true,
    });
  }

  render() {
    const { previewVisible, previewImage } = this.state;
    const uploadButton = (
      <div>
        <Icon type="plus" />
        <div className="ant-upload-text">上传文件</div>
      </div>
    );

    const { getFieldDecorator } = this.props.form;
    const formItemLayout = {
      labelCol: { span: 8 },
      wrapperCol: { span: 16 },
    };

    const licenceList = this.getLicenceList();

    return (
      <Form>
        <Modal visible={previewVisible} footer={null} onCancel={this.handleCancel}>
          <img alt="example" style={{ width: '100%' }} src={previewImage} />
        </Modal>
        <Form.Item {...formItemLayout} label={this.props.isPerson ? '个人姓名' : '企业名称'} >
          {this.props.merchantCompanyName}
        </Form.Item>
        { this.props.isPerson
          &&
          <Form.Item
            {...formItemLayout}
            label="营业执照"
          >
            <Row>
              <Col span={24}>
              请上传你的营业执照,可上传可上传照片或者影印件，请参考<a href="https://i.alipayobjects.com/i/ecmng/zip/201408/getiyingyezhizhao-m.zip" target="_blank" rel="noopener noreferrer">执照示例</a>
              </Col>
            </Row>
            <Row>
              <Col span={24}>
              建议尺寸320 x 320px，支持.jpg、.jpeg、.png格式，小于1M
              </Col>
            </Row>
            { getFieldDecorator('licencePic', {
              rules: [{ validator: (rule, value, callback) => {
                if (!value) {
                  callback('请上传营业执照');
                  return;
                }
                if (value && value.length < 1) {
                  callback('请上传营业执照');
                  return;
                }
                if (value && value.fileList) {
                  if (value.fileList.length < 1) {
                    callback('请上传营业执照');
                  }
                }
                if (value && value.file && value.file.status === 'error') {
                  callback('文件上传失败，请重试。');
                }
                callback();
              } }],
            })(
              <Upload
                action={API_UPLOAD_FILES}
                method={'get'}
                listType="picture-card"
                data={{ imgDimensionType: 'logo', _uploader_: 'formdata' }}
                defaultFileList={licenceList}
                name={'Filedata'}
                accept="image/jpg image/jpge image/png"
                onPreview={this.handlePreview}
                onChange={this.handleLicenceImgChanged}
              >
                {this.state.showAddLicenceBtn ? uploadButton : null}
              </Upload>
            )}
          </Form.Item>
        }
        <Form.Item
          {...formItemLayout}
          label="联系人信息"
        >
          <Row>
            <Col span={24}>
              联系人只用于支付宝官方与你联系使用，不会呈现在生活号页面
            </Col>
          </Row>
          <Row>
            <Col span={24}>
              <BaseInfo dispatch={this.props.dispatch} {...this.props} />
            </Col>
          </Row>
        </Form.Item>
        <Form.Item wrapperCol={{ span: 16, offset: 7 }} >
          <Row>
            <Col span={1}>
              { getFieldDecorator('isAgree', {
              })(
                <Checkbox onChange={this.onAgreeChanged} checked={this.props.isAgreed} />
              )}
            </Col>
            <Col span={23}>
              同意
              <a
                href="/platform/agreementLife.htm"
                target="_blank"
                rel="noopener noreferrer"
              >
                《支付宝生活号协议》
              </a>
            </Col>
          </Row>
        </Form.Item>
        <Form.Item wrapperCol={{ span: 16, offset: 12 }}>
          <Button className={styles.buttonStyle} type="primary" onClick={this.checkValue} size="large">下一步</Button>
        </Form.Item>
      </Form>
    );
  }
}

SignStep1.propTypes = {
  getFieldDecorator: PropTypes.func,
  merchantCompanyName: PropTypes.string,
  editPhone: PropTypes.bool,
  editName: PropTypes.bool,
  editEmail: PropTypes.bool,
  isAgreed: PropTypes.bool,
  form: PropTypes.object,
  privateLicenseURL: PropTypes.string,
  privateLicenseId: PropTypes.string,
  privateLicenseName: PropTypes.string,
  isPerson: PropTypes.bool,

};

export default Form.create({})(SignStep1);

