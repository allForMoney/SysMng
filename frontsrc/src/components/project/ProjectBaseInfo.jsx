import React from 'react';
import { connect } from 'dva';

import {
  Button,
  Row,
  Col,
  Card,
  Modal,
  Upload,
  Tag,
  Icon,
} from 'antd';
import FrameContent from '../common/FrameContent';
import LinkBtn from '../common/LinkBtn';

class ProjectBaseInfo extends React.Component {
  state={
    showAllUnit: false,
    buildUnit: [1,2,3,4], // this.props.buildUnit,
    showChangeModal: false,
    promise: null,
  }

  cancelChange = () => {
    this.setState({ showChangeModal: false });
  }

  showMorebuild = () => {
    this.setState({ showAllUnit: true });
  }

  hideMorebuild = () => {
    this.setState({ showAllUnit: false });
  }

  getUnitTags= () => {
    const { partnerSchool } = this.props.projectInfo;
    const { showAllUnit } = this.state;
    let tags = [];
    if (showAllUnit) {
      tags = partnerSchool.split(',').map(item => <Tag>{item}</Tag>);
    } else {
      tags = partnerSchool.split(',').map((item, index) => {
        if (index < 3) {
          return <Tag>{item}</Tag>;
        }
      }
     );
    }
    return tags;
  }

  render() {
    const {
      projectInfo,
    } = this.props;
    const {
      schoolName,
      schoolHead,
      finaceHeader,
      finaceHeaderTel,
      finaceHeaderQq,
      projectHeader,
      projectHeaderTel,
      reporter,
      reporterTel,
      reporterQq,
      unionSchool,
      projectNo,
      majorName,
    } = projectInfo;
    const {
      showAllUnit,
      showChangeModal,
     } = this.state;

    const buildUnitTags = this.getUnitTags();

    return (
      <FrameContent>
        <Card title={`[编号: ${projectNo}, 名称: ${majorName}]`}>
          <Modal
            title="变更申请"
            visible={showChangeModal}
            footer={null}
            onOk={this.saveChange}
            onCancel={this.cancelChange}
          >
            <Upload
              action={'//jsonplaceholder.typicode.com/posts/'}
              beforeUpload={this.beforeUpload}
            >
              <Button>
                <Icon type="upload" /> 上传文件
              </Button>
            </Upload>
          </Modal>
          <Card>
            该信息为只读信息，如有疑义，请
            <Button
              onClick={
              () => this.props.dispatch({
                type: 'baseModel/setState',
                payload: { showMsgModal: true }
              })
              }
            >留言</Button>
            。 如须变更，请点击
            <Button onClick={() => this.setState({ showChangeModal: true })}>变更申请</Button>办理手续。
          </Card>
          <Card title="第一主持单位">
            {schoolName}
          </Card>
          <Card title="联合主持单位">
            {unionSchool && '无'}
          </Card>
          <Card title="参与建设单位">
            { buildUnitTags }
            {!showAllUnit &&
            <LinkBtn onClick={this.showMorebuild}>更多</LinkBtn>
            }
            {showAllUnit &&
            <LinkBtn onClick={this.hideMorebuild}>收起</LinkBtn>
            }
          </Card>
          <Card title="第一主持单位">
            <Row>
              <Col offset={1} span={4}>法定代表人</Col>
              <Col span={4}>{schoolHead}</Col>
            </Row>
            <Row>
              <Col offset={1} span={4}>项目负责人</Col>
              <Col span={4}>{projectHeader}</Col>
              <Col span={4}>联系电话</Col>
              <Col span={4}>{projectHeaderTel}</Col>
            </Row>
            <Row>
              <Col offset={1} span={4}>财务部门负责人</Col>
              <Col span={4}>{finaceHeader}</Col>
              <Col span={4}>联系电话</Col>
              <Col span={4}>{finaceHeaderTel}</Col>
              <Col span={4}>QQ号码</Col>
              <Col span={4}>{finaceHeaderQq}</Col>
            </Row>
            <Row>
              <Col offset={1} span={4}>填报人</Col>
              <Col span={4}>{reporter}</Col>
              <Col span={4}>联系电话</Col>
              <Col span={4}>{reporterTel}</Col>
              <Col span={4}>QQ号码</Col>
              <Col span={4}>{reporterQq}</Col>
            </Row>
          </Card>
        </Card>
      </FrameContent>
    );
  }
}

function mapStateToProps(state) {
  const { userType, userName } = state.baseModel;
  const {
    projectInfo,
  } = state.ProjectModel;
  return { userType, userName, projectInfo };
}

export default connect(mapStateToProps)(ProjectBaseInfo);
