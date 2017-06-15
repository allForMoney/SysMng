import debug from 'debug';

const req = debug('Life:Req ~>');
const res = debug('Life:Res ~>');
const log = debug('Life:Log ~>');

export default {
  req,
  res,
  log,
};
