import React from 'react';
import { Router, Route } from 'dva/router';
import Sign from './routes/Sign';
import List from './routes/List';
import Blank from './routes/Blank';
import ModifyConcat from './components/sysConfig/ModifyConcat';
import Advice from './components/sysConfig/Advice';
import BudgetBaseInfo from './components/budget/BudgetBaseInfo';

function RouterConfig({ history }) {
  return (
    <Router history={history}>
      <Route path="/" component={Sign} />
      <Route path="/sign" component={List} />
      <Route path="/blank" component={Blank} />
      <Route path="/budget/base" component={BudgetBaseInfo} />
      <Route path="/sys/concat" component={ModifyConcat} />
      <Route path="/sys/advice" component={Advice} />
    </Router>
  );
}

export default RouterConfig;
