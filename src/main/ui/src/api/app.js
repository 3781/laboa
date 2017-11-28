import httpUtil from '../util/http'

export function login(username, password) {
  return httpUtil.post('/login', {
    username,
    password,
  });
}

export function logout() {
  return httpUtil.post('/logout');
}
