import React, { PropTypes } from 'react';
import {
  Form,
  Input,
  Button,
  Upload,
  Icon,
  Modal,
  Row,
  Col,
  message,
  Popover,
} from 'antd';
import {
  SIGN_NAMESPACE,
  PUBLICNAMEREG,
  API_UPLOAD_FILES,
} from '../../configs/signConfigs';
import { CHANGE_STEP, CHECK_PUBLICNAME, JCROP_PIC } from '../../actions/signActions';
import styles from '../../less/sign.less';

// 图片裁剪实例对象
let jcropIns = null;

class SignStep2 extends React.Component {
  state = {
    previewVisible: false, // 图片预览弹窗展示与否
    previewImage: '', // 预览图片地址

    JcropVisible: false, // 图片裁剪显示与否
    JcropImage: '', // 裁剪图片地址

    cacheCoords: null, // 图片裁剪参数
    jcropAttr: '', // 当前裁剪的是图片是logo还是背景图

    logoErrorMsg: '请上传头像照片',
  };

  getPicWidthHeight = (url) => {
    const image = new Image();
    image.src = url;
    image.onload = () => {
      const orgWidth = image.naturalWidth;
      const orgHeight = image.naturalHeight;
      let cacheCoords = this.state.cacheCoords;
      cacheCoords = Object.assign({}, cacheCoords, { orgWidth, orgHeight });
      this.setState({ cacheCoords });
    };
  }

  handlePublcNameChanged = () => {
    const { getFieldValue, publicName } = this.props.form;
    const newValue = getFieldValue('publicName');
    if (newValue !== publicName) { // 校验生活号名称唯一性
      this.props.dispatch({
        type: `${SIGN_NAMESPACE}/${CHECK_PUBLICNAME}`,
        payload: { newValue },
      });
    }
  }

  handleCancel = () => this.setState({ previewVisible: false })

  handlePreview = (file) => {
    this.setState({
      previewImage: file.url || file.thumbUrl,
      previewVisible: true,
    });
  }

  handleHideJcrop = () => {
    this.setState({ JcropVisible: false, JcropImage: '' });
    jcropIns.destroy();
  }

  handleJcropDone = () => {
    const { cacheCoords, JcropImage, jcropAttr } = this.state;
    this.props.dispatch({
      type: `${SIGN_NAMESPACE}/${JCROP_PIC}`,
      payload: {
        xx: cacheCoords.x,
        yy: cacheCoords.y,
        x2: cacheCoords.x2,
        y2: cacheCoords.y2,
        width: cacheCoords.w,
        height: cacheCoords.h,
        avatarImage: JcropImage,
        orgWidth: cacheCoords.orgWidth,
        orgHeight: cacheCoords.orgHeight,
        jcropAttr,
      }
    });

    this.handleHideJcrop();
  }

  hangdleJCropPics = ({
    file,
    removePayload,
    defaultCoords,
    aspectRatio,
    minSize,
    maxSize,
  }) => {
    const { name, response, status } = file;
    if (status === 'removed') { // 删除文件
      this.props.dispatch({
        type: `${SIGN_NAMESPACE}/setState`,
        payload: removePayload
      });
      return;
    }

    const showCoords = (coords) => {
      let cacheCoords = this.state.cacheCoords;
      cacheCoords = Object.assign({}, cacheCoords, coords);
      this.setState({ cacheCoords });
    };

    if (response && status === 'done') {
      if (response.resultCode === 'succeed') {
        const url = response.result;
        this.getPicWidthHeight(url);
        this.setState({
          JcropVisible: true,
          JcropImage: url,
          cacheCoords: defaultCoords
        });
        $('#modal_Jcrp').Jcrop({
          bgOpacity: 0.6,
          onChange: showCoords,
          onSelect: showCoords,
          aspectRatio,
          minSize,
          maxSize,
          allowSelect: false,
          allowMove: true,
          allowResize: true,
        }, function(){
          this.setSelect([0, 0, 160, 160]);
          jcropIns = this;
        });
      } else {
        message.warning(`${name} 上传失败,请刷新页面或稍后重试`, 100);
      }
    }
  }

