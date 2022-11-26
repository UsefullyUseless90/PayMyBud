<template>
  <div class="form">
      <div class="gestion_entete">
        <h2>Envoyer de l'argent</h2>
        <button type="button" class="btn btn-outline-success"><router-link to="/connexions">Ajouter contact</router-link></button>
        
        
      </div>
      <router-view/>
          <select class="form-control" v-model="transactionDTO.destinataire">
            <option value="">--Utilisateur--</option>
            <option
              v-for="(value, index) in connexions"
              :key="index"
              :value="value">
              {{ value.idUtilisateur }}
              {{ value.nomPrenom }}
            </option>
          </select>
          <input
            type="number"
            id="tentacles"
            name="tentacles"
            min="1"
            max="10000"
            v-model="transactionDTO.montant"
          />
          <input
            type="text"
            id="description"
            name="description"
            v-model="transactionDTO.description"
          />
          <input type="date" id="date" name="date" v-model="transactionDTO.date" />
          <div class="button2Transactions">
          <button @click="submit()" type="button" class="btn btn-success">Payer</button>
        </div>
  </div>
</template>

<script>
import TransactionsDataServices from "../../services/TransactionsDataServices";
import ConnexionDataServices from "@/services/ConnexionDataServices";
import Connexions from "../../views/Connexions.vue"
import UserService from "../../services/utilisateurService"

export default {
  name: "transactionDTO-item",
  data() {
    return {
      transactionDTO: {
        destinataire: {
          idUtilisateur: "",
          nomPrenom: "",
        },
        date: "",
        description: "",
        montant: 0,
      },
      Connexions,
      ConnexionDataServices,
      connexions: [],
      transactions: [],
    }
  },
  methods: {
    submit() {
      TransactionsDataServices.create(this.userId, this.transactionDTO)
        .then((response) => {
          this.transactionDTO = {...response.data}
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
      ConnexionDataServices.getHistoriqueConnexion(this.userId)
      .then((response) => {
        this.connexions = {...response.data};
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

.a{
  color: white;
 
}

</style>
