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
    steps: 0,
    editable: this.props.userType === 'inputer', // 是否可编辑
    showCheckBtn: this.props.userType === 'finan' || this.props.userType === 'manager', // 是否展示审批菜单
  }

  saveChange= () => {
    this.cancelMsg();
  }

  doCheck= (flag) => { // flag=true,通过审核
    const { projectId, userType } = this.props;
    this.props.dispatch({
      type: 'achiveModel/changeCheckStatus',
      payload: {
        projectId,
        userType,
        status: flag
      }
    });
  }

  submit= () => {
    this.props.dispatch({
      type: 'achiveModel/saveAchiveTarget',
    });
  }

  goNext = () => {
    const { validateFields } = this.targetForm;
    validateFields((err, values) => {
      this.props.dispatch({
        type: 'achiveModel/setState',
        payload: values,
      });
      this.setState({ steps: 1 });
    });
  }

  render() {
    const {
      targetImplement,
      targetFirstYear,
      targetSecondYear,
      projectId,
      projectName,
      achiveTargetList,
      projectInfo,
      userName,
    } = this.props;
    const {
      steps,
      showCheckBtn,
      editable,
     } = this.state;
    return (
      <FrameContent>
        {steps === 0 &&
          <Card title="目标设定">
            <AchiveTarget
              ref={(form) => { this.targetForm = form; }}
              editable={editable}
              targetImplement={targetImplement}
              targetFirstYear={targetFirstYear}
              targetSecondYear={targetSecondYear}
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
            <Card title={`绩效指标[编号:${projectInfo.projectNo}, 名称:${projectInfo.majorName}]`}>
              <AchiveTargetList
                editable={editable}
                dataSource={achiveTargetList}
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
    projectName,
    projectInfo,
   } = state.baseModel;
  const {
     achiveTargetList,
     targetImplement,
     targetFirstYear,
     targetSecondYear,
   } = state.achiveModel;
  return {
    userType,
    projectInfo,
    userName,
    projectId,
    projectName,
    achiveTargetList,
    targetImplement,
    targetFirstYear,
    targetSecondYear,
  };
}

export default connect(mapStateToProps)(Achive);
