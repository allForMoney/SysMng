import React from 'react';
import { Form, Input, Button, Icon, Card, Table, Upload, message, Modal, Popconfirm } from 'antd';
import FrameContent from '../common/FrameContent';
import AddProjectForm from './AddProjectForm';
import styles from '../../index.less';
import { connect } from 'dva';

class ProjectInfoList extends React.Component {
  state = {
    selectedRows: [],
    selectedRowKeys: [],
    modelVisible: false,
    modalTitle: '添加',
    selectObj: {},
  }
 
  onPageChanged = (page) => {
    this.props.dispatch({
      type: 'ProjectModel/setState',
      payload: {
        projectListPage: page,
      },
    });
    this.props.dispatch({
      type: 'ProjectModel/getProjectList',
    });
  }

  doSearch = (e) => {
    e.preventDefault();
    const values = this.props.form.getFieldsValue();
    console.log(values);
    this.props.dispatch({
      type: 'ProjectModel/setState',
      payload: {
        filterParam: values,
        projectListPage: 1,
      },
    });
    this.props.dispatch({
      type: 'ProjectModel/getProjectList',
    });
  }

  addNew =() => {
    this.setState({
      modelVisible: true,
      modalTitle: '添加'
    });
  }

  editProject =() => {
    const { selectedRows } = this.state;
    const selectObj = selectedRows[0];
    if (!selectObj) {
      message('请选中一条数据');
      return;
    }
    this.setState({
      modelVisible: true,
      modalTitle: '修改',
      selectObj
    });
  }

  deletePro = () => {
    const { selectedRows } = this.state;
    if (selectedRows.length < 1) {
      message('请选中一条数据');
      return;
    }
    const projectNos = selectedRows.map(item => item.projectNo);
    console.log(projectNos);
    this.props.dispatch({
      type: 'ProjectModel/deletePro',
      payload: {
        projectNos
      },
    });
  }

  importPro =() => {

  }
  addFormRdf =(form) => {
    this.addForm = form;
  }
    
  handleOk = () => {
    const { validateFields } = this.addForm;

    validateFields((err, values) => {
      if (err) {
        return;
      }
      this.props.dispatch({
        type: 'ProjectModel/AddProject',
        payload: values
      });
    });

    this.handleCancel();
  }

  handleCancel = () => {
    this.setState({ modelVisible: false });
    this.addForm.resetFields();
  }

  render() {
    const {
      loading,
      projectList,
      projectListNum,
      projectListPage,
    } = this.props;
    const { getFieldDecorator } = this.props.form;

    const columns =
      [{
        title: '项目编号',
        dataIndex: 'projectNo',
      }, {
        title: '专业名称',
        dataIndex: 'majorName',
      }, {
        title: '立项年度',
        dataIndex: 'createYear',
      }, {
        title: '学校名称',
        dataIndex: 'schoolName',
      }, {
        title: '学校领导',
        dataIndex: 'schoolHead',
      }, {
        title: '财务部门负责人',
        dataIndex: 'finaceHeader',
      }, {
        title: '财务负责人电话',
        dataIndex: 'finaceHeaderTel',
      }, {
        title: '财务负责人QQ',
        dataIndex: 'finaceHeaderQq',
      }, {
        title: '项目负责人',
        dataIndex: 'projectHeader',
      }, {
        title: '项目负责人电话',
        dataIndex: 'projectHeaderTel',
      }, {
        title: '填报人',
        dataIndex: 'reporter',
      }, {
        title: '填报人电话',
        dataIndex: 'reporterTel',
      }, {
        title: '填报人qq',
        dataIndex: 'reporterQq',
      }, {
        title: '备注',
        dataIndex: 'note',
      }, {
        title: '联合主持单位',
        dataIndex: 'unionSchool',
      }, {
        title: '参与建设单位',
        dataIndex: 'partnerSchool',
      }];

  

    const pageConfig = {
      className: 'ant-table-pagination',
      total: projectListNum,
      current: projectListPage,
      pageSize: 20,
      onChange: this.onPageChanged,
    };
    
    const { modelVisible, modalTitle, selectObj, selectedRowKeys } = this.state;
    const rowSelection = {
      selectedRowKeys,
      onChange: (Keys, selectedRows) => {
        console.log(`selectedRowKeys: ${Keys}`, 'selectedRows: ', selectedRows);
        this.setState({ selectedRows, selectedRowKeys: Keys });
      },
    };
    return (
      <FrameContent>
        <Card title="项目基础数据管理">
          <Modal
            title={modalTitle}
            visible={modelVisible}
            onOk={this.handleOk}
            onCancel={this.handleCancel}
          >
            <AddProjectForm {...selectObj} ref={this.addFormRdf} />
          </Modal>
          <Form onSubmit={this.onSearchSubmit} layout="inline">
            <Form.Item label="项目编号 ：">
              { getFieldDecorator('projectNo', {
              })(
                <Input />
              )}
            </Form.Item>
            <Form.Item label="专业名称 ：">
              { getFieldDecorator('majorName', {
              })(
                <Input />
              )}
            </Form.Item>
            <Form.Item label="主持单位 ：">
              { getFieldDecorator('schoolName', {
              })(
                <Input />
              )}
            </Form.Item>
            <Form.Item >
              <Button size="small" type="primary" htmlType="submit" icon="search" onClick={this.doSearch}>查询</Button>
              <Button size="small" style={{marginLeft:5}} type="primary" onClick={() => this.props.form.resetFields()}><Icon type="rollback" />重置</Button>
              <Button size="small" style={{marginLeft:5}} type="primary" onClick={this.addNew}><Icon type="plus" />添加</Button>
              <Button size="small" style={{marginLeft:5}} type="primary" onClick={this.editProject}><Icon type="edit" />编辑</Button>
              <Popconfirm title="确认删除选中的数据吗?" onConfirm={this.deletePro} okText="是" cancelText="否">
                <Button size="small" style={{ marginLeft: 5 }} type="primary"><Icon type="close" />删除</Button>
              </Popconfirm>
              <Upload action={'//project/uploadProject'}>
                <Button>
                  <Icon type="upload" /> 上传文件
                </Button>
              </Upload>
            </Form.Item>
          </Form>
          <Table
            rowSelection={rowSelection}
            columns={columns}
            dataSource={projectList}
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
  const { userType, userName } = state.baseModel;
  const {
    projectList,
    projectListNum,
    projectListPage,
  } = state.ProjectModel;
  return {
    userType,
    userName,
    projectList,
    projectListNum,
    projectListPage,
  };
}

export default connect(mapStateToProps)(Form.create({})(ProjectInfoList));
