import request from '../utils/request';

export function updateReportTime(params) {
  return request('/other/updateReportTime', {
    method: 'post',
    data: JSON.stringify(params),
  });
}

export function sendSMS(params) {
  return request('/other/sendSMS', {
    method: 'post',
    data: JSON.stringify(params),
  });
}
