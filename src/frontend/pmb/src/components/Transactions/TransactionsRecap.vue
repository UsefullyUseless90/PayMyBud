<template>
  <div>
    <table class="table">
      <tbody v-for="(transaction, index) in transactions" :key="index">
        <tr>
          <td>{{ transaction.embeddedTransaction }}</td>
          <td>{{ transaction.description }}</td>
          <td>{{ transaction.montant }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import TransactionsDataServices from "../../services/TransactionsDataServices";

export default {
  name: "transactions-item",
  data() {
    return {
      transactions:[],
    };
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
