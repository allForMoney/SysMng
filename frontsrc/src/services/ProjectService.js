import qs from 'qs';

import request from '../utils/request';

export function getProjectInfoById(params) {
  return request(`/project/?${qs.stringify(params)}`);
}

export function getProjectList(params) {
  return request(`/project/getProjectList?${qs.stringify(params)}`);
}

export function getBudgetRecList(params) {
  return request(`/project/getBudgetRecList?${qs.stringify(params)}`);
}

export function deletePro(params) {
  return request(`/project/deletePro?${qs.stringify(params)}`);
}

export function AddProject(params) {
  console.log(params);
  return request('/project', {
    method: 'post',
    data: qs.stringify(params),
  });
}
