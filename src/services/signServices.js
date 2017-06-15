import qs from 'qs';
import {
  API_GET_BASEINFO,
  API_SEND_SMSCODE,
  API_CHECK_SMSCODE,
  API_CHECK_PUBLICNAME,
  API_GET_CLASSIFY,
  API_JCROP_PIC,
  API_SUBMIT,
} from '../configs/signConfigs';
import request from '../utils/request';
import getCookie from '../utils/signUtils';

export function getBaseInfo(params) {
  return request(`${API_GET_BASEINFO}?${qs.stringify(params)}`);
}

export function sendSMSCode(params) {
  return request(`${API_SEND_SMSCODE}?${qs.stringify(params)}`);
}

export function checkSMSCode(params) {
  return request(API_CHECK_SMSCODE, {
    method: 'post',
    data: params,
  });
}

export function getClassify(params) {
  return request(`${API_GET_CLASSIFY}?${qs.stringify(params)}`);
}

export function uploadjcropPics(params) {
  const ctoken = getCookie('ctoken');
  const fullParam = Object.assign(params, {
    behaviorId: 'uploadPublicBook',
    imgDimensionType: 'logo',
    _input_charset: 'utf-8',
    ctoken
  });
  return request(API_JCROP_PIC, {
    method: 'post',
    data: fullParam,
  });
}

export function checkPublicName(params) {
  return request(API_CHECK_PUBLICNAME, {
    method: 'post',
    data: params,
  });
}
export function submit(params) {
  return request(`${API_SUBMIT}?${qs.stringify(params)}`);
}
