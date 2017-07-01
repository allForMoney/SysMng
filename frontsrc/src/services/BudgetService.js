import qs from 'qs';
import request from '../utils/request';

export function getBudgetSeasonList(params) {
  return request(`/budget/report/quarterly/detail?${qs.stringify(params)}`);
}

export function getOutcomeBudget(params) {
  return request(`/project/getProjectList?${qs.stringify(params)}`);
}

export function getBudgetRecList(params) {
  return request(`/project/getBudgetRecList?${qs.stringify(params)}`);
}
export function getBudgetProjectList(params) {
  return request(`/budget/getByProjectId?${qs.stringify(params)}`);
}

export function updateSeasonBudget(params) {
  return request('/budget/report/update', {
    method: 'post',
    data: params,
  });
}
export function changeCheckStatus(params) {
  return request('/budget/checkstatus', {
    method: 'post',
    data: params,
  });
}
