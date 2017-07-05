import dva from 'dva';
import createLoading from 'dva-loading';

const app = dva({
  onError(error) {
    console.error(error.stack);
  },
});

app.use(createLoading());

app.model(require('./models/BaseModel'));

// 4. Router
app.router(require('./routes/LoginForm'));

// 5. Start
app.start('#react-root');
