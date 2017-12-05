import httpUtil from '../util/http';

export function update(formData) {
  return httpUtil.patch('/file', formData);
}

export function del(formData) {
  return httpUtil.delete('/file', formData);
}
