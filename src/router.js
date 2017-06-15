import React from 'react';
import { Router, Route } from 'dva/router';
import Sign from './routes/Sign';
import List from './routes/List';

function RouterConfig({ history }) {
  return (
    <Router history={history}>
      <Route path="/" component={Sign} />
      <Route path="/sign" component={List} />
    </Router>
  );
}

export default RouterConfig;
