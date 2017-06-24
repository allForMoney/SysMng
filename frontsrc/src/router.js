import React from 'react';
import { Router, Route } from 'dva/router';
import List from './routes/List';
import Blank from './routes/Blank';
import ModifyConcat from './components/sysConfig/ModifyConcat';
import Advice from './components/sysConfig/Advice';
import ProjectBudget from './components/budget/ProjectBudget';
import BudgetJustify from './components/budget/BudgetJustify';
import BudgetJustifyRec from './components/budget/BudgetJustifyRec';
import BudgetMsgTable from './components/budget/BudgetMsgTable';
import BudgetSeaonList from './components/budget/BudgetSeason/BudgetSeaonList';
import AddBudgetSeason from './components/budget/BudgetSeason/AddBudgetSeason';

import Achive from './components/achive/Achive';

import ImportBudget16 from './components/import/ImportBudget16';
import ProjectInfoList from './components/project/ProjectInfoList';
import ProjectBaseInfo from './components/project/ProjectBaseInfo';

function RouterConfig({ history }) {
  return (
    <Router history={history}>
      <Route path="/" component={Blank} />
      <Route path="/sign" component={List} />
      <Route path="/blank" component={Blank} />
      <Route path="/budget/base" component={ProjectBaseInfo} />
      <Route path="/budget/project" component={ProjectBudget} />
      <Route path="/budget/justify" component={BudgetJustify} />
      <Route path="/budget/justifyRec" component={BudgetJustifyRec} />
      <Route path="/budget/addbudgetseason" component={AddBudgetSeason} />
      <Route path="/budget/budgetSeasonList" component={BudgetSeaonList} />
      <Route path="/budget/msg" component={BudgetMsgTable} />

      <Route path="/achive/add" component={Achive} />
      
      <Route path="/import/budget16" component={ImportBudget16} />
      <Route path="/base/projectList" component={ProjectInfoList} />

      <Route path="/sys/concat" component={ModifyConcat} />
      <Route path="/sys/advice" component={Advice} />
    </Router>
  );
}

export default RouterConfig;
