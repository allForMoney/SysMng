import qs from 'qs';

import request from '../utils/request';


export function logout() {
  return request('/logout');
}

export function getProjectInfoById(params) {
  return request(`/project/get?${qs.stringify(params)}`);
}

export function saveMsg(params) {
  console.log(params);
  const { mesType } = params;
  if (!mesType) {
    return;
  }
  let url = '';
  switch (mesType) {
    case 'budget':
      url = 'leavemessage/budget/create';
      break;
    case 'project':
      url = 'leavemessage/project/create';
      break;
    case 'indicator':
      url = 'leavemessage/indicator/create';
      break;
    default:
      break;
  }

  return request(url, {
    method: 'post',
    data: JSON.stringify(params),
  });
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

export function modifyPass(params) {
  console.log(params);
  return request(`/user/changePassword?${qs.stringify(params)}`, {
    method: 'post',
  });
}
export function updateConcat(params) {
  console.log(params);
  return request('/project/contacter/change', {
    method: 'post',
    data: JSON.stringify(params),
  });
}
