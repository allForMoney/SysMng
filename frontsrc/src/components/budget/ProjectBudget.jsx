import React from 'react';
import { connect } from 'dva';
import { Table, Pagination,
Card,
Button,
 } from 'antd';
import { routerRedux } from 'dva/router';
import FrameContent from '../common/FrameContent';

function ProjectBudget(props) {
  function pageChangeHandler() {
  }
  const { projectList, projectTotal, projectPage, projectName, loading } = props;
  const columns =
    [{
      title: '序号',
      dataIndex: 'index',
      key: 'index',
      width: 10,
    }, {
      title: '资金用途',
      dataIndex: 'index2',
      key: 'index2',
      width: 10,
    }, {
      title: '项目建设资金总数',
      dataIndex: 'index3',
      key: 'index3',
      width: 10,
    }, {
      title: '资金来源',
      children: [{
        title: '部本专项资金',
        children: [{
          title: '合计',
          dataIndex: 'total',
          key: 'street',
        }, {
          title: '占项目建设资金的比例(%)',
          dataIndex: 'street',
          key: 'street3',
        }, {
          title: '其中',
          children: [{
            title: '第一年',
            dataIndex: 'street',
            key: 'street33',
          }, {
            title: '第二年',
            dataIndex: 'street',
            key: 'street333',
          }]
        }],
      }, {
        title: '项目筹措资金',
        children: [{
          title: '合计',
          dataIndex: 'total',
          key: 'street1',
        }, {
          title: '占项目建设资金的比例(%)',
          dataIndex: 'street',
          key: 'street11',
        }, {
          title: '其中',
          children: [{
            title: '举办方或地方财政投入资金',
            children: [{
              title: '第一年',
              dataIndex: 'street',
              key: 'stre2et',
            }, {
              title: '第二年',
              dataIndex: 'street',
              key: 'str22eet',
            }, {
              title: '第三年',
              dataIndex: 'street',
              key: 'str2222eet',
            }]
          }, {
            title: '行业企业支持资金',
            children: [{
              title: '第一年',
              dataIndex: 'street',
              key: 'seetreet',
            }, {
              title: '第二年',
              dataIndex: 'street',
              key: 'streeet',
            }, {
              title: '第三年',
              dataIndex: 'street',
              key: 'streeeeeet',
            }]
          }, {
            title: '相关院校自筹资金',
            children: [{
              title: '第一年',
              dataIndex: 'street',
              key: 'streeft',
            }, {
              title: '第二年',
              dataIndex: 'street',
              key: 'streefft',
            }, {
              title: '第三年',
              dataIndex: 'street',
              key: 'strefffet',
            }]
          }]
        }],
      }],
    }];

  const title = `项目预算表${projectName}`;
  return (
    <FrameContent>
      <Card title={title}>
        <p>
           该信息为只读信息，如有疑义，请
          <Button
            onClick={
            () => props.dispatch({
              type: 'baseModel/setState',
              payload: { showMsgModal: true }
            })
            }
          >留言</Button>
          。 预算一经确定，原则上不予调整。确须调整，请点击
          <Button
            onClick={() => {
              props.dispatch(routerRedux.push({
                pathname: '/budget/justify',
              }));
            }
            }
          >预算调整</Button>办理手续。
        </p>
        <Table
          columns={columns}
          dataSource={projectList}
          loading={loading}
          rowKey={record => record.id}
          pagination={false}
        />
        <Pagination
          className="ant-table-pagination"
          total={projectTotal}
          current={projectPage}
          pageSize={20}
          onChange={pageChangeHandler}
        />
      </Card>
    </FrameContent>
  );
}
function mapStateToProps(state) {
  const { projectList, projectTotal, projectPage, projectName } = state.baseModel;
  return {
    loading: state.loading.models.baseModel,
    projectList,
    projectTotal,
    projectPage,
    projectName,
  };
}

export default connect(mapStateToProps)(ProjectBudget);
