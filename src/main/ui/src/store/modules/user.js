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
      userApi.get().then((response) => {
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
      userApi.update(infoForm).then((response) => {
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
  changePassword({ commit }, passwordForm) {
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
  listUsers({ commit }, userQueryForm) {
    return new Promise((resolve, reject) => {
      userApi.list(userQueryForm).then((response) => {
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
