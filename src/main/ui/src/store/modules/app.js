import { register, login, logout } from '../../api/app';

/**
 * author: loser
 * version: 2017/11/28
 */
const state = {
  username: sessionStorage.username || '',
  lastLoginTime: sessionStorage.lastLoginTime || '',
  permissions: [],
};

const getters = {
  getLastLoginTime() {
    return state.lastLoginTime;
  },
  getUsername() {
    return state.username;
  },
  listPermissions() {

  },
};

const mutations = {
  SET_LOGIN(username, lastLoginTime) {
    state.username = username;
    state.lastLoginTime = lastLoginTime;

    sessionStorage.username = username;
    sessionStorage.lastLoginTime = lastLoginTime;
  },
  SET_LOGOUT() {
    state.username = '';
    state.lastLoginTime = '';
    state.permissions = [];

    delete sessionStorage.username;
    delete sessionStorage.lastLoginTime;
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
            const info = res.info;
            commit('SET_LOGIN', info.username, info.lastLoginTime);
          }

          if (res.success) {
            resolve(res.info);
          } else {
            reject(res.info);
          }
        }
      });
    });
  },
  logout({ commit }) {
    commit('SET_LOGOUT');
    logout();
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
