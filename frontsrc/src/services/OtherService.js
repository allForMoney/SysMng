import request from '../utils/request';

export function updateReportTime(params) {
  return request('/other/updateReportTime', {
    method: 'post',
    data: JSON.stringify(params),
  });
}
