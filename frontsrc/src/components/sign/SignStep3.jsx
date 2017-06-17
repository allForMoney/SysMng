import React, { PropTypes } from 'react';
import { Form, Alert, Button, Upload, Icon, Modal, Row, Col, message, Select } from 'antd';
import { SIGN_NAMESPACE, API_UPLOAD_FILES, FILE_NEED_QULIFI } from '../../configs/signConfigs';
import { CHANGE_STEP, SUBMIT, GET_CLASSIFY } from '../../actions/signActions';
import styles from '../../less/sign.less';

const Option = Select.Option;

class SignStep3 extends React.Component {
  state = {
    previewVisible: false, // 图片预览弹窗展示与否
    previewImage: '', // 预览图片地址
    showSpecial: false, // 是否展示特殊资质证明
    showMccError: false, // 是否展示行业分类错误信息

    showAddSpeciBtn: !this.props.qualificationURL1, // 是否展示添加特殊资质证明图片按钮
    showAddIPRBtn: !this.props.ownIntellectualUrl, // 是否展示添加知识产权图片按钮
    showAddAttorneyBtn: !this.props.authorizationURL, // 是否展示添加授权运营书图片按钮

    noticeVisible: false, // 是否提示修改过 联系方式
    needUploadFiles: [], // 需要上传的文件种类,可能包括 qualification/ownIntellectua/authorization
  };

  getOwnIntellFile = () => {
    let ownList = [];
    const { ownIntellectualId, ownIntellectualUrl } = this.props;
    if (ownIntellectualId && ownIntellectualUrl) {
      ownList = [{
        uid: ownIntellectualId,
        status: 'done',
        url: ownIntellectualUrl,
      }];
    }
    return ownList;
  }

  getAuthorizationFileList = () => {
    let authList = [];
    const { authorizationId, authorizationURL, authorizationName } = this.props;
    if (authorizationId && authorizationURL) {
      authList = [{
        uid: authorizationId,
        name: authorizationName,
        status: 'done',
        url: authorizationURL,
      }];
    }
    return authList;
  }

  getQualifiFileList = () => {
    const qualiList = [];
    const {
      qualificationId1,
      qualificationURL1,
      qualificationName1,
      qualificationId2,
      qualificationURL2,
      qualificationName2,
      qualificationId3,
      qualificationURL3,
      qualificationName3,
    } = this.props;
    const generateFile = (uid, name, url) => ({ uid, name, url, status: 'done' })
    if (qualificationId1 && qualificationURL1) {
      qualiList.push(generateFile(qualificationId1, qualificationName1, qualificationURL1));
    }
    if (qualificationId2 && qualificationURL2) {
      qualiList.push(generateFile(qualificationId2, qualificationName2, qualificationURL2));
    }
    if (qualificationId3 && qualificationURL3) {
      qualiList.push(generateFile(qualificationId3, qualificationName3, qualificationURL3));
    }
    return qualiList;
  }

  getAttrByFileList = (fileList, urlAttr, idAttr, nameAttr) => {
    const obj = {};
    [1, 2, 3].forEach((value) => {
      obj[`${urlAttr}${value}`] = '';
      obj[`${idAttr}${value}`] = '';
      obj[`${nameAttr}${value}`] = '';
    });

    fileList.forEach((file, index) => {
      const { uid, name, response } = file;
      obj[`${urlAttr}${index + 1}`] = response.result;
      obj[`${idAttr}${index + 1}`] = uid;
      obj[`${nameAttr}${index + 1}`] = name;
    });
    return obj;
  }
  // 切换二级行业分类
  handleMcc2Change = (value, option) => {
    const mccIndustry0 = option.props.parentKey;
    // 获取行业分类
    this.props.dispatch({
      type: `${SIGN_NAMESPACE}/${GET_CLASSIFY}`,
      payload: { code: value }
    });
    // 保存一级/二级分类
    this.props.dispatch({
      type: `${SIGN_NAMESPACE}/setState`,
      payload: { mccIndustry0, mccIndustry1: value }
    });
  }
  // 切换三级行业分类
  handleMcc3Change = (value, option) => {
    let needUploadFiles = option.props.needUploadFiles;
    this.props.dispatch({
      type: `${SIGN_NAMESPACE}/setState`,
      payload: { mccIndustry2: value }
    });
    needUploadFiles = needUploadFiles || [];
    this.setState({
      needUploadFiles,
      showSpecial: needUploadFiles.includes(FILE_NEED_QULIFI),
      showMccError: false,
    });
  }

