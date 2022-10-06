import { createRouter, createWebHistory } from "vue-router";
import EditionUtilisateur from "../components/Utilisateurs/EditionUtilisateur.vue";
import TransactionsParticulier from "../components/Transactions/TransactionParticulier.vue";
import ConnexionsListe from "../components/Connexions/ConnexionsListe.vue";
import UtilisateurContenu from "../components/Utilisateurs/UtilisateurContenu.vue"
import Inscription from "../views/Inscription.vue"


  const routes = [
    // Partie création compte
    {
      path: "/inscription",
      name: "Inscription",
      component: Inscription,
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
      component: () => import("../views/Connexions.vue"),
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
      component: () => import("../views/Transactions.vue"),
      
    },
    {
      path: "/",
      name: "Accueil",
      // route level code-splitting
      // this generates a separate chunk (about.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () =>
        import("../views/HomeView.vue"),
    },
  ];

  const router = createRouter({
    history: createWebHistory(),
    routes,
  })

  export default router;
