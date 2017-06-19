import React from 'react';
import { connect } from 'dva';

import {
  Button,
  Tag,
  Row,
  Col,
  Card,
  Modal,
  Input,
  Upload,
  Icon,

} from 'antd';
import FrameContent from '../common/FrameContent';

class BudgetBaseInfo extends React.Component {
  state={
    showAllUnit: false,
    buildUnit: [1,2,3,4], // this.props.buildUnit,
    showChangeModal: false,
    promise: null,
  }

  saveChange= () => {
    this.cancelMsg();
  }

  render() {
    const {
      unit1,
      unit2,
      buildUnit,
      lawName,
      rename,userName,
      phone,
    } = this.props;
    const {
      showAllUnit,
      showChangeModal,
     } = this.state;
    return (
      <FrameContent>
        <Modal
          title="变更申请"
          visible={showChangeModal}
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
          {unit1}
        </Card>
        <Card title="联合主持单位">
          {unit2 && '无'}
        </Card>
        <Card title="联合主持单位">
          {unit2}
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
            <Col span={4}>{userName}</Col>
          </Row>
          <Row>
            <Col offset={1} span={4}>项目负责人</Col>
            <Col span={4}>{userName}</Col>
            <Col span={4}>联系电话</Col>
            <Col span={4}>{phone}</Col>
          </Row>
          <Row>
            <Col offset={1} span={4}>财务部门负责人</Col>
            <Col span={4}>{userName}</Col>
            <Col span={4}>联系电话</Col>
            <Col span={4}>{phone}</Col>
            <Col span={4}>QQ号码</Col>
            <Col span={4}>{phone}</Col>
          </Row>
          <Row>
            <Col offset={1} span={4}>填报人</Col>
            <Col span={4}>{userName}</Col>
            <Col span={4}>联系电话</Col>
            <Col span={4}>{phone}</Col>
            <Col span={4}>QQ号码</Col>
            <Col span={4}>{phone}</Col>
          </Row>
        </Card>
      </FrameContent>
    );
  }
}

function mapStateToProps(state) {
  const { userType, userName } = state.baseModel;
  return { userType, userName };
}

export default connect(mapStateToProps)(BudgetBaseInfo);
