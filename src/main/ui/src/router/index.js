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
      beforeEnter(to, from, next) {
        state.dispatch('logout').then(() => {
          next('/login');
        }).catch(() => {
          next('/login');
        });
      },
    },
    {
      path: '/',
      component: () => import('../page/app/AuthenticatedLayout'),
      children: [
        { path: '/password' },
        { path: '/information' },
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
