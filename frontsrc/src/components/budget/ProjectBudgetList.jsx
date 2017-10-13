import React from 'react';
import { connect } from 'dva';
import { Table, Pagination } from 'antd';
import { routerRedux } from 'dva/router';
import FrameContent from '../components/common/FrameContent';

function ProjectBudgetList({ dispatch, list: dataSource, loading, total, page: current }) {
  function pageChangeHandler(page) {
    dispatch(routerRedux.push({
      pathname: '/users',
      query: { page }
    }));
  }
  const columns = [{
    title: 'Name',
    dataIndex: 'name',
    key: 'name',
    render: (text, { id }) => <a href="" > { text } {id} </a>,
  },
  {
    title: 'Email',
    dataIndex: 'email',
    key: 'email',
  },
  {
    title: 'Website',
    dataIndex: 'website',
    key: 'website',
  },
  {
    title: 'Operation',
    key: 'operation',
  },
  ];

  return (

    <div>
      <Table
        columns={columns}
        dataSource={dataSource}
        bordered
        loading={loading}
        rowKey={record => record.id}
        pagination={false}
      />
      <Pagination
        className="ant-table-pagination"
        total={total}
        current={current}
        pageSize={20}
        onChange={pageChangeHandler}
      />
    </div>

  );
}
function mapStateToProps(state) {
  const { list, total, page } = state.listmodel;
  return {
    loading: state.loading.models.users,
    list,
    total,
    page,
  };
}

export default connect(mapStateToProps)(ProjectBudgetList);
