import httpUtil from '../util/http';

export function getAvailableUsers(formData) {
  return httpUtil.get('/cooperation/user', { params: formData });
}

export function saveCooperation(formData) {
  return httpUtil.post('/cooperation', formData);
}

export function updateCooperation(formData) {
  return httpUtil.patch('/cooperation', formData);
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

export function listCooperationTree(cooperationId) {
  return httpUtil.get(`/cooperation/${cooperationId}/tree`);
}

export function getCooperation(cooperationId) {
  return httpUtil.get(`/cooperation/${cooperationId}`);
}

export function deleteCooperation(cooperationId) {
  return httpUtil.delete(`/cooperation/${cooperationId}`);
}

export function listMembers(formData) {
  return httpUtil.get('/cooperation/members', { params: formData });
}

export function saveMember(formData) {
  return httpUtil.post('/cooperation/member', formData);
}

export function changeMemberRole(formData) {
  return httpUtil.patch('/cooperation/member', formData);
}

export function deleteMember(memberId) {
  return httpUtil.delete(`/cooperation/member/${memberId}`);
}

export function listApplys(formData) {
  return httpUtil.get('/cooperation/apply', { params: formData });
}

export function doApply(cooperationId) {
  return httpUtil.post(`/cooperation/apply/${cooperationId}`);
}

export function dealApply(formData) {
  return httpUtil.patch('/cooperation/apply', formData);
}
