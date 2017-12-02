import httpUtil from '../util/http';

export function getLoginInfo() {
  return httpUtil.get('/user/loginInfo');
}

export function register(registerForm) {
  return httpUtil.post('/register', registerForm);
}

export function login(loginForm) {
  return httpUtil.patch('/login', loginForm);
}

export function logout() {
  return httpUtil.delete('/logout');
}
