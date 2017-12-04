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

export function configureStatus(configureStatusForm) {
  return httpUtil.patch('/user/status', configureStatusForm);
}

export function configureRole(configureRoleForm) {
  return httpUtil.patch('/user/role', configureRoleForm);
}

export function passwordReset(passwordResetForm) {
  return httpUtil.patch('/user/passwordReset', passwordResetForm);
}
