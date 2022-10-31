import { createRouter, createWebHistory } from "vue-router";
import EditionUtilisateur from "../components/Utilisateurs/EditionUtilisateur.vue";
import TransactionsParticulier from "../components/Transactions/TransactionParticulier.vue";
import ConnexionsListe from "../components/Connexions/ConnexionsListe.vue";
import UtilisateurContenu from "../components/Utilisateurs/UtilisateurContenu.vue"
import Inscription from "../views/Inscription.vue"
import Transactions from "../views/Transactions.vue"
import Connexions from "../views/Connexions.vue"
import Logout from "../views/Logout.vue"


  const routes = [
    // Partie création compte
    {
      path: "/inscription",
      name: "Inscription",
      component: Inscription,
    },
    {
      path: "/login",
      name: "Login",
      component: () =>
      import("../views/Login.vue"),
    },
    {
      path:"/logout",
      name:"Logout",
      component: Logout,
    },
    {
      path: "/editionProfil",
      name: "Edition",
      component: EditionUtilisateur,
    },
    //Partie utilisateur
    {
      path: "/utilisateurs",
      name: "utilisateurs",
      component: UtilisateurContenu,
    },
    //Partie Connexion
    {
      path: "/connexions",
      name: "listeConnexion",
      component: ConnexionsListe,
    },
    {
      path: "/connexions/nouvelleConnexion",
      name: "ConnexionDemande",
      component: Connexions,
    },
    // Partie Transaction
    {
      path: "/transaction/emeteur",
      name: "emetteur",
      component: TransactionsParticulier,
    },
    {
      path: "/transaction/destinataire",
      name: "destinataire",
      component: TransactionsParticulier,
    },
    {
      path: "/transaction/transactions",
      name: "transactions",
      component: Transactions,
      
    },
    {
      path: "/accueil",
      name: "home",
      // route level code-splitting
      // this generates a separate chunk (about.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () =>
        import("../views/HomeView.vue"),
    },

    {
      path: "/",
      name: "Login",
      // route level code-splitting
      // this generates a separate chunk (about.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () =>
        import("../views/Login.vue"),
    },
  ];

  const router = createRouter({
    history: createWebHistory(),
    routes,
  })
  
  router.beforeEach((to, from, next) => {
    const requiresAuth = !to.matched.some((record) => record.meta.nonRequiresAuth);
    const isLoginPage = to.matched.some((record) => record.meta.loginPage);
    const isAuthenticated = localStorage.getItem('auth');
    if (requiresAuth && !isAuthenticated) {
      /*next('/');*/
    } else if (isLoginPage && isAuthenticated) {
      router.push('/accueil');
    }
    next();
  });

  export default router;
