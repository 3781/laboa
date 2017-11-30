import { register, login, logout } from '../../api/app';

/**
 * author: loser
 * version: 2017/11/28
 */
const state = {
  username: sessionStorage.username || '',
  permissions: [],
};

const getters = {
  getUsername() {
    return state.username;
  },
  listPermissions() {

  },
};

const mutations = {
  SET_LOGIN(thisState, username) {
    state.username = username;
    sessionStorage.username = username;
  },
  SET_LOGOUT() {
    state.username = '';
    state.permissions = [];

    delete sessionStorage.username;
  },
};

const actions = {
  register({ commit }, registerForm) {
    return new Promise((resolve, reject) => {
      register(registerForm).then((response) => {
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
  login({ commit }, loginForm) {
    return new Promise((resolve, reject) => {
      login(loginForm).then((response) => {
        if (response) {
          const res = response.data;
          if (res.success) {
            commit('SET_LOGIN', res.info.username);
          }

          if (res.success) {
            resolve(res.info.lastLoginTime);
          } else {
            reject(res.info);
          }
        }
      });
    });
  },
  logout({ commit }) {
    return new Promise((resolve, reject) => {
      logout().then(() => {
        commit('SET_LOGOUT');
        resolve();
      }).catch(() => {
        commit('SET_LOGOUT');
        reject();
      });
    });
  },
  logoutLocal({ commit }) {
    commit('SET_LOGOUT');
  },
};

export default {
  state,
  getters,
  mutations,
  actions,
};
