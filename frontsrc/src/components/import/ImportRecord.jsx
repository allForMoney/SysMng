/**
 * 项目导入记录
 * 这个页面可以复用,可以分别用来展示项目预算和绩效目标的导入情况
 */
import moment from 'moment';
import React from 'react';
import { Form, Input, Button, Icon, Card, Table, Upload, message, Modal, Popconfirm } from 'antd';
import FrameContent from '../common/FrameContent';
import LinkBtn from '../common/LinkBtn';
import styles from '../../index.less';
import { routerRedux } from 'dva/router';


class ImportRecord extends React.Component {
  state = {
    editRec: {}
  }

  componentDidMount() {
    this.props.dispatch({
      type: 'ImportData/setState',
      payload: {
        allImportPage: 1,
      },
    });

    setTimeout(() => {
      this.props.dispatch({
        type: 'ImportData/getAllImportData',
      });
    }, 100);
  }

  onPageChanged = (page) => {
    this.props.dispatch({
      type: 'ImportData/setState',
      payload: {
        allImportPage: page,
      },
    });
    this.props.dispatch({
      type: 'ImportData/getAllImportData',
    });
  }

  render() {
    const {
      dataSouce,
      pageNum,
      pageTotal,
      tableTitel,
      loading,
      actionFunc,
    } = this.props;
    const columns =
      [{
        title: '项目编号',
        dataIndex: 'projectNo',
      }, {
        title: '专业名称',
        dataIndex: 'majorName',
      }, {
        title: '第一主持单位',
        dataIndex: 'schoolName',
      }, {
        title: '导入时间',
        dataIndex: 'partnerSchool',
        render: time => moment(time).format('YYYY-MM-DD:HH:mm:ss')
      }, {
        title: '导入人',
        dataIndex: 'importUserNo',
      }, {
        title: '查看明细',
        render: (item, record) => (<LinkBtn onClick={actionFunc.bind(this, record)}>查看详情</LinkBtn>)
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
