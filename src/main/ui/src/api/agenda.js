import httpUtil from '../util/http';

export function saveAgenda(formData) {
  return httpUtil.post('/agenda', formData);
}

export function updateAgenda(formData) {
  return httpUtil.patch('/agenda', formData);
}

export function summaryAgenda(formData) {
  return httpUtil.patch('/agenda/summary', formData);
}

export function list(formData) {
  return httpUtil.get('/agenda/list', { params: formData });
}

export function listToDo(formData) {
  return httpUtil.get('/agenda/todo', { params: formData });
}

export function close(formData) {
  return httpUtil.delete('/agenda', { params: formData });
}