  handleCancel = () => this.setState({ previewVisible: false })

  handlePreview = (file) => {
    this.setState({
      previewImage: file.url || file.thumbUrl,
      previewVisible: true,
    });
  }

  updateState = (file, urlAttr, idAttr, nameAttr) => {
    const { uid, name, response, status } = file;
    if (response && response.stat === 'ok' && status) {
      switch (status) {
        case 'done':
          this.props.dispatch({
            type: `${SIGN_NAMESPACE}/setState`,
            payload: {
              [idAttr]: uid,
              [nameAttr]: name,
              [urlAttr]: response.result
            }
          });
          break;
        case 'removed':
          this.props.dispatch({
            type: `${SIGN_NAMESPACE}/setState`,
            payload: {
              [idAttr]: uid,
              [nameAttr]: name,
              [urlAttr]: response.result
            }
          });
          break;
        case 'error':
          message.warning(`${name} 上传失败,请刷新页面或稍后重试`, 100);
          break;
        default:
          break;
      }
    }
  }

  handleQualifiFileChange = ({ fileList, file }) => {
    let showAddSpeciBtn = false;
    if (fileList.length < 3) {
      showAddSpeciBtn = true;
    }
    this.setState({ showAddSpeciBtn });

    this.updateFileUrl(fileList, file, 'qualificationURL', 'qualificationId', 'qualificationName');
  }

  updateFileUrl = (fileList, file, urlAttr, idAttr, nameAttr) => {
    const { name, response, status } = file;
    if (response && response.stat === 'ok' && status) {
      switch (status) {
        case 'done':
          this.props.dispatch({
            type: `${SIGN_NAMESPACE}/setState`,
            payload: this.getAttrByFileList(fileList, urlAttr, idAttr, nameAttr)
          });
          break;
        case 'removed':
          this.props.dispatch({
            type: `${SIGN_NAMESPACE}/setState`,
            payload: this.getAttrByFileList(fileList, urlAttr, idAttr, nameAttr)
          });
          break;
        case 'error':
          message.warning(`${name} 上传失败,请刷新页面或稍后重试`, 100);
          break;
        default:
          break;
      }
    }
  }

  handleIPRPicChange = ({ fileList, file }) => {
    let showAddIPRBtn = false;
    if (fileList.length < 1) {
      showAddIPRBtn = true;
    }
    this.setState({ showAddIPRBtn });

    this.updateState(file, 'ownIntellectualUrl', 'ownIntellectualId');
  }

  handleAuthPicChange = ({ fileList, file }) => {
    let showAddAttorneyBtn = false;
    if (fileList.length < 1) {
      showAddAttorneyBtn = true;
    }
    this.setState({ showAddAttorneyBtn });

    this.updateState(file, 'authorizationURL', 'authorizationId', 'authorizationName');
  }

  goToward = (currentStep) => {
    this.props.dispatch({
      type: `${SIGN_NAMESPACE}/${CHANGE_STEP}`,
      payload: { currentStep },
    });
  }
  /** 提交审核 */
  preSubmit = () => {
    const { validateFields } = this.props.form;
    let flag = true;
    validateFields((err) => {
      if (err) {
        flag = false;
      }
    });
    if (!this.props.mccIndustry1 || !this.props.mccIndustry2) {
      flag = false;
      this.setState({ showMccError: true });
    }

    if (!flag) {
      return;
    }
    this.noticeEdit();
  }
  // 如果用户修改过联系方式,弹窗提示
  noticeEdit = () => {
    const { editPhone, editName, editEmail } = this.props;
    // TODO 此处判断有误 如果修改了联系方式,弹窗提示.如果没有,直接提交保存
    if (editPhone || editName || editEmail) {
      this.setState({
        noticeVisible: true,
      });
    } else {
      this.confirmSubmit();
    }
  }

  confirmSubmit = () => {
    this.setState({ noticeVisible: false });
    Modal.confirm({
      content: '请确认选择正确的所属行业，以及提交了相关资质，并且提供的服务和公司的经营范围有关联，否则可能审核不能通过。',
      okText: '确定',
      onOk: this.doSubmit,
    });
  }

