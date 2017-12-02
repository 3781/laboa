import { getLoginInfo, register, login, logout } from '../../api/app';
import vueRouter from '../../router/index';

/**
 * author: loser
 * version: 2017/11/28
 */
const state = {
  username: sessionStorage.username || '',
  role: sessionStorage.role || '',
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
  SET_LOGIN(thisState, { username, role }) {
    state.username = username;
    state.role = role;
    sessionStorage.username = username;
    sessionStorage.role = role;
  },
  SET_LOGOUT() {
    state.username = '';
    state.role = '';

    delete sessionStorage.username;
    delete sessionStorage.role;
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
