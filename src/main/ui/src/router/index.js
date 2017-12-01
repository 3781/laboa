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
        { path: '/agenda/create' },
        { path: '/agenda/todo' },
        { path: '/agenda/own' },
        { path: '/agenda/cooperation' },
        { path: '/cooperation/create' },
        { path: '/cooperation/own' },
        { path: '/cooperation/manage' },
        { path: '/cooperation/join' },
        { path: '/cooperation/create' },
        { path: '/cooperation/own' },
        { path: '/cooperation/all' },
        { path: '/file/create' },
        { path: '/file/own' },
        { path: '/file/all' },
        { path: '/user/all' },
        { path: '/user/password' },
        { path: '/user/info', component: () => import('../page/user/info') },
      ],
    },
  ],
});

vueRouter.beforeEach((to, from, next) => {
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

export default vueRouter;
