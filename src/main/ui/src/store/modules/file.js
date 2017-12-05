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
  update({ commit }, formData) {
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
};

export default {
  state,
  getters,
  mutations,
  actions,
};
