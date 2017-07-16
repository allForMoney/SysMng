import qs from 'qs';
import request from '../utils/request';

export function getDataAnaList(params) {
  return request(`/dataanalyse/byyear?${qs.stringify(params)}`, {
    data: JSON.stringify(params),
  });
}
