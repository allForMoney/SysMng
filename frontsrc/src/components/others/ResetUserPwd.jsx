import { connect } from 'dva';
import React from 'react';
import { Form, Input, Button, Icon, Card, Table, Upload, message, Modal, Popconfirm } from 'antd';
import FrameContent from '../common/FrameContent';
import styles from '../../index.less';
import LinkBtn from '../common/LinkBtn';


class ResetUserPwd extends React.Component {

  resetPwd = (rec) => {
    console.log(rec);
    this.props.dispatch({
      type: 'OtherModel/resetPwd',
      payload: { id: rec.id }
    });
  }

  componentDidMount() {
    this.props.dispatch({
      type: 'OtherModel/setState',
      payload: {
        userRecPageNum: 1,
      },
    });
    this.props.dispatch({
      type: 'OtherModel/getUserList',
    });
  }
  doSearch=(e) => {
    e.preventDefault();
    const values = this.props.form.getFieldsValue();

    this.props.dispatch({
      type: 'OtherModel/setState',
      payload: {
        userRecPageNum: 1,
      },
    });
    this.props.dispatch({
      type: 'OtherModel/getUserList',
      payload: values
    });
  }


  onPageChanged = (page) => {
    this.props.dispatch({
      type: 'OtherModel/setState',
      payload: {
        userRecPageNum: page,
      },
    });
    this.props.dispatch({
      type: 'OtherModel/getUserList',
    });
  }

  render() {
    const columns =
      [{
        title: '登录账号',
        dataIndex: 'username',
      }, {
        title: '用户角色',
        dataIndex: 'roleName',
      }, {
        title: '专业名称',
        dataIndex: 'majorName',
      }, {
        title: '联系方式',
        dataIndex: 'telephoneNum',
      }, {
        title: '重置密码',
        dataIndex: 'schoolHead',
        render: (value, rec) => <LinkBtn onClick={this.resetPwd.bind(this, rec)}>重置密码</LinkBtn>
      }];

    const {
      userRecList,
      userRecPageNum,
      userRecTotal,
      loading,
      form,
    } = this.props;
    const { getFieldDecorator } = form;

    const pageConfig = {
      className: 'ant-table-pagination',
      total: userRecTotal,
      current: userRecPageNum,
      pageSize: 20,
      onChange: this.onPageChanged,
    };
    return (
      <FrameContent>
        <Card title="用户列表">
          提示：密码重置后变为123456
          <Form onSubmit={this.onSearchSubmit} layout="inline">
            <Form.Item label="主持单位 ：">
              { getFieldDecorator('userNo', {
              })(
                <Input size="small" />
                )}
            </Form.Item>
            <Form.Item >
              <Button size="small" type="primary" htmlType="submit" icon="search" onClick={this.doSearch}>查询</Button>
              <Button size="small" style={{ marginLeft: 5 }} type="primary" onClick={() => this.props.form.resetFields()}><Icon type="rollback" />重置</Button>
            </Form.Item>
          </Form>
          <Table
            columns={columns}
            dataSource={userRecList}
            bordered
            loading={loading}
            rowKey={record => record.name}
            pagination={pageConfig}
          />
        </Card>
      </FrameContent>
    );
  }
}

function mapStateToProps(state) {
  const {
      userRecList,
      userRecPageNum,
      userRecTotal,
      loading,
  } = state.OtherModel;
  return {
    userRecList,
    userRecPageNum,
    userRecTotal,
    loading,
  };
}

export default connect(mapStateToProps)(Form.create({})(ResetUserPwd));
