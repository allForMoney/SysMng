import { Layout, Menu, Breadcrumb,
  Input,
  Icon, Card, Button, Modal } from 'antd';
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

  onMenuClicked = ({ keyPath }) => {
    const [cookie3, cookie2, cookie1] = keyPath;
    this.setState({ cookie1, cookie2, cookie3, selectKeys: cookie3 });
  }
  
  onCreateBtnClicked = () => {
    const form = this.form;

    form.validateFields((err, values) => {
      if (err) {
        return;
      }
      this.props.dispatch({
        type: 'baseModel/modifyPass',
        payload: values
      });
      form.resetFields();
      this.setState({ popWinVisible: false });
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
    console.log(msgValue);
    this.props.dispatch({
      type: 'baseModel/saveMsg',
      payload: { msgValue }
    });
    this.cancelMsg();
  }
  
  render() {
    const { cookie1, cookie2, cookie3, msgValue,
    selectKeys,
    openKeys,
   } = this.state;
    const { userType, userName, showMsgModal } = this.props;

    const isAdmin = userType === 'admin';
    const isMinistry = userType === 'ministry';
    let isSchool = true;
    if (isAdmin || isMinistry) {
      isSchool = false;
    }

    let identity = '';
    switch (userType) {
      case 'admin':
        identity = '管理员';
        break;
      case 'ministry':
        identity = '教育部';
        break;
      case 'inputer':
        identity = '填报人';
        break;
      case 'finan':
        identity = '财务部门负责人';
        break;
      case 'manager':
        identity = '项目负责人';
        break;
      default:
        identity = '填报人';
        break;
    }

    const cookiePath = (
      <Breadcrumb style={{ margin: '12px 0' }}>
        <Breadcrumb.Item>{cookie1}</Breadcrumb.Item>
        <Breadcrumb.Item>{cookie2}</Breadcrumb.Item>
        <Breadcrumb.Item>{cookie3}</Breadcrumb.Item>
      </Breadcrumb>
      );

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
        <Header className="header">
          <div className={styles.logo} />
          <div style={{ float: 'right'}} >
            <span>用户名：{userName} 身份： {identity}</span>
            <Button typdive="primary" onClick={this.logoOut}>退出登录</Button>
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
              mode="inline"
              defaultOpenKeys={['budget', 'projectBudget', '绩效', '绩效目标设定', '系统设置']}
              style={{ height: '80%' }}
              onClick={this.onMenuClicked}
            >
              <SubMenu key="budget" title={<span><Icon type="user" />预算</span>}>
                <Menu.Item key="base">
                  <Link to="/budget/base" ><Icon type="bars" />项目基本情况</Link>
                </Menu.Item>
                <SubMenu key="projectBudget" title={<span><Icon type="user" />项目预算</span>} >
                  <Menu.Item key="project">
                    <Link to="/budget/project" ><Icon type="bars" />项目预算表</Link>
                  </Menu.Item>
                  <Menu.Item key="justify">
                    <Link to="/budget/justify" ><Icon type="bars" />项目预算调整</Link>
                  </Menu.Item>
                  <Menu.Item key="justifyRec">
                    <Link to="/budget/justifyRec" ><Icon type="bars" />预算调整记录</Link>
                  </Menu.Item>
                </SubMenu>
                <Menu.Item key="预算执行季报">
                  <Link to="/budget_baseinfo" ><Icon type="bars" />预算执行季报</Link>
                </Menu.Item>
                <Menu.Item key="预算执行季报查询">
                  <Link to="/budget_baseinfo" ><Icon type="bars" />预算执行季报查询</Link>
                </Menu.Item>
                <Menu.Item key="支出预算处理结果">
                  <Link to="/blank" ><Icon type="bars" />支出预算处理结果</Link>
                </Menu.Item>
                <Menu.Item key="预算留言处理情况">
                  <Link to="/budget/msg" ><Icon type="bars" />预算留言处理情况</Link>
                </Menu.Item>
              </SubMenu>
              <SubMenu key="绩效" title={<span><Icon type="user" />绩效</span>}>
                <SubMenu key="绩效目标设定" title={<span><Icon type="user" />项目预算</span>} >
                  <Menu.Item key="目标查看">
                    <Link to="/budget_baseinfo" ><Icon type="bars" />目标查看</Link>
                  </Menu.Item>
                  <Menu.Item key="绩效指标">
                    <Link to="/budget_baseinfo" ><Icon type="bars" />绩效指标</Link>
                  </Menu.Item>
                </SubMenu>
                <Menu.Item key="项目支出绩效目标自评表">
                  <Link to="/blank" ><Icon type="bars" />项目支出绩效目标自评表</Link>
                </Menu.Item>
                <Menu.Item key="绩效运行存在问题纠正情况">
                  <Link to="/blank" ><Icon type="bars" />绩效运行存在问题纠正情况</Link>
                </Menu.Item>
                <Menu.Item key="年度绩效自评报告">
                  <Link to="/blank" ><Icon type="bars" />年度绩效自评报告</Link>
                </Menu.Item>
                <Menu.Item key="绩效留言处理情况">
                  <Link to="/blank" ><Icon type="bars" />绩效留言处理情况</Link>
                </Menu.Item>
              </SubMenu>
              <SubMenu key="系统设置" title={<span><Icon type="user" />系统设置</span>}>
                <Menu.Item key="修改联系方式">
                  <Link to="/sys/concat" ><Icon type="bars" />修改联系方式</Link>
                </Menu.Item>
                <Menu.Item key="系统使用建议">
                  <Link to="/sys/advice" ><Icon type="bars" />系统使用建议</Link>
                </Menu.Item>
                <Menu.Item key="修改密码">
                  <Link onClick={this.modifyPass}><Icon type="bars" />修改密码</Link>
                </Menu.Item>
                <Menu.Item key="退出登录">
                  <Link to="/budget_baseinfo" ><Icon type="bars" />退出登录</Link>
                </Menu.Item>
              </SubMenu>
            </Menu>
            }
            { isAdmin &&
            <Menu
              mode="inline"
              defaultSelectedKeys={['项目基本信息列表']}
              defaultOpenKeys={['数据导入']}
              style={{ height: '100%' }}
              onClick={this.onMenuClicked}
            >
              <SubMenu key="数据导入" title={<span><Icon type="user" />数据导入</span>}>
                <Menu.Item key="项目基本信息列表">
                  <Link to="/budget_baseinfo" ><Icon type="bars" />项目基本信息列表</Link>
                </Menu.Item>
                <Menu.Item key="项目基本信息导入">
                  <Link to="/budget_baseinfo" ><Icon type="bars" />项目基本信息导入</Link>
                </Menu.Item>
                <Menu.Item key="预算导入（2015年前）">
                  <Link to="/budget_baseinfo" ><Icon type="bars" />预算导入（2015年前）</Link>
                </Menu.Item>
                <Menu.Item key="预算导入（2016年后）">
                  <Link to="/budget_baseinfo" ><Icon type="bars" />预算导入（2016年后）</Link>
                </Menu.Item>
                <Menu.Item key="项目预算导入查询">
                  <Link to="/budget_baseinfo" ><Icon type="bars" />项目预算导入查询</Link>
                </Menu.Item>
                <Menu.Item key="绩效目标导入">
                  <Link to="/budget_baseinfo" ><Icon type="bars" />绩效目标导入</Link>
                </Menu.Item>
                <Menu.Item key="绩效目标导入查询">
                  <Link to="/budget_baseinfo" ><Icon type="bars" />绩效目标导入查询</Link>
                </Menu.Item>
              </SubMenu>
              <SubMenu key="其他功能" title={<span><Icon type="user" />其他功能</span>}>
                <Menu.Item key="上报时间设置">
                  <Link to="/budget_baseinfo" ><Icon type="bars" />上报时间设置</Link>
                </Menu.Item>
                <Menu.Item key="发送短信通知">
                  <Link to="/budget_baseinfo" ><Icon type="bars" />发送短信通知</Link>
                </Menu.Item>
                <Menu.Item key="系统使用建议">
                  <Link to="/budget_baseinfo" ><Icon type="bars" />系统使用建议</Link>
                </Menu.Item>
                <Menu.Item key="用户密码重置">
                  <Link to="/budget_baseinfo" ><Icon type="bars" />用户密码重置</Link>
                </Menu.Item>
                <Menu.Item key="修改密码">
                  <Link to="/budget_baseinfo" ><Icon type="bars" />修改密码</Link>
                </Menu.Item>
                <Menu.Item key="退出登录">
                  <Link to="/budget_baseinfo" ><Icon type="bars" />退出登录</Link>
                </Menu.Item>
              </SubMenu>
            </Menu>
            }
            { isMinistry &&
            <Menu
              mode="inline"
              defaultSelectedKeys={['项目基本情况_mini']}
              defaultOpenKeys={['预算']}
              style={{ height: '100%' }}
              onClick={this.onMenuClicked}
            >
              <SubMenu key="预算" title={<span><Icon type="user" />预算</span>}>
                <Menu.Item key="项目基本情况_mini">
                  <Link to="/budget_baseinfo" ><Icon type="bars" />项目基本情况</Link>
                </Menu.Item>
                <Menu.Item key="项目预算">
                  <Link to="/budget_baseinfo" ><Icon type="bars" />项目预算</Link>
                </Menu.Item>
                <Menu.Item key="预算执行情况季报">
                  <Link to="/budget_baseinfo" ><Icon type="bars" />预算执行情况季报</Link>
                </Menu.Item>
                <Menu.Item key="项目预算调整">
                  <Link to="/budget_baseinfo" ><Icon type="bars" />项目预算调整</Link>
                </Menu.Item>
                <Menu.Item key="预算执行结果">
                  <Link to="/budget_baseinfo" ><Icon type="bars" />预算执行结果</Link>
                </Menu.Item>
                <Menu.Item key="预算留言处理">
                  <Link to="/budget_baseinfo" ><Icon type="bars" />预算留言处理</Link>
                </Menu.Item>
                <Menu.Item key="有关批复">
                  <Link to="/budget_baseinfo" ><Icon type="bars" />有关批复</Link>
                </Menu.Item>
                <Menu.Item key="领导批示处理结果">
                  <Link to="/budget_baseinfo" ><Icon type="bars" />领导批示处理结果</Link>
                </Menu.Item>
              </SubMenu>
              <SubMenu key="数据分析" title={<span><Icon type="user" />数据分析</span>}>
                <Menu.Item key="预算执行分析">
                  <Link to="/budget_baseinfo" ><Icon type="bars" />预算执行分析</Link>
                </Menu.Item>
                <Menu.Item key="预算与投入">
                  <Link to="/budget_baseinfo" ><Icon type="bars" />预算与投入</Link>
                </Menu.Item>
                <Menu.Item key="专业预算与投入">
                  <Link to="/budget_baseinfo" ><Icon type="bars" />专业预算与投入</Link>
                </Menu.Item>
                <Menu.Item key="预算与支出">
                  <Link to="/budget_baseinfo" ><Icon type="bars" />预算与支出</Link>
                </Menu.Item>
                <Menu.Item key="专业预算与支出">
                  <Link to="/budget_baseinfo" ><Icon type="bars" />专业预算与支出</Link>
                </Menu.Item>
                <Menu.Item key="报表数据分析">
                  <Link to="/budget_baseinfo" ><Icon type="bars" />报表数据分析</Link>
                </Menu.Item>
              </SubMenu>
              <SubMenu key="绩效" title={<span><Icon type="user" />绩效</span>}>
                <SubMenu key="绩效目标" title={<span><Icon type="user" />绩效目标</span>} >
                  <Menu.Item key="查询">
                    <Link to="/budget_baseinfo" ><Icon type="bars" />查询</Link>
                  </Menu.Item>
                  <Menu.Item key="绩效留言处理">
                    <Link to="/budget_baseinfo" ><Icon type="bars" />绩效留言处理</Link>
                  </Menu.Item>
                  <Menu.Item key="变更处理">
                    <Link to="/budget_baseinfo" ><Icon type="bars" />变更处理</Link>
                  </Menu.Item>
                </SubMenu>
                <SubMenu key="绩效监控" title={<span><Icon type="user" />绩效监控</span>} >
                  <Menu.Item key="绩效目标比对">
                    <Link to="/budget_baseinfo" ><Icon type="bars" />绩效目标比对</Link>
                  </Menu.Item>
                  <Menu.Item key="绩效运行中发生的问题">
                    <Link to="/budget_baseinfo" ><Icon type="bars" />绩效运行中发生的问题</Link>
                  </Menu.Item>
                </SubMenu>
                <SubMenu key="绩效评价" title={<span><Icon type="user" />绩效评价</span>} >
                  <Menu.Item key="第一年度绩效评价">
                    <Link to="/budget_baseinfo" ><Icon type="bars" />第一年度绩效评价</Link>
                  </Menu.Item>
                  <Menu.Item key="第二年度前半年绩效评价">
                    <Link to="/budget_baseinfo" ><Icon type="bars" />第二年度前半年绩效评价</Link>
                  </Menu.Item>
                  <Menu.Item key="项目终了绩效评价">
                    <Link to="/budget_baseinfo" ><Icon type="bars" />项目终了绩效评价</Link>
                  </Menu.Item>
                </SubMenu>
                <Menu.Item key="特别情况处理">
                  <Link to="/budget_baseinfo" ><Icon type="bars" />特别情况处理</Link>
                </Menu.Item>
              </SubMenu>
              <SubMenu key="专家库" title={<span><Icon type="user" />专家库</span>}>
                <Menu.Item key="专家信息管理">
                  <Link to="/budget_baseinfo" ><Icon type="bars" />专家信息管理</Link>
                </Menu.Item>
                <Menu.Item key="专家信用记录">
                  <Link to="/budget_baseinfo" ><Icon type="bars" />专家信用记录</Link>
                </Menu.Item>
                <Menu.Item key="专家抽取">
                  <Link to="/budget_baseinfo" ><Icon type="bars" />专家抽取</Link>
                </Menu.Item>
              </SubMenu>
              <SubMenu key="系统设置" title={<span><Icon type="user" />系统设置</span>}>
                <Menu.Item key="修改密码">
                  <Link to="/budget_baseinfo" ><Icon type="bars" />修改密码</Link>
                </Menu.Item>
                <Menu.Item key="退出登录">
                  <Link to="/budget_baseinfo" ><Icon type="bars" />退出登录</Link>
                </Menu.Item>
              </SubMenu>
            </Menu>
            }
          </Sider>
          <Layout style={{ padding: '0 24px 24px' }}>
            <Content style={{ background: '#fff', padding: 24, margin: 0, minHeight: 280 }}>
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
  const { userType, userName, showMsgModal } = state.baseModel;
  return { userType, userName, showMsgModal };
}
export default connect(mapStateToProps)(FrameContent);
