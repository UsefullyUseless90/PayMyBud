<template>
  <div class="infos">
       <strong><p>Nom Prenom</p></strong>
        <input
          type="text"
          id="nomPrenom"
          name="nomPrenom"
          v-model="connexionDTO.demandeur.nomPrenom"
        />
      <strong><p>Identifiant Utilisateur</p></strong>  
        <input
          type="text"
          id="idUtilisateur"
          name="idUtilisateur"
          v-model="connexionDTO.demandeur.idUtilisateur"/>
      <button type="button" v-on:click="submitDemande()" onclick="myFunction()" class="btn btn-primary btn-lg btn-block">Envoyer la demande</button>
    </div>
</template>
<script>
import ConnexionDataServices from "@/services/ConnexionDataServices";
import UserService from "../../services/utilisateurService"

export default {
  name: "connexion-item",
  data() {
    return {
      connexionDTO: {
        demandeur: {
          nomPrenom: "",
          idUtilisateur: "",
        },
      },
      ConnexionDataServices,
    };
  },
  computed:{
    userId: function () {
      return this.$store.state.auth.user.idUtilisateur;
    },
  },
  methods:{
    submitDemande() {
      ConnexionDataServices.create(this.userId, this.connexionDTO)
        .then((response) => {
          this.connexionDTO = {...response.data};
        })
        error => {
              this.loading = false;
              this.message =
                (error.response && error.response.data) ||
                error.message ||
                error.toString();
              }          
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
};
</script>
<style>
.gestion_entete {
  font-weight: bold;
  font-size: x-large;
 /* margin-left: 500px;
  margin-right: 330px;*/
}

.infos {
 
  border-radius: 5px / 5px;
  margin-left: 20%;
}
.button{
  margin-top:  15px;
}
</style>