  handleLogoImgChanged = ({ file, fileList }) => {
    this.hangdleJCropPics({
      file,
      removePayload: { logoId: '', logoURL: '', logoFileList: [], showAddHeadImg: true },
      defaultCoords: {
        x: 0,
        x2: 160,
        y: 0,
        y2: 160,
        w: 160,
        h: 160
      },
      aspectRatio: 1,
      minSize: [40, 40],
      maxSize: [320, 320],
    });
    this.setState({ jcropAttr: 'logoURL' });
    this.props.dispatch({
      type: `${SIGN_NAMESPACE}/setState`,
      payload: { logoFileList: fileList, showAddHeadImg: !fileList.length }
    });
  }

  handleBgImgChanged = ({ file, fileList }) => {
    this.hangdleJCropPics({
      file,
      removePayload: { backgroundURL: '', bgFileList: [], showAddBgImg: true },
      defaultCoords: {
        x: 0,
        x2: 320,
        y: 0,
        y2: 200,
        w: 320,
        h: 200,
      },
      aspectRatio: 1.6,
      minSize: [80, 80],
      maxSize: [1600, 1000],
    });
    this.setState({ jcropAttr: 'backgroundURL' });
    this.props.dispatch({
      type: `${SIGN_NAMESPACE}/setState`,
      payload: { bgFileList: fileList, showAddBgImg: !fileList.length }
    });
  }

  goToward = (currentStep) => {
    const { validateFields } = this.props.form;
    if (currentStep > 0) {
      validateFields((err) => {
        let flag = true;
        if (err) {
          flag = false;
        }
        if (!this.props.publicNameAvailable) {
          flag = false;
        }

        if (!this.props.logoURL) {
          this.props.dispatch({
            type: `${SIGN_NAMESPACE}/setState`,
            payload: { showLogoError: true }
          });
          flag = false;
        }

        if (!this.props.backgroundURL) {
          this.props.dispatch({
            type: `${SIGN_NAMESPACE}/setState`,
            payload: { showBgError: true }
          });
          flag = false;
        }

        if (!flag) {
          return;
        }
        this.changeStep(currentStep);
      });
    } else {
      this.changeStep(currentStep);
    }
  }

  changeStep = currentStep => this.props.dispatch({
    type: `${SIGN_NAMESPACE}/${CHANGE_STEP}`,
    payload: { currentStep },
  });

  hanglePublicDesChanged = (e) => {
    let { value } = e.target;
    const publicDescNum = value.length;
    if (publicDescNum > 100) {
      value = value.substr(0, 100);
    }
    this.props.dispatch({
      type: `${SIGN_NAMESPACE}/setState`,
      payload: { publicDesc: value }
    });
    this.props.form.setFieldsValue({ publicDesc: value });
  }

