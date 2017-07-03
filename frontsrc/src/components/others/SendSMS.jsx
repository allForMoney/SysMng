/**
 * 添加项目表单  我也不知道为什么放这里一个...
 */
import React from 'react';
import { Row, Col, Icon, Input, Button } from 'antd';
import { connect } from 'dva';
import FrameContent from '../common/FrameContent';
import styles from '../../index.less';

class SendSMS extends React.Component {
  state={
    projectNo: '',
  }
  render() {
    const {
      showUpload16,
      projectInfo,
    } = this.props;
    const {
      projectNo,
     } = this.state;
    return (
      <FrameContent>
        <Row className={styles.baseRow}>
          说明：先输入项目编号，点查询，查询出来项目信息后，再输入短信内容发送短信。
        </Row>
        <Row className={styles.baseRow}>
          <Col span={4}>项目编号</Col>
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
      </FrameContent>
    );
  }
}

function mapStateToProps(state) {
  const {
    userType,
    userName,
    projectId,
    projectName
   } = state.baseModel;
  const {
    projectInfo,
    showUpload16,
    loading,
   } = state.ImportData;

  return {
    userType,
    userName,
    projectId,
    projectName,
    projectInfo,
    showUpload16,
    loading,
  };
}
export default connect(mapStateToProps)(SendSMS);
