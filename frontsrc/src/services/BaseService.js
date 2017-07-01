import qs from 'qs';

import request from '../utils/request';


export function logout() {
  return request('/logout');
}

export function login(params) {
  console.log(params);
  return request('/login', {
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
