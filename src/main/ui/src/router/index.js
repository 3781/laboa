import Vue from 'vue';
import VueRouter from 'vue-router';
import state from '../store/index';

Vue.use(VueRouter);
const vueRouter = new VueRouter({
  mode: 'history',
  routes: [
    {
      path: '/register',
      component: () => import('../page/app/Register'),
    },
    {
      path: '/login',
      component: () => import('../page/app/Login'),
    },
    {
      path: '/logout',
      beforeEnter() {
        state.dispatch('logout');
      },
    },
    {
      path: '/',
      component: () => import('../page/app/AuthenticatedLayout'),
      children: [
        { path: '/agenda/create', component: () => import('../page/agenda/create') },
        { path: '/agenda/todo', component: () => import('../page/agenda/todo') },
        { path: '/agenda/own', component: () => import('../page/agenda/own') },
        { path: '/agenda/cooperation', component: () => import('../page/agenda/cooperation') },
        { path: '/cooperation/create', component: () => import('../page/cooperation/create') },
        { path: '/cooperation/own', component: () => import('../page/cooperation/own') },
        { path: '/cooperation/manage', component: () => import('../page/cooperation/manage') },
        { path: '/cooperation/join', component: () => import('../page/cooperation/join') },
        { path: '/cooperation/:cooperationId', component: () => import('../page/cooperation/detail') },
        { path: '/file/create', component: () => import('../page/file/create') },
        { path: '/file/own', component: () => import('../page/file/own') },
        { path: '/file/all', component: () => import('../page/file/all') },
        { path: '/user/all', component: () => import('../page/user/all') },
        { path: '/user/password', component: () => import('../page/user/password') },
        { path: '/user/info', component: () => import('../page/user/info') },
      ],
    },
  ],
});

vueRouter.beforeEach((to, from, next) => {
  state.commit('SET_MAIN_LOADING', true);

  if ((to.path === '/login' || to.path === '/register')) {
    if (state.getters.getUsername !== '') {
      next('/');
    } else {
      next();
    }
  } else if (state.getters.getUsername === '') {
    next('/login');
  } else {
    next();
  }
});

vueRouter.afterEach(() => {
  state.commit('SET_MAIN_LOADING', false);
});

export default vueRouter;
