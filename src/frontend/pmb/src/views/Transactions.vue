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

export default{
    name: "transaction-item",
    components: { 
      NouvelleTransaction, 
      TransactionTableVue, 
      TransactionsRecapVue 
    }
}

</script>
<style>
.nav{
  margin-left: 75%;
}
</style>