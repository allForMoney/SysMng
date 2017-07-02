import qs from 'qs';

import request from '../utils/request';

/** 获取绩效目标 */
export function getProjectArchive(params) {
  return request(`/indicator/detail?${qs.stringify(params)}`);
}
/** 获取所有绩效记录 */
export function getAllAchiveRec(params) {
  return request(`/indicator/allimport?${qs.stringify(params)}`);
}

/** 更新绩效 */
export function updateAchiveTarget(params) {
  return request('/indicator/update', {
    method: 'post',
    data: JSON.stringify(params),
  });
}

export function changeCheckStatus(params) {
  return request('/indicator/checkstatus', {
    method: 'post',
    data: JSON.stringify(params),
  });
}
