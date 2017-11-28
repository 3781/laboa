import {login, logout} from '../../api/app'

/**
 * author: loser
 * version: 2017/11/28
 */
const state = {
  username: '',
  lastLoginTime: '',
  permissions:[],
};

const getters = {
  getLastLoginTime(){
    return state.lastLoginTime;
  },
  getUsername(){
    return state.username;
  },
  listPermissions(){

  }
};

const mutations = {
  SET_LOGIN(username, lastLoginTime){
    state.username = username;
    state.lastLoginTime = lastLoginTime;
  },
  SET_LOGOUT(){
    state.username = '';
    state.lastLoginTime = '';
    state.permissions = [];
  }
};

const actions = {
  login({commit}, {username, password}){
    return login(username.trim(), password).then(response => {
      const res = response.data;
      if(res.success){
        const info = res.info;
        commit("SET_LOGIN", info.username, info.lastLoginTime);
      }
    });
  },
  logout({commit}){
    return logout().then(() => {
      commit("SET_LOGOUT");
    });
  }
};

export default {
  state,
  getters,
  mutations,
  actions,
};
