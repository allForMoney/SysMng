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
} from 'antd';

import FrameContent from '../common/FrameContent';
import LinkBtn from '../common/LinkBtn';

class BudgetBaseInfo extends React.Component {
  state={
    showAllUnit: false,
    buildUnit: [1,2,3,4], // this.props.buildUnit,
    showMsgModal: false,
    showChangeModal: false,
  }

  getBuildUnit = () => {
    const { showAllUnit, buildUnit } = this.state;
    const buildUnitTags = [];
    const length = buildUnit.length;
    if (showAllUnit) { // 展示所有
      for (let i = 0; i < length; i= i + 3) {
        buildUnitTags.push(
          <Row>
            <Col span={6}>
              <Tag>
                {buildUnit[i]}
              </Tag>
            </Col>
            <Col span={6}>
              <Tag>
                {buildUnit[i + 1]}
              </Tag>
            </Col>
            <Col span={6}>
              <Tag>
                {buildUnit[i + 2]}
              </Tag>
            </Col>
          </Row>
        );
      }
    } else {
      const [a, b, c] = buildUnit;
      buildUnitTags.push(
        <Row>
          <Col span={6}>
            <Tag>
              {a}
            </Tag>
          </Col>
          <Col span={6}>
            <Tag>
              {b}
            </Tag>
          </Col>
          <Col span={6}>
            <Tag>
              {c}
            </Tag>
          </Col>
        </Row>
      );

    }
    return buildUnitTags;
  }
  
  showMorebuild = () => {
    this.setState({ showAllUnit: true });
  }

  hideMorebuild= () => {
    this.setState({ showAllUnit: false });
  }

  cancelMsg= () => {
    this.setState({ showMsgModal: false, msgValue: '' });
  }

  cancelChange= () => {
    this.setState({ showChangeModal: false, uploadFile: '' });
  }

  saveMsg= () => {
    const { msgValue } = this.state;
    console.log(msgValue);
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
    const buildUnitTags = this.getBuildUnit();
    const {
      showAllUnit,
      showMsgModal,
      showChangeModal,
      msgValue,
      uploadFile,
     } = this.state;
    return (
      <FrameContent>
        <Modal
          title="留言信息"
          visible={showMsgModal}
          onOk={this.saveMsg}
          onCancel={this.cancelMsg}
        >
          <Input
            type="textarea"
            rows={8}
            placeholder="你的留言"
            value={msgValue}
            onChange={e => this.setState({ msgValue: e.target.value })}
          />
        </Modal>
        <Modal
          title="变更申请"
          visible={showChangeModal}
          onOk={this.saveChange}
          onCancel={this.cancelChange}
        >
          <Input
            type="textarea"
            rows={8}
            placeholder="你的留言"
            value={msgValue}
            onChange={e => this.setState({ msgValue: e.target.value })}
          />
        </Modal>
        <Card>
          该信息为只读信e息，如有疑义，请
          <Button onClick={() => this.setState({ showMsgModal: true })}>留言</Button>
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