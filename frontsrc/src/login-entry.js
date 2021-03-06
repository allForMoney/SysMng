import React from 'react';
import dva from 'dva';
import createLoading from 'dva-loading';
import LoginForm from './routes/LoginForm';
import './login.html';

const app = dva({
  onError(error) {
    console.error(error.stack);
  },
});

app.use(createLoading());

app.model(require('./models/LoginModel'));

// 4. Router
app.router(() => <LoginForm />);

// 5. Start
app.start('#react-root');
