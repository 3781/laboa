import httpUtil from '../util/http';

export function get() {
  return httpUtil.get('/user/info');
}

export function update(infoForm) {
  return httpUtil.patch('/user/info', infoForm);
}

export function changePassword(passwordForm) {
  return httpUtil.patch('/user/password', passwordForm);
}

export function list(userQueryForm) {
  return httpUtil.get('/user/list', { params: userQueryForm });
}
