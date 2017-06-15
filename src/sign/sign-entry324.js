/**
 * 签约流程
 * @url /platform/open.htm?
 * @author yuqing.he
 * @since 2017/5/27.
 */
import React from 'react';
import dva from 'dva/mobile';
import createLoading from 'dva-loading';
import Sign from './Sign';
import './sign.html';

const app = dva({
  onError(e) {
    // eslint-disable-next-line
    console.log(e);
  },
});

app.use(createLoading());

app.model(require('./models/signModel'));

app.router(() => <Sign />);

app.start('#react-root');
