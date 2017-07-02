import qs from 'qs';
import request from '../utils/request';

export function getBudgetSeasonList(params) {
  return request(`/budget/report/quarterly/detail?${qs.stringify(params)}`);
}

export function getAllBudgetRec(params) {
  return request(`/budget/allimport?${qs.stringify(params)}`);
}

export function getBudgetRecList(params) {
  return request(`/project/getBudgetRecList?${qs.stringify(params)}`);
}
export function getBudgetProjectList(params) {
  console.log(params);
  return request(`/budget/getByProjectId?${qs.stringify(params)}`);
}

export function updateSeasonBudget(params) {
  return request('/budget/report/update', {
    method: 'post',
    data: JSON.stringify(params),
  });
}
export function changeCheckStatus(params) {
  return request('/budget/report/quarterly/audit', {
    method: 'post',
    data: JSON.stringify(params),
  });
}
