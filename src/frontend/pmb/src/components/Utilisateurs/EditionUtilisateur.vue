<template>
  <div class="nav">
    <router-link to="/">Accueil</router-link> |
        <router-link to="/transaction/transactions">Transactions</router-link> |
        <router-link to="/editionProfil">Profil</router-link> |
        <router-link to="/connexions/nouvelleConnexion">Contact</router-link> |
        <router-link to="/logout">Déconnexion</router-link>|
        <router-view/>
      </div>
  <div class="container">
    <div class="row col-md-6 col-md-offset-3">
      <div class="card card-primary">
        <div class="card-heading text-center"></div>
        <h1>Informations Personnelles</h1>
      </div>
      <div class="card-body"></div>
      <form action="app.js" method="post">
        <div class="form-group">
          <label for="Nom"> Nom </label>
          <input type="text" class="form-control" id="nom" />
        </div>
      </form>
      <form>
        <div class="form-group">
          <label for="Prenom"> Prenom </label>
          <input type="text" class="form-control" id="prenom" />
        </div>
      </form>
      <form>
        <div class="form-group">
          <label for="AdresseEmail"> Addresse Email </label>
          <input type="text" class="form-control" id="adresseEmail" />
        </div>
      </form>
    </div>
    <div class="card-footer">
      <button onclick="myFunction()" @click="modification" class="btn btn-primary">Validation Changement Informations</button>
    </div>
  </div>
</template>

<script>
import UtilisateurDataServices from "../../services/UtilisateurDataServices";

export default {
  name: "edition-utilisateurDAO",
  data() {
    return {
      utilisateurDTO:{
        nomPrenom:"",

      }
    };
  },
  methods: {
    miseAJourUtilisateur() {
      UtilisateurDataServices.update(
        this.utilisateurActuel.id,
        this.utilisateurActuel
      )
        .then(() => {
          this.message =
            "La mise à jour de l'utilsateur à été réalisée avec succès!";
        })
        .catch((e) => {
          alert(e);
        });
    },
    deleteCustomer() {
      UtilisateurDataServices.delete(this.currentCustomer.id)
        .then(() => {
          this.$router.push({ name: "utilisateurs" });
        })
        .catch((e) => {
          alert(e);
        });
    },
    myFunction() {
      location.reload();
    }
  },
  mounted() {
    this.getUtilisateur(this.$route.params.id);
  },
};
</script>

<style scoped>
.container{
  margin-top: 5%;
margin-left: 40%;
font-weight: bold;
font-size: x-large;
display: block;
}
label{
  font-weight: bold;
  font-size: x-large;
  width: 300px;
}
input{
  width: 300px;
}
.nav{
  margin-left: 75%;
}
button{
  background-color: green;
  color: #fff;
  height: 50px;
  border: 1px green;
  border-radius: 20px / 20px;
  font-weight: bold;
  font-size: x-large;
  margin-top: 25px;
  width: 300px;
  height: 80px;
  margin-left: 2%;

}
</style>
