import qs from 'qs';

import request from '../utils/request';

export function getProjectInfoById(params) {
  return request(`/project/?${qs.stringify(params)}`);
}

export function getProjectList(params) {
  return request(`/project/getProjectList?${qs.stringify(params)}`);
}

export function deletePro(params) {
  return request(`/project/deletePro?${qs.stringify(params)}`);
}

export function AddProject(params) {
  return request('...', {
    method: 'post',
    data: params,
  });
}
