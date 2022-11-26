<template>
  <select
    v-model="transactionDTO.destinataire"
  >
    <option selected>--Utilisateur--</option>
    <option v-for="item in transactions" :key="item.id" :value="item.id">
      {{ item.demandeur.nom }}
      {{ item.demandeur.prenom }}
    </option>
  </select>
</template>
<script>
import TransactionsDataService from "../../services/TransactionsDataServices";

export default {
  name: "transactions-item",
  data() {
    return {
      transactions: [],
      transactionDTO: {
        emetteur: {
          idUtilisateur: 1,
          nomPrenom: "Jeff Nippard",
        },
        destinataire: {
          idUtilisateur: "",
          nomPrenom: "",
        },
        date: "",
        description: "",
        montant: 0,
      },
      methods: {
        retrieveTransactions() {
          TransactionsDataService.getAll()
            .then((response) => {
              this.transactions = response.data;
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
  },
};
</script>
