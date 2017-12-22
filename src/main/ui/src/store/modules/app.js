import { getLoginInfo, register, login, logout } from '../../api/app';
import vueRouter from '../../router/index';

/**
 * author: loser
 * version: 2017/11/28
 */
const state = {
  username: localStorage.username || '',
  role: localStorage.role || '',
  permissions: localStorage.permissions || '',
  sidebarLoading: false,
  mainLoading: false,
};

const getters = {
  getUsername() {
    return state.username;
  },
  getRole() {
    return state.role;
  },
  getPermissions() {
    return state.permissions;
  },
  getSidebarLoading() {
    return state.sidebarLoading;
  },
  getMainLoading() {
    return state.mainLoading;
  },
};

const mutations = {
  ADD_PERMISSION(thisState, permission) {
    state.permissions.push(permission);
    localStorage.permissions = state.permissions;
  },
  SET_SIDEBAR_LOADING(thisState, loading) {
    state.sidebarLoading = loading;
  },
  SET_MAIN_LOADING(thisState, loading) {
    state.mainLoading = loading;
  },
  SET_LOGIN(thisState, { username, role, permissions }) {
    state.username = username;
    state.role = role;
    state.permissions = permissions;
    localStorage.username = username;
    localStorage.role = role;
    localStorage.permissions = permissions;
  },
  SET_LOGOUT() {
    state.username = '';
    state.role = '';
    state.permissions = [];

    delete localStorage.username;
    delete localStorage.role;
    delete localStorage.permissions;
    vueRouter.push('/login');
  },
};

const actions = {
  getLoginInfo({ commit }) {
    return new Promise((resolve, reject) => {
      getLoginInfo().then((response) => {
        if (response) {
          const res = response.data;
          if (res.success) {
            commit('SET_LOGIN', res.info);
          }

          if (res.success) {
            resolve(res.info.lastLoginTime);
          } else {
            reject(res.info);
          }
        }
      }).catch((error) => {
        reject(error);
      });
    });
  },
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
      }).catch((error) => {
        reject(error);
      });
    });
  },
  login({ commit }, loginForm) {
    return new Promise((resolve, reject) => {
      login(loginForm).then((response) => {
        if (response) {
          const res = response.data;
          if (res.success) {
            commit('SET_LOGIN', res.info);
          }

          if (res.success) {
            resolve(res.info.lastLoginTime);
          } else {
            reject(res.info);
          }
        }
      }).catch((error) => {
        reject(error);
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
