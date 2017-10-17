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
    showUnionUnit: false,
    buildUnit: [1, 2, 3, 4], // this.props.buildUnit,
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

  changeMoreUnionDis = () => {
    this.setState({ showUnionUnit: this.state.showUnionUnit });
  }

  getUnitTags= () => {
    const { partnerSchool } = this.props.projectInfo;
    const { showAllUnit } = this.state;
    let tags = [];
    if (!partnerSchool) {
      return tags;
    }
    if (showAllUnit) {
      tags = partnerSchool.split('|').map(item => <Tag>{item}</Tag>);
    } else {
      tags = partnerSchool.split('|').map((item, index) => {
        if (index < 3) {
          return <Tag>{item}</Tag>;
        }
      }
     );
    }
    return tags;
  }
  getUnionUnitTgs= () => {
    const { unionSchool } = this.props.projectInfo;
    const { showUnionUnit } = this.state;
    let tags = [];
    if (!unionSchool) {
      return tags;
    }
    if (showUnionUnit) {
      tags = unionSchool.split('|').map(item => <Tag>{item}</Tag>);
    } else {
      tags = unionSchool.split('|').map((item, index) => {
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
      showUnionUnit,
      showChangeModal,
     } = this.state;

    const buildUnitTags = this.getUnitTags();
    const buildUnionUnitTags = this.getUnionUnitTgs();
    //加粗字体颜色
    const borderColor = "#1DA57A";

    let title = <div style = {{color :borderColor}}>{`[编号: ${projectNo}, 名称: ${majorName}]`} </div>;
    return (
      <Card title={title}>
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
        <div>
            该信息为只读信息，如有疑义，请
            <Button
              onClick={
              () => this.props.dispatch({
                type: 'baseModel/setState',
                payload: {
                  showMsgModal: true,
                  msgType: 'project',
                  mesType: 1,
                }
              })
              }
            >留言</Button>
            。 如须变更，请点击
            <Button onClick={() => this.setState({ showChangeModal: true })}>变更申请</Button>办理手续。
          </div>
        <Card title={<div style = {{color :borderColor}}>第一主持单位</div>} bodyStyle = {{padding:"12px 32px"}}>
          {schoolName}
        </Card>
        <Card title={<div style = {{color :borderColor}}>联合主持单位</div>} bodyStyle = {{padding:"12px 32px"}}>
          { buildUnionUnitTags }
          {!showUnionUnit &&(buildUnionUnitTags.length>3)&&
          <LinkBtn onClick={this.changeMoreUnionDis}>更多</LinkBtn>
          }
          {showUnionUnit &&
          <LinkBtn onClick={this.changeMoreUnionDis}>收起</LinkBtn>
          }
        </Card>
        <Card title={<div style = {{color :borderColor}}>参与建设单位</div>} bodyStyle = {{padding:"12px 32px"}}>
          { buildUnitTags }
          {!showAllUnit &&(buildUnitTags.length>3)&&
            <LinkBtn onClick={this.showMorebuild}>更多</LinkBtn>
            }
          {showAllUnit &&
            <LinkBtn onClick={this.hideMorebuild}>收起</LinkBtn>
            }
        </Card>
        <Card title={<div style = {{color :borderColor}}>第一主持单位</div>}  bodyStyle = {{padding:"12px 32px"}}>
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
            <Col span={3}>QQ号码</Col>
            <Col span={4}>{finaceHeaderQq}</Col>
          </Row>
          <Row>
            <Col offset={1} span={4}>填报人</Col>
            <Col span={4}>{reporter}</Col>
            <Col span={4}>联系电话</Col>
            <Col span={4}>{reporterTel}</Col>
            <Col span={3}>QQ号码</Col>
            <Col span={4}>{reporterQq}</Col>
          </Row>
        </Card>
      </Card>

    );
  }
}

function mapStateToProps(state) {
  const { userType, userName, projectInfo } = state.baseModel;
  return { userType, userName, projectInfo };
}

export default connect(mapStateToProps)(ProjectBaseInfo);
