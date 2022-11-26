<template>
 
  <div class="infos">
    
    <h1>Relier un compte bancaire ?</h1>
  
    <strong><p>Identifiant Compte Bancaire</p></strong>  
        <input
          type="text"
          id="idCompte"
          name="idCompte"
          v-model="compteBancaire.idCompte"
          />
       <strong><p>IBAN</p></strong>
        <input
          type="text"
          id="iban"
          name="iban"
          v-model="compteBancaire.iban"
        />
        <strong><p>BIC</p></strong>  
        <input
          type="text"
          id="bic"
          name="bic"
          v-model="compteBancaire.bic"/>
          <strong><p>Banque</p></strong>  
        <input
          type="text"
          id="banque"
          name="banque"
          v-model="compteBancaire.banque"/>
      <button type="button" v-on:click="createlink()" class="btn btn-primary btn-lg btn-block">Envoyer la demande</button>
    </div>
</template>
<script>
import UserService from "../services/utilisateurService"
import ConnexionDataServices from "@/services/ConnexionDataServices";
export default {
  name: "Banque-item",
  data() {
    return {
      compteBancaire: {
          idCompte: "",
          iban:"",
          bic:"",
          banque: "",
        },
      };
  },
  computed:{
    userId: function () {
      return this.$store.state.auth.user.idUtilisateur;
    },
  },
  methods:{
    createlink() {
      ConnexionDataServices.createLinkBank(this.userId, this.compteBancaire)
        .then((response) => {
          this.compteBancaire = {...response.data};
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
};
</script>
<style>
.infos{
  display: block;
}

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
