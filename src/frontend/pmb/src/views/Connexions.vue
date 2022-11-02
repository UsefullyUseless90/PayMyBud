<template>
  <div class="nouvelleConnexion">
    <div class="nav">
    <router-link to="/accueil">Accueil</router-link> |
        <router-link to="/transaction/transactions">Transactions</router-link> |
        <router-link to="/editionProfil">Profil</router-link> |
        <router-link to="/connexions/nouvelleConnexion">Contact</router-link> |
        <router-link to="/logout">Déconnexion</router-link>|
        <router-view/>
      </div>

    <h1>Connexions</h1>
    
    <ConnexionTableVue/>
    <ConnexionsListeVue/>
    <ConnexionDemandeVue/>
  </div>

</template>

<script>
  import ConnexionDemandeVue from '@/components/Connexions/ConnexionDemande.vue';
  import ConnexionsListeVue from '@/components/Connexions/ConnexionsListe.vue';
  import ConnexionTableVue from '@/components/Connexions/ConnexionTable.vue';
  import { mapGetters } from 'vuex';
  import axios from 'axios';
  

  export default{
  name:"connexion-item",
  components:{
      ConnexionDemandeVue,
      ConnexionsListeVue,
      ConnexionTableVue
    },

    computed:{
      ...mapGetters(["isLoggedIn"]),
    },
  mounted() {
    if (!this.currentUser) {
      this.$router.push('');
    }
  },

  beforeMount(){
        axios.post("http://localhost:8080/paymybuddy/login/signin",
        {
          headers: {
            user: JSON.stringify(this.user),
            Authorization: 'Bearer ' + this.token
          }
        })
          .then(
              (result) => {
                this.message = result.data;
              }
          )
          .catch(
            error => {
              console.log(error.data)
            }
          )
      },
  }


</script>
<style>
.nav{
  margin-left: 75%;
}
</style>