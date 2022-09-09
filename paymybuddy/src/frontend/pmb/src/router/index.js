import Vue from 'vue' 
import Router from 'vue-router'
import Inscription from '../components/Utilisateurs/Inscription.vue'
import EditionUtilisateur from '../components/Utilisateurs/EditionUtilisateur.vue'
import UtilisateursListe from '../components/Utilisateurs/UtilisateursTable.vue'
import TransactionsListes from '../components/Transactions/TransactionsListes.vue' 
import TransactionsParticulier from '../components/Transactions/TransactionParticulier.vue'
import NouvelleTransaction from '../components/Transactions/NouvelleTransaction.vue'
import ConnexionsListe from '../components/Connexions/ConnexionsListe.vue'
import ConnexionsParticulier from '../components/Connexions/ConnexionsParticulier.vue'
import UtilisateurDataServices from '@/services/UtilisateurDataServices'
import TransactionsDataServices from '@/services/TransactionsDataServices'

Vue.use(Router)

export default new Router({
  routes: [
    {
        path: '/inscription',
        name: 'Inscription',
        component: UtilisateurDataServices.create
    },
    {
      path:'/editionProfil',
      name:'Edition',
      component: EditionUtilisateur
    },
    {
      path:'/utilisateurs',
      name:'utilisateurs',
      component: UtilisateurDataServices.getAll
    },
    {
      path:'/transactions',
      name:'transactions',
      component: TransactionsDataServices.getAll
    },
    {
      path:'/transaction/emeteur',
      name:'emetteur',
      component:TransactionsParticulier
    },
    {
      path:'/transaction/destinataire',
      name:'destinataire',
      component: TransactionsParticulier
    },
    {
      path:'/transaction/nouvelleTransaction',
      name:'nouvelleTransaction',
      component: NouvelleTransaction
    },
    {
      path:'/connexions',
      name:'Liste',
      component:ConnexionsListe
    },
    {
      path:'http://localhost8080/',
      name:'ConnexionParticulier',
      component: ConnexionsParticulier
    },
    {
        path: '/about',
        name: 'About',
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
    }
  ]
})