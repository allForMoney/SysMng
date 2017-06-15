import dva from 'dva';
import createLoading from 'dva-loading';
import './index.html';


const app = dva();


app.use(createLoading());

app.model(require('./models/signModel'));
app.model(require('./models/listModel'));

// 4. Router
app.router(require('./router'));

// 5. Start
app.start('#react-root');
