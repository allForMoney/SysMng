import qs from 'qs';
import request from '../utils/request';

export function getBudgetSeasonList(params) {
  return request(`/budget/report/quarterly/detail?${qs.stringify(params)}`);
}
export function getLastAdjust(params) {
  return request(`/budgetadjust/getLastAdjust?${qs.stringify(params)}`);
}

export function getAllBudgetRec(params) {
  return request(`/budget/allimport?${qs.stringify(params)}`);
}
/** 获取预算调整列表 */
export function getBudgetJustifyList(params) {
  return request(`/budgetadjust/all?${qs.stringify(params)}`);
}
/** 获取预算调整列表 */
export function getBudgetJustifyCompareList(params) {
  return request(`/budgetadjust/getCompareInfo?${qs.stringify(params)}`);
}

export function getBudgetRecList(params) {
  return request(`/budget/report/all?${qs.stringify(params)}`);
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
  return request(`/budgetadjust/audit?${qs.stringify(params)}`, {
    method: 'post',
  });
}

export function changeSeasonCheckStatus(params) {
  return request(`/budget/report/quarterly/audit?${qs.stringify(params)}`, {
    method: 'post',
  });
}
