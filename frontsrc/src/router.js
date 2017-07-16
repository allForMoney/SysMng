import React from 'react';
import { Router, Route } from 'dva/router';
import Blank from './routes/Blank';
import LoginForm from './routes/LoginForm';
import ModifyConcat from './components/sysConfig/ModifyConcat';
import Advice from './components/sysConfig/Advice';
import ProjectBudget from './components/budget/ProjectBudget';
import BudgetJustify from './components/budget/BudgetJustify';
import BudgetJustifyRec from './components/budget/BudgetJustifyRec';
import BudgetMsgList from './components/budget/BudgetMsgList';
import BudgetMsgTable from './components/budget/BudgetMsgTable';
import BudgetSeaonList from './components/budget/BudgetSeason/BudgetSeaonList';
import AddBudgetSeason from './components/budget/BudgetSeason/AddBudgetSeason';

import Achive from './components/achive/Achive';
import AchiveAllList from './components/achive/AchiveAllList';
import AchiveMsgList from './components/achive/AchiveMsgList';

import ImportBudget16 from './components/import/ImportBudget16';
import ImportBudget15 from './components/import/ImportBudget15';
import ImportAchive from './components/import/ImportAchive';
import ImportProjet from './components/import/ImportProjet';
import BudgetImportRecord from './routes/BudgetImportRecord';
import AchivemportRecord from './routes/AchivemportRecord';

import ProjectInfoList from './components/project/ProjectInfoList';
import ProjectBaseInfo from './components/project/ProjectBaseInfo';
import reportTime from './components/others/reportTime';
import SendSMS from './components/others/SendSMS';
import ResetUserPwd from './components/others/ResetUserPwd';

import BudgetSeason_Country from './components/budget/BudgetSeason_Country';
import BudgetResult_Country from './components/budget/BudgetResult_Country';
// 专家相关
import ExpertList from './components/expert/ExpertList';
import DrawExpert from './components/expert/DrawExpert';
import DataAnaListYear from './components/dataAnalysis/DataAnaListYear';
import DataAnaListBudgetYear from './components/dataAnalysis/DataAnaListBudgetYear';

function RouterConfig({ history }) {
  return (
    <Router history={history}>
      <Route path="/login" component={LoginForm} />
      <Route path="/blank" component={Blank} />
      <Route path="/budget/base" component={ProjectBaseInfo} />
      <Route path="/budget/project" component={ProjectBudget} />
      <Route path="/budget/justify" component={BudgetJustify} />
      <Route path="/budget/justifyRec" component={BudgetJustifyRec} />
      <Route path="/budget/addbudgetseason" component={AddBudgetSeason} />
      <Route path="/budget/budgetSeasonList" component={BudgetSeaonList} />
      <Route path="/budget/msg" component={BudgetMsgTable} />
      <Route path="/budget/msgList" component={BudgetMsgList} />
      <Route path="/budget/projectSeaonBudget" component={BudgetSeason_Country} />
      <Route path="/budget/projectBudgetResult" component={BudgetResult_Country} />

      <Route path="/achive/add" component={Achive} />
      <Route path="/achive/allList" component={AchiveAllList} />
      <Route path="/achive/msgAll" component={AchiveMsgList} />

      <Route path="/import/budget16" component={ImportBudget16} />
      <Route path="/import/budget15" component={ImportBudget15} />
      <Route path="/import/importAchive" component={ImportAchive} />
      <Route path="/import/importProjet" component={ImportProjet} />
      <Route path="/import/budgetImportRec" component={BudgetImportRecord} />
      <Route path="/import/achiveImportRec" component={AchivemportRecord} />
      <Route path="/base/projectList" component={ProjectInfoList} />

      <Route path="/sys/concat" component={ModifyConcat} />
      <Route path="/sys/advice" component={Advice} />
      <Route path="/sys/reportTime" component={reportTime} />
      <Route path="/sys/sendSMS" component={SendSMS} />
      <Route path="/sys/resetPwd" component={ResetUserPwd} />

      <Route path="/expert/list" component={ExpertList} />
      <Route path="/expert/drawExpert" component={DrawExpert} />
      <Route path="/dataAna/year" component={DataAnaListYear} />
      <Route path="/dataAna/budgetyear" component={DataAnaListBudgetYear} />

    </Router>
  );
}

export default RouterConfig;
