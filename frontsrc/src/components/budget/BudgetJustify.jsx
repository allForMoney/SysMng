import React from 'react';
import { connect } from 'dva';

import {
  Button,
  Tag,
  Row,
  Col,
  Card,
  Select,
  Input,
  Upload,
  Icon,

} from 'antd';

import FrameContent from '../common/FrameContent';
import LinkBtn from '../common/LinkBtn';

const Option = Select.Option;

class BudgetJustify extends React.Component {
  state={
    showUpload: false,
    adjustType: 'adjust2016',
  }

  downloadBudget= () => {
    const { projectInfo } = this.props;
    const url = `/budget/download/${projectInfo.id}`;
    window.open(url);
    this.setState({
      showUpload: true,
    });
  }

  onAdjustTypeChanged= (value) => {
    this.setState({
      adjustType: value,
    });
  }

  render() {
    const {
        URL1,
        url2,
        url3,
        userId,
        projectInfo
    } = this.props;
    const { showUpload, adjustType } = this.state;

    // projectId  adjustUserId  adjustType
    const uploadProps = {
      action: `/budgetadjust/import?projectId=${projectInfo.id}&adjustUserId=${userId}&adjustType=${adjustType}`,
      multiple: true,
    };

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
                <Button onClick={this.downloadBudget}>当前预算表格下载</Button>
              </Col>
            </Row>
          </Card>
          {showUpload &&
            <Card title="上传预算调整申请文件">
             预算模板:
              <Select defaultValue="adjust2016" onChange={this.onAdjustTypeChanged} style={{display:'block', width: 120}}>
                <Option value="adjust2016">2016版预算</Option>
                <Option value="yusuan2">2015版预算</Option>
              </Select>
              <Upload {...uploadProps}>
                <Button>
                  <Icon type="upload" /> 上传文件
                </Button>
              </Upload>
            </Card>
          }
        </Card>
      </FrameContent>
    );
  }
}

function mapStateToProps(state) {
  const {
    projectInfo,
    userId,
   } = state.baseModel;

  return {
    projectInfo,
    userId
  };
}

export default connect(mapStateToProps)(BudgetJustify);
