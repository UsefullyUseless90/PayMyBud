<template>
  <img src="./assets/logoPMB.png" alt="Pay My Buddy Logo" class="pic">
  <div id="app">
    <LiensPagesApp/>
    <NouvelleTransaction/>
    <TransactionTable/>
    <TransactionsRecap/>
   
  </div>  
</template>

<script>

import LiensPagesApp from './components/LiensPagesApp.vue'
import TransactionsRecap from './components/Transactions/TransactionsRecap.vue'; 
import NouvelleTransaction from './components/Transactions/NouvelleTransaction.vue';
import TransactionTable from './components/Transactions/TransactionTable';


export default {
  name:'App',
  components:{
    LiensPagesApp,
    TransactionsRecap,
    NouvelleTransaction,
    TransactionTable,
  }
}
</script>
<style>
@import url('../src/style/reset.css');

  #app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  
}

.pic{
  height: 200px;
}

</style>
