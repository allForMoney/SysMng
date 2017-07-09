import qs from 'qs';

import request from '../utils/request';

export function getAllImportData(params) {
  return request(`/common/import/all?${qs.stringify(params)}`);
}
