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
import AchiveTarget from './AchiveTarget';
import AchiveTargetList from './AchiveTargetList';
import styles from '../../index.less';

class Achive extends React.Component {
  state={
    showChangeModal: false,
    steps: 1,
    editable: this.props.userType === 'inputer', // 是否可编辑
    showCheckBtn: this.props.userType === 'finan' || this.props.userType === 'manager', // 是否展示审批菜单
  }

  saveChange= () => {
    this.cancelMsg();
  }

  doCheck= (flag) => {// flag=true,通过审核
    
  }

  goNext = () => {
    this.setState({ steps: 1 });
  }

  render() {
    const {
      target1,
      target2,
      target3,
      projectId,
      projectName,
    } = this.props;
    const {
      showChangeModal,
      steps,
      showCheckBtn,
      editable,
     } = this.state;
    return (
      <FrameContent>
        <Modal
          title="填写具体目标"
          visible={showChangeModal}
          onOk={this.saveChange}
          onCancel={this.cancelChange}
        >222
        </Modal>
        {steps === 0 &&
          <Card title="目标设定">
            <AchiveTarget
              target1={target1}
              target2={target2}
              target3={target3}
            />
            <Col className={styles.btnContainer}>
              <Button className={styles.btnClass} type="primary" onClick={this.goNext}>下一步</Button>
            </Col>
          </Card>
        }
        {steps === 1 &&
          <div className="">
            {showCheckBtn &&
            <Col className={styles.btnContainer}>
              <Button className={styles.btnClass} type="primary" onClick={this.doCheck.bind(this, false)}>返回上一级</Button>
              <Button className={styles.btnClass} type="primary" onClick={this.doCheck.bind(this, true)}>通过审核</Button>
            </Col>
            }
            <Card title={`绩效指标[编号:${projectId}, 名称:${projectName}]`}>
              <AchiveTargetList
                // editable={editable}
                editable
              />
            </Card>
            <Col className={styles.btnContainer}>
              <Button className={styles.btnClass} type="primary" onClick={() => this.setState({ steps: 0 })}>返回</Button>
              {!showCheckBtn &&
                <Button className={styles.btnClass} type="primary" onClick={this.submit}>保存</Button>
              }
            </Col>
          </div>
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
  return {
    userType,
    userName,
    projectId,
    projectName,
  };
}

export default connect(mapStateToProps)(Achive);
