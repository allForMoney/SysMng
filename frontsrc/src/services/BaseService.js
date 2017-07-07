import qs from 'qs';

import request from '../utils/request';


export function logout() {
  return request('/logout');
}

export function getProjectInfoById(params) {
  return request(`/project/get?${qs.stringify(params)}`);
}

export function login(params) {
  console.log(params);
  return request('/loginSys', {
    method: 'post',
    data: JSON.stringify(params),
  });
}
export function getProviceInfo() {
  return request('/common/provences', {
    method: 'post',
  });
}
export function isLogin(params) {
  console.log(params);
  return request('/isLogin');
}
