import fetch from 'dva/fetch';
import { message } from 'antd';
/**
 * Requests a URL, returning a promise.
 *
 * @param  {string} url       The URL we want to request
 * @param  {object} [options] The options we want to pass to "fetch"
 * @return {object}           An object containing either "data" or "err"
 */
export default async function request(url, options) {
  if (options && options.method) {
    options.headers = {
      'Content-Type': 'application/json; charset=UTF-8'
    },
    options.body = options.data;
  }
  const response = await fetch(url, options);

  const data = await response.json();
  return data;
}
