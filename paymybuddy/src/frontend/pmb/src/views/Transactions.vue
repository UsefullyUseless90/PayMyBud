<template>
  <div class="nouvelleTransaction">
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