<template>
  <div class="nouvelleTransaction">
    <div class="nav">
    <router-link to="/accueil">Accueil</router-link> |
        <router-link to="/transaction/transactions">Transactions</router-link> |
        <router-link to="/editionProfil">Profil</router-link> |
        <router-link to="/connexions/nouvelleConnexion">Contact</router-link> |
        <router-link to="/logout">Déconnexion</router-link>|
        <router-view/>
      </div>

    <h1>Transactions</h1>
      <NouvelleTransaction/>
      <TransactionTableVue/>
      <TransactionsRecapVue/>
    </div>


</template>

<script>
import NouvelleTransaction from '@/components/Transactions/NouvelleTransaction.vue';
import TransactionTableVue from '@/components/Transactions/TransactionTable.vue';
import TransactionsRecapVue from '@/components/Transactions/TransactionsRecap.vue';
import axios from 'axios'
import { mapGetters } from 'vuex';


export default{
    name: "transaction-item",
    components: { 
      NouvelleTransaction, 
      TransactionTableVue, 
      TransactionsRecapVue 
    },
    
    computed:{
      ...mapGetters(["isLoggedIn"])
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