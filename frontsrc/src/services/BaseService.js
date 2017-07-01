import qs from 'qs';

import request from '../utils/request';


export function logout(params) {
  return request('/logout');
}

export function login(params) {
  console.log(params);
  return request('/login', {
    method: 'post',
    data: JSON.stringify(params),
  });
}
export function login(params) {
  console.log(params);
  return request('/isLogin', {
    method: 'post',
    data: JSON.stringify(params),
  });
}
