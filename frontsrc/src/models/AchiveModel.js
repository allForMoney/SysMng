
import {
  saveAchiveTarget,
  getAchiveAllList,
  changeCheckStatus,
  getProjectArchive,
  updateAchiveTarget,
} from '../services/AchiveService';
import { message } from 'antd';

export default {
  namespace: 'achiveModel',

  state: {
    loading: false,
    id: '',
    indicatorBase: {},
    indicatorDetails: [],

    achiveAllList: [],
    achiveAllListNum: 0,
    achiveAllListPage: 0,
    filterParam: {}

  },
  effects: {

    * getProjectArchive({ payload }, { call, select, put }) {
      const { projectInfo } = yield select(state => state.baseModel);
      const projectId = projectInfo.id;

      const data = yield call(getProjectArchive, { projectId, ...payload });
      if (data && data.code === '1' && data.result) {
        const { id, indicatorBase, indicatorDetails } = data.result;
        yield put({
          type: 'setState',
          payload: {
            id,
            indicatorBase,
            indicatorDetails,
          }
        });
      }
    },
    /** 获取所有项目的预算信息 */
    * getAchiveAllList({ payload }, { call, select, put }) {
      const { achiveAllListPage, filterParam } = yield select(state => state.achiveModel);

      const data = yield call(getAchiveAllList, { achiveAllListPage, ...filterParam });
      if (data && data.code === '1' && data.result) {
        const { currentPage, list, pageNum } = data.result;
        yield put({
          type: 'setState',
          payload: {
            achiveAllList: list,
            achiveAllListNum: pageNum,
            achiveAllListPage: currentPage,
          }
        });
      }
    },

    * updateAchiveTarget({ payload }, { call, select }) {
      const {
        indicatorBase,
        indicatorDetails,
      } = yield select(state => state.achiveModel);
      const data = yield call(updateAchiveTarget, {
        indicatorBase,
        indicatorDetails,
      });
      if (data && data.code === '1') {
        message('更新成功');
      }
    },

    * changeCheckStatus({ payload }, { call }) {
      const data = yield call(changeCheckStatus, payload);
      if (data && data.code === 1) {
        message('审核成功');
      }
    },


  },

  reducers: {
    setState(state, action) {
      return { ...state, ...action.payload };
    },


    setLoading(state, action) {
      return { ...state, loading: action.payload };
    },
  },
};
