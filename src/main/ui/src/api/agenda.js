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

export function listOwnAgendas(formData) {
  return httpUtil.get('/agenda/own', { params: formData });
}
export function listJoinAgendas(formData) {
  return httpUtil.get('/agenda/join', { params: formData });
}

export function listToDo(formData) {
  return httpUtil.get('/agenda/todo', { params: formData });
}

export function close(formData) {
  return httpUtil.patch('/agenda', formData, { params: { open: false } });
}

export function open(formData) {
  return httpUtil.patch('/agenda', formData, { params: { open: true } });
}

export function del(formData) {
  return httpUtil.delete('/agenda', { params: formData });
}

export function getAgendaDetail(agendaId) {
  return httpUtil.get(`/agenda/${agendaId}`);
}