  doSubmit = () => {
    const {
      editPhone,
      editName,
      editEmail,
      newConcatName,
      newEmail,
      newPhoneNum,
    } = this.props;
    let {
        name, // 联系人姓名
        email, // 联系人电子邮箱
        mobile, // 联系人手机号码
    } = this.props;

    if (editPhone) {
      mobile = newPhoneNum;
    }
    if (editEmail) {
      email = newEmail;
    }
    if (editName) {
      name = newConcatName;
    }
    this.props.dispatch({
      type: `${SIGN_NAMESPACE}/setState`,
      payload: { name, email, mobile }
    });
    this.props.dispatch({
      type: `${SIGN_NAMESPACE}/${SUBMIT}`,
    });
  }

  hideNoticeModal = () => {
    this.setState({
      noticeVisible: false,
    });
  }

  render() {
    const { previewVisible, previewImage } = this.state;
    const { getFieldDecorator } = this.props.form;
    const formItemLayout = {
      labelCol: {
        sm: { span: 24 },
      },
      wrapperCol: {
        sm: { span: 24 },
      },
    };
    const tailFormItemLayout = {
      wrapperCol: {
        xs: {
          span: 24,
          offset: 0,
        },
        sm: {
          span: 24,
          offset: 10,
        },
      },
    };
    const uploadButton = (
      <div>
        <Icon type="plus" />
        <div className="ant-upload-text">上传文件</div>
      </div>
    );
    const ownIntellFileList = this.getOwnIntellFile();
    const authorizationFileList = this.getAuthorizationFileList();
    const qualifiFileList = this.getQualifiFileList();
    const { mccCategories1, mccCategories2 } = this.props;
    const provinceOptions = mccCategories1.map(
        mcc => <Option key={mcc.categoryCode} parentKey={mcc.parentCode}>{mcc.categoryDesc}</Option>
        );
    const cityOptions = mccCategories2.map(
        mcc =>
          <Option
            key={mcc.categoryCode}
            needUploadFiles={mcc.needUploadFiles}
          >
            {mcc.categoryDesc}
          </Option>
        );

    return (
      <Form layout="vertical" >
        <Modal visible={previewVisible} footer={null} onCancel={this.handleCancel}>
          <img alt="example" style={{ width: '100%' }} src={previewImage} />
        </Modal>
        <Modal
          title="提示"
          visible={this.state.noticeVisible}
          onOk={this.confirmSubmit}
          width={400}
          onCancel={this.hideNoticeModal}
          okText="确认"
          cancelText="取消"
        >
          <Icon type="question-circle" className={styles.noticeIcon} />
          <p className={styles.noticeContent}>
            修改联系人信息，将会更新所有签约产品
            <a target="_blank" rel="noopener noreferrer" href="https://b.alipay.com/order/serviceIndex.htm">
            （查看我的产品订单）
            </a>
            的联系方式，是否确认修改？
          </p>
        </Modal>
        <Form.Item
          {...formItemLayout}
          label="选择所属行业"
        >
          {getFieldDecorator('mcc', {
            rules: []
          })(
            <div className="">
              <Select placeholder={'请选择'} style={{ width: 90, 'margin-right': 10 }} onSelect={this.handleMcc2Change}>
                {provinceOptions}
              </Select>
              <Select style={{ width: 90 }} onSelect={this.handleMcc3Change}>
                {cityOptions}
              </Select>
            </div>
          )}
          <Row>
            {this.state.showMccError && <span className={styles.errorText}>请选择所属行业</span>}
          </Row>
        </Form.Item>
        {
            this.state.showSpecial
            &&
            <Form.Item
              {...formItemLayout}
              label="特殊资质证明"
            >
              <Row>
                <Col span={24}>
                你所选择的行业可能需要特殊资质证明，上传证明可以增加审核通过率。
                </Col>
              </Row>
              { getFieldDecorator('qualifiPic', {
                rules: [{ validator: (rule, value, callback) => {
                  if (!value) {
                    callback('请上传特殊资质证明');
                    return;
                  }
                  if (value && value.length < 1) {
                    callback('请上传特殊资质证明');
                  }
                  if (value && value.fileList) {
                    if (value.fileList.length < 1) {
                      callback('请上传特殊资质证明');
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
                  listType="picture-card"
                  data={{ imgDimensionType: 'logo', _uploader_: 'formdata' }}
                  name={'Filedata'}
                  accept="image/jpg image/jpge image/png"
                  defaultFileList={qualifiFileList}
                  onPreview={this.handlePreview}
                  onChange={this.handleQualifiFileChange}
                >
                  { this.state.showAddSpeciBtn ? uploadButton : null }
                </Upload>
                )}
            </Form.Item>
        }
        <Form.Item
          {...formItemLayout}
          label="自有知识产权证明（可选）"
        >
          <Row>
            <Col span={24}>
            提供属于公司自有的品牌名、商标、形象等相关自有知识产权证明，可以增加审核通过率。
            </Col>
          </Row>
          { getFieldDecorator('IPR', {
            rules: [{ validator: (rule, value, callback) => {
              if (value && value.file && value.file.status === 'error') {
                callback('文件上传失败，请重试。');
              }
              callback();
            } }],
          })(
            <Upload
              action={API_UPLOAD_FILES}
              listType="picture-card"
              data={{ imgDimensionType: 'logo', _uploader_: 'formdata' }}
              name={'Filedata'}
              accept="image/jpg image/jpge image/png"             
              onPreview={this.handlePreview}
              defaultFileList={ownIntellFileList}
              onChange={this.handleIPRPicChange}
              className={'upload-list-inline'}
            >
              { this.state.showAddIPRBtn ? uploadButton : null }
            </Upload>
            )}
        </Form.Item>
        <Form.Item
          {...formItemLayout}
          label="运营授权书（可选）"
        >
          <Row>
            <Col span={24}>
            如果您使用的品牌名、logo和其他知识产品类信息非签约的主体公司所有，请下载<a href="https://i.alipayobjects.com/i/ecmng/zip/201408/shouquanshu.zip">授权运营书</a>，填写加盖公章后上传扫描件，否则可能无法通过审核。
            </Col>
          </Row>
          { getFieldDecorator('attorneyPic', {
            rules: [{ validator: (rule, value, callback) => {
              if (value && value.file && value.file.status === 'error') {
                callback('文件上传失败，请重试。');
              }
              callback();
            } }],
          })(
            <Upload
              action={API_UPLOAD_FILES}
              listType="picture-card"
              data={{ imgDimensionType: 'logo', _uploader_: 'formdata' }}
              name={'Filedata'}
              accept="image/jpg image/jpge image/png"
              defaultFileList={authorizationFileList}
              onPreview={this.handlePreview}
              onChange={this.handleAuthPicChange}
            >
              { this.state.showAddAttorneyBtn ? uploadButton : null }
            </Upload>
            )}
        </Form.Item>
        <Alert message="请确认正确的选择了所属行业，并提交对应的相关资质，否则可能无法通过审核。" type="info" showIcon />
        <Form.Item {...tailFormItemLayout}>
          <Button className={styles.buttonStyle} type="primary" onClick={this.goToward.bind(this, 1)} size="large">返回</Button>{'     '}
          <Button className={styles.buttonStyle} type="primary" onClick={this.preSubmit} size="large">提交审核</Button>
        </Form.Item>
      </Form>
    );
  }
}

SignStep3.propTypes = {
  form: PropTypes.object,
  editPhone: PropTypes.bool,
  editName: PropTypes.bool,
  editEmail: PropTypes.bool,

  ownIntellectualId: PropTypes.string, // 知识产权证明
  ownIntellectualUrl: PropTypes.string,
  authorizationId: PropTypes.string, // 授权书
  authorizationURL: PropTypes.string,
  authorizationName: PropTypes.string,
  qualificationId1: PropTypes.string, // 个人资质证明
  qualificationURL1: PropTypes.string,
  qualificationName1: PropTypes.string,
  qualificationId2: PropTypes.string,
  qualificationURL2: PropTypes.string,
  qualificationName2: PropTypes.string,
  qualificationId3: PropTypes.string,
  qualificationURL3: PropTypes.string,
  qualificationName3: PropTypes.string,

  mccCategories1: PropTypes.array,
  mccCategories2: PropTypes.array,
  newConcatName: PropTypes.string,
  newEmail: PropTypes.string,
  newPhoneNum: PropTypes.string,
  name: PropTypes.string,
  email: PropTypes.string,
  mobile: PropTypes.string,
};

SignStep3.defaultProps = {
  mccCategories1: [],
  mccCategories2: [],
};

export default Form.create({})(SignStep3);
