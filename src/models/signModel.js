import { message } from 'antd';
import {
  SIGN_NAMESPACE,
  MAINBODY_PRIVETE,
} from '../configs/signConfigs';
import {
  CHANGE_STEP,
  SEND_SMSCODE,
  CHECK_SMSCODE,
  CHECK_PUBLICNAME,
  GET_CLASSIFY,
  GET_BASEINFO,
  JCROP_PIC,
  SUBMIT,
} from '../actions/signActions';
import {
  getBaseInfo,
  sendSMSCode,
  checkSMSCode,
  checkPublicName,
  getClassify,
  uploadjcropPics,
  submit,
} from '../services/signServices';

export default {
  namespace: SIGN_NAMESPACE,

  state: {
    currentStep: 2,
    certifiedName: '', // 支付宝实名认证名称
    isAgreed: true, // 是否已同意协议

    editName: false, // 是否编辑用户名
    editPhone: false, // 是否编辑手机号
    editEmail: false, // 是否编辑邮箱地址

    mainBody: 'private', // 营业执照
    isPerson: true,
    privateLicenseId: '', // 营业执照id
    privateLicenseURL: '', // 个人营业执照url
    privateLicenseName: '', // 个人营业执照名字
    merchantCompanyName: '', // 企业/个人名称
    name: '', // 联系人姓名
    email: '', // 联系人电子邮箱
    mobile: '', // 联系人手机号码
    newConcatName: '', // 修改后的联系人姓名
    newEmail: '', // 修改后的电子邮箱
    newPhoneNum: '', // 修改后的电话号码

    logoId: '', // 公众账号图标id
    logoURL: '', // 公众账号图标地址
    backgroundURL: '', // 公众账号背景图片地址
    publicName: '', // 生活号名称
    publicDesc: '', // 生活号备注信息

    mccIndustry0: '', // 行业一级分类
    mccIndustry1: '', // 行业二级分类
    mccIndustry2: '', // 行业三级分类

    ownIntellectualId: '', // 知识产权证明
    ownIntellectualUrl: '',
    authorizationId: '', // 授权书
    authorizationURL: '',
    authorizationName: '',
    qualificationId1: '', // 个人资质证明
    qualificationURL1: '',
    qualificationName1: '',

    publicNameAvailable: true,

    mccCategories1: [],
    mccCategories2: [],

    showAddHeadImg: false,
    showAddBgImg: false,
    currentPicList: [],
    logoFileList: [],
    bgFileList: [],

    showLogoError: false,
    showBgError: false,

    loading: false,
  },

  subscriptions: {
    init({ dispatch }) {
      dispatch({
        type: GET_BASEINFO
      });
    },
  },

  effects: {

    * [GET_BASEINFO]({ payload }, { call, put }) {
      const { data } = yield call(getBaseInfo);
      const {
        logoURL,
        backgroundURL,
        name,
        email,
        mobile,
        mainBody,
      } = data.openForm;
      let logoFileList = [];
      if (logoURL) {
        logoFileList = [{
          url: logoURL,
          uid: -1
        }];
      }
      let bgFileList = [];
      if (backgroundURL) {
        bgFileList = [{
          url: backgroundURL,
          uid: -1
        }];
      }
      const editName = !name;
      const editPhone = !mobile;
      const editEmail = !email;
      const isPerson = mainBody === MAINBODY_PRIVETE;
      if (data && data.stat === 'ok') {
        // 根据获取的数据,更新state
        yield put({
          type: 'setState',
          payload: {
            mccCategories1: data.mccCategoriesSub,
            showAddHeadImg: !logoURL,
            showAddBgImg: !backgroundURL,
            logoFileList,
            bgFileList,
            editName,
            editPhone,
            editEmail,
            isPerson,
            certifiedName: data.certifiedName,
            ...data.openForm }
        });
      }
    },

    * [GET_CLASSIFY]({ payload }, { call, put }) {
      const { data } = yield call(getClassify, payload);
      if (data && data.stat === 'ok') {
        // 根据获取的数据,更新state
        yield put({
          type: 'setState',
          payload: { mccCategories2: data.mccCategories }
        });
      }
    },

    * [SEND_SMSCODE]({ payload }, { call }) {
      const { mobile } = payload;
      const { stat } = yield call(sendSMSCode, { mobile });
      if (stat === 'ok') {
        message.success('短信验证码发送成功');
      }
    },

    * [CHECK_SMSCODE]({ payload }, { call, put }) {
      const { newPhoneNum } = payload;
      const { stat } = yield call(checkSMSCode, payload);
      if (stat === 'ok') {
        yield put({
          type: 'setState',
          payload: { newPhoneNum }
        });
        yield put({
          type: `${CHANGE_STEP}`,
          payload: { currentStep: 1 }
        });
      }
    },

    * [CHECK_PUBLICNAME]({ payload }, { call, put }) {
      const { newValue } = payload;
      const { data } = yield call(checkPublicName, { publicName: newValue });
      if (data.stat === 'ok') {
        let isAvailable = true;
        if (data.existed === true) {
          isAvailable = false;
        }
        yield put({
          type: 'setState',
          payload: {
            publicNameAvailable: isAvailable,
            publicName: newValue }
        });
      }
    },

    * [JCROP_PIC]({ payload }, { call, put, select }) {
      const { jcropAttr, ...params } = payload;
      const { data } = yield call(uploadjcropPics, params);
      let {
        showAddHeadImg,
        showAddBgImg,
        logoFileList,
        bgFileList,
        showLogoError,
        showBgError,
      } = yield select(state => state[SIGN_NAMESPACE]);
      if (data.stat === 'ok') {
        const url = data.result;
        if (jcropAttr === 'logoURL') {
          showAddHeadImg = false;
          showLogoError = false;
          logoFileList = [{
            uid: -1,
            url
          }];
        } else {
          showAddBgImg = false;
          showBgError = false;
          bgFileList = [{
            uid: -1,
            url
          }];
        }
        yield put({
          type: 'setState',
          payload: {
            [jcropAttr]: data.result,
            showAddBgImg,
            bgFileList,
            logoFileList,
            showLogoError,
            showBgError,
          }
        });
      } else {
        if (jcropAttr === 'logoURL') {
          showAddHeadImg = true;
          logoFileList = [];
        } else {
          showAddBgImg = true;
          bgFileList = [];
        }
        yield put({
          type: 'setState',
          payload: { showAddHeadImg, showAddBgImg, logoFileList, bgFileList }
        });
        message.error(`${data.errorMessage}`);
      }
    },

    * [SUBMIT]({ payload }, { call, select }) {
      const stateData = yield select(state => state[SIGN_NAMESPACE]);
      const {
          mainBody,
          privateLicenseId, // 营业执照id
          privateLicenseURL, // 个人营业执照url
          privateLicenseName, // 个人营业执照名字
          merchantCompanyName, // 企业/个人名称
          name,
          mobile,
          email,
          logoId, // 公众账号图标id
          logoURL, // 公众账号图标地址
          backgroundURL, // 公众账号背景图片地址
          publicName, // 生活号名称
          publicDesc, // 生活号备注信息
          mccIndustry0, // 行业一级分类
          mccIndustry1, // 行业二级分类
          mccIndustry2, // 行业三级分类
          ownIntellectualId, // 知识产权证明
          ownIntellectualUrl,
          authorizationId, // 授权书
          authorizationURL,
          authorizationName,
          qualificationId1, // 个人资质证明
          qualificationURL1,
          qualificationName1,
      } = stateData;
      yield call(submit, {
        mainBody,
        privateLicenseId, // 营业执照id
        privateLicenseURL, // 个人营业执照url
        privateLicenseName, // 个人营业执照名字
        merchantCompanyName, // 企业/个人名称
        name,
        mobile,
        email,
        logoId, // 公众账号图标id
        logoURL, // 公众账号图标地址
        backgroundURL, // 公众账号背景图片地址
        publicName, // 生活号名称
        publicDesc, // 生活号备注信息
        mccIndustry0, // 行业一级分类
        mccIndustry1, // 行业二级分类
        mccIndustry2, // 行业三级分类
        ownIntellectualId, // 知识产权证明
        ownIntellectualUrl,
        authorizationId, // 授权书
        authorizationURL,
        authorizationName,
        qualificationId1, // 个人资质证明
        qualificationURL1,
        qualificationName1,
      });
    },
  },

  reducers: {
    setState(state, action) {
      return { ...state, ...action.payload };
    },

    [CHANGE_STEP](state, action) {
      return { ...state, ...action.payload };
    },

    setLoading(state, action) {
      return { ...state, loading: action.payload };
    },
  },
};
