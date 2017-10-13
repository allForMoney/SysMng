/**
 * 绩效目标导入
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

class ImportAchive extends React.Component {
  state={
    projectNo: '',
  }
  componentDidMount = () => {
    this.props.dispatch({
      type: 'ImportData/setState',
      payload: { projectInfo: {} },
    });
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
      payload: { projectNo, attr: 'showUploadAchive' },
    });
  }

  saveForm = (form) => {
    this.form = form;
  }

  render() {
    const {
      showUploadAchive,
      projectInfo,
      userId,
    } = this.props;
    const {
      projectNo,
     } = this.state;

    const action = `
    /indicator/import?projectId=${projectInfo.id}&importUser=${userId}&importType=1`;

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
          message.success(`${info.file.name} file uploaded successfully`);
        } else if (info.file.status === 'error') {
          message.error(`${info.file.name} file upload failed.`);
        }
      },
    };

    return (

      <Card title="绩效导入">
        <Row className={styles.baseRow}>
          说明：先输入项目编号，点查询，查询出来项目信息后，再选择文件并上传导入。
          <a href="/common/templete/download?type=JXMB" target="_blank">导入模板下载</a>
        </Row>
        <Row className={styles.baseRow}>
          <Col span={4}>要导入绩效的项目编号</Col>
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
        {showUploadAchive &&
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
    showUploadAchive,
    projectInfo,
    loading,
   } = state.ImportData;

  return {
    userType,
    userName,
    projectId,
    projectName,
    projectInfo,
    showUploadAchive,
    userId,
    loading,
  };
}

export default connect(mapStateToProps)(ImportAchive);
