import * as userApi from '../../api/user';

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
  getInfo() {
    return new Promise((resolve, reject) => {
      userApi.getInfo().then((response) => {
        if (response) {
          const res = response.data;
          if (res.success) {
            resolve(res.info);
          } else {
            reject(res.info);
          }
        }
      });
    });
  },
  updateInfo({ commit }, infoForm) {
    return new Promise((resolve, reject) => {
      userApi.updateInfo(infoForm).then((response) => {
        if (response) {
          const res = response.data;
          if (res.success) {
            resolve(res.info);
          } else {
            reject(res.info);
          }
        }
      });
    });
  },
  changePassword(passwordForm) {
    return new Promise((resolve, reject) => {
      userApi.changePassword(passwordForm).then((response) => {
        if (response) {
          const res = response.data;
          if (res.success) {
            resolve(res.info);
          } else {
            reject(res.info);
          }
        }
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
