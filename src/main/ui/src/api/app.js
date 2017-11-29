import httpUtil from '../util/http';

export function register(registerForm) {
  return httpUtil.post('/register', registerForm);
}

export function login(loginForm) {
  return httpUtil.post('/login', loginForm);
}

export function logout() {
  return httpUtil.post('/logout');
}
