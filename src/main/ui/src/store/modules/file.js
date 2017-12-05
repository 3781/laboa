import * as fileApi from '../../api/file';

/**
 * author: loser
 * version: 2017/11/28
 */
const state = {

};

const getters = {

};

const mutations = {

};

const actions = {
  listFiles({ commit }, formData) {
    return new Promise((resolve, reject) => {
      fileApi.list(formData).then((response) => {
        if (response) {
          const res = response.data;
          if (res.success) {
            resolve(res.info);
          } else {
            reject(res.info);
          }
        }
      }).catch((error) => {
        reject(error);
      });
    });
  },
  listAllFiles({ commit }, formData) {
    return new Promise((resolve, reject) => {
      fileApi.listAll(formData).then((response) => {
        if (response) {
          const res = response.data;
          if (res.success) {
            resolve(res.info);
          } else {
            reject(res.info);
          }
        }
      }).catch((error) => {
        reject(error);
      });
    });
  },
  updateFile({ commit }, formData) {
    return new Promise((resolve, reject) => {
      fileApi.update(formData).then((response) => {
        if (response) {
          const res = response.data;
          if (res.success) {
            resolve(res.info);
          } else {
            reject(res.info);
          }
        }
      }).catch((error) => {
        reject(error);
      });
    });
  },
  delFile({ commit }, formData) {
    return new Promise((resolve, reject) => {
      fileApi.del(formData).then((response) => {
        if (response) {
          const res = response.data;
          if (res.success) {
            resolve(res.info);
          } else {
            reject(res.info);
          }
        }
      }).catch((error) => {
        reject(error);
      });
    });
  },
};

export default {
  state,
  getters,
  mutations,
  actions,
};
