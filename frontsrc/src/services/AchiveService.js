import qs from 'qs';

import request from '../utils/request';

export function saveAchiveTarget(params) {
  return request(`/achive/?${qs.stringify(params)}`);
}

export function getProjectList(params) {
  return request(`/achive/getProjectList?${qs.stringify(params)}`);
}

// export function getBudgetRecList(params) {
//   return request(`/project/getBudgetRecList?${qs.stringify(params)}`);
// }

// export function deletePro(params) {
//   return request(`/project/deletePro?${qs.stringify(params)}`);
// }

export function changeCheckStatus(params) {
  return request('/achive/checkstatus', {
    method: 'post',
    body: params,
  });
}
