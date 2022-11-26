<template>
 <div class="form">
     <h2>Transfer de fonds / Exportation</h2>
   <router-view/>
     <div class="infos">
      <select class="form-control" v-model="transfertEntrantDTO.envoi">
            <option value="">--Comptes--</option>
            <option
              v-for="(compte, index) in comptes"
              :key="index"
              :value="compte">
              {{ compte.idCompte }}
              {{ compte.banque }}
            </option>
          </select>
       <input
         type="number"
         id="tentacles"
         name="tentacles"
         min="1"
         max="10000"
         v-model="transfertEntrantDTO.montant"
       />
  </div>
       <div class="button2Transactions">
       <button @click="submit()" type="button" class="btn btn-success">Transferer</button>
     </div>

</div>
</template>
<script>
import TransactionsDataServices from "../services/TransactionsDataServices";
import UserService from "../services/utilisateurService"
import ConnexionDataServices from "@/services/ConnexionDataServices";

export default {
  name: "transactionDTO-item",
  data() {
    return {
      transfertEntrantDTO: {
        envoi:"",
        montant: 0,
      },
      comptes:[],
    }
  },
  methods: {
    submit() {
      TransactionsDataServices.export(this.userId, this.transfertEntrantDTO)
        .then((response) => {
          this.transfertEntrantDTO = {...response.data}
        },
            error => {
              this.loading = false;
              this.message =
                (error.response && error.response.data) ||
                error.message ||
                error.toString();
            }
          );
    },
    },
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
    onConnexionChange(event) {
      const src = event.target.id; // This should give you the id of the select that has fired the event
      const index = parseInt(src.replace("connexions.id", ""));
      this.requestItems[index].connexions.id = event.target.value;
      console.log(this.requestItems[index].connexions.id);
    },
    computed: {
    userId: function () {
      return this.$store.state.auth.user.idUtilisateur;
    },
  },
  mounted() {
    if (this.$store.state.auth.user !== null) {
      ConnexionDataServices.getHistoriqueCompte(this.userId)
      .then((response) => {
        this.comptes = {...response.data};
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

  };


</script>

<style>
.form{
  display: block;
}
.a{
  color: white;
 
}

</style>