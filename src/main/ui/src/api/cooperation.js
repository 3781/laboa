import httpUtil from '../util/http';

export function getAvailableUsers(params) {
  return httpUtil.get('/cooperation/user', { params });
}

export function saveCooperation(formData) {
  return httpUtil.post('/cooperation', formData);
}
