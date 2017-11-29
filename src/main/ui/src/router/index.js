import Vue from 'vue';
import VueRouter from 'vue-router';
import state from '../store/index';
import Login from '../page/app/Login';
import Register from '../page/app/Register';
import Home from '../page/app/Home';

Vue.use(VueRouter);
const vueRouter = new VueRouter({
  mode: 'history',
  routes: [
    {
      path: '/register',
      name: 'register',
      component: Register,
    },
    {
      path: '/login',
      name: 'login',
      component: Login,
    },
    {
      path: '/',
      name: 'home',
      component: Home,
    },
  ],
});

vueRouter.beforeEach((to, from, next) => {
  if ((to.name === 'login' || to.name === 'register')) {
    if (state.getters.getUsername !== '') {
      next({ name: 'home' });
    } else {
      next();
    }
  } else if (state.getters.getUsername === '') {
    next({ name: 'login' });
  } else {
    next();
  }
});

export default vueRouter;
