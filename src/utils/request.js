import ajax from '@alipay/ajax';
import {
  toast,
  debug,
  localStorageDebug,
} from '../utils';
import {
  REQUEST_TIMEOUT,
  ERROR_CODE,
} from '../config';

export function checkLoginStatus(response) {
  if (response && response.stat === 'deny') {
    toast({
      type: 'error',
      msg: '登录超时，请重新登录！',
    });

    // 如果没有则跳转到登录地址 response.target
    /*setTimeout(() => {
     window.location = '/';
     }, 500);
     */
  }
}

// 错误状态处理
// TODO: 待确定生活号后台错误信息
export function failedHandler(response = {}) {
  const {
    stat, // 状态
    errorCode, // 错误码
  } = response;
  let {
    errorMessage, // 错误信息
  } = response;
  let messageType = 'warning';
  const isFailed = stat !== 'ok';

  if (isFailed) {
    /*
    if (errorCode === ERROR_CODE.NO_PERMISSION) {
      messageType = 'error';
      resMsg = errorMessage || '没有操作权限！';
    } else {
      resMsg = errorMessage || '请求出错误，请重试！';
    }
    */

    errorMessage = errorMessage || '发生错误，请重试！';

    toast({
      type: messageType,
      msg: errorMessage,
    });

    return {
      status: 'failed',
      message: errorMessage,
      code: errorCode,
    };
  }
}

/**
 * Requests a URL, returning a promise.
 * @see http://web.npm.alibaba-inc.com/package/@alipay/ajax
 */
export default function request(url, { method = 'get', ...options } = {}) {
  const DEFAULTS = {
    url,
    type: 'json',
    method,
    // 超时时间，单位秒
    timeout: REQUEST_TIMEOUT,
    cache: false,
  };

  if (method === 'post') {
    DEFAULTS.contentType = 'application/x-www-form-urlencoded; charset=utf-8';
  }

  const reqParams = {
    ...DEFAULTS,
    ...options,
  };

  debug.req(`Request => ${url}`, reqParams);

  return ajax(reqParams).then((data, status, xhr) => { // eslint-disable-line
    debug.res('Response => %s %o', url, data);
    localStorageDebug(`Res_form_${url}`, JSON.stringify(data));

    let error = null;

    if (data) {
      // 登录状态检查
      checkLoginStatus(data);

      // 错误状态处理
      error = failedHandler(data);

      return ({ data, error });
    }

    return ({ data: null, error });
  }).catch(err => {
    console.log('AJAX Error', err);

    let code = null;
    let {
      message,
    } = err;

    if (message === 'timeout') {
      message = '请求超时，请重试！';
      code = ERROR_CODE.TIMEOUT;
    } else if (message.indexOf('Unexpected token') > -1) {
      message = '数据解析出错，请重试！';
      code = ERROR_CODE.SYNTAX_ERROR;
    }

    message = message || '请求数据出错，请重试！';

    toast({
      type: 'error',
      msg: message,
    });

    const error = {
      message,
      status: 'error',
      code,
    };

    return ({ error });
  });
};
