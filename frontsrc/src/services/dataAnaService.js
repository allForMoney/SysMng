import qs from 'qs';
import request from '../utils/request';

export function getDataAnaList(params) {
  return request(`/experts/all?${qs.stringify(params)}`);
}
