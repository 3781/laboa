import httpUtil from '../util/http';

export function update(formData) {
  return httpUtil.post(`/file/${formData.fileId}`, formData);
}

export function list(formData) {
  return httpUtil.get('/file', { params: formData });
}

export function listAll(formData) {
  const formDataForAll = {};
  Object.assign(formDataForAll, formData, { all: true });
  return httpUtil.get('/file', { params: formDataForAll });
}

export function del(formData) {
  return httpUtil.delete('/file', { params: formData });
}
