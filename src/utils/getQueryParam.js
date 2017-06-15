/**
 * 获取 URL search 参数
 * @author minhui.lmh
 * @since 2017/5/4.
 */

import qs from 'qs';

export default function getQueryParam(param) {
  let search = window.location.search;

  search = search.slice(1);

  const params = qs.parse(search);

  if (param) {
    return Object.prototype.hasOwnProperty.call(params, param) ? params[param] : null;
  }

  return params;
}