  render() {
    const { previewVisible, previewImage, JcropVisible, JcropImage } = this.state;
    const uploadButton = (
      <div>
        <Icon type="plus" />
        <div className="ant-upload-text">上传文件</div>
      </div>
    );

    const { getFieldDecorator } = this.props.form;
    const formItemLayout = {
      labelCol: {
        sm: { span: 5 },
      },
      wrapperCol: {
        sm: { span: 19 },
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
          offset: 16,
        },
      },
    };
    // 生活号名称规则
    const publicNameInfo = (
      <div className={styles.infos}>
        生活号命名要求：<br />
        (1)当前支付宝账号实名认证名称为<span>{this.props.certifiedName}</span>，请尽量保证生活号名称相关性以便通过审核。<br />
        (2)不得含有违反法律法规和公序良俗的相关信息。<br />
        (3)不得侵害他人名誉权、知识产权、商业秘密等合法权利。<br />
        (4)以人物或以企业名称命名的，需要和支付宝账户实名信息保持一致；但不得以国家领导人名称命名。<br />
        (5)不得以存在明显歧义的词组来命名，如：小三。<br />
        (6)不得以太过广泛的、或产品、行业词组来命名，如：女装、皮革批发。<br />
        (7)不得以实名认证的媒体资质账号创建生活号，或媒体相关名称命名生活号，如：XX电视台、XX杂志等。<br />
        <p >详细的命名规则，请<a rel="noopener noreferrer" href="https://cshall.alipay.com/enterprise/help_detail.htm?help_id=500897" target="_blank" >点击这里</a>查看。</p>
      </div>);
    // 生活号头像要求
    const logoInfo = (
      <div className={styles.infos}>
        生活号头像要求：<br />
        (1)不得含有违反法律法规和公序良俗的相关信息。<br />
        (2)不得侵害他人名誉权、肖像权、知识产权、商业秘密等合法权利
      </div>);
    // 生活号头图背景要求
    const bgInfo = (
      <div className={styles.infos}>
        生活号头图背景要求：<br  />
        (1)不得含有违反法律法规和公序良俗的相关信息。<br />
        (2)不得侵害他人名誉权、肖像权、知识产权、商业秘密等合法权利。
      </div>);


