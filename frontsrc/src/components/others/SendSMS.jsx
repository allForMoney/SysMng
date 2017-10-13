/**
 * 添加项目表单  我也不知道为什么放这里一个...
 */
import React from 'react';
import { Row, Col, Icon, Input, Button, Card } from 'antd';
import { connect } from 'dva';
import FrameContent from '../common/FrameContent';
import styles from '../../index.less';
import ProjectInfo from '../import/ProjectInfo';
import SMSForm from './SMSForm';

class SendSMS extends React.Component {
  state={
    projectNo: '',
  }

  onProjectValueChanged= (e) => {
    const projectNo = e.target.value;
    this.setState({ projectNo });
  }

  doSeachPro = () => {
    const { projectNo } = this.state;

    this.props.dispatch({
      type: 'OtherModel/getProjectInfo',
      payload: { projectNo },
    });
  }

  sendSMS = () => {
    this.smsForm.validateFields((err, values) => {
      if (err) {
        return;
      }
      this.props.dispatch({
        type: 'OtherModel/sendSMS',
        payload: values
      });
    });
  }


  render() {
    const {
      projectInfo,
      showSMSText,
    } = this.props;
    const {
      projectNo,
     } = this.state;
    return (
      <div>
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
            <ProjectInfo {...projectInfo} />
          </Card>
        </Row>
        {showSMSText &&
        <SMSForm
          ref={(form) => { this.smsForm = form; }}
          onSendSMS={this.sendSMS}
        />
        }
      </div>
    );
  }
}

function mapStateToProps(state) {
  const {
    showSMSText,
    projectInfo,
   } = state.OtherModel;

  return {
    projectInfo,
    showSMSText,
  };
}
export default connect(mapStateToProps)(SendSMS);
