import request from '../utils/request';
import qs from 'qs';

export function getBudgetMsgList(params) {
  return request(`/leavemessage/budget/all?${qs.stringify(params)}`);
}

export function getAchiveMsgList(params) {
  return request(`/leavemessage/indicator/all?${qs.stringify(params)}`);
}

export function saveBudgetReply(params) {
  return request(`/leavemessage/reply?${qs.stringify(params)}`, {
    method: 'post',
  });
}

