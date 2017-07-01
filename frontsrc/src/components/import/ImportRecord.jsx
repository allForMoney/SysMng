/**
 * 项目导入记录
 * 这个页面可以复用,可以分别用来展示项目预算和绩效目标的导入情况
 */
import { connect } from 'dva';
import React from 'react';
import { Form, Input, Button, Icon, Card, Table, Upload, message, Modal, Popconfirm } from 'antd';
import FrameContent from '../common/FrameContent';
import LinkBtn from '../common/LinkBtn';
import styles from '../../index.less';
import { routerRedux } from 'dva/router';


class ImportRecord extends React.Component {
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

  showDetail =(record) => {
    console.log(record);
  }

  render() {
    const {
      dataSouce,
      pageNum,
      pageTotal,
      tableTitel,
      loading,
    } = this.props;
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
        title: '第一主持单位',
        dataIndex: 'schoolName',
      }, {
        title: '导入时间',
        dataIndex: 'partnerSchool',
      }, {
        title: '导入人',
        dataIndex: 'partnerSchool',
      }, {
        title: '查看明细',
        render: (item, record) => {
          return (<LinkBtn onClick={showDetail.bind(this, record)}>查看详情</LinkBtn>)
        }
      }];

    const pageConfig = {
      className: 'ant-table-pagination',
      total: pageTotal,
      current: pageNum,
      pageSize: 20,
      onChange: this.onPageChanged,
    };
    
    return (
      <FrameContent>
        <Card title={tableTitel}>
          <Table
            columns={columns}
            dataSource={dataSouce}
            loading={loading}
            rowKey={record => record.name}
            pagination={pageConfig}
          />
        </Card>
      </FrameContent>
    );
  }
}

export default ImportRecord;
