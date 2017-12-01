import httpUtil from '../util/http';

export function getInfo() {
  return httpUtil.get('/user/info');
}

export function updateInfo(infoForm) {
  return httpUtil.patch('/user/info', infoForm);
}

export function changePassword(passwordForm) {
  return httpUtil.patch('/user/password', passwordForm);
}
