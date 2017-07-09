import request from '../utils/request';
import qs from 'qs';

export function updateReportTime(params) {
  return request('/system/changeReportTime', {
    method: 'post',
    data: JSON.stringify(params),
  });
}

export function sendSMS(params) {
  return request(`/system/sendmessage?${qs.stringify(params)}`, {
    method: 'post',
  });
}
export function resetPwd(params) {
  return request('/other/resetPwd', {
    method: 'post',
    data: JSON.stringify(params),
  });
}

export function getUserList(params) {
  return request(`/other/getUserList?${qs.stringify(params)}`);
}
