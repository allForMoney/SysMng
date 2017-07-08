import qs from 'qs';
import request from '../utils/request';

export function getExpertList(params) {
  return request(`/experts/all?${qs.stringify(params)}`);
}

export function addExpert(params) {
  return request('/experts/create', {
    method: 'post',
    data: JSON.stringify(params),
  });
}
export function updateExpert(params) {
  return request('/experts/update', {
    method: 'post',
    data: JSON.stringify(params),
  });
}

export function deleteExpert(params) {
  return request('/experts/delete', {
    method: 'post',
    data: JSON.stringify(params),
  });
}
