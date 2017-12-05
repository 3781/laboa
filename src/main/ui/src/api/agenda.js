import httpUtil from '../util/http';

export function saveAgenda(formData) {
  return httpUtil.post('/agenda', formData);
}

export function summaryAgenda(formData) {
  return httpUtil.post('/agenda/summary', formData);
}
