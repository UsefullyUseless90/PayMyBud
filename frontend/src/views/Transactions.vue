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
    },
    
};

</script>
<style>
.nav{
  margin-left: 75%;
}
.carre {
  width: 1550px;
  height: 150px;
  margin-left: auto;
  margin-right: auto;
}
.gestion_entete {
  font-weight: bold;
  font-size: x-large;
  margin-left: 500px;
  margin-right: 330px;
}
.infos {
  height: 45px;
  width: 25%;
  border-radius: 5px / 5px;
  display: flex;
  justify-content: space-between;
}
.rectangle {
  width: 2600px;
  height: 50px;
  font-size: x-large;
}
.table {
  margin-left: auto;
  margin-right: auto;
}

td {
  color: black;
}

.center {
  margin-left: auto;
  margin-right: auto;
  border: 2px solid black;
  background-color: green;
}

table {
  text-align: center;
  font-weight: bold;
  font-size: xx-large;
  color: #fff;
  height: 150px;
  width: 1550px;
}

td {
  color: black;
}
</style>