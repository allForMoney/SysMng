/**
 * 预算导入16年版本
 */
import React from 'react';
import { connect } from 'dva';

import {
  Button,
  Row,
  Col,
  Card,
  Input,
  Upload,
  Icon,
  message,

} from 'antd';

import FrameContent from '../common/FrameContent';
import ProjectInfo from './ProjectInfo';
import styles from '../../index.less';

class ImportBudget16 extends React.Component {
  componentDidMount = () => {
    this.props.dispatch({
      type: 'ImportData/setState',
      payload: { projectInfo: {} },
    });
  }

  state={
    projectNo: '',
  }

  onProjectValueChanged= (e) => {
    const projectNo = e.target.value;
    this.setState({ projectNo });
  }

  doSeachPro = () => {
    const { projectNo } = this.state;
    console.log(projectNo);
    this.props.dispatch({
      type: 'ImportData/getProjectInfo',
      payload: { projectNo, attr: 'showUpload16' },
    });
  }

  saveForm = (form) => {
    this.form = form;
  }

  render() {
    const {
      showUpload16,
      projectInfo,
      userId,
    } = this.props;
    const {
      projectNo,
     } = this.state;

    const action = `/budget/import/${projectInfo.id}?importUser=${userId}&importType=yusuan2016`;

    const uploadProps = {
      name: 'file',
      action,
      headers: {
        authorization: 'authorization-text',
      },
      onChange(info) {
        if (info.file.status !== 'uploading') {
          console.log(info.file, info.fileList);
        }
        if (info.file.status === 'done') {
          message.success(`${info.file.name} 上传成功`);
        } else if (info.file.status === 'error') {
          message.error(`${info.file.name} 上传失败，失败原因：${info.file.response.message}`);
        }
      },
    };

    return (
      <Card title="预算导入(16年后)">
        <Row className={styles.baseRow}>
          说明：先输入项目编号，点查询，查询出来项目信息后，再选择文件并上传导入。
          <a href="/common/templete/download?type=BUDGET2016" target="_blank">导入模板下载</a>
        </Row>
        <Row className={styles.baseRow}>
          <Col span={4}>要导入预算的项目编号</Col>
          <Col span={6}>
            <Input value={projectNo} onChange={this.onProjectValueChanged} />
          </Col>
          <Col span={2}>
            <Button style={{ marginLeft: 10 }} type="primary" icon="search" onClick={this.doSeachPro}>查询</Button>
          </Col>
        </Row>
        <Row className={styles.baseRow}>
          <Card title="项目基本情况">
            <ProjectInfo ref={this.saveForm} {...projectInfo} />
          </Card>
        </Row>
        {showUpload16 &&
          <Row className="">
            <Upload {...uploadProps}>
              <Button>
                <Icon type="upload" /> 上传
            </Button>
            </Upload>
          </Row>
        }
      </Card>
    );
  }
}

function mapStateToProps(state) {
  const {
    userType,
    userName,
    projectId,
    projectName,
    userId,
   } = state.baseModel;
  const {
    projectInfo,
    showUpload16,
    loading,
   } = state.ImportData;

  return {
    userType,
    userId,
    userName,
    projectId,
    projectName,
    projectInfo,
    showUpload16,
    loading,
  };
}

export default connect(mapStateToProps)(ImportBudget16);
