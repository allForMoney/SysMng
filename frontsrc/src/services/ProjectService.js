import qs from 'qs';

import request from '../utils/request';

export function getProjectInfoById(params) {
  return request(`/project/get?${qs.stringify(params)}`);
}

export function getProjectList(params) {
  return request(`/project/all?${qs.stringify(params)}`);
}

export function getBudgetRecList(params) {
  return request(`/project/getBudgetRecList?${qs.stringify(params)}`);
}

export function deletePro(params) {
  return request('/project/delete', {
    method: 'post',
    data: JSON.stringify(params),
  });
}

export function AddProject(params) {
  console.log(params);
  return request('/project/create', {
    method: 'post',
    data: JSON.stringify(params),
  });
}

export function UpdateProject(params) {
  console.log(params);
  return request('/project/update', {
    method: 'post',
    data: JSON.stringify(params),
  });
}
