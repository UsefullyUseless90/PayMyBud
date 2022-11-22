import Vue from 'vue';
import Router from 'vue-router';
import Home from './views/Home.vue';
import InscriptionVue from './views/Inscription.vue';
import Login from './views/Login.vue';


Vue.use(Router);

export const router = new Router({
  mode: 'history',
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
      path: '/home',
      component: Home
    },
    {
      path: '/login',
      component: Login
    },
    {
      path: '/register',
      component: InscriptionVue
    },
    {
      path: '/profile',
      name: 'profile',
      // lazy-loaded
      component: () => import('./views/Profile.vue')
    },
    
    {
      path: '/transactions',
      name: 'transactions',
      // lazy-loaded
      component: () => import('./views/Transactions.vue')
    },
    {
      path: '/user/edit',
      name: 'edition',
      // lazy-loaded
      component: () => import('./views/EditionUtilisateur.vue')
    },
    {
      path: '/connexions',
      name: 'connexions',
      // lazy-loaded
      component: () => import('./views/Connexions.vue')
    }
  ]
});