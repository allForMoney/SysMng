import dva from 'dva';
import createLoading from 'dva-loading';
import './index.html';

const app = dva({
  onError(error) {
    console.error(error.stack);
  },
});

app.use(createLoading());

app.model(require('./models/ImportDataModel'));
app.model(require('./models/BaseModel'));
app.model(require('./models/BudgetJustifyModel'));
app.model(require('./models/BudgetModel'));
app.model(require('./models/ExpertModel'));
app.model(require('./models/MsgModel'));
app.model(require('./models/OtherModel'));
app.model(require('./models/ProjectModel'));
app.model(require('./models/AchiveModel'));

// 4. Router
app.router(require('./router'));

// 5. Start
app.start('#react-root');
