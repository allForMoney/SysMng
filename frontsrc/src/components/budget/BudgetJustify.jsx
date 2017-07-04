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
import LinkBtn from '../common/LinkBtn';

class BudgetJustify extends React.Component {
  state={
    showAllUnit: false,
  }

  saveChange= () => {
    this.cancelMsg();
  }

  render() {
    const {
        URL1,
        url2,
        url3,
        projectId
    } = this.props;
    const {
      showAllUnit,
      showChangeModal,
     } = this.state;
    return (
      <FrameContent>
        <Card title="预算调整申请">
          <p>使用说明：先下载当前预算表格，在其中做出修改后，再上传。</p>
          <Card title="已上传附件">
            <Row>
              <Col offset={2} span={7}>
               预算调整请示: <a href={URL1} target="_blank" rel="noopener noreferrer">{URL1}</a>
              </Col>
              <Col span={7}>
               拟调整后的预算表: <a href={URL1} target="_blank" rel="noopener noreferrer">{URL1}</a>
              </Col>
              <Col span={7}>
               预算调整请示: <a href={URL1} target="_blank" rel="noopener noreferrer">{URL1}</a>
              </Col>
            </Row>
            <Row>
              <Col offset={16} span={7}>
                <a href={`/Budget/ExportByProject?projectId=${projectId}`}>当前预算表格下载</a>
              </Col>
            </Row>
          </Card>

        </Card>
      </FrameContent>
    );
  }
}

function mapStateToProps(state) {
  const {
    URL1,
    url2,
    url3,
    projectId } = state.baseModel;
  return {
    URL1,
    url2,
    url3,
    projectId };
}

export default connect(mapStateToProps)(BudgetJustify);
