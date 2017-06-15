import { Layout, Menu, Breadcrumb, Icon } from 'antd';
import React, { PropTypes } from 'react';

import { Link } from 'dva/router';

const { SubMenu } = Menu;
const { Header, Content, Sider } = Layout;

class FrameContent extends React.Component {
  state = {
    cookie1: 'Home',
    cookie2: 'index',
    cookie3: ''
  }
  onMenuClicked = ({ keyPath }) => {
    const [cookie3, cookie2, cookie1] = keyPath;
    this.setState({ cookie1, cookie2, cookie3 });


  }
  render() {
    const { cookie1, cookie2, cookie3 } = this.state;
    return (
      <Layout>
        <Header className="header">
          <div className="logo" />
          <Menu
            theme="dark"
            mode="horizontal"
            defaultSelectedKeys={['2']}
            style={{ lineHeight: '64px' }}
          >
            <Menu.Item key="1">nav 1</Menu.Item>
            <Menu.Item key="2">nav 2</Menu.Item>
            <Menu.Item key="3">nav 3</Menu.Item>
          </Menu>
        </Header>
        <Layout>
          <Sider width={200} style={{ background: '#fff' }}>
            <Menu
              mode="inline"
              defaultSelectedKeys={['中文']}
              defaultOpenKeys={['因为']}
              style={{ height: '100%' }}
              onClick={this.onMenuClicked}
            >
              <SubMenu key="因为" title={<span><Icon type="user" />subnav 1</span>}>
                <Menu.Item key="中文" onc>
                  <Link to="/sign" ><Icon type="bars" />sign</Link>
                </Menu.Item>
                <Menu.Item key="2">option2</Menu.Item>
                <Menu.Item key="3">option3</Menu.Item>
                <Menu.Item key="4">option4</Menu.Item>
              </SubMenu>
              <SubMenu key="sub2" title={<span><Icon type="laptop" />subnav 2</span>}>
                <Menu.Item key="5">option5</Menu.Item>
                <Menu.Item key="6">option6</Menu.Item>
                <Menu.Item key="7">option7</Menu.Item>
                <Menu.Item key="8">option8</Menu.Item>
              </SubMenu>
              <SubMenu key="sub3" title={<span><Icon type="notification" />subnav 3</span>}>
                <Menu.Item key="9">option9</Menu.Item>
                <Menu.Item key="10">option10</Menu.Item>
                <Menu.Item key="11">option11</Menu.Item>
                <Menu.Item key="12">option12</Menu.Item>
              </SubMenu>
            </Menu>
          </Sider>
          <Layout style={{ padding: '0 24px 24px' }}>
            <Breadcrumb style={{ margin: '12px 0' }}>
              <Breadcrumb.Item>{cookie1}</Breadcrumb.Item>
              <Breadcrumb.Item>{cookie2}</Breadcrumb.Item>
              <Breadcrumb.Item>{cookie3}</Breadcrumb.Item>
            </Breadcrumb>
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
};

export default FrameContent;
