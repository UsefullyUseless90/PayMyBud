<template>
  <div>
    <table class="table">
      <tbody v-for="(transactions, index) in transactions" :key="index">
        <tr>
          <td>{{ transactions.embeddedTransaction.date }}</td>
          <td>{{ transactions.description }}</td>
          <td>{{ transactions.montant }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import TransactionsDataServices from '@/services/TransactionsDataServices';
import UserService from '@/services/utilisateurService'
export default {
  name: "transactions-item",
  data() {
    return {
      transactions:[],
    };
  },
  methods: {
    onSave() {
      if (this.currentUser.motDePasse === this.currentUser.confirmMotDePasse) {
        UserService.update(this.userId, this.currentUser).then(
          () => {
            this.$store.dispatch("auth/logout");
            var user = {
              email: this.currentUser.adresseEmail,
              password: this.currentUser.motDePasse,
            };
            this.$store.dispatch("auth/login", user).then(
              () => {
                this.$router.push("/home");
              },
              (error) => {
                alert(
                  error.response.data.error || error.message || error.toString()
                );
              }
            );
          },
          (error) => {
            alert(error.response.data || error.message || error.toString());
          }
        );
      } else {
        alert("Les mots de passe doivent être identiques!");
      }
    },
  },
  computed: {
    userId: function () {
      return this.$store.state.auth.user.idUtilisateur;
    },
  },
  mounted() {
    if (this.$store.state.auth.user !== null) {
      TransactionsDataServices.getHistoriqueTransactions(this.userId)
      .then((response) => {
        this.transactions = {...response.data};
      },
        (error) => {
          alert(error.response.data.error || error.message || error.toString());
          if (error.response.data.status === 401) {
            this.$store.dispatch("auth/logout");
            this.$router.push("/login");
          } else {
            this.$router.push("/home");
          }
        }
      );
    } else {
      this.$router.push("/login");
    }
  },
  created(){
  }
};
</script>
<style>

</style>
