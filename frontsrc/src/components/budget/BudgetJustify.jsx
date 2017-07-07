import React from 'react';
import { connect } from 'dva';

import {
  Button,
  Tag,
  Row,
  Col,
  Card,
  Select,
  Form,
  Upload,
  Icon,
message,
} from 'antd';

import FrameContent from '../common/FrameContent';
import LinkBtn from '../common/LinkBtn';
import styles from '../../index.less';

const FormItem = Form.Item;
const Option = Select.Option;

class BudgetJustify extends React.Component {
  state={
    showUpload: true,
    adjustType: 'adjust2016',
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
      if (rep.code === '1') {
        this.props.dispatch({
          type: 'BudgetJustifyModel/setState',
          payload: rep.result
        });
      }
    }, false);
    xhr.open('POST', uploadURl);
    // xhr.setRequestHeader("Content-Type", "multipart/form-data;");
    // 记得加入上传数据formData
    xhr.send(formData);
  }

  render() {
    const {
        userId,
        desFile, // 说明文件
        requestFile,  // 请求文件
        fileName, // 调整文件 xls
        projectInfo
    } = this.props;
    const { showUpload, adjustType } = this.state;

    // projectId  adjustUserId  adjustType
    const uploadURl = `/budgetadjust/import?projectId=${projectInfo.id}&adjustUserId=${userId}&adjustType=${adjustType}`;
    const formItemLayout = {
      labelCol: { span: 7 },
      wrapperCol: { span: 14 },
    };

    return (
      <FrameContent>
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
              <Col offset={16} span={7}>
                <Button onClick={this.downloadBudget}>当前预算表格下载</Button>
              </Col>
            </Row>
          </Card>
          {showUpload &&
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
      </FrameContent>
    );
  }
}

function mapStateToProps(state) {
  const {
    projectInfo,
    userId,
   } = state.baseModel;
  const {
    desFile, // 说明文件
    requestFile,  // 请求文件
    fileName, // 调整文件 xls
   } = state.BudgetJustifyModel;

  return {
    projectInfo,
    desFile, // 说明文件
    requestFile,  // 请求文件
    fileName, // 调整文件 xls
    userId
  };
}

export default connect(mapStateToProps)(BudgetJustify);
