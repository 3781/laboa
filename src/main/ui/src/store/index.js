/**
 * author: loser
 * version: 2017/11/28
 */
import Vue from 'vue';
import Vuex from 'vuex';
import agenda from './modules/agenda';
import cooperation from './modules/cooperation';
import file from './modules/file';
import user from './modules/user';
import app from './modules/app';

Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    agenda,
    file,
    cooperation,
    user,
    app,
  },
});
