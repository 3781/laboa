import httpUtil from '../util/http';

export function getAvailableUsers(params) {
  return httpUtil.get('/cooperation/user', { params });
}

export function saveCooperation(formData) {
  return httpUtil.post('/cooperation', formData);
}

export function listOwnCooperation(formData) {
  return httpUtil.get('/cooperation/owner', { params: formData });
}

export function listManageCooperation(formData) {
  return httpUtil.get('/cooperation/manager', { params: formData });
}

export function listJoinCooperation(formData) {
  return httpUtil.get('/cooperation/member', { params: formData });
}
