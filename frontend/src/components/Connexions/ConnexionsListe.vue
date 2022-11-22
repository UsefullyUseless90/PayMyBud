<template>
  <div class="table">
    <table>
      <tbody v-for="(connexion, index) in connexions" :key="index">
        <tr>
          <td>{{ connexion.idUtilisateur}}</td>
          <td>{{ connexion.nomPrenom }}</td>
        </tr>
      </tbody>
    </table>
  </div>
</template>
<script>

import UserService from "../../services/utilisateurService";
import ConnexionDataServices from "@/services/ConnexionDataServices";

export default {
  name: "connexions-item",
  data() {
    return {
      connexions: [],
    };
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
@import"../../style/reset.css";
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
  