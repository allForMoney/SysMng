/**
 * 所有项目列表,主要用在教育部 用户下展示列表
 * 可以用在需要展示项目列表的地方
 */
import React from 'react';
import { Form, Input, Button, Modal, Card, Table, Row, Col, Icon, Popconfirm, Select } from 'antd';
import FrameContent from '../common/FrameContent';
import LinkBtn from '../common/LinkBtn';
import { connect } from 'dva';

const Option = Select.Option;

class ExpertList extends React.Component {
  state={
    editModalVisible: false,
    modalTitle: '',
    editRec: {}
  }
  componentDidMount() {
    this.props.dispatch({
      type: 'expertModel/getExpertList',
    });
  }

  onPageChanged = (page) => {
    this.props.dispatch({
      type: 'expertModel/setState',
      payload: {
        expertPage: page,
      },
    });
    this.props.dispatch({
      type: 'expertModel/getExpertList',
    });
  }

  deleteExpert = (rec) => {
    const { id } = rec;
    this.props.dispatch({
      type: 'expertModel/deleteExpert',
      payload: { ids: [id] }
    });
  }

  editExpert = (rec) => {
    const { form } = this.props;
    form.setFieldsValue(rec);
    this.setState({
      editModalVisible: true,
      modalTitle: '更新专家',
      editRec: rec
    });
  }

  addExpert = () => {
    const { form } = this.props;
    form.resetFields();
    this.setState({
      editModalVisible: true,
      modalTitle: '添加专家'
    });
  }

  handleCancel = () => {
    const { form } = this.props;
    form.resetFields();
    this.setState({
      editModalVisible: false,
    });
  }

  handleOk = () => {
    const { modalTitle, editRec } = this.state;
    let type = 'expertModel/addExpert';
    if (modalTitle !== '添加专家') {
      type = 'expertModel/updateExpert';
    }

    const { form } = this.props;
    form.validateFields((err, values) => {
      if (err) {
        return;
      }
      this.setState({
        editModalVisible: false,
      });

      this.props.dispatch({
        type,
        payload: { ...editRec, ...values }
      });
    });
  }

  render() {
    const {
      loading,
      expertList,
      expertPage,
      expertNum,
    } = this.props;
    const { getFieldDecorator } = this.props.form;

    const columns =
      [{
        title: '编号',
        dataIndex: 'code',
        key: 'code',
      }, {
        title: '姓名',
        dataIndex: 'name',
      }, {
        title: '性别',
        dataIndex: 'gender',
      }, {
        title: '身份证号',
        dataIndex: 'cid',
      }, {
        title: '联系电话',
        dataIndex: 'telephoneNumber',
      }, {
        title: '职称',
        dataIndex: 'professionalTitle',
      }, {
        title: '学历',
        dataIndex: 'eduLevel',
      }, {
        title: '回避单位',
        dataIndex: 'avoidUnit',
      }, {
        title: '研究领域',
        dataIndex: 'researchField',
      }, {
        title: '操作',
        render: (text, rec) => (
          <span>
            <LinkBtn onClick={this.editExpert.bind(this, rec)}>编辑</LinkBtn>{' '}
            <Popconfirm title="确认删除?" onConfirm={this.deleteExpert.bind(this, rec)} okText="确认" cancelText="取消">
              <LinkBtn>删除</LinkBtn>
            </Popconfirm>

          </span>
        )
      }];

    const pageConfig = {
      className: 'ant-table-pagination',
      total: expertNum,
      current: expertPage,
      pageSize: 20,
      onChange: this.onPageChanged,
    };
    const { editModalVisible, modalTitle } = this.state;
    const formItemLayout = {
      labelCol: {
        xs: { span: 24 },
        sm: { span: 6 },
      },
      wrapperCol: {
        xs: { span: 24 },
        sm: { span: 14 },
      },
    };
    const requireRules = [{
      required: true, message: '不可为空',
    }];
    return (

      <Card>
        <Modal
          title={modalTitle}
          visible={editModalVisible}
          onOk={this.handleOk}
          onCancel={this.handleCancel}
        >
          <Form>
            <Form.Item label="编号 ：" {...formItemLayout}>
              { getFieldDecorator('code', {
                rules: requireRules,
              })(
                <Input />
                )}
            </Form.Item>
            <Form.Item label="姓名 ：" {...formItemLayout}>
              { getFieldDecorator('name', {
                rules: requireRules,
              })(
                <Input />
                )}
            </Form.Item>
            <Form.Item label="性别 ：" {...formItemLayout}>
              { getFieldDecorator('gender', {
                rules: requireRules,
              })(
                <Select defaultValue="女" style={{ width: 120 }}>
                  <Option value="男">男</Option>
                  <Option value="女">女</Option>
                </Select>
                )}
            </Form.Item>
            <Form.Item label="身份证号 ：" {...formItemLayout}>
              { getFieldDecorator('cid', {
                rules: requireRules,
              })(
                <Input />
                )}
            </Form.Item>
            <Form.Item label="联系电话 ：" {...formItemLayout}>
              { getFieldDecorator('telephoneNumber', {
                rules: requireRules,
              })(
                <Input />
                )}
            </Form.Item>
            <Form.Item label="职称 ：" {...formItemLayout}>
              { getFieldDecorator('professionalTitle', {
                rules: requireRules,
              })(
                <Input />
                )}
            </Form.Item>
            <Form.Item label="学历 ：" {...formItemLayout}>
              { getFieldDecorator('eduLevel', {
                rules: requireRules,
              })(
                <Input />
                )}
            </Form.Item>
            <Form.Item label="回避单位 ：" {...formItemLayout}>
              { getFieldDecorator('avoidUnit', {
                rules: requireRules,
              })(
                <Input />
                )}
            </Form.Item>
            <Form.Item label="研究领域 ：" {...formItemLayout}>
              { getFieldDecorator('researchField', {
                rules: requireRules,
              })(
                <Input />
                )}
            </Form.Item>
          </Form>
        </Modal>
        <Row>
          <Col>
            <Button onClick={this.addExpert}><Icon type="plus-circle-o" />添加专家</Button>
          </Col>
        </Row>
        <Table
          columns={columns}
          dataSource={expertList}
          bordered
          loading={loading}
        />
      </Card>

    );
  }
}
function mapStateToProps(state) {
  const {
    expertList,
    expertPage,
    expertNum,
    loading,
  } = state.expertModel;

  return {
    expertList,
    loading,
    expertPage,
    expertNum,
  };
}

export default connect(mapStateToProps)(Form.create({})(ExpertList));
