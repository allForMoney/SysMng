export default {
  namespace: 'baseModel',

  state: {
    userType: 'manager',  // inputer/finan/manager/admin/ministry
    userName: '12345'
  },
  effects: {

    * updateConcat({ payload }, { call, put }) {
      console.log(payload);
    },

    * updateAdvice({ payload }, { call, put }) {
      console.log(payload);
    },

    * modifyPass({ payload }, { call, put }) {
      console.log(payload);
    },
  }
};
