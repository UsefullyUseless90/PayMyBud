<template>
  <div>
    <table class="table">
      <tbody v-for="(transaction, index) in transactions" :key="index">
        <tr>
          <td>{{ transaction.embeddedTransaction.date }}</td>
          <td>{{ transaction.description }}</td>
          <td>{{ transaction.montant }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import axios from 'axios';
import TransactionsDataServices from "../../services/TransactionsDataServices";

export default {
  name: "transactions-item",
  data() {
    return {
      transactions:[],
    };
  },
  computed:{
      user(){ return this.$store.state.user },
      token(){ return this.$store.state.token },
    },
  methods: {
    retrieveTransactions() {
      TransactionsDataServices.getDestinataire()
        .then((response) => {
          this.transactions = {...response.data};
        })
        .catch((e) => {
          alert(e);
        });
    },
    logout(){
        this.$store.commit('setUser'      , {} )
        this.$store.commit('setToken'     , '' )
        this.$router.push('http://localhost:8080/paymybuddy/login/signin')        
      },
     
  },
  mounted() {
    this.retrieveTransactions();
  },
};
</script>
<style>
.table {
  margin-left: auto;
  margin-right: auto;
}

td {
  color: black;
}
</style>
