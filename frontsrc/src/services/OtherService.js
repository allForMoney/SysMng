import request from '../utils/request';
import qs from 'qs';

export function updateReportTime(params) {
  return request('/system/changeReportTime', {
    method: 'post',
    data: JSON.stringify(params),
  });
}
export function getReportTime() {
  return request('/system/getReportTime');
}

export function sendSMS(params) {
  return request(`/system/sendmessage?${qs.stringify(params)}`, {
    method: 'post',
  });
}
export function resetPwd(params) {
  return request(`/user/resetpassword?${qs.stringify(params)}`, {
    method: 'post',
  });
}

export function getUserList(params) {
  return request(`/user/all?${qs.stringify(params)}`);
}