    return (
      <Row>
        <Col>
          <Modal
            visible={JcropVisible}
            okText="保存"
            onCancel={this.handleHideJcrop}
            onOk={this.handleJcropDone}
          >
            <div className={styles.jcrop_pic}>
              <img alt="example" src={JcropImage} id="modal_Jcrp" />
            </div>
          </Modal>
        </Col>
        <Col span={16}>
          <h5 className={styles.titleStyle}>生活号基础信息</h5>
          <Form className={styles.step2form}>
            <Modal visible={previewVisible} footer={null} onCancel={this.handleCancel}>
              <img alt="example" style={{ width: '100%' }} src={previewImage} />
            </Modal>
            <Form.Item {...formItemLayout} label={'生活号名称'} >
              <Row>
                <Col span={20}>
                  { getFieldDecorator('publicName', {
                    initialValue: this.props.publicName,
                    rules: [{ required: true, message: '生活号名称不可为空' },
                    { max: 20, message: '请填写正确的生活号名称，2~20个字。' },
                    { min: 2, message: '请填写正确的生活号名称，2~20个字。' },
                    { pattern: PUBLICNAMEREG, message: '你输入的名称包含非法字符，请重新输入。' }],
                  })(
                    <Input onBlur={this.handlePublcNameChanged} />
                  )}
                </Col>
                <Col offset={1} span={3}>
                  <Popover
                    placement="right"
                    content={publicNameInfo}
                    trigger="hover"
                  >
                    <Icon type="question-circle" className={styles.infoIcon} />
                  </Popover>
                </Col>
              </Row>
              <Row>
                <Col span={24}>
                审核通过后终身仅允许修改1次，请谨慎填写
                </Col>
              </Row>
              { !this.props.publicNameAvailable
                &&
                <div className={styles.errorText}>你输入的名称已存在，请重新输入。</div>
              }
            </Form.Item>
            <Form.Item
              {...formItemLayout}
              label="设置头像"
            >
              { getFieldDecorator('logoURL', {
                rules: [],
              })(
                <Upload
                  action={API_UPLOAD_FILES}
                  listType="picture-card"
                  fileList={this.props.logoFileList}
                  data={{ imgDimensionType: 'logo', _uploader_: 'formdata' }}
                  name={'Filedata'}
                  accept={'jpg .jpge .png'}
                  onPreview={this.handlePreview}
                  onChange={this.handleLogoImgChanged}
                >
                  {this.props.showAddHeadImg ? uploadButton : null}
                </Upload>
              )}
              <Row>
                {this.props.showLogoError && <span className={styles.errorText}>{this.state.logoErrorMsg}</span>}
                <Col span={24}>
                审核通过后每月有1次修改机会
                </Col>
              </Row>
              <Row>
                <Col span={20}>
                建议尺寸320 x 320px，支持.jpg、.jpeg、.png格式，小于1M
                </Col>
                <Col offset={1} span={3}>
                  <Popover
                    placement="right"
                    content={logoInfo}
                    trigger="hover"
                  >
                    <Icon type="question-circle" className={styles.infoIcon} />
                  </Popover>
                </Col>
              </Row>
            </Form.Item>
            <Form.Item
              {...formItemLayout}
              label="设置背景图"
            >
              { getFieldDecorator('backgroundURL', {
                rules: [],
              })(
                <Upload
                  action={API_UPLOAD_FILES}
                  listType="picture-card"
                  accept={'jpg .jpge .png'}
                  data={{ imgDimensionType: 'logo', _uploader_: 'formdata' }}
                  name={'Filedata'}
                  fileList={this.props.bgFileList}
                  onPreview={this.handlePreview}
                  onChange={this.handleBgImgChanged}
                  className={styles.bgPicStyles}
                >
                  {this.props.showAddBgImg ? uploadButton : null}
                </Upload>
              )}
              <Row>
                {this.props.showBgError && <span className={styles.errorText}>请上传背景照片</span>}
                <Col span={24}>
                审核通过后每月有1次修改机会
                </Col>
              </Row>
              <Row>
                <Col span={20}>
                建议尺寸 1600 x 1000px，支持.jpg .jpeg .png 格式，小于1M
                </Col>
                <Col offset={1} span={2}>
                  <Popover
                    placement="right"
                    content={bgInfo}
                    trigger="hover"
                  >
                    <Icon type="question-circle" className={styles.infoIcon} />
                  </Popover>
                </Col>
              </Row>
            </Form.Item>
            <Form.Item
              {...formItemLayout}
              label="简介"
            >
              { getFieldDecorator('publicDesc', {
                initialValue: this.props.publicDesc,
                rules: [{ required: true, message: '不可为空' },
                        { max: 100, message: '简介最多为100个字' }],
              })(
                <Input type="hidden" />
              )}
              <Input type="textarea" rows={4} value={this.props.publicDesc} onChange={this.hanglePublicDesChanged} />
              <span className={styles.publicDescInfo}>{this.props.publicDesc.length}/100</span>
              <Row>
                <Col span={24}>
                审核通过后每月有1次修改机会
                </Col>
              </Row>
              <Row>
                <Col span={24}>
                请根据所属行业和经营范围填写合适的介绍信息，描述不清晰可能会影响审核结果，请谨慎填写。
                </Col>
              </Row>
            </Form.Item>
            <Form.Item {...tailFormItemLayout}>
              <Button className={styles.buttonStyle} type="primary" onClick={this.goToward.bind(this, 0)} size="large">返回</Button>
              <Button className={styles.buttonStyle} type="primary" onClick={this.goToward.bind(this, 2)} size="large">下一步</Button>
            </Form.Item>
          </Form>
        </Col>
        <Col span={1} push={3}>
          <div className={styles.separator} />
        </Col>
        <Col span={6} push={4}>
          <div className={styles.staticpic} />
        </Col>
      </Row>

    );
  }
}

SignStep2.propTypes = {
  publicName: PropTypes.string,
  publicDesc: PropTypes.string,
  form: PropTypes.object,
  publicNameAvailable: PropTypes.bool,
  logoURL: PropTypes.string,
  backgroundURL: PropTypes.string,
  showAddHeadImg: PropTypes.bool,
  showAddBgImg: PropTypes.bool,
  logoFileList: PropTypes.array,
  bgFileList: PropTypes.array,
  showLogoError: PropTypes.bool,
  showBgError: PropTypes.bool,
};

SignStep2.defaultProps = {
  publicDesc: '',
};

export default Form.create({})(SignStep2);
