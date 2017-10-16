import { browserHistory } from 'dva/router';
import { Layout, Menu, Breadcrumb,
  Input,
  Icon, message, Button, Modal } from 'antd';
import React, { PropTypes } from 'react';
import { connect } from 'dva';
import { Link } from 'dva/router';
import styles from './FrameContent.less';
import ModifyPass from '../sysConfig/ModifyPass';

const { SubMenu } = Menu;
const { Header, Content, Sider } = Layout;

class FrameContent extends React.Component {
  state = {
    cookie1: 'Home',
    cookie2: 'index',
    cookie3: '',
    showModiPass: false,
    msgValue: '',
    selectKeys: [],
    openKeys: [],
  }

  onMenuClicked = ({ key }) => {
    if (key === 'logoOut') {
      this.logoOut();
    } else if (key === 'modifyPass') {
      this.modifyPass();
    } else {
      this.props.history.push(key);
    }
  }

  onCreateBtnClicked = () => {
    const form = this.form;

    form.validateFields((err, values) => {
      if (err) {
        return;
      }
      if (values.newPassword !== values.repeatPass) {
        message.info('请输入一致的密码');
        return;
      }
      this.props.dispatch({
        type: 'baseModel/modifyPass',
        payload: values
      });
      form.resetFields();
      this.setState({ showModiPass: false });
    });
  }

  onCancelBtnClicked = () => {
    this.form.resetFields();
    this.setState({ showModiPass: false });
  }

  modifyPass = () => {
    this.setState({ showModiPass: true });
  }

  logoOut = () => {
    Modal.confirm({
      content: '确认退出？',
      onOk: this.doLogoOut,
      okText: '确定',
    });
  }

  doLogoOut = () => {
    // TODO
    delete sessionStorage.username;
    delete sessionStorage.password;
    window.location = '../login.html';
  }

  saveFormRef = (form) => {
    this.form = form;
  }

  cancelMsg= () => {
    this.props.dispatch({
      type: 'baseModel/setState',
      payload: { showMsgModal: false }
    });
  }

  saveMsg= () => {
    const { msgValue } = this.state;

    this.props.dispatch({
      type: 'baseModel/saveMsg',
      payload: { msgValue }
    });
    this.setState({ msgValue: '' });
    this.cancelMsg();
  }

