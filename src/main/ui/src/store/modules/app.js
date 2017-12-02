import { register, login, logout } from '../../api/app';
import vueRouter from '../../router/index';

/**
 * author: loser
 * version: 2017/11/28
 */
const state = {
  username: sessionStorage.username || '',
  permissions: [],
  sidebarLoading: false,
  mainLoading: false,
};

const getters = {
  getUsername() {
    return state.username;
  },
  listPermissions() {

  },
  getSidebarLoading() {
    return state.sidebarLoading;
  },
  getMainLoading() {
    return state.mainLoading;
  },
};

const mutations = {
  SET_SIDEBAR_LOADING(thisState, loading) {
    state.sidebarLoading = loading;
  },
  SET_MAIN_LOADING(thisState, loading) {
    state.mainLoading = loading;
  },
  SET_LOGIN(thisState, username) {
    state.username = username;
    sessionStorage.username = username;
  },
  SET_LOGOUT() {
    state.username = '';
    state.permissions = [];

    delete sessionStorage.username;
    vueRouter.push('/login');
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
    logout().then(() => {
      commit('SET_LOGOUT');
    }).catch(() => {
      commit('SET_LOGOUT');
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
