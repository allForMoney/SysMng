import React from 'react';
import { connect } from 'dva';

import {
  Button,
  Input,
  Row,
  Col,
  Card,
  Select,
  Form,
message,
} from 'antd';

import FrameContent from '../common/FrameContent';
import LinkBtn from '../common/LinkBtn';
import styles from '../../index.less';
import BudgetJustifyCheck from './BudgetJustifyCheck';

const FormItem = Form.Item;
const Option = Select.Option;

class BudgetJustify extends React.Component {
  state={
    showUpload: false,
    adjustType: 'adjust2016',
    auditOpin: ''
  }
  componentDidMount() {
    this.props.dispatch({
      type: 'BudgetJustifyModel/getLastAdjust',
    });
  }

  downloadBudget= () => {
    const { projectInfo } = this.props;
    const url = `/budget/download/${projectInfo.id}`;
    window.open(url);
    this.setState({
      showUpload: true,
    });
  }

  onAdjustTypeChanged= (value) => {
    this.setState({
      adjustType: value,
    });
  }

  getCheckVisible = (userType, auditStatus) => {
    let cancelVisible = false;
    let passVisible = false;
    if (userType === 'finace') {
      if (auditStatus === 0) {
        passVisible = true;
      }
      if (auditStatus === 1) {
        cancelVisible = true;
      }
    }
    if (userType === 'school') {
      if (auditStatus === 1) {
        passVisible = true;
      }
      if (auditStatus === 3) {
        cancelVisible = true;
      }
    }
    if (userType === 'country') {
      if (auditStatus === 3) {
        passVisible = true;
      }
      if (auditStatus === 5) {
        cancelVisible = true;
      }
    }

    return {
      cancelVisible,
      passVisible
    };
  }

  submitFiles= () => {
    const xhr = new XMLHttpRequest();
    // 将上传的多个文件放入formData中
    const picFileList = document.getElementsByTagName('input');
    const {
        userId,
        projectInfo
    } = this.props;
    const { adjustType } = this.state;

    const uploadURl = `/budgetadjust/import?projectId=${projectInfo.id}&adjustUserId=${userId}&adjustType=${adjustType}`;

    const formData = new FormData();
    for (let i = 0; i < picFileList.length; i++) {
      formData.append('file', picFileList[i].files[0]);
    }
    // 监听事件
    xhr.addEventListener('error', () => message.warn('文件上传失败'), false);// 发送文件和表单自定义参数
    xhr.addEventListener('load', (evt) => {
      const rep = JSON.parse(evt.target.response);
      console.log(rep);
      if (rep.code === '1') {
        this.props.dispatch({
          type: 'BudgetJustifyModel/setState',
          payload: rep.result
        });
      } else if (rep.code === '0') {
        message.error(rep.msg);
      } else if (rep.status === 500) {
        message.error(rep.message);
      }
    }, false);
    xhr.open('POST', uploadURl);
    // xhr.setRequestHeader("Content-Type", "multipart/form-data;");
    // 记得加入上传数据formData
    xhr.send(formData);
  }

  onAuditContentChanged = (value) => {
    this.setState({
      auditOpin: value,
    });
  }

  render() {
    const {
        userId,
        desFile, // 说明文件
        requestFile,  // 请求文件
        fileName, // 调整文件 xls
        projectInfo,
        userType,
        auditStatus,
    } = this.props;
    const { showUpload, adjustType, auditOpin } = this.state;

    // projectId  adjustUserId  adjustType
    const uploadURl = `/budgetadjust/import?projectId=${projectInfo.id}&adjustUserId=${userId}&adjustType=${adjustType}`;
    const formItemLayout = {
      labelCol: { span: 7 },
      wrapperCol: { span: 14 },
    };

    const { cancelVisible, passVisible } = this.getCheckVisible(userType, auditStatus);

    return (
      <div>
        {userType === 'inputer' &&
          <Card title="预算调整申请">
            <p>使用说明：先下载当前预算表格，在其中做出修改后，再上传。</p>
            <Card title="已上传附件">
              <Row>
                <Col offset={2} span={7}>
                预算调整请示: <a href={`budgetadjust/download/file?projectId=${projectInfo.id}&fileName=${requestFile}`} target="_blank" rel="noopener noreferrer">{requestFile}</a>
                </Col>
                <Col span={7}>
                拟调整后的预算表: <a href={`budgetadjust/download/file?projectId=${projectInfo.id}&fileName=${fileName}`} target="_blank" rel="noopener noreferrer">{fileName}</a>
                </Col>
                <Col span={7}>
                预算调整说明: <a href={`budgetadjust/download/file?projectId=${projectInfo.id}&fileName=${desFile}`} target="_blank" rel="noopener noreferrer">{desFile}</a>
                </Col>
              </Row>
              <Row>
                <Col offset={16} span={7} style={{ margin: 10 }}>
                  <Button onClick={this.downloadBudget}>当前预算表格下载</Button>
                </Col>
              </Row>
            </Card>
            {showUpload && userType === 'inputer' &&
              <Card title="上传预算调整申请文件">
                <Form >
                  <FormItem label="预算模板" {...formItemLayout}>
                    <Select defaultValue="adjust2016" onChange={this.onAdjustTypeChanged} style={{ display: 'block', width: 120 }}>
                      <Option value="adjust2016">2016版预算</Option>
                      <Option value="yusuan2">2015版预算</Option>
                    </Select>
                  </FormItem>
                  <FormItem label="预算调整请示:" {...formItemLayout}>
                    <input name="file" type="file" className={styles.uploadInput} />
                  </FormItem>
                  <FormItem label="拟调整后的预算表:" {...formItemLayout}>
                    <input name="file" type="file" className={styles.uploadInput} />
                  </FormItem>
                  <FormItem label="预算调整请示:" {...formItemLayout}>
                    <input name="file" type="file" className={styles.uploadInput} />
                  </FormItem>
                  <FormItem wrapperCol={{ span: 10, offset: 4 }}>
                    <Button type="primary" onClick={this.submitFiles}>提交</Button>
                    <Button type="primary" htmlType="reset" className={styles.uploadBtn} >重置</Button>
                  </FormItem>
                </Form>
              </Card>
            }
          </Card>
        }
        {
          userType !== 'inputer' &&
          <BudgetJustifyCheck />
        }
      </div>
    );
  }
}

function mapStateToProps(state) {
  const {
    projectInfo,
    userId,
    userType,
   } = state.baseModel;
  const {
    desFile, // 说明文件
    requestFile,  // 请求文件
    fileName, // 调整文件 xls
    id,
   } = state.BudgetJustifyModel;

  return {
    projectInfo,
    id,
    userType,
    desFile, // 说明文件
    requestFile,  // 请求文件
    fileName, // 调整文件 xls
    userId
  };
}

export default connect(mapStateToProps)(BudgetJustify);