  render() {
    const {
    msgValue,
   } = this.state;
    const { userType, userName, showMsgModal } = this.props;

    const isAdmin = userType === 'admin';
    const isMinistry = userType === 'country';
    let isSchool = true;
    if (isAdmin || isMinistry) {
      isSchool = false;
    }

    let identity = '';
    switch (userType) {
      case 'admin':
        identity = '管理员';
        break;
      case 'country':
        identity = '教育部';
        break;
      case 'inputer':
        identity = '填报人';
        break;
      case 'finace':
        identity = '财务部门负责人';
        break;
      case 'school':
        identity = '项目负责人';
        break;
      default:
        identity = '填报人';
        break;
    }

    return (
      <Layout>
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
        <Header className={styles.header}>
          <div className={styles.logo}>
            职业教育国家级专业教学资源库预算绩效管理系统
          </div>
          <div style={{ float: 'right' }} >
            <span>用户名：{userName} 身份： {identity}</span>
            <Button type="primary" size="small" style={{ marginLeft: 10 }} onClick={this.logoOut}>退出登录</Button>
          </div>
          <ModifyPass
            visible={this.state.showModiPass}
            ref={this.saveFormRef}
            userName={userName}
            onCancel={this.onCancelBtnClicked}
            onCreate={this.onCreateBtnClicked}
          />
        </Header>
        <Layout style={{ height: '100vh' }}>
          <Sider width={250} style={{ background: '#fff', overflow: 'auto' }}>
            {isSchool &&
            <Menu
              defaultSelectedKeys={['/base/projectList']}
              mode="inline"
              defaultOpenKeys={['budget', 'projectBudget', '绩效', '系统设置']}
              style={{ height: '80%' }}
              onClick={this.onMenuClicked}
            >
              <SubMenu key="budget" title={<span><Icon type="user" />预算</span>}>
                <Menu.Item key="/budget/base">
                  <Icon type="bars" />项目基本情况
                </Menu.Item>
                <SubMenu key="projectBudget" title={<span><Icon type="user" />项目预算</span>} >
                  <Menu.Item key="/budget/project" >
                    <Icon type="bars" />项目预算表
                  </Menu.Item>
                  <Menu.Item key="/budget/justify">
                    <Icon type="bars" />项目预算调整
                  </Menu.Item>
                  <Menu.Item key="/budget/justifyRec">
                    <Icon type="bars" />预算调整记录
                  </Menu.Item>
                </SubMenu>
                <Menu.Item key="/budget/addbudgetseason">
                  <Icon type="bars" />预算执行季报
                </Menu.Item>
                <Menu.Item key="/budget/budgetSeasonList">
                  <Icon type="bars" />预算执行季报查询
                </Menu.Item>
                <Menu.Item key="/blank">
                  <Icon type="bars" />支出预算处理结果
                </Menu.Item>
                <Menu.Item key="/budget/msg">
                  <Icon type="bars" />预算留言处理情况
                </Menu.Item>
              </SubMenu>
              <SubMenu key="绩效" title={<span><Icon type="user" />绩效</span>}>
                <Menu.Item key="/achive/add">
                  <Icon type="bars" />绩效目标设定
                </Menu.Item>
                <Menu.Item key="/blank">
                  <Icon type="bars" />项目支出绩效目标自评表
                </Menu.Item>
                <Menu.Item key="/blank">
                  <Icon type="bars" />绩效运行存在问题纠正情况
                </Menu.Item>
                <Menu.Item key="/blank">
                  <Icon type="bars" />年度绩效自评报告
                </Menu.Item>
                <Menu.Item key="/blank">
                  <Icon type="bars" />绩效留言处理情况
                </Menu.Item>
              </SubMenu>
              <SubMenu key="系统设置" title={<span><Icon type="user" />系统设置</span>}>
                <Menu.Item key="/sys/concat">
                  <Icon type="bars" />修改联系方式
                </Menu.Item>
                <Menu.Item key="/sys/advice">
                  <Icon type="bars" />系统使用建议
                </Menu.Item>
                <Menu.Item key="modifyPass" >
                  <Icon type="bars" />修改密码
                </Menu.Item>
                <Menu.Item key="logoOut" >
                  <Icon type="bars" />退出登录
                </Menu.Item>
              </SubMenu>
            </Menu>
            }
            { isAdmin &&
            <Menu
              mode="inline"
              defaultSelectedKeys={['/base/projectList']}
              defaultOpenKeys={['数据导入', '其他功能']}
              style={{ height: '100%' }}
              onClick={this.onMenuClicked}
            >
              <SubMenu key="数据导入" title={<span><Icon type="user" />数据导入</span>}>
                <Menu.Item key="/base/projectList">
                  <Icon type="bars" />项目基本信息列表
                </Menu.Item>
                <Menu.Item key="/import/importProjet">
                  <Icon type="bars" />项目基本信息导入
                </Menu.Item>
                <Menu.Item key="/import/budget15">
                  <Icon type="bars" />预算导入（2015年前）
                </Menu.Item>
                <Menu.Item key="/import/budget16">
                  <Icon type="bars" />预算导入（2016年后）
                </Menu.Item>
                <Menu.Item key="/import/budgetImportRec">
                  <Icon type="bars" />项目预算导入查询
                </Menu.Item>
                <Menu.Item key="/import/importAchive">
                  <Icon type="bars" />绩效目标导入
                </Menu.Item>
                <Menu.Item key="/import/achiveImportRec">
                  <Icon type="bars" />绩效目标导入查询
                </Menu.Item>
              </SubMenu>
              <SubMenu key="其他功能" title={<span><Icon type="user" />其他功能</span>}>
                <Menu.Item key="/sys/reportTime">
                  <Icon type="bars" />上报时间设置
                </Menu.Item>
                <Menu.Item key="/sys/sendSMS">
                  <Icon type="bars" />发送短信通知
                </Menu.Item>
                <Menu.Item key="/sys/advice">
                  <Icon type="bars" />系统使用建议
                </Menu.Item>
                <Menu.Item key="/sys/resetPwd">
                  <Icon type="bars" />用户密码重置
                </Menu.Item>
                <Menu.Item key="modifyPass" >
                  <Icon type="bars" />修改密码
                </Menu.Item>
                <Menu.Item key="logoOut" >
                  <Icon type="bars" />退出登录
                </Menu.Item>
              </SubMenu>
            </Menu>
            }
            { isMinistry &&
            <Menu
              mode="inline"
              defaultSelectedKeys={['/base/projectList']}
              defaultOpenKeys={['预算', '数据分析', '绩效', '专家库', '系统设置']}
              style={{ height: '100%' }}
              onClick={this.onMenuClicked}
            >
              <SubMenu key="预算" title={<span><Icon type="user" />预算</span>}>
                <Menu.Item key="/base/projectList">
                  <Icon type="bars" />项目基本情况
                </Menu.Item>
                <Menu.Item key="/import/budgetImportRec">
                  <Icon type="bars" />项目预算
                </Menu.Item>
                <Menu.Item key="/budget/projectSeaonBudget">
                  <Icon type="bars" />预算执行情况季报
                </Menu.Item>
                <Menu.Item key="/budget/justifyRec">
                  <Icon type="bars" />项目预算调整
                </Menu.Item>
                <Menu.Item key="/budget/projectBudgetResult">
                  <Icon type="bars" />预算执行结果
                </Menu.Item>
                <Menu.Item key="/budget/msgList">
                  <Icon type="bars" />预算留言处理
                </Menu.Item>
                <Menu.Item key="/blank">
                  <Icon type="bars" />有关批复
                </Menu.Item>
                <Menu.Item key="/blank">
                  <Icon type="bars" />领导批示处理结果
                </Menu.Item>
              </SubMenu>
              <SubMenu key="数据分析" title={<span><Icon type="user" />数据分析</span>}>
                <Menu.Item key="/dataAna/year">
                  <Icon type="bars" />预算执行分析
                </Menu.Item>
                <Menu.Item key="/dataAna/budgetyear">
                  <Icon type="bars" />预算与投入
                </Menu.Item>
                <Menu.Item key="/blank">
                  <Icon type="bars" />专业预算与投入
                </Menu.Item>
                <Menu.Item key="/blank">
                  <Icon type="bars" />预算与支出
                </Menu.Item>
                <Menu.Item key="/blank">
                  <Icon type="bars" />专业预算与支出
                </Menu.Item>
                <Menu.Item key="/blank">
                  <Icon type="bars" />报表数据分析
                </Menu.Item>
              </SubMenu>
              <SubMenu key="绩效" title={<span><Icon type="user" />绩效</span>}>
                <SubMenu key="绩效目标" title={<span><Icon type="user" />绩效目标</span>} >
                  <Menu.Item key="/achive/allList">
                    <Icon type="bars" />查询
                  </Menu.Item>
                  <Menu.Item key="/achive/msgAll">
                    <Icon type="bars" />绩效留言处理
                  </Menu.Item>
                  <Menu.Item key="/blank">
                    <Icon type="bars" />变更处理
                  </Menu.Item>
                </SubMenu>
                <SubMenu key="绩效监控" title={<span><Icon type="user" />绩效监控</span>} >
                  <Menu.Item key="/blank">
                    <Icon type="bars" />绩效目标比对
                  </Menu.Item>
                  <Menu.Item key="/blank">
                    <Icon type="bars" />绩效运行中发生的问题
                  </Menu.Item>
                </SubMenu>
                <SubMenu key="绩效评价" title={<span><Icon type="user" />绩效评价</span>} >
                  <Menu.Item key="/blank">
                    <Icon type="bars" />第一年度绩效评价
                  </Menu.Item>
                  <Menu.Item key="/blank">
                    <Icon type="bars" />第二年度前半年绩效评价
                  </Menu.Item>
                  <Menu.Item key="/blank">
                    <Icon type="bars" />项目终了绩效评价
                  </Menu.Item>
                </SubMenu>
                <Menu.Item key="/blank">
                  <Icon type="bars" />特别情况处理
                </Menu.Item>
              </SubMenu>
              <SubMenu key="专家库" title={<span><Icon type="user" />专家库</span>}>
                <Menu.Item key="/expert/list">
                  <Icon type="bars" />专家信息管理
                </Menu.Item>
                <Menu.Item key="/blank">
                  <Icon type="bars" />专家信用记录
                </Menu.Item>
                <Menu.Item key="/blank">
                  <Icon type="bars" />专家抽取
                </Menu.Item>
              </SubMenu>
              <SubMenu key="系统设置" title={<span><Icon type="user" />系统设置</span>}>
                <Menu.Item key="modifyPass" >
                  <Icon type="bars" />修改密码
                </Menu.Item>
                <Menu.Item key="logoOut" >
                  <Icon type="bars" />退出登录
                </Menu.Item>
              </SubMenu>
            </Menu>
            }
          </Sider>
          <Layout style={{ padding: '0 10px 10px' }}>
            <Content style={{ padding: 5, minHeight: 280 }}>
              {this.props.children}
            </Content>
          </Layout>
        </Layout>
      </Layout>
    );
  }
}

FrameContent.propTypes = {
  children: PropTypes.any,
  userType: PropTypes.string,
};

function mapStateToProps(state) {
  const { userType, userName, showMsgModal, msgType } = state.baseModel;
  return { userType, userName, showMsgModal, msgType };
}
export default connect(mapStateToProps)(FrameContent);
