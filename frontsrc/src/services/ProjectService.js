import qs from 'qs';

import request from '../utils/request';

export function getProjectInfoById(params) {
  return request(`/project/?${qs.stringify(params)}`);
}

export function checkSMSCode(params) {
  return request('...', {
    method: 'post',
    data: params,
  });
}